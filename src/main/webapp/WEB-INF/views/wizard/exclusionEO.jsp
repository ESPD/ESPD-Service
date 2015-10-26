<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="container espd-container">

	<form:form id="espdform" role="form" class="form-horizontal" method="post" commandName="espd" data-toggle="validator">
	
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
				(${(param['agent'] == "eo")?"I am EO":"I am CA"})
			</h2>
		</div>
		
		<div class="panel panel-default espd-panel">
			<div data-i18n="createcaexcl_grounds_criminal_conv" class="espd-panel-heading">
				<s:message code="crit_top_title_grounds_criminal_conv"/>
			</div>
			<div class="espd-panel-body panel-body">
			
					<span data-i18n="crit_eu_main_title_grounds_criminal_conv_eo">
						<s:message code='crit_eu_main_title_grounds_criminal_conv_eo'/>
					</span>
			
					<div class="checkbox">
						<label>
							<form:checkbox path="criminalConvictions"/>
							<span data-i18n="crit_eu_title_grounds_criminal_conv">
								<s:message code='crit_eu_title_grounds_criminal_conv'/>
							</span>
						</label>
						<span data-i18n="crit_eu_tooltip_grounds_criminal_conv" data-toggle="tooltip" title="<s:message code='crit_eu_tooltip_grounds_criminal_conv'/>"></span>
					</div>
					<div class="checkbox">
						<label>
							<form:checkbox path="corruption"/>
							<span data-i18n="crit_eu_title_corruption">
								<s:message code='crit_eu_title_corruption'/>
							</span>
						</label>
						<span data-i18n="crit_eu_tooltip_corruption" data-toggle="tooltip" title="<s:message code='crit_eu_tooltip_corruption'/>"></span>
					</div>
					<div class="checkbox">	
						<label>
							<form:checkbox path="fraud"/>
							<span data-i18n="crit_eu_title_fraud">
								<s:message code='crit_eu_title_fraud'/>
							</span>
						</label>
						<span data-i18n="crit_eu_tooltip_fraud" data-toggle="tooltip" title="<s:message code='crit_eu_tooltip_fraud'/>"></span>
					</div>
					<div class="checkbox">	
						<label>
							<form:checkbox path="terroristOffences"/>
							<span data-i18n="crit_eu_title_terrorist">
								<s:message code='crit_eu_title_terrorist'/>
							</span>
						</label>
						<span data-i18n="crit_eu_tooltip_terrorist" data-toggle="tooltip" title="<s:message code='crit_eu_tooltip_terrorist'/>"></span>
					</div>
					<div class="checkbox">	
						<label>
							<form:checkbox path="moneyLaundering"/>
							<span data-i18n="crit_eu_title_money_laundering">
								<s:message code='crit_eu_title_money_laundering'/>
							</span>
						</label>
						<span data-i18n="crit_eu_tooltip_money_laundering" data-toggle="tooltip" title="<s:message code='crit_eu_tooltip_money_laundering'/>"></span>
					</div>
					<div class="checkbox">	
						<label>
							<form:checkbox path="moneyLaundering"/>
							<span data-i18n="crit_eu_title_child_labour">
								<s:message code='crit_eu_title_child_labour'/>
							</span>
						</label>
						<span data-i18n="crit_eu_tooltip_child_labour" data-toggle="tooltip" title="<s:message code='crit_eu_tooltip_child_labour'/>"></span>
					</div>			
			</div>
		</div>
	
		<div class="panel panel-default espd-panel">
			<div data-i18n="createcaexcl_grounds_payment_taxes" class="espd-panel-heading">
				<s:message code="crit_top_title_grounds_payment_taxes"/>
			</div>
			<div class="espd-panel-body panel-body">
			
				<span data-i18n="crit_eu_main_title_payment_taxes_eo">
					<s:message code='crit_eu_main_title_payment_taxes_eo'/>
				</span>
			
				<div class="checkbox">	
					<label>
						<form:checkbox path="paymentTaxes"/>
						<span data-i18n="crit_eu_title_payment_taxes">
							<s:message code='crit_eu_title_payment_taxes'/>
						</span>
					</label>
				</div>			
				<div class="checkbox">	
					<label>
						<form:checkbox path="paymentSocsec"/>
						<span data-i18n="crit_eu_title_payment_social_security">
							<s:message code='crit_eu_title_payment_social_security'/>
						</span>
					</label>
				</div>
			</div>
		</div>
	
		<div class="panel panel-default espd-panel">
			<div data-i18n="createcaexcl_insolvency_conflicts" class="espd-panel-heading">
				<s:message code="crit_top_title_insolvency_conflicts"/>
			</div>
			<div class="espd-panel-body panel-body">

				<span data-i18n="crit_eu_main_breaching_obligations_eo">
					<s:message code='crit_eu_main_breaching_obligations_eo'/>
				</span>

				<div class="checkbox">	
					<label>
						<form:checkbox path="breachingObligations"/>
						<span data-i18n="crit_eu_title_breaching_obligations_eo">
							<s:message code='crit_eu_title_breaching_obligations_eo'/>
						</span>
					</label>
					<span data-i18n="crit_eu_tooltip_breaching_obligations" data-toggle="tooltip" title="<s:message code='crit_eu_tooltip_breaching_obligations'/>"></span>
				</div>		
				<div class="checkbox">	
					<label>
						<form:checkbox path="bankruptSubject"/>
						<span data-i18n="crit_eu_title_bankrupt_eo">
							<s:message code='crit_eu_title_bankrupt_eo'/>
						</span>
					</label>
				</div>		
				<div class="checkbox">	
					<label>
						<form:checkbox path="guiltyGrave"/>
						<span data-i18n="crit_eu_title_guilty_misconduct_eo">
							<s:message code='crit_eu_title_guilty_misconduct_eo'/>
						</span>
					</label>
					<span data-i18n="crit_eu_tooltip_guilty_misconduct_eo" data-toggle="tooltip" title="<s:message code='crit_eu_tooltip_guilty_misconduct_eo'/>"></span>
				</div>		
				<div class="checkbox">	
					<label>
						<form:checkbox path="agreementsEo"/>
						<span data-i18n="crit_eu_title_agreement_economic_eo">
							<s:message code='crit_eu_title_agreement_economic_eo'/>
						</span>
					</label>
				</div>		
				<div class="checkbox">	
					<label>
						<form:checkbox path="conflictInterest"/>
						<span data-i18n="crit_eu_title_conflict_interest_eo">
							<s:message code='crit_eu_title_conflict_interest_eo'/>
						</span>
					</label>
				</div>		
				<div class="checkbox">	
					<label>
						<form:checkbox path="involvementPreparation"/>
						<span data-i18n="crit_eu_title_involvment_eo">
							<s:message code='crit_eu_title_involvment_eo'/>
						</span>
					</label>
				</div>
				<div class="checkbox">	
					<label>
						<form:checkbox path="earlyTermination"/>
						<span data-i18n="crit_eu_title_early_termination_eo">
							<s:message code='crit_eu_title_early_termination_eo'/>
						</span>
					</label>
				</div>
				<div class="checkbox">	
					<label>
						<form:checkbox path="guiltyMisinterpretation"/>
						<span data-i18n="crit_eu_title_guilty_misinterpretation_eo">
							<s:message code='crit_eu_title_guilty_misinterpretation_eo'/>
						</span>
					</label>
				</div>
				
			</div>
		</div>
	
		<div class="col-md-7">&nbsp;</div>
		<div class="col-md-5">
			<div class="btn-group">
				<button type="submit" name="prev" class="btn btn-default btn-lg">
					<i class="fa fa-arrow-circle-o-right"></i>
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
	
</div>
