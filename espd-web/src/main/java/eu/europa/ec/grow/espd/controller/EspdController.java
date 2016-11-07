/*
 *
 * Copyright 2016 EUROPEAN COMMISSION
 *
 * Licensed under the EUPL, Version 1.1 or – as soon they
 * will be approved by the European Commission - subsequent
 * versions of the EUPL (the "Licence");
 *
 * You may not use this work except in compliance with the Licence.
 *
 * You may obtain a copy of the Licence at:
 *
 * https://joinup.ec.europa.eu/community/eupl/og_page/eupl
 *
 * Unless required by applicable law or agreed to in
 * writing, software distributed under the Licence is
 * distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied.
 * See the Licence for the specific language governing
 * permissions and limitations under the Licence.
 *
 */

package eu.europa.ec.grow.espd.controller;

import com.google.common.base.Optional;
import eu.europa.ec.grow.espd.domain.EconomicOperatorImpl;
import eu.europa.ec.grow.espd.domain.EconomicOperatorRepresentative;
import eu.europa.ec.grow.espd.domain.EspdDocument;
import eu.europa.ec.grow.espd.domain.enums.other.Country;
import eu.europa.ec.grow.espd.ted.TedRequest;
import eu.europa.ec.grow.espd.ted.TedResponse;
import eu.europa.ec.grow.espd.ted.TedService;
import eu.europa.ec.grow.espd.tenderned.HtmlToPdfTransformer;
import eu.europa.ec.grow.espd.tenderned.UnescapeHtml4;
import eu.europa.ec.grow.espd.tenderned.exception.PdfRenderingException;
import eu.europa.ec.grow.espd.xml.EspdExchangeMarshaller;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.output.CountingOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@SessionAttributes(value = { "espd" })
@Slf4j
class EspdController {

	private static final String WELCOME_PAGE = "welcome";
	private static final String REQUEST_CA_PROCEDURE_PAGE = "request/ca/procedure";
	private static final String RESPONSE_EO_PROCEDURE_PAGE = "response/eo/procedure";
	private static final String PRINT_PAGE = "response/eo/print";
	private static final String SESSION_EXPIRED_PAGE = "sessionExpired";

	private final EspdExchangeMarshaller exchangeMarshaller;
	private final TedService tedService;
	private final HtmlToPdfTransformer pdfTransformer;

	@Autowired
	EspdController(EspdExchangeMarshaller exchangeMarshaller, TedService tedService,
			HtmlToPdfTransformer pdfTransformer) {
		this.exchangeMarshaller = exchangeMarshaller;
		this.tedService = tedService;
		this.pdfTransformer = pdfTransformer;
	}

	@ModelAttribute("espd")
	public EspdDocument newDocument() {
		return new EspdDocument();
	}

	@RequestMapping("/")
	public String index() {
		return WELCOME_PAGE;
	}

	@RequestMapping("/{page:filter|contact}")
	public String getPage(@PathVariable String page) {
		return page;
	}

	@RequestMapping(value = "/welcome")
	public String cancel(SessionStatus status) {
		try {
			return WELCOME_PAGE;
		} finally {
			status.setComplete();
		}
	}

	@RequestMapping(value = "/filter", params = "action", method = POST)
	public String whoAreYouScreen(
			@RequestParam("authority.country") Country country,
			@RequestParam String action,
			@RequestPart List<MultipartFile> attachments,
			@ModelAttribute("espd") EspdDocument document,
			Model model,
			BindingResult result) throws IOException {
		if ("ca_create_espd_request".equals(action)) {
			return createNewRequestAsCA(country, document);
		} else if ("ca_reuse_espd_request".equals(action)) {
			return reuseRequestAsCA(attachments.get(0), model, result);
		} else if ("ca_review_espd_response".equals(action)) {
			return reviewResponseAsCA(attachments.get(0), model, result);
		} else if ("eo_import_espd".equals(action)) {
			return importEspdAsEo(country, attachments.get(0), model, result);
		} else if ("eo_merge_espds".equals(action)) {
			return mergeTwoEspds(attachments, model, result);
		} else if ("eo_create_response".equals(action)) {
			return createNewResponseAsEO(country, document);
		}
		return "filter";
	}

	private String createNewRequestAsCA(Country country, EspdDocument document) {
		document.getAuthority().setCountry(country);
		document.selectCAExclusionCriteriaEU();
		copyTedInformation(document);
		return redirectToPage(REQUEST_CA_PROCEDURE_PAGE);
	}

	private void copyTedInformation(EspdDocument document) {
		TedResponse tedResponse = tedService
				.getContractNoticeInformation(TedRequest.builder().receptionId(document.getTedReceptionId()).build());
		if (tedResponse.isEmpty()) {
			return;
		}

		TedResponse.TedNotice notice = tedResponse.getFirstNotice();
		if (document.hasProcurementInformation()) {
			if (isBlank(document.getOjsNumber()) && isBlank(document.getTedUrl())) {
				// only update these fields if none of them is filled in
				document.setOjsNumber(tedResponse.getNoDocOjs());
				document.setTedUrl(notice.getTedUrl());
			}
		} else {
			document.setOjsNumber(tedResponse.getNoDocOjs());
			document.getAuthority().setName(notice.getOfficialName());
			document.setProcedureTitle(notice.getTitle());
			document.setProcedureShortDesc(notice.getShortDescription());
			document.setFileRefByCA(notice.getReferenceNumber());
			document.setTedUrl(notice.getTedUrl());
		}
	}

	private String reuseRequestAsCA(MultipartFile attachment, Model model,
			BindingResult result) throws IOException {
		try (InputStream is = attachment.getInputStream()) {
			Optional<EspdDocument> espd = exchangeMarshaller.importEspdRequest(is);
			if (espd.isPresent()) {
				model.addAttribute("espd", espd.get());
				return redirectToPage(REQUEST_CA_PROCEDURE_PAGE);
			}
		}

		result.rejectValue("attachments", "espd_upload_request_error");
		return "filter";
	}

	private String reviewResponseAsCA(MultipartFile attachment, Model model,
			BindingResult result) throws IOException {
		try (InputStream is = attachment.getInputStream()) {
			Optional<EspdDocument> espd = exchangeMarshaller.importEspdResponse(is);
			if (espd.isPresent()) {
				model.addAttribute("espd", espd.get());
				return redirectToPage(PRINT_PAGE);
			}
		}

		result.rejectValue("attachments", "espd_upload_response_error");
		return "filter";
	}

	private String importEspdAsEo(Country country, MultipartFile attachment, Model model, BindingResult result)
			throws IOException {
		try (InputStream is = attachment.getInputStream()) {
			Optional<EspdDocument> wrappedEspd = exchangeMarshaller.importAmbiguousEspdFile(is);

			// how can wrappedEspd be null???
			if (wrappedEspd != null && wrappedEspd.isPresent()) {
				EspdDocument espd = wrappedEspd.get();
				if (espd.getEconomicOperator() == null) {
					espd.setEconomicOperator(new EconomicOperatorImpl());
				}
				if (needsToLoadProcurementProcedureInformation(espd)) {
					// in this case we need to contact TED again to load the procurement information
					copyTedInformation(espd);
				}
				espd.getEconomicOperator().setCountry(country);
				model.addAttribute("espd", espd);
				return redirectToPage(RESPONSE_EO_PROCEDURE_PAGE);
			}
		}

		result.rejectValue("attachments", "espd_upload_error");
		return "filter";
	}

	private boolean needsToLoadProcurementProcedureInformation(EspdDocument espdDocument) {
		return isBlank(espdDocument.getOjsNumber()) && isNotBlank(espdDocument.getTedReceptionId());
	}

	private String mergeTwoEspds(List<MultipartFile> attachments, Model model, BindingResult result)
			throws IOException {
		try (InputStream reqIs = attachments.get(1).getInputStream();
				InputStream respIs = attachments.get(2).getInputStream()) {
			Optional<EspdDocument> wrappedEspd = exchangeMarshaller.mergeEspdRequestAndResponse(reqIs, respIs);
			if (wrappedEspd.isPresent()) {
				model.addAttribute("espd", wrappedEspd.get());
				return redirectToPage(RESPONSE_EO_PROCEDURE_PAGE);
			}
		}

		result.rejectValue("attachments", "espd_upload_error");
		return "filter";
	}

	private String createNewResponseAsEO(Country country, EspdDocument document) {
		if (document.getEconomicOperator() == null) {
			document.setEconomicOperator(new EconomicOperatorImpl());
		}
		document.getEconomicOperator().setCountry(country);
		document.giveLifeToAllExclusionCriteria();
		document.giveLifeToAllSelectionCriteria();
		return redirectToPage(RESPONSE_EO_PROCEDURE_PAGE);
	}

	@RequestMapping("/{flow:request|response}/{agent:ca|eo}/{step:procedure|exclusion|selection|finish|print}")
	public String view(
			@PathVariable String flow,
			@PathVariable String agent,
			@PathVariable String step,
			@ModelAttribute("espd") EspdDocument espd) {
		return flow + "_" + agent + "_" + step;
	}

	@RequestMapping(value = "/{flow:request|response}/{agent:ca|eo}/{step:procedure|exclusion|selection|finish|print}", method = POST, params = "prev")
	public String previous(
			@PathVariable String flow,
			@PathVariable String agent,
			@PathVariable String step,
			@RequestParam String prev,
			@ModelAttribute("espd") EspdDocument espd,
			BindingResult bindingResult) {
		return bindingResult.hasErrors() ?
				flow + "_" + agent + "_" + step : redirectToPage(flow + "/" + agent + "/" + prev);
	}

	@RequestMapping(value = "/{flow:request|response}/{agent:ca|eo}/{step:procedure|exclusion|selection|finish}", method = POST, params = "print")
	public String print(
			@PathVariable String flow,
			@PathVariable String agent,
			@PathVariable String step,
			@RequestParam String print,
			@ModelAttribute("espd") EspdDocument espd,
			BindingResult bindingResult) {
		return bindingResult.hasErrors() ?
				flow + "_" + agent + "_" + step : redirectToPage(flow + "/" + agent + "/print");
	}

	@RequestMapping(value = "/{flow:request|response}/{agent:ca|eo}/{step:procedure}", method = POST, params = "add")
	public String addRepresentative(
			@PathVariable String flow,
			@PathVariable String agent,
			@PathVariable String step,
			@RequestParam Integer add,
			@ModelAttribute("espd") EspdDocument espd,
			BindingResult bindingResult) {
		espd.getEconomicOperator().getRepresentatives().add(add, new EconomicOperatorRepresentative());
		return redirectToPage(flow + "/" + agent + "/" + step + "#representative" + add);
	}

	@RequestMapping(value = "/{flow:request|response}/{agent:ca|eo}/{step:procedure}", method = POST, params = "remove")
	public String removeRepresentative(
			@PathVariable String flow,
			@PathVariable String agent,
			@PathVariable String step,
			@RequestParam Integer remove,
			@ModelAttribute("espd") EspdDocument espd,
			BindingResult bindingResult) {
		espd.getEconomicOperator().getRepresentatives().remove(remove.intValue());
		if (espd.getEconomicOperator().getRepresentatives().size() == 0) {
			espd.getEconomicOperator().getRepresentatives().add(new EconomicOperatorRepresentative());
		}
		remove = Math.min(espd.getEconomicOperator().getRepresentatives().size() - 1, remove);
		return redirectToPage(flow + "/" + agent + "/" + step + "#representative" + remove);
	}

	@RequestMapping(value = "/{flow:request|response}/{agent:ca|eo}/{step:procedure|exclusion|selection|finish|generate}",
			method = POST, params = "next")
	public String next(
			@PathVariable String flow,
			@PathVariable String agent,
			@PathVariable String step,
			@RequestParam String next,
			@ModelAttribute("espd") EspdDocument espd,
			HttpServletRequest request,
			HttpServletResponse response,
			BindingResult bindingResult,
			SessionStatus status,
			Model model) throws PdfRenderingException, IOException {

		if (bindingResult.hasErrors()) {
			return flow + "_" + agent + "_" + step;
		}

		if (!"generate".equals(next)) {
			return redirectToPage(flow + "/" + agent + "/" + next);
		}

		downloadEspdFile(agent, espd, response);

		return null;
	}

	@PostMapping(value = "/{flow:request|response}/{agent:ca|eo}/{step:print}", params = "next=savePrintHtml")
	public String printPDF(
			@PathVariable String flow,
			@PathVariable String agent,
			@PathVariable String step,
			@ModelAttribute("espd") EspdDocument espd,
			HttpServletResponse response,
			BindingResult bindingResult,
			Model model) throws PdfRenderingException, IOException {

		if (bindingResult.hasErrors()) {
			return flow + "_" + agent + "_" + step;
		}

		espd.setHtml(addHtmlHeader(espd.getHtml()));

		ByteArrayOutputStream pdfOutput = pdfTransformer.convertToPDF(espd.getHtml(), agent);

		String pdfFileName = "ca".equals(agent) ? "espd-request.pdf" : "espd-response.pdf";
		response.setContentType(MediaType.APPLICATION_PDF_VALUE);
		response.setContentLength(pdfOutput.size());
		response.setHeader(HttpHeaders.CONTENT_DISPOSITION, format("attachment; filename=\"%s\"", pdfFileName));

		// Send content to Browser
		response.getOutputStream().write(pdfOutput.toByteArray());
		response.getOutputStream().flush();
		return null;
	}

	/**
	 * This method is for adding headers to the html code that's being saved on
	 * the print.jsp page to make the html valid for creating a PDF file.
	 *
	 * @param html The HTML code of the ESPD to be printed
	 *
	 * @return The HTML surrounded by the proper tags
	 */
	private String addHtmlHeader(String html) {
		String newHtml = UnescapeHtml4.unescapeHtml4(html);
		return "<html><head/><body>" + newHtml + "</div></body></html>";
	}

	private static String redirectToPage(String pageName) {
		return "redirect:/" + pageName;
	}

	private void downloadEspdFile(@PathVariable String agent, @ModelAttribute("espd") EspdDocument espd,
			HttpServletResponse response) throws IOException {
		try (CountingOutputStream out = new CountingOutputStream(response.getOutputStream())) {
			response.setContentType(APPLICATION_XML_VALUE);
			if ("eo".equals(agent)) {
				response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"espd-response.xml\"");
				exchangeMarshaller.generateEspdResponse(espd, out);
			} else {
				response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"espd-request.xml\"");
				exchangeMarshaller.generateEspdRequest(espd, out);
			}
			response.setHeader(HttpHeaders.CONTENT_LENGTH, String.valueOf(out.getByteCount()));
			out.flush();
		}
	}

	@InitBinder
	private void dateBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
		binder.registerCustomEditor(Date.class, editor);
	}

	/**
	 * If we have a value 'null' as a path variable we can assume the session was expired.
	 *
	 * @return The name of the expired page
	 */
	@RequestMapping("**/null/**")
	public String getPage() {
		return SESSION_EXPIRED_PAGE;
	}
}
