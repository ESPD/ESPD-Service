<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<div class="container espd-container">
	<div class="panel-default">
	
		<div class="panel panel-default no-border">	
			<ul class="nav nav-pills nav-wizard nav-justified">
			    <li class="active"><a href="#"><i class="fa fa-random"></i>&nbsp;<span class="label_progress_start"><s:message code='progress_start'/></span></a><div class="nav-arrow"></div></li>
			    <li class="active"><div class="nav-wedge"></div><a href="#"><i class="fa fa-university"></i>&nbsp;<span class="label_progress_procedure"><s:message code='progress_procedure'/></span></a><div class="nav-arrow"></div></li>
			    <li class="active"><div class="nav-wedge"></div><a href="#"><i class="fa fa-exclamation"></i>&nbsp;<span class="label_progress_exclusion"><s:message code='progress_exclusion'/></span></a><div class="nav-arrow"></div></li>
			    <li class="active"><div class="nav-wedge"></div><a href="#"><i class="fa fa-check-circle"></i>&nbsp;<span class="label_progress_selection"><s:message code='progress_selection'/></span></a><div class="nav-arrow"></div></li>
			    <li class="active"><div class="nav-wedge"></div><a href="#"><i class="fa fa-download"></i>&nbsp;<span class="label_progress_finish"><s:message code='progress_finish'/></span></a></li>
			</ul>
		</div>

		<div class="paragraph">
			<h2 class="label_createcafinish_header"><s:message code='createcafinish_header'/></h2>
		</div>

		<div class="espd-panel panel panel-default">
			<div class="espd-panel-heading label_createcafinish_export">
				 <s:message code='createcafinish_export'/>
			</div>
			<div class="panel-body label_createcafinish_export_content">
				<s:message code='createcafinish_export_content'/>
			</div>
		</div>
	
		<div class="col-md-7">&nbsp;</div>
		<div class="col-md-5">
			<div class="btn-group">
				<a class="btn btn-default btn-lg" href="/espd/createcasel">
				<i class="fa fa-arrow-circle-o-left"></i>
				<span class="label_PREV"><s:message code="PREV"/></span>
				</a>
				<a class="btn btn-default btn-lg" href="/espd/welcome">
				<i class="fa fa-times-circle"></i>
				<span class="label_CANCEL"><s:message code="CANCEL"/></span>
				</a>
				<a class="btn btn-default btn-lg" href="/espd/download" target="_blank">
					<i class="fa fa-download"></i>
				<span class="label_EXPORT"><s:message code="EXPORT"/></span>
				</a>								
			</div>
		</div>    
	</div>
</div>
