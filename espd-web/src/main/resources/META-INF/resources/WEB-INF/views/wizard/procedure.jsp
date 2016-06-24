<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

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

<tiles:importAttribute name="agent"/>
<tiles:importAttribute name="flow"/>

<script>
    $(function () {
        $("#espdform").validate({
            errorContainer: $("div.errorContainer"),
            errorPlacement: function ($error, $element) {
                $element.parent().append($error);
            }
        });
        $("#ojsNumber").inputmask("9999/S 999-999999");

        // eo registered answer and not applicable are mutually exclusive
        $("#eo_registered_answer_yes").click(function () {
			$('#eo_registered_na').attr('checked', false);
        });
        $("#eo_registered_answer_no").click(function () {
			$('#eo_registered_na').attr('checked', false);
        });
        $("#eo_registered_na").click(function () {
			$('#eo_registered_answer_yes').attr('checked', false);
			$('#eo_registered_answer_no').attr('checked', false);
        });
    });
</script>

<form:form id="espdform" role="form" class="form-horizontal" method="post" commandName="espd" data-toggle="validator">

    <tiles:insertDefinition name="viewChangeRole">
        <tiles:putAttribute name="agent" value="${agent}"/>
        <tiles:putAttribute name="page" value="${flow}/${agent == 'ca' ? 'eo' : 'ca'}/procedure"/>
        <tiles:putAttribute name="showLink" value="${flow == 'request'}"/>
    </tiles:insertDefinition>
    
    <div class="panel-default">
        <tiles:insertDefinition name="progress">
        	<tiles:putAttribute name="agent" value="${agent}"/>
        	<tiles:putAttribute name="flow" value="${flow}"/>
            <tiles:putAttribute name="procedure" value="true"/>
        </tiles:insertDefinition>
        <div class="errorContainer alert alert-danger" style="display: none">
            <ul class="fa-ul">
                <li>
                    <i class="info-label fa fa-exclamation-triangle fa-lg fa-li"></i>
                        ${div18n['correct_errors']}
                    <div class="errorLabelContainer"></div>
                </li>
            </ul>
        </div>
        <div class="paragraph">
            <h2>${span18n['createca_header']}</h2>
        </div>
        <div class="espd-panel panel panel-default">
            <div class="espd-panel-heading" data-toggle="collapse" data-target="#ojsdiv">
                    ${span18n['createca_info_pub']}
            </div>
            <div id="ojsdiv" class="panel-body collapse in">
                <div class="alert alert-espd-info" style="border: 1px dotted blue; background-color: #D8D8D8;">
                        ${div18n['createca_to_be_filled_alert']}
                    <div class="form-group">
                        <label class="control-label col-md-4">${span18n['createca_ojs_label']}</label>

                        <div class="col-md-8">
                            <form:input cssClass="form-control" path="ojsNumber" placeholder="[ ][ ][ ][ ]/S [ ][ ][ ]–[ ][ ][ ][ ][ ][ ]"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-4">${span18n['createca_ojs_url']}</label>
                        <div class="col-md-8">
                            <a href="${espd.tedUrl}" target="_blank" class="btn btn-link">${espd.tedUrl}</a>
                        </div>
                    </div>
                    ${span18n['createca_official_journal_alert']}
                </div>
            </div>
        </div>
        <div class="espd-panel panel panel-default">
            <div class="espd-panel-heading" data-toggle="collapse" data-target="#cadiv">
                    ${span18n['createca_contact_details_ca']}
            </div>
            <div id="cadiv" class="panel-body collapse in">

                    <div class="col-md-6">
                        <div class="form-group">
                            <label class="control-label col-md-4">${span18n['createca_name']}</label>

                            <div class="col-md-8">
                                <form:textarea rows="1" cssClass="form-control" path="authority.name"/>
                                <span class="error"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-8 col-md-offset-4">
                                <form:errors path="authority.name" cssClass="alert-danger"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class=" form-group ">
                            <label class="control-label col-md-4">${span18n['createca_country']}</label>

                            <div class="col-md-8">
                                <tiles:insertDefinition name="countries">
                                    <tiles:putAttribute name="field" value="authority.country"/>
                                </tiles:insertDefinition>
                            </div>
                        </div>
                    </div>

            </div>
        </div>
        <div class="espd-panel panel panel-default">
            <div class="espd-panel-heading" data-toggle="collapse"
                 data-target="#ppdiv">${span18n['createca_info_procurement_proc']}</div>
            <div id="ppdiv" class="panel-body collapse in">
                    <div class="col-md-12">
                        <div class="form-group">
                            <label class="control-label col-md-4">${span18n['createca_procurer_name']}</label>

                            <div class="col-md-8">
                                <form:textarea rows="1" cssClass="form-control" path="procedureTitle"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-12">
                        <div class="form-group">
                            <label class="control-label col-md-4">${span18n['createca_title_or_short_desc']}</label>

                            <div class="col-md-8">
                                <form:textarea path="procedureShortDesc" cssStyle="resize: none" rows="4" cols="20"
                                               cssClass="form-control"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-12">
                        <div class="form-group">
                            <label class="control-label col-md-4">
                                    ${span18n['createca_file_ref_ca']}
                            </label>

                            <div class="col-md-8">
                                <form:textarea rows="1" cssClass="form-control" path="fileRefByCA"/>
                            </div>
                        </div>
                    </div>
            </div>
        </div>
        <c:if test="${agent == 'eo'}">
            <div class="paragraph">
                <h2>${span18n['createeo_header']}</h2>
            </div>
            <div class="espd-panel panel panel-default">
                <div class="espd-panel-heading" data-toggle="collapse"
                     data-target="#createeo_info_eo_div">${span18n['createeo_info_eo']}</div>
                <div id="createeo_info_eo_div" class="collapse in">
                    <div class="panel-body">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="control-label col-md-4">${span18n['createeo_name']}</label>

                                    <div class="col-md-8">
                                        <form:textarea rows="1" cssClass="form-control" path="economicOperator.name"/>
                                    </div>
                                </div>
                                <tiles:insertDefinition name="partyInfo">
                                    <tiles:putAttribute name="field" value="economicOperator"/>
                                    <tiles:putAttribute name="address" value="true"/>
                                </tiles:insertDefinition>
                                <div class="form-group">
                                    <label class="control-label col-md-4">${span18n['createeo_internet_addr_if_exists']}</label>

                                    <div class="col-md-8">
                                        <form:textarea rows="1" cssClass="form-control" path="economicOperator.website"/>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <tiles:insertDefinition name="partyInfo">
                                    <tiles:putAttribute name="field" value="economicOperator"/>
                                    <tiles:putAttribute name="contacts" value="true"/>
                                </tiles:insertDefinition>
                                <div class="form-group">
                                    <label class="control-label col-md-4">${span18n['createeo_contact_person']}</label>

                                    <div class="col-md-8">
                                        <form:textarea rows="1" cssClass="form-control" path="economicOperator.contactName"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-4">${span18n['createeo_vat']}</label>

                                    <div class="col-md-8">
                                        <form:textarea rows="1" cssClass="form-control" path="economicOperator.vatNumber"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-4">${span18n['createeo_another_vat']}</label>

                                    <div class="col-md-8">
                                        <form:textarea rows="1" cssClass="form-control" path="economicOperator.anotherNationalId"/>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label class="control-label col-md-6">
                                            ${span18n['createeo_is_eo_sized']}
                                    <span data-i18n="createeo_is_eo_sized_tooltip" title="${i18n['createeo_is_eo_sized_tooltip']}" data-toggle="tooltip"></span>
                                    </label>

                                    <div class="col-md-6">
										<form:radiobutton path="economicOperator.isSmallSizedEnterprise" value="true"/>${span18n["yes"]}
										<form:radiobutton path="economicOperator.isSmallSizedEnterprise" value="false"/>${span18n["no"]}
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label class="control-label col-md-6">${span18n['createeo_if_proc_reserved']}</label>

                                    <div class="col-md-6">
										<form:radiobutton path="procurementReserved.answer" value="true" data-target-show="#disworkers-form"/>${span18n["yes"]}
										<form:radiobutton path="procurementReserved.answer" value="false" data-target-hide="#disworkers-form"/>${span18n["no"]}
                                    </div>
                                </div>
                            </div>
                            <div id="disworkers-form" class="col-md-12 ${espd['procurementReserved'].answer ? '' : 'collapse'}">
                                <div class=" form-group">
                                    <label class="control-label col-md-6">${span18n['createeo_percentage_disworkers']}</label>

                                    <div class="col-md-6">
                                        <form:textarea rows="1" cssClass="form-control" path="procurementReserved.doubleValue1" number="true"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-6">${span18n['createeo_disworkers_details']}</label>

                                    <div class="col-md-6">
                                        <form:textarea rows="1" cssClass="form-control" path="procurementReserved.description1"/>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label class="control-label col-md-6">${span18n['createeo_eo_approved_cert']}</label>

                                    <div class="col-md-6">
										<form:radiobutton path="eoRegistered.answer" value="true" id="eo_registered_answer_yes" data-target-show="#reg-official-yes" data-target-hide="#reg-official-no"/>${span18n["yes"]}
										<form:radiobutton path="eoRegistered.answer" value="false" id="eo_registered_answer_no" data-target-show="#reg-official-no" data-target-hide="#reg-official-yes"/>${span18n["no"]}
										&nbsp;&nbsp;&nbsp;&nbsp;
										<form:checkbox path="eoRegistered.booleanValue2" id="eo_registered_na"/>${span18n['not_applicable']}
                                    </div>
                                </div>
                            </div>
                            <div id="reg-official-yes" class="${espd['eoRegistered'].answer ? '' : 'collapse'}"><%-- [IF YES] --%>
                                <div class="col-md-12 alert alert-espd-info"
                                     style="border: 1px dotted blue; background-color: #D8D8D8;">${span18n['createeo_answer_following_parts']}</div>
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label class="control-label col-md-6">${span18n['createeo_provide_regnumber']}</label>

                                        <div class="col-md-6">
                                            <form:textarea rows="1" cssClass="form-control" path="eoRegistered.description1"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label class="control-label col-md-6">${span18n['createeo_cert_e_avaliable']}</label>

                                        <div class="col-md-6">
                                            <form:textarea rows="1" cssClass="form-control" path="eoRegistered.description2"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label class="control-label col-md-6">${span18n['createeo_ref_for_cert']}</label>

                                        <div class="col-md-6">
                                            <form:textarea rows="1" cssClass="form-control" path="eoRegistered.description3"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label class="control-label col-md-6">${span18n['createeo_all_selection_covered']}</label>

                                        <div class="col-md-6">
											<form:radiobutton path="eoRegistered.booleanValue1" value="true"/>${span18n["yes"]}
											<form:radiobutton path="eoRegistered.booleanValue1" value="false"/>${span18n["no"]}
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div id="reg-official-no" class="${espd['eoRegistered'].answer ? 'collapse' : ''}"><%-- [IF NO] --%>
                                <div class="col-md-12 alert alert-espd-info"
                                     style="border: 1px dotted blue; background-color: #D8D8D8;">
                                    <span data-i18n="createeo_add_complete_missing">${i18n['createeo_add_complete_missing']}</span>
                                </div>
                                <div class="col-md-12 ">
                                    <div class="form-group">
                                        <label class="control-label col-md-6"> ${span18n['createeo_eo_has_cert_soc']}</label>
                                        <div class="col-md-6">
                                            <form:radiobutton path="eoRegistered.booleanValue3" value="true"/>${span18n["yes"]}
                                            <form:radiobutton path="eoRegistered.booleanValue3" value="false"/>${span18n["no"]}
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label class="control-label col-md-6">${span18n['createeo_doc_e_avaliable']}</label>

                                        <div class="col-md-6">
                                            <form:textarea rows="1" cssClass="form-control" path="eoRegistered.description5"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label class="control-label col-md-6">${span18n['createeo_is_eo_proc_together']}</label>

                                    <div class="col-md-6">
										<form:radiobutton path="eoParticipatingProcurementProcedure.answer" value="true" data-target-show="#group-form"/>${span18n["yes"]}
										<form:radiobutton path="eoParticipatingProcurementProcedure.answer" value="false" data-target-hide="#group-form"/>${span18n["no"]}     
                                    </div>
                                </div>
                            </div>
                            <div id="group-form" class="${espd['eoParticipatingProcurementProcedure'].answer ? '' : 'collapse'}"><%-- [IF YES] --%>
                                <div class="col-md-12 alert alert-espd-info"
                                     style="border: 1px dotted blue; background-color: #D8D8D8;">${span18n['createeo_ensure_others_espd']}</div>
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label class="control-label col-md-6"> ${span18n['createeo_eo_group_role']}</label>

                                        <div class="col-md-6">
                                            <form:textarea rows="1" cssClass="form-control"
                                                        path="eoParticipatingProcurementProcedure.description1"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label class="control-label col-md-6"> ${span18n['createeo_other_eo_part']}</label>

                                        <div class="col-md-6">
                                            <form:textarea rows="1" cssClass="form-control"
                                                        path="eoParticipatingProcurementProcedure.description2"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label class="control-label col-md-6"> ${span18n['createeo_name_part_group']}</label>

                                        <div class="col-md-6">
                                            <form:textarea rows="1" cssClass="form-control"
                                                        path="eoParticipatingProcurementProcedure.description3"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label class="control-label col-md-6">${span18n['createeo_lots_concerned']}</label>

                                    <div class="col-md-6">
                                        <form:textarea rows="1" cssClass="form-control" path="lotConcerned" id="lotConcerned"/>
                                    </div>
                                </div>
                            </div>
                        
                    </div>
                </div>
            </div>
            <div class="espd-panel panel panel-default">
                <div class="espd-panel-heading" data-toggle="collapse" data-target="#createeo_info_respresent_div">
                        ${span18n['createeo_info_respresent']}
                </div>
                <div id="createeo_info_respresent_div" class="collapse in">
                    <div class="panel-body">
                            <div class="col-md-12 alert alert-espd-info"
                                 style="border: 1px dotted blue; background-color: #D8D8D8;">
                                    ${span18n['createeo_person_empowered']}
                            </div>
                            <div class="col-md-12">
                            	<div class="col-md-6">
	                                <div class="form-group">
	                                    <label class="control-label col-md-4">${span18n['createeo_first_name']}</label>
	
	                                    <div class="col-md-8">
	                                        <form:textarea rows="1" cssClass="form-control"
	                                                    path="economicOperator.representative.firstName"/>
	                                    </div>
	                                </div>
                            	</div>
                            	<div class="col-md-6">
	                                <div class="form-group">
	                                    <label class="control-label col-md-4">${span18n['createeo_last_name']}</label>
	
	                                    <div class="col-md-8">
	                                        <form:textarea rows="1" cssClass="form-control"
	                                                    path="economicOperator.representative.lastName"/>
	                                    </div>
	                                </div>
                            	</div>
                            </div> 
                            <div class="col-md-12">
                            	<div class="col-md-6">
	                                <div class="form-group">
	                                    <label class="control-label col-md-4">${span18n['createeo_birth_date']}</label>
	                                    <div class="col-md-8">
	                                        <form:input type="text" path="economicOperator.representative.dateOfBirth" cssClass="form-control datepicker"/>
	                                    </div>
	                                </div>
                            	</div>
                            	<div class="col-md-6">
	                                <div class="form-group">
	                                    <label class="control-label col-md-4">${span18n['createeo_birth_place']}</label>
	
	                                    <div class="col-md-8">
	                                        <form:textarea rows="1" cssClass="form-control"
	                                                    path="economicOperator.representative.placeOfBirth"/>
	                                    </div>
	                                </div>
                            	</div>
                            </div>
                            <div class="col-md-12">
	                            <div class="col-md-6">
	                                <tiles:insertDefinition name="partyInfo">
	                                    <tiles:putAttribute name="field" value="economicOperator.representative"/>
	                                    <tiles:putAttribute name="address" value="true"/>
	                                </tiles:insertDefinition>
	                            </div>
	                            <div class="col-md-6">
	                                <div class="form-group">
	                                    <label class="control-label col-md-4" data-i18n="createca_email"><s:message
	                                            code="createca_email"/></label>
	
	                                    <div class="col-md-8">
	                                        <form:textarea rows="1" cssClass="form-control" path="economicOperator.representative.email"/>
	                                    </div>
	                                </div>
	                                <div class="form-group">
	                                    <label class="control-label col-md-4" data-i18n="createca_telephone"><s:message
	                                            code="createca_telephone"/></label>
	
	                                    <div class="col-md-8">
	                                        <form:textarea rows="1" cssClass="form-control" path="economicOperator.representative.phone"/>
	                                    </div>
	                                </div>
	                                <div class="form-group">
	                                    <label class="control-label col-md-4">${span18n['createeo_pos_act_in_capacity']}</label>
	
	                                    <div class="col-md-8">
	                                        <form:textarea rows="1" cssClass="form-control" path="economicOperator.representative.position"/>
	                                    </div>
	                                </div>
	                            </div>
                            </div>
                            <div class="col-md-12">
                            	<div class="col-md-12">
	                                <div class="form-group">
	                                    <label class="control-label col-md-2">${span18n['createeo_detinfo_of_represent']}</label>
	
	                                    <div class="col-md-10">
	                                        <form:textarea path="economicOperator.representative.additionalInfo"
	                                                       cssStyle="resize: none" rows="4" cols="20"
	                                                       cssClass="form-control"/>
	                                    </div>
	                                </div>
                                </div>
                            </div>
                        </div>
                    
                </div>
            </div>
            <div class="espd-panel panel panel-default">
                <div class="espd-panel-heading" data-toggle="collapse" data-target="#createeo_info_reliance_div">
                        ${span18n['createeo_info_reliance']}
                </div>
                <div id="createeo_info_reliance_div" class="collapse in">
                    <div class="panel-body">
                            <div class="col-md-12 form-group">
                                <label class="control-label col-md-6">
                                        ${span18n['createeo_eo_rely_other_entities']}
                                </label>

                                <div class="col-md-6">
									<form:radiobutton path="eoReliesCapacities.answer" value="true" data-target-show="#separate_espd_div"/>${span18n["yes"]}
									<form:radiobutton path="eoReliesCapacities.answer" value="false" data-target-hide="#separate_espd_div"/>${span18n["no"]}
                                </div>
                            </div>
                            <div id="separate_espd_div" class="col-md-12 alert alert-espd-info ${espd['eoReliesCapacities'].answer ? '' : 'collapse'}"
                                 style="border: 1px dotted blue; background-color: #D8D8D8;margin-top: 15px; display: none;">
                                    ${span18n['createeo_separate_espd_sections_a_b']}
                            </div>
                    </div>
                </div>
            </div>
            
            <div class="espd-panel panel panel-default">
                <div class="espd-panel-heading" data-toggle="collapse" data-target="#createeo_subcontractors">
                        ${span18n['createeo_information_subcontractors']}
                </div>
                <div id="createeo_subcontractors" class="collapse in">
                    <div class="panel-body">
                    
                            <div class="col-md-12 alert alert-espd-info"
                                 style="border: 1px dotted blue; background-color: #D8D8D8;">
                                    ${span18n['createeo_information_subcontractors_header']}
                            </div>
                            
                            <div class="col-md-12 form-group">
                                <label class="control-label col-md-6">
                                        ${span18n['createeo_information_subcontractors_title']}
                                </label>

                                <div class="col-md-6">
									<form:radiobutton path="subcontractingThirdParties.answer" value="true" data-target-show="#createeo_subcontractors_div"/>${span18n["yes"]}
									<form:radiobutton path="subcontractingThirdParties.answer" value="false" data-target-hide="#createeo_subcontractors_div"/>${span18n["no"]}
                                </div>
                            </div>
                            <div id="createeo_subcontractors_div" class="${espd['subcontractingThirdParties'].answer ? '' : 'collapse'}">
								<div class="col-md-12 form-group">
									<label class="control-label col-md-6">${span18n['createeo_information_subcontractors_description']}</label>
		
									<div class="col-md-6">
										<form:textarea rows="1" cssClass="form-control" path="subcontractingThirdParties.description1"/>
									</div>
								</div>
                            </div>
                            <div class="col-md-12 alert alert-espd-info"style="border: 1px dotted blue; background-color: #D8D8D8;">
                            	${span18n['createeo_information_subcontractors_footer']}
                            </div>
                    </div>
                </div>
            </div>
        </c:if>
    </div>
    <tiles:insertDefinition name="footerButtons">
        <tiles:putAttribute name="prev" value="/filter"/>
        <tiles:putAttribute name="next" value="exclusion"/>
    </tiles:insertDefinition>
</form:form>
