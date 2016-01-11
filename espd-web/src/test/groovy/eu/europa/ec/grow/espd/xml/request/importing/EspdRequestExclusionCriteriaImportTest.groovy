package eu.europa.ec.grow.espd.xml.request.importing

import eu.europa.ec.grow.espd.constants.enums.Country
import eu.europa.ec.grow.espd.xml.base.AbstractXmlFileImport
import org.apache.commons.io.IOUtils

/**
 * Created by ratoico on 11/25/15.
 */
class EspdRequestExclusionCriteriaImportTest extends AbstractXmlFileImport {

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

    def "all exclusion criteria should be selected"() {
        given:
        def espdRequestXml = importXmlRequestFile("all_exclusion_criteria_selected.xml")

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
        espd.paymentSocialSecurity.exists == true

        then: "should have all environmental"
        espd.breachingObligationsEnvironmental.exists == true

        then: "should have all insolvency"
        espd.bankruptcy.exists == true
        espd.insolvency.exists == true
        espd.analogousSituation.exists == true
        espd.assetsAdministeredByLiquidator.exists == true
        espd.businessActivitiesSuspended.exists == true

        then: "should have all misconduct"
        espd.arrangementWithCreditors.exists == true
        espd.guiltyGrave.exists == true

        then: "should have all conflict of interest"
        espd.conflictInterest.exists == true
        espd.involvementPreparationProcurement.exists == true
        espd.earlyTermination.exists == true
        espd.guiltyMisinterpretation.exists == true

        then: "national"
        espd.purelyNationalGrounds.exists == true
    }

    def "no exclusion criteria should appear as selected"() {
        given:
        def espdRequestXml = importXmlRequestFile("no_exclusion_criteria_selected.xml")

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
        espd.paymentSocialSecurity.exists == false

        then: "environmental"
        espd.breachingObligationsEnvironmental.exists == false

        then: "insolvency"
        espd.bankruptcy.exists == false
        espd.insolvency.exists == false
        espd.analogousSituation.exists == false
        espd.assetsAdministeredByLiquidator.exists == false
        espd.businessActivitiesSuspended.exists == false

        then: "misconduct"
        espd.arrangementWithCreditors.exists == false
        espd.guiltyGrave.exists == false

        then: "conflict of interest"
        espd.conflictInterest.exists == false
        espd.involvementPreparationProcurement.exists == false
        espd.earlyTermination.exists == false
        espd.guiltyMisinterpretation.exists == false

        then: "national"
        espd.purelyNationalGrounds.exists == false
    }

}