<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:importAttribute name="field"/>
<tiles:importAttribute name="address"/>
<tiles:importAttribute name="vatNumber"/>
<tiles:importAttribute name="contacts"/>

<c:if test="${address}">
	<div class=" form-group">
		<label class="control-label col-md-4" data-i18n="createca_street_and_num"><s:message code="createca_street_and_num"/></label>
		<div class="col-md-8">
			<form:input cssClass="form-control" path="${field}.street" id="streetAndNumber" placeholder="${i18n['createca_street_and_num_placeholder']}" data-i18n="createca_street_and_num_placeholder"/>
		</div>
	</div>
	<div class=" form-group">
		<label class="control-label col-md-4" data-i18n="createca_postcode"><s:message code="createca_postcode"/></label>
		<div class="col-md-8">
			<form:input cssClass="form-control" path="${field}.postalCode" placeholder="${i18n['createca_postcode_placeholder']}" data-i18n="createca_postcode_placeholder"/>
		</div>
	</div>
	<div class=" form-group">
		<label class="control-label col-md-4" data-i18n="createca_city"><s:message code="createca_city"/></label>
        <div class="col-md-8">
			<form:input cssClass="form-control" path="${field}.city" placeholder="${i18n['createca_city_placeholder']}" data-i18n="createca_city_placeholder"/>
		</div>
	</div>
	<div class=" form-group ">
		<label class="control-label col-md-4" data-i18n="createca_country"><s:message code="createca_country"/></label>
		<div class="col-md-8">
			<tiles:insertDefinition name="countries">
				<tiles:putAttribute name="field" value="${field}.country"/>
			</tiles:insertDefinition>
		</div>
	</div>
</c:if>
<c:if test="${vatNumber}">
	<div class="form-group">
		<label class="control-label col-md-4" data-i18n="createeo_vat"><s:message code="createeo_vat"/></label>
		<div class="col-md-8">
			<form:input cssClass="form-control" path="${field}.vatNumber" placeholder="${i18n['createeo_vat_placeholder']}" data-i18n="createeo_vat_placeholder"/>
		</div>
	</div>
</c:if>
<c:if test="${contacts}">
	<div class="form-group">
		<label class="control-label col-md-4" data-i18n="createca_email"><s:message code="createca_email"/></label>
		<div class="col-md-8">
			<form:input cssClass="form-control" path="${field}.contactEmail" placeholder="${i18n['createca_email_placeholder']}" data-i18n="createca_email_placeholder"/>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-md-4" data-i18n="createca_telephone"><s:message code="createca_telephone"/></label>
		<div class="col-md-8">
			<form:input cssClass="form-control" path="${field}.contactPhone" placeholder="${i18n['createca_telephone_placeholder']}" data-i18n="createca_telephone_placeholder"/>
		</div>
	</div>
</c:if>