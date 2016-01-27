package eu.europa.ec.grow.espd.controller;

import eu.europa.ec.grow.espd.business.EspdExchangeMarshaller;
import eu.europa.ec.grow.espd.constants.enums.Country;
import eu.europa.ec.grow.espd.domain.EconomicOperatorImpl;
import eu.europa.ec.grow.espd.domain.EspdDocument;

import org.apache.commons.io.output.CountingOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@SessionAttributes("espd")
class WelcomeController {

    private final EspdExchangeMarshaller exchangeMarshaller;
    
    @Autowired
    WelcomeController(EspdExchangeMarshaller exchangeMarshaller) {
        this.exchangeMarshaller = exchangeMarshaller;
    }

    @ModelAttribute("espd")
    public EspdDocument newDocument() {
        return new EspdDocument();
    }

    @RequestMapping("/")
    public String getWelcome() {
        return "welcome";
    }

    @RequestMapping("/{page:welcome|filter|print}")
    public String getPage(@PathVariable String page) {
        return page;
    }
    
    @RequestMapping(value = "/filter", params = "action", method = POST)
    public String caCreateEspdRequest(Map<String, Object> model, @RequestParam("authority.country") Country country, @RequestParam String action, @Valid @RequestPart MultipartFile attachment, @ModelAttribute("espd") EspdDocument document, BindingResult result) throws IOException {
    	if("ca_create_espd_request".equals(action)) {
    		document.getAuthority().setCountry(country);
    		return "redirect:/request/ca/procedure";
    	}
    	else if("ca_reuse_espd_request".equals(action)) {
        	EspdDocument espd = loadESPDRequest(attachment);
        	if(espd != null) {
        		model.put("espd", espd);
        		return "redirect:/request/ca/procedure";
        	}
    	   	result.rejectValue("attachment", "espd_upload_request_error");
        	return "filter";
    	}
    	else if("ca_review_espd_response".equals(action)) {
        	EspdDocument espd = loadESPDResponse(attachment);
        	if(espd != null) {
        		model.put("espd", espd);
            	return "redirect:/response/ca/procedure";
        	}
    	   	result.rejectValue("attachment", "espd_upload_response_error");
        	return "filter";
    	}
    	else if("eo_import_espd".equals(action)) {
    	   	EspdDocument espd = loadESPD(attachment);
    	   	if(espd != null) {
    	   		if (espd.getEconomicOperator() == null) {
    	   			espd.setEconomicOperator(new EconomicOperatorImpl());
    	   		}
    	   		espd.getEconomicOperator().setCountry(country);
    	   		model.put("espd", espd);
    	   		return "redirect:/response/eo/procedure";
    	   	}
    	   	result.rejectValue("attachment", "espd_upload_error");
    	   	return "filter";
    	}
    	return "filter";
    }

    @RequestMapping("/{flow:request|response}/{agent:ca|eo}/{step:procedure|exclusion|selection|finish}")
    public String view(
            @PathVariable String flow,
            @PathVariable String agent,
            @PathVariable String step,
            @ModelAttribute("espd") EspdDocument espd) {
        return flow + "_" + agent + "_" + step;
    }

    @RequestMapping(value = "/{flow:request|response}/{agent:ca|eo}/{step:procedure|exclusion|selection|finish}", method = POST, params = "prev")
    public String prev(
            @PathVariable String flow,
            @PathVariable String agent,
            @PathVariable String step,
            @RequestParam String prev,
            @ModelAttribute("espd") EspdDocument espd,
            BindingResult bindingResult) {
        return bindingResult.hasErrors() ?
                flow + "_" + agent + "_" + step : "redirect:/" + flow + "/" + agent + "/" + prev;
    }

    @RequestMapping(value = "/{flow:request|response}/{agent:ca|eo}/{step:procedure|exclusion|selection|finish|generate}", method = POST, params = "next")
    public String next(
            @PathVariable String flow,
            @PathVariable String agent,
            @PathVariable String step,
            @RequestParam String next,
            @ModelAttribute("espd") EspdDocument espd,
            HttpServletResponse response,
            SessionStatus status,
            BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()) {
            return flow + "_" + agent + "_" + step;
        }
        if (!"generate".equals(next)) {
            return "redirect:/" + flow + "/" + agent + "/" + next;
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

    /**
     * Import ESPD from request with fast check for ESPDRequest
     * @see eu.europa.ec.grow.espd.controller.WelcomeController#loadESPD(MultipartFile, String)
     */
    private EspdDocument loadESPDRequest(MultipartFile attachment) throws UnsupportedEncodingException, IOException {
    	return loadESPD(attachment, "(?s).*xml(?s).*ESPDRequest(?s).*");// regexp for: "... xml ... ESPDRequest ... "
    }

    /**
     * Import ESPD from request with fast check for ESPDResponse
     * @see eu.europa.ec.grow.espd.controller.WelcomeController#loadESPD(MultipartFile, String)
     */
    private EspdDocument loadESPDResponse(MultipartFile attachment) throws UnsupportedEncodingException, IOException {
    	return loadESPD(attachment, "(?s).*xml(?s).*ESPDResponse(?s).*");// regexp for: "... xml ... ESPDResponse ... "
    }
    
    /**
     * Import ESPD from request with fast check for ESPDRequest or ESPDResponse
     * @see eu.europa.ec.grow.espd.controller.WelcomeController#loadESPD(MultipartFile, String)
     */
    private EspdDocument loadESPD(MultipartFile attachment) throws UnsupportedEncodingException, IOException {
    	return loadESPD(attachment, "(?s).*xml(?s).*(ESPDRequest|ESPDResponse)(?s).*");// regexp for: "... xml ... ESPDRequest ... or ... xml ... ESPDResponse ..."
    }

    /**
     * Imports ESPD document from request attachment.
     * @param attachment byte array container from request
     * @param matches contains regular expression to perform fast match for first bytes of uploaded file
     * @return parced ESPD document or null
     */
    private EspdDocument loadESPD(MultipartFile attachment, String matches) throws UnsupportedEncodingException, IOException {
    	try (InputStream is = attachment.getInputStream()) {
    		String firstBytes = new String(Arrays.copyOfRange(attachment.getBytes(), 0, 80), "UTF-8");// peek at the first bytes in the file to see if it is a ESPD Request or Response
    		if(firstBytes.matches(matches)) {
    			return firstBytes.contains("ESPDRequest") ? exchangeMarshaller.importEspdRequest(is) : exchangeMarshaller.importEspdResponse(is);// decide how to read the uploaded file
    		}
    		return null;
    	}
    }

    @InitBinder
    private void dateBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
        binder.registerCustomEditor(Date.class, editor);
    }
}
