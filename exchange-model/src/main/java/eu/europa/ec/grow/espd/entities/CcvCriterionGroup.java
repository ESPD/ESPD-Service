package eu.europa.ec.grow.espd.entities;

import java.util.List;

/**
 * Created by ratoico on 12/4/15 at 10:45 AM.
 */
public interface CcvCriterionGroup {

    String getId();

    List<? extends CcvCriterionGroup> getSubgroups();

    List<? extends CcvCriterionRequirement> getRequirements();
}
