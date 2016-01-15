<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:importAttribute name="yearField"/>
<tiles:importAttribute name="yearHtmlId"/>
<tiles:importAttribute name="yearCssClass"/>
<tiles:importAttribute name="maximumYears"/>

<jsp:useBean id="today" class="java.util.Date" scope="session"/>
<fmt:formatDate pattern="yyyy" value="${today}" var="current_year" />

<form:select path="${yearField}" id="${yearHtmlId}" cssClass="${yearCssClass}">
    <form:option value="${null}" label="---"/>
    <c:forEach begin="${current_year - maximumYears + 1}" end="${current_year}" var="year">
        <!-- Trick to show the years in reverse order -->
        <form:option value="${current_year - year + current_year - maximumYears + 1}"/>
    </c:forEach>
</form:select>
