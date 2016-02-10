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
		<meta name="description" content="ESPD is an information system that will aid in the process of creating an electronic file used throughout the tendering process in the EU and will reduce the administrative burden by reusing key information on the Contracting Authority, Economical Operator, criteria and evidence description.">
		<meta name="keywords" content="espd, public, procurement, europa, europe, european commission"/>
		<meta name="author" content="European Commission">
		<title>ESPD</title>
		
		<link rel="icon" type="image/png" href="data:image/png;base64,iVBORw0KGgo=">
		<link rel="stylesheet" type="text/css" href="<s:url value="/static/css/all.css"/>">
		<script src="<s:url value="/static/js/all.js"/>"></script>
		
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