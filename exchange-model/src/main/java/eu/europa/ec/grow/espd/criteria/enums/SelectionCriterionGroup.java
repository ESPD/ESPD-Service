package eu.europa.ec.grow.espd.criteria.enums;

import eu.europa.ec.grow.espd.entities.CcvCriterionGroup;
import eu.europa.ec.grow.espd.entities.CcvCriterionRequirement;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static eu.europa.ec.grow.espd.criteria.enums.ExclusionCriterionRequirement.YOUR_ANSWER;

/**
 * Created by ratoico on 12/7/15 at 3:44 PM.
 */
@Getter
public enum SelectionCriterionGroup implements CcvCriterionGroup {

    /**
     *
     */
    ALL_CRITERIA_SATISFIED_GROUP("24831d4d-57bb-4818-bf6a-b074b68288b1", Collections.<CcvCriterionGroup>emptyList(),
            list(YOUR_ANSWER));

    private final String id;

    private final List<? extends CcvCriterionGroup> subgroups;

    private final List<? extends CcvCriterionRequirement> requirements;

    SelectionCriterionGroup(String id, List<? extends CcvCriterionGroup> subgroups,
            List<? extends CcvCriterionRequirement> requirements) {
        this.id = id;
        this.subgroups = subgroups;
        this.requirements = requirements;
    }

    @SafeVarargs
    private static <T> List<T> list(T... values) {
        return Collections.unmodifiableList(Arrays.asList(values));
    }
}
