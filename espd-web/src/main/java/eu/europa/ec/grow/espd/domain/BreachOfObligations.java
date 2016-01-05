package eu.europa.ec.grow.espd.domain;

import eu.europa.ec.grow.espd.constants.enums.Country;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Deprecated
public class BreachOfObligations extends ExclusionCriterion {

    private Boolean isFinal;
    private Date dateOfConviction;
    private String periodLength;
    private String otherMeans;

    public static final BreachOfObligations buildWithExists(Boolean exists) {
        BreachOfObligations breach = new BreachOfObligations();
        breach.setExists(exists);
        return breach;
    }

    @Override
    public Country getCountry() {
        throw new UnsupportedOperationException("Breach of obligations criterion does not have a country.");
    }

    @Override
    public Double getAmount() {
        throw new UnsupportedOperationException("Breach of obligations criterion does not have an amount.");
    }

    @Override
    public String getCurrency() {
        throw new UnsupportedOperationException("Breach of obligations criterion does not have a currency.");
    }

    @Override
    public String getReason() {
        throw new UnsupportedOperationException("Breach of obligations criterion does not have a reason.");
    }
}
