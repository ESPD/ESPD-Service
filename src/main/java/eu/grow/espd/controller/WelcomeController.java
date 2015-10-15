package eu.grow.espd.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import eu.grow.espd.domain.EspdDocument;
import grow.names.specification.ubl.schema.xsd.e_certiscriteria_1.CriteriaType;
import grow.names.specification.ubl.schema.xsd.e_certiscriteria_1.CriteriaTypes;

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
	
	@RequestMapping("/createcasel")
	public String showProcessCASelectionPage(@ModelAttribute("espd") EspdDocument espd, Map<String, Object> model) {

		return "createcasel";
	}
	
	@RequestMapping("/createcafinish")
	public String showProcessCAFinishPage(@ModelAttribute("espd") EspdDocument espd, Map<String, Object> model) {

		return "createcafinish";
	}
	
	@RequestMapping("/test")
	public String test() throws JAXBException {
		
		InputStream file = this.getClass().getClassLoader().getResourceAsStream("criteria.xml");
		JAXBContext jaxbContext = JAXBContext.newInstance(grow.names.specification.ubl.schema.xsd.e_certiscriteria_1.CriteriaType.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		CriteriaType criteriaType = (CriteriaType) jaxbUnmarshaller.unmarshal(file);

		System.out.println(criteriaType);
		
		return null;
	}

	
	@RequestMapping("/test2")
	public void test2() throws JAXBException {
		try {

			URL url = new URL("http://wltmkt03.cc.cec.eu.int:2041/ecertisrest/criteriatemplates?lang=en");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/xml");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}
			
			InputStream file = conn.getInputStream();
			JAXBContext jaxbContext = JAXBContext.newInstance(CriteriaTypes.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			CriteriaTypes criteriaType = (CriteriaTypes) jaxbUnmarshaller.unmarshal(file);

			System.out.println(criteriaType);
			conn.disconnect();

		  } catch (MalformedURLException e) {

			e.printStackTrace();

		  } catch (IOException e) {

			e.printStackTrace();

		  }

	}

}
