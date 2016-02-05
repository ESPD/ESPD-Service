<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<%
request.setAttribute("criminalListEO", eu.europa.ec.grow.espd.business.CriteriaTemplates.criminalListEO);
request.setAttribute("taxesListEO", eu.europa.ec.grow.espd.business.CriteriaTemplates.taxesListEO);
request.setAttribute("insolvencyListEO", eu.europa.ec.grow.espd.business.CriteriaTemplates.insolvencyListEO);
%>

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
        <tiles:putAttribute name="page" value="${flow}/ca/exclusion"/>
    </tiles:insertDefinition>

    <div class="panel-default">

        <tiles:insertDefinition name="progress">
            <tiles:putAttribute name="exclusion" value="true"/>
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

        <div class="paragraph">
            <h2>${span18n["createcaexcl_header"]}</h2>
        </div>
        
		<tiles:insertDefinition name="panelTemplate">
			<tiles:putAttribute name="id" value="criminal_conv"/>
			<tiles:putAttribute name="title_code" value="crit_top_title_grounds_criminal_conv"/>
			<tiles:putAttribute name="subtitle_code" value="crit_eu_main_title_grounds_criminal_conv_eo"/>
			<tiles:putAttribute name="tooltip_code" value=""/>
			<tiles:putAttribute name="criteriaList" value="${criminalListEO}"/>
		</tiles:insertDefinition>
        
		<tiles:insertDefinition name="panelTemplate">
			<tiles:putAttribute name="id" value="payment_taxes"/>
			<tiles:putAttribute name="title_code" value="crit_top_title_grounds_payment_taxes"/>
			<tiles:putAttribute name="subtitle_code" value="crit_eu_main_title_payment_taxes_eo"/>
			<tiles:putAttribute name="tooltip_code" value=""/>
			<tiles:putAttribute name="criteriaList" value="${taxesListEO}"/>
		</tiles:insertDefinition>
        
		<tiles:insertDefinition name="panelTemplate">
			<tiles:putAttribute name="id" value="insolvency_conflicts"/>
			<tiles:putAttribute name="title_code" value="crit_top_title_insolvency_conflicts"/>
			<tiles:putAttribute name="subtitle_code" value="crit_eu_main_breaching_obligations_eo"/>
			<tiles:putAttribute name="tooltip_code" value=""/>
			<tiles:putAttribute name="criteriaList" value="${insolvencyListEO}"/>
		</tiles:insertDefinition>

        <div class="panel panel-default espd-panel">
            <div data-i18n="crit_top_title_purely_national" class="espd-panel-heading" data-toggle="collapse"
                 data-target="#ca-insolvency-section">
                <s:message code='crit_top_title_purely_national'/>
            </div>
            <div id="ca-insolvency-section" class="collapse in">
                <div class="espd-panel-body panel-body">
                    <span data-i18n="crit_eu_main_breaching_obligations" class="aligned" style="font-weight: bold;">
                        <s:message code='crit_eu_main_purely_national'/>
                    </span>
					<tiles:insertDefinition name="exclusionFormTemplate">
						<tiles:putAttribute name="field" value="purelyNationalGrounds"/>
						<tiles:putAttribute name="checkExistanse" value="true"/>
						<tiles:putAttribute name="title_code" value="crit_eu_title_purely_national"/>
						<tiles:putAttribute name="description_code" value="crit_eu_text_purely_national"/>
						<tiles:putAttribute name="selfCleaning" value="false"/>
					</tiles:insertDefinition>
                </div>
            </div>
        </div>

        <tiles:insertDefinition name="footerButtons">
            <tiles:putAttribute name="prev" value="procedure"/>
            <tiles:putAttribute name="next" value="selection"/>
        </tiles:insertDefinition>
    </div>

</form:form>
	
