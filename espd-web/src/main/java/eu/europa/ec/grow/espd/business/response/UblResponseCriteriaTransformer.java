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
        addAlwaysUblCriterion(AwardCriterion.PROCUREMENT_RESERVED, espdDocument.getProcurementReserved(),
                criterionTypes);
        addAlwaysUblCriterion(AwardCriterion.EO_REGISTERED, espdDocument.getEoRegistered(), criterionTypes);
        addAlwaysUblCriterion(AwardCriterion.EO_PARTICIPATING_PROCUREMENT_PROCEDURE,
                espdDocument.getEoParticipatingProcurementProcedure(), criterionTypes);
        addAlwaysUblCriterion(AwardCriterion.EO_RELIES_CAPACITIES, espdDocument.getEoReliesCapacities(),
                criterionTypes);
        addAlwaysUblCriterion(AwardCriterion.MEETS_OBJECTIVE, espdDocument.getMeetsObjective(), criterionTypes);
        return Collections.unmodifiableList(criterionTypes);
    }

}
