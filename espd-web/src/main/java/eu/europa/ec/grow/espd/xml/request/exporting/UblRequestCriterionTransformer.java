package eu.europa.ec.grow.espd.xml.request.exporting;

import eu.europa.ec.grow.espd.xml.common.exporting.UblCriterionTypeTemplate;
import eu.europa.ec.grow.espd.xml.common.exporting.UblRequirementTypeTemplate;
import isa.names.specification.ubl.schema.xsd.ccv_commonaggregatecomponents_1.CriterionType;

/**
 * Transforms the criterion information coming from ESPD into a {@link CriterionType} object.
 * <p/>
 * Created by vigi on 11/16/15:3:38 PM.
 */
class UblRequestCriterionTransformer extends UblCriterionTypeTemplate {

    @Override
    protected UblRequirementTypeTemplate buildRequirementTransformer() {
        return new UblRequestRequirementTransformer();
    }
}
