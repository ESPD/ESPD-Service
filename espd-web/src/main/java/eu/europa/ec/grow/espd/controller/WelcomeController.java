package eu.europa.ec.grow.espd.controller;

import eu.europa.ec.grow.espd.business.EspdExchangeMarshaller;
import eu.europa.ec.grow.espd.constants.enums.Country;
import eu.europa.ec.grow.espd.domain.EspdDocument;
import org.apache.commons.io.output.CountingOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.xml.bind.JAXBException;
import javax.xml.transform.stream.StreamSource;
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

    private final Jaxb2Marshaller jaxb2Marshaller;

    private final EspdExchangeMarshaller exchangeMarshaller;

    @Autowired
    WelcomeController(final Jaxb2Marshaller jaxb2Marshaller, final EspdExchangeMarshaller exchangeMarshaller) {
        this.jaxb2Marshaller = jaxb2Marshaller;
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
            @RequestParam("authority.country") Country country, @ModelAttribute("espd") EspdDocument espd, @RequestParam(required = false) MultipartFile attachment,
            Map<String, Object> model) throws IOException {
        if ("eo_import_espd".equals(action)) {
            try (InputStream is = attachment.getInputStream()) {
                espd = (EspdDocument) jaxb2Marshaller.unmarshal(new StreamSource(is));
                espd.setAction(action);
                espd.getAuthority().setCountry(country);
                model.put("espd", espd); 
                return "redirect:/procedure?agent=" + agent;
            }
        } else if ("ca_create_espd".equals(action)) {
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
        return ("eo".equals(agent)) ? "exclusionEO" : "exclusionCA";
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
        return ("eo".equals(agent)) ? "selectionEO" : "selectionCA";
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
    public String exportXmlFile(HttpServletResponse response, @ModelAttribute("espd") EspdDocument espd,
            SessionStatus status, BindingResult bindingResult) throws JAXBException, IOException {
        if (bindingResult.hasErrors()) {
            return "/finish";
        }

        try (CountingOutputStream out = new CountingOutputStream(response.getOutputStream())) {
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename =\"espd.xml\"");
            response.setContentType(APPLICATION_XML_VALUE);

            exchangeMarshaller.generateEspdRequest(espd, out);
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
