<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:importAttribute name="field"/>
<tiles:importAttribute name="lastYears"/>

<c:forEach var="count" items="${lastYears}" varStatus="loop">
	<div class="form-group">
		<div class="col-md-5">
		    <label class="control-label col-md-3 small">${span18n['crit_year']}</label>
		    <div class="col-md-9">
		        <form:select path="${field}.year${loop.count}" cssClass="form-control" >
					<form:option value="${null}" label="---"/>
					<form:options items="${lastYears}"/>
				</form:select>
		    </div>
	    </div>
		<div class="col-md-7">
		    <label class="control-label col-md-3 small">${span18n['crit_number']}</label>
		    <div class="col-md-9">
		         <form:input path="${field}.number${loop.count}" digits="true" cssClass="form-control" placeholder="${i18n['crit_number_placeholder']}"/>
		    </div>
	    </div>
	</div>
</c:forEach>