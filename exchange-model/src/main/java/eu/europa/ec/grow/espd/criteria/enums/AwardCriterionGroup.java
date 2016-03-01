package eu.europa.ec.grow.espd.criteria.enums;

import eu.europa.ec.grow.espd.entities.CcvCriterionGroup;
import eu.europa.ec.grow.espd.entities.CcvCriterionRequirement;
import lombok.Getter;

import java.util.Collections;
import java.util.List;

import static eu.europa.ec.grow.espd.criteria.enums.AwardCriterionRequirement.*;
import static eu.europa.ec.grow.espd.criteria.enums.ListUtil.list;

/**
 * Created by ratoico on 1/15/16 at 3:36 PM.
 */
@Getter
public enum AwardCriterionGroup implements CcvCriterionGroup {

    /**
     *
     */
    PROCUREMENT_RESERVED_GROUP("6febbe4a-e715-427c-a2b1-19cfabadaef0", Collections.<CcvCriterionGroup>emptyList(),
            list(INDICATOR, CORRESPONDING_PERCENTAGE, DETAILS_EMPLOYEES_CATEGORY)),
    /**
     *
     */
    EO_REGISTERED_GROUP_1_1("92e44d3b-af8e-4a29-91a8-24d27aa27fee", Collections.<CcvCriterionGroup>emptyList(),
            list(REGISTRATION_COVERS_SELECTION_CRITERIA)),
    /**
     *
     */
    EO_REGISTERED_GROUP_1("64162276-7014-408f-a9af-080426bfe1fd", list(EO_REGISTERED_GROUP_1_1),
            list(INDICATOR, NOT_APPLICABLE, PROVIDE_REGISTRATION_NUMBER, REG_NO_AVAILABLE_ELECTRONICALLY,
                    REFERENCES_REGISTRATION)),
    /**
     *
     */
    EO_REGISTERED_GROUP_2("59e6f3ef-15cd-4e21-82ac-ea497ccd44e2", Collections.<CcvCriterionGroup>emptyList(),
            list(EO_ABLE_PROVIDE_CERTIFICATE, DOC_AVAILABLE_ELECTRONICALLY)),
    /**
     *
     */
    EO_PARTICIPATING_PROCUREMENT_PROCEDURE_GROUP("d939f2c6-ba25-4dc4-889c-11d1853add19",
            Collections.<CcvCriterionGroup>emptyList(),
            list(INDICATOR, ECONOMIC_OPERATOR_ROLE, OTHER_ECONOMIC_OPERATORS, PARTICIPANT_GROUP_NAME)),
    /**
     *
     */
    EO_RELIES_CAPACITIES_GROUP("e688f7d6-dcef-4726-bc61-052e63ead60f",
            Collections.<CcvCriterionGroup>emptyList(), list(INDICATOR)),
    /**
     *
     */
    MEETS_OBJECTIVE_GROUP("3e5c2859-68a7-4312-92e4-01ae79c00cb8",
            Collections.<CcvCriterionGroup>emptyList(), list(INDICATOR, PLEASE_DESCRIBE)), /**
     *
     */
    SUBCONTRACTING_THIRD_PARTIES_GROUP("d5fe5a71-7fd3-4910-b6f4-5cd2a4d23524",
                    Collections.<CcvCriterionGroup>emptyList(), list(INDICATOR, LIST_SUBCONTRACTORS)),
    /**
     *
     */
    INFO_ELECTRONICALLY_GROUP("ab335516-73a4-41f7-977b-a98c13a51060", Collections.<CcvCriterionGroup>emptyList(),
            list(INFO_AVAILABLE_ELECTRONICALLY, URL, URL_CODE)),;

    private final String id;

    private final List<? extends CcvCriterionGroup> subgroups;

    private final List<? extends CcvCriterionRequirement> requirements;

    AwardCriterionGroup(String id, List<? extends CcvCriterionGroup> subgroups,
            List<? extends CcvCriterionRequirement> requirements) {
        this.id = id;
        this.subgroups = subgroups;
        this.requirements = requirements;
    }

}
