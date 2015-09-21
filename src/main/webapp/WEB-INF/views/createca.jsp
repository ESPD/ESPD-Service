<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<script>
	$(function() {
		$("#espdform").validate({
			errorContainer: $("div.errorContainer"),
			errorLabelContainer: $(".errorLabelContainer ul"),
			wrapper: 'li'
		});
	});
</script>

<div class="container espd-container">

	<form:form id="espdform" role="form" class="form-horizontal" method="post" commandName="espd" data-toggle="validator">

	<div class="panel-default">
		<div class="panel panel-default no-border">	
			<ul class="nav nav-pills nav-wizard nav-justified">
			    <li class="active"><a href="#"><i class="fa fa-random"></i>&nbsp;<span>Start</span></a><div class="nav-arrow"></div></li>
			    <li class="active"><div class="nav-wedge"></div><a href="#"><i class="fa fa-university"></i>&nbsp;<span>Procedure</span></a><div class="nav-arrow"></div></li>
			    <li><div class="nav-wedge"></div><a href="#"><i class="fa fa-exclamation"></i>&nbsp;<span>Exclusion</span></a><div class="nav-arrow"></div></li>
			    <li><div class="nav-wedge"></div><a href="#"><i class="fa fa-check-circle"></i>&nbsp;<span>Selection</span></a><div class="nav-arrow"></div></li>
			    <li><div class="nav-wedge"></div><a href="#"><i class="fa fa-download"></i>&nbsp;<span>Finish</span></a></li>
			</ul>
		</div>

		<div class="paragraph">
			<h2>Information concerning the contracting authority and the procurement procedure</h2>
		</div>
		
		<div class="errorContainer alert alert-danger" style="display: none">
			<ul class="fa-ul">
			<li>
				<i class="info-label fa fa-exclamation-triangle fa-lg fa-li"></i>
				<p>You need to correct the following errors before you can continue:
				<div class="errorLabelContainer"> <ul></ul> </div>
			</li>
			</ul>
		</div>

		<div class="espd-panel panel panel-default">
			<div class="espd-panel-heading" data-toggle="collapse" data-target="#cadiv">
				Contact details of the Contracting Authority
			</div>
			<div id="cadiv" class="collapse in">
			<div class="panel-body">
				<div class="row">
					<div class="col-md-6">
						<div class="form-group ">
							<label class="control-label col-md-4" for="name">Name:</label>
							<div class="col-md-8">
								<form:input cssClass="form-control" path="name" placeholder="Enter name" required="true"/>
							</div>
						</div>
						<div class="form-group ">
							<label class="control-label col-md-4" for="name">National registration number: (if known):</label>
							<div class="col-md-8">
								<form:input cssClass="form-control" path="streetAndNumber" placeholder="Enter registration number"/>
							</div>
						</div>
						<div class="form-group ">
							<label class="control-label col-md-4" for="name">Street and number:</label>
							<div class="col-md-8">
								<form:input cssClass="form-control" path="natRegNumber" placeholder="Enter street and number"/>
							</div>
						</div>
						<div class="form-group ">
							<label class="control-label col-md-4" for="name">Postcode:</label>
							<div class="col-md-8">
								<form:input cssClass="form-control" path="postcode" placeholder="Enter Postcode"/>
							</div>
						</div>
						<div class="form-group ">
							<label class="control-label col-md-4" for="name">City:</label>
							<div class="col-md-8">
								<form:input cssClass="form-control" path="city" placeholder="Enter City"/>
							</div>
						</div>
						<div class="form-group ">
							<label class="control-label col-md-4" for="name">Country:</label>
							<div class="col-md-8">
								<form:input cssClass="form-control" path="country" placeholder="Enter Country"/>
							</div>
						</div>
					</div>

					<div class="col-md-6">
						<div class="form-group ">
							<label class="control-label col-md-4" for="name">Contact Person:</label>
							<div class="col-md-8">
								<form:input cssClass="form-control" path="contactPerson" placeholder="Enter contact person"/>
							</div>
						</div>
						<div class="form-group ">
							<label class="control-label col-md-4" for="name">E-mail:</label>
							<div class="col-md-8">
								<form:input cssClass="form-control" path="email" placeholder="Enter E-mail"/>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-4" for="name">Confirm e-mail:</label>
							<div class="col-md-8">
								<input class="form-control" placeholder="Confirm E-mail">
							</div>
						</div>
						<div class="form-group ">
							<label class="control-label col-md-4" for="name">Telephone:</label>
							<div class="col-md-8">
								<form:input cssClass="form-control" path="telephone" placeholder="Enter Telephone"/>
							</div>
						</div>
						<div class="form-group ">
							<label class="control-label col-md-4" for="name">Confirm Telephone:</label>
							<div class="col-md-8">
								<input type="name" class="form-control" placeholder="Confirm Telephone">
							</div>
						</div>
						<div class="form-group ">
							<label class="control-label col-md-4" for="name">Website:(if applicable)</label>
							<div class="col-md-8">
								<form:input cssClass="form-control" path="website" placeholder="Enter Website"/>
							</div>
						</div>
					</div>
				</div>
			</div>
			</div>
		</div>
		<div class="espd-panel panel panel-default">
			<div class="espd-panel-heading" data-toggle="collapse" data-target="#ppdiv">
				Information about the procurement procedure
			</div>
			<div id="ppdiv" class="collapse in">
			<div class="panel-body">
				<div class="row">
					<div class="col-md-12">
						<div class="alert alert-espd-info" style="border: 1px dotted blue; background-color: #D8D8D8;">
							<p>To be filled in by the contracting authority or contracting entity:
							<p>Reference to the relevant notice published in the Official Journal of the European Union:
							<p>OJEU S number [], date [], page [], 
							<p>Notice number in the OJ S: [ ][ ][ ][ ]/S [ ][ ][ ]–[ ][ ][ ][ ][ ][ ][ ]
						</div>
					
						<div class="form-group col-md-12">
							<label class="control-label" for="pwd">Title or short description of the procurement procedure:</label>
							<form:textarea path="procedureDesc" cssStyle="resize: none" rows="4" cols="20" cssClass="form-control"/>
						</div>
		
						<div class="form-group col-md-12">
							<label class="control-label col-md-8" for="name">Where applicable, indication of the lot(s) concerned: (optional)</label>
							<div class="col-md-4">
								<form:input cssClass="form-control" path="lotConcerned" placeholder=""/>
							</div>
						</div>
						<div class="form-group col-md-12">
							<label class="control-label col-md-8" for="name">File reference number attributed by the contracting authority: (if applicable)</label>
							<div class="col-md-4">
								<form:input cssClass="form-control" path="fileRefByCA" placeholder=""/>
							</div>
						</div>
						<div class="form-group col-md-12">
							<label class="control-label col-md-8" for="name">Where applicable, website where the procurement documents are available: (optional)</label>
							<div class="col-md-4">
								<form:input cssClass="form-control" path="websiteProcDocs" placeholder=""/>
							</div>							
						</div>
					</div>
				</div>
			</div>
			</div>
		</div>
 
		
	</div>
	
	<div class="col-md-8">&nbsp;</div>
	<div class="col-md-4">
		<div class="btn-group">
			<a class="btn btn-default btn-lg" href="/espd/filter">
				<i class="fa fa-arrow-circle-o-left"></i> Previous
			</a>
			<a class="btn btn-default btn-lg" href="/espd/welcome">
				<i class="fa fa-times-circle"></i> Cancel
			</a>
			<button type="submit" class="btn btn-default btn-lg">
				<i class="fa fa-arrow-circle-o-right"></i> Next
			</button>
		</div>
	</div>    
	
	</form:form>
</div>


