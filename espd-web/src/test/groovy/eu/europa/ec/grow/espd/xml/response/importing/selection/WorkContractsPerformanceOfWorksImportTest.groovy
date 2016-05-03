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
import eu.europa.ec.grow.espd.xml.LocalDateAdapter
import eu.europa.ec.grow.espd.xml.base.AbstractXmlFileImport
import org.apache.commons.io.IOUtils
/**
 * Created by ratoico on 1/8/16 at 11:45 AM.
 */
class WorkContractsPerformanceOfWorksImportTest extends AbstractXmlFileImport {

    def "14. should import all fields of 'For works contracts: performance of works of the specified type'"() {
        given:
        def espdResponseXml = importXmlResponseFile("selection/work_contracts_performance_of_works_import.xml")

        when:
        EspdDocument espd = marshaller.importEspdResponse(IOUtils.toInputStream(espdResponseXml)).get()

        then:
        espd.workContractsPerformanceOfWorks.exists == true

        then: "answer is null and it is a selection criterion so the default value should be true"
        espd.workContractsPerformanceOfWorks.answer == true

        then:
        espd.workContractsPerformanceOfWorks.description1 == "description1"
        espd.workContractsPerformanceOfWorks.amount1 == 11.1
        espd.workContractsPerformanceOfWorks.currency1 == "RON"
        espd.workContractsPerformanceOfWorks.date1 == LocalDateAdapter.unmarshal("2016-01-17").toDate()
        espd.workContractsPerformanceOfWorks.recipients1 == "recipients1"

        then:
        espd.workContractsPerformanceOfWorks.description2 == "description2"
        espd.workContractsPerformanceOfWorks.amount2 == 22.2
        espd.workContractsPerformanceOfWorks.currency2 == "EUR"
        espd.workContractsPerformanceOfWorks.date2 == LocalDateAdapter.unmarshal("2016-01-18").toDate()
        espd.workContractsPerformanceOfWorks.recipients2 == "recipients2"

        then:
        espd.workContractsPerformanceOfWorks.description3 == "description3"
        espd.workContractsPerformanceOfWorks.amount3 == 33.3
        espd.workContractsPerformanceOfWorks.currency3 == "USD"
        espd.workContractsPerformanceOfWorks.date3 == LocalDateAdapter.unmarshal("2016-01-19").toDate()
        espd.workContractsPerformanceOfWorks.recipients3 == "recipients3"

        then:
        espd.workContractsPerformanceOfWorks.description4 == "description4"
        espd.workContractsPerformanceOfWorks.amount4 == 44.4
        espd.workContractsPerformanceOfWorks.currency4 == "CHF"
        espd.workContractsPerformanceOfWorks.date4 == LocalDateAdapter.unmarshal("2016-01-20").toDate()
        espd.workContractsPerformanceOfWorks.recipients4 == "recipients4"

        then:
        espd.workContractsPerformanceOfWorks.description5 == "description5"
        espd.workContractsPerformanceOfWorks.amount5 == 55.5
        espd.workContractsPerformanceOfWorks.currency5 == "YEN"
        espd.workContractsPerformanceOfWorks.date5 == LocalDateAdapter.unmarshal("2016-01-21").toDate()
        espd.workContractsPerformanceOfWorks.recipients5 == "recipients5"

        then: "info electronically"
        espd.workContractsPerformanceOfWorks.availableElectronically.answer == true
        espd.workContractsPerformanceOfWorks.availableElectronically.url == "www.hodor.com"
        espd.workContractsPerformanceOfWorks.availableElectronically.code == "PERF"
    }

    def "all fields needed to generate a XML sample"() {
        given:
        def espd = new EspdDocument(workContractsPerformanceOfWorks: new TechnicalProfessionalCriterion(exists: true, answer: true,
                description1: "description1", amount1: 11.1, currency1: "RON", date1: LocalDateAdapter.unmarshal("2016-01-17").toDate(), recipients1: "recipients1",
                description2: "description2", amount2: 22.2, currency2: "EUR", date2: LocalDateAdapter.unmarshal("2016-01-18").toDate(), recipients2: "recipients2",
                description3: "description3", amount3: 33.3, currency3: "USD", date3: LocalDateAdapter.unmarshal("2016-01-19").toDate(), recipients3: "recipients3",
                description4: "description4", amount4: 44.4, currency4: "CHF", date4: LocalDateAdapter.unmarshal("2016-01-20").toDate(), recipients4: "recipients4",
                description5: "description5", amount5: 55.5, currency5: "YEN", date5: LocalDateAdapter.unmarshal("2016-01-21").toDate(), recipients5: "recipients5",
                availableElectronically: new AvailableElectronically(answer: true, url: "www.hodor.com", code: "PERF")))
//                saveEspdAsXmlResponse(espd, "/home/ratoico/Downloads/espd-request.xml")

        expect:
        1 == 1
    }

}