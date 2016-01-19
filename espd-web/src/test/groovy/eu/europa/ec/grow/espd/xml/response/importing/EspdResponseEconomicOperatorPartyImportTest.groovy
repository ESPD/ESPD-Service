package eu.europa.ec.grow.espd.xml.response.importing

import eu.europa.ec.grow.espd.constants.enums.Country
import eu.europa.ec.grow.espd.xml.base.AbstractXmlFileImport
import org.apache.commons.io.IOUtils

/**
 * Created by ratoico on 1/19/16 at 10:20 AM.
 */
class EspdResponseEconomicOperatorPartyImportTest extends AbstractXmlFileImport {

    def "should import economic operator party information"() {
        given:
        def espdRequestXml = importXmlResponseFile("economic_operator_party_full_import.xml")

        when:
        def espd = marshaller.importEspdResponse(IOUtils.toInputStream(espdRequestXml))

        then:
        espd.eoperator.name == "ACME Corp."
        espd.eoperator.website == "www.hodor.com"
        espd.eoperator.vatNumber == "B207781243"
        espd.eoperator.street == "Vitruvio"
        espd.eoperator.postalCode == "28006"
        espd.eoperator.city == "Madrid"
        espd.eoperator.country == Country.SPAIN
        espd.eoperator.contactName == "hodor"
        espd.eoperator.contactPhone == "+666"
        espd.eoperator.contactEmail == "hodor@hodor.com"
    }

}