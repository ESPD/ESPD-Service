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

        then:
        espd.numberManagerialStaff.exists == true

        then: "answer is null and it is a selection criterion so the default value should be true"
        espd.numberManagerialStaff.answer == true

        then:
        espd.numberManagerialStaff.year1 == 2016
        espd.numberManagerialStaff.number1 == 11

        then:
        espd.numberManagerialStaff.year2 == 2015
        espd.numberManagerialStaff.number2 == 22

        then:
        espd.numberManagerialStaff.year3 == 2014
        espd.numberManagerialStaff.number3 == 33

        then: "info electronically"
        espd.numberManagerialStaff.availableElectronically.answer == true
        espd.numberManagerialStaff.availableElectronically.url == "www.hodor.com"
        espd.numberManagerialStaff.availableElectronically.code == "GENERAL_TURNOVER"
        espd.numberManagerialStaff.availableElectronically.issuer == "HODOR"
    }

    def "all fields needed to generate a XML sample"() {
        given:
        def espd = new EspdDocument(numberManagerialStaff: new TechnicalProfessionalCriterion(exists: true, answer: true,
                year1: 2016, number1: 11,
                year2: 2015, number2: 22,
                year3: 2014, number3: 33,
                availableElectronically: new AvailableElectronically(answer: true, url: "www.hodor.com", code: "GENERAL_TURNOVER", issuer: "HODOR")))
//                saveEspdAsXmlResponse(espd, "/home/ratoico/Downloads/espd-response.xml")

        expect:
        1 == 1
    }

}