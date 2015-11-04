<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:importAttribute name="field"/>
<tiles:importAttribute name="title_code"/>
<tiles:importAttribute name="tooltip_code"/>
<tiles:importAttribute name="description_code"/>

<tiles:importAttribute name="selfCleaning"/>
<tiles:importAttribute name="breachOfObligations"/>
<tiles:importAttribute name="avaliableElectronically"/>

<div class="row" style="border: 1px solid lightgray; margin-right: 20px; margin-left: 20px; margin-bottom: 5px;">
		<div class="col-md-5" style="border-right: 1px solid lightgray; padding-top: 20px;">
				<div class="form-group">
					<div class="col-md-12"> 
						<strong data-i18n="${title_code}">  
							<s:message code='${title_code}'/> 
						</strong>
						<c:if test="${tooltip_code != ''}">
							<s:message var="tooltip_text" code='${tooltip_code}'/>
							<span data-i18n="${tooltip_code}" data-toggle="tooltip" title="${tooltip_text}"></span>
						</c:if>
					</div> 
				</div>
				
				<c:if test="${not empty description_code}">
					<div class="row">
						<div class="col-md-1"></div>
				    	<div class="col-md-11">
				    		<s:message var="description_text" code='${description_code}'/>
				    		<span class="small" data-i18n="${description_code}">${description_text}</span>
				    	</div>
				    </div>
				</c:if>
		</div> 
		<div class="col-md-7" style="border-left: 1px solid lightgray; padding:20px; left: -1px;  padding-bottom: 0px;">
		
			<div class="col-md-12">
				<div class="form-group">
						 
					<label class="control-label small" style="padding-top: 0px;" for="${field}-field6">
						Your answer?
					</label>
						
					<form:checkbox path="${field}.exists" id="${field}-field6" data-toggle="collapse" data-target="${'#'}${field}-form" cssClass="${yesByDefault?'radioslide-yesByDefault':''} radioslide checktoggle form-control" />
				</div>
			</div>

			<div class="col-md-12" id="${field}-form" style="display:none"> 
			
				<tiles:insertAttribute name="form">
					<tiles:putAttribute name="field" value="${field}"/>
				</tiles:insertAttribute>
				
				<c:if test="${breachOfObligations}">
					<tiles:insertDefinition name="breachOfObligations">
						<tiles:putAttribute name="field" value="${field}"/>
					</tiles:insertDefinition>
				</c:if>
				
				<c:if test="${selfCleaning}">
					<tiles:insertDefinition name="selfCleaning">
						<tiles:putAttribute name="field" value="${field}"/>
					</tiles:insertDefinition>
				</c:if>
			
			</div>

			<div class="col-md-12">
				<c:if test="${avaliableElectronically}">
					<tiles:insertDefinition name="avaliableElectronically">
						<tiles:putAttribute name="field" value="${field}"/>
					</tiles:insertDefinition>
				</c:if>
			</div>
		</div>
		
		

		

</div>


