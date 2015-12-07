package eu.europa.ec.grow.espd.business;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import eu.europa.ec.grow.espd.constants.enums.Agency;
import eu.europa.ec.grow.espd.entities.CcvCriterionGroup;
import isa.names.specification.ubl.schema.xsd.ccv_commonaggregatecomponents_1.CriterionRequirementGroupType;
import isa.names.specification.ubl.schema.xsd.ccv_commonaggregatecomponents_1.CriterionRequirementType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.IDType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.apache.commons.collections.CollectionUtils.isEmpty;

/**
 * Created by ratoico on 12/7/15 at 2:27 PM.
 */
@Component
class ToCriterionGroupTransformer implements Function<CcvCriterionGroup, CriterionRequirementGroupType> {

    private final ToCriterionRequirementTransformer requirementTransformer;

    @Autowired
    ToCriterionGroupTransformer(ToCriterionRequirementTransformer requirementTransformer) {
        this.requirementTransformer = requirementTransformer;
    }

    @Override
    public CriterionRequirementGroupType apply(CcvCriterionGroup input) {
        CriterionRequirementGroupType groupType = new CriterionRequirementGroupType();

        addId(input, groupType);
        addRequirements(input, groupType);
        addSubgroups(input, groupType);

        return groupType;
    }

    private void addId(CcvCriterionGroup input, CriterionRequirementGroupType groupType) {
        IDType idType = new IDType();
        idType.setSchemeVersionID("1.0");
        idType.setSchemeAgencyID(Agency.EU_COM_GROW.getIdentifier());
        idType.setValue(input.getId());
        groupType.setID(idType);
    }

    private void addRequirements(CcvCriterionGroup input, CriterionRequirementGroupType groupType) {
        if (isEmpty(input.getRequirements())) {
            return;
        }

        List<CriterionRequirementType> requirementTypes = Lists
                .transform(input.getRequirements(), requirementTransformer);
        groupType.getCriterionRequirement().addAll(requirementTypes);
    }

    private void addSubgroups(CcvCriterionGroup input, CriterionRequirementGroupType groupType) {
        if (isEmpty(input.getSubgroups())) {
            return;
        }

        List<CriterionRequirementGroupType> subgroups = Lists.transform(input.getSubgroups(), this);
        groupType.getCriterionRequirementGroup().addAll(subgroups);
    }
}
