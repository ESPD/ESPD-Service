<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:importAttribute name="currencyField"/>

<form:select path="${currencyField}" cssClass="form-control">
    <form:option value="${null}" label="---"/>
    <c:forEach items="<%=eu.europa.ec.grow.espd.constants.enums.Currency.values()%>" var="curr">
        <form:option value="${curr}">${curr}</form:option>
    </c:forEach>
</form:select>