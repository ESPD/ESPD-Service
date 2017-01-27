<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
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
<div id="breadbar" class="hidden-print">
    <s:eval var="breadcrumbAsMap" scope="page" expression="@espdConfiguration.breadcrumbAsMap"/>
    <c:if test="${not empty breadcrumbAsMap}">
        <ul class="breadcrumbs">
            <c:forEach var="entry" items="${breadcrumbAsMap}" varStatus="status">
                <li>
                    <c:choose>
                        <c:when test="${not status.last}">
                            <a target="_blank" href="${entry.key}" data-i18n="${entry.value}"
                               class="breadcrumbElement"><s:message
                                    code="${entry.value}" text="${entry.value}"/></a>
                        </c:when>
                        <c:otherwise>
                            <a href="${entry.key}" data-i18n="${entry.value}" class="breadcrumbElement"><s:message
                                    code="${entry.value}" text="${entry.value}"/></a>
                        </c:otherwise>
                    </c:choose>
                </li>
            </c:forEach>
        </ul>
    </c:if>
</div>