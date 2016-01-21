<%@page import="java.util.Calendar"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.lang.Integer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:importAttribute name="field"/>

<%
int MAX_YEARS = 5;
ArrayList<Integer> years = new ArrayList<Integer>();
int current = Calendar.getInstance().get(Calendar.YEAR);
for(int i = 0; i < MAX_YEARS; i++) {
	years.add(new Integer(current - i));
}
request.setAttribute("lastYears", years);
%>

<s:message code="crit_year_placeholder" var="yearPlaceholder"/>
<s:message code="crit_amount_concerned_placeholder" var="amountPlaceholder"/>

<c:set var="maximumYears" value="5"/>	

<c:forEach begin="1" end="5" varStatus="loop">
	<div class="form-group">
		<div class="col-md-3">
		    <label class="control-label col-md-3 small">${span18n['crit_year']}</label>
		    <div class="col-md-9">
		        <tiles:insertDefinition name="years">
		            <tiles:putAttribute name="yearField" value="${field}.year${loop.index}"/>
		            <tiles:putAttribute name="lastYears" value="${lastYears}"/>
		        </tiles:insertDefinition>
		    </div>
	    </div>
		<div class="col-md-6">
		    <label class="control-label col-md-3 small">${span18n['crit_amount_concerned']}</label>
		    <div class="col-md-9">
		         <form:input path="${field}.amount${loop.index}" cssClass="form-control" placeholder="${amountPlaceholder}"/>
		    </div>
	    </div>
		<div class="col-md-3">
		    <div class="col-md-9">
		    
				<form:select path="${field}.currency${loop.index}" cssClass="form-control">
				    <form:option value="${null}" label="---"/>
				    <c:forEach items="<%=eu.europa.ec.grow.espd.constants.enums.Currency.values()%>" var="curr">
				        <form:option value="${curr}">${curr}</form:option>
				    </c:forEach>
				</form:select>
				
		    </div>
	    </div>
	</div>
</c:forEach>
