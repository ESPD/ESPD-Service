package eu.europa.ec.grow.espd.xml.common.exporting;

import eu.europa.ec.grow.espd.domain.EspdCriterion;
import eu.europa.ec.grow.espd.domain.ubl.CcvCriterionRequirement;
import isa.names.specification.ubl.schema.xsd.ccv_commonaggregatecomponents_1.RequirementType;

/**
 * Created by ratoico on 12/22/15 at 3:11 PM.
 */
public abstract class UblRequirementTypeTemplate {

    protected abstract RequirementType buildRequirementType(CcvCriterionRequirement ccvRequirement,
            EspdCriterion espdCriterion);
}
