<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:importAttribute name="field"/>
<tiles:importAttribute name="lastYears"/>
<c:set var="currentYear" value='<%= new java.text.SimpleDateFormat("yyyy").format(new java.util.Date()) %>'/>
<c:forEach var="count" items="${lastYears}" varStatus="loop">
	<div class="form-group">
		<div class="col-md-4">
		    <label class="control-label col-xs-3 small">${span18n['crit_year']}</label>
		    <div class="col-xs-9" style="margin-bottom: 5px;">
			    <form:input type="text" path='${field}.unboundedGroups[${loop.index}]["year"]' digits="true" min="1900" max="${currentYear}" cssClass="form-control"></form:input>
		    </div>
	    </div>
	    <div class="col-md-8">
			<label class="control-label col-xs-3 small">${span18n['crit_amount_concerned']}</label>
			<div class="col-xs-5">
			    <form:input type="text" path='${field}.unboundedGroups[${loop.index}]["amount"]' number="true" cssClass="form-control"/>
			</div>
			<div class="col-xs-4">
	            <tiles:insertDefinition name="currencies">
	                <tiles:putAttribute name="currencyField" value="${field}.unboundedGroups[${loop.index}]['currency']"/>
	            </tiles:insertDefinition>
		    </div>
	    </div>
	</div>
</c:forEach>
