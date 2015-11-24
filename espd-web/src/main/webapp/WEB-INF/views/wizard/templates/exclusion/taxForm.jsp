<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:importAttribute name="field"/>

<div class="form-group">
	<label class="control-label col-md-4 small" for="${field}-field-country">Country or Member State concerned</label>
	<div class="col-md-8">
        <tiles:insertDefinition name="countries">
            <tiles:putAttribute name="countryField" value="${field}.country"/>
            <tiles:putAttribute name="countryHtmlId" value="country"/>
            <tiles:putAttribute name="countryCssClass" value="form-control"/>
        </tiles:insertDefinition>
	</div>
</div> 
<div class="form-group">
	<label class="control-label col-md-4 small" for="${field}-field-amount">Amount concerned</label>
	<div class="col-md-5">
		<form:input path="${field}.amount" cssClass="form-control" id="${field}-field-amount" placeholder="Enter amount"/>
	</div>
	<div class="col-md-3">
		<form:select id="${field}-field-country" path="${field}.currency" cssClass="form-control">
			<form:option value='euro'>EUR</form:option>
			<form:option value='dollar'>USD</form:option>
		</form:select>
	</div>
</div>

