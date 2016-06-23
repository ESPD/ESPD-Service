<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
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
<tiles:importAttribute name="agent"/>
<form:form id="espdform" role="form" class="form-horizontal" method="post" commandName="espd" data-toggle="validator">
	<div class="panel-default">
        <tiles:insertDefinition name="progress">
        	<tiles:putAttribute name="agent" value="${agent}"/>
       		<tiles:putAttribute name="flow" value="${flow}"/>
			<tiles:putAttribute name="finish" value="true"/>
        </tiles:insertDefinition>
		<div class="paragraph">
			<h2>
				<span data-i18n="createcafinish_header"><s:message code="createcafinish_header"/></span>
			</h2>
		</div>
		<c:if test="${agent == 'eo'}"><%-- Part V is visible only for EO --%>
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
		</c:if>
		<div class="panel panel-default espd-panel">
			<div data-i18n="createcafinish_concl_statements" class="espd-panel-heading" data-toggle="collapse" data-target="#finish-statements-signature-section">
				 <s:message code='createcafinish_concl_statements'/>
			</div>
            <div id="finish-statements-signature-section" class="collapse in">
                <div class="espd-panel-body panel-body">
                    <span data-i18n="createcafinish_concl_statements_text" style="">
                        <s:message code='createcafinish_concl_statements_text'/>
                    </span>
                    <p>
	                    <span data-i18n="createcafinish_concl_statements_signature">
	                        <s:message code='createcafinish_concl_statements_signature'/>
	                    </span>
                    </p>
                </div>
                <c:if test="${agent == 'eo'}">
                    <div class="form-group">
                        <label class="control-label col-md-2 small">${span18n['crit_date']}</label>
                        <div class="col-md-4">
                            <form:input type="text" path="documentDate" cssClass="form-control datepicker" cssStyle="border-radius: 0;"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2 small">${span18n['place']}</label>
                        <div class="col-md-4">
                            <form:textarea rows="1" type="text" path="location" cssClass="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2 small">${span18n['signature']}</label>
                    </div>
                </c:if>
                <br/><br/><br/>
            </div>
		</div>
		<div class="panel panel-default espd-panel">
			<div data-i18n="createcafinish_export" class="espd-panel-heading" data-toggle="collapse" data-target="#finish-statements-section">
				 <s:message code='createcafinish_export'/>
			</div>
            <div id="finish-statements-section" class="collapse in">
                <div class="espd-panel-body panel-body">
                    <span data-i18n="createcafinish_export_content">
                        <s:message code='createcafinish_export_content'/>
                    </span>
                </div>
            </div>
		</div>
        <tiles:insertDefinition name="footerButtons">
            <tiles:putAttribute name="nextCode" value="export"/>
            <tiles:putAttribute name="prev" value="selection"/>
            <tiles:putAttribute name="next" value="generate"/>
            <tiles:putAttribute name="print" value="${true}"/>
        </tiles:insertDefinition>
	</div>
</form:form>