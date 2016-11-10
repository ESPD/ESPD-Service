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
import eu.europa.ec.grow.espd.util.EspdConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.configuration.DefaultConfigurationBuilder;
import org.apache.commons.io.IOUtils;
import org.apache.fop.apps.*;
import org.apache.xmlgraphics.io.Resource;
import org.apache.xmlgraphics.io.ResourceResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.xml.transform.*;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.net.URI;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.apache.commons.lang3.StringUtils.trimToEmpty;

/**
 * This class converts HTML pages to a PDF file using XSLT transformations and Apache FOP.
 */
@Component
@Slf4j
public class HtmlToPdfTransformer {

	private static final String XSL_CA = "xhtml2fo_tenderned_ca.xsl";
	private static final String XSL_EO = "xhtml2fo_tenderned_eo.xsl";

	private final TransformerFactory transformerFactory;
	private final XsltURIResolver resolver;
	private final FopFactory fopFactory;
	private final ResourceLoader resourceLoader;
	private final EspdConfiguration espdConfiguration;

	@Autowired
	HtmlToPdfTransformer(ResourceLoader resourceLoader, EspdConfiguration espdConfiguration) {
		this.resourceLoader = resourceLoader;
		this.espdConfiguration = espdConfiguration;
		this.transformerFactory = TransformerFactory.newInstance();
		this.resolver = new XsltURIResolver();
		transformerFactory.setURIResolver(resolver);
		this.fopFactory = buildFopFactory();
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
		String xsltLocation;

		if ("ca".equals(agent)) {
			xsltLocation = XSL_CA;
		} else {
			xsltLocation = XSL_EO;
		}

		try {
			// Setup a buffer to obtain the content length (empirical initial size)
			ByteArrayOutputStream out = new ByteArrayOutputStream(html.length() / 6);

			// Setup FOP
			Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, out);

			// Setup Transformer
			Source xsltSource = resolver.resolve(xsltLocation, null);
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

	private FopFactory buildFopFactory() {
		try {
			DefaultConfigurationBuilder cfgBuilder = new DefaultConfigurationBuilder();
			Configuration cfg = cfgBuilder
					.build(resourceLoader.getResource(espdConfiguration.getFopXmlConfigurationLocation())
					                     .getInputStream());
			URI defaultBaseURI = buildDefaultBaseUri();
			log.debug("--- apache.fop.defaultBaseUri: '{}'.", espdConfiguration.getFopDefaultBaseUri());
			log.debug("--- Computed base URI: '{}'.", defaultBaseURI);
			FopFactoryBuilder fopFactoryBuilder = new FopFactoryBuilder(
					defaultBaseURI, new EspdResourceResolver(resourceLoader))
					.setConfiguration(cfg);
			return fopFactoryBuilder.build();
		} catch (SAXException | IOException | ConfigurationException e) {
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * Embedded fonts can be loaded via absolute or relative paths or via classpath depending on the chosen strategy.
	 * When using an embedded server it is recommended to use the classpath approach.
	 *
	 * @return
	 * @throws IOException
	 */
	private URI buildDefaultBaseUri() throws IOException {
		String baseUri = trimToEmpty(espdConfiguration.getFopDefaultBaseUri());
		if (baseUri.startsWith("classpath:")) {
			return new ClassPathResource(baseUri.replace("classpath:", "")).getURI();
		}
		return new File(espdConfiguration.getFopDefaultBaseUri()).toURI();
	}

	private static class XsltURIResolver implements URIResolver {

		@Override
		public Source resolve(String href, String base) throws TransformerException {
			InputStream is = this.getClass().getClassLoader()
			                     .getResourceAsStream("tenderned/pdfrendering/xslt/" + href);
			return new StreamSource(is);
		}
	}

	/**
	 * A {@link ResourceResolver} which delegates to a Spring {@link ResourceLoader} for loading font information
	 * for Apache FOP, generally from application classpath, in a consistent and portable manner across different
	 * Servlet containers and application servers.
	 */
	private static class EspdResourceResolver implements ResourceResolver {

		private final ResourceLoader resourceLoader;

		private EspdResourceResolver(ResourceLoader resourceLoader) {
			this.resourceLoader = resourceLoader;
		}

		@Override
		public Resource getResource(URI uri) throws IOException {
			log.debug("--- Fop resource resolver get resource: '{}'.", uri);
			InputStream is = resourceLoader.getResource(uri.toASCIIString()).getInputStream();
			return new Resource(is);
		}

		@Override
		public OutputStream getOutputStream(URI uri) throws IOException {
			log.debug("--- Fop resource resolver get output stream: '{}'.", uri);
			return resourceLoader.getResource(uri.toASCIIString()).getURL().openConnection().getOutputStream();
		}
	}

}
