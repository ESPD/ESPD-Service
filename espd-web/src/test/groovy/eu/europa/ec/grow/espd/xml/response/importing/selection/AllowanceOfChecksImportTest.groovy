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

import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.TechnicalProfessionalCriterion
import eu.europa.ec.grow.espd.xml.base.AbstractXmlFileImport
/**
 * Created by ratoico on 1/12/16 at 5:24 PM.
 */
class AllowanceOfChecksImportTest extends AbstractXmlFileImport {

    def "22. should import all fields of 'Allowance of checks'"() {
        when:
        EspdDocument espd = parseXmlResponseFile("selection/allowance_of_checks_import.xml")

        then:
        espd.allowanceOfChecks.exists == true
        espd.allowanceOfChecks.answer == true
    }

    def "all fields needed to generate a XML sample"() {
        given:
        def espd = new EspdDocument(allowanceOfChecks: new TechnicalProfessionalCriterion(exists: true, answer: true))
//                saveEspdAsXmlResponse(espd, "/home/ratoico/Downloads/espd-response.xml")

        expect:
        1 == 1
    }

}