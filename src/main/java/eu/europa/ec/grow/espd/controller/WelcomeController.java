package eu.europa.ec.grow.espd.controller;

import eu.europa.ec.grow.espd.domain.EspdDocument;
import eu.europa.ec.grow.espd.domain.selection.SelectionCriterion;

import org.apache.commons.io.output.CountingOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Controller
@SessionAttributes("espd")
class WelcomeController {

    private final Jaxb2Marshaller jaxb2Marshaller;

    @Autowired
    WelcomeController(final Jaxb2Marshaller jaxb2Marshaller) {
        this.jaxb2Marshaller = jaxb2Marshaller;
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

    @RequestMapping(value = "/filter", method = RequestMethod.POST)
    public String importXmlFile(@RequestParam String action, @RequestParam String agent,
            @RequestParam String country, @RequestParam(required = false) MultipartFile attachment,
            Map<String, Object> model) throws IOException {
        if ("eo_import_espd".equals(action)) {
            try (InputStream is = attachment.getInputStream()) {
                EspdDocument espd = (EspdDocument) jaxb2Marshaller.unmarshal(new StreamSource(is));
                espd.setAction(action);
                espd.setCountry(country);
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

    @RequestMapping(value = "/procedure", method = RequestMethod.POST)
    public String postCreatePage(@RequestParam String agent, @ModelAttribute("espd") @Valid EspdDocument espd,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "procedure";
        }

        return "redirect:/exclusion?agent=" + agent;
    }

    // Exclusion : page 2

    @RequestMapping("/exclusion")
    public String showExcludeCAPage(@RequestParam String agent, @ModelAttribute("espd") EspdDocument espd) {
        return ("eo".equals(agent)) ? "exclusionEO" : "exclusionCA";
    }

    @RequestMapping(value = "/exclusion", method = RequestMethod.POST, params = "next")
    public String postNextExcludePage(@RequestParam String agent, @ModelAttribute("espd") EspdDocument espd) {
        return "redirect:/selection?agent=" + agent;
    }

    @RequestMapping(value = "/exclusion", method = RequestMethod.POST, params = "prev")
    public String postPrevExcludePage(@RequestParam String agent, @ModelAttribute("espd") EspdDocument espd) {
        return "redirect:/procedure?agent=" + agent;
    }

    // Selection : page 3

    @RequestMapping("/selection")
    public String showSelectCAPage(@RequestParam String agent, @ModelAttribute("espd") EspdDocument espd) {
        preselectEconomicOperatorSelectionCriteria(agent, espd);
        return ("eo".equals(agent)) ? "selectionEO" : "selectionCA";
    }

    private void preselectEconomicOperatorSelectionCriteria(final String agent, final EspdDocument espd) {
        if ("eo".equals(agent)) {

        	espd.setSuitabilityEnrolment(new SelectionCriterion());
        	espd.setSuitabilityServiceContracts(new SelectionCriterion());
        	espd.setEconomicEnrolment(new SelectionCriterion());
        	espd.setEconomicServiceContracts(new SelectionCriterion());

            if (espd.getSuitabilityEnrolment().getExists() == null) {
                espd.getSuitabilityEnrolment().setExists(true);
            }
            if (espd.getSuitabilityServiceContracts().getExists() == null) {
                espd.getSuitabilityServiceContracts().setExists(true);
            }
            if (espd.getEconomicEnrolment().getExists() == null) {
                espd.getEconomicEnrolment().setExists(true);
            }
            if (espd.getEconomicServiceContracts().getExists() == null) {
                espd.getEconomicServiceContracts().setExists(true);
            }
        }
    }

    @RequestMapping(value = "/selection", method = RequestMethod.POST, params = "next")
    public String postNextSelectPage(@RequestParam String agent, @ModelAttribute("espd") EspdDocument espd) {
        return "redirect:/finish?agent=" + agent;
    }

    @RequestMapping(value = "/selection", method = RequestMethod.POST, params = "prev")
    public String postPrevSelectPage(@RequestParam String agent, @ModelAttribute("espd") EspdDocument espd) {
        return "redirect:/exclusion?agent=" + agent;
    }

    // Finish : Page 4

    @RequestMapping("/finish")
    public String showFinishCAPage(@ModelAttribute("espd") EspdDocument espd) {
        return "finish";
    }

    @RequestMapping(value = "/finish", method = RequestMethod.POST, produces = MediaType.APPLICATION_XML_VALUE)
    String exportXmlFile(HttpServletResponse response, @ModelAttribute("espd") EspdDocument espd,
            SessionStatus status, BindingResult bindingResult) throws JAXBException, IOException {
        if (bindingResult.hasErrors()) {
            return "/finish";
        }

        try (CountingOutputStream out = new CountingOutputStream(response.getOutputStream())) {
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=espd.xml");
            response.setContentType(MediaType.APPLICATION_XML_VALUE);

            StreamResult result = new StreamResult(out);
            jaxb2Marshaller.marshal(espd, result);
            response.setHeader(HttpHeaders.CONTENT_LENGTH, String.valueOf(out.getByteCount()));

            out.flush();
            return null;
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
