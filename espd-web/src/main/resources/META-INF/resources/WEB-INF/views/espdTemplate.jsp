<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%
	eu.europa.ec.grow.espd.util.I18NFunc inst = new eu.europa.ec.grow.espd.util.I18NFunc(pageContext);
	request.setAttribute("i18n", inst.message());
	request.setAttribute("div18n", inst.div());
	request.setAttribute("span18n", inst.span());
%>
		
<!DOCTYPE HTML>

<tiles:importAttribute name="agent"/>
<tiles:importAttribute name="flow"/>

<html>
    <head>
		<meta charset="UTF-8"/>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="European Single Procurement Document (ESPD) is a self-declaration of the businesses' financial status, abilities and suitability for a public procurement procedure. It is used as a preliminary evidence of fulfilment of the exclusion and eligibility criteria required in EU public procurement procedures. The tenderers no longer have to provide full documentary evidence and different forms, which means a significant simplification of access to cross-border tendering opportunities. The European Commission provides a free online service for the buyers, bidders and other parties interested in filling in the ESPD electronically.">
		<meta name="keywords" content=" European Single Procurement Document, ESPD, public, procurement, e-procurement, electronic procurement, tender, web service, online service, form, self-declaration, proof, evidence, exclusion, eligibility, selection, criteria, public buyer, contracting authority, bidder, tenderer, economic operator, Europe, European Union, European Commission"/>
		<meta name="author" content="European Commission, DG GROW, DG Internal Market">
		<title>ESPD</title>
		
		<link rel="icon" type="image/png" href="data:image/png;base64,iVBORw0KGgo=">
		<link rel="stylesheet" type="text/css" href="<s:url value="/static/bundle/all.css"/>">
		<script src="<s:url value="/static/bundle/all.js"/>"></script>
		
		<s:eval var="ecertisCriterionURL" scope="page" expression="@espdConfiguration.ecertisCriterionURL" />

		<script>
			var pageLanguage = "${pageContext.response.locale}".toLowerCase();
			
			$(function () {
				validator(defaultValidators, "required", "${i18n['validator_required']}");
				validator(defaultValidators, "number", "${i18n['validator_number']}");
				validator(defaultValidators, "digits", "${i18n['validator_digits']}");
				validator(defaultValidators, "url", "${i18n['validator_url']}");
	
				jQuery.extend(jQuery.validator.messages, defaultValidators);
			            
				$("input.datepicker").datepicker({format: "dd-mm-yyyy", clearBtn: true, todayHighlight: true});
				$("span[data-toggle='tooltip']").tooltip({placement: "top", html: true, trigger: "hover"}).addClass("fa").addClass("fa-info-circle");
				$("input:radio[data-target-show]").change(dataShow);
				$("input:radio[data-target-hide]").change(dataHide);
				sortDropdowns();

				$('.ecertis-link-header').click(function() {
					var url = "${ecertisCriterionURL}";
					var country = "${espd.authority.country.iso2Code}";
					
				   	var uuid = $(this).attr("data-uuid");
				   	if($(this).hasClass( "collapsed" ) && uuid != "") {

				   		var content = $(this).attr("data-target");
				    	$(content).find("#content, #issued, #ecertis404").hide();
				    	$(content).children("#loading").show();
					    	
				    	$.getJSON(url.replace("[uuid]",uuid).replace("[country]",country.toLowerCase()).replace("[lang]",pageLanguage),
				    		function( data ) {
								$(content).children("#loading").hide();
								
								if(data && data.DomainID == "eproc" && data.hasOwnProperty("SubCriterion")) {
									content = $(content).children("#content").show();
									$(content).find("#language").html(data.Name.languageID.toUpperCase());
									
									var T = $(content).find("#template").hide();
									$(T).siblings("#subcriterion").remove();

									if(data.hasOwnProperty("SubCriterion")) {
										$.each( data.SubCriterion, function( key, val ) {
											var item = T.clone().attr("id","subcriterion").appendTo(T.parent()).show();
											var list = item.children("#evidencesFound").html("");
				    						item.find("#evidencesFound, #evidencesNotFound").hide();
											item.children("#subname").html(val.Name.value);

											//Currently display only first LegislationReference from array, in future could be more
											item.find("#description").html(val.LegislationReference[0].Title.value);
											item.find("#url").text(val.LegislationReference[0].Article.value).attr("href",data.LegislationReference[0].URI);

											var hasEvidences = false;
											$.each( $(val.RequirementGroup), function( key, val ) {
												$.each( $(val.TypeOfEvidence), function( key, val ) {
													var names = [];
													$.each( $(val["EvidenceIssuerParty"]), function( key, val ) {
														$.each($(val["PartyName"]), function(i,val) { names.push(val.Name.value) });
													})

													// EvidenceDocumentReference with evidence URL is an array with only one element,
													// currently it is implemented as it is, but in future could be more than one
													var evidenceURL = val.EvidenceDocumentReference[0].Attachment.ExternalReference.URI;
													var evidence = T.find("#evidence").clone().appendTo(list);
													evidence.find("#name").text(val.Name.value).attr("href", evidenceURL);
													evidence.find("#issued").toggle(names.length != 0).children("#issuerNames").text(names.join(","));

													hasEvidences = true;
												});
											});
											
											item.children(hasEvidences?"#evidencesFound":"#evidencesNotFound").show();
										});
									}
									
								}
								else {
					   				$(content).find("#ecertis404").show();
								}
					   		}).fail(function() {
					   			$(content).children("#loading").hide();
					   			$(content).find("#ecertis404").show();
							}
					   	);
				    }
				});
				
			});
		</script>
    </head>
    <body>
    	<div class="container panel" style="padding:0; border-color: #396ea2 !important;">
	        <div id="header">
	            <tiles:insertAttribute name="header"/>
	            <tiles:insertAttribute name="breadcrumb"/>
	        </div>
	        <div id="body" class="container espd-container">
	            <tiles:insertAttribute name="body">
	            	<tiles:putAttribute name="agent" value="${agent}"/>
	            	<tiles:putAttribute name="flow" value="${flow}"/>
	            </tiles:insertAttribute>
	        </div>
		</div>
        <div id="footer">
            <tiles:insertAttribute name="footer"/>
        </div>
        <link rel="stylesheet" type="text/css" href="<s:url value="/static/font-awesome-4.2.0/css/font-awesome.min.css"/>">
    </body>
</html>