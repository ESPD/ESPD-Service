package eu.europa.ec.grow.espd.business.request;

import eu.europa.ec.grow.espd.business.common.UblCriteriaTemplate;
import eu.europa.ec.grow.espd.business.common.UblCriterionTypeTemplate;
import isa.names.specification.ubl.schema.xsd.ccv_commonaggregatecomponents_1.CriterionType;

/**
 * Create the UBL {@link CriterionType} criteria for a ESPD Request, including both exclusion and selection
 * criteria.
 * <p/>
 * Created by ratoico on 11/26/15 at 5:19 PM.
 */
class UblRequestCriteriaTransformer extends UblCriteriaTemplate {

    @Override
    protected UblCriterionTypeTemplate buildCriterionTypeTransformerTemplate() {
        return new UblRequestCriterionTransformer();
    }
}
