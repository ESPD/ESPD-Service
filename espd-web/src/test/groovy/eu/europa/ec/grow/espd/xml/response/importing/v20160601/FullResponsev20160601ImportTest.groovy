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

package eu.europa.ec.grow.espd.xml.response.importing.v20160601

import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.enums.other.Country
import eu.europa.ec.grow.espd.xml.LocalDateAdapter
import eu.europa.ec.grow.espd.xml.LocalTimeAdapter
import eu.europa.ec.grow.espd.xml.base.AbstractXmlFileImport
import org.joda.time.LocalDate
import org.joda.time.LocalTime
import spock.lang.Shared

/**
 * Created by ratoico on 7/25/16.
 */
class FullResponsev20160601ImportTest extends AbstractXmlFileImport {

    @Shared
    static EspdDocument espd

    void setupSpec() {
        // init objects run before the first feature method
        espd = parseXmlResponseFile("v20160601/espd-response-20160601-full.xml")
    }

    void cleanupSpec() {
        espd = null
    }

    def "should import economic operator party full information"() {
        expect:
        espd.economicOperator.name == "Ben larbi"
        espd.economicOperator.website == "https://www.yahoo.com/"
        espd.economicOperator.vatNumber == "B207781243"
        espd.economicOperator.anotherNationalId == "eo another national identification number"
        espd.economicOperator.street == "Roma avenue 16"
        espd.economicOperator.postalCode == "00199"
        espd.economicOperator.city == "Roma"
        espd.economicOperator.country == Country.IT
        espd.economicOperator.contactName == "Giacommo Dino"
        espd.economicOperator.contactPhone == "0039456456"
        espd.economicOperator.contactEmail == "liban@mailinator.com"
        espd.economicOperator.isSmallSizedEnterprise == true
    }

    def "should import economic operator representative full information"() {
        given:
        def representative = espd.economicOperator.representatives[0]

        expect:
        representative.firstName == "Keith"
        representative.lastName == "Sweat"
        representative.dateOfBirth == LocalDateAdapter.unmarshal("1965-09-28").toDate()
        representative.placeOfBirth == "Naples"
        representative.street == "Pisa 256"
        representative.postalCode == "10256"
        representative.city == "roma"
        representative.country == Country.IT
        representative.email == "galabru@mailinator.com"
        representative.phone == "0039565656"
        representative.position == "representative position"
        representative.additionalInfo == "The Agris Mundus fees are the same."
    }

    def "should import lots information"() {
        expect:
        espd.lotConcerned == "Z_Z group"
    }

    def "should import espd request full information"() {
        expect:
        espd.requestMetadata.id == "c821c012-a5aa-49de-8cd4-8e2efd978af3"
        espd.requestMetadata.description == "ESPDRequest 1233"
        LocalDateAdapter.marshal(new LocalDate(espd.requestMetadata.issueDate)) == "2016-04-19"
        LocalTimeAdapter.marshal(new LocalTime(espd.requestMetadata.issueDate)) == "13:51:42"
    }

    def "should parse TED procurement procedure information"() {
        expect:
        espd.fileRefByCA == "SMART 2015/0065"
        espd.ojsNumber == "1234/S 567-891234"
        espd.procedureTitle == "Royal procurer title"
        espd.procedureShortDesc == "to lecture students on a topic for a time slot."
        espd.tedUrl == "http://ted.europa.eu/udl?uri=TED:NOTICE:12345-2016:TEXT:EN:HTML"
    }

    def "01. should import all fields of 'Participation in a criminal organisation'"() {
        expect:
        espd.criminalConvictions.exists == true
        espd.criminalConvictions.answer == true
        espd.criminalConvictions.dateOfConviction == LocalDateAdapter.unmarshal("2015-12-09").toDate()
        espd.criminalConvictions.reason == "Participation in a criminal organisation"
        espd.criminalConvictions.convicted == "Mr Dragon A"
        espd.criminalConvictions.periodLength == "6 years"

        and: "self cleaning"
        espd.criminalConvictions.selfCleaning.answer == true
        espd.criminalConvictions.selfCleaning.description == "Many causes and conditions can impair mobility and movement."

        and: "info electronically"
        espd.criminalConvictions.availableElectronically.answer == true
        espd.criminalConvictions.availableElectronically.url == "https://www.lds.org/topics/disability/list/physical-disability?lang=eng"
        espd.criminalConvictions.availableElectronically.code == "Participation in a criminal organisation"
    }

    def "02. should import all fields of 'Corruption'"() {
        expect:
        espd.corruption.exists == true
        espd.corruption.answer == true
        espd.corruption.dateOfConviction == LocalDateAdapter.unmarshal("2015-07-14").toDate()
        espd.corruption.reason == "Corruption"
        espd.corruption.convicted == "Mr Dragon B"
        espd.corruption.periodLength == "2"

        and: "self cleaning"
        espd.corruption.selfCleaning.answer == true
        espd.corruption.selfCleaning.description == "Get yer godforsaken bonnie lass out of me teeth!"

        and: "info electronically"
        espd.corruption.availableElectronically.answer == true
        espd.corruption.availableElectronically.url == "https://www.lds.org/topics/disability/list/physical-disability?lang=eng"
        espd.corruption.availableElectronically.code == "Corruption code"
    }

    def "03. should import all fields of 'Fraud'"() {
        expect:
        espd.fraud.exists == true
        espd.fraud.answer == true
        espd.fraud.dateOfConviction == LocalDateAdapter.unmarshal("2014-10-08").toDate()
        espd.fraud.reason == "T' slash or not t' slash: tis th' question."
        espd.fraud.convicted == "Mr Dragon C"
        espd.fraud.periodLength == "3"

        and: "self cleaning"
        espd.fraud.selfCleaning.answer == true
        espd.fraud.selfCleaning.description == "May I always be pillagin' an' swashbucklin', me plank monkey."

        and: "info electronically"
        espd.fraud.availableElectronically.answer == true
        espd.fraud.availableElectronically.url == "http://www.example.com/images/pic1.jpg"
        espd.fraud.availableElectronically.code == "Fraud code"
    }

    def "04. should import all fields of 'Terrorist offences'"() {
        expect:
        espd.terroristOffences.exists == true
        espd.terroristOffences.answer == true
        espd.terroristOffences.dateOfConviction == LocalDateAdapter.unmarshal("2015-11-04").toDate()
        espd.terroristOffences.reason == "I'm yer lad, an' yer me clay-brained numbskull. For real, that be!"
        espd.terroristOffences.convicted == "Mr Dragon D"
        espd.terroristOffences.periodLength == "6 y"

        and: "self cleaning"
        espd.terroristOffences.selfCleaning.answer == true
        espd.terroristOffences.selfCleaning.description == "Terrorist offences or offences linked to terrorist activities code descr"

        and: "info electronically"
        espd.terroristOffences.availableElectronically.answer == true
        espd.terroristOffences.availableElectronically.url == "http://www.example.com/images/pic2.jpg"
        espd.terroristOffences.availableElectronically.code == "Terrorist offences or offences linked to terrorist activities code"
    }

    def "05. should import all fields of 'Money laundering'"() {
        expect:
        espd.moneyLaundering.exists == true
        espd.moneyLaundering.answer == true
        espd.moneyLaundering.dateOfConviction == LocalDateAdapter.unmarshal("2014-08-14").toDate()
        espd.moneyLaundering.reason == "T' plank or not t' plank: tis th' question."
        espd.moneyLaundering.convicted == "Mr Dragon E"
        espd.moneyLaundering.periodLength == "8 Y"

        and: "self cleaning"
        espd.moneyLaundering.selfCleaning.answer == true
        espd.moneyLaundering.selfCleaning.description == "Money laundering or terrorist financing code descr"

        and: "info electronically"
        espd.moneyLaundering.availableElectronically.answer == true
        espd.moneyLaundering.availableElectronically.url == "http://www.example.com/images/pic3.jpg"
        espd.moneyLaundering.availableElectronically.code == "Money laundering or terrorist financing code"
    }

    def "06. should import all fields of 'Child labour'"() {
        expect:
        espd.childLabour.exists == true
        espd.childLabour.answer == true
        espd.childLabour.dateOfConviction == LocalDateAdapter.unmarshal("2015-05-13").toDate()
        espd.childLabour.reason == "Yarrr, give me yer booty lest I hornswaggle yer putrid face."
        espd.childLabour.convicted == "Mr Dragon F"
        espd.childLabour.periodLength == "3"

        and: "self cleaning"
        espd.childLabour.selfCleaning.answer == true
        espd.childLabour.selfCleaning.description == "Child labour and other forms of trafficking in human beings code descr"

        and: "info electronically"
        espd.childLabour.availableElectronically.answer == true
        espd.childLabour.availableElectronically.url == "http://www.example.com/images/pic4.jpg"
        espd.childLabour.availableElectronically.code == "Child labour and other forms of trafficking in human beings code"
    }

    def "07. should import all fields of 'Payment of taxes'"() {
        expect:
        espd.paymentTaxes.exists == true
        espd.paymentTaxes.answer == true
        espd.paymentTaxes.country == Country.FR
        espd.paymentTaxes.amount == 300.0
        espd.paymentTaxes.currency == "EUR"

        and: "first subgroup"
        espd.paymentTaxes.breachEstablishedOtherThanJudicialDecision == true
        espd.paymentTaxes.meansDescription == "how compassion, sensitivity, and sincerity by respecting the individual’s wishes."

        and: "second subgroup"
        espd.paymentTaxes.decisionFinalAndBinding == true
        espd.paymentTaxes.dateOfConviction == LocalDateAdapter.unmarshal("2015-05-13").toDate()
        espd.paymentTaxes.periodLength == "3 months"

        and: "third subgroup"
        espd.paymentTaxes.eoFulfilledObligations == true
        espd.paymentTaxes.obligationsDescription == "Maintain a balance between helping and allowing the individual to grow."

        and: "info electronically"
        espd.paymentTaxes.availableElectronically.answer == true
        espd.paymentTaxes.availableElectronically.url == "https://www.lds.org/topics/disability/list/physical-disability?lang=ro"
        espd.paymentTaxes.availableElectronically.code == "Payment of taxes code"
    }

    def "08. should import all fields of 'Payment of social security'"() {
        expect:
        espd.paymentSocialSecurity.exists == true
        espd.paymentSocialSecurity.answer == true
        espd.paymentSocialSecurity.country == Country.ES
        espd.paymentSocialSecurity.amount == 600.0
        espd.paymentSocialSecurity.currency == "EUR"

        and: "first subgroup"
        espd.paymentSocialSecurity.breachEstablishedOtherThanJudicialDecision == true
        espd.paymentSocialSecurity.meansDescription == "May ye always be swabbin' an' scallywaggin', me deckhand."

        and: "second subgroup"
        espd.paymentSocialSecurity.decisionFinalAndBinding == true
        espd.paymentSocialSecurity.dateOfConviction == LocalDateAdapter.unmarshal("2002-02-14").toDate()
        espd.paymentSocialSecurity.periodLength == "4 months"

        and: "third subgroup"
        espd.paymentSocialSecurity.eoFulfilledObligations == true
        espd.paymentSocialSecurity.obligationsDescription == "A wise bilge rat keeps only comp'ny with a slimy rum junkie an' a putrid bung hole."

        and: "info electronically"
        espd.paymentSocialSecurity.availableElectronically.answer == true
        espd.paymentSocialSecurity.availableElectronically.url == "http://www.example.com/images/pic5.jpg"
        espd.paymentSocialSecurity.availableElectronically.code == "Payment of social security code"
    }

    def "09. should import all fields of 'Breaching of obligations in the fields of environmental law'"() {
        expect:
        espd.breachingObligationsEnvironmental.exists == true
        espd.breachingObligationsEnvironmental.answer == true
        espd.breachingObligationsEnvironmental.description == "People with mobility and movement impairments may find it difficult to participate when facing social and physical barriers."

        and: "self cleaning"
        espd.breachingObligationsEnvironmental.selfCleaning.answer == true
        espd.breachingObligationsEnvironmental.selfCleaning.description == "Quite often they are individuals of courage and independence."
    }

    def "10. should import all fields of 'Breaching of obligations in the fields of social law'"() {
        expect:
        espd.breachingObligationsSocial.exists == true
        espd.breachingObligationsSocial.answer == true
        espd.breachingObligationsSocial.description == "Once a rum junkie, always a rum junkie. Yo ho!"

        and: "self cleaning"
        espd.breachingObligationsSocial.selfCleaning.answer == true
        espd.breachingObligationsSocial.selfCleaning.description == "Breaching of obligations in the field of social law cleaning descr"
    }

    def "11. should import all fields of 'Breaching of obligations in the fields of labour law'"() {
        expect:
        espd.breachingObligationsLabour.exists == true
        espd.breachingObligationsLabour.answer == true
        espd.breachingObligationsLabour.description == "By the foul bowels of Davy Jones! Why be we keelhaulin' so cockeredly?"

        and: "self cleaning"
        espd.breachingObligationsLabour.selfCleaning.answer == true
        espd.breachingObligationsLabour.selfCleaning.description == "Breaching of obligations in the fields of labour law cleaning descr"
    }

    def "12. should import all fields of 'Bankruptcy'"() {
        expect:
        espd.bankruptcy.exists == true
        espd.bankruptcy.answer == true
        espd.bankruptcy.description == "Learn about the disability and how you can help."
        espd.bankruptcy.reason == "Get to know the person and the caregiver."

        and: "info electronically"
        espd.bankruptcy.availableElectronically.answer == true
        espd.bankruptcy.availableElectronically.url == "https://www.lds.org/topics/disability/list/physical-disability?lang=it"
        espd.bankruptcy.availableElectronically.code == "Bankruptcy code"
    }

    def "13. should import all fields of 'Insolvency'"() {
        expect:
        espd.insolvency.exists == true
        espd.insolvency.answer == true
        espd.insolvency.description == "Bilge rats! Stab that blubbery cackle fruit!"
        espd.insolvency.reason == "Broadsidin', swabbin' an' slashin' — that be how we sail."

        and: "info electronically"
        espd.insolvency.availableElectronically.answer == true
        espd.insolvency.availableElectronically.url == "http://www.example.com/images/pic6.jpg"
        espd.insolvency.availableElectronically.code == "Insolvency code"
    }

    def "14. should import all fields of 'Arrangement with creditors'"() {
        expect:
        espd.arrangementWithCreditors.exists == true
        espd.arrangementWithCreditors.answer == true
        espd.arrangementWithCreditors.description == "Cursed wenches o' fire! Why be they screamin' so cockeredly?"
        espd.arrangementWithCreditors.reason == "I think that be his peg-leg. Begad!"

        and: "info electronically"
        espd.arrangementWithCreditors.availableElectronically.answer == true
        espd.arrangementWithCreditors.availableElectronically.url == "http://www.example.com/images/pic7.jpg"
        espd.arrangementWithCreditors.availableElectronically.code == "Arrangement with creditors code"
    }

    def "15. should import all fields of 'Analogous situation like bankruptcy under national law'"() {
        expect:
        espd.analogousSituation.exists == true
        espd.analogousSituation.answer == true
        espd.analogousSituation.description == "Whence I be done mutinizin', I'm going t' stab their rum."
        espd.analogousSituation.reason == "Ye be plunderin' like a fancy blaggard."

        and: "info electronically"
        espd.analogousSituation.availableElectronically.answer == true
        espd.analogousSituation.availableElectronically.url == "http://www.example.com/images/pic8.jpg"
        espd.analogousSituation.availableElectronically.code == "Analogous situation like bankruptcy under national law code"
    }

    def "16. should import all fields of 'Assets being administered by liquidator'"() {
        expect:
        espd.assetsAdministeredByLiquidator.exists == true
        espd.assetsAdministeredByLiquidator.answer == true
        espd.assetsAdministeredByLiquidator.description == "Yarrr, give me yer flintlock lest I rob yer rotten beard."
        espd.assetsAdministeredByLiquidator.reason == "She be pillagin' like a haggard cap'n."

        and: "info electronically"
        espd.assetsAdministeredByLiquidator.availableElectronically.answer == true
        espd.assetsAdministeredByLiquidator.availableElectronically.url == "http://www.example.com/images/pic9.jpg"
        espd.assetsAdministeredByLiquidator.availableElectronically.code == "Assets being administered by liquidator code"
    }

    def "17. should import all fields of 'Business activities are suspended'"() {
        expect:
        espd.businessActivitiesSuspended.exists == true
        espd.businessActivitiesSuspended.answer == true
        espd.businessActivitiesSuspended.description == "I be cuttin' off their teeth an' feedin' it to me clay-brained numbskull."
        espd.businessActivitiesSuspended.reason == "I'm yer goblin, an' yer me seadog. For real, that be!"

        and: "info electronically"
        espd.businessActivitiesSuspended.availableElectronically.answer == true
        espd.businessActivitiesSuspended.availableElectronically.url == "http://www.example.com/images/pic10.jpg"
        espd.businessActivitiesSuspended.availableElectronically.code == "Business activities are suspended code"
    }

    def "18. should import all fields of 'Guilty of grave professional misconduct'"() {
        expect:
        espd.guiltyGrave.exists == true
        espd.guiltyGrave.answer == true
        espd.guiltyGrave.description == "This may include providing ramps."

        and: "self cleaning"
        espd.guiltyGrave.selfCleaning.answer == true
        espd.guiltyGrave.selfCleaning.description == "Guilty of grave professional misconduct descr"
    }

    def "19. should import all fields of 'Agreements with other economic operators aimed at distorting competition'"() {
        expect:
        espd.agreementsWithOtherEO.exists == true
        espd.agreementsWithOtherEO.answer == true
        espd.agreementsWithOtherEO.description == "Prepare to accommodate individuals"

        and: "self cleaning"
        espd.agreementsWithOtherEO.selfCleaning.answer == true
        espd.agreementsWithOtherEO.selfCleaning.description == "Agreements with other economic operators aimed at distorting competition descr"
    }

    def "20. should import all fields of 'Conflict of interest due to its participation in the procurement procedure'"() {
        expect:
        espd.conflictInterest.exists == true
        espd.conflictInterest.answer == true
    }

    def "21. should import all fields of 'Direct or indirect involvement in the preparation of this procurement procedure'"() {
        expect:
        espd.involvementPreparationProcurement.exists == true
        espd.involvementPreparationProcurement.answer == true
        espd.involvementPreparationProcurement.description == "Once a cap'n, always a cap'n. Bucket o' bladder stench!"
    }

    def "22. should import all fields of 'Early termination'"() {
        expect:
        espd.earlyTermination.exists == true
        espd.earlyTermination.answer == true
        espd.earlyTermination.description == "Shiver me timbers! Shark-chum that flea-bitten heartie!"

        and: "self cleaning"
        espd.earlyTermination.selfCleaning.answer == true
        espd.earlyTermination.selfCleaning.description == "Early termination, damages or other comparable sanctions  descr"
    }

    def "23. should import all fields of 'Guilty of misinterpretation'"() {
        expect:
        espd.guiltyMisinterpretation.exists == true
        espd.guiltyMisinterpretation.answer == true
    }

    def "24. should import all fields of 'Purely national exclusion grounds'"() {
        expect:
        espd.purelyNationalGrounds.exists == true
        espd.purelyNationalGrounds.answer == true
        espd.purelyNationalGrounds.description == "This may include providing ramps, seating accommodations"

        and: "info electronically"
        espd.purelyNationalGrounds.availableElectronically.answer == true
        espd.purelyNationalGrounds.availableElectronically.url == "https://www.lds.org/topics/disability/list/physical-disability?lang=hodor"
        espd.purelyNationalGrounds.availableElectronically.code == "Purely national exclusion grounds code"
    }

    def "01. should import all fields of 'Satisfies all'"() {
        expect:
        espd.selectionSatisfiesAll.exists == true
        espd.selectionSatisfiesAll.answer == false
    }

    def "02. should import all fields of 'Enrolment in a relevant professional register'"() {
        expect:
        espd.enrolmentProfessionalRegister.exists == true
        espd.enrolmentProfessionalRegister.answer == true

        and: "info electronically"
        espd.enrolmentProfessionalRegister.availableElectronically.answer == true
        espd.enrolmentProfessionalRegister.availableElectronically.url == "https://www.lds.org/topics/disability/list/physical-disability?lang=nl"
        espd.enrolmentProfessionalRegister.availableElectronically.code == "Enrolment in a relevant professional register code"
    }

    def "03. should import all fields of 'Enrolment in a trade register'"() {
        expect:
        espd.enrolmentTradeRegister.exists == true
        espd.enrolmentTradeRegister.answer == true

        and: "info electronically"
        espd.enrolmentTradeRegister.availableElectronically.answer == true
        espd.enrolmentTradeRegister.availableElectronically.url == "http://www.example.com/images/pic11.jpg"
        espd.enrolmentTradeRegister.availableElectronically.code == "Enrolment in a trade register code"
    }

    def "04. should import all fields of 'For service contracts: authorisation of particular organisation needed'"() {
        expect:
        espd.serviceContractsAuthorisation.exists == true

        and: "selection criteria with no answer have a default value of true"
        espd.serviceContractsAuthorisation.answer == true
        espd.serviceContractsAuthorisation.description == "Nope"

        and: "info electronically"
        espd.serviceContractsAuthorisation.availableElectronically.answer == true
        espd.serviceContractsAuthorisation.availableElectronically.url == "https://www.lds.org/topics/disability/list/physical-disability?lang=fr"
        espd.serviceContractsAuthorisation.availableElectronically.code == "For service contracts: authorisation of particular organisation needed code"
    }

    def "05. should import all fields of 'For service contracts: membership  of particular organisation needed'"() {
        expect:
        espd.serviceContractsMembership.exists == true

        and: "selection criteria with no answer have a default value of true"
        espd.serviceContractsMembership.answer == true
        espd.serviceContractsMembership.description == "Get yer disloyal parrot out of me special pirate parts!"

        and: "info electronically"
        espd.serviceContractsMembership.availableElectronically.answer == true
        espd.serviceContractsMembership.availableElectronically.url == "http://www.example.com/images/pic12.jpg"
        espd.serviceContractsMembership.availableElectronically.code == "For service contracts: membership of particular organisation needed code"
    }

    def "06. should import all fields of 'General yearly turnover'"() {
        expect:
        espd.generalYearlyTurnover.exists == true
        espd.generalYearlyTurnover.answer == true

        and:
        espd.generalYearlyTurnover.year1 == 2012
        espd.generalYearlyTurnover.amount1 == 1000000.0
        espd.generalYearlyTurnover.currency1 == "EUR"

        and:
        espd.generalYearlyTurnover.year2 == 2013
        espd.generalYearlyTurnover.amount2 == 2000000.0
        espd.generalYearlyTurnover.currency2 == "RON"

        and:
        espd.generalYearlyTurnover.year3 == 2014
        espd.generalYearlyTurnover.amount3 == 3000000.0
        espd.generalYearlyTurnover.currency3 == "USD"

        and:
        espd.generalYearlyTurnover.year4 == 2015
        espd.generalYearlyTurnover.amount4 == 4000000.0
        espd.generalYearlyTurnover.currency4 == "CHF"

        and:
        espd.generalYearlyTurnover.year5 == 2016
        espd.generalYearlyTurnover.amount5 == 5000000.0
        espd.generalYearlyTurnover.currency5 == "PLN"

        and: "info electronically"
        espd.generalYearlyTurnover.availableElectronically.answer == true
        espd.generalYearlyTurnover.availableElectronically.url == "https://www.lds.org/topics/disability/list/physical-disability?lang=eng"
        espd.generalYearlyTurnover.availableElectronically.code == "General yearly turnover code"
    }

    def "07. should import all fields of 'Average yearly turnover'"() {
        expect:
        espd.averageYearlyTurnover.exists == true
        espd.averageYearlyTurnover.answer == true

        and:
        espd.averageYearlyTurnover.year1 == 2012
        espd.averageYearlyTurnover.amount1 == 2000000.0
        espd.averageYearlyTurnover.currency1 == "EUR"

        and:
        espd.averageYearlyTurnover.year2 == 2013
        espd.averageYearlyTurnover.amount2 == 2000001.0
        espd.averageYearlyTurnover.currency2 == "EUR"

        and:
        espd.averageYearlyTurnover.year3 == 2014
        espd.averageYearlyTurnover.amount3 == 100000.0
        espd.averageYearlyTurnover.currency3 == "EUR"

        and:
        espd.averageYearlyTurnover.year4 == 2015
        espd.averageYearlyTurnover.amount4 == 1000000.0
        espd.averageYearlyTurnover.currency4 == "CHF"

        and:
        espd.averageYearlyTurnover.year5 == 2011
        espd.averageYearlyTurnover.amount5 == 200004.53
        espd.averageYearlyTurnover.currency5 == "RON"

        and: "info electronically"
        espd.averageYearlyTurnover.availableElectronically.answer == true
        espd.averageYearlyTurnover.availableElectronically.url == "http://www.example.com/images/pic13.jpg"
        espd.averageYearlyTurnover.availableElectronically.code == "Average yearly turnover code"
    }

    def "08. should import all fields of 'Specific yearly turnover'"() {
        expect:
        espd.specificYearlyTurnover.exists == true
        espd.specificYearlyTurnover.answer == true

        and:
        espd.specificYearlyTurnover.year1 == 2012
        espd.specificYearlyTurnover.amount1 == 5000000.775555
        espd.specificYearlyTurnover.currency1 == "EUR"

        and:
        espd.specificYearlyTurnover.year2 == 2013
        espd.specificYearlyTurnover.amount2 == 100000.0
        espd.specificYearlyTurnover.currency2 == "RON"

        and:
        espd.specificYearlyTurnover.year3 == 2014
        espd.specificYearlyTurnover.amount3 == 200000.0
        espd.specificYearlyTurnover.currency3 == "EUR"

        and:
        espd.specificYearlyTurnover.year4 == 2015
        espd.specificYearlyTurnover.amount4 == 300000.0
        espd.specificYearlyTurnover.currency4 == "CHF"

        and:
        espd.specificYearlyTurnover.year5 == 2011
        espd.specificYearlyTurnover.amount5 == 400000.0
        espd.specificYearlyTurnover.currency5 == "EUR"

        and: "info electronically"
        espd.specificYearlyTurnover.availableElectronically.answer == true
        espd.specificYearlyTurnover.availableElectronically.url == "http://www.example.com/images/pic14.jpg"
        espd.specificYearlyTurnover.availableElectronically.code == "Specific yearly turnover code"
    }

    def "09. should import all fields of 'Specific average turnover'"() {
        expect:
        espd.specificAverageTurnover.exists == true
        espd.specificAverageTurnover.answer == true

        and:
        espd.specificAverageTurnover.year1 == 2012
        espd.specificAverageTurnover.amount1 == 1000000.0
        espd.specificAverageTurnover.currency1 == "EUR"

        and:
        espd.specificAverageTurnover.year2 == 2013
        espd.specificAverageTurnover.amount2 == 2000003.0
        espd.specificAverageTurnover.currency2 == "USD"

        and:
        espd.specificAverageTurnover.year3 == 2014
        espd.specificAverageTurnover.amount3 == 3000000.1234
        espd.specificAverageTurnover.currency3 == "EUR"

        and:
        espd.specificAverageTurnover.year4 == 2015
        espd.specificAverageTurnover.amount4 == 10.0001
        espd.specificAverageTurnover.currency4 == "EUR"

        and:
        espd.specificAverageTurnover.year5 == 2011
        espd.specificAverageTurnover.amount5 == 5000000.0
        espd.specificAverageTurnover.currency5 == "RON"

        and: "info electronically"
        espd.specificAverageTurnover.availableElectronically.answer == true
        espd.specificAverageTurnover.availableElectronically.url == "http://www.example.com/images/pic15.jpg"
        espd.specificAverageTurnover.availableElectronically.code == "Specific average turnover code"
    }

    def "10. should import all fields of 'Set up of economic operator'"() {
        expect: "selection criteria with no answer have a default value of true"
        espd.setupEconomicOperator.answer == true
        espd.setupEconomicOperator.year1 == 2014
    }

    def "11. should import all fields of 'Financial ratio'"() {
        expect:
        espd.financialRatio.exists == true

        and: "selection criteria with no answer have a default value of true"
        espd.financialRatio.answer == true

        and:
        espd.financialRatio.description1 == "TEST_1"
        espd.financialRatio.ratio1 == 1.0

        and:
        espd.financialRatio.description2 == "TEST_2"
        espd.financialRatio.ratio2 == 2.0

        and:
        espd.financialRatio.description3 == "TEST_3"
        espd.financialRatio.ratio3 == 3.0

        and:
        espd.financialRatio.description4 == "TEST_4"
        espd.financialRatio.ratio4 == 4.0

        and:
        espd.financialRatio.description5 == "TEST_5"
        espd.financialRatio.ratio5 == 5.0

        and: "info electronically"
        espd.financialRatio.availableElectronically.answer == true
        espd.financialRatio.availableElectronically.url == "https://www.lds.org/topics/disability/list/physical-disability?lang=pt"
        espd.financialRatio.availableElectronically.code == "Financial ratio code"
    }

    def "12. should import all fields of 'Professional risk indemnity insurance'"() {
        expect:
        espd.professionalRiskInsurance.exists == true
        espd.professionalRiskInsurance.answer == true

        and:
        espd.professionalRiskInsurance.amount1 == 3000000000000000
        espd.professionalRiskInsurance.currency1 == "RON"

        and: "info electronically"
        espd.professionalRiskInsurance.availableElectronically.answer == true
        espd.professionalRiskInsurance.availableElectronically.url == "https://www.lds.org/topics/disability/list/physical-disability?lang=rou"
        espd.professionalRiskInsurance.availableElectronically.code == "Professional risk indemnity insurance code"
    }

    def "13. should import all fields of 'Other economic or financial requirements'"() {
        expect:
        espd.otherEconomicFinancialRequirements.exists == true
        espd.otherEconomicFinancialRequirements.answer == true

        and:
        espd.otherEconomicFinancialRequirements.description == "Great Oden's Ghost! Slash that pale clay-brained numbskull!"

        and: "info electronically"
        espd.otherEconomicFinancialRequirements.availableElectronically.answer == true
        espd.otherEconomicFinancialRequirements.availableElectronically.url == "http://www.example.com/images/pic16.jpg"
        espd.otherEconomicFinancialRequirements.availableElectronically.code == "Other economic or financial requirements code"
    }

    def "14. should import all fields of 'For works contracts: performance of works of the specified type'"() {
        expect:
        espd.workContractsPerformanceOfWorks.exists == true
        espd.workContractsPerformanceOfWorks.answer == true

        and:
        espd.workContractsPerformanceOfWorks.description1 == "TEST 1"
        espd.workContractsPerformanceOfWorks.amount1 == 100000.0
        espd.workContractsPerformanceOfWorks.currency1 == "EUR"
        espd.workContractsPerformanceOfWorks.date1 == LocalDateAdapter.unmarshal("2016-03-16").toDate()
        espd.workContractsPerformanceOfWorks.recipients1 == "recipients1"

        and:
        espd.workContractsPerformanceOfWorks.description2 == "TEST 2"
        espd.workContractsPerformanceOfWorks.amount2 == 200000.0
        espd.workContractsPerformanceOfWorks.currency2 == "RON"
        espd.workContractsPerformanceOfWorks.date2 == LocalDateAdapter.unmarshal("2016-01-06").toDate()
        espd.workContractsPerformanceOfWorks.recipients2 == "recipients2"

        and:
        espd.workContractsPerformanceOfWorks.description3 == "TEST 3"
        espd.workContractsPerformanceOfWorks.amount3 == 300000.0
        espd.workContractsPerformanceOfWorks.currency3 == "USD"
        espd.workContractsPerformanceOfWorks.date3 == LocalDateAdapter.unmarshal("2016-01-19").toDate()
        espd.workContractsPerformanceOfWorks.recipients3 == "recipients3"

        and:
        espd.workContractsPerformanceOfWorks.description4 == "TEST 4"
        espd.workContractsPerformanceOfWorks.amount4 == 400000.0
        espd.workContractsPerformanceOfWorks.currency4 == "CHF"
        espd.workContractsPerformanceOfWorks.date4 == LocalDateAdapter.unmarshal("2015-08-21").toDate()
        espd.workContractsPerformanceOfWorks.recipients4 == "recipients4"

        and:
        espd.workContractsPerformanceOfWorks.description5 == "TEST 5"
        espd.workContractsPerformanceOfWorks.amount5 == 500000.0
        espd.workContractsPerformanceOfWorks.currency5 == "PLN"
        espd.workContractsPerformanceOfWorks.date5 == LocalDateAdapter.unmarshal("2015-10-27").toDate()
        espd.workContractsPerformanceOfWorks.recipients5 == "recipients5"

        and: "info electronically"
        espd.workContractsPerformanceOfWorks.availableElectronically.answer == true
        espd.workContractsPerformanceOfWorks.availableElectronically.url == "http://www.agrismundus.eu/Partners/"
        espd.workContractsPerformanceOfWorks.availableElectronically.code == "For works contracts: performance of works of the specified type code"
    }

    def "15. should import all fields of 'For supply contracts: performance of deliveries of the specified type'"() {
        expect:
        espd.supplyContractsPerformanceDeliveries.exists == true
        espd.supplyContractsPerformanceDeliveries.answer == true

        and:
        espd.supplyContractsPerformanceDeliveries.description1 == "TEST A"
        espd.supplyContractsPerformanceDeliveries.amount1 == 200000.0
        espd.supplyContractsPerformanceDeliveries.currency1 == "EUR"
        espd.supplyContractsPerformanceDeliveries.date1 == LocalDateAdapter.unmarshal("2016-04-05").toDate()
        espd.supplyContractsPerformanceDeliveries.recipients1 == "rec 1"

        and:
        espd.supplyContractsPerformanceDeliveries.description2 == "TEST B"
        espd.supplyContractsPerformanceDeliveries.amount2 == 300000.0
        espd.supplyContractsPerformanceDeliveries.currency2 == "RON"
        espd.supplyContractsPerformanceDeliveries.date2 == LocalDateAdapter.unmarshal("2016-02-05").toDate()
        espd.supplyContractsPerformanceDeliveries.recipients2 == "rec 2"

        and:
        espd.supplyContractsPerformanceDeliveries.description3 == "TEST C"
        espd.supplyContractsPerformanceDeliveries.amount3 == 500000.0
        espd.supplyContractsPerformanceDeliveries.currency3 == "USD"
        espd.supplyContractsPerformanceDeliveries.date3 == LocalDateAdapter.unmarshal("2015-12-09").toDate()
        espd.supplyContractsPerformanceDeliveries.recipients3 == "rec 3"

        and:
        espd.supplyContractsPerformanceDeliveries.description4 == "TEST D"
        espd.supplyContractsPerformanceDeliveries.amount4 == 600000.0
        espd.supplyContractsPerformanceDeliveries.currency4 == "PLN"
        espd.supplyContractsPerformanceDeliveries.date4 == LocalDateAdapter.unmarshal("2016-01-06").toDate()
        espd.supplyContractsPerformanceDeliveries.recipients4 == "rec 4"

        and:
        espd.supplyContractsPerformanceDeliveries.description5 == "TEST E"
        espd.supplyContractsPerformanceDeliveries.amount5 == 800000.0
        espd.supplyContractsPerformanceDeliveries.currency5 == "EUR"
        espd.supplyContractsPerformanceDeliveries.date5 == LocalDateAdapter.unmarshal("2015-10-27").toDate()
        espd.supplyContractsPerformanceDeliveries.recipients5 == "rec 5"

        and: "info electronically"
        espd.supplyContractsPerformanceDeliveries.availableElectronically.answer == true
        espd.supplyContractsPerformanceDeliveries.availableElectronically.url == "http://www.selection/crit15.jpg"
        espd.supplyContractsPerformanceDeliveries.availableElectronically.code == "For supply contracts: performance of deliveries of the specified type code"
    }

    def "16. should import all fields of 'For service contracts: performance of services of the specified type'"() {
        expect:
        espd.serviceContractsPerformanceServices.exists == true
        espd.serviceContractsPerformanceServices.answer == true

        and:
        espd.serviceContractsPerformanceServices.description1 == "F"
        espd.serviceContractsPerformanceServices.amount1 == 100001.0
        espd.serviceContractsPerformanceServices.currency1 == "EUR"
        espd.serviceContractsPerformanceServices.date1 == LocalDateAdapter.unmarshal("2016-04-20").toDate()
        espd.serviceContractsPerformanceServices.recipients1 == "r 1"

        and:
        espd.serviceContractsPerformanceServices.description2 == "G"
        espd.serviceContractsPerformanceServices.amount2 == 100002.0
        espd.serviceContractsPerformanceServices.currency2 == "RON"
        espd.serviceContractsPerformanceServices.date2 == LocalDateAdapter.unmarshal("2016-01-04").toDate()
        espd.serviceContractsPerformanceServices.recipients2 == "r 2"

        and:
        espd.serviceContractsPerformanceServices.description3 == "H"
        espd.serviceContractsPerformanceServices.amount3 == 100003.3
        espd.serviceContractsPerformanceServices.currency3 == "USD"
        espd.serviceContractsPerformanceServices.date3 == LocalDateAdapter.unmarshal("2016-02-09").toDate()
        espd.serviceContractsPerformanceServices.recipients3 == "r 3"

        and:
        espd.serviceContractsPerformanceServices.description4 == "I"
        espd.serviceContractsPerformanceServices.amount4 == 400004.0
        espd.serviceContractsPerformanceServices.currency4 == "PLN"
        espd.serviceContractsPerformanceServices.date4 == LocalDateAdapter.unmarshal("2016-01-01").toDate()
        espd.serviceContractsPerformanceServices.recipients4 == "r 4"

        and:
        espd.serviceContractsPerformanceServices.description5 == "J"
        espd.serviceContractsPerformanceServices.amount5 == 555.5556
        espd.serviceContractsPerformanceServices.currency5 == "EUR"
        espd.serviceContractsPerformanceServices.date5 == LocalDateAdapter.unmarshal("2015-12-29").toDate()
        espd.serviceContractsPerformanceServices.recipients5 == "r 5"

        and: "info electronically"
        espd.serviceContractsPerformanceServices.availableElectronically.answer == true
        espd.serviceContractsPerformanceServices.availableElectronically.url == "http://www.selection/crit16.jpg"
        espd.serviceContractsPerformanceServices.availableElectronically.code == "For service contracts: performance of services of the specified type code"
    }

    def "17. should import all fields of 'Technicians or technical bodies for quality control'"() {
        expect:
        espd.techniciansTechnicalBodies.exists == true
        espd.techniciansTechnicalBodies.answer == true

        and:
        espd.techniciansTechnicalBodies.description == "Curse th' wench! I'd rather lose me peg-leg."

        and: "info electronically"
        espd.techniciansTechnicalBodies.availableElectronically.answer == true
        espd.techniciansTechnicalBodies.availableElectronically.url == "http://www.selection/crit17.jpg"
        espd.techniciansTechnicalBodies.availableElectronically.code == "Technicians or technical bodies for quality control code"
    }

    def "18. should import all fields of 'For works contracts: technicians or technical bodies to carry out the work'"() {
        expect:
        espd.workContractsTechnicians.exists == true
        espd.workContractsTechnicians.answer == true

        and:
        espd.workContractsTechnicians.description == "Ye scallywags! Blast that malmsey-nosed landlubber!"

        and: "info electronically"
        espd.workContractsTechnicians.availableElectronically.answer == true
        espd.workContractsTechnicians.availableElectronically.url == "http://www.selection/crit18.jpg"
        espd.workContractsTechnicians.availableElectronically.code == "For works contracts: technicians or technical bodies to carry out the work code"
    }

    def "19. should import all fields of 'Technical facilities and measures for ensuring quality'"() {
        expect:
        espd.technicalFacilitiesMeasures.exists == true
        espd.technicalFacilitiesMeasures.answer == true

        and:
        espd.technicalFacilitiesMeasures.description == "I shall rob that monkey with my scupper. Curse ye!"

        and: "info electronically"
        espd.technicalFacilitiesMeasures.availableElectronically.answer == true
        espd.technicalFacilitiesMeasures.availableElectronically.url == "http://www.selection/crit19.jpg"
        espd.technicalFacilitiesMeasures.availableElectronically.code == "Technical facilities and measures for ensuring quality code"
    }

    def "20. should import all fields of 'Study and research facilities'"() {
        expect:
        espd.studyResearchFacilities.exists == true
        espd.studyResearchFacilities.answer == true

        and:
        espd.studyResearchFacilities.description == "By the foul bowels of Davy Jones! Why be I knifin' so cockeredly?"

        and: "info electronically"
        espd.studyResearchFacilities.availableElectronically.answer == true
        espd.studyResearchFacilities.availableElectronically.url == "http://www.selection/crit20.jpg"
        espd.studyResearchFacilities.availableElectronically.code == "Study and research facilities code"
    }

    def "21. should import all fields of 'Supply chain management'"() {
        expect:
        espd.supplyChainManagement.exists == true
        espd.supplyChainManagement.answer == true

        and:
        espd.supplyChainManagement.description == "Get yer foul dagger out of me peg-leg!"

        and: "info electronically"
        espd.supplyChainManagement.availableElectronically.answer == true
        espd.supplyChainManagement.availableElectronically.url == "http://www.selection/crit21.jpg"
        espd.supplyChainManagement.availableElectronically.code == "Supply chain management code"
    }

    def "22. should import all fields of 'Allowance of checks'"() {
        expect:
        espd.allowanceOfChecks.exists == true
        espd.allowanceOfChecks.answer == true
    }

    def "23. should import all fields of 'Educational and professional qualifications'"() {
        expect:
        espd.educationalProfessionalQualifications.exists == true
        espd.educationalProfessionalQualifications.answer == true

        and:
        espd.educationalProfessionalQualifications.description == "A wise boot-licker keeps only comp'ny with a haggard bilge rat an' a pale cutlass."

        and: "info electronically"
        espd.educationalProfessionalQualifications.availableElectronically.answer == true
        espd.educationalProfessionalQualifications.availableElectronically.url == "http://www.selection/crit23.jpg"
        espd.educationalProfessionalQualifications.availableElectronically.code == "Educational and professional qualifications code"
    }

    def "24. should import all fields of 'Environmental management measures'"() {
        expect:
        espd.environmentalManagementFeatures.exists == true
        espd.environmentalManagementFeatures.answer == true

        and:
        espd.environmentalManagementFeatures.description == "Scallywaggin', weighin' anchor' an' broadsidin' — that be how ye sail."

        and: "info electronically"
        espd.environmentalManagementFeatures.availableElectronically.answer == true
        espd.environmentalManagementFeatures.availableElectronically.url == "http://www.selection/crit24.jpg"
        espd.environmentalManagementFeatures.availableElectronically.code == "Environmental management measures code"
    }

    def "25. should import all fields of 'Number of managerial staff'"() {
        expect:
        espd.numberManagerialStaff.exists == true
        espd.numberManagerialStaff.answer == true

        and:
        espd.numberManagerialStaff.year1 == 2014
        espd.numberManagerialStaff.number1 == 123

        and:
        espd.numberManagerialStaff.year2 == 2015
        espd.numberManagerialStaff.number2 == 456

        and:
        espd.numberManagerialStaff.year3 == 2013
        espd.numberManagerialStaff.number3 == 789

        and: "info electronically"
        espd.numberManagerialStaff.availableElectronically.answer == true
        espd.numberManagerialStaff.availableElectronically.url == "http://www.agrismundus.eu/"
        espd.numberManagerialStaff.availableElectronically.code == "Number of managerial staff code"
    }

    def "26. should import all fields of 'Average annual manpower'"() {
        expect:
        espd.averageAnnualManpower.exists == true
        espd.averageAnnualManpower.answer == true

        and:
        espd.averageAnnualManpower.year1 == 2014
        espd.averageAnnualManpower.number1 == 321

        and:
        espd.averageAnnualManpower.year2 == 2015
        espd.averageAnnualManpower.number2 == 654

        and:
        espd.averageAnnualManpower.year3 == 2010
        espd.averageAnnualManpower.number3 == 987

        and: "info electronically"
        espd.averageAnnualManpower.availableElectronically.answer == true
        espd.averageAnnualManpower.availableElectronically.url == "http://www.selection/crit26.jpg"
        espd.averageAnnualManpower.availableElectronically.code == "Average annual manpower code"
    }

    def "27. should import all fields of 'Tools, plant or technical equipment'"() {
        expect:
        espd.toolsPlantTechnicalEquipment.exists == true
        espd.toolsPlantTechnicalEquipment.answer == true

        and:
        espd.toolsPlantTechnicalEquipment.description == "I care not for a flea-bitten lassie. Just be givin' me th' swag!"

        and: "info electronically"
        espd.toolsPlantTechnicalEquipment.availableElectronically.answer == true
        espd.toolsPlantTechnicalEquipment.availableElectronically.url == "http://www.selection/crit27.jpg"
        espd.toolsPlantTechnicalEquipment.availableElectronically.code == "Tools, plant or technical equipment code"
    }

    def "28. should import all fields of 'Subcontracting proportion'"() {
        expect:
        espd.subcontractingProportion.exists == true
        espd.subcontractingProportion.specify == "General training objective : to train students to cope with the current global/international concerns in agriculture and rural development to."
    }

    def "29. should import all fields of 'For supply contracts: samples, descriptions or photographs without certification of authenticity'"() {
        expect:
        espd.supplyContractsSamplesDescriptionsWithoutCa.exists == true
        espd.supplyContractsSamplesDescriptionsWithoutCa.answer == true

        and: "info electronically"
        espd.supplyContractsSamplesDescriptionsWithoutCa.availableElectronically.answer == true
        espd.supplyContractsSamplesDescriptionsWithoutCa.availableElectronically.url == "http://www.selection/crit29.jpg"
        espd.supplyContractsSamplesDescriptionsWithoutCa.availableElectronically.code == "For supply contracts: samples, descriptions or photographs without certification of authenticity code"
    }

    def "30. should import all fields of 'For supply contracts: samples, descriptions or photographs with certification of authenticity'"() {
        expect:
        espd.supplyContractsSamplesDescriptionsWithCa.exists == true
        espd.supplyContractsSamplesDescriptionsWithCa.answer == true

        and: "info electronically"
        espd.supplyContractsSamplesDescriptionsWithCa.availableElectronically.answer == true
        espd.supplyContractsSamplesDescriptionsWithCa.availableElectronically.url == "http://www.selection/crit30.jpg"
        espd.supplyContractsSamplesDescriptionsWithCa.availableElectronically.code == "For supply contracts: samples, descriptions or photographs with certification of authenticity code"
    }

    def "31. should import all fields of 'For supply contracts: certificates by quality control institutes'"() {
        expect:
        espd.supplyContractsCertificatesQc.exists == true
        espd.supplyContractsCertificatesQc.answer == false
        espd.supplyContractsCertificatesQc.description == "Only a plank monkey can be plunderin' on the piece-of-eight."

        and: "info electronically"
        espd.supplyContractsCertificatesQc.availableElectronically.answer == true
        espd.supplyContractsCertificatesQc.availableElectronically.url == "http://www.selection/crit31.jpg"
        espd.supplyContractsCertificatesQc.availableElectronically.code == "For supply contracts: certificates by quality control institutes code"
    }

    def "32. should import all fields of 'Certificates by independent bodies about quality assurance standards'"() {
        expect:
        espd.certificateIndependentBodiesAboutQa.exists == true
        espd.certificateIndependentBodiesAboutQa.answer == false
        espd.certificateIndependentBodiesAboutQa.description == "I be cuttin' off their peg-leg an' feedin' it to me deckhand."

        and: "info electronically"
        espd.certificateIndependentBodiesAboutQa.availableElectronically.answer == true
        espd.certificateIndependentBodiesAboutQa.availableElectronically.url == "http://www.selection/crit32.jpg"
        espd.certificateIndependentBodiesAboutQa.availableElectronically.code == "Certificates by independent bodies about quality assurance standards code"
    }

    def "33. should import all fields of 'Certificates by independent bodies about environmental management systems or standards'"() {
        expect:
        espd.certificateIndependentBodiesAboutEnvironmental.exists == true
        espd.certificateIndependentBodiesAboutEnvironmental.answer == false
        espd.certificateIndependentBodiesAboutEnvironmental.description == "I be cuttin' off her ol' poop deck an' feedin' it to me heartie."

        and: "info electronically"
        espd.certificateIndependentBodiesAboutEnvironmental.availableElectronically.answer == true
        espd.certificateIndependentBodiesAboutEnvironmental.availableElectronically.url == "http://www.selection/crit33.jpg"
        espd.certificateIndependentBodiesAboutEnvironmental.availableElectronically.code == "Certificates by independent bodies about environmental management systems or standards code"
    }

    def "01. should import all fields of 'Procurement reserved'"() {
        expect:
        espd.procurementReserved.exists == true
        espd.procurementReserved.answer == true
        espd.procurementReserved.doubleValue1 == 2.0
        espd.procurementReserved.description1 == "Physical Disabilities"
    }

    def "02. should import all fields of 'Economic operator registered'"() {
        expect:
        espd.eoRegistered.exists == true
        espd.eoRegistered.answer == true
        espd.eoRegistered.booleanValue1 == true
        espd.eoRegistered.booleanValue2 == false
        espd.eoRegistered.booleanValue3 == false
        espd.eoRegistered.description1 == "Bilge-for-brains! Stab that godforsaken porthole!"
        espd.eoRegistered.description2 == "Curse ye! Stab that disloyal botswain!"
        espd.eoRegistered.description3 == "Riggin', knifin' an' slashin' — that be how I sail."
        // description 4 field (part e) has been replaced by an indicator stored in booleanValue3
        espd.eoRegistered.description5 == null
    }

    def "03. should import all fields of 'Economic operator participating procurement procedure'"() {
        expect:
        espd.eoParticipatingProcurementProcedure.exists == true
        espd.eoParticipatingProcurementProcedure.answer == true
        espd.eoParticipatingProcurementProcedure.description1 == "Head of Department"
        espd.eoParticipatingProcurementProcedure.description2 == "Professor D"
        espd.eoParticipatingProcurementProcedure.description3 == "D_D group"
    }

    def "04. should import all fields of 'Economic operator relies capacities'"() {
        expect:
        espd.eoReliesCapacities.exists == true
        espd.eoReliesCapacities.answer == true
    }

    def "05. should import all fields of 'Subcontracting Third Parties'"() {
        expect:
        espd.subcontractingThirdParties.exists == true
        espd.subcontractingThirdParties.answer == true
        espd.subcontractingThirdParties.description1 == "GBN group"
    }

    def "06. should import all fields of 'Meets objective'"() {
        expect:
        espd.meetsObjective.exists == true

        and: "there is no answer"
        espd.meetsObjective.answer == true
        espd.meetsObjective.description1 == "A two-years programme of 120 ECTS"

        and: "info electronically"
        espd.meetsObjective.availableElectronically.answer == true
        espd.meetsObjective.availableElectronically.url == "http://www.agrismundus.eu/"
        espd.meetsObjective.availableElectronically.code == "reduction code"
    }

}