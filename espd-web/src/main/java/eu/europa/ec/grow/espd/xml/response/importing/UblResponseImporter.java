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

package eu.europa.ec.grow.espd.xml.response.importing;

import eu.europa.ec.grow.espd.domain.EspdDocument;
import eu.europa.ec.grow.espd.domain.EspdRequestMetadata;
import eu.europa.ec.grow.espd.domain.PartyImpl;
import eu.europa.ec.grow.espd.domain.enums.other.DocumentTypeCode;
import eu.europa.ec.grow.espd.xml.common.importing.*;
import grow.names.specification.ubl.schema.xsd.espdrequest_1.ESPDRequestType;
import grow.names.specification.ubl.schema.xsd.espdresponse_1.ESPDResponseType;
import isa.names.specification.ubl.schema.xsd.ccv_commonaggregatecomponents_1.CriterionType;
import lombok.extern.slf4j.Slf4j;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.DocumentReferenceType;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

import static org.apache.commons.collections.CollectionUtils.isNotEmpty;

/**
 * Created by ratoico on 3/9/16 at 2:08 PM.
 */
@Slf4j
abstract class UblResponseImporter {

    private final PartyImplTransformer partyImplTransformer;
    private final EconomicOperatorImplTransformer economicOperatorImplTransformer;
    private final CriteriaToEspdDocumentPopulator criteriaToEspdDocumentPopulator;

    UblResponseImporter(PartyImplTransformer partyImplTransformer,
            EconomicOperatorImplTransformer economicOperatorImplTransformer,
            CriteriaToEspdDocumentPopulator criteriaToEspdDocumentPopulator) {
        this.partyImplTransformer = partyImplTransformer;
        this.economicOperatorImplTransformer = economicOperatorImplTransformer;
        this.criteriaToEspdDocumentPopulator = criteriaToEspdDocumentPopulator;
    }

    /**
     * Build an instance of a {@link EspdDocument} populated with data coming from a UBL {@link ESPDResponseType}.
     *
     */
    protected final EspdDocument buildEspdDocument(ESPDRequestType requestType, ESPDResponseType responseType) {
        EspdDocument espdDocument = new EspdDocument();

        addPartyInformation(responseType, espdDocument);
        addCriteriaInformation(espdDocument, requestType, responseType);
        addOtherInformation(responseType, espdDocument);

        return espdDocument;
    }

    private void addPartyInformation(ESPDResponseType input, EspdDocument espdDocument) {
        if (input.getContractingParty() != null && input.getContractingParty().getParty() != null) {
            PartyImpl authority = partyImplTransformer.apply(input.getContractingParty().getParty());
            espdDocument.setAuthority(authority);
        }
        if (input.getEconomicOperatorParty() != null) {
            espdDocument.setEconomicOperator(economicOperatorImplTransformer.apply(input.getEconomicOperatorParty()));
        }
    }

    private void addCriteriaInformation(EspdDocument espdDocument, ESPDRequestType requestType,
            ESPDResponseType responseType) {
        criteriaToEspdDocumentPopulator
                .addCriteriaToEspdDocument(espdDocument, selectCriteria(requestType, responseType));
    }

    private void addOtherInformation(ESPDResponseType input, EspdDocument espdDocument) {
        addProjectLotInformation(input, espdDocument);
        addEspdRequestInformation(input, espdDocument);
        addTedInformation(input, espdDocument);
    }

    private void addProjectLotInformation(ESPDResponseType input, EspdDocument espdDocument) {
        espdDocument.setLotConcerned(CommonUblImporter.readLot(input.getProcurementProjectLot()));
    }

    private void addEspdRequestInformation(ESPDResponseType input, EspdDocument espdDocument) {
        EspdRequestMetadata metadata = CommonUblImporter.readRequestMetadata(input.getAdditionalDocumentReference());
        espdDocument.setRequestMetadata(metadata);
        if (StringUtils.isBlank(metadata.getId())) {
            log.warn("No ESPD Request information found for response '{}'.", getResponseId(input));
        }
    }

    private void addTedInformation(ESPDResponseType input, EspdDocument espdDocument) {
        if (input.getContractFolderID() != null) {
            espdDocument.setFileRefByCA(input.getContractFolderID().getValue());
        }

        List<DocumentReferenceType> tedContractNumbers = UblDocumentReferences
                .filterByTypeCode(input.getAdditionalDocumentReference(), DocumentTypeCode.TED_CN);
        if (isNotEmpty(tedContractNumbers)) {
            DocumentReferenceType procurementInfo = tedContractNumbers.get(0);
            espdDocument.setOjsNumber(UblDocumentReferences.readIdValue(procurementInfo));
            espdDocument.setProcedureTitle(UblDocumentReferences.readFileNameValue(procurementInfo));
            espdDocument.setProcedureShortDesc(UblDocumentReferences.readDescriptionValue(procurementInfo));
            espdDocument.setTedUrl(UblDocumentReferences.readUrlValue(procurementInfo));
        } else {
            log.warn("No TED information found for response '{}'.", getResponseId(input));
        }
    }

    private String getResponseId(ESPDResponseType input) {
        if (input == null || input.getID() == null) {
            return "";
        }
        return input.getID().getValue();
    }

    /**
     * Select the list of UBL criteria to be parsed and set onto the ESPD domain object.
     *
     * @param requestType A UBL {@link ESPDRequestType}
     * @param responseType A UBL {@link ESPDResponseType}
     *
     * @return A list of UBL criteria to be parsed into the ESPD domain object.
     */
    protected abstract List<CriterionType> selectCriteria(ESPDRequestType requestType, ESPDResponseType responseType);
}
