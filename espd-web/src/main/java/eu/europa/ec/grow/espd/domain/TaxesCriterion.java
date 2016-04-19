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
    private Boolean breachEstablishedOtherThanJudicialDecision;
    private String meansDescription;
    private Boolean decisionFinalAndBinding;
    private Date dateOfConviction;
    private String periodLength;
    private Boolean eoFulfilledObligations;
    private String obligationsDescription;

    public static TaxesCriterion buildWithExists(Boolean exists) {
        TaxesCriterion taxes = new TaxesCriterion();
        taxes.setExists(exists);
        return taxes;
    }

}