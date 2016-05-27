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
    private OtherCriterion procurementReserved;
    private OtherCriterion eoRegistered;
    private OtherCriterion eoParticipatingProcurementProcedure;
    private OtherCriterion eoReliesCapacities;
    private OtherCriterion meetsObjective;
    private OtherCriterion subcontractingThirdParties;

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
