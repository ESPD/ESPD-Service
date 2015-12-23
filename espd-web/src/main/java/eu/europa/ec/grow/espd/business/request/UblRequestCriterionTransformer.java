package eu.europa.ec.grow.espd.business.request;

import eu.europa.ec.grow.espd.business.common.UblCriterionTypeTemplate;
import isa.names.specification.ubl.schema.xsd.ccv_commonaggregatecomponents_1.CriterionType;

/**
 * Transforms the criterion information coming from ESPD into a {@link CriterionType} object.
 * <p/>
 * Created by vigi on 11/16/15:3:38 PM.
 */
class UblRequestCriterionTransformer extends UblCriterionTypeTemplate {

    @Override
    protected eu.europa.ec.grow.espd.business.common.UblRequirementTypeTemplate buildRequirementTransformer() {
        return new UblRequestRequirementTransformer();
    }
}
