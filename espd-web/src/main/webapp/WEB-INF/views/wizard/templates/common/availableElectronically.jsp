<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

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
		<form:input type="text" path="${field}.availableElectronically.url" class="form-control input-sm" data-18n="crit_url_placeholder" placeholder="${i18n['crit_url_placeholder']}"/>
	</div>
	<label class="control-label col-md-1 small">
		${span18n["crit_code"]}
	</label>
	<div class="col-md-4">
		<form:input type="text" path="${field}.availableElectronically.code" class="form-control input-sm" data-18n="crit_code_placeholder" placeholder="${i18n['crit_code_placeholder']}"/>
	</div>
</div>
