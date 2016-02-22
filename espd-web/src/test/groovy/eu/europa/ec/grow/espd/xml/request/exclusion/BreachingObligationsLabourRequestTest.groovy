package eu.europa.ec.grow.espd.xml.request.exclusion

import eu.europa.ec.grow.espd.criteria.enums.ExclusionCriterion
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.LawCriterion
import eu.europa.ec.grow.espd.xml.base.AbstractExclusionCriteriaFixture
/**
 * Created by ratoico on 1/11/16 at 11:03 AM.
 */
class BreachingObligationsLabourRequestTest extends AbstractExclusionCriteriaFixture {

    def "11. should contain the 'Breaching of obligations in the fields of labour law' criterion"() {
        given:
        def espd = new EspdDocument(breachingObligationsLabour: new LawCriterion(exists: true))

        when:
        def request = parseRequestXml(espd)
        def idx = getRequestCriterionIndex(ExclusionCriterion.BREACHING_OF_OBLIGATIONS_LABOUR)

        then: "CriterionID element"
        checkCriterionId(request, idx, "a34b70d6-c43d-4726-9a88-8e2b438424bf")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "EXCLUSION.LABOUR_LAW")

        then: "CriterionName element"
        request.Criterion[idx].Name.text() == "Breaching of obligations in the fields of labour law"

        then: "CriterionDescription element"
        request.Criterion[idx].Description.text() == "Has the economic operator, to its knowledge, breached its obligations in the fields of labour law? As referred to for the purposes of this procurement in national law, in the relevant notice or the procurement documents or in Article 18(2) of Directive 2014/24/EU."

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "57(4)")

        then: "check all the sub groups"
        request.Criterion[idx].RequirementGroup.size() == 1

        then: "main sub group"
        request.Criterion[idx].RequirementGroup[0].ID.text() == "c5bc8338-6f20-4f53-a3b1-1e6be0480759"
        request.Criterion[idx].RequirementGroup[0].RequirementGroup.size() == 1
        request.Criterion[idx].RequirementGroup[0].Requirement.size() == 2

        then: "main sub group requirements"
        def r1_0 = request.Criterion[idx].RequirementGroup[0].Requirement[0]
        checkRequirement(r1_0, "974c8196-9d1c-419c-9ca9-45bb9f5fd59a", "Your answer?", "INDICATOR")

        def r1_1 = request.Criterion[idx].RequirementGroup[0].Requirement[1]
        checkRequirement(r1_1, "e098da8e-4717-4500-965f-f882d5b4e1ad", "Please describe them", "DESCRIPTION")

        then: "check first sub group"
        def sub1_1 = request.Criterion[idx].RequirementGroup[0].RequirementGroup[0]
        checkSelfCleaningRequirementGroup(sub1_1)
    }

}