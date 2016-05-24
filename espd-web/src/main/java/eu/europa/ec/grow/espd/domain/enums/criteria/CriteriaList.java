/*
 *
 * Copyright 2016 EUROPEAN COMMISSION
 *
 * Licensed under the EUPL, Version 1.1 or – as soon they
 * will be approved by the European Commission - subsequent
 * versions of the EUPL (the "Licence");
 *
 * You may not use this work except in compliance with the Licence.
 *
 * You may obtain a copy of the Licence at:
 *
 * https://joinup.ec.europa.eu/community/eupl/og_page/eupl
 *
 * Unless required by applicable law or agreed to in
 * writing, software distributed under the Licence is
 * distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied.
 * See the Licence for the specific language governing
 * permissions and limitations under the Licence.
 *
 */

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
        for (OtherCriterion criterion : OtherCriterion.values()) {
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
