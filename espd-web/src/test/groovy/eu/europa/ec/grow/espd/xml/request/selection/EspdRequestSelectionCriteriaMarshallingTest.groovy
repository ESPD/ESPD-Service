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

package eu.europa.ec.grow.espd.xml.request.selection

import eu.europa.ec.grow.espd.domain.enums.criteria.OtherCriterion
import eu.europa.ec.grow.espd.domain.enums.criteria.SelectionCriterion
import eu.europa.ec.grow.espd.domain.*
import eu.europa.ec.grow.espd.xml.base.AbstractSelectionCriteriaFixture

/**
 *  Created by vigi on 11/19/15:3:32 PM.
 */
class EspdRequestSelectionCriteriaMarshallingTest extends AbstractSelectionCriteriaFixture {

    def "CA selects 'All section criteria' -> EO sees only 'All selection criteria' and not the individual ones"() {
        given:
        def espd = new EspdDocument(selectionSatisfiesAll: new SatisfiesAllCriterion(exists: true),
                enrolmentProfessionalRegister: new SuitabilityCriterion(exists: true),
                enrolmentTradeRegister: new SuitabilityCriterion(exists: true),
                serviceContractsAuthorisation: new SuitabilityCriterion(exists: true),
                serviceContractsMembership: new SuitabilityCriterion(exists: true))
        def idx = getRequestCriterionIndex(SelectionCriterion.ALL_SELECTION_CRITERIA_SATISFIED)

        when:
        def request = parseRequestXml(espd)

        then: "all mandatory exclusion criteria (minus 'purely national') plus 'satisifes all' plus the eo criteria"
        request.Criterion.size() == getMandatoryExclusionCriteriaSize() + 1 + OtherCriterion.values().length

        then: "check the CriterionID"
        checkCriterionId(request, idx, "7e7db838-eeac-46d9-ab39-42927486f22d")

        then: "check the CriterionTypeCode"
        request.Criterion[idx].TypeCode.text() == "CRITERION.SELECTION.ALL_SATISFIED"
        request.Criterion[idx].TypeCode.@listAgencyID.text() == "EU-COM-GROW"
        request.Criterion[idx].TypeCode.@listID.text() == "CriteriaTypeCode"
        request.Criterion[idx].TypeCode.@listVersionID.text() == "1.0.2"

        then: "check name and description"
        request.Criterion[idx].Name.text() == "All selection criteria will be satisfied"
        request.Criterion[idx].Description.text() == "It satisfies all the required selection criteria indicated in the relevant notice or in the procurement documents referred to in the notice."

        then: "main subgroup"
        request.Criterion[idx].RequirementGroup.size() == 1
        request.Criterion[idx].RequirementGroup[0].ID.text() == "f3a6836d-2de2-4cd1-81ca-fb06178d05c5"

        checkRequirement(request.Criterion[idx].RequirementGroup[0].Requirement[0], "15335c12-ad77-4728-b5ad-3c06a60d65a4", "Your answer?", "INDICATOR")
    }

    def "CA select individual selection criteria -> EO sees only the selected ones (and even not the 'All selection criteria')"() {
        given:
        def espd = new EspdDocument(selectionSatisfiesAll: new SatisfiesAllCriterion(exists: false),
                enrolmentProfessionalRegister: new SuitabilityCriterion(exists: true),
                generalYearlyTurnover: new EconomicFinancialStandingCriterion(exists: true),
                workContractsPerformanceOfWorks: new TechnicalProfessionalCriterion(exists: true))

        when:
        def request = parseRequestXml(espd)

        then: "only selected selection criteria are present plus mandatory exclusion plus the award criterion"
        request.Criterion.size() == getMandatoryExclusionCriteriaSize() + 3 + OtherCriterion.values().length

        then: "check the CriterionID"
        // satisfies all is not selected by the CA so the index of the first selection criterion is one position lower
        def idx = getRequestCriterionIndex(SelectionCriterion.ALL_SELECTION_CRITERIA_SATISFIED)
        checkCriterionId(request, idx, "6ee55a59-6adb-4c3a-b89f-e62a7ad7be7f")
        checkCriterionId(request, idx + 1, "499efc97-2ac1-4af2-9e84-323c2ca67747")
        checkCriterionId(request, idx + 2, "cdd3bb3e-34a5-43d5-b668-2aab86a73822")
    }

    def "CA selects no selection criteria -> EO sees all selection criteria (including 'All selection criteria')"() {
        given:
        def espd = new EspdDocument(
                selectionSatisfiesAll: null,
                enrolmentProfessionalRegister: new SuitabilityCriterion(exists: false),
                enrolmentTradeRegister: null,
                serviceContractsAuthorisation: null,
                serviceContractsMembership: null,
                generalYearlyTurnover: null,
                averageYearlyTurnover: null,
                specificYearlyTurnover: null,
                specificAverageTurnover: null,
                setupEconomicOperator: new EconomicFinancialStandingCriterion(exists: false),
                financialRatio: null,
                professionalRiskInsurance: null,
                otherEconomicFinancialRequirements: null,
                workContractsPerformanceOfWorks: null,
                supplyContractsPerformanceDeliveries: null,
                serviceContractsPerformanceServices: null,
                techniciansTechnicalBodies: null,
                workContractsTechnicians: null,
                technicalFacilitiesMeasures: null,
                studyResearchFacilities: null,
                supplyChainManagement: null,
                allowanceOfChecks: null,
                educationalProfessionalQualifications: null,
                environmentalManagementFeatures: new TechnicalProfessionalCriterion(exists: false),
                numberManagerialStaff: null,
                averageAnnualManpower: null,
                toolsPlantTechnicalEquipment: null,
                subcontractingProportion: null,
                supplyContractsSamplesDescriptionsWithoutCa: null,
                supplyContractsSamplesDescriptionsWithCa: null,
                supplyContractsCertificatesQc: null,
                certificateIndependentBodiesAboutQa: null,
                certificateIndependentBodiesAboutEnvironmental: null)

        when:
        def request = parseRequestXml(espd)

        then: "all mandatory exclusion plus all selection plus the only award criterion"
        request.Criterion.size() == getMandatoryExclusionCriteriaSize() + SelectionCriterion.values().length + OtherCriterion.values().length
        int idx = getRequestCriterionIndex(SelectionCriterion.ALL_SELECTION_CRITERIA_SATISFIED)
        for (SelectionCriterion criterion : SelectionCriterion.values()) {
            checkCriterionId(request, idx++, criterion.getUuid())
        }
    }

}