package eu.europa.ec.grow.espd.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import eu.europa.ec.grow.espd.util.EspdMessageSource;

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
    private final ObjectMapper mapper;

    @Autowired
    public MessageSourceController(final EspdMessageSource ms) {
        this.ms = ms;
        this.mapper = new ObjectMapper();
    }

    @RequestMapping(value = "/translate", method = RequestMethod.POST)
    @ResponseBody
    public String translate(@RequestParam(value = "labels[]") String[] labels, @RequestParam String lang) throws JsonProcessingException {
        Locale locale = Locale.forLanguageTag(lang);
        for (int i = 0; i < labels.length; i++) {
            labels[i] = ms.getMessage(labels[i], null, locale);
        }
        return mapper.writeValueAsString(labels);
    }
}
