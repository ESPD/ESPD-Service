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
        EspdDocument espd = marshaller.importEspdRequest(IOUtils.toInputStream(espdRequestXml))

        then: "all criteria satisfied has no default answer value"
        espd.selectionSatisfiesAll.exists == false
        espd.selectionSatisfiesAll.answer == null

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
        EspdDocument espd = marshaller.importEspdRequest(IOUtils.toInputStream(espdRequestXml))

        then: "all criteria satisfied"
        espd.selectionSatisfiesAll.exists == false

        then: "should have all suitability"
        espd.enrolmentProfessionalRegister.exists == false
        espd.enrolmentTradeRegister.exists == false
        espd.serviceContractsAuthorisation.exists == false
        espd.serviceContractsMembership.exists == false

        then: "should have all economic financial standing"
        espd.generalYearlyTurnover.exists == false
        espd.averageYearlyTurnover.exists == false
        espd.specificYearlyTurnover.exists == false
        espd.specificAverageTurnover.exists == false
        espd.financialRatio.exists == false
        espd.professionalRiskInsurance.exists == false
        espd.otherEconomicFinancialRequirements.exists == false

        then: "should have all technical professional ability"
        espd.workContractsPerformanceOfWorks.exists == false
        espd.supplyContractsPerformanceDeliveries.exists == false
        espd.serviceContractsPerformanceServices.exists == false
        espd.techniciansTechnicalBodies.exists == false
        espd.workContractsTechnicians.exists == false
        espd.technicalFacilitiesMeasures.exists == false
        espd.studyResearchFacilities.exists == false
        espd.supplyChainManagement.exists == false
        espd.allowanceOfChecks.exists == false
        espd.educationalProfessionalQualifications.exists == false
        espd.environmentalManagementFeatures.exists == false
        espd.numberManagerialStaff.exists == false
        espd.averageAnnualManpower.exists == false
        espd.toolsPlantTechnicalEquipment.exists == false
        espd.subcontractingProportion.exists == false
        espd.supplyContractsSamplesDescriptionsWithoutCa.exists == false
        espd.supplyContractsSamplesDescriptionsWithCa.exists == false
        espd.supplyContractsCertificatesQc.exists == false
        espd.certificateIndependentBodiesAboutQa.exists == false
        espd.certificateIndependentBodiesAboutEnvironmental.exists == false
    }

}