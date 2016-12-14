<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:importAttribute name="field"/>

<div class="form-group">
    <label class="control-label col-md-3 small" data-i18n="crit_date_of_conviction"><s:message code='crit_date_of_conviction'/></label>
    <div class="col-md-9">
        <form:input type="text" path="${field}.dateOfConviction" cssClass="form-control datepicker"/>
    </div>
</div>
<div class="form-group">
    <label class="control-label col-md-3 small" data-i18n="crit_reason"><s:message
            code='crit_reason'/></label>

    <div class="col-md-9">
        <form:textarea path="${field}.reason" cssClass="form-control" />
    </div>
</div>
<div class="form-group">
    <label class="control-label col-md-3 small" for="${field}-field3" data-i18n="crit_who_convicted">
            ${span18n["crit_who_convicted"]}
    </label>

    <div class="col-md-9">
        <form:textarea path="${field}.convicted" cssClass="form-control"/>
    </div>
</div>
<div class="form-group">
    <label class="control-label col-md-3 small" data-i18n="crit_length_period_exclusion"><s:message code='crit_length_period_exclusion'/></label>
    <div class="col-md-9">
        <form:textarea rows="1" path="${field}.periodLength" class="form-control"/>
    </div>
</div>

