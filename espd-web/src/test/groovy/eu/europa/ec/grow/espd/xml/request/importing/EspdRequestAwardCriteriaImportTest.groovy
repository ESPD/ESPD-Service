package eu.europa.ec.grow.espd.xml.request.importing
import eu.europa.ec.grow.espd.xml.base.AbstractXmlFileImport
import org.apache.commons.io.IOUtils

/**
 * Created by ratoico on 1/20/16 at 2:39 PM.
 */
class EspdRequestAwardCriteriaImportTest extends AbstractXmlFileImport {

    def "all award criteria should be selected for a ESPD request"() {
        given:
        def espdRequestXml = importXmlRequestFile("all_award_criteria_selected.xml")

        when:
        def espd = marshaller.importEspdRequest(IOUtils.toInputStream(espdRequestXml)).get()

        then: "should have all award criteria"
        espd.meetsObjective.exists == true
    }

}