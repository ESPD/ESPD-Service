package eu.europa.ec.grow.espd.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by ratoico on 1/5/16 at 5:25 PM.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LawCriterion extends ExclusionCriterion {

    public static LawCriterion buildWithExists(boolean exists) {
        LawCriterion criterion = new LawCriterion();
        criterion.setExists(exists);
        return criterion;
    }
}
