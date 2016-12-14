<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:importAttribute name="field"/>

<c:forEach var="group" items="${espd[field].unboundedGroups}" varStatus="vs" >
	<a name="${field}${vs.index}"></a>
    <div class="form-group">
	    <div class="col-md-4">
			<label class="control-label col-md-3 small">${span18n['crit_ratio']}</label>
	        <div class="col-md-9">
	            <form:input type="text" path='${field}.unboundedGroups[${vs.index}]["ratio"]' number="true" cssClass="form-control"/>
	        </div>
    	</div>
	    <div class="col-md-7">
	        <label class="control-label col-md-3 small">${span18n['crit_description']}</label>
	        <div class="col-md-9">
	            <form:textarea rows="1" path='${field}.unboundedGroups[${vs.index}]["description"]' cssClass="form-control"/>
	        </div>
	    </div>
	    <div class="col-md-1">
			<div class="btn-group pull-right hidden-print">
            	<button id="remove_${field}" type="submit" class="btn btn-default btn-sm " name="remove_${field}" value="${vs.index}">
                	<i class="fa fa-trash" aria-hidden="true"></i>
            	</button>
	    	</div>
	    </div>
    </div>
	<c:if test="${vs.last}">
		<div class="form-group">
			<div class="btn-group pull-right hidden-print">
				<button id="add_${field}" type="submit" class="btn btn-default btn-sm" name="add_${field}" value="${vs.index + 1}">
				<i class="fa fa-plus" aria-hidden="true"></i>
				</button>
			</div>
		</div>
	</c:if>
</c:forEach>
<hr/>