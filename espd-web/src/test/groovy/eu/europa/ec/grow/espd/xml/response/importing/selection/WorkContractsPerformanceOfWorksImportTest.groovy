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
import eu.europa.ec.grow.espd.domain.DynamicRequirementGroup
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.TechnicalProfessionalCriterion
import eu.europa.ec.grow.espd.xml.LocalDateAdapter
import eu.europa.ec.grow.espd.xml.base.AbstractXmlFileImport
import org.apache.commons.io.IOUtils

/**
 * Created by ratoico on 1/8/16 at 11:45 AM.
 */
class WorkContractsPerformanceOfWorksImportTest extends AbstractXmlFileImport {

    def "14. should import all fields of 'For works contracts: performance of works of the specified type' with unbounded requirement groups"() {
        given:
        def espdResponseXml = importXmlResponseFile("selection/work_contracts_performance_of_works_import.xml")

        when:
        EspdDocument espd = marshaller.importEspdResponse(IOUtils.toInputStream(espdResponseXml)).get()
        def unboundedGroups = espd.workContractsPerformanceOfWorks.unboundedGroups

        then:
        espd.workContractsPerformanceOfWorks.exists == true

        then: "answer is null and it is a selection criterion so the default value should be true"
        espd.workContractsPerformanceOfWorks.answer == true

        then:
        unboundedGroups.size() == 6

        then:
        unboundedGroups[0].get("description") == "description1"
        unboundedGroups[0].get("amount") == 11.1
        unboundedGroups[0].get("currency") == "RON"
        unboundedGroups[0].get("startDate") == LocalDateAdapter.unmarshal("2016-01-17").toDate()
        unboundedGroups[0].get("endDate") == LocalDateAdapter.unmarshal("2016-02-17").toDate()
        unboundedGroups[0].get("recipients") == "recipients1"

        then:
        unboundedGroups[1].get("description") == "description2"
        unboundedGroups[1].get("amount") == 22.2
        unboundedGroups[1].get("currency") == "EUR"
        unboundedGroups[1].get("startDate") == LocalDateAdapter.unmarshal("2016-01-18").toDate()
        unboundedGroups[1].get("endDate") == LocalDateAdapter.unmarshal("2016-02-18").toDate()
        unboundedGroups[1].get("recipients") == "recipients2"

        then:
        unboundedGroups[2].get("description") == "description3"
        unboundedGroups[2].get("amount") == 33.3
        unboundedGroups[2].get("currency") == "USD"
        unboundedGroups[2].get("startDate") == LocalDateAdapter.unmarshal("2016-01-19").toDate()
        unboundedGroups[2].get("endDate") == LocalDateAdapter.unmarshal("2016-02-19").toDate()
        unboundedGroups[2].get("recipients") == "recipients3"

        then:
        unboundedGroups[3].get("description") == "description4"
        unboundedGroups[3].get("amount") == 44.4
        unboundedGroups[3].get("currency") == "CHF"
        unboundedGroups[3].get("startDate") == LocalDateAdapter.unmarshal("2016-01-20").toDate()
        unboundedGroups[3].get("endDate") == LocalDateAdapter.unmarshal("2016-02-20").toDate()
        unboundedGroups[3].get("recipients") == "recipients4"

        then:
        unboundedGroups[4].get("description") == "description5"
        unboundedGroups[4].get("amount") == 55.5
        unboundedGroups[4].get("currency") == "YEN"
        unboundedGroups[4].get("startDate") == LocalDateAdapter.unmarshal("2016-01-21").toDate()
        unboundedGroups[4].get("endDate") == LocalDateAdapter.unmarshal("2016-02-21").toDate()
        unboundedGroups[4].get("recipients") == "recipients5"

        then: "see that we can handle more than 5 requirement unboundedGroups"
        unboundedGroups[5].get("description") == "description6"
        unboundedGroups[5].get("amount") == 66.6
        unboundedGroups[5].get("currency") == "YEN"
        unboundedGroups[5].get("startDate") == LocalDateAdapter.unmarshal("2016-01-22").toDate()
        unboundedGroups[5].get("endDate") == LocalDateAdapter.unmarshal("2016-02-23").toDate()
        unboundedGroups[5].get("recipients") == "recipients6"

        then: "info electronically"
        espd.workContractsPerformanceOfWorks.availableElectronically.answer == true
        espd.workContractsPerformanceOfWorks.availableElectronically.url == "www.hodor.com"
        espd.workContractsPerformanceOfWorks.availableElectronically.code == "PERF"
        espd.workContractsPerformanceOfWorks.availableElectronically.issuer == "HODOR"
    }

    def "14. should import all fields of 'For works contracts: performance of works of the specified type' with old requirement ids before the unbounded groups feature"() {
        given:
        def espdResponseXml = importXmlResponseFile("selection/work_contracts_performance_of_works_import_old_req_ids.xml")

        when:
        EspdDocument espd = marshaller.importEspdResponse(IOUtils.toInputStream(espdResponseXml)).get()
        def unboundedGroups = espd.workContractsPerformanceOfWorks.unboundedGroups

        then:
        espd.workContractsPerformanceOfWorks.exists == true

        then: "answer is null and it is a selection criterion so the default value should be true"
        espd.workContractsPerformanceOfWorks.answer == true

        then:
        unboundedGroups.size() == 5

        then:
        unboundedGroups[0].get("description") == "description1"
        unboundedGroups[0].get("amount").getClass() == BigDecimal.class
        unboundedGroups[0].get("amount") == 11.1
        unboundedGroups[0].get("currency") == "RON"
        unboundedGroups[0].get("startDate") == LocalDateAdapter.unmarshal("2016-01-17").toDate()
        unboundedGroups[0].get("endDate") == LocalDateAdapter.unmarshal("2016-02-17").toDate()
        unboundedGroups[0].get("recipients") == "recipients1"

        then:
        unboundedGroups[1].get("description") == "description2"
        unboundedGroups[1].get("amount") == 22.2
        unboundedGroups[1].get("currency") == "EUR"
        unboundedGroups[1].get("startDate") == LocalDateAdapter.unmarshal("2016-01-18").toDate()
        unboundedGroups[1].get("endDate") == LocalDateAdapter.unmarshal("2016-02-18").toDate()
        unboundedGroups[1].get("recipients") == "recipients2"

        then:
        unboundedGroups[2].get("description") == "description3"
        unboundedGroups[2].get("amount") == 33.3
        unboundedGroups[2].get("currency") == "USD"
        unboundedGroups[2].get("startDate") == LocalDateAdapter.unmarshal("2016-01-19").toDate()
        unboundedGroups[2].get("endDate") == LocalDateAdapter.unmarshal("2016-02-19").toDate()
        unboundedGroups[2].get("recipients") == "recipients3"

        then:
        unboundedGroups[3].get("description") == "description4"
        unboundedGroups[3].get("amount") == 44.4
        unboundedGroups[3].get("currency") == "CHF"
        unboundedGroups[3].get("startDate") == LocalDateAdapter.unmarshal("2016-01-20").toDate()
        unboundedGroups[3].get("endDate") == LocalDateAdapter.unmarshal("2016-02-20").toDate()
        unboundedGroups[3].get("recipients") == "recipients4"

        then:
        unboundedGroups[4].get("description") == "description5"
        unboundedGroups[4].get("amount") == 55.5
        unboundedGroups[4].get("currency") == "YEN"
        unboundedGroups[4].get("startDate") == LocalDateAdapter.unmarshal("2016-01-21").toDate()
        unboundedGroups[4].get("endDate") == LocalDateAdapter.unmarshal("2016-02-21").toDate()
        unboundedGroups[4].get("recipients") == "recipients5"

        then: "info electronically"
        espd.workContractsPerformanceOfWorks.availableElectronically.answer == true
        espd.workContractsPerformanceOfWorks.availableElectronically.url == "www.hodor.com"
        espd.workContractsPerformanceOfWorks.availableElectronically.code == "PERF"
        espd.workContractsPerformanceOfWorks.availableElectronically.issuer == "HODOR"
    }

    def "all fields needed to generate a XML sample"() {
        given:
        def espd = new EspdDocument(workContractsPerformanceOfWorks: new TechnicalProfessionalCriterion(exists: true, answer: true,
                unboundedGroups: [
                        new DynamicRequirementGroup("description": "description1", "amount": 11.1, "currency": "RON", "startDate": LocalDateAdapter.unmarshal("2016-01-17").toDate(), "endDate": LocalDateAdapter.unmarshal("2016-02-17").toDate(), "recipients": "recipients1"),
                        new DynamicRequirementGroup("description": "description2", "amount": 22.2, "currency": "EUR", "startDate": LocalDateAdapter.unmarshal("2016-01-18").toDate(), "endDate": LocalDateAdapter.unmarshal("2016-02-18").toDate(), "recipients": "recipients2"),
                        new DynamicRequirementGroup("description": "description3", "amount": 33.3, "currency": "USD", "startDate": LocalDateAdapter.unmarshal("2016-01-19").toDate(), "endDate": LocalDateAdapter.unmarshal("2016-02-19").toDate(), "recipients": "recipients3"),
                        new DynamicRequirementGroup("description": "description4", "amount": 44.4, "currency": "CHF", "startDate": LocalDateAdapter.unmarshal("2016-01-20").toDate(), "endDate": LocalDateAdapter.unmarshal("2016-02-20").toDate(), "recipients": "recipients4"),
                        new DynamicRequirementGroup("description": "description5", "amount": 55.5, "currency": "YEN", "startDate": LocalDateAdapter.unmarshal("2016-01-21").toDate(), "endDate": LocalDateAdapter.unmarshal("2016-02-21").toDate(), "recipients": "recipients5"),
                        new DynamicRequirementGroup("description": "description6", "amount": 66.6, "currency": "RON", "startDate": LocalDateAdapter.unmarshal("2016-01-22").toDate(), "endDate": LocalDateAdapter.unmarshal("2016-02-22").toDate(), "recipients": "recipients6")],
                availableElectronically: new AvailableElectronically(answer: true, url: "www.hodor.com", code: "PERF", issuer: "HODOR")))
//        saveEspdAsXmlResponse(espd, "/home/ratoico/Downloads/espd-response.xml")

        expect:
        1 == 1
    }

}