<%@page import="java.util.ArrayList"%>
<%@page import="eu.europa.ec.grow.espd.domain.EconomicOperatorImpl"%>
<%@page import="eu.europa.ec.grow.espd.domain.EconomicOperatorRepresentative"%>
<%@page import="org.springframework.util.CollectionUtils"%>
<%@page import="eu.europa.ec.grow.espd.domain.EspdDocument"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

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

<tiles:importAttribute name="agent"/>
<tiles:importAttribute name="flow"/>

<script>
    $(function () {
        $("#espdform").validate({
            errorContainer: $("div.errorContainer"),
            errorPlacement: function ($error, $element) {
                $element.parent().append($error);
            }
        });
        $("#ojsNumber").inputmask("9999/S 999-999999");

        // eo registered answer and not applicable are mutually exclusive
        $("#eo_registered_answer_yes").click(function () {
			$('#eo_registered_na').attr('checked', false);
        });
        $("#eo_registered_answer_no").click(function () {
			$('#eo_registered_na').attr('checked', false);
        });
        $("#eo_registered_na").click(function () {
			$('#eo_registered_answer_yes').attr('checked', false);
			$('#eo_registered_answer_no').attr('checked', false);
        });
    });
</script>

<form:form id="espdform" role="form" class="form-horizontal" method="post" commandName="espd" data-toggle="validator">

    <tiles:insertDefinition name="viewChangeRole">
        <tiles:putAttribute name="agent" value="${agent}"/>
        <tiles:putAttribute name="page" value="${flow}/${agent == 'ca' ? 'eo' : 'ca'}/procedure"/>
        <tiles:putAttribute name="showLink" value="${flow == 'request'}"/>
    </tiles:insertDefinition>
    
    <div class="panel-default">
        <tiles:insertDefinition name="progress">
        	<tiles:putAttribute name="agent" value="${agent}"/>
        	<tiles:putAttribute name="flow" value="${flow}"/>
            <tiles:putAttribute name="procedure" value="true"/>
        </tiles:insertDefinition>
        
        <%
        	// Add at least one EconomicOperatorRepresentative to be sure user will see it in form
        	EspdDocument espd = (EspdDocument)request.getAttribute("espd");
        	if (espd != null) { 
        		if (espd.getEconomicOperator() == null) {
        			espd.setEconomicOperator(new EconomicOperatorImpl());
        		}
	        	if (CollectionUtils.isEmpty(espd.getEconomicOperator().getRepresentatives())) {
        			espd.getEconomicOperator().setRepresentatives(new ArrayList<EconomicOperatorRepresentative>());
        			espd.getEconomicOperator().getRepresentatives().add(new EconomicOperatorRepresentative());
	        	}
        	}
        %>

		<%@ include file="/WEB-INF/views/wizard/procedureForm.jsp" %>

    </div>
 
    <tiles:insertDefinition name="footerButtons">
        <tiles:putAttribute name="prev" value="/filter"/>
        <tiles:putAttribute name="next" value="exclusion"/>
    </tiles:insertDefinition>
</form:form>
