<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:importAttribute name="field"/>

<s:message code="crit_year_placeholder" var="yearPlaceholder"/>
<s:message code="crit_number_placeholder" var="numberPlaceholder"/>

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
    <label class="control-label col-md-4 small" for="${field}-number1"
           data-i18n="crit_number"><s:message code='crit_number'/></label>
    <div class="col-md-5">
        <form:input path="${field}.number1" cssClass="form-control" id="${field}-number1"
                    placeholder="${numberPlaceholder}"/>
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
    <label class="control-label col-md-4 small" for="${field}-number2"
           data-i18n="crit_number"><s:message code='crit_number'/></label>
    <div class="col-md-5">
        <form:input path="${field}.number2" cssClass="form-control" id="${field}-number2"
                    placeholder="${numberPlaceholder}"/>
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
    <label class="control-label col-md-4 small" for="${field}-number3"
           data-i18n="crit_number"><s:message code='crit_number'/></label>
    <div class="col-md-5">
        <form:input path="${field}.number3" cssClass="form-control" id="${field}-number3"
                    placeholder="${numberPlaceholder}"/>
    </div>
</div>

