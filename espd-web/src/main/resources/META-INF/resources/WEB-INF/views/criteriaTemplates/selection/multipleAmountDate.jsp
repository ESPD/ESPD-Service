<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:importAttribute name="field"/>

<c:forEach var="group" items="${espd[field].unboundedGroups}" varStatus="vs" >
	
	<a name="${field}${vs.index}"></a>
	
	<div class="form-group form-group-sm">
        <div class="col-xs-3">
            <label class="control-label small">${span18n['crit_amount']}</label>
        </div>
		<div class="col-xs-6">
			<form:input type="text" path='${field}.unboundedGroups[${vs.index}]["amount"]' number="true" cssClass="form-control small" />
		</div>
		<div class="col-xs-3">
	        <tiles:insertDefinition name="currencies">
	            <tiles:putAttribute name="currencyField" value='${field}.unboundedGroups[${vs.index}]["currency"]'/>
	            <tiles:putAttribute name="style" value="border-radius: 0;"/>
	        </tiles:insertDefinition>
		</div>
    </div>
    
	<div class="form-group form-group-sm">
        <div class="col-xs-3">
            <label class="control-label small">${span18n['crit_start_date']}</label>
        </div>
		<div class="col-xs-3">
			<form:input type="text" path='${field}.unboundedGroups[${vs.index}]["startDate"]' cssClass="form-control datepicker" cssStyle="border-radius: 0;"/>
		</div>
        <div class="col-xs-3">
            <label class="control-label small">${span18n['crit_end_date']}</label>
        </div>
        <div class="col-xs-3">
            <form:input type="text" path='${field}.unboundedGroups[${vs.index}]["endDate"]' cssClass="form-control datepicker" cssStyle="border-radius: 0;"/>
        </div>
    </div>
    
	<div class="form-group">
		<div class="btn-group pull-right hidden-print">
			<c:if test="${vs.last}">
				<button id="add_${field}" type="submit" class="btn btn-default btn-sm" name="add_${field}" value="${vs.index + 1}" data-toggle="tooltip" title="Add">
				<i class="fa fa-plus" aria-hidden="true"></i>
				</button>
			</c:if>
			<button id="remove_${field}" type="submit" class="btn btn-default btn-sm " name="remove_${field}" value="${vs.index}" data-toggle="tooltip" title="Delete">
				<i class="fa fa-trash" aria-hidden="true"></i>
			</button>
		</div>
	</div>
	
	<hr>
</c:forEach>
