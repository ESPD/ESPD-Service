<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<%--
  ~
  ~ Copyright 2016 EUROPEAN COMMISSION
  ~
  ~ Licensed under the EUPL, Version 1.1 or – as soon they
  ~ will be approved by the European Commission - subsequent
  ~ versions of the EUPL (the "Licence");
  ~
  ~ You may not use this work except in compliance with the Licence.
  ~
  ~ You may obtain a copy of the Licence at:
  ~
  ~ https://joinup.ec.europa.eu/community/eupl/og_page/eupl
  ~
  ~ Unless required by applicable law or agreed to in
  ~ writing, software distributed under the Licence is
  ~ distributed on an "AS IS" basis,
  ~ WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
  ~ express or implied.
  ~ See the Licence for the specific language governing
  ~ permissions and limitations under the Licence.
  ~
  --%>

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
