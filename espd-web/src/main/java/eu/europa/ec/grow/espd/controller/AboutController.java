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

import eu.europa.ec.grow.espd.util.EspdConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by vigi on 10/21/15:2:31 PM.
 */
@Controller
class AboutController {

    private final EspdConfiguration espdConfiguration;

    @Autowired
    AboutController(final EspdConfiguration espdConfiguration) {
        this.espdConfiguration = espdConfiguration;
    }

    @RequestMapping("/about")
    String showAboutPage(Model model) {
        model.addAttribute("buildVersion", espdConfiguration.getBuildVersion());
        model.addAttribute("lastBuildDate", espdConfiguration.getLastBuildDate());
        model.addAttribute("environment", espdConfiguration.getActiveProfile());
        return "about";
    }
}
