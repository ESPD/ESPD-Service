<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
eu.europa.ec.grow.espd.util.I18NFunc inst = new eu.europa.ec.grow.espd.util.I18NFunc(pageContext);
request.setAttribute("i18n", inst.message());
request.setAttribute("div18n", inst.div());
request.setAttribute("span18n", inst.span());
%>

<meta charset="utf-8"/>
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
