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

package eu.europa.ec.grow.espd.xml.request.exporting;

import eu.europa.ec.grow.espd.domain.EspdDocument;
import eu.europa.ec.grow.espd.util.EspdConfiguration;
import eu.europa.ec.grow.espd.xml.common.exporting.CommonUblFactory;
import eu.europa.ec.grow.espd.xml.common.exporting.UblContractingPartyTypeTransformer;
import grow.names.specification.ubl.schema.xsd.espdrequest_1.ESPDRequestType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.ContractingPartyType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Transforms a {@link EspdDocument} into a {@link ESPDRequestType}.
 * <p/>
 * Created by vigi on 11/11/15:10:58 AM.
 */
@Component
public class UblRequestTypeTransformer {

    private final UblContractingPartyTypeTransformer contractingPartyTransformer;
    private final UblRequestCriteriaTransformer criteriaTransformer;
    private final EspdConfiguration espdConfiguration;

    @Autowired
    UblRequestTypeTransformer(UblContractingPartyTypeTransformer contractingPartyTransformer,
            UblRequestCriteriaTransformer criteriaTransformer, EspdConfiguration espdConfiguration) {
        this.contractingPartyTransformer = contractingPartyTransformer;
        // TODO use template method here as well
        this.criteriaTransformer = criteriaTransformer;
        this.espdConfiguration = espdConfiguration;
    }

    public ESPDRequestType buildRequestType(EspdDocument espdDocument) {
        ESPDRequestType espdRequestType = new ESPDRequestType();

        addUBLVersionInformation(espdRequestType);
        addCustomizationInformation(espdRequestType);
        addIdInformation(espdRequestType);
        addCopyIndicatorInformation(espdRequestType);
        addVersionIdInformation(espdRequestType);
        addIssueDateAndTimeInformation(espdRequestType);
        addContractFolderIdInformation(espdDocument, espdRequestType);
        addContractingPartyInformation(espdDocument, espdRequestType);
        addProcurementProjectLots(espdDocument, espdRequestType);
        addAdditionalDocumentReference(espdDocument, espdRequestType);
        addCriteria(espdDocument, espdRequestType);

        return espdRequestType;
    }

    private void addUBLVersionInformation(ESPDRequestType espdRequestType) {
        espdRequestType.setUBLVersionID(CommonUblFactory.buildUblVersionIDType());
    }

    private void addCustomizationInformation(ESPDRequestType espdRequestType) {
        espdRequestType
                .setCustomizationID(CommonUblFactory.buildCustomizationIDType(CommonUblFactory.EspdType.ESPD_REQUEST));
    }

    private void addIdInformation(ESPDRequestType espdRequestType) {
        espdRequestType.setID(CommonUblFactory.buildDocumentIdentifierType());
    }

    private void addCopyIndicatorInformation(ESPDRequestType espdRequestType) {
        espdRequestType.setCopyIndicator(CommonUblFactory.buildCopyIndicatorType(false));
    }

    private void addVersionIdInformation(ESPDRequestType espdRequestType) {
        espdRequestType.setVersionID(CommonUblFactory.buildVersionIDType(espdConfiguration.getExchangeModelVersion()));
    }

    private void addIssueDateAndTimeInformation(ESPDRequestType espdRequestType) {
        Date now = new Date();
        espdRequestType.setIssueTime(CommonUblFactory.buildIssueTimeType(now));
        espdRequestType.setIssueDate(CommonUblFactory.buildIssueDateType(now));
    }

    private void addContractFolderIdInformation(EspdDocument espdDocument, ESPDRequestType espdRequestType) {
        espdRequestType.setContractFolderID(CommonUblFactory.buildContractFolderType(espdDocument.getFileRefByCA()));
    }

    private void addContractingPartyInformation(EspdDocument espdDocument, ESPDRequestType espdRequestType) {
        ContractingPartyType contractingPartyType = contractingPartyTransformer.apply(espdDocument.getAuthority());
        espdRequestType.setContractingParty(contractingPartyType);
    }

    private void addProcurementProjectLots(EspdDocument espdDocument, ESPDRequestType espdRequestType) {
        espdRequestType.getProcurementProjectLot()
                .add(CommonUblFactory.buildProcurementProjectLot(espdDocument.getLotConcerned()));
    }

    private void addAdditionalDocumentReference(EspdDocument espdDocument, ESPDRequestType espdRequestType) {
        espdRequestType.getAdditionalDocumentReference()
                .add(CommonUblFactory.buildProcurementProcedureType(espdDocument));
    }

    private void addCriteria(EspdDocument espdDocument, ESPDRequestType espdRequestType) {
        espdRequestType.getCriterion().addAll(criteriaTransformer.apply(espdDocument));
    }

}