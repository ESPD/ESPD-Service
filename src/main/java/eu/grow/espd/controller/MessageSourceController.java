package eu.grow.espd.controller;

import com.google.gson.Gson;
import eu.grow.espd.util.EspdMessageSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Locale;

/**
 * Created by vigi on 10/22/15:10:10 AM.
 */
@Controller
class MessageSourceController {

    private final EspdMessageSource ms;

    @Autowired
    public MessageSourceController(final EspdMessageSource ms) {
        this.ms = ms;
    }

    @RequestMapping(value = "/translate", method = RequestMethod.POST)
    @ResponseBody
    public String translate(@RequestParam(value = "labels[]") String[] labels, @RequestParam String lang) {
        Locale locale = Locale.forLanguageTag(lang);
        for (int i = 0; i < labels.length; i++) {
            labels[i] = ms.getMessage(labels[i], null, locale);
        }
        //Map<String, Object> map = new HashMap<String, Object>();
        //map.put("labels", labels);
        //map.put("locale", locale.getLanguage());
        //map.put("datefmt", ((SimpleDateFormat)DateFormat.getDateInstance(DateFormat.MEDIUM, locale)).toLocalizedPattern());
        return new Gson().toJson(labels);
    }
}
