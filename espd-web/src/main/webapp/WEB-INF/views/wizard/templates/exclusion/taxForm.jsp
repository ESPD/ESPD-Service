<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:importAttribute name="field"/>

<div class="form-group">
	<label class="control-label col-md-4 small" for="${field}-field-country" data-i18n="crit_country_member_state"><s:message code='crit_country_member_state'/></label>
	<div class="col-md-8">
        <tiles:insertDefinition name="countries">
            <tiles:putAttribute name="countryField" value="${field}.country"/>
            <tiles:putAttribute name="countryHtmlId" value="${field}.country"/>
            <tiles:putAttribute name="countryCssClass" value="form-control"/>
        </tiles:insertDefinition>
	</div>
</div> 
<div class="form-group">
	<label class="control-label col-md-4 small" for="${field}-field-amount" data-i18n="crit_amount_concerned"><s:message code='crit_amount_concerned'/></label>
	<div class="col-md-5">
        <s:message code="crit_amount_concerned_placeholder" var="amountPlaceholder"/>
		<form:input path="${field}.amount" cssClass="form-control" id="${field}-field-amount" placeholder="${amountPlaceholder}"/>
	</div>
	<div class="col-md-3">
        <tiles:insertDefinition name="currencies">
            <tiles:putAttribute name="currencyField" value="${field}.currency"/>
            <tiles:putAttribute name="currencyHtmlId" value="${field}.currency"/>
            <tiles:putAttribute name="currencyCssClass" value="form-control"/>
        </tiles:insertDefinition>
	</div>
</div>

