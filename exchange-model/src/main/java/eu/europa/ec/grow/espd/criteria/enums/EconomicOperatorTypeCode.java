package eu.europa.ec.grow.espd.criteria.enums;

import eu.europa.ec.grow.espd.entities.CcvCriterionType;
import lombok.Getter;

/**
 * Created by ratoico on 1/15/16 at 3:11 PM.
 */
@Getter
public enum EconomicOperatorTypeCode implements CcvCriterionType {

    DATA_ON_ECONOMIC_OPERATOR("Date on economic operator");

    private final String description;

    EconomicOperatorTypeCode(final String description) {
        this.description = description;
    }

    @Override
    public String getTypeName() {
        return name();
    }
}
