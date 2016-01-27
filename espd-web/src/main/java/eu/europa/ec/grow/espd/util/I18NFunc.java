package eu.europa.ec.grow.espd.util;

import org.springframework.context.MessageSource;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.jsp.PageContext;
import java.util.HashMap;
import java.util.Locale;

@SuppressWarnings("serial")
public class I18NFunc {

    private final HashMap<String, String> messageMap;
    private final HashMap<String, String> spanMessageMap;
    private final HashMap<String, String> divMessageMap;

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
                return new StringBuilder("<span data-i18n=\"")
                        .append((String) key)
                        .append("\">")
                        .append(ms.getMessage(key.toString(), null, locale))
                        .append("</span>")
                        .toString();
            }
        };
        divMessageMap = new HashMap<String, String>() {
            @Override
            public String get(Object key) {
                if (key == null) {
                    return "";
                }
                return new StringBuilder("<div data-i18n=\"")
                        .append((String) key)
                        .append("\">")
                        .append(ms.getMessage(key.toString(), null, locale))
                        .append("</div>")
                        .toString();
            }
        };
    }

    public HashMap<String, String> message() {
        return messageMap;
    }

    public HashMap<String, String> span() {
        return spanMessageMap;
    }

    public HashMap<String, String> div() {
        return divMessageMap;
    }
}
