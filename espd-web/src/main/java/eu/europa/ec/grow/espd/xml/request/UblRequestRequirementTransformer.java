package eu.europa.ec.grow.espd.xml.request;

import eu.europa.ec.grow.espd.constants.enums.Agency;
import eu.europa.ec.grow.espd.domain.Criterion;
import eu.europa.ec.grow.espd.entities.CcvCriterionRequirement;
import eu.europa.ec.grow.espd.xml.common.UblRequirementTypeTemplate;
import isa.names.specification.ubl.schema.xsd.ccv_commonaggregatecomponents_1.RequirementType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.DescriptionType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.IDType;

import org.springframework.stereotype.Component;

/**
 * Created by ratoico on 12/7/15 at 2:25 PM.
 */
@Component
class UblRequestRequirementTransformer extends UblRequirementTypeTemplate {

    @Override
    protected RequirementType buildRequirementType(CcvCriterionRequirement ccvRequirement, Criterion espdCriterion) {
        RequirementType requirementType = new RequirementType();

        IDType idType = new IDType();
        idType.setValue(ccvRequirement.getId());
        idType.setSchemeAgencyID(Agency.EU_COM_GROW.getIdentifier());
        idType.setSchemeID("CriterionRelatedIDs");
        idType.setSchemeVersionID("1.0");
        requirementType.setID(idType);

        DescriptionType descriptionType = new DescriptionType();
        descriptionType.setValue(ccvRequirement.getDescription());
        requirementType.setDescription(descriptionType);

        requirementType.setResponseDataType(ccvRequirement.getResponseType().getCode());

        return requirementType;
    }
}
