package eu.europa.ec.grow.espd.domain;

import eu.europa.ec.grow.espd.constants.enums.Country;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * Created by ratoico on 1/6/16 at 11:39 AM.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MisconductCriterion extends ExclusionCriterion {

    @Override
    public Date getDateOfConviction() {
        return null;
    }

    @Override
    public String getPeriodLength() {
        return null;
    }

    @Override
    public Country getCountry() {
        return null;
    }

    @Override
    public Double getAmount() {
        return null;
    }

    @Override
    public String getCurrency() {
        return null;
    }

    @Override
    public String getReason() {
        return null;
    }

    public static MisconductCriterion buildWithExists(boolean exists) {
        MisconductCriterion criterion = new MisconductCriterion();
        criterion.setExists(exists);
        return criterion;
    }
}
