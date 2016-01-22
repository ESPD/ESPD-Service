package eu.europa.ec.grow.espd.controller;

import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.output.CountingOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import eu.europa.ec.grow.espd.business.EspdExchangeMarshaller;
import eu.europa.ec.grow.espd.constants.enums.Country;
import eu.europa.ec.grow.espd.domain.EspdDocument;

@Controller
@SessionAttributes("espd")
class WelcomeController {

    private final EspdExchangeMarshaller exchangeMarshaller;

    @Autowired WelcomeController(EspdExchangeMarshaller exchangeMarshaller) {
        this.exchangeMarshaller = exchangeMarshaller;
    }

    @ModelAttribute("espd") public EspdDocument newDocument() {
    	return new EspdDocument();
    }
    
    @RequestMapping({ "/", "/welcome" }) public String getWelcome() {
    	return "welcome";
    }
    
    @RequestMapping("/filter") public String getFilter() {
    	return "filter";
    }

    @RequestMapping(value = "/filter", params="ca_create_espd", method = POST)
    public String requestCACreate(@RequestParam String agent, @RequestParam("authority.country") Country country, Map<String, Object> model) throws IOException {
    	if(agent.matches("eo|ca")) {
	    	EspdDocument espd = (EspdDocument) model.get("espd");
	    	espd.getAuthority().setCountry(country);
	    	return "redirect:/request/"+agent+"/procedure";
    	}
    	return null;
    }

    @RequestMapping(value = "/filter", params="eo_import_espd", method = POST)
    public String requestEOImport(@RequestParam String agent, @RequestParam("authority.country") Country country, @RequestParam(required = false) MultipartFile attachment, Map<String, Object> model) throws IOException {
    	if(agent.matches("eo|ca")) {
	    	try (InputStream is = attachment.getInputStream()) {
	    		EspdDocument espd = exchangeMarshaller.importEspdRequest(is);
	    		model.put("espd", espd);
	        	return "redirect:/request/"+agent+"/procedure";
	    	}
    	}
    	return null;
    }

    @RequestMapping("/request/{agent:ca|eo}/{step:procedure|exclusion|selection|finish}") 
    public String view(@PathVariable String agent, @PathVariable String step, @ModelAttribute("espd") EspdDocument espd) { 
    	return "request_" + agent + "_" + step;
    }
    
    @RequestMapping(value = "/request/{agent:ca|eo}/{step:procedure|exclusion|selection|finish}", method = POST, params = "prev")
    public String prev(@PathVariable String agent, @PathVariable String step, @RequestParam String prev, @ModelAttribute("espd") EspdDocument espd, BindingResult bindingResult) {
        return bindingResult.hasErrors() ? "request_"+agent+"_"+step : "redirect:/request/"+agent+"/"+prev;
    }
    
    @RequestMapping(value = "/request/{agent:ca|eo}/{step:procedure|exclusion|selection|finish|generate}", method = POST, params = "next")
    public String next(@PathVariable String agent, @PathVariable String step, @RequestParam String next, @ModelAttribute("espd") EspdDocument espd, HttpServletResponse response, SessionStatus status, BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()) {
            return "request_"+agent+"_"+step;
        }
        if(!"generate".equals(next)) {
        	return "redirect:/request/"+agent+"/"+next;
        }
        
        try (CountingOutputStream out = new CountingOutputStream(response.getOutputStream())) {
            response.setContentType(APPLICATION_XML_VALUE);
            if ("eo".equals(agent)) {
                response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename =\"espd-response.xml\"");
                exchangeMarshaller.generateEspdResponse(espd, out);
            } else {
                response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename =\"espd-request.xml\"");
                exchangeMarshaller.generateEspdRequest(espd, out);
            }
            response.setHeader(HttpHeaders.CONTENT_LENGTH, String.valueOf(out.getByteCount()));
            out.flush();
        } finally {
            status.setComplete();
        }
        return null;
    }
 
    @InitBinder
    private void dateBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
        binder.registerCustomEditor(Date.class, editor);
    }
}
