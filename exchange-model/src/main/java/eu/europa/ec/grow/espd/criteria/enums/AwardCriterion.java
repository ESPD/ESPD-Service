package eu.europa.ec.grow.espd.criteria.enums;

import eu.europa.ec.grow.espd.entities.CcvCriterion;
import eu.europa.ec.grow.espd.entities.CcvCriterionGroup;
import eu.europa.ec.grow.espd.entities.CcvCriterionType;
import eu.europa.ec.grow.espd.entities.CcvLegislation;
import lombok.Getter;

import java.util.List;

import static eu.europa.ec.grow.espd.criteria.enums.AwardCriterionGroup.*;
import static eu.europa.ec.grow.espd.criteria.enums.ListUtil.list;

/**
 * Created by ratoico on 1/15/16 at 4:11 PM.
 */
@Getter
public enum AwardCriterion implements CcvCriterion {

    /**
     *
     */
    PROCUREMENT_RESERVED("2043338f-a38a-490b-b3ec-2607cb25a017",
            "Only in case the procurement is reserved: is the economic operator a sheltered workshop, a 'social business' or will it provide for the performance of the contract in the context of sheltered employment programmes?",
            "", AwardTypeCode.DATA_ON_ECONOMIC_OPERATOR, null, list(PROCUREMENT_RESERVED_GROUP)),
    /**
     *
     */
    EO_REGISTERED("9b19e869-6c89-4cc4-bd6c-ac9ca8602165",
            "If applicable, is the economic operator registered on an official list of approved economic operators or does it have an equivalent certificate (e.g. under a national (pre)qualification system)?",
            "", AwardTypeCode.DATA_ON_ECONOMIC_OPERATOR, null,
            list(EO_REGISTERED_GROUP_1, EO_REGISTERED_GROUP_2)),
    /**
     *
     */
    EO_PARTICIPATING_PROCUREMENT_PROCEDURE("ee51100f-8e3e-40c9-8f8b-57d5a15be1f2",
            "Is the economic operator participating in the procurement procedure together with others?",
            "", AwardTypeCode.DATA_ON_ECONOMIC_OPERATOR, null,
            list(EO_PARTICIPATING_PROCUREMENT_PROCEDURE_GROUP)),
    /**
     *
     */
    EO_RELIES_CAPACITIES("0d62c6ed-f074-4fcf-8e9f-f691351d52ad",
            "Does the economic operator rely on the capacities of other entities in order to meet the selection criteria set out under Part IV and the criteria and rules (if any) set out under Part V below?",
            "", AwardTypeCode.DATA_ON_ECONOMIC_OPERATOR, null, list(EO_RELIES_CAPACITIES_GROUP)),
    /**
     *
     */
    MEETS_OBJECTIVE("9c70375e-1264-407e-8b50-b9736bc08901",
            "It meets the objective and non discriminatory criteria or rules to be applied in order to limit the number of candidates in the following way: In case certain certificates or other forms of documentary evidence are required, please indicate for each whether the economic operator has the required documents:",
            "If some of these certificates or forms of documentary evidence are available electronically, please indicate for each:",
            AwardTypeCode.REDUCTION_OF_CANDIDATES, null,
            list(MEETS_OBJECTIVE_GROUP, INFO_ELECTRONICALLY_GROUP)),;

    private final String uuid;

    private final String shortName;

    private final String description;

    private final AwardTypeCode criterionTypeCode;

    private final LegislationReference legislationReference;

    private final List<? extends CcvCriterionGroup> groups;

    AwardCriterion(String uuid, String shortName, String description,
            AwardTypeCode criterionTypeCode, LegislationReference legislationReference,
            List<? extends CcvCriterionGroup> groups) {
        this.uuid = uuid;
        this.shortName = shortName;
        this.description = description;
        this.criterionTypeCode = criterionTypeCode;
        this.legislationReference = legislationReference;
        this.groups = groups;
    }

    @Override
    public String getTypeCode() {
        return getCriterionTypeCode().name();
    }

    @Override
    public String getName() {
        return getShortName();
    }

    @Override
    public CcvLegislation getLegislation() {
        return getLegislationReference();
    }

    @Override
    public CcvCriterionType getCriterionType() {
        return this.criterionTypeCode;
    }

}
