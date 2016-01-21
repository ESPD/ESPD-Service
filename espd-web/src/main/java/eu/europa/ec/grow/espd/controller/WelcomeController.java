package eu.europa.ec.grow.espd.controller;

import eu.europa.ec.grow.espd.business.EspdExchangeMarshaller;
import eu.europa.ec.grow.espd.constants.enums.Country;
import eu.europa.ec.grow.espd.domain.EspdDocument;
import org.apache.commons.io.output.CountingOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

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
    public String createCA(@RequestParam String agent, @RequestParam("authority.country") Country country, Map<String, Object> model) throws IOException {
    	EspdDocument espd = (EspdDocument) model.get("espd");
    	espd.getAuthority().setCountry(country);
    	return "EO".equals(agent) ? "redirect:/request/eo/procedure" : "redirect:/request/ca/procedure";
    }

    @RequestMapping(value = "/filter", params="eo_import_espd", method = POST)
    public String importEO(@RequestParam String agent, @RequestParam("authority.country") Country country, @RequestParam(required = false) MultipartFile attachment, Map<String, Object> model) throws IOException {
    	try (InputStream is = attachment.getInputStream()) {
    		EspdDocument espd = exchangeMarshaller.importEspdRequest(is);
    		model.put("espd", espd);
        	return "EO".equals(agent) ? "redirect:/request/eo/procedure" : "redirect:/request/ca/procedure";
    	}
    }

    /* Create : page 1 */

    @RequestMapping("/request/ca/procedure") public String getProcedureCA(@ModelAttribute("espd") EspdDocument espd) { 
    	return "requestCAProcedure"; 
    }
    @RequestMapping("/request/eo/procedure") public String getProcedureEO(@ModelAttribute("espd") EspdDocument espd) { 
    	return "requestEOProcedure"; 
    }
    
    @RequestMapping(value = "/request/ca/procedure", method = POST)
    public String postProcedureCA(@ModelAttribute("espd") @Valid EspdDocument espd, BindingResult bindingResult) {
    	return bindingResult.hasErrors() ? "requestCAProcedure" : "redirect:/request/ca/exclusion";
    }

    @RequestMapping(value = "/request/eo/procedure", method = POST)
    public String postProcedureEO(@ModelAttribute("espd") @Valid EspdDocument espd, BindingResult bindingResult) {
        return bindingResult.hasErrors() ? "requestEOProcedure" : "redirect:/request/eo/exclusion";
    }

    /* Exclusion : page 2 */

    @RequestMapping("/request/ca/exclusion") public String getExclusionCA(@ModelAttribute("espd") EspdDocument espd) {
        return "requestCAExclusion";
    }
    @RequestMapping("/request/eo/exclusion") public String getExclusionEO(@ModelAttribute("espd") EspdDocument espd) {
        return "requestEOExclusion";
    }
    @RequestMapping(value = "/request/ca/exclusion", method = POST, params = "prev") public String prevExclusionCA(@ModelAttribute("espd") EspdDocument espd) {
        return "redirect:/request/ca/procedure";
    }
    @RequestMapping(value = "/request/eo/exclusion", method = POST, params = "prev") public String prevExclusionEO(@ModelAttribute("espd") EspdDocument espd) {
        return "redirect:/request/eo/procedure";
    }
    @RequestMapping(value = "/request/ca/exclusion", method = POST, params = "next") public String nextExclusionCA(@ModelAttribute("espd") EspdDocument espd) {
        return "redirect:/request/ca/selection";
    }
    @RequestMapping(value = "/request/eo/exclusion", method = POST, params = "next") public String nextExclusionEO(@ModelAttribute("espd") EspdDocument espd) {
        return "redirect:/request/eo/selection";
    }

    /* Selection : page 3 */

    @RequestMapping("/request/ca/selection") public String getSelectionCA(@ModelAttribute("espd") EspdDocument espd) {
        return "requestCASelection";
    }
    @RequestMapping("/request/eo/selection") public String getSelectionEO(@ModelAttribute("espd") EspdDocument espd) {
        return "requestEOSelection";
    }
    @RequestMapping(value = "/request/ca/selection", method = POST, params = "prev") public String prevSelectionCA(@ModelAttribute("espd") EspdDocument espd) {
        return "redirect:/request/ca/exclusion";
    }
    @RequestMapping(value = "/request/eo/selection", method = POST, params = "prev") public String prevSelectionEO(@ModelAttribute("espd") EspdDocument espd) {
        return "redirect:/request/eo/exclusion";
    }
    @RequestMapping(value = "/request/ca/selection", method = POST, params = "next") public String nextSelectionCA(@ModelAttribute("espd") EspdDocument espd) {
        return "redirect:/request/ca/finish";
    }
    @RequestMapping(value = "/request/eo/selection", method = POST, params = "next") public String nextSelectionEO(@ModelAttribute("espd") EspdDocument espd) {
        return "redirect:/request/eo/finish";
    }

    /* Finish : Page 4 */

    @RequestMapping("/request/ca/finish") public String getFinishCA(@ModelAttribute("espd") EspdDocument espd) {
        return "requestCAFinish";
    }
    @RequestMapping("/request/eo/finish") public String getFinishEO(@ModelAttribute("espd") EspdDocument espd) {
        return "requestEOFinish";
    }

    @RequestMapping(value = "/request/ca/finish", method = POST, params = "prev") public String prevFinishCA() {
        return "redirect:/request/ca/selection";
    }
    @RequestMapping(value = "/request/eo/finish", method = POST, params = "prev") public String prevFinishEO() {
        return "redirect:/request/eo/selection";
    }

    @RequestMapping(value = "/request/ca/finish", method = RequestMethod.POST)
    public String postFinishCA(@ModelAttribute("espd") EspdDocument espd, HttpServletResponse response, SessionStatus status, BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()) {
            return "/request/ca/finish";
        }
        export(espd, response, false, status);
        return null;
    }

    @RequestMapping(value = "/request/eo/finish", method = RequestMethod.POST)
    public String postFinishEO(@ModelAttribute("espd") EspdDocument espd, HttpServletResponse response, SessionStatus status, BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()) {
            return "/request/eo/finish";
        }
        export(espd, response, true, status);
        return null;
    }

    private void export(EspdDocument espd, HttpServletResponse response, Boolean isEO, SessionStatus status) throws IOException {
        try (CountingOutputStream out = new CountingOutputStream(response.getOutputStream())) {
            response.setContentType(APPLICATION_XML_VALUE);

            if (isEO) {
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
    }

    @InitBinder
    private void dateBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
        binder.registerCustomEditor(Date.class, editor);
    }
}
