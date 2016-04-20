<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:importAttribute name="field"/>

<s:message code="crit_description_placeholder" var="descriptionPlaceholder"/>
<s:message code="crit_ratio_placeholder" var="ratioPlaceholder"/>

<c:forEach begin="1" end="5" varStatus="loop">
	<div class="form-group">
		<div class="col-md-4">
		    <label class="control-label col-md-3 small">${span18n['crit_ratio']}</label>
		    <div class="col-md-9">
		        <form:input path="${field}.ratio${loop.index}" number="true" cssClass="form-control" data-i18n="crit_ratio_placeholder" placeholder="${ratioPlaceholder}"/>
		    </div>
	    </div>
		<div class="col-md-8">
		    <label class="control-label col-md-3 small">${span18n['crit_description']}</label>
		    <div class="col-md-9">
		        <form:input path="${field}.description${loop.index}" cssClass="form-control" data-i18n="crit_description_placeholder" placeholder="${descriptionPlaceholder}"/>
		    </div>
	    </div>
	</div>
</c:forEach>
