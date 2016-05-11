<%@ page import="eu.europa.ec.grow.espd.domain.enums.other.Language" %><%--
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
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    eu.europa.ec.grow.espd.util.I18NFunc inst = new eu.europa.ec.grow.espd.util.I18NFunc(pageContext);
    request.setAttribute("i18n", inst.message());
    request.setAttribute("div18n", inst.div());
    request.setAttribute("span18n", inst.span());
%>
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
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="author" content="European Commission, DG GROW, DG Internal Market">
    <title>ESPD error</title>
    <link rel="icon" type="image/png" href="data:image/png;base64,iVBORw0KGgo=">
    <link rel="stylesheet" type="text/css" href="<s:url value="/static/bundle/all.css"/>">
</head>
<body>
<div class="container panel" style="padding:0; border-color: #396ea2 !important;">
    <div id="header">
        <img id="banner-flag" class="hidden-print" src="${pageContext.request.contextPath}/static/img/logo.png" alt="European Commission logo"/>
        <img id="banner-flag" class="visible-print" style="width: 114px; height: 80px;" src="${pageContext.request.contextPath}/static/img/logo.png" alt="European Commission logo"/>
        <span id="banner-title-text" class="hidden-print">${span18n["app_title"]}</span>
        <span id="banner-title-text" style="font-size: 160%" class="visible-print">${span18n["app_title"]}</span>
        <span id="banner-image-title-fill"></span>
        <span id="banner-sub-title-text" class="hidden-print" data-i18n="app_subtitle"><s:message code="app_subtitle"/></span>
        <div id="top-lang-selector" class="hidden-print">
            <ul class="reset-list">
                <li><a target="_blank" href="http://ec.europa.eu/growth/legal-notice/index_en.htm">${span18n["legal_notice"]}</a></li>
                <li><a target="_blank" href="http://ec.europa.eu/growth/cookies/index_en.htm">${span18n["cookies"]}</a></li>
                <li><a target="_blank" href="${pageContext.request.contextPath}/contact">${span18n["contact"]}</a></li>
                <li>
                    <label for="language"></label><select id="language">
                    <c:forEach var="lang" items="<%=Language.values()%>">
                        <option value="${lang.code}" ${(lang.code eq pageContext.response.locale)?"selected":""}>${lang.sourceLanguage}</option>
                    </c:forEach>
                </select>
                </li>
            </ul>
        </div>
    </div>
    <div id="breadbar" class="hidden-print">
        <ul class="breadcrumbs">
            <li><a target="_blank" href="http://ec.europa.eu/index_en.htm" data-i18n="ec" class="breadcrumElement"><s:message code="ec"/></a></li>
            <li><a target="_blank" href="http://ec.europa.eu/growth/index_en.htm" data-i18n="grow" class="breadcrumElement"><s:message code="grow"/></a></li>
            <li><a href="#" data-i18n="espd" class="breadcrumElement"><s:message code="espd"/></a></li>
        </ul>
    </div>
    <div id="body" class="container espd-container">
        <div class="row-fluid">
            <div class="span9">
                <div>
                    <h2 class="text-center alert alert-danger">An unexpected error has occurred.</h2>
                </div>
            </div>
        </div>
    </div>
</div>
<c:set var="now" value="<%=new java.util.Date()%>" scope="request"/>
<div id="footer">
    <footer style="padding-top: 10px; color: black" class="hidden-print container-fluid">
        <div class="row">
            <div class="col-lg-12">
                <span data-i18n="footer_last_update"><s:message code='footer_last_update'/></span>
                <span class="datefmt"> <fmt:formatDate value="${now}"/> </span> |
                <a target="_blank" href="http://ec.europa.eu/growth/tools-databases/security-incidents/index_en.htm">
                <span data-i18n="footer_report_security_incident"><s:message
                        code='footer_report_security_incident'/></span>
                </a>
            </div>
        </div>
    </footer>
</div>
<s:eval var="piwikEnabled" scope="page" expression='@espdConfiguration.piwikEnabled'/>
<s:eval var="piwikServer" scope="page" expression='@espdConfiguration.piwikServer'/>
<s:eval var="piwikId" scope="page" expression='@espdConfiguration.piwikId'/>
<c:if test="${piwikEnabled == true}">
    <script type="text/javascript">
        var piwik = Piwik.getTracker("${piwikServer}", ${piwikId});
        piwik.enableLinkTracking(true);
        piwik.trackPageView();
    </script>
    <noscript><p><img src="${piwikServer}?idsite=${piwikId}" style="border:0;" alt=""/></p></noscript>
</c:if>
</body>
</html>
