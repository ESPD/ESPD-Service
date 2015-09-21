<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
        
<div class="container espd-container">

	<div class="panel-default">
		<h2 class="label_WELCOME_DISCLAMER_TITLE"><s:message code="WELCOME_DISCLAMER_TITLE"/></h2>
		<div class="label_WELCOME_DISCLAMER_CONTENT"><s:message code="WELCOME_DISCLAMER_CONTENT"/></div>
		<h2 class="label_WELCOME_USECASES_TITLE"><s:message code="WELCOME_USECASES_TITLE"/></h2>
		<div class="label_WELCOME_USECASES_CONTENT"><s:message code="WELCOME_USECASES_CONTENT"/></div>
	</div>
	
	<div class="col-md-9">&nbsp;</div>
	<div class="col-md-3">
		<div class="btn-group">
			<a href="/espd/splash" class="btn btn-default btn-lg">
				<i class="fa fa-arrow-circle-o-right"></i>
				<span class="label_NEXT"><s:message code="NEXT"/></span>
			</a>
		</div>
	</div>
	
</div>
 