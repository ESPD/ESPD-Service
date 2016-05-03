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

package eu.europa.ec.grow.espd.util;

import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
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
                return loadMessageValue(key, ms, locale);
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
                        .append(loadMessageValue(key, ms, locale))
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
                        .append(loadMessageValue(key, ms, locale))
                        .append("</div>")
                        .toString();
            }
        };
    }

    private static String loadMessageValue(Object key, MessageSource ms, Locale locale) {
        try {
            return ms.getMessage(key.toString(), null, locale);
        } catch (NoSuchMessageException e) {
            return "? " + key;
        }
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
