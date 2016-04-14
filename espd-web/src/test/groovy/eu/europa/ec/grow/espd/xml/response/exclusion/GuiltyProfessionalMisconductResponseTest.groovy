package eu.europa.ec.grow.espd.xml.response.exclusion

import eu.europa.ec.grow.espd.domain.enums.criteria.ExclusionCriterion
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.MisconductDistortionCriterion
import eu.europa.ec.grow.espd.domain.SelfCleaning
import eu.europa.ec.grow.espd.xml.base.AbstractExclusionCriteriaFixture
/**
 * Created by ratoico on 12/9/15 at 1:21 PM.
 */
class GuiltyProfessionalMisconductResponseTest extends AbstractExclusionCriteriaFixture {

    def "18. should contain the 'Guilty of grave professional misconduct' criterion"() {
        given:
        def espd = new EspdDocument(guiltyGrave: new MisconductDistortionCriterion(exists: true))

        when:
        def request = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(ExclusionCriterion.GUILTY_OF_PROFESSIONAL_MISCONDUCT)

        then: "CriterionID element"
        checkCriterionId(request, idx, "514d3fde-1e3e-4dcd-b02a-9f984d5bbda3")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "EXCLUSION.MISCONDUCT")

        then: "CriterionName element"
        request.Criterion[idx].Name.text() == "Guilty of grave professional misconduct"

        then: "CriterionDescription element"
        request.Criterion[idx].Description.text() == "Is the economic operator  guilty of grave professional misconduct? Where applicable, see definitions in national law, the relevant notice or the procurement documents."

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "57(4)")

        then: "check all the sub groups"
        request.Criterion[idx].RequirementGroup.size() == 1

        then: "main sub group"
        request.Criterion[idx].RequirementGroup[0].ID.text() == "67362ec7-cec3-4cb8-a38e-5d7a2a31e6d8"
        request.Criterion[idx].RequirementGroup[0].RequirementGroup.size() == 1
        request.Criterion[idx].RequirementGroup[0].Requirement.size() == 2

        then: "main sub group requirements"
        def r1_0 = request.Criterion[idx].RequirementGroup[0].Requirement[0]
        checkRequirement(r1_0, "974c8196-9d1c-419c-9ca9-45bb9f5fd59a", "Your answer?", "INDICATOR")

        def r1_1 = request.Criterion[idx].RequirementGroup[0].Requirement[1]
        checkRequirement(r1_1, "e098da8e-4717-4500-965f-f882d5b4e1ad", "Please describe them", "DESCRIPTION")

        then: "self cleaninng"
        def sub1_1 = request.Criterion[idx].RequirementGroup[0].RequirementGroup[0]
        checkSelfCleaningRequirementGroup(sub1_1)
    }

    def "check the 'Your answer' requirement response"() {
        given:
        def espd = new EspdDocument(guiltyGrave: new MisconductDistortionCriterion(exists: true, answer: true))

        when:
        def request = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(ExclusionCriterion.GUILTY_OF_PROFESSIONAL_MISCONDUCT)

        then:
        def req = request.Criterion[idx].RequirementGroup[0].Requirement[0]
        checkRequirement(req, "974c8196-9d1c-419c-9ca9-45bb9f5fd59a", "Your answer?", "INDICATOR")
        req.Response.size() == 1
        req.Response[0].Indicator.text() == "true"
    }

    def "check the 'Please describe them' requirement response"() {
        given:
        def espd = new EspdDocument(guiltyGrave: new MisconductDistortionCriterion(exists: true,
                description: "bogus description."))

        when:
        def request = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(ExclusionCriterion.GUILTY_OF_PROFESSIONAL_MISCONDUCT)

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[0]

        def req = subGroup.Requirement[1]
        checkRequirement(req, "e098da8e-4717-4500-965f-f882d5b4e1ad", "Please describe them", "DESCRIPTION")
        req.Response[0].Description.text() == "bogus description."
    }

    def "check the 'Have you taken measures to demonstrate your reliability (\"Self-Cleaning\")' requirement response"() {
        given:
        def espd = new EspdDocument(guiltyGrave: new MisconductDistortionCriterion(exists: true,
                selfCleaning: new SelfCleaning(answer: false)))

        when:
        def request = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(ExclusionCriterion.GUILTY_OF_PROFESSIONAL_MISCONDUCT)

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[0].RequirementGroup[0]

        def req = subGroup.Requirement[0]
        req.Response.size() == 1
        req.Response[0].Indicator.text() == "false"
    }

    def "check the 'Self cleaning description' requirement response"() {
        given:
        def espd = new EspdDocument(guiltyGrave: new MisconductDistortionCriterion(exists: true,
                selfCleaning: new SelfCleaning(description: "Hodor_16 is clean")))

        when:
        def request = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(ExclusionCriterion.GUILTY_OF_PROFESSIONAL_MISCONDUCT)

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[0].RequirementGroup[0]

        def req = subGroup.Requirement[1]
        req.Response.size() == 1
        req.Response[0].Description.text() == "Hodor_16 is clean"
    }

}