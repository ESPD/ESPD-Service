package eu.europa.ec.grow.espd.business

import eu.europa.ec.grow.espd.config.JaxbConfiguration
import eu.europa.ec.grow.espd.domain.EspdDocument
import groovy.util.slurpersupport.GPathResult
import org.springframework.oxm.jaxb.Jaxb2Marshaller
import spock.lang.Shared
import spock.lang.Specification


/**
 * Created by vigi on 11/17/15:3:54 PM.
 */
class EspdRequestExclusionCriteriaTest extends Specification {

    @Shared
    Jaxb2Marshaller jaxb2Marshaller

    @Shared
    EspdDocumentToEspdRequestTransformer toEspdRequestTransformer

    @Shared
    EspdExchangeMarshaller marshaller

    StringWriter out

    void setupSpec() {
        jaxb2Marshaller = new JaxbConfiguration().jaxb2Marshaller()
        def contractingPartyTransformer = new ToContractingPartyTransformer()
        def ccvCriterionTransformer = new CcvCriterionTransformer()
        toEspdRequestTransformer = new EspdDocumentToEspdRequestTransformer(contractingPartyTransformer, ccvCriterionTransformer)
        marshaller = new EspdExchangeMarshaller(jaxb2Marshaller, toEspdRequestTransformer)
    }

    void cleanupSpec() {
        marshaller = null
        toEspdRequestTransformer = null
        jaxb2Marshaller = null
    }

    void setup() {
        out = new StringWriter()
    }

    void cleanup() {
        out = null
    }

    def "should contain grounds relating to criminal convictions"() {
        when:
        def result = parseXml()
        println out.toString()

        then: "CriterionID element"
        result.Criterion[0].CriterionID.text() == "005eb9ed-1347-4ca3-bb29-9bc0db64e1ab"
        result.Criterion[0].CriterionID.@schemeAgencyID.text() == "COM_DG_GROW"
        result.Criterion[0].CriterionID.@schemeVersionID.text() == "1.0"
        result.Criterion[0].CriterionID.@schemeID.text() == "CriteriaID"

        then: "CriterionTypeCode element"
        result.Criterion[0].CriterionTypeCode.text() == "EXCLUSION.CRIMINAL_CONVICTIONS"
        result.Criterion[0].CriterionTypeCode.@listAgencyID.text() == "COM_DG_GROW"
        result.Criterion[0].CriterionTypeCode.@listID.text() == "CriteriaTypeCode"
        result.Criterion[0].CriterionTypeCode.@listVersionID.text() == "1.0"

        then: "CriterionName element"
        result.Criterion[0].CriterionName.text() == "Grounds relating to criminal convictions"

        then: "CriterionDescription element"
        result.Criterion[0].CriterionDescription.text() == "Within the past five years, has the economic operator itself or any person who is a member of its administrative, management or supervisory body or has powers of representation, decision or control therein been the subject of a conviction by final judgment for criminal conviction, such as participation in a criminal organisation, as defined in Article 2 of Council Framework Decision 2008/841/JHA?"
    }

    private GPathResult parseXml() {
        marshaller.generateEspdRequest(new EspdDocument(), out)
        new XmlSlurper().parseText(out.toString())
    }

}