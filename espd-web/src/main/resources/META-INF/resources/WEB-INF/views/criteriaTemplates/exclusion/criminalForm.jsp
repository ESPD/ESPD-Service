<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<tiles:importAttribute name="field"/>
<c:forEach var="group" items="${espd[field].unboundedGroups}" varStatus="vs" >
	<a name="${field}${vs.index}"></a>
	<div class="form-group">
	    <label class="control-label col-md-3 small" data-i18n="crit_date_of_conviction"><s:message code='crit_date_of_conviction'/></label>
	    <div class="col-md-9">
	        <form:input type="text" path='${field}.unboundedGroups[${vs.index}]["dateOfConviction"]' cssClass="form-control datepicker"/>
	    </div>
	</div>
	<div class="form-group">
	    <label class="control-label col-md-3 small" data-i18n="crit_reason"><s:message
	            code='crit_reason'/></label>
	    <div class="col-md-9">
	        <form:textarea path='${field}.unboundedGroups[${vs.index}]["reason"]' cssClass="form-control" />
	    </div>
	</div>
	<div class="form-group">
	    <label class="control-label col-md-3 small" for="${field}-field3" data-i18n="crit_who_convicted">
	            ${span18n["crit_who_convicted"]}
	    </label>
	
	    <div class="col-md-9">
	        <form:textarea path='${field}.unboundedGroups[${vs.index}]["convicted"]' cssClass="form-control"/>
	    </div>
	</div>
	<div class="form-group">
	    <label class="control-label col-md-3 small" data-i18n="crit_length_period_exclusion"><s:message code='crit_length_period_exclusion'/></label>
	    <div class="col-md-9">
	        <form:textarea rows="1" path='${field}.unboundedGroups[${vs.index}]["periodLength"]' class="form-control"/>
	    </div>
	</div>
	<div class="form-group">
		${span18n["crit_taken_self_cleaning_measures"]}
		<form:radiobutton path='${field}.unboundedGroups[${vs.index}].subIndicatorAnswer' value="true" data-target-show="${'#'}${field}-self-cleaning-${vs.index}"/>${span18n["yes"]}
		<form:radiobutton path='${field}.unboundedGroups[${vs.index}].subIndicatorAnswer' value="false" data-target-hide="${'#'}${field}-self-cleaning-${vs.index}"/>${span18n["no"]}
	</div>
	<div id="${field}-self-cleaning-${vs.index}" class="tab-pane ${espd[field].unboundedGroups[vs.index].subIndicatorAnswer ? '' : 'collapse'}">
		<div class="form-group">
			<label class="control-label col-md-3 small">
				${span18n["crit_please_describe_them"]}
			</label>
			<div class="col-md-9">
				<form:textarea path='${field}.unboundedGroups[${vs.index}]["selfCleaningDescription"]' cssClass="form-control"/>
			</div>
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
	<hr/>
</c:forEach>