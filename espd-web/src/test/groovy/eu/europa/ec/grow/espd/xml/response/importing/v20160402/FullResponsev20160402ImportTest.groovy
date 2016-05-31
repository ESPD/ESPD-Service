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

package eu.europa.ec.grow.espd.xml.response.importing.v20160402

import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.enums.other.Country
import eu.europa.ec.grow.espd.xml.LocalDateAdapter
import eu.europa.ec.grow.espd.xml.LocalTimeAdapter
import eu.europa.ec.grow.espd.xml.base.AbstractXmlFileImport
import org.joda.time.LocalDate
import org.joda.time.LocalTime
import spock.lang.Shared
/**
 * Backwards compatibility with version 20160402 test.
 *
 * Created by ratoico on 5/30/16.
 */
class FullResponsev20160402ImportTest extends AbstractXmlFileImport {

    @Shared
    static EspdDocument espd

    void setupSpec() {
        // init objects run before the first feature method
        espd = parseXmlResponseFile("v20160402/espd-response-20160402-full.xml")
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
        espd.economicOperator.country == Country.ITALY
        espd.economicOperator.contactName == "Giacommo Dino"
        espd.economicOperator.contactPhone == "0039456456"
        espd.economicOperator.contactEmail == "liban@mailinator.com"
        espd.economicOperator.isSmallSizedEnterprise == true
    }

    def "should import economic operator representative full information"() {
        expect:
        espd.economicOperator.representative.firstName == "Keith"
        espd.economicOperator.representative.lastName == "Sweat"
        espd.economicOperator.representative.dateOfBirth == LocalDateAdapter.unmarshal("1965-09-28").toDate()
        espd.economicOperator.representative.placeOfBirth == "Naples"
        espd.economicOperator.representative.street == "Pisa 256"
        espd.economicOperator.representative.postalCode == "10256"
        espd.economicOperator.representative.city == "roma"
        espd.economicOperator.representative.country == Country.ITALY
        espd.economicOperator.representative.email == "galabru@mailinator.com"
        espd.economicOperator.representative.phone == "0039565656"
        espd.economicOperator.representative.position == "representative position"
        espd.economicOperator.representative.additionalInfo == "The Agris Mundus fees are the same."
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

    def "07. should import all fields of 'Payment of taxes'"() {
        expect:
        espd.paymentTaxes.exists == true
        espd.paymentTaxes.answer == true
        espd.paymentTaxes.country == Country.FRANCE
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

    def "09. should import all fields of 'Breaching of obligations in the fields of environmental law'"() {
        expect:
        espd.breachingObligationsEnvironmental.exists == true
        espd.breachingObligationsEnvironmental.answer == true
        espd.breachingObligationsEnvironmental.description == "People with mobility and movement impairments may find it difficult to participate when facing social and physical barriers."

        and: "self cleaning"
        espd.breachingObligationsEnvironmental.selfCleaning.answer == true
        espd.breachingObligationsEnvironmental.selfCleaning.description == "Quite often they are individuals of courage and independence."
    }

    def "10. should import all fields of 'Bankruptcy'"() {
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

    def "18. should import all fields of 'Guilty of grave professional misconduct'"() {
        expect:
        espd.guiltyGrave.exists == true
        espd.guiltyGrave.answer == true
        espd.guiltyGrave.description == "This may include providing ramps."

        and: "self cleaning"
        espd.guiltyGrave.selfCleaning.answer == true
        espd.guiltyGrave.selfCleaning.description == "Guilty of grave professional misconduct descr"
    }

    def "19. should import all fields of 'Conflict of interest due to its participation in the procurement procedure'"() {
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

    def "21. should import all fields of 'Purely national exclusion grounds'"() {
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
        espd.selectionSatisfiesAll == null
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

    def "24. should import all fields of 'Number of managerial staff'"() {
        expect:
        espd.numberManagerialStaff.exists == true

        and: "answer is null and it is a selection criterion so the default value should be true"
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
        espd.eoRegistered.description1 == "3333"
        espd.eoRegistered.description2 == "erfezrazeraezraze"
        espd.eoRegistered.description3 == "fgfddsfdsqfqdsfqdsf"
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