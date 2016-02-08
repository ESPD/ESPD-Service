package eu.europa.ec.grow.espd.domain;

import eu.europa.ec.grow.espd.entities.CcvCriterion;
import lombok.Data;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@Data
public class EspdDocument {

    //private String action;// "ca_create_espd" or "eo_import_espd"

    private PartyImpl authority;
    private EconomicOperatorImpl economicOperator;

    private String ojsNumber;
    private String procedureTitle;
    private String procedureShortDesc;
    private String lotConcerned;
    private String fileRefByCA;
    private String tedUrl; // aka ojsUrl

    private EspdRequestMetadata requestMetadata;

    private String tedReceptionId;

    // Award criteria
    private AwardCriterion procurementReserved;
    private AwardCriterion eoRegistered;
    private AwardCriterion eoParticipatingProcurementProcedure;
    private AwardCriterion eoReliesCapacities;
    private AwardCriterion meetsObjective;

    // Exclusion criteria

    private CriminalConvictionsCriterion criminalConvictions;
    private CriminalConvictionsCriterion corruption;
    private CriminalConvictionsCriterion fraud;
    private CriminalConvictionsCriterion terroristOffences;
    private CriminalConvictionsCriterion moneyLaundering;
    private CriminalConvictionsCriterion childLabour;

    private TaxesCriterion paymentTaxes;
    private TaxesCriterion paymentSocialSecurity;

    private LawCriterion breachingObligationsEnvironmental;
    private LawCriterion breachingObligationsSocial;
    private LawCriterion breachingObligationsLabour;

    private BankruptcyCriterion bankruptcy;
    private BankruptcyCriterion insolvency;
    private BankruptcyCriterion arrangementWithCreditors;
    private BankruptcyCriterion analogousSituation;
    private BankruptcyCriterion assetsAdministeredByLiquidator;
    private BankruptcyCriterion businessActivitiesSuspended;

    private MisconductDistortionCriterion guiltyGrave;
    private MisconductDistortionCriterion agreementsWithOtherEO;

    private ConflictInterestCriterion conflictInterest;
    private ConflictInterestCriterion involvementPreparationProcurement;
    private ConflictInterestCriterion earlyTermination;
    private ConflictInterestCriterion guiltyMisinterpretation;

    private PurelyNationalGrounds purelyNationalGrounds;

    // Selection criteria

    private SatisfiesAllCriterion selectionSatisfiesAll;

    private SuitabilityCriterion enrolmentProfessionalRegister;
    private SuitabilityCriterion enrolmentTradeRegister;
    private SuitabilityCriterion serviceContractsAuthorisation;
    private SuitabilityCriterion serviceContractsMembership;

    private EconomicFinancialStandingCriterion generalYearlyTurnover;
    private EconomicFinancialStandingCriterion averageYearlyTurnover;
    private EconomicFinancialStandingCriterion specificYearlyTurnover;
    private EconomicFinancialStandingCriterion specificAverageTurnover;
    private EconomicFinancialStandingCriterion setupEconomicOperator;
    private EconomicFinancialStandingCriterion financialRatio;
    private EconomicFinancialStandingCriterion professionalRiskInsurance;
    private EconomicFinancialStandingCriterion otherEconomicFinancialRequirements;

    private TechnicalProfessionalCriterion workContractsPerformanceOfWorks;
    private TechnicalProfessionalCriterion supplyContractsPerformanceDeliveries;
    private TechnicalProfessionalCriterion serviceContractsPerformanceServices;
    private TechnicalProfessionalCriterion techniciansTechnicalBodies;
    private TechnicalProfessionalCriterion workContractsTechnicians;
    private TechnicalProfessionalCriterion technicalFacilitiesMeasures;
    private TechnicalProfessionalCriterion studyResearchFacilities;
    private TechnicalProfessionalCriterion supplyChainManagement;
    private TechnicalProfessionalCriterion allowanceOfChecks;
    private TechnicalProfessionalCriterion educationalProfessionalQualifications;
    private TechnicalProfessionalCriterion environmentalManagementFeatures;
    private TechnicalProfessionalCriterion numberManagerialStaff;
    private TechnicalProfessionalCriterion averageAnnualManpower;
    private TechnicalProfessionalCriterion toolsPlantTechnicalEquipment;
    private TechnicalProfessionalCriterion subcontractingProportion;
    private TechnicalProfessionalCriterion supplyContractsSamplesDescriptionsWithoutCa;
    private TechnicalProfessionalCriterion supplyContractsSamplesDescriptionsWithCa;
    private TechnicalProfessionalCriterion supplyContractsCertificatesQc;
    private TechnicalProfessionalCriterion certificateIndependentBodiesAboutQa;
    private TechnicalProfessionalCriterion certificateIndependentBodiesAboutEnvironmental;

    //trick to use MultipartFile as @RequestParam
    public void setAttachment(MultipartFile attachment) throws IOException {
    }

    public MultipartFile getAttachment() {
        return null;
    }

    public final boolean atLeastOneSelectionCriterionWasSelected() {
        boolean atLeastOnePresent = false;
        for (eu.europa.ec.grow.espd.criteria.enums.SelectionCriterion ccvCriterion :
                eu.europa.ec.grow.espd.criteria.enums.SelectionCriterion.values()) {
            Criterion espdCriterion = readCriterionFromEspd(ccvCriterion);
            atLeastOnePresent = atLeastOnePresent | (espdCriterion != null && espdCriterion.getExists());
        }
        return atLeastOnePresent;
    }

    public final Criterion readCriterionFromEspd(CcvCriterion ccvCriterion) {
        try {
            return (Criterion) PropertyUtils.getSimpleProperty(this, ccvCriterion.getEspdDocumentField());
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            return null;
        }
    }
}
