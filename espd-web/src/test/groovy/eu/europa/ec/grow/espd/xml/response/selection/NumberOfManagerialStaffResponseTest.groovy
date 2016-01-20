package eu.europa.ec.grow.espd.xml.response.selection

import eu.europa.ec.grow.espd.criteria.enums.SelectionCriterion
import eu.europa.ec.grow.espd.domain.AvailableElectronically
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.TechnicalProfessionalCriterion
import eu.europa.ec.grow.espd.xml.base.AbstractSelectionCriteriaFixture
/**
 * Created by ratoico on 12/9/15 at 1:48 PM.
 */
class NumberOfManagerialStaffResponseTest extends AbstractSelectionCriteriaFixture {

    def "24. should contain the 'Number of managerial staff' criterion"() {
        given:
        def espd = new EspdDocument(numberManagerialStaff: new TechnicalProfessionalCriterion(exists: true))

        when:
        def request = parseResponseXml(espd)
        def idx = getCriterionIndex(SelectionCriterion.NUMBER_OF_MANAGERIAL_STAFF)

        then: "CriterionID element"
        request.Criterion.size() == getResponseNumberOfCriteria()
        checkCriterionId(request, idx, "6346959b-e097-4ea1-89cd-d1b4c131ea4d")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "SELECTION.TECHNICAL_PROFESSIONAL_ABILITY")

        then: "CriterionName element"
        request.Criterion[idx].Name.text() == "Number of managerial staff"

        then: "CriterionDescription element"
        request.Criterion[idx].Description.text() == "The economic operator’s number of managerial staff for the last three years were as follows:"

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "58(4)")

        then: "check all the sub groups"
        request.Criterion[idx].RequirementGroup.size() == 4

        then:
        checkYearNumberGroup1(request.Criterion[idx].RequirementGroup[0])
        checkYearNumberGroup2(request.Criterion[idx].RequirementGroup[1])
        checkYearNumberGroup3(request.Criterion[idx].RequirementGroup[2])

        then: "info available electronically sub group"
        checkInfoAvailableElectronicallyRequirementGroup(request.Criterion[idx].RequirementGroup[3])
    }

    def "check the 'Year' requirements response"() {
        given:
        def espd = new EspdDocument(numberManagerialStaff: new TechnicalProfessionalCriterion(exists: true,
                year1: 2016, year2: 2015, year3: 2014))

        when:
        def request = parseResponseXml(espd)
        def idx = getCriterionIndex(SelectionCriterion.NUMBER_OF_MANAGERIAL_STAFF)

        then: "First year"
        def subGroup1 = request.Criterion[idx].RequirementGroup[0]
        def req1 = subGroup1.Requirement[0]
        req1.Response.size() == 1
        req1.Response[0].Quantity.text() == "2016"
        req1.Response[0].Quantity.@unitCode.text() == "YEAR"

        then: "Second year"
        def subGroup2 = request.Criterion[idx].RequirementGroup[1]
        def req2 = subGroup2.Requirement[0]
        req2.Response.size() == 1
        req2.Response[0].Quantity.text() == "2015"
        req2.Response[0].Quantity.@unitCode.text() == "YEAR"

        then: "Third year"
        def subGroup3 = request.Criterion[idx].RequirementGroup[2]
        def req3 = subGroup3.Requirement[0]
        req3.Response.size() == 1
        req3.Response[0].Quantity.text() == "2014"
        req3.Response[0].Quantity.@unitCode.text() == "YEAR"
    }

    def "check the 'Number' requirements response"() {
        given:
        def espd = new EspdDocument(numberManagerialStaff: new TechnicalProfessionalCriterion(exists: true,
                number1: 11, number2: 22, number3: 33))

        when:
        def request = parseResponseXml(espd)
        def idx = getCriterionIndex(SelectionCriterion.NUMBER_OF_MANAGERIAL_STAFF)

        then: "First number"
        def subGroup1 = request.Criterion[idx].RequirementGroup[0]
        def req1 = subGroup1.Requirement[1]
        req1.Response.size() == 1
        req1.Response[0].Quantity.text() == "11"
        req1.Response[0].Quantity.@unitCode.text() == "NUMBER"

        then: "Second number"
        def subGroup2 = request.Criterion[idx].RequirementGroup[1]
        def req2 = subGroup2.Requirement[1]
        req2.Response.size() == 1
        req2.Response[0].Quantity.text() == "22"
        req2.Response[0].Quantity.@unitCode.text() == "NUMBER"

        then: "Third number"
        def subGroup3 = request.Criterion[idx].RequirementGroup[2]
        def req3 = subGroup3.Requirement[1]
        req3.Response.size() == 1
        req3.Response[0].Quantity.text() == "33"
        req3.Response[0].Quantity.@unitCode.text() == "NUMBER"
    }

    def "check the 'Is this information available electronically' requirement response"() {
        given:
        def espd = new EspdDocument(numberManagerialStaff: new TechnicalProfessionalCriterion(exists: true,
                availableElectronically: new AvailableElectronically(exists: false)))

        when:
        def request = parseResponseXml(espd)
        def idx = getCriterionIndex(SelectionCriterion.NUMBER_OF_MANAGERIAL_STAFF)

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[3]

        def req = subGroup.Requirement[0]
        req.Response.size() == 1
        req.Response[0].Indicator.text() == "false"
    }

    def "check the 'Info electronically URL' requirement response"() {
        given:
        def espd = new EspdDocument(numberManagerialStaff: new TechnicalProfessionalCriterion(exists: true,
                availableElectronically: new AvailableElectronically(exists: true, url: "http://hodor_24.com")))

        when:
        def request = parseResponseXml(espd)
        def idx = getCriterionIndex(SelectionCriterion.NUMBER_OF_MANAGERIAL_STAFF)

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[3]

        def req = subGroup.Requirement[1]
        req.Response.size() == 1
        req.Response[0].Evidence.EvidenceDocumentReference.Attachment.ExternalReference.URI.text() == "http://hodor_24.com"
    }

    def "check the 'Info electronically code' requirement response"() {
        given:
        def espd = new EspdDocument(numberManagerialStaff: new TechnicalProfessionalCriterion(exists: true,
                availableElectronically: new AvailableElectronically(exists: true, code: "HODOR_24")))

        when:
        def request = parseResponseXml(espd)
        def idx = getCriterionIndex(SelectionCriterion.NUMBER_OF_MANAGERIAL_STAFF)

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[3]

        def req = subGroup.Requirement[2]
        req.Response.size() == 1
        req.Response[0].Code.text() == "HODOR_24"
    }

}