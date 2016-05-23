<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
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
	${span18n["crit_taken_self_cleaning_measures"]}
	<form:radiobutton path="${field}.selfCleaning.answer" value="true" data-target-show="${'#'}${field}-reliability"/>${span18n["yes"]}
	<form:radiobutton path="${field}.selfCleaning.answer" value="false" data-target-hide="${'#'}${field}-reliability"/>${span18n["no"]}
</div>
<div id="${field}-reliability" class="tab-pane ${espd[field].selfCleaning.answer ? '' : 'collapse'}">
	<div class="form-group">
		<label class="control-label col-md-4 small">
			${span18n["crit_please_describe_them"]}
		</label>
		<div class="col-md-8">
			<form:textarea path="${field}.selfCleaning.description" cssClass="form-control" data-i18n="crit_please_describe_them_placeholder" placeholder="${i18n['crit_please_describe_them_placeholder']}"/>
		</div>
	</div>
</div>
