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
import eu.europa.ec.grow.espd.xml.base.AbstractXmlFileImport
import org.apache.commons.io.IOUtils
/**
 * Created by ratoico on 1/13/16 at 10:15 AM.
 */
class NumberOfManagerialStaffImportTest extends AbstractXmlFileImport {

    def "24. should import all fields of 'Number of managerial staff'"() {
        given:
        def espdResponseXml = importXmlResponseFile("selection/number_of_managerial_staff_import.xml")

        when:
        EspdDocument espd = marshaller.importEspdResponse(IOUtils.toInputStream(espdResponseXml)).get()
        def unboundedGroups = espd.numberManagerialStaff.unboundedGroups

        then:
        unboundedGroups.size() == 3

        then:
        espd.numberManagerialStaff.exists == true

        then: "answer is null and it is a selection criterion so the default value should be true"
        espd.numberManagerialStaff.answer == true

        then:
        unboundedGroups[0].get("year") == 2016
        unboundedGroups[0].get("number") == 11

        then:
        unboundedGroups[1].get("year") == 2015
        unboundedGroups[1].get("number") == 22

        then:
        unboundedGroups[2].get("year") == 2014
        unboundedGroups[2].get("number") == 33

        then: "info electronically"
        espd.numberManagerialStaff.availableElectronically.answer == true
        espd.numberManagerialStaff.availableElectronically.url == "www.hodor.com"
        espd.numberManagerialStaff.availableElectronically.code == "GENERAL_TURNOVER"
        espd.numberManagerialStaff.availableElectronically.issuer == "HODOR"
    }

    def "all fields needed to generate a XML sample"() {
        given:
        def espd = new EspdDocument(numberManagerialStaff: new TechnicalProfessionalCriterion(exists: true, answer: true,
                unboundedGroups: [
                        new DynamicRequirementGroup("year": 2016, "number": 11),
                        new DynamicRequirementGroup("year": 2015, "number": 22),
                        new DynamicRequirementGroup("year": 2014, "number": 33)],
                availableElectronically: new AvailableElectronically(answer: true, url: "www.hodor.com", code: "GENERAL_TURNOVER", issuer: "HODOR")))
//                saveEspdAsXmlResponse(espd, "/home/ratoico/Downloads/espd-response.xml")

        expect:
        1 == 1
    }

}