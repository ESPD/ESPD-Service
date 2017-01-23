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

package eu.europa.ec.grow.espd.xml.response.importing.award

import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.OtherCriterion
import eu.europa.ec.grow.espd.xml.base.AbstractXmlFileImport
/**
 * Created by ratoico on 1/20/16 at 3:30 PM.
 */
class EconomicOperatorRegisteredImportTest extends AbstractXmlFileImport {

    def "02. should import all fields of 'Economic operator registered'"() {
        when:
        EspdDocument espd = parseXmlResponseFile("award/eo_registered_import.xml")

        then:
        espd.eoRegistered.exists == true
        espd.eoRegistered.answer == false
        espd.eoRegistered.booleanValue1 == true
        espd.eoRegistered.booleanValue2 == null // not applicable is not recognized anymore
        espd.eoRegistered.booleanValue3 == true
        espd.eoRegistered.description1 == "descr 1"
        espd.eoRegistered.description2 == "descr 2"
        espd.eoRegistered.description3 == "descr 3"
        // description 4 field (part e) has been replaced by an indicator stored in booleanValue3
        espd.eoRegistered.description5 == "descr 5"
    }

    def "all fields needed to generate a XML sample"() {
        given:
        def espd = new EspdDocument(eoRegistered: new OtherCriterion(exists: true, answer: false,
                description1: "descr 1", description2: "descr 2", description3: "descr 3", description5: "descr 5",
                booleanValue1: true, booleanValue2: true, booleanValue3: true))
//        saveEspdAsXmlResponse(espd, "/home/ratoico/Downloads/espd-response.xml")

        expect:
        1 == 1
    }

}