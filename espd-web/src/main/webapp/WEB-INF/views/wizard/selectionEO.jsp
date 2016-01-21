<%@page import="java.util.Calendar"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.lang.Integer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<c:set var="lastYearsAmount" value="${[2016,2015,2014,2013,2012]}" scope="application" />
<c:set var="lastYearsNumber" value="${[2016,2015,2014]}" scope="application" />

<form:form id="espdform" role="form" class="form-horizontal" method="post" commandName="espd" data-toggle="validator">
    <tiles:insertDefinition name="viewChangeRole">
        <tiles:putAttribute name="currentPage" value="selection"/>
    </tiles:insertDefinition>
    <div class="panel-default">
    
        <tiles:insertDefinition name="progress">
			<tiles:putAttribute name="selection" value="true"/>
        </tiles:insertDefinition>

        <div class="paragraph"><h2>${span18n['createcasel_header']}</h2></div>
        <div class="alert alert-espd-info">
            <ul class="fa-ul">
                <li>
                    <i class="info-label fa fa-info-circle fa-lg fa-li"></i>${span18n['createcasel_alert']}
                </li>
            </ul>
        </div>
        <div class="panel panel-default espd-panel">
            <div class="espd-panel-heading" data-toggle="collapse" data-target="#eo-satisfies-all-section">
            </div>
            <div id="eo-satisfies-all-section" class="collapse in">
                <div class="espd-panel-body panel-body">
					<strong>${span18n['crit_selection_eo_declares_that']}</strong>
					<span data-i18n="crit_selection_eo_declares_that_tooltip" data-toggle="tooltip" title="${i18n['crit_selection_eo_satisfies_all_criteria']}"></span>
                </div>
                <div class="row criteria-row">
                    <div class="col-md-5 criteria-cell-left">
                        <div class="form-group">
                            <div class="col-md-12">
                                <strong>1. ${span18n['crit_selection_eo_satisfies_all_criteria']}</strong>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-7 criteria-cell-right">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label class="control-label small" style="padding-top:0;">${span18n['crit_your_answer']}</label>
                                <form:checkbox path="selectionSatisfiesAll.exists" data-toggle="collapse" data-target-invert="${'#'}eo-satisfies-all-form" class="radioslide checktoggle form-control"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="eo-satisfies-all-form">
        <div class="panel panel-default espd-panel">
            <div data-i18n="createcasel_suitability" class="espd-panel-heading" data-toggle="collapse" data-target="#eo-suitability-section">
                <s:message code='createcasel_suitability'/>
            </div>
            <div id="eo-suitability-section" class="collapse in">
                <div class="espd-panel-body panel-body">
                    <div class="">
                        <strong data-i18n="crit_selection_eo_suitability_article">
                            <s:message code='crit_selection_eo_suitability_article'/>
                        </strong>
                        <span data-i18n="crit_selection_eo_suitability_article_tooltip" data-toggle="tooltip"
                              title="<s:message code='crit_selection_eo_suitability_article_tooltip'/>"></span>
                    </div>
                </div>
                <tiles:insertDefinition name="suitabilityCriterionEO">
                    <tiles:putAttribute name="field" value="enrolmentProfessionalRegister"/>
                    <tiles:putAttribute name="number" value="2"/>
                    <tiles:putAttribute name="has_your_answer" value="true"/>
                    <tiles:putAttribute name="title_code" value="crit_selection_suitability_enrolment_professional_register_main"/>
                    <tiles:putAttribute name="tooltip_code" value="crit_selection_suitability_enrolment_professional_register_description"/>
                </tiles:insertDefinition>

                <tiles:insertDefinition name="suitabilityCriterionEO">
                    <tiles:putAttribute name="field" value="enrolmentTradeRegister"/>
                    <tiles:putAttribute name="number" value="3"/>
                    <tiles:putAttribute name="has_your_answer" value="true"/>
                    <tiles:putAttribute name="title_code" value="crit_selection_suitability_enrolment_trade_register_main"/>
                    <tiles:putAttribute name="tooltip_code"
                                        value="crit_selection_suitability_enrolment_trade_register_description"/>
                </tiles:insertDefinition>

                <tiles:insertDefinition name="suitabilityCriterionEO">
                    <tiles:putAttribute name="field" value="serviceContractsAuthorisation"/>
                    <tiles:putAttribute name="number" value="4"/>
                    <tiles:putAttribute name="has_your_answer" value="true"/>
                    <tiles:putAttribute name="title_code" value="crit_selection_suitability_service_contracts_auth_main"/>
                    <tiles:putAttribute name="tooltip_code"
                                        value="crit_selection_suitability_service_contracts_auth_description"/>
                </tiles:insertDefinition>

                <tiles:insertDefinition name="suitabilityCriterionEO">
                    <tiles:putAttribute name="field" value="serviceContractsMembership"/>
                    <tiles:putAttribute name="number" value="5"/>
                    <tiles:putAttribute name="has_your_answer" value="true"/>
                    <tiles:putAttribute name="title_code" value="crit_selection_suitability_service_contracts_membership_main"/>
                    <tiles:putAttribute name="tooltip_code"
                                        value="crit_selection_suitability_service_contracts_membership_description"/>
                </tiles:insertDefinition>
            </div>
        </div>
        <div class="panel panel-default espd-panel">
            <div data-i18n="createcasel_economic_and_financial_standing" class="espd-panel-heading" data-toggle="collapse" data-target="#eo-economic-financial-section">
                <s:message code='createcasel_economic_and_financial_standing'/>
            </div>
            <div id="eo-economic-financial-section" class="collapse in">
                <div class="espd-panel-body panel-body">
                    <div class="">
                        <strong data-i18n="crit_selection_eo_economic_article">
                            <s:message code='crit_selection_eo_economic_article'/>
                        </strong>
                        <span data-i18n="crit_selection_eo_economic_article_tooltip" data-toggle="tooltip" title="${i18n['crit_selection_eo_economic_article_tooltip']}"></span>
                    </div>
                </div>
                <tiles:insertDefinition name="economicFinancialCriterionEO">
                    <tiles:putAttribute name="field" value="generalYearlyTurnover"/>
                    <tiles:putAttribute name="number" value="6"/>
                    <tiles:putAttribute name="has_your_answer" value="true"/>
                    <tiles:putAttribute name="lastYearsAmount" value="${lastYearsAmount}"/>
                    <tiles:putAttribute name="title_code" value="crit_selection_economic_general_yearly_turnover_main"/>
                    <tiles:putAttribute name="tooltip_code" value="crit_selection_economic_general_yearly_turnover_description"/>
                </tiles:insertDefinition>

                <tiles:insertDefinition name="economicFinancialCriterionEO">
                    <tiles:putAttribute name="field" value="averageYearlyTurnover"/>
                    <tiles:putAttribute name="number" value="7"/>
                    <tiles:putAttribute name="has_your_answer" value="true"/>
                    <tiles:putAttribute name="lastYearsAmount" value="${lastYearsAmount}"/>
                    <tiles:putAttribute name="title_code" value="crit_selection_economic_average_yearly_turnover_main"/>
                    <tiles:putAttribute name="tooltip_code"
                                        value="crit_selection_economic_average_yearly_turnover_description"/>
                </tiles:insertDefinition>

                <tiles:insertDefinition name="economicFinancialCriterionEO">
                    <tiles:putAttribute name="field" value="specificYearlyTurnover"/>
                    <tiles:putAttribute name="number" value="8"/>
                    <tiles:putAttribute name="has_your_answer" value="true"/>
                    <tiles:putAttribute name="lastYearsAmount" value="${lastYearsAmount}"/>
                    <tiles:putAttribute name="title_code" value="crit_selection_economic_specific_yearly_turnover_main"/>
                    <tiles:putAttribute name="tooltip_code"
                                        value="crit_selection_economic_specific_yearly_turnover_description"/>
                </tiles:insertDefinition>

                <tiles:insertDefinition name="economicFinancialCriterionEO">
                    <tiles:putAttribute name="field" value="specificAverageTurnover"/>
                    <tiles:putAttribute name="number" value="9"/>
                    <tiles:putAttribute name="has_your_answer" value="true"/>
                    <tiles:putAttribute name="lastYearsAmount" value="${lastYearsAmount}"/>
                    <tiles:putAttribute name="title_code" value="crit_selection_economic_specific_average_turnover_main"/>
                    <tiles:putAttribute name="tooltip_code"
                                        value="crit_selection_economic_specific_average_turnover_description"/>
                </tiles:insertDefinition>

                <tiles:insertDefinition name="economicFinancialCriterionEO">
                    <tiles:putAttribute name="field" value="financialRatio"/>
                    <tiles:putAttribute name="number" value="10"/>
                    <tiles:putAttribute name="has_multiple_description_ratio" value="true"/>
                    <tiles:putAttribute name="has_your_answer" value="false"/>
                    <tiles:putAttribute name="title_code" value="crit_selection_economic_financial_ratio_main"/>
                    <tiles:putAttribute name="tooltip_code"
                                        value="crit_selection_economic_financial_ratio_description"/>
                </tiles:insertDefinition>

                <tiles:insertDefinition name="economicFinancialCriterionEO">
                    <tiles:putAttribute name="field" value="professionalRiskInsurance"/>
                    <tiles:putAttribute name="number" value="11"/>
                    <tiles:putAttribute name="has_your_answer" value="false"/>
                    <tiles:putAttribute name="has_single_amount" value="true"/>
                    <tiles:putAttribute name="title_code" value="crit_selection_economic_professional_risk_insurance_main"/>
                    <tiles:putAttribute name="tooltip_code"
                                        value="crit_selection_economic_professional_risk_insurance_description"/>
                </tiles:insertDefinition>

                <tiles:insertDefinition name="economicFinancialCriterionEO">
                    <tiles:putAttribute name="field" value="otherEconomicFinancialRequirements"/>
                    <tiles:putAttribute name="number" value="12"/>
                    <tiles:putAttribute name="has_your_answer" value="false"/>
                    <tiles:putAttribute name="has_please_describe_them" value="true"/>
                    <tiles:putAttribute name="title_code" value="crit_selection_economic_other_financial_requirements_main"/>
                    <tiles:putAttribute name="tooltip_code"
                                        value="crit_selection_economic_other_financial_requirements_description"/>
                </tiles:insertDefinition>
            </div>
        </div>
        <div class="panel panel-default espd-panel">
            <div data-i18n="createcasel_technical_professional_ability" class="espd-panel-heading" data-toggle="collapse" data-target="#eo-technical-professional-section">
                <s:message code='createcasel_technical_professional_ability'/>
            </div>
            <div id="eo-technical-professional-section" class="collapse in">
                <div class="espd-panel-body panel-body">
                    <div class="">
                        <strong data-i18n="crit_selection_technical_professional_ability_article">
                            <s:message code='crit_selection_technical_professional_ability_article'/>
                        </strong>
                        <span data-i18n="crit_selection_technical_professional_ability_article_tooltip" data-toggle="tooltip"
                              title="<s:message code='crit_selection_technical_professional_ability_article_tooltip'/>"></span>
                    </div>
                </div>

                <tiles:insertDefinition name="technicalProfessionalCriterionEO">
                    <tiles:putAttribute name="field" value="workContractsPerformanceOfWorks"/>
                    <tiles:putAttribute name="number" value="13"/>
                    <tiles:putAttribute name="has_multiple_description_amount_date_recipients" value="true"/>
                    <tiles:putAttribute name="title_code" value="crit_selection_technical_work_contracts_performance_works_main"/>
                    <tiles:putAttribute name="tooltip_code"
                                        value="crit_selection_technical_work_contracts_performance_works_description"/>
                </tiles:insertDefinition>

                <tiles:insertDefinition name="technicalProfessionalCriterionEO">
                    <tiles:putAttribute name="field" value="supplyContractsPerformanceDeliveries"/>
                    <tiles:putAttribute name="number" value="14"/>
                    <tiles:putAttribute name="has_multiple_description_amount_date_recipients" value="true"/>
                    <tiles:putAttribute name="title_code" value="crit_selection_technical_supply_contracts_performance_deliveries_main"/>
                    <tiles:putAttribute name="tooltip_code"
                                        value="crit_selection_technical_supply_contracts_performance_deliveries_description"/>
                </tiles:insertDefinition>

                <tiles:insertDefinition name="technicalProfessionalCriterionEO">
                    <tiles:putAttribute name="field" value="serviceContractsPerformanceServices"/>
                    <tiles:putAttribute name="number" value="15"/>
                    <tiles:putAttribute name="has_multiple_description_amount_date_recipients" value="true"/>
                    <tiles:putAttribute name="title_code" value="crit_selection_technical_service_contracts_performance_services_main"/>
                    <tiles:putAttribute name="tooltip_code"
                                        value="crit_selection_technical_service_contracts_performance_services_description"/>
                </tiles:insertDefinition>

                <tiles:insertDefinition name="technicalProfessionalCriterionEO">
                    <tiles:putAttribute name="field" value="techniciansTechnicalBodies"/>
                    <tiles:putAttribute name="number" value="16"/>
                    <tiles:putAttribute name="has_please_describe_them" value="true"/>
                    <tiles:putAttribute name="title_code" value="crit_selection_technical_technicians_technical_bodies_main"/>
                    <tiles:putAttribute name="tooltip_code"
                                        value="crit_selection_technical_technicians_technical_bodies_description"/>
                </tiles:insertDefinition>

                <tiles:insertDefinition name="technicalProfessionalCriterionEO">
                    <tiles:putAttribute name="field" value="workContractsTechnicians"/>
                    <tiles:putAttribute name="number" value="17"/>
                    <tiles:putAttribute name="has_please_describe_them" value="true"/>
                    <tiles:putAttribute name="title_code" value="crit_selection_technical_work_contracts_technicians_main"/>
                    <tiles:putAttribute name="tooltip_code"
                                        value="crit_selection_technical_work_contracts_technicians_description"/>
                </tiles:insertDefinition>

                <tiles:insertDefinition name="technicalProfessionalCriterionEO">
                    <tiles:putAttribute name="field" value="technicalFacilitiesMeasures"/>
                    <tiles:putAttribute name="number" value="18"/>
                    <tiles:putAttribute name="has_please_describe_them" value="true"/>
                    <tiles:putAttribute name="title_code" value="crit_selection_technical_technical_facilities_measures_main"/>
                    <tiles:putAttribute name="tooltip_code"
                                        value="crit_selection_technical_technical_facilities_measures_description"/>
                </tiles:insertDefinition>

                <tiles:insertDefinition name="technicalProfessionalCriterionEO">
                    <tiles:putAttribute name="field" value="studyResearchFacilities"/>
                    <tiles:putAttribute name="number" value="19"/>
                    <tiles:putAttribute name="has_please_describe_them" value="true"/>
                    <tiles:putAttribute name="title_code" value="crit_selection_technical_study_research_facilities_main"/>
                    <tiles:putAttribute name="tooltip_code"
                                        value="crit_selection_technical_study_research_facilities_description"/>
                </tiles:insertDefinition>

                <tiles:insertDefinition name="technicalProfessionalCriterionEO">
                    <tiles:putAttribute name="field" value="supplyChainManagement"/>
                    <tiles:putAttribute name="number" value="20"/>
                    <tiles:putAttribute name="has_please_describe_them" value="true"/>
                    <tiles:putAttribute name="title_code" value="crit_selection_technical_supply_chain_management_main"/>
                    <tiles:putAttribute name="tooltip_code"
                                        value="crit_selection_technical_supply_chain_management_description"/>
                </tiles:insertDefinition>

                <tiles:insertDefinition name="technicalProfessionalCriterionEO">
                    <tiles:putAttribute name="field" value="allowanceOfChecks"/>
                    <tiles:putAttribute name="number" value="21"/>
                    <tiles:putAttribute name="has_your_answer" value="true"/>
                    <tiles:putAttribute name="allows_checks" value="true"/>
                    <tiles:putAttribute name="has_info_electronically" value="false"/>
                    <tiles:putAttribute name="title_code" value="crit_selection_technical_allowance_of_checks_main"/>
                    <tiles:putAttribute name="tooltip_code"
                                        value="crit_selection_technical_allowance_of_checks_description"/>
                </tiles:insertDefinition>

                <tiles:insertDefinition name="technicalProfessionalCriterionEO">
                    <tiles:putAttribute name="field" value="educationalProfessionalQualifications"/>
                    <tiles:putAttribute name="number" value="22"/>
                    <tiles:putAttribute name="has_please_describe_them" value="true"/>
                    <tiles:putAttribute name="title_code" value="crit_selection_technical_educational_professional_qualifications_main"/>
                    <tiles:putAttribute name="tooltip_code"
                                        value="crit_selection_technical_educational_professional_qualifications_description"/>
                </tiles:insertDefinition>

                <tiles:insertDefinition name="technicalProfessionalCriterionEO">
                    <tiles:putAttribute name="field" value="environmentalManagementFeatures"/>
                    <tiles:putAttribute name="number" value="23"/>
                    <tiles:putAttribute name="has_please_describe_them" value="true"/>
                    <tiles:putAttribute name="title_code" value="crit_selection_technical_environment_management_features_main"/>
                    <tiles:putAttribute name="tooltip_code"
                                        value="crit_selection_technical_environment_management_features_description"/>
                </tiles:insertDefinition>

                <tiles:insertDefinition name="technicalProfessionalCriterionEO">
                    <tiles:putAttribute name="field" value="numberManagerialStaff"/>
                    <tiles:putAttribute name="number" value="24"/>
                    <tiles:putAttribute name="lastYearsNumber" value="${lastYearsNumber}"/>
                    <tiles:putAttribute name="title_code" value="crit_selection_technical_number_managerial_staff_main"/>
                    <tiles:putAttribute name="tooltip_code"
                                        value="crit_selection_technical_number_managerial_staff_description"/>
                </tiles:insertDefinition>

                <tiles:insertDefinition name="technicalProfessionalCriterionEO">
                    <tiles:putAttribute name="field" value="averageAnnualManpower"/>
                    <tiles:putAttribute name="number" value="25"/>
                    <tiles:putAttribute name="lastYearsNumber" value="${lastYearsNumber}"/>
                    <tiles:putAttribute name="title_code" value="crit_selection_technical_average_annual_manpower_main"/>
                    <tiles:putAttribute name="tooltip_code"
                                        value="crit_selection_technical_average_annual_manpower_description"/>
                </tiles:insertDefinition>

                <tiles:insertDefinition name="technicalProfessionalCriterionEO">
                    <tiles:putAttribute name="field" value="toolsPlantTechnicalEquipment"/>
                    <tiles:putAttribute name="number" value="26"/>
                    <tiles:putAttribute name="has_please_describe_them" value="true"/>
                    <tiles:putAttribute name="title_code" value="crit_selection_technical_tools_plant_main"/>
                    <tiles:putAttribute name="tooltip_code"
                                        value="crit_selection_technical_tools_plant_description"/>
                </tiles:insertDefinition>

                <tiles:insertDefinition name="technicalProfessionalCriterionEO">
                    <tiles:putAttribute name="field" value="subcontractingProportion"/>
                    <tiles:putAttribute name="number" value="27"/>
                    <tiles:putAttribute name="has_please_describe_them" value="true"/>
                    <tiles:putAttribute name="title_code" value="crit_selection_technical_subcontracting_proportion_main"/>
                    <tiles:putAttribute name="tooltip_code"
                                        value="crit_selection_technical_subcontracting_proportion_description"/>
                </tiles:insertDefinition>

                <tiles:insertDefinition name="technicalProfessionalCriterionEO">
                    <tiles:putAttribute name="field" value="supplyContractsSamplesDescriptionsWithoutCa"/>
                    <tiles:putAttribute name="number" value="28"/>
                    <tiles:putAttribute name="has_your_answer" value="true"/>
                    <tiles:putAttribute name="title_code" value="crit_selection_technical_supply_contracts_without_ca_main"/>
                    <tiles:putAttribute name="tooltip_code"
                                        value="crit_selection_technical_supply_contracts_without_ca_description"/>
                </tiles:insertDefinition>

                <tiles:insertDefinition name="technicalProfessionalCriterionEO">
                    <tiles:putAttribute name="field" value="supplyContractsSamplesDescriptionsWithCa"/>
                    <tiles:putAttribute name="number" value="29"/>
                    <tiles:putAttribute name="has_your_answer" value="true"/>
                    <tiles:putAttribute name="title_code" value="crit_selection_technical_supply_contracts_with_ca_main"/>
                    <tiles:putAttribute name="tooltip_code"
                                        value="crit_selection_technical_supply_contracts_with_ca_description"/>
                </tiles:insertDefinition>

                <tiles:insertDefinition name="technicalProfessionalCriterionEO">
                    <tiles:putAttribute name="field" value="supplyContractsCertificatesQc"/>
                    <tiles:putAttribute name="number" value="30"/>
                    <tiles:putAttribute name="has_your_answer" value="true"/>
                    <tiles:putAttribute name="has_please_describe_them" value="true"/>
                    <tiles:putAttribute name="has_explain_supply_contracts_quality" value="true"/>
                    <tiles:putAttribute name="title_code" value="crit_selection_technical_supply_contracts_certificate_quality_main"/>
                    <tiles:putAttribute name="tooltip_code"
                                        value="crit_selection_technical_supply_contracts_certificate_quality_description"/>
                </tiles:insertDefinition>

                <tiles:insertDefinition name="technicalProfessionalCriterionEO">
                    <tiles:putAttribute name="field" value="certificateIndependentBodiesAboutQa"/>
                    <tiles:putAttribute name="number" value="31"/>
                    <tiles:putAttribute name="has_your_answer" value="true"/>
                    <tiles:putAttribute name="has_please_describe_them" value="true"/>
                    <tiles:putAttribute name="has_explain_certificates_independent_quality" value="true"/>
                    <tiles:putAttribute name="title_code" value="crit_selection_technical_certificate_independent_bodies_quality_main"/>
                    <tiles:putAttribute name="tooltip_code"
                                        value="crit_selection_technical_certificate_independent_bodies_quality_description"/>
                </tiles:insertDefinition>

                <tiles:insertDefinition name="technicalProfessionalCriterionEO">
                    <tiles:putAttribute name="field" value="certificateIndependentBodiesAboutEnvironmental"/>
                    <tiles:putAttribute name="number" value="32"/>
                    <tiles:putAttribute name="has_your_answer" value="true"/>
                    <tiles:putAttribute name="has_please_describe_them" value="true"/>
                    <tiles:putAttribute name="has_explain_certificates_independent_environmental" value="true"/>
                    <tiles:putAttribute name="title_code" value="crit_selection_technical_certificate_independent_bodies_environmental_main"/>
                    <tiles:putAttribute name="tooltip_code"
                                        value="crit_selection_technical_certificate_independent_bodies_environmental_description"/>
                </tiles:insertDefinition>
            </div>
        </div>
        </div>
        
        <tiles:insertDefinition name="footerButtons">
        </tiles:insertDefinition>
    </div>
</form:form>