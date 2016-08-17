<%@ page import="eu.europa.ec.grow.espd.domain.enums.other.Language" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

<s:eval var="espdEnvironment" scope="page" expression="@espdConfiguration.espdEnvironment" />

<div class="row" style="padding:50px">

	<c:if test="${espdEnvironment == true}">
		<div class="alert alert-danger">
			<ul class="fa-ul">
                <li>
                    <i class="error-label fa fa-info-circle fa-lg fa-li"></i>
                    <s:message code="app_environment"/> <a href="https://ec.europa.eu/espd">https://ec.europa.eu/espd</a>
                </li>
			</ul>
		</div>
	</c:if>

    <fmt:formatNumber var="colLen" value="6" maxFractionDigits="0"/>
    <div class="col-lg-3 col-sm-6">
        <c:forEach var="lang" items="<%=Language.values()%>" varStatus="i">
            ${((i.index % colLen) == 0 && !i.first && !i.last) ? "</div><div class='col-lg-3 col-sm-6 langBoxBorder'>" : ""}
            <div class="splashLangDiv">
                <a href="${pageContext.request.contextPath}/filter?lang=${lang.code}" class="splashLangBox">${lang.code}</a>
                <a href="${pageContext.request.contextPath}/filter?lang=${lang.code}">${lang.sourceLanguage}</a>
            </div>
        </c:forEach>
    </div>
</div>
