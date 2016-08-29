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
				).attr("style","margin: 4px;"));
			}
			else if($(this).attr('type') == "checkbox") {
				$(this).replaceWith($("<i />").attr("class",
					($(this).attr('checked') == "checked" || $(this).attr('checked') == "true") ? "fa fa-check-square-o" : "fa fa-square-o"
				).attr("style","margin: 4px;"));
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
	.alert-espd-info {
		display: none !important;
	}
	
	.info-label {
		display: none !important;
	}

	.panel-heading:after {
		content: "";
	}
	
	.criteria-row-form {
	    border: none;
	    border-top: 1px solid transparent;
	    border-right: none;
	    border-left: none;
	    margin-right: 20px;
	    margin-left: 20px;
	    margin-bottom: -1px;
	}
	
	.criteria-row-form-left {
		border: none; padding-top: 5px;padding-left: 5px; padding-top: 7px;
	}
	
	.criteria-row-form-right {
		border: none; padding:20px; left: -1px;  padding-bottom: 0px;
	}

	#loader-wrapper .loader-section {
	    position: relative;
	    top: 0;
	    width: 51%;
	    height: 100%;
	    background: #222222;
	    z-index: 1000;
	}

.chromeframe {
    margin: 0.2em 0;
    background: #ccc;
    color: #000;
    padding: 0.2em 0;
}

#loader-wrapper {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    z-index: 1000;
}
#loader {
    display: block;
    position: fixed;
    left: 50%;
    top: 50%;
    width: 150px;
    height: 150px;
    margin: -75px 0 0 -75px;
    border-radius: 50%;
    border: 3px solid transparent;
    border-top-color: #04498a;
 
    -webkit-animation: spin 2s linear infinite; /* Chrome, Opera 15+, Safari 5+ */
    animation: spin 2s linear infinite; /* Chrome, Firefox 16+, IE 10+, Opera */

    z-index: 1001;
}

    #loader:before {
        content: "";
        position: absolute;
        top: 5px;
        left: 5px;
        right: 5px;
        bottom: 5px;
        border-radius: 50%;
        border: 3px solid transparent;
        border-top-color: #04498a;

        -webkit-animation: spin 3s linear infinite; /* Chrome, Opera 15+, Safari 5+ */
        animation: spin 3s linear infinite; /* Chrome, Firefox 16+, IE 10+, Opera */
    }

    #loader:after {
        content: "";
        position: absolute;
        top: 15px;
        left: 15px;
        right: 15px;
        bottom: 15px;
        border-radius: 50%;
        border: 3px solid transparent;
        border-top-color: #04498a;

        -webkit-animation: spin 1.5s linear infinite; /* Chrome, Opera 15+, Safari 5+ */
          animation: spin 1.5s linear infinite; /* Chrome, Firefox 16+, IE 10+, Opera */
    }

    @-webkit-keyframes spin {
        0%   { 
            -webkit-transform: rotate(0deg);  /* Chrome, Opera 15+, Safari 3.1+ */
            -ms-transform: rotate(0deg);  /* IE 9 */
            transform: rotate(0deg);  /* Firefox 16+, IE 10+, Opera */
        }
        100% {
            -webkit-transform: rotate(360deg);  /* Chrome, Opera 15+, Safari 3.1+ */
            -ms-transform: rotate(360deg);  /* IE 9 */
            transform: rotate(360deg);  /* Firefox 16+, IE 10+, Opera */
        }
    }
    @keyframes spin {
        0%   { 
            -webkit-transform: rotate(0deg);  /* Chrome, Opera 15+, Safari 3.1+ */
            -ms-transform: rotate(0deg);  /* IE 9 */
            transform: rotate(0deg);  /* Firefox 16+, IE 10+, Opera */
        }
        100% {
            -webkit-transform: rotate(360deg);  /* Chrome, Opera 15+, Safari 3.1+ */
            -ms-transform: rotate(360deg);  /* IE 9 */
            transform: rotate(360deg);  /* Firefox 16+, IE 10+, Opera */
        }
    }

    #loader-wrapper .loader-section {
        position: fixed;
        opacity: 0.5;
        top: 0;
        width: 50%;
        height: 100%;
        background: #222222;
        z-index: 1000;
        -webkit-transform: translateX(0);  /* Chrome, Opera 15+, Safari 3.1+ */
        -ms-transform: translateX(0);  /* IE 9 */
        transform: translateX(0);  /* Firefox 16+, IE 10+, Opera */
    }

    #loader-wrapper .loader-section.section-left {
        left: 0;
    }

    #loader-wrapper .loader-section.section-right {
        right: 0;
    }

    /* Loaded */
    .loaded #loader-wrapper .loader-section.section-left {
        -webkit-transform: translateX(-100%);  /* Chrome, Opera 15+, Safari 3.1+ */
            -ms-transform: translateX(-100%);  /* IE 9 */
                transform: translateX(-100%);  /* Firefox 16+, IE 10+, Opera */

        -webkit-transition: all 0.7s 0.3s cubic-bezier(0.645, 0.045, 0.355, 1.000);  
                transition: all 0.7s 0.3s cubic-bezier(0.645, 0.045, 0.355, 1.000);
    }

    .loaded #loader-wrapper .loader-section.section-right {
        -webkit-transform: translateX(100%);  /* Chrome, Opera 15+, Safari 3.1+ */
            -ms-transform: translateX(100%);  /* IE 9 */
                transform: translateX(100%);  /* Firefox 16+, IE 10+, Opera */

-webkit-transition: all 0.7s 0.3s cubic-bezier(0.645, 0.045, 0.355, 1.000);  
        transition: all 0.7s 0.3s cubic-bezier(0.645, 0.045, 0.355, 1.000);
    }
    
    .loaded #loader {
        opacity: 0;
        -webkit-transition: all 0.3s ease-out;  
                transition: all 0.3s ease-out;
    }
    .loaded #loader-wrapper {
        visibility: hidden;

        -webkit-transform: translateY(-100%);  /* Chrome, Opera 15+, Safari 3.1+ */
            -ms-transform: translateY(-100%);  /* IE 9 */
                transform: translateY(-100%);  /* Firefox 16+, IE 10+, Opera */

        -webkit-transition: all 0.3s 1s ease-out;  
                transition: all 0.3s 1s ease-out;
    }

</style>

<div id="loader-wrapper">
    <div id="loader"></div>
    <div class="loader-section section-left"></div>
    <div class="loader-section section-right"></div>
</div>

<form:form id="espdform" role="form" class="form-horizontal" method="post" commandName="espd">

	<%-- PROCEDURE --%>
	
	<%@ include file="/WEB-INF/views/wizard/procedureForm.jsp" %>
 
	<%-- EXCLUSION --%>
    <div class="panel-default">

        <div>
            <h2>${span18n["createcaexcl_header"]}</h2>
        </div>
        
		<tiles:insertDefinition name="topLevelCriteriaTemplate">
			<tiles:putAttribute name="topLevelCriteriaList" value="${exclusionEO}"/>
		</tiles:insertDefinition>

        <div class="panel panel-espd">
            <div class="panel-heading" data-toggle="collapse" data-target="#ca-insolvency-section">
                <h4 class="panel-title">${span18n['crit_top_title_purely_national']}</h4>
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
	              	    	<tiles:putAttribute name="hasCriterion" value="false"/>
                        </tiles:insertDefinition>
                    </c:if>
                </div>
            </div>
        </div>
    </div>

	<%-- SELECTION --%>
    <div class="panel-default">
        
        
        <div><h2>${span18n['createcasel_header']}</h2></div>
        <div class="alert alert-espd-info">
            <ul class="fa-ul">
                <li>
                    <i class="info-label fa fa-info-circle fa-lg fa-li"></i>${span18n['createcasel_alert']}
                </li>
            </ul>
        </div>
        <div class="panel panel-espd">
            <div class="panel-heading" data-toggle="collapse" data-target="#eo-satisfies-all-section">
            	<h4 class="panel-title">${span18n['all_selection_switch']}</h4>
            </div>
            <div id="eo-satisfies-all-section" class="collapse in">
                <div class="espd-panel-body panel-body">
					<strong>${span18n['crit_selection_eo_declares_that']}</strong>
                </div>
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
		<div>
			<h2>
				<span data-i18n="createcafinish_header"><s:message code="createcafinish_header"/></span>
			</h2>
		</div>
		<div class="panel panel-espd">
			<div class="panel-heading" data-toggle="collapse" data-target="#finish-reduction-of-numbers-section">
				 <h4 class="panel-title">${span18n['createcafinish_reduction']}</h4>
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
	                    <tiles:putAttribute name="hasCriterion" value="false"/>
					</tiles:insertDefinition>
                </div>
            </div>
		</div>
		<div class="panel panel-espd">
			<div class="panel-heading" data-toggle="collapse" data-target="#finish-statements-signature-section">
				 <h4 class="panel-title">${span18n['createcafinish_concl_statements']}</h4>
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

<script>
	$(document).ready(function() {
		$('body').addClass('loaded');
	});
</script>