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

package eu.europa.ec.grow.espd.config;

import eu.europa.ec.grow.espd.util.EspdConfiguration;
import eu.europa.ec.grow.espd.tenderned.XsltURIResolver;
import lombok.extern.slf4j.Slf4j;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.configuration.DefaultConfigurationBuilder;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.FopFactoryBuilder;
import org.apache.xmlgraphics.io.Resource;
import org.apache.xmlgraphics.io.ResourceResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.xml.sax.SAXException;

import javax.xml.transform.TransformerFactory;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

import static org.apache.commons.lang3.StringUtils.trimToEmpty;

/**
 * Configuration class for setting up Apache FOP which is used to generate PDFs from the overview page of an ESPD entity.
 * <p>
 * Created by ratoico on 11/10/16.
 */
@Configuration
@Slf4j
class ApacheFopConfig {

	private final ResourceLoader resourceLoader;
	private final EspdConfiguration espdConfiguration;

	@Autowired
	ApacheFopConfig(ResourceLoader resourceLoader, EspdConfiguration espdConfiguration) {
		this.resourceLoader = resourceLoader;
		this.espdConfiguration = espdConfiguration;
	}

	@Bean
	FopFactory fopFactory() {
		try {
			DefaultConfigurationBuilder cfgBuilder = new DefaultConfigurationBuilder();
			org.apache.avalon.framework.configuration.Configuration cfg = cfgBuilder
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

	@Bean
	XsltURIResolver xsltURIResolver() {
		return new XsltURIResolver();
	}

	@Bean
	TransformerFactory xmlTransformerFactory(XsltURIResolver resolver) {
		TransformerFactory factory = TransformerFactory.newInstance();
		factory.setURIResolver(resolver);
		return factory;
	}

	/**
	 * Embedded fonts can be loaded via absolute or relative paths or via classpath depending on the chosen strategy.
	 * When using an embedded server it is recommended to use the classpath approach.
	 *
	 * @return
	 *
	 * @throws IOException
	 */
	private URI buildDefaultBaseUri() throws IOException {
		String baseUri = trimToEmpty(espdConfiguration.getFopDefaultBaseUri());
		if (baseUri.startsWith("classpath:")) {
			return new ClassPathResource(baseUri.replace("classpath:", "")).getURI();
		}
		return new File(espdConfiguration.getFopDefaultBaseUri()).toURI();
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
			log.trace("--- Fop resource resolver get resource: '{}'.", uri);
			InputStream is = resourceLoader.getResource(uri.toASCIIString()).getInputStream();
			return new Resource(is);
		}

		@Override
		public OutputStream getOutputStream(URI uri) throws IOException {
			log.trace("--- Fop resource resolver get output stream: '{}'.", uri);
			return resourceLoader.getResource(uri.toASCIIString()).getURL().openConnection().getOutputStream();
		}
	}
}
