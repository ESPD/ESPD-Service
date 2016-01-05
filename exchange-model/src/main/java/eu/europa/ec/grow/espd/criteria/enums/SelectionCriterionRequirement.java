package eu.europa.ec.grow.espd.criteria.enums;

import eu.europa.ec.grow.espd.entities.CcvCriterionRequirement;
import lombok.Getter;

/**
 * Created by ratoico on 12/8/15 at 1:45 PM.
 */
@Getter
public enum SelectionCriterionRequirement implements CcvCriterionRequirement {

    /**
     *
     */
    YOUR_ANSWER("15335c12-ad77-4728-b5ad-3c06a60d65a4", CriterionText.YOUR_ANSWER_TEXT,
            ExpectedResponseType.INDICATOR),
    /**
     *
     */
    YEAR_1("5aacceb3-280e-42f1-b2da-3d8ac7877fe9", CriterionText.YEAR_TEXT,
            ExpectedResponseType.QUANTITY),
    /**
     *
     */
    AMOUNT_1("42db0eaa-d2dd-48cb-83ac-38d73cab9b50", CriterionText.AMOUNT_TEXT,
            ExpectedResponseType.AMOUNT),
    /**
     *
     */
    YEAR_2("49a57870-7fb8-451f-a7af-fa0e7f8b97e7", CriterionText.YEAR_TEXT,
            ExpectedResponseType.QUANTITY),
    /**
     *
     */
    AMOUNT_2("4acd0a02-c267-4d05-b456-c0565c2ffd46", CriterionText.AMOUNT_TEXT,
            ExpectedResponseType.AMOUNT),
    /**
     *
     */
    YEAR_3("9d0cf1cb-27bc-4747-8579-47dce4d8d490", CriterionText.YEAR_TEXT,
            ExpectedResponseType.QUANTITY),
    /**
     *
     */
    AMOUNT_3("28fb4b41-5178-4b79-ba24-d9a62fa4a658", CriterionText.AMOUNT_TEXT,
            ExpectedResponseType.AMOUNT),
    /**
     *
     */
    REQUIRED_RATIO("e4d37adc-08cd-4f4d-a8d8-32b62b0a1f46", CriterionText.REQUIRED_RATIO_TEXT,
            ExpectedResponseType.RATIO),
    /**
     *
     */
    PLEASE_SPECIFY("3aaca389-4a7b-406b-a4b9-080845d127e7", CriterionText.PLEASE_SPECIFY_TEXT,
            ExpectedResponseType.DESCRIPTION),
    /**
     *
     */
    PERCENTAGE("612a1625-118d-4ea4-a6db-413184e7c0a8", CriterionText.PERCENTAGE_TEXT,
            ExpectedResponseType.PERCENTAGE),
    /**
     *
     */
    PLEASE_DESCRIBE("51391308-0bf6-423c-95e2-d5a54aa31fb8", CriterionText.PLEASE_DESCRIBE_TEXT,
            ExpectedResponseType.DESCRIPTION),
    /**
     *
     */
    DESCRIPTION_1("ab05ff3b-f3e1-4441-9b43-ee9912e29e92", CriterionText.DESCRIPTION_TEXT,
            ExpectedResponseType.DESCRIPTION),
    /**
     *
     */
    DESCRIPTION_2("927def36-1fa3-4018-8b45-7ee2c5b1e0af", CriterionText.DESCRIPTION_TEXT,
            ExpectedResponseType.DESCRIPTION),
    /**
     *
     */
    DESCRIPTION_3("e6ca4034-cfee-499a-9a47-c4f2862ef4d0", CriterionText.DESCRIPTION_TEXT,
            ExpectedResponseType.DESCRIPTION),
    /**
     *
     */
    DATE_1("42ec8116-31a7-4118-8612-5b04f5c8bde7", CriterionText.DATE_TEXT,
            ExpectedResponseType.DATE),
    /**
     *
     */
    DATE_2("8d0e5e16-85ed-4730-a784-d4db8f439c0c", CriterionText.DATE_TEXT,
            ExpectedResponseType.DATE),
    /**
     *
     */
    DATE_3("c953e635-580b-4d7c-a30c-2edbde3b8fdf", CriterionText.DATE_TEXT,
            ExpectedResponseType.DATE),
    /**
     *
     */
    RECIPIENTS_1("a92536ab-6783-40bb-a037-5d31f421fd85", CriterionText.RECIPIENTS_TEXT,
            ExpectedResponseType.TEXT),
    /**
     *
     */
    RECIPIENTS_2("c8babafa-b6fa-4e14-8749-d913d8f1d33b", CriterionText.RECIPIENTS_TEXT,
            ExpectedResponseType.TEXT),
    /**
     *
     */
    RECIPIENTS_3("5157e1ff-d272-4382-98a9-6953f5a15300", CriterionText.RECIPIENTS_TEXT,
            ExpectedResponseType.TEXT),
    /**
     *
     */
    INFO_AVAILABLE_ELECTRONICALLY("9dae5670-cb75-4c97-901b-96ddac5a633a",
            CriterionText.INFO_AVAILABLE_ELECTRONICALLY_TEXT, ExpectedResponseType.INDICATOR),
    /**
     *
     */
    URL("03bb1954-13ae-47d8-8ef8-b7fe0f22d700", CriterionText.URL_TEXT, ExpectedResponseType.URL),
    /**
     *
     */
    URL_CODE("e2d863a0-60cb-4e58-8c14-4c1595af48b7", CriterionText.URL_CODE_TEXT, ExpectedResponseType.TEXT),
    ;

    private final String id;

    private final String description;

    private final ExpectedResponseType responseType;

    SelectionCriterionRequirement(String id, String description, ExpectedResponseType responseType) {
        this.id = id;
        this.description = description;
        this.responseType = responseType;
    }
}
