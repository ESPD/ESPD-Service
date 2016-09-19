<%@ page import="eu.europa.ec.grow.espd.domain.enums.other.Country" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  ~
  ~ Copyright 2016 EUROPEAN COMMISSION
  ~
  ~ Licensed under the EUPL, Version 1.1 or – as soon they
  ~ will be approved by the European Commission - subsequent
  ~ versions of the EUPL (the "Licence");
  ~
  ~ You may not use this work except in compliance with the Licence.
  ~
  ~ You may obtain a copy of the Licence at:
  ~
  ~ https://joinup.ec.europa.eu/community/eupl/og_page/eupl
  ~
  ~ Unless required by applicable law or agreed to in
  ~ writing, software distributed under the Licence is
  ~ distributed on an "AS IS" basis,
  ~ WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
  ~ express or implied.
  ~ See the Licence for the specific language governing
  ~ permissions and limitations under the Licence.
  ~
  --%>

<%
    eu.europa.ec.grow.espd.util.I18NFunc inst = new eu.europa.ec.grow.espd.util.I18NFunc(pageContext);
    request.setAttribute("i18n", inst.message());
    request.setAttribute("div18n", inst.div());
    request.setAttribute("span18n", inst.span());
%>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description"
          content="ESPD is an information system that will aid in the process of creating an electronic file used throughout the tendering process in the EU and will reduce the administrative burden by reusing key information on the Contracting Authority, Economical Operator, criteria and evidence description.">
    <meta name="keywords" content="espd, public, procurement, europa, europe, european commission"/>
    <meta name="author" content="European Commission">
    <title>ESPD</title>
    <link rel="icon" type="image/png" href="data:image/png;base64,iVBORw0KGgo=">
    <link rel="stylesheet" type="text/css" href="<s:url value="/static/css/all.css"/>">
    <script src="<s:url value="/static/js/all.js"/>"></script>
</head>
<body>
<div class="container panel" style="padding:0; border-color: #396ea2 !important;">
    <div id="header">
        <img id="banner-flag" class="hidden-print" src="${pageContext.request.contextPath}/static/img/logo.png"
             alt="European Commission logo"/>
        <img id="banner-flag" class="visible-print" style="width: 114px; height: 80px;"
             src="${pageContext.request.contextPath}/static/img/logo.png" alt="European Commission logo"/>
        <c:if test="${not empty applicationScope.deploymentEnvironment}">
			<span id="banner-env-text">
				<span id="environment">${applicationScope.deploymentEnvironment}</span>
			</span>
        </c:if>
        <span id="banner-title-text" class="hidden-print">${span18n["app_title"]}</span>
        <span id="banner-title-text" style="font-size: 160%" class="visible-print">${span18n["app_title"]}</span>
        <span id="banner-image-title-fill"></span>
        <span id="banner-sub-title-text" class="hidden-print" data-i18n="app_subtitle"><s:message
                code="app_subtitle"/></span>

        <div id="top-lang-selector" class="hidden-print">
            <ul class="reset-list">
                <li><a target="_blank"
                       href="http://ec.europa.eu/growth/legal-notice/index_en.htm">${span18n["legal_notice"]}</a></li>
                <li><a target="_blank" href="http://ec.europa.eu/growth/cookies/index_en.htm">${span18n["cookies"]}</a>
                </li>
                <li><a href="${pageContext.request.contextPath}/contact">${span18n["contact"]}</a></li>
                <li>
                    <label for="language"></label><select id="language">
                    <c:forEach items="<%=eu.eeu.europa.ec.grow.espd.xml.enums.other.Languageues()%>" var="lang">
                        <option value="${lang.code}" ${(lang.code eq pageContext.response.locale)?"selected":""}>${lang.sourceLanguage}</option>
                    </c:forEach>
                </select>
                </li>
            </ul>
        </div>
    </div>
    <div id="breadbar" class="hidden-print">
        <ul class="breadcrumbs">
            <li><a href="#" data-i18n="europa" class="breadcrumElement"><s:message code="europa"/></a></li>
            <li><a href="#" data-i18n="ec" class="breadcrumElement"><s:message code="ec"/></a></li>
            <li><a href="#" data-i18n="grow" class="breadcrumElement"><s:message code="grow"/></a></li>
            <li><a href="#" data-i18n="espd" class="breadcrumElement"><s:message code="espd"/></a></li>
        </ul>
    </div>
    <div id="body" class="container espd-container">
        <form:form id="espdform" role="form" class="form-horizontal" method="post" commandName="espd"
                   data-toggle="validator">

            <div class="row">
                <strong class="col-md-4 col-md-offset-8">
                    <c:if test="${agent == 'eo'}">
                        <span data-i18n="role_eo"><s:message code="role_eo"/></span>
                    </c:if>
                    <c:if test="${agent == 'ca'}">
                        <span data-i18n="role_ca"><s:message code="role_ca"/></span>
                    </c:if>
                    <a href="${pageContext.request.contextPath}/${page}">
                        <c:if test="${agent == 'eo'}">
                            <span data-i18n="view_ca"><s:message code="view_ca"/></span>
                        </c:if>
                        <c:if test="${agent == 'ca'}">
                            <span data-i18n="view_eo"><s:message code="view_eo"/></span>
                        </c:if>
                    </a>
                </strong>
            </div>

            <div class="panel-default">
                <div class="panel panel-default no-border hidden-print">
                    <ul class="nav nav-pills nav-wizard nav-justified">
                        <li class="${(start||procedure||exclusion||selection||finish)?'active':''}">
                            <a href="#"><i class="fa fa-random"></i>&nbsp;${span18n['progress_start']}</a>

                            <div class="nav-arrow"></div>
                        </li>
                        <li class="${(procedure||exclusion||selection||finish)?'active':''}">
                            <div class="nav-wedge"></div>
                            <a href="#"><i class="fa fa-university"></i>&nbsp;${span18n['progress_procedure']}</a>

                            <div class="nav-arrow"></div>
                        </li>
                        <li class="${(exclusion||selection||finish)?'active':''}">
                            <div class="nav-wedge"></div>
                            <a href="#"><i class="fa fa-exclamation"></i>&nbsp;${span18n['progress_exclusion']}</a>

                            <div class="nav-arrow"></div>
                        </li>
                        <li class="${(finish||selection)?'active':''}">
                            <div class="nav-wedge"></div>
                            <a href="#"><i class="fa fa-check-circle"></i>&nbsp;${span18n['progress_selection']}</a>

                            <div class="nav-arrow"></div>
                        </li>
                        <li class="${finish?'active':''}">
                            <div class="nav-wedge"></div>
                            <a href="#"><i class="fa fa-download"></i>&nbsp;${span18n['progress_finish']} </a>
                        </li>
                    </ul>
                </div>
                <div class="errorContainer alert alert-danger" style="display: none">
                    <ul class="fa-ul">
                        <li>
                            <i class="info-label fa fa-exclamation-triangle fa-lg fa-li"></i>
                                ${div18n['correct_errors']}
                            <div class="errorLabelContainer">
                                <ul></ul>
                            </div>
                        </li>
                    </ul>
                </div>
                <form:errors path="*" cssClass="errorContainer alert alert-danger"></form:errors>
                <div><h2>${span18n['createcasel_header']}</h2></div>
                <div class="alert alert-espd-info">
                    <ul class="fa-ul">
                        <li>
                            <i class="info-label fa fa-info-circle fa-lg fa-li"></i>${span18n['createcasel_alert']}
                        </li>
                    </ul>
                </div>

                <div class="panel panel-espd">
                    <div class="panel-heading" data-toggle="collapse" data-target="#eo-satisfies-all-section">
                    	<h4 class="panel-title">${span18n['all_selection_switch']}</h4>
                    </div>
                    <div id="eo-satisfies-all-section" class="collapse in">
                        <div class="espd-panel-body panel-body">
                            <strong>${span18n['crit_selection_eo_declares_that']}</strong>
                            <span data-i18n="crit_selection_eo_declares_that_tooltip" data-toggle="tooltip"
                                  title="${i18n['crit_selection_eo_satisfies_all_criteria']}"></span>
                        </div>
                        <div class="row criteria-row-form">
                            <div class="col-md-5 criteria-row-check-left">
                                <div class="form-group">
                                    <div class="col-md-12">
                                        <strong>${span18n['crit_selection_eo_satisfies_all_criteria']}</strong>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-7 criteria-row-check-right">
                                <div class="col-md-12">
                                    <div class="form-group">
                                            ${span18n["crit_your_answer"]}
                                        <form:radiobutton path="selectionSatisfiesAll.answer" value="true"
                                                          data-target-hide="${'#'}eo-satisfies-all-form"/>${span18n["yes"]}
                                        <form:radiobutton path="selectionSatisfiesAll.answer" value="false"
                                                          data-target-show="${'#'}eo-satisfies-all-form"/>${span18n["no"]}
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div id="eo-satisfies-all-form" class="${espd['selectionSatisfiesAll'].answer ? 'collapse' : ''}">
                    <div class="panel panel-espd">
                        <div class="panel-heading" data-toggle="collapse" data-target="${'#'}${id}">
                        	<h4 class="panel-title">${span18n['title_code']}</h4>
                        </div>
                        <div id="${id}" class="collapse in">
                            <div class="espd-panel-body panel-body">
                                <strong>
                                        ${span18n[subtitle_code]}
                                </strong>
                                <c:if test="${!disableTooltips && not empty tooltip_code}">
                                    <span data-i18n="${tooltip_code}" data-toggle="tooltip"
                                          title="${i18n[tooltip_code]}"></span>
                                </c:if>
                                <c:forEach items="${espd.ublCriteria}" var="crit">
                                    <div class="row"
                                         style="border: 1px solid lightgray; margin-right: 5px; margin-left: 0px; margin-bottom: 5px;">
                                        <div class="col-md-5"
                                             style="border-right: 1px solid lightgray; padding-top: 5px;padding-left: 5px; padding-top: 7px;">
                                            <div class="form-group">
                                                <div class="col-md-12">
                                                    <strong>
                                                            ${crit.name.value}
                                                    </strong>
                                                </div>
                                                <div class="col-md-12">
                                                    <span class="small">
                                                            ${crit.description.value}
                                                    </span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-7"
                                             style="border-left: 1px solid lightgray; padding:20px; left: -1px;  padding-bottom: 0px;">
                                            <c:forEach items="${crit.requirementGroup}" var="group">
                                                <c:forEach items="${group.requirement}" var="req">
                                                    <div class="form-group">
                                                        <label class="control-label col-md-4 small">${req.description.value}</label>
                                                        <c:choose>
                                                            <c:when test="${req.responseDataType == 'INDICATOR'}">
                                                                <form:radiobutton
                                                                        path="${req.response[0].indicator.value}"
                                                                        value="true"
                                                                        data-target-show="${invert_answer?'':checktarget}"
                                                                        data-target-hide="${invert_answer?checktarget:''}"/>${span18n["yes"]}
                                                                <form:radiobutton
                                                                        path="${req.response[0].indicator.value}"
                                                                        value="false"
                                                                        data-target-show="${invert_answer?checktarget:''}"
                                                                        data-target-hide="${invert_answer?'':checktarget}"/>${span18n["no"]}
                                                            </c:when>
                                                            <c:when test="${req.responseDataType == 'DESCRIPTION'}">
                                                                <div class="col-md-8">
                                                                    <form:textarea
                                                                            path="${req.response[0].description.value}"
                                                                            class="form-control"></form:textarea>
                                                                </div>
                                                            </c:when>
                                                            <c:when test="${req.responseDataType == 'DATE'}">
                                                                <div class="col-md-8">
                                                                    <form:input type="text" path="${req.response[0].date.value}"
                                                                                cssClass="form-control datepicker"/>
                                                                </div>
                                                            </c:when>
                                                            <c:when test="${req.responseDataType == 'AMOUNT'}">
                                                                <div class="col-md-8">
                                                                    <form:textarea rows="1" path="${req.response[0].amount.value}"
                                                                                number="true" cssClass="form-control"/>
                                                                    <form:select
                                                                            path="${req.response[0].amount.currencyID}"
                                                                            cssClass="form-control" cssStyle="${style}">
                                                                        <form:option value="${null}" label="---"/>
                                                                        <c:forEach
                                                                                items="<%=eu.eeu.europa.ec.grow.espd.xml.enums.other.Currencyues()%>"
                                                                                var="curr">
                                                                            <form:option
                                                                                    value="${curr}">${curr} (${curr.description})</form:option>
                                                                        </c:forEach>
                                                                    </form:select>
                                                                </div>
                                                            </c:when>
                                                            <c:when test="${req.responseDataType == 'QUANTITY_YEAR'}">
                                                                <div class="col-md-8">
                                                                    <c:set var="lastYears"
                                                                           value="${[2016, 2015, 2014, 2013, 2012]}"/>
                                                                    <form:select
                                                                            path="${req.response[0].quantity.value}"
                                                                            cssClass="form-control">
                                                                        <form:option value="${null}" label="---"/>
                                                                        <form:options items="${lastYears}"/>
                                                                    </form:select>
                                                                </div>
                                                            </c:when>
                                                            <c:when test="${req.responseDataType == 'CODE_COUNTRY'}">
                                                                <div class="col-md-8">
                                                                    <form:select path="${req.response[0].code.value}"
                                                                                 cssClass="${cssClass}">
                                                                        <form:option value="${null}" label="---"/>
                                                                        <optgroup label="EU">
                                                                            <c:forEach items="<%=Country.EU_COUNTRIES%>"
                                                                                       var="cty">
                                                                                <form:option data-i18n="${cty.i18nCode}"
                                                                                             value="${cty}"><s:message
                                                                                        code="${cty.i18nCode}"/></form:option>
                                                                            </c:forEach>
                                                                        </optgroup>
                                                                        <optgroup label="EU+">
                                                                            <c:forEach
                                                                                    items="<%=Country.EU_PLUS_COUNTRIES%>"
                                                                                    var="cty">
                                                                                <form:option data-i18n="${cty.i18nCode}"
                                                                                             value="${cty}"><s:message
                                                                                        code="${cty.i18nCode}"/></form:option>
                                                                            </c:forEach>
                                                                        </optgroup>
                                                                        <optgroup label="EFTA">
                                                                            <c:forEach
                                                                                    items="<%=Country.EFTA_COUNTRIES%>"
                                                                                    var="cty">
                                                                                <form:option data-i18n="${cty.i18nCode}"
                                                                                             value="${cty}"><s:message
                                                                                        code="${cty.i18nCode}"/></form:option>
                                                                            </c:forEach>
                                                                        </optgroup>
                                                                    </form:select>
                                                                </div>
                                                            </c:when>
                                                            <c:when test="${req.responseDataType == 'CODE'}">
                                                                <div class="col-md-8">
                                                                    <form:textarea rows="1"
                                                                            path="${req.response[0].code.value}"
                                                                            class="form-control"/>
                                                                </div>
                                                            </c:when>
                                                            <c:when test="${req.responseDataType == 'EVIDENCE_URL'}">
                                                                <div class="col-md-8">
                                                                   <form:textarea rows="1"
                                                                                path="${req.response[0].evidence[0].evidenceDocumentReference[0].attachment.uri.value}"
                                                                                class="form-control input-sm"/>
                                                                </div>
                                                            </c:when>
                                                        </c:choose>
                                                    </div>
                                                </c:forEach>
                                            </c:forEach>

                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="hidden-print">
                    <div class="col-md-offset-3 col-md-6 hidden-print">
                        <div class="btn-group btn-group-justified" role="group">
                            <c:if test="${!prevUrl}">
                                <div class="btn-group" role="group">
                                    <button id="prevBtn" type="submit" class="btn btn-default btn-lg" name="prev"
                                            value="${prev}">
                                        <i class="fa fa-arrow-circle-o-left"></i> <span
                                            data-i18n="${prevCode}"><s:message code="${prevCode}"/></span>
                                    </button>
                                </div>
                            </c:if>
                            <c:if test="${prevUrl}">
                                <div class="btn-group" role="group">
                                    <a id="prevAnchor" class="btn btn-default btn-lg"
                                       href="${pageContext.request.contextPath}${prev}" role="button">
                                        <i class="fa fa-arrow-circle-o-left"></i> <span
                                            data-i18n="${prevCode}"><s:message code="${prevCode}"/></span>
                                    </a>
                                </div>
                            </c:if>
                            <div class="btn-group" role="group">
                                <a class="btn btn-default btn-lg" href="${pageContext.request.contextPath}/welcome"
                                   role="button">
                                    <i class="fa fa-times-circle"></i>
                                    <span data-i18n="cancel"><s:message code="cancel"/></span>
                                </a>
                            </div>
                            <c:if test="${print}">
                                <div class="btn-group" role="group">
                                    <a id="printBtn" target="_blank" class="btn btn-default btn-lg"
                                       href="${pageContext.request.contextPath}/print" role="button">
                                        <i class="fa fa-print"></i> <span data-i18n="print"><s:message
                                            code="print"/></span>
                                    </a>
                                </div>
                            </c:if>
                            <c:if test="${!nextUrl}">
                                <div class="btn-group" role="group">
                                    <button id="nextBtn" type="submit" class="btn btn-default btn-lg" name="next"
                                            value="${next}">
                                        <i class="fa fa-arrow-circle-o-right"></i> <span
                                            data-i18n="${nextCode}"><s:message code="${nextCode}"/></span>
                                    </button>
                                </div>
                            </c:if>
                            <c:if test="${nextUrl}">
                                <div class="btn-group" role="group">
                                    <a id="nextAnchor" class="btn btn-default btn-lg"
                                       href="${pageContext.request.contextPath}${next}" role="button">
                                        <i class="fa fa-arrow-circle-o-right"></i> <span
                                            data-i18n="${nextCode}"><s:message code="${nextCode}"/></span>
                                    </a>
                                </div>
                            </c:if>
                        </div>
                    </div>
                    <div class="col-md-3 hidden-print">&nbsp;</div>
                </div>
            </div>
        </form:form>
    </div>
</div>
<div id="footer">
    <c:set var="now" value="<%=new java.util.Date()%>" scope="request"/>
    <footer style="padding-top: 10px; color: black" class="hidden-print container-fluid">
        <div class="row">
            <div class="col-lg-12">
                <span data-i18n="footer_last_update"><s:message code='footer_last_update'/></span>
                <span class="datefmt"> <fmt:formatDate value="${now}"/> </span> |
                <a target="_blank" href="http://ec.europa.eu/growth/tools-databases/security-incidents/index_en.htm">
                    <span data-i18n="footer_report_security_incident"><s:message
                            code='footer_report_security_incident'/></span>
                </a>
            </div>
        </div>
    </footer>
</div>
</body>
</html>