package eu.europa.ec.grow.espd.xml.base

import eu.europa.ec.grow.espd.config.JaxbConfiguration
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.xml.EspdExchangeMarshaller
import eu.europa.ec.grow.espd.xml.common.*
import eu.europa.ec.grow.espd.xml.request.UblRequestToEspdDocumentTransformer
import eu.europa.ec.grow.espd.xml.request.UblRequestTypeTransformer
import eu.europa.ec.grow.espd.xml.response.UblResponseToEspdDocumentTransformer
import eu.europa.ec.grow.espd.xml.response.UblResponseTypeTransformer
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
        initEspdMarshaller(jaxb2Marshaller)
    }

    @Shared
    protected static EspdExchangeMarshaller marshaller

    protected StringWriter xmlOutput

    void setupSpec() {
        // init objects run before the first feature method
    }

    private static void initEspdMarshaller(Jaxb2Marshaller jaxb2Marshaller) {
        def commonUblFactory = new CommonUblFactory()
        def ublContractingPartyTypeTransformer = new UblContractingPartyTypeTransformer()
        def economicOperatorPartyTypeTransformer = new UblEconomicOperatorPartyTypeTransformer()
        def ublRequestTypeTransformer = new UblRequestTypeTransformer(commonUblFactory, ublContractingPartyTypeTransformer)
        def partyImplTransformer = new PartyImplTransformer()
        def criteriaToEspdDocumentPopulator = new CriteriaToEspdDocumentPopulator()
        def ublDocumentReferences = new UblDocumentReferences()
        def requestToEspdDocumentTransformer = new UblRequestToEspdDocumentTransformer(partyImplTransformer, criteriaToEspdDocumentPopulator, ublDocumentReferences)
        def economicOperatorImplTransformer = new EconomicOperatorImplTransformer(partyImplTransformer)
        def responseToEspdDocumentTransformer = new UblResponseToEspdDocumentTransformer(partyImplTransformer, economicOperatorImplTransformer, criteriaToEspdDocumentPopulator, ublDocumentReferences)
        def ublResponseTypeTransformer = new UblResponseTypeTransformer(commonUblFactory, ublContractingPartyTypeTransformer, economicOperatorPartyTypeTransformer)
        marshaller = new EspdExchangeMarshaller(jaxb2Marshaller, ublRequestTypeTransformer, requestToEspdDocumentTransformer,
                responseToEspdDocumentTransformer, ublResponseTypeTransformer)
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

    protected GPathResult parseRequestXml() {
        parseRequestXml(new EspdDocument())
    }

    protected GPathResult parseRequestXml(EspdDocument espdDocument) {
        marshaller.generateEspdRequest(espdDocument, xmlOutput)
        new XmlSlurper().parseText(xmlOutput.toString())
    }

    protected GPathResult parseResponseXml() {
        parseResponseXml(new EspdDocument())
    }

    protected GPathResult parseResponseXml(EspdDocument espdDocument) {
        marshaller.generateEspdResponse(espdDocument, xmlOutput)
        new XmlSlurper().parseText(xmlOutput.toString())
    }

    protected void printXmlOutput() {
        println("---------------------- ${xmlOutput}")
    }

    protected void saveEspdAsXmlResponse(EspdDocument espd, String filePath) {
        parseResponseXml(espd)
        def file = new File(filePath)
        file.text = xmlOutput
    }

}
