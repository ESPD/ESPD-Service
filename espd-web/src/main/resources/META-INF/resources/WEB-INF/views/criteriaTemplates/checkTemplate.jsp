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
<tiles:importAttribute name="title_code"/>
<tiles:importAttribute name="description_code"/>
<tiles:importAttribute name="is_always_checked"/>
<tiles:importAttribute name="default_value"/>
<tiles:importAttribute name="hasCriterion"/>

<c:if test="${hasCriterion}">
	<tiles:importAttribute name="criterion"/>
</c:if>

<div class="checkbox criteria-row-check">
	<div class="form-group" style="margin-bottom: 0px;">
		<div class="col-md-12">
			<c:if test="${is_always_checked}">
				<label class="checkbox-inline" style="padding-left:0px">
					<form:hidden path="${field}.exists" value="true"/>
					<b>${span18n[title_code]}</b>
				</label>
			</c:if>
			<c:if test="${!is_always_checked}">
				<label class="checkbox-inline">
					<form:checkbox path="${field}.exists"/>
					<b>${span18n[title_code]}</b>
				</label>
			</c:if>
		    <c:if test="${not empty description_code}">
		    	<p>
		        	<span class="small" data-i18n="${description_code}"><s:message code='${description_code}'/></span>
		        </p>
		    </c:if>
    	</div>
		<c:if test="${hasCriterion == true}">
			<div class="col-md-12">
				<%@include file="/WEB-INF/views/criteriaTemplates/ecertisinfo.jsp" %>
			</div>
		</c:if>
	</div>
</div>
