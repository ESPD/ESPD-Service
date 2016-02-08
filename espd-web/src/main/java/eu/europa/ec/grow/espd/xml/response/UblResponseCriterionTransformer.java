package eu.europa.ec.grow.espd.xml.response;

import eu.europa.ec.grow.espd.xml.common.UblCriterionTypeTemplate;
import eu.europa.ec.grow.espd.xml.common.UblRequirementTypeTemplate;

/**
 * Created by ratoico on 12/22/15 at 4:07 PM.
 */
class UblResponseCriterionTransformer extends UblCriterionTypeTemplate{

    @Override
    protected UblRequirementTypeTemplate buildRequirementTransformer() {
        return new UblResponseRequirementTransformer();
    }

}
