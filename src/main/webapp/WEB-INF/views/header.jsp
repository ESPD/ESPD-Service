<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

	<script>
		$(function() { $('#language').on('change', function() { language( this.value ); });});
	</script>

	<div id="header">

		<c:if test="${not empty applicationScope.deploymentEnvironment}">
			<span id="banner-env-text">
				<span id="environment">${applicationScope.deploymentEnvironment}</span>
			</span>
		</c:if>
		<span id="banner-title-text" data-i18n="app_title"><s:message code="app_title"/></span>
		<span id="banner-image-title-fill"></span>
		<span id="banner-sub-title-text" data-i18n="app_subtitle"><s:message code="app_subtitle"/></span>
		<div id="top-lang-selector">
			<ul class="reset-list">
				<li><a href="#" data-i18n="legal_notice"><s:message code="legal_notice"/></a></li>
				<li><a href="#" data-i18n="cookies"><s:message code="cookies"/></a></li>
				<li><a href="#" data-i18n="search"><s:message code="search"/></a></li>
				<li>
					<label for="language"></label><select id="language">
						<c:forEach var="code" items="${locales.codes}">
							<option value="${code}" ${(code eq pageContext.response.locale)?"selected":""}>${locales.nameByCode[code]}</option>
						</c:forEach>
					</select>
				</li>
			</ul>
		</div>
	</div>