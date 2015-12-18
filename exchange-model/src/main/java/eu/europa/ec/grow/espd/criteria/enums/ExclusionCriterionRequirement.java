package eu.europa.ec.grow.espd.criteria.enums;

import eu.europa.ec.grow.espd.entities.CcvCriterionRequirement;
import lombok.Getter;

/**
 * Created by vigi on 11/23/15:11:50 AM.
 */
@Getter
public enum ExclusionCriterionRequirement implements CcvCriterionRequirement {

    /**
     *
     */
    YOUR_ANSWER("974c8196-9d1c-419c-9ca9-45bb9f5fd59a", CriterionText.YOUR_ANSWER_TEXT,
            ExpectedResponseType.INDICATOR),
    /**
     *
     */
    DATE_OF_CONVICTION("ecf40999-7b64-4e10-b960-7f8ff8674cf6", CriterionText.DATE_OF_CONVICTION_TEXT,
            ExpectedResponseType.DATE),
    /**
     *
     */
    REASON("7d35fb7c-da5b-4830-b598-4f347a04dceb", CriterionText.REASON_TEXT, ExpectedResponseType.DESCRIPTION),
    /**
     *
     */
    WHO_CONVICTED("c5012430-14da-454c-9d01-34cedc6a7ded", CriterionText.WHO_CONVICTED_TEXT,
            ExpectedResponseType.DESCRIPTION),
    /**
     *
     */
    LENGTH_PERIOD_EXCLUSION("9ca9096f-edd2-4f19-b6b1-b55c83a2d5c8", CriterionText.LENGTH_PERIOD_EXCLUSION_TEXT,
            ExpectedResponseType.TEXT),
    /**
     *
     */
    MEASURES_SELF_CLEANING("20c5361b-7599-4ee6-b030-7f8323174d1e", CriterionText.MEASURES_SELF_CLEANING_TEXT,
            ExpectedResponseType.INDICATOR),
    /**
     *
     */
    PLEASE_DESCRIBE("7b07904f-e080-401a-a3a1-9a3efeeda54b", CriterionText.PLEASE_DESCRIBE_TEXT,
            ExpectedResponseType.DESCRIPTION),
    /**
     *
     */
    INFO_AVAILABLE_ELECTRONICALLY("c1347b74-1872-4060-a6db-f4044edcd7c4",
            CriterionText.INFO_AVAILABLE_ELECTRONICALLY_TEXT, ExpectedResponseType.INDICATOR),
    /**
     *
     */
    URL("f4313bb6-21b6-499e-bdff-debe10e11d2c", CriterionText.URL_TEXT, ExpectedResponseType.URL),
    /**
     *
     */
    URL_CODE("1f1cd18e-3e01-4ca2-af4c-e2981924ba8d", CriterionText.URL_CODE_TEXT, ExpectedResponseType.TEXT),
    /**
     *
     */
    COUNTRY_MS("6c87d3d4-e8eb-4253-b385-6373020ab886", CriterionText.COUNTRY_MS_TEXT, ExpectedResponseType.COUNTRY),
    /**
     *
     */
    AMOUNT("9052cc59-cfe5-41c6-a314-02a7f378ffe8", CriterionText.AMOUNT_TEXT, ExpectedResponseType.AMOUNT),
    /**
     *
     */
    BREACH_OF_OBLIGATIONS_OTHER_THAN("9b4497e6-a166-46f9-8581-7fc39ff975c4",
            CriterionText.BREACH_OF_OBLIGATIONS_OTHER_THAN_TEXT, ExpectedResponseType.INDICATOR),
    /**
     *
     */
    DESCRIBE_MEANS("201f11c3-1fa2-4464-acc0-f021266fd881", CriterionText.DESCRIBE_MEANS_TEXT,
            ExpectedResponseType.DESCRIPTION),
    /**
     *
     */
    DECISION_FINAL_AND_BINDING("08b0c984-c5e6-4143-8493-868c39745637", CriterionText.DECISION_FINAL_AND_BINDING_TEXT,
            ExpectedResponseType.INDICATOR),
    /**
     *
     */
    EO_FULFILLED_OBLIGATION("70f8697b-8953-411a-a489-4ff62e5250d2", CriterionText.EO_FULFILLED_OBLIGATIONS_TEXT,
            ExpectedResponseType.INDICATOR),
    /**
     *
     */
    REASONS_NEVERTHELESS_CONTRACT("4e3f468a-86c4-4c99-bd15-c8b221229348",
            CriterionText.REASONS_NEVERTHELESS_CONTRACT_TEXT, ExpectedResponseType.DESCRIPTION),;

    private final String id;

    private final String description;

    private final ExpectedResponseType responseType;

    ExclusionCriterionRequirement(String id, String description, ExpectedResponseType responseType) {
        this.id = id;
        this.description = description;
        this.responseType = responseType;
    }

}
