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
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

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

<%request.setAttribute("selectionEO", eu.europa.ec.grow.espd.xml.CriteriaTemplates.selectionEO);%>



<%-- Alpha criterion selected --%>
<c:set var="use_alpha" value="${espd.selectionSatisfiesAll.exists}"/>

<%-- Alpha criterion not selected but other selection criteria exists --%>
<c:set var="use_atod" value="${espd.atLeastOneSelectionCriterionWasSelected && !espd.selectionSatisfiesAll.exists }"/>

<%-- Both options when no selection and no alpha selecter OR all selection and alpha selected or not selected --%>
<c:set var="use_both" value="${(!espd.atLeastOneSelectionCriterionWasSelected && espd.selectionSatisfiesAll.exists == false) || espd.allSelectionCriterionWasSelectedExceptAlpha}"/>


<tiles:importAttribute name="flow"/>

<script>
    $(function () {
        $("#espdform").validate({
            errorContainer: $("div.errorContainer"),
            errorPlacement: function ($error, $element) {
            	$element.parent().append($error);
            }
        });
    });
</script>

<form:form id="espdform" role="form" class="form-horizontal" method="post" commandName="espd" data-toggle="validator">

    <tiles:insertDefinition name="viewChangeRole">
        <tiles:putAttribute name="agent" value="eo"/>
        <tiles:putAttribute name="page" value="${flow}/ca/selection"/>
        <tiles:putAttribute name="showLink" value="${flow == 'request'}"/>
    </tiles:insertDefinition>
    
    <div class="panel-default">
        <tiles:insertDefinition name="progress">
        	<tiles:putAttribute name="agent" value="${agent}"/>
        	<tiles:putAttribute name="flow" value="${flow}"/>
			<tiles:putAttribute name="selection" value="true"/>
        </tiles:insertDefinition>
        
        <div class="errorContainer alert alert-danger" style="display: none">
            <ul class="fa-ul">
                <li>
                    <i class="info-label fa fa-exclamation-triangle fa-lg fa-li"></i>
					${div18n['correct_errors']}
                    <div class="errorLabelContainer">
                        <ul></ul>
                    </div>
                </li>
            </ul>
        </div>
        <form:errors path="*" cssClass="errorContainer alert alert-danger"></form:errors>
        <div><h2>${span18n['createcasel_header']}</h2></div>
        <div class="alert alert-espd-info">
            <ul class="fa-ul">
                <li>
                    <i class="info-label fa fa-info-circle fa-lg fa-li"></i>${span18n['createeosel_alert']}
                </li>
            </ul>
        </div>
        
		<% 
        	EspdDocument espd = (EspdDocument)request.getAttribute("espd");
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
            	{// Add at least one workContractsPerformanceOfWorks to be sure user will see it in form
	        		if (espd.getWorkContractsPerformanceOfWorks() == null) {
	        			espd.setWorkContractsPerformanceOfWorks(new TechnicalProfessionalCriterion());
	        		}
		        	if (CollectionUtils.isEmpty(espd.getWorkContractsPerformanceOfWorks().getUnboundedGroups())) {
	        			espd.getWorkContractsPerformanceOfWorks().setUnboundedGroups(new ArrayList<DynamicRequirementGroup>());
	        			espd.getWorkContractsPerformanceOfWorks().getUnboundedGroups().add(new DynamicRequirementGroup());
		        	}
            	}
            	{// Add at least one supplyContractsPerformanceDeliveries to be sure user will see it in form
	        		if (espd.getSupplyContractsPerformanceDeliveries() == null) {
	        			espd.setSupplyContractsPerformanceDeliveries(new TechnicalProfessionalCriterion());
	        		}
		        	if (CollectionUtils.isEmpty(espd.getSupplyContractsPerformanceDeliveries().getUnboundedGroups())) {
	        			espd.getSupplyContractsPerformanceDeliveries().setUnboundedGroups(new ArrayList<DynamicRequirementGroup>());
	        			espd.getSupplyContractsPerformanceDeliveries().getUnboundedGroups().add(new DynamicRequirementGroup());
		        	}
            	}
            	{// Add at least one serviceContractsPerformanceServices to be sure user will see it in form
	        		if (espd.getServiceContractsPerformanceServices() == null) {
	        			espd.setServiceContractsPerformanceServices(new TechnicalProfessionalCriterion());
	        		}
		        	if (CollectionUtils.isEmpty(espd.getServiceContractsPerformanceServices().getUnboundedGroups())) {
	        			espd.getServiceContractsPerformanceServices().setUnboundedGroups(new ArrayList<DynamicRequirementGroup>());
	        			espd.getServiceContractsPerformanceServices().getUnboundedGroups().add(new DynamicRequirementGroup());
		        	}
            	}
        	}
        %>
        
        <c:if test="${use_both}">
			<div class="alert alert-espd-info-dotted">
				${span18n['createcasel_alert']}
				<label class="control-label">${span18n['question_to_use_alpha']}</label>
				<form:radiobutton name="usealpha" path="selectionSatisfiesAll.exists" data-target-show="#eo-selection-criteria" data-target-hide="#alpha-criterion" value="false"/>${span18n['yes']}
				<form:radiobutton name="usealpha" path="selectionSatisfiesAll.exists" data-target-show="#alpha-criterion" data-target-hide="#eo-selection-criteria" value="true"/>${span18n['no']}
			</div>
        </c:if>
        
        

		<c:if test="${use_both || use_alpha}">
		
            <div id="alpha-criterion"  class="panel panel-espd" style="${use_both?(espd.selectionSatisfiesAll.exists ? '' : 'display:none'):''}">
                <div class="panel-heading" data-toggle="collapse" data-target="#eo-satisfies-all-section">
                    <h4 class="panel-title">${span18n['all_selection_switch']}</h4>
                </div>
                <div id="eo-satisfies-all-section" class="collapse in">
                    <div class="espd-panel-body panel-body">
                        <strong>${span18n['crit_selection_eo_declares_that']}</strong>
                        <span data-i18n="crit_selection_eo_declares_that_tooltip" data-toggle="tooltip" title="${i18n['crit_selection_eo_declares_that_tooltip']}"></span>

	                    <div class="row criteria-row-form">
	                        <div class="col-md-5 criteria-row-check-left">
	                            <div class="form-group">
	                                <div class="col-md-12">
	                                    <strong>${span18n['crit_selection_eo_satisfies_all_criteria']}</strong>
	                                </div>
	                            </div>
	                        </div>
	                        <div class="col-md-7 criteria-row-check-right">
	                            <div class="col-md-12">
	                                <div class="form-group">
	                                    ${span18n["crit_your_answer"]}
	                                    <form:radiobutton path="selectionSatisfiesAll.answer" value="true"/>${span18n["yes"]}
	                                    <form:radiobutton path="selectionSatisfiesAll.answer" value="false"/>${span18n["no"]}
	                                </div>
	                            </div>
	                        </div>
	                    </div>
                    </div>
                </div>
            </div>
        </c:if>
        
		<c:if test="${use_both || use_atod}">
	        <div id="eo-selection-criteria" style="${use_both?(espd.selectionSatisfiesAll.exists ? 'display:none' : ''):''}">
				<tiles:insertDefinition name="topLevelCriteriaTemplate">
					<tiles:putAttribute name="topLevelCriteriaList" value="${selectionEO}"/>
				</tiles:insertDefinition>
			</div>
        </c:if>
        
                    <div class="alert alert-espd-info-dotted">
	<h1>DEBUGGY:</h1>
	selectionSatisfiesAll.exists : ${espd.selectionSatisfiesAll.exists} <br>
	atLeastOneSelectionCriterionWasSelected : ${espd.atLeastOneSelectionCriterionWasSelected} <br>
	allSelectionCriterionWasSelectedExceptAlpha : ${espd.allSelectionCriterionWasSelectedExceptAlpha} <br><br>
	use_alpha : ${use_alpha} <br>
	use_atod : ${use_atod} <br>
	use_both : ${use_both} <br>
</div>
        
        <tiles:insertDefinition name="footerButtons">
            <tiles:putAttribute name="prev" value="exclusion"/>
            <tiles:putAttribute name="next" value="finish"/>
        </tiles:insertDefinition>
        

    </div>
    

</form:form>


