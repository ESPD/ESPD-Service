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

package eu.europa.ec.grow.espd.tenderned;

import eu.europa.ec.grow.espd.tenderned.exception.PdfRenderingException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.transform.*;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * This class converts HTML pages to a PDF file using XSLT transformations and Apache FOP.
 */
@Component
@Slf4j
public class HtmlToPdfTransformer {

	private static final String XSL_CA = "xhtml2fo_tenderned_ca.xsl";
	private static final String XSL_EO = "xhtml2fo_tenderned_eo.xsl";

	private final FopFactory fopFactory;
	private final TransformerFactory transformerFactory;
	private final XsltURIResolver xsltURIResolver;

	@Autowired
	HtmlToPdfTransformer(FopFactory fopFactory, TransformerFactory transformerFactory, XsltURIResolver uriResolver) {
		this.fopFactory = fopFactory;
		this.transformerFactory = transformerFactory;
		this.xsltURIResolver = uriResolver;
	}

	/**
	 * Method that will convert the given HTML code of a page to PDF.
	 *
	 * @param html  is a String of html content
	 * @param agent is a String, can be 'ca' or 'eo'
	 *
	 * @throws PdfRenderingException In case an exception occurred
	 */
	public ByteArrayOutputStream convertToPDF(String html, String agent) throws PdfRenderingException {
		String xsltLocation = "ca".equalsIgnoreCase(agent) ? XSL_CA : XSL_EO;

		try {
			// Setup a buffer to obtain the content length (empirical initial size)
			ByteArrayOutputStream out = new ByteArrayOutputStream(html.length() / 6);

			// Setup FOP
			Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, out);

			// Setup Transformer
			Source xsltSource = xsltURIResolver.resolve(xsltLocation, null);
			Transformer transformer = transformerFactory.newTransformer(xsltSource);

			// Make sure the XSL transformation's result is piped through to FOP
			Result res = new SAXResult(fop.getDefaultHandler());

			// Setup input
			InputStream htmlInputStream = IOUtils.toInputStream(html, UTF_8.name());
			StreamSource source = new StreamSource(htmlInputStream);

			// Start the transformation and rendering process
			transformer.transform(source, res);

			return out;
		} catch (TransformerException | FOPException | IOException e) {
			throw new PdfRenderingException("Something went wrong while generating the PDF file.", e);
		}
	}

}
