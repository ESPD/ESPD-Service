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

package eu.europa.ec.grow.espd.domain

import org.apache.commons.beanutils.PropertyUtils
import spock.lang.Specification

/**
 * Created by ratoico on 6/6/16.
 */
class EspdDocumentTest extends Specification {

    def "all exclusion criteria should have 'exists' set to true"() {
        given:
        def espd = new EspdDocument()

        when:
        espd.giveLifeToAllExclusionCriteria()

        then:
        for (eu.europa.ec.grow.espd.domain.enums.criteria.ExclusionCriterion crit : eu.europa.ec.grow.espd.domain.enums.criteria.ExclusionCriterion.values()) {
            PropertyUtils.getNestedProperty(espd, "${crit.espdDocumentField}.exists") == true
        }
    }

    def "all selection criteria should have 'exists' set to true"() {
        given:
        def espd = new EspdDocument()

        when:
        espd.giveLifeToAllSelectionCriteria()

        then:
        for (eu.europa.ec.grow.espd.domain.enums.criteria.SelectionCriterion crit : eu.europa.ec.grow.espd.domain.enums.criteria.SelectionCriterion.values()) {
            PropertyUtils.getNestedProperty(espd, "${crit.espdDocumentField}.exists") == true
        }
    }

    def "should read a random ccv criterion information from the ESPD domain object"() {
        given:
        def crit = new CriminalConvictionsCriterion(exists: true, answer: false, reason: "hold the door!", convicted: "hodor")
        def espd = new EspdDocument(criminalConvictions: crit)

        when:
        def result = espd.readCriterionFromEspd(eu.europa.ec.grow.espd.domain.enums.criteria.ExclusionCriterion.PARTICIPATION_CRIMINAL_ORGANISATION)

        then:
        result.exists == crit.exists
        result.answer == crit.answer
        result.reason == "hold the door!"
        result.convicted == "hodor"
        result == crit
    }

    def "should preselect the mandatory exclusion criteria for CA"() {
        given:
        def espd = new EspdDocument()

        when:
        espd.selectCAExclusionCriteria()

        then:
        for (eu.europa.ec.grow.espd.domain.enums.criteria.ExclusionCriterion crit : eu.europa.ec.grow.espd.domain.enums.criteria.ExclusionCriterion.values()) {
            if (!eu.europa.ec.grow.espd.domain.enums.criteria.ExclusionCriterion.NATIONAL_EXCLUSION_GROUNDS.equals(crit)) {
                PropertyUtils.getNestedProperty(espd, "${crit.espdDocumentField}.exists") == true
            } else {
                espd.purelyNationalGrounds.exists == false
            }
        }
    }
}
