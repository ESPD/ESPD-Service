<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<div class="container espd-container">
	<div class="panel-default">
		<ul class="nav nav-pills nav-wizard nav-justified">
		    <li class="active"><a href="#"><i class="fa fa-random"></i>&nbsp;<span>Start</span></a><div class="nav-arrow"></div></li>
		    <li><div class="nav-wedge"></div><a href="#"><i class="fa fa-university"></i>&nbsp;<span>Procedure</span></a><div class="nav-arrow"></div></li>
		    <li><div class="nav-wedge"></div><a href="#"><i class="fa fa-exclamation"></i>&nbsp;<span>Exclusion</span></a><div class="nav-arrow"></div></li>
		    <li><div class="nav-wedge"></div><a href="#"><i class="fa fa-check-circle"></i>&nbsp;<span>Selection</span></a><div class="nav-arrow"></div></li>
		    <li><div class="nav-wedge"></div><a href="#"><i class="fa fa-download"></i>&nbsp;<span>Finish</span></a></li>
		</ul>
	
		<h2>Selection of entity</h2>

		<div class="alert alert-espd-info">
			<ul class="fa-ul">
			<li>
				<i class="fa fa-info-circle fa-lg fa-li"></i>
				<p>Standard form for the European Single Procurement Document (ESPD).
				<p>The ESPD is a self-declaration provided by economic operators which are asked for a preliminary evidence in a procurement procedure. It replaces the certificates issued by public authorities or third parties. It aims to reduce the administrative burden arising from the requirement to produce a substantial number of certificates or other types of evidence related to exclusion and selection criteria.
			</li>
			</ul>
		</div>
		
		<h2>Who are you?</h2>
		
		<div class="radio">
			<label><input type="radio" name="whoareyou">I am a contracting authority </label>
		</div>
		<div class="radio">
			<label><input type="radio" name="whoareyou">I am an economic operator </label>
		</div>
		
		<h2>What would you like to do?</h2>
		
		<div class="radio">
			<label><input type="radio" name="action">Create a new ESPD</label>
		</div>
		<div class="radio">
			<label><input type="radio" name="action">Reuse an existing ESPD</label>
		</div>
		<div class="radio">
			<label><input type="radio" name="action">Overview of the received ESPDs</label>
		</div>
		<div class="radio">
			<label><input type="radio" name="action">Review ESPD</label>
		</div>
	</div>

	<div class="col-md-9">&nbsp;</div>
	<div class="col-md-3">
		<div class="btn-group">
			<a class="btn btn-default btn-lg" href="/espd/welcome">
				<i class="fa fa-times-circle"></i> Cancel
			</a>
			<a class="btn btn-default btn-lg" href="/espd/createca">
				<i class="fa fa-arrow-circle-o-right"></i> Next
			</a>
		</div>
	</div>
</div>
