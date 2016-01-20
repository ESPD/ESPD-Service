package eu.europa.ec.grow.espd.criteria.enums;

import eu.europa.ec.grow.espd.entities.CcvCriterionRequirement;
import lombok.Getter;

/**
 * Created by ratoico on 1/15/16 at 3:12 PM.
 */
@Getter
public enum AwardRequirement implements CcvCriterionRequirement {

    /**
     *
     */
    INDICATOR("7f18c64e-ae09-4646-9400-f3666d50af51", "", ExpectedResponseType.INDICATOR),
    /**
     *
     */
    CORRESPONDING_PERCENTAGE("4e552658-d532-4770-943b-b90efcc9788d",
            "What is the corresponding percentage of disabled or disadvantaged workers?",
            ExpectedResponseType.PERCENTAGE),
    /**
     *
     */
    DETAILS_EMPLOYEES_CATEGORY("e01d0929-c7a9-455a-aaf9-e1f7cd966336",
            "If required, please provide details on whether the employees concerned belong to a specific category of disabled or disadvantaged workers?",
            ExpectedResponseType.DESCRIPTION),
    /**
     *
     */
    PROVIDE_REGISTRATION_NUMBER("30064ad3-fc11-4579-8528-fdd0b9a5ba75",
            "a) Please provide the relevant registration or certification number, if applicable:",
            ExpectedResponseType.DESCRIPTION),
    /**
     *
     */
    REG_NO_AVAILABLE_ELECTRONICALLY("b3403349-cbc0-4d84-879e-fc0f2d90ecbd",
            "b) If the certificate of registration or certification is available electronically, please state:",
            ExpectedResponseType.DESCRIPTION),
    /**
     *
     */
    REFERENCES_REGISTRATION("792ff522-6f3f-4a62-ab6e-a8b272bc290e",
            "c) Please state the references on which the registration or certification is based, and, where applicable, the classification obtained in the official list:",
            ExpectedResponseType.DESCRIPTION),
    /**
     *
     */
    REGISTRATION_COVERS_SELECTION_CRITERIA("d9996ef5-49f9-4cf8-a2f5-31c9f4efd894",
            "d) Does the registration or certification cover all of the required selection criteria?",
            ExpectedResponseType.INDICATOR),
    /**
     *
     */
    EO_ABLE_PROVIDE_CERTIFICATE("0e71abd3-198e-49c5-8128-5708617bb191",
            "e) Will the economic operator be able to provide a certificate with regard to the payment of social security contributions and taxes or provide information enabling the contracting authority or contracting entity to obtaining it directly by accessing a national database in any Member State that is available free of charge?",
            ExpectedResponseType.DESCRIPTION),
    /**
     *
     */
    DOC_AVAILABLE_ELECTRONICALLY("caa72cea-5443-49fb-84ba-ab6c64427f77",
            "If the relevant documentation is available electronically, please indicate:",
            ExpectedResponseType.DESCRIPTION),
    /**
     *
     */
    ECONOMIC_OPERATOR_ROLE("907fd62b-02f1-452c-81a8-785bedb0c536",
            "a) Please indicate the role of the economic operator in the group (leader, responsible for specific tasks...):",
            ExpectedResponseType.DESCRIPTION),
    /**
     *
     */
    OTHER_ECONOMIC_OPERATORS("7c267f95-a3a7-49ef-abd9-e121dcd641a9",
            "b) Please identify the other economic operators participating in the procurement procedure together:",
            ExpectedResponseType.DESCRIPTION),
    /**
     *
     */
    PARTICIPANT_GROUP_NAME("96f38793-4469-4153-aba6-c613282cdbdc",
            "c) Where applicable, name of the participating group:", ExpectedResponseType.DESCRIPTION),
    /**
     *
     */
    INFO_AVAILABLE_ELECTRONICALLY("0622bbd1-7378-45e1-8fb9-25429740ac22",
            CriterionText.INFO_AVAILABLE_ELECTRONICALLY_TEXT, ExpectedResponseType.INDICATOR),
    /**
     *
     */
    URL("ee1ee1cd-3791-4855-8b8b-28d4f4c5c007", CriterionText.URL_TEXT, ExpectedResponseType.URL),
    /**
     *
     */
    URL_CODE("1e55ff14-c643-4abc-91d7-2f4dfcdf2409", CriterionText.URL_CODE_TEXT, ExpectedResponseType.CODE),
    /**
     *
     */
    PLEASE_DESCRIBE("323f19b5-3308-4873-b2d1-767963cc81e9", CriterionText.PLEASE_DESCRIBE_TEXT,
            ExpectedResponseType.DESCRIPTION),
    ;

    private final String id;

    private final String description;

    private final ExpectedResponseType responseType;

    AwardRequirement(String id, String description, ExpectedResponseType responseType) {
        this.id = id;
        this.description = description;
        this.responseType = responseType;
    }
}
