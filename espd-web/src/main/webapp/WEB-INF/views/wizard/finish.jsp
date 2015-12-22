<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<form:form id="espdform" role="form" class="form-horizontal" method="post" commandName="espd" data-toggle="validator">
	
	<div class="panel-default">
	
		<div class="panel panel-default no-border">	
			<ul class="nav nav-pills nav-wizard nav-justified">
			    <li class="active"><a href="#"><i class="fa fa-random"></i>&nbsp;<span data-i18n="progress_start"><s:message code='progress_start'/></span></a><div class="nav-arrow"></div></li>
			    <li class="active"><div class="nav-wedge"></div><a href="#"><i class="fa fa-university"></i>&nbsp;<span data-i18n="progress_procedure"><s:message code='progress_procedure'/></span></a><div class="nav-arrow"></div></li>
			    <li class="active"><div class="nav-wedge"></div><a href="#"><i class="fa fa-exclamation"></i>&nbsp;<span data-i18n="progress_exclusion"><s:message code='progress_exclusion'/></span></a><div class="nav-arrow"></div></li>
			    <li class="active"><div class="nav-wedge"></div><a href="#"><i class="fa fa-check-circle"></i>&nbsp;<span data-i18n="progress_selection"><s:message code='progress_selection'/></span></a><div class="nav-arrow"></div></li>
			    <li class="active"><div class="nav-wedge"></div><a href="#"><i class="fa fa-download"></i>&nbsp;<span data-i18n="progress_finish"><s:message code='progress_finish'/></span></a></li>
			</ul>
		</div>

		<div class="paragraph">
			<h2>
				<span data-i18n="createcafinish_header"><s:message code="createcafinish_header"/></span>
			</h2>
		</div>
		
		<div class="panel panel-default espd-panel">
			<div data-i18n="createcafinish_reduction" class="espd-panel-heading" data-toggle="collapse" data-target="#finish-statements-section">
				 <s:message code='createcafinish_reduction'/>
			</div>
            <div id="finish-statements-section" class="collapse in">
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
					
					<p>TODO
                    
                </div>
            </div>
		</div>
		
		<div class="panel panel-default espd-panel">
			<div data-i18n="createcafinish_concl_statements" class="espd-panel-heading" data-toggle="collapse" data-target="#finish-statements-section">
				 <s:message code='createcafinish_concl_statements'/>
			</div>
            <div id="finish-statements-section" class="collapse in">
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

