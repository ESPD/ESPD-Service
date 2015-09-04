<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

	<script>
		$(function() { $('#language').on('change', function() { language( this.value ); });});
	</script>
	
	<div id="header">
		<img id="banner-flag" src="/espd/static/img/logo.png" alt="European Commission logo"/>
		<!-- 
		<c:if test="${not empty applicationScope.deploymentEnvironment}">
			<span id="banner-env-text">
				<span id="environment">${applicationScope.deploymentEnvironment}</span>
			</span>
		</c:if>
		 --> 
		<span id="banner-sub-title-text" class="label_APP_SUBTITLE"><s:message code="APP_SUBTITLE"/></span>
		<span id="banner-title-text" class="label_APP_TITLE"><s:message code="APP_TITLE"/></span>
		<span id="banner-image-title-fill"></span>
		<span id="top-lang-selector">
			<ul class="reset-list">
				<li><a href="#" class="label_LEGAL_NOTICE"><s:message code="LEGAL_NOTICE"/></a></li>
				<li><a href="#" class="label_COOKIES"><s:message code="COOKIES"/></a></li>
				<li><a href="#" class="label_SEARCH"><s:message code="SEARCH"/></a></li>
				<li>
					<select id="language">
						<c:forEach var="code" items="${locales.codes}">
							<option value="${code}" ${(code eq pageContext.response.locale)?"selected":""}>${locales.nameByCode[code]}</option>
						</c:forEach>
					</select>
				</li>
			</ul>
		</span>
	</div>