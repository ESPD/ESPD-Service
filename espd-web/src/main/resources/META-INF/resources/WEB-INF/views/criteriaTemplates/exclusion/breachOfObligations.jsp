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

<div class="form-group">
	${span18n['crit_other_than_judicial_or_administrative_decision']}
	<form:radiobutton path="${field}.breachEstablishedOtherThanJudicialDecision" value="true" data-target-show="${'#'}${field}-breach-of-obligations-form-b" data-target-hide="${'#'}${field}-breach-of-obligations-form-a"/>${span18n["yes"]}
	<form:radiobutton path="${field}.breachEstablishedOtherThanJudicialDecision" value="false" data-target-show="${'#'}${field}-breach-of-obligations-form-a" data-target-hide="${'#'}${field}-breach-of-obligations-form-b"/>${span18n["no"]}
</div>

<div id="${field}-breach-of-obligations-form-a" class="${(espd[field].breachEstablishedOtherThanJudicialDecision == true || espd[field].breachEstablishedOtherThanJudicialDecision == null) ? 'collapse' : ''}">
	<div class="form-group">
        ${span18n['crit_decision_final_and_binding']}
		<form:radiobutton path="${field}.decisionFinalAndBinding" value="true"/>${span18n["yes"]}
		<form:radiobutton path="${field}.decisionFinalAndBinding" value="false"/>${span18n["no"]}
    </div>

	<div class="form-group">
		<label class="control-label col-md-4 small">${span18n['crit_date_of_conviction_or_decision']}</label>
		<div class="col-md-8">
			<form:input type="text" path="${field}.dateOfConviction" cssClass="form-control datepicker"/>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-md-4 small">${span18n['crit_taxes_length_period_of_exclusion']}</label>
		<div class="col-md-8">
			<form:textarea rows="1" type="text" path="${field}.periodLength" cssClass="form-control"/>
		</div>
	</div>
</div>

<div id="${field}-breach-of-obligations-form-b" class="${espd[field].breachEstablishedOtherThanJudicialDecision ? '' : 'collapse'}">
	<div class="form-group">
		<label class="control-label col-md-4 small">${span18n['crit_taxes_means_description']}</label>
		<div class="col-md-8">
			<form:textarea path="${field}.meansDescription" cssClass="form-control"/>
		</div>
	</div>
    <div class="form-group"> 
		${span18n['crit_taxes_eo_fulfilled_obligations']}
		<form:radiobutton path="${field}.eoFulfilledObligations" value="true" data-target-show="${'#'}${field}-breach-of-obligations-form-c"/>${span18n["yes"]}
		<form:radiobutton path="${field}.eoFulfilledObligations" value="false" data-target-hide="${'#'}${field}-breach-of-obligations-form-c"/>${span18n["no"]}
    </div>
    <div id="${field}-breach-of-obligations-form-c" class="${espd[field].eoFulfilledObligations ? '' : 'collapse'}">
        <div class="form-group">
            <label class="control-label col-md-4 small">${span18n['crit_please_describe_them']}</label>
            <div class="col-md-8">
                <form:textarea path="${field}.obligationsDescription" cssClass="form-control"/>
            </div>
        </div>
    </div>
    
</div>