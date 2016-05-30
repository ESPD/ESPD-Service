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

import eu.europa.ec.grow.espd.domain.OtherCriterion
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.xml.base.AbstractXmlFileImport
import org.apache.commons.io.IOUtils

/**
 * Created by ratoico on 1/20/16 at 3:45 PM.
 */
class EconomicOperatorReliesCapacitiesImportTest extends AbstractXmlFileImport {

    def "04. should import all fields of 'Economic operator relies capacities'"() {
        given:
        def espdResponseXml = importXmlResponseFile("award/eo_relies_capacities_import.xml")

        when:
        EspdDocument espd = marshaller.importEspdResponse(IOUtils.toInputStream(espdResponseXml)).get()

        then:
        espd.eoReliesCapacities.exists == true
        espd.eoReliesCapacities.answer == true
    }

    def "all fields needed to generate a XML sample"() {
        given:
        def espd = new EspdDocument(eoReliesCapacities: new OtherCriterion(exists: true, answer: true))
//        saveEspdAsXmlResponse(espd, "/home/ratoico/Downloads/espd-response.xml")

        expect:
        1 == 1
    }


}