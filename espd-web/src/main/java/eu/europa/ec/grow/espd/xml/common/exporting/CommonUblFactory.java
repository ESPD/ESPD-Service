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

package eu.europa.ec.grow.espd.xml.common.exporting;

import eu.europa.ec.grow.espd.domain.EspdDocument;
import eu.europa.ec.grow.espd.domain.EspdRequestMetadata;
import eu.europa.ec.grow.espd.domain.enums.other.Agency;
import eu.europa.ec.grow.espd.domain.enums.other.DocumentTypeCode;
import eu.europa.ec.grow.espd.domain.ubl.CacCountry;
import eu.europa.ec.grow.espd.xml.common.MarshallingConstants;
import grow.names.specification.ubl.schema.xsd.espdrequest_1.ESPDRequestType;
import grow.names.specification.ubl.schema.xsd.espdresponse_1.ESPDResponseType;
import oasis.names.specification.ubl.schema.xsd.commonaggregatecomponents_2.*;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.*;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import java.util.Date;
import java.util.UUID;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * Simple factory for creating simple UBL elements that are shared between a {@link ESPDRequestType}
 * and {@link ESPDResponseType}.
 * <p/>
 * Created by ratoico on 11/26/15.
 */
public final class CommonUblFactory {

    public enum EspdType {
        ESPD_REQUEST,
        ESPD_RESPONSE
    }

    private CommonUblFactory() {

    }

    /**
     * Identifies the earliest version of the UBL 2 schema for this document type that defines all of the elements
     * that might be encountered in the current instance.
     *
     * @return The corresponding UBL element
     */
    public static UBLVersionIDType buildUblVersionIDType() {
        UBLVersionIDType versionIDType = new UBLVersionIDType();
        versionIDType.setValue("2.1");
        versionIDType.setSchemeAgencyID("OASIS-UBL-TC");
        return versionIDType;
    }

    /**
     * Identifies a user-defined customization of UBL for a specific use.
     *
     * @param espdType If it's about a ESPD Request or Response
     *
     * @return The corresponding UBL element
     */
    public static CustomizationIDType buildCustomizationIDType(EspdType espdType) {
        CustomizationIDType customizationIDType = new CustomizationIDType();
        if (EspdType.ESPD_REQUEST.equals(espdType)) {
            customizationIDType.setValue("urn:www.cenbii.eu:transaction:biitrns070:ver3.0");
        } else if (EspdType.ESPD_RESPONSE.equals(espdType)) {
            customizationIDType.setValue("urn:www.cenbii.eu:transaction:biitrns092:ver3.0");
        }
        customizationIDType.setSchemeAgencyID("BII");
        customizationIDType.setSchemeVersionID("3.0");
        customizationIDType.setSchemeName("CustomizationID");
        return customizationIDType;
    }

    /**
     * Identifier of a document. A transaction instance must contain an identifier.
     * The identifier enables positive referencing the document instance for various purposes
     * including referencing between transactions that are part of the same process.
     *
     * @return The corresponding UBL element
     */
    public static IDType buildDocumentIdentifierType() {
        return buildDocumentIdType(UUID.randomUUID().toString());
    }

    /**
     * Indicates whether this document is a copy (true) or not (false).
     *
     * @param isCopy If the document is a copy or not
     *
     * @return The corresponding UBL element
     */
    public static CopyIndicatorType buildCopyIndicatorType(boolean isCopy) {
        CopyIndicatorType copyIndicatorType = new CopyIndicatorType();
        copyIndicatorType.setValue(isCopy);
        return copyIndicatorType;
    }

    /**
     * The version of the document that has been identified with the document identifier.
     *
     * @return The corresponding UBL element
     */
    public static VersionIDType buildVersionIDType(String version) {
        VersionIDType versionIDType = new VersionIDType();
        versionIDType.setValue(version);
        versionIDType.setSchemeAgencyID(Agency.EU_COM_GROW.getIdentifier());
        return versionIDType;
    }

    /**
     * Date when the referred document was issued.
     *
     * @param when The desired date
     *
     * @return The corresponding UBL element
     */
    public static IssueDateType buildIssueDateType(Date when) {
        if (when == null) {
            return null;
        }
        IssueDateType issueDateType = new IssueDateType();
        issueDateType.setValue(new LocalDate(when));
        return issueDateType;
    }

    /**
     * Time when the document was issued.
     *
     * @param when The desired time
     *
     * @return The corresponding UBL element
     */
    public static IssueTimeType buildIssueTimeType(Date when) {
        if (when == null) {
            return null;
        }
        IssueTimeType issueTimeType = new IssueTimeType();
        issueTimeType.setValue(new LocalTime(when));
        return issueTimeType;
    }

    public static ContractFolderIDType buildContractFolderType(String fileReferenceNumber) {
        ContractFolderIDType contractFolderIDType = new ContractFolderIDType();
        contractFolderIDType.setValue(fileReferenceNumber);
        contractFolderIDType.setSchemeAgencyID("TeD");
        return contractFolderIDType;
    }

    /**
     * Reference to the Contract Notice in TeD.
     * <p></p>
     * For procurement projects above the threshold it is compulsory to specify the following data,
     * by means of an AdditionalDocumentReference element, about the Contract Notice published in TeD:
     * the OJEU S number[], date[], page[], Notice number in OJS: YYYY/S [][][]-[][][][][][],
     * Title and Description of the Procurement Project
     *
     * @param espdDocument The ESPD model containing the contract notice information
     *
     * @return A UBL document reference element
     */
    public static DocumentReferenceType buildProcurementProcedureType(EspdDocument espdDocument) {
        DocumentReferenceType documentReferenceType = new DocumentReferenceType();
        if (isBlank(espdDocument.getOjsNumber())) {
            documentReferenceType.setID(buildTemporaryDocumentIdType(MarshallingConstants.TEMPORARY_OJS_NUMBER));
        } else {
            documentReferenceType.setID(buildDocumentIdType(espdDocument.getOjsNumber()));
        }

        // A reference to a Contract Notice published in the TeD platform (European Commission, Office of Publications).
        documentReferenceType.setDocumentTypeCode(buildDocumentTypeCode(DocumentTypeCode.TED_CN));
        documentReferenceType.setAttachment(
                buildAttachmentType(espdDocument.getTedUrl(), espdDocument.getProcedureTitle(),
                        espdDocument.getProcedureShortDesc(), espdDocument.getTedReceptionId()));

        return documentReferenceType;
    }

    /**
     * Build a reference to the original {@link ESPDRequestType} document that was used to generate a
     * {@link ESPDResponseType}.
     *
     * @param metadata Information regarding the ESPD request
     *
     * @return A UBL document reference element
     */
    public static DocumentReferenceType buildEspdRequestReferenceType(EspdRequestMetadata metadata) {
        if (isBlank(metadata.getId())) {
            return null;
        }

        DocumentReferenceType documentReferenceType = new DocumentReferenceType();

        documentReferenceType.setID(buildDocumentIdType(metadata.getId()));
        documentReferenceType.setIssueDate(buildIssueDateType(metadata.getIssueDate()));
        documentReferenceType.setIssueTime(buildIssueTimeType(metadata.getIssueDate()));
        documentReferenceType.setDocumentTypeCode(buildDocumentTypeCode(DocumentTypeCode.ESPD_REQUEST));
        documentReferenceType.getDocumentDescription().add(buildDocumentDescription(metadata.getDescription()));
        documentReferenceType.setAttachment(buildAttachmentType(metadata.getUrl(), null, null, null));

        return documentReferenceType;
    }

    private static IDType buildDocumentIdType(String id) {
        return buildDocumentIdType(id, "ISO/IEC 9834-8:2008 - 4UUID");
    }

    private static IDType buildTemporaryDocumentIdType(String id) {
        return buildDocumentIdType(id, MarshallingConstants.TEMPORARY_OJS_NUMBER_SCHEME_ID);
    }

    private static IDType buildDocumentIdType(String id, String schemeId) {
        IDType idType = new IDType();
        idType.setSchemeID(schemeId);
        idType.setSchemeAgencyID(Agency.EU_COM_GROW.getIdentifier());
        idType.setSchemeAgencyName(Agency.EU_COM_GROW.getLongName());
        idType.setSchemeVersionID("1.1");
        idType.setValue(id);

        return idType;
    }

    private static DocumentTypeCodeType buildDocumentTypeCode(DocumentTypeCode typeCode) {
        DocumentTypeCodeType documentTypeCode = new DocumentTypeCodeType();
        documentTypeCode.setListAgencyID(Agency.EU_COM_GROW.getIdentifier());
        documentTypeCode.setListID("ReferencesTypeCodes");
        documentTypeCode.setListVersionID("1.0");
        documentTypeCode.setValue(typeCode.name());
        return documentTypeCode;
    }

    private static DocumentDescriptionType buildDocumentDescription(String description) {
        DocumentDescriptionType descriptionType = new DocumentDescriptionType();
        descriptionType.setValue(description);
        return descriptionType;
    }

    private static AttachmentType buildAttachmentType(String url, String fileName, String description,
            String tedReceptionId) {
        AttachmentType attachmentType = new AttachmentType();
        ExternalReferenceType externalReferenceType = new ExternalReferenceType();

        if (isNotBlank(url)) {
            URIType uriType = new URIType();
            uriType.setValue(url);
            externalReferenceType.setURI(uriType);
        }

        if (isNotBlank(fileName)) {
            FileNameType fileNameType = new FileNameType();
            fileNameType.setValue(fileName);
            externalReferenceType.setFileName(fileNameType);
        }

        DescriptionType descriptionType = new DescriptionType();
        if (isNotBlank(description)) {
            descriptionType.setValue(description);
        } else {
            // we need a default value so that we can read the tedReceptionId as a second description
            descriptionType.setValue("-");
        }
        externalReferenceType.getDescription().add(descriptionType);

        if (isNotBlank(tedReceptionId)) {
            DescriptionType receptionDescription = new DescriptionType();
            receptionDescription.setValue(tedReceptionId);
            externalReferenceType.getDescription().add(receptionDescription);
        }

        attachmentType.setExternalReference(externalReferenceType);

        return attachmentType;
    }

    public static ProcurementProjectLotType buildProcurementProjectLot(String lotConcerned) {
        ProcurementProjectLotType lotType = new ProcurementProjectLotType();
        IDType idType = new IDType();
        if (StringUtils.isNotBlank(lotConcerned)) {
            idType.setValue(lotConcerned);
        } else {
            idType.setValue(MarshallingConstants.NO_LOTS);
        }
        lotType.setID(idType);
        return lotType;
    }

    static CountryType buildCountryType(CacCountry country) {
        CountryType countryType = new CountryType();
        IdentificationCodeType identificationCodeType = new IdentificationCodeType();
        identificationCodeType.setValue(country.getIso2Code());
        identificationCodeType.setListAgencyID("ISO");
        identificationCodeType.setListName(country.getIsoType());
        identificationCodeType.setListVersionID("1.0");
        countryType.setIdentificationCode(identificationCodeType);
        return countryType;
    }
}
