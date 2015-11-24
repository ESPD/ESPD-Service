package eu.europa.ec.grow.espd.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ExclusionCriterion extends Criterion {

    private AvailableElectronically availableElectronically;
    private SelfCleaning selfCleaning;
    private BreachOfObligations breachOfObligations;
}
