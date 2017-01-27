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

import eu.europa.ec.grow.espd.domain.AvailableElectronically
import eu.europa.ec.grow.espd.domain.DynamicRequirementGroup
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.TechnicalProfessionalCriterion
import eu.europa.ec.grow.espd.domain.enums.criteria.SelectionCriterion
import eu.europa.ec.grow.espd.xml.base.AbstractSelectionCriteriaFixture

/**
 * Created by ratoico on 12/9/15 at 1:48 PM.
 */
class NumberOfManagerialStaffResponseTest extends AbstractSelectionCriteriaFixture {

    def "25. should contain the 'Number of managerial staff' criterion"() {
        given:
        def espd = new EspdDocument(numberManagerialStaff: new TechnicalProfessionalCriterion(exists: true))

        when:
        def response = generateResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.NUMBER_OF_MANAGERIAL_STAFF)

        then: "CriterionID element"
        checkCriterionId(response, idx, "6346959b-e097-4ea1-89cd-d1b4c131ea4d")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(response, idx, "CRITERION.SELECTION.TECHNICAL_PROFESSIONAL_ABILITY.MANAGEMENT.MANAGERIAL_STAFF")

        then: "CriterionName element"
        response.Criterion[idx].Name.text() == "Number of managerial staff"

        then: "CriterionDescription element"
        response.Criterion[idx].Description.text() == "The economic operator’s number of managerial staff for the last three years were as follows:"

        then: "CriterionLegislationReference element"
        checkLegislationReference(response, idx, "58(4)")

        then: "check all the sub groups"
        response.Criterion[idx].RequirementGroup.size() == 2

        then:
        checkYearNumberGroup1(response.Criterion[idx].RequirementGroup[0])

        then: "info available electronically sub group"
        checkInfoAvailableElectronicallyRequirementGroup(response.Criterion[idx].RequirementGroup[1])
    }

    def "check the 'Year' requirements response"() {
        given:
        def espd = new EspdDocument(numberManagerialStaff: new TechnicalProfessionalCriterion(exists: true,
                unboundedGroups: [
                        new DynamicRequirementGroup("year": 2016),
                        new DynamicRequirementGroup("year": 2015),
                        new DynamicRequirementGroup("year": 2014)
                ]))

        when:
        def response = generateResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.NUMBER_OF_MANAGERIAL_STAFF)

        then: "First year"
        def subGroup1 = response.Criterion[idx].RequirementGroup[0]
        def req1 = subGroup1.Requirement[0]
        req1.Response.size() == 1
        req1.Response[0].Quantity.text() == "2016"
        req1.Response[0].Quantity.@unitCode.text() == "YEAR"

        then: "Second year"
        def subGroup2 = response.Criterion[idx].RequirementGroup[1]
        def req2 = subGroup2.Requirement[0]
        req2.Response.size() == 1
        req2.Response[0].Quantity.text() == "2015"
        req2.Response[0].Quantity.@unitCode.text() == "YEAR"

        then: "Third year"
        def subGroup3 = response.Criterion[idx].RequirementGroup[2]
        def req3 = subGroup3.Requirement[0]
        req3.Response.size() == 1
        req3.Response[0].Quantity.text() == "2014"
        req3.Response[0].Quantity.@unitCode.text() == "YEAR"
    }

    def "check the 'Number' requirements response"() {
        given:
        def espd = new EspdDocument(numberManagerialStaff: new TechnicalProfessionalCriterion(exists: true,
                unboundedGroups: [
                        new DynamicRequirementGroup("number": 11),
                        new DynamicRequirementGroup("number": 22),
                        new DynamicRequirementGroup("number": 33)
                ]))

        when:
        def response = generateResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.NUMBER_OF_MANAGERIAL_STAFF)

        then: "First number"
        def subGroup1 = response.Criterion[idx].RequirementGroup[0]
        def req1 = subGroup1.Requirement[1]
        req1.Response.size() == 1
        req1.Response[0].Quantity.text() == "11"
        req1.Response[0].Quantity.@unitCode.text() == "NUMBER"

        then: "Second number"
        def subGroup2 = response.Criterion[idx].RequirementGroup[1]
        def req2 = subGroup2.Requirement[1]
        req2.Response.size() == 1
        req2.Response[0].Quantity.text() == "22"
        req2.Response[0].Quantity.@unitCode.text() == "NUMBER"

        then: "Third number"
        def subGroup3 = response.Criterion[idx].RequirementGroup[2]
        def req3 = subGroup3.Requirement[1]
        req3.Response.size() == 1
        req3.Response[0].Quantity.text() == "33"
        req3.Response[0].Quantity.@unitCode.text() == "NUMBER"
    }

    def "check the 'Is this information available electronically' requirement response"() {
        given:
        def espd = new EspdDocument(numberManagerialStaff: new TechnicalProfessionalCriterion(exists: true,
                availableElectronically: new AvailableElectronically(answer: false)))

        when:
        def response = generateResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.NUMBER_OF_MANAGERIAL_STAFF)

        then:
        def subGroup = response.Criterion[idx].RequirementGroup[1]
        def req = subGroup.Requirement[0]
        req.Response.size() == 1
        req.Response[0].Indicator.text() == "false"
    }

    def "check the 'Info electronically URL' requirement response"() {
        given:
        def espd = new EspdDocument(numberManagerialStaff: new TechnicalProfessionalCriterion(exists: true,
                availableElectronically: new AvailableElectronically(answer: true, url: "http://hodor_24.com")))

        when:
        def response = generateResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.NUMBER_OF_MANAGERIAL_STAFF)

        then:
        def subGroup = response.Criterion[idx].RequirementGroup[1].RequirementGroup[0]
        def req = subGroup.Requirement[0]
        req.Response.size() == 1
        checkEvidence(req.Response[0].Evidence, "http://hodor_24.com")
    }

    def "check the 'Info electronically code' requirement response"() {
        given:
        def espd = new EspdDocument(numberManagerialStaff: new TechnicalProfessionalCriterion(exists: true,
                availableElectronically: new AvailableElectronically(answer: true, code: "HODOR_24")))

        when:
        def response = generateResponseXml(espd)
        def idx = getResponseCriterionIndex(SelectionCriterion.NUMBER_OF_MANAGERIAL_STAFF)

        then:
        def subGroup = response.Criterion[idx].RequirementGroup[1].RequirementGroup[0]
        def req = subGroup.Requirement[1]
        req.Response.size() == 1
        req.Response[0].Code.text() == "HODOR_24"
    }

}