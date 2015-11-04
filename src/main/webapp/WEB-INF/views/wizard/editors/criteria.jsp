<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>

<div class="checkbox">
	<label>
		<form:checkbox path="${field}.exists"/>
		<span data-i18n="${title_code}">  
			<s:message code='${title_code}'/> 
		</span>
	</label>
	<c:if test="${tooltip_code != ''}">
		<s:message var="tooltip_text" code='${tooltip_code}'/>
		<span data-i18n="${tooltip_code}" data-toggle="tooltip" title="${tooltip_text}"></span>
	</c:if>
</div>
<c:if test="${description_code != ''}">
    <span class="small" data-i18n="${description_code}"><s:message code='${description_code}'/></span>
</c:if>
<c:set var="description_code" value="" scope="session"/>
