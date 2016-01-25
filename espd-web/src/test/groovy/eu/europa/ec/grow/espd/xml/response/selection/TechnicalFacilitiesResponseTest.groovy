package eu.europa.ec.grow.espd.xml.response.selection

import eu.europa.ec.grow.espd.criteria.enums.SelectionCriterion
import eu.europa.ec.grow.espd.domain.AvailableElectronically
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.TechnicalProfessionalCriterion
import eu.europa.ec.grow.espd.xml.base.AbstractSelectionCriteriaFixture
/**
 * Created by ratoico on 12/9/15 at 1:48 PM.
 */
class TechnicalFacilitiesResponseTest extends AbstractSelectionCriteriaFixture {

    def "19. should contain the 'Technical facilities and measures for ensuring quality' criterion"() {
        given:
        def espd = new EspdDocument(technicalFacilitiesMeasures: new TechnicalProfessionalCriterion(exists: true))

        when:
        def request = parseResponseXml(espd)
        def idx = getCriterionIndex(SelectionCriterion.TECHNICAL_FACILITIES_AND_MEASURES)

        then: "CriterionID element"
        request.Criterion.size() == getResponseNumberOfCriteria()
        checkCriterionId(request, idx, "4bf996d9-439c-40c6-9ab9-980a48cb55a1")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "SELECTION.TECHNICAL_PROFESSIONAL_ABILITY")

        then: "CriterionName element"
        request.Criterion[idx].Name.text() == "Technical facilities and measures for ensuring quality"

        then: "CriterionDescription element"
        request.Criterion[idx].Description.text() == "It uses the following technical facilities and measures for ensuring quality and its study and research facilities are as follows:"

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "58(4)")

        then: "check all the sub groups"
        request.Criterion[idx].RequirementGroup.size() == 2

        then: "main sub group"
        request.Criterion[idx].RequirementGroup[0].ID.text() == "33648fa9-9dc0-4b8e-8058-bfaa0eb08ab2"
        request.Criterion[idx].RequirementGroup[0].RequirementGroup.size() == 0
        request.Criterion[idx].RequirementGroup[0].Requirement.size() == 1

        then: "main sub group requirements"
        def r1_0 = request.Criterion[idx].RequirementGroup[0].Requirement[0]
        checkRequirement(r1_0, "51391308-0bf6-423c-95e2-d5a54aa31fb8", "Please describe them", "DESCRIPTION")

        then: "info available electronically sub group"
        checkInfoAvailableElectronicallyRequirementGroup(request.Criterion[idx].RequirementGroup[1])
    }

    def "check the 'Please describe them' requirements response"() {
        given:
        def espd = new EspdDocument(technicalFacilitiesMeasures: new TechnicalProfessionalCriterion(exists: true,
                description: "technical description"))

        when:
        def request = parseResponseXml(espd)
        def idx = getCriterionIndex(SelectionCriterion.TECHNICAL_FACILITIES_AND_MEASURES)

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[0]

        def req = subGroup.Requirement[0]
        req.Response.size() == 1
        req.Response[0].Description.text() == "technical description"
    }

    def "check the 'Is this information available electronically' requirement response"() {
        given:
        def espd = new EspdDocument(technicalFacilitiesMeasures: new TechnicalProfessionalCriterion(exists: true,
                availableElectronically: new AvailableElectronically(exists: false)))

        when:
        def request = parseResponseXml(espd)
        def idx = getCriterionIndex(SelectionCriterion.TECHNICAL_FACILITIES_AND_MEASURES)

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[1]

        def req = subGroup.Requirement[0]
        req.Response.size() == 1
        req.Response[0].Indicator.text() == "false"
    }

    def "check the 'Info electronically URL' requirement response"() {
        given:
        def espd = new EspdDocument(technicalFacilitiesMeasures: new TechnicalProfessionalCriterion(exists: true,
                availableElectronically: new AvailableElectronically(exists: true, url: "http://hodor_18.com")))

        when:
        def request = parseResponseXml(espd)
        def idx = getCriterionIndex(SelectionCriterion.TECHNICAL_FACILITIES_AND_MEASURES)

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[1]

        def req = subGroup.Requirement[1]
        req.Response.size() == 1
        checkEvidence(req.Response[0].Evidence, "http://hodor_18.com")
    }

    def "check the 'Info electronically code' requirement response"() {
        given:
        def espd = new EspdDocument(technicalFacilitiesMeasures: new TechnicalProfessionalCriterion(exists: true,
                availableElectronically: new AvailableElectronically(exists: true, code: "HODOR_18")))

        when:
        def request = parseResponseXml(espd)
        def idx = getCriterionIndex(SelectionCriterion.TECHNICAL_FACILITIES_AND_MEASURES)

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[1]

        def req = subGroup.Requirement[2]
        req.Response.size() == 1
        req.Response[0].Code.text() == "HODOR_18"
    }

}