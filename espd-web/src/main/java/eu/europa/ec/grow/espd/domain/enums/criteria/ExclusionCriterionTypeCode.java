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

import com.fasterxml.jackson.annotation.JsonFormat;
import eu.europa.ec.grow.espd.domain.ubl.CcvCriterionType;
import lombok.Getter;

/**
 * Created by vigi on 11/16/15:4:59 PM.
 */
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ExclusionCriterionTypeCode implements CcvCriterionType {

    /**
     * Grounds for exclusion for bankruptcy of insolvency
     */
    BANKRUPTCY_INSOLVENCY("Grounds for exclusion for bankruptcy or insolvency"),
    /**
     * Grounds for exclusion relating to possible conflicts of interests
     */
    CONFLICT_OF_INTEREST("Grounds for exclusion relating to possible conflicts of interests"),
    /**
     * Grounds for exclusion relating to criminal convictions
     */
    CRIMINAL_CONVICTIONS("Grounds for exclusion relating to criminal convictions"),
    /**
     * Grounds for exclusion for misconduct
     */
    DISTORTING_MARKET("Grounds for exclusion related to market distortion"),
    /**
     * Grounds for exclusion relating to the breaching of environmental laws
     */
    ENVIRONMENTAL_LAW("Grounds for exclusion relating to the breaching of environmental laws"),
    /**
     * Grounds for exclusion relating to the breaching of labour laws
     */
    LABOUR_LAW("Grounds for exclusion relating to the breaching of labour laws"),
    /**
     * Grounds for exclusion for misconduct
     */
    MISCONDUCT("Grounds for exclusion for misconduct"),
    /**
     * Grounds for exclusion relating to the payment of social security contributions
     */
    PAYMENT_OF_SOCIAL_SECURITY("Grounds for exclusion relating to the payment of social security contributions"),
    /**
     * Grounds for exclusion relating to the payment of taxes contributions
     */
    PAYMENT_OF_TAXES("Grounds for exclusion relating to the payment of taxes contributions"),
    /**
     * Grounds for exclusion relating to the breaching of social laws
     */
    SOCIAL_LAW("Grounds for exclusion relating to the breaching of social laws"),
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
    public String getEspdType() {
        return name();
    }

    @Override
    public String getCode() {
        return null;
    }

}
