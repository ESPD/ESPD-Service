<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>

<div class="checkbox" style="border: 1px solid lightgray;    margin-bottom: 5px;    padding-left: 5px;    padding-bottom: 5px;">
	<label>
		<form:checkbox path="${field}.exists"/>
		<span data-i18n="${title_code}">  
			<s:message code='${title_code}'/> 
		</span>
	</label>
	<c:if test="${not empty tooltip_code}">
		<s:message var="tooltip_text" code='${tooltip_code}'/>
		<span data-i18n="${tooltip_code}" data-toggle="tooltip" title="${tooltip_text}"></span>
	</c:if>
	
	<c:if test="${not empty description_code}">
	    <span class="small" data-i18n="${description_code}"><s:message code='${description_code}'/></span>
	</c:if>
</div>
<c:set var="description_code" value="" scope="session"/>
