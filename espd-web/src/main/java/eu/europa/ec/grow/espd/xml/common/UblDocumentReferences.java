package eu.europa.ec.grow.espd.xml.common;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableList;
import eu.europa.ec.grow.espd.constants.enums.DocumentTypeCode;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.DocumentReferenceType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.ExternalReferenceType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.DescriptionType;
import org.apache.commons.collections.CollectionUtils;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import static org.apache.commons.collections.CollectionUtils.isEmpty;
import static org.apache.commons.collections.CollectionUtils.isNotEmpty;

/**
 * Created by ratoico on 1/21/16 at 1:51 PM.
 */
@Component
public class UblDocumentReferences {

    public List<DocumentReferenceType> filterByTypeCode(List<DocumentReferenceType> unfiltered,
            final DocumentTypeCode typeCode) {
        if (CollectionUtils.isEmpty(unfiltered)) {
            return ImmutableList.of();
        }

        Collection<DocumentReferenceType> filtered = Collections2.filter(unfiltered,
                new DocumentReferenceTypePredicate(typeCode));
        return ImmutableList.copyOf(filtered);
    }

    public String readIdValue(DocumentReferenceType input) {
        if (input == null || input.getID() == null) {
            return null;
        }

        return input.getID().getValue();
    }

    public String readFileNameValue(DocumentReferenceType input) {
        if (input == null || input.getAttachment() == null || input.getAttachment().getExternalReference() == null) {
            return null;
        }

        ExternalReferenceType externalReference = input.getAttachment().getExternalReference();
        if (externalReference.getFileName() != null) {
            return externalReference.getFileName().getValue();
        }

        return null;
    }

    public String readDescriptionValue(DocumentReferenceType input) {
        if (input == null || input.getAttachment() == null || input.getAttachment().getExternalReference() == null) {
            return null;
        }

        ExternalReferenceType externalReference = input.getAttachment().getExternalReference();
        if (isNotEmpty(externalReference.getDescription())) {
            DescriptionType descriptionType = externalReference.getDescription().get(0);
            return descriptionType.getValue();
        }

        return null;
    }

    public String readDocumentDescriptionValue(DocumentReferenceType input) {
        if (input == null || isEmpty(input.getDocumentDescription())) {
            return null;
        }

       return input.getDocumentDescription().get(0).getValue();
    }

    public Date readIssueDate(DocumentReferenceType input) {
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

    public String readUrlValue(DocumentReferenceType input) {
        if (noUrlElementPresent(input)) {
            return null;
        }

        return input.getAttachment().getExternalReference().getURI().getValue();
    }

    private boolean noUrlElementPresent(DocumentReferenceType input) {
        return input == null || input.getAttachment() == null || input.getAttachment().getExternalReference() == null
                || input.getAttachment().getExternalReference().getURI() == null;
    }

    private static class DocumentReferenceTypePredicate implements Predicate<DocumentReferenceType> {

        private final DocumentTypeCode typeCode;

        public DocumentReferenceTypePredicate(DocumentTypeCode typeCode) {
            this.typeCode = typeCode;
        }

        @Override
        public boolean apply(DocumentReferenceType input) {
            if (input == null || input.getDocumentTypeCode() == null) {
                return false;
            }
            return typeCode.name().equalsIgnoreCase(input.getDocumentTypeCode().getValue());
        }
    }
}
