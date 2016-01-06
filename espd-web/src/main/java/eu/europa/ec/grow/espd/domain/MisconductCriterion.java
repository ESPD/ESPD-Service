package eu.europa.ec.grow.espd.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by ratoico on 1/6/16 at 11:39 AM.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MisconductCriterion extends ExclusionCriterion {

    public static MisconductCriterion buildWithExists(boolean exists) {
        MisconductCriterion criterion = new MisconductCriterion();
        criterion.setExists(exists);
        return criterion;
    }
}
