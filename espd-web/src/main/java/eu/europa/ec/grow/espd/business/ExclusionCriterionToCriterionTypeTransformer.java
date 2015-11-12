package eu.europa.ec.grow.espd.business;

import eu.europa.ec.grow.espd.domain.ExclusionCriterion;
import eu.europa.ec.grow.espd.util.Function;
import isa.names.specification.ubl.schema.xsd.ccv_commonaggregatecomponents_1.CriterionType;
import org.springframework.stereotype.Component;

/**
 * Created by vigi on 11/11/15:3:04 PM.
 */
@Component
class ExclusionCriterionToCriterionTypeTransformer implements Function<ExclusionCriterion, CriterionType> {

    @Override
    public CriterionType apply(final ExclusionCriterion exclusionCriterion) {
        return null;
    }
}
