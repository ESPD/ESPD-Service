<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:importAttribute name="field"/>

<div class="form-group">
	<div class="col-xs-3" style="padding:2px">
	    <label class="control-label col-md-3 small">${span18n['crit_description']}</label>
    </div>
	<div class="col-xs-2" style="padding:2px">
	    <label class="control-label col-md-3 small">${span18n['crit_amount']}</label>
    </div>
	<div class="col-xs-2" style="padding:2px">
	    &nbsp;
    </div>
	<div class="col-xs-2" style="padding:2px">
	    <label class="control-label col-md-3 small">${span18n['crit_date']}</label>
    </div>
	<div class="col-xs-3" style="padding:2px">
	    <label class="control-label col-md-3 small">${span18n['crit_recipients']}</label>
    </div>
</div>

<c:forEach begin="1" end="5" varStatus="loop">
	<div class="form-group form-group-sm" style="margin-bottom: 0px;">
		<div class="col-xs-3" style="padding:1px;">
			<form:input path="${field}.description${loop.index}" cssClass="form-control small" cssStyle="border-radius: 0;" placeholder="${i18n['crit_description_placeholder']}"/>
		</div>
		<div class="col-xs-2" style="padding:1px;">
			<form:input path="${field}.amount${loop.index}" number="true" cssClass="form-control small" cssStyle="border-radius: 0;padding-left: 2px; padding-right: 0px;" placeholder="${i18n['crit_amount_concerned_placeholder']}"/>
		</div>
		<div class="col-xs-2" style="padding:1px;">
	        <tiles:insertDefinition name="currencies">
	            <tiles:putAttribute name="currencyField" value="${field}.currency${loop.index}"/>
	            <tiles:putAttribute name="style" value="border-radius: 0;"/>
	        </tiles:insertDefinition>
		</div>
		<div class="col-xs-2" style="padding:1px;">
			<form:input path="${field}.date${loop.index}" cssClass="form-control datepicker" cssStyle="border-radius: 0;" placeholder="${i18n['crit_date_placeholder']}"/>
		</div>
		<div class="col-xs-3" style="padding:1px;">
			<form:input path="${field}.recipients${loop.index}" cssClass="form-control small" cssStyle="border-radius: 0;" placeholder="${i18n['crit_recipients_placeholder']}"/>
		</div>
	</div>
</c:forEach>
