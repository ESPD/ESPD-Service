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
			    <li class="active"><a href="#"><i class="fa fa-random"></i>&nbsp;<span data-i18n="progress_start"><s:message code='progress_start'/></span></a><div class="nav-arrow"></div></li>
			    <li class="active"><div class="nav-wedge"></div><a href="#"><i class="fa fa-university"></i>&nbsp;<span data-i18n="progress_procedure"><s:message code='progress_procedure'/></span></a><div class="nav-arrow"></div></li>
			    <li><div class="nav-wedge"></div><a href="#"><i class="fa fa-exclamation"></i>&nbsp;<span data-i18n="progress_exclusion"><s:message code='progress_exclusion'/></span></a><div class="nav-arrow"></div></li>
			    <li><div class="nav-wedge"></div><a href="#"><i class="fa fa-check-circle"></i>&nbsp;<span data-i18n="progress_selection"><s:message code='progress_selection'/></span></a><div class="nav-arrow"></div></li>
			    <li><div class="nav-wedge"></div><a href="#"><i class="fa fa-download"></i>&nbsp;<span data-i18n="progress_finish"><s:message code='progress_finish'/></span></a></li>
			</ul>
		</div>

		<div class="paragraph">
			<h2>
				<span data-i18n="createca_header"><s:message code="createca_header"/></span>
				(${espd.isCA?"I am CA":""}${espd.isEO?"I am EO":""})
			</h2>
		</div>
		
		<div class="errorContainer alert alert-danger" style="display: none">
			<ul class="fa-ul">
			<li>
				<i class="info-label fa fa-exclamation-triangle fa-lg fa-li"></i>
				<div data-i18n="createca_correct_errors"><s:message code="createca_correct_errors"/></div>
				<div class="errorLabelContainer"> <ul></ul> </div>
			</li>
			</ul>
		</div>

		<div class="espd-panel panel panel-default">
			<div class="espd-panel-heading" data-toggle="collapse" data-target="#cadiv">
				<span data-i18n="createca_contact_details_ca"><s:message code="createca_contact_details_ca"/></span>
			</div>
			<div id="cadiv" class="collapse in">
			<div class="panel-body">
				<div class="row">
					<div class="col-md-6">
						<div class="form-group ">
							<label class="control-label col-md-4 " for="name"><span data-i18n="createca_name"><s:message code="createca_name"/></span></label>
							<div class="col-md-8">
								<form:input cssClass="form-control" path="name" placeholder="Enter name" required="true"/>
							</div>
						</div>
						<div class="form-group ">
							<label class="control-label col-md-4 " for="name"><span data-i18n="createca_nat_regnum"><s:message code="createca_nat_regnum"/></span></label>
							<div class="col-md-8">
								<form:input cssClass="form-control" path="streetAndNumber" placeholder="Enter registration number"/>
							</div>
						</div>
						<div class="form-group ">
							<label class="control-label col-md-4 " for="name"><span data-i18n="createca_street_and_num"><s:message code="createca_street_and_num"/></span></label>
							<div class="col-md-8">
								<form:input cssClass="form-control" path="natRegNumber" placeholder="Enter street and number"/>
							</div>
						</div>
						<div class="form-group ">
							<label class="control-label col-md-4 " for="name"><span data-i18n="createca_postcode"><s:message code="createca_postcode"/></span></label>
							<div class="col-md-8">
								<form:input cssClass="form-control" path="postcode" placeholder="Enter Postcode"/>
							</div>
						</div>
						<div class="form-group ">
							<label class="control-label col-md-4 " for="name"><span data-i18n="createca_city"><s:message code="createca_city"/></span></label>
							<div class="col-md-8">
								<form:input cssClass="form-control" path="city" placeholder="Enter City"/>
							</div>
						</div>
						<div class="form-group ">
							<label class="control-label col-md-4 " for="name"><span data-i18n="createca_country"><s:message code="createca_country"/></span></label>
							<div class="col-md-8">
								<form:select path="country" cssClass="form-control">
									<form:option value="empty" label="---"/>
									<form:option data-i18n="country_austria" value='austria'><s:message code="country_austria"/></form:option>
									<form:option data-i18n="country_belgium" value='belgium'><s:message code="country_belgium"/></form:option>
									<form:option data-i18n="country_bulgaria" value='bulgaria'><s:message code="country_bulgaria"/></form:option>
									<form:option data-i18n="country_cyprus" value='cyprus'><s:message code="country_cyprus"/></form:option>
									<form:option data-i18n="country_croatia" value='croatia'><s:message code="country_croatia"/></form:option>
									<form:option data-i18n="country_czech_republic" value='czech_republic'><s:message code="country_czech_republic"/></form:option>
									<form:option data-i18n="country_denmark" value='denmark'><s:message code="country_denmark"/></form:option>
									<form:option data-i18n="country_estonia" value='estonia'><s:message code="country_estonia"/></form:option>
									<form:option data-i18n="country_finland" value='finland'><s:message code="country_finland"/></form:option>
									<form:option data-i18n="country_france" value='france'><s:message code="country_france"/></form:option>
									<form:option data-i18n="country_germany" value='germany'><s:message code="country_germany"/></form:option>
									<form:option data-i18n="country_greece" value='greece'><s:message code="country_greece"/></form:option>
									<form:option data-i18n="country_hungary" value='hungary'><s:message code="country_hungary"/></form:option>
									<form:option data-i18n="country_ireland" value='ireland'><s:message code="country_ireland"/></form:option>
									<form:option data-i18n="country_italy" value='italy'><s:message code="country_italy"/></form:option>
									<form:option data-i18n="country_latvia" value='latvia'><s:message code="country_latvia"/></form:option>
									<form:option data-i18n="country_lithuania" value='lithuania'><s:message code="country_lithuania"/></form:option>
									<form:option data-i18n="country_luxembourg" value='luxembourg'><s:message code="country_luxembourg"/></form:option>
									<form:option data-i18n="country_malta" value='malta'><s:message code="country_malta"/></form:option>
									<form:option data-i18n="country_netherlands" value='netherlands'><s:message code="country_netherlands"/></form:option>
									<form:option data-i18n="country_poland" value='poland'><s:message code="country_poland"/></form:option>
									<form:option data-i18n="country_portugal" value='portugal'><s:message code="country_portugal"/></form:option>
									<form:option data-i18n="country_romania" value='romania'><s:message code="country_romania"/></form:option>
									<form:option data-i18n="country_slovakia" value='slovakia'><s:message code="country_slovakia"/></form:option>
									<form:option data-i18n="country_slovenia" value='slovenia'><s:message code="country_slovenia"/></form:option>
									<form:option data-i18n="country_spain" value='spain'><s:message code="country_spain"/></form:option>
									<form:option data-i18n="country_sweden" value='sweden'><s:message code="country_sweden"/></form:option>
									<form:option data-i18n="country_united_kingdom" value='united_kingdom'><s:message code="country_united_kingdom"/></form:option>
								</form:select>
							</div>
						</div>
					</div>

					<div class="col-md-6">
						<div class="form-group ">
							<label class="control-label col-md-4 " for="name"><span data-i18n="createca_contact_person"><s:message code="createca_contact_person"/></span></label>
							<div class="col-md-8">
								<form:input cssClass="form-control" path="contactPerson" placeholder="Enter contact person"/>
							</div>
						</div>
						<div class="form-group ">
							<label class="control-label col-md-4 " for="name"><span data-i18n="createca_email"><s:message code="createca_email"/></span></label>
							<div class="col-md-8">
								<form:input cssClass="form-control" path="email" placeholder="Enter E-mail"/>
							</div>
						</div>
						<%--
						<div class="form-group">
							<label class="control-label col-md-4 " for="name"><span data-i18n="createca_confirm_email"><s:message code="createca_confirm_email"/></span></label>
							<div class="col-md-8">
								<input class="form-control" placeholder="Confirm E-mail">
							</div>
						</div>
						 --%>
						<div class="form-group ">
							<label class="control-label col-md-4 " for="name"><span data-i18n="createca_telephone"><s:message code="createca_telephone"/></span></label>
							<div class="col-md-8">
								<form:input cssClass="form-control" path="telephone" placeholder="Enter Telephone"/>
							</div>
						</div>
						<%--
						<div class="form-group ">
							<label class="control-label col-md-4 " for="name"><span data-i18n="createca_confirm_telephone"><s:message code="createca_confirm_telephone"/></span></label>
							<div class="col-md-8">
								<input type="text" class="form-control" placeholder="Confirm Telephone">
							</div>
						</div>
						 --%>
						<div class="form-group ">
							<label class="control-label col-md-4 " for="name"><span data-i18n="createca_website"><s:message code="createca_website"/></span></label>
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
			<span data-i18n="createca_info_procurement_proc"><s:message code="createca_info_procurement_proc"/></span>
			</div>
			<div id="ppdiv" class="collapse in">
			<div class="panel-body">
				<div class="row">
					<div class="col-md-12">
						<div class="alert alert-espd-info" style="border: 1px dotted blue; background-color: #D8D8D8;">
							<span data-i18n="createca_to_be_filled_alert"><s:message code="createca_to_be_filled_alert"/></span>
						</div>
					
						<div class="form-group col-md-12">
							<label class="control-label" for="name"><span data-i18n="createca_title_or_short_desc_"><s:message code="createca_title_or_short_desc_"/></span></label>
							<form:textarea path="procedureDesc" cssStyle="resize: none" rows="4" cols="20" cssClass="form-control"/>
						</div>
		
						<div class="form-group col-md-12">
							<label class="control-label col-md-8 " for="name"><span data-i18n="createca_lots_concerned"><s:message code="createca_lots_concerned"/></span></label>
							<div class="col-md-4">
								<form:input cssClass="form-control" path="lotConcerned" placeholder=""/>
							</div>
						</div>
						<div class="form-group col-md-12">
							<label class="control-label col-md-8 " for="name"><span data-i18n="createca_file_ref_ca"><s:message code="createca_file_ref_ca"/></span></label>
							<div class="col-md-4">
								<form:input cssClass="form-control" path="fileRefByCA" placeholder=""/>
							</div>
						</div>
						<div class="form-group col-md-12">
							<label class="control-label col-md-8 " for="name"><span data-i18n="createca_website_proc_doc"><s:message code="createca_website_proc_doc"/></span></label>
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
	
	<div class="col-md-7">&nbsp;</div>
	<div class="col-md-5">
		<div class="btn-group">
			<a class="btn btn-default btn-lg" href="${pageContext.request.contextPath}/filter">
				<i class="fa fa-arrow-circle-o-left"></i>
				<span data-i18n="previous"><s:message code="previous"/></span>
			</a>
			<a class="btn btn-default btn-lg" href="${pageContext.request.contextPath}/welcome">
				<i class="fa fa-times-circle"></i>
				<span data-i18n="cancel"><s:message code="cancel"/></span>
			</a>
			<button type="submit" class="btn btn-default btn-lg">
				<i class="fa fa-arrow-circle-o-right"></i>
				<span data-i18n="next"><s:message code="next"/></span>
			</button>
		</div>
	</div>    
	
	</form:form>
</div>


