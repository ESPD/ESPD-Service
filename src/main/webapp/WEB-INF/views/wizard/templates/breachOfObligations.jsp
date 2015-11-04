<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:importAttribute name="field"/>

<div class="form-group">
	<label class="control-label small" for="${field}-breach-of-obligations-question">Has this breach of obligations have been established though judicial or administrative decision?</label> 
	<input type="checkbox" id="${field}-breach-of-obligations-question" data-toggle="collapse" data-target="${'#'}${field}-breach-of-obligations-form" class="radioslide checktoggle" />
</div>
<div class="tab-pane" id="${field}-breach-of-obligations-form" style="display:none">
	<div class="form-group">
		<label class="control-label small" for="${field}-breach-of-obligations-final">Is this decision final and binding?</label>
			<input type="checkbox" id="${field}-breach-of-obligations-final"/>
		
	</div>
	<div class="form-group">
		<label class="control-label col-md-4 small" for="${field}-breach-of-obligations-date">Indicate date of conviction or decision </label>
		<div class="col-md-8"> 
			<input type="text" class="form-control datepicker" id="${field}-breach-of-obligations-date-value" placeholder="Enter date"/>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-md-4 small" for="${field}-breach-of-obligations-repiod-length">Other means</label>
		<div class="col-md-8"> 
			<textarea class="form-control" id="${field}-breach-of-obligations-repiod-length" placeholder="describe"></textarea>
		</div>
	</div>
</div>
