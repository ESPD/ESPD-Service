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
    protected EspdExchangeMarshaller marshaller

    protected StringWriter xmlOutput

    void setupSpec() {
        jaxb2Marshaller = new JaxbConfiguration().jaxb2Marshaller()
        initEspdMarshaller(jaxb2Marshaller)
    }

    private void initEspdMarshaller(Jaxb2Marshaller jaxb2Marshaller) {
        def commonUblFactory = new CommonUblFactory()
        def contractingPartyTransformer = new ToContractingPartyTransformer()
        def toCriterionTypeTransformer = new CcvCriterionToCriterionTypeTransformer()
        def requestCriteriaTransformer = new EspdRequestCriteriaTransformer(toCriterionTypeTransformer)
        def toEspdRequestTransformer = new EspdDocumentToEspdRequestTransformer(commonUblFactory, contractingPartyTransformer, requestCriteriaTransformer)
        def toPartyImplTransformer = new ToPartyImplTransformer()
        def toEspdDocumentTransformer = new EspdRequestToEspdDocumentTransformer(toPartyImplTransformer)
        def toEspdResponseTransformer = new EspdDocumentToEspdResponseTransformer(commonUblFactory, contractingPartyTransformer, toCriterionTypeTransformer)
        marshaller = new EspdExchangeMarshaller(jaxb2Marshaller, toEspdRequestTransformer, toEspdDocumentTransformer, toEspdResponseTransformer)
    }

    void cleanupSpec() {
        marshaller = null
        jaxb2Marshaller = null
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
}
