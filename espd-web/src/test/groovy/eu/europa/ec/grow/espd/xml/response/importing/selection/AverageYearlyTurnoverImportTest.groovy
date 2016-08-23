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
import eu.europa.ec.grow.espd.domain.EconomicFinancialStandingCriterion
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.xml.base.AbstractXmlFileImport
import org.apache.commons.io.IOUtils
/**
 * Created by ratoico on 8/23/16.
 */
class AverageYearlyTurnoverImportTest extends AbstractXmlFileImport {

    def "06. should import all fields of 'Average yearly turnover'"() {
        given:
        def espdResponseXml = importXmlResponseFile("selection/average_yearly_turnover_import.xml")

        when:
        EspdDocument espd = marshaller.importEspdResponse(IOUtils.toInputStream(espdResponseXml)).get()

        then:
        espd.averageYearlyTurnover.exists == true
        espd.averageYearlyTurnover.answer == true

        then:
        espd.averageYearlyTurnover.numberOfYears == 3
        espd.averageYearlyTurnover.averageTurnover == 111.154
        espd.averageYearlyTurnover.averageTurnoverCurrency == "RON"

        then: "info electronically"
        espd.averageYearlyTurnover.availableElectronically.answer == true
        espd.averageYearlyTurnover.availableElectronically.url == "www.hodor.com"
        espd.averageYearlyTurnover.availableElectronically.code == "AVERAGE_YEARLY_TURNOVER"
    }

    def "all fields needed to generate a XML sample"() {
        given:
        def espd = new EspdDocument(averageYearlyTurnover: new EconomicFinancialStandingCriterion(exists: true, answer: true,
                numberOfYears: 3, averageTurnover: 111.154, averageTurnoverCurrency: "RON",
                availableElectronically: new AvailableElectronically(answer: true, url: "www.hodor.com", code: "AVERAGE_YEARLY_TURNOVER")))
//                saveEspdAsXmlResponse(espd, "/home/ratoico/Downloads/espd-response.xml")

        expect:
        1 == 1
    }

}