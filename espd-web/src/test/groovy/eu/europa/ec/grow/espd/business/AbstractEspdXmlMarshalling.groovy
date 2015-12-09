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
    protected static Jaxb2Marshaller jaxb2Marshaller= new JaxbConfiguration().jaxb2Marshaller()

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
        def contractingPartyTransformer = new ToContractingPartyTransformer()
        def criterionGroupTransformer = new ToCriterionGroupTransformer(new ToCriterionRequirementTransformer())
        def toCriterionTypeTransformer = new CcvCriterionToCriterionTypeTransformer(criterionGroupTransformer)
        def requestCriteriaTransformer = new EspdRequestCriteriaTransformer(toCriterionTypeTransformer)
        def toEspdRequestTransformer = new EspdDocumentToEspdRequestTransformer(commonUblFactory, contractingPartyTransformer, requestCriteriaTransformer)
        def toPartyImplTransformer = new ToPartyImplTransformer()
        def toEspdDocumentTransformer = new EspdRequestToEspdDocumentTransformer(toPartyImplTransformer)
        def responseCriteriaTransformer = new EspdResponseCriteriaTransformer(toCriterionTypeTransformer)
        def toEspdResponseTransformer = new EspdDocumentToEspdResponseTransformer(commonUblFactory, contractingPartyTransformer, responseCriteriaTransformer)
        marshaller = new EspdExchangeMarshaller(jaxb2Marshaller, toEspdRequestTransformer, toEspdDocumentTransformer, toEspdResponseTransformer)
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

    protected static void checkCriterionId(def request, int idx, String expectedId) {
        assert request.Criterion[idx].CriterionID.text() == expectedId
        assert request.Criterion[idx].CriterionID.@schemeAgencyID.text() == "EU-COM-GROW"
        assert request.Criterion[idx].CriterionID.@schemeVersionID.text() == "1.0"
        assert request.Criterion[idx].CriterionID.@schemeID.text() == "CriteriaID"
    }

    protected static void checkCriterionTypeCode(def request, int idx, String expectedTypeCode) {
        assert request.Criterion[idx].CriterionTypeCode.text() == expectedTypeCode
        assert request.Criterion[idx].CriterionTypeCode.@listAgencyID.text() == "EU-COM-GROW"
        assert request.Criterion[idx].CriterionTypeCode.@listID.text() == "CriteriaTypeCode"
        assert request.Criterion[idx].CriterionTypeCode.@listVersionID.text() == "1.0"
    }

    protected static void checkLegislationReference(def request, int idx, String expectedArticle) {
        def ref = request.Criterion[idx].CriterionLegislationReference

        assert ref.LegislationTitle.text() == "DIRECTIVE 2014/24/EU OF THE EUROPEAN PARLIAMENT AND OF THE COUNCIL of 26 February 2014 on public procurement and repealing Directive 2004/18/EC"
        assert ref.LegislationDescription.text() == "Directive 2014/24/EU"
        assert ref.JurisdictionLevelCode.text() == "EU_DIRECTIVE"
        assert ref.JurisdictionLevelCode.@listAgencyID.text() == "EU-COM-GROW"
        assert ref.JurisdictionLevelCode.@listID.text() == "CriterionJurisdictionLevelCode"
        assert ref.JurisdictionLevelCode.@listVersionID.text() == "1.0"
        assert ref.LegislationArticle.text() == expectedArticle
        assert ref.LegislationURIID.text() == "http://eur-lex.europa.eu/legal-content/EN/TXT/?uri=celex:32014L0024"
    }
}
