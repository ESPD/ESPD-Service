package eu.grow.espd.controller;

import eu.grow.espd.util.EspdConfiguration;
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
