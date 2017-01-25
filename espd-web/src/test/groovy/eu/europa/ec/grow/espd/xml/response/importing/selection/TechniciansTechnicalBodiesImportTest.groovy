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

package eu.europa.ec.grow.espd.xml.response.importing.selection

import eu.europa.ec.grow.espd.domain.AvailableElectronically
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.TechnicalProfessionalCriterion
import eu.europa.ec.grow.espd.xml.base.AbstractXmlFileImport
/**
 * Created by ratoico on 1/8/16 at 1:15 PM.
 */
class TechniciansTechnicalBodiesImportTest extends AbstractXmlFileImport {

    def "17. should import all fields of 'Technicians or technical bodies for quality control'"() {
        when:
        EspdDocument espd = parseXmlResponseFile("selection/technicians_technical_bodies_import.xml")

        then:
        espd.techniciansTechnicalBodies.exists == true

        then: "answer is null and it is a selection criterion so the default value should be true"
        espd.techniciansTechnicalBodies.answer == true // has no answer criterion
        espd.techniciansTechnicalBodies.description == "specify something"

        then: "info electronically"
        espd.techniciansTechnicalBodies.availableElectronically.answer == true
        espd.techniciansTechnicalBodies.availableElectronically.url == "www.hodor.com"
        espd.techniciansTechnicalBodies.availableElectronically.code == "TECHNICAL_BODIES"
        espd.techniciansTechnicalBodies.availableElectronically.issuer == "HODOR"
    }

    def "all fields needed to generate a XML sample"() {
        given:
        def espd = new EspdDocument(techniciansTechnicalBodies: new TechnicalProfessionalCriterion(exists: true, answer: true,
                description: "specify something",
                availableElectronically: new AvailableElectronically(answer: true, url: "www.hodor.com", code: "TECHNICAL_BODIES", issuer: "HODOR")))
//                saveEspdAsXmlResponse(espd, "/home/ratoico/Downloads/espd-response.xml")

        expect:
        1 == 1
    }

}