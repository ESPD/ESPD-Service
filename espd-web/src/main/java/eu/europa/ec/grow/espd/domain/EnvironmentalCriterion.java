package eu.europa.ec.grow.espd.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by ratoico on 1/5/16 at 5:25 PM.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class EnvironmentalCriterion extends ExclusionCriterion implements DescriptionHolder {

    public static EnvironmentalCriterion buildWithExists(boolean exists) {
        EnvironmentalCriterion criterion = new EnvironmentalCriterion();
        criterion.setExists(exists);
        return criterion;
    }
}
