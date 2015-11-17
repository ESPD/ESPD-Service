package eu.europa.ec.grow.espd.criteria.enums;

import lombok.Getter;

/**
 * Created by vigi on 11/16/15:5:04 PM.
 */
@Getter
public enum SelectionCriterionTypeCode {

    /**
     *
     */
    SUITABILITY("Selection criteria relating to the suitability of the economic operator"),
    /**
     *
     */
    ECONOMIC_FINANCIAL_STANDING("Selection criteria relating to the economical and financial standing"),
    /**
     *
     */
    TECHNICAL_PROFESSIONAL_ABILITY("Selection criteria relating to the technical and professional ability"),
    /**
     *
     */
    QUALITY_ASSURANCE(
            "Selection criteria relating to the quality assurance schemes and environmental management standards"),
    /**
     *
     */
    ALL_CRITERIA_SATISFIED(
            "The economic operator declares that it will satisfy all the required selection criteria indicated in the relevant notice or in the procurement documents referred to in the notice"),
    /**
     *
     */
    REDUCTION_CANDIDATES("Selection criteria relating to the reduction of the number of qualified candidates");

    private final String description;

    SelectionCriterionTypeCode(final String description) {
        this.description = description;
    }
}
