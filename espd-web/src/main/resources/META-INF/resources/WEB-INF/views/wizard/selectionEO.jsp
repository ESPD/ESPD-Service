<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

<%request.setAttribute("selectionEO", eu.europa.ec.grow.espd.xml.CriteriaTemplates.selectionEO);%>

<tiles:importAttribute name="flow"/>

<script>
    $(function () {
        $("#espdform").validate({
            errorContainer: $("div.errorContainer"),
            errorPlacement: function ($error, $element) {
            	$element.parent().append($error);
            }
        });
    });
</script>

<form:form id="espdform" role="form" class="form-horizontal" method="post" commandName="espd" data-toggle="validator">

    <tiles:insertDefinition name="viewChangeRole">
        <tiles:putAttribute name="agent" value="eo"/>
        <tiles:putAttribute name="page" value="${flow}/ca/selection"/>
        <tiles:putAttribute name="showLink" value="${flow == 'request'}"/>
    </tiles:insertDefinition>
    
    <div class="panel-default">
        <tiles:insertDefinition name="progress">
        	<tiles:putAttribute name="agent" value="${agent}"/>
        	<tiles:putAttribute name="flow" value="${flow}"/>
			<tiles:putAttribute name="selection" value="true"/>
        </tiles:insertDefinition>
        
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
                    <i class="info-label fa fa-info-circle fa-lg fa-li"></i>${span18n['createeosel_alert']}
                </li>
            </ul>
        </div>
        <c:if test="${espd.selectionSatisfiesAll != null && espd.selectionSatisfiesAll.exists}">
            <div class="panel panel-espd">
                <div class="panel-heading" data-toggle="collapse" data-target="#eo-satisfies-all-section">
                    <h4 class="panel-title">${span18n['all_selection_switch']}</h4>
                </div>
                <div id="eo-satisfies-all-section" class="collapse in">
                    <div class="espd-panel-body panel-body">
                        <strong>${span18n['crit_selection_eo_declares_that']}</strong>
                        <span data-i18n="crit_selection_eo_declares_that_tooltip" data-toggle="tooltip" title="${i18n['crit_selection_eo_satisfies_all_criteria']}"></span>
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
                                    <form:radiobutton path="selectionSatisfiesAll.answer" value="true" data-target-hide="${'#'}eo-satisfies-all-form"/>${span18n["yes"]}
                                    <form:radiobutton path="selectionSatisfiesAll.answer" value="false" data-target-show="${'#'}eo-satisfies-all-form"/>${span18n["no"]}
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>

        <div id="eo-satisfies-all-form" class="${espd['selectionSatisfiesAll'].answer ? 'collapse' : ''}">
  
			<tiles:insertDefinition name="topLevelCriteriaTemplate">
				<tiles:putAttribute name="topLevelCriteriaList" value="${selectionEO}"/>
			</tiles:insertDefinition>

		</div>
        
        <tiles:insertDefinition name="footerButtons">
            <tiles:putAttribute name="prev" value="exclusion"/>
            <tiles:putAttribute name="next" value="finish"/>
        </tiles:insertDefinition>
    </div>
</form:form>