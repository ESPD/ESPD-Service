<%@ page import="eu.europa.ec.grow.espd.domain.enums.other.Language" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<div class="row" style="padding:50px">
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
