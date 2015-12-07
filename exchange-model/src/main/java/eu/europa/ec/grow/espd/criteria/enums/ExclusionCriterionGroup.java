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
    SELF_CLEANING_GROUP("", Collections.<CcvCriterionGroup>emptyList(),
            list(MEASURES_SELF_CLEANING, PLEASE_DESCRIBE)),
    /**
     *
     */
    INFO_ELECTRONICALLY_GROUP("", Collections.<CcvCriterionGroup>emptyList(),
            list(INFO_AVAILABLE_ELECTRONICALLY, URL, URL_CODE)),
    /**
     *
     */
    CRIMINAL_CONVICTION_MAIN_GROUP("", list(SELF_CLEANING_GROUP),
            list(YOUR_ANSWER, DATE_OF_CONVICTION, REASON, WHO_CONVICTED, LENGTH_PERIOD_EXCLUSION)),
    /**
     *
     */
    PAYMENT_BREACH_OTHER_THAN_GROUP("", Collections.<CcvCriterionGroup>emptyList(),
            list(BREACH_OF_OBLIGATIONS_OTHER_THAN, DESCRIBE_MEANS)),
    /**
     *
     */
    PAYMENT_BREACH_FINAL_BINDING_GROUP("", Collections.<CcvCriterionGroup>emptyList(),
            list(DECISION_FINAL_AND_BINDING, DATE_OF_CONVICTION, LENGTH_PERIOD_EXCLUSION)),
    /**
     *
     */
    PAYMENT_EO_FULFILLED_GROUP("", Collections.<CcvCriterionGroup>emptyList(),
            list(EO_FULFILLED_OBLIGATION, PLEASE_DESCRIBE)),
    /**
     *
     */
    PAYMENT_MAIN_GROUP("",
            list(PAYMENT_BREACH_OTHER_THAN_GROUP, PAYMENT_BREACH_FINAL_BINDING_GROUP, PAYMENT_EO_FULFILLED_GROUP),
            list(YOUR_ANSWER, COUNTRY_MS, AMOUNT)),
    /**
     *
     */
    ENVIRONMENTAL_MAIN_GROUP("", list(SELF_CLEANING_GROUP), list(YOUR_ANSWER, PLEASE_DESCRIBE)),
    /**
     *
     */
    INSOLVENCY_MAIN_GROUP("", Collections.<CcvCriterionGroup>emptyList(),
            list(YOUR_ANSWER, PLEASE_DESCRIBE, REASONS_NEVERTHELESS_CONTRACT)),
    /**
     *
     */
    MISCONDUCT_MAIN_GROUP("", list(SELF_CLEANING_GROUP), list(YOUR_ANSWER, PLEASE_DESCRIBE)),
    /**
     *
     */
    CONFLICT_OF_INTEREST_EO_PROCUREMENT_PROCEDURE_GROUP("", list(SELF_CLEANING_GROUP),
            list(YOUR_ANSWER, PLEASE_DESCRIBE)),
    /**
     *
     */
    DIRECT_INVOLVEMENT_PROCUREMENT_PROCEDURE_GROUP("", Collections.<CcvCriterionGroup>emptyList(),
            list(YOUR_ANSWER, PLEASE_DESCRIBE)),
    /**
     *
     */
    EARLY_TERMINATION_MAIN_GROUP("", list(SELF_CLEANING_GROUP), list(YOUR_ANSWER, PLEASE_DESCRIBE)),
    /**
     *
     */
    GUILTY_OF_MISINTERPRETATION_GROUP("", Collections.<CcvCriterionGroup>emptyList(),
            list(YOUR_ANSWER, PLEASE_DESCRIBE)),;

    private final String id;

    private final List<? extends CcvCriterionGroup> subgroups;

    private final List<? extends CcvCriterionRequirement> requirements;

    ExclusionCriterionGroup(String id, List<? extends CcvCriterionGroup> subgroups,
            List<? extends CcvCriterionRequirement> requirements) {
        this.id = id;
        this.subgroups = subgroups;
        this.requirements = requirements;
    }

    private static <T> List<T> list(T... values) {
        return Collections.unmodifiableList(Arrays.asList(values));
    }
}
