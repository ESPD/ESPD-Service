package eu.europa.ec.grow.espd.util;

import java.util.HashMap;
import java.util.Locale;

import javax.servlet.jsp.PageContext;

import org.springframework.context.MessageSource;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

@SuppressWarnings("serial")
public class I18NFunc {

	private MessageMap messageMap;
	
	public I18NFunc(PageContext pageContext) {
		WebApplicationContext springContext = WebApplicationContextUtils.getWebApplicationContext(pageContext.getServletContext());
		MessageSource ms = (MessageSource) springContext.getBean("messageSource");
		Locale locale = pageContext.getResponse().getLocale();
		messageMap = new MessageMap(ms, locale);
	}

    public HashMap<String, String> message() {
		return messageMap;
    }

	class MessageMap extends HashMap<String, String> {

		private MessageSource ms;
		private Locale locale;
		
		public MessageMap(MessageSource ms, Locale locale) {
			this.ms = ms;
			this.locale = locale;
		}
		
		@Override
	    public synchronized String get(Object key) {
			if(key == null) {
				return "";
			}
	        return ms.getMessage(key.toString(), null, locale);
	    }
		 
	}
}
