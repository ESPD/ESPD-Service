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

<c:forEach begin="1" end="3" varStatus="loop">
	<div class="form-group">
		<div class="col-md-6">
		    <label class="control-label col-md-3 small" data-i18n="crit_year">${span18n['crit_year']}</label>
		    <div class="col-md-9">
		        <tiles:insertDefinition name="years">
		            <tiles:putAttribute name="yearField" value="${field}.year${loop.index}"/>
		            <tiles:putAttribute name="lastYears" value="${lastYears}"/>
		        </tiles:insertDefinition>
		    </div>
	    </div>
		<div class="col-md-6">
		    <label class="control-label col-md-3 small">${span18n['crit_number']}</label>
		    <div class="col-md-9">
		         <form:input path="${field}.number1" cssClass="form-control" placeholder="${i18n['crit_number_placeholder']}"/>
		    </div>
	    </div>
	</div>
</c:forEach>