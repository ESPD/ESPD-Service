package eu.europa.ec.grow.espd.criteria.enums;

import eu.europa.ec.grow.espd.entities.CcvResponseType;

/**
 * Created by ratoico on 12/4/15 at 1:58 PM.
 */
public enum ExpectedResponseType implements CcvResponseType {

    INDICATOR,
    DATE,
    TEXT,
    DESCRIPTION,
    BOOLEAN,
    URL,
    QUANTITY,
    AMOUNT,
    CURRENCY,
    COUNTRY,
    RATIO,
    PERCENTAGE,
    PERIOD,
    CODE;

    @Override
    public String getCode() {
        return name();
    }
}
