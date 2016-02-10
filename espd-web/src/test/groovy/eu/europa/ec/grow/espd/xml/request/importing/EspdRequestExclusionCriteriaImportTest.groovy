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

        then: "should have all criminal convictions and their default answers should be false"
        espd.criminalConvictions.exists == true
        espd.criminalConvictions.answer == false
        espd.corruption.exists == true
        espd.corruption.answer == false
        espd.fraud.exists == true
        espd.fraud.answer == false
        espd.terroristOffences.exists == true
        espd.terroristOffences.answer == false
        espd.moneyLaundering.exists == true
        espd.moneyLaundering.answer == false
        espd.childLabour.exists == true
        espd.childLabour.answer == false

        then: "should have all tax convictions and their default answers should be false"
        espd.paymentTaxes.exists == true
        espd.paymentTaxes.answer == false
        espd.paymentSocialSecurity.exists == true
        espd.paymentSocialSecurity.answer == false

        then: "should have all environmental and their default answers should be false"
        espd.breachingObligationsEnvironmental.exists == true
        espd.breachingObligationsEnvironmental.answer == false

        then: "should have all insolvency and their default answers should be false"
        espd.bankruptcy.exists == true
        espd.bankruptcy.answer == false
        espd.insolvency.exists == true
        espd.insolvency.answer == false
        espd.arrangementWithCreditors.exists == true
        espd.arrangementWithCreditors.answer == false
        espd.analogousSituation.exists == true
        espd.analogousSituation.answer == false
        espd.assetsAdministeredByLiquidator.exists == true
        espd.assetsAdministeredByLiquidator.answer == false
        espd.businessActivitiesSuspended.exists == true
        espd.businessActivitiesSuspended.answer == false

        then: "should have all misconduct and their default answers should be false"
        espd.guiltyGrave.exists == true
        espd.guiltyGrave.answer == false
        espd.agreementsWithOtherEO.exists == true
        espd.agreementsWithOtherEO.answer == false

        then: "should have all conflict of interest and their default answers should be false"
        espd.conflictInterest.exists == true
        espd.conflictInterest.answer == false
        espd.involvementPreparationProcurement.exists == true
        espd.involvementPreparationProcurement.answer == false
        espd.earlyTermination.exists == true
        espd.earlyTermination.answer == false
        espd.guiltyMisinterpretation.exists == true
        espd.guiltyMisinterpretation.answer == false

        then: "national and their default answers should be false"
        espd.purelyNationalGrounds.exists == true
        espd.purelyNationalGrounds.answer == false
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