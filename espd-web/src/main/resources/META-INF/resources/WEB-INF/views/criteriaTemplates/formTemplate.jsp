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

<tiles:importAttribute name="checkExistanse"/>
<tiles:importAttribute name="field"/>
<tiles:importAttribute name="hasCriterion"/>
<c:if test="${hasCriterion}">
	<tiles:importAttribute name="criterion"/>
</c:if>

<c:if test="${(checkExistanse && espd[field] != null && espd[field].exists) || (!checkExistanse)}">

	<tiles:importAttribute name="form"/>
	<tiles:importAttribute name="has_your_answer"/>
	<tiles:importAttribute name="invert_answer"/>
	<tiles:importAttribute name="allows_checks"/>
	<tiles:importAttribute name="title_code"/>
	<tiles:importAttribute name="description_code"/>
	<tiles:importAttribute name="availableElectronically" />
	<tiles:importAttribute name="disableTooltips" />
	
	<div class="row criteria-row-form">
	    <div class="col-md-5 criteria-row-form-left">
	        <div class="form-group">
	            <div class="col-md-12">

					<span style="font-weight: bold;cursor: pointer;" class="ecertis-link-header collapsed" data-uuid="${criterion.uuid}" data-i18n="${title_code}" data-toggle="collapse" data-target="${'#'}${field}-ecertis">
						<s:message code='${title_code}'/> 
					</span>
	                
					<div id="${field}-ecertis" class="alert alert-espd-info  collapse">
						<h4 id="loading">${span18n["ecertis_loading"]}</h4>
						<h5 id="ecertis404">${span18n["ecertis_404"]}</h5>
						<div id="content">
							<h5>${span18n["ecertis_language"]}: <span id="language"><!-- dynamic Language --></span></h5>
							<ol type="I" id="list">
								<li id="template">
									<span id="subname"><!-- dynamic subcriteria name --></span>
									(
									<span id="description"></span> <a id="url" target="_blank"><!-- dynamic URL --></a>
									)
									<ol id="evidencesFound">
										<li id="evidence">
											<dl>
												<dt>
													<a id="name" target="_blank"><!-- dynamic evidence link --></a>
												</dt>
												<dd id="issued">
													${span18n["ecertis_issued"]}: <span id="issuerNames"><!-- dynamic issuer names --></span>
												</dd>
											</dl>
										</li>
									</ol>
									<h5  id="evidencesNotFound">${span18n["ecertis_no_evidences"]}</h5>
								</li>
							</ol>
						</div>
					</div>
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
	    <div class="col-md-7 criteria-row-form-right">
	
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
	                <c:import url="${form}" charEncoding="UTF-8"/>
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
