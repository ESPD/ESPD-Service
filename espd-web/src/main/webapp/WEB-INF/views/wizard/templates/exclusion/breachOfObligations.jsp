<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:importAttribute name="field"/>

<div class="form-group">
    <label class="control-label small">${span18n['crit_other_than_judicial_or_administrative_decision']}</label>
    <form:checkbox path="${field}.breachEstablishedOtherThanJudicialDecision"
                   data-target="${'#'}${field}-breach-of-obligations-form-a" cssClass="radioslide checktoggle"/>
</div>
<div id="${field}-breach-of-obligations-form-a" style="display:none">
    <div class="form-group">
        <label class="control-label col-md-4 small">${span18n['crit_taxes_means_description']}</label>

        <div class="col-md-8">
            <form:textarea path="${field}.meansDescription" cssClass="form-control" id="${field}-means-description"
                           placeholder="${i18n['crit_taxes_means_description']}"/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label small">${span18n['crit_decision_final_and_binding']}</label>
        <form:checkbox path="${field}.decisionFinalAndBinding" data-target="${'#'}${field}-breach-of-obligations-form-b"
                       cssClass="radioslide checktoggle"/>
    </div>
    <div id="${field}-breach-of-obligations-form-b" style="display:none">
        <div class="form-group">
            <label class="control-label col-md-4 small">${span18n['crit_date_of_conviction_or_decision']}</label>

            <div class="col-md-8">
                <form:input path="${field}.dateOfConviction" cssClass="form-control datepicker"
                            placeholder="${i18n['crit_date_of_conviction_or_decision']}"/>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-md-4 small">${span18n['crit_taxes_length_period_of_exclusion']}</label>

            <div class="col-md-8">
                <form:input type="text" path="${field}.periodLength" cssClass="form-control"
                            placeholder="${i18n['crit_length_period_exclusion_placeholder']}"/>
            </div>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label small" for="${field}-eo-fulfilled-obligations"
               data-i18n="crit_taxes_eo_fulfilled_obligations"><s:message
                code='crit_taxes_eo_fulfilled_obligations'/></label>
        <form:checkbox path="${field}.eoFulfilledObligations" data-target="${'#'}${field}-breach-of-obligations-form-c"
                       cssClass="radioslide checktoggle"/>
    </div>
    <div id="${field}-breach-of-obligations-form-c" style="display:none">
        <div class="form-group">
            <label class="control-label col-md-4 small">${span18n['crit_please_describe_them']}</label>

            <div class="col-md-8">
                <form:textarea path="${field}.obligationsDescription" cssClass="form-control"
                               placeholder="${i18n['crit_please_describe_them_placeholder']}"></form:textarea>
            </div>
        </div>
    </div>
</div>

