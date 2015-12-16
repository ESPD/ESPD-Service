package eu.europa.ec.grow.espd.business.request

import eu.europa.ec.grow.espd.business.AbstractEspdXmlMarshalling
import eu.europa.ec.grow.espd.constants.enums.Country
import eu.europa.ec.grow.espd.domain.*

/**
 *  Created by vigi on 11/11/15:3:31 PM:11:56 AM.
 */
class EspdRequestMarshallingTest extends AbstractEspdXmlMarshalling {

    def "should make sure that we use the correct XML namespaces"() {
        when:
        def result = parseRequestXml()

        then:
        result.lookupNamespace('espd-req') == 'urn:grow:names:specification:ubl:schema:xsd:ESPDRequest-1'
        result.lookupNamespace('espd') == 'urn:grow:names:specification:ubl:schema:xsd:ESPDResponse-1'
        result.lookupNamespace('espd-cac') == 'urn:grow:names:specification:ubl:schema:xsd:ESPD-CommonAggregateComponents-1'
        result.lookupNamespace('espd-cbc') == 'urn:grow:names:specification:ubl:schema:xsd:ESPD-CommonBasicComponents-1'
        result.lookupNamespace('cbc') == 'urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2'
        result.lookupNamespace('cac') == 'urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2'
        result.lookupNamespace('ccv') == 'urn:isa:names:specification:ubl:schema:xsd:CCV-CommonAggregateComponents-1'
        result.lookupNamespace('ccv-cbc') == 'urn:isa:names:specification:ubl:schema:xsd:CCV-CommonBasicComponents-1'
        result.lookupNamespace('cev-cbc') == 'urn:isa:names:specification:ubl:schema:xsd:CEV-CommonBasicComponents-1'
//        result.lookupNamespace('cev') == 'urn:isa:names:specification:ubl:schema:xsd:CEV-CommonAggregateComponents-1'
//        result.lookupNamespace('ext') == 'urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2'
    }

    def "should contain UBLVersionID element information"() {
        when:
        def result = parseRequestXml()

        then:
        result.UBLVersionID.text() == "2.1"
        result.UBLVersionID.@schemeAgencyID.text() == "OASIS-UBL-TC"
    }

    def "should contain CustomizationID element information"() {
        when:
        def result = parseRequestXml()

        then:
        result.CustomizationID.text() == "urn:www.cenbii.eu:transaction:biitrns070:ver3.0"
        result.CustomizationID.@schemeAgencyID.text() == "BII"
        result.CustomizationID.@schemeVersionID.text() == "3.0"
        result.CustomizationID.@schemeName.text() == "CustomizationID"
    }

    def "should contain ID element information"() {
        when:
        def result = parseRequestXml()

        then: "id is an UUID"
        result.ID.text().length() == 36

        then:
        result.ID.@schemeAgencyID.text() == "EU-COM-GROW"
        result.ID.@schemeAgencyName.text() == "DG GROW (European Commission)"
        result.ID.@schemeVersionID.text() == "1.1"
        result.ID.@schemeID.text() == "ISO/IEC 9834-8:2008 - 4UUID"
    }

    def "should contain CopyIndicator element information"() {
        when:
        def result = parseRequestXml()

        then:
        result.CopyIndicator.size() == 1
        result.CopyIndicator.toBoolean() == false
    }

    def "should contain VersionID element information"() {
        when:
        def result = parseRequestXml()

        then:
        result.VersionID.text() == "1"
        result.VersionID.@schemeAgencyID.text() == "EU-COM-GROW"
    }

    def "should contain IssueDate element information"() {
        when:
        def result = parseRequestXml()

        then: "issue date must match the date format YYYY-MM-dd"
        result.IssueDate.text() ==~ "\\d{4}-\\d{2}-\\d{2}"
    }

    def "should contain IssueTime element information"() {
        when:
        def result = parseRequestXml()

        then: "issue time must match the time format HH:mm:ss"
        result.IssueTime.text() ==~ "\\d{2}:\\d{2}:\\d{2}"
    }

    def "should contain ContractFolderID element information"() {
        when:
        def result = parseRequestXml()

        then:
        result.ContractFolderID.text() == "SMART 2015/0065"
        result.ContractFolderID.@schemeAgencyID.text() == "TeD"
    }

    def "should transform ContractingParty element information"() {
        given:
        def authority = new PartyImpl(name: "  Hodor authority  ", nationalRegistrationNumber: "  Hodor national reg number  ",
                street: "  Hodor street  ", postalCode: "  Hodor postcode  ", city: "  Hodor city  ", country: Country.ROMANIA,
                contactName: "  Hodor contact person  ", contactEmail: "  hodor@hodor.com  ", contactPhone: "  555-HODOR  ",
                website: "  www.hodor.com  ")
        def espd = new EspdDocument(authority: authority)

        when:
        def result = parseRequestXml(espd)

        then: "all values should be trimmed"
        result.ContractingParty.Party.PartyName.Name.text() == "Hodor authority"
        result.ContractingParty.Party.WebsiteURI.text() == "www.hodor.com"

        then: "party identification"
        result.ContractingParty.Party.PartyIdentification.ID.text() == "Hodor national reg number"

        then: "check address information"
        result.ContractingParty.Party.PostalAddress.Country.IdentificationCode.text() == "RO"
        result.ContractingParty.Party.PostalAddress.Country.IdentificationCode.@listAgencyID.text() == "ISO"
        result.ContractingParty.Party.PostalAddress.Country.IdentificationCode.@listName.text() == "ISO 3166-1"
        result.ContractingParty.Party.PostalAddress.Country.IdentificationCode.@listVersionID.text() == "1.0"
        result.ContractingParty.Party.PostalAddress.CityName.text() == "Hodor city"
        result.ContractingParty.Party.PostalAddress.StreetName.text() == "Hodor street"
        result.ContractingParty.Party.PostalAddress.PostalZone.text() == "Hodor postcode"

        then: "check contact information"
        result.ContractingParty.Party.Contact.Name.text() == "Hodor contact person"
        result.ContractingParty.Party.Contact.ElectronicMail.text() == "hodor@hodor.com"
        result.ContractingParty.Party.Contact.Telephone.text() == "555-HODOR"
    }

    def "should contain ProcurementProjectLot element information when there are no lots"() {
        when:
        def result = parseRequestXml()

        then: "In a Procurement Project with no Lots one ProcurementProjectLot element, and only one, MUST be included in the XML instance"
        result.ProcurementProjectLot.size() == 1

        then: "The identifier for this single ProcurementProjectLot MUST be the number 0"
        result.ProcurementProjectLot.ID.text() == "0"
    }

    def "should contain no Criterion elements information"() {
        when:
        def result = parseRequestXml()

        then:
        result.Criterion.size() == 0
    }

    def "should contain all exclusion and selection criteria (without satisfies all)"() {
        given:
        def espd = new EspdDocument(
                // exclusion
                criminalConvictions: new CriminalConvictions(exists: true),
                corruption: new CriminalConvictions(exists: true),
                fraud: new CriminalConvictions(exists: true),
                terroristOffences: new CriminalConvictions(exists: true),
                moneyLaundering: new CriminalConvictions(exists: true),
                childLabour: new CriminalConvictions(exists: true),
                paymentTaxes: new Taxes(exists: true),
                paymentSocialSecurity: new Taxes(exists: true),
                breachingObligations: new BreachOfObligations(exists: true),
                bankruptcy: new BreachOfObligations(exists: true),
                insolvency: new BreachOfObligations(exists: true),
                arrangementWithCreditors: new BreachOfObligations(exists: true),
                analogousSituation: new BreachOfObligations(exists: true),
                assetsAdministeredByLiquidator: new BreachOfObligations(exists: true),
                businessActivitiesSuspended: new BreachOfObligations(exists: true),
                guiltyGrave: new BreachOfObligations(exists: true),
                conflictInterest: new BreachOfObligations(exists: true),
                involvementPreparationProcurement: new BreachOfObligations(exists: true),
                earlyTermination: new BreachOfObligations(exists: true),
                guiltyMisinterpretation: new BreachOfObligations(exists: true),
                purelyNationalGrounds: new PurelyNationalGrounds(exists: true),
                // selection
                enrolmentProfessionalRegister: new SelectionCriterion(exists: true),
                enrolmentTradeRegister: new SelectionCriterion(exists: true),
                serviceContractsAuthorisation: new SelectionCriterion(exists: true),
                serviceContractsMembership: new SelectionCriterion(exists: true),
                generalYearlyTurnover: new SelectionCriterion(exists: true),
                averageYearlyTurnover: new SelectionCriterion(exists: true),
                specificYearlyTurnover: new SelectionCriterion(exists: true),
                specificAverageTurnover: new SelectionCriterion(exists: true),
                financialRatio: new SelectionCriterion(exists: true),
                professionalRiskInsurance: new SelectionCriterion(exists: true),
                otherEconomicFinancialRequirements: new SelectionCriterion(exists: true),
                workContractsPerformanceOfWorks: new SelectionCriterion(exists: true),
                supplyContractsPerformanceDeliveries: new SelectionCriterion(exists: true),
                serviceContractsPerformanceServices: new SelectionCriterion(exists: true),
                techniciansTechnicalBodies: new SelectionCriterion(exists: true),
                workContractsTechnicians: new SelectionCriterion(exists: true),
                technicalFacilitiesMeasures: new SelectionCriterion(exists: true),
                studyResearchFacilities: new SelectionCriterion(exists: true),
                supplyChainManagement: new SelectionCriterion(exists: true),
                allowanceOfChecks: new SelectionCriterion(exists: true),
                educationalProfessionalQualifications: new SelectionCriterion(exists: true),
                environmentalManagementFeatures: new SelectionCriterion(exists: true),
                numberManagerialStaff: new SelectionCriterion(exists: true),
                averageAnnualManpower: new SelectionCriterion(exists: true),
                toolsPlantTechnicalEquipment: new SelectionCriterion(exists: true),
                subcontractingProportion: new SelectionCriterion(exists: true),
                supplyContractsSamplesDescriptionsWithoutCa: new SelectionCriterion(exists: true),
                supplyContractsSamplesDescriptionsWithCa: new SelectionCriterion(exists: true),
                supplyContractsCertificatesQc: new SelectionCriterion(exists: true),
                certificateIndependentBodiesAboutQa: new SelectionCriterion(exists: true),
                certificateIndependentBodiesAboutEnvironmental: new SelectionCriterion(exists: true)
        )

        when:
        def result = parseRequestXml(espd)
//        def file = new File("/home/ratoico/Downloads/espd-request.xml")
//        file.text = xmlOutput

        then:
        result.Criterion.size() == 52
    }

    def "should not fail when a criterion has a null exists flag"() {
        given:
        def espd = new EspdDocument(criminalConvictions: new CriminalConvictions(exists: null))

        when:
        def result = parseRequestXml(espd)

        then:
        result.Criterion.size() == 0
    }

}
