package eu.europa.ec.grow.espd.xml.request.selection
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.xml.base.AbstractEspdXmlMarshalling
import org.apache.commons.io.IOUtils
/**
 * Created by ratoico on 1/6/16 at 3:15 PM.
 */
class EspdRequestSelectionCriteriaImportTest extends AbstractEspdXmlMarshalling {

    def "all exclusion criteria should be selected"() {
        given:
        def espdRequestXml = readXmlFile("all_selection_criteria_selected.xml")

        when:
        EspdDocument espd = marshaller.importEspdRequest(IOUtils.toInputStream(espdRequestXml))

        then: "all criteria satisfied"
        espd.selectionSatisfiesAll.exists == false

        then: "should have all suitability"
        espd.enrolmentProfessionalRegister.exists == true
        espd.enrolmentTradeRegister.exists == true
        espd.serviceContractsAuthorisation.exists == true
        espd.serviceContractsMembership.exists == true

        then: "should have all economic financial standing"
        espd.generalYearlyTurnover.exists == true
        espd.averageYearlyTurnover.exists == true
        espd.specificYearlyTurnover.exists == true
        espd.specificAverageTurnover.exists == true
        espd.financialRatio.exists == true
        espd.professionalRiskInsurance.exists == true
        espd.otherEconomicFinancialRequirements.exists == true

        then: "should have all technical professional ability"
        espd.workContractsPerformanceOfWorks.exists == true
        espd.supplyContractsPerformanceDeliveries.exists == true
        espd.serviceContractsPerformanceServices.exists == true
        espd.techniciansTechnicalBodies.exists == true
        espd.workContractsTechnicians.exists == true
        espd.technicalFacilitiesMeasures.exists == true
        espd.studyResearchFacilities.exists == true
        espd.supplyChainManagement.exists == true
        espd.allowanceOfChecks.exists == true
        espd.educationalProfessionalQualifications.exists == true
        espd.environmentalManagementFeatures.exists == true
        espd.numberManagerialStaff.exists == true
        espd.averageAnnualManpower.exists == true
        espd.toolsPlantTechnicalEquipment.exists == true
        espd.subcontractingProportion.exists == true
        espd.supplyContractsSamplesDescriptionsWithoutCa.exists == true
        espd.supplyContractsSamplesDescriptionsWithCa.exists == true
        espd.supplyContractsCertificatesQc.exists == true
        espd.certificateIndependentBodiesAboutQa.exists == true
        espd.certificateIndependentBodiesAboutEnvironmental.exists == true
    }

    def "no exclusion criteria should appear as selected"() {
        given:
        def espdRequestXml = readXmlFile("no_selection_criteria_selected.xml")

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

    private String readXmlFile(String fileName) {
        return new File("./src/test/groovy/eu/europa/ec/grow/espd/xml/samples/${fileName}").getText('UTF-8')
    }

}