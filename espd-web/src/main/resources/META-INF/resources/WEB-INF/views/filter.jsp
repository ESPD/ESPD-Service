<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<script>
$(function() { 
	var country = $("select[name='authority.country']");

    $('input.radiotab').click(function () {
        $(this).tab('show');
    });
    var nextBtn = $('#nextBtn');
    $("*[name='agent']").click(function () {
        nextBtn.prop('disabled', true);
    	$('#tab-country-selection').removeClass('active');
    	$('#tab-single-upload').removeClass('active');
    	$('#tab-multiple-upload').removeClass('active');
    	$('.radioCa').removeAttr('checked');
    	$('[value="empty"]').prop('selected', true);
    	$("input:file").val('');
    	
    	$('#where_are_you_from_'+(($(this).val()=='eo')?'ca':'eo')).hide();
		$('#where_are_you_from_' + $(this).val()).show();
    });
	$("input:file").change(function (){
		if($(this).val() != '') {
			$('#tab-country-selection').addClass('active');
			nextBtn.prop('disabled', country.val() === '');
		}
	});
	country.change(function() {
    	nextBtn.prop('disabled', $(this).val() === '');
    });
    $("[name=action]").click(function () {
    	nextBtn.prop('disabled', country.val() === '');
    }); 
    nextBtn.prop('disabled', true);
});
</script>
<form:form id="espdform" role="form" class="form-horizontal" action="filter" method="post" commandName="espd" data-toggle="validator" enctype="multipart/form-data">
	<div class="panel-default">
        <tiles:insertDefinition name="progress">
			<tiles:putAttribute name="agent" value="unknown"/>
			<tiles:putAttribute name="flow" value="unknown"/>
			<tiles:putAttribute name="start" value="true"/>
        </tiles:insertDefinition>
		<div class="paragraph">
			<h2 data-i18n="filter_header"><s:message code='filter_header'/></h2>
		</div>
		<div class="alert alert-espd-info">
			<ul class="fa-ul">
                <li>
                    <i class="info-label fa fa-info-circle fa-lg fa-li"></i>
                    <div id="filter_text">${span18n['filter_alert']}</div>
                    <div id="filter_faq">
                        <s:message var="click_here" code="click_here"/>
                        <c:set var="hereLink" value="<a id='here_link' href='http://ec.europa.eu/growth/single-market/public-procurement/e-procurement/espd/index_en.htm'>${click_here}</a>"/>
                        <c:set var="faqLinkHref" value="http://ec.europa.eu/DocsRoom/documents/16002/attachments/1/translations/${pageContext.response.locale}/renditions/native"/>
                        <s:message var="faqLeaflet" code="faq_leaflet"/>
                        <c:set var="faqLink" value="<a id='faq_link' href='${faqLinkHref}'>${faqLeaflet}</a>"/>
                        <s:message code="faq_text" arguments="${hereLink},${faqLink}"/>
                    </div>
                </li>
			</ul>
		</div>
        <form:errors path="attachments" cssClass="errorContainer alert alert-danger"/>
		<div class="paragraph">
			<h3>
				<span data-i18n="filter_who_are_you"><s:message code='filter_who_are_you'/></span>
				<span data-i18n="tooltip_espd_used_both_ca_eo" data-toggle="tooltip" title="${i18n['tooltip_espd_used_both_ca_eo']}"></span>
			</h3>
			<div class="radio" >
				<label><input id="whoareyou_ca" name="agent" value="ca" class="radiotab" type="radio" href="#tab_ca"/>${span18n['filter_i_am_ca']}</label>
				<span data-i18n="tooltip_ca_ref_buyer" data-toggle="tooltip" title="<s:message code='tooltip_ca_ref_buyer'/>"></span>
			</div>
			<div class="radio">
				<label><input id="whoareyou_eo" name="agent" value="eo" class="radiotab" type="radio" href="#tab_eo"/>${span18n['filter_i_am_eop']}</label>
				<span data-i18n="tooltip_eo_ref_suppl" data-toggle="tooltip" title="${i18n['tooltip_eo_ref_suppl']}"></span>
			</div>
			<div class="tab-content" >
				<div class="tab-pane" id="tab_ca">
					<h3>${span18n['filter_what_you_do']}</h3>
                    <div class="row">
                        <div class="radio col-md-3">
                            <label><input name="action" value="ca_create_espd_request" class="radiotab radioCa" type="radio" data-target="#tab-country-selection"/>${span18n['filter_create_espd']}</label>
                            <span data-i18n="tooltip_ca_can_create_espd" data-toggle="tooltip" title="${i18n['tooltip_ca_can_create_espd']}"></span>
                        </div>
                        <div class="col-md-5">
                            <form:input path="tedReceptionId" id="tedReceptionId" cssClass="form-control small" data-i18n="filter_ted_reception_id_placeholder" placeholder="${i18n['filter_ted_reception_id_placeholder']}"/>
                        </div>
                        <div class="col-md-4" style="padding-left: 0px;padding-right: 5px">
                            <span data-i18n="tooltip_ted_reception_id" data-toggle="tooltip" title="${i18n['tooltip_ted_reception_id']}"></span>
                        </div>
                        <%--<div class="col-md-offset-3">&nbsp;</div>--%>
                    </div>
                    <div class="row">
                        <div class="radio col-md-3">
                            <label><input name="action" value="ca_reuse_espd_request" class="radiotab radioCa" type="radio" data-target="#tab-single-upload"/>${span18n['filter_reuse_espd']}</label>
                            <span data-i18n="tooltip_ca_can_import_espd" data-toggle="tooltip" title="${i18n['tooltip_ca_can_import_espd']}"></span>
                        </div>
                        <div class="col-md-offset-9"></div>
                    </div>
                    <div class="row">
                        <div class="radio col-md-3">
                            <label><input name="action" value="ca_review_espd_response" class="radiotab radioCa" type="radio" data-target="#tab-single-upload"/>${span18n['filter_review_espd']}</label>
                            <span data-i18n="tooltip_review_espd" data-toggle="tooltip" title="${i18n['tooltip_review_espd']}"></span>
                        </div>
                        <div class="col-md-offset-9">&nbsp;</div>
                    </div>
				</div>
				<div class="tab-pane" id="tab_eo">
					<h3 data-i18n="filter_what_you_do"><s:message code='filter_what_you_do'/></h3>
					<div class="radio">
						<span class="k-button fa fa-upload hoverable"></span>
						<label><input name="action" value="eo_import_espd" class="radiotab radioCa" type="radio" data-target="#tab-single-upload"/>${span18n['filter_import_espd']}</label>
						<span data-i18n="tooltip_filter_eo_can_import_espd" data-toggle="tooltip" title="${i18n['tooltip_filter_eo_can_import_espd']}"></span>
					</div>
                    <div class="radio">
                        <span class="k-button fa fa-upload hoverable"></span>
                        <label><input name="action" value="eo_merge_espds" class="radiotab radioCa" type="radio" data-target="#tab-multiple-upload"/>${span18n['filter_merge_espds']}</label>
                        <span data-i18n="tooltip_filter_eo_merge_espds" data-toggle="tooltip" title="${i18n['tooltip_filter_eo_merge_espds']}"></span>
                    </div>
                    <div class="radio">
                        <span class="k-button fa fa-upload hoverable"></span>
                        <label><input name="action" value="eo_create_response" class="radiotab radioCa" type="radio" data-target="#tab-country-selection"/>${span18n['filter_create_response']}</label>
                        <span data-i18n="tooltip_filter_create_response" data-toggle="tooltip" title="${i18n['tooltip_filter_create_response']}"></span>
                    </div>
				</div>
			</div>
			<div class="tab-content" >
				<div class="tab-pane" id="tab-single-upload">
					<h3 data-i18n="filter_upload_document"><s:message code='filter_upload_document'/></h3>
                    <s:message code="filter_upload_request_response"/>
					<form:input type="file" path="attachments"/>
				</div>
                <div class="tab-pane" id="tab-multiple-upload">
                    <h3 data-i18n="filter_upload_documents"><s:message code='filter_upload_documents'/></h3>
                    <s:message code="filter_upload_request"/>
                    <form:input type="file" path="attachments"/>
                    <s:message code="filter_upload_response"/>
                    <form:input type="file" path="attachments"/>
                </div>
                <div class="tab-pane" id="tab-country-selection">
                
					<h3 id="where_are_you_from_ca">${span18n['filter_where_are_you_from_ca']}</h3>
					<h3 id="where_are_you_from_eo">${span18n['filter_where_are_you_from_eo']}</h3>
					
					<span data-i18n="filter_select_country"><s:message code='filter_select_country'/></span>
			        <tiles:insertDefinition name="countries">
			        	<tiles:putAttribute name="field" value="authority.country"/>
			        	<tiles:putAttribute name="cssClass" value=""/>
			        </tiles:insertDefinition>
				</div>
			</div>
		</div>
	</div>
    <div class="form-group">
    </div>
    <tiles:insertDefinition name="footerButtons">
    	<tiles:putAttribute name="prev" value="/welcome"/>
    	<tiles:putAttribute name="next" value="procedure"/>
    </tiles:insertDefinition>
</form:form>
