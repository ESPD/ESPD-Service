<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
        
<div class="container espd-container">
	
	<h1 class="label_WELCOME_DISCLAMER_TITLE"><s:message code="WELCOME_DISCLAMER_TITLE"/></h1>
	<div class="label_WELCOME_DISCLAMER_CONTENT"><s:message code="WELCOME_DISCLAMER_CONTENT"/></div>
	<h1 class="label_WELCOME_USECASES_TITLE"><s:message code="WELCOME_USECASES_TITLE"/></h1>
	<div class="label_WELCOME_USECASES_CONTENT"><s:message code="WELCOME_USECASES_CONTENT"/></div>

	<div class="col-md-9">&nbsp;</div>
	<div class="col-md-3">
		<div class="btn-group">
			<a href="/espd/splash" class="btn btn-default btn-lg" role="button">
				<span class="label_NEXT"><s:message code="NEXT"/></span>
				<label class="fa fa-arrow-circle-right"/>
			</a>
		</div>
	</div>

</div>
 