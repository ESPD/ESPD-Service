package eu.europa.ec.grow.espd.business.request;

import eu.europa.ec.grow.espd.business.common.UblCriteriaTemplate;
import eu.europa.ec.grow.espd.business.common.UblCriterionTypeTemplate;
import eu.europa.ec.grow.espd.criteria.enums.AwardCriterion;
import eu.europa.ec.grow.espd.domain.EspdDocument;
import isa.names.specification.ubl.schema.xsd.ccv_commonaggregatecomponents_1.CriterionType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    @Override
    protected List<CriterionType> buildAwardCriteria(EspdDocument espdDocument) {
        List<CriterionType> criterionTypes = new ArrayList<>(1);
        // meets objective (reduction of numbers) is common to request and response
        addSelectedUblCriterion(AwardCriterion.MEETS_OBJECTIVE, espdDocument.getMeetsObjective(), criterionTypes);
        return Collections.unmodifiableList(criterionTypes);
    }

}
