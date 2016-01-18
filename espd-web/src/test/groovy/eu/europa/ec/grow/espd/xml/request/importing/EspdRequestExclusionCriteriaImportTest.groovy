package eu.europa.ec.grow.espd.xml.request.importing

import eu.europa.ec.grow.espd.xml.base.AbstractXmlFileImport
import org.apache.commons.io.IOUtils
/**
 * Created by ratoico on 11/25/15.
 */
class EspdRequestExclusionCriteriaImportTest extends AbstractXmlFileImport {

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
        espd.arrangementWithCreditors.exists == true
        espd.analogousSituation.exists == true
        espd.assetsAdministeredByLiquidator.exists == true
        espd.businessActivitiesSuspended.exists == true

        then: "should have all misconduct"
        espd.guiltyGrave.exists == true
        espd.agreementsWithOtherEO.exists == true

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
        espd.arrangementWithCreditors.exists == false
        espd.analogousSituation.exists == false
        espd.assetsAdministeredByLiquidator.exists == false
        espd.businessActivitiesSuspended.exists == false

        then: "misconduct"
        espd.guiltyGrave.exists == false
        espd.agreementsWithOtherEO.exists == false

        then: "conflict of interest"
        espd.conflictInterest.exists == false
        espd.involvementPreparationProcurement.exists == false
        espd.earlyTermination.exists == false
        espd.guiltyMisinterpretation.exists == false

        then: "national"
        espd.purelyNationalGrounds.exists == false
    }

}