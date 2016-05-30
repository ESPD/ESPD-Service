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
 * Created by ratoico on 1/8/16 at 2:44 PM.
 */
class GeneralYearlyTurnoverImportTest extends AbstractXmlFileImport {

    def "06. should import all fields of 'General yearly turnover'"() {
        given:
        def espdResponseXml = importXmlResponseFile("selection/general_yearly_turnover_import.xml")

        when:
        EspdDocument espd = marshaller.importEspdResponse(IOUtils.toInputStream(espdResponseXml)).get()

        then:
        espd.generalYearlyTurnover.exists == true
        espd.generalYearlyTurnover.answer == true

        then:
        espd.generalYearlyTurnover.year1 == 2016
        espd.generalYearlyTurnover.amount1 == 111.1
        espd.generalYearlyTurnover.currency1 == "RON"

        then:
        espd.generalYearlyTurnover.year2 == 2015
        espd.generalYearlyTurnover.amount2 == 222.2
        espd.generalYearlyTurnover.currency2 == "EUR"

        then:
        espd.generalYearlyTurnover.year3 == 2014
        espd.generalYearlyTurnover.amount3 == 333.3
        espd.generalYearlyTurnover.currency3 == "USD"

        then:
        espd.generalYearlyTurnover.year4 == 2013
        espd.generalYearlyTurnover.amount4 == 444.4
        espd.generalYearlyTurnover.currency4 == "CHF"

        then:
        espd.generalYearlyTurnover.year5 == 2012
        espd.generalYearlyTurnover.amount5 == 555.5
        espd.generalYearlyTurnover.currency5 == "YEN"

        then: "info electronically"
        espd.generalYearlyTurnover.availableElectronically.answer == true
        espd.generalYearlyTurnover.availableElectronically.url == "www.hodor.com"
        espd.generalYearlyTurnover.availableElectronically.code == "GENERAL_TURNOVER"
    }

    def "a selection criterion with no answer will be treated as FALSE"() {
        given:
        def espdResponseXml = importXmlResponseFile("selection/selection_criterion_no_answer_import.xml")

        when:
        EspdDocument espd = marshaller.importEspdResponse(IOUtils.toInputStream(espdResponseXml)).get()

        then:
        espd.generalYearlyTurnover.exists == true
        espd.generalYearlyTurnover.answer == false

    }

    def "all fields needed to generate a XML sample"() {
        given:
        def espd = new EspdDocument(generalYearlyTurnover: new EconomicFinancialStandingCriterion(exists: true, answer: true,
                year1: 2016, amount1: 111.1, currency1: "RON",
                year2: 2015, amount2: 222.2, currency2: "EUR",
                year3: 2014, amount3: 333.3, currency3: "USD",
                year4: 2013, amount4: 444.4, currency4: "CHF",
                year5: 2012, amount5: 555.5, currency5: "YEN",
                availableElectronically: new AvailableElectronically(answer: true, url: "www.hodor.com", code: "GENERAL_TURNOVER")))
//                saveEspdAsXmlResponse(espd, "/home/ratoico/Downloads/espd-response.xml")

        expect:
        1 == 1
    }

}