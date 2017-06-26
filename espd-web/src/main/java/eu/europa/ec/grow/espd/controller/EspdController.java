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
import eu.europa.ec.grow.espd.domain.*;
import eu.europa.ec.grow.espd.domain.enums.other.Country;
import eu.europa.ec.grow.espd.domain.intf.UnboundedRequirementGroup;
import eu.europa.ec.grow.espd.ted.TedRequest;
import eu.europa.ec.grow.espd.ted.TedResponse;
import eu.europa.ec.grow.espd.ted.TedService;
import eu.europa.ec.grow.espd.tenderned.UnescapeHtml4;
import eu.europa.ec.grow.espd.tenderned.exception.PdfRenderingException;
import eu.europa.ec.grow.espd.tenderned.exception.TedNoticeException;
import eu.europa.ec.grow.espd.util.EspdExporter;
import eu.europa.ec.grow.espd.xml.EspdXmlImporter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.http.HttpHeaders;
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
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;

@Controller
@SessionAttributes(value = { "espd" })
@Slf4j
class EspdController {

	private static final String WELCOME_PAGE = "welcome";
	private static final String REQUEST_CA_PROCEDURE_PAGE = "request/ca/procedure";
	private static final String RESPONSE_EO_PROCEDURE_PAGE = "response/eo/procedure";
	private static final String OVERVIEW_PAGE = "response/eo/overview";
	private static final String SESSION_EXPIRED_PAGE = "sessionExpired";

	private final EspdXmlImporter xmlImporter;
	private final EspdExporter espdExporter;
	private final TedService tedService;

	@Autowired
	EspdController(EspdXmlImporter xmlImporter, EspdExporter espdExporter, TedService tedService) {
		this.xmlImporter = xmlImporter;
		this.espdExporter = espdExporter;
		this.tedService = tedService;
	}

	@ModelAttribute("espd")
	public EspdDocument newDocument() {
		return new EspdDocument();
	}

	@RequestMapping("/")
	public String index() {
		return WELCOME_PAGE;
	}

	@GetMapping("/contact")
	public String viewContactPage() {
		return "contact";
	}

	@GetMapping("/filter")
	public String viewFilterPage(@ModelAttribute("espdFilterParams") EspdInitializationParameters initParams) {
		return "filter";
	}

	@RequestMapping(value = "/welcome")
	public String cancel(SessionStatus status) {
		try {
			return WELCOME_PAGE;
		} finally {
			status.setComplete();
		}
	}

	@PostMapping(value = "/filter", params = "action=ca_create_espd_request")
	public String createNewRequestAsCA(
			@RequestParam("agent") String agent,
			@RequestParam("country") Country country,
			@ModelAttribute("espd") EspdDocument document) throws IOException {
		document.setExtendCe("ce".equals(agent));
		if (document.getAuthority() == null) {
			document.setAuthority(new PartyImpl());
		}
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

	@PostMapping(value = "/filter", params = "action=ca_reuse_espd_request")
	public String reuseRequestAsCA(
			@RequestParam("agent") String agent,
			@RequestPart List<MultipartFile> attachments,
			@ModelAttribute("espdFilterParams") EspdInitializationParameters initParams,
			Model model,
			BindingResult result) throws IOException {
		try (InputStream is = attachments.get(0).getInputStream()) {
			Optional<EspdDocument> espd = xmlImporter.importEspdRequest(is);
			if (espd.isPresent()) {
				EspdDocument espdGot = espd.get();
				espdGot.setExtendCe("ce".equals(agent));
				model.addAttribute("espd", espdGot);
				return redirectToPage(REQUEST_CA_PROCEDURE_PAGE);
			}
		}

		result.rejectValue("attachments", "espd_upload_request_error");
		return "filter";
	}

	@PostMapping(value = "/filter", params = "action=ca_review_espd_response")
	public String reviewResponseAsCA(
			@RequestParam("agent") String agent,
			@RequestPart List<MultipartFile> attachments,
			@ModelAttribute("espdFilterParams") EspdInitializationParameters initParams,
			Model model,
			BindingResult result) throws IOException {
		try (InputStream is = attachments.get(0).getInputStream()) {
			Optional<EspdDocument> espd = xmlImporter.importEspdResponse(is);
			if (espd.isPresent()) {
				EspdDocument espdGot = espd.get();
				espdGot.setExtendCe("ce".equals(agent));
				model.addAttribute("espd", espdGot);
				return redirectToPage(OVERVIEW_PAGE);
			}
		}

		result.rejectValue("attachments", "espd_upload_response_error");
		return "filter";
	}

	@PostMapping(value = "/filter", params = "action=eo_import_espd")
	public String importEspdAsEo(
			@RequestParam("country") Country country,
			@RequestPart List<MultipartFile> attachments,
			@ModelAttribute("espdFilterParams") EspdInitializationParameters initParams,
			Model model,
			BindingResult result) throws IOException {
		try (InputStream is = attachments.get(0).getInputStream()) {
			Optional<EspdDocument> wrappedEspd = xmlImporter.importAmbiguousEspdFile(is);

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
		} catch (TedNoticeException e) {
			result.rejectValue("attachments", "error_ted_notice_not_supported");
			return "filter";
		}

		result.rejectValue("attachments", "espd_upload_error");
		return "filter";
	}

	private boolean needsToLoadProcurementProcedureInformation(EspdDocument espdDocument) {
		return isBlank(espdDocument.getOjsNumber()) && isNotBlank(espdDocument.getTedReceptionId());
	}

	@PostMapping(value = "/filter", params = "action=eo_merge_espds")
	public String mergeTwoEspds(
			@RequestPart List<MultipartFile> attachments,
			@ModelAttribute("espdFilterParams") EspdInitializationParameters initParams,
			Model model,
			BindingResult result) throws IOException {
		try (InputStream reqIs = attachments.get(1).getInputStream();
				InputStream respIs = attachments.get(2).getInputStream()) {
			Optional<EspdDocument> wrappedEspd = xmlImporter.mergeEspdRequestAndResponse(reqIs, respIs);
			if (wrappedEspd.isPresent()) {
				model.addAttribute("espd", wrappedEspd.get());
				return redirectToPage(RESPONSE_EO_PROCEDURE_PAGE);
			}
		}

		result.rejectValue("attachments", "espd_upload_error");
		return "filter";
	}

	
	@PostMapping(value = "/filter", params = "action=eo_create_espd_response")
	public String createNewResponseAsEO(
			@RequestParam("country") Country country,
			@ModelAttribute("espd") EspdDocument document,
			Model model,
			BindingResult result) throws IOException {
		if (document.getEconomicOperator() == null) {
			document.setEconomicOperator(new EconomicOperatorImpl());
		}
		
		document.getEconomicOperator().setCountry(country);
		document.giveLifeToAllExclusionCriteria();
		document.giveLifeToAllSelectionCriteria();
		
		setDefaultDatesForTurnoverFields(document);
		
		return redirectToPage(RESPONSE_EO_PROCEDURE_PAGE);
	}
	
	private void setDefaultDatesForTurnoverFields(EspdDocument document) {

		int numberOfYears = 5;

		List<DynamicRequirementGroup> ug = new ArrayList<>(numberOfYears);

		for (int year = 1; year <= numberOfYears; year++) {
			ug.add(getTurnoverDynamicRequirementGroupForYear(year));
		}

		document.getGeneralYearlyTurnover().setUnboundedGroups(ug);
		document.getSpecificYearlyTurnover().setUnboundedGroups(ug);
	}
		
	private DynamicRequirementGroup getTurnoverDynamicRequirementGroupForYear(int year) {

		DynamicRequirementGroup drg = new DynamicRequirementGroup();
				
		Date startDate = new DateTime().minusYears(year).dayOfYear().withMinimumValue().withTimeAtStartOfDay().toDate();
		Date endDate = new DateTime().minusYears(year).dayOfYear().withMaximumValue().withTime(23, 59, 59, 999).toDate();
		
		drg.put("startDate", startDate);
		drg.put("endDate", endDate);
		
		return drg;
	}
	

	@GetMapping("/{flow:request|response}/{agent:ca|eo}/{step:procedure|exclusion|selection|finish|overview}")
	public String view(
			@PathVariable String flow,
			@PathVariable String agent,
			@PathVariable String step,
			@ModelAttribute("espd") EspdDocument espd) {
		return flow + "_" + agent + "_" + step;
	}

	@PostMapping(value = "/{flow:request|response}/{agent:ca|eo}/{step:procedure|exclusion|selection|finish|overview}", params = "prev")
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

	@PostMapping(value = "/{flow:request|response}/{agent:ca|eo}/{step:procedure|exclusion|selection|finish}",
			params = "next")
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

		return redirectToPage(flow + "/" + agent + "/" + next);
	}

	@PostMapping(value = "/{flow:request|response}/{agent:ca|eo}/{step:finish}", params = "overview")
	public String overview(
			@PathVariable String flow,
			@PathVariable String agent,
			@PathVariable String step,
			@ModelAttribute("espd") EspdDocument espd,
			BindingResult bindingResult) {
		return bindingResult.hasErrors() ?
				flow + "_" + agent + "_" + step : redirectToPage(flow + "/" + agent + "/overview");
	}

	@PostMapping(value = "/{flow:request|response}/{agent:ca|eo}/{step:overview}", params = "download=xml")
	public String downloadXmlFile(
			@PathVariable String flow,
			@PathVariable String agent,
			@PathVariable String step,
			@ModelAttribute("espd") EspdDocument espd,
			HttpServletResponse response) throws IOException {
		response.setContentType(APPLICATION_XML_VALUE);
		ByteArrayOutputStream out = espdExporter.exportAsXml(espd, agent);
		serveFileForDownload(out, agent, "xml", response);

		return null;
	}

	@PostMapping(value = "/{flow:request|response}/{agent:ca|eo}/{step:overview}", params = "download=pdf")
	public String downloadPdf(
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

		ByteArrayOutputStream pdfOutput = espdExporter.exportAsPdf(espd, agent);

		serveFileForDownload(pdfOutput, agent, "pdf", response);

		return null;
	}

	@PostMapping(value = "/{flow:request|response}/{agent:ca|eo}/{step:overview}", params = "download=zip")
	public String downloadZip(
			@PathVariable String flow,
			@PathVariable String agent,
			@PathVariable String step,
			@ModelAttribute("espd") EspdDocument espd,
			HttpServletResponse response,
			BindingResult bindingResult,
			Model model, Locale locale) throws PdfRenderingException, IOException {

		if (bindingResult.hasErrors()) {
			return flow + "_" + agent + "_" + step;
		}

		espd.setHtml(addHtmlHeader(espd.getHtml()));

		ByteArrayOutputStream zipOutputStream = espdExporter.exportAsZip(espd, agent, locale);

		serveFileForDownload(zipOutputStream, agent, "zip", response);

		return null;
	}

	private void serveFileForDownload(ByteArrayOutputStream fileStream, String agent, String fileType,
			HttpServletResponse response) throws IOException {
		String fileName = "ca".equalsIgnoreCase(agent) ? "espd-request." + fileType : "espd-response." + fileType;
		response.setContentType("application/" + fileType);
		response.setContentLength(fileStream.size());
		response.setHeader(HttpHeaders.CONTENT_DISPOSITION, format("attachment; filename=\"%s\"", fileName));

		// Send content to Browser
		response.getOutputStream().write(fileStream.toByteArray());
		response.getOutputStream().flush();
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

	@RequestMapping("/initialization")
	public String initializeEspd(@ModelAttribute("espdFilterParams") EspdInitializationParameters initParams,
			Model model) {
		EspdDocument espd = new EspdDocument();
		copyProcurementInformation(initParams, espd);
		if (espd.getEconomicOperator() == null) {
			espd.setEconomicOperator(new EconomicOperatorImpl());
		}
		espd.getEconomicOperator().copyProperties(initParams);
		model.addAttribute("espd", espd);
		return "filter";
	}

	private void copyProcurementInformation(EspdInitializationParameters initParams, EspdDocument espd) {
		espd.setProcedureTitle(initParams.getTitle());
		espd.setProcedureShortDesc(initParams.getDescription());
		espd.setFileRefByCA(initParams.getFileRefByCA());
		if (espd.getAuthority() == null) {
			espd.setAuthority(new PartyImpl());
		}
		espd.getAuthority().setName(initParams.getOfficialName());
		espd.getAuthority().setCountry(initParams.getProcurerCountry());
	}

	private static String redirectToPage(String pageName) {
		return "redirect:/" + pageName;
	}

	@InitBinder
	private void dateBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
		binder.registerCustomEditor(Date.class, editor);
		// additional binder for supporting empty values containing years in dynamic unbounded requirement groups
		CustomNumberEditor numberEditor = new CustomNumberEditor(Integer.class, true);
		binder.registerCustomEditor(Integer.class, numberEditor);
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

	@PostMapping(value = "/{flow:request|response}/eo/procedure", params = "add")
	public String addRepresentative(@PathVariable String flow, @RequestParam Integer add,
			@ModelAttribute("espd") EspdDocument espd, BindingResult bindingResult) {
		espd.getEconomicOperator().getRepresentatives().add(add, new EconomicOperatorRepresentative());
		return redirectToPage(flow + "/eo/procedure#representative" + add);
	}

	@PostMapping(value = "/{flow:request|response}/eo/selection", params = "add_financialRatio")
	public String addFinancialRatio(@PathVariable String flow, @RequestParam("add_financialRatio") Integer addIndex,
			@ModelAttribute("espd") EspdDocument espd, BindingResult bindingResult) {
		return addMultipleReference(espd.getFinancialRatio(),
				addIndex, "#financialRatio", flow, "selection");
	}
	
	@PostMapping(value = "/{flow:request|response}/eo/selection", params = "add_workContractsPerformanceOfWorks")
	public String addWorkContractsPerformanceOfWorks(@PathVariable String flow,
			@RequestParam("add_workContractsPerformanceOfWorks") Integer addIndex,
			@ModelAttribute("espd") EspdDocument espd, BindingResult bindingResult) {
		return addMultipleReference(espd.getWorkContractsPerformanceOfWorks(),
				addIndex, "#workContractsPerformanceOfWorks", flow, "selection");
	}

	@PostMapping(value = "/{flow:request|response}/eo/selection", params = "add_supplyContractsPerformanceDeliveries")
	public String addSupplyContractsPerformanceDeliveries(@PathVariable String flow,
			@RequestParam("add_supplyContractsPerformanceDeliveries") Integer addIndex,
			@ModelAttribute("espd") EspdDocument espd, BindingResult bindingResult) {
		return addMultipleReference(espd.getSupplyContractsPerformanceDeliveries(),
				addIndex, "#supplyContractsPerformanceDeliveries", flow, "selection");
	}

	@PostMapping(value = "/{flow:request|response}/eo/selection", params = "add_serviceContractsPerformanceServices")
	public String addServiceContractsPerformanceServices(@PathVariable String flow,
			@RequestParam("add_serviceContractsPerformanceServices") Integer addIndex,
			@ModelAttribute("espd") EspdDocument espd, BindingResult bindingResult) {
		return addMultipleReference(espd.getServiceContractsPerformanceServices(),
				addIndex, "#serviceContractsPerformanceServices", flow, "selection");
	}

	@PostMapping(value = "/{flow:request|response}/eo/exclusion", params = "add_criminalConvictions")
	public String addCriminalConvictions(@PathVariable String flow,
			@RequestParam("add_criminalConvictions") Integer addIndex, @ModelAttribute("espd") EspdDocument espd, BindingResult bindingResult) {
		return addMultipleReference(espd.getCriminalConvictions(), addIndex, "#criminalConvictions", flow, "exclusion");
	}
	@PostMapping(value = "/{flow:request|response}/eo/exclusion", params = "add_corruption")
	public String addCorruption(@PathVariable String flow,
			@RequestParam("add_corruption") Integer addIndex, @ModelAttribute("espd") EspdDocument espd, BindingResult bindingResult) {
		return addMultipleReference(espd.getCorruption(), addIndex, "#corruption", flow, "exclusion");
	}
	@PostMapping(value = "/{flow:request|response}/eo/exclusion", params = "add_fraud")
	public String addFraud(@PathVariable String flow,
			@RequestParam("add_fraud") Integer addIndex, @ModelAttribute("espd") EspdDocument espd, BindingResult bindingResult) {
		return addMultipleReference(espd.getFraud(), addIndex, "#fraud", flow, "exclusion");
	}
	@PostMapping(value = "/{flow:request|response}/eo/exclusion", params = "add_terroristOffences")
	public String addTerroristOffences(@PathVariable String flow,
			@RequestParam("add_terroristOffences") Integer addIndex, @ModelAttribute("espd") EspdDocument espd, BindingResult bindingResult) {
		return addMultipleReference(espd.getTerroristOffences(), addIndex, "#terroristOffences", flow, "exclusion");
	}
	@PostMapping(value = "/{flow:request|response}/eo/exclusion", params = "add_moneyLaundering")
	public String addMoneyLaundering(@PathVariable String flow,
			@RequestParam("add_moneyLaundering") Integer addIndex, @ModelAttribute("espd") EspdDocument espd, BindingResult bindingResult) {
		return addMultipleReference(espd.getMoneyLaundering(), addIndex, "#moneyLaundering", flow, "exclusion");
	}
	@PostMapping(value = "/{flow:request|response}/eo/exclusion", params = "add_childLabour")
	public String addchildLabour(@PathVariable String flow,
			@RequestParam("add_childLabour") Integer addIndex, @ModelAttribute("espd") EspdDocument espd, BindingResult bindingResult) {
		return addMultipleReference(espd.getChildLabour(), addIndex, "#childLabour", flow, "exclusion");
	}

	private String addMultipleReference(UnboundedRequirementGroup espdCriterion, Integer referencePosition,
			String referenceHash, String flow, String referencePage) {
		espdCriterion.getUnboundedGroups().add(referencePosition, new DynamicRequirementGroup());
		return redirectToPage(flow + "/eo/" + referencePage + referenceHash + referencePosition);
	}

	@PostMapping(value = "/{flow:request|response}/eo/procedure", params = "remove")
	public String removeRepresentative(@PathVariable String flow, @RequestParam Integer remove,
			@ModelAttribute("espd") EspdDocument espd, BindingResult bindingResult) {
		if (CollectionUtils.isNotEmpty(espd.getEconomicOperator().getRepresentatives())) {

			espd.getEconomicOperator().getRepresentatives().remove(remove.intValue());
		}
		if (CollectionUtils.isEmpty(espd.getEconomicOperator().getRepresentatives())) {
			espd.getEconomicOperator().getRepresentatives().add(new EconomicOperatorRepresentative());
		}
		remove = Math.min(espd.getEconomicOperator().getRepresentatives().size() - 1, remove);
		return redirectToPage(flow + "/eo/procedure#representative" + remove);
	}

	@PostMapping(value = "/{flow:request|response}/eo/selection", params = "remove_financialRatio")
	public String removeFinancialRatio(@PathVariable String flow,
			@RequestParam("remove_financialRatio") Integer removeIndex,
			@ModelAttribute("espd") EspdDocument espd, BindingResult bindingResult) {
		return removeMultipleReference(espd.getFinancialRatio(), removeIndex, "#financialRatio", flow, "selection");
	}
	
	@PostMapping(value = "/{flow:request|response}/eo/selection", params = "remove_workContractsPerformanceOfWorks")
	public String removeWorkContractsPerformanceOfWorks(@PathVariable String flow,
			@RequestParam("remove_workContractsPerformanceOfWorks") Integer removeIndex,
			@ModelAttribute("espd") EspdDocument espd, BindingResult bindingResult) {
		return removeMultipleReference(espd.getWorkContractsPerformanceOfWorks(),
				removeIndex, "#workContractsPerformanceOfWorks", flow, "selection");
	}

	@PostMapping(value = "/{flow:request|response}/eo/selection", params = "remove_supplyContractsPerformanceDeliveries")
	public String removeSupplyContractsPerformanceDeliveries(@PathVariable String flow,
			@RequestParam("remove_supplyContractsPerformanceDeliveries") Integer removeIndex,
			@ModelAttribute("espd") EspdDocument espd, BindingResult bindingResult) {
		return removeMultipleReference(espd.getSupplyContractsPerformanceDeliveries(),
				removeIndex, "#supplyContractsPerformanceDeliveries", flow, "selection");
	}

	@PostMapping(value = "/{flow:request|response}/eo/selection", params = "remove_serviceContractsPerformanceServices")
	public String removeServiceContractsPerformanceServices(@PathVariable String flow,
			@RequestParam("remove_serviceContractsPerformanceServices") Integer removeIndex,
			@ModelAttribute("espd") EspdDocument espd, BindingResult bindingResult) {
		return removeMultipleReference(espd.getServiceContractsPerformanceServices(),
				removeIndex, "#serviceContractsPerformanceServices", flow, "selection");
	}

	@PostMapping(value = "/{flow:request|response}/eo/exclusion", params = "remove_criminalConvictions")
	public String removeCriminalConvictions(@PathVariable String flow, @RequestParam("remove_criminalConvictions") Integer removeIndex, @ModelAttribute("espd") EspdDocument espd, BindingResult bindingResult) {
		return removeMultipleReference(espd.getCriminalConvictions(), removeIndex, "#criminalConvictions", flow, "exclusion");
	}
	@PostMapping(value = "/{flow:request|response}/eo/exclusion", params = "remove_corruption")
	public String removeCorruption(@PathVariable String flow, @RequestParam("remove_corruption") Integer removeIndex, @ModelAttribute("espd") EspdDocument espd, BindingResult bindingResult) {
		return removeMultipleReference(espd.getCorruption(), removeIndex, "#corruption", flow, "exclusion");
	}
	@PostMapping(value = "/{flow:request|response}/eo/exclusion", params = "remove_fraud")
	public String removeFraud(@PathVariable String flow, @RequestParam("remove_fraud") Integer removeIndex, @ModelAttribute("espd") EspdDocument espd, BindingResult bindingResult) {
		return removeMultipleReference(espd.getFraud(), removeIndex, "#fraud", flow, "exclusion");
	}
	@PostMapping(value = "/{flow:request|response}/eo/exclusion", params = "remove_terroristOffences")
	public String removeTerroristOffences(@PathVariable String flow, @RequestParam("remove_terroristOffences") Integer removeIndex, @ModelAttribute("espd") EspdDocument espd, BindingResult bindingResult) {
		return removeMultipleReference(espd.getTerroristOffences(), removeIndex, "#terroristOffences", flow, "exclusion");
	}
	@PostMapping(value = "/{flow:request|response}/eo/exclusion", params = "remove_moneyLaundering")
	public String removeMoneyLaundering(@PathVariable String flow, @RequestParam("remove_moneyLaundering") Integer removeIndex, @ModelAttribute("espd") EspdDocument espd, BindingResult bindingResult) {
		return removeMultipleReference(espd.getMoneyLaundering(), removeIndex, "#moneyLaundering", flow, "exclusion");
	}
	@PostMapping(value = "/{flow:request|response}/eo/exclusion", params = "remove_childLabour")
	public String removeChildLabour(@PathVariable String flow, @RequestParam("remove_childLabour") Integer removeIndex, @ModelAttribute("espd") EspdDocument espd, BindingResult bindingResult) {
		return removeMultipleReference(espd.getChildLabour(), removeIndex, "#childLabour", flow, "exclusion");
	}

	private String removeMultipleReference(UnboundedRequirementGroup espdCriterion, Integer referencePosition,
			String referenceHash, String flow, String referencePage) {
		if (CollectionUtils.isNotEmpty(espdCriterion.getUnboundedGroups())) {
			espdCriterion.getUnboundedGroups().remove(referencePosition.intValue());
		}
		if (CollectionUtils.isEmpty(espdCriterion.getUnboundedGroups())) {
			espdCriterion.getUnboundedGroups().add(new DynamicRequirementGroup());
		}
		referencePosition = Math.min(espdCriterion.getUnboundedGroups().size() - 1, referencePosition);
		return redirectToPage(flow + "/eo/" + referencePage + referenceHash + referencePosition);
	}
}
