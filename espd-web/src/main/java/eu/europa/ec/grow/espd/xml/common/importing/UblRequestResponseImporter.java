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

package eu.europa.ec.grow.espd.xml.common.importing;

import eu.europa.ec.grow.espd.domain.EspdDocument;
import eu.europa.ec.grow.espd.domain.EspdRequestMetadata;
import eu.europa.ec.grow.espd.domain.PartyImpl;
import eu.europa.ec.grow.espd.domain.enums.other.DocumentTypeCode;
import grow.names.specification.ubl.schema.xsd.espd_commonaggregatecomponents_1.EconomicOperatorPartyType;
import grow.names.specification.ubl.schema.xsd.espdrequest_1.ESPDRequestType;
import grow.names.specification.ubl.schema.xsd.espdresponse_1.ESPDResponseType;
import isa.names.specification.ubl.schema.xsd.ccv_commonaggregatecomponents_1.CriterionType;
import lombok.extern.slf4j.Slf4j;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.ContractingPartyType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.DocumentReferenceType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.ProcurementProjectLotType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.ContractFolderIDType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.IssueDateType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.IssueTimeType;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;

import java.util.Date;
import java.util.List;

import static org.apache.commons.collections.CollectionUtils.isEmpty;
import static org.apache.commons.collections.CollectionUtils.isNotEmpty;

/**
 * Created by ratoico on 3/9/16 at 2:08 PM.
 */
@Slf4j
public abstract class UblRequestResponseImporter {

	private final PartyImplTransformer partyImplTransformer;
	private final EconomicOperatorImplTransformer economicOperatorImplTransformer;
	private final CriteriaToEspdDocumentPopulator criteriaToEspdDocumentPopulator;

	protected UblRequestResponseImporter(PartyImplTransformer partyImplTransformer,
			EconomicOperatorImplTransformer economicOperatorImplTransformer,
			CriteriaToEspdDocumentPopulator criteriaToEspdDocumentPopulator) {
		this.partyImplTransformer = partyImplTransformer;
		this.economicOperatorImplTransformer = economicOperatorImplTransformer;
		this.criteriaToEspdDocumentPopulator = criteriaToEspdDocumentPopulator;
	}

	/**
	 * Build an instance of a {@link EspdDocument} populated with data coming from a UBL {@link ESPDRequestType}, a
	 * {@link ESPDResponseType} or a combination of the two of them (in the case of a merge).
	 *
	 * @param requestType a UBL {@link ESPDRequestType}
	 * @param responseType a UBL {@link ESPDResponseType}
	 */
	protected final EspdDocument buildEspdDocument(ESPDRequestType requestType, ESPDResponseType responseType) {
		EspdDocument espdDocument = new EspdDocument();

		addPartyInformation(requestType, responseType, espdDocument);
		addCriteriaInformation(requestType, responseType, espdDocument);
		addOtherInformation(requestType, responseType, espdDocument);

		return espdDocument;
	}

	private void addPartyInformation(ESPDRequestType requestType, ESPDResponseType responseType, EspdDocument espdDocument) {
		ContractingPartyType caParty = provideContractingParty(requestType, responseType);
		if (caParty != null && caParty.getParty() != null) {
			PartyImpl authority = partyImplTransformer.apply(caParty.getParty());
			espdDocument.setAuthority(authority);
		}
		EconomicOperatorPartyType economicOperatorParty = provideEconomicOperatorParty(requestType, responseType);
		if (economicOperatorParty != null) {
			espdDocument.setEconomicOperator(economicOperatorImplTransformer.buildEconomicOperator(
					economicOperatorParty));
		}
	}

	private void addCriteriaInformation(ESPDRequestType requestType, ESPDResponseType responseType, EspdDocument espdDocument) {
		criteriaToEspdDocumentPopulator
				.addCriteriaToEspdDocument(espdDocument, provideCriteria(requestType, responseType));
	}

	private void addOtherInformation(ESPDRequestType requestType, ESPDResponseType responseType, EspdDocument espdDocument) {
		addProjectLotInformation(requestType, responseType, espdDocument);
		addEspdRequestInformation(requestType, responseType, espdDocument);
		addTedInformation(requestType, responseType, espdDocument);
	}

	private void addProjectLotInformation(ESPDRequestType requestType, ESPDResponseType responseType, EspdDocument espdDocument) {
		List<ProcurementProjectLotType> projectLots = provideProjectLots(requestType, responseType);
		espdDocument.setLotConcerned(readLot(projectLots));
	}

	private void addEspdRequestInformation(ESPDRequestType requestType, ESPDResponseType responseType, EspdDocument espdDocument) {
		List<DocumentReferenceType> documentReferences = provideDocumentReferences(requestType, responseType);
		EspdRequestMetadata metadata = readRequestMetadata(documentReferences);
		espdDocument.setRequestMetadata(metadata);
		if (StringUtils.isBlank(metadata.getId())) {
			log.warn("No ESPD Request information found for response '{}'.", getResponseId(responseType));
		}
	}

	private EspdRequestMetadata readRequestMetadata(List<DocumentReferenceType> documentReferenceTypes) {
		EspdRequestMetadata metadata = new EspdRequestMetadata();
		List<DocumentReferenceType> requestReferences = UblDocumentReferences
				.filterByTypeCode(documentReferenceTypes, DocumentTypeCode.ESPD_REQUEST);
		if (isNotEmpty(requestReferences)) {
			DocumentReferenceType requestInfo = requestReferences.get(0);
			metadata.setId(UblDocumentReferences.readIdValue(requestInfo));
			metadata.setIssueDate(readIssueDate(requestInfo.getIssueDate(), requestInfo.getIssueTime()));
			metadata.setDescription(UblDocumentReferences.readDocumentDescriptionValue(requestInfo));
			metadata.setUrl(UblDocumentReferences.readUrlValue(requestInfo));
		}
		return metadata;
	}

	protected final Date readIssueDate(IssueDateType issueDateType, IssueTimeType issueTimeType) {
		if (issueDateType == null || issueDateType.getValue() == null) {
			return null;
		}

		LocalDate localDate = issueDateType.getValue();
		if (issueTimeType != null && issueTimeType.getValue() != null) {
			LocalTime localTime = issueTimeType.getValue();
			return new LocalDateTime(localDate.getYear(), localDate.getMonthOfYear(),
					localDate.getDayOfMonth(), localTime.getHourOfDay(), localTime.getMinuteOfHour(),
					localTime.getSecondOfMinute()).toDate();
		}

		return new LocalDateTime(localDate.getYear(), localDate.getMonthOfYear(),
				localDate.getDayOfMonth(), 0, 0, 0).toDate();
	}

	private void addTedInformation(ESPDRequestType requestType, ESPDResponseType responseType, EspdDocument espdDocument) {
		ContractFolderIDType contractFolder = provideContractFolder(requestType, responseType);
		if (contractFolder != null) {
			espdDocument.setFileRefByCA(contractFolder.getValue());
		}

		List<DocumentReferenceType> documentReferences = provideDocumentReferences(requestType, responseType);
		List<DocumentReferenceType> tedContractNumbers = UblDocumentReferences
				.filterByTypeCode(documentReferences, DocumentTypeCode.TED_CN);
		if (isNotEmpty(tedContractNumbers)) {
			DocumentReferenceType procurementInfo = tedContractNumbers.get(0);
			espdDocument.setOjsNumber(UblDocumentReferences.readIdValue(procurementInfo));
			espdDocument.setProcedureTitle(UblDocumentReferences.readFileNameValue(procurementInfo));
			espdDocument.setProcedureShortDesc(UblDocumentReferences.readDescriptionValue(procurementInfo));
			espdDocument.setTedUrl(UblDocumentReferences.readUrlValue(procurementInfo));
			// read the tedReceptionId as a second description
			espdDocument.setTedReceptionId(UblDocumentReferences.readDescriptionValue(procurementInfo, 1));
		} else {
			log.warn("No TED information found for response '{}'.", getResponseId(responseType));
		}
	}

	private String getResponseId(ESPDResponseType input) {
		if (input == null || input.getID() == null) {
			return "";
		}
		return input.getID().getValue();
	}

	private String readLot(List<ProcurementProjectLotType> lots) {
		if (isEmpty(lots)) {
			return null;
		}

		ProcurementProjectLotType lotType = lots.get(0);
		if (lotType.getID() != null && !"0".equals(lotType.getID().getValue())) {
			return lotType.getID().getValue();
		}

		return null;
	}


	/**
	 * Provide the UBL element containing information about the contracting authority party type.
	 *
	 * @param requestType  A UBL {@link ESPDRequestType}
	 * @param responseType A UBL {@link ESPDResponseType}
	 *
	 * @return A {@link ContractingPartyType} element
	 */
	protected abstract ContractingPartyType provideContractingParty(ESPDRequestType requestType,
			ESPDResponseType responseType);

	/**
	 * Provide the UBL element containing information about the economic operator party type.
	 *
	 * @param requestType  A UBL {@link ESPDRequestType}
	 * @param responseType A UBL {@link ESPDResponseType}
	 *
	 * @return A {@link EconomicOperatorPartyType} element
	 */
	protected abstract EconomicOperatorPartyType provideEconomicOperatorParty(ESPDRequestType requestType,
			ESPDResponseType responseType);

	/**
	 * Provide the list of UBL criteria to be parsed and set onto the ESPD domain object.
	 *
	 * @param requestType  A UBL {@link ESPDRequestType}
	 * @param responseType A UBL {@link ESPDResponseType}
	 *
	 * @return A list of UBL criteria to be parsed into the ESPD domain object.
	 */
	protected abstract List<CriterionType> provideCriteria(ESPDRequestType requestType, ESPDResponseType responseType);

	/**
	 * Provide the list of UBL elements containing information about the procurement project lots type.
	 *
	 * @param requestType  A UBL {@link ESPDRequestType}
	 * @param responseType A UBL {@link ESPDResponseType}
	 *
	 * @return A list of {@link ProcurementProjectLotType} element
	 */
	protected abstract List<ProcurementProjectLotType> provideProjectLots(ESPDRequestType requestType,
			ESPDResponseType responseType);

	/**
	 * Provide the list of UBL elements containing information about the additional document references type.
	 *
	 * @param requestType  A UBL {@link ESPDRequestType}
	 * @param responseType A UBL {@link ESPDResponseType}
	 *
	 * @return A list of {@link DocumentReferenceType} element
	 */
	protected abstract List<DocumentReferenceType> provideDocumentReferences(ESPDRequestType requestType,
			ESPDResponseType responseType);

	/**
	 * Provide the UBL element containing information about the contract folder id type.
	 *
	 * @param requestType  A UBL {@link ESPDRequestType}
	 * @param responseType A UBL {@link ESPDResponseType}
	 *
	 * @return A {@link ContractFolderIDType} element
	 */
	protected abstract ContractFolderIDType provideContractFolder(ESPDRequestType requestType,
			ESPDResponseType responseType);
}
