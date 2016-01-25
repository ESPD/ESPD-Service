<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<script>
$(function() { 
	var country = $("select[name='authority.country']");

    $("*[name='agent']").click(function () {
    	$('#nextBtn').prop('disabled', true);
    	$('#tab-from').removeClass('active');
    	$('#tab-upload').removeClass('active');
    	$('.radioCa').removeAttr('checked');
    	$('[value="empty"]').prop('selected', true);
    	$("input:file").val('');
    });
	$("input:file").change(function (){
		if($(this).val() != '') {
			$('#tab-from').addClass('active');
			$('#nextBtn').prop('disabled', country.val() === '');
		}
	});
	country.change(function() {
    	$('#nextBtn').prop('disabled', $(this).val() === '');
    });
    $("[name=ca_create_espd], [name=eo_import_espd]").click(function () {
    	$('#nextBtn').prop('disabled', country.val() === '');
    });
    $('#nextBtn').prop('disabled', true);
});
</script>

<form:form id="espdform" role="form" class="form-horizontal" method="post" commandName="espd" data-toggle="validator" enctype="multipart/form-data">

	<div class="panel-default">

        <tiles:insertDefinition name="progress">
			<tiles:putAttribute name="start" value="true"/>
        </tiles:insertDefinition>

		<div class="paragraph">
			<h2 data-i18n="filter_header"><s:message code='filter_header'/></h2>
		</div>

		<div class="alert alert-espd-info">
			<ul class="fa-ul">
			<li>
				<i class="info-label fa fa-info-circle fa-lg fa-li"></i>
				<span data-i18n="filter_alert"><s:message code='filter_alert'/></span>
			</li>
			</ul>
		</div>
		
		<div class="paragraph">

			<h3>
				<span data-i18n="filter_who_are_you"><s:message code='filter_who_are_you'/></span>
				<span data-i18n="tooltip_espd_used_both_ca_eo" data-toggle="tooltip" title="<s:message code='tooltip_espd_used_both_ca_eo'/>"></span>
			</h3>
			
			<div class="radio" >
				<label><input id="whoareyou_ca" name="agent" value="ca" class="radiotab" type="radio" href="#tab_ca"><span data-i18n="filter_i_am_ca"><s:message code='filter_i_am_ca'/></span></label>
				<span data-i18n="tooltip_ca_ref_buyer" data-toggle="tooltip" title="<s:message code='tooltip_ca_ref_buyer'/>"></span>
			</div>
			
			<div class="radio">
				<label><input id="whoareyou_eo" name="agent" value="eo" class="radiotab" type="radio" href="#tab_eo"><span data-i18n="filter_i_am_eop"><s:message code='filter_i_am_eop'/></span></label>
				<span data-i18n="tooltip_eo_ref_suppl" data-toggle="tooltip" title="<s:message code='tooltip_eo_ref_suppl'/>"></span>
			</div>

			<div class="tab-content" >

				<div class="tab-pane" id="tab_ca">

					<h3 data-i18n="filter_what_you_do"><s:message code='filter_what_you_do'/></h3>
		
					<div class="radio">
						<label><input name="ca_create_espd" class="radiotab radioCa" type="radio" data-target="#tab-from"><span data-i18n="filter_create_espd"><s:message code='filter_create_espd'/></span></label>
						<span data-i18n="tooltip_ca_can_create_espd" data-toggle="tooltip" title="<s:message code='tooltip_ca_can_create_espd'/>"></span>
					</div>
					<div class="radio">
						<label><input disabled name="action" class="radiotab radioCa" type="radio" data-target="#"><span data-i18n="filter_reuse_espd"><s:message code='filter_reuse_espd'/></span></label>
						<span data-i18n="tooltip_ca_can_import_espd" data-toggle="tooltip" title="<s:message code='tooltip_ca_can_import_espd'/>"></span>
					</div>
					<div class="radio">
						<label><input disabled name="action" class="radiotab radioCa" type="radio" data-target="#"><span data-i18n="filter_review_espd"><s:message code='filter_review_espd'/></span></label>
						<span data-i18n="tooltip_review_espd" data-toggle="tooltip" title="<s:message code='tooltip_review_espd'/>"></span>
					</div>
				</div>

				<div class="tab-pane" id="tab_eo">
					<h3 data-i18n="filter_what_you_do"><s:message code='filter_what_you_do'/></h3>
					<div class="radio">
						<span class="k-button fa fa-upload hoverable"></span>
						<label><input name="eo_import_espd" class="radiotab radioCa" type="radio" data-target="#tab-upload"><span data-i18n="filter_import_espd"><s:message code='filter_import_espd'/></span></label>
						<span data-i18n="tooltip_filter_eo_can_import_espd" data-toggle="tooltip" title="<s:message code='tooltip_filter_eo_can_import_espd'/>"/>
						
					</div>
					
				</div>
			</div>
			<div class="tab-content" >

				<div class="tab-pane" id="tab-upload">
					<h3 data-i18n="">Upload document</h3>
					<form:input type="file" path="attachment"/>
				</div> 
				
				<div class="tab-pane" id="tab-from">
				
					<h3 data-i18n="filter_where_are_you_from"><s:message code='filter_where_are_you_from'/></h3>

					<span data-i18n="filter_select_country"><s:message code='filter_select_country'/></span>
			        <tiles:insertDefinition name="countries">
			        	<tiles:putAttribute name="field" value="authority.country"/>
			        	<tiles:putAttribute name="cssClass" value=""/>
			        </tiles:insertDefinition>

				</div>

			</div>
		</div>
	</div>

    <tiles:insertDefinition name="footerButtons">
    	<tiles:putAttribute name="prev" value="/welcome"/>
    	<tiles:putAttribute name="next" value="procedure"/>
    </tiles:insertDefinition>
	
</form:form>
