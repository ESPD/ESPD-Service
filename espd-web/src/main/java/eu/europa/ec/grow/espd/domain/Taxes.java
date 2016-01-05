package eu.europa.ec.grow.espd.domain;

import eu.europa.ec.grow.espd.constants.enums.Country;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class Taxes extends ExclusionCriterion {

    private Country country;
    private Double amount;
    private String currency;
    private boolean breachEstablishedOtherThanJudicialDecision;
    private String meansDescription;
    private boolean decisionFinalAndBinding;
    private Date dateOfConviction;
    private String periodLength;
    private boolean eoFulfilledObligations;
    private String obligationsDescription;

    public static final Taxes buildWithExists(Boolean exists) {
        Taxes taxes = new Taxes();
        taxes.setExists(exists);
        return taxes;
    }

    @Override
    public String getReason() {
        throw new UnsupportedOperationException("Payment criterion does not have a date of reason.");
    }

}