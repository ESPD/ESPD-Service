<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<tiles:importAttribute name="field"/>
<tiles:importAttribute name="has_please_describe_them"/>
<tiles:importAttribute name="lastYearsAmount"/>
<tiles:importAttribute name="has_multiple_description_ratio"/>
<tiles:importAttribute name="has_multiple_year_amount"/>
<tiles:importAttribute name="has_single_amount"/>
<tiles:importAttribute name="has_specify_year"/>
<tiles:importAttribute name="has_number_of_years"/>
<c:if test="${has_please_describe_them}">
    <div class="col-md-12" id="${field}-form">
        <div class="form-group">
            <div class="tab-pane" id="${field}-reliability">
                <div class="form-group">
                    <label class="control-label col-md-3 small">
                            ${span18n["crit_please_describe_them"]}
                    </label>
                    <div class="col-md-9">
                        <form:textarea path="${field}.description" cssClass="form-control"></form:textarea>
                    </div>
                </div>
            </div>
        </div>
    </div>
</c:if>
<c:if test="${has_multiple_year_amount && lastYearsAmount != null}">
    <tiles:insertDefinition name="multipleYearAmount">
        <tiles:putAttribute name="field" value="${field}"/>
        <tiles:putAttribute name="lastYears" value="${lastYearsAmount}"/>
    </tiles:insertDefinition>
</c:if>
<c:if test="${has_multiple_description_ratio}">
    <tiles:insertDefinition name="multipleDescriptionRatio">
        <tiles:putAttribute name="field" value="${field}"/>
    </tiles:insertDefinition>
</c:if>
<c:if test="${has_single_amount}">
    <div class="form-group">
        <label class="control-label col-xs-3 small">
                ${span18n["crit_amount"]}
        </label>
        <div class="col-xs-6">
            <form:input type="text" path="${field}.amount" cssClass="form-control" number="true"></form:input>
        </div>
        <div class="col-xs-3">
            <tiles:insertDefinition name="currencies">
                <tiles:putAttribute name="currencyField" value="${field}.currency"/>
            </tiles:insertDefinition>
        </div>
    </div>
</c:if>
<c:if test="${has_specify_year}">
    <div class="form-group">
        <label class="control-label col-md-3 small" data-i18n="crit_please_specify"><s:message
                code='crit_please_specify'/></label>
        <div class="col-md-9">
            <form:select path="${field}.year" cssClass="form-control">
                <form:option value="${null}" label="---"/>
                <form:options items="${lastYearsAmount}"/>
            </form:select>
        </div>
    </div>
</c:if>
<c:if test="${has_number_of_years}">
    <div class="form-group">
        <div class="col-md-4">
            <label class="control-label col-xs-3 small">${span18n['crit_number_of_years']}</label>
            <div class="col-xs-9" style="margin-bottom: 5px;">
                <form:input type="text" path="${field}.numberOfYears" digits="true" max="100"
                            cssClass="form-control"></form:input>
            </div>
        </div>
        <div class="col-md-8">
            <label class="control-label col-xs-3 small">${span18n['crit_average_turnover']}</label>
            <div class="col-xs-5">
                <form:input type="text" path="${field}.averageTurnover" number="true"
                            cssClass="form-control"></form:input>
            </div>
            <div class="col-xs-4">
                <tiles:insertDefinition name="currencies">
                    <tiles:putAttribute name="currencyField" value="${field}.averageTurnoverCurrency"/>
                </tiles:insertDefinition>
            </div>
        </div>
    </div>
</c:if>