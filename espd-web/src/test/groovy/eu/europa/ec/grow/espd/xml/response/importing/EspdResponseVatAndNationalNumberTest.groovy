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

package eu.europa.ec.grow.espd.xml.response.importing

import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.xml.base.AbstractXmlFileImport

/**
 * Created by ratoico on 8/18/16.
 */
class EspdResponseVatAndNationalNumberTest extends AbstractXmlFileImport {

    EspdDocument espd

    void setup() {

    }

    void cleanup() {
        espd = null
    }

    def "should import economic operator party VAT number by using the schemeId"() {
        given:
        espd = parseXmlResponseFile("response_eo_party_only_vat_number_scheme_id.xml")

        expect:
        espd.economicOperator.vatNumber == "BE0999999999"
        espd.economicOperator.anotherNationalId == null
    }

    def "should import economic operator party national number by using the schemeId"() {
        given:
        espd = parseXmlResponseFile("response_eo_party_only_national_number_scheme_id.xml")

        expect:
        espd.economicOperator.anotherNationalId == "RO12345678"
        espd.economicOperator.vatNumber == null
    }

}