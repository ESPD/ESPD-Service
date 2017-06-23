<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:importAttribute name="field"/>

<div class="form-group">
	${span18n["crit_taken_self_cleaning_measures"]}
	<form:radiobutton path="${field}.selfCleaning.answer" value="true" data-target-show="${'#'}${field}-self-cleaning"/>${span18n["yes"]}
	<form:radiobutton path="${field}.selfCleaning.answer" value="false" data-target-hide="${'#'}${field}-self-cleaning"/>${span18n["no"]}
</div>
<div id="${field}-self-cleaning" class="tab-pane ${espd[field].selfCleaning.answer ? '' : 'collapse'}">
	<div class="form-group">
		<label class="control-label col-md-3 small">
			${span18n["crit_please_describe_them"]}
		</label>
		<div class="col-md-9">
			<form:textarea path="${field}.selfCleaning.description" cssClass="form-control"/>
		</div>
	</div>
</div>
