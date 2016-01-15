<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:importAttribute name="field"/>

<s:message code="crit_description_placeholder" var="descriptionPlaceholder"/>
<s:message code="crit_amount_concerned_placeholder" var="amountPlaceholder"/>
<s:message code="crit_recipients_placeholder" var="recipientsPlaceholder"/>
<s:message code="crit_date_placeholder" var="datePlaceholder"/>

<!-- 1 -->
<div class="form-group">
    <label class="control-label col-md-4 small" for="${field}-description1"
           data-i18n="crit_description"><s:message code='crit_description'/></label>
    <div class="col-md-8">
        <form:input path="${field}.description1" cssClass="form-control" id="${field}-description1"
                    placeholder="${descriptionPlaceholder}"/>
    </div>
</div>
<div class="form-group">
    <label class="control-label col-md-4 small" for="${field}-amount1"
           data-i18n="crit_amount"><s:message code='crit_amount'/></label>
    <div class="col-md-5">
        <form:input path="${field}.amount1" cssClass="form-control" id="${field}-amount1"
                    placeholder="${amountPlaceholder}"/>
    </div>
</div>
<div class="form-group">
    <label class="control-label col-md-4 small" for="${field}-date1" data-i18n="crit_date"><s:message
            code='crit_date'/></label>
    <div class="col-md-8">
        <form:input path="${field}.date1" cssClass="form-control datepicker" id="${field}-date1"
                    placeholder="${datePlaceholder}"/>
    </div>
</div>
<div class="form-group">
    <label class="control-label col-md-4 small" for="${field}-recipients1"
           data-i18n="crit_recipients"><s:message code='crit_recipients'/></label>
    <div class="col-md-5">
        <form:input path="${field}.recipients1" cssClass="form-control" id="${field}-recipients1"
                    placeholder="${recipientsPlaceholder}"/>
    </div>
</div>
<!-- 2 -->
<div class="form-group">
    <label class="control-label col-md-4 small" for="${field}-description2"
           data-i18n="crit_description"><s:message code='crit_description'/></label>
    <div class="col-md-8">
        <form:input path="${field}.description2" cssClass="form-control" id="${field}-description2"
                    placeholder="${descriptionPlaceholder}"/>
    </div>
</div>
<div class="form-group">
    <label class="control-label col-md-4 small" for="${field}-amount2"
           data-i18n="crit_amount_concerned"><s:message code='crit_amount_concerned'/></label>
    <div class="col-md-5">
        <form:input path="${field}.amount2" cssClass="form-control" id="${field}-amount2"
                    placeholder="${amountPlaceholder}"/>
    </div>
</div>
<div class="form-group">
    <label class="control-label col-md-4 small" for="${field}-date2" data-i18n="crit_date"><s:message
            code='crit_date'/></label>
    <div class="col-md-8">
        <form:input path="${field}.date2" cssClass="form-control datepicker" id="${field}-date2"
                    placeholder="${datePlaceholder}"/>
    </div>
</div>
<div class="form-group">
    <label class="control-label col-md-4 small" for="${field}-recipients2"
           data-i18n="crit_recipients"><s:message code='crit_recipients'/></label>
    <div class="col-md-5">
        <form:input path="${field}.recipients2" cssClass="form-control" id="${field}-recipients2"
                    placeholder="${recipientsPlaceholder}"/>
    </div>
</div>
<!-- 3 -->
<div class="form-group">
    <label class="control-label col-md-4 small" for="${field}-description3"
           data-i18n="crit_description"><s:message code='crit_description'/></label>
    <div class="col-md-8">
        <form:input path="${field}.description3" cssClass="form-control" id="${field}-description3"
                    placeholder="${descriptionPlaceholder}"/>
    </div>
</div>
<div class="form-group">
    <label class="control-label col-md-4 small" for="${field}-amount3"
           data-i18n="crit_amount_concerned"><s:message code='crit_amount_concerned'/></label>
    <div class="col-md-5">
        <form:input path="${field}.amount3" cssClass="form-control" id="${field}-amount3"
                    placeholder="${amountPlaceholder}"/>
    </div>
</div>
<div class="form-group">
    <label class="control-label col-md-4 small" for="${field}-date3" data-i18n="crit_date"><s:message
            code='crit_date'/></label>
    <div class="col-md-8">
        <form:input path="${field}.date3" cssClass="form-control datepicker" id="${field}-date3"
                    placeholder="${datePlaceholder}"/>
    </div>
</div>
<div class="form-group">
    <label class="control-label col-md-4 small" for="${field}-recipients3"
           data-i18n="crit_recipients"><s:message code='crit_recipients'/></label>
    <div class="col-md-5">
        <form:input path="${field}.recipients3" cssClass="form-control" id="${field}-recipients3"
                    placeholder="${recipientsPlaceholder}"/>
    </div>
</div>
<!-- 4 -->
<div class="form-group">
    <label class="control-label col-md-4 small" for="${field}-description4"
           data-i18n="crit_description"><s:message code='crit_description'/></label>
    <div class="col-md-8">
        <form:input path="${field}.description4" cssClass="form-control" id="${field}-description4"
                    placeholder="${descriptionPlaceholder}"/>
    </div>
</div>
<div class="form-group">
    <label class="control-label col-md-4 small" for="${field}-amount4"
           data-i18n="crit_amount_concerned"><s:message code='crit_amount_concerned'/></label>
    <div class="col-md-5">
        <form:input path="${field}.amount4" cssClass="form-control" id="${field}-amount4"
                    placeholder="${amountPlaceholder}"/>
    </div>
</div>
<div class="form-group">
    <label class="control-label col-md-4 small" for="${field}-date4" data-i18n="crit_date"><s:message
            code='crit_date'/></label>
    <div class="col-md-8">
        <form:input path="${field}.date4" cssClass="form-control datepicker" id="${field}-date4"
                    placeholder="${datePlaceholder}"/>
    </div>
</div>
<div class="form-group">
    <label class="control-label col-md-4 small" for="${field}-recipients4"
           data-i18n="crit_recipients"><s:message code='crit_recipients'/></label>
    <div class="col-md-5">
        <form:input path="${field}.recipients4" cssClass="form-control" id="${field}-recipients4"
                    placeholder="${recipientsPlaceholder}"/>
    </div>
</div>
<!-- 5 -->
<div class="form-group">
    <label class="control-label col-md-4 small" for="${field}-description5"
           data-i18n="crit_description"><s:message code='crit_description'/></label>
    <div class="col-md-8">
        <form:input path="${field}.description5" cssClass="form-control" id="${field}-description5"
                    placeholder="${descriptionPlaceholder}"/>
    </div>
</div>
<div class="form-group">
    <label class="control-label col-md-4 small" for="${field}-amount5"
           data-i18n="crit_amount_concerned"><s:message code='crit_amount_concerned'/></label>
    <div class="col-md-5">
        <form:input path="${field}.amount5" cssClass="form-control" id="${field}-amount5"
                    placeholder="${amountPlaceholder}"/>
    </div>
</div>
<div class="form-group">
    <label class="control-label col-md-4 small" for="${field}-date5" data-i18n="crit_date"><s:message
            code='crit_date'/></label>
    <div class="col-md-8">
        <form:input path="${field}.date5" cssClass="form-control datepicker" id="${field}-date5"
                    placeholder="${datePlaceholder}"/>
    </div>
</div>
<div class="form-group">
    <label class="control-label col-md-4 small" for="${field}-recipients5"
           data-i18n="crit_recipients"><s:message code='crit_recipients'/></label>
    <div class="col-md-5">
        <form:input path="${field}.recipients5" cssClass="form-control" id="${field}-recipients5"
                    placeholder="${recipientsPlaceholder}"/>
    </div>
</div>