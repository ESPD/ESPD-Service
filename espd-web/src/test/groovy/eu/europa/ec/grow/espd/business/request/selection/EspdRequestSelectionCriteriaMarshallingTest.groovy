package eu.europa.ec.grow.espd.business.request.selection

import eu.europa.ec.grow.espd.business.AbstractEspdXmlMarshalling
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.SelectionCriterion

/**
 *  Created by vigi on 11/19/15:3:32 PM.
 */
class EspdRequestSelectionCriteriaMarshallingTest extends AbstractEspdXmlMarshalling {

    def "should contain a single selection Criterion element if the economic operator claims that it satisfies all the criteria"() {
        given:
        def espd = new EspdDocument(selectionSatisfiesAll: new SelectionCriterion(exists: true),
                enrolmentProfessionalRegister: new SelectionCriterion(exists: true),
                enrolmentTradeRegister: new SelectionCriterion(exists: true),
                serviceContractsAuthorisation: new SelectionCriterion(exists: true),
                serviceContractsMembership: new SelectionCriterion(exists: true),)

        when:
        def request = parseRequestXml(espd)

        then: "one and only one selection criteria"
        request.Criterion.size() == 1

        then: "check the CriterionID"
        checkCriterionId(request, 0, "7e7db838-eeac-46d9-ab39-42927486f22d")

        then: "check the CriterionTypeCode"
        request.Criterion[0].TypeCode.text() == "SELECTION.ALL_CRITERIA_SATISFIED"
        request.Criterion[0].TypeCode.@listAgencyID.text() == "EU-COM-GROW"
        request.Criterion[0].TypeCode.@listID.text() == "CriteriaTypeCode"
        request.Criterion[0].TypeCode.@listVersionID.text() == "1.0"

        then: "check name and description"
        request.Criterion[0].Name.text() == "All selection criteria will be satisfied"
        request.Criterion[0].Description.text() == "The economic operator satisfies all the required selection criteria indicated in the relevant notice or in the procurement documents referred to in the notice."
    }

    def "all selection criteria (except satisfies all) should be in the correct order"() {
        given:
        def espd = new EspdDocument(
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
                certificateIndependentBodiesAboutEnvironmental: new SelectionCriterion(exists: true))

        when:
        def request = parseRequestXml(espd)

        then:
        request.Criterion.size() == 31
        checkCriterionId(request, 0, "6ee55a59-6adb-4c3a-b89f-e62a7ad7be7f")
        checkCriterionId(request, 1, "87b3fa26-3549-4f92-b8e0-3fd8f04bf5c7")
        checkCriterionId(request, 2, "9eeb6d5c-0eb8-48e8-a4c5-5087a7c095a4")
        checkCriterionId(request, 3, "73f10e36-ed7a-412e-995c-aa76463e3776")
        checkCriterionId(request, 4, "499efc97-2ac1-4af2-9e84-323c2ca67747")
        checkCriterionId(request, 5, "b16cb9fc-6cb7-4585-9302-9533b415cf48")
        checkCriterionId(request, 6, "074f6031-55f9-4e99-b9a4-c4363e8bc315")
        checkCriterionId(request, 7, "d3dfb714-f558-4512-bbc5-e456fa2339de")
        checkCriterionId(request, 8, "e4d37adc-08cd-4f4d-a8d8-32b62b0a1f46")
        checkCriterionId(request, 9, "7604bd40-4462-4086-8763-a50da51a869c")
        checkCriterionId(request, 10, "ab0e7f2e-6418-40e2-8870-6713123e41ad")
        checkCriterionId(request, 11, "cdd3bb3e-34a5-43d5-b668-2aab86a73822")
        checkCriterionId(request, 12, "3a18a175-1863-4b1d-baef-588ce61960ca")
        checkCriterionId(request, 13, "5e506c16-26ab-4e32-bb78-b27f87dc0565")
        checkCriterionId(request, 14, "3aaca389-4a7b-406b-a4b9-080845d127e7")
        checkCriterionId(request, 15, "c599c130-b29f-461e-a187-4e16c7d40db7")
        checkCriterionId(request, 16, "4bf996d9-439c-40c6-9ab9-980a48cb55a1")
        checkCriterionId(request, 17, "90a2e100-44cc-45d3-9970-69d6714f1596")
        checkCriterionId(request, 18, "dc12a151-7fdf-4733-a8f0-30f667292e66")
        checkCriterionId(request, 19, "c8809aa1-29b6-4f27-ae2f-27e612e394db")
        checkCriterionId(request, 20, "07301031-2270-41af-8e7e-66fe0c777107")
        checkCriterionId(request, 21, "9460457e-b43d-48a9-acd1-615de6ddd33e")
        checkCriterionId(request, 22, "6346959b-e097-4ea1-89cd-d1b4c131ea4d")
        checkCriterionId(request, 23, "1f49b3f0-d50f-43f6-8b30-4bafab108b9b")
        checkCriterionId(request, 24, "cc18c023-211d-484d-a32e-52f3f970285f")
        checkCriterionId(request, 25, "612a1625-118d-4ea4-a6db-413184e7c0a8")
        checkCriterionId(request, 26, "bdf0601d-2480-4250-b870-658d0ee95be6")
        checkCriterionId(request, 27, "7662b7a9-bcb8-4763-a0a7-7505d8e8470d")
        checkCriterionId(request, 28, "a7669d7d-9297-43e1-9d10-691a1660187c")
        checkCriterionId(request, 29, "d726bac9-e153-4e75-bfca-c5385587766d")
        checkCriterionId(request, 30, "8ed65e48-fd0d-444f-97bd-4f58da632999")
    }

}