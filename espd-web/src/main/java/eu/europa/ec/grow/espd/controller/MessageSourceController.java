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

package eu.europa.ec.grow.espd.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Locale;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * Created by vigi on 10/22/15:10:10 AM.
 */
@Controller
class MessageSourceController {

    private final MessageSource ms;
    private final ObjectMapper mapper;

    @Autowired
    MessageSourceController(MessageSource ms, ObjectMapper mapper) {
        this.ms = ms;
        this.mapper = mapper;
    }

    @RequestMapping(value = {"/translate", "/{flow:request|response}/{agent:ca|eo}/translate"}, method = RequestMethod.POST)
    @ResponseBody public String translate(@RequestParam(value = "labels[]") String[] labels, @RequestParam String lang) throws JsonProcessingException {
        Locale locale = Locale.forLanguageTag(lang);
        for (int i = 0; i < labels.length; i++) {
            if (isBlank(labels[i])) {
                continue;
            }
            labels[i] = ms.getMessage(labels[i], null, locale);
        }
        // TODO use MessageSourceResolvable to retrieve all labels at once
        return mapper.writeValueAsString(labels);
    }
}
