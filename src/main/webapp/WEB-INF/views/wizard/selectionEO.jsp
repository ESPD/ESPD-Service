<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<form:form id="espdform" role="form" class="form-horizontal" method="post" commandName="espd" data-toggle="validator">
    <div class="panel-default">
        <div class="panel panel-default no-border">
            <ul class="nav nav-pills nav-wizard nav-justified">
                <li class="active"><a href="#"><i class="fa fa-random"></i>&nbsp;<span
                        data-i18n="progress_start"><s:message code='progress_start'/></span></a>
                    <div class="nav-arrow"></div>
                </li>
                <li class="active">
                    <div class="nav-wedge"></div>
                    <a href="#"><i class="fa fa-university"></i>&nbsp;<span data-i18n="progress_procedure"><s:message
                            code='progress_procedure'/></span></a>
                    <div class="nav-arrow"></div>
                </li>
                <li class="active">
                    <div class="nav-wedge"></div>
                    <a href="#"><i class="fa fa-exclamation"></i>&nbsp;<span data-i18n="progress_exclusion"><s:message
                            code='progress_exclusion'/></span></a>
                    <div class="nav-arrow"></div>
                </li>
                <li class="active">
                    <div class="nav-wedge"></div>
                    <a href="#"><i class="fa fa-check-circle"></i>&nbsp;<span data-i18n="progress_selection"><s:message
                            code='progress_selection'/></span></a>
                    <div class="nav-arrow"></div>
                </li>
                <li>
                    <div class="nav-wedge"></div>
                    <a href="#"><i class="fa fa-download"></i>&nbsp;<span data-i18n="progress_finish"><s:message
                            code='progress_finish'/></span></a></li>
            </ul>
        </div>
        <div class="paragraph">
            <h2>
                <span data-i18n="createcasel_header"><s:message code="createcasel_header"/></span>
                (${(param['agent'] == "eo")?"I am an Economic Operator":"I am a Contracting Authority"})
            </h2>
            <a href="${pageContext.request.contextPath}/selection?agent=ca">View as CA</a>
        </div>
        <div class="alert alert-espd-info">
            <ul class="fa-ul">
                <li>
                    <i class="info-label fa fa-info-circle fa-lg fa-li"></i>
                    <span data-i18n="createcasel_alert"><s:message code='createcasel_alert'/></span>
                </li>
            </ul>
        </div>
        <div class="panel panel-default espd-panel">
            <div class="espd-panel-heading" data-toggle="collapse" data-target="#eo-satisfies-all-section">
            </div>
            <div id="eo-satisfies-all-section" class="collapse in">
                <div class="espd-panel-body panel-body">
                    <div id="selectionDeclares" class="">
                        <strong data-i18n="crit_selection_eo_declares_that">
                            <s:message code='crit_selection_eo_declares_that'/>
                        </strong>
                        <span data-i18n="crit_selection_eo_declares_that_tooltip" data-toggle="tooltip"
                              title="<s:message code='crit_selection_eo_declares_that_tooltip'/>"></span>
                    </div>
                </div>
                <div class="row criteria-row">
                    <div class="col-md-5 criteria-cell-left">
                        <div class="form-group">
                            <div class="col-md-12">
                                <strong>1. </strong>
                                <strong data-i18n="crit_selection_eo_satisfies_all_criteria">
                                    <s:message code='crit_selection_eo_satisfies_all_criteria'/>
                                </strong>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-7 criteria-cell-right">
                        <div class="col-md-12">
                            <div class="form-group">

                                <label class="control-label small" style="padding-top: 0;" for="eo-satisfies-all">
                                    <s:message code='crit_your_answer'/>
                                </label>
                                <form:checkbox path="selectionSatisfiesAll.exists" id="eo-satisfies-all"
                                               data-toggle="collapse" data-target="${'#'}eo-satisfies-all-form"
                                               class="radioslide checktoggle form-control"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="panel panel-default espd-panel">
            <div data-i18n="createcasel_suitability" class="espd-panel-heading" data-toggle="collapse" data-target="#eo-suitability-section">
                <s:message code='createcasel_suitability'/>
            </div>
            <div id="eo-suitability-section" class="collapse in">
                <div class="espd-panel-body panel-body">
                    <div class="">
                        <strong data-i18n="crit_selection_eo_suitability_article">
                            <s:message code='crit_selection_eo_suitability_article'/>
                        </strong>
                        <span data-i18n="crit_selection_eo_suitability_article_tooltip" data-toggle="tooltip"
                              title="<s:message code='crit_selection_eo_suitability_article_tooltip'/>"></span>
                    </div>
                </div>
                <tiles:insertDefinition name="selectionCriterionEO">
                    <tiles:putAttribute name="field" value="suitabilityEnrolment"/>
                    <tiles:putAttribute name="number" value="1"/>
                    <tiles:putAttribute name="title_code" value="crit_selection_eo_suitability_enrolment_main"/>
                    <tiles:putAttribute name="tooltip_code" value="crit_selection_eo_suitability_enrolment_description"/>
                </tiles:insertDefinition>
                <tiles:insertDefinition name="selectionCriterionEO">
                    <tiles:putAttribute name="field" value="suitabilityServiceContracts"/>
                    <tiles:putAttribute name="number" value="2"/>
                    <tiles:putAttribute name="title_code" value="crit_selection_eo_suitability_service_contracts_main"/>
                    <tiles:putAttribute name="tooltip_code"
                                        value="crit_selection_eo_suitability_service_contracts_description"/>
                    <tiles:putAttribute name="has_please_describe_them" value="true"/>
                </tiles:insertDefinition>
            </div>
        </div>
        <div class="panel panel-default espd-panel">
            <div data-i18n="createcasel_economic_and_financial_standing" class="espd-panel-heading" data-toggle="collapse" data-target="#eo-economic-financial-section">
                <s:message code='createcasel_economic_and_financial_standing'/>
            </div>
            <div id="eo-economic-financial-section" class="collapse in">
                <div class="espd-panel-body panel-body">
                    <div class="">
                        <strong data-i18n="crit_selection_eo_economic_article">
                            <s:message code='crit_selection_eo_economic_article'/>
                        </strong>
                        <span data-i18n="crit_selection_eo_economic_article_tooltip" data-toggle="tooltip"
                              title="<s:message code='crit_selection_eo_economic_article_tooltip'/>"></span>
                    </div>
                </div>
                <tiles:insertDefinition name="selectionCriterionEO">
                    <tiles:putAttribute name="field" value="economicEnrolment"/>
                    <tiles:putAttribute name="number" value="1"/>
                    <tiles:putAttribute name="title_code" value="crit_selection_eo_economic_enrolment_main"/>
                    <tiles:putAttribute name="tooltip_code" value="crit_selection_eo_economic_enrolment_description"/>
                </tiles:insertDefinition>
                <tiles:insertDefinition name="selectionCriterionEO">
                    <tiles:putAttribute name="field" value="economicServiceContracts"/>
                    <tiles:putAttribute name="number" value="2"/>
                    <tiles:putAttribute name="title_code" value="crit_selection_eo_economic_service_contracts_main"/>
                    <tiles:putAttribute name="tooltip_code"
                                        value="crit_selection_eo_economic_service_contracts_description"/>
                    <tiles:putAttribute name="has_please_describe_them" value="true"/>
                </tiles:insertDefinition>
            </div>
        </div>
        <div class="panel panel-default espd-panel">
            <div data-i18n="createcasel_grounds_insolvency" class="espd-panel-heading" data-toggle="collapse" data-target="#eo-grounds-insolvency-section">
                <s:message code='createcasel_grounds_insolvency'/>
            </div>
            <div id="eo-grounds-insolvency-section" class="collapse in">
                <div class="panel-body">
                    ...
                </div>
            </div>
        </div>
        <div class="col-md-7">&nbsp;</div>
        <div class="col-md-5">
            <div class="btn-group">
                <button type="submit" class="btn btn-default btn-lg" name="prev">
                    <i class="fa fa-arrow-circle-o-left"></i>
                    <span data-i18n="previous"><s:message code="previous"/></span>
                </button>
                <a class="btn btn-default btn-lg" href="${pageContext.request.contextPath}/welcome">
                    <i class="fa fa-times-circle"></i>
                    <span data-i18n="cancel"><s:message code="cancel"/></span>
                </a>
                <button type="submit" class="btn btn-default btn-lg" name="next">
                    <i class="fa fa-arrow-circle-o-right"></i>
                    <span data-i18n="next"><s:message code="next"/></span>
                </button>
            </div>
        </div>
    </div>
</form:form>