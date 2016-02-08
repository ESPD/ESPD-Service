<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:importAttribute name="field"/>
<tiles:importAttribute name="exclusionForm"/>
<tiles:importAttribute name="selfCleaning"/>
<tiles:importAttribute name="breachOfObligations"/>

<c:if test="${exclusionForm != ''}">
	<c:import url="${exclusionForm}"/>
</c:if>
<c:if test="${breachOfObligations}">
	<tiles:insertDefinition name="breachOfObligations">
		<tiles:putAttribute name="field" value="${field}"/>
	</tiles:insertDefinition>
</c:if>
<c:if test="${selfCleaning}">
	<tiles:insertDefinition name="selfCleaning">
		<tiles:putAttribute name="field" value="${field}"/>
	</tiles:insertDefinition>
</c:if>