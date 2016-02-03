package eu.europa.ec.grow.espd.criteria.enums;

import eu.europa.ec.grow.espd.entities.CcvResponseType;

/**
 * Created by ratoico on 12/4/15 at 1:58 PM.
 */
public enum ExpectedResponseType implements CcvResponseType {

    INDICATOR,
    DATE,
    DESCRIPTION,
    EVIDENCE_URL,
    QUANTITY,
    QUANTITY_YEAR,
    QUANTITY_INTEGER,
    AMOUNT,
    CODE_COUNTRY,
    PERCENTAGE,
    PERIOD,
    CODE;

    @Override
    public String getCode() {
        return name();
    }
}
