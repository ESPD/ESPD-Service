package eu.europa.ec.grow.espd.xml.response.selection

import eu.europa.ec.grow.espd.criteria.enums.SelectionCriterion
import eu.europa.ec.grow.espd.domain.AvailableElectronically
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.TechnicalProfessionalCriterion
import eu.europa.ec.grow.espd.xml.LocalDateAdapter
import eu.europa.ec.grow.espd.xml.base.AbstractSelectionCriteriaFixture
import org.joda.time.LocalDate
/**
 * Created by ratoico on 12/9/15 at 1:48 PM.
 */
class ServiceContractsPerformanceServicesResponseTest extends AbstractSelectionCriteriaFixture {

    def "15. should contain the 'For service contracts: performance of services of the specified type' criterion"() {
        given:
        def espd = new EspdDocument(serviceContractsPerformanceServices: new TechnicalProfessionalCriterion(exists: true))

        when:
        def request = parseResponseXml(espd)
        def idx = getCriterionIndex(SelectionCriterion.SERVICE_CONTRACTS_PERFORMANCE_OF_SERVICES)

        then: "CriterionID element"
        request.Criterion.size() == getResponseNumberOfCriteria()
        checkCriterionId(request, idx, "5e506c16-26ab-4e32-bb78-b27f87dc0565")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "SELECTION.TECHNICAL_PROFESSIONAL_ABILITY")

        then: "CriterionName element"
        request.Criterion[idx].Name.text() == "For service contracts: performance of services of the specified type"

        then: "CriterionDescription element"
        request.Criterion[idx].Description.text() == "For public service contracts only: During the reference period, the economic operator has provided the following main services of the type specified. Contracting authorities may require up to three years and allow experience dating from more than three years."

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "58(4)")

        then: "check all the sub groups"
        request.Criterion[idx].RequirementGroup.size() == 6

        then: "check description amount date recipients"
        checkDescriptionAmountDateRecipientsGroup1(request.Criterion[idx].RequirementGroup[0])
        checkDescriptionAmountDateRecipientsGroup2(request.Criterion[idx].RequirementGroup[1])
        checkDescriptionAmountDateRecipientsGroup3(request.Criterion[idx].RequirementGroup[2])
        checkDescriptionAmountDateRecipientsGroup4(request.Criterion[idx].RequirementGroup[3])
        checkDescriptionAmountDateRecipientsGroup5(request.Criterion[idx].RequirementGroup[4])

        then: "info available electronically sub group"
        checkInfoAvailableElectronicallyRequirementGroup(request.Criterion[idx].RequirementGroup[5])
    }

    def "check the 'Description' requirements response"() {
        given:
        def espd = new EspdDocument(serviceContractsPerformanceServices: new TechnicalProfessionalCriterion(exists: true,
                description1: "desc1", description2: "desc2", description3: "desc3", description4: "desc4", description5: "desc5"))

        when:
        def request = parseResponseXml(espd)
        def idx = getCriterionIndex(SelectionCriterion.SERVICE_CONTRACTS_PERFORMANCE_OF_SERVICES)

        then: "First description"
        def subGroup1 = request.Criterion[idx].RequirementGroup[0]
        def req1 = subGroup1.Requirement[0]
        req1.Response.size() == 1
        req1.Response[0].Description.text() == "desc1"

        then: "Second description"
        def subGroup2 = request.Criterion[idx].RequirementGroup[1]
        def req2 = subGroup2.Requirement[0]
        req2.Response.size() == 1
        req2.Response[0].Description.text() == "desc2"

        then: "Third description"
        def subGroup3 = request.Criterion[idx].RequirementGroup[2]
        def req3 = subGroup3.Requirement[0]
        req3.Response.size() == 1
        req3.Response[0].Description.text() == "desc3"

        then: "Fourth description"
        def subGroup4 = request.Criterion[idx].RequirementGroup[3]
        def req4 = subGroup4.Requirement[0]
        req4.Response.size() == 1
        req4.Response[0].Description.text() == "desc4"

        then: "Fifth description"
        def subGroup5 = request.Criterion[idx].RequirementGroup[4]
        def req5 = subGroup5.Requirement[0]
        req5.Response.size() == 1
        req5.Response[0].Description.text() == "desc5"
    }

    def "check the 'Amount' requirements response"() {
        given:
        def espd = new EspdDocument(serviceContractsPerformanceServices: new TechnicalProfessionalCriterion(exists: true,
                amount1: 11.11, amount2: 22.22, amount3: 33.33, amount4: 44.44, amount5: 55.55,
                currency1: "EUR", currency2: "RON", currency3: "USD", currency4: "CHF", currency5: "YEN"))

        when:
        def request = parseResponseXml(espd)
        def idx = getCriterionIndex(SelectionCriterion.SERVICE_CONTRACTS_PERFORMANCE_OF_SERVICES)

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

        then: "Fourth amount"
        def subGroup4 = request.Criterion[idx].RequirementGroup[3]
        def req4 = subGroup4.Requirement[1]
        req4.Response.size() == 1
        req4.Response.Amount.text() == "44.44"
        req4.Response.Amount.@currencyID == "CHF"

        then: "Fifth amount"
        def subGroup5 = request.Criterion[idx].RequirementGroup[4]
        def req5 = subGroup5.Requirement[1]
        req5.Response.size() == 1
        req5.Response.Amount.text() == "55.55"
        req5.Response.Amount.@currencyID == "YEN"
    }

    def "check the 'Date' requirements response"() {
        given:
        def date1 = new Date()
        def date2 = new Date().plus(1)
        def date3 = new Date().plus(2)
        def date4 = new Date().plus(3)
        def date5 = new Date().plus(4)
        def espd = new EspdDocument(serviceContractsPerformanceServices: new TechnicalProfessionalCriterion(exists: true,
                date1: date1, date2: date2, date3: date3, date4: date4, date5: date5))

        when:
        def request = parseResponseXml(espd)
        def idx = getCriterionIndex(SelectionCriterion.SERVICE_CONTRACTS_PERFORMANCE_OF_SERVICES)

        then: "First date"
        def subGroup1 = request.Criterion[idx].RequirementGroup[0]
        def req1 = subGroup1.Requirement[2]
        req1.Response.size() == 1
        req1.Response[0].Date.text() == LocalDateAdapter.marshal(new LocalDate(date1.time))

        then: "Second date"
        def subGroup2 = request.Criterion[idx].RequirementGroup[1]
        def req2 = subGroup2.Requirement[2]
        req2.Response.size() == 1
        req2.Response[0].Date.text() == LocalDateAdapter.marshal(new LocalDate(date2.time))

        then: "Third date"
        def subGroup3 = request.Criterion[idx].RequirementGroup[2]
        def req3 = subGroup3.Requirement[2]
        req3.Response.size() == 1
        req3.Response[0].Date.text() == LocalDateAdapter.marshal(new LocalDate(date3.time))

        then: "Fourth date"
        def subGroup4 = request.Criterion[idx].RequirementGroup[3]
        def req4 = subGroup4.Requirement[2]
        req4.Response.size() == 1
        req4.Response[0].Date.text() == LocalDateAdapter.marshal(new LocalDate(date4.time))

        then: "Fifth date"
        def subGroup5 = request.Criterion[idx].RequirementGroup[4]
        def req5 = subGroup5.Requirement[2]
        req5.Response.size() == 1
        req5.Response[0].Date.text() == LocalDateAdapter.marshal(new LocalDate(date5.time))
    }

    def "check the 'Recipients' requirements response"() {
        given:
        def espd = new EspdDocument(serviceContractsPerformanceServices: new TechnicalProfessionalCriterion(exists: true,
                recipients1: "rec1", recipients2: "rec2", recipients3: "rec3", recipients4: "rec4", recipients5: "rec5"))

        when:
        def request = parseResponseXml(espd)
        def idx = getCriterionIndex(SelectionCriterion.SERVICE_CONTRACTS_PERFORMANCE_OF_SERVICES)

        then: "First recipients"
        def subGroup1 = request.Criterion[idx].RequirementGroup[0]
        def req1 = subGroup1.Requirement[3]
        req1.Response.size() == 1
        req1.Response[0].Description.text() == "rec1"

        then: "Second recipients"
        def subGroup2 = request.Criterion[idx].RequirementGroup[1]
        def req2 = subGroup2.Requirement[3]
        req2.Response.size() == 1
        req2.Response[0].Description.text() == "rec2"

        then: "Third recipients"
        def subGroup3 = request.Criterion[idx].RequirementGroup[2]
        def req3 = subGroup3.Requirement[3]
        req3.Response.size() == 1
        req3.Response[0].Description.text() == "rec3"

        then: "Fourth recipients"
        def subGroup4 = request.Criterion[idx].RequirementGroup[3]
        def req4 = subGroup4.Requirement[3]
        req4.Response.size() == 1
        req4.Response[0].Description.text() == "rec4"

        then: "Fifth recipients"
        def subGroup5 = request.Criterion[idx].RequirementGroup[4]
        def req5 = subGroup5.Requirement[3]
        req5.Response.size() == 1
        req5.Response[0].Description.text() == "rec5"
    }

    def "check the 'Is this information available electronically' requirement response"() {
        given:
        def espd = new EspdDocument(serviceContractsPerformanceServices: new TechnicalProfessionalCriterion(exists: true,
                availableElectronically: new AvailableElectronically(exists: false)))

        when:
        def request = parseResponseXml(espd)
        def idx = getCriterionIndex(SelectionCriterion.SERVICE_CONTRACTS_PERFORMANCE_OF_SERVICES)

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[5]

        def req = subGroup.Requirement[0]
        req.Response.size() == 1
        req.Response[0].Indicator.text() == "false"
    }

    def "check the 'Info electronically URL' requirement response"() {
        given:
        def espd = new EspdDocument(serviceContractsPerformanceServices: new TechnicalProfessionalCriterion(exists: true,
                availableElectronically: new AvailableElectronically(exists: true, url: "http://hodor_15.com")))

        when:
        def request = parseResponseXml(espd)
        def idx = getCriterionIndex(SelectionCriterion.SERVICE_CONTRACTS_PERFORMANCE_OF_SERVICES)

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[5]

        def req = subGroup.Requirement[1]
        req.Response.size() == 1
        req.Response[0].Evidence.EvidenceDocumentReference.Attachment.ExternalReference.URI.text() == "http://hodor_15.com"
    }

    def "check the 'Info electronically code' requirement response"() {
        given:
        def espd = new EspdDocument(serviceContractsPerformanceServices: new TechnicalProfessionalCriterion(exists: true,
                availableElectronically: new AvailableElectronically(exists: true, code: "HODOR_15")))

        when:
        def request = parseResponseXml(espd)
        def idx = getCriterionIndex(SelectionCriterion.SERVICE_CONTRACTS_PERFORMANCE_OF_SERVICES)

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[5]

        def req = subGroup.Requirement[2]
        req.Response.size() == 1
        req.Response[0].Code.text() == "HODOR_15"
    }

}