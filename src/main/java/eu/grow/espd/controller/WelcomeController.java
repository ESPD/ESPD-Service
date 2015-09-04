package eu.grow.espd.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import eu.grow.espd.config.Locales;

@Controller
public class WelcomeController {
	
	@RequestMapping({ "/", "/welcome" })
	public String showWelcomePage(Map<String, Object> model) {
		return "welcome";
	}

}
