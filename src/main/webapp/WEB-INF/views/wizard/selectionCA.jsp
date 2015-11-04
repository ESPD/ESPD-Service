<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script>
    $(function () {
        $("#ca-satisfies-all-criteria").click(function () {
            $("#ca-selection-criteria").toggle();
        });
    });
</script>
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
            <a href="${pageContext.request.contextPath}/selection?agent=eo">View as EO</a>
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
            <div class="espd-panel-heading">
            </div>
            <div class="espd-panel-body panel-body">
                <div id="selectionDeclares" class="">
                    <strong data-i18n="crit_selection_ca_declares_that">
                        <s:message code='crit_selection_ca_declares_that'/>
                    </strong>
                    <span data-i18n="crit_selection_ca_declares_that_tooltip" data-toggle="tooltip"
                          title="<s:message code='crit_selection_ca_declares_that_tooltip'/>"></span>
                </div>
                <div class="checkbox">
                    <label>
                        <form:checkbox id="ca-satisfies-all-criteria" path="selectionSatisfiesAll.exists"
                                       class="checktoggle" value="true"/>
                        <span data-i18n="crit_selection_ca_satisfies_all_criteria">
                        <s:message code='crit_selection_ca_satisfies_all_criteria'/>
                        </span>
                    </label>
                </div>
            </div>
        </div>
        <div class="tab-pane active" id="ca-selection-criteria">
            <div class="panel panel-default espd-panel">
                <div data-i18n="createcasel_suitability" class="espd-panel-heading">
                    <s:message code='createcasel_suitability'/>
                </div>
                <div class="espd-panel-body panel-body">
                    <div class="">
                        <strong data-i18n="crit_selection_ca_suitability_article">
                            <s:message code='crit_selection_ca_suitability_article'/>
                        </strong>
                            <span data-i18n="crit_selection_ca_suitability_tooltip" data-toggle="tooltip"
                                  title="<s:message code='crit_selection_ca_suitability_article_tooltip'/>"></span>
                    </div>
                    <c:set var="field" value="suitabilityEnrolment" scope="session"/>
                    <c:set var="title_code" value="crit_selection_ca_suitability_enrolment_main" scope="session"/>
                    <c:set var="tooltip_code" value="" scope="session"/>
                    <c:set var="description_code" value="crit_selection_ca_suitability_enrolment_description"
                           scope="session"/>
                    <jsp:include page="editors/criteria.jsp"/>
                    <c:set var="field" value="suitabilityServiceContracts" scope="session"/>
                    <c:set var="title_code" value="crit_selection_ca_suitability_service_contracts_main"
                           scope="session"/>
                    <c:set var="tooltip_code" value="" scope="session"/>
                    <c:set var="description_code" value="crit_selection_ca_suitability_service_contracts_description"
                           scope="session"/>
                    <jsp:include page="editors/criteria.jsp"/>
                </div>
            </div>
            <div class="panel panel-default espd-panel">
                <div data-i18n="createcasel_economic_and_financial_standing" class="espd-panel-heading">
                    <s:message code='createcasel_economic_and_financial_standing'/>
                </div>
                <div class="espd-panel-body panel-body">
                    <div class="">
                        <strong data-i18n="crit_selection_ca_economic_article">
                            <s:message code='crit_selection_ca_economic_article'/>
                        </strong>
                            <span data-i18n="crit_selection_ca_economic_article_tooltip" data-toggle="tooltip"
                                  title="<s:message code='crit_selection_ca_economic_article_tooltip'/>"></span>
                    </div>
                    <c:set var="field" value="economicGeneralTurnover" scope="session"/>
                    <c:set var="title_code" value="crit_selection_ca_economic_general_yearly_main" scope="session"/>
                    <c:set var="tooltip_code" value="" scope="session"/>
                    <c:set var="description_code" value="crit_selection_ca_economic_general_yearly_description"
                           scope="session"/>
                    <jsp:include page="editors/criteria.jsp"/>
                    <c:set var="field" value="economicAverageTurnover" scope="session"/>
                    <c:set var="title_code" value="crit_selection_ca_economic_average_yearly_main" scope="session"/>
                    <c:set var="tooltip_code" value="" scope="session"/>
                    <c:set var="description_code" value="crit_selection_ca_economic_average_yearly_description"
                           scope="session"/>
                    <jsp:include page="editors/criteria.jsp"/>
                </div>
            </div>
            <div class="panel panel-default espd-panel">
                <div data-i18n="createcasel_grounds_insolvency" class="espd-panel-heading">
                    <s:message code='createcasel_grounds_insolvency'/>
                </div>
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