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

import eu.europa.ec.grow.espd.domain.ConflictInterestCriterion
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.xml.base.AbstractXmlFileImport
/**
 * Created by ratoico on 1/8/16 at 10:49 AM.
 */
class ConflictOfInterestImportTest extends AbstractXmlFileImport {

    def "20. should import all fields of 'Conflict of interest due to its participation in the procurement procedure'"() {
        when:
        EspdDocument espd = parseXmlResponseFile("exclusion/conflict_of_interest_import.xml")

        then:
        espd.conflictInterest.exists == true
        espd.conflictInterest.answer == true
        espd.conflictInterest.description == "Hodor is conflicted"
    }

    def "all fields needed to generate a XML sample"() {
        given:
        def espd = new EspdDocument(conflictInterest: new ConflictInterestCriterion(exists: true,  answer: true,
                description: "Hodor is conflicted"))
//        saveEspdAsXmlResponse(espd, "/home/ratoico/Downloads/espd-response.xml")

        expect:
        1 == 1
    }

}