<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%--
  ~
  ~ Copyright 2016 EUROPEAN COMMISSION
  ~
  ~ Licensed under the EUPL, Version 1.1 or – as soon they
  ~ will be approved by the European Commission - subsequent
  ~ versions of the EUPL (the "Licence");
  ~
  ~ You may not use this work except in compliance with the Licence.
  ~
  ~ You may obtain a copy of the Licence at:
  ~
  ~ https://joinup.ec.europa.eu/community/eupl/og_page/eupl
  ~
  ~ Unless required by applicable law or agreed to in
  ~ writing, software distributed under the Licence is
  ~ distributed on an "AS IS" basis,
  ~ WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
  ~ express or implied.
  ~ See the Licence for the specific language governing
  ~ permissions and limitations under the Licence.
  ~
  --%>

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

		<script>
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
				optsort();
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
    </body>
</html>