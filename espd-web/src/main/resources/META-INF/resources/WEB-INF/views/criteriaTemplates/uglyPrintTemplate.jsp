<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<tiles:importAttribute name="field"/>

<c:forEach begin="1" end="5" varStatus="loop">
	<div class="form-group">
		<div class="col-xs-3">
		    <label class="control-label col-md-3 small">${span18n['crit_description']}</label>
	    </div>
		<div class="col-xs-9">
			<form:textarea path="${field}.description${loop.index}" cssClass="form-control small" cssStyle="border-radius: 0;" placeholder="${i18n['crit_description_placeholder']}"/>
		</div>
	</div>
	<div class="form-group">
		<div class="col-xs-3">
		    <label class="control-label col-md-3 small">${span18n['crit_amount']}</label>
	    </div>
		<div class="col-xs-6">
			<form:input path="${field}.amount${loop.index}" number="true" cssClass="form-control small" cssStyle="border-radius: 0;padding-left: 2px; padding-right: 0;" placeholder="${i18n['crit_amount_concerned_placeholder']}"/>
		</div>
		<div class="col-xs-3">
			<tiles:insertDefinition name="currencies">
	            <tiles:putAttribute name="currencyField" value="${field}.currency${loop.index}"/>
	            <tiles:putAttribute name="style" value="border-radius: 0;"/>
	        </tiles:insertDefinition>
		</div>
	</div>
	<div class="form-group">
		<div class="col-xs-3">
		    <label class="control-label col-md-3 small">${span18n['crit_date']}</label>
	    </div>
		<div class="col-xs-9">
			<form:input path="${field}.date${loop.index}" cssClass="form-control datepicker" cssStyle="border-radius: 0;" placeholder="${i18n['crit_date_placeholder']}"/>
		</div>
	</div>
	<div class="form-group">
		<div class="col-xs-3">
		    <label class="control-label col-md-3 small">${span18n['crit_recipients']}</label>
	    </div>
		<div class="col-xs-9">
			<form:textarea path="${field}.recipients${loop.index}" cssClass="form-control small" cssStyle="border-radius: 0;" placeholder="${i18n['crit_recipients_placeholder']}"/>
		</div>
	</div>
</c:forEach>




