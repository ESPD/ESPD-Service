package eu.europa.ec.grow.espd.util;

import org.springframework.context.MessageSource;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.jsp.PageContext;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@SuppressWarnings("serial")
public class I18NFunc {

    private final Map<String, String> messageMap;
    private final Map<String, String> spanMessageMap;
    private final Map<String, String> divMessageMap;

    public I18NFunc(PageContext pageContext) {
        WebApplicationContext springContext = WebApplicationContextUtils
                .getWebApplicationContext(pageContext.getServletContext());
        final MessageSource ms = (MessageSource) springContext.getBean("messageSource");
        final Locale locale = pageContext.getResponse().getLocale();
        messageMap = new HashMap<String, String>() {
            @Override
            public String get(Object key) {
                if (key == null) {
                    return "";
                }
                return ms.getMessage(key.toString(), null, locale);
            }
        };
        spanMessageMap = new HashMap<String, String>() {
            @Override
            public String get(Object key) {
                if (key == null) {
                    return "";
                }
                return "<span data-i18n=\"" + key + "\">" + ms.getMessage(key.toString(), null, locale) + "</span>";
            }
        };
        divMessageMap = new HashMap<String, String>() {
            @Override
            public String get(Object key) {
                if (key == null) {
                    return "";
                }
                return "<div data-i18n=\"" + key + "\">" + ms.getMessage(key.toString(), null, locale) + "</div>";
            }
        };
    }

    public Map<String, String> message() {
        return messageMap;
    }

    public Map<String, String> span() {
        return spanMessageMap;
    }

    public Map<String, String> div() {
        return divMessageMap;
    }
}
