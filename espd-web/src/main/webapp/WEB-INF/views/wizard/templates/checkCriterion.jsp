<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:importAttribute name="field"/>
<tiles:importAttribute name="title_code"/>
<tiles:importAttribute name="tooltip_code"/>
<tiles:importAttribute name="description_code"/>

<div class="checkbox" style="border: 1px solid lightgray;    margin-bottom: 5px;    padding-left: 5px;    padding-bottom: 5px;">
	<label>
		<form:checkbox path="${field}.exists"/>
		<span data-i18n="${title_code}">  
			<s:message code='${title_code}'/> 
		</span>
		<%-- TOOLTIP (delete this)--%>
		<c:if test="${field == 'paymentTaxes'}">
			<img id="taxTooltip" title=
				"
				<div style='width:550px; text-align:left'>
					Evidences for your country to proof this criteria are<br>
					a) Online Ausdruck des Steuerkontoauszugs<br>
					b) Online Unbedenklichkeitsbescheinigung des Finanzamts<br>
					c) Buchungsmitteilung des Finanzamtes<br>
					d) Rückstandsbescheinigung (Verzicht auf Absendung einer Eilnachricht) durch das Finanzamt<br>
					e) Unbedenklichkeitsbescheinigung durch das Finanzamt<br>
				</div>
				"
				src="${pageContext.request.contextPath}/static/img/certis.png" alt="ecertis" style="width:60px;height:15px;margin-top:-4px;">
			<script>
				$(function() {
					$('#taxTooltip').tooltip({placement:"top", html: true, trigger: "hover"});
				});
			</script>
		</c:if>
		<%-- TOOLTIP (delete this)--%>
		<c:if test="${not empty tooltip_code}">
			<s:message var="tooltip_text" code='${tooltip_code}'/>
			<span data-i18n="${tooltip_code}" data-toggle="tooltip" title="${tooltip_text}"></span>
		</c:if>
	</label>
	<c:if test="${not empty description_code}">
		<br>
	    <span class="small" data-i18n="${description_code}"><s:message code='${description_code}'/></span>
	</c:if>
</div>



