<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<div class="container espd-container">

	<div class="panel-default">
	
		<div class="panel panel-default no-border">	
			<ul class="nav nav-pills nav-wizard nav-justified">
			    <li class="active"><a href="#"><i class="fa fa-random"></i>&nbsp;<span class="label_progress_start"><s:message code='progress_start'/></span></a><div class="nav-arrow"></div></li>
			    <li class="active"><div class="nav-wedge"></div><a href="#"><i class="fa fa-university"></i>&nbsp;<span class="label_progress_procedure"><s:message code='progress_procedure'/></span></a><div class="nav-arrow"></div></li>
			    <li class="active"><div class="nav-wedge"></div><a href="#"><i class="fa fa-exclamation"></i>&nbsp;<span class="label_progress_exclusion"><s:message code='progress_exclusion'/></span></a><div class="nav-arrow"></div></li>
			    <li><div class="nav-wedge"></div><a href="#"><i class="fa fa-check-circle"></i>&nbsp;<span class="label_progress_selection"><s:message code='progress_selection'/></span></a><div class="nav-arrow"></div></li>
			    <li><div class="nav-wedge"></div><a href="#"><i class="fa fa-download"></i>&nbsp;<span class="label_progress_finish"><s:message code='progress_finish'/></span></a></li>
			</ul>
		</div>

		<div class="paragraph">
			<h2 class="label_createcaexcl_header"><s:message code="createcaexcl_header"/></h2>
		</div>
		
		<div class="panel panel-default espd-panel">
			<div class="espd-panel-heading label_createcaexcl_grounds_criminal_conv">
				<s:message code="crit_top_title_grounds_criminal_conv"/>
			</div>
			<div class="panel-body">
				<div>
					<div class="text-element">
						<div class="aligned">
							<input id="PT3_A_1_C" class="ce-ec-radio checkboxer k-state-disabled" type="checkbox" name="PT3_A_1_C" checked="checked" disabled="disabled">
							<span class="label_crit_eu_title_grounds_criminal_conv">
								<s:message code='crit_eu_title_grounds_criminal_conv'/>
							</span>
						</div>
					</div>
					<div class="text-element">	
						<div class="aligned">
							<input id="PT3_A_2_C" class="ce-ec-radio checkboxer k-state-disabled" type="checkbox" name="PT3_A_2_C" checked="checked" disabled="disabled">
							<span class="label_crit_eu_title_corruption">
								<s:message code='crit_eu_title_corruption'/>
							</span>
						</div>
					</div>
					<div class="text-element">	
						<div class="aligned">
							<input id="PT3_A_3_C" class="ce-ec-radio checkboxer k-state-disabled" type="checkbox" name="PT3_A_3_C" checked="checked" disabled="disabled">
							<span class="label_crit_eu_title_fraud">
								<s:message code='crit_eu_title_fraud'/>
							</span>
						</div>
					</div>
					<div class="text-element">	
						<div class="aligned">
							<input id="PT3_A_4_C" class="ce-ec-radio checkboxer k-state-disabled" type="checkbox" name="PT3_A_4_C" checked="checked" disabled="disabled">
							<span class="label_crit_eu_title_terrorist">
								<s:message code='crit_eu_title_terrorist'/>
							</span>
						</div>
					</div>
					<div class="text-element">	
						<div class="aligned">
							<input id="PT3_A_5_C" class="ce-ec-radio checkboxer k-state-disabled" type="checkbox" name="PT3_A_5_C" checked="checked" disabled="disabled">
							<span class="label_crit_eu_title_money_laundering">
								<s:message code='crit_eu_title_money_laundering'/>
							</span>
						</div>
					</div>
					<div class="text-element">
						<div class="aligned">
							<input id="PT3_A_6_C" class="ce-ec-radio checkboxer k-state-disabled" type="checkbox" name="PT3_A_6_C" checked="checked" disabled="disabled">
							<span class="label_crit_eu_title_child_labour">
								<s:message code='crit_eu_title_child_labour'/>
							</span>
						</div>
					</div>						
				</div>
			</div>
		</div>
	
		<div class="panel panel-default espd-panel">
			<div class="espd-panel-heading label_createcaexcl_grounds_payment_taxes">
				<s:message code="crit_top_title_grounds_payment_taxes"/>
			</div>
			<div class="panel-body">
				<div class="text-element">
					<div class="aligned">
						<input id="PT3_B_1_C" class="ce-ec-radio checkboxer k-state-disabled" type="checkbox" name="PT3_B_1_C" checked="checked" disabled="disabled">
							<span class="label_crit_eu_title_payment_taxes">
								<s:message code='crit_eu_title_payment_taxes'/>
							</span>
					</div>		
				</div>
				<div class="text-element">
					<div class="aligned">
						<input id="PT3_B_2_C" class="ce-ec-radio checkboxer k-state-disabled" type="checkbox" name="PT3_B_2_C" checked="checked" disabled="disabled">
							<span class="label_crit_eu_title_payment_social_security">
								<s:message code='crit_eu_title_payment_social_security'/>
							</span>
					</div>		
				</div>
			</div>
		</div>
	
		<div class="panel panel-default espd-panel">
			<div class="espd-panel-heading label_createcaexcl_insolvency_conflicts">
				<s:message code="crit_top_title_insolvency_conflicts"/>
			</div>
			<div class="panel-body">
				<div class="text-element">
					<div class="aligned">
						<input id="PT3_C_1_C" class="ce-ec-radio checkboxer k-state-disabled" type="checkbox" name="PT3_C_1_C" checked="checked" disabled="disabled">
							<span class="label_crit_eu_title_breaching_obligations">
								<s:message code='crit_eu_title_breaching_obligations'/>
							</span>
					</div>		
				</div>
				
				<div class="text-element">
					<div class="aligned">
						<input id="PT3_C_2_C" class="ce-ec-radio checkboxer k-state-disabled" type="checkbox" name="PT3_C_2_C" checked="checked" disabled="disabled">
							<span class="label_crit_eu_title_bankrupt">
								<s:message code='crit_eu_title_bankrupt'/>
							</span>
					</div>		
				</div>
				
				<div class="text-element">
					<div class="aligned">
						<input id="PT3_C_3_C" class="ce-ec-radio checkboxer k-state-disabled" type="checkbox" name="PT3_C_3_C" checked="checked" disabled="disabled">
							<span class="label_crit_eu_title_guilty_misconduct">
								<s:message code='crit_eu_title_guilty_misconduct'/>
							</span>
					</div>		
				</div>
				
				<div class="text-element">
					<div class="aligned">
						<input id="PT3_C_4_C" class="ce-ec-radio checkboxer k-state-disabled" type="checkbox" name="PT3_C_4_C" checked="checked" disabled="disabled">
							<span class="label_crit_eu_title_agreement_economic">
								<s:message code='crit_eu_title_agreement_economic'/>
							</span>
					</div>		
				</div>
				
				<div class="text-element">
					<div class="aligned">
						<input id="PT3_C_5_C" class="ce-ec-radio checkboxer k-state-disabled" type="checkbox" name="PT3_C_5_C" checked="checked" disabled="disabled">
							<span class="label_crit_eu_title_conflict_interest">
								<s:message code='crit_eu_title_conflict_interest'/>
							</span>
					</div>		
				</div>
				
				<div class="text-element">
					<div class="aligned">
						<input id="PT3_C_6_C" class="ce-ec-radio checkboxer k-state-disabled" type="checkbox" name="PT3_C_6_C" checked="checked" disabled="disabled">
							<span class="label_crit_eu_title_involvment">
								<s:message code='crit_eu_title_involvment'/>
							</span>
					</div>		
				</div>
				
				<div class="text-element">
					<div class="aligned">
						<input id="PT3_C_7_C" class="ce-ec-radio checkboxer k-state-disabled" type="checkbox" name="PT3_C_7_C" checked="checked" disabled="disabled">
							<span class="label_crit_eu_title_early_termination">
								<s:message code='crit_eu_title_early_termination'/>
							</span>
					</div>		
				</div>
				
				<div class="text-element">
					<div class="aligned">
						<input id="PT3_C_8_C" class="ce-ec-radio checkboxer k-state-disabled" type="checkbox" name="PT3_C_8_C" checked="checked" disabled="disabled">
							<span class="label_crit_eu_title_guilty_misinterpretation">
								<s:message code='crit_eu_title_guilty_misinterpretation'/>
							</span>
					</div>		
				</div>
				
			</div>
		</div>
	
		<div class="col-md-7">&nbsp;</div>
		<div class="col-md-5">
			<div class="btn-group">
				<a class="btn btn-default btn-lg" href="/espd/createca">
				<i class="fa fa-arrow-circle-o-left"></i>
				<span class="label_PREV"><s:message code="PREV"/></span>
				</a>
				<a class="btn btn-default btn-lg" href="/espd/welcome">
				<i class="fa fa-times-circle"></i>
				<span class="label_CANCEL"><s:message code="CANCEL"/></span>
				</a>
				<a class="btn btn-default btn-lg" href="/espd/createcasel">
				<i class="fa fa-arrow-circle-o-right"></i>
				<span class="label_NEXT"><s:message code="NEXT"/></span>
				</a>
			</div>
		</div>    
	</div>
</div>
