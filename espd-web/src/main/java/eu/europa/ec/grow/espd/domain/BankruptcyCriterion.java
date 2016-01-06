package eu.europa.ec.grow.espd.domain;

import eu.europa.ec.grow.espd.constants.enums.Country;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * Created by ratoico on 1/6/16 at 10:44 AM.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BankruptcyCriterion extends ExclusionCriterion {

    private String reason;

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
        return reason;
    }

    public static BankruptcyCriterion buildWithExists(boolean exists) {
        BankruptcyCriterion criterion = new BankruptcyCriterion();
        criterion.setExists(exists);
        return criterion;
    }
}
