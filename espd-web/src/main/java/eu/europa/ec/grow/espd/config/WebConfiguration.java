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

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.HttpEncodingProperties;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.web.OrderedCharacterEncodingFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.resource.ResourceUrlEncodingFilter;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

import net.bull.javamelody.MonitoringFilter;
import net.bull.javamelody.Parameter;

@Configuration
class WebConfiguration extends WebMvcConfigurerAdapter {

	@Autowired
	private HttpEncodingProperties properties;
	
    @Bean
    UrlBasedViewResolver viewResolver() {
        UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
        viewResolver.setViewClass(TilesView.class);
        viewResolver.setExposeContextBeansAsAttributes(true);
        viewResolver.setExposedContextBeanNames("locales");
        return viewResolver;
    }

    @Bean
    TilesConfigurer tilesConfigurer() {
        TilesConfigurer tilesConfig = new TilesConfigurer();
        tilesConfig.setDefinitions("classpath:tiles.xml");
        return tilesConfig;
    }

    @Bean
    LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setIgnoreInvalidLocale(true);
        lci.setParamName("lang");
        return lci;
    }

    @Bean
    LocaleResolver localeResolver() {
        CookieLocaleResolver resolver = new CookieLocaleResolver();
        resolver.setCookieName("ESPD_LOCALE");
        resolver.setDefaultLocale(Locale.ENGLISH);
        return resolver;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

    /**
     * If the template engine you are using calls the response encodeURL() method, the version information
     * will be automatically added to the URL of the static resources that will be cached.
     * This will work in JSPs in conjunction with spring:url tag.
     * <p>It needs to be mapped on '/*'.</p>
     *
     * @return
     */
    @Bean
    ResourceUrlEncodingFilter resourceUrlEncodingFilter() {
        return new ResourceUrlEncodingFilter();
    }

    @Bean
    MonitoringFilter melodyMonitoringFilter() {
        return new MonitoringFilter();
    }

    @Bean
    FilterRegistrationBean melodyFilterRegistration(MonitoringFilter melodyFilter) {
        FilterRegistrationBean frb = new FilterRegistrationBean(melodyFilter);
        frb.addInitParameter(Parameter.NO_DATABASE.getCode(), "true");
        frb.addInitParameter(Parameter.ALLOWED_ADDR_PATTERN.getCode(),
                "(158\\.16[6-8]\\..*)|(127\\.0\\.0\\.1)|(localhost)");
        frb.addInitParameter(Parameter.URL_EXCLUDE_PATTERN.getCode(), "(/img/.*)|(/js/.*)|(/css/.*)|(.*/.woff)");
        return frb;
    }

    /*
     * A workaround for the problem is that OrderedCharacterEncodingFilter is running after HiddenHttpMethodFilter. 
     * HiddenHttpMethodFilter triggers processing of the request body as it calls getParameter on the request. 
     * OrderedCharacterEncodingFilter then runs and sets the request's encoding. 
     * Setting the request's encoding after its body has been processed is bad and, on WebLogic, 
     * causes the request to lose track of all its multipart data.
     * 
     * This causes issues with the HiddenHttpMethodFilter. If this one is registered first, it already accesses 
     * the request parameters before the encoding is enforced by the CharacterEncodingFilter
     * and the request data is already in the wrong format.
     * 
     * https://github.com/spring-projects/spring-boot/issues/2862
     * https://github.com/spring-projects/spring-boot/issues/2148
     */
    @Bean
    @ConditionalOnProperty("weblogic.Name")
    OrderedCharacterEncodingFilter characterEncodingFilter() {
    	OrderedCharacterEncodingFilter filter = new OrderedCharacterEncodingFilter();
    	filter.setEncoding(this.properties.getCharset().name());
    	filter.setForceEncoding(this.properties.isForce());
        filter.setOrder(Ordered.LOWEST_PRECEDENCE);
    	return filter;
    }
    
}
