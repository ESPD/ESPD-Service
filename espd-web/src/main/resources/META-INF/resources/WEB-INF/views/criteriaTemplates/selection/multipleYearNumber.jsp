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
		    	<form:input type="text" path="${field}.unboundedGroups[${loop.index}]['year']" digits="true" max='<%= new java.text.SimpleDateFormat("yyyy").format(new java.util.Date()) %>' cssClass="form-control"></form:input>
		    </div>
	    </div>
		<div class="col-md-7">
		    <label class="control-label col-md-3 small">${span18n['crit_number']}</label>
		    <div class="col-md-9">
		         <form:input type="text" path="${field}.unboundedGroups[${loop.index}]['number']" digits="true" max="1000000" cssClass="form-control"/>
		    </div>
	    </div>
	</div>
</c:forEach>