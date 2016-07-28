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

package eu.europa.ec.grow.espd.xml.response.exporting;

import eu.europa.ec.grow.espd.domain.EspdDocument;
import eu.europa.ec.grow.espd.util.EspdConfiguration;
import eu.europa.ec.grow.espd.xml.common.exporting.CommonUblFactory;
import eu.europa.ec.grow.espd.xml.common.exporting.UblContractingPartyTypeTransformer;
import eu.europa.ec.grow.espd.xml.common.exporting.UblEconomicOperatorPartyTypeTransformer;
import grow.names.specification.ubl.schema.xsd.espd_commonaggregatecomponents_1.EconomicOperatorPartyType;
import grow.names.specification.ubl.schema.xsd.espdresponse_1.ESPDResponseType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.ContractingPartyType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.LocationType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.PartyType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.SignatureType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.IDType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.NameType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * Transforms a {@link EspdDocument} into a {@link ESPDResponseType}.
 * Created by ratoico on 11/26/15.
 */
@Component
public class UblResponseTypeTransformer {

    private final UblContractingPartyTypeTransformer contractingPartyTransformer;
    private final UblEconomicOperatorPartyTypeTransformer economicOperatorPartyTypeTransformer;
    private final UblResponseCriteriaTransformer criteriaTransformer;
    private final EspdConfiguration espdConfiguration;

    @Autowired
    UblResponseTypeTransformer(UblContractingPartyTypeTransformer contractingPartyTransformer,
            UblEconomicOperatorPartyTypeTransformer economicOperatorPartyTypeTransformer,
            UblResponseCriteriaTransformer criteriaTransformer, EspdConfiguration espdConfiguration) {
        this.contractingPartyTransformer = contractingPartyTransformer;
        this.economicOperatorPartyTypeTransformer = economicOperatorPartyTypeTransformer;
        this.criteriaTransformer = criteriaTransformer;
        this.espdConfiguration = espdConfiguration;
    }

    public ESPDResponseType buildResponseType(EspdDocument espdDocument) {
        ESPDResponseType responseType = new ESPDResponseType();

        addUBLVersionInformation(responseType);
        addCustomizationInformation(responseType);
        addIdInformation(responseType);
        addCopyIndicatorInformation(responseType);
        addVersionIdInformation(responseType);
        addIssueDateAndTimeInformation(espdDocument, responseType);
        addContractFolderIdInformation(espdDocument, responseType);
        addPartyInformation(espdDocument, responseType);
        addProcurementProjectLots(espdDocument, responseType);
        addAdditionalDocumentReference(espdDocument, responseType);
        addCriteria(espdDocument, responseType);
	    addSignatureInformation(espdDocument, responseType);
	    addConsortiumName(espdDocument, responseType);

        return responseType;
    }

    private void addUBLVersionInformation(ESPDResponseType responseType) {
        responseType.setUBLVersionID(CommonUblFactory.buildUblVersionIDType());
    }

    private void addCustomizationInformation(ESPDResponseType responseType) {
        responseType
                .setCustomizationID(CommonUblFactory.buildCustomizationIDType(CommonUblFactory.EspdType.ESPD_RESPONSE));
    }

    private void addIdInformation(ESPDResponseType responseType) {
        responseType.setID(CommonUblFactory.buildDocumentIdentifierType());
    }

    private void addCopyIndicatorInformation(ESPDResponseType responseType) {
        responseType.setCopyIndicator(CommonUblFactory.buildCopyIndicatorType(false));
    }

    private void addVersionIdInformation(ESPDResponseType responseType) {
        responseType.setVersionID(CommonUblFactory.buildVersionIDType(espdConfiguration.getBuildVersion()));
    }

    private void addIssueDateAndTimeInformation(EspdDocument espdDocument, ESPDResponseType responseType) {
        Date documentDate = espdDocument.getDocumentDate() != null ? espdDocument.getDocumentDate() : new Date();
        responseType.setIssueTime(CommonUblFactory.buildIssueTimeType(documentDate));
        responseType.setIssueDate(CommonUblFactory.buildIssueDateType(documentDate));
    }

    private void addContractFolderIdInformation(EspdDocument espdDocument, ESPDResponseType responseType) {
        responseType.setContractFolderID(CommonUblFactory.buildContractFolderType(espdDocument.getFileRefByCA()));
    }

    private void addPartyInformation(EspdDocument espdDocument, ESPDResponseType responseType) {
        if (espdDocument.getAuthority() != null) {
            ContractingPartyType contractingPartyType = contractingPartyTransformer.apply(espdDocument.getAuthority());
            responseType.setContractingParty(contractingPartyType);
        }

        if (espdDocument.getEconomicOperator() != null) {
            EconomicOperatorPartyType economicOperatorPartyType = economicOperatorPartyTypeTransformer
                    .apply(espdDocument.getEconomicOperator());
            responseType.setEconomicOperatorParty(economicOperatorPartyType);
        }

    }

    private void addProcurementProjectLots(EspdDocument espdDocument, ESPDResponseType responseType) {
        responseType.getProcurementProjectLot().add(CommonUblFactory.buildProcurementProjectLot(
                espdDocument.getLotConcerned()));
    }

    private void addAdditionalDocumentReference(EspdDocument espdDocument, ESPDResponseType responseType) {
        if (espdDocument.getRequestMetadata() != null) {
            responseType.getAdditionalDocumentReference()
                    .add(CommonUblFactory.buildEspdRequestReferenceType(espdDocument.getRequestMetadata()));
        }
        responseType.getAdditionalDocumentReference()
                .add(CommonUblFactory.buildProcurementProcedureType(espdDocument));
    }

    private void addCriteria(EspdDocument espdDocument, ESPDResponseType responseType) {
        responseType.getCriterion().addAll(criteriaTransformer.apply(espdDocument));
    }

	private void addSignatureInformation(EspdDocument espdDocument, ESPDResponseType responseType) {
		if (isBlank(espdDocument.getLocation())) {
			return;
		}

		NameType nameType = new NameType();
		nameType.setValue(espdDocument.getLocation());

		LocationType locationType = new LocationType();
		locationType.setName(nameType);

		PartyType signatoryParty = new PartyType();
		signatoryParty.setPhysicalLocation(locationType);

		SignatureType signatureType = new SignatureType();
		signatureType.setSignatoryParty(signatoryParty);

		IDType idType = CommonUblFactory.buildIdType();
		idType.setValue(UUID.randomUUID().toString());
		signatureType.setID(idType);

		responseType.getSignature().add(signatureType);
	}

	private void addConsortiumName(EspdDocument espdDocument, ESPDResponseType responseType) {
		if (isBlank(espdDocument.getConsortiumName())) {
			return;
		}

		NameType nameType = new NameType();
		nameType.setValue(espdDocument.getConsortiumName());
		responseType.setEconomicOperatorGroupName(nameType);
	}

}
