package eu.europa.ec.grow.espd.xml.request.award

import eu.europa.ec.grow.espd.domain.AwardCriterion
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.xml.base.AbstractAwardCriteriaFixture

/**
 * Created by ratoico on 1/20/16 at 2:19 PM.
 */
class MeetsObjectiveRequestTest extends AbstractAwardCriteriaFixture {

    def "05. should contain the 'If applicable, is the economic operator registered' criterion"() {
        given:
        def espd = new EspdDocument(meetsObjective: new AwardCriterion(exists: true))

        when:
        def request = parseRequestXml(espd)
        // we need a dirty workaround to get the proper index since the request only contains one award criterion we specify the criterion with ordinal 0
        def idx = getCriterionIndex(eu.europa.ec.grow.espd.criteria.enums.AwardCriterion.PROCUREMENT_RESERVED)

        then: "CriterionID element"
        request.Criterion.size() == getRequestNumberOfCriteria()
        checkCriterionId(request, idx, "9c70375e-1264-407e-8b50-b9736bc08901")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "REDUCTION_OF_CANDIDATES")

        then: "CriterionName element"
        request.Criterion[idx].Name.text() == "It meets the objective and non discriminatory criteria or rules to be applied in order to limit the number of candidates in the following way: In case certain certificates or other forms of documentary evidence are required, please indicate for each whether the economic operator has the required documents:"

        then: "CriterionDescription element"
        request.Criterion[idx].Description.text() == "If some of these certificates or forms of documentary evidence are available electronically, please indicate for each:"

        then: "check all the sub groups"
        request.Criterion[idx].RequirementGroup.size() == 2

        then: "main sub group"
        request.Criterion[idx].RequirementGroup[0].ID.text() == "3e5c2859-68a7-4312-92e4-01ae79c00cb8"
        request.Criterion[idx].RequirementGroup[0].RequirementGroup.size() == 0
        request.Criterion[idx].RequirementGroup[0].Requirement.size() == 2

        then: "first sub group requirements"
        def r1_0 = request.Criterion[idx].RequirementGroup[0].Requirement[0]
        checkRequirement(r1_0, "7f18c64e-ae09-4646-9400-f3666d50af51", "", "INDICATOR")

        def r1_1 = request.Criterion[idx].RequirementGroup[0].Requirement[1]
        checkRequirement(r1_1, "323f19b5-3308-4873-b2d1-767963cc81e9", "Please describe them", "DESCRIPTION")

        then: "second subgroup"
        request.Criterion[idx].RequirementGroup[1].ID.text() == "ab335516-73a4-41f7-977b-a98c13a51060"
        request.Criterion[idx].RequirementGroup[1].RequirementGroup.size() == 0
        request.Criterion[idx].RequirementGroup[1].Requirement.size() == 3

        then: "second subgroup requirements"
        def r2_0 = request.Criterion[idx].RequirementGroup[1].Requirement[0]
        checkRequirement(r2_0, "0622bbd1-7378-45e1-8fb9-25429740ac22",
                "Is this information available electronically?", "INDICATOR")

        def r2_1 = request.Criterion[idx].RequirementGroup[1].Requirement[1]
        checkRequirement(r2_1, "ee1ee1cd-3791-4855-8b8b-28d4f4c5c007", "URL", "URL")

        def r2_2 = request.Criterion[idx].RequirementGroup[1].Requirement[2]
        checkRequirement(r2_2, "1e55ff14-c643-4abc-91d7-2f4dfcdf2409", "Code", "CODE")
    }

}