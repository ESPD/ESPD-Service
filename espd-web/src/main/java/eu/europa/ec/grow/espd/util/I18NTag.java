package eu.europa.ec.grow.espd.util;

import java.util.HashMap;
import java.util.Locale;

import javax.servlet.jsp.PageContext;

import org.springframework.context.MessageSource;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class I18NTag extends HashMap<String, String> {
	
	private MessageSource ms;
	private Locale locale;
	
	public I18NTag(PageContext pageContext) {
		WebApplicationContext springContext = WebApplicationContextUtils.getWebApplicationContext(pageContext.getServletContext());
		ms = (MessageSource) springContext.getBean("messageSource");
		locale = pageContext.getResponse().getLocale();
	}

	@Override
    public synchronized String get(Object key) {
		if(key == null) {
			return "";
		}
        return ms.getMessage(key.toString(), null, locale);
    }
	 
}
