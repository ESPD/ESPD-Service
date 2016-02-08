<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>


<tiles:importAttribute name="id" />
<tiles:importAttribute name="title_code"/>
<tiles:importAttribute name="subtitle_code"/>
<tiles:importAttribute name="tooltip_code"/>
<tiles:importAttribute name="disableTooltips"/>

<tiles:importAttribute name="criteriaList" />

<div class="panel panel-default espd-panel">
	        
	<div class="espd-panel-heading" data-toggle="collapse" data-target="${'#'}${id}">
		${span18n[title_code]}
	</div>
	            
	<div id="${id}" class="collapse in">
		<div class="espd-panel-body panel-body">

			<strong>
				${span18n[subtitle_code]}
			</strong>
			
			<c:if test="${!disableTooltips && not empty tooltip_code}">
				<span data-i18n="${tooltip_code}" data-toggle="tooltip" title="${i18n[tooltip_code]}"></span>
			</c:if>
			
			<c:forEach var="criterion" items="${criteriaList}">

				<tiles:insertDefinition name="${criterion['template']}">
					<c:forEach var="criterionParam" items="${criterion}">
					    <tiles:putAttribute name="${criterionParam.key}" value="${criterionParam.value}"/>
					</c:forEach>

					<%-- only for ugly print version --%>
					<c:if test="${disableTooltips == true}">
						<tiles:putAttribute name="disableTooltips" value="true"/>
					</c:if>
				</tiles:insertDefinition>
				
			</c:forEach>
			
		</div>
	</div>
</div>
