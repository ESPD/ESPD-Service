package eu.europa.ec.grow.espd.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by ratoico on 1/6/16 at 11:39 AM.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MisconductDistortionCriterion extends ExclusionCriterion {

    public static MisconductDistortionCriterion buildWithExists(boolean exists) {
        MisconductDistortionCriterion criterion = new MisconductDistortionCriterion();
        criterion.setExists(exists);
        return criterion;
    }
}
