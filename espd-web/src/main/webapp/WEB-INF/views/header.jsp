<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<jsp:useBean id="languages" class="java.util.LinkedHashMap" scope="request"/>
<c:set target="${languages}" property="bg" value="български"/>
<c:set target="${languages}" property="cs" value="čeština"/>
<c:set target="${languages}" property="da" value="Dansk"/>
<c:set target="${languages}" property="de" value="Deutsch"/>
<c:set target="${languages}" property="et" value="Eesti keel"/>
<c:set target="${languages}" property="el" value="ελληνικά"/>
<c:set target="${languages}" property="en" value="English"/>
<c:set target="${languages}" property="es" value="Español"/>
<c:set target="${languages}" property="fr" value="Français"/>
<c:set target="${languages}" property="ga" value="Gaeilge"/>
<c:set target="${languages}" property="hr" value="Hrvatski"/>
<c:set target="${languages}" property="it" value="Italiano"/>
<c:set target="${languages}" property="lv" value="Latviešu valoda"/>
<c:set target="${languages}" property="lt" value="Lietuvių kalba"/>
<c:set target="${languages}" property="hu" value="Magyar"/>
<c:set target="${languages}" property="mt" value="Malti"/>
<c:set target="${languages}" property="nl" value="Nederlands"/>
<c:set target="${languages}" property="pl" value="Polski"/>
<c:set target="${languages}" property="pt" value="Português"/>
<c:set target="${languages}" property="ro" value="Română"/>
<c:set target="${languages}" property="sk" value="Slovenčina"/>
<c:set target="${languages}" property="sl" value="Slovenščina"/>
<c:set target="${languages}" property="fi" value="Suomi"/>
<c:set target="${languages}" property="sv" value="Svenska"/>
<script>
    $(function () {
        $('#language').on('change', function () {
            language(this.value);
        });
    });
</script>

<div id="header">
    <img id="banner-flag" class="hidden-print" src="${pageContext.request.contextPath}/static/img/logo.png" alt="European Commission logo"/>
    <img id="banner-flag" class="visible-print" style="width: 114px; height: 80px;" src="${pageContext.request.contextPath}/static/img/logo.png" alt="European Commission logo"/>
    <c:if test="${not empty applicationScope.deploymentEnvironment}">
			<span id="banner-env-text">
				<span id="environment">${applicationScope.deploymentEnvironment}</span>
			</span>
    </c:if>
    <span id="banner-title-text" class="hidden-print">${span18n["app_title"]}</span>
    <span id="banner-title-text" style="font-size: 160%" class="visible-print">${span18n["app_title"]}</span>
    <span id="banner-image-title-fill"></span>
    <span id="banner-sub-title-text" class="hidden-print" data-i18n="app_subtitle"><s:message code="app_subtitle"/></span>
    <div id="top-lang-selector" class="hidden-print">
        <ul class="reset-list">
            <li><a target="_blank" href="http://ec.europa.eu/growth/legal-notice/index_en.htm" data-i18n="legal_notice"><s:message code="legal_notice"/></a></li>
            <li><a target="_blank" href="http://ec.europa.eu/growth/cookies/index_en.htm" data-i18n="cookies"><s:message code="cookies"/></a></li>
            <li><a target="_blank" href="http://ec.europa.eu/geninfo/query/index.do?swlang=${pageContext.response.locale}" data-i18n="search"><s:message code="search"/></a></li>
            <li>
                <label for="language"></label><select id="language">
                <c:forEach var="lang" items="${languages}">
                    <option value="${lang.key}" ${(lang.key eq pageContext.response.locale)?"selected":""}>${lang.value}</option>
                </c:forEach>
            </select>
            </li>
        </ul>
    </div>
</div>