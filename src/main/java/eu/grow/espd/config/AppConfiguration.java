package eu.grow.espd.config;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

import com.google.gson.Gson;

@Configuration
@EnableWebMvc
public class AppConfiguration extends WebMvcConfigurerAdapter {

	@Bean
	public UrlBasedViewResolver viewResolver() {
		UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
		viewResolver.setViewClass(TilesView.class);
		viewResolver.setExposeContextBeansAsAttributes(true);
		viewResolver.setExposedContextBeanNames("locales");
		return viewResolver;
	}

	@Bean
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer tilesConfig = new TilesConfigurer();
		tilesConfig.setDefinitions("/WEB-INF/tiles.xml");
		tilesConfig.setCheckRefresh(true);
		return tilesConfig;
	}

	@Bean
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(5 * 1024 * 1024);
		return multipartResolver;
	}
	
	@Controller
	static public class MessageSourceController {
		
		@Autowired EspdMessageSource ms;

		@RequestMapping(value="/translate", method = RequestMethod.POST)
		public @ResponseBody String translate(@RequestParam(value="labels[]") String[] labels, @RequestParam String lang) {
			Locale locale = Locale.forLanguageTag(lang);
			for(int i = 0 ; i < labels.length; i++) {
				labels[i] = ms.getMessage(labels[i], null, locale);
			}
			//Map<String, Object> map = new HashMap<String, Object>();
			//map.put("labels", labels);
			//map.put("locale", locale.getLanguage());
			//map.put("datefmt", ((SimpleDateFormat)DateFormat.getDateInstance(DateFormat.MEDIUM, locale)).toLocalizedPattern());
			return new Gson().toJson(labels);
		}
	}
}
