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

    @Autowired
    WelcomeController(EspdExchangeMarshaller exchangeMarshaller) {
        this.exchangeMarshaller = exchangeMarshaller;
    }

    @ModelAttribute("espd")
    public EspdDocument newDocument() {
        return new EspdDocument();
    }

    @RequestMapping({ "/", "/welcome" })
    public String showWelcomePage() {
        return "welcome";
    }

    @RequestMapping("/splash")
    public String showSplashPage() {
        return "splash";
    }

    @RequestMapping("/filter")
    public String showFilterPage() {

        return "filter";
    }

    @RequestMapping(value = "/filter", method = POST)
    public String importXmlFile(@RequestParam String action, @RequestParam String agent,
            @RequestParam("authority.country") Country country, @RequestParam(required = false) MultipartFile attachment,
            Map<String, Object> model) throws IOException {
        if ("eo_import_espd".equals(action)) {
            try (InputStream is = attachment.getInputStream()) {
                EspdDocument espd = exchangeMarshaller.importEspdRequest(is);
                espd.setAction(action);
                model.put("espd", espd);
                return "redirect:/procedure?agent=" + agent;
            }
        } else if ("ca_create_espd".equals(action)) {
            // TODO improve this
            EspdDocument espd = (EspdDocument) model.get("espd");
            espd.getAuthority().setCountry(country);
            return "redirect:/procedure?agent=" + agent;
        }

        return null;
    }

    // Create : page 1

    @RequestMapping("/procedure")
    public String showCreatePage(@ModelAttribute("espd") EspdDocument espd) {
        return "procedure";
    }

    @RequestMapping(value = "/procedure", method = POST)
    public String postCreatePage(@RequestParam String agent, @ModelAttribute("espd") @Valid EspdDocument espd,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "procedure";
        }

        return "redirect:/exclusion?agent=" + agent;
    }

    @RequestMapping(value = "/procedure", method = POST, params = "prev")
    public String procedurePreviousPage() {
        return "redirect:/filter";
    }

    // Exclusion : page 2

    @RequestMapping("/exclusion")
    public String showExcludeCAPage(@RequestParam String agent, @ModelAttribute("espd") EspdDocument espd) {
        return (isEconomicOperator(agent)) ? "exclusionEO" : "exclusionCA";
    }

    @RequestMapping(value = "/exclusion", method = POST, params = "next")
    public String exclusionCriteriaNextPage(@RequestParam String agent, @ModelAttribute("espd") EspdDocument espd) {
        return "redirect:/selection?agent=" + agent;
    }

    @RequestMapping(value = "/exclusion", method = POST, params = "prev")
    public String exclusionCriteriaPreviousPage(@RequestParam String agent, @ModelAttribute("espd") EspdDocument espd) {
        return "redirect:/procedure?agent=" + agent;
    }

    // Selection : page 3

    @RequestMapping("/selection")
    public String showSelectCAPage(@RequestParam String agent, @ModelAttribute("espd") EspdDocument espd) {
        return isEconomicOperator(agent) ? "selectionEO" : "selectionCA";
    }

    private boolean isEconomicOperator(@RequestParam String agent) {
        return "eo".equals(agent);
    }

    @RequestMapping(value = "/selection", method = POST, params = "next")
    public String selectionCriteriaNextPage(@RequestParam String agent, @ModelAttribute("espd") EspdDocument espd) {
        return "redirect:/finish?agent=" + agent;
    }

    @RequestMapping(value = "/selection", method = POST, params = "prev")
    public String selectionCriteriaPreviousPage(@RequestParam String agent, @ModelAttribute("espd") EspdDocument espd) {
        return "redirect:/exclusion?agent=" + agent;
    }

    // Finish : Page 4

    @RequestMapping("/finish")
    public String showFinishCAPage(@ModelAttribute("espd") EspdDocument espd) {
        return "finish";
    }

    @RequestMapping(value = "/finish", method = RequestMethod.POST)
    public String exportXmlFile(@RequestParam String agent, @ModelAttribute("espd") EspdDocument espd,
            HttpServletResponse response, SessionStatus status, BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()) {
            return "/finish";
        }

        try (CountingOutputStream out = new CountingOutputStream(response.getOutputStream())) {
            response.setContentType(APPLICATION_XML_VALUE);

            if (isEconomicOperator(agent)) {
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

    @RequestMapping(value = "/finish", method = POST, params = "prev")
    public String finishPreviousPage(@RequestParam String agent) {
        return "redirect:/selection?agent=" + agent;
    }

    @InitBinder
    private void dateBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
        binder.registerCustomEditor(Date.class, editor);
    }
}
