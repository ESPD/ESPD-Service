package eu.europa.ec.grow.espd.xml.request

import eu.europa.ec.grow.espd.constants.enums.Country
import eu.europa.ec.grow.espd.domain.*
import eu.europa.ec.grow.espd.xml.base.AbstractCriteriaFixture

/**
 *  Created by vigi on 11/11/15:3:31 PM:11:56 AM.
 */
class EspdRequestMarshallingTest extends AbstractCriteriaFixture {

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
        given:
        def espd = new EspdDocument(fileRefByCA: "HODOR refd by CA")

        when:
        def result = parseRequestXml(espd)

        then:
        result.ContractFolderID.text() == "HODOR refd by CA"
        result.ContractFolderID.@schemeAgencyID.text() == "TeD"
    }

    def "should contain AdditionalDocumentReference element information"() {
        given:
        def espd = new EspdDocument(ojsNumber: "S206|2015-10-23|PN33|2015/S 206-373035",
                procedureTitle: "Belgium-Brussels: SMART 2015/0065 — Benchmarking deployment of eHealth among general practitioners 2015",
                procedureShortDesc: "Service category No 11: Management consulting services [6] and related services."
        )

        when:
        def result = parseRequestXml(espd)

        then:
        result.AdditionalDocumentReference[0].ID.text() == "S206|2015-10-23|PN33|2015/S 206-373035"
        result.AdditionalDocumentReference[0].ID.@schemeID.text() == "ISO/IEC 9834-8:2008 - 4UUID"
        result.AdditionalDocumentReference[0].ID.@schemeAgencyID.text() == "EU-COM-GROW"
        result.AdditionalDocumentReference[0].ID.@schemeAgencyName.text() == "DG GROW (European Commission)"
        result.AdditionalDocumentReference[0].ID.@schemeVersionID.text() == "1.1"

        then:
        result.AdditionalDocumentReference[0].DocumentTypeCode.@listAgencyID.text() == "EU-COM-GROW"
        result.AdditionalDocumentReference[0].DocumentTypeCode.@listID.text() == "ReferencesTypeCodes"
        result.AdditionalDocumentReference[0].DocumentTypeCode.@listVersionID.text() == "1.0"
        result.AdditionalDocumentReference[0].DocumentTypeCode.text() == "TeD_CN"

        then:
        result.AdditionalDocumentReference[0].Attachment.ExternalReference.FileName.text() == "Belgium-Brussels: SMART 2015/0065 — Benchmarking deployment of eHealth among general practitioners 2015"
        result.AdditionalDocumentReference[0].Attachment.ExternalReference.Description[0].text() == "Service category No 11: Management consulting services [6] and related services."
    }

    def "should transform ContractingParty element information"() {
        given:
        def authority = new PartyImpl(name: "  Hodor authority  ", vatNumber: "  Hodor national reg number  ",
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
        result.ContractingParty.Party.PostalAddress.Postbox.text() == "Hodor postcode"

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

    def "should contain all Criterion elements information even if none of them is selected from ESPD"() {
        when:
        def result = parseRequestXml()

        then:
        result.Criterion.size() == getTotalNumberOfCriteria()
    }

    def "should contain all exclusion and selection criteria"() {
        given:
        def espd = new EspdDocument(
                // exclusion
                criminalConvictions: new CriminalConvictionsCriterion(exists: true),
                corruption: new CriminalConvictionsCriterion(exists: true),
                fraud: new CriminalConvictionsCriterion(exists: true),
                terroristOffences: new CriminalConvictionsCriterion(exists: true),
                moneyLaundering: new CriminalConvictionsCriterion(exists: true),
                childLabour: new CriminalConvictionsCriterion(exists: true),
                paymentTaxes: new TaxesCriterion(exists: true),
                paymentSocialSecurity: new TaxesCriterion(exists: true),
                breachingObligationsEnvironmental: new LawCriterion(exists: true),
                breachingObligationsSocial: new LawCriterion(exists: true),
                breachingObligationsLabour: new LawCriterion(exists: true),
                bankruptcy: new BankruptcyCriterion(exists: true),
                insolvency: new BankruptcyCriterion(exists: true),
                arrangementWithCreditors: new BankruptcyCriterion(exists: true),
                analogousSituation: new BankruptcyCriterion(exists: true),
                assetsAdministeredByLiquidator: new BankruptcyCriterion(exists: true),
                businessActivitiesSuspended: new BankruptcyCriterion(exists: true),
                guiltyGrave: new MisconductDistortionCriterion(exists: true),
                conflictInterest: new ConflictInterestCriterion(exists: true),
                involvementPreparationProcurement: new ConflictInterestCriterion(exists: true),
                earlyTermination: new ConflictInterestCriterion(exists: true),
                guiltyMisinterpretation: new ConflictInterestCriterion(exists: true),
                purelyNationalGrounds: new PurelyNationalGrounds(exists: true),
                // selection
                enrolmentProfessionalRegister: new SuitabilityCriterion(exists: true),
                enrolmentTradeRegister: new SuitabilityCriterion(exists: true),
                serviceContractsAuthorisation: new SuitabilityCriterion(exists: true),
                serviceContractsMembership: new SuitabilityCriterion(exists: true),
                generalYearlyTurnover: new EconomicFinancialStandingCriterion(exists: true),
                averageYearlyTurnover: new EconomicFinancialStandingCriterion(exists: true),
                specificYearlyTurnover: new EconomicFinancialStandingCriterion(exists: true),
                specificAverageTurnover: new EconomicFinancialStandingCriterion(exists: true),
                financialRatio: new EconomicFinancialStandingCriterion(exists: true),
                professionalRiskInsurance: new EconomicFinancialStandingCriterion(exists: true),
                otherEconomicFinancialRequirements: new EconomicFinancialStandingCriterion(exists: true),
                workContractsPerformanceOfWorks: new TechnicalProfessionalCriterion(exists: true),
                supplyContractsPerformanceDeliveries: new TechnicalProfessionalCriterion(exists: true),
                serviceContractsPerformanceServices: new TechnicalProfessionalCriterion(exists: true),
                techniciansTechnicalBodies: new TechnicalProfessionalCriterion(exists: true),
                workContractsTechnicians: new TechnicalProfessionalCriterion(exists: true),
                technicalFacilitiesMeasures: new TechnicalProfessionalCriterion(exists: true),
                studyResearchFacilities: new TechnicalProfessionalCriterion(exists: true),
                supplyChainManagement: new TechnicalProfessionalCriterion(exists: true),
                allowanceOfChecks: new TechnicalProfessionalCriterion(exists: true),
                educationalProfessionalQualifications: new TechnicalProfessionalCriterion(exists: true),
                environmentalManagementFeatures: new TechnicalProfessionalCriterion(exists: true),
                numberManagerialStaff: new TechnicalProfessionalCriterion(exists: true),
                averageAnnualManpower: new TechnicalProfessionalCriterion(exists: true),
                toolsPlantTechnicalEquipment: new TechnicalProfessionalCriterion(exists: true),
                subcontractingProportion: new TechnicalProfessionalCriterion(exists: true),
                supplyContractsSamplesDescriptionsWithoutCa: new TechnicalProfessionalCriterion(exists: true),
                supplyContractsSamplesDescriptionsWithCa: new TechnicalProfessionalCriterion(exists: true),
                supplyContractsCertificatesQc: new TechnicalProfessionalCriterion(exists: true),
                certificateIndependentBodiesAboutQa: new TechnicalProfessionalCriterion(exists: true),
                certificateIndependentBodiesAboutEnvironmental: new TechnicalProfessionalCriterion(exists: true)
        )

        when:
        def result = parseRequestXml(espd)

        then:
        result.Criterion.size() == getTotalNumberOfCriteria()
    }

    def "should not fail when a criterion has a null exists flag"() {
        given:
        def espd = new EspdDocument(criminalConvictions: new CriminalConvictionsCriterion(exists: null))

        when:
        def result = parseRequestXml(espd)

        then:
        result.Criterion.size() == getTotalNumberOfCriteria()
    }

}
