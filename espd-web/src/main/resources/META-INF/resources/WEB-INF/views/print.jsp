<%@ page import="eu.europa.ec.grow.espd.xml.CriteriaTemplates" %>
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

<%
request.setAttribute("exclusionEO", eu.europa.ec.grow.espd.xml.CriteriaTemplates.exclusionEO);
request.setAttribute("suitabilityListEO", CriteriaTemplates.suitabilityListEO);
request.setAttribute("economicListEO", CriteriaTemplates.economicListEO);
request.setAttribute("technicalListEO", CriteriaTemplates.technicalListEO_UglyPrintVersion);
request.setAttribute("qualityAssuranceListEO", CriteriaTemplates.qualityAssuranceListEO);
%>

<script>
    $(function () {
    
        $("#ojsNumber").inputmask("9999/S 999-9999999");
        <c:if test="${agent == 'ca'}">
            // CA only needs to see the labels but not the values
            $(":radio").attr('checked', false);
            $("input:radio[data-target-show]").each(dataShow);
        </c:if>

        if($('#eo_registered_na').attr('checked')){
			$('#eo_registered_answer_yes').attr('checked', false);
			$('#eo_registered_answer_no').attr('checked', false);
			$('#eo_registered_answer_yes').val(false);
			$('#eo_registered_answer_no').val(false);
        }

        //replace inputs with spans
		$('#espdform').find('input:not([type=hidden])').each(function() {
			if($(this).attr('type') == "radio") {
				$(this).replaceWith($("<i />").attr("class",
					($(this).attr('checked') == "checked" || $(this).attr('checked') == "true") ? "fa fa-check-square-o" : "fa fa-square-o"
				));
			}
			else if($(this).attr('type') == "checkbox") {
				$(this).replaceWith($("<i />").attr("class",
					($(this).attr('checked') == "checked" || $(this).attr('checked') == "true") ? "fa fa-check-square-o" : "fa fa-square-o"
				));
			}
			else {
				$(this).replaceWith($("<span />").addClass("wordwrap").text(this.value));
			}
		});

		$('#espdform').find('select').each(function() {
			if($(this).find('option:selected').length == 1) {
				$(this).replaceWith($("<span />").text($(this).find('option:selected')[0].label));
			}
			else {
				$(this).replaceWith($("<span />").text(""));
			}
		});
		$('#espdform').find('textarea').each(function() {
			$(this).replaceWith($("<span />").addClass("wordwrap").text(this.value));
		});


    });
</script>

<style>
	/*
	.visible-print {
		display: block !important;
	}
	.hidden-print {
		display: none !important;
	}
	*/
	.espd-app-container {
		border-color: black !important;
	}
	#breadbar .breadcrumbs {
    	background-color: black;
    }
	
	#prevBtn, #nextBtn, #cancelBtn {
		color: black !important;
		border: 1px solid black !important;
	}
	
	.espd-panel-heading:after {
		content: "";
	}
	.espd-panel-heading {
		width: 100%;
	    top: 0px;
	    left: 0px;
	    
		color: black;
	    background: none;
	    border: 1px solid black;
	}
	.form-horizontal .control-label {
		padding-top: 0px;
	}
	.espd-panel {
		border: none;
		margin: 0px;
		
		box-shadow: none;
		-webkit-box-shadow: none;
	} 
	.panel-default {
		margin-right: 0px;
		margin-left: 0px;
	}
	.panel-body {
    	padding: 10px 0px 0px 0px;
    }
    .espd-container {
		color: black;
	}
	.alert-espd-info-dotted {
	    border: 1px dotted black;
	}
	.alert-espd-info {
	    background-color: lightgrey;
	}
</style>

<form:form id="espdform" role="form" class="form-horizontal" method="post" commandName="espd">

	<%-- PROCEDURE --%>
	
	<%@ include file="/WEB-INF/views/wizard/procedureForm.jsp" %>
 
	<%-- EXCLUSION --%>
    <div class="panel-default">

        <div class="paragraph">
            <h2>${span18n["createcaexcl_header"]}</h2>
        </div>
        
		<tiles:insertDefinition name="topLevelCriteriaTemplate">
			<tiles:putAttribute name="topLevelCriteriaList" value="${exclusionEO}"/>
		</tiles:insertDefinition>

        <div class="panel panel-default espd-panel">
            <div data-i18n="crit_top_title_purely_national" class="espd-panel-heading" data-toggle="collapse"
                 data-target="#ca-insolvency-section">
                <s:message code='crit_top_title_purely_national'/>
            </div>
            <div id="ca-insolvency-section" class="collapse in">
                <div class="espd-panel-body panel-body">
                    <span data-i18n="crit_eu_main_breaching_obligations" style="font-weight: bold;">
                        <s:message code='crit_eu_main_purely_national'/>
                    </span>
                    <c:if test="${espd.purelyNationalGrounds != null && espd.purelyNationalGrounds.exists}">
                        <tiles:insertDefinition name="exclusionFormTemplate">
                            <tiles:putAttribute name="field" value="purelyNationalGrounds"/>
                            <tiles:putAttribute name="title_code" value="crit_eu_title_purely_national"/>
                            <tiles:putAttribute name="description_code" value="crit_eu_text_purely_national"/>
                            <tiles:putAttribute name="selfCleaning" value="false"/>
                        </tiles:insertDefinition>
                    </c:if>
                </div>
            </div>
        </div>
    </div>

	<%-- SELECTION --%>
    <div class="panel-default">
        
        
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
            	${span18n["all_selection_switch"]}
            </div>
            <div id="eo-satisfies-all-section" class="collapse in">
                <div class="espd-panel-body panel-body">
					<strong>${span18n['crit_selection_eo_declares_that']}</strong>
                </div>
                <div class="row criteria-row">
                    <div class="col-md-5 criteria-cell-left">
                        <div class="form-group">
                            <div class="col-md-12">
                                <strong>${span18n['crit_selection_eo_satisfies_all_criteria']}</strong>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-7 criteria-cell-right">
                        <div class="col-md-12">
                            <div class="form-group">
							 	${span18n["crit_your_answer"]}
								<form:radiobutton path="selectionSatisfiesAll.answer" value="true" data-target-hide="${'#'}eo-satisfies-all-form"/>${span18n["yes"]}
								<form:radiobutton path="selectionSatisfiesAll.answer" value="false" data-target-show="${'#'}eo-satisfies-all-form"/>${span18n["no"]}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
       
        <div id="eo-satisfies-all-form" class="${espd['selectionSatisfiesAll'].answer ? 'collapse' : ''}">
        
			<tiles:insertDefinition name="euCriteriaListTemplate">
				<tiles:putAttribute name="id" value="eo-suitability-section"/>
				<tiles:putAttribute name="title_code" value="createcasel_suitability"/>
				<tiles:putAttribute name="subtitle_code" value="crit_selection_eo_suitability_article"/>
				<tiles:putAttribute name="disableTooltips" value="true"/>
				<tiles:putAttribute name="criteriaList" value="${suitabilityListEO}"/>
			</tiles:insertDefinition>

			<tiles:insertDefinition name="euCriteriaListTemplate">
				<tiles:putAttribute name="id" value="eo-economic-financial-section"/>
				<tiles:putAttribute name="title_code" value="createcasel_economic_and_financial_standing"/>
				<tiles:putAttribute name="subtitle_code" value="crit_selection_eo_economic_article"/>
				<tiles:putAttribute name="disableTooltips" value="true"/>
				<tiles:putAttribute name="criteriaList" value="${economicListEO}"/>
			</tiles:insertDefinition>
			
			<tiles:insertDefinition name="euCriteriaListTemplate">
				<tiles:putAttribute name="id" value="eo-technical-professional-section"/>
				<tiles:putAttribute name="title_code" value="createcasel_technical_professional_ability"/>
				<tiles:putAttribute name="subtitle_code" value="crit_selection_technical_professional_ability_article"/>
				<tiles:putAttribute name="disableTooltips" value="true"/>
				<tiles:putAttribute name="criteriaList" value="${technicalListEO}"/>
			</tiles:insertDefinition>

            <tiles:insertDefinition name="euCriteriaListTemplate">
                <tiles:putAttribute name="id" value="eo-quality-assurance-section"/>
                <tiles:putAttribute name="title_code" value="createcasel_quality_assurance"/>
                <tiles:putAttribute name="subtitle_code" value="crit_selection_quality_assurance_article"/>
                <tiles:putAttribute name="disableTooltips" value="true"/>
                <tiles:putAttribute name="criteriaList" value="${qualityAssuranceListEO}"/>
            </tiles:insertDefinition>
        
		</div>
    </div>

	<%-- FINISH --%>
	<div class="panel-default">
		<div class="paragraph">
			<h2>
				<span data-i18n="createcafinish_header"><s:message code="createcafinish_header"/></span>
			</h2>
		</div>
		<div class="panel panel-default espd-panel">
			<div data-i18n="createcafinish_reduction" class="espd-panel-heading" data-toggle="collapse" data-target="#finish-reduction-of-numbers-section">
				 <s:message code='createcafinish_reduction'/>
			</div>
            <div id="finish-reduction-of-numbers-section" class="collapse in">
                <div class="espd-panel-body panel-body">
					<div class="alert alert-espd-info">
						<ul class="fa-ul">
						<li>
							<i class="info-label fa fa-info-circle fa-lg fa-li"></i>
							<span data-i18n="createcafinish_toptext"><s:message code='createcafinish_toptext'/></span>
						</li>
						</ul>
					</div>
					<span data-i18n="createcafinish_reduction_question" style="font-weight: bold;">
                        <s:message code='createcafinish_reduction_question'/>
                    </span>
					<tiles:insertDefinition name="objectiveFormTemplate">
						<tiles:putAttribute name="field" value="meetsObjective"/>
						<tiles:putAttribute name="title_code" value="createcafinish_title_eo_declares_that"/>
						<tiles:putAttribute name="description_code" value="createcafinish_text_eo_declares_that"/>
					</tiles:insertDefinition>
                </div>
            </div>
		</div>
		<div class="panel panel-default espd-panel">
			<div data-i18n="createcafinish_concl_statements" class="espd-panel-heading" data-toggle="collapse" data-target="#finish-statements-signature-section">
				 <s:message code='createcafinish_concl_statements'/>
			</div>
            <div id="finish-statements-signature-section" class="collapse in">
                <div class="espd-panel-body panel-body">
                    <span data-i18n="createcafinish_concl_statements_text">
                        <s:message code='createcafinish_concl_statements_text'/>
                    </span>
                    <p>
	                    <span data-i18n="createcafinish_concl_statements_signature">
	                        <s:message code='createcafinish_concl_statements_signature'/>
	                    </span>
                    </p>
                    <div class="form-group">
                        <label class="control-label col-md-2 small">${span18n['crit_date']}</label>
                        <div class="col-md-4">
                           <form:input type="text" path="documentDate" cssClass="form-control datepicker" cssStyle="border-radius: 0;"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2 small">${span18n['place']}</label>
                        <div class="col-md-4">
                            <form:textarea rows="1" path="location" cssClass="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2 small">${span18n['signature']}</label>
                    </div>
                    <br/><br/><br/>
                </div>
            </div>
		</div>
        <tiles:insertDefinition name="footerButtons">
            <tiles:putAttribute name="nextCode" value="export"/>
            <tiles:putAttribute name="prev" value="finish"/>
            <tiles:putAttribute name="next" value="generate"/>
        </tiles:insertDefinition>
	</div>
</form:form>