/*
 *
 * Copyright 2016 EUROPEAN COMMISSION
 *
 * Licensed under the EUPL, Version 1.1 or – as soon they
 * will be approved by the European Commission - subsequent
 * versions of the EUPL (the "Licence");
 *
 * You may not use this work except in compliance with the Licence.
 *
 * You may obtain a copy of the Licence at:
 *
 * https://joinup.ec.europa.eu/community/eupl/og_page/eupl
 *
 * Unless required by applicable law or agreed to in
 * writing, software distributed under the Licence is
 * distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied.
 * See the Licence for the specific language governing
 * permissions and limitations under the Licence.
 *
 */

package eu.europa.ec.grow.espd.xml.response.selection

import eu.europa.ec.grow.espd.domain.DynamicRequirementGroup
import eu.europa.ec.grow.espd.domain.enums.criteria.SelectionCriterion
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

    def "16. should contain the 'For service contracts: performance of services of the specified type' criterion"() {
        given:
        def espd = new EspdDocument(serviceContractsPerformanceServices: new TechnicalProfessionalCriterion(exists: true))

        when:
        def response = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.SERVICE_CONTRACTS_PERFORMANCE_OF_SERVICES)

        then: "CriterionID element"
        checkCriterionId(response, idx, "5e506c16-26ab-4e32-bb78-b27f87dc0565")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(response, idx, "CRITERION.SELECTION.TECHNICAL_PROFESSIONAL_ABILITY.REFERENCES.SERVICES_DELIVERY_PERFORMANCE")

        then: "CriterionName element"
        response.Criterion[idx].Name.text() == "For service contracts: performance of services of the specified type"

        then: "CriterionDescription element"
        response.Criterion[idx].Description.text() == "For public service contracts only: During the reference period, the economic operator has provided the following main services of the type specified. Contracting authorities may require up to three years and allow experience dating from more than three years."

        then: "CriterionLegislationReference element"
        checkLegislationReference(response, idx, "58(4)")

        then: "check all the sub groups"
        response.Criterion[idx].RequirementGroup.size() == 2

        then: "check description amount date recipients"
        checkDescriptionAmountDateRecipientsGroup1(response.Criterion[idx].RequirementGroup[0])

        then: "info available electronically sub group"
        checkInfoAvailableElectronicallyRequirementGroup(response.Criterion[idx].RequirementGroup[1])
    }

    def "check the 'Description' requirements response"() {
        given:
        def espd = new EspdDocument(serviceContractsPerformanceServices: new TechnicalProfessionalCriterion(exists: true,
                unboundedGroups: [new DynamicRequirementGroup("description": "desc1"), new DynamicRequirementGroup("description": "desc2"),
                                  new DynamicRequirementGroup("description": "desc3"), new DynamicRequirementGroup("description": "desc4"),
                                  new DynamicRequirementGroup("description": "desc5"), new DynamicRequirementGroup("description": "desc6")]))

        when:
        def response = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.SERVICE_CONTRACTS_PERFORMANCE_OF_SERVICES)

        then: "First description"
        def subGroup1 = response.Criterion[idx].RequirementGroup[0]
        def req1 = subGroup1.Requirement[0]
        req1.Response.size() == 1
        req1.Response[0].Description.text() == "desc1"

        then: "Second description"
        def subGroup2 = response.Criterion[idx].RequirementGroup[1]
        def req2 = subGroup2.Requirement[0]
        req2.Response.size() == 1
        req2.Response[0].Description.text() == "desc2"

        then: "Third description"
        def subGroup3 = response.Criterion[idx].RequirementGroup[2]
        def req3 = subGroup3.Requirement[0]
        req3.Response.size() == 1
        req3.Response[0].Description.text() == "desc3"

        then: "Fourth description"
        def subGroup4 = response.Criterion[idx].RequirementGroup[3]
        def req4 = subGroup4.Requirement[0]
        req4.Response.size() == 1
        req4.Response[0].Description.text() == "desc4"

        then: "Fifth description"
        def subGroup5 = response.Criterion[idx].RequirementGroup[4]
        def req5 = subGroup5.Requirement[0]
        req5.Response.size() == 1
        req5.Response[0].Description.text() == "desc5"
    }

    def "check the 'Amount' requirements response"() {
        given:
        def espd = new EspdDocument(serviceContractsPerformanceServices: new TechnicalProfessionalCriterion(exists: true,
                unboundedGroups: [new DynamicRequirementGroup("amount": 11.11, "currency": "EUR"),
                                  new DynamicRequirementGroup("amount": 22.22, "currency": "RON"),
                                  new DynamicRequirementGroup("amount": 33.33, "currency": "USD"),
                                  new DynamicRequirementGroup("amount": 44.44, "currency": "CHF"),
                                  new DynamicRequirementGroup("amount": 55.55, "currency": "YEN")]))

        when:
        def response = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.SERVICE_CONTRACTS_PERFORMANCE_OF_SERVICES)

        then: "First amount"
        def subGroup1 = response.Criterion[idx].RequirementGroup[0]
        def req1 = subGroup1.Requirement[1]
        req1.Response.size() == 1
        req1.Response.Amount.text() == "11.11"
        req1.Response.Amount.@currencyID.text() == "EUR"

        then: "Second amount"
        def subGroup2 = response.Criterion[idx].RequirementGroup[1]
        def req2 = subGroup2.Requirement[1]
        req2.Response.size() == 1
        req2.Response.Amount.text() == "22.22"
        req2.Response.Amount.@currencyID.text() == "RON"

        then: "Third amount"
        def subGroup3 = response.Criterion[idx].RequirementGroup[2]
        def req3 = subGroup3.Requirement[1]
        req3.Response.size() == 1
        req3.Response.Amount.text() == "33.33"
        req3.Response.Amount.@currencyID == "USD"

        then: "Fourth amount"
        def subGroup4 = response.Criterion[idx].RequirementGroup[3]
        def req4 = subGroup4.Requirement[1]
        req4.Response.size() == 1
        req4.Response.Amount.text() == "44.44"
        req4.Response.Amount.@currencyID == "CHF"

        then: "Fifth amount"
        def subGroup5 = response.Criterion[idx].RequirementGroup[4]
        def req5 = subGroup5.Requirement[1]
        req5.Response.size() == 1
        req5.Response.Amount.text() == "55.55"
        req5.Response.Amount.@currencyID == "YEN"
    }

    def "check the 'Start Date' requirements response"() {
        given:
        def date1 = new Date()
        def date2 = new Date().plus(1)
        def espd = new EspdDocument(serviceContractsPerformanceServices: new TechnicalProfessionalCriterion(exists: true,
                unboundedGroups: [new DynamicRequirementGroup("startDate": date1),
                                  new DynamicRequirementGroup("startDate": date2)]))

        when:
        def response = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.SERVICE_CONTRACTS_PERFORMANCE_OF_SERVICES)

        then: "First date"
        def subGroup1 = response.Criterion[idx].RequirementGroup[0]
        def req1 = subGroup1.Requirement[2]
        req1.Response.size() == 1
        req1.Response[0].Date.text() == LocalDateAdapter.marshal(new LocalDate(date1.time))

        then: "Second date"
        def subGroup2 = response.Criterion[idx].RequirementGroup[1]
        def req2 = subGroup2.Requirement[2]
        req2.Response.size() == 1
        req2.Response[0].Date.text() == LocalDateAdapter.marshal(new LocalDate(date2.time))
    }

    def "check the 'End Date' requirements response"() {
        given:
        def date1 = new Date()
        def date2 = new Date().plus(1)
        def espd = new EspdDocument(serviceContractsPerformanceServices: new TechnicalProfessionalCriterion(exists: true,
                unboundedGroups: [new DynamicRequirementGroup("endDate": date1),
                                  new DynamicRequirementGroup("endDate": date2)]))

        when:
        def response = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.SERVICE_CONTRACTS_PERFORMANCE_OF_SERVICES)

        then: "First date"
        def subGroup1 = response.Criterion[idx].RequirementGroup[0]
        def req1 = subGroup1.Requirement[3]
        req1.Response.size() == 1
        req1.Response[0].Date.text() == LocalDateAdapter.marshal(new LocalDate(date1.time))

        then: "Second date"
        def subGroup2 = response.Criterion[idx].RequirementGroup[1]
        def req2 = subGroup2.Requirement[3]
        req2.Response.size() == 1
        req2.Response[0].Date.text() == LocalDateAdapter.marshal(new LocalDate(date2.time))
    }

    def "check the 'Recipients' requirements response"() {
        given:
        def espd = new EspdDocument(serviceContractsPerformanceServices: new TechnicalProfessionalCriterion(exists: true,
                unboundedGroups: [new DynamicRequirementGroup("recipients": "rec1"),
                                  new DynamicRequirementGroup("recipients": "rec2")]))

        when:
        def response = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.SERVICE_CONTRACTS_PERFORMANCE_OF_SERVICES)

        then: "First recipients"
        def subGroup1 = response.Criterion[idx].RequirementGroup[0]
        def req1 = subGroup1.Requirement[4]
        req1.Response.size() == 1
        req1.Response[0].Description.text() == "rec1"

        then: "Second recipients"
        def subGroup2 = response.Criterion[idx].RequirementGroup[1]
        def req2 = subGroup2.Requirement[4]
        req2.Response.size() == 1
        req2.Response[0].Description.text() == "rec2"
    }

    def "check the 'Is this information available electronically' requirement response"() {
        given:
        def espd = new EspdDocument(serviceContractsPerformanceServices: new TechnicalProfessionalCriterion(exists: true,
                availableElectronically: new AvailableElectronically(answer: false)))

        when:
        def response = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.SERVICE_CONTRACTS_PERFORMANCE_OF_SERVICES)

        then:
        def subGroup = response.Criterion[idx].RequirementGroup[1]

        def req = subGroup.Requirement[0]
        req.Response.size() == 1
        req.Response[0].Indicator.text() == "false"
    }

    def "check the 'Info electronically URL' requirement response"() {
        given:
        def espd = new EspdDocument(serviceContractsPerformanceServices: new TechnicalProfessionalCriterion(exists: true,
                availableElectronically: new AvailableElectronically(answer: true, url: "http://hodor_15.com")))

        when:
        def response = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.SERVICE_CONTRACTS_PERFORMANCE_OF_SERVICES)

        then:
        def subGroup = response.Criterion[idx].RequirementGroup[1].RequirementGroup[0]

        def req = subGroup.Requirement[0]
        req.Response.size() == 1
        checkEvidence(req.Response[0].Evidence, "http://hodor_15.com")
    }

    def "check the 'Info electronically code' requirement response"() {
        given:
        def espd = new EspdDocument(serviceContractsPerformanceServices: new TechnicalProfessionalCriterion(exists: true,
                availableElectronically: new AvailableElectronically(answer: true, code: "HODOR_15")))

        when:
        def response = parseResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.SERVICE_CONTRACTS_PERFORMANCE_OF_SERVICES)

        then:
        def subGroup = response.Criterion[idx].RequirementGroup[1].RequirementGroup[0]

        def req = subGroup.Requirement[1]
        req.Response.size() == 1
        req.Response[0].Code.text() == "HODOR_15"
    }

}