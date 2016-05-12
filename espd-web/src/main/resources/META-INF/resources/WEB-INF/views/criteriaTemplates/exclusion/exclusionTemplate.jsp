<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

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

<tiles:importAttribute name="field"/>
<tiles:importAttribute name="exclusionForm"/>
<tiles:importAttribute name="selfCleaning"/>
<tiles:importAttribute name="breachOfObligations"/>

<c:if test="${exclusionForm != ''}">
	<c:import url="${exclusionForm}" charEncoding="UTF-8"/>
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