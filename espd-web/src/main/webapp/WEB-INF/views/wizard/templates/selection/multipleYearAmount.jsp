<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:importAttribute name="field"/>

<s:message code="crit_year_placeholder" var="yearPlaceholder"/>
<s:message code="crit_amount_concerned_placeholder" var="amountPlaceholder"/>

<div class="form-group">
    <label class="control-label col-md-4 small" for="${field}-year1"
           data-i18n="crit_year"><s:message code='crit_year'/></label>
    <div class="col-md-8">
        <tiles:insertDefinition name="years">
            <tiles:putAttribute name="yearField" value="${field}.year1"/>
            <tiles:putAttribute name="yearHtmlId" value="${field}-year1"/>
            <tiles:putAttribute name="yearCssClass" value="form-control"/>
        </tiles:insertDefinition>
    </div>
</div>
<div class="form-group">
    <label class="control-label col-md-4 small" for="${field}-amount1"
           data-i18n="crit_amount_concerned"><s:message code='crit_amount_concerned'/></label>
    <div class="col-md-5">
        <form:input path="${field}.amount1" cssClass="form-control" id="${field}-amount1"
                    placeholder="${amountPlaceholder}"/>
    </div>
    <div class="col-md-3">
        <tiles:insertDefinition name="currencies">
            <tiles:putAttribute name="currencyField" value="${field}.currency1"/>
            <tiles:putAttribute name="currencyHtmlId" value="${field}-currency1"/>
            <tiles:putAttribute name="currencyCssClass" value="form-control"/>
        </tiles:insertDefinition>
    </div>
</div>

<div class="form-group">
    <label class="control-label col-md-4 small" for="${field}-year2"
           data-i18n="crit_year"><s:message code='crit_year'/></label>
    <div class="col-md-8">
        <tiles:insertDefinition name="years">
            <tiles:putAttribute name="yearField" value="${field}.year2"/>
            <tiles:putAttribute name="yearHtmlId" value="${field}-year2"/>
            <tiles:putAttribute name="yearCssClass" value="form-control"/>
        </tiles:insertDefinition>
    </div>
</div>
<div class="form-group">
    <label class="control-label col-md-4 small" for="${field}-amount2"
           data-i18n="crit_amount_concerned"><s:message code='crit_amount_concerned'/></label>
    <div class="col-md-5">
        <form:input path="${field}.amount2" cssClass="form-control" id="${field}-amount2"
                    placeholder="${amountPlaceholder}"/>
    </div>
    <div class="col-md-3">
        <tiles:insertDefinition name="currencies">
            <tiles:putAttribute name="currencyField" value="${field}.currency2"/>
            <tiles:putAttribute name="currencyHtmlId" value="${field}-currency2"/>
            <tiles:putAttribute name="currencyCssClass" value="form-control"/>
        </tiles:insertDefinition>
    </div>
</div>

<div class="form-group">
    <label class="control-label col-md-4 small" for="${field}-year3"
           data-i18n="crit_year"><s:message code='crit_year'/></label>
    <div class="col-md-8">
        <tiles:insertDefinition name="years">
            <tiles:putAttribute name="yearField" value="${field}.year3"/>
            <tiles:putAttribute name="yearHtmlId" value="${field}-year3"/>
            <tiles:putAttribute name="yearCssClass" value="form-control"/>
        </tiles:insertDefinition>
    </div>
</div>
<div class="form-group">
    <label class="control-label col-md-4 small" for="${field}-amount3"
           data-i18n="crit_amount_concerned"><s:message code='crit_amount_concerned'/></label>
    <div class="col-md-5">
        <form:input path="${field}.amount3" cssClass="form-control" id="${field}-amount3"
                    placeholder="${amountPlaceholder}"/>
    </div>
    <div class="col-md-3">
        <tiles:insertDefinition name="currencies">
            <tiles:putAttribute name="currencyField" value="${field}.currency3"/>
            <tiles:putAttribute name="currencyHtmlId" value="${field}-currency3"/>
            <tiles:putAttribute name="currencyCssClass" value="form-control"/>
        </tiles:insertDefinition>
    </div>
</div>

<div class="form-group">
    <label class="control-label col-md-4 small" for="${field}-year4"
           data-i18n="crit_year"><s:message code='crit_year'/></label>
    <div class="col-md-8">
        <tiles:insertDefinition name="years">
            <tiles:putAttribute name="yearField" value="${field}.year4"/>
            <tiles:putAttribute name="yearHtmlId" value="${field}-year4"/>
            <tiles:putAttribute name="yearCssClass" value="form-control"/>
        </tiles:insertDefinition>
    </div>
</div>
<div class="form-group">
    <label class="control-label col-md-4 small" for="${field}-amount4"
           data-i18n="crit_amount_concerned"><s:message code='crit_amount_concerned'/></label>
    <div class="col-md-5">
        <form:input path="${field}.amount4" cssClass="form-control" id="${field}-amount4"
                    placeholder="${amountPlaceholder}"/>
    </div>
    <div class="col-md-3">
        <tiles:insertDefinition name="currencies">
            <tiles:putAttribute name="currencyField" value="${field}.currency4"/>
            <tiles:putAttribute name="currencyHtmlId" value="${field}-currency4"/>
            <tiles:putAttribute name="currencyCssClass" value="form-control"/>
        </tiles:insertDefinition>
    </div>
</div>

<div class="form-group">
    <label class="control-label col-md-4 small" for="${field}-year5"
           data-i18n="crit_year"><s:message code='crit_year'/></label>
    <div class="col-md-8">
        <tiles:insertDefinition name="years">
            <tiles:putAttribute name="yearField" value="${field}.year5"/>
            <tiles:putAttribute name="yearHtmlId" value="${field}-year5"/>
            <tiles:putAttribute name="yearCssClass" value="form-control"/>
        </tiles:insertDefinition>
    </div>
</div>
<div class="form-group">
    <label class="control-label col-md-4 small" for="${field}-amount5"
           data-i18n="crit_amount_concerned"><s:message code='crit_amount_concerned'/></label>
    <div class="col-md-5">
        <form:input path="${field}.amount5" cssClass="form-control" id="${field}-amount5"
                    placeholder="${amountPlaceholder}"/>
    </div>
    <div class="col-md-3">
        <tiles:insertDefinition name="currencies">
            <tiles:putAttribute name="currencyField" value="${field}.currency5"/>
            <tiles:putAttribute name="currencyHtmlId" value="${field}-currency5"/>
            <tiles:putAttribute name="currencyCssClass" value="form-control"/>
        </tiles:insertDefinition>
    </div>
</div>