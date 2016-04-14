package eu.europa.ec.grow.espd.xml.response.exporting;

import eu.europa.ec.grow.espd.xml.common.exporting.UblCriterionTypeTemplate;
import eu.europa.ec.grow.espd.xml.common.exporting.UblRequirementTypeTemplate;

/**
 * Created by ratoico on 12/22/15 at 4:07 PM.
 */
class UblResponseCriterionTransformer extends UblCriterionTypeTemplate {

    @Override
    protected UblRequirementTypeTemplate buildRequirementTransformer() {
        return new UblResponseRequirementTransformer();
    }

}
