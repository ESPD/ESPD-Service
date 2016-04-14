package eu.europa.ec.grow.espd.domain.enums.criteria;

import eu.europa.ec.grow.espd.domain.ubl.CcvRequirementGroup;
import eu.europa.ec.grow.espd.domain.ubl.CcvCriterionRequirement;
import lombok.Getter;

import java.util.Collections;
import java.util.List;

/**
 * Created by ratoico on 1/15/16 at 3:36 PM.
 */
@Getter
public enum AwardRequirementGroup implements CcvRequirementGroup {

    /**
     *
     */
    PROCUREMENT_RESERVED_GROUP("6febbe4a-e715-427c-a2b1-19cfabadaef0", Collections.<CcvRequirementGroup>emptyList(),
            ListUtil.list(AwardCriterionRequirement.INDICATOR, AwardCriterionRequirement.CORRESPONDING_PERCENTAGE,
                    AwardCriterionRequirement.DETAILS_EMPLOYEES_CATEGORY)),
    /**
     *
     */
    EO_REGISTERED_GROUP_1_1("92e44d3b-af8e-4a29-91a8-24d27aa27fee", Collections.<CcvRequirementGroup>emptyList(),
            ListUtil.list(AwardCriterionRequirement.REGISTRATION_COVERS_SELECTION_CRITERIA)),
    /**
     *
     */
    EO_REGISTERED_GROUP_1("64162276-7014-408f-a9af-080426bfe1fd", ListUtil.list(EO_REGISTERED_GROUP_1_1),
            ListUtil.list(AwardCriterionRequirement.INDICATOR, AwardCriterionRequirement.NOT_APPLICABLE,
                    AwardCriterionRequirement.PROVIDE_REGISTRATION_NUMBER,
                    AwardCriterionRequirement.REG_NO_AVAILABLE_ELECTRONICALLY,
                    AwardCriterionRequirement.REFERENCES_REGISTRATION)),
    /**
     *
     */
    EO_REGISTERED_GROUP_2("59e6f3ef-15cd-4e21-82ac-ea497ccd44e2", Collections.<CcvRequirementGroup>emptyList(),
            ListUtil.list(AwardCriterionRequirement.EO_ABLE_PROVIDE_CERTIFICATE,
                    AwardCriterionRequirement.DOC_AVAILABLE_ELECTRONICALLY)),
    /**
     *
     */
    EO_PARTICIPATING_PROCUREMENT_PROCEDURE_GROUP("d939f2c6-ba25-4dc4-889c-11d1853add19",
            Collections.<CcvRequirementGroup>emptyList(),
            ListUtil.list(AwardCriterionRequirement.INDICATOR, AwardCriterionRequirement.ECONOMIC_OPERATOR_ROLE,
                    AwardCriterionRequirement.OTHER_ECONOMIC_OPERATORS,
                    AwardCriterionRequirement.PARTICIPANT_GROUP_NAME)),
    /**
     *
     */
    EO_RELIES_CAPACITIES_GROUP("e688f7d6-dcef-4726-bc61-052e63ead60f",
            Collections.<CcvRequirementGroup>emptyList(), ListUtil.list(AwardCriterionRequirement.INDICATOR)),
    /**
     *
     */
    MEETS_OBJECTIVE_GROUP("3e5c2859-68a7-4312-92e4-01ae79c00cb8",
            Collections.<CcvRequirementGroup>emptyList(), ListUtil
            .list(AwardCriterionRequirement.INDICATOR, AwardCriterionRequirement.PLEASE_DESCRIBE)), /**
     *
     */
    SUBCONTRACTING_THIRD_PARTIES_GROUP("d5fe5a71-7fd3-4910-b6f4-5cd2a4d23524",
                    Collections.<CcvRequirementGroup>emptyList(), ListUtil
                    .list(AwardCriterionRequirement.INDICATOR, AwardCriterionRequirement.LIST_SUBCONTRACTORS)),
    /**
     *
     */
    INFO_ELECTRONICALLY_GROUP("ab335516-73a4-41f7-977b-a98c13a51060", Collections.<CcvRequirementGroup>emptyList(),
            ListUtil.list(AwardCriterionRequirement.INFO_AVAILABLE_ELECTRONICALLY, AwardCriterionRequirement.URL,
                    AwardCriterionRequirement.URL_CODE)),;

    private final String id;

    private final List<? extends CcvRequirementGroup> subgroups;

    private final List<? extends CcvCriterionRequirement> requirements;

    AwardRequirementGroup(String id, List<? extends CcvRequirementGroup> subgroups,
            List<? extends CcvCriterionRequirement> requirements) {
        this.id = id;
        this.subgroups = subgroups;
        this.requirements = requirements;
    }

}
