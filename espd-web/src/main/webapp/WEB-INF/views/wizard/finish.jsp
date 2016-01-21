<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<form:form id="espdform" role="form" class="form-horizontal" method="post" commandName="espd" data-toggle="validator">
	
	<div class="panel-default">
	
        <tiles:insertDefinition name="progress">
			<tiles:putAttribute name="finish" value="true"/>
        </tiles:insertDefinition>

		<div class="paragraph">
			<h2>
				<span data-i18n="createcafinish_header"><s:message code="createcafinish_header"/></span>
			</h2>
		</div>
		
		<div class="panel panel-default espd-panel">
			<div data-i18n="createcafinish_reduction" class="espd-panel-heading" data-toggle="collapse" data-target="#finish-reduction-of-numbers-section">
				 <s:message code='createcafinish_reduction'/>
			</div>
            <div id="finish-reduction-of-numbers-section" class="collapse in">
                <div class="espd-panel-body panel-body">

					<div class="alert alert-espd-info">
						<ul class="fa-ul">
						<li>
							<i class="info-label fa fa-info-circle fa-lg fa-li"></i>
							<span data-i18n="createcafinish_toptext"><s:message code='createcafinish_toptext'/></span>
						</li>
						</ul>
					</div>
					
					<span data-i18n="createcafinish_reduction_question" class="aligned" style="font-weight: bold;">
                        <s:message code='createcafinish_reduction_question'/>
                    </span>
					
					<tiles:insertDefinition name="simpleFormCriterion">
						<tiles:putAttribute name="field" value="meetsObjective"/>
                        <tiles:putAttribute name="descriptionField" value="description1"/>
						<tiles:putAttribute name="title_code" value="createcafinish_title_eo_declares_that"/>
						<tiles:putAttribute name="description_code" value="createcafinish_text_eo_declares_that"/>
						<tiles:putAttribute name="availableElectronically" value="true"/>
						<tiles:putAttribute name="selfCleaning" value="false"/>
					</tiles:insertDefinition>
                    
                </div>
            </div>
		</div>
		
		<div class="panel panel-default espd-panel">
			<div data-i18n="createcafinish_concl_statements" class="espd-panel-heading" data-toggle="collapse" data-target="#finish-statements-signature-section">
				 <s:message code='createcafinish_concl_statements'/>
			</div>
            <div id="finish-statements-signature-section" class="collapse in">
                <div class="espd-panel-body panel-body">
                    <span data-i18n="createcafinish_concl_statements_text" class="aligned" style="">
                        <s:message code='createcafinish_concl_statements_text'/>
                    </span>
                    
                    <p>
	                    <span data-i18n="createcafinish_concl_statements_signature" class="aligned" style="">
	                        <s:message code='createcafinish_concl_statements_signature'/>
	                    </span>
                    </p>
                    
                </div>
            </div>
		</div>
		
		<div class="panel panel-default espd-panel">
			<div data-i18n="createcafinish_export" class="espd-panel-heading" data-toggle="collapse" data-target="#finish-statements-section">
				 <s:message code='createcafinish_export'/>
			</div>
            <div id="finish-statements-section" class="collapse in">
                <div class="espd-panel-body panel-body">
                    <span data-i18n="createcafinish_export_content" class="aligned" style="">
                        <s:message code='createcafinish_export_content'/>
                    </span>
                </div>
            </div>
		</div>

        <tiles:insertDefinition name="footerButtons">
            <tiles:putAttribute name="lastStep"  value="true"/>
        </tiles:insertDefinition>
	</div>
	</form:form>

