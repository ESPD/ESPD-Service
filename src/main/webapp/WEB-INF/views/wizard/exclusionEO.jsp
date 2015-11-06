<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<form:form id="espdform" role="form" class="form-horizontal" method="post" commandName="espd" data-toggle="validator">
    <tiles:insertDefinition name="viewChangeRole">
        <tiles:putAttribute name="currentPage" value="exclusion"/>
    </tiles:insertDefinition>
	
	<div class="panel-default">
	
		<div class="panel panel-default no-border">	
			<ul class="nav nav-pills nav-wizard nav-justified">
			    <li class="active"><a href="#"><i class="fa fa-random"></i>&nbsp;<span data-i18n="progress_start"><s:message code='progress_start'/></span></a><div class="nav-arrow"></div></li>
			    <li class="active"><div class="nav-wedge"></div><a href="#"><i class="fa fa-university"></i>&nbsp;<span data-i18n="progress_procedure"><s:message code='progress_procedure'/></span></a><div class="nav-arrow"></div></li>
			    <li class="active"><div class="nav-wedge"></div><a href="#"><i class="fa fa-exclamation"></i>&nbsp;<span data-i18n="progress_exclusion"><s:message code='progress_exclusion'/></span></a><div class="nav-arrow"></div></li>
			    <li><div class="nav-wedge"></div><a href="#"><i class="fa fa-check-circle"></i>&nbsp;<span data-i18n="progress_selection"><s:message code='progress_selection'/></span></a><div class="nav-arrow"></div></li>
			    <li><div class="nav-wedge"></div><a href="#"><i class="fa fa-download"></i>&nbsp;<span data-i18n="progress_finish"><s:message code='progress_finish'/></span></a></li>
			</ul>
		</div>

		<div class="paragraph">
			<h2>
				<span data-i18n="createcaexcl_header"><s:message code="createcaexcl_header"/></span>
			</h2>
		</div>
		
		<div class="panel panel-default espd-panel">
		
			<div data-i18n="crit_top_title_grounds_criminal_conv" class="espd-panel-heading" data-toggle="collapse" data-target="#criminal_conv">
				<s:message code="crit_top_title_grounds_criminal_conv"/>
			</div>
						
			<div id="criminal_conv" class="collapse in">
			<div class="espd-panel-body panel-body">
			
					<div class="">
						<span data-i18n="crit_eu_main_title_grounds_criminal_conv_eo">
							<s:message code='crit_eu_main_title_grounds_criminal_conv_eo'/>
						</span>
						<span data-i18n="crit_eu_main_tooltip_grounds_criminal_conv_eo" data-toggle="tooltip" title="<s:message code='crit_eu_main_tooltip_grounds_criminal_conv_eo'/>"></span>
					</div>
					
					<tiles:insertDefinition name="criminalFormCriterion">
						<tiles:putAttribute name="field" value="criminalConvictions"/>
						<tiles:putAttribute name="title_code" value="crit_eu_title_grounds_criminal_conv"/>
						<tiles:putAttribute name="tooltip_code" value="crit_eu_tooltip_grounds_criminal_conv"/>
					</tiles:insertDefinition>

					<tiles:insertDefinition name="criminalFormCriterion">
						<tiles:putAttribute name="field" value="corruption"/>
						<tiles:putAttribute name="title_code" value="crit_eu_title_corruption"/>
						<tiles:putAttribute name="tooltip_code" value="crit_eu_tooltip_grounds_criminal_conv"/>
					</tiles:insertDefinition>
					
					<tiles:insertDefinition name="criminalFormCriterion">
						<tiles:putAttribute name="field" value="fraud"/>
						<tiles:putAttribute name="title_code" value="crit_eu_title_fraud"/>
						<tiles:putAttribute name="tooltip_code" value="crit_eu_tooltip_fraud"/>
					</tiles:insertDefinition>
					
					<tiles:insertDefinition name="criminalFormCriterion">
						<tiles:putAttribute name="field" value="terroristOffences"/>
						<tiles:putAttribute name="title_code" value="crit_eu_title_terrorist"/>
						<tiles:putAttribute name="tooltip_code" value="crit_eu_tooltip_terrorist"/>
					</tiles:insertDefinition>
					
					<tiles:insertDefinition name="criminalFormCriterion">
						<tiles:putAttribute name="field" value="moneyLaundering"/>
						<tiles:putAttribute name="title_code" value="crit_eu_title_money_laundering"/>
						<tiles:putAttribute name="tooltip_code" value="crit_eu_tooltip_money_laundering"/>
					</tiles:insertDefinition>
					
					<tiles:insertDefinition name="criminalFormCriterion">
						<tiles:putAttribute name="field" value="childLabour"/>
						<tiles:putAttribute name="title_code" value="crit_eu_title_child_labour"/>
						<tiles:putAttribute name="tooltip_code" value="crit_eu_tooltip_child_labour"/>
					</tiles:insertDefinition>
			</div>
		</div>
		</div>
	
		<div class="panel panel-default espd-panel">
			<div data-i18n="crit_top_title_grounds_payment_taxes" class="espd-panel-heading" data-toggle="collapse" data-target="#payment_taxes">
				<s:message code="crit_top_title_grounds_payment_taxes"/>
			</div>
			<div id="payment_taxes" class="collapse in">
			<div class="espd-panel-body panel-body">
			
				<div class="">
					<span data-i18n="crit_eu_main_title_payment_taxes_eo">
						<s:message code='crit_eu_main_title_payment_taxes_eo'/>
					</span>
					<span data-i18n="crit_eu_main_tooltip_payment_taxes_eo" data-toggle="tooltip" title="<s:message code='crit_eu_main_tooltip_payment_taxes_eo'/>"></span>
				</div>

				<tiles:insertDefinition name="taxFormCriterion">
					<tiles:putAttribute name="field" value="paymentTaxes"/>
					<tiles:putAttribute name="title_code" value="crit_eu_title_payment_taxes"/>
				</tiles:insertDefinition>
				
				<tiles:insertDefinition name="taxFormCriterion">
					<tiles:putAttribute name="field" value="paymentSocsec"/>
					<tiles:putAttribute name="title_code" value="crit_eu_title_payment_social_security"/>
				</tiles:insertDefinition>
					
			</div>
			</div>
		</div>
	
		<div class="panel panel-default espd-panel">
			<div data-i18n="crit_top_title_insolvency_conflicts" class="espd-panel-heading" data-toggle="collapse" data-target="#insolvency_conflicts">
				<s:message code="crit_top_title_insolvency_conflicts"/>
			</div>
			<div id="insolvency_conflicts" class="collapse in">
			<div class="espd-panel-body panel-body">

				<div class="">
					<span data-i18n="crit_eu_main_breaching_obligations_eo">
						<s:message code='crit_eu_main_breaching_obligations_eo'/>
					</span>
					<span data-i18n="crit_eu_main_tooltip_breaching_obligations_eo" data-toggle="tooltip" title="<s:message code='crit_eu_main_tooltip_breaching_obligations_eo'/>"></span>
				</div>
					
				<tiles:insertDefinition name="simpleFormCriterion">
					<tiles:putAttribute name="field" value="breachingObligations"/>
					<tiles:putAttribute name="title_code" value="crit_eu_title_breaching_obligations"/>
					<tiles:putAttribute name="tooltip_code" value="crit_eu_tooltip_breaching_obligations"/>
					<tiles:putAttribute name="description_code" value="crit_eu_text_breaching_obligations"/>
					<tiles:putAttribute name="avaliableElectronically" value="false"/>
				</tiles:insertDefinition>
				
				<tiles:insertDefinition name="reasonsNeverlessPerformForm">
					<tiles:putAttribute name="field" value="bankruptSubject"/>
					<tiles:putAttribute name="title_code" value="crit_eu_title_bankrupt"/>
					<tiles:putAttribute name="tooltip_code" value="crit_eu_tooltip_bankrupt"/>
					<tiles:putAttribute name="description_code" value="crit_eu_text_bankrupt"/>
					<tiles:putAttribute name="selfCleaning" value="false"/>
				</tiles:insertDefinition>
				
				<tiles:insertDefinition name="simpleFormCriterion">
					<tiles:putAttribute name="field" value="guiltyGrave"/>
					<tiles:putAttribute name="title_code" value="crit_eu_title_guilty_misconduct"/>
					<tiles:putAttribute name="tooltip_code" value="crit_eu_tooltip_guilty_misconduct"/>
					<tiles:putAttribute name="description_code" value="crit_eu_text_guilty_misconduct"/>
					<tiles:putAttribute name="avaliableElectronically" value="false"/>
				</tiles:insertDefinition>
				
				<tiles:insertDefinition name="simpleFormCriterion">
					<tiles:putAttribute name="field" value="agreementsEo"/>
					<tiles:putAttribute name="title_code" value="crit_eu_title_agreement_economic"/>
					<tiles:putAttribute name="description_code" value="crit_eu_text_agreement_economic"/>
					<tiles:putAttribute name="avaliableElectronically" value="false"/>
				</tiles:insertDefinition>
				
				<tiles:insertDefinition name="simpleFormCriterion">
					<tiles:putAttribute name="field" value="conflictInterest"/>
					<tiles:putAttribute name="title_code" value="crit_eu_title_conflict_interest"/>
					<tiles:putAttribute name="tooltip_code" value="crit_eu_tooltip_conflict_interest"/>
					<tiles:putAttribute name="description_code" value="crit_eu_text_conflict_interest"/>
				</tiles:insertDefinition>
				
				<tiles:insertDefinition name="simpleFormCriterion">
					<tiles:putAttribute name="field" value="involvementPreparation"/>
					<tiles:putAttribute name="title_code" value="crit_eu_title_involvment"/>
					<tiles:putAttribute name="description_code" value="crit_eu_text_involvment"/>
					<tiles:putAttribute name="avaliableElectronically" value="false"/>
					<tiles:putAttribute name="selfCleaning" value="false"/>
				</tiles:insertDefinition>
				
				<tiles:insertDefinition name="simpleFormCriterion">
					<tiles:putAttribute name="field" value="earlyTermination"/>
					<tiles:putAttribute name="title_code" value="crit_eu_title_early_termination"/>
					<tiles:putAttribute name="description_code" value="crit_eu_text_early_termination"/>
					<tiles:putAttribute name="avaliableElectronically" value="false"/>
				</tiles:insertDefinition>
				
				<tiles:insertDefinition name="simpleFormCriterion">
					<tiles:putAttribute name="field" value="guiltyMisinterpretation"/>
					<tiles:putAttribute name="title_code" value="crit_eu_title_guilty_misinterpretation"/>
					<tiles:putAttribute name="description_code" value="crit_eu_text_guilty_misinterpretation"/>
					<tiles:putAttribute name="selfCleaning" value="false"/>
				</tiles:insertDefinition>

			</div>
			</div>
		</div>
	
		<div class="col-md-7">&nbsp;</div>
		<div class="col-md-5">
			<div class="btn-group">
				<button type="submit" name="prev" class="btn btn-default btn-lg">
					<i class="fa fa-arrow-circle-o-left"></i>
					<span data-i18n="previous"><s:message code="previous"/></span>
				</button>
				<a class="btn btn-default btn-lg" href="${pageContext.request.contextPath}/welcome">
				<i class="fa fa-times-circle"></i>
				<span data-i18n="cancel"><s:message code="cancel"/></span>
				</a>

				<button type="submit" name="next" class="btn btn-default btn-lg">
					<i class="fa fa-arrow-circle-o-right"></i>
					<span data-i18n="next"><s:message code="next"/></span>
				</button>
			</div>
		</div>    
	</div>
	
	</form:form>
	
