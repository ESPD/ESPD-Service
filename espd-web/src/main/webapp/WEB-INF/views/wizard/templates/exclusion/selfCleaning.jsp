<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:importAttribute name="field"/>

<div class="form-group">
		<label class="control-label small" data-i18n="crit_taken_self_cleaning_measures"><s:message code='crit_taken_self_cleaning_measures'/></label>
		<form:checkbox path="${field}.selfCleaning.answer" id="${field}-field5" data-target="${'#'}${field}-reliability" cssClass="radioslide checktoggle" />
</div>
<div class="tab-pane" id="${field}-reliability" style="display:none">
	<div class="form-group">
		<label class="control-label col-md-4 small" for="${field}-field6" data-i18n="crit_please_describe_them"><s:message code='crit_please_describe_them'/></label>
		<div class="col-md-8">
            <s:message code="crit_please_describe_them_placeholder" var="describePlaceholder"/>
			<form:textarea path="${field}.selfCleaning.description" cssClass="form-control" id="${field}-field6" placeholder="${describePlaceholder}"></form:textarea>
		</div>
	</div>
</div>
