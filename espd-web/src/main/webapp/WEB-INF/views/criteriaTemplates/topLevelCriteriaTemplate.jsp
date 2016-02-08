<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<tiles:importAttribute name="topLevelCriteriaList" />
<tiles:importAttribute name="disableTooltips"/>

<c:forEach var="topLevelCriteria" items="${topLevelCriteriaList}">
	<tiles:insertDefinition name="${topLevelCriteria['template']}">
		<c:forEach var="euCriteria" items="${topLevelCriteria}">
			<tiles:putAttribute name="${euCriteria.key}" value="${euCriteria.value}"/>
		</c:forEach>
		<%-- only for ugly print version --%>
		<c:if test="${disableTooltips == true}">
			<tiles:putAttribute name="disableTooltips" value="true"/>
		</c:if>
	</tiles:insertDefinition>
</c:forEach>
