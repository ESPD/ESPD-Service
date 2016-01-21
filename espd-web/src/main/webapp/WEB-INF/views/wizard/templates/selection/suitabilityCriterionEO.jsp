<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:importAttribute name="field"/>
<tiles:importAttribute name="number"/>
<tiles:importAttribute name="title_code"/>
<tiles:importAttribute name="tooltip_text"/>
<tiles:importAttribute name="tooltip_code"/>

<div class="row criteria-row">
    <div class="col-md-5 criteria-cell-left">
        <div class="form-group">
            <div class="col-md-12">
                <strong data-i18n="${title_code}">
                    <s:message code='${title_code}'/>
                </strong>
            </div>
            <div class="col-md-12">
                <c:if test="${not empty tooltip_code}">
                    <s:message var="tooltip_text" code='${tooltip_code}'/>
                    <span data-i18n="${tooltip_code}">${tooltip_text}</span>
                </c:if>
            </div>
        </div>
    </div>
    <div class="col-md-7 criteria-cell-right">
        <div class="col-md-12">
            <div class="form-group">
                <label class="control-label small" style="padding-top: 0px;" >
                    <s:message code='crit_your_answer'/>
                </label>
                <form:checkbox path="${field}.exists" data-toggle="collapse" data-target="${'#'}${field}-form" class="radioslide checktoggle form-control" />
            </div>
        </div>
        <tiles:insertDefinition name="availableElectronically">
            <tiles:putAttribute name="field" value="${field}"/>
        </tiles:insertDefinition>
    </div>
</div>