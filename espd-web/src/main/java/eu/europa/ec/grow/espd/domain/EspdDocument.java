package eu.europa.ec.grow.espd.domain;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Data
public class EspdDocument {

    private String action;// "ca_create_espd" or "eo_import_espd"

    private PartyImpl authority;

    private String procedureDesc;
    private String lotConcerned;
    private String fileRefByCA;
    private String websiteProcDocs;

    //Exclusions

    private CriminalConvictions criminalConvictions;
    private CriminalConvictions corruption;
    private CriminalConvictions fraud;
    private CriminalConvictions terroristOffences;
    private CriminalConvictions moneyLaundering;
    private CriminalConvictions childLabour;

    private Taxes paymentTaxes;
    private Taxes paymentSocialSecurity;

    private BreachOfObligations breachingObligations;
    private BreachOfObligations bankruptcy;
    private BreachOfObligations insolvency;
    private BreachOfObligations arrangementWithCreditors;
    private BreachOfObligations analogousSituation;
    private BreachOfObligations assetsAdministeredByLiquidator;
    private BreachOfObligations businessActivitiesSuspended;
    private BreachOfObligations guiltyGrave;
    // TODO should remove this field? Probably we should.
    //private BreachOfObligations agreementsEo;
    private BreachOfObligations conflictInterest;
    private BreachOfObligations involvementPreparationProcurement;
    private BreachOfObligations earlyTermination;
    private BreachOfObligations guiltyMisinterpretation;

    private Criterion purelyNationalGrounds;

    private SelectionCriterion enrollmentProfessionalRegister;
    private SelectionCriterion enrollmentTradeRegister;
    private SelectionCriterion serviceContractsAuthorisation;
    private SelectionCriterion serviceContractsMembership;

    private SelectionCriterion generalYearlyTurnover;
    private SelectionCriterion averageYearlyTurnover;
    private SelectionCriterion specificYearlyTurnover;
    private SelectionCriterion specificAverageTurnover;
    private SelectionCriterion financialRatio;
    private SelectionCriterion professionalRiskInsurance;
    private SelectionCriterion otherEconomicFinancialRequirements;

    private SelectionCriterion workContractsPerformanceOfWorks;
    private SelectionCriterion supplyContractsPerformanceDeliveries;
    private SelectionCriterion serviceContractsPerformanceServices;
    private SelectionCriterion techniciansTechnicalBodies;
    private SelectionCriterion workContractsTechnicians;
    private SelectionCriterion technicalFacilitiesMeasures;
    private SelectionCriterion studyResearchFacilities;
    private SelectionCriterion supplyChainManagement;
    private SelectionCriterion allowanceOfChecks;
    private SelectionCriterion educationalProfessionalQualifications;
    private SelectionCriterion environmentalManagementFeatures;
    private SelectionCriterion numberManagerialStaff;
    private SelectionCriterion averageAnnualManpower;
    private SelectionCriterion toolsPlantTechnicalEquipment;
    private SelectionCriterion subcontractingProportion;
    private SelectionCriterion supplyContractsSamplesDescriptionsWithoutCa;
    private SelectionCriterion supplyContractsSamplesDescriptionsWithCa;
    private SelectionCriterion supplyContractsCertificatesQc;
    private SelectionCriterion certificateIndependentBodiesAboutQa;
    private SelectionCriterion certificateIndependentBodiesAboutEnvironmental;

    private SelectionCriterion economicEnrolment;

    private SelectionCriterion economicServiceContracts;

    private Criterion selectionSatisfiesAll;

    //trick to use MultipartFile as @RequestParam
    public void setAttachment(MultipartFile attachment) throws IOException, JAXBException {
    }

    public MultipartFile getAttachment() {
        return null;
    }

    public final boolean satisfiesAllCriteria() {
        return getSelectionSatisfiesAll() != null && getSelectionSatisfiesAll().getExists();
    }
}
