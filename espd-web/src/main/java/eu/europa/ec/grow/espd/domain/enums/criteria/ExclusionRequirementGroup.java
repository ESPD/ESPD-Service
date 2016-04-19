package eu.europa.ec.grow.espd.domain.enums.criteria;

import eu.europa.ec.grow.espd.domain.ubl.CcvRequirementGroup;
import eu.europa.ec.grow.espd.domain.ubl.CcvCriterionRequirement;
import lombok.Getter;

import java.util.Collections;
import java.util.List;

/**
 * Created by ratoico on 12/7/15 at 10:33 AM.
 */
@Getter
public enum ExclusionRequirementGroup implements CcvRequirementGroup {

    /**
     *
     */
    SELF_CLEANING_GROUP("41dd2e9b-1bfd-44c7-93ee-56bd74a4334b", Collections.<CcvRequirementGroup>emptyList(),
            ListUtil.list(ExclusionCriterionRequirement.MEASURES_SELF_CLEANING,
                    ExclusionCriterionRequirement.PLEASE_DESCRIBE_SELF_CLEANING)),
    /**
     *
     */
    INFO_ELECTRONICALLY_GROUP("7458d42a-e581-4640-9283-34ceb3ad4345", Collections.<CcvRequirementGroup>emptyList(),
            ListUtil.list(ExclusionCriterionRequirement.INFO_AVAILABLE_ELECTRONICALLY,
                    ExclusionCriterionRequirement.URL, ExclusionCriterionRequirement.URL_CODE)),
    /**
     *
     */
    PARTICIPATION_CRIMINAL_ORGANISATION_GROUP("7c637c0c-7703-4389-ba52-02997a055bd7", ListUtil.list(SELF_CLEANING_GROUP),
            ListUtil.list(ExclusionCriterionRequirement.YOUR_ANSWER, ExclusionCriterionRequirement.DATE_OF_CONVICTION,
                    ExclusionCriterionRequirement.REASON, ExclusionCriterionRequirement.WHO_CONVICTED,
                    ExclusionCriterionRequirement.LENGTH_PERIOD_EXCLUSION)),
    /**
     *
     */
    CORRUPTION_GROUP("fc0c436c-f10c-401b-9ac2-25247ce886c0", ListUtil.list(SELF_CLEANING_GROUP),
            ListUtil.list(ExclusionCriterionRequirement.YOUR_ANSWER, ExclusionCriterionRequirement.DATE_OF_CONVICTION,
                    ExclusionCriterionRequirement.REASON, ExclusionCriterionRequirement.WHO_CONVICTED,
                    ExclusionCriterionRequirement.LENGTH_PERIOD_EXCLUSION)),
    /**
     *
     */
    FRAUD_GROUP("94ff6812-b9a6-40c7-9676-d9fb83b51d51", ListUtil.list(SELF_CLEANING_GROUP),
            ListUtil.list(ExclusionCriterionRequirement.YOUR_ANSWER, ExclusionCriterionRequirement.DATE_OF_CONVICTION,
                    ExclusionCriterionRequirement.REASON, ExclusionCriterionRequirement.WHO_CONVICTED,
                    ExclusionCriterionRequirement.LENGTH_PERIOD_EXCLUSION)),
    /**
     *
     */
    TERRORIST_OFFENCES_GROUP("2380efc6-2c86-4aa6-8645-c56cb87ad5a1", ListUtil.list(SELF_CLEANING_GROUP),
            ListUtil.list(ExclusionCriterionRequirement.YOUR_ANSWER, ExclusionCriterionRequirement.DATE_OF_CONVICTION,
                    ExclusionCriterionRequirement.REASON, ExclusionCriterionRequirement.WHO_CONVICTED,
                    ExclusionCriterionRequirement.LENGTH_PERIOD_EXCLUSION)),
    /**
     *
     */
    MONEY_LAUNDERING_GROUP("7a866000-53f4-47a9-a4b7-f9f4a81392bf", ListUtil.list(SELF_CLEANING_GROUP),
            ListUtil.list(ExclusionCriterionRequirement.YOUR_ANSWER, ExclusionCriterionRequirement.DATE_OF_CONVICTION,
                    ExclusionCriterionRequirement.REASON, ExclusionCriterionRequirement.WHO_CONVICTED,
                    ExclusionCriterionRequirement.LENGTH_PERIOD_EXCLUSION)),
    /**
     *
     */
    CHILD_LABOUR_GROUP("602c69d2-b9db-4edf-bd64-412f476d7575", ListUtil.list(SELF_CLEANING_GROUP),
            ListUtil.list(ExclusionCriterionRequirement.YOUR_ANSWER, ExclusionCriterionRequirement.DATE_OF_CONVICTION,
                    ExclusionCriterionRequirement.REASON, ExclusionCriterionRequirement.WHO_CONVICTED,
                    ExclusionCriterionRequirement.LENGTH_PERIOD_EXCLUSION)),
    /**
     *
     */
    PAYMENT_BREACH_OTHER_THAN_GROUP("7c2aec9f-4876-4c33-89e6-2ab6d6cf5d02", Collections.<CcvRequirementGroup>emptyList(),
            ListUtil.list(ExclusionCriterionRequirement.BREACH_OF_OBLIGATIONS_OTHER_THAN,
                    ExclusionCriterionRequirement.DESCRIBE_MEANS)),
    /**
     *
     */
    PAYMENT_BREACH_FINAL_BINDING_GROUP("c882afa4-6971-4b00-8970-0c283eb122cc",
            Collections.<CcvRequirementGroup>emptyList(),
            ListUtil.list(ExclusionCriterionRequirement.DECISION_FINAL_AND_BINDING,
                    ExclusionCriterionRequirement.DATE_OF_CONVICTION,
                    ExclusionCriterionRequirement.LENGTH_PERIOD_EXCLUSION)),
    /**
     *
     */
    PAYMENT_EO_FULFILLED_GROUP("fc57e473-d63e-4a04-b589-dcf81cab8052", Collections.<CcvRequirementGroup>emptyList(),
            ListUtil.list(ExclusionCriterionRequirement.EO_FULFILLED_OBLIGATION,
                    ExclusionCriterionRequirement.DESCRIBE_OBLIGATIONS)),
    /**
     *
     */
    PAYMENT_OF_TAXES_GROUP("098fd3cc-466e-4233-af1a-affe09471bce",
            ListUtil.list(PAYMENT_BREACH_OTHER_THAN_GROUP, PAYMENT_BREACH_FINAL_BINDING_GROUP,
                    PAYMENT_EO_FULFILLED_GROUP),
            ListUtil.list(ExclusionCriterionRequirement.YOUR_ANSWER, ExclusionCriterionRequirement.COUNTRY_MS,
                    ExclusionCriterionRequirement.AMOUNT)),
    /**
     *
     */
    PAYMENT_OF_SOCIAL_SECURITY_GROUP("e0b0dedc-19d7-4d12-9542-1ca656b6f4f8",
            ListUtil.list(PAYMENT_BREACH_OTHER_THAN_GROUP, PAYMENT_BREACH_FINAL_BINDING_GROUP,
                    PAYMENT_EO_FULFILLED_GROUP),
            ListUtil.list(ExclusionCriterionRequirement.YOUR_ANSWER, ExclusionCriterionRequirement.COUNTRY_MS,
                    ExclusionCriterionRequirement.AMOUNT)),
    /**
     *
     */
    BREACHING_OF_OBLIGATIONS_ENVIRONMENTAL_GROUP("976b5acb-c00f-46ca-8f83-5ce6abfdfe43", ListUtil
            .list(SELF_CLEANING_GROUP),
            ListUtil.list(ExclusionCriterionRequirement.YOUR_ANSWER, ExclusionCriterionRequirement.PLEASE_DESCRIBE)),
    /**
     *
     */
    BREACHING_OF_OBLIGATIONS_SOCIAL_GROUP("78e741f5-d41e-48e8-9052-70b8c7e5c8ab", ListUtil.list(SELF_CLEANING_GROUP),
            ListUtil.list(ExclusionCriterionRequirement.YOUR_ANSWER, ExclusionCriterionRequirement.PLEASE_DESCRIBE)),
    /**
     *
     */
    BREACHING_OF_OBLIGATIONS_LABOUR_GROUP("c5bc8338-6f20-4f53-a3b1-1e6be0480759", ListUtil.list(SELF_CLEANING_GROUP),
            ListUtil.list(ExclusionCriterionRequirement.YOUR_ANSWER, ExclusionCriterionRequirement.PLEASE_DESCRIBE)),
    /**
     *
     */
    BANKRUPTCY_GROUP("d91c11a1-f19e-4b83-8ade-c4be2bf00555", Collections.<CcvRequirementGroup>emptyList(),
            ListUtil.list(ExclusionCriterionRequirement.YOUR_ANSWER, ExclusionCriterionRequirement.PLEASE_DESCRIBE,
                    ExclusionCriterionRequirement.REASONS_NEVERTHELESS_CONTRACT)),
    /**
     *
     */
    INSOLVENCY_GROUP("0ef4758c-7edd-4c49-a572-8a68276e205f", Collections.<CcvRequirementGroup>emptyList(),
            ListUtil.list(ExclusionCriterionRequirement.YOUR_ANSWER, ExclusionCriterionRequirement.PLEASE_DESCRIBE,
                    ExclusionCriterionRequirement.REASONS_NEVERTHELESS_CONTRACT)),
    /**
     *
     */
    ARRANGEMENT_WITH_CREDITORS_GROUP("74594d42-a656-43e7-b79c-cb629f17acdc", Collections.<CcvRequirementGroup>emptyList(),
            ListUtil.list(ExclusionCriterionRequirement.YOUR_ANSWER, ExclusionCriterionRequirement.PLEASE_DESCRIBE,
                    ExclusionCriterionRequirement.REASONS_NEVERTHELESS_CONTRACT)),
    /**
     *
     */
    ANALOGOUS_SITUATION_GROUP("8dea9e4d-0e51-4851-8942-a26a83c19e02", Collections.<CcvRequirementGroup>emptyList(),
            ListUtil.list(ExclusionCriterionRequirement.YOUR_ANSWER, ExclusionCriterionRequirement.PLEASE_DESCRIBE,
                    ExclusionCriterionRequirement.REASONS_NEVERTHELESS_CONTRACT)),
    /**
     *
     */
    ASSETS_ADMINISTERED_BY_LIQUIDATOR_GROUP("7d1b880c-e2b5-433c-b721-f7afbfa728f9",
            Collections.<CcvRequirementGroup>emptyList(),
            ListUtil.list(ExclusionCriterionRequirement.YOUR_ANSWER, ExclusionCriterionRequirement.PLEASE_DESCRIBE,
                    ExclusionCriterionRequirement.REASONS_NEVERTHELESS_CONTRACT)),
    /**
     *
     */
    BUSINESS_ACTIVITIES_SUSPENDED_GROUP("d07214ba-53ff-4bb3-854d-e723ff420dac",
            Collections.<CcvRequirementGroup>emptyList(),
            ListUtil.list(ExclusionCriterionRequirement.YOUR_ANSWER, ExclusionCriterionRequirement.PLEASE_DESCRIBE,
                    ExclusionCriterionRequirement.REASONS_NEVERTHELESS_CONTRACT)),
    /**
     *
     */
    GUILTY_OF_PROFESSIONAL_MISCONDUCT_GROUP("67362ec7-cec3-4cb8-a38e-5d7a2a31e6d8", ListUtil.list(SELF_CLEANING_GROUP),
            ListUtil.list(ExclusionCriterionRequirement.YOUR_ANSWER, ExclusionCriterionRequirement.PLEASE_DESCRIBE)),
    /**
     *
     */
    AGREEMENTS_WITH_OTHER_EO_GROUP("dd635133-2952-4cbf-a582-d6e61efd7c28", ListUtil.list(SELF_CLEANING_GROUP),
            ListUtil.list(ExclusionCriterionRequirement.YOUR_ANSWER, ExclusionCriterionRequirement.PLEASE_DESCRIBE)),
    /**
     *
     */
    CONFLICT_OF_INTEREST_EO_PROCUREMENT_PROCEDURE_GROUP("30450436-f559-4dfa-98ba-f0842ed9d2a0",
            ListUtil.list(SELF_CLEANING_GROUP), ListUtil
            .list(ExclusionCriterionRequirement.YOUR_ANSWER, ExclusionCriterionRequirement.PLEASE_DESCRIBE)),
    /**
     *
     */
    DIRECT_INVOLVEMENT_PROCUREMENT_PROCEDURE_GROUP("77ae3f29-7c5f-4afa-af97-24afec48c5bf",
            Collections.<CcvRequirementGroup>emptyList(), ListUtil
            .list(ExclusionCriterionRequirement.YOUR_ANSWER, ExclusionCriterionRequirement.PLEASE_DESCRIBE)),
    /**
     *
     */
    EARLY_TERMINATION_GROUP("72f29e01-d0be-4e33-90f3-954c26fd0899", ListUtil.list(SELF_CLEANING_GROUP),
            ListUtil.list(ExclusionCriterionRequirement.YOUR_ANSWER, ExclusionCriterionRequirement.PLEASE_DESCRIBE)),
    /**
     *
     */
    GUILTY_OF_MISINTERPRETATION_GROUP("ddea7091-f238-4fcb-9d93-eacb13190243",
            Collections.<CcvRequirementGroup>emptyList(), ListUtil
            .list(ExclusionCriterionRequirement.YOUR_ANSWER)),
    /**
     *
     */
    NATIONAL_EXCLUSION_GROUNDS_GROUP("cff842a7-c95d-4445-8c89-84fcd53aa181",
            Collections.<CcvRequirementGroup>emptyList(), ListUtil
            .list(ExclusionCriterionRequirement.YOUR_ANSWER, ExclusionCriterionRequirement.PLEASE_DESCRIBE));

    private final String id;

    private final List<? extends CcvRequirementGroup> subgroups;

    private final List<? extends CcvCriterionRequirement> requirements;

    ExclusionRequirementGroup(String id, List<? extends CcvRequirementGroup> subgroups,
            List<? extends CcvCriterionRequirement> requirements) {
        this.id = id;
        this.subgroups = subgroups;
        this.requirements = requirements;
    }

}
