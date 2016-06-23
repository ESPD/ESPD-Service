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

package eu.europa.ec.grow.espd.xml;

import com.google.common.collect.ImmutableMap;

import eu.europa.ec.grow.espd.domain.enums.criteria.ExclusionCriterion;
import eu.europa.ec.grow.espd.domain.enums.criteria.SelectionCriterion;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings({ "unchecked", "rawtypes" })
public final class CriteriaTemplates {

    public static final int YEAR = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
    public static final Integer[] LAST_YEARS_AMOUNT = new Integer[] { YEAR - 1, YEAR - 2, YEAR - 3, YEAR - 4, YEAR - 5 };
    public static final Integer[] LAST_YEARS_NUMBER = new Integer[] { YEAR - 1, YEAR - 2, YEAR - 3 };

    private CriteriaTemplates() {

    }

    /* EXCLUSION CA */
    public static final Map[] criminalListCA = new Map[] {
            ImmutableMap.<String, Object>builder().
                    put("template", "checkTemplate").
                    put("field", "criminalConvictions").
                    put("title_code", "crit_eu_title_grounds_criminal_conv").
                    put("description_code", "crit_eu_text_grounds_criminal_conv").
                    put("is_always_checked", "true").
                    put("is_disabled", "true").
                    put("criterion", ExclusionCriterion.PARTICIPATION_CRIMINAL_ORGANISATION).build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "checkTemplate").
                    put("field", "corruption").
                    put("title_code", "crit_eu_title_corruption").
                    put("description_code", "crit_eu_text_corruption").
                    put("is_always_checked", "true").
                    put("is_disabled", "true").
                    put("criterion", ExclusionCriterion.CORRUPTION).build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "checkTemplate").
                    put("field", "fraud").
                    put("title_code", "crit_eu_title_fraud").
                    put("description_code", "crit_eu_text_fraud").
                    put("is_always_checked", "true").
                    put("is_disabled", "true").
                    put("criterion", ExclusionCriterion.FRAUD).build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "checkTemplate").
                    put("field", "terroristOffences").
                    put("title_code", "crit_eu_title_terrorist").
                    put("description_code", "crit_eu_text_terrorist").
                    put("is_always_checked", "true").
                    put("is_disabled", "true").
                    put("criterion", ExclusionCriterion.TERRORIST_OFFENCES).build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "checkTemplate").
                    put("field", "moneyLaundering").
                    put("title_code", "crit_eu_title_money_laundering").
                    put("description_code", "crit_eu_text_money_laundering").
                    put("is_always_checked", "true").
                    put("is_disabled", "true").
                    put("criterion", ExclusionCriterion.MONEY_LAUNDERING).build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "checkTemplate").
                    put("field", "childLabour").
                    put("title_code", "crit_eu_title_child_labour").
                    put("description_code", "crit_eu_text_child_labour").
                    put("is_always_checked", "true").
                    put("is_disabled", "true").
                    put("criterion", ExclusionCriterion.CHILD_LABOUR).build()
    };

    public static final Map[] taxesListCA = new Map[] {
            ImmutableMap.<String, Object>builder().
                    put("template", "checkTemplate").
                    put("field", "paymentTaxes").
                    put("title_code", "crit_eu_title_payment_taxes").
                    put("description_code", "crit_eu_text_payment_taxes").
                    put("is_always_checked", "true").
                    put("is_disabled", "true").
                    put("criterion", ExclusionCriterion.PAYMENT_OF_TAXES).build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "checkTemplate").
                    put("field", "paymentSocialSecurity").
                    put("title_code", "crit_eu_title_payment_social_security").
                    put("description_code", "crit_eu_text_payment_social_security").
                    put("is_always_checked", "true").
                    put("is_disabled", "true").
                    put("criterion", ExclusionCriterion.PAYMENT_OF_SOCIAL_SECURITY).build()
    };

    public static final Map[] insolvencyListCA = new Map[] {
            ImmutableMap.<String, Object>builder().
                    put("template", "checkTemplate").
                    put("field", "breachingObligationsEnvironmental").
                    put("title_code", "crit_eu_title_breaching_obligations_environmental").
                    put("description_code", "crit_eu_text_breaching_obligations_environmental").
                    put("is_always_checked", "false").
                    put("default_value", "true").
                    put("is_disabled", "true").
                    put("criterion", ExclusionCriterion.BREACHING_OF_OBLIGATIONS_ENVIRONMENTAL).build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "checkTemplate").
                    put("field", "breachingObligationsSocial").
                    put("title_code", "crit_eu_title_breaching_obligations_social").
                    put("description_code", "crit_eu_text_breaching_obligations_social").
                    put("is_always_checked", "false").
                    put("default_value", "true").
                    put("is_disabled", "true").
                    put("criterion", ExclusionCriterion.BREACHING_OF_OBLIGATIONS_SOCIAL).build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "checkTemplate").
                    put("field", "breachingObligationsLabour").
                    put("title_code", "crit_eu_title_breaching_obligations_labour").
                    put("description_code", "crit_eu_text_breaching_obligations_labour").
                    put("is_always_checked", "false").
                    put("default_value", "true").
                    put("is_disabled", "true").
                    put("criterion", ExclusionCriterion.BREACHING_OF_OBLIGATIONS_LABOUR).build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "checkTemplate").
                    put("field", "bankruptcy").
                    put("title_code", "crit_eu_title_bankrupt").
                    put("description_code", "crit_eu_text_bankrupt").
                    put("is_always_checked", "false").
                    put("default_value", "true").
                    put("is_disabled", "true").
                    put("criterion", ExclusionCriterion.BANKRUPTCY).build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "checkTemplate").
                    put("field", "insolvency").
                    put("title_code", "crit_eu_title_insolvency").
                    put("description_code", "crit_eu_text_insolvency").
                    put("is_always_checked", "false").
                    put("default_value", "true").
                    put("is_disabled", "true").
                    put("criterion", ExclusionCriterion.INSOLVENCY).build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "checkTemplate").
                    put("field", "arrangementWithCreditors").
                    put("title_code", "crit_eu_title_arrangement_creditors").
                    put("description_code", "crit_eu_text_arrangement_creditors").
                    put("is_always_checked", "false").
                    put("default_value", "true").
                    put("is_disabled", "true").
                    put("criterion", ExclusionCriterion.ARRANGEMENT_WITH_CREDITORS).build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "checkTemplate").
                    put("field", "analogousSituation").
                    put("title_code", "crit_eu_title_national_bankruptcy").
                    put("description_code", "crit_eu_text_national_bankruptcy").
                    put("is_always_checked", "false").
                    put("default_value", "true").
                    put("is_disabled", "true").
                    put("criterion", ExclusionCriterion.ANALOGOUS_SITUATION).build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "checkTemplate").
                    put("field", "assetsAdministeredByLiquidator").
                    put("title_code", "crit_eu_title_liquidator").
                    put("description_code", "crit_eu_text_liquidator").
                    put("is_always_checked", "false").
                    put("default_value", "true").
                    put("is_disabled", "true").
                    put("criterion", ExclusionCriterion.ASSETS_ADMINISTERED_BY_LIQUIDATOR).build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "checkTemplate").
                    put("field", "businessActivitiesSuspended").
                    put("title_code", "crit_eu_title_suspended_business").
                    put("description_code", "crit_eu_text_suspended_business").
                    put("is_always_checked", "false").
                    put("default_value", "true").
                    put("is_disabled", "true").
                    put("criterion", ExclusionCriterion.BUSINESS_ACTIVITIES_SUSPENDED).build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "checkTemplate").
                    put("field", "guiltyGrave").
                    put("title_code", "crit_eu_title_guilty_misconduct").
                    put("description_code", "crit_eu_text_guilty_misconduct").
                    put("is_always_checked", "false").
                    put("default_value", "true").
                    put("is_disabled", "true").
                    put("criterion", ExclusionCriterion.GUILTY_OF_PROFESSIONAL_MISCONDUCT).build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "checkTemplate").
                    put("field", "agreementsWithOtherEO").
                    put("title_code", "crit_eu_title_agreement_economic").
                    put("description_code", "crit_eu_text_agreement_economic").
                    put("is_always_checked", "false").
                    put("default_value", "true").
                    put("is_disabled", "true").
                    put("criterion", ExclusionCriterion.AGREEMENTS_WITH_OTHER_EO).build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "checkTemplate").
                    put("field", "conflictInterest").
                    put("title_code", "crit_eu_title_conflict_interest").
                    put("description_code", "crit_eu_text_conflict_interest").
                    put("is_always_checked", "false").
                    put("default_value", "true").
                    put("is_disabled", "true").
                    put("criterion", ExclusionCriterion.CONFLICT_OF_INTEREST_EO_PROCUREMENT_PROCEDURE).build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "checkTemplate").
                    put("field", "involvementPreparationProcurement").
                    put("title_code", "crit_eu_title_involvement").
                    put("description_code", "crit_eu_text_involvement").
                    put("is_always_checked", "false").
                    put("default_value", "true").
                    put("is_disabled", "true").
                    put("criterion", ExclusionCriterion.DIRECT_INVOLVEMENT_PROCUREMENT_PROCEDURE).build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "checkTemplate").
                    put("field", "earlyTermination").
                    put("title_code", "crit_eu_title_early_termination").
                    put("description_code", "crit_eu_text_early_termination").
                    put("is_always_checked", "false").
                    put("default_value", "true").
                    put("is_disabled", "true").
                    put("criterion", ExclusionCriterion.EARLY_TERMINATION).build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "checkTemplate").
                    put("field", "guiltyMisinterpretation").
                    put("title_code", "crit_eu_title_guilty_misinterpretation").
                    put("description_code", "crit_eu_text_guilty_misinterpretation").
                    put("is_always_checked", "false").
                    put("default_value", "true").
                    put("is_disabled", "true").
                    put("criterion", ExclusionCriterion.GUILTY_OF_MISINTERPRETATION).build()
    };

    /* EXCLUSION EO */
    public static final Map[] criminalListEO = new Map[] {
            ImmutableMap.<String, Object>builder().
                    put("template", "criminalFormTemplate").
                    put("field", "criminalConvictions").
                    put("title_code", "crit_eu_title_grounds_criminal_conv").
                    put("description_code", "crit_eu_text_grounds_criminal_conv").
                    build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "criminalFormTemplate").
                    put("field", "corruption").
                    put("title_code", "crit_eu_title_corruption").
                    put("description_code", "crit_eu_text_corruption").
                    build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "criminalFormTemplate").
                    put("field", "fraud").
                    put("title_code", "crit_eu_title_fraud").
                    put("description_code", "crit_eu_text_fraud").
                    build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "criminalFormTemplate").
                    put("field", "terroristOffences").
                    put("title_code", "crit_eu_title_terrorist").
                    put("description_code", "crit_eu_text_terrorist").
                    build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "criminalFormTemplate").
                    put("field", "moneyLaundering").
                    put("title_code", "crit_eu_title_money_laundering").
                    put("description_code", "crit_eu_text_money_laundering").
                    build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "criminalFormTemplate").
                    put("field", "childLabour").
                    put("title_code", "crit_eu_title_child_labour").
                    put("description_code", "crit_eu_text_child_labour").
                    build()
    };

    public static final Map[] taxesListEO = new Map[] {
            ImmutableMap.<String, Object>builder().
                    put("template", "taxFormTemplate").
                    put("field", "paymentTaxes").
                    put("title_code", "crit_eu_title_payment_taxes").
                    put("description_code", "crit_eu_text_payment_taxes").
                    put("selfCleaning", "false").
                    build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "taxFormTemplate").
                    put("field", "paymentSocialSecurity").
                    put("title_code", "crit_eu_title_payment_social_security").
                    put("description_code", "crit_eu_text_payment_social_security").
                    put("selfCleaning", "false").
                    build(),
    };

    public static final Map[] insolvencyListEO = new Map[] {
            ImmutableMap.<String, Object>builder().
                    put("template", "exclusionFormTemplate").
                    put("field", "breachingObligationsEnvironmental").
                    put("title_code", "crit_eu_title_breaching_obligations_environmental").
                    put("description_code", "crit_eu_text_breaching_obligations_environmental").
                    put("checkExistanse", "true").
                    put("availableElectronically", "false").
                    build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "exclusionFormTemplate").
                    put("field", "breachingObligationsSocial").
                    put("title_code", "crit_eu_title_breaching_obligations_social").
                    put("description_code", "crit_eu_text_breaching_obligations_social").
                    put("checkExistanse", "true").
                    put("availableElectronically", "false").
                    build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "exclusionFormTemplate").
                    put("field", "breachingObligationsLabour").
                    put("title_code", "crit_eu_title_breaching_obligations_labour").
                    put("description_code", "crit_eu_text_breaching_obligations_labour").
                    put("checkExistanse", "true").
                    put("availableElectronically", "false").
                    build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "reasonsNeverlessPerformForm").
                    put("field", "bankruptcy").
                    put("title_code", "crit_eu_title_bankrupt").
                    put("description_code", "crit_eu_text_bankrupt").
                    put("checkExistanse", "true").
                    put("selfCleaning", "false").
                    build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "reasonsNeverlessPerformForm").
                    put("field", "insolvency").
                    put("title_code", "crit_eu_title_insolvency").
                    put("description_code", "crit_eu_text_insolvency").
                    put("checkExistanse", "true").
                    put("selfCleaning", "false").
                    build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "reasonsNeverlessPerformForm").
                    put("field", "arrangementWithCreditors").
                    put("title_code", "crit_eu_title_arrangement_creditors").
                    put("description_code", "crit_eu_text_arrangement_creditors").
                    put("checkExistanse", "true").
                    put("selfCleaning", "false").
                    build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "reasonsNeverlessPerformForm").
                    put("field", "analogousSituation").
                    put("title_code", "crit_eu_title_national_bankruptcy").
                    put("description_code", "crit_eu_text_national_bankruptcy").
                    put("checkExistanse", "true").
                    put("selfCleaning", "false").
                    build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "reasonsNeverlessPerformForm").
                    put("field", "assetsAdministeredByLiquidator").
                    put("title_code", "crit_eu_title_liquidator").
                    put("description_code", "crit_eu_text_liquidator").
                    put("checkExistanse", "true").
                    put("selfCleaning", "false").
                    build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "reasonsNeverlessPerformForm").
                    put("field", "businessActivitiesSuspended").
                    put("title_code", "crit_eu_title_suspended_business").
                    put("description_code", "crit_eu_text_suspended_business").
                    put("checkExistanse", "true").
                    put("selfCleaning", "false").
                    build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "exclusionFormTemplate").
                    put("field", "agreementsWithOtherEO").
                    put("title_code", "crit_eu_title_agreement_economic").
                    put("description_code", "crit_eu_text_agreement_economic").
                    put("checkExistanse", "true").
                    put("availableElectronically", "false").
                    build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "exclusionFormTemplate").
                    put("field", "guiltyGrave").
                    put("title_code", "crit_eu_title_guilty_misconduct").
                    put("description_code", "crit_eu_text_guilty_misconduct").
                    put("checkExistanse", "true").
                    put("availableElectronically", "false").
                    build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "exclusionFormTemplate").
                    put("field", "conflictInterest").
                    put("title_code", "crit_eu_title_conflict_interest").
                    put("checkExistanse", "true").
                    put("selfCleaning", "false").
                    put("hasDescription", "false").
                    put("availableElectronically", "false").
                    put("description_code", "crit_eu_text_conflict_interest").
                    build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "exclusionFormTemplate").
                    put("field", "involvementPreparationProcurement").
                    put("title_code", "crit_eu_title_involvement").
                    put("description_code", "crit_eu_text_involvement").
                    put("availableElectronically", "false").
                    put("checkExistanse", "true").
                    put("selfCleaning", "false").
                    build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "exclusionFormTemplate").
                    put("field", "earlyTermination").
                    put("title_code", "crit_eu_title_early_termination").
                    put("description_code", "crit_eu_text_early_termination").
                    put("checkExistanse", "true").
                    put("availableElectronically", "false").
                    build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "exclusionFormTemplate").
                    put("field", "guiltyMisinterpretation").
                    put("title_code", "crit_eu_title_guilty_misinterpretation").
                    put("description_code", "crit_eu_text_guilty_misinterpretation").
                    put("checkExistanse", "true").
                    put("selfCleaning", "false").
                    put("hasDescription", "false").
                    put("availableElectronically", "false").
                    build()

    };

    /* SELECTION CA */
    public static final Map[] suitabilityListCA = new Map[] {
            ImmutableMap.<String, Object>builder().
                    put("template", "checkTemplate").
                    put("field", "enrolmentProfessionalRegister").
                    put("title_code", "crit_selection_suitability_enrolment_professional_register_main").
                    put("description_code", "crit_selection_suitability_enrolment_professional_register_description").
                    put("criterion", SelectionCriterion.ENROLMENT_PROFESSIONAL_REGISTER).build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "checkTemplate").
                    put("field", "enrolmentTradeRegister").
                    put("title_code", "crit_selection_suitability_enrolment_trade_register_main").
                    put("description_code", "crit_selection_suitability_enrolment_trade_register_description").
                    put("criterion", SelectionCriterion.ENROLMENT_TRADE_REGISTER).build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "checkTemplate").
                    put("field", "serviceContractsAuthorisation").
                    put("title_code", "crit_selection_suitability_service_contracts_auth_main").
                    put("description_code", "crit_selection_suitability_service_contracts_auth_description").
                    put("criterion", SelectionCriterion.SERVICE_CONTRACTS_AUTHORISATION).build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "checkTemplate").
                    put("field", "serviceContractsMembership").
                    put("title_code", "crit_selection_suitability_service_contracts_membership_main").
                    put("description_code", "crit_selection_suitability_service_contracts_membership_description").
                    put("criterion", SelectionCriterion.SERVICE_CONTRACTS_MEMBERSHIP).build()
    };

    public static final Map[] economicListCA = new Map[] {
            ImmutableMap.<String, Object>builder().
                    put("template", "checkTemplate").
                    put("field", "generalYearlyTurnover").
                    put("title_code", "crit_selection_economic_general_yearly_turnover_main").
                    put("description_code", "crit_selection_economic_general_yearly_turnover_description").
                    put("criterion", SelectionCriterion.GENERAL_YEARLY_TURNOVER).build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "checkTemplate").
                    put("field", "averageYearlyTurnover").
                    put("title_code", "crit_selection_economic_average_yearly_turnover_main").
                    put("description_code", "crit_selection_economic_average_yearly_turnover_description").
                    put("criterion", SelectionCriterion.AVERAGE_YEARLY_TURNOVER).build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "checkTemplate").
                    put("field", "specificYearlyTurnover").
                    put("title_code", "crit_selection_economic_specific_yearly_turnover_main").
                    put("description_code", "crit_selection_economic_specific_yearly_turnover_description").
                    put("criterion", SelectionCriterion.SPECIFIC_YEARLY_TURNOVER).build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "checkTemplate").
                    put("field", "specificAverageTurnover").
                    put("title_code", "crit_selection_economic_specific_average_turnover_main").
                    put("description_code", "crit_selection_economic_specific_average_turnover_description").
                    put("criterion", SelectionCriterion.SPECIFIC_AVERAGE_TURNOVER).build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "checkTemplate").
                    put("field", "setupEconomicOperator").
                    put("title_code", "crit_selection_economic_setup_eo_main").
                    put("description_code", "crit_selection_economic_setup_eo_description").
                    put("criterion", SelectionCriterion.SETUP_ECONOMIC_OPERATOR).build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "checkTemplate").
                    put("field", "financialRatio").
                    put("title_code", "crit_selection_economic_financial_ratio_main").
                    put("description_code", "crit_selection_economic_financial_ratio_description").
                    put("criterion", SelectionCriterion.FINANCIAL_RATIO).build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "checkTemplate").
                    put("field", "professionalRiskInsurance").
                    put("title_code", "crit_selection_economic_professional_risk_insurance_main").
                    put("description_code", "crit_selection_economic_professional_risk_insurance_description").
                    put("criterion", SelectionCriterion.PROFESSIONAL_RISK_INSURANCE).build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "checkTemplate").
                    put("field", "otherEconomicFinancialRequirements").
                    put("title_code", "crit_selection_economic_other_financial_requirements_main").
                    put("description_code", "crit_selection_economic_other_financial_requirements_description").
                    put("criterion", SelectionCriterion.OTHER_ECONOMIC_OR_FINANCIAL_REQUIREMENTS).build()
    };

    public static final Map[] technicalListCA = new Map[] {
            ImmutableMap.<String, Object>builder().
                    put("template", "checkTemplate").
                    put("field", "workContractsPerformanceOfWorks").
                    put("title_code", "crit_selection_technical_work_contracts_performance_works_main").
                    put("description_code", "crit_selection_technical_work_contracts_performance_works_description").
                    put("criterion", SelectionCriterion.WORK_CONTRACTS_PERFORMANCE_OF_WORKS).build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "checkTemplate").
                    put("field", "supplyContractsPerformanceDeliveries").
                    put("title_code", "crit_selection_technical_supply_contracts_performance_deliveries_main").
                    put("description_code",
                            "crit_selection_technical_supply_contracts_performance_deliveries_description").
                    put("criterion", SelectionCriterion.SUPPLY_CONTRACTS_PERFORMANCE_OF_DELIVERIES).build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "checkTemplate").
                    put("field", "serviceContractsPerformanceServices").
                    put("title_code", "crit_selection_technical_service_contracts_performance_services_main").
                    put("description_code",
                            "crit_selection_technical_service_contracts_performance_services_description").
                    put("criterion", SelectionCriterion.SERVICE_CONTRACTS_PERFORMANCE_OF_SERVICES).build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "checkTemplate").
                    put("field", "techniciansTechnicalBodies").
                    put("title_code", "crit_selection_technical_technicians_technical_bodies_main").
                    put("description_code", "crit_selection_technical_technicians_technical_bodies_description").
                    put("criterion", SelectionCriterion.TECHNICIANS_OR_TECHNICAL_BODIES).build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "checkTemplate").
                    put("field", "workContractsTechnicians").
                    put("title_code", "crit_selection_technical_work_contracts_technicians_main").
                    put("description_code", "crit_selection_technical_work_contracts_technicians_description").
                    put("criterion", SelectionCriterion.WORK_CONTRACTS_TECHNICIANS_OR_TECHNICAL_BODIES).build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "checkTemplate").
                    put("field", "technicalFacilitiesMeasures").
                    put("title_code", "crit_selection_technical_technical_facilities_measures_main").
                    put("description_code", "crit_selection_technical_technical_facilities_measures_description").
                    put("criterion", SelectionCriterion.TECHNICAL_FACILITIES_AND_MEASURES).build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "checkTemplate").
                    put("field", "studyResearchFacilities").
                    put("title_code", "crit_selection_technical_study_research_facilities_main").
                    put("description_code", "crit_selection_technical_study_research_facilities_description").
                    put("criterion", SelectionCriterion.STUDY_AND_RESEARCH_FACILITIES).build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "checkTemplate").
                    put("field", "supplyChainManagement").
                    put("title_code", "crit_selection_technical_supply_chain_management_main").
                    put("description_code", "crit_selection_technical_supply_chain_management_description").
                    put("criterion", SelectionCriterion.SUPPLY_CHAIN_MANAGEMENT).build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "checkTemplate").
                    put("field", "allowanceOfChecks").
                    put("title_code", "crit_selection_technical_allowance_of_checks_main").
                    put("description_code", "crit_selection_technical_allowance_of_checks_description").
                    put("criterion", SelectionCriterion.ALLOWANCE_OF_CHECKS).build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "checkTemplate").
                    put("field", "educationalProfessionalQualifications").
                    put("title_code", "crit_selection_technical_educational_professional_qualifications_main").
                    put("description_code",
                            "crit_selection_technical_educational_professional_qualifications_description").
                    put("criterion", SelectionCriterion.EDUCATIONAL_AND_PROFESSIONAL_QUALIFICATIONS).build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "checkTemplate").
                    put("field", "environmentalManagementFeatures").
                    put("title_code", "crit_selection_technical_environment_management_features_main").
                    put("description_code", "crit_selection_technical_environment_management_features_description").
                    put("criterion", SelectionCriterion.ENVIRONMENTAL_MANAGEMENT_FEATURES).build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "checkTemplate").
                    put("field", "numberManagerialStaff").
                    put("title_code", "crit_selection_technical_number_managerial_staff_main").
                    put("description_code", "crit_selection_technical_number_managerial_staff_description").
                    put("criterion", SelectionCriterion.NUMBER_OF_MANAGERIAL_STAFF).build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "checkTemplate").
                    put("field", "averageAnnualManpower").
                    put("title_code", "crit_selection_technical_average_annual_manpower_main").
                    put("description_code", "crit_selection_technical_average_annual_manpower_description").
                    put("criterion", SelectionCriterion.AVERAGE_ANNUAL_MANPOWER).build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "checkTemplate").
                    put("field", "toolsPlantTechnicalEquipment").
                    put("title_code", "crit_selection_technical_tools_plant_main").
                    put("description_code", "crit_selection_technical_tools_plant_description").
                    put("criterion", SelectionCriterion.TOOLS_PLANT_TECHNICAL_EQUIPMENT).build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "checkTemplate").
                    put("field", "subcontractingProportion").
                    put("title_code", "crit_selection_technical_subcontracting_proportion_main").
                    put("description_code", "crit_selection_technical_subcontracting_proportion_description").
                    put("criterion", SelectionCriterion.SUBCONTRACTING_PROPORTION).build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "checkTemplate").
                    put("field", "supplyContractsSamplesDescriptionsWithoutCa").
                    put("title_code", "crit_selection_technical_supply_contracts_without_ca_main").
                    put("description_code", "crit_selection_technical_supply_contracts_without_ca_description").
                    put("criterion", SelectionCriterion.SUPPLY_CONTRACTS_SAMPLES_DESCRIPTIONS_WITHOUT_CA).build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "checkTemplate").
                    put("field", "supplyContractsSamplesDescriptionsWithCa").
                    put("title_code", "crit_selection_technical_supply_contracts_with_ca_main").
                    put("description_code", "crit_selection_technical_supply_contracts_with_ca_description").
                    put("criterion", SelectionCriterion.SUPPLY_CONTRACTS_SAMPLES_DESCRIPTIONS_WITH_CA).build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "checkTemplate").
                    put("field", "supplyContractsCertificatesQc").
                    put("title_code", "crit_selection_technical_supply_contracts_certificate_quality_main").
                    put("description_code", "crit_selection_technical_supply_contracts_certificate_quality_description").
                    put("criterion", SelectionCriterion.SUPPLY_CONTRACTS_CERTIFICATES_QC).build(),

    };

    public static final Map[] qualityAssuranceListCA = new Map[] {
            ImmutableMap.<String, Object>builder().
                    put("template", "checkTemplate").
                    put("field", "certificateIndependentBodiesAboutQa").
                    put("title_code", "crit_selection_quality_certificate_independent_bodies_quality_main").
                    put("description_code",
                            "crit_selection_quality_certificate_independent_bodies_quality_description").
                    put("criterion", SelectionCriterion.CERTIFICATE_INDEPENDENT_BODIES_ABOUT_QA).build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "checkTemplate").
                    put("field", "certificateIndependentBodiesAboutEnvironmental").
                    put("title_code", "crit_selection_quality_certificate_independent_bodies_environmental_main").
                    put("description_code",
                            "crit_selection_quality_certificate_independent_bodies_environmental_description").
                    put("criterion", SelectionCriterion.CERTIFICATE_INDEPENDENT_BODIES_ABOUT_ENVIRONMENTAL).build()
    };

    /* SELECTION EO */
    public static final Map[] suitabilityListEO = new Map[] {
            ImmutableMap.<String, Object>builder().
                    put("template", "selectionFormTemplate").
                    put("field", "enrolmentProfessionalRegister").
                    put("title_code", "crit_selection_suitability_enrolment_professional_register_main").
                    put("description_code", "crit_selection_suitability_enrolment_professional_register_description").
                    build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "selectionFormTemplate").
                    put("field", "enrolmentTradeRegister").
                    put("title_code", "crit_selection_suitability_enrolment_trade_register_main").
                    put("description_code", "crit_selection_suitability_enrolment_trade_register_description").
                    build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "economicFinancialCriterionEO").
                    put("field", "serviceContractsAuthorisation").
                    put("has_your_answer", "true").
                    put("has_please_describe_them", "true").
                    put("has_multiple_year_amount", "false").
                    put("title_code", "crit_selection_suitability_service_contracts_auth_main").
                    put("description_code", "crit_selection_suitability_service_contracts_auth_description").
                    build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "economicFinancialCriterionEO").
                    put("field", "serviceContractsMembership").
                    put("has_your_answer", "true").
                    put("has_please_describe_them", "true").
                    put("has_multiple_year_amount", "false").
                    put("title_code", "crit_selection_suitability_service_contracts_membership_main").
                    put("description_code", "crit_selection_suitability_service_contracts_membership_description").
                    build(),
    };

    public static final Map[] economicListEO = new Map[] {
            ImmutableMap.<String, Object>builder().
                    put("template", "economicFinancialCriterionEO").
                    put("field", "generalYearlyTurnover").
                    put("title_code", "crit_selection_economic_general_yearly_turnover_main").
                    put("description_code", "crit_selection_economic_general_yearly_turnover_description").
                    put("lastYearsAmount", LAST_YEARS_AMOUNT).
                    build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "economicFinancialCriterionEO").
                    put("field", "averageYearlyTurnover").
                    put("title_code", "crit_selection_economic_average_yearly_turnover_main").
                    put("description_code", "crit_selection_economic_average_yearly_turnover_description").
                    put("lastYearsAmount", LAST_YEARS_AMOUNT).
                    build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "economicFinancialCriterionEO").
                    put("field", "specificYearlyTurnover").
                    put("title_code", "crit_selection_economic_specific_yearly_turnover_main").
                    put("description_code", "crit_selection_economic_specific_yearly_turnover_description").
                    put("lastYearsAmount", LAST_YEARS_AMOUNT).
                    build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "economicFinancialCriterionEO").
                    put("field", "specificAverageTurnover").
                    put("title_code", "crit_selection_economic_specific_average_turnover_main").
                    put("description_code", "crit_selection_economic_specific_average_turnover_description").
                    put("lastYearsAmount", LAST_YEARS_AMOUNT).
                    build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "economicFinancialCriterionEO").
                    put("field", "setupEconomicOperator").
                    put("title_code", "crit_selection_economic_setup_eo_main").
                    put("description_code", "crit_selection_economic_setup_eo_description").
                    put("has_specify_year", "true").
                    put("availableElectronically", "false").
                    put("has_multiple_year_amount", "false").
                    put("lastYearsAmount", LAST_YEARS_AMOUNT).
                    build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "economicFinancialCriterionEO").
                    put("field", "financialRatio").
                    put("title_code", "crit_selection_economic_financial_ratio_main").
                    put("description_code", "crit_selection_economic_financial_ratio_description").
                    put("has_multiple_description_ratio", "true").
                    build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "economicFinancialCriterionEO").
                    put("field", "professionalRiskInsurance").
                    put("title_code", "crit_selection_economic_professional_risk_insurance_main").
                    put("description_code", "crit_selection_economic_professional_risk_insurance_description").
                    put("has_single_amount", "true").
                    build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "economicFinancialCriterionEO").
                    put("field", "otherEconomicFinancialRequirements").
                    put("title_code", "crit_selection_economic_other_financial_requirements_main").
                    put("description_code", "crit_selection_economic_other_financial_requirements_description").
                    put("has_please_describe_them", "true").
                    build(),
    };

    public static final Map[] technicalListEO = new Map[] {
            ImmutableMap.<String, Object>builder().
                    put("template", "technicalProfessionalCriterionEO").
                    put("field", "workContractsPerformanceOfWorks").
                    put("has_multiple_description_amount_date_recipients", "true").
                    put("title_code", "crit_selection_technical_work_contracts_performance_works_main").
                    put("description_code", "crit_selection_technical_work_contracts_performance_works_description").
                    build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "technicalProfessionalCriterionEO").
                    put("field", "supplyContractsPerformanceDeliveries").
                    put("has_multiple_description_amount_date_recipients", "true").
                    put("title_code", "crit_selection_technical_supply_contracts_performance_deliveries_main").
                    put("description_code",
                            "crit_selection_technical_supply_contracts_performance_deliveries_description").
                    build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "technicalProfessionalCriterionEO").
                    put("field", "serviceContractsPerformanceServices").
                    put("has_multiple_description_amount_date_recipients", "true").
                    put("title_code", "crit_selection_technical_service_contracts_performance_services_main").
                    put("description_code",
                            "crit_selection_technical_service_contracts_performance_services_description").
                    build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "technicalProfessionalCriterionEO").
                    put("field", "techniciansTechnicalBodies").
                    put("has_please_describe_them", "true").
                    put("title_code", "crit_selection_technical_technicians_technical_bodies_main").
                    put("description_code", "crit_selection_technical_technicians_technical_bodies_description").
                    build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "technicalProfessionalCriterionEO").
                    put("field", "workContractsTechnicians").
                    put("has_please_describe_them", "true").
                    put("title_code", "crit_selection_technical_work_contracts_technicians_main").
                    put("description_code", "crit_selection_technical_work_contracts_technicians_description").
                    build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "technicalProfessionalCriterionEO").
                    put("field", "technicalFacilitiesMeasures").
                    put("has_please_describe_them", "true").
                    put("title_code", "crit_selection_technical_technical_facilities_measures_main").
                    put("description_code", "crit_selection_technical_technical_facilities_measures_description").
                    build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "technicalProfessionalCriterionEO").
                    put("field", "studyResearchFacilities").
                    put("has_please_describe_them", "true").
                    put("title_code", "crit_selection_technical_study_research_facilities_main").
                    put("description_code", "crit_selection_technical_study_research_facilities_description").
                    build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "technicalProfessionalCriterionEO").
                    put("field", "supplyChainManagement").
                    put("has_please_describe_them", "true").
                    put("title_code", "crit_selection_technical_supply_chain_management_main").
                    put("description_code", "crit_selection_technical_supply_chain_management_description").
                    build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "technicalProfessionalCriterionEO").
                    put("field", "allowanceOfChecks").
                    put("has_your_answer", "true").
                    put("allows_checks", "true").
                    put("availableElectronically", "false").
                    put("title_code", "crit_selection_technical_allowance_of_checks_main").
                    put("description_code", "crit_selection_technical_allowance_of_checks_description").
                    build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "technicalProfessionalCriterionEO").
                    put("field", "educationalProfessionalQualifications").
                    put("has_please_describe_them", "true").
                    put("title_code", "crit_selection_technical_educational_professional_qualifications_main").
                    put("description_code",
                            "crit_selection_technical_educational_professional_qualifications_description").
                    build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "technicalProfessionalCriterionEO").
                    put("field", "environmentalManagementFeatures").
                    put("has_please_describe_them", "true").
                    put("title_code", "crit_selection_technical_environment_management_features_main").
                    put("description_code", "crit_selection_technical_environment_management_features_description").
                    build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "technicalProfessionalCriterionEO").
                    put("field", "numberManagerialStaff").
                    put("lastYearsNumber", LAST_YEARS_NUMBER).
                    put("title_code", "crit_selection_technical_number_managerial_staff_main").
                    put("description_code", "crit_selection_technical_number_managerial_staff_description").
                    build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "technicalProfessionalCriterionEO").
                    put("field", "averageAnnualManpower").
                    put("lastYearsNumber", LAST_YEARS_NUMBER).
                    put("title_code", "crit_selection_technical_average_annual_manpower_main").
                    put("description_code", "crit_selection_technical_average_annual_manpower_description").
                    build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "technicalProfessionalCriterionEO").
                    put("field", "toolsPlantTechnicalEquipment").
                    put("has_please_describe_them", "true").
                    put("title_code", "crit_selection_technical_tools_plant_main").
                    put("description_code", "crit_selection_technical_tools_plant_description").
                    build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "technicalProfessionalCriterionEO").
                    put("field", "subcontractingProportion").
                    put("has_please_specify", "true").
                    put("availableElectronically", "false").
                    put("title_code", "crit_selection_technical_subcontracting_proportion_main").
                    put("description_code", "crit_selection_technical_subcontracting_proportion_description").
                    build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "technicalProfessionalCriterionEO").
                    put("field", "supplyContractsSamplesDescriptionsWithoutCa").
                    put("has_your_answer", "true").
                    put("title_code", "crit_selection_technical_supply_contracts_without_ca_main").
                    put("description_code", "crit_selection_technical_supply_contracts_without_ca_description").
                    build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "technicalProfessionalCriterionEO").
                    put("field", "supplyContractsSamplesDescriptionsWithCa").
                    put("has_your_answer", "true").
                    put("title_code", "crit_selection_technical_supply_contracts_with_ca_main").
                    put("description_code", "crit_selection_technical_supply_contracts_with_ca_description").
                    build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "technicalProfessionalCriterionEO").
                    put("field", "supplyContractsCertificatesQc").
                    put("has_your_answer", "true").
                    put("has_please_describe_them", "true").
                    put("has_explain_supply_contracts_quality", "true").
                    put("title_code", "crit_selection_technical_supply_contracts_certificate_quality_main").
                    put("description_code", "crit_selection_technical_supply_contracts_certificate_quality_description").
                    build()
    };

    public static final Map[] qualityAssuranceListEO = new Map[] {
            ImmutableMap.<String, Object>builder().
                    put("template", "technicalProfessionalCriterionEO").
                    put("field", "certificateIndependentBodiesAboutQa").
                    put("has_your_answer", "true").
                    put("has_please_describe_them", "true").
                    put("has_explain_certificates_independent_quality", "true").
                    put("title_code", "crit_selection_quality_certificate_independent_bodies_quality_main").
                    put("description_code",
                            "crit_selection_quality_certificate_independent_bodies_quality_description").
                    build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "technicalProfessionalCriterionEO").
                    put("field", "certificateIndependentBodiesAboutEnvironmental").
                    put("has_your_answer", "true").
                    put("has_please_describe_them", "true").
                    put("has_explain_certificates_independent_environmental", "true").
                    put("title_code", "crit_selection_quality_certificate_independent_bodies_environmental_main").
                    put("description_code",
                            "crit_selection_quality_certificate_independent_bodies_environmental_description").
                    build()
    };

    /**
     * only for HTML version of pronting some templates are replaced with "css-print friendly"
     */
    public static final Map[] technicalListEO_UglyPrintVersion = new Map[technicalListEO.length];

    static {
        for (int i = 0; i < technicalListEO_UglyPrintVersion.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.putAll(technicalListEO[i]);
            if (i == 0 || i == 1 || i == 2) {
                map.put("template", "uglyPrintTemplate");
            }
            technicalListEO_UglyPrintVersion[i] = map;
        }
    }

    /**
     * TOP LEVEL EXCLUSION CA
     */
    public static final Map[] exclusionCA = new Map[] {
            ImmutableMap.<String, Object>builder().
                    put("template", "euCriteriaListTemplate").
                    put("id", "ca-criminal-convictions-section").
                    put("title_code", "crit_top_title_grounds_criminal_conv").
                    put("subtitle_code", "crit_eu_main_title_grounds_criminal_conv").
                    put("criteriaList", criminalListCA).build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "euCriteriaListTemplate").
                    put("id", "ca-payment-of-taxes-section").
                    put("title_code", "crit_top_title_grounds_payment_taxes").
                    put("subtitle_code", "crit_eu_main_title_payment_taxes").
                    put("criteriaList", taxesListCA).build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "euCriteriaListTemplate").
                    put("id", "ca-insolvency-section").
                    put("title_code", "crit_top_title_insolvency_conflicts").
                    put("subtitle_code", "crit_eu_main_breaching_obligations").
                    put("criteriaList", insolvencyListCA).build(),
    };

    /**
     * TOP LEVEL EXCLUSION EO
     */
    public static final Map[] exclusionEO = new Map[] {
            ImmutableMap.<String, Object>builder().
                    put("template", "euCriteriaListTemplate").
                    put("id", "criminal_conv").
                    put("title_code", "crit_top_title_grounds_criminal_conv").
                    put("subtitle_code", "crit_eu_main_title_grounds_criminal_conv_eo").
                    put("criteriaList", criminalListEO).build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "euCriteriaListTemplate").
                    put("id", "payment_taxes").
                    put("title_code", "crit_top_title_grounds_payment_taxes").
                    put("subtitle_code", "crit_eu_main_title_payment_taxes_eo").
                    put("criteriaList", taxesListEO).build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "euCriteriaListTemplate").
                    put("id", "insolvency_conflicts").
                    put("title_code", "crit_top_title_insolvency_conflicts").
                    put("subtitle_code", "crit_eu_main_breaching_obligations_eo").
                    put("criteriaList", insolvencyListEO).build()
    };

    /**
     * TOP LEVEL SELECTION CA
     */
    public static final Map[] selectionCA = new Map[] {
            ImmutableMap.<String, Object>builder().
                    put("template", "euCriteriaListTemplate").
                    put("id", "ca-suitability-section").
                    put("title_code", "createcasel_suitability").
                    put("subtitle_code", "crit_selection_suitability_article").
                    put("tooltip_code", "crit_selection_suitability_article_tooltip").
                    put("criteriaList", suitabilityListCA).build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "euCriteriaListTemplate").
                    put("id", "ca-economic-financial-section").
                    put("title_code", "createcasel_economic_and_financial_standing").
                    put("subtitle_code", "crit_selection_eo_economic_article").
                    put("tooltip_code", "crit_selection_economic_financial_standing_article_tooltip").
                    put("criteriaList", economicListCA).build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "euCriteriaListTemplate").
                    put("id", "ca-technical_professional_ability-section").
                    put("title_code", "createcasel_technical_professional_ability").
                    put("subtitle_code", "crit_selection_technical_professional_ability_article").
                    put("tooltip_code", "crit_selection_technical_professional_ability_article_tooltip").
                    put("criteriaList", technicalListCA).build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "euCriteriaListTemplate").
                    put("id", "ca-quality-assurance-section").
                    put("title_code", "createcasel_quality_assurance").
                    put("subtitle_code", "crit_selection_quality_assurance_article").
                    put("tooltip_code", "crit_selection_quality_assurance_tooltip").
                    put("criteriaList", qualityAssuranceListCA).build()
    };

    /**
     * TOP LEVEL SELECTION EO
     */
    public static final Map[] selectionEO = new Map[] {
            ImmutableMap.<String, Object>builder().
                    put("template", "euCriteriaListTemplate").
                    put("id", "eo-suitability-section").
                    put("title_code", "createcasel_suitability").
                    put("subtitle_code", "crit_selection_eo_suitability_article").
                    put("tooltip_code", "crit_selection_eo_suitability_article_tooltip").
                    put("criteriaList", suitabilityListEO).build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "euCriteriaListTemplate").
                    put("id", "eo-economic-financial-section").
                    put("title_code", "createcasel_economic_and_financial_standing").
                    put("subtitle_code", "crit_selection_eo_economic_article").
                    put("tooltip_code", "crit_selection_eo_economic_article_tooltip").
                    put("criteriaList", economicListEO).build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "euCriteriaListTemplate").
                    put("id", "eo-technical-professional-section").
                    put("title_code", "createcasel_technical_professional_ability").
                    put("subtitle_code", "crit_selection_technical_professional_ability_article").
                    put("tooltip_code", "crit_selection_technical_professional_ability_article_tooltip").
                    put("criteriaList", technicalListEO).build(),

            ImmutableMap.<String, Object>builder().
                    put("template", "euCriteriaListTemplate").
                    put("id", "eo-quality-assurance-section").
                    put("title_code", "createcasel_quality_assurance").
                    put("subtitle_code", "crit_selection_quality_assurance_article").
                    put("tooltip_code", "crit_selection_quality_assurance_tooltip").
                    put("criteriaList", qualityAssuranceListEO).build()
    };

}
