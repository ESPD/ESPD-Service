<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:importAttribute name="field"/>

<div class="form-group">
	<label class="control-label small" for="${field}-other-than-judicial-question" data-i18n="crit_other_than_judicial_or_administrative_decision"><s:message code='crit_other_than_judicial_or_administrative_decision'/></label>
	<input path="${field}.breachEstablishedOtherThanJudicialDecision" type="checkbox" id="${field}-other-than-judicial-question" data-toggle="collapse" data-target="${'#'}${field}-breach-of-obligations-form" class="radioslide checktoggle" />
</div>
<div class="tab-pane" id="${field}-breach-of-obligations-form" style="display:none">
    <div class="form-group">
        <label class="control-label col-md-4 small" for="${field}-field6" data-i18n="crit_taxes_means_description"><s:message code='crit_taxes_means_description'/></label>
        <div class="col-md-8">
            <s:message code="crit_taxes_means_description" var="meansDescriptionPlaceholder"/>
            <textarea path="${field}.meansDescription" class="form-control" id="${field}-means-description" placeholder="${meansDescriptionPlaceholder}"></textarea>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label small" for="${field}-decision-final-and-binding" data-i18n="crit_decision_final_and_binding"><s:message code='crit_decision_final_and_binding'/></label>
        <input path="${field}.decisionFinalAndBinding" type="checkbox" id="${field}-decision-final-and-binding" data-toggle="collapse" data-target="${'#'}${field}-breach-of-obligations-form" class="radioslide checktoggle" />
    </div>
	<div class="form-group">
		<label class="control-label col-md-4 small" for="${field}-breach-of-obligations-date" data-i18n="crit_date_of_conviction_or_decision"><s:message code='crit_date_of_conviction_or_decision'/></label>
		<div class="col-md-8"> 
			<input type="text" path="${field}.dateOfConviction" class="form-control datepicker" id="${field}-breach-of-obligations-date-value" placeholder="Enter date"/>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-md-4 small" for="${field}-breach-of-obligations-repiod-length" data-i18n="crit_taxes_length_period_of_exclusion"><s:message code='crit_taxes_length_period_of_exclusion'/></label>
		<div class="col-md-8"> 
			<input type="text" path="${field}.periodLength" class="form-control" id="${field}-breach-of-obligations-repiod-length"/>
		</div>
	</div>
    <div class="form-group">
        <label class="control-label small" for="${field}-eo-fulfilled-obligations" data-i18n="crit_taxes_eo_fulfilled_obligations"><s:message code='crit_taxes_eo_fulfilled_obligations'/></label>
        <input path="${field}.eoFulfilledObligations" type="checkbox" id="${field}-eo-fulfilled-obligations" data-toggle="collapse" data-target="${'#'}${field}-breach-of-obligations-form" class="radioslide checktoggle" />
    </div>
    <div class="form-group">
        <label class="control-label col-md-4 small" for="${field}-please-describe" data-i18n="crit_please_describe_them"><s:message code='crit_please_describe_them'/></label>
        <div class="col-md-8">
            <s:message code="crit_please_describe_them_placeholder" var="describePlaceholder"/>
            <textarea path="${field}.obligationsDescription" class="form-control" id="${field}--please-describe" placeholder="${describePlaceholder}"></textarea>
        </div>
    </div>
</div>
