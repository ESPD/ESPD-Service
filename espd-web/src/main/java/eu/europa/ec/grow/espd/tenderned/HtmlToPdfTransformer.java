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
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.configuration.DefaultConfigurationBuilder;
import org.apache.commons.io.IOUtils;
import org.apache.fop.apps.*;
import org.apache.xmlgraphics.io.Resource;
import org.apache.xmlgraphics.io.ResourceResolver;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.xml.transform.*;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.net.URI;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * This class converts html to pdf file
 */
@Component
public class HtmlToPdfTransformer {

	private static final String XSL_CA = "xhtml2fo_tenderned_ca.xsl";
	private static final String XSL_EO = "xhtml2fo_tenderned_eo.xsl";

	private final TransformerFactory transformerFactory;
	private final XsltURIResolver resolver;
	private final FopFactory fopFactory;

	public HtmlToPdfTransformer() {
		transformerFactory = TransformerFactory.newInstance();
		resolver = new XsltURIResolver();
		transformerFactory.setURIResolver(resolver);

		try {
			DefaultConfigurationBuilder cfgBuilder = new DefaultConfigurationBuilder();
			Configuration cfg = cfgBuilder.build(new ClassPathResource("/tenderned/pdfrendering/fop/fop-config.xml").getInputStream());
			FopFactoryBuilder fopFactoryBuilder = new FopFactoryBuilder(new File("./espd-web/src/main/resources").toURI()).setConfiguration(cfg);
//			fopFactory = FopFactory.newInstance(initFopConfiguration());
			fopFactory = fopFactoryBuilder.build();
		} catch (SAXException | IOException | ConfigurationException e) {
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * Method that will convert the given HTML code of a page to PDF.
	 *
	 * @param html  is a String of html content
	 * @param agent is a String, can be 'ca' or 'eo'
	 *
	 * @return a {@link ByteArrayOutputStream} object
	 *
	 * @throws PdfRenderingException In case an exception occurred
	 */
	public OutputStream convertToPDF(String html, String agent, OutputStream out) throws PdfRenderingException {
		String xsl;

		if ("ca".equals(agent)) {
			xsl = XSL_CA;
		} else {
			xsl = XSL_EO;
		}
		FopFactory fopFactory;
		try {
			DefaultConfigurationBuilder cfgBuilder = new DefaultConfigurationBuilder();
			Configuration cfg = cfgBuilder
					.build(new ClassPathResource("/tenderned/pdfrendering/fop/fop-config.xml").getInputStream());
//			FopFactoryBuilder fopFactoryBuilder = new FopFactoryBuilder(
//					new ClassPathResource("/tenderned/pdfrendering/").getURI()/*, new EspdResourceResolver()*/)
//					.setConfiguration(cfg);
			FopFactoryBuilder fopFactoryBuilder = new FopFactoryBuilder(new File("./espd-web/src/main/resources").toURI()).setConfiguration(cfg);
			fopFactory = fopFactoryBuilder.build();
		} catch (SAXException | IOException | ConfigurationException e) {
			throw new IllegalArgumentException(e);
		}

		try {
			Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, out);
			Result res = new SAXResult(fop.getDefaultHandler());

			Source xslSource = resolver.resolve(xsl, null);
			Transformer xhtml2foTransformer = transformerFactory.newTransformer(xslSource);

			InputStream htmlInputStream = IOUtils.toInputStream(html, UTF_8.name());
			StreamSource xhtmlSource = new StreamSource(htmlInputStream);
			xhtml2foTransformer.transform(xhtmlSource, res);
			return out;
		} catch (TransformerException | FOPException | IOException e) {
			throw new PdfRenderingException("Something went wrong while generating PDF file.", e);
		}
	}

	private static class XsltURIResolver implements URIResolver {

		@Override
		public Source resolve(String href, String base) throws TransformerException {
			InputStream inputStream = this.getClass().getClassLoader()
			                              .getResourceAsStream("tenderned/pdfrendering/xslt/" + href);
			return new StreamSource(inputStream);
		}
	}

	private static class EspdResourceResolver implements ResourceResolver {

		@Override
		public Resource getResource(URI uri) throws IOException {
			System.out.println("--------- get resource: " + uri);
			int aaa = uri.toASCIIString().lastIndexOf("/tenderned/pdfrendering/");
			String res = uri.toASCIIString().substring(aaa);
			InputStream is = new ClassPathResource(res).getInputStream();
			return new Resource(is);
		}

		@Override
		public OutputStream getOutputStream(URI uri) throws IOException {
			System.out.println("--------- get outputstream: " + uri);
			return new ClassPathResource(uri.toASCIIString()).getURL().openConnection().getOutputStream();
			//			URL url = servletContext.getResource(uri.toASCIIString());
			//			return url.openConnection().getOutputStream();
		}
	}
}
