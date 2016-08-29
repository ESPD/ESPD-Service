<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>


		<div>
			<h2>
				<span data-i18n="createcafinish_header"><s:message code="createcafinish_header"/></span>
			</h2>
		</div>
		<c:if test="${agent == 'eo'}"><%-- Part V is visible only for EO --%>
            <div class="panel panel-espd">
                <div class="panel-heading" data-toggle="collapse" data-target="#finish-reduction-of-numbers-section">
	 				<h4 class="panel-title">
						${span18n['createcafinish_reduction']}
					</h4>
                </div>
                <div id="finish-reduction-of-numbers-section" class="collapse in">
                    <div class="espd-panel-body panel-body">
                        <div class="alert alert-espd-info">
                            <ul class="fa-ul">
                                <li>
                                    <i class="info-label fa fa-info-circle fa-lg fa-li"></i>
                                    <span data-i18n="createcafinish_toptext"><s:message code='createcafinish_toptext'/></span>
                                </li>
                            </ul>
                        </div>
                        <span data-i18n="createcafinish_reduction_question" style="font-weight: bold;">
                            <s:message code='createcafinish_reduction_question'/>
                        </span>
                        <tiles:insertDefinition name="objectiveFormTemplate">
                            <tiles:putAttribute name="field" value="meetsObjective"/>
                            <tiles:putAttribute name="title_code" value="createcafinish_title_eo_declares_that"/>
                            <tiles:putAttribute name="description_code" value="createcafinish_text_eo_declares_that"/>
	                  		<tiles:putAttribute name="hasCriterion" value="false"/>
                        </tiles:insertDefinition>
                    </div>
                </div>
            </div>
		</c:if>
		<div class="panel panel-espd">
			<div class="panel-heading" data-toggle="collapse" data-target="#finish-statements-signature-section">
				<h4 class="panel-title">${span18n['createcafinish_concl_statements']}</h4>
			</div>
            <div id="finish-statements-signature-section" class="collapse in">
                <div class="espd-panel-body panel-body">
                    <span data-i18n="createcafinish_concl_statements_text" style="">
                        <s:message code='createcafinish_concl_statements_text'/>
                    </span>
                    <p>
	                    <span data-i18n="createcafinish_concl_statements_signature">
	                        <s:message code='createcafinish_concl_statements_signature'/>
	                    </span>
                    </p>
                </div>
                <c:if test="${agent == 'eo'}">
                    <div class="form-group">
                        <label class="control-label col-md-2 small">${span18n['crit_date']}</label>
                        <div class="col-md-4">
                            <form:input type="text" path="documentDate" cssClass="form-control datepicker" cssStyle="border-radius: 0;"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2 small">${span18n['place']}</label>
                        <div class="col-md-4">
                            <form:textarea rows="1" type="text" path="location" cssClass="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2 small">${span18n['signature']}</label>
                    </div>
                </c:if>
                <br/><br/><br/>
            </div>
		</div>
		