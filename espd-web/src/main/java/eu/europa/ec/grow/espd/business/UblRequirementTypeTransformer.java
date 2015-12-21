package eu.europa.ec.grow.espd.business;

import com.google.common.base.Function;
import eu.europa.ec.grow.espd.constants.enums.Agency;
import eu.europa.ec.grow.espd.entities.CcvCriterionRequirement;
import isa.names.specification.ubl.schema.xsd.ccv_commonaggregatecomponents_1.RequirementType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.DescriptionType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.IDType;
import org.springframework.stereotype.Component;

/**
 * Created by ratoico on 12/7/15 at 2:25 PM.
 */
@Component
class UblRequirementTypeTransformer implements Function<CcvCriterionRequirement, RequirementType> {

    @Override
    public RequirementType apply(final CcvCriterionRequirement input) {
        RequirementType requirementType = new RequirementType();

        IDType idType = new IDType();
        idType.setValue(input.getId());
        idType.setSchemeAgencyID(Agency.EU_COM_GROW.getIdentifier());
        idType.setSchemeID("CriterionRelatedIDs");
        idType.setSchemeVersionID("1.0");
        requirementType.setID(idType);

        DescriptionType descriptionType = new DescriptionType();
        descriptionType.setValue(input.getDescription());
        requirementType.setDescription(descriptionType);

        requirementType.setResponseDataType(input.getResponseType().getCode());

        return requirementType;
    }
}
