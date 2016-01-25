<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:importAttribute name="field"/>
<tiles:importAttribute name="lastYears"/>

<c:forEach var="count" items="${lastYears}" varStatus="loop">
	<div class="form-group">
		<div class="col-md-4" style="padding-right: 0px;">
		    <label class="control-label col-md-3 small">${span18n['crit_year']}</label>
		    <div class="col-md-9" style="padding-right: 0px;">
		        <form:select path="${field}.year${loop.count}" cssClass="form-control" >
					<form:option value="${null}" label="---"/>
					<form:options items="${lastYears}"/>
				</form:select>
		    </div>
	    </div>
		<div class="col-md-6" style="padding-right: 1px;">
		    <label class="control-label col-md-4 small">${span18n['crit_amount_concerned']}</label>
		    <div class="col-md-8" style="padding: 0px;">
		         <form:input path="${field}.amount${loop.count}" number="true" cssClass="form-control" placeholder="${i18n['crit_amount_concerned_placeholder']}"/>
		    </div>
	    </div>
		<div class="col-md-2" style="padding: 0px 0px 0px 5px;">
            <tiles:insertDefinition name="currencies">
                <tiles:putAttribute name="currencyField" value="${field}.currency${loop.count}"/>
            </tiles:insertDefinition>
	    </div>
	</div>
</c:forEach>
