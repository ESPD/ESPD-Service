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

package eu.europa.ec.grow.espd.xml.base

import eu.europa.ec.grow.espd.domain.EspdDocument
import org.apache.commons.io.IOUtils

/**
 * Created by ratoico on 1/7/16 at 10:51 AM.
 */
abstract class AbstractXmlFileImport extends AbstractEspdXmlMarshalling {

    protected String importXmlRequestFile(String fileName) {
        return new File("./src/test/groovy/eu/europa/ec/grow/espd/xml/samples/request/${fileName}").getText('UTF-8')
    }

    protected String importXmlResponseFile(String fileName) {
        return new File("./src/test/groovy/eu/europa/ec/grow/espd/xml/samples/response/${fileName}").getText('UTF-8')
    }

    protected EspdDocument parseXmlResponseFile(String fileName) {
        def espdResponseXml = importXmlResponseFile(fileName)
        return marshaller.importEspdResponse(IOUtils.toInputStream(espdResponseXml)).get()
    }

}