<%@ page import="eu.europa.ec.grow.espd.domain.enums.other.Language" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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

<script>
    $(function () {
        $('#language').on('change', function () {
            language(this.value);
            var faqLink = $('#faq_link');
            if (faqLink && this.value) {
                faqLink.attr('href', 'http://ec.europa.eu/DocsRoom/documents/16002/attachments/1/translations/' + this.value.substr(0, 2) + '/renditions/native');
            }
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
    <span id="banner-image-title-fill" class="hidden-print"></span>
    <span id="banner-sub-title-text" class="hidden-print" data-i18n="app_subtitle"><s:message code="app_subtitle"/></span>
    <div id="top-lang-selector" class="hidden-print">
        <ul class="reset-list">
            <li><a target="_blank" href="http://ec.europa.eu/growth/legal-notice/index_en.htm">${span18n["legal_notice"]}</a></li>
            <li><a target="_blank" href="http://ec.europa.eu/growth/cookies/index_en.htm">${span18n["cookies"]}</a></li>
            <li><a target="_blank" href="${pageContext.request.contextPath}/contact">${span18n["contact"]}</a></li>
            <li><a target="_blank" href="${pageContext.request.contextPath}/about">${span18n["about"]}</a></li>
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