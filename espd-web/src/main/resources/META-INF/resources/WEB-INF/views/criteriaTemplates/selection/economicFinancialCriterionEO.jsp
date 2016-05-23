<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

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

<tiles:importAttribute name="field"/>
<tiles:importAttribute name="has_please_describe_them"/>
<tiles:importAttribute name="lastYearsAmount"/>
<tiles:importAttribute name="has_multiple_description_ratio"/>
<tiles:importAttribute name="has_multiple_year_amount"/>
<tiles:importAttribute name="has_single_amount"/>
<tiles:importAttribute name="has_specify_year"/>

        
	        <c:if test="${has_please_describe_them}">
	            <div class="col-md-12" id="${field}-form">
	                <div class="form-group">
	                    <div class="tab-pane" id="${field}-reliability">
	                        <div class="form-group">
	                            <label class="control-label col-md-4 small">
	                            	${span18n["crit_please_describe_them"]}
	                            </label>
	                            <div class="col-md-8">
	                                <form:textarea path="${field}.description" cssClass="form-control" data-i18n="crit_please_describe_them_placeholder" placeholder="${i18n['crit_please_describe_them_placeholder']}"></form:textarea>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	            </div>
	        </c:if>
	        
	        <c:if test="${has_multiple_year_amount && lastYearsAmount != null}">
	            <tiles:insertDefinition name="multipleYearAmount">
	                <tiles:putAttribute name="field" value="${field}"/>
	                <tiles:putAttribute name="lastYears" value="${lastYearsAmount}"/>
	            </tiles:insertDefinition>
	        </c:if>
	        <c:if test="${has_multiple_description_ratio}">
	            <tiles:insertDefinition name="multipleDescriptionRatio">
	                <tiles:putAttribute name="field" value="${field}"/>
	            </tiles:insertDefinition>
	        </c:if>
	        <c:if test="${has_single_amount}">
	            <div class="form-group">
	                <label class="control-label col-xs-4 small">
	                	${span18n["crit_amount"]}
	                </label>
	                <div class="col-xs-5">
	                    <form:input path="${field}.amount1" cssClass="form-control" data-i18n="crit_amount_concerned_placeholder" placeholder="${i18n['crit_amount_concerned_placeholder']}"/>
	                </div>
	                <div class="col-xs-3">
	                    <tiles:insertDefinition name="currencies">
	                        <tiles:putAttribute name="currencyField" value="${field}.currency1"/>
	                    </tiles:insertDefinition>
	                </div>
	            </div>
	            
	        </c:if>
            <c:if test="${has_specify_year}">
                <div class="form-group">
                    <label class="control-label col-md-4 small" data-i18n="crit_please_specify"><s:message
                            code='crit_please_specify'/></label>

                    <div class="col-md-5">
                        <form:select path="${field}.year1" cssClass="form-control">
                            <form:option value="${null}" label="---"/>
                            <form:options items="${lastYearsAmount}"/>
                        </form:select>
                    </div>

                </div>
            </c:if>
