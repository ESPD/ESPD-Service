<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row" style="border: 1px solid lightgray;margin-right: 20px; margin-left: 20px; margin-bottom: 5px;">
    <div class="col-md-5" style="border-right: 1px solid lightgray; padding-top: 5px;">
        <div class="form-group">
            <div class="col-md-12">
                <strong>${number}. </strong>
                <strong data-i18n="${title_code}">
                    <s:message code='${title_code}'/>
                </strong>
                <c:if test="${tooltip_code != ''}">
                    <s:message var="tooltip_text" code='${tooltip_code}'/>
                    <span data-i18n="${tooltip_code}" data-toggle="tooltip" title="${tooltip_text}"></span>
                </c:if>
            </div>
        </div>
    </div>
    <div class="col-md-7" style="border-left: 1px solid lightgray; padding:20px; left: -1px;  padding-bottom: 0px;">
        <div class="col-md-12">
            <div class="form-group">
                <label class="control-label small" style="padding-top: 0px;" for="${field}-field6"
                       data-i18n="crit_your_answer">
                    <s:message code='crit_your_answer'/>
                </label>
                <form:checkbox path="${field}.exists" id="${field}-field6" data-toggle="collapse"
                               data-target="${'#'}${field}-form" class="radioslide checktoggle form-control"/>
            </div>
        </div>
        <div class="col-md-12" id="${field}-form" style="display:none">
            <div class="form-group">
                <div class="form-group">
                    <label class="control-label col-md-4 small" for="${field}-field1"
                           data-i18n="crit_date_of_conviction"><s:message code='crit_date_of_conviction'/></label>

                    <div class="col-md-8">
                        <s:message code="crit_date_of_conviction_placeholder" var="dateOfconvictionPlaceholder"/>
                        <form:input path="${field}.dateOfConviction" cssClass="form-control datepicker"
                                    id="${field}-field1" placeholder="${dateOfconvictionPlaceholder}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-4 small" for="${field}-field2" data-i18n="crit_reason"><s:message
                            code='crit_reason'/></label>

                    <div class="col-md-8">
                        <s:message code="crit_reason_placeholder" var="reasonPlaceholder"/>
                        <form:textarea path="${field}.reason" cssClass="form-control" id="${field}-field2"
                                       placeholder="${reasonPlaceholder}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-4 small" for="${field}-field3"
                           data-i18n="crit_who_convicted"><s:message code='crit_who_convicted'/></label>

                    <div class="col-md-8">
                        <s:message code="crit_who_convicted_placeholder" var="whoConvictedPlaceholder"/>
                        <form:textarea path="${field}.convicted" cssClass="form-control" id="${field}-field3"
                                       placeholder="${whoConvictedPlaceholder}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-4 small" for="${field}-field4"
                           data-i18n="crit_length_period_exclusion"><s:message
                            code='crit_length_period_exclusion'/></label>

                    <div class="col-md-8">
                        <s:message code="crit_length_period_exclusion_placeholder" var="periodLengthPlaceholder"/>
                        <form:input path="${field}.periodLength" class="form-control" id="${field}-field4"
                                    placeholder="${periodLengthPlaceholder}"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-12">
                        <label class="control-label small" for="${field}-field5"
                               data-i18n="crit_taken_self_cleaning_measures"><s:message
                                code='crit_taken_self_cleaning_measures'/></label>
                        <input type="checkbox" id="${field}-field5" data-toggle="collapse"
                               data-target="${'#'}${field}-reliability" class="radioslide checktoggle"/>
                    </div>
                </div>
                <div class="tab-pane" id="${field}-reliability" style="display:none">
                    <div class="form-group">
                        <label class="control-label col-md-4 small" for="${field}-field6"
                               data-i18n="crit_please_describe_them"><s:message
                                code='crit_please_describe_them'/></label>

                        <div class="col-md-8">
                            <s:message code="crit_please_describe_them_placeholder" var="describePlaceholder"/>
                            <textarea class="form-control" id="${field}-field6"
                                      placeholder="${describePlaceholder}"></textarea>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-12">
            <div class="form-group">
                <label class="control-label small" for="${field}-field6"
                       data-i18n="crit_information_available_electronically"><s:message
                        code="crit_information_available_electronically"/></label>
                <input type="checkbox" id="${field}-field6" data-toggle="collapse"
                       data-target="#${field}-electronically" class="radioslide checktoggle form-control"/>
            </div>
        </div>
        <div class="col-md-12" id="${field}-electronically" style="display:none">
            <div class="form-group" style="">
                <label class="control-label col-md-2 small" for="${field}-field6" data-i18n="crit_url"><s:message
                        code="crit_url"/></label>

                <div class="col-md-5">
                    <s:message code="crit_url_placeholder" var="urlPlaceholder"/>
                    <input type="text" class="form-control input-sm" id="${field}-field6"
                           placeholder="${urlPlaceholder}"/>
                </div>
                <label class="control-label col-md-1 small" for="${field}-field7" data-i18n="crit_code"><s:message
                        code="crit_code"/></label>

                <div class="col-md-4">
                    <s:message code="crit_code_placeholder" var="codePlaceholder"/>
                    <input type="text" class="form-control input-sm" id="${field}-field7"
                           placeholder="${codePlaceholder}"/>
                </div>
            </div>
        </div>
    </div>

</div>

