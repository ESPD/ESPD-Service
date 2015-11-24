package eu.europa.ec.grow.espd.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by vigi on 11/3/15:2:56 PM.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SelectionCriterion extends Criterion {

    private AvailableElectronically availableElectronically;
}
