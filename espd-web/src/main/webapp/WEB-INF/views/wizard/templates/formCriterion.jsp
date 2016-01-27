<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:importAttribute name="field"/>
<tiles:importAttribute name="title_code"/>
<tiles:importAttribute name="tooltip_code"/>
<tiles:importAttribute name="description_code"/>
<tiles:importAttribute name="descriptionField"/>

<tiles:importAttribute name="selfCleaning"/>
<tiles:importAttribute name="breachOfObligations"/>
<tiles:importAttribute name="availableElectronically"/>

<div class="row" style="border: 1px solid lightgray; margin-right: 5px; margin-left: 0px; margin-bottom: 5px;">
    <div class="col-md-5"
         style="border-right: 1px solid lightgray; padding-top: 5px;padding-left: 5px; padding-top: 7px;">
        <div class="form-group">
            <div class="col-md-12">
                <strong data-i18n="${title_code}">
                    <s:message code='${title_code}'/>
                </strong>
                <c:if test="${tooltip_code != ''}">
                    <s:message var="tooltip_text" code='${tooltip_code}'/>
                    <span data-i18n="${tooltip_code}" data-toggle="tooltip" title="${tooltip_text}"></span>
                </c:if>
            </div>
            <c:if test="${not empty description_code}">
                <div class="col-md-12">
                    <s:message var="description_text" code='${description_code}'/>
                    <span class="small" data-i18n="${description_code}">${description_text}</span>
                </div>
            </c:if>
        </div>
    </div>
    <div class="col-md-7" style="border-left: 1px solid lightgray; padding:20px; left: -1px;  padding-bottom: 0px;">

        <div class="col-md-12">
            <div class="form-group">
                <label class="control-label small" style="padding-top: 0px;" for="${field}-field6">
                    <s:message code="crit_your_answer"></s:message>
                </label>
                <form:checkbox path="${field}.answer" data-target="${'#'}${field}-form"
                               cssClass="${yesByDefault?'radioslide-yesByDefault':''} radioslide checktoggle form-control"/>
            </div>
        </div>

        <div class="col-md-12" id="${field}-form" style="display:none">
            <c:if test="${descriptionField != ''}">
                <tiles:insertAttribute name="form">
                    <tiles:putAttribute name="field" value="${field}"/>
                    <tiles:putAttribute name="descriptionField" value="${descriptionField}"/>
                </tiles:insertAttribute>
            </c:if>
            <c:if test="${breachOfObligations}">
                <tiles:insertDefinition name="breachOfObligations">
                    <tiles:putAttribute name="field" value="${field}"/>
                </tiles:insertDefinition>
            </c:if>
            <c:if test="${selfCleaning}">
                <tiles:insertDefinition name="selfCleaning">
                    <tiles:putAttribute name="field" value="${field}"/>
                </tiles:insertDefinition>
            </c:if>
        </div>
        <div class="col-md-12">
            <c:if test="${availableElectronically}">
                <tiles:insertDefinition name="availableElectronically">
                    <tiles:putAttribute name="field" value="${field}"/>
                </tiles:insertDefinition>
            </c:if>
        </div>
    </div>
</div>


