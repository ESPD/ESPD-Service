<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:importAttribute name="field"/>

<div class="form-group">
	<label class="control-label col-md-4 small" for="${field}-field1">Date of conviction</label>
	<div class="col-md-8"> 
		<form:input path="${field}.dateOfConviction" cssClass="form-control datepicker" id="${field}-field1" placeholder="Enter date"/>
	</div>
</div> 
<div class="form-group"> 
	<label class="control-label col-md-4 small" for="${field}-field2">Reason</label>
	<div class="col-md-8"> 
		<form:textarea path="${field}.reason" cssClass="form-control" id="${field}-field2" placeholder="Enter reason"/>
	</div>
</div>
<div class="form-group">
	<label class="control-label col-md-4 small" for="${field}-field3">Who has been convicted</label>
	<div class="col-md-8"> 
		<form:textarea path="${field}.convicted" cssClass="form-control" id="${field}-field3" placeholder="Enter Who has been convicted"/>
	</div>
</div>
<div class="form-group">
	<label class="control-label col-md-4 small" for="${field}-field4">Length of the period of exclusion</label>
	<div class="col-md-8"> 
		<form:input path="${field}.periodLength" class="form-control" id="${field}-field4" placeholder="Enter Length of the period of exclusion"/>
	</div>
</div>