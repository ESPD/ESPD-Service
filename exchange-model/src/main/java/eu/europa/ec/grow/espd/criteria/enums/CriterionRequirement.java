package eu.europa.ec.grow.espd.criteria.enums;

import eu.europa.ec.grow.espd.entities.CcvCriterionRequirement;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by vigi on 11/23/15:11:50 AM.
 */
@Getter
public enum CriterionRequirement implements CcvCriterionRequirement {

    /**
     *
     */
    CRIMINAL_CONVICTIONS_1("802f9b05-b463-4455-b92b-35ef2ac4af68", CriterionRequirementConstants.GENERAL_YES_TEXT),
    /**
     *
     */
    CRIMINAL_CONVICTIONS_2("fba0982a-6d69-41aa-8494-8d46e2eb97ca", CriterionRequirementConstants.SELF_CLEANING_TEXT),
    /**
     *
     */
    CRIMINAL_CONVICTIONS_3("4b435c95-3de7-43dc-ae8d-6e7028e95a38",
            CriterionRequirementConstants.INFO_ELECTRONICALLY_TEXT),
    /**
     *
     */
    CORRUPTION_1("6daa0604-d8d3-451d-8e72-9a8743437629", CriterionRequirementConstants.GENERAL_YES_TEXT),
    /**
     *
     */
    CORRUPTION_2("21bc3c38-4797-41cf-a9da-f57275615cc9", CriterionRequirementConstants.SELF_CLEANING_TEXT),
    /**
     *
     */
    CORRUPTION_3("ae160e97-ef2b-4ac9-95cb-1c346890766b", CriterionRequirementConstants.INFO_ELECTRONICALLY_TEXT),
    /**
     *
     */
    FRAUD_1("f8da1acf-af6e-4433-9342-10b246281207", CriterionRequirementConstants.GENERAL_YES_TEXT),
    /**
     *
     */
    FRAUD_2("679be0c0-5ef7-4edb-b600-f4e751e9b238", CriterionRequirementConstants.SELF_CLEANING_TEXT),
    /**
     *
     */
    FRAUD_3("f954cb48-fd3a-46b6-93b4-dbd98ddd8ab8", CriterionRequirementConstants.INFO_ELECTRONICALLY_TEXT),
    /**
     *
     */
    TERRORIST_OFFENCES_1("6ce51802-2dfd-4a43-aaf8-b2fdaada0466", CriterionRequirementConstants.GENERAL_YES_TEXT),
    /**
     *
     */
    TERRORIST_OFFENCES_2("1134da12-b3d2-45ad-80b1-37d635e1d6e0", CriterionRequirementConstants.SELF_CLEANING_TEXT),
    /**
     *
     */
    TERRORIST_OFFENCES_3("02615e92-f733-4a62-a6ee-08c324abb8d1",
            CriterionRequirementConstants.INFO_ELECTRONICALLY_TEXT),
    /**
     *
     */
    MONEY_LAUNDERING_1("20238d99-4fbb-48b0-aba0-dd06489d14ef", CriterionRequirementConstants.GENERAL_YES_TEXT),
    /**
     *
     */
    MONEY_LAUNDERING_2("9f0d8862-02fe-45ae-9e39-ebb235cfbaf1", CriterionRequirementConstants.SELF_CLEANING_TEXT),
    /**
     *
     */
    MONEY_LAUNDERING_3("25f57c43-f472-4532-a99e-8950052f276a", CriterionRequirementConstants.INFO_ELECTRONICALLY_TEXT),
    /**
     *
     */
    CHILD_LABOUR_1("29049af7-65cc-4314-851b-afa06cac6108", CriterionRequirementConstants.GENERAL_YES_TEXT),
    /**
     *
     */
    CHILD_LABOUR_2("790374f1-9c94-4267-93bb-fcaba9ca6a7d", CriterionRequirementConstants.SELF_CLEANING_TEXT),
    /**
     *
     */
    CHILD_LABOUR_3("e8b2a4e1-38a0-494d-89c6-484c0f0dc983", CriterionRequirementConstants.INFO_ELECTRONICALLY_TEXT),
    /**
     *
     */
    PAYMENT_TAXES_1("d30637e4-f7ec-43da-962d-b1d48fd161e7", CriterionRequirementConstants.PAYMENT_YES_TEXT),
    /**
     *
     */
    PAYMENT_TAXES_2("2d39df06-2c5b-40bd-b5d1-86d377384baa", CriterionRequirementConstants.PAYMENT_BREACH_OBLIGATIONS_TEXT),
    /**
     *
     */
    PAYMENT_TAXES_3("fe04e6fe-af83-4896-ad0c-7983e5138aea", CriterionRequirementConstants.SELF_CLEANING_TEXT),
    /**
     *
     */
    PAYMENT_TAXES_4("2b7e912b-7343-493d-99e0-5483e944a7e0", CriterionRequirementConstants.INFO_ELECTRONICALLY_TEXT),
    /**
     *
     */
    PAYMENT_SOCIAL_SECURITY_1("82133444-f458-4100-878c-f43e02efb6a6", CriterionRequirementConstants.PAYMENT_YES_TEXT),
    /**
     *
     */
    // TODO get this id
    PAYMENT_SOCIAL_SECURITY_2("//TODO need id here", CriterionRequirementConstants.PAYMENT_BREACH_OBLIGATIONS_TEXT),
    /**
     *
     */
    PAYMENT_SOCIAL_SECURITY_3("1efc023b-0cd3-49d1-b646-0c9ba5b9b888", CriterionRequirementConstants.SELF_CLEANING_TEXT),
    /**
     *
     */
    PAYMENT_SOCIAL_SECURITY_4("ac9c530a-07b8-4a9d-b82b-c40ad5373eec",
            CriterionRequirementConstants.INFO_ELECTRONICALLY_TEXT),
    /**
     *
     */
    BREACH_OBLIGATIONS_ENV_1("20000170-bc67-4536-9420-01ea0f90db7a", CriterionRequirementConstants.PLEASE_DESCRIBE_TEXT),
    /**
     *
     */
    // TODO get this id
    BREACH_OBLIGATIONS_ENV_2("// TODO need id here", CriterionRequirementConstants.SELF_CLEANING_TEXT),
    /**
     *
     */
    BANKRUPTCY_1("d2e52b5d-2e34-4166-9d40-21470af8eb7c", CriterionRequirementConstants.PLEASE_DESCRIBE_TEXT),
    /**
     *
     */
    BANKRUPTCY_2("638e1e4b-863b-4768-b718-f6dde9601983", CriterionRequirementConstants.REASONS_PERFORM_CONTRACT_TEXT),
    /**
     *
     */
    BANKRUPTCY_3("44387dd3-5b75-43f6-8562-9615bb913cde", CriterionRequirementConstants.INFO_ELECTRONICALLY_TEXT),
    /**
     *
     */
    PROFESSIONAL_MISCONDUCT_1("b9dfe216-7c6d-4ca5-b1a4-793015399eb1", CriterionRequirementConstants.PLEASE_DESCRIBE_TEXT),
    /**
     *
     */
    PROFESSIONAL_MISCONDUCT_2("ff6f6761-2d7f-47f6-ade3-757639cb7a5d", CriterionRequirementConstants.SELF_CLEANING_TEXT),
    /**
     *
     */
    AGREEMENT_WITH_EO_1("139c0bc8-4341-4328-94c3-b5182e468b6e", CriterionRequirementConstants.PLEASE_DESCRIBE_TEXT),
    /**
     *
     */
    AGREEMENT_WITH_EO_2("05c4f8c9-2bed-43ef-847b-0e9bb67b66dd", CriterionRequirementConstants.SELF_CLEANING_TEXT),
    /**
     *
     */
    CONFLICT_OF_INTEREST_1("e1cc66aa-1406-4719-98e5-ab0ca08e5092", CriterionRequirementConstants.PLEASE_DESCRIBE_TEXT),
    /**
     *
     */
    CONFLICT_OF_INTEREST_2("e1f3c3a0-0b30-48ce-8a14-bb8e8ee78808", CriterionRequirementConstants.SELF_CLEANING_TEXT),
    /**
     *
     */
    CONFLICT_OF_INTEREST_3("a7631f4e-fd36-4184-93dc-a5367f843ba5",
            CriterionRequirementConstants.INFO_ELECTRONICALLY_TEXT),
    /**
     *
     */
    INVOLVEMENT_PROCUREMENT_PROCEDURE_1("d51b51c0-44d5-44a4-85df-736dec6f351e",
            CriterionRequirementConstants.PLEASE_DESCRIBE_TEXT),
    /**
     *
     */
    EARLY_TERMINATION_1("d1104636-f3c3-48f5-95eb-2db9c6d9e7c2", CriterionRequirementConstants.PLEASE_DESCRIBE_TEXT),
    /**
     *
     */
    EARLY_TERMINATION_2("80d631f0-d6cd-47cc-874c-e8c4706d5cfb", CriterionRequirementConstants.SELF_CLEANING_TEXT),
    /**
     *
     */
    GUILTY_OF_MISINTERPRETATION_1("6c44b54c-f598-4ad9-bc0f-4133d55bd7c9",
            CriterionRequirementConstants.PLEASE_DESCRIBE_TEXT),
    /**
     *
     */
    GUILTY_OF_MISINTERPRETATION_2("d9502d1f-6447-47e0-9cc0-601b7cded065",
            CriterionRequirementConstants.INFO_ELECTRONICALLY_TEXT),;

    private final String id;

    private final String description;

    CriterionRequirement(String id, String description) {
        this.id = id;
        this.description = description;
    }

    public static final List<CriterionRequirement> CRIMINAL_CONVICTIONS_REQUIREMENTS = Collections.unmodifiableList(
            Arrays.asList(CRIMINAL_CONVICTIONS_1, CRIMINAL_CONVICTIONS_2, CRIMINAL_CONVICTIONS_3));

    public static final List<CriterionRequirement> CORRUPTION_REQUIREMENTS = Collections.unmodifiableList(
            Arrays.asList(CORRUPTION_1, CORRUPTION_2, CORRUPTION_3));

    public static final List<CriterionRequirement> FRAUD_REQUIREMENTS = Collections.unmodifiableList(
            Arrays.asList(FRAUD_1, FRAUD_2, FRAUD_3));

    public static final List<CriterionRequirement> TERRORIST_OFFENCES_REQUIREMENTS = Collections.unmodifiableList(
            Arrays.asList(TERRORIST_OFFENCES_1, TERRORIST_OFFENCES_2, TERRORIST_OFFENCES_3));

    public static final List<CriterionRequirement> MONEY_LAUNDERING_REQUIREMENTS = Collections.unmodifiableList(
            Arrays.asList(MONEY_LAUNDERING_1, MONEY_LAUNDERING_2, MONEY_LAUNDERING_3));

    public static final List<CriterionRequirement> CHILD_LABOUR_REQUIREMENTS = Collections.unmodifiableList(
            Arrays.asList(CHILD_LABOUR_1, CHILD_LABOUR_2, CHILD_LABOUR_3));

    public static final List<CriterionRequirement> PAYMENT_TAXES_REQUIREMENTS = Collections.unmodifiableList(
            Arrays.asList(PAYMENT_TAXES_1, PAYMENT_TAXES_2, PAYMENT_TAXES_3, PAYMENT_TAXES_4));

    public static final List<CriterionRequirement> PAYMENT_SOCIAL_SECURITY_REQUIREMENTS = Collections.unmodifiableList(
            Arrays.asList(PAYMENT_SOCIAL_SECURITY_1, PAYMENT_SOCIAL_SECURITY_2, PAYMENT_SOCIAL_SECURITY_3,
                    PAYMENT_SOCIAL_SECURITY_4));

    public static final List<CriterionRequirement> BREACH_OBLIGATIONS_ENV_REQUIREMENTS = Collections.unmodifiableList(
            Arrays.asList(BREACH_OBLIGATIONS_ENV_1, BREACH_OBLIGATIONS_ENV_2));

    public static final List<CriterionRequirement> BANKRUPTCY_REQUIREMENTS = Collections.unmodifiableList(
            Arrays.asList(BANKRUPTCY_1, BANKRUPTCY_2, BANKRUPTCY_3));

    public static final List<CriterionRequirement> PROFESSIONAL_MISCONDUCT_REQUIREMENTS = Collections.unmodifiableList(
            Arrays.asList(PROFESSIONAL_MISCONDUCT_1, PROFESSIONAL_MISCONDUCT_2));

    public static final List<CriterionRequirement> AGREEMENT_WITH_EO_REQUIREMENTS = Collections.unmodifiableList(
            Arrays.asList(AGREEMENT_WITH_EO_1, AGREEMENT_WITH_EO_2));

    public static final List<CriterionRequirement> CONFLICT_OF_INTEREST_REQUIREMENTS = Collections.unmodifiableList(
            Arrays.asList(CONFLICT_OF_INTEREST_1, CONFLICT_OF_INTEREST_2, CONFLICT_OF_INTEREST_3));

    public static final List<CriterionRequirement> INVOLVEMENT_PROCUREMENT_PROCEDURE_REQUIREMENTS = Collections
            .unmodifiableList(Arrays.asList(INVOLVEMENT_PROCUREMENT_PROCEDURE_1));

    public static final List<CriterionRequirement> EARLY_TERMINATION_REQUIREMENTS = Collections.unmodifiableList(
            Arrays.asList(EARLY_TERMINATION_1, EARLY_TERMINATION_2));

    public static final List<CriterionRequirement> GUILTY_OF_MISINTERPRETATION_REQUIREMENTS = Collections
            .unmodifiableList(Arrays.asList(GUILTY_OF_MISINTERPRETATION_1, GUILTY_OF_MISINTERPRETATION_2));

    private static class CriterionRequirementConstants {
        private static final String GENERAL_YES_TEXT = "If yes indicate: a) Date of conviction; b) Reason; c) Who has been convicted; d) Length of the period of exclusion.";
        private static final String SELF_CLEANING_TEXT = "If yes, have you taken measures to demonstrate your reliability (\"Self-Cleaning\")?";
        private static final String INFO_ELECTRONICALLY_TEXT = "If the relevant documentation is available electronically, please indicate where to obtain the evidences: web address, issuing authority or body, precise reference of the documentation.";
        private static final String PAYMENT_YES_TEXT = "If yes, please indicate the country or Member State concerned and the amount concerned.";
        private static final String PAYMENT_BREACH_OBLIGATIONS_TEXT = "Has this breach of obligations been established through judicial or administrative decision?";
        private static final String REASONS_PERFORM_CONTRACT_TEXT = "If yes, indicate reasons for being nevertheless to perform the contract.";
        private static final String PLEASE_DESCRIBE_TEXT = "If yes, please describe them.";

    }

}
