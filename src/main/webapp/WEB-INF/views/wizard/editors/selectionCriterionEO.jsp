<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row criteria-row">
    <div class="col-md-5 criteria-cell-left">
        <div class="form-group">
            <div class="col-md-12">
                <strong>${number}. </strong>
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

                <label class="control-label small" style="padding-top: 0px;" for="${field}-field6">
                    <s:message code='crit_your_answer'/>
                </label>
                <form:checkbox path="${field}.exists" id="${field}-field6" data-toggle="collapse" data-target="${'#'}${field}-form" class="radioslide checktoggle form-control" />
            </div>
        </div>

        <div class="col-md-12" id="${field}-form">
            <div class="form-group">
                <div class="tab-pane" id="${field}-reliability">
                    <div class="form-group">
                        <label class="control-label col-md-4 small" for="${field}-field6"><s:message code='crit_please_describe_them'/></label>
                        <div class="col-md-8">
                            <textarea class="form-control" id="${field}-field6" placeholder=""></textarea>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-md-12">
            <div class="form-group">
                <label class="control-label small" for="${field}-field6"><s:message code='crit_information_available_electronically'/></label>
                <input type="checkbox" id="${field}-field6" data-toggle="collapse" data-target="#${field}-electronically" class="radioslide checktoggle form-control" />
            </div>
        </div>
        <div class="col-md-12" id="${field}-electronically" style="display:none">
            <div class="form-group" style="">
                <label class="control-label col-md-2 small" for="${field}-url">Url</label>
                <div class="col-md-5">
                    <input type="text"  class="form-control input-sm" id="${field}-url"/>
                </div>

                <label class="control-label col-md-1 small" for="${field}-code-available">Code</label>

                <div class="col-md-4">
                    <input type="text" class="form-control input-sm" id="${field}-code-available" placeholder="Provide code if applicable"/>
                </div>
            </div>
        </div>
    </div>

</div>

