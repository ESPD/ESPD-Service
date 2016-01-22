<%@ page import="eu.europa.ec.grow.espd.constants.enums.Country" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:importAttribute name="field"/>

<form:select path="${field}" cssClass="form-control">
    <form:option value="${null}" label="---"/>
    <optgroup label="EU">
        <c:forEach items="<%=Country.EU_COUNTRIES%>" var="cty">
            <form:option data-i18n="${cty.i18nCode}" value="${cty}"><s:message
                    code="${cty.i18nCode}"/></form:option>
        </c:forEach>
    </optgroup>
    <optgroup label="EU+">
        <c:forEach items="<%=Country.EU_PLUS_COUNTRIES%>" var="cty">
            <form:option data-i18n="${cty.i18nCode}" value="${cty}"><s:message
                    code="${cty.i18nCode}"/></form:option>
        </c:forEach>
    </optgroup>
    <optgroup label="EFTA">
        <c:forEach items="<%=Country.EFTA_COUNTRIES%>" var="cty">
            <form:option data-i18n="${cty.i18nCode}" value="${cty}"><s:message
                    code="${cty.i18nCode}"/></form:option>
        </c:forEach>
    </optgroup>
</form:select>
