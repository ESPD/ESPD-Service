<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:importAttribute name="field"/>
<tiles:importAttribute name="address"/>
<tiles:importAttribute name="vat"/>
<tiles:importAttribute name="contacts"/>

<c:if test="${address}">
	<div class=" form-group">
		<label class="control-label col-md-4" data-i18n="createca_street_and_num"><s:message code="createca_street_and_num"/></label>
		<div class="col-md-8">
			<form:input cssClass="form-control" path="${field}.street" id="streetAndNumber" placeholder="Enter street and number"/>
		</div>
	</div>
	<div class=" form-group">
		<label class="control-label col-md-4" data-i18n="createca_postcode"><s:message code="createca_postcode"/></label>
		<div class="col-md-8">
			<form:input cssClass="form-control" path="${field}.postalCode" id="postcode" placeholder="Enter Postcode"/>
		</div>
	</div>
	<div class=" form-group">
		<label class="control-label col-md-4" data-i18n="createca_city"><s:message code="createca_city"/></label>
        <div class="col-md-8">
			<form:input cssClass="form-control" path="${field}.city" id="city" placeholder="Enter City"/>
		</div>
	</div>
	<div class=" form-group ">
		<label class="control-label col-md-4" data-i18n="createca_country"><s:message code="createca_country"/></label>
		<div class="col-md-8">
			<tiles:insertDefinition name="countries">
				<tiles:putAttribute name="countryField" value="${field}.country"/>
				<tiles:putAttribute name="countryHtmlId" value="country"/>
				<tiles:putAttribute name="countryCssClass" value="form-control"/>
			</tiles:insertDefinition>
		</div>
	</div>
</c:if>
<c:if test="${vat}">
	<div class="form-group">
		<label class="control-label col-md-4" data-i18n="createeo_vat"><s:message code="createeo_vat"/></label>
		<div class="col-md-8">
			<form:input cssClass="form-control" path="${field}.vat" placeholder="VAT"/>
		</div>
	</div>
</c:if>
<c:if test="${contacts}">
	<div class="form-group">
		<label class="control-label col-md-4" data-i18n="createca_email"><s:message code="createca_email"/></label>
		<div class="col-md-8">
			<form:input cssClass="form-control" path="${field}.contactEmail" id="contactEmail" placeholder="Enter E-mail"/>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-md-4" data-i18n="createca_telephone"><s:message code="createca_telephone"/></label>
		<div class="col-md-8">
			<form:input cssClass="form-control" path="${field}.contactPhone" id="contactPhone" placeholder="Enter Telephone"/>
		</div>
	</div>
</c:if>