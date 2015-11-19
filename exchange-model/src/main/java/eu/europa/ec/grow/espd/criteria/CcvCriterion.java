package eu.europa.ec.grow.espd.criteria;

/**
 * Created by vigi on 11/17/15:4:16 PM.
 */
public interface CcvCriterion {

    String getUuid();

    String getTypeCode();

    String getName();

    String getDescription();

    CcvLegislation getLegislation();
}
