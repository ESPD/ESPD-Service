package eu.europa.ec.grow.espd.business

import eu.europa.ec.grow.espd.constants.enums.Country
import org.apache.commons.io.IOUtils

/**
 * Created by ratoico on 11/25/15.
 */
class EspdDocumentUnmarshallingTest extends AbstractEspdXmlMarshalling {

    def "should parse full authority information"() {
        given:
        def espdRequestXml = readXmlFile("party_authority_full.xml")

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
        def espdRequestXml = readXmlFile("party_authority_minimalistic.xml")

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

    def "all exclusion criteria should be selected"() {
        given:
        def espdRequestXml = readXmlFile("all_exclusion_criteria_selected.xml")

        when:
        def espd = marshaller.importEspdRequest(IOUtils.toInputStream(espdRequestXml))

        then: "should have all criminal convictions"
        espd.criminalConvictions.exists == true
        espd.corruption.exists == true
        espd.fraud.exists == true
        espd.terroristOffences.exists == true
        espd.moneyLaundering.exists == true
        espd.childLabour.exists == true

        then: "should have all tax convictions"
        espd.paymentTaxes.exists == true
        espd.paymentSocsec.exists == true

        then: "should have all breach of obligations"
        espd.breachingObligations.exists == true
        espd.bankruptSubject.exists == true
        espd.guiltyGrave.exists == true
        espd.agreementsEo.exists == true
        espd.conflictInterest.exists == true
        espd.involvementPreparation.exists == true
        espd.earlyTermination.exists == true
        espd.guiltyMisinterpretation.exists == true
    }

    def "no exclusion criteria should appear as selected"() {
        given:
        def espdRequestXml = readXmlFile("no_exclusion_criteria_selected.xml")

        when:
        def espd = marshaller.importEspdRequest(IOUtils.toInputStream(espdRequestXml))

        then: "criminal convictions"
        espd.criminalConvictions.exists == false
        espd.corruption.exists == false
        espd.fraud.exists == false
        espd.terroristOffences.exists == false
        espd.moneyLaundering.exists == false
        espd.childLabour.exists == false

        then: "tax convictions"
        espd.paymentTaxes.exists == false
        espd.paymentSocsec.exists == false

        then: "breach of obligations"
        espd.breachingObligations.exists == false
        espd.bankruptSubject.exists == false
        espd.guiltyGrave.exists == false
        espd.agreementsEo.exists == false
        espd.conflictInterest.exists == false
        espd.involvementPreparation.exists == false
        espd.earlyTermination.exists == false
        espd.guiltyMisinterpretation.exists == false
    }

    private String readXmlFile(String fileName) {
        return new File("./src/test/groovy/eu/europa/ec/grow/espd/business/xmls/${fileName}").getText('UTF-8')
    }

}