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

package eu.europa.ec.grow.espd.xml.request.importing;

import eu.europa.ec.grow.espd.domain.EspdDocument;
import eu.europa.ec.grow.espd.domain.EspdRequestMetadata;
import eu.europa.ec.grow.espd.xml.common.importing.CriteriaToEspdDocumentPopulator;
import eu.europa.ec.grow.espd.xml.common.importing.EconomicOperatorImplTransformer;
import eu.europa.ec.grow.espd.xml.common.importing.PartyImplTransformer;
import eu.europa.ec.grow.espd.xml.common.importing.UblRequestResponseImporter;
import grow.names.specification.ubl.schema.xsd.espd_commonaggregatecomponents_1.EconomicOperatorPartyType;
import grow.names.specification.ubl.schema.xsd.espdrequest_1.ESPDRequestType;
import grow.names.specification.ubl.schema.xsd.espdresponse_1.ESPDResponseType;
import isa.names.specification.ubl.schema.xsd.ccv_commonaggregatecomponents_1.CriterionType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.ContractingPartyType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.DocumentReferenceType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.ProcurementProjectLotType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.ContractFolderIDType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * Create an instance of a {@link EspdDocument} populated with data coming from a UBL {@link ESPDRequestType}.
 * <p/>
 * Created by ratoico on 11/25/15 11:28 AM.
 */
@Component
public class UblRequestImporter extends UblRequestResponseImporter {

    @Autowired
	protected UblRequestImporter(
			PartyImplTransformer partyImplTransformer,
			EconomicOperatorImplTransformer economicOperatorImplTransformer,
			CriteriaToEspdDocumentPopulator criteriaToEspdDocumentPopulator) {
		super(partyImplTransformer, economicOperatorImplTransformer, criteriaToEspdDocumentPopulator);
	}

	/**
     * Build an instance of a {@link EspdDocument} populated with data coming from a UBL {@link ESPDRequestType}.
     *
     * @param input The XML object structure of an ESPD Request
     *
     * @return An {@link EspdDocument} entity containing the information coming from the XML request file.
     */
    public EspdDocument importRequest(ESPDRequestType input) {
	    EspdDocument document = buildEspdDocument(input, null);
	    // the request information is read differently than in the case of a response
	    addEspdRequestInformation(input, document);
	    return document;
    }

    private void addEspdRequestInformation(ESPDRequestType input, EspdDocument espdDocument) {
        EspdRequestMetadata metadata = new EspdRequestMetadata();
        metadata.setId(readRequestId(input));
        metadata.setIssueDate(readIssueDate(input.getIssueDate(), input.getIssueTime()));
        metadata.setDescription(readRequestDescription(input));
        // TODO build URL of the request
        espdDocument.setRequestMetadata(metadata);
    }

    private String readRequestId(ESPDRequestType input) {
        if (input.getID() == null) {
            return null;
        }
        return input.getID().getValue();
    }

    private String readRequestDescription(ESPDRequestType input) {
        if (input.getContractFolderID() == null || isBlank(input.getContractFolderID().getValue())) {
            return null;
        }
        return "ESPDRequest " + input.getContractFolderID().getValue();
    }

	@Override
	protected ContractingPartyType provideContractingParty(ESPDRequestType requestType, ESPDResponseType responseType) {
		return requestType.getContractingParty();
	}

	@Override
	protected EconomicOperatorPartyType provideEconomicOperatorParty(ESPDRequestType requestType,
			ESPDResponseType responseType) {
		// a request does not have economic operator information
		return null;
	}

	@Override
	protected List<CriterionType> provideCriteria(ESPDRequestType requestType, ESPDResponseType responseType) {
		return requestType.getCriterion();
	}

	@Override
	protected List<ProcurementProjectLotType> provideProjectLots(ESPDRequestType requestType,
			ESPDResponseType responseType) {
		return requestType.getProcurementProjectLot();
	}

	@Override
	protected List<DocumentReferenceType> provideDocumentReferences(ESPDRequestType requestType,
			ESPDResponseType responseType) {
		return requestType.getAdditionalDocumentReference();
	}

	@Override
	protected ContractFolderIDType provideContractFolder(ESPDRequestType requestType, ESPDResponseType responseType) {
		return requestType.getContractFolderID();
	}
}
