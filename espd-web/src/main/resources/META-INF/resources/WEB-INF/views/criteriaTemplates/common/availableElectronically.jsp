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

<div class="form-group">
	${span18n["crit_information_available_electronically"]}
	<form:radiobutton path="${field}.availableElectronically.answer" value="true" data-target-show="${'#'}${field}-electronically"/>${span18n["yes"]}
	<form:radiobutton path="${field}.availableElectronically.answer" value="false" data-target-hide="${'#'}${field}-electronically"/>${span18n["no"]}		
</div>

<div id="${field}-electronically" class="form-group ${espd[field].availableElectronically.answer ? '' : 'collapse'}">
	<label class="control-label col-md-2 small">
		${span18n["crit_url"]}
	</label>
	<div class="col-md-5">
		<form:textarea rows="1" path="${field}.availableElectronically.url" class="form-control input-sm" url="true"/>
	</div>
	<label class="control-label col-md-1 small">
		${span18n["crit_code"]}
	</label>
	<div class="col-md-4">
		<form:textarea rows="1" path="${field}.availableElectronically.code" class="form-control input-sm"/>
	</div>
</div>
