package eu.europa.ec.grow.espd.business.response;

import eu.europa.ec.grow.espd.business.common.UblCriterionTypeTemplate;
import eu.europa.ec.grow.espd.business.common.UblRequirementTypeTemplate;

/**
 * Created by ratoico on 12/22/15 at 4:07 PM.
 */
class UblResponseCriterionTransformer extends UblCriterionTypeTemplate{

    @Override
    protected UblRequirementTypeTemplate buildRequirementTransformer() {
        return new UblResponseRequirementTransformer();
    }

}
