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
import eu.europa.ec.grow.espd.domain.EconomicFinancialStandingCriterion
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.xml.base.AbstractXmlFileImport
import org.apache.commons.io.IOUtils
/**
 * Created by ratoico on 1/12/16 at 3:35 PM.
 */
class FinancialRatioImportTest extends AbstractXmlFileImport {

    def "11. should import all fields of 'Financial ratio'"() {
        when:
        EspdDocument espd = parseXmlResponseFile("selection/financial_ratio_import.xml")
        def unboundedGroups = espd.financialRatio.unboundedGroups

        then:
        espd.financialRatio.exists == true

        then: "selection criteria with no answer have a default value of true"
        espd.financialRatio.answer == true

        then:
        unboundedGroups.size() == 5

        then:
        unboundedGroups[0].get("description") == "description1"
        unboundedGroups[0].get("ratio") == 11.1

        then:
        unboundedGroups[1].get("description") == "description2"
        unboundedGroups[1].get("ratio") == 22.2

        then:
        unboundedGroups[2].get("description") == "description3"
        unboundedGroups[2].get("ratio") == 33.3

        then:
        unboundedGroups[3].get("description") == "description4"
        unboundedGroups[3].get("ratio") == 44.4

        then:
        unboundedGroups[4].get("description") == "description5"
        unboundedGroups[4].get("ratio") == 55.5

        then: "info electronically"
        espd.financialRatio.availableElectronically.answer == true
        espd.financialRatio.availableElectronically.url == "www.hodor.com"
        espd.financialRatio.availableElectronically.code == "PROF_REGISTER"
        espd.financialRatio.availableElectronically.issuer == "HODOR"
    }

    def "all fields needed to generate a XML sample"() {
        given:
        def espd = new EspdDocument(financialRatio: new EconomicFinancialStandingCriterion(exists: true, answer: true,
                unboundedGroups: [new DynamicRequirementGroup("description": "description1", "ratio": 11.1),
                                  new DynamicRequirementGroup("description": "description2", "ratio": 22.2),
                                  new DynamicRequirementGroup("description": "description3", "ratio": 33.3),
                                  new DynamicRequirementGroup("description": "description4", "ratio": 44.4),
                                  new DynamicRequirementGroup("description": "description5", "ratio": 55.5)],
                availableElectronically: new AvailableElectronically(answer: true, url: "www.hodor.com", code: "PROF_REGISTER", issuer: "HODOR")))
//                saveEspdAsXmlResponse(espd, "/home/ratoico/Downloads/espd-response.xml")

        expect:
        1 == 1
    }

}