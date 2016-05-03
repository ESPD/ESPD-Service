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
