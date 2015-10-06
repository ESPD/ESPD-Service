<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<div class="container espd-container">
	<div class="panel-default">

		<ul class="nav nav-pills nav-wizard nav-justified">
			<li class="active"><a href="#"><i class="fa fa-random"></i>&nbsp;<span class="label_progress_start"><s:message code='progress_start'/></span></a><div class="nav-arrow"></div></li>
			<li><div class="nav-wedge"></div><a href="#"><i class="fa fa-university"></i>&nbsp;<span class="label_progress_procedure"><s:message code='progress_procedure'/></span></a><div class="nav-arrow"></div></li>
			<li><div class="nav-wedge"></div><a href="#"><i class="fa fa-exclamation"></i>&nbsp;<span class="label_progress_exclusion"><s:message code='progress_exclusion'/></span></a><div class="nav-arrow"></div></li>
			<li><div class="nav-wedge"></div><a href="#"><i class="fa fa-check-circle"></i>&nbsp;<span class="label_progress_selection"><s:message code='progress_selection'/></span></a><div class="nav-arrow"></div></li>
			<li><div class="nav-wedge"></div><a href="#"><i class="fa fa-download"></i>&nbsp;<span class="label_progress_finish"><s:message code='progress_finish'/></span></a></li>
		</ul>
	
		<div class="paragraph">
			<h2 class="label_filter_header"><s:message code='filter_header'/></h2>
		</div>

		<div class="alert alert-espd-info">
			<ul class="fa-ul">
			<li>
				<i class="info-label fa fa-info-circle fa-lg fa-li"></i>
				<span class="label_filter_alert"><s:message code='filter_alert'/></span>
			</li>
			</ul>
		</div>
		
		<div class="paragraph">
			<h3 class="label_filter_who_are_you"><s:message code='filter_who_are_you'/></h3>
			
			<div id="which-entity">
				<div class="radio">
					<input type="radio" name="whoareyou" id="look1" class="which-entity-radio" value="information">
					<label for="look1"><span class="label_filter_i_am_ca"><s:message code='filter_i_am_ca'/></span></label>
				</div>
				<div class="radio">
					<input type="radio" name="whoareyou" id="look2" class="which-entity-radio" value="help">
					<label for="look2"><span class="label_filter_i_am_eop"><s:message code='filter_i_am_eop'/></span></label>
				</div>
			</div>
			
			<h3 id="question" class="label_filter_what_you_do"><s:message code='filter_what_you_do'/></h3>
			
			<div id="create-espd">
				<div class="radio">
					<label><input type="radio" name="action" class="create-espd-radio" value="create"><span class="label_filter_create_espd"><s:message code='filter_create_espd'/></span></label>
				</div>
				<div class="radio">
					<label><input type="radio" name="action" class="create-espd-radio" value="reuse"><span class="label_filter_reuse_espd"><s:message code='filter_reuse_espd'/></span></label>
				</div>
				<div class="radio">
					<label><input type="radio" name="action" class="create-espd-radio" value="overview"><span class="label_filter_overview_espds"><s:message code='filter_overview_espds'/></span></label>
				</div>
				<div class="radio">
					<label><input type="radio" name="action" class="create-espd-radio" value="review"><span class="label_filter_review_espd"><s:message code='filter_review_espd'/></span></label>
				</div>
			</div>
			
			<div id="eo_actions">

				<div class="labelIndent">
					<div>
						<div class="linkHand aligned">
							<label for="importESPD" class="k-button fa fa-upload hoverable"></label>
						</div>

						<div class="aligned">
							<label for="court1" class="labelTextItem PAGE1_Q3_1">Import
								ESPD</label>
						</div>
						<div id="toolTipProfile6" class="aligned"
							class="tooltip tooltipinfoa">
							<span class="should-have-tooltip"><label
								class="fa fa-info-circle tooltiper"></label></span>
						</div>

						<input style="display: none" type="file" name="importESPD"
							id="importESPD"> <input style="display: none" type="file"
							name="openExcel" id="openExcel" multiple>

						<div style="display: none" id="importPart1"></div>

					</div>
					<div style="margin-top: 1em;"></div>
					<div>
						<div class="linkHand aligned">
							<label for="reuseESPD" class="k-button fa fa-upload hoverable"></label>
						</div>

						<div class="aligned">
							<label for="court2" class="labelTextItem label_filter_reuse_espd">
								<s:message code='filter_reuse_espd'/>
							</label>
						</div>
						<div id="toolTipProfile7" class="aligned"
							class="tooltip tooltipinfoa">
							<span class="should-have-tooltip"><label
								class="fa fa-info-circle tooltiper"></label></span>
						</div>

						<input style="display: none" type="file" name="reuseESPD"
							id="reuseESPD">
					</div>

					<div style="display: none" id="importPart2">

						<input class="labelTextItem k-textbox input-long" type="text"
							name="textOfFile2" id="textOfFile2" readonly>
					</div>

				</div>
			</div>
			
		</div>
	</div>

	<div class="col-md-8">&nbsp;</div>
	<div class="col-md-4">
		<div class="btn-group">
			<a id="cancel" class="btn btn-default btn-lg" href="/espd/welcome">
				<i class="fa fa-times-circle"></i>
				<span class="label_CANCEL"><s:message code="CANCEL"/></span>
			</a>
			<a id="nextStep" class="btn btn-default btn-lg" href="/espd/createca">
				<i class="fa fa-arrow-circle-o-right"></i>
				<span class="label_NEXT"><s:message code="NEXT"/></span>
			</a>
		</div>
	</div>
</div>

<script>
$(document).ready(function() {

	initDefaultVariables();
	restartHomepage();
	initPageHandlers();

});

function initDefaultVariables() {

	eo_actions = $("#eo_actions");	
	whichEntity = $("#which-entity");	
	whichEntityRadio = $(".which-entity-radio");
	createESPD = $("#create-espd");
	createESPDRadio = $(".create-espd-radio");
	publicAuthority = $("#public-authority");
	publicAuthorityRadio = $(".public-authority-radio");
	EOActionsRadio = $(".eo_actions-radio");	
	nextStep = $("#nextStep");	
	cancel = $("#cancel");	
	question = $("#question");
}

function enableButton() {
	nextStep.removeClass("k-state-disabled");
	nextStep.removeAttr("disabled");
}

function disableButton() {
	nextStep.addClass("k-state-disabled");
	nextStep.attr("disabled", "disabled");
}

function showAuthority() {

	$("#look2").prop('checked', true);
	EOActionsRadio.removeAttr("checked");
	eo_actions.show();
	createESPD.hide();
	EOActionsRadio.removeAttr("checked");
	publicAuthorityRadio.removeAttr("checked");
	publicAuthority.show();
	question.show();	
}

function setInformation() {
	cancel.show();
    nextStep.show();
	$("#look1").prop('checked', true);
	whichEntity.val("information");
	createESPD.show();
	createESPDRadio.removeAttr("checked");
	eo_actions.hide();
	question.show();
	publicAuthority.hide();
}

function showCourt() {
	cancel.show();

	$("#look2").prop('checked', true);
	whichEntity.val("help");
	eo_actions.show();
	$("#court1").prop('checked', true);
	nextStep.show();
	enableButton();
}

function restartHomepage() {
	disableButton();
	whichEntity.show();	
	createESPD.hide();
	eo_actions.hide();
	whichEntityRadio.removeAttr("checked");
	createESPDRadio.removeAttr("checked");
	EOActionsRadio.removeAttr("checked");
	publicAuthorityRadio.removeAttr("checked");
	publicAuthority.hide();
	question.hide();
}

function initPageHandlers(){

	whichEntityRadio.click(function() {
		cancel.show();

		if ($(this).val() == "information") {
			setInformation();
			disableButton();

		} else {
			disableButton();
			showAuthority();
		}
	});

	createESPDRadio.click(function() {
		if ($(this).val() == "create") {
			enableButton();
		} else if ($(this).val() == "reuse"){
			disableButton();
		} else if ($(this).val() == "overview") {
			enableButton();
		} else if ($(this).val() == "review") {
			enableButton();
		}
		
	});

	EOActionsRadio.click(function() {
		if ($(this).val() == "import") {		
			disableButton();			
		} else {		
			disableButton();
		}
	});

	publicAuthorityRadio.click(function() {
		if ($(this).val() == "no") {		
			enableButton();
		} else {		
			disableButton();
		}
	});
}

</script>
