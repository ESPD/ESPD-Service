package eu.europa.ec.grow.espd.entities;

import java.util.List;

/**
 * Created by vigi on 11/17/15:4:16 PM.
 */
public interface CcvCriterion {

    String getUuid();

    String getTypeCode();

    String getName();

    String getDescription();

    CcvLegislation getLegislation();

    List<? extends CcvCriterionRequirement> getRequirements();
}
