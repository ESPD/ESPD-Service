package eu.europa.ec.grow.espd.business
import eu.europa.ec.grow.espd.domain.EspdDocument
import grow.names.specification.ubl.schema.xsd.espdrequest_1.ESPDRequestType
import spock.lang.Specification
/**
 * Created by vigi on 11/11/15:11:08 AM.
 */
class EspdDocumentToEspdRequestTransformerTest extends Specification {

    EspdDocumentToEspdRequestTransformer transformer

    void setup() {
        transformer = new EspdDocumentToEspdRequestTransformer()
    }

    void cleanup() {
        transformer = null
    }

    def "should transform a basic ESPD Document into a ESPD Request"() {

        when:
        def result = transformer.apply(new EspdDocument())

        then:
        result == new ESPDRequestType()
    }
}
