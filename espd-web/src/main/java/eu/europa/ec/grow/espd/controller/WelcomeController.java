package eu.europa.ec.grow.espd.controller;

import eu.europa.ec.grow.espd.business.EspdExchangeMarshaller;
import eu.europa.ec.grow.espd.constants.enums.Country;
import eu.europa.ec.grow.espd.domain.EconomicOperatorImpl;
import eu.europa.ec.grow.espd.domain.EspdDocument;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.CountingOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@SessionAttributes("espd")
@Slf4j
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
    public String index() {
        return "welcome";
    }

    @RequestMapping("/{page:welcome|filter|print}")
    public String getPage(@PathVariable String page) {
        return page;
    }

    @RequestMapping(value = "/filter", params = "action", method = POST)
    public String whoAreYouScreen(
            @RequestParam("authority.country") Country country,
            @RequestParam String action,
            @Valid @RequestPart MultipartFile attachment,
            @ModelAttribute("espd") EspdDocument document,
            Model model,
            BindingResult result) throws IOException {
        if ("ca_create_espd_request".equals(action)) {
            return createNewRequestAsCA(country, document);
        } else if ("ca_reuse_espd_request".equals(action)) {
            return reuseRequestAsCA(attachment, model, result);
        } else if ("ca_review_espd_response".equals(action)) {
            return reviewResponseAsCA(attachment, model, result);
        } else if ("eo_import_espd".equals(action)) {
            return importEspdAsEo(country, attachment, model, result);
        }
        return "filter";
    }

    private String createNewRequestAsCA(@RequestParam("authority.country") Country country,
            @ModelAttribute("espd") EspdDocument document) {
        document.getAuthority().setCountry(country);
        return "redirect:/request/ca/procedure";
    }

    private String reuseRequestAsCA(@Valid @RequestPart MultipartFile attachment, Model model,
            BindingResult result) throws IOException {
        EspdDocument espd = readEspdRequest(attachment.getInputStream());
        if (espd != null) {
            model.addAttribute("espd", espd);
            return "redirect:/request/ca/procedure";
        }
        result.rejectValue("attachment", "espd_upload_request_error");
        return "filter";
    }

    private String reviewResponseAsCA(@Valid @RequestPart MultipartFile attachment, Model model,
            BindingResult result) throws IOException {
        EspdDocument espd = readEspdResponse(attachment.getInputStream());
        if (espd != null) {
            model.addAttribute("espd", espd);
            return "redirect:/print";
        }
        result.rejectValue("attachment", "espd_upload_response_error");
        return "filter";
    }

    private String importEspdAsEo(@RequestParam("authority.country") Country country,
            @Valid @RequestPart MultipartFile attachment, Model model, BindingResult result) throws IOException {
        EspdDocument espd = importXmlFile(attachment.getInputStream());
        if (espd != null) {
            if (espd.getEconomicOperator() == null) {
                espd.setEconomicOperator(new EconomicOperatorImpl());
            }
            espd.getEconomicOperator().setCountry(country);
            model.addAttribute("espd", espd);
            return "redirect:/response/eo/procedure";
        }
        result.rejectValue("attachment", "espd_upload_error");
        return "filter";
    }

    private EspdDocument importXmlFile(InputStream is) throws IOException {
        // peek at the first bytes in the file to see if it is a ESPD Request or Response
        try (BufferedInputStream bis = new BufferedInputStream(is)) {
            int peekReadLimit = 80;
            bis.mark(peekReadLimit);
            byte[] peek = new byte[peekReadLimit];
            int bytesRead = bis.read(peek, 0, peekReadLimit - 1);
            if (bytesRead < 0) {
                return null;
            }
            bis.reset(); // need to read from the beginning afterwards
            String firstBytes = new String(peek, "UTF-8");

            // decide how to read the uploaded file
            if (firstBytes.contains("ESPDResponse")) {
                return readEspdResponse(bis);
            } else if (firstBytes.contains("ESPDRequest")) {
                return readEspdRequest(bis);
            }
        } finally {
            IOUtils.closeQuietly(is);
        }
        return null;
    }

    private EspdDocument readEspdResponse(InputStream is) {
        try {
            return exchangeMarshaller.importEspdResponse(is);
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
            return null;
        } finally {
            IOUtils.closeQuietly(is);
        }
    }

    private EspdDocument readEspdRequest(InputStream is) {
        try {
            return exchangeMarshaller.importEspdRequest(is);
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
            return null;
        } finally {
            IOUtils.closeQuietly(is);
        }
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
    public String previous(
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

    @InitBinder
    private void dateBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
        binder.registerCustomEditor(Date.class, editor);
    }
}
