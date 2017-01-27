/*
 *
 * Copyright 2016 EUROPEAN COMMISSION
 *
 * Licensed under the EUPL, Version 1.1 or – as soon they
 * will be approved by the European Commission - subsequent
 * versions of the EUPL (the "Licence");
 *
 * You may not use this work except in compliance with the Licence.
 *
 * You may obtain a copy of the Licence at:
 *
 * https://joinup.ec.europa.eu/community/eupl/og_page/eupl
 *
 * Unless required by applicable law or agreed to in
 * writing, software distributed under the Licence is
 * distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied.
 * See the Licence for the specific language governing
 * permissions and limitations under the Licence.
 *
 */

package eu.europa.ec.grow.espd.util;

import eu.europa.ec.grow.espd.domain.EspdDocument;
import eu.europa.ec.grow.espd.tenderned.HtmlToPdfTransformer;
import eu.europa.ec.grow.espd.tenderned.exception.PdfRenderingException;
import eu.europa.ec.grow.espd.tenderned.exception.ZipException;
import eu.europa.ec.grow.espd.xml.EspdXmlExporter;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by ratoico on 1/23/17.
 */
@Component
public class EspdExporter {

	private final EspdXmlExporter xmlExporter;

	private final HtmlToPdfTransformer pdfExporter;

	private final ResourceLoader resourceLoader;

	@Autowired
	EspdExporter(EspdXmlExporter xmlExporter, HtmlToPdfTransformer pdfExporter, ResourceLoader resourceLoader) {
		this.xmlExporter = xmlExporter;
		this.pdfExporter = pdfExporter;
		this.resourceLoader = resourceLoader;
	}

	public ByteArrayOutputStream exportAsXml(EspdDocument espdDocument, String agent) {
		if ("eo".equals(agent)) {
			return xmlExporter.generateEspdResponse(espdDocument);
		} else {
			return xmlExporter.generateEspdRequest(espdDocument);
		}
	}

	public ByteArrayOutputStream exportAsPdf(EspdDocument espdDocument, String agent) throws PdfRenderingException {
		return pdfExporter.convertToPDF(espdDocument.getHtml(), agent);
	}

	public ByteArrayOutputStream exportAsZip(EspdDocument espdDocument, String agent)
			throws PdfRenderingException, IOException {
		try (ByteArrayOutputStream pdfOutput = pdfExporter.convertToPDF(espdDocument.getHtml(), agent);
				ByteArrayOutputStream xmlOutput = exportAsXml(espdDocument, agent);
				InputStream readMeStream = resourceLoader.getResource("classpath:zipInstructions.txt")
				                                         .getInputStream()) {
			return zip(xmlOutput, pdfOutput, readMeStream, agent);
		}
	}

	private ByteArrayOutputStream zip(ByteArrayOutputStream xmlOut, ByteArrayOutputStream pdfOut,
			InputStream readmeStream, String agent) {
		String fileName = "ca".equalsIgnoreCase(agent) ? "espd-request" : "espd-response";
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ZipOutputStream zipStream = new ZipOutputStream(baos)) {
			addOutputStreamToZipFile(xmlOut, fileName + ".xml", zipStream);
			addOutputStreamToZipFile(pdfOut, fileName + ".pdf", zipStream);
			addInputStreamToZipFile(readmeStream, "README.txt", zipStream);
			return baos;
		} catch (IOException e) {
			throw new ZipException("Error creating the ESPD archive.", e);
		}
	}

	private void addOutputStreamToZipFile(ByteArrayOutputStream out, String fileName, ZipOutputStream zipStream)
			throws IOException {
		ZipEntry zipEntry = new ZipEntry(fileName);
		zipStream.putNextEntry(zipEntry);
		out.writeTo(zipStream);
		zipStream.closeEntry();
	}

	private void addInputStreamToZipFile(InputStream inputStream, String fileName, ZipOutputStream zipStream)
			throws IOException {
		ZipEntry zipEntry = new ZipEntry(fileName);
		zipStream.putNextEntry(zipEntry);
		IOUtils.copy(inputStream, zipStream);
		zipStream.closeEntry();
	}
}
