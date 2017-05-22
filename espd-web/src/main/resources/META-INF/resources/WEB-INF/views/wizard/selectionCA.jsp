<%@page import="eu.europa.ec.grow.espd.domain.TechnicalProfessionalCriterion"%>
<%@page import="eu.europa.ec.grow.espd.domain.DynamicRequirementGroup"%>
<%@page import="eu.europa.ec.grow.espd.domain.EconomicFinancialStandingCriterion"%>
<%@page import="eu.europa.ec.grow.espd.domain.EspdDocument"%>
<%@page import="java.util.ArrayList"%>
<%@page import="eu.europa.ec.grow.espd.domain.EconomicOperatorImpl"%>
<%@page import="eu.europa.ec.grow.espd.domain.EconomicOperatorRepresentative"%>
<%@page import="org.springframework.util.CollectionUtils"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<%--
  ~
  ~ Copyright 2016 EUROPEAN COMMISSION
  ~
  ~ Licensed under the EUPL, Version 1.1 or – as soon they
  ~ will be approved by the European Commission - subsequent
  ~ versions of the EUPL (the "Licence");
  ~
  ~ You may not use this work except in compliance with the Licence.
  ~
  ~ You may obtain a copy of the Licence at:
  ~
  ~ https://joinup.ec.europa.eu/community/eupl/og_page/eupl
  ~
  ~ Unless required by applicable law or agreed to in
  ~ writing, software distributed under the Licence is
  ~ distributed on an "AS IS" basis,
  ~ WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
  ~ express or implied.
  ~ See the Licence for the specific language governing
  ~ permissions and limitations under the Licence.
  ~
  --%>

<%request.setAttribute("selectionCA", eu.europa.ec.grow.espd.xml.CriteriaTemplates.selectionCA);%>

<script>
    $(function () {
		$("input[name='usealpha']").change(function(){
			if(this.value == false || this.value == "false") {
				$('#usealpha-checkbox').attr('checked', false);
			}
		});
    });
</script>

<tiles:importAttribute name="flow"/>

<c:set var="usealpha" value="${espd.selectionSatisfiesAll != null && espd.selectionSatisfiesAll.exists}"/>

<%
	// needed to display at least one unbound element
	EspdDocument espd = (EspdDocument) request.getAttribute("espd");
	if (espd != null) {

		{// Add at least one Financial Ratio to be sure user will see it in form
			if (espd.getFinancialRatio() == null) {
				espd.setFinancialRatio(new EconomicFinancialStandingCriterion());
			}
			if (CollectionUtils.isEmpty(espd.getFinancialRatio().getUnboundedGroups())) {
				espd.getFinancialRatio().setUnboundedGroups(new ArrayList<DynamicRequirementGroup>());
				espd.getFinancialRatio().getUnboundedGroups().add(new DynamicRequirementGroup());
			}
		}

		{// Add at least one General Yearly Turnover to be sure user will see it in form
			if (espd.getGeneralYearlyTurnover() == null) {
				espd.setGeneralYearlyTurnover(new EconomicFinancialStandingCriterion());
			}
			if (CollectionUtils.isEmpty(espd.getGeneralYearlyTurnover().getUnboundedGroups())) {
				espd.getGeneralYearlyTurnover().setUnboundedGroups(new ArrayList<DynamicRequirementGroup>());
				espd.getGeneralYearlyTurnover().getUnboundedGroups().add(new DynamicRequirementGroup());
			}
		}

		{// Add at least one Specific Yearly Turnover to be sure user will see it in form
			if (espd.getSpecificYearlyTurnover() == null) {
				espd.setSpecificYearlyTurnover(new EconomicFinancialStandingCriterion());
			}
			if (CollectionUtils.isEmpty(espd.getSpecificYearlyTurnover().getUnboundedGroups())) {
				espd.getSpecificYearlyTurnover().setUnboundedGroups(new ArrayList<DynamicRequirementGroup>());
				espd.getSpecificYearlyTurnover().getUnboundedGroups().add(new DynamicRequirementGroup());
			}
		}

		{// Add at least one workContractsPerformanceOfWorks to be sure user will see it in form
			if (espd.getWorkContractsPerformanceOfWorks() == null) {
				espd.setWorkContractsPerformanceOfWorks(new TechnicalProfessionalCriterion());
			}
			if (CollectionUtils.isEmpty(espd.getWorkContractsPerformanceOfWorks().getUnboundedGroups())) {
				espd.getWorkContractsPerformanceOfWorks()
						.setUnboundedGroups(new ArrayList<DynamicRequirementGroup>());
				espd.getWorkContractsPerformanceOfWorks().getUnboundedGroups()
						.add(new DynamicRequirementGroup());
			}
		}
		{// Add at least one supplyContractsPerformanceDeliveries to be sure user will see it in form
			if (espd.getSupplyContractsPerformanceDeliveries() == null) {
				espd.setSupplyContractsPerformanceDeliveries(new TechnicalProfessionalCriterion());
			}
			if (CollectionUtils.isEmpty(espd.getSupplyContractsPerformanceDeliveries().getUnboundedGroups())) {
				espd.getSupplyContractsPerformanceDeliveries()
						.setUnboundedGroups(new ArrayList<DynamicRequirementGroup>());
				espd.getSupplyContractsPerformanceDeliveries().getUnboundedGroups()
						.add(new DynamicRequirementGroup());
			}
		}
		{// Add at least one serviceContractsPerformanceServices to be sure user will see it in form
			if (espd.getServiceContractsPerformanceServices() == null) {
				espd.setServiceContractsPerformanceServices(new TechnicalProfessionalCriterion());
			}
			if (CollectionUtils.isEmpty(espd.getServiceContractsPerformanceServices().getUnboundedGroups())) {
				espd.getServiceContractsPerformanceServices()
						.setUnboundedGroups(new ArrayList<DynamicRequirementGroup>());
				espd.getServiceContractsPerformanceServices().getUnboundedGroups()
						.add(new DynamicRequirementGroup());
			}
		}
	}
%>

<form:form id="espdform" role="form" class="form-horizontal" method="post" commandName="espd" data-toggle="validator" autocomplete="off">
	<tiles:insertDefinition name="viewChangeRole">
        <tiles:putAttribute name="agent" value="ca"/>
        <tiles:putAttribute name="page" value="${flow}/eo/selection"/>
        <tiles:putAttribute name="showLink" value="${flow == 'request'}"/>
    </tiles:insertDefinition>
    <div class="panel-default">
        <tiles:insertDefinition name="progress">
        	<tiles:putAttribute name="agent" value="${agent}"/>
        	<tiles:putAttribute name="flow" value="${flow}"/>
			<tiles:putAttribute name="selection" value="true"/>
        </tiles:insertDefinition>
        <div>
            <h2>
                <span data-i18n="createcasel_header"><s:message code="createcasel_header"/></span>
            </h2>
        </div>
        <div class="alert alert-espd-info-dotted"
                    ${span18n['createcasel_alert']}
  
					<label class="control-label">${span18n['question_to_use_alpha']}</label>
					<c:if test="${usealpha}">
						<input name="usealpha" data-target-show="#ca-selection-criteria" data-target-hide="#alpha-criterion" type="radio" value="false">${span18n['yes']}
						<input name="usealpha" data-target-show="#alpha-criterion" data-target-hide="#ca-selection-criteria" type="radio" value="true" checked="checked">${span18n['no']}
					</c:if>
					<c:if test="${!usealpha}">
						<input name="usealpha" data-target-show="#ca-selection-criteria" data-target-hide="#alpha-criterion" type="radio" value="false" checked="checked">${span18n['yes']}
						<input name="usealpha" data-target-show="#alpha-criterion" data-target-hide="#ca-selection-criteria" type="radio" value="true">${span18n['no']}
					</c:if>
        </div>

        <div id="alpha-criterion" class="panel panel-espd" style="${usealpha?'':'display:none'}">
            <div class="panel-heading" data-toggle="collapse" data-target="#ca-satisfies-all-section">
				<h4 class="panel-title">${span18n['all_selection_switch']}</h4>
            </div>
            <div id="ca-satisfies-all-section" class="collapse in">
                <div class="espd-panel-body panel-body">
                        ${span18n['crit_selection_ca_declares_that']}
                        <span data-i18n="crit_selection_declares_that_tooltip" data-toggle="tooltip" title="${i18n['crit_selection_declares_that_tooltip']}"></span>
	                    <div class="checkbox">
	                        <label>
	                            <form:checkbox id="usealpha-checkbox" path="selectionSatisfiesAll.exists" class="checktoggle" value="true"/>
	                            ${span18n['crit_selection_satisfies_all_criteria']}
	                        </label>
	                    </div>
	             </div>
            </div>
        </div>
        
        <div class="tab-pane active" id="ca-selection-criteria" style="${usealpha?'display:none':''}">
			<tiles:insertDefinition name="topLevelCriteriaTemplate">
				<tiles:putAttribute name="topLevelCriteriaList" value="${selectionCA}"/>
			</tiles:insertDefinition>
        </div>
        
        <tiles:insertDefinition name="footerButtons">
            <tiles:putAttribute name="prev" value="exclusion"/>
            <tiles:putAttribute name="next" value="finish"/>
        </tiles:insertDefinition>
    </div>
</form:form>