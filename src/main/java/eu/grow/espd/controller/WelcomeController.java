package eu.grow.espd.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import eu.grow.espd.domain.EspdDocument;

@Controller
@SessionAttributes("espd")
public class WelcomeController {

	@ModelAttribute("espd")
    public EspdDocument newDocument() {
		return new EspdDocument();
	}

	@RequestMapping({ "/", "/welcome" })
	public String showWelcomePage(Map<String, Object> model) {
		return "welcome";
	}
	
	@RequestMapping("/splash")
	public String showSplashPage(Map<String, Object> model) {
		return "splash";
	}
	
	@RequestMapping("/filter")
	public String showFilterPage(Map<String, Object> model) {
		
		return "filter";
	}
	
	@RequestMapping(value="/createca")
	public String showProcessCAPage(@ModelAttribute("espd") EspdDocument espd, Map<String, Object> model) {
		
		return "createca";
	}
	
	@RequestMapping(value="/createca", method=RequestMethod.POST)
	public String postProcessCAPage(@ModelAttribute("espd") EspdDocument espd, Map<String, Object> model) {

		return "redirect:/createcaexcl";
	}

	@RequestMapping("/createcaexcl")
	public String showProcessCAExcludePage(@ModelAttribute("espd") EspdDocument espd, Map<String, Object> model) {

		return "createcaexcl";
	}
	
	@RequestMapping(value="/createcaexcl", method=RequestMethod.POST, params="prev")
	public String postPrevProcessCAExcludePage(@ModelAttribute("espd") EspdDocument espd, Map<String, Object> model) {

		return "redirect:/createca";
	}
	@RequestMapping(value="/createcaexcl", method=RequestMethod.POST, params="next")
	public String postNextProcessCAExcludePage(@ModelAttribute("espd") EspdDocument espd, Map<String, Object> model) {

		return "redirect:/createcasel";
	}
	
	@RequestMapping("/createcasel")
	public String showProcessCASelectionPage(@ModelAttribute("espd") EspdDocument espd, Map<String, Object> model) {

		return "createcasel";
	}
	
	@RequestMapping("/createcafinish")
	public String showProcessCAFinishPage(@ModelAttribute("espd") EspdDocument espd, Map<String, Object> model) {

		return "createcafinish";
	}
	
	@RequestMapping(value="/createcafinish", method=RequestMethod.POST)
	public String postProcessCAFinishPage(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("espd") EspdDocument espd, Map<String, Object> model) throws JAXBException, IOException {

		JAXBContext jaxbContext = JAXBContext.newInstance(EspdDocument.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		
		response.setHeader("Content-Disposition", "attachment;filename=espd.xml");
		response.setContentType("application/octet-stream");

		ServletOutputStream out = response.getOutputStream();

		jaxbMarshaller.marshal(espd, out);
		
		out.flush();
		out.close();

		return null;
	}

}
