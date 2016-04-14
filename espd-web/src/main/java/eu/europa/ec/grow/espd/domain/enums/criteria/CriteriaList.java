package eu.europa.ec.grow.espd.domain.enums.criteria;

import com.google.common.base.Optional;
import eu.europa.ec.grow.espd.domain.ubl.CcvCriterion;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ratoico on 3/11/16 at 10:17 AM.
 */
public final class CriteriaList {

    private static final Map<String, CcvCriterion> CRITERIA = new HashMap<>();

    static {
        for (ExclusionCriterion criterion : ExclusionCriterion.values()) {
            CRITERIA.put(criterion.getUuid(), criterion);
        }
        for (SelectionCriterion criterion : SelectionCriterion.values()) {
            CRITERIA.put(criterion.getUuid(), criterion);
        }
        for (AwardCriterion criterion : AwardCriterion.values()) {
            CRITERIA.put(criterion.getUuid(), criterion);
        }
    }

    private CriteriaList() {

    }

    public static Optional<CcvCriterion> findById(String uuid) {
        if (CRITERIA.get(uuid) != null) {
            return Optional.of(CRITERIA.get(uuid));
        }
        return Optional.absent();
    }
}
