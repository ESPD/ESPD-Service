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

import eu.europa.ec.grow.espd.domain.enums.criteria.OtherCriterion
import eu.europa.ec.grow.espd.domain.enums.criteria.SelectionCriterion
import eu.europa.ec.grow.espd.domain.AvailableElectronically
import eu.europa.ec.grow.espd.domain.CriminalConvictionsCriterion
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.SelfCleaning
import eu.europa.ec.grow.espd.xml.base.AbstractExclusionCriteriaFixture
/**
 * Created by ratoico on 12/23/15 at 3:59 PM.
 */
class MixedExclusionCriteriaResponseTest extends AbstractExclusionCriteriaFixture {

    def "should contain mixed criminal convictions criteria"() {
        given:
        def participation = new CriminalConvictionsCriterion(exists: true, convicted: "Hodor_01 was convicted")
        def corruption = new CriminalConvictionsCriterion(exists: true, convicted: "Hodor_02 was convicted")
        def espd = new EspdDocument(criminalConvictions: participation, corruption: corruption)

        when:
        def request = parseResponseXml(espd)

        then:
        request.Criterion.size() == 2 + SelectionCriterion.values().length + OtherCriterion.values().length

        then: "check who has been convicted requirement in participation criterion"
        def idx1 = 0
        def participationSubGroup = request.Criterion[idx1].RequirementGroup[0]
        def participationReq = participationSubGroup.Requirement[3]
        checkRequirement(participationReq, "c5012430-14da-454c-9d01-34cedc6a7ded", "Who has been convicted", "DESCRIPTION")
        participationReq.Response.size() == 1
        participationReq.Response[0].Description.text() == "Hodor_01 was convicted"

        then: "check who has been convicted requirement in corruption criterion"
        def idx2 = 1
        def corruptionSubGroup = request.Criterion[idx2].RequirementGroup[0]
        def corruptionReq = corruptionSubGroup.Requirement[3]
        checkRequirement(corruptionReq, "c5012430-14da-454c-9d01-34cedc6a7ded", "Who has been convicted", "DESCRIPTION")
        corruptionReq.Response.size() == 1
        corruptionReq.Response[0].Description.text() == "Hodor_02 was convicted"
    }

    def "should contain mixed criminal convictions self cleaning requirements"() {
        given:
        def participation = new CriminalConvictionsCriterion(exists: true, selfCleaning: new SelfCleaning(answer: true, description: "Hodor_01 is clean"))
        def corruption = new CriminalConvictionsCriterion(exists: true, selfCleaning: new SelfCleaning(answer: true, description: "Hodor_02 is clean"))
        def espd = new EspdDocument(criminalConvictions: participation, corruption: corruption)

        when:
        def request = parseResponseXml(espd)

        then: "check self cleaning description requirement in participation criterion"
        def idx1 = 0
        def participationSubGroup = request.Criterion[idx1].RequirementGroup[0].RequirementGroup[0]
        def participationReq = participationSubGroup.Requirement[1]
        participationReq.Response[0].Description.text() == "Hodor_01 is clean"

        then: "check who has been convicted requirement in corruption criterion"
        def idx2 = 1
        def corruptionSubGroup = request.Criterion[idx2].RequirementGroup[0].RequirementGroup[0]
        def corruptionReq = corruptionSubGroup.Requirement[1]
        corruptionReq.Response.size() == 1
        corruptionReq.Response[0].Description.text() == "Hodor_02 is clean"
    }

    def "should contain mixed criminal convictions info electronically requirements"() {
        given:
        def participation = new CriminalConvictionsCriterion(exists: true, availableElectronically: new AvailableElectronically(answer: true, url: "http://hodor_01.com"))
        def corruption = new CriminalConvictionsCriterion(exists: true, availableElectronically: new AvailableElectronically(answer: true, url: "http://hodor_02.com"))
        def espd = new EspdDocument(criminalConvictions: participation, corruption: corruption)

        when:
        def request = parseResponseXml(espd)

        then: "check self cleaning description requirement in participation criterion"
        def idx1 = 0
        def participationSubGroup = request.Criterion[idx1].RequirementGroup[1]
        def participationReq = participationSubGroup.Requirement[1]
        checkEvidence(participationReq.Response[0].Evidence, "http://hodor_01.com")

        then: "check who has been convicted requirement in corruption criterion"
        def idx2 = 1
        def corruptionSubGroup = request.Criterion[idx2].RequirementGroup[1]
        def corruptionReq = corruptionSubGroup.Requirement[1]
        corruptionReq.Response.size() == 1
        checkEvidence(corruptionReq.Response[0].Evidence, "http://hodor_02.com")
    }

}