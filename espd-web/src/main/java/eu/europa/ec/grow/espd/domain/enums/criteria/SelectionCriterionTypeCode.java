package eu.europa.ec.grow.espd.domain.enums.criteria;

import eu.europa.ec.grow.espd.domain.ubl.CcvCriterionType;
import lombok.Getter;

/**
 * Created by vigi on 11/16/15:5:04 PM.
 */
@Getter
public enum SelectionCriterionTypeCode implements CcvCriterionType {

    /**
     * Selection criteria relating to the suitability of the economic operator
     */
    SUITABILITY("Selection criteria relating to the suitability of the economic operator"),
    /**
     * Selection criteria relating to the economical and financial standing
     */
    ECONOMIC_FINANCIAL_STANDING("Selection criteria relating to the economical and financial standing"),
    /**
     * Selection criteria relating to the technical and professional ability
     */
    TECHNICAL_PROFESSIONAL_ABILITY("Selection criteria relating to the technical and professional ability"),
    /**
     * The economic operator declares that it will satisfy all the required selection criteria indicated
     * in the relevant notice or in the procurement documents referred to in the notice
     */
    ALL_CRITERIA_SATISFIED(
            "The economic operator declares that it will satisfy all the required selection criteria indicated in the relevant notice or in the procurement documents referred to in the notice"),
    /**
     * Selection criteria relating to the quality assurance schemes and environmental management standards
     */
    QUALITY_ASSURANCE(
            "Selection criteria relating to the quality assurance schemes and environmental management standards ");

    private final String description;

    SelectionCriterionTypeCode(final String description) {
        this.description = description;
    }

    @Override
    public String getTypeName() {
        return name();
    }
}
