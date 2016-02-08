<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:importAttribute name="field"/>

<div class="form-group">
    <label class="control-label col-xs-4 small">
		${span18n["crit_country_member_state"]}
	</label>
    <div class="col-xs-8">
        <tiles:insertDefinition name="countries">
            <tiles:putAttribute name="field" value="${field}.country"/>
        </tiles:insertDefinition>
    </div>
</div>
<div class="form-group">
	<label class="control-label col-xs-4 small">
		${span18n["crit_amount_concerned"]}
	</label>
    <div class="col-xs-5">
        <form:input path="${field}.amount" cssClass="form-control" number="true" data-i18n="crit_amount_concerned_placeholder" placeholder="${i18n['crit_amount_concerned_placeholder']}"/>
    </div>
    <div class="col-xs-3">
        <tiles:insertDefinition name="currencies">
            <tiles:putAttribute name="currencyField" value="${field}.currency"/>
            <tiles:putAttribute name="currencyHtmlId" value="${field}.currency"/>
            <tiles:putAttribute name="currencyCssClass" value="form-control"/>
        </tiles:insertDefinition>
    </div>
</div>

