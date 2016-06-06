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

<tiles:importAttribute name="flow"/>

<form:form id="espdform" role="form" class="form-horizontal" method="post" commandName="espd" data-toggle="validator">
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
                        <span data-i18n="crit_selection_declares_that_tooltip" data-toggle="tooltip" title="${i18n['crit_selection_declares_that_tooltip']}"></span>
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