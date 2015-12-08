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
			<div data-i18n="crit_top_title_grounds_criminal_conv" class="espd-panel-heading" data-toggle="collapse" data-target="#ca-criminal-convictions-section">
				<s:message code="crit_top_title_grounds_criminal_conv"/>
			</div>
            <div id="ca-criminal-convictions-section" class="collapse in">
                <div class="espd-panel-body panel-body">
                        <span data-i18n="crit_eu_main_title_grounds_criminal_conv">
                                <s:message code='crit_eu_main_title_grounds_criminal_conv'/>
                        </span>
                        <span data-i18n="crit_eu_main_tooltip_grounds_criminal_conv" data-toggle="tooltip" title="<s:message code='crit_eu_main_tooltip_grounds_criminal_conv'/>"></span>

                        <tiles:insertDefinition name="checkCriterion">
                            <tiles:putAttribute name="field"  value="criminalConvictions"/>
							<tiles:putAttribute name="title_code" value="crit_eu_title_grounds_criminal_conv"/>
							<tiles:putAttribute name="description_code" value="crit_eu_text_grounds_criminal_conv"/>
                        </tiles:insertDefinition>

                        <tiles:insertDefinition name="checkCriterion">
                            <tiles:putAttribute name="field" value="corruption"/>
							<tiles:putAttribute name="title_code" value="crit_eu_title_corruption"/>
							<tiles:putAttribute name="description_code" value="crit_eu_text_corruption"/>
                        </tiles:insertDefinition>

                        <tiles:insertDefinition name="checkCriterion">
                            <tiles:putAttribute name="field" value="fraud"/>
							<tiles:putAttribute name="title_code" value="crit_eu_title_fraud"/>
							<tiles:putAttribute name="description_code" value="crit_eu_text_fraud"/>	
                        </tiles:insertDefinition>

                        <tiles:insertDefinition name="checkCriterion">
                            <tiles:putAttribute name="field" value="terroristOffences"/>
							<tiles:putAttribute name="title_code" value="crit_eu_title_terrorist"/>
							<tiles:putAttribute name="description_code" value="crit_eu_text_terrorist"/>
                        </tiles:insertDefinition>

                        <tiles:insertDefinition name="checkCriterion">
                            <tiles:putAttribute name="field" value="moneyLaundering"/>
							<tiles:putAttribute name="title_code" value="crit_eu_title_money_laundering"/>
							<tiles:putAttribute name="description_code" value="crit_eu_text_money_laundering"/>
                        </tiles:insertDefinition>

                        <tiles:insertDefinition name="checkCriterion">
                            <tiles:putAttribute name="field" value="childLabour"/>
							<tiles:putAttribute name="title_code" value="crit_eu_title_child_labour"/>
							<tiles:putAttribute name="description_code" value="crit_eu_text_child_labour"/>
                        </tiles:insertDefinition>
                </div>
            </div>
		</div>
	
		<div class="panel panel-default espd-panel">
		
			<span data-i18n="crit_top_title_grounds_payment_taxes" class="espd-panel-heading" data-toggle="collapse" data-target="#ca-payment-of-taxes-section">
				<s:message code="crit_top_title_grounds_payment_taxes"/>
			</span>
			<div id="ca-payment-of-taxes-section" class="collapse in">
                <div class="espd-panel-body panel-body">
                    <span data-i18n="crit_eu_main_title_payment_taxes" class="aligned">
                            <s:message code='crit_eu_main_title_payment_taxes'/>
                    </span>
                    <span data-i18n="crit_eu_main_tooltip_payment_taxes" data-toggle="tooltip" title="<s:message code='crit_eu_main_tooltip_payment_taxes'/>"></span>

                    <tiles:insertDefinition name="checkCriterion">
                        <tiles:putAttribute name="field" value="paymentTaxes"/>
						<tiles:putAttribute name="title_code" value="crit_eu_title_payment_taxes"/>
						<tiles:putAttribute name="description_code" value="crit_eu_text_payment_taxes"/>
                    </tiles:insertDefinition>

                    <tiles:insertDefinition name="checkCriterion">
                        <tiles:putAttribute name="field" value="paymentSocialSecurity"/>
						<tiles:putAttribute name="title_code" value="crit_eu_title_payment_social_security"/>
						<tiles:putAttribute name="description_code" value="crit_eu_text_payment_social_security"/>
               	    </tiles:insertDefinition>

                </div>
            </div>
		</div>
	
		<div class="panel panel-default espd-panel">
			<div data-i18n="crit_top_title_insolvency_conflicts" class="espd-panel-heading" data-toggle="collapse" data-target="#ca-insolvency-section">
				<s:message code="crit_top_title_insolvency_conflicts"/>
			</div>
            <div id="ca-insolvency-section" class="collapse in">
                <div class="espd-panel-body panel-body">
                    <span data-i18n="crit_eu_main_breaching_obligations" class="aligned">
                        <s:message code='crit_eu_main_breaching_obligations'/>
                    </span>
                    <span data-i18n="crit_eu_main_tooltip_breaching_obligations" data-toggle="tooltip" title="<s:message code='crit_eu_main_tooltip_breaching_obligations'/>"></span>
                    <tiles:insertDefinition name="checkCriterion">
                        <tiles:putAttribute name="field" value="breachingObligations"/>
                        <tiles:putAttribute name="title_code" value="crit_eu_title_breaching_obligations"/>
                        <tiles:putAttribute name="description_code" value="crit_eu_text_breaching_obligations"/>
                    </tiles:insertDefinition>

                    <tiles:insertDefinition name="checkCriterion">
                        <tiles:putAttribute name="field" value="bankruptcy"/>
                        <tiles:putAttribute name="title_code" value="crit_eu_title_bankrupt"/>
                        <tiles:putAttribute name="description_code" value="crit_eu_text_bankrupt"/>
                    </tiles:insertDefinition>
                 
					<tiles:insertDefinition name="checkCriterion">
						<tiles:putAttribute name="field" value="insolvency"/>
						<tiles:putAttribute name="title_code" value="crit_eu_title_insolvency"/>
						<tiles:putAttribute name="description_code" value="crit_eu_text_insolvency"/>
					</tiles:insertDefinition>
					
					<tiles:insertDefinition name="checkCriterion">
						<tiles:putAttribute name="field" value="arrangementWithCreditors"/>
						<tiles:putAttribute name="title_code" value="crit_eu_title_arrangement_creditors"/>
						<tiles:putAttribute name="description_code" value="crit_eu_text_arrangement_creditors"/>
					</tiles:insertDefinition>
					
					<tiles:insertDefinition name="checkCriterion">
						<tiles:putAttribute name="field" value="analogousSituation"/>
						<tiles:putAttribute name="title_code" value="crit_eu_title_national_bankruptcy"/>
						<tiles:putAttribute name="description_code" value="crit_eu_text_national_bankruptcy"/>
					</tiles:insertDefinition>
					
					<tiles:insertDefinition name="checkCriterion">
						<tiles:putAttribute name="field" value="assetsAdministeredByLiquidator"/>
						<tiles:putAttribute name="title_code" value="crit_eu_title_liquidator"/>
						<tiles:putAttribute name="description_code" value="crit_eu_text_liquidator"/>
					</tiles:insertDefinition>
					
					<tiles:insertDefinition name="checkCriterion">
						<tiles:putAttribute name="field" value="businessActivitiesSuspended"/>
						<tiles:putAttribute name="title_code" value="crit_eu_title_suspended_business"/>
						<tiles:putAttribute name="description_code" value="crit_eu_text_suspended_business"/>
					</tiles:insertDefinition>
				
                    <tiles:insertDefinition name="checkCriterion">
                        <tiles:putAttribute name="field" value="guiltyGrave"/>
						<tiles:putAttribute name="title_code" value="crit_eu_title_guilty_misconduct"/>
						<tiles:putAttribute name="description_code" value="crit_eu_text_guilty_misconduct"/>
                    </tiles:insertDefinition>

                    <tiles:insertDefinition name="checkCriterion">
                        <tiles:putAttribute name="field" value="conflictInterest"/>
                        <tiles:putAttribute name="title_code" value="crit_eu_title_conflict_interest"/>
                        <tiles:putAttribute name="description_code" value="crit_eu_text_conflict_interest"/>
                    </tiles:insertDefinition>

                    <tiles:insertDefinition name="checkCriterion">
                        <tiles:putAttribute name="field" value="involvementPreparationProcurement"/>
                        <tiles:putAttribute name="title_code" value="crit_eu_title_involvment"/>
                        <tiles:putAttribute name="description_code" value="crit_eu_text_involvment"/>
                    </tiles:insertDefinition>

                    <tiles:insertDefinition name="checkCriterion">
                        <tiles:putAttribute name="field" value="earlyTermination"/>
                        <tiles:putAttribute name="title_code" value="crit_eu_title_early_termination"/>
                        <tiles:putAttribute name="description_code" value="crit_eu_text_early_termination"/>
                    </tiles:insertDefinition>

                    <tiles:insertDefinition name="checkCriterion">
                        <tiles:putAttribute name="field" value="guiltyMisinterpretation"/>
                        <tiles:putAttribute name="title_code" value="crit_eu_title_guilty_misinterpretation"/>
                        <tiles:putAttribute name="description_code" value="crit_eu_text_guilty_misinterpretation"/>
                    </tiles:insertDefinition>

                </div>
            </div>
		</div>

        <tiles:insertDefinition name="footerButtons">
        </tiles:insertDefinition>
	</div>
	
	</form:form>
	
