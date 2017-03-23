<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<tiles:importAttribute name="field"/>
<hr/>
<div class="col-md-12">
    <div class="form-group form-group-sm">
        <span style="font-size: 13px;" data-i18n="crit_information_available_electronically">
        	${i18n["crit_information_available_electronically"]}
        </span>
        <form:radiobutton path="${field}.availableElectronically.answer" value="true"
                          data-target-show="${'#'}${field}-electronically"/>${span18n["yes"]}
        <form:radiobutton path="${field}.availableElectronically.answer" value="false"
                          data-target-hide="${'#'}${field}-electronically"/>${span18n["no"]}
    </div>
</div>
<div id="${field}-electronically" class="col-md-12 ${espd[field].availableElectronically.answer ? '' : 'collapse'}">
    <div class="form-group form-group-sm">
        <div class="col-xs-3">
            <label class="control-label small">${span18n["crit_url"]}</label>
        </div>
        <div class="col-xs-9">
            <form:textarea rows="1" path="${field}.availableElectronically.url" class="form-control input-sm"
                           url="true"/>
        </div>
    </div>
    <div class="form-group form-group-sm">
        <div class="col-xs-3">
            <label class="control-label small">${span18n["crit_code"]}</label>
        </div>
        <div class="col-xs-9">
            <form:textarea rows="1" path="${field}.availableElectronically.code" class="form-control input-sm"/>
        </div>
    </div>
    <div class="form-group form-group-sm">
        <div class="col-xs-3">
            <label class="control-label small">${span18n["crit_issuer"]}</label>
        </div>
        <div class="col-xs-9">
            <form:textarea rows="1" path="${field}.availableElectronically.issuer" class="form-control input-sm"/>
        </div>
    </div>
</div>

