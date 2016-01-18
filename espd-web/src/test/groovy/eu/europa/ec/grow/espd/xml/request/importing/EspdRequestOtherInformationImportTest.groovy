package eu.europa.ec.grow.espd.xml.request.importing

import eu.europa.ec.grow.espd.constants.enums.Country
import eu.europa.ec.grow.espd.xml.base.AbstractXmlFileImport
import org.apache.commons.io.IOUtils

/**
 * Created by ratoico on 1/18/16 at 2:56 PM.
 */
class EspdRequestOtherInformationImportTest extends AbstractXmlFileImport {

    def "should parse full authority information"() {
        given:
        def espdRequestXml = importXmlRequestFile("economic_operator_authority_full.xml")

        when:
        def espd = marshaller.importEspdRequest(IOUtils.toInputStream(espdRequestXml))

        then:
        espd.authority.name == "hodor"
        espd.authority.website == "www.hodor.com"
        espd.authority.nationalRegistrationNumber == "43354d43323"
        espd.authority.street == "elm street"
        espd.authority.postalCode == "1500"
        espd.authority.city == "drubetis"
        espd.authority.country == Country.ROMANIA
        espd.authority.contactName == "gogu"
        espd.authority.contactPhone == "+43435543"
        espd.authority.contactEmail == "gogu@gogu.com"
    }

    def "should parse minimal authority information"() {
        given:
        def espdRequestXml = importXmlRequestFile("party_authority_minimalistic.xml")

        when:
        def espd = marshaller.importEspdRequest(IOUtils.toInputStream(espdRequestXml))

        then:
        espd.authority.name == "hodor"
        espd.authority.website == null
        espd.authority.nationalRegistrationNumber == null
        espd.authority.street == null
        espd.authority.postalCode == null
        espd.authority.city == null
        espd.authority.country == Country.ROMANIA
        espd.authority.contactName == null
        espd.authority.contactPhone == null
        espd.authority.contactEmail == null
    }

    def "should parse procurement procedure information"() {
        given:
        def espdRequestXml = importXmlRequestFile("info_about_procurement_procedure_import.xml")

        when:
        def espd = marshaller.importEspdRequest(IOUtils.toInputStream(espdRequestXml))

        then:
        espd.fileRefByCA == "SMART 2016/0069"
        espd.ojsNumber == "S206|2015-10-23|PN33|2015/S206-373035"
        espd.procedureTitle == "Belgium-Brussels: SMART 2015/0065 — Benchmarking deployment of eHealth among general practitioners 2015"
        espd.procedureShortDesc == "Service category No 11: Management consulting services [6] and related services."
    }

}