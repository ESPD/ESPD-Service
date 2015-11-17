package eu.europa.ec.grow.espd.criteria.enums;

import lombok.Getter;

/**
 * Created by vigi on 11/16/15:4:59 PM.
 */
@Getter
public enum ExclusionCriterionTypeCode {

    /**
     *
     */
    CRIMINAL_CONVICTIONS("Grounds for exclusion relating to criminal convictions"),
    /**
     *
     */
    PAYMENT_OF_TAXES("Grounds for exclusion relating to the payment of taxes contributions"),
    /**
     *
     */
    PAYMENT_OF_SOCIAL_SECURITY("Grounds for exclusion relating to the payment of social security contributions"),
    /**
     *
     */
    ENVIRONMENTAL("Grounds for exclusion relating to the breaching of environmental, social and labour laws"),
    /**
     *
     */
    INSOLVENCY("Grounds for exclusion for bankruptcy of insolvency"),
    /**
     *
     */
    MISCONDUCT("Grounds for exclusion for misconduct"),
    /**
     *
     */
    CONFLICT_OF_INTEREST("Grounds for exclusion relating to possible conflicts of interests"),
    /**
     *
     */
    OTHER("Other exclusion grounds that may be foreseen in the national legislation of the contracting authority's or contracting entity's Member State");

    private final String description;

    ExclusionCriterionTypeCode(final String description) {
        this.description = description;
    }
}
