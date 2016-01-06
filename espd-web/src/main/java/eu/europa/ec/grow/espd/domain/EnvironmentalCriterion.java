package eu.europa.ec.grow.espd.domain;

import eu.europa.ec.grow.espd.constants.enums.Country;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * Created by ratoico on 1/5/16 at 5:25 PM.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class EnvironmentalCriterion extends ExclusionCriterion implements DescriptionHolder {

    private String description;

    @Override
    public Date getDateOfConviction() {
        throw new UnsupportedOperationException("Environmental criterion does not have a date of conviction.");
    }

    @Override
    public String getPeriodLength() {
        throw new UnsupportedOperationException("Environmental criterion does not have a period length.");
    }

    @Override
    public Country getCountry() {
        throw new UnsupportedOperationException("Environmental criterion does not have a country.");
    }

    @Override
    public Double getAmount() {
        throw new UnsupportedOperationException("Environmental criterion does not have an amount.");
    }

    @Override
    public String getCurrency() {
        throw new UnsupportedOperationException("Environmental criterion does not have a currency.");
    }

    @Override
    public String getReason() {
        throw new UnsupportedOperationException("Environmental criterion does not have a reason.");
    }

    public static EnvironmentalCriterion buildWithExists(boolean exists) {
        EnvironmentalCriterion criterion = new EnvironmentalCriterion();
        criterion.setExists(exists);
        return criterion;
    }
}
