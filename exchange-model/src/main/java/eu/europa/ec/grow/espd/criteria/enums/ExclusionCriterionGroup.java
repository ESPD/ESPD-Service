package eu.europa.ec.grow.espd.criteria.enums;

import eu.europa.ec.grow.espd.entities.CcvCriterionGroup;
import eu.europa.ec.grow.espd.entities.CcvCriterionRequirement;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static eu.europa.ec.grow.espd.criteria.enums.ExclusionCriterionRequirement.*;

/**
 * Created by ratoico on 12/7/15 at 10:33 AM.
 */
@Getter
public enum ExclusionCriterionGroup implements CcvCriterionGroup {

    /**
     *
     */
    SELF_CLEANING_GROUP("41dd2e9b-1bfd-44c7-93ee-56bd74a4334b", Collections.<CcvCriterionGroup>emptyList(),
            list(MEASURES_SELF_CLEANING, PLEASE_DESCRIBE)),
    /**
     *
     */
    INFO_ELECTRONICALLY_GROUP("7458d42a-e581-4640-9283-34ceb3ad4345", Collections.<CcvCriterionGroup>emptyList(),
            list(INFO_AVAILABLE_ELECTRONICALLY, URL, URL_CODE)),
    /**
     *
     */
    PARTICIPATION_CRIMINAL_ORGANISATION_GROUP("7c637c0c-7703-4389-ba52-02997a055bd7", list(SELF_CLEANING_GROUP),
            list(YOUR_ANSWER, DATE_OF_CONVICTION, REASON, WHO_CONVICTED, LENGTH_PERIOD_EXCLUSION)),
    /**
     *
     */
    CORRUPTION_GROUP("fc0c436c-f10c-401b-9ac2-25247ce886c0", list(SELF_CLEANING_GROUP),
            list(YOUR_ANSWER, DATE_OF_CONVICTION, REASON, WHO_CONVICTED, LENGTH_PERIOD_EXCLUSION)),
    /**
     *
     */
    FRAUD_GROUP("94ff6812-b9a6-40c7-9676-d9fb83b51d51", list(SELF_CLEANING_GROUP),
            list(YOUR_ANSWER, DATE_OF_CONVICTION, REASON, WHO_CONVICTED, LENGTH_PERIOD_EXCLUSION)),
    /**
     *
     */
    TERRORIST_OFFENCES_GROUP("2380efc6-2c86-4aa6-8645-c56cb87ad5a1", list(SELF_CLEANING_GROUP),
            list(YOUR_ANSWER, DATE_OF_CONVICTION, REASON, WHO_CONVICTED, LENGTH_PERIOD_EXCLUSION)),
    /**
     *
     */
    MONEY_LAUNDERING_GROUP("7a866000-53f4-47a9-a4b7-f9f4a81392bf", list(SELF_CLEANING_GROUP),
            list(YOUR_ANSWER, DATE_OF_CONVICTION, REASON, WHO_CONVICTED, LENGTH_PERIOD_EXCLUSION)),
    /**
     *
     */
    CHILD_LABOUR_GROUP("602c69d2-b9db-4edf-bd64-412f476d7575", list(SELF_CLEANING_GROUP),
            list(YOUR_ANSWER, DATE_OF_CONVICTION, REASON, WHO_CONVICTED, LENGTH_PERIOD_EXCLUSION)),
    /**
     *
     */
    PAYMENT_BREACH_OTHER_THAN_GROUP("7c2aec9f-4876-4c33-89e6-2ab6d6cf5d02", Collections.<CcvCriterionGroup>emptyList(),
            list(BREACH_OF_OBLIGATIONS_OTHER_THAN, DESCRIBE_MEANS)),
    /**
     *
     */
    PAYMENT_BREACH_FINAL_BINDING_GROUP("c882afa4-6971-4b00-8970-0c283eb122cc",
            Collections.<CcvCriterionGroup>emptyList(),
            list(DECISION_FINAL_AND_BINDING, DATE_OF_CONVICTION, LENGTH_PERIOD_EXCLUSION)),
    /**
     *
     */
    PAYMENT_EO_FULFILLED_GROUP("fc57e473-d63e-4a04-b589-dcf81cab8052", Collections.<CcvCriterionGroup>emptyList(),
            list(EO_FULFILLED_OBLIGATION, PLEASE_DESCRIBE)),
    /**
     *
     */
    PAYMENT_OF_TAXES_GROUP("098fd3cc-466e-4233-af1a-affe09471bce",
            list(PAYMENT_BREACH_OTHER_THAN_GROUP, PAYMENT_BREACH_FINAL_BINDING_GROUP, PAYMENT_EO_FULFILLED_GROUP),
            list(YOUR_ANSWER, COUNTRY_MS, AMOUNT)),
    /**
     *
     */
    PAYMENT_OF_SOCIAL_SECURITY_GROUP("e0b0dedc-19d7-4d12-9542-1ca656b6f4f8",
            list(PAYMENT_BREACH_OTHER_THAN_GROUP, PAYMENT_BREACH_FINAL_BINDING_GROUP, PAYMENT_EO_FULFILLED_GROUP),
            list(YOUR_ANSWER, COUNTRY_MS, AMOUNT)),
    /**
     *
     */
    BREACHING_OF_OBLIGATIONS_ENVIRONMENTAL_GROUP("976b5acb-c00f-46ca-8f83-5ce6abfdfe43", list(SELF_CLEANING_GROUP),
            list(YOUR_ANSWER, PLEASE_DESCRIBE)),
    /**
     *
     */
    BANKRUPTCY_GROUP("d91c11a1-f19e-4b83-8ade-c4be2bf00555", Collections.<CcvCriterionGroup>emptyList(),
            list(YOUR_ANSWER, PLEASE_DESCRIBE, REASONS_NEVERTHELESS_CONTRACT)),
    /**
     *
     */
    INSOLVENCY_GROUP("0ef4758c-7edd-4c49-a572-8a68276e205f", Collections.<CcvCriterionGroup>emptyList(),
            list(YOUR_ANSWER, PLEASE_DESCRIBE, REASONS_NEVERTHELESS_CONTRACT)),
    /**
     *
     */
    ARRANGEMENT_WITH_CREDITORS_GROUP("74594d42-a656-43e7-b79c-cb629f17acdc", Collections.<CcvCriterionGroup>emptyList(),
            list(YOUR_ANSWER, PLEASE_DESCRIBE, REASONS_NEVERTHELESS_CONTRACT)),
    /**
     *
     */
    ANALOGOUS_SITUATION_GROUP("8dea9e4d-0e51-4851-8942-a26a83c19e02", Collections.<CcvCriterionGroup>emptyList(),
            list(YOUR_ANSWER, PLEASE_DESCRIBE, REASONS_NEVERTHELESS_CONTRACT)),
    /**
     *
     */
    ASSETS_ADMINISTERED_BY_LIQUIDATOR_GROUP("7d1b880c-e2b5-433c-b721-f7afbfa728f9",
            Collections.<CcvCriterionGroup>emptyList(),
            list(YOUR_ANSWER, PLEASE_DESCRIBE, REASONS_NEVERTHELESS_CONTRACT)),
    /**
     *
     */
    BUSINESS_ACTIVITIES_SUSPENDED_GROUP("d07214ba-53ff-4bb3-854d-e723ff420dac",
            Collections.<CcvCriterionGroup>emptyList(),
            list(YOUR_ANSWER, PLEASE_DESCRIBE, REASONS_NEVERTHELESS_CONTRACT)),
    /**
     *
     */
    GUILTY_OF_PROFESSIONAL_MISCONDUCT_GROUP("67362ec7-cec3-4cb8-a38e-5d7a2a31e6d8", list(SELF_CLEANING_GROUP),
            list(YOUR_ANSWER, PLEASE_DESCRIBE)),
    /**
     *
     */
    CONFLICT_OF_INTEREST_EO_PROCUREMENT_PROCEDURE_GROUP("30450436-f559-4dfa-98ba-f0842ed9d2a0",
            list(SELF_CLEANING_GROUP), list(YOUR_ANSWER, PLEASE_DESCRIBE)),
    /**
     *
     */
    DIRECT_INVOLVEMENT_PROCUREMENT_PROCEDURE_GROUP("77ae3f29-7c5f-4afa-af97-24afec48c5bf",
            Collections.<CcvCriterionGroup>emptyList(), list(YOUR_ANSWER, PLEASE_DESCRIBE)),
    /**
     *
     */
    EARLY_TERMINATION_GROUP("72f29e01-d0be-4e33-90f3-954c26fd0899", list(SELF_CLEANING_GROUP),
            list(YOUR_ANSWER, PLEASE_DESCRIBE)),
    /**
     *
     */
    GUILTY_OF_MISINTERPRETATION_GROUP("ddea7091-f238-4fcb-9d93-eacb13190243",
            Collections.<CcvCriterionGroup>emptyList(), list(YOUR_ANSWER, PLEASE_DESCRIBE));

    private final String id;

    private final List<? extends CcvCriterionGroup> subgroups;

    private final List<? extends CcvCriterionRequirement> requirements;

    ExclusionCriterionGroup(String id, List<? extends CcvCriterionGroup> subgroups,
            List<? extends CcvCriterionRequirement> requirements) {
        this.id = id;
        this.subgroups = subgroups;
        this.requirements = requirements;
    }

    @SafeVarargs
    private static <T> List<T> list(T... values) {
        return Collections.unmodifiableList(Arrays.asList(values));
    }
}
