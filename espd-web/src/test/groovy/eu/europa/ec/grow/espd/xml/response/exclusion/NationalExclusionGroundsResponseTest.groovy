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

package eu.europa.ec.grow.espd.xml.response.exclusion

import eu.europa.ec.grow.espd.domain.AvailableElectronically
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.PurelyNationalGrounds
import eu.europa.ec.grow.espd.domain.SelfCleaning
import eu.europa.ec.grow.espd.domain.enums.criteria.ExclusionCriterion
import eu.europa.ec.grow.espd.xml.base.AbstractExclusionCriteriaFixture
/**
 * Created by ratoico on 12/9/15 at 1:28 PM.
 */
class NationalExclusionGroundsResponseTest extends AbstractExclusionCriteriaFixture {

    def "24. should contain the 'Purely national grounds of exclusion' criterion"() {
        given:
        def espd = new EspdDocument(purelyNationalGrounds: new PurelyNationalGrounds(exists: true))

        when:
        def response = generateResponseXml(espd)
        def idx = getResponseCriterionIndex(ExclusionCriterion.NATIONAL_EXCLUSION_GROUNDS)

        then: "CriterionID element"
        checkCriterionId(response, idx, "63adb07d-db1b-4ef0-a14e-a99785cf8cf6")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(response, idx, "CRITERION.EXCLUSION.NATIONAL.OTHER")

        then: "CriterionName element"
        response.Criterion[idx].Name.text() == "Purely national exclusion grounds"

        then: "CriterionDescription element"
        response.Criterion[idx].Description.text() == "Other exclusion grounds that may be foreseen in the national legislation of the contracting authority’s or contracting entity’s Member State. Do the purely national grounds of exclusion, which are specified in the relevant notice or in the procurement documents, apply?"

        then: "CriterionLegislationReference element"
        checkLegislationReference(response, idx, "57(4)")

        then: "check all the sub groups"
        response.Criterion[idx].RequirementGroup.size() == 2

        then: "main sub group"
        def g1 = response.Criterion[idx].RequirementGroup[0]
        g1.ID.text() == "77ae3f29-7c5f-4afa-af97-24afec48c5bf"
        g1.@pi.text() == ""
        g1.RequirementGroup.size() == 1
        g1.Requirement.size() == 1
        checkRequirement(g1.Requirement[0], "974c8196-9d1c-419c-9ca9-45bb9f5fd59a", "Your answer?", "INDICATOR")

        then:
        def g1_1 = g1.RequirementGroup[0]
        g1_1.ID.text() == "73f0fe4c-4ed9-4343-8096-d898cf200146"
        g1_1.@pi.text() == "GROUP_FULFILLED.ON_TRUE"
        g1_1.RequirementGroup.size() == 1
        g1_1.Requirement.size() == 1
        checkRequirement(g1_1.Requirement[0], "e098da8e-4717-4500-965f-f882d5b4e1ad", "Please describe them", "DESCRIPTION")

        then: "check the self-cleaning sub group"
        def g1_1_1 = g1_1.RequirementGroup[0]
        g1_1_1.@pi.text() == ""
        checkSelfCleaningRequirementGroup(g1_1_1)

        then: "check second sub group"
        def g2 = response.Criterion[idx].RequirementGroup[1]
        checkInfoAvailableElectronicallyRequirementGroup(g2)
    }

    def "check the 'Your answer' requirement response"() {
        given:
        def espd = new EspdDocument(purelyNationalGrounds: new PurelyNationalGrounds(exists: true, answer: true))

        when:
        def response = generateResponseXml(espd)
        def idx = getResponseCriterionIndex(ExclusionCriterion.NATIONAL_EXCLUSION_GROUNDS)

        then:
        def req = response.Criterion[idx].RequirementGroup[0].Requirement[0]
        checkRequirement(req, "974c8196-9d1c-419c-9ca9-45bb9f5fd59a", "Your answer?", "INDICATOR")
        req.Response.size() == 1
        req.Response[0].Indicator.text() == "true"
    }

    def "check the 'Please describe them' requirement response"() {
        given:
        def espd = new EspdDocument(purelyNationalGrounds: new PurelyNationalGrounds(exists: true,
                description: "bogus description."))

        when:
        def response = generateResponseXml(espd)
        def idx = getResponseCriterionIndex(ExclusionCriterion.NATIONAL_EXCLUSION_GROUNDS)

        then:
        def subGroup = response.Criterion[idx].RequirementGroup[0].RequirementGroup[0]

        def req = subGroup.Requirement[0]
        checkRequirement(req, "e098da8e-4717-4500-965f-f882d5b4e1ad", "Please describe them", "DESCRIPTION")
        req.Response[0].Description.text() == "bogus description."
    }

    def "check the 'Have you taken measures to demonstrate your reliability (\"Self-Cleaning\")' requirement response"() {
        given:
        def espd = new EspdDocument(purelyNationalGrounds: new PurelyNationalGrounds(exists: true,
                selfCleaning: new SelfCleaning(answer: false)))

        when:
        def response = generateResponseXml(espd)
        def idx = getResponseCriterionIndex(ExclusionCriterion.PARTICIPATION_CRIMINAL_ORGANISATION)

        then:
        def subGroup = response.Criterion[idx].RequirementGroup[0].RequirementGroup[0].RequirementGroup[0]

        def req = subGroup.Requirement[0]
        req.Response.size() == 1
        req.Response[0].Indicator.text() == "false"
    }

    def "check the 'Self cleaning description' requirement response"() {
        given:
        def espd = new EspdDocument(purelyNationalGrounds: new PurelyNationalGrounds(exists: true,
                selfCleaning: new SelfCleaning(description: "Hodor_24 is clean")))

        when:
        def response = generateResponseXml(espd)
        def idx = getResponseCriterionIndex(ExclusionCriterion.PARTICIPATION_CRIMINAL_ORGANISATION)

        then:
        def subGroup = response.Criterion[idx].RequirementGroup[0].RequirementGroup[0].RequirementGroup[0].RequirementGroup[0]

        def req = subGroup.Requirement[0]
        req.Response.size() == 1
        req.Response[0].Description.text() == "Hodor_24 is clean"
    }

    def "check the 'Is this information available electronically' requirement response"() {
        given:
        def espd = new EspdDocument(purelyNationalGrounds: new PurelyNationalGrounds(exists: true,
                availableElectronically: new AvailableElectronically(answer: false)))

        when:
        def response = generateResponseXml(espd)
        def idx = getResponseCriterionIndex(ExclusionCriterion.NATIONAL_EXCLUSION_GROUNDS)

        then:
        def subGroup = response.Criterion[idx].RequirementGroup[1]

        def req = subGroup.Requirement[0]
        req.Response.size() == 1
        req.Response[0].Indicator.text() == "false"
    }

    def "check the 'Info electronically URL' requirement response"() {
        given:
        def espd = new EspdDocument(purelyNationalGrounds: new PurelyNationalGrounds(exists: true,
                availableElectronically: new AvailableElectronically(answer: true, url: "http://hodor_21.com")))

        when:
        def response = generateResponseXml(espd)
        def idx = getResponseCriterionIndex(ExclusionCriterion.NATIONAL_EXCLUSION_GROUNDS)

        then:
        def subGroup = response.Criterion[idx].RequirementGroup[1].RequirementGroup[0]

        def req = subGroup.Requirement[0]
        req.Response.size() == 1
        checkEvidence(req.Response[0].Evidence, "http://hodor_21.com")
    }

    def "check the 'Info electronically code' requirement response"() {
        given:
        def espd = new EspdDocument(purelyNationalGrounds: new PurelyNationalGrounds(exists: true,
                availableElectronically: new AvailableElectronically(answer: true, code: "HODOR_21")))

        when:
        def response = generateResponseXml(espd)
        def idx = getResponseCriterionIndex(ExclusionCriterion.NATIONAL_EXCLUSION_GROUNDS)

        then:
        def subGroup = response.Criterion[idx].RequirementGroup[1].RequirementGroup[0]

        def req = subGroup.Requirement[1]
        req.Response.size() == 1
        req.Response[0].Code.text() == "HODOR_21"
    }

}