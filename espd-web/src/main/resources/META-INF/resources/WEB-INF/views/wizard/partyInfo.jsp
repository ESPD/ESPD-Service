<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<%--
  ~
  ~ Copyright 2016 EUROPEAN COMMISSION
  ~
  ~ Licensed under the EUPL, Version 1.1 or – as soon they
  ~ will be approved by the European Commission - subsequent
  ~ versions of the EUPL (the "Licence");
  ~
  ~ You may not use this work except in compliance with the Licence.
  ~
  ~ You may obtain a copy of the Licence at:
  ~
  ~ https://joinup.ec.europa.eu/community/eupl/og_page/eupl
  ~
  ~ Unless required by applicable law or agreed to in
  ~ writing, software distributed under the Licence is
  ~ distributed on an "AS IS" basis,
  ~ WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
  ~ express or implied.
  ~ See the Licence for the specific language governing
  ~ permissions and limitations under the Licence.
  ~
  --%>

<tiles:importAttribute name="field"/>
<tiles:importAttribute name="address"/>
<tiles:importAttribute name="vatNumber"/>
<tiles:importAttribute name="contacts"/>

<c:if test="${address}">
	<div class=" form-group">
		<label class="control-label col-md-4" data-i18n="createca_street_and_num"><s:message code="createca_street_and_num"/></label>
		<div class="col-md-8">
			<form:textarea rows="1" cssClass="form-control" path="${field}.street" id="streetAndNumber"/>
		</div>
	</div>
	<div class=" form-group">
		<label class="control-label col-md-4" data-i18n="createca_postcode"><s:message code="createca_postcode"/></label>
		<div class="col-md-8">
			<form:textarea rows="1" cssClass="form-control" path="${field}.postalCode"/>
		</div>
	</div>
	<div class=" form-group">
		<label class="control-label col-md-4" data-i18n="createca_city"><s:message code="createca_city"/></label>
        <div class="col-md-8">
			<form:textarea rows="1" cssClass="form-control" path="${field}.city"/>
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
			<form:textarea rows="1" cssClass="form-control" path="${field}.vatNumber"/>
		</div>
	</div>
</c:if>
<c:if test="${contacts}">
	<div class="form-group">
		<label class="control-label col-md-4" data-i18n="createca_email"><s:message code="createca_email"/></label>
		<div class="col-md-8">
			<form:textarea rows="1" cssClass="form-control" path="${field}.contactEmail"/>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-md-4" data-i18n="createca_telephone"><s:message code="createca_telephone"/></label>
		<div class="col-md-8">
			<form:textarea rows="1" cssClass="form-control" path="${field}.contactPhone"/>
		</div>
	</div>
</c:if>