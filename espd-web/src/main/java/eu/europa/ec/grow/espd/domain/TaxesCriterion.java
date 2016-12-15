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

package eu.europa.ec.grow.espd.domain;

import eu.europa.ec.grow.espd.domain.enums.other.Country;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class TaxesCriterion extends ExclusionCriterion {

    private Country country;
    private BigDecimal amount;
    private String currency;
    private Boolean breachEstablishedOtherThanJudicialDecision = Boolean.FALSE;
    private String meansDescription;
    private Boolean decisionFinalAndBinding = Boolean.FALSE;
    private Date dateOfConviction;
    private String periodLength;
    private Boolean eoFulfilledObligations = Boolean.FALSE;
    private String obligationsDescription;

    public static TaxesCriterion buildWithExists(Boolean exists) {
        TaxesCriterion taxes = new TaxesCriterion();
        taxes.setExists(exists);
        return taxes;
    }

}