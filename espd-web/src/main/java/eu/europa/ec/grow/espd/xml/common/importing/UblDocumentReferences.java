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

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableList;
import eu.europa.ec.grow.espd.domain.enums.other.DocumentTypeCode;
import eu.europa.ec.grow.espd.xml.common.MarshallingConstants;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.DocumentReferenceType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.ExternalReferenceType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.DescriptionType;
import org.apache.commons.collections.CollectionUtils;

import java.util.Collection;
import java.util.List;

import static org.apache.commons.collections.CollectionUtils.isEmpty;
import static org.apache.commons.collections.CollectionUtils.isNotEmpty;

/**
 * Created by ratoico on 1/21/16 at 1:51 PM.
 */
final class UblDocumentReferences {

    private UblDocumentReferences() {

    }

    static List<DocumentReferenceType> filterByTypeCode(List<DocumentReferenceType> unfiltered,
		    final DocumentTypeCode typeCode) {
        if (CollectionUtils.isEmpty(unfiltered)) {
            return ImmutableList.of();
        }

        Collection<DocumentReferenceType> filtered = Collections2.filter(unfiltered,
                new DocumentReferenceTypePredicate(typeCode));
        return ImmutableList.copyOf(filtered);
    }

    static String readIdValue(DocumentReferenceType input) {
        if (input == null || input.getID() == null || hasTemporaryOjsNumber(input)) {
            return null;
        }

        return input.getID().getValue();
    }

    private static boolean hasTemporaryOjsNumber(DocumentReferenceType input) {
        return MarshallingConstants.TEMPORARY_OJS_NUMBER_SCHEME_ID.equals(input.getID().getSchemeID()) ||
                MarshallingConstants.TEMPORARY_OJS_NUMBER.equals(input.getID().getValue());
    }

    static String readFileNameValue(DocumentReferenceType input) {
        if (input == null || input.getAttachment() == null || input.getAttachment().getExternalReference() == null) {
            return null;
        }

        ExternalReferenceType externalReference = input.getAttachment().getExternalReference();
        if (externalReference.getFileName() != null) {
            return externalReference.getFileName().getValue();
        }

        return null;
    }

    static String readDescriptionValue(DocumentReferenceType input) {
        return readDescriptionValue(input, 0);
    }

    static String readDescriptionValue(DocumentReferenceType input, int position) {
        if (input == null || input.getAttachment() == null || input.getAttachment().getExternalReference() == null) {
            return null;
        }

        ExternalReferenceType externalReference = input.getAttachment().getExternalReference();
        if (isNotEmpty(externalReference.getDescription()) && externalReference.getDescription().size() > position) {
            DescriptionType descriptionType = externalReference.getDescription().get(position);
            return descriptionType.getValue();
        }

        return null;
    }

    static String readDocumentDescriptionValue(DocumentReferenceType input) {
        if (input == null || isEmpty(input.getDocumentDescription())) {
            return null;
        }

       return input.getDocumentDescription().get(0).getValue();
    }

    static String readUrlValue(DocumentReferenceType input) {
        if (noUrlElementPresent(input)) {
            return null;
        }

        return input.getAttachment().getExternalReference().getURI().getValue();
    }

    private static boolean noUrlElementPresent(DocumentReferenceType input) {
        return input == null || input.getAttachment() == null || input.getAttachment().getExternalReference() == null
                || input.getAttachment().getExternalReference().getURI() == null;
    }

    private static class DocumentReferenceTypePredicate implements Predicate<DocumentReferenceType> {

        private final DocumentTypeCode typeCode;

        DocumentReferenceTypePredicate(DocumentTypeCode typeCode) {
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
