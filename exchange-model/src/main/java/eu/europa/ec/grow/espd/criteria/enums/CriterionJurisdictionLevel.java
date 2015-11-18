package eu.europa.ec.grow.espd.criteria.enums;

import lombok.Getter;

/**
 * Created by vigi on 11/18/15:11:23 AM.
 */
@Getter
public enum CriterionJurisdictionLevel {


    /**
     *
     */
    EU_REGULATION("EU_REGULATION", "European Regulation"),
    /**
     *
     */
    EU_DIRECTIVE("EU_DIRECTIVE", "European Directive"),
    /**
     *
     */
    EU_DECISION("EU_DECISION", "European Decision"),
    /**
     *
     */
    NATIONAL_LEGISLATION("NATIONAL_LEGISLATION", "National Legislation");

    public static final String LIST_ID = "CriterionJurisdictionLevelCode";

    private final String code;

    private final String value;

    CriterionJurisdictionLevel(String code, String value) {
        this.code = code;
        this.value = value;
    }
}
