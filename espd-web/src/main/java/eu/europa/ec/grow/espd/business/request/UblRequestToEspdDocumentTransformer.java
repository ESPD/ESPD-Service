package eu.europa.ec.grow.espd.business.request;

import com.google.common.base.Function;
import eu.europa.ec.grow.espd.business.common.CriteriaToEspdDocumentPopulator;
import eu.europa.ec.grow.espd.business.common.PartyImplTransformer;
import eu.europa.ec.grow.espd.business.common.UblDocumentReferences;
import eu.europa.ec.grow.espd.constants.enums.DocumentTypeCode;
import eu.europa.ec.grow.espd.domain.EspdDocument;
import eu.europa.ec.grow.espd.domain.EspdRequestMetadata;
import eu.europa.ec.grow.espd.domain.PartyImpl;
import grow.names.specification.ubl.schema.xsd.espdrequest_1.ESPDRequestType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.DocumentReferenceType;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

import static org.apache.commons.collections.CollectionUtils.isNotEmpty;
import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * Create an instance of a {@link EspdDocument} populated with data coming from a UBL {@link ESPDRequestType}.
 * <p/>
 * Created by ratoico on 11/25/15 11:28 AM.
 */
@Component
public class UblRequestToEspdDocumentTransformer implements Function<ESPDRequestType, EspdDocument> {

    private final PartyImplTransformer partyImplTransformer;
    private final CriteriaToEspdDocumentPopulator criteriaToEspdDocumentPopulator;
    private final UblDocumentReferences ublDocumentReferences;

    @Autowired
    UblRequestToEspdDocumentTransformer(PartyImplTransformer partyImplTransformer,
            CriteriaToEspdDocumentPopulator criteriaToEspdDocumentPopulator,
            UblDocumentReferences ublDocumentReferences) {
        this.partyImplTransformer = partyImplTransformer;
        this.criteriaToEspdDocumentPopulator = criteriaToEspdDocumentPopulator;
        this.ublDocumentReferences = ublDocumentReferences;
    }

    /**
     * Build an instance of a {@link EspdDocument} populated with data coming from a UBL {@link ESPDRequestType}.
     *
     * @param input
     *
     * @return
     */
    @Override
    public EspdDocument apply(ESPDRequestType input) {
        EspdDocument espdDocument = new EspdDocument();

        addPartyInformation(input, espdDocument);
        addCriteriaInformation(input, espdDocument);
        addTedInformation(input, espdDocument);
        addEspdRequestInformation(input, espdDocument);

        return espdDocument;
    }

    private void addPartyInformation(ESPDRequestType input, EspdDocument espdDocument) {
        if (input.getContractingParty() == null || input.getContractingParty().getParty() == null) {
            return;
        }

        PartyImpl authority = partyImplTransformer.apply(input.getContractingParty().getParty());
        espdDocument.setAuthority(authority);
    }

    private void addEspdRequestInformation(ESPDRequestType input, EspdDocument espdDocument) {
        EspdRequestMetadata metadata = new EspdRequestMetadata();
        metadata.setId(readRequestId(input));
        metadata.setIssueDate(readRequestDate(input));
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

    private Date readRequestDate(ESPDRequestType input) {
        if (input.getIssueDate() == null) {
            return null;
        }

        LocalDate localDate = input.getIssueDate().getValue();
        if (input.getIssueTime() != null) {
            LocalTime localTime = input.getIssueTime().getValue();
            return new LocalDateTime(localDate.getYear(), localDate.getMonthOfYear(),
                    localDate.getDayOfMonth(), localTime.getHourOfDay(), localTime.getMinuteOfHour(),
                    localTime.getSecondOfMinute()).toDate();
        }

        return new LocalDateTime(localDate.getYear(), localDate.getMonthOfYear(),
                localDate.getDayOfMonth(), 0, 0, 0).toDate();
    }

    private void addTedInformation(ESPDRequestType input, EspdDocument espdDocument) {
        if (input.getContractFolderID() != null) {
            espdDocument.setFileRefByCA(input.getContractFolderID().getValue());
        }

        List<DocumentReferenceType> tedContractNumbers = ublDocumentReferences
                .filterByTypeCode(input.getAdditionalDocumentReference(), DocumentTypeCode.TED_CN);
        if (isNotEmpty(tedContractNumbers)) {
            DocumentReferenceType procurementInfo = tedContractNumbers.get(0);
            espdDocument.setOjsNumber(ublDocumentReferences.readIdValue(procurementInfo));
            espdDocument.setProcedureTitle(ublDocumentReferences.readFileNameValue(procurementInfo));
            espdDocument.setProcedureShortDesc(ublDocumentReferences.readDescriptionValue(procurementInfo));
        }
    }

    private void addCriteriaInformation(ESPDRequestType input, EspdDocument espdDocument) {
        criteriaToEspdDocumentPopulator.addCriteriaToEspdDocument(espdDocument, input.getCriterion());
    }

}
