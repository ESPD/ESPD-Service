<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:importAttribute name="field"/>
<tiles:importAttribute name="has_please_describe_them"/>
<tiles:importAttribute name="has_multiple_description_amount_date_recipients"/>
<tiles:importAttribute name="lastYearsNumber"/>
<tiles:importAttribute name="has_explain_supply_contracts_quality"/>
<tiles:importAttribute name="has_explain_certificates_independent_quality"/>
<tiles:importAttribute name="has_explain_certificates_independent_environmental"/>
<tiles:importAttribute name="has_please_specify"/>

<c:if test="${has_please_describe_them}">
    <div class="col-md-12">
        <div class="form-group">
            <div class="tab-pane" id="${field}-reliability">
                <div class="form-group">
                    <c:choose>
                        <c:when test="${has_explain_supply_contracts_quality}">
                            <label class="control-label col-md-3 small">${span18n['crit_explain_supply_contracts_quality_description']}</label>
                        </c:when>
                        <c:when test="${has_explain_certificates_independent_quality}">
                            <label class="control-label col-md-3 small">${span18n['crit_explain_certificates_independent_quality_description']}</label>
                        </c:when>
                        <c:when test="${has_explain_certificates_independent_environmental}">
                            <label class="control-label col-md-3 small">${span18n['crit_explain_certificates_independent_environmental_description']}</label>
                        </c:when>
                        <c:otherwise>
                            <label class="control-label col-md-3 small">${span18n['crit_please_describe_them']}</label>
                        </c:otherwise>
                    </c:choose>
                    <div class="col-md-9">
                        <form:textarea path="${field}.description" class="form-control"></form:textarea>
                    </div>
                </div>
            </div>
        </div>
    </div>
</c:if>
<c:if test="${has_please_specify}">
    <div class="col-md-12" id="${field}-form-specify">
        <div class="form-group">
            <div class="tab-pane" id="${field}-specify">
                <div class="form-group">
                    <label class="control-label col-md-3 small">${span18n['crit_please_specify']}</label>
                    <div class="col-md-9">
                        <form:textarea path="${field}.specify" class="form-control"></form:textarea>
                    </div>
                </div>
            </div>
        </div>
    </div>
</c:if>
<c:if test="${has_multiple_description_amount_date_recipients}">
    <tiles:insertDefinition name="multipleDescriptionAmountDateRecipients">
        <tiles:putAttribute name="field" value="${field}"/>
    </tiles:insertDefinition>
</c:if>
<c:if test="${lastYearsNumber != null}">
    <tiles:insertDefinition name="multipleYearNumber">
        <tiles:putAttribute name="field" value="${field}"/>
        <tiles:putAttribute name="lastYears" value="${lastYearsNumber}"/>
    </tiles:insertDefinition>
</c:if>