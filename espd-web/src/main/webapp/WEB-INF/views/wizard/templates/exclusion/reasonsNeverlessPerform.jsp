<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:importAttribute name="field"/>
<tiles:importAttribute name="descriptionField"/>

<tiles:insertTemplate template="/WEB-INF/views/wizard/templates/simpleForm.jsp" flush="true">
	<tiles:putAttribute name="field" value="${field}" />
    <tiles:putAttribute name="descriptionField" value="${descriptionField}"/>
</tiles:insertTemplate>

<div class="form-group">
	<label class="control-label col-md-4 small" for="${field}-field-nevertheless" data-i18n="crit_reasons_nevertheless_contract"><s:message code='crit_reasons_nevertheless_contract'/> </label>
	<div class="col-md-8">
        <s:message code="crit_reasons_nevertheless_contract_placeholder" var="reasonPlaceholder"/>
		<textarea if="${field}-field-nevertheless" class="form-control" placeholder="${reasonPlaceholder}"></textarea>
	</div>
</div> 
