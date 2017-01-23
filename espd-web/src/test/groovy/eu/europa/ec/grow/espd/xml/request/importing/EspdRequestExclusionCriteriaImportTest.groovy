/*
 *
 * Copyright 2016 EUROPEAN COMMISSION
 *
 * Licensed under the EUPL, Version 1.1 or – as soon they
 * will be approved by the European Commission - subsequent
 * versions of the EUPL (the "Licence");
 *
 * You may not use this work except in compliance with the Licence.
 *
 * You may obtain a copy of the Licence at:
 *
 * https://joinup.ec.europa.eu/community/eupl/og_page/eupl
 *
 * Unless required by applicable law or agreed to in
 * writing, software distributed under the Licence is
 * distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied.
 * See the Licence for the specific language governing
 * permissions and limitations under the Licence.
 *
 */

package eu.europa.ec.grow.espd.xml.request.importing

import eu.europa.ec.grow.espd.xml.base.AbstractXmlFileImport
/**
 * Created by ratoico on 11/25/15.
 */
class EspdRequestExclusionCriteriaImportTest extends AbstractXmlFileImport {

    def "all exclusion criteria should be selected"() {
        when:
        def espd = parseXmlRequestFile("all_exclusion_criteria_selected.xml")

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
        when:
        def espd = parseXmlRequestFile("no_exclusion_criteria_selected.xml")

        then: "criminal convictions"
        espd.criminalConvictions == null
        espd.corruption == null
        espd.fraud == null
        espd.terroristOffences == null
        espd.moneyLaundering == null
        espd.childLabour == null

        then: "tax convictions"
        espd.paymentTaxes == null
        espd.paymentSocialSecurity == null

        then: "environmental"
        espd.breachingObligationsEnvironmental == null

        then: "insolvency"
        espd.bankruptcy == null
        espd.insolvency == null
        espd.arrangementWithCreditors == null
        espd.analogousSituation == null
        espd.assetsAdministeredByLiquidator == null
        espd.businessActivitiesSuspended == null

        then: "misconduct"
        espd.guiltyGrave == null
        espd.agreementsWithOtherEO == null

        then: "conflict of interest"
        espd.conflictInterest == null
        espd.involvementPreparationProcurement == null
        espd.earlyTermination == null
        espd.guiltyMisinterpretation == null

        then: "national"
        espd.purelyNationalGrounds == null
    }

}