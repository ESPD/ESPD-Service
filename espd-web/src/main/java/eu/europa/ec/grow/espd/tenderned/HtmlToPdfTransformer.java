package eu.europa.ec.grow.espd.tenderned;

import eu.europa.ec.grow.espd.tenderned.exception.PdfRenderingException;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.configuration.DefaultConfigurationBuilder;
import org.apache.commons.io.IOUtils;
import org.apache.fop.apps.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.xml.transform.*;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

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

		File f = new File(".");
		System.out.println("+++++++++++ " + f.getAbsolutePath());
		File fopConfig = new File(".");
		//		fopFactory = FopFactory.newInstance(initFopConfiguration());
		try {
			DefaultConfigurationBuilder cfgBuilder = new DefaultConfigurationBuilder();
			Configuration cfg = cfgBuilder.build(new ClassPathResource("/tenderned/pdfrendering/fop/fop-config.xml").getInputStream());
			FopFactoryBuilder fopFactoryBuilder = new FopFactoryBuilder(new File(".").toURI()).setConfiguration(cfg);
//			fopFactory = FopFactory.newInstance(initFopConfiguration());
			fopFactory = fopFactoryBuilder.build();
		} catch (SAXException | IOException | ConfigurationException e) {
			throw new IllegalArgumentException(e);
		}
	}

	private URI initFopConfiguration() {
		URI fopConfigURI;
		try {
			fopConfigURI = this.getClass().getResource("/tenderned/pdfrendering/fop/fop-config.xml").toURI();
		} catch (URISyntaxException e) {
			throw new IllegalArgumentException("Could not load fop config.", e);
		}
		return fopConfigURI;
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
}
