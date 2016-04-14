package eu.europa.ec.grow.espd.xml.request.exporting;

import eu.europa.ec.grow.espd.domain.EspdCriterion;
import eu.europa.ec.grow.espd.domain.enums.other.Agency;
import eu.europa.ec.grow.espd.domain.ubl.CcvCriterionRequirement;
import eu.europa.ec.grow.espd.xml.common.exporting.UblRequirementTypeTemplate;
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
    protected RequirementType buildRequirementType(CcvCriterionRequirement ccvRequirement, EspdCriterion espdCriterion) {
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
