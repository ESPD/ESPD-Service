package eu.europa.ec.grow.espd.business.response;

import eu.europa.ec.grow.espd.business.common.UblCriteriaTemplate;
import eu.europa.ec.grow.espd.business.common.UblCriterionTypeTemplate;
import eu.europa.ec.grow.espd.criteria.enums.AwardCriterion;
import eu.europa.ec.grow.espd.domain.EspdDocument;
import isa.names.specification.ubl.schema.xsd.ccv_commonaggregatecomponents_1.CriterionType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Create the UBL {@link CriterionType} criteria for a ESPD Response, including both exclusion and selection
 * criteria.
 * <p/>
 * Created by ratoico on 11/27/15 at 11:40 AM.
 */
class UblResponseCriteriaTransformer extends UblCriteriaTemplate {

    @Override
    protected UblCriterionTypeTemplate buildCriterionTypeTransformerTemplate() {
        return new UblResponseCriterionTransformer();
    }

    @Override
    protected List<CriterionType> buildAwardCriteria(EspdDocument espdDocument) {
        List<CriterionType> criterionTypes = new ArrayList<>(AwardCriterion.values().length + 1);
        for (AwardCriterion criterion : AwardCriterion.values()) {
            addAlwaysUblCriterion(criterion, espdDocument, criterionTypes);
        }
        return Collections.unmodifiableList(criterionTypes);
    }

}
