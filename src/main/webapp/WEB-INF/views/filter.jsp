<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<div class="container espd-container">
	<div class="panel-default">

		<ul class="nav nav-pills nav-wizard nav-justified">
			<li class="active"><a href="#"><i class="fa fa-random"></i>&nbsp;<span class="label_progress_start"><s:message code='progress_start'/></span></a><div class="nav-arrow"></div></li>
			<li><div class="nav-wedge"></div><a href="#"><i class="fa fa-university"></i>&nbsp;<span class="label_progress_procedure"><s:message code='progress_procedure'/></span></a><div class="nav-arrow"></div></li>
			<li><div class="nav-wedge"></div><a href="#"><i class="fa fa-exclamation"></i>&nbsp;<span class="label_progress_exclusion"><s:message code='progress_exclusion'/></span></a><div class="nav-arrow"></div></li>
			<li><div class="nav-wedge"></div><a href="#"><i class="fa fa-check-circle"></i>&nbsp;<span class="label_progress_selection"><s:message code='progress_selection'/></span></a><div class="nav-arrow"></div></li>
			<li><div class="nav-wedge"></div><a href="#"><i class="fa fa-download"></i>&nbsp;<span class="label_progress_finish"><s:message code='progress_finish'/></span></a></li>
		</ul>
	
		<div class="paragraph">
			<h2 class="label_filter_header"><s:message code='filter_header'/></h2>
		</div>

		<div class="alert alert-espd-info">
			<ul class="fa-ul">
			<li>
				<i class="info-label fa fa-info-circle fa-lg fa-li"></i>
				<span class="label_filter_alert"><s:message code='filter_alert'/></span>
			</li>
			</ul>
		</div>
		
		<div class="paragraph">
			<h3 class="label_filter_who_are_you"><s:message code='filter_who_are_you'/></h3>
			
			<div class="radio">
				<label><input type="radio" name="whoareyou"><span class="label_filter_i_am_ca"><s:message code='filter_i_am_ca'/></span></label>
			</div>
			<div class="radio">
				<label><input type="radio" name="whoareyou"><span class="label_filter_i_am_eop"><s:message code='filter_i_am_eop'/></span></label>
			</div>

			<h3 class="label_filter_what_you_do"><s:message code='filter_what_you_do'/></h3>
			
			<div class="radio">
				<label><input type="radio" name="action"><span class="label_filter_create_espd"><s:message code='filter_create_espd'/></span></label>
			</div>
			<div class="radio">
				<label><input type="radio" name="action"><span class="label_filter_reuse_espd"><s:message code='filter_reuse_espd'/></span></label>
			</div>
			<div class="radio">
				<label><input type="radio" name="action"><span class="label_filter_overview_espds"><s:message code='filter_overview_espds'/></span></label>
			</div>
			<div class="radio">
				<label><input type="radio" name="action"><span class="label_filter_review_espd"><s:message code='filter_review_espd'/></span></label>
			</div>
		</div>
	</div>

	<div class="col-md-8">&nbsp;</div>
	<div class="col-md-4">
		<div class="btn-group">
			<a class="btn btn-default btn-lg" href="/espd/welcome">
				<i class="fa fa-times-circle"></i>
				<span class="label_CANCEL"><s:message code="CANCEL"/></span>
			</a>
			<a class="btn btn-default btn-lg" href="/espd/createca">
				<i class="fa fa-arrow-circle-o-right"></i>
				<span class="label_NEXT"><s:message code="NEXT"/></span>
			</a>
		</div>
	</div>
</div>
