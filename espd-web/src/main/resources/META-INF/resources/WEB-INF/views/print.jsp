<%@ page import="eu.europa.ec.grow.espd.xml.CriteriaTemplates" %>
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

<%
request.setAttribute("exclusionEO", eu.europa.ec.grow.espd.xml.CriteriaTemplates.exclusionEO);
request.setAttribute("suitabilityListEO", CriteriaTemplates.suitabilityListEO);
request.setAttribute("economicListEO", CriteriaTemplates.economicListEO);
request.setAttribute("technicalListEO", CriteriaTemplates.technicalListEO_UglyPrintVersion);
request.setAttribute("qualityAssuranceListEO", CriteriaTemplates.qualityAssuranceListEO);
%>

<script>
    $(function () {
        $("#ojsNumber").inputmask("9999/S 999-9999999");
        <c:if test="${agent == 'ca'}">
            // CA only needs to see the labels but not the values
            $(":radio").attr('checked', false);
            $("input:radio[data-target-show]").each(dataShow);
        </c:if>

        if($('#eo_registered_na').attr('checked')){
			$('#eo_registered_answer_yes').attr('checked', false);
			$('#eo_registered_answer_no').attr('checked', false);
			$('#eo_registered_answer_yes').val(false);
			$('#eo_registered_answer_no').val(false);
        }

        //replace inputs with spans
		$('#espdform').find('input:not([type=hidden])').each(function() {
			if($(this).attr('type') == "radio") {
				$(this).replaceWith($("<i />").attr("class",
					($(this).attr('checked') == "checked" || $(this).attr('checked') == "true") ? "fa fa-check-square-o" : "fa fa-square-o"
				));
			}
			else if($(this).attr('type') == "checkbox") {
				$(this).replaceWith($("<i />").attr("class",
					($(this).attr('checked') == "checked" || $(this).attr('checked') == "true") ? "fa fa-check-square-o" : "fa fa-square-o"
				));
			}
			else {
				$(this).replaceWith($("<span />").addClass("wordwrap").text(this.value));
			}
		});

		$('#espdform').find('select').each(function() {
			if($(this).find('option:selected').length == 1) {
				$(this).replaceWith($("<span />").text($(this).find('option:selected')[0].label));
			}
			else {
				$(this).replaceWith($("<span />").text(""));
			}
		});
		$('#espdform').find('textarea').each(function() {
			$(this).replaceWith($("<span />").addClass("wordwrap").text(this.value));
		});


    });
</script>

<style>
	.espd-panel-heading:after {
		content: "";
	}
	.form-horizontal .control-label {
		padding-top: 0px;
	}
</style>

<form:form id="espdform" role="form" class="form-horizontal" method="post" commandName="espd">

	<%-- PROCEDURE --%>
    <div class="panel-default">

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
                            <form:textarea rows="1" cssClass="form-control" path="ojsNumber"
                                        placeholder="[ ][ ][ ][ ]/S [ ][ ][ ]–[ ][ ][ ][ ][ ][ ]"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-4">${span18n['createca_ojs_url']}</label>
                        <div class="col-md-8">
                            <span class="btn btn-link">${espd.tedUrl}</span>
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
                                <form:textarea rows="1" cssClass="form-control" path="authority.name" required="true"/>
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
                                <form:textarea rows="1" cssClass="form-control" path="procedureTitle" />
                            </div>
                        </div>
                    </div>
                    <div class="col-md-12">
                        <div class="form-group">
                            <label class="control-label col-md-4">${span18n['createca_title_or_short_desc']}</label>

                            <div class="col-md-8">
                                <form:textarea path="procedureShortDesc" cssStyle="resize: none" rows="4" cols="20" cssClass="form-control"/>
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
                            <div class="col-md-12 ${espd['procurementReserved'].answer ? '' : 'collapse'}" id="disworkers-form">
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
                                            <form:textarea rows="1" cssClass="form-control" path="eoParticipatingProcurementProcedure.description2"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label class="control-label col-md-6"> ${span18n['createeo_name_part_group']}</label>

                                        <div class="col-md-6">
                                            <form:textarea rows="1" cssClass="form-control" path="eoParticipatingProcurementProcedure.description3"/>
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
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="control-label col-md-4">${span18n['createeo_first_name']}</label>

                                    <div class="col-md-8">
                                        <form:textarea rows="1" cssClass="form-control" path="economicOperator.representative.firstName"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-4">${span18n['createeo_birth_date']}</label>

                                    <div class="col-md-8">
                                        <form:input type="text" path="economicOperator.representative.dateOfBirth" cssClass="form-control datepicker"/>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="control-label col-md-4">${span18n['createeo_last_name']}</label>

                                    <div class="col-md-8">
                                        <form:textarea rows="1" cssClass="form-control" path="economicOperator.representative.lastName"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-4">${span18n['createeo_birth_place']}</label>

                                    <div class="col-md-8">
                                        <form:textarea rows="1" cssClass="form-control" path="economicOperator.representative.placeOfBirth"/>
                                    </div>
                                </div>
                            </div>
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
            
    </div>

	<%-- EXCLUSION --%>
    <div class="panel-default">

        <div class="paragraph">
            <h2>${span18n["createcaexcl_header"]}</h2>
        </div>
        
		<tiles:insertDefinition name="topLevelCriteriaTemplate">
			<tiles:putAttribute name="topLevelCriteriaList" value="${exclusionEO}"/>
		</tiles:insertDefinition>

        <div class="panel panel-default espd-panel">
            <div data-i18n="crit_top_title_purely_national" class="espd-panel-heading" data-toggle="collapse"
                 data-target="#ca-insolvency-section">
                <s:message code='crit_top_title_purely_national'/>
            </div>
            <div id="ca-insolvency-section" class="collapse in">
                <div class="espd-panel-body panel-body">
                    <span data-i18n="crit_eu_main_breaching_obligations" style="font-weight: bold;">
                        <s:message code='crit_eu_main_purely_national'/>
                    </span>
                    <c:if test="${espd.purelyNationalGrounds != null && espd.purelyNationalGrounds.exists}">
                        <tiles:insertDefinition name="exclusionFormTemplate">
                            <tiles:putAttribute name="field" value="purelyNationalGrounds"/>
                            <tiles:putAttribute name="title_code" value="crit_eu_title_purely_national"/>
                            <tiles:putAttribute name="description_code" value="crit_eu_text_purely_national"/>
                            <tiles:putAttribute name="selfCleaning" value="false"/>
                        </tiles:insertDefinition>
                    </c:if>
                </div>
            </div>
        </div>
    </div>

	<%-- SELECTION --%>
    <div class="panel-default">
        
        
        <div class="paragraph"><h2>${span18n['createcasel_header']}</h2></div>
        <div class="alert alert-espd-info">
            <ul class="fa-ul">
                <li>
                    <i class="info-label fa fa-info-circle fa-lg fa-li"></i>${span18n['createcasel_alert']}
                </li>
            </ul>
        </div>
        <div class="panel panel-default espd-panel">
            <div class="espd-panel-heading" data-toggle="collapse" data-target="#eo-satisfies-all-section">
            	${span18n["all_selection_switch"]}
            </div>
            <div id="eo-satisfies-all-section" class="collapse in">
                <div class="espd-panel-body panel-body">
					<strong>${span18n['crit_selection_eo_declares_that']}</strong>
                </div>
                <div class="row criteria-row">
                    <div class="col-md-5 criteria-cell-left">
                        <div class="form-group">
                            <div class="col-md-12">
                                <strong>${span18n['crit_selection_eo_satisfies_all_criteria']}</strong>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-7 criteria-cell-right">
                        <div class="col-md-12">
                            <div class="form-group">
							 	${span18n["crit_your_answer"]}
								<form:radiobutton path="selectionSatisfiesAll.answer" value="true" data-target-hide="${'#'}eo-satisfies-all-form"/>${span18n["yes"]}
								<form:radiobutton path="selectionSatisfiesAll.answer" value="false" data-target-show="${'#'}eo-satisfies-all-form"/>${span18n["no"]}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
       
        <div id="eo-satisfies-all-form" class="${espd['selectionSatisfiesAll'].answer ? 'collapse' : ''}">
        
			<tiles:insertDefinition name="euCriteriaListTemplate">
				<tiles:putAttribute name="id" value="eo-suitability-section"/>
				<tiles:putAttribute name="title_code" value="createcasel_suitability"/>
				<tiles:putAttribute name="subtitle_code" value="crit_selection_eo_suitability_article"/>
				<tiles:putAttribute name="disableTooltips" value="true"/>
				<tiles:putAttribute name="criteriaList" value="${suitabilityListEO}"/>
			</tiles:insertDefinition>

			<tiles:insertDefinition name="euCriteriaListTemplate">
				<tiles:putAttribute name="id" value="eo-economic-financial-section"/>
				<tiles:putAttribute name="title_code" value="createcasel_economic_and_financial_standing"/>
				<tiles:putAttribute name="subtitle_code" value="crit_selection_eo_economic_article"/>
				<tiles:putAttribute name="disableTooltips" value="true"/>
				<tiles:putAttribute name="criteriaList" value="${economicListEO}"/>
			</tiles:insertDefinition>
			
			<tiles:insertDefinition name="euCriteriaListTemplate">
				<tiles:putAttribute name="id" value="eo-technical-professional-section"/>
				<tiles:putAttribute name="title_code" value="createcasel_technical_professional_ability"/>
				<tiles:putAttribute name="subtitle_code" value="crit_selection_technical_professional_ability_article"/>
				<tiles:putAttribute name="disableTooltips" value="true"/>
				<tiles:putAttribute name="criteriaList" value="${technicalListEO}"/>
			</tiles:insertDefinition>

            <tiles:insertDefinition name="euCriteriaListTemplate">
                <tiles:putAttribute name="id" value="eo-quality-assurance-section"/>
                <tiles:putAttribute name="title_code" value="createcasel_quality_assurance"/>
                <tiles:putAttribute name="subtitle_code" value="crit_selection_quality_assurance_article"/>
                <tiles:putAttribute name="disableTooltips" value="true"/>
                <tiles:putAttribute name="criteriaList" value="${qualityAssuranceListEO}"/>
            </tiles:insertDefinition>
        
		</div>
    </div>

	<%-- FINISH --%>
	<div class="panel-default">
		<div class="paragraph">
			<h2>
				<span data-i18n="createcafinish_header"><s:message code="createcafinish_header"/></span>
			</h2>
		</div>
		<div class="panel panel-default espd-panel">
			<div data-i18n="createcafinish_reduction" class="espd-panel-heading" data-toggle="collapse" data-target="#finish-reduction-of-numbers-section">
				 <s:message code='createcafinish_reduction'/>
			</div>
            <div id="finish-reduction-of-numbers-section" class="collapse in">
                <div class="espd-panel-body panel-body">
					<div class="alert alert-espd-info">
						<ul class="fa-ul">
						<li>
							<i class="info-label fa fa-info-circle fa-lg fa-li"></i>
							<span data-i18n="createcafinish_toptext"><s:message code='createcafinish_toptext'/></span>
						</li>
						</ul>
					</div>
					<span data-i18n="createcafinish_reduction_question" style="font-weight: bold;">
                        <s:message code='createcafinish_reduction_question'/>
                    </span>
					<tiles:insertDefinition name="objectiveFormTemplate">
						<tiles:putAttribute name="field" value="meetsObjective"/>
						<tiles:putAttribute name="title_code" value="createcafinish_title_eo_declares_that"/>
						<tiles:putAttribute name="description_code" value="createcafinish_text_eo_declares_that"/>
					</tiles:insertDefinition>
                </div>
            </div>
		</div>
		<div class="panel panel-default espd-panel">
			<div data-i18n="createcafinish_concl_statements" class="espd-panel-heading" data-toggle="collapse" data-target="#finish-statements-signature-section">
				 <s:message code='createcafinish_concl_statements'/>
			</div>
            <div id="finish-statements-signature-section" class="collapse in">
                <div class="espd-panel-body panel-body">
                    <span data-i18n="createcafinish_concl_statements_text">
                        <s:message code='createcafinish_concl_statements_text'/>
                    </span>
                    <p>
	                    <span data-i18n="createcafinish_concl_statements_signature">
	                        <s:message code='createcafinish_concl_statements_signature'/>
	                    </span>
                    </p>
                    <div class="form-group">
                        <label class="control-label col-md-2 small">${span18n['crit_date']}</label>
                        <div class="col-md-4">
                           <form:input type="text" path="documentDate" cssClass="form-control datepicker" cssStyle="border-radius: 0;"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2 small">${span18n['place']}</label>
                        <div class="col-md-4">
                            <form:textarea rows="1" path="location" cssClass="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2 small">${span18n['signature']}</label>
                    </div>
                    <br/><br/><br/>
                </div>
            </div>
		</div>
        <tiles:insertDefinition name="footerButtons">
            <tiles:putAttribute name="nextCode" value="export"/>
            <tiles:putAttribute name="prev" value="finish"/>
            <tiles:putAttribute name="next" value="generate"/>
        </tiles:insertDefinition>
	</div>
</form:form>