<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<%
	request.setAttribute("suitabilityListCA", eu.europa.ec.grow.espd.xml.CriteriaTemplates.suitabilityListCA);
request.setAttribute("economicListCA", eu.europa.ec.grow.espd.xml.CriteriaTemplates.economicListCA);
request.setAttribute("technicalListCA", eu.europa.ec.grow.espd.xml.CriteriaTemplates.technicalListCA);
%>

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
        
			<tiles:insertDefinition name="panelTemplate">
				<tiles:putAttribute name="id" value="ca-suitability-section"/>
				<tiles:putAttribute name="title_code" value="createcasel_suitability"/>
				<tiles:putAttribute name="subtitle_code" value="crit_selection_suitability_article"/>
				<tiles:putAttribute name="tooltip_code" value="crit_selection_suitability_article_tooltip"/>
				<tiles:putAttribute name="criteriaList" value="${suitabilityListCA}"/>
			</tiles:insertDefinition>
			
			<tiles:insertDefinition name="panelTemplate">
				<tiles:putAttribute name="id" value="ca-economic-financial-section"/>
				<tiles:putAttribute name="title_code" value="createcasel_economic_and_financial_standing"/>
				<tiles:putAttribute name="subtitle_code" value="crit_selection_ca_economic_article"/>
				<tiles:putAttribute name="tooltip_code" value="crit_selection_economic_financial_standing_article_tooltip"/>
				<tiles:putAttribute name="criteriaList" value="${economicListCA}"/>
			</tiles:insertDefinition>
			
			<tiles:insertDefinition name="panelTemplate">
				<tiles:putAttribute name="id" value="ca-technical_professional_ability-section"/>
				<tiles:putAttribute name="title_code" value="createcasel_technical_professional_ability"/>
				<tiles:putAttribute name="subtitle_code" value="crit_selection_technical_professional_ability_article"/>
				<tiles:putAttribute name="tooltip_code" value="crit_selection_technical_professional_ability_article_tooltip"/>
				<tiles:putAttribute name="criteriaList" value="${technicalListCA}"/>
			</tiles:insertDefinition>

        </div>
        <tiles:insertDefinition name="footerButtons">
            <tiles:putAttribute name="prev" value="exclusion"/>
            <tiles:putAttribute name="next" value="finish"/>
        </tiles:insertDefinition>
    </div>
</form:form>