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
    	return "EO".equals(agent) ? "redirect:/procedureEO" : "redirect:/procedureCA";
    }

    @RequestMapping(value = "/filter", params="eo_import_espd", method = POST)
    public String importEO(@RequestParam String agent, @RequestParam("authority.country") Country country, @RequestParam(required = false) MultipartFile attachment, Map<String, Object> model) throws IOException {
    	try (InputStream is = attachment.getInputStream()) {
    		EspdDocument espd = exchangeMarshaller.importEspdRequest(is);
    		model.put("espd", espd);
    		return "EO".equals(agent) ? "redirect:/procedureEO" : "redirect:/procedureCA";
    	}
    }

    /* Create : page 1 */

    @RequestMapping("/requeastprocedureCA") public String getProcedureCA(@ModelAttribute("espd") EspdDocument espd) { 
    	return "procedureCA"; 
    }
    @RequestMapping("/procedureEO") public String getProcedureEO(@ModelAttribute("espd") EspdDocument espd) { 
    	return "procedureEO"; 
    }
    
    @RequestMapping(value = "/procedureCA", method = POST)
    public String postProcedureCA(@ModelAttribute("espd") @Valid EspdDocument espd, BindingResult bindingResult) {
    	return bindingResult.hasErrors() ? "procedureCA" : "redirect:/exclusionCA";
    }

    @RequestMapping(value = "/procedureEO", method = POST)
    public String postProcedureEO(@ModelAttribute("espd") @Valid EspdDocument espd, BindingResult bindingResult) {
        return bindingResult.hasErrors() ? "procedureEO" : "redirect:/exclusionEO";
    }

    /* Exclusion : page 2 */

    @RequestMapping("/exclusionCA") public String getExclusionCA(@ModelAttribute("espd") EspdDocument espd) {
        return "exclusionCA";
    }
    @RequestMapping("/exclusionEO") public String getExclusionEO(@ModelAttribute("espd") EspdDocument espd) {
        return "exclusionEO";
    }
    @RequestMapping(value = "/exclusionCA", method = POST, params = "prev") public String prevExclusionCA(@ModelAttribute("espd") EspdDocument espd) {
        return "redirect:/procedureCA";
    }
    @RequestMapping(value = "/exclusionEO", method = POST, params = "prev") public String prevExclusionEO(@ModelAttribute("espd") EspdDocument espd) {
        return "redirect:/procedureEO";
    }
    @RequestMapping(value = "/exclusionCA", method = POST, params = "next") public String nextExclusionCA(@ModelAttribute("espd") EspdDocument espd) {
        return "redirect:/selectionCA";
    }
    @RequestMapping(value = "/exclusionEO", method = POST, params = "next") public String nextExclusionEO(@ModelAttribute("espd") EspdDocument espd) {
        return "redirect:/selectionEO";
    }

    /* Selection : page 3 */

    @RequestMapping("/selectionCA") public String getSelectionCA(@ModelAttribute("espd") EspdDocument espd) {
        return "selectionCA";
    }
    @RequestMapping("/selectionEO") public String getSelectionEO(@ModelAttribute("espd") EspdDocument espd) {
        return "selectionEO";
    }
    @RequestMapping(value = "/selectionCA", method = POST, params = "prev") public String prevSelectionCA(@ModelAttribute("espd") EspdDocument espd) {
        return "redirect:/exclusionCA";
    }
    @RequestMapping(value = "/selectionEO", method = POST, params = "prev") public String prevSelectionEO(@ModelAttribute("espd") EspdDocument espd) {
        return "redirect:/exclusionEO";
    }
    @RequestMapping(value = "/selectionCA", method = POST, params = "next") public String nextSelectionCA(@ModelAttribute("espd") EspdDocument espd) {
        return "redirect:/finishCA";
    }
    @RequestMapping(value = "/selectionEO", method = POST, params = "next") public String nextSelectionEO(@ModelAttribute("espd") EspdDocument espd) {
        return "redirect:/finishEO";
    }

    /* Finish : Page 4 */

    @RequestMapping("/finishCA") public String getFinishCA(@ModelAttribute("espd") EspdDocument espd) {
        return "finishCA";
    }
    @RequestMapping("/finishEO") public String getFinishEO(@ModelAttribute("espd") EspdDocument espd) {
        return "finishEO";
    }

    @RequestMapping(value = "/finishCA", method = POST, params = "prev") public String prevFinishCA() {
        return "redirect:/selectionCA";
    }
    @RequestMapping(value = "/finishEO", method = POST, params = "prev") public String prevFinishEO() {
        return "redirect:/selectionEO";
    }

    @RequestMapping(value = "/finishCA", method = RequestMethod.POST)
    public String postFinishCA(@ModelAttribute("espd") EspdDocument espd, HttpServletResponse response, SessionStatus status, BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()) {
            return "/finishCA";
        }
        export(espd, response, false, status);
        return null;
    }

    @RequestMapping(value = "/finishEO", method = RequestMethod.POST)
    public String postFinishEO(@ModelAttribute("espd") EspdDocument espd, HttpServletResponse response, SessionStatus status, BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()) {
            return "/finishEO";
        }
        export(espd, response, true, status);
        return null;
    }

    private void export(EspdDocument espd, HttpServletResponse response, Boolean isEO, SessionStatus status) throws IOException {
        try (CountingOutputStream out = new CountingOutputStream(response.getOutputStream())) {
            response.setContentType(APPLICATION_XML_VALUE);

            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename =\"espd-response.xml\"");
            if (isEO) {
                exchangeMarshaller.generateEspdResponse(espd, out);
            } else {
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
