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

package eu.europa.ec.grow.espd.xml.response.importing.exclusion

import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.MisconductDistortionCriterion
import eu.europa.ec.grow.espd.domain.SelfCleaning
import eu.europa.ec.grow.espd.xml.base.AbstractXmlFileImport
/**
 * Created by ratoico on 1/18/16 at 10:41 AM.
 */
class AgreementsWithEOImportTest extends AbstractXmlFileImport {

    def "19. should import all fields of 'Conflict of interest due to its participation in the procurement procedure'"() {
        when:
        EspdDocument espd = parseXmlResponseFile("exclusion/agreements_with_eo_import.xml")

        then:
        espd.agreementsWithOtherEO.exists == true
        espd.agreementsWithOtherEO.answer == true
        espd.agreementsWithOtherEO.description == "Hodor is distorting"

        then: "self cleaning"
        espd.agreementsWithOtherEO.selfCleaning.answer == true
        espd.agreementsWithOtherEO.selfCleaning.description == "Hodor is clean"

        then: "info electronically has a default answer of false"
        espd.agreementsWithOtherEO.availableElectronically.answer == false
        espd.agreementsWithOtherEO.availableElectronically.url == null
        espd.agreementsWithOtherEO.availableElectronically.code == null
    }

    def "an exclusion criterion with no answer will have a value of FALSE"() {
        when:
        EspdDocument espd = parseXmlResponseFile("exclusion/exclusion_criterion_no_answer_import.xml")

        then:
        espd.agreementsWithOtherEO.exists == true
        espd.agreementsWithOtherEO.answer == false
    }

    def "all fields needed to generate a XML sample"() {
        given:
        def espd = new EspdDocument(agreementsWithOtherEO: new MisconductDistortionCriterion(exists: true,  answer: true,
                description: "Hodor is distorting",
                selfCleaning: new SelfCleaning(answer: true, description: "Hodor is clean")))
//        saveEspdAsXmlResponse(espd, "/home/ratoico/Downloads/espd-response.xml")

        expect:
        1 == 1
    }

}