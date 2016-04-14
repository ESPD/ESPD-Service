package eu.europa.ec.grow.espd.domain;

import eu.europa.ec.grow.espd.domain.ubl.CcvCriterion;
import eu.europa.ec.grow.espd.domain.enums.criteria.SelectionCriterion;
import isa.names.specification.ubl.schema.xsd.ccv_commonaggregatecomponents_1.CriterionType;
import lombok.Data;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Data
public class EspdDocument {

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
    private AwardCriterion subcontractingThirdParties;

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

    private QualityAssuranceCriterion certificateIndependentBodiesAboutQa;
    private QualityAssuranceCriterion certificateIndependentBodiesAboutEnvironmental;

    private List<CriterionType> ublCriteria;

    //trick to use List of MultipartFile as @RequestParam
    public void setAttachments(List<MultipartFile> attachments) throws IOException {}

    public List<MultipartFile> getAttachments() {
        return null;
    }

    public final boolean atLeastOneSelectionCriterionWasSelected() {
        boolean atLeastOnePresent = false;
        for (SelectionCriterion ccvCriterion :
                SelectionCriterion.values()) {
            EspdCriterion espdCriterion = readCriterionFromEspd(ccvCriterion);
            atLeastOnePresent = atLeastOnePresent | (espdCriterion != null && espdCriterion.getExists());
        }
        return atLeastOnePresent;
    }

    public final EspdCriterion readCriterionFromEspd(CcvCriterion ccvCriterion) {
        try {
            return (EspdCriterion) PropertyUtils.getSimpleProperty(this, ccvCriterion.getEspdDocumentField());
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            return null;
        }
    }
}
