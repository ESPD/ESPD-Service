<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:importAttribute name="field"/>

<s:message code="crit_description_placeholder" var="descriptionPlaceholder"/>
<s:message code="crit_ratio_placeholder" var="ratioPlaceholder"/>

<div class="form-group">
    <label class="control-label col-md-4 small" for="${field}-description1"
           data-i18n="crit_description"><s:message code='crit_description'/></label>
    <div class="col-md-8">
        <form:input path="${field}.description1" cssClass="form-control" id="${field}-description1"
                    placeholder="${descriptionPlaceholder}"/>
    </div>
</div>
<div class="form-group">
    <label class="control-label col-md-4 small" for="${field}-ratio1"
           data-i18n="crit_ratio"><s:message code='crit_ratio'/></label>
    <div class="col-md-5">
        <form:input path="${field}.ratio1" cssClass="form-control" id="${field}-ratio1"
                    placeholder="${ratioPlaceholder}"/>
    </div>
</div>

<div class="form-group">
    <label class="control-label col-md-4 small" for="${field}-description2"
           data-i18n="crit_description"><s:message code='crit_description'/></label>
    <div class="col-md-8">
        <form:input path="${field}.description2" cssClass="form-control" id="${field}-description2"
                    placeholder="${descriptionPlaceholder}"/>
    </div>
</div>
<div class="form-group">
    <label class="control-label col-md-4 small" for="${field}-ratio2"
           data-i18n="crit_ratio"><s:message code='crit_ratio'/></label>
    <div class="col-md-5">
        <form:input path="${field}.ratio2" cssClass="form-control" id="${field}-ratio2"
                    placeholder="${ratioPlaceholder}"/>
    </div>
</div>

<div class="form-group">
    <label class="control-label col-md-4 small" for="${field}-description3"
           data-i18n="crit_description"><s:message code='crit_description'/></label>
    <div class="col-md-8">
        <form:input path="${field}.description3" cssClass="form-control" id="${field}-field-description3"
                    placeholder="${descriptionPlaceholder}"/>
    </div>
</div>
<div class="form-group">
    <label class="control-label col-md-4 small" for="${field}-ratio3"
           data-i18n="crit_ratio"><s:message code='crit_ratio'/></label>
    <div class="col-md-5">
        <form:input path="${field}.ratio3" cssClass="form-control" id="${field}-ratio3"
                    placeholder="${ratioPlaceholder}"/>
    </div>
</div>

<div class="form-group">
    <label class="control-label col-md-4 small" for="${field}-description4"
           data-i18n="crit_description"><s:message code='crit_description'/></label>
    <div class="col-md-8">
        <form:input path="${field}.description4" cssClass="form-control" id="${field}-description4"
                    placeholder="${descriptionPlaceholder}"/>
    </div>
</div>
<div class="form-group">
    <label class="control-label col-md-4 small" for="${field}-ratio4"
           data-i18n="crit_ratio"><s:message code='crit_ratio'/></label>
    <div class="col-md-5">
        <form:input path="${field}.ratio4" cssClass="form-control" id="${field}-ratio4"
                    placeholder="${ratioPlaceholder}"/>
    </div>
</div>

<div class="form-group">
    <label class="control-label col-md-4 small" for="${field}-description5"
           data-i18n="crit_description"><s:message code='crit_description'/></label>
    <div class="col-md-8">
        <form:input path="${field}.description5" cssClass="form-control" id="${field}-description5"
                    placeholder="${descriptionPlaceholder}"/>
    </div>
</div>
<div class="form-group">
    <label class="control-label col-md-4 small" for="${field}-ratio5"
           data-i18n="crit_ratio"><s:message code='crit_ratio'/></label>
    <div class="col-md-5">
        <form:input path="${field}.ratio5" cssClass="form-control" id="${field}-ratio5"
                    placeholder="${ratioPlaceholder}"/>
    </div>
</div>
