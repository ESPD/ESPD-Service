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
    YOUR_ANSWER("", CriterionText.YOUR_ANSWER_TEXT, ExpectedResponseType.ANSWER_INDICATOR),
    /**
     *
     */
    DATE_OF_CONVICTION("", CriterionText.DATE_OF_CONVICTION_TEXT, ExpectedResponseType.DATE),
    /**
     *
     */
    REASON("", CriterionText.REASON_TEXT, ExpectedResponseType.DESCRIPTION),
    /**
     *
     */
    WHO_CONVICTED("", CriterionText.WHO_CONVICTED_TEXT, ExpectedResponseType.DESCRIPTION),
    /**
     *
     */
    LENGTH_PERIOD_EXCLUSION("", CriterionText.LENGTH_PERIOD_EXCLUSION_TEXT, ExpectedResponseType.TEXT),
    /**
     *
     */
    MEASURES_SELF_CLEANING("", CriterionText.MEASURES_SELF_CLEANING_TEXT, ExpectedResponseType.ANSWER_INDICATOR),
    /**
     *
     */
    PLEASE_DESCRIBE("", CriterionText.PLEASE_DESCRIBE_TEXT, ExpectedResponseType.DESCRIPTION),
    /**
     *
     */
    INFO_AVAILABLE_ELECTRONICALLY("", CriterionText.INFO_AVAILABLE_ELECTRONICALLY_TEXT, ExpectedResponseType.ANSWER_INDICATOR),
    /**
     *
     */
    URL("", CriterionText.URL_TEXT, ExpectedResponseType.URL),
    /**
     *
     */
    URL_CODE("", CriterionText.URL_CODE_TEXT, ExpectedResponseType.TEXT),
    /**
     *
     */
    COUNTRY_MS("", CriterionText.COUNTRY_MS_TEXT, ExpectedResponseType.COUNTRY),
    /**
     *
     */
    AMOUNT("", CriterionText.AMOUNT_TEXT, ExpectedResponseType.AMOUNT),
    /**
     *
     */
    BREACH_OF_OBLIGATIONS_OTHER_THAN("", CriterionText.BREACH_OF_OBLIGATIONS_OTHER_THAN_TEXT, ExpectedResponseType.ANSWER_INDICATOR),
    /**
     *
     */
    DESCRIBE_MEANS("", CriterionText.DESCRIBE_MEANS_TEXT, ExpectedResponseType.DESCRIPTION),
    /**
     *
     */
    DECISION_FINAL_AND_BINDING("", CriterionText.DECISION_FINAL_AND_BINDING_TEXT, ExpectedResponseType.AMOUNT),
    /**
     *
     */
    EO_FULFILLED_OBLIGATION("", CriterionText.EO_FULFILLED_OBLIGATIONS_TEXT, ExpectedResponseType.DESCRIPTION),
    /**
     *
     */
    REASONS_NEVERTHELESS_CONTRACT("", CriterionText.REASONS_NEVERTHELESS_CONTRACT_TEXT, ExpectedResponseType.DESCRIPTION),
    ;


//    /**
//     *
//     */
//    PARTICIPATION_CRIMINAL_ORGANISATION_1("802f9b05-b463-4455-b92b-35ef2ac4af68",
//            CriterionRequirementConstants.GENERAL_YES_TEXT),
//    /**
//     *
//     */
//    PARTICIPATION_CRIMINAL_ORGANISATION_2("fba0982a-6d69-41aa-8494-8d46e2eb97ca",
//            CriterionRequirementConstants.SELF_CLEANING_TEXT),
//    /**
//     *
//     */
//    PARTICIPATION_CRIMINAL_ORGANISATION_3("4b435c95-3de7-43dc-ae8d-6e7028e95a38",
//            CriterionRequirementConstants.INFO_ELECTRONICALLY_TEXT),
//    /**
//     *
//     */
//    CORRUPTION_1("6daa0604-d8d3-451d-8e72-9a8743437629", CriterionRequirementConstants.GENERAL_YES_TEXT),
//    /**
//     *
//     */
//    CORRUPTION_2("21bc3c38-4797-41cf-a9da-f57275615cc9", CriterionRequirementConstants.SELF_CLEANING_TEXT),
//    /**
//     *
//     */
//    CORRUPTION_3("ae160e97-ef2b-4ac9-95cb-1c346890766b", CriterionRequirementConstants.INFO_ELECTRONICALLY_TEXT),
//    /**
//     *
//     */
//    FRAUD_1("f8da1acf-af6e-4433-9342-10b246281207", CriterionRequirementConstants.GENERAL_YES_TEXT),
//    /**
//     *
//     */
//    FRAUD_2("679be0c0-5ef7-4edb-b600-f4e751e9b238", CriterionRequirementConstants.SELF_CLEANING_TEXT),
//    /**
//     *
//     */
//    FRAUD_3("f954cb48-fd3a-46b6-93b4-dbd98ddd8ab8", CriterionRequirementConstants.INFO_ELECTRONICALLY_TEXT),
//    /**
//     *
//     */
//    TERRORIST_OFFENCES_1("6ce51802-2dfd-4a43-aaf8-b2fdaada0466", CriterionRequirementConstants.GENERAL_YES_TEXT),
//    /**
//     *
//     */
//    TERRORIST_OFFENCES_2("1134da12-b3d2-45ad-80b1-37d635e1d6e0", CriterionRequirementConstants.SELF_CLEANING_TEXT),
//    /**
//     *
//     */
//    TERRORIST_OFFENCES_3("02615e92-f733-4a62-a6ee-08c324abb8d1",
//            CriterionRequirementConstants.INFO_ELECTRONICALLY_TEXT),
//    /**
//     *
//     */
//    MONEY_LAUNDERING_1("20238d99-4fbb-48b0-aba0-dd06489d14ef", CriterionRequirementConstants.GENERAL_YES_TEXT),
//    /**
//     *
//     */
//    MONEY_LAUNDERING_2("9f0d8862-02fe-45ae-9e39-ebb235cfbaf1", CriterionRequirementConstants.SELF_CLEANING_TEXT),
//    /**
//     *
//     */
//    MONEY_LAUNDERING_3("25f57c43-f472-4532-a99e-8950052f276a", CriterionRequirementConstants.INFO_ELECTRONICALLY_TEXT),
//    /**
//     *
//     */
//    CHILD_LABOUR_1("29049af7-65cc-4314-851b-afa06cac6108", CriterionRequirementConstants.GENERAL_YES_TEXT),
//    /**
//     *
//     */
//    CHILD_LABOUR_2("790374f1-9c94-4267-93bb-fcaba9ca6a7d", CriterionRequirementConstants.SELF_CLEANING_TEXT),
//    /**
//     *
//     */
//    CHILD_LABOUR_3("e8b2a4e1-38a0-494d-89c6-484c0f0dc983", CriterionRequirementConstants.INFO_ELECTRONICALLY_TEXT),
//    /**
//     *
//     */
//    PAYMENT_TAXES_1("d30637e4-f7ec-43da-962d-b1d48fd161e7", CriterionRequirementConstants.PAYMENT_YES_TEXT),
//    /**
//     *
//     */
//    PAYMENT_TAXES_2("b789ef69-6136-4627-a8fd-2592d07e8e26",
//            CriterionRequirementConstants.PAYMENT_BREACH_OBLIGATIONS_TEXT),
//    /**
//     *
//     */
//    PAYMENT_TAXES_3("fe04e6fe-af83-4896-ad0c-7983e5138aea", CriterionRequirementConstants.SELF_CLEANING_TEXT),
//    /**
//     *
//     */
//    PAYMENT_TAXES_4("2b7e912b-7343-493d-99e0-5483e944a7e0", CriterionRequirementConstants.INFO_ELECTRONICALLY_TEXT),
//    /**
//     *
//     */
//    PAYMENT_SOCIAL_SECURITY_1("82133444-f458-4100-878c-f43e02efb6a6", CriterionRequirementConstants.PAYMENT_YES_TEXT),
//    /**
//     *
//     */
//    PAYMENT_SOCIAL_SECURITY_2("4674bd09-7ef1-43fc-aa1a-12ed274a305a",
//            CriterionRequirementConstants.PAYMENT_BREACH_OBLIGATIONS_TEXT),
//    /**
//     *
//     */
//    PAYMENT_SOCIAL_SECURITY_3("1efc023b-0cd3-49d1-b646-0c9ba5b9b888", CriterionRequirementConstants.SELF_CLEANING_TEXT),
//    /**
//     *
//     */
//    PAYMENT_SOCIAL_SECURITY_4("ac9c530a-07b8-4a9d-b82b-c40ad5373eec",
//            CriterionRequirementConstants.INFO_ELECTRONICALLY_TEXT),
//    /**
//     *
//     */
//    BREACH_OBLIGATIONS_ENV_1("20000170-bc67-4536-9420-01ea0f90db7a",
//            CriterionRequirementConstants.PLEASE_DESCRIBE_TEXT),
//    /**
//     *
//     */
//    BREACH_OBLIGATIONS_ENV_2("15c7eac6-ef84-4b93-8d39-a15022a54fb0", CriterionRequirementConstants.SELF_CLEANING_TEXT),
//    /**
//     *
//     */
//    BANKRUPTCY_1("d2e52b5d-2e34-4166-9d40-21470af8eb7c", CriterionRequirementConstants.PLEASE_DESCRIBE_TEXT),
//    /**
//     *
//     */
//    BANKRUPTCY_2("638e1e4b-863b-4768-b718-f6dde9601983", CriterionRequirementConstants.REASONS_PERFORM_CONTRACT_TEXT),
//    /**
//     *
//     */
//    BANKRUPTCY_3("44387dd3-5b75-43f6-8562-9615bb913cde", CriterionRequirementConstants.INFO_ELECTRONICALLY_TEXT),
//    /**
//     *
//     */
//    INSOLVENCY_1("b9dfe216-7c6d-4ca5-b1a4-793015399eb1", CriterionRequirementConstants.PLEASE_DESCRIBE_TEXT),
//    /**
//     *
//     */
//    INSOLVENCY_2("ff6f6761-2d7f-47f6-ade3-757639cb7a5d", CriterionRequirementConstants.REASONS_PERFORM_CONTRACT_TEXT),
//    /**
//     *
//     */
//    INSOLVENCY_3("297e9485-bb1c-4321-a927-a5681afe1d27", CriterionRequirementConstants.INFO_ELECTRONICALLY_TEXT),
//    /**
//     *
//     */
//    ARRANGEMENT_WITH_CREDITORS_1("139c0bc8-4341-4328-94c3-b5182e468b6e",
//            CriterionRequirementConstants.PLEASE_DESCRIBE_TEXT),
//    /**
//     *
//     */
//    ARRANGEMENT_WITH_CREDITORS_2("05c4f8c9-2bed-43ef-847b-0e9bb67b66dd",
//            CriterionRequirementConstants.SELF_CLEANING_TEXT),
//    /**
//     *
//     */
//    ARRANGEMENT_WITH_CREDITORS_3("86f20dd3-08d4-44ef-a8e6-c7b3c1302737",
//            CriterionRequirementConstants.INFO_ELECTRONICALLY_TEXT),
//    /**
//     *
//     */
//    ANALOGOUS_SITUATION_1("e683f15e-1fc1-4179-aa71-acbe1860a266", CriterionRequirementConstants.PLEASE_DESCRIBE_TEXT),
//    /**
//     *
//     */
//    ANALOGOUS_SITUATION_2("1f0092c2-5d3f-4979-935f-8edaab9eed35", CriterionRequirementConstants.SELF_CLEANING_TEXT),
//    /**
//     *
//     */
//    ANALOGOUS_SITUATION_3("c6ba6251-7035-4e6e-9831-09d724f20101",
//            CriterionRequirementConstants.INFO_ELECTRONICALLY_TEXT),
//    /**
//     *
//     */
//    ASSETS_ADMINISTERED_BY_LIQUIDATOR_1("5c39ff37-4248-4516-9dd0-457000823fec",
//            CriterionRequirementConstants.PLEASE_DESCRIBE_TEXT),
//    /**
//     *
//     */
//    ASSETS_ADMINISTERED_BY_LIQUIDATOR_2("90785e17-82f3-44dd-8305-a4b87ae9efc4",
//            CriterionRequirementConstants.SELF_CLEANING_TEXT),
//    /**
//     *
//     */
//    ASSETS_ADMINISTERED_BY_LIQUIDATOR_3("5249d04c-a650-4aa2-a7f3-df0517f2f3c9",
//            CriterionRequirementConstants.INFO_ELECTRONICALLY_TEXT),
//    /**
//     *
//     */
//    BUSINESS_ACTIVITIES_SUSPENDED_1("d9fac652-e2d9-43ca-9ee1-4b86e582a3b9",
//            CriterionRequirementConstants.PLEASE_DESCRIBE_TEXT),
//    /**
//     *
//     */
//    BUSINESS_ACTIVITIES_SUSPENDED_2("9facfe9c-07d1-416f-bfe9-582c1a7776b3",
//            CriterionRequirementConstants.SELF_CLEANING_TEXT),
//    /**
//     *
//     */
//    GUILTY_OF_PROFESSIONAL_MISCONDUCT_1("458dd6b0-5d3c-4aea-ab29-25e4606baa5f",
//            CriterionRequirementConstants.PLEASE_DESCRIBE_TEXT),
//    /**
//     *
//     */
//    GUILTY_OF_PROFESSIONAL_MISCONDUCT_2("fc23518c-a47c-4784-a119-0e2f4c0fc2ff",
//            CriterionRequirementConstants.SELF_CLEANING_TEXT),
//    /**
//     *
//     */
//    CONFLICT_OF_INTEREST_1("e1cc66aa-1406-4719-98e5-ab0ca08e5092", CriterionRequirementConstants.PLEASE_DESCRIBE_TEXT),
//    /**
//     *
//     */
//    CONFLICT_OF_INTEREST_2("e1f3c3a0-0b30-48ce-8a14-bb8e8ee78808", CriterionRequirementConstants.SELF_CLEANING_TEXT),
//    /**
//     *
//     */
//    DIRECT_INVOLVEMENT_PROCUREMENT_PROCEDURE_1("d51b51c0-44d5-44a4-85df-736dec6f351e",
//            CriterionRequirementConstants.PLEASE_DESCRIBE_TEXT),
//    /**
//     *
//     */
//    EARLY_TERMINATION_1("d1104636-f3c3-48f5-95eb-2db9c6d9e7c2", CriterionRequirementConstants.PLEASE_DESCRIBE_TEXT),
//    /**
//     *
//     */
//    EARLY_TERMINATION_2("80d631f0-d6cd-47cc-874c-e8c4706d5cfb", CriterionRequirementConstants.SELF_CLEANING_TEXT),
//    /**
//     *
//     */
//    GUILTY_OF_MISINTERPRETATION_1("6c44b54c-f598-4ad9-bc0f-4133d55bd7c9",
//            CriterionRequirementConstants.PLEASE_DESCRIBE_TEXT),
//    /**
//     *
//     */
//    GUILTY_OF_MISINTERPRETATION_2("d9502d1f-6447-47e0-9cc0-601b7cded065",
//            CriterionRequirementConstants.SELF_CLEANING_TEXT),
//    /**
//     *
//     */
//    NATIONAL_EXCLUSION_GROUNDS_1("b3d3e57f-8f62-4b36-8bf2-7377bbff5950",
//            CriterionRequirementConstants.PLEASE_DESCRIBE_TEXT),
//    /**
//     *
//     */
//    NATIONAL_EXCLUSION_GROUNDS_2("4601e2f3-bae5-4336-a94a-5e4f2d08b8a6",
//            CriterionRequirementConstants.INFO_ELECTRONICALLY_TEXT);

    private final String id;

    private final String description;

    private final ExpectedResponseType responseType;

    ExclusionCriterionRequirement(String id, String description, ExpectedResponseType responseType) {
        this.id = id;
        this.description = description;
        this.responseType = responseType;
    }

//    public static final List<ExclusionCriterionRequirement> CRIMINAL_CONVICTIONS_REQUIREMENTS = Collections.unmodifiableList(
//            Arrays.asList(PARTICIPATION_CRIMINAL_ORGANISATION_1, PARTICIPATION_CRIMINAL_ORGANISATION_2,
//                    PARTICIPATION_CRIMINAL_ORGANISATION_3));
//
//    public static final List<ExclusionCriterionRequirement> CORRUPTION_REQUIREMENTS = Collections.unmodifiableList(
//            Arrays.asList(CORRUPTION_1, CORRUPTION_2, CORRUPTION_3));
//
//    public static final List<ExclusionCriterionRequirement> FRAUD_REQUIREMENTS = Collections.unmodifiableList(
//            Arrays.asList(FRAUD_1, FRAUD_2, FRAUD_3));
//
//    public static final List<ExclusionCriterionRequirement> TERRORIST_OFFENCES_REQUIREMENTS = Collections.unmodifiableList(
//            Arrays.asList(TERRORIST_OFFENCES_1, TERRORIST_OFFENCES_2, TERRORIST_OFFENCES_3));
//
//    public static final List<ExclusionCriterionRequirement> MONEY_LAUNDERING_REQUIREMENTS = Collections.unmodifiableList(
//            Arrays.asList(MONEY_LAUNDERING_1, MONEY_LAUNDERING_2, MONEY_LAUNDERING_3));
//
//    public static final List<ExclusionCriterionRequirement> CHILD_LABOUR_REQUIREMENTS = Collections.unmodifiableList(
//            Arrays.asList(CHILD_LABOUR_1, CHILD_LABOUR_2, CHILD_LABOUR_3));
//
//    public static final List<ExclusionCriterionRequirement> PAYMENT_TAXES_REQUIREMENTS = Collections.unmodifiableList(
//            Arrays.asList(PAYMENT_TAXES_1, PAYMENT_TAXES_2, PAYMENT_TAXES_3, PAYMENT_TAXES_4));
//
//    public static final List<ExclusionCriterionRequirement> PAYMENT_SOCIAL_SECURITY_REQUIREMENTS = Collections.unmodifiableList(
//            Arrays.asList(PAYMENT_SOCIAL_SECURITY_1, PAYMENT_SOCIAL_SECURITY_2, PAYMENT_SOCIAL_SECURITY_3,
//                    PAYMENT_SOCIAL_SECURITY_4));
//
//    public static final List<ExclusionCriterionRequirement> BREACH_OBLIGATIONS_ENV_REQUIREMENTS = Collections.unmodifiableList(
//            Arrays.asList(BREACH_OBLIGATIONS_ENV_1, BREACH_OBLIGATIONS_ENV_2));
//
//    public static final List<ExclusionCriterionRequirement> BANKRUPTCY_REQUIREMENTS = Collections.unmodifiableList(
//            Arrays.asList(BANKRUPTCY_1, BANKRUPTCY_2, BANKRUPTCY_3));
//
//    public static final List<ExclusionCriterionRequirement> INSOLVENCY_REQUIREMENTS = Collections.unmodifiableList(
//            Arrays.asList(INSOLVENCY_1, INSOLVENCY_2, INSOLVENCY_3));
//
//    public static final List<ExclusionCriterionRequirement> ARRANGEMENT_WITH_CREDITORS_REQUIREMENTS = Collections
//            .unmodifiableList(
//                    Arrays.asList(ARRANGEMENT_WITH_CREDITORS_1, ARRANGEMENT_WITH_CREDITORS_2,
//                            ARRANGEMENT_WITH_CREDITORS_3));
//
//    public static final List<ExclusionCriterionRequirement> ANALOGOUS_SITUATION_REQUIREMENTS = Collections.unmodifiableList(
//            Arrays.asList(ANALOGOUS_SITUATION_1, ANALOGOUS_SITUATION_2, ANALOGOUS_SITUATION_3));
//
//    public static final List<ExclusionCriterionRequirement> ASSETS_ADMINISTERED_BY_LIQUIDATOR_REQUIREMENTS = Collections
//            .unmodifiableList(
//                    Arrays.asList(ASSETS_ADMINISTERED_BY_LIQUIDATOR_1, ASSETS_ADMINISTERED_BY_LIQUIDATOR_2,
//                            ASSETS_ADMINISTERED_BY_LIQUIDATOR_3));
//
//    public static final List<ExclusionCriterionRequirement> BUSINESS_ACTIVITIES_SUSPENDED_REQUIREMENTS = Collections
//            .unmodifiableList(
//                    Arrays.asList(BUSINESS_ACTIVITIES_SUSPENDED_1, BUSINESS_ACTIVITIES_SUSPENDED_2));
//
//    public static final List<ExclusionCriterionRequirement> PROFESSIONAL_MISCONDUCT_REQUIREMENTS = Collections.unmodifiableList(
//            Arrays.asList(GUILTY_OF_PROFESSIONAL_MISCONDUCT_1, GUILTY_OF_PROFESSIONAL_MISCONDUCT_2));
//
//    public static final List<ExclusionCriterionRequirement> CONFLICT_OF_INTEREST_REQUIREMENTS = Collections.unmodifiableList(
//            Arrays.asList(CONFLICT_OF_INTEREST_1, CONFLICT_OF_INTEREST_2));
//
//    public static final List<ExclusionCriterionRequirement> DIRECT_INVOLVEMENT_PROCUREMENT_PROCEDURE_REQUIREMENTS =
//            Collections.unmodifiableList(
//                    Collections.singletonList(DIRECT_INVOLVEMENT_PROCUREMENT_PROCEDURE_1));
//
//    public static final List<ExclusionCriterionRequirement> EARLY_TERMINATION_REQUIREMENTS = Collections.unmodifiableList(
//            Arrays.asList(EARLY_TERMINATION_1, EARLY_TERMINATION_2));
//
//    public static final List<ExclusionCriterionRequirement> GUILTY_OF_MISINTERPRETATION_REQUIREMENTS = Collections
//            .unmodifiableList(Arrays.asList(GUILTY_OF_MISINTERPRETATION_1, GUILTY_OF_MISINTERPRETATION_2));
//
//    public static final List<ExclusionCriterionRequirement> NATIONAL_EXCLUSION_GROUNDS_REQUIREMENTS = Collections
//            .unmodifiableList(Arrays.asList(NATIONAL_EXCLUSION_GROUNDS_1, NATIONAL_EXCLUSION_GROUNDS_2));
//
//
//    private static class CriterionRequirementConstants {
//        private static final String GENERAL_YES_TEXT = "If yes indicate: a) Date of conviction; b) Reason; c) Who has been convicted; d) Length of the period of exclusion.";
//        private static final String SELF_CLEANING_TEXT = "Have you taken measures to demonstrate your reliability (\"Self-Cleaning\")?  Please describe them.";
//        private static final String INFO_ELECTRONICALLY_TEXT = "If the relevant documentation is available electronically, please indicate where to obtain the evidences: web address, issuing authority or body, precise reference of the documentation.";
//        private static final String PAYMENT_YES_TEXT = "If yes, please indicate the country or Member State concerned and the amount concerned.";
//        private static final String PAYMENT_BREACH_OBLIGATIONS_TEXT = "If this breach of obligations has been established through a judicial or administrative decision, please answer whether the decision was final and binding (answer \"YES\" or \"NO\"); If yes, provide the date of the conviction or decision and the length of the period of exclusion; If no describe which other means were used to establish the breach, if any.";
//        private static final String REASONS_PERFORM_CONTRACT_TEXT = "If yes, motivate the reasons for being nevertheless to perform the contract.";
//        private static final String PLEASE_DESCRIBE_TEXT = "If yes, please describe them.";
//
//    }



}
