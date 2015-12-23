package eu.europa.ec.grow.espd.domain;

import eu.europa.ec.grow.espd.constants.enums.Country;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class PurelyNationalGrounds extends ExclusionCriterion {

    public static final PurelyNationalGrounds buildWithExists(Boolean exists) {
        PurelyNationalGrounds criminalConvictions = new PurelyNationalGrounds();
        criminalConvictions.setExists(exists);
        return criminalConvictions;
    }

    @Override
    public Date getDateOfConviction() {
        throw new UnsupportedOperationException("National grounds criterion does not have a date of conviction.");
    }

    @Override
    public String getPeriodLength() {
        throw new UnsupportedOperationException("National grounds criterion does not have a period length.");
    }

    @Override
    public Country getCountry() {
        throw new UnsupportedOperationException("National grounds criterion does not have a country.");
    }

    @Override
    public Integer getAmount() {
        throw new UnsupportedOperationException("National grounds criterion does not have an amount.");
    }

    @Override
    public String getCurrency() {
        throw new UnsupportedOperationException("National grounds criterion does not have a currency.");
    }

    @Override
    public String getReason() {
        throw new UnsupportedOperationException("National grounds criterion does not have a reason.");
    }

}
