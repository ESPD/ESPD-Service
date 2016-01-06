package eu.europa.ec.grow.espd.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by ratoico on 1/5/16 at 1:54 PM.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SuitabilityCriterion extends SelectionCriterion {

    public static SuitabilityCriterion buildWithExists(boolean exists) {
        SuitabilityCriterion criterion = new SuitabilityCriterion();
        criterion.setExists(exists);
        return criterion;
    }
}
