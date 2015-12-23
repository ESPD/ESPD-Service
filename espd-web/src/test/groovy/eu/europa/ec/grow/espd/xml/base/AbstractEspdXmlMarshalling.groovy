package eu.europa.ec.grow.espd.xml.base

import eu.europa.ec.grow.espd.business.EspdExchangeMarshaller
import eu.europa.ec.grow.espd.business.common.CommonUblFactory
import eu.europa.ec.grow.espd.business.common.PartyImplTransformer
import eu.europa.ec.grow.espd.business.common.UblContractingPartyTypeTransformer
import eu.europa.ec.grow.espd.business.request.UblRequestToEspdDocumentTransformer
import eu.europa.ec.grow.espd.business.request.UblRequestTypeTransformer
import eu.europa.ec.grow.espd.business.response.UblResponseTypeTransformer
import eu.europa.ec.grow.espd.config.JaxbConfiguration
import eu.europa.ec.grow.espd.domain.EspdDocument
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
        def ublRequestTypeTransformer = new UblRequestTypeTransformer(commonUblFactory, ublContractingPartyTypeTransformer)
        def partyImplTransformer = new PartyImplTransformer()
        def toEspdDocumentTransformer = new UblRequestToEspdDocumentTransformer(partyImplTransformer)
        def ublResponseTypeTransformer = new UblResponseTypeTransformer(commonUblFactory, ublContractingPartyTypeTransformer)
        marshaller = new EspdExchangeMarshaller(jaxb2Marshaller, ublRequestTypeTransformer, toEspdDocumentTransformer, ublResponseTypeTransformer)
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

}
