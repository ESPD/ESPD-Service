package eu.europa.ec.grow.espd.xml.common.importing;

import eu.europa.ec.grow.espd.domain.EspdRequestMetadata;
import eu.europa.ec.grow.espd.domain.enums.other.DocumentTypeCode;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.DocumentReferenceType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.ProcurementProjectLotType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.IssueDateType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.IssueTimeType;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;

import java.util.Date;
import java.util.List;

import static org.apache.commons.collections.CollectionUtils.isEmpty;
import static org.apache.commons.collections.CollectionUtils.isNotEmpty;

/**
 * Created by ratoico on 3/10/16 at 11:01 AM.
 */
public final class CommonUblImporter {

    private CommonUblImporter() {

    }

    public static Date readIssueDate(IssueDateType issueDateType, IssueTimeType issueTimeType) {
        if (issueDateType == null) {
            return null;
        }

        LocalDate localDate = issueDateType.getValue();
        if (issueTimeType != null) {
            LocalTime localTime = issueTimeType.getValue();
            return new LocalDateTime(localDate.getYear(), localDate.getMonthOfYear(),
                    localDate.getDayOfMonth(), localTime.getHourOfDay(), localTime.getMinuteOfHour(),
                    localTime.getSecondOfMinute()).toDate();
        }

        return new LocalDateTime(localDate.getYear(), localDate.getMonthOfYear(),
                localDate.getDayOfMonth(), 0, 0, 0).toDate();
    }

    public static String readLot(List<ProcurementProjectLotType> lots) {
        if (isEmpty(lots)) {
            return null;
        }

        ProcurementProjectLotType lotType = lots.get(0);
        if (lotType.getID() != null && !"0".equals(lotType.getID().getValue())) {
            return lotType.getID().getValue();
        }

        return null;
    }

    public static EspdRequestMetadata readRequestMetadata(List<DocumentReferenceType> documentReferenceTypes) {
        EspdRequestMetadata metadata = new EspdRequestMetadata();
        List<DocumentReferenceType> requestReferences = UblDocumentReferences
                .filterByTypeCode(documentReferenceTypes, DocumentTypeCode.ESPD_REQUEST);
        if (isNotEmpty(requestReferences)) {
            DocumentReferenceType requestInfo = requestReferences.get(0);
            metadata.setId(UblDocumentReferences.readIdValue(requestInfo));
            metadata.setIssueDate(CommonUblImporter.readIssueDate(requestInfo.getIssueDate(), requestInfo.getIssueTime()));
            metadata.setDescription(UblDocumentReferences.readDocumentDescriptionValue(requestInfo));
            metadata.setUrl(UblDocumentReferences.readUrlValue(requestInfo));
        }
        return metadata;
    }

}
