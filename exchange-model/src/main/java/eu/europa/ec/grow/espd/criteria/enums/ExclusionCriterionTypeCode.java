package eu.europa.ec.grow.espd.criteria.enums;

import eu.europa.ec.grow.espd.entities.CcvCriterionType;
import lombok.Getter;

/**
 * Created by vigi on 11/16/15:4:59 PM.
 */
@Getter
public enum ExclusionCriterionTypeCode implements CcvCriterionType {

    /**
     * Grounds for exclusion relating to criminal convictions
     */
    CRIMINAL_CONVICTIONS("Grounds for exclusion relating to criminal convictions"),
    /**
     * Grounds for exclusion relating to the payment of taxes contributions
     */
    PAYMENT_OF_TAXES("Grounds for exclusion relating to the payment of taxes contributions"),
    /**
     * Grounds for exclusion relating to the payment of social security contributions
     */
    PAYMENT_OF_SOCIAL_SECURITY("Grounds for exclusion relating to the payment of social security contributions"),
    /**
     * Grounds for exclusion relating to the breaching of environmental, social and labour laws
     */
    ENVIRONMENTAL("Grounds for exclusion relating to the breaching of environmental, social and labour laws"),
    /**
     * Grounds for exclusion for bankruptcy of insolvency
     */
    BANKRUPTCY_INSOLVENCY("Grounds for exclusion for bankruptcy or insolvency"),
    /**
     * Grounds for exclusion for misconduct
     */
    MISCONDUCT("Grounds for exclusion for misconduct"),
    /**
     * Grounds for exclusion relating to possible conflicts of interests
     */
    CONFLICT_OF_INTEREST("Grounds for exclusion relating to possible conflicts of interests"),
    /**
     * Other exclusion grounds that may be foreseen in the national legislation of the contracting authority's
     * or contracting entity's Member State
     */
    OTHER("Other exclusion grounds that may be foreseen in the national legislation of the contracting authority's or contracting entity's Member State");

    private final String description;

    ExclusionCriterionTypeCode(final String description) {
        this.description = description;
    }

    @Override
    public String getTypeName() {
        return name();
    }
}
