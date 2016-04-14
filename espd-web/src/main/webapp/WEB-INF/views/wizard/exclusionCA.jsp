<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<%request.setAttribute("exclusionCA", eu.europa.ec.grow.espd.xml.CriteriaTemplates.exclusionCA);%>

<tiles:importAttribute name="flow"/>

<form:form id="espdform" role="form" class="form-horizontal" method="post" commandName="espd" data-toggle="validator">

    <tiles:insertDefinition name="viewChangeRole">
        <tiles:putAttribute name="agent" value="ca"/>
        <tiles:putAttribute name="page" value="${flow}/eo/exclusion"/>
        <tiles:putAttribute name="showLink" value="${flow == 'request'}"/>
    </tiles:insertDefinition>
    
    <div class="panel-default">
        <tiles:insertDefinition name="progress">
            <tiles:putAttribute name="exclusion" value="true"/>
        </tiles:insertDefinition>
        <div class="paragraph">
            <h2>
                <span data-i18n="createcaexcl_header"><s:message code="createcaexcl_header"/></span>
            </h2>
        </div>

		<tiles:insertDefinition name="topLevelCriteriaTemplate">
			<tiles:putAttribute name="topLevelCriteriaList" value="${exclusionCA}"/>
		</tiles:insertDefinition>

        <div class="panel panel-default espd-panel">
            <div data-i18n="crit_top_title_purely_national" class="espd-panel-heading" data-toggle="collapse"
                 data-target="#ca-national-section">
                <s:message code='crit_top_title_purely_national'/>
            </div>
            <div id="ca-national-section" class="espd-panel-body panel-body collapse in">
                    <span data-i18n="crit_eu_main_purely_national" style="font-weight: bold;">
                        <s:message code='crit_eu_main_purely_national'/>
                    </span>
	                <tiles:insertDefinition name="checkTemplate">
	                    <tiles:putAttribute name="field" value="purelyNationalGrounds"/>
	                    <tiles:putAttribute name="title_code" value="crit_eu_title_purely_national"/>
	                    <tiles:putAttribute name="description_code" value="crit_eu_text_purely_national"/>
	                </tiles:insertDefinition>
            </div>
        </div>
        <tiles:insertDefinition name="footerButtons">
            <tiles:putAttribute name="prev" value="procedure"/>
            <tiles:putAttribute name="next" value="selection"/>
        </tiles:insertDefinition>
    </div>
</form:form>
	
