package eu.europa.ec.grow.espd.xml.response.selection

import eu.europa.ec.grow.espd.domain.AvailableElectronically
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.TechnicalProfessionalCriterion
import eu.europa.ec.grow.espd.xml.LocalDateAdapter
import eu.europa.ec.grow.espd.xml.base.AbstractSelectionCriteriaFixture
import org.joda.time.LocalDate
/**
 * Created by ratoico on 12/9/15 at 1:48 PM.
 */
class WorkContractsPerformanceWorksResponseTest extends AbstractSelectionCriteriaFixture {

    def "13. should contain the 'For works contracts: performance of works of the specified type' criterion"() {
        given:
        def espd = new EspdDocument(workContractsPerformanceOfWorks: new TechnicalProfessionalCriterion(exists: true))

        when:
        def request = parseResponseXml(espd)
        def idx = 0

        then: "CriterionID element"
        request.Criterion.size() == 1
        checkCriterionId(request, idx, "cdd3bb3e-34a5-43d5-b668-2aab86a73822")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "SELECTION.TECHNICAL_PROFESSIONAL_ABILITY")

        then: "CriterionName element"
        request.Criterion[idx].Name.text() == "For works contracts: performance of works of the specified type"

        then: "CriterionDescription element"
        request.Criterion[idx].Description.text() == "For public works contracts only: During the reference period, the economic operator has performed the following works of the specified type. Contracting authorities may require up to five years and allow experience dating from more than five years."

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "58(4)")

        then: "check all the sub groups"
        request.Criterion[idx].RequirementGroup.size() == 4

        then: "check description amount date recipients"
        checkDescriptionAmountDateRecipients1Group(request.Criterion[idx].RequirementGroup[0])
        checkDescriptionAmountDateRecipients2Group(request.Criterion[idx].RequirementGroup[1])
        checkDescriptionAmountDateRecipients3Group(request.Criterion[idx].RequirementGroup[2])

        then: "info available electronically sub group"
        checkInfoAvailableElectronicallyRequirementGroup(request.Criterion[idx].RequirementGroup[3])
    }

    def "check the 'Description' requirements response"() {
        given:
        def espd = new EspdDocument(workContractsPerformanceOfWorks: new TechnicalProfessionalCriterion(exists: true,
                description1: "desc1", description2: "desc2", description3: "desc3"))

        when:
        def request = parseResponseXml(espd)
        def idx = 0

        then: "First amount"
        def subGroup1 = request.Criterion[idx].RequirementGroup[0]
        def req1 = subGroup1.Requirement[0]
        req1.Response.size() == 1
        req1.Response[0].Description.text() == "desc1"

        then: "Second amount"
        def subGroup2 = request.Criterion[idx].RequirementGroup[1]
        def req2 = subGroup2.Requirement[0]
        req2.Response.size() == 1
        req2.Response[0].Description.text() == "desc2"

        then: "Third amount"
        def subGroup3 = request.Criterion[idx].RequirementGroup[2]
        def req3 = subGroup3.Requirement[0]
        req3.Response.size() == 1
        req3.Response[0].Description.text() == "desc3"
    }

    def "check the 'Amount' requirements response"() {
        given:
        def espd = new EspdDocument(workContractsPerformanceOfWorks: new TechnicalProfessionalCriterion(exists: true,
                amount1: 11.11, amount2: 22.22, amount3: 33.33, currency1: "EUR", currency2: "RON", currency3: "USD"))

        when:
        def request = parseResponseXml(espd)
        def idx = 0

        then: "First amount"
        def subGroup1 = request.Criterion[idx].RequirementGroup[0]
        def req1 = subGroup1.Requirement[1]
        req1.Response.size() == 1
        req1.Response.Amount.text() == "11.11"
        req1.Response.Amount.@currencyID.text() == "EUR"

        then: "Second amount"
        def subGroup2 = request.Criterion[idx].RequirementGroup[1]
        def req2 = subGroup2.Requirement[1]
        req2.Response.size() == 1
        req2.Response.Amount.text() == "22.22"
        req2.Response.Amount.@currencyID.text() == "RON"

        then: "Third amount"
        def subGroup3 = request.Criterion[idx].RequirementGroup[2]
        def req3 = subGroup3.Requirement[1]
        req3.Response.size() == 1
        req3.Response.Amount.text() == "33.33"
        req3.Response.Amount.@currencyID == "USD"
    }

    def "check the 'Date' requirements response"() {
        given:
        def date1 = new Date()
        def date2 = new Date().plus(1)
        def date3 = new Date().plus(2)
        def espd = new EspdDocument(workContractsPerformanceOfWorks: new TechnicalProfessionalCriterion(exists: true,
                date1: date1, date2: date2, date3: date3))

        when:
        def request = parseResponseXml(espd)
        def idx = 0

        then: "First amount"
        def subGroup1 = request.Criterion[idx].RequirementGroup[0]
        def req1 = subGroup1.Requirement[2]
        req1.Response.size() == 1
        req1.Response[0].Date.text() == LocalDateAdapter.marshal(new LocalDate(date1.time))

        then: "Second amount"
        def subGroup2 = request.Criterion[idx].RequirementGroup[1]
        def req2 = subGroup2.Requirement[2]
        req2.Response.size() == 1
        req2.Response[0].Date.text() == LocalDateAdapter.marshal(new LocalDate(date2.time))

        then: "Third amount"
        def subGroup3 = request.Criterion[idx].RequirementGroup[2]
        def req3 = subGroup3.Requirement[2]
        req3.Response.size() == 1
        req3.Response[0].Date.text() == LocalDateAdapter.marshal(new LocalDate(date3.time))
    }

    def "check the 'Recipients' requirements response"() {
        given:
        def espd = new EspdDocument(workContractsPerformanceOfWorks: new TechnicalProfessionalCriterion(exists: true,
                recipients1: "rec1", recipients2: "rec2", recipients3: "rec3"))

        when:
        def request = parseResponseXml(espd)
        def idx = 0

        then: "First amount"
        def subGroup1 = request.Criterion[idx].RequirementGroup[0]
        def req1 = subGroup1.Requirement[3]
        req1.Response.size() == 1
        req1.Response[0].Description.text() == "rec1"

        then: "Second amount"
        def subGroup2 = request.Criterion[idx].RequirementGroup[1]
        def req2 = subGroup2.Requirement[3]
        req2.Response.size() == 1
        req2.Response[0].Description.text() == "rec2"

        then: "Third amount"
        def subGroup3 = request.Criterion[idx].RequirementGroup[2]
        def req3 = subGroup3.Requirement[3]
        req3.Response.size() == 1
        req3.Response[0].Description.text() == "rec3"
    }

    def "check the 'Is this information available electronically' requirement response"() {
        given:
        def espd = new EspdDocument(workContractsPerformanceOfWorks: new TechnicalProfessionalCriterion(exists: true,
                availableElectronically: new AvailableElectronically(exists: false)))

        when:
        def request = parseResponseXml(espd)
        def idx = 0

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[3]

        def req = subGroup.Requirement[0]
        req.Response.size() == 1
        req.Response[0].Indicator.text() == "false"
    }

    def "check the 'Info electronically URL' requirement response"() {
        given:
        def espd = new EspdDocument(workContractsPerformanceOfWorks: new TechnicalProfessionalCriterion(exists: true,
                availableElectronically: new AvailableElectronically(exists: true, url: "http://hodor_13.com")))

        when:
        def request = parseResponseXml(espd)
        def idx = 0

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[3]

        def req = subGroup.Requirement[1]
        req.Response.size() == 1
        req.Response[0].Evidence.EvidenceDocumentReference.Attachment.ExternalReference.URI.text() == "http://hodor_13.com"
    }

    def "check the 'Info electronically code' requirement response"() {
        given:
        def espd = new EspdDocument(workContractsPerformanceOfWorks: new TechnicalProfessionalCriterion(exists: true,
                availableElectronically: new AvailableElectronically(exists: true, code: "HODOR_13")))

        when:
        def request = parseResponseXml(espd)
        def idx = 0

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[3]

        def req = subGroup.Requirement[2]
        req.Response.size() == 1
        req.Response[0].Code.text() == "HODOR_13"
    }

}