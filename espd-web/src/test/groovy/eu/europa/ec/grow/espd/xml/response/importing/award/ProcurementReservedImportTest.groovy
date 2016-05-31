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
 * Created by ratoico on 1/20/16 at 2:42 PM.
 */
class ProcurementReservedImportTest extends AbstractXmlFileImport {

    def "01. should import all fields of 'Procurement reserved'"() {
        given:
        def espdResponseXml = importXmlResponseFile("award/procurement_reserved_import.xml")

        when:
        EspdDocument espd = marshaller.importEspdResponse(IOUtils.toInputStream(espdResponseXml)).get()

        then:
        espd.procurementReserved.exists == true
        espd.procurementReserved.answer == true
        espd.procurementReserved.doubleValue1 == 11.11
        espd.procurementReserved.description1 == "Hodor"
    }

    def "all fields needed to generate a XML sample"() {
        given:
        def espd = new EspdDocument(procurementReserved: new OtherCriterion(exists: true, description1: "Hodor",
                answer: true, doubleValue1: 11.11))
//        saveEspdAsXmlResponse(espd, "/home/ratoico/Downloads/espd-response.xml")

        expect:
        1 == 1
    }


}