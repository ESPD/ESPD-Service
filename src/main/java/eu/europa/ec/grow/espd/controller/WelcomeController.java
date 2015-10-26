package eu.europa.ec.grow.espd.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import eu.europa.ec.grow.espd.domain.EspdDocument;

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
	
	@RequestMapping(value="/filter", method=RequestMethod.POST)
	public String postEOFilterPage(@RequestParam String action, @RequestParam String whoareyou, @ModelAttribute("espd") EspdDocument espd, @RequestParam(required=false) MultipartFile attachment, Map<String, Object> model) throws JAXBException, IOException {
		if("eo_import_espd".equals(action)) {
			JAXBContext jaxbContext = JAXBContext.newInstance(EspdDocument.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			espd = (EspdDocument)jaxbUnmarshaller.unmarshal(attachment.getInputStream());
			espd.setAction(action);
			espd.setAgent(whoareyou);
			model.put("espd", espd);

			return "redirect:/procedure";
		}
		else if("ca_create_espd".equals(action)) {
			espd.setAgent(whoareyou);
			return "redirect:/procedure";
		}
		
		return null;
	}
	
	// Create : page 1

	@RequestMapping("/procedure")
	public String showCreatePage(@ModelAttribute("espd") EspdDocument espd) {
		return "procedure";
	}

	
	@RequestMapping(value="/procedure", method=RequestMethod.POST)
	public String postCreatePage(@ModelAttribute("espd") EspdDocument espd) {

		return espd.getIsEO() ? "redirect:/exclusionEO" : "redirect:/exclusionCA";
	}
	
	// Exclusion : page 2
	
	@RequestMapping({"/exclusionCA","/exclusionEO"})
	public String showExcludeCAPage(@ModelAttribute("espd") EspdDocument espd) {
		return espd.getIsEO() ? "exclusionEO" : "exclusionCA";
	}

	@RequestMapping(value={"/exclusionCA","/exclusionEO"}, method=RequestMethod.POST, params="next")
	public String postNextExcludePage(@ModelAttribute("espd") EspdDocument espd) {
		return espd.getIsEO() ? "redirect:/selectionEO" : "redirect:/selectionCA";
	}
	
	@RequestMapping(value={"/exclusionCA","/exclusionEO"}, method=RequestMethod.POST, params="prev")
	public String postPrevExcludePage(@ModelAttribute("espd") EspdDocument espd) {
		return "redirect:/procedure";
	}

	// Selection : page 3
	
	@RequestMapping({"/selectionCA","/selectionEO"})
	public String showSelectCAPage(@ModelAttribute("espd") EspdDocument espd) {
		return espd.getIsEO() ? "selectionEO" : "selectionCA";
	}

	@RequestMapping(value={"/selectionCA","/selectionEO"}, method=RequestMethod.POST, params="next")
	public String postNextSelectPage(@ModelAttribute("espd") EspdDocument espd) {
		return "redirect:/finish";
	}
	
	@RequestMapping(value={"/selectionCA","/selectionEO"}, method=RequestMethod.POST, params="prev")
	public String postPrevSelectPage(@ModelAttribute("espd") EspdDocument espd) {
		return espd.getIsEO() ? "redirect:/exclusionEO" : "redirect:/exclusionCA";
	}

	// Finish : Page 4

	@RequestMapping("/finish")
	public String showFinishCAPage(@ModelAttribute("espd") EspdDocument espd) {
		return "finish";
	}

	@RequestMapping(value="/finish", method=RequestMethod.POST)
	public String postProcessCAFinishPage(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("espd") EspdDocument espd, SessionStatus status) throws JAXBException, IOException {

		JAXBContext jaxbContext = JAXBContext.newInstance(EspdDocument.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		
		response.setHeader("Content-Disposition", "attachment;filename=espd.xml");
		response.setContentType("application/octet-stream");

		ServletOutputStream out = response.getOutputStream();

		jaxbMarshaller.marshal(espd, out);
		
		out.flush();
		out.close();

		status.setComplete();

		return null;
	}

}
