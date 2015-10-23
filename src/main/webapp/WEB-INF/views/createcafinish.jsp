<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="container espd-container">

	<form:form id="espdform" role="form" class="form-horizontal" method="post" commandName="espd" data-toggle="validator">
	
	<div class="panel-default">
	
		<div class="panel panel-default no-border">	
			<ul class="nav nav-pills nav-wizard nav-justified">
			    <li class="active"><a href="#"><i class="fa fa-random"></i>&nbsp;<span data-i18n="progress_start"><s:message code='progress_start'/></span></a><div class="nav-arrow"></div></li>
			    <li class="active"><div class="nav-wedge"></div><a href="#"><i class="fa fa-university"></i>&nbsp;<span data-i18n="progress_procedure"><s:message code='progress_procedure'/></span></a><div class="nav-arrow"></div></li>
			    <li class="active"><div class="nav-wedge"></div><a href="#"><i class="fa fa-exclamation"></i>&nbsp;<span data-i18n="progress_exclusion"><s:message code='progress_exclusion'/></span></a><div class="nav-arrow"></div></li>
			    <li class="active"><div class="nav-wedge"></div><a href="#"><i class="fa fa-check-circle"></i>&nbsp;<span data-i18n="progress_selection"><s:message code='progress_selection'/></span></a><div class="nav-arrow"></div></li>
			    <li class="active"><div class="nav-wedge"></div><a href="#"><i class="fa fa-download"></i>&nbsp;<span data-i18n="progress_finish"><s:message code='progress_finish'/></span></a></li>
			</ul>
		</div>

		<div class="paragraph">
			<h2 data-i18n="createcafinish_header"><s:message code='createcafinish_header'/></h2>
		</div>

		<div class="espd-panel panel panel-default">
			<div data-i18n="createcafinish_export" class="espd-panel-heading">
				 <s:message code='createcafinish_export'/>
			</div>
			<div data-i18n="createcafinish_export_content" class="panel-body">
				<s:message code='createcafinish_export_content'/>
			</div>
		</div>
	
		<div class="col-md-7">&nbsp;</div>
		<div class="col-md-5">
			<div class="btn-group">
				<a class="btn btn-default btn-lg" href="${pageContext.request.contextPath}/createcasel">
				<i class="fa fa-arrow-circle-o-left"></i>
				<span data-i18n="PREV"><s:message code="PREV"/></span>
				</a>
				<a class="btn btn-default btn-lg" href="${pageContext.request.contextPath}/welcome">
				<i class="fa fa-times-circle"></i>
				<span data-i18n="CANCEL"><s:message code="CANCEL"/></span>
				</a>
				<button type="submit" name="next" class="btn btn-default btn-lg">
					<i class="fa fa-download"></i>
					<span data-i18n="EXPORT"><s:message code="EXPORT"/></span>
				</button>							
			</div>
		</div>    
	</div>
	</form:form>
</div>
