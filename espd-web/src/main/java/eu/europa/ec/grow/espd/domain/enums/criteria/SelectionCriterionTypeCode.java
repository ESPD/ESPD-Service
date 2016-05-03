/*
 *
 * Copyright 2016 EUROPEAN COMMISSION
 *
 * Licensed under the EUPL, Version 1.1 or – as soon they
 * will be approved by the European Commission - subsequent
 * versions of the EUPL (the "Licence");
 *
 * You may not use this work except in compliance with the Licence.
 *
 * You may obtain a copy of the Licence at:
 *
 * https://joinup.ec.europa.eu/community/eupl/og_page/eupl
 *
 * Unless required by applicable law or agreed to in
 * writing, software distributed under the Licence is
 * distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied.
 * See the Licence for the specific language governing
 * permissions and limitations under the Licence.
 *
 */

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
