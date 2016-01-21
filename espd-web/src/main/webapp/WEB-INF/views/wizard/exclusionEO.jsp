<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<form:form id="espdform" role="form" class="form-horizontal" method="post" commandName="espd" data-toggle="validator">

    <tiles:insertDefinition name="viewChangeRole">
        <tiles:putAttribute name="agent" value="EO"/>
        <tiles:putAttribute name="page" value="exclusionCA"/>
    </tiles:insertDefinition>
	
	<div class="panel-default">
	
        <tiles:insertDefinition name="progress">
			<tiles:putAttribute name="exclusion" value="true"/>
        </tiles:insertDefinition>

		<div class="paragraph">
			<h2>${span18n("createcaexcl_header")}</h2>
		</div>
		
		<div class="panel panel-default espd-panel">

			<div class="espd-panel-heading" data-toggle="collapse" data-target="#criminal_conv">
				{$span18n("crit_top_title_grounds_criminal_conv")}
			</div>

			<div id="criminal_conv" class="collapse in">
			<div class="espd-panel-body panel-body">
			
					<div class="">
						<span data-i18n="crit_eu_main_title_grounds_criminal_conv_eo" style="font-weight: bold;">
							<s:message code='crit_eu_main_title_grounds_criminal_conv_eo'/>
						</span>
					</div>

					<tiles:insertDefinition name="criminalFormCriterion">
						<tiles:putAttribute name="field" value="criminalConvictions"/>
						<tiles:putAttribute name="title_code" value="crit_eu_title_grounds_criminal_conv"/>
						<tiles:putAttribute name="description_code" value="crit_eu_text_grounds_criminal_conv"/>
					</tiles:insertDefinition>

					<tiles:insertDefinition name="criminalFormCriterion">
						<tiles:putAttribute name="field" value="corruption"/>
						<tiles:putAttribute name="title_code" value="crit_eu_title_corruption"/>
						<tiles:putAttribute name="description_code" value="crit_eu_text_corruption"/>
					</tiles:insertDefinition>
					
					<tiles:insertDefinition name="criminalFormCriterion">
						<tiles:putAttribute name="field" value="fraud"/>
						<tiles:putAttribute name="title_code" value="crit_eu_title_fraud"/>
						<tiles:putAttribute name="description_code" value="crit_eu_text_fraud"/>
					</tiles:insertDefinition>
					
					<tiles:insertDefinition name="criminalFormCriterion">
						<tiles:putAttribute name="field" value="terroristOffences"/>
						<tiles:putAttribute name="title_code" value="crit_eu_title_terrorist"/>
						<tiles:putAttribute name="description_code" value="crit_eu_text_terrorist"/>
					</tiles:insertDefinition>
					
					<tiles:insertDefinition name="criminalFormCriterion">
						<tiles:putAttribute name="field" value="moneyLaundering"/>
						<tiles:putAttribute name="title_code" value="crit_eu_title_money_laundering"/>
						<tiles:putAttribute name="description_code" value="crit_eu_text_money_laundering"/>
					</tiles:insertDefinition>
					
					<tiles:insertDefinition name="criminalFormCriterion">
						<tiles:putAttribute name="field" value="childLabour"/> 
						<tiles:putAttribute name="title_code" value="crit_eu_title_child_labour"/>
						<tiles:putAttribute name="description_code" value="crit_eu_text_child_labour"/>
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
					<span data-i18n="crit_eu_main_title_payment_taxes_eo" style="font-weight: bold;">
						<s:message code='crit_eu_main_title_payment_taxes_eo'/>
					</span>
				</div>

				<tiles:insertDefinition name="taxFormCriterion">
					<tiles:putAttribute name="field" value="paymentTaxes"/>
					<tiles:putAttribute name="title_code" value="crit_eu_title_payment_taxes"/>
					<tiles:putAttribute name="description_code" value="crit_eu_text_payment_taxes"/>
				</tiles:insertDefinition>
				
				<tiles:insertDefinition name="taxFormCriterion">
					<tiles:putAttribute name="field" value="paymentSocialSecurity"/>
					<tiles:putAttribute name="title_code" value="crit_eu_title_payment_social_security"/>
					<tiles:putAttribute name="description_code" value="crit_eu_text_payment_social_security"/>
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
					<span data-i18n="crit_eu_main_breaching_obligations_eo" style="font-weight: bold;">
						<s:message code='crit_eu_main_breaching_obligations_eo'/>
					</span>
				</div>
					
				<tiles:insertDefinition name="simpleFormCriterion">
					<tiles:putAttribute name="field" value="breachingObligationsEnvironmental"/>
					<tiles:putAttribute name="title_code" value="crit_eu_title_breaching_obligations_environmental"/>
					<tiles:putAttribute name="description_code" value="crit_eu_text_breaching_obligations_environmental"/>
					<tiles:putAttribute name="availableElectronically" value="false"/>
				</tiles:insertDefinition>

                <tiles:insertDefinition name="simpleFormCriterion">
                    <tiles:putAttribute name="field" value="breachingObligationsSocial"/>
                    <tiles:putAttribute name="title_code" value="crit_eu_title_breaching_obligations_social"/>
                    <tiles:putAttribute name="description_code" value="crit_eu_text_breaching_obligations_social"/>
                    <tiles:putAttribute name="availableElectronically" value="false"/>
                </tiles:insertDefinition>

                <tiles:insertDefinition name="simpleFormCriterion">
                    <tiles:putAttribute name="field" value="breachingObligationsLabour"/>
                    <tiles:putAttribute name="title_code" value="crit_eu_title_breaching_obligations_labour"/>
                    <tiles:putAttribute name="description_code" value="crit_eu_text_breaching_obligations_labour"/>
                    <tiles:putAttribute name="availableElectronically" value="false"/>
                </tiles:insertDefinition>
				
				<tiles:insertDefinition name="reasonsNeverlessPerformForm">
					<tiles:putAttribute name="field" value="bankruptcy"/>
					<tiles:putAttribute name="title_code" value="crit_eu_title_bankrupt"/>
					<tiles:putAttribute name="description_code" value="crit_eu_text_bankrupt"/>
					<tiles:putAttribute name="selfCleaning" value="false"/>
				</tiles:insertDefinition>
				
				<tiles:insertDefinition name="reasonsNeverlessPerformForm">
					<tiles:putAttribute name="field" value="insolvency"/>
					<tiles:putAttribute name="title_code" value="crit_eu_title_insolvency"/>
					<tiles:putAttribute name="description_code" value="crit_eu_text_insolvency"/>
					<tiles:putAttribute name="selfCleaning" value="false"/>
				</tiles:insertDefinition>
				
				<tiles:insertDefinition name="reasonsNeverlessPerformForm">
					<tiles:putAttribute name="field" value="arrangementWithCreditors"/>
					<tiles:putAttribute name="title_code" value="crit_eu_title_arrangement_creditors"/>
					<tiles:putAttribute name="description_code" value="crit_eu_text_arrangement_creditors"/>
					<tiles:putAttribute name="selfCleaning" value="false"/>
				</tiles:insertDefinition>
				
				<tiles:insertDefinition name="reasonsNeverlessPerformForm">
					<tiles:putAttribute name="field" value="analogousSituation"/>
					<tiles:putAttribute name="title_code" value="crit_eu_title_national_bankruptcy"/>
					<tiles:putAttribute name="description_code" value="crit_eu_text_national_bankruptcy"/>
					<tiles:putAttribute name="selfCleaning" value="false"/>
				</tiles:insertDefinition>
				
				<tiles:insertDefinition name="reasonsNeverlessPerformForm">
					<tiles:putAttribute name="field" value="assetsAdministeredByLiquidator"/>
					<tiles:putAttribute name="title_code" value="crit_eu_title_liquidator"/>
					<tiles:putAttribute name="description_code" value="crit_eu_text_liquidator"/>
					<tiles:putAttribute name="selfCleaning" value="false"/>
				</tiles:insertDefinition>
				
				<tiles:insertDefinition name="reasonsNeverlessPerformForm">
					<tiles:putAttribute name="field" value="businessActivitiesSuspended"/>
					<tiles:putAttribute name="title_code" value="crit_eu_title_suspended_business"/>
					<tiles:putAttribute name="description_code" value="crit_eu_text_suspended_business"/>
					<tiles:putAttribute name="selfCleaning" value="false"/>
				</tiles:insertDefinition>
				
				<tiles:insertDefinition name="simpleFormCriterion">
					<tiles:putAttribute name="field" value="agreementsWithOtherEO"/>
					<tiles:putAttribute name="title_code" value="crit_eu_title_agreement_economic"/>
					<tiles:putAttribute name="description_code" value="crit_eu_text_agreement_economic"/>
					<tiles:putAttribute name="availableElectronically" value="false"/>
				</tiles:insertDefinition>

                <tiles:insertDefinition name="simpleFormCriterion">
                    <tiles:putAttribute name="field" value="guiltyGrave"/>
                    <tiles:putAttribute name="title_code" value="crit_eu_title_guilty_misconduct"/>
                    <tiles:putAttribute name="description_code" value="crit_eu_text_guilty_misconduct"/>
                    <tiles:putAttribute name="availableElectronically" value="false"/>
                </tiles:insertDefinition>
				
				<tiles:insertDefinition name="simpleFormCriterion">
					<tiles:putAttribute name="field" value="conflictInterest"/>
					<tiles:putAttribute name="title_code" value="crit_eu_title_conflict_interest"/>
					<tiles:putAttribute name="description_code" value="crit_eu_text_conflict_interest"/>
				</tiles:insertDefinition>
				
				<tiles:insertDefinition name="simpleFormCriterion">
					<tiles:putAttribute name="field" value="involvementPreparationProcurement"/>
					<tiles:putAttribute name="title_code" value="crit_eu_title_involvement"/>
					<tiles:putAttribute name="description_code" value="crit_eu_text_involvement"/>
					<tiles:putAttribute name="availableElectronically" value="false"/>
					<tiles:putAttribute name="selfCleaning" value="false"/>
				</tiles:insertDefinition>
				
				<tiles:insertDefinition name="simpleFormCriterion">
					<tiles:putAttribute name="field" value="earlyTermination"/>
					<tiles:putAttribute name="title_code" value="crit_eu_title_early_termination"/>
					<tiles:putAttribute name="description_code" value="crit_eu_text_early_termination"/>
					<tiles:putAttribute name="availableElectronically" value="false"/>
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
		
		<div class="panel panel-default espd-panel">
			<div data-i18n="crit_top_title_purely_national" class="espd-panel-heading" data-toggle="collapse" data-target="#ca-insolvency-section">
				 <s:message code='crit_top_title_purely_national'/>
			</div>
            <div id="ca-insolvency-section" class="collapse in">
                <div class="espd-panel-body panel-body">
                    <span data-i18n="crit_eu_main_breaching_obligations" class="aligned" style="font-weight: bold;">
                        <s:message code='crit_eu_main_purely_national'/>
                    </span>

                    <tiles:insertDefinition name="formCriterion">
                        <tiles:putAttribute name="field" value="purelyNationalGrounds"/>
                        <tiles:putAttribute name="title_code" value="crit_eu_title_purely_national"/>
                        <tiles:putAttribute name="description_code" value="crit_eu_text_purely_national"/>
						<tiles:putAttribute name="availableElectronically" value="true"/>
						<tiles:putAttribute name="selfCleaning" value="true"/>
                    </tiles:insertDefinition>
                    
                </div>
            </div>
		</div>

        <tiles:insertDefinition name="footerButtons">
        </tiles:insertDefinition>
	</div>
	
	</form:form>
	
