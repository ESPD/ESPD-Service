package eu.europa.ec.grow.espd.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by ratoico on 1/6/16 at 11:51 AM.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ConflictInterestCriterion extends ExclusionCriterion {

    public static ConflictInterestCriterion buildWithExists(boolean exists) {
        ConflictInterestCriterion criterion = new ConflictInterestCriterion();
        criterion.setExists(exists);
        return criterion;
    }
}
