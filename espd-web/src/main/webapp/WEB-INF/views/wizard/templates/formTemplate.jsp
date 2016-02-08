<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:importAttribute name="checkExistanse"/>
<tiles:importAttribute name="field"/>

<c:if test="${(checkExistanse && espd[field] != null && espd[field].exists) || (!checkExistanse)}">

	<tiles:importAttribute name="form"/>
	<tiles:importAttribute name="has_your_answer"/>
	<tiles:importAttribute name="invert_answer"/>
	<tiles:importAttribute name="allows_checks"/>
	<tiles:importAttribute name="title_code"/>
	<tiles:importAttribute name="description_code"/>
	<tiles:importAttribute name="availableElectronically" />
	<tiles:importAttribute name="disableTooltips" />
	
	<div class="row" style="border: 1px solid lightgray; margin-right: 5px; margin-left: 0px; margin-bottom: 5px;">
	    <div class="col-md-5" style="border-right: 1px solid lightgray; padding-top: 5px;padding-left: 5px; padding-top: 7px;">
	        <div class="form-group">
	            <div class="col-md-12">
	            	<strong>
	                	${span18n[title_code]}
	                </strong>
	            </div>
	            <c:if test="${not empty description_code}">
	                <div class="col-md-12">
	                    <span class="small">
	                    	${span18n[description_code]}
	                    </span>
	                </div>
	            </c:if>
	        </div>
	    </div>
	    <div class="col-md-7" style="border-left: 1px solid lightgray; padding:20px; left: -1px;  padding-bottom: 0px;">
	
			<c:if test="${has_your_answer}">
				<div class="col-md-12">
					<div class="form-group">
						${span18n[allows_checks?"crit_do_you_allow_checks":"crit_your_answer"]}
						<c:set var="checktarget" value="${'#'.concat(field).concat('-form')}"/>
						<form:radiobutton path="${field}.answer" value="true" data-target-show="${invert_answer?'':checktarget}" data-target-hide="${invert_answer?checktarget:''}"/>${span18n["yes"]}
						<form:radiobutton path="${field}.answer" value="false" data-target-show="${invert_answer?checktarget:''}" data-target-hide="${invert_answer?'':checktarget}"/>${span18n["no"]}
					</div>
				</div>
			</c:if>
	
	        <div class="col-md-12 ${(has_your_answer && ((invert_answer && espd[field].answer) || (!(invert_answer || espd[field].answer)))) ? 'collapse' : ''}" id="${field}-form" style="min-height: 0px;">
	            <c:if test="${form != ''}">
	                <c:import url="${form}"/>
	            </c:if>
	        </div>
			<c:if test="${availableElectronically}">
		        <div class="col-md-12">
					<tiles:insertDefinition name="availableElectronically">
						<tiles:putAttribute name="field" value="${field}"/>
					</tiles:insertDefinition>
		        </div>
			</c:if>
	    </div>
	</div>

</c:if>
