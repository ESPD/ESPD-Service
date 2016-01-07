package eu.europa.ec.grow.espd.entities;

import java.util.List;

/**
 * Specific condition that the Economic Operator has to fulfill in order to be considered
 * as a candidate in a procurement process.
 * <p/>
 * Created by vigi on 11/17/15:4:16 PM.
 */
public interface CcvCriterion {

    String getUuid();

    String getTypeCode();

    String getName();

    String getDescription();

    CcvLegislation getLegislation();

    List<? extends CcvCriterionGroup> getGroups();

    CcvCriterionType getCriterionType();
}
