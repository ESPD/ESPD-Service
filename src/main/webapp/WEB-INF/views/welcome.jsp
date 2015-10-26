<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
        
<div class="container espd-container">

	<div class="panel-default">
		<h2 data-i18n="welcome_disclaimer_title"><s:message code="welcome_disclaimer_title"/></h2>
		<div data-i18n="welcome_disclaimer_content"><s:message code="welcome_disclaimer_content"/></div>
		<h2 data-i18n="welcome_usecases_title"><s:message code="welcome_usecases_title"/></h2>
		<div data-i18n="welcome_usecases_content"><s:message code="welcome_usecases_content"/></div>
	</div>
	
	<div class="col-md-9">&nbsp;</div>
	<div class="col-md-3">
		<div class="btn-group">
			<a href="${pageContext.request.contextPath}/splash" class="btn btn-default btn-lg">
				<i class="fa fa-arrow-circle-o-right"></i>
				<span data-i18n="next"><s:message code="next"/></span>
			</a>
		</div>
	</div>
	
</div>
 