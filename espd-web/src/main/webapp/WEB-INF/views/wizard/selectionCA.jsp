<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<tiles:importAttribute name="flow"/>

<script>
    $(function () {
        $("#ca-satisfies-all-criteria").click(function () {
            $("#ca-selection-criteria").toggle();
        });
    });
</script>
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
            </div>
            <div id="ca-satisfies-all-section" class="collapse in">
                <div class="espd-panel-body panel-body">
                        <span data-i18n="crit_selection_ca_declares_that">
                            <s:message code='crit_selection_ca_declares_that'/>
                        </span>
                        <span data-i18n="crit_selection_declares_that_tooltip" data-toggle="tooltip"
                              title="<s:message code='crit_selection_declares_that_tooltip'/>"></span>

                    <div class="checkbox">
                        <label>
                            <form:checkbox id="ca-satisfies-all-criteria" path="selectionSatisfiesAll.exists"
                                           class="checktoggle" value="true"/>
                            <span data-i18n="crit_selection_satisfies_all_criteria">
                            <s:message code='crit_selection_satisfies_all_criteria'/>
                            </span>
                        </label>
                    </div>
                </div>
            </div>
        </div>
        <div class="tab-pane active" id="ca-selection-criteria">
            <div class="panel panel-default espd-panel">
                <div data-i18n="createcasel_suitability" class="espd-panel-heading" data-toggle="collapse"
                     data-target="#ca-suitability-section">
                    <s:message code='createcasel_suitability'/>
                </div>
                <div id="ca-suitability-section" class="collapse in">
                    <div class="espd-panel-body panel-body">
                        <span data-i18n="crit_selection_suitability_article">
                            <s:message code='crit_selection_suitability_article'/>
                        </span>
                        <span data-i18n="crit_selection_ca_suitability_tooltip" data-toggle="tooltip"
                              title="<s:message code='crit_selection_suitability_article_tooltip'/>">
                        </span>

                        <c:set var="field" value="enrolmentProfessionalRegister" scope="session"/>
                        <c:set var="title_code"
                               value="crit_selection_suitability_enrolment_professional_register_main"
                               scope="session"/>
                        <c:set var="tooltip_code" value="" scope="session"/>
                        <c:set var="description_code"
                               value="crit_selection_suitability_enrolment_professional_register_description"
                               scope="session"/>
                        <jsp:include page="editors/criteria.jsp"/>

                        <c:set var="field" value="enrolmentTradeRegister" scope="session"/>
                        <c:set var="title_code"
                               value="crit_selection_suitability_enrolment_trade_register_main"
                               scope="session"/>
                        <c:set var="tooltip_code" value="" scope="session"/>
                        <c:set var="description_code"
                               value="crit_selection_suitability_enrolment_trade_register_description"
                               scope="session"/>
                        <jsp:include page="editors/criteria.jsp"/>

                        <c:set var="field" value="serviceContractsAuthorisation" scope="session"/>
                        <c:set var="title_code" value="crit_selection_suitability_service_contracts_auth_main"
                               scope="session"/>
                        <c:set var="tooltip_code" value="" scope="session"/>
                        <c:set var="description_code"
                               value="crit_selection_suitability_service_contracts_auth_description"
                               scope="session"/>
                        <jsp:include page="editors/criteria.jsp"/>

                        <c:set var="field" value="serviceContractsMembership" scope="session"/>
                        <c:set var="title_code" value="crit_selection_suitability_service_contracts_membership_main"
                               scope="session"/>
                        <c:set var="tooltip_code" value="" scope="session"/>
                        <c:set var="description_code"
                               value="crit_selection_suitability_service_contracts_membership_description"
                               scope="session"/>
                        <jsp:include page="editors/criteria.jsp"/>
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

                        <c:set var="field" value="generalYearlyTurnover" scope="session"/>
                        <c:set var="title_code" value="crit_selection_economic_general_yearly_turnover_main"
                               scope="session"/>
                        <c:set var="tooltip_code" value="" scope="session"/>
                        <c:set var="description_code"
                               value="crit_selection_economic_general_yearly_turnover_description"
                               scope="session"/>
                        <jsp:include page="editors/criteria.jsp"/>

                        <c:set var="field" value="averageYearlyTurnover" scope="session"/>
                        <c:set var="title_code" value="crit_selection_economic_average_yearly_turnover_main"
                               scope="session"/>
                        <c:set var="tooltip_code" value="" scope="session"/>
                        <c:set var="description_code"
                               value="crit_selection_economic_average_yearly_turnover_description"
                               scope="session"/>
                        <jsp:include page="editors/criteria.jsp"/>

                        <c:set var="field" value="specificYearlyTurnover" scope="session"/>
                        <c:set var="title_code" value="crit_selection_economic_specific_yearly_turnover_main"
                               scope="session"/>
                        <c:set var="tooltip_code" value="" scope="session"/>
                        <c:set var="description_code"
                               value="crit_selection_economic_specific_yearly_turnover_description"
                               scope="session"/>
                        <jsp:include page="editors/criteria.jsp"/>

                        <c:set var="field" value="specificAverageTurnover" scope="session"/>
                        <c:set var="title_code" value="crit_selection_economic_specific_average_turnover_main"
                               scope="session"/>
                        <c:set var="tooltip_code" value="" scope="session"/>
                        <c:set var="description_code"
                               value="crit_selection_economic_specific_average_turnover_description"
                               scope="session"/>
                        <jsp:include page="editors/criteria.jsp"/>

                        <c:set var="field" value="financialRatio" scope="session"/>
                        <c:set var="title_code" value="crit_selection_economic_financial_ratio_main" scope="session"/>
                        <c:set var="tooltip_code" value="" scope="session"/>
                        <c:set var="description_code" value="crit_selection_economic_financial_ratio_description"
                               scope="session"/>
                        <jsp:include page="editors/criteria.jsp"/>

                        <c:set var="field" value="professionalRiskInsurance" scope="session"/>
                        <c:set var="title_code" value="crit_selection_economic_professional_risk_insurance_main"
                               scope="session"/>
                        <c:set var="tooltip_code" value="" scope="session"/>
                        <c:set var="description_code"
                               value="crit_selection_economic_professional_risk_insurance_description"
                               scope="session"/>
                        <jsp:include page="editors/criteria.jsp"/>

                        <c:set var="field" value="otherEconomicFinancialRequirements" scope="session"/>
                        <c:set var="title_code" value="crit_selection_economic_other_financial_requirements_main"
                               scope="session"/>
                        <c:set var="tooltip_code" value="" scope="session"/>
                        <c:set var="description_code"
                               value="crit_selection_economic_other_financial_requirements_description"
                               scope="session"/>
                        <jsp:include page="editors/criteria.jsp"/>
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

                        <c:set var="field" value="workContractsPerformanceOfWorks" scope="session"/>
                        <c:set var="title_code" value="crit_selection_technical_work_contracts_performance_works_main"
                               scope="session"/>
                        <c:set var="tooltip_code" value="" scope="session"/>
                        <c:set var="description_code"
                               value="crit_selection_technical_work_contracts_performance_works_description"
                               scope="session"/>
                        <jsp:include page="editors/criteria.jsp"/>

                        <c:set var="field" value="supplyContractsPerformanceDeliveries" scope="session"/>
                        <c:set var="title_code" value="crit_selection_technical_supply_contracts_performance_deliveries_main"
                               scope="session"/>
                        <c:set var="tooltip_code" value="" scope="session"/>
                        <c:set var="description_code"
                               value="crit_selection_technical_supply_contracts_performance_deliveries_description"
                               scope="session"/>
                        <jsp:include page="editors/criteria.jsp"/>

                        <c:set var="field" value="serviceContractsPerformanceServices" scope="session"/>
                        <c:set var="title_code" value="crit_selection_technical_service_contracts_performance_services_main"
                               scope="session"/>
                        <c:set var="tooltip_code" value="" scope="session"/>
                        <c:set var="description_code"
                               value="crit_selection_technical_service_contracts_performance_services_description"
                               scope="session"/>
                        <jsp:include page="editors/criteria.jsp"/>

                        <c:set var="field" value="techniciansTechnicalBodies" scope="session"/>
                        <c:set var="title_code" value="crit_selection_technical_technicians_technical_bodies_main"
                               scope="session"/>
                        <c:set var="tooltip_code" value="" scope="session"/>
                        <c:set var="description_code"
                               value="crit_selection_technical_technicians_technical_bodies_description"
                               scope="session"/>
                        <jsp:include page="editors/criteria.jsp"/>

                        <c:set var="field" value="workContractsTechnicians" scope="session"/>
                        <c:set var="title_code" value="crit_selection_technical_work_contracts_technicians_main"
                               scope="session"/>
                        <c:set var="tooltip_code" value="" scope="session"/>
                        <c:set var="description_code"
                               value="crit_selection_technical_work_contracts_technicians_description"
                               scope="session"/>
                        <jsp:include page="editors/criteria.jsp"/>

                        <c:set var="field" value="technicalFacilitiesMeasures" scope="session"/>
                        <c:set var="title_code" value="crit_selection_technical_technical_facilities_measures_main"
                               scope="session"/>
                        <c:set var="tooltip_code" value="" scope="session"/>
                        <c:set var="description_code"
                               value="crit_selection_technical_technical_facilities_measures_description"
                               scope="session"/>
                        <jsp:include page="editors/criteria.jsp"/>

                        <c:set var="field" value="studyResearchFacilities" scope="session"/>
                        <c:set var="title_code" value="crit_selection_technical_study_research_facilities_main"
                               scope="session"/>
                        <c:set var="tooltip_code" value="" scope="session"/>
                        <c:set var="description_code"
                               value="crit_selection_technical_study_research_facilities_description"
                               scope="session"/>
                        <jsp:include page="editors/criteria.jsp"/>

                        <c:set var="field" value="supplyChainManagement" scope="session"/>
                        <c:set var="title_code" value="crit_selection_technical_supply_chain_management_main"
                               scope="session"/>
                        <c:set var="tooltip_code" value="" scope="session"/>
                        <c:set var="description_code"
                               value="crit_selection_technical_supply_chain_management_description"
                               scope="session"/>
                        <jsp:include page="editors/criteria.jsp"/>

                        <c:set var="field" value="allowanceOfChecks" scope="session"/>
                        <c:set var="title_code" value="crit_selection_technical_allowance_of_checks_main"
                               scope="session"/>
                        <c:set var="tooltip_code" value="" scope="session"/>
                        <c:set var="description_code"
                               value="crit_selection_technical_allowance_of_checks_description"
                               scope="session"/>
                        <jsp:include page="editors/criteria.jsp"/>

                        <c:set var="field" value="educationalProfessionalQualifications" scope="session"/>
                        <c:set var="title_code" value="crit_selection_technical_educational_professional_qualifications_main"
                               scope="session"/>
                        <c:set var="tooltip_code" value="" scope="session"/>
                        <c:set var="description_code"
                               value="crit_selection_technical_educational_professional_qualifications_description"
                               scope="session"/>
                        <jsp:include page="editors/criteria.jsp"/>

                        <c:set var="field" value="environmentalManagementFeatures" scope="session"/>
                        <c:set var="title_code" value="crit_selection_technical_environment_management_features_main"
                               scope="session"/>
                        <c:set var="tooltip_code" value="" scope="session"/>
                        <c:set var="description_code"
                               value="crit_selection_technical_environment_management_features_description"
                               scope="session"/>
                        <jsp:include page="editors/criteria.jsp"/>

                        <c:set var="field" value="numberManagerialStaff" scope="session"/>
                        <c:set var="title_code" value="crit_selection_technical_number_managerial_staff_main"
                               scope="session"/>
                        <c:set var="tooltip_code" value="" scope="session"/>
                        <c:set var="description_code"
                               value="crit_selection_technical_number_managerial_staff_description"
                               scope="session"/>
                        <jsp:include page="editors/criteria.jsp"/>

                        <c:set var="field" value="averageAnnualManpower" scope="session"/>
                        <c:set var="title_code" value="crit_selection_technical_average_annual_manpower_main"
                               scope="session"/>
                        <c:set var="tooltip_code" value="" scope="session"/>
                        <c:set var="description_code"
                               value="crit_selection_technical_average_annual_manpower_description"
                               scope="session"/>
                        <jsp:include page="editors/criteria.jsp"/>

                        <c:set var="field" value="toolsPlantTechnicalEquipment" scope="session"/>
                        <c:set var="title_code" value="crit_selection_technical_tools_plant_main"
                               scope="session"/>
                        <c:set var="tooltip_code" value="" scope="session"/>
                        <c:set var="description_code"
                               value="crit_selection_technical_tools_plant_description"
                               scope="session"/>
                        <jsp:include page="editors/criteria.jsp"/>

                        <c:set var="field" value="subcontractingProportion" scope="session"/>
                        <c:set var="title_code" value="crit_selection_technical_subcontracting_proportion_main"
                               scope="session"/>
                        <c:set var="tooltip_code" value="" scope="session"/>
                        <c:set var="description_code"
                               value="crit_selection_technical_subcontracting_proportion_description"
                               scope="session"/>
                        <jsp:include page="editors/criteria.jsp"/>

                        <c:set var="field" value="supplyContractsSamplesDescriptionsWithoutCa" scope="session"/>
                        <c:set var="title_code" value="crit_selection_technical_supply_contracts_without_ca_main"
                               scope="session"/>
                        <c:set var="tooltip_code" value="" scope="session"/>
                        <c:set var="description_code"
                               value="crit_selection_technical_supply_contracts_without_ca_description"
                               scope="session"/>
                        <jsp:include page="editors/criteria.jsp"/>

                        <c:set var="field" value="supplyContractsSamplesDescriptionsWithCa" scope="session"/>
                        <c:set var="title_code" value="crit_selection_technical_supply_contracts_with_ca_main"
                               scope="session"/>
                        <c:set var="tooltip_code" value="" scope="session"/>
                        <c:set var="description_code"
                               value="crit_selection_technical_supply_contracts_with_ca_description"
                               scope="session"/>
                        <jsp:include page="editors/criteria.jsp"/>

                        <c:set var="field" value="supplyContractsCertificatesQc" scope="session"/>
                        <c:set var="title_code" value="crit_selection_technical_supply_contracts_certificate_quality_main"
                               scope="session"/>
                        <c:set var="tooltip_code" value="" scope="session"/>
                        <c:set var="description_code"
                               value="crit_selection_technical_supply_contracts_certificate_quality_description"
                               scope="session"/>
                        <jsp:include page="editors/criteria.jsp"/>

                        <c:set var="field" value="certificateIndependentBodiesAboutQa" scope="session"/>
                        <c:set var="title_code" value="crit_selection_technical_certificate_independent_bodies_quality_main"
                               scope="session"/>
                        <c:set var="tooltip_code" value="" scope="session"/>
                        <c:set var="description_code"
                               value="crit_selection_technical_certificate_independent_bodies_quality_description"
                               scope="session"/>
                        <jsp:include page="editors/criteria.jsp"/>

                        <c:set var="field" value="certificateIndependentBodiesAboutEnvironmental" scope="session"/>
                        <c:set var="title_code" value="crit_selection_technical_certificate_independent_bodies_environmental_main"
                               scope="session"/>
                        <c:set var="tooltip_code" value="" scope="session"/>
                        <c:set var="description_code"
                               value="crit_selection_technical_certificate_independent_bodies_environmental_description"
                               scope="session"/>
                        <jsp:include page="editors/criteria.jsp"/>
                    </div>
                </div>
            </div>
        </div>
        <tiles:insertDefinition name="footerButtons">
        </tiles:insertDefinition>
    </div>
</form:form>