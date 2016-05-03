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
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.xml.base.AbstractXmlFileImport
import org.apache.commons.io.IOUtils
/**
 * Created by ratoico on 1/6/16 at 3:15 PM.
 */
class EspdRequestSelectionCriteriaImportTest extends AbstractXmlFileImport {

    def "all exclusion criteria should be selected"() {
        given:
        def espdRequestXml = importXmlRequestFile("all_selection_criteria_selected.xml")

        when:
        EspdDocument espd = marshaller.importEspdRequest(IOUtils.toInputStream(espdRequestXml)).get()

        then: "all criteria satisfied has no default answer value"
        espd.selectionSatisfiesAll == null

        then: "should have all suitability and their default answers should be true"
        espd.enrolmentProfessionalRegister.exists == true
        espd.enrolmentProfessionalRegister.answer == true
        espd.enrolmentTradeRegister.exists == true
        espd.enrolmentTradeRegister.answer == true
        espd.serviceContractsAuthorisation.exists == true
        espd.serviceContractsAuthorisation.answer == true
        espd.serviceContractsMembership.exists == true
        espd.serviceContractsMembership.answer == true

        then: "should have all economic financial standing and their default answers should be true"
        espd.generalYearlyTurnover.exists == true
        espd.generalYearlyTurnover.answer == true
        espd.averageYearlyTurnover.exists == true
        espd.averageYearlyTurnover.answer == true
        espd.specificYearlyTurnover.exists == true
        espd.specificYearlyTurnover.answer == true
        espd.specificAverageTurnover.exists == true
        espd.specificAverageTurnover.answer == true
        espd.financialRatio.exists == true
        espd.financialRatio.answer == true
        espd.professionalRiskInsurance.exists == true
        espd.professionalRiskInsurance.answer == true
        espd.otherEconomicFinancialRequirements.exists == true
        espd.otherEconomicFinancialRequirements.answer == true

        then: "should have all technical professional ability and their default answers should be true"
        espd.workContractsPerformanceOfWorks.exists == true
        espd.workContractsPerformanceOfWorks.answer == true
        espd.supplyContractsPerformanceDeliveries.exists == true
        espd.supplyContractsPerformanceDeliveries.answer == true
        espd.serviceContractsPerformanceServices.exists == true
        espd.serviceContractsPerformanceServices.answer == true
        espd.techniciansTechnicalBodies.exists == true
        espd.techniciansTechnicalBodies.answer == true
        espd.workContractsTechnicians.exists == true
        espd.workContractsTechnicians.answer == true
        espd.technicalFacilitiesMeasures.exists == true
        espd.technicalFacilitiesMeasures.answer == true
        espd.studyResearchFacilities.exists == true
        espd.studyResearchFacilities.answer == true
        espd.supplyChainManagement.exists == true
        espd.supplyChainManagement.answer == true
        espd.allowanceOfChecks.exists == true
        espd.allowanceOfChecks.answer == true
        espd.educationalProfessionalQualifications.exists == true
        espd.educationalProfessionalQualifications.answer == true
        espd.environmentalManagementFeatures.exists == true
        espd.environmentalManagementFeatures.answer == true
        espd.numberManagerialStaff.exists == true
        espd.numberManagerialStaff.answer == true
        espd.averageAnnualManpower.exists == true
        espd.averageAnnualManpower.answer == true
        espd.toolsPlantTechnicalEquipment.exists == true
        espd.toolsPlantTechnicalEquipment.answer == true
        espd.subcontractingProportion.exists == true
        espd.subcontractingProportion.answer == true
        espd.supplyContractsSamplesDescriptionsWithoutCa.exists == true
        espd.supplyContractsSamplesDescriptionsWithoutCa.answer == true
        espd.supplyContractsSamplesDescriptionsWithCa.exists == true
        espd.supplyContractsSamplesDescriptionsWithCa.answer == true
        espd.supplyContractsCertificatesQc.exists == true
        espd.supplyContractsCertificatesQc.answer == true
        espd.certificateIndependentBodiesAboutQa.exists == true
        espd.certificateIndependentBodiesAboutQa.answer == true
        espd.certificateIndependentBodiesAboutEnvironmental.exists == true
        espd.certificateIndependentBodiesAboutEnvironmental.answer == true
    }

    def "no exclusion criteria should appear as selected"() {
        given:
        def espdRequestXml = importXmlRequestFile("no_selection_criteria_selected.xml")

        when:
        EspdDocument espd = marshaller.importEspdRequest(IOUtils.toInputStream(espdRequestXml)).get()

        then: "all criteria satisfied"
        espd.selectionSatisfiesAll == null

        then: "should have all suitability"
        espd.enrolmentProfessionalRegister == null
        espd.enrolmentTradeRegister == null
        espd.serviceContractsAuthorisation == null
        espd.serviceContractsMembership == null

        then: "should have all economic financial standing"
        espd.generalYearlyTurnover == null
        espd.averageYearlyTurnover == null
        espd.specificYearlyTurnover == null
        espd.specificAverageTurnover == null
        espd.financialRatio == null
        espd.professionalRiskInsurance == null
        espd.otherEconomicFinancialRequirements == null

        then: "should have all technical professional ability"
        espd.workContractsPerformanceOfWorks == null
        espd.supplyContractsPerformanceDeliveries == null
        espd.serviceContractsPerformanceServices == null
        espd.techniciansTechnicalBodies == null
        espd.workContractsTechnicians == null
        espd.technicalFacilitiesMeasures == null
        espd.studyResearchFacilities == null
        espd.supplyChainManagement == null
        espd.allowanceOfChecks == null
        espd.educationalProfessionalQualifications == null
        espd.environmentalManagementFeatures == null
        espd.numberManagerialStaff == null
        espd.averageAnnualManpower == null
        espd.toolsPlantTechnicalEquipment == null
        espd.subcontractingProportion == null
        espd.supplyContractsSamplesDescriptionsWithoutCa == null
        espd.supplyContractsSamplesDescriptionsWithCa == null
        espd.supplyContractsCertificatesQc == null
        espd.certificateIndependentBodiesAboutQa == null
        espd.certificateIndependentBodiesAboutEnvironmental == null
    }

}