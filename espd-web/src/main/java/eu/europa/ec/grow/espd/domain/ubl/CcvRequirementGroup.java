package eu.europa.ec.grow.espd.domain.ubl;

import java.util.List;

/**
 * Created by ratoico on 12/4/15 at 10:45 AM.
 */
public interface CcvRequirementGroup {

    String getId();

    List<? extends CcvRequirementGroup> getSubgroups();

    List<? extends CcvCriterionRequirement> getRequirements();
}
