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

import eu.europa.ec.grow.espd.domain.AvailableElectronically
import eu.europa.ec.grow.espd.domain.CriminalConvictionsCriterion
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.SelfCleaning
import eu.europa.ec.grow.espd.xml.LocalDateAdapter
import eu.europa.ec.grow.espd.xml.base.AbstractXmlFileImport
import org.apache.commons.io.IOUtils
/**
 * Created by ratoico on 1/7/16 at 10:41 AM.
 */
class ParticipationCriminalOrganisationImportTest extends AbstractXmlFileImport {

    def "01. should import all fields of 'Participation in a criminal organisation'"() {
        given:
        def espdResponseXml = importXmlResponseFile("exclusion/participation_criminal_organisation_import.xml")

        when:
        EspdDocument espd = marshaller.importEspdResponse(IOUtils.toInputStream(espdResponseXml)).get()

        then:
        espd.criminalConvictions.exists == true
        espd.criminalConvictions.answer == true
        espd.criminalConvictions.dateOfConviction == LocalDateAdapter.unmarshal("2016-01-17").toDate()
        espd.criminalConvictions.reason == "Reason here"
        espd.criminalConvictions.convicted == "Hodor was convicted"
        espd.criminalConvictions.periodLength == "7 years"

        then: "self cleaning"
        espd.criminalConvictions.selfCleaning.answer == true
        espd.criminalConvictions.selfCleaning.description == "Hodor is clean"

        then: "info electronically"
        espd.criminalConvictions.availableElectronically.answer == true
        espd.criminalConvictions.availableElectronically.url == "www.hodor.com"
        espd.criminalConvictions.availableElectronically.code == "INTERNATIONAL"
    }

    def "all fields needed to generate a XML sample"() {
        given:
        def espd = new EspdDocument(criminalConvictions: new CriminalConvictionsCriterion(exists: false,  answer: true,
                dateOfConviction: new Date(),
                reason: "Reason here", convicted: "Hodor was convicted", periodLength: "7 years",
                selfCleaning: new SelfCleaning(answer: true, description: "Hodor is clean"),
                availableElectronically: new AvailableElectronically(answer: true, url: "www.hodor.com", code: "INTERNATIONAL")))
//        saveEspdAsXmlResponse(espd, "/home/ratoico/Downloads/espd-response.xml")

        expect:
        1 == 1
    }

}