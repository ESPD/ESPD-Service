package eu.europa.ec.grow.espd.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by ratoico on 1/6/16 at 10:44 AM.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BankruptcyCriterion extends ExclusionCriterion {

    private String reason;

    public static BankruptcyCriterion buildWithExists(boolean exists) {
        BankruptcyCriterion criterion = new BankruptcyCriterion();
        criterion.setExists(exists);
        return criterion;
    }
}
