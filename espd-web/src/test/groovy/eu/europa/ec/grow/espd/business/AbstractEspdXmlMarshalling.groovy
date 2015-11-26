package eu.europa.ec.grow.espd.business

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
    protected Jaxb2Marshaller jaxb2Marshaller

    @Shared
    protected EspdDocumentToEspdRequestTransformer toEspdRequestTransformer

    @Shared
    protected EspdRequestToEspdDocumentTransformer toEspdDocumentTransformer

    @Shared
    protected EspdExchangeMarshaller marshaller

    protected StringWriter xmlOutput

    void setupSpec() {
        jaxb2Marshaller = new JaxbConfiguration().jaxb2Marshaller()
        def commonUblFactory = new CommonUblFactory()
        def contractingPartyTransformer = new ToContractingPartyTransformer()
        def toCriterionTypeTransformer = new CcvCriterionToCriterionTypeTransformer()
        toEspdRequestTransformer = new EspdDocumentToEspdRequestTransformer(commonUblFactory, contractingPartyTransformer, toCriterionTypeTransformer)
        def toPartyImplTransformer = new ToPartyImplTransformer()
        toEspdDocumentTransformer = new EspdRequestToEspdDocumentTransformer(toPartyImplTransformer)
        marshaller = new EspdExchangeMarshaller(jaxb2Marshaller, toEspdRequestTransformer, toEspdDocumentTransformer, toEspdResponseTransformer)
    }

    void cleanupSpec() {
        marshaller = null
        toEspdRequestTransformer = null
        toEspdDocumentTransformer = null
        jaxb2Marshaller = null
    }

    void setup() {
        xmlOutput = new StringWriter()
    }

    void cleanup() {
        xmlOutput = null
    }

    protected GPathResult parseXml() {
        marshaller.generateEspdRequest(new EspdDocument(), xmlOutput)
        new XmlSlurper().parseText(xmlOutput.toString())
    }

    protected GPathResult parseXml(EspdDocument espdDocument) {
        marshaller.generateEspdRequest(espdDocument, xmlOutput)
        new XmlSlurper().parseText(xmlOutput.toString())
    }
}
