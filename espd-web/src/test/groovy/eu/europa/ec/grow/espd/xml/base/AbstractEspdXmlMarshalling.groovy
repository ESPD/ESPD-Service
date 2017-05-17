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

import eu.europa.ec.grow.espd.config.JaxbConfiguration
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.util.EspdConfiguration
import eu.europa.ec.grow.espd.xml.EspdXmlExporter
import eu.europa.ec.grow.espd.xml.EspdXmlImporter
import eu.europa.ec.grow.espd.xml.common.exporting.UblContractingPartyTypeTransformer
import eu.europa.ec.grow.espd.xml.common.exporting.UblEconomicOperatorPartyTypeTransformer
import eu.europa.ec.grow.espd.xml.common.importing.CriteriaToEspdDocumentPopulator
import eu.europa.ec.grow.espd.xml.common.importing.EconomicOperatorImplTransformer
import eu.europa.ec.grow.espd.xml.common.importing.PartyImplTransformer
import eu.europa.ec.grow.espd.xml.request.exporting.UblRequestCriteriaTransformer
import eu.europa.ec.grow.espd.xml.request.exporting.UblRequestTypeTransformer
import eu.europa.ec.grow.espd.xml.request.importing.UblRequestImporter
import eu.europa.ec.grow.espd.xml.response.exporting.UblResponseCriteriaTransformer
import eu.europa.ec.grow.espd.xml.response.exporting.UblResponseTypeTransformer
import eu.europa.ec.grow.espd.xml.response.importing.UblRequestResponseMerger
import eu.europa.ec.grow.espd.xml.response.importing.UblResponseImporter
import groovy.util.slurpersupport.GPathResult
import org.springframework.oxm.jaxb.Jaxb2Marshaller
import spock.lang.Shared
import spock.lang.Specification
/**
 *  Created by vigi on 11/20/15:9:13 AM.
 */
abstract class AbstractEspdXmlMarshalling extends Specification {

    @Shared
    protected static Jaxb2Marshaller jaxb2Marshaller = new JaxbConfiguration().jaxb2Marshaller()

    static {
        // init the marshaller only once because it's expensive to create
        initEspdMarshallers(jaxb2Marshaller)
    }

    @Shared
    protected static EspdXmlExporter xmlExporter

    @Shared
    protected static EspdXmlImporter xmlImporter

    protected StringWriter xmlOutput

    void setupSpec() {
        // init objects run before the first feature method
    }

    private static void initEspdMarshallers(Jaxb2Marshaller jaxb2Marshaller) {
        def ublContractingPartyTypeTransformer = new UblContractingPartyTypeTransformer()
        def economicOperatorPartyTypeTransformer = new UblEconomicOperatorPartyTypeTransformer()
        def espdConfig = new EspdConfiguration(null)
        espdConfig.exchangeModelVersion = "1.0.2"
        def ublRequestTypeTransformer = new UblRequestTypeTransformer(ublContractingPartyTypeTransformer, new UblRequestCriteriaTransformer(), espdConfig)
        def partyImplTransformer = new PartyImplTransformer()
        def criteriaToEspdDocumentPopulator = new CriteriaToEspdDocumentPopulator()
        def economicOperatorImplTransformer = new EconomicOperatorImplTransformer(partyImplTransformer)
        def ublRequestImporter = new UblRequestImporter(partyImplTransformer, economicOperatorImplTransformer, criteriaToEspdDocumentPopulator)
        def ublResponseImporter = new UblResponseImporter(partyImplTransformer, economicOperatorImplTransformer, criteriaToEspdDocumentPopulator)
        def ublResponseTypeTransformer = new UblResponseTypeTransformer(ublContractingPartyTypeTransformer, economicOperatorPartyTypeTransformer, new UblResponseCriteriaTransformer(), espdConfig)
        def requestResponseMerger = new UblRequestResponseMerger(partyImplTransformer, economicOperatorImplTransformer, criteriaToEspdDocumentPopulator)
        xmlExporter = new EspdXmlExporter(jaxb2Marshaller, ublRequestTypeTransformer, ublResponseTypeTransformer)
        xmlImporter = new EspdXmlImporter(jaxb2Marshaller, ublRequestImporter, ublResponseImporter, requestResponseMerger)
    }

    void cleanupSpec() {
        // cleanup run after the last feature method
    }

    void setup() {
        xmlOutput = new StringWriter()
    }

    void cleanup() {
        xmlOutput = null
    }

    protected GPathResult generateRequestXml() {
        generateRequestXml(new EspdDocument())
    }

    protected GPathResult generateRequestXml(EspdDocument espdDocument) {
        xmlExporter.generateEspdRequest(espdDocument, xmlOutput)
        new XmlSlurper().parseText(xmlOutput.toString())
    }

    protected GPathResult generateResponseXml() {
        generateResponseXml(new EspdDocument())
    }

    protected GPathResult generateResponseXml(EspdDocument espdDocument) {
        xmlExporter.generateEspdResponse(espdDocument, xmlOutput)
        new XmlSlurper().parseText(xmlOutput.toString())
    }

    protected void printXmlOutput() {
        println("---------------------- ${xmlOutput}")
    }

    protected void saveEspdAsXmlResponse(EspdDocument espd, String filePath) {
        generateResponseXml(espd)
        def file = new File(filePath)
        file.text = xmlOutput
        println("--------------- created ESPD Response at ${file.absolutePath}")
    }

}
