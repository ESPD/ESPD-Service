<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<tiles:importAttribute name="flow"/>
<form:form id="espdform" role="form" class="form-horizontal" method="post" commandName="espd" data-toggle="validator">
	<tiles:insertDefinition name="viewChangeRole">
        <tiles:putAttribute name="agent" value="ca"/>
        <tiles:putAttribute name="page" value="${flow}/eo/selection"/>
    </tiles:insertDefinition>
    <div class="panel-default">
        <tiles:insertDefinition name="progress">
			<tiles:putAttribute name="selection" value="true"/>
        </tiles:insertDefinition>
        <div class="paragraph">
            <h2>
                <span data-i18n="createcasel_header"><s:message code="createcasel_header"/></span>
            </h2>
        </div>
        <div class="alert alert-espd-info">
            <ul class="fa-ul">
                <li>
                    <i class="info-label fa fa-info-circle fa-lg fa-li"></i>
                    <span data-i18n="createcasel_alert"><s:message code='createcasel_alert'/></span>
                </li>
            </ul>
        </div>
        <div class="panel panel-default espd-panel">
            <div class="espd-panel-heading" data-toggle="collapse" data-target="#ca-satisfies-all-section">
				${span18n["all_selection_switch"]}
            </div>
            <div id="ca-satisfies-all-section" class="collapse in">
                <div class="espd-panel-body panel-body">
                        ${span18n['crit_selection_ca_declares_that']}
                        <span data-i18n="crit_selection_declares_that_tooltip" data-toggle="tooltip" title="${i18n['crit_selection_declares_that_tooltip']}'/>"></span>
	                    <div class="checkbox">
	                        <label>
	                            <form:checkbox path="selectionSatisfiesAll.exists" class="checktoggle" value="true"/>
	                            ${span18n['crit_selection_satisfies_all_criteria']}
	                        </label>
	                    </div>
                </div>
            </div>
        </div>
        <div class="tab-pane active" id="ca-selection-criteria">
            <div class="panel panel-default espd-panel">
                <div data-i18n="createcasel_suitability" class="espd-panel-heading" data-toggle="collapse" data-target="#ca-suitability-section">
                    <s:message code='createcasel_suitability'/>
                </div>
                <div id="ca-suitability-section" class="collapse in">
                    <div class="espd-panel-body panel-body">
                        ${span18n['crit_selection_suitability_article']}
                        <span data-i18n="crit_selection_suitability_article_tooltip" data-toggle="tooltip" title="${i18n['crit_selection_suitability_article_tooltip']}"></span>
                        <tiles:insertDefinition name="requestCriterion">
                            <tiles:putAttribute name="field" value="enrolmentProfessionalRegister"/>
                            <tiles:putAttribute name="title_code" value="crit_selection_suitability_enrolment_professional_register_main"/>
                            <tiles:putAttribute name="description_code" value="crit_eu_text_guilty_misinterpretation"/>
                        </tiles:insertDefinition>
                            <tiles:insertDefinition name="requestCriterion">
                                <tiles:putAttribute name="field" value="enrolmentTradeRegister"/>
                                <tiles:putAttribute name="title_code" value="crit_selection_suitability_enrolment_trade_register_main"/>
                                <tiles:putAttribute name="description_code" value="crit_selection_suitability_enrolment_trade_register_description"/>
                            </tiles:insertDefinition>
                            <tiles:insertDefinition name="requestCriterion">
                                <tiles:putAttribute name="field" value="serviceContractsAuthorisation"/>
                                <tiles:putAttribute name="title_code" value="crit_selection_suitability_service_contracts_auth_main"/>
                                <tiles:putAttribute name="description_code" value="crit_selection_suitability_service_contracts_auth_description"/>
                            </tiles:insertDefinition>
                            <tiles:insertDefinition name="requestCriterion">
                                <tiles:putAttribute name="field" value="serviceContractsMembership"/>
                                <tiles:putAttribute name="title_code" value="crit_selection_suitability_service_contracts_membership_main"/>
                                <tiles:putAttribute name="description_code" value="crit_selection_suitability_service_contracts_membership_description"/>
                            </tiles:insertDefinition>
                    </div>
                </div>
            </div>
            <div class="panel panel-default espd-panel">
                <div data-i18n="createcasel_economic_and_financial_standing" class="espd-panel-heading"
                     data-toggle="collapse" data-target="#ca-economic-financial-section">
                    <s:message code='createcasel_economic_and_financial_standing'/>
                </div>
                <div id="ca-economic-financial-section" class="collapse in">
                    <div class="espd-panel-body panel-body">
                        <span data-i18n="crit_selection_ca_economic_article">
                            <s:message code='crit_selection_ca_economic_article'/>
                        </span>
                        <span data-i18n="crit_selection_economic_financial_standing_article_tooltip"
                              data-toggle="tooltip"
                              title="<s:message code='crit_selection_economic_financial_standing_article_tooltip'/>">
                        </span>
                        <tiles:insertDefinition name="requestCriterion">
                            <tiles:putAttribute name="field" value="generalYearlyTurnover"/>
                            <tiles:putAttribute name="title_code" value="crit_selection_economic_general_yearly_turnover_main"/>
                            <tiles:putAttribute name="description_code" value="crit_selection_economic_general_yearly_turnover_description"/>
                        </tiles:insertDefinition>
                        <tiles:insertDefinition name="requestCriterion">
                            <tiles:putAttribute name="field" value="averageYearlyTurnover"/>
                            <tiles:putAttribute name="title_code" value="crit_selection_economic_average_yearly_turnover_main"/>
                            <tiles:putAttribute name="description_code" value="crit_selection_economic_average_yearly_turnover_description"/>
                        </tiles:insertDefinition>
                        <tiles:insertDefinition name="requestCriterion">
                            <tiles:putAttribute name="field" value="specificYearlyTurnover"/>
                            <tiles:putAttribute name="title_code" value="crit_selection_economic_specific_yearly_turnover_main"/>
                            <tiles:putAttribute name="description_code" value="crit_selection_economic_specific_yearly_turnover_description"/>
                        </tiles:insertDefinition>
                        <tiles:insertDefinition name="requestCriterion">
                            <tiles:putAttribute name="field" value="specificAverageTurnover"/>
                            <tiles:putAttribute name="title_code" value="crit_selection_economic_specific_average_turnover_main"/>
                            <tiles:putAttribute name="description_code" value="crit_selection_economic_specific_average_turnover_description"/>
                        </tiles:insertDefinition>
                        <tiles:insertDefinition name="requestCriterion">
                            <tiles:putAttribute name="field" value="setupEconomicOperator"/>
                            <tiles:putAttribute name="title_code" value="crit_selection_economic_setup_eo_main"/>
                            <tiles:putAttribute name="description_code" value="crit_selection_economic_setup_eo_description"/>
                        </tiles:insertDefinition>
                        <tiles:insertDefinition name="requestCriterion">
                            <tiles:putAttribute name="field" value="financialRatio"/>
                            <tiles:putAttribute name="title_code" value="crit_selection_economic_financial_ratio_main"/>
                            <tiles:putAttribute name="description_code" value="crit_selection_economic_financial_ratio_description"/>
                        </tiles:insertDefinition>
                        <tiles:insertDefinition name="requestCriterion">
                            <tiles:putAttribute name="field" value="professionalRiskInsurance"/>
                            <tiles:putAttribute name="title_code" value="crit_selection_economic_professional_risk_insurance_main"/>
                            <tiles:putAttribute name="description_code" value="crit_selection_economic_professional_risk_insurance_description"/>
                        </tiles:insertDefinition>
                        <tiles:insertDefinition name="requestCriterion">
                            <tiles:putAttribute name="field" value="otherEconomicFinancialRequirements"/>
                            <tiles:putAttribute name="title_code" value="crit_selection_economic_other_financial_requirements_main"/>
                            <tiles:putAttribute name="description_code" value="crit_selection_economic_other_financial_requirements_description"/>
                        </tiles:insertDefinition>
                    </div>
                </div>
            </div>
            <div class="panel panel-default espd-panel">
                <div data-i18n="createcasel_technical_professional_ability" class="espd-panel-heading"
                     data-toggle="collapse"
                     data-target="#ca-technical_professional_ability-section">
                    <s:message code='createcasel_technical_professional_ability'/>
                </div>
                <div id="ca-technical_professional_ability-section" class="collapse in">
                    <div class="espd-panel-body panel-body">
                        <span data-i18n="crit_selection_technical_professional_ability_article">
                            <s:message code='crit_selection_technical_professional_ability_article'/>
                        </span>
                        <span data-i18n="crit_selection_technical_professional_ability_article_tooltip"
                              data-toggle="tooltip"
                              title="<s:message code='crit_selection_technical_professional_ability_article_tooltip'/>">
                        </span>
                        <tiles:insertDefinition name="requestCriterion">
                            <tiles:putAttribute name="field" value="workContractsPerformanceOfWorks"/>
                            <tiles:putAttribute name="title_code" value="crit_selection_technical_work_contracts_performance_works_main"/>
                            <tiles:putAttribute name="description_code" value="crit_selection_technical_work_contracts_performance_works_description"/>
                        </tiles:insertDefinition>
                        <tiles:insertDefinition name="requestCriterion">
                            <tiles:putAttribute name="field" value="supplyContractsPerformanceDeliveries"/>
                            <tiles:putAttribute name="title_code" value="crit_selection_technical_supply_contracts_performance_deliveries_main"/>
                            <tiles:putAttribute name="description_code" value="crit_selection_technical_supply_contracts_performance_deliveries_description"/>
                        </tiles:insertDefinition>
                        <tiles:insertDefinition name="requestCriterion">
                            <tiles:putAttribute name="field" value="serviceContractsPerformanceServices"/>
                            <tiles:putAttribute name="title_code" value="crit_selection_technical_service_contracts_performance_services_main"/>
                            <tiles:putAttribute name="description_code" value="crit_selection_technical_service_contracts_performance_services_description"/>
                        </tiles:insertDefinition>
                        <tiles:insertDefinition name="requestCriterion">
                            <tiles:putAttribute name="field" value="techniciansTechnicalBodies"/>
                            <tiles:putAttribute name="title_code" value="crit_selection_technical_technicians_technical_bodies_main"/>
                            <tiles:putAttribute name="description_code" value="crit_selection_technical_technicians_technical_bodies_description"/>
                        </tiles:insertDefinition>
                        <tiles:insertDefinition name="requestCriterion">
                            <tiles:putAttribute name="field" value="workContractsTechnicians"/>
                            <tiles:putAttribute name="title_code" value="crit_selection_technical_work_contracts_technicians_main"/>
                            <tiles:putAttribute name="description_code" value="crit_selection_technical_work_contracts_technicians_description"/>
                        </tiles:insertDefinition>
                        <tiles:insertDefinition name="requestCriterion">
                            <tiles:putAttribute name="field" value="technicalFacilitiesMeasures"/>
                            <tiles:putAttribute name="title_code" value="crit_selection_technical_technical_facilities_measures_main"/>
                            <tiles:putAttribute name="description_code" value="crit_selection_technical_technical_facilities_measures_description"/>
                        </tiles:insertDefinition>
                        <tiles:insertDefinition name="requestCriterion">
                            <tiles:putAttribute name="field" value="studyResearchFacilities"/>
                            <tiles:putAttribute name="title_code" value="crit_selection_technical_study_research_facilities_main"/>
                            <tiles:putAttribute name="description_code" value="crit_selection_technical_study_research_facilities_description"/>
                        </tiles:insertDefinition>
                        <tiles:insertDefinition name="requestCriterion">
                            <tiles:putAttribute name="field" value="supplyChainManagement"/>
                            <tiles:putAttribute name="title_code" value="crit_selection_technical_supply_chain_management_main"/>
                            <tiles:putAttribute name="description_code" value="crit_selection_technical_supply_chain_management_description"/>
                        </tiles:insertDefinition>
                        <tiles:insertDefinition name="requestCriterion">
                            <tiles:putAttribute name="field" value="allowanceOfChecks"/>
                            <tiles:putAttribute name="title_code" value="crit_selection_technical_allowance_of_checks_main"/>
                            <tiles:putAttribute name="description_code" value="crit_selection_technical_allowance_of_checks_description"/>
                        </tiles:insertDefinition>
                        <tiles:insertDefinition name="requestCriterion">
                            <tiles:putAttribute name="field" value="educationalProfessionalQualifications"/>
                            <tiles:putAttribute name="title_code" value="crit_selection_technical_educational_professional_qualifications_main"/>
                            <tiles:putAttribute name="description_code" value="crit_selection_technical_educational_professional_qualifications_description"/>
                        </tiles:insertDefinition>
                        <tiles:insertDefinition name="requestCriterion">
                            <tiles:putAttribute name="field" value="environmentalManagementFeatures"/>
                            <tiles:putAttribute name="title_code" value="crit_selection_technical_environment_management_features_main"/>
                            <tiles:putAttribute name="description_code" value="crit_selection_technical_environment_management_features_description"/>
                        </tiles:insertDefinition>
                        <tiles:insertDefinition name="requestCriterion">
                            <tiles:putAttribute name="field" value="numberManagerialStaff"/>
                            <tiles:putAttribute name="title_code" value="crit_selection_technical_number_managerial_staff_main"/>
                            <tiles:putAttribute name="description_code" value="crit_selection_technical_number_managerial_staff_description"/>
                        </tiles:insertDefinition>
                        <tiles:insertDefinition name="requestCriterion">
                            <tiles:putAttribute name="field" value="averageAnnualManpower"/>
                            <tiles:putAttribute name="title_code" value="crit_selection_technical_average_annual_manpower_main"/>
                            <tiles:putAttribute name="description_code" value="crit_selection_technical_average_annual_manpower_description"/>
                        </tiles:insertDefinition>
                        <tiles:insertDefinition name="requestCriterion">
                            <tiles:putAttribute name="field" value="toolsPlantTechnicalEquipment"/>
                            <tiles:putAttribute name="title_code" value="crit_selection_technical_tools_plant_main"/>
                            <tiles:putAttribute name="description_code" value="crit_selection_technical_tools_plant_description"/>
                        </tiles:insertDefinition>
                        <tiles:insertDefinition name="requestCriterion">
                            <tiles:putAttribute name="field" value="subcontractingProportion"/>
                            <tiles:putAttribute name="title_code" value="crit_selection_technical_subcontracting_proportion_main"/>
                            <tiles:putAttribute name="description_code" value="crit_selection_technical_subcontracting_proportion_description"/>
                        </tiles:insertDefinition>
                        <tiles:insertDefinition name="requestCriterion">
                            <tiles:putAttribute name="field" value="supplyContractsSamplesDescriptionsWithoutCa"/>
                            <tiles:putAttribute name="title_code" value="crit_selection_technical_supply_contracts_without_ca_main"/>
                            <tiles:putAttribute name="description_code" value="crit_selection_technical_supply_contracts_without_ca_description"/>
                        </tiles:insertDefinition>
                        <tiles:insertDefinition name="requestCriterion">
                            <tiles:putAttribute name="field" value="supplyContractsSamplesDescriptionsWithCa"/>
                            <tiles:putAttribute name="title_code" value="crit_selection_technical_supply_contracts_with_ca_main"/>
                            <tiles:putAttribute name="description_code" value="crit_selection_technical_supply_contracts_with_ca_description"/>
                        </tiles:insertDefinition>
                        <tiles:insertDefinition name="requestCriterion">
                            <tiles:putAttribute name="field" value="supplyContractsCertificatesQc"/>
                            <tiles:putAttribute name="title_code" value="crit_selection_technical_supply_contracts_certificate_quality_main"/>
                            <tiles:putAttribute name="description_code" value="crit_selection_technical_supply_contracts_certificate_quality_description"/>
                        </tiles:insertDefinition>
                        <tiles:insertDefinition name="requestCriterion">
                            <tiles:putAttribute name="field" value="certificateIndependentBodiesAboutQa"/>
                            <tiles:putAttribute name="title_code" value="crit_selection_technical_certificate_independent_bodies_quality_main"/>
                            <tiles:putAttribute name="description_code" value="crit_selection_technical_certificate_independent_bodies_quality_description"/>
                        </tiles:insertDefinition>
                        <tiles:insertDefinition name="requestCriterion">
                            <tiles:putAttribute name="field" value="certificateIndependentBodiesAboutEnvironmental"/>
                            <tiles:putAttribute name="title_code" value="crit_selection_technical_certificate_independent_bodies_environmental_main"/>
                            <tiles:putAttribute name="description_code" value="crit_selection_technical_certificate_independent_bodies_environmental_description"/>
                        </tiles:insertDefinition>
                    </div>
                </div>
            </div>
        </div>
        <tiles:insertDefinition name="footerButtons">
            <tiles:putAttribute name="prev" value="exclusion"/>
            <tiles:putAttribute name="next" value="finish"/>
        </tiles:insertDefinition>
    </div>
</form:form>