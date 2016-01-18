<%@ page import="eu.europa.ec.grow.espd.constants.enums.Country" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<script>
    $(function () {
        $("#espdform").validate({
            errorContainer: $("div.errorContainer"),
            errorPlacement: function ($error, $element) {
            	$element.parent().append($error);
            }
        });
        $("#ojsNumber").inputmask("9999/S 999-9999999");
    });
</script>

<c:set var="isAgentEO" value="${param['agent'] == 'eo'}"/>

<form:form id="espdform" role="form" class="form-horizontal" method="post" commandName="espd" data-toggle="validator">
    <tiles:insertDefinition name="viewChangeRole">
        <tiles:putAttribute name="currentPage" value="procedure"/>
    </tiles:insertDefinition>

    <div class="panel-default">
        <div class="panel panel-default no-border">
            <ul class="nav nav-pills nav-wizard nav-justified">
                <li class="active">
					<a href="#"><i class="fa fa-random"></i>&nbsp;${span18n['progress_start']}</a>
                    <div class="nav-arrow"></div>
                </li>
                <li class="active">
                    <div class="nav-wedge"></div>
                    <a href="#"><i class="fa fa-university"></i>&nbsp;${span18n['progress_procedure']}</a>
                    <div class="nav-arrow"></div>
                </li>
                <li>
                    <div class="nav-wedge"></div>
                    <a href="#"><i class="fa fa-exclamation"></i>&nbsp;${span18n['progress_exclusion']}</a>
                    <div class="nav-arrow"></div>
                </li>
                <li>
                    <div class="nav-wedge"></div>
                    <a href="#"><i class="fa fa-check-circle"></i>&nbsp;${span18n['progress_selection']}</a>
                    <div class="nav-arrow"></div>
                </li>
                <li>
                    <div class="nav-wedge"></div>
                    <a href="#"><i class="fa fa-download"></i>&nbsp;${span18n['progress_finish']} </a>
				</li>
            </ul>
        </div>

        <div class="errorContainer alert alert-danger" style="display: none">
            <ul class="fa-ul">
                <li>
                    <i class="info-label fa fa-exclamation-triangle fa-lg fa-li"></i>

					${div18n['createca_correct_errors']}
                    <div class="errorLabelContainer">
                        <ul></ul>
                    </div>
                </li>
            </ul>
        </div>
        
        <div class="paragraph">
            <h2>${span18n['createca_header']}</h2>
        </div>
 
        <div class="espd-panel panel panel-default">
            <div class="espd-panel-heading" data-toggle="collapse" data-target="#cadiv">
                ${span18n['createca_info_pub']}
            </div>
            <div id="cadiv" class="collapse in">
                <div class="panel-body">
					<div class="alert alert-espd-info" style="border: 1px dotted blue; background-color: #D8D8D8;">
                                ${div18n['createca_to_be_filled_alert']}
	                            <div class="form-group">
	                                <label class="control-label col-md-4">${span18n['createca_ojs_label']}</label>
	                                <div class="col-md-8">
	                                    <form:input cssClass="form-control" path="ojsNumber" placeholder="[ ][ ][ ][ ]/S [ ][ ][ ]–[ ][ ][ ][ ][ ][ ][ ]"/>
	                                </div>
	                            </div>
                               	${span18n['createca_official_journal_alert']}
					</div>
                </div>
            </div>
        </div>  
        
        <div class="espd-panel panel panel-default">
            <div class="espd-panel-heading" data-toggle="collapse" data-target="#cadiv">
                ${span18n['createca_contact_details_ca']}
            </div>
            <div id="cadiv" class="collapse in">
                <div class="panel-body">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="control-label col-md-4">${span18n['createca_name']}</label>
                                <div class="col-md-8">
                                    <form:input cssClass="form-control" path="authority.name" placeholder="${i18n['createca_name_placeholder']}" data-i18n="createca_name_placeholder" required="true"/>
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
										<tiles:putAttribute name="countryField" value="authority.country"/>
										<tiles:putAttribute name="countryHtmlId" value="country"/>
										<tiles:putAttribute name="countryCssClass" value="form-control"/>
									</tiles:insertDefinition>
								</div>
							</div>
                        </div>
                    </div>
            </div>
        </div>
        <div class="espd-panel panel panel-default">
            <div class="espd-panel-heading" data-toggle="collapse" data-target="#ppdiv" >
            	${span18n['createca_info_procurement_proc']}
            </div>
            <div id="ppdiv" class="collapse in">
                <div class="panel-body">
                	<div class="col-md-12">
						<div class="form-group">
							<label class="control-label col-md-4">${span18n['createca_procurer_name']}</label>
							<div class="col-md-8">
								<form:input cssClass="form-control" path="procedureTitle" placeholder="${i18n['createca_procurer_name_placeholder']}" data-i18n="createca_procurer_name_placeholder"/>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group">
							<label class="control-label col-md-4">${span18n['createca_title_or_short_desc']}</label>
							<div class="col-md-8">
								<form:textarea path="procedureShortDesc" cssStyle="resize: none" rows="4" cols="20" cssClass="form-control" placeholder="${i18n['createca_title_or_short_desc_placeholder']}" data-i18n="createca_title_or_short_desc_placeholder"/>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group">
								<label class="control-label col-md-4">
									${span18n['createca_file_ref_ca']}
								</label>
								<div class="col-md-8">
									<form:input cssClass="form-control" path="fileRefByCA" placeholder="${i18n['createca_file_ref_ca_placeholder']}" data-i18n="createca_file_ref_ca_placeholder"/>
								</div>
							</div>
						</div>
					</div>
            </div>
        </div>


        <c:if test="${isAgentEO}">
	        <div class="paragraph">
	            <h2>${span18n['createeo_header']}</h2>
	        </div>
        
	        <div class="espd-panel panel panel-default">
	            <div class="espd-panel-heading" data-toggle="collapse" data-target="#createeo_info_eo_div">
	            	${span18n['createeo_info_eo']}
	            </div>
	            <div id="createeo_info_eo_div" class="collapse in">
	                <div class="panel-body">
	                	
	                        <div class="col-md-6">
	                            <div class="form-group">
	                                <label class="control-label col-md-4">${span18n['createeo_name']}</label>
	                                <div class="col-md-8">
	                                    <form:input cssClass="form-control" path="eoperator.name" placeholder="${i18n['createeo_name_placeholder']}" data-i18n="createeo_name_placeholder"/>
	                                </div>
	                            </div>
		                       <tiles:insertDefinition name="partyInfo">
		                             <tiles:putAttribute name="field" value="eoperator"/>
		                             <tiles:putAttribute name="address" value="true"/>
		                       </tiles:insertDefinition>

		                       <div class="form-group">
		                            <label class="control-label col-md-4">${span18n['createeo_internet_addr_if_exists']}</label>
		                            <div class="col-md-8">
		                                <form:input cssClass="form-control" path="eoperator.website" placeholder="${i18n['createeo_internet_addr_if_exists_placeholder']}" data-i18n="createeo_internet_addr_if_exists_placeholder"/>
		                            </div>
		                       </div>
	                        </div>
	                        <div class="col-md-6">

	                            <tiles:insertDefinition name="partyInfo">
	                                 <tiles:putAttribute name="field" value="eoperator"/>
	                                 <tiles:putAttribute name="contacts" value="true"/>
	                            </tiles:insertDefinition>
                           
		                       <div class="form-group">
		                            <label class="control-label col-md-4">${span18n['createeo_contact_person']}</label>
		                            <div class="col-md-8">
		                            	<form:input cssClass="form-control" path="eoperator.contactName" placeholder="${i18n['createeo_contact_person_placeholder']}" data-i18n="createeo_contact_person_placeholder"/>
		                            </div>
		                        </div>

								<div class="form-group">
									<label class="control-label col-md-4">${span18n['createeo_vat']}</label>
									<div class="col-md-8">
										<form:input cssClass="form-control" path="eoperator.vat" placeholder="${i18n['createeo_vat_placeholder']}" data-i18n="createeo_vat_placeholder"/>
									</div>
								</div>
								
		                       <div class="form-group">
		                            <label class="control-label col-md-4">${span18n['createeo_another_vat']}</label>
		                            <div class="col-md-8">
		                            	<form:input cssClass="form-control" path="eoperator.anotherNationalId" placeholder="${i18n['createeo_another_vat_placeholder']}" data-i18n="createeo_another_vat_placeholder"/>
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
	                                <form:checkbox path="eoperator.isSmallSizedEnterprise" cssClass="radioslide checktoggle form-control" />
	                            </div>
	                        </div>
                       </div>
                       <div class="col-md-12">
                       		<div class="form-group">
	                            <label class="control-label col-md-6">
	                            	${span18n['createeo_if_proc_reserved']}
	                            </label>
	                            <div class="col-md-6">
	                                <form:checkbox path="eoperator.isShelteredWorkshop" data-toggle="collapse" data-target="#disworkers-form" cssClass="radioslide checktoggle form-control" />
	                            </div>
                            </div>
                       </div>
                       <div class="col-md-12" id="disworkers-form" style="display:none"> 
	                       <div class=" form-group">
	                            <label class="control-label col-md-6">
	                            	${span18n['createeo_percentage_disworkers']}
	                            </label>
	                            <div class="col-md-6" >
	                                <form:input cssClass="form-control" path="eoperator.percentageDisabledWorkers" placeholder="${i18n['createeo_percentage_disworkers_placeholder']}" data-i18n="createeo_percentage_disworkers_placeholder"/>
	                            </div>
	                       </div>
	                       <div class="form-group">
	                            <label class="control-label col-md-6">
	                            	${span18n['createeo_disworkers_details']}
	                            </label>
	                            <div class="col-md-6">
	                                <form:input cssClass="form-control" path="eoperator.detailsDisabledWorkers" placeholder="${i18n['createeo_disworkers_details_placeholder']}" data-i18n="createeo_disworkers_details_placeholder"/>
	                            </div>
	                       </div>
						</div>
						
                       <div class="col-md-12">
                       		<div class="form-group">
	                            <label class="control-label col-md-6">
	                            	${span18n['createeo_eo_approved_cert']}
	                            </label>
	                            <div class="col-md-6">
	                                <form:checkbox path="eoperator.isEORegisteredOfficially" data-toggle="collapse" data-target="#reg-official-yes" data-target-invert="#reg-official-no" cssClass="radioslide checktoggle form-control" />
	                            </div>
	                        </div>
                       </div>
                       <div id="reg-official-yes" style="display:none"><%-- [IF YES] --%>
                           <div class="col-md-12 alert alert-espd-info" style="border: 1px dotted blue; background-color: #D8D8D8;">
                           		${span18n['createeo_answer_following_parts']}
                           </div>
	                       <div class="col-md-12">
	                       		<div class="form-group">
		                            <label class="control-label col-md-6">${span18n['createeo_provide_regnumber']}</label>
		                            <div class="col-md-6">
		                                <form:input cssClass="form-control" path="eoperator.certNumber" placeholder="${i18n['createeo_provide_regnumber_placeholder']}" data-i18n="createeo_provide_regnumber_placeholder"/>
		                            </div>
	                            </div>
	                       </div>
	                       <div class="col-md-12">
	                       		<div class="form-group">
		                            <label class="control-label col-md-6">${span18n['createeo_cert_e_avaliable']}</label>
		                            <div class="col-md-6">
		                                <form:input cssClass="form-control" path="eoperator.certificateUrl" placeholder="${i18n['createeo_cert_e_avaliable_placeholder']}" data-i18n="createeo_cert_e_avaliable_placeholder"/>
		                            </div>
	                            </div>
	                       </div>
	                       <div class="col-md-12">
	                       		<div class="form-group">
		                            <label class="control-label col-md-6">${span18n['createeo_ref_for_cert']}</label>
		                            <div class="col-md-6">
		                                <form:input cssClass="form-control" path="eoperator.certRefBasis" placeholder="${i18n['createeo_ref_for_cert_placeholder']}" data-i18n="createeo_ref_for_cert_placeholder"/>
		                            </div>
		                        </div>
	                       </div>
	                       <div class="col-md-12">
	                       		<div class="form-group">
		                            <label class="control-label col-md-6">${span18n['createeo_all_selection_covered']}</label>
		                            <div class="col-md-6">
		                                <form:checkbox path="eoperator.certCoversAllCrit" cssClass="radioslide checktoggle form-control" />
		                            </div>
	                            </div>
	                       </div>
 					</div>
 					 <div id="reg-official-no" style="display:none"><%-- [IF NO] --%>
                           <div class="col-md-12 alert alert-espd-info" style="border: 1px dotted blue; background-color: #D8D8D8;">
                                <span data-i18n="createeo_add_complete_missing">${i18n['createeo_add_complete_missing']}</span>
                           </div>
	                       <div class="col-md-12 ">
	                       		<div class="form-group">
		                            <label class="control-label col-md-6" > ${span18n['createeo_eo_has_cert_soc']}</label>
		                            <div class="col-md-6">
		                                <form:input cssClass="form-control" path="eoperator.socialSecPaymentDoc" placeholder="${i18n['createeo_eo_has_cert_soc_placeholder']}" data-i18n="createeo_eo_has_cert_soc_placeholder"/>
		                            </div>
	                            </div>
	                       </div>
	                       <div class="col-md-12">
	                       		<div class="form-group">
		                            <label class="control-label col-md-6"> ${span18n['createeo_doc_e_avaliable']}</label>
		                            <div class="col-md-6">
		                                <form:input cssClass="form-control" path="eoperator.socialSecPaymentDocUrl" placeholder="${i18n['createeo_doc_e_avaliable_placeholder']}" data-i18n="createeo_doc_e_avaliable_placeholder"/>
		                            </div>
	                            </div>
	                       </div>
					</div>
					
                       <div class="col-md-12">
                       		<div class="form-group">
	                            <label class="control-label col-md-6">${span18n['createeo_is_eo_proc_together']}</label>
	                            <div class="col-md-6">
	                                <form:checkbox path="eoperator.isEOInGroup" data-toggle="collapse" data-target="#group-form" cssClass="radioslide checktoggle form-control" />
	                            </div>
                           	</div>
                       </div>
                       <div id="group-form" style="display:none"><%-- [IF YES] --%>
                            <div class="col-md-12 alert alert-espd-info" style="border: 1px dotted blue; background-color: #D8D8D8;">
                                ${span18n['createeo_ensure_others_espd']}
                            </div>
	                       <div class="col-md-12">
	                       		<div class="form-group">
		                            <label class="control-label col-md-6"> ${span18n['createeo_eo_group_role']}</label>
		                            <div class="col-md-6">
		                                <form:input cssClass="form-control" path="eoperator.eoRoleInGroup" placeholder="${i18n['createeo_eo_group_role_placeholder']}" data-i18n="createeo_eo_group_role_placeholder"/>
		                            </div>
	                           	</div>
	                       </div>
	                       <div class="col-md-12">
	                       		<div class="form-group">
		                            <label class="control-label col-md-6"> ${span18n['createeo_other_eo_part']}</label>
		                            <div class="col-md-6">
		                                <form:input cssClass="form-control" path="eoperator.otherEOInGroup" placeholder="${i18n['createeo_other_eo_part_placeholder']}" data-i18n="createeo_other_eo_part_placeholder"/>
		                            </div>
	                            </div>
	                       </div>
	                       <div class="col-md-12">
	                       		<div class="form-group">
		                            <label class="control-label col-md-6" > ${span18n['createeo_name_part_group']}</label>
		                            <div class="col-md-6">
		                                <form:input cssClass="form-control" path="eoperator.eoGroupName" placeholder="${i18n['createeo_name_part_group_placeholder']}" data-i18n="createeo_name_part_group_placeholder"/>
		                            </div>
	                            </div>
	                       </div>
						</div>
                       <div class="col-md-12">
                       		<div class="form-group">
	                            <label class="control-label col-md-6">${span18n['createeo_lots_concerned']}</label>
	                            <div class="col-md-6">
	                                <form:input cssClass="form-control" path="lotConcerned" id="lotConcerned" placeholder="${i18n['createeo_lots_concerned_placeholder']}" data-i18n="createeo_lots_concerned_placeholder"/>
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
                       <div class="col-md-12 alert alert-espd-info" style="border: 1px dotted blue; background-color: #D8D8D8;">
                            ${span18n['createeo_person_empowered']}
                       </div>

                       <div class="col-md-6">
                       		<div class="form-group">
	                           <label class="control-label col-md-4">${span18n['createeo_first_name']}</label>
	                           <div class="col-md-8">
	                                <form:input cssClass="form-control" path="eoperator.representFirstName" placeholder="${i18n['createeo_first_name_placeholder']}" data-i18n="createeo_first_name_placeholder"/>
	                           </div>
                           </div>
                       		<div class="form-group">
	                           <label class="control-label col-md-4">${span18n['createeo_birth_date']}</label>
	                           <div class="col-md-8"> 
									<form:input path="eoperator.representDateOfBirth" cssClass="form-control datepicker" placeholder="${i18n['createeo_birth_date_placeholder']}" data-i18n="createeo_birth_date_placeholder"/>
	                           </div>
                           </div>
                       </div>
                       <div class="col-md-6">
                       		<div class="form-group">
	                           <label class="control-label col-md-4">${span18n['createeo_last_name']}</label>
	                           <div class="col-md-8">
	                                <form:input cssClass="form-control" path="eoperator.representLastName" placeholder="${i18n['createeo_last_name_placeholder']}" data-i18n="createeo_last_name_placeholder"/>
	                           </div>
                           </div>
                       		<div class="form-group">
	                           <label class="control-label col-md-4">${span18n['createeo_birth_place']}</label>
	                           <div class="col-md-8">
	                                <form:input cssClass="form-control" path="eoperator.representPlaceOfBirth" placeholder="${i18n['createeo_birth_place_placeholder']}" data-i18n="createeo_birth_place_placeholder"/>
	                           </div>
                           </div>
                       </div>

                       <div class="col-md-6">
	                        <tiles:insertDefinition name="partyInfo">
	                            <tiles:putAttribute name="field" value="empowered"/>
	                            <tiles:putAttribute name="address" value="true"/>
	                        </tiles:insertDefinition>
                       </div>
                       <div class="col-md-6">
	                        <tiles:insertDefinition name="partyInfo">
	                            <tiles:putAttribute name="field" value="empowered"/>
	                            <tiles:putAttribute name="contacts" value="true"/>
	                        </tiles:insertDefinition>
	                        
                       		<div class="form-group">
	                           <label class="control-label col-md-4" >${span18n['createeo_pos_act_in_capacity']}</label>
	                           <div class="col-md-8">
	                                 <form:input cssClass="form-control" path="eoperator.representPosition" placeholder="${i18n['createeo_pos_act_in_capacity_placeholder']}" data-i18n="createeo_pos_act_in_capacity_placeholder"/>
	                           </div>
                           </div>
                       </div>
                       <div class="col-md-12">
                       		<div class="form-group">
	                            <label class="control-label col-md-2">${span18n['createeo_detinfo_of_represent']}</label>
	                            <div class="col-md-10">
	                               <form:textarea path="eoperator.representAddInfo" cssStyle="resize: none" rows="4" cols="20" cssClass="form-control" placeholder="${i18n['createeo_detinfo_of_represent_placeholder']}" data-i18n="createeo_detinfo_of_represent_placeholder"/>
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
                                <form:checkbox path="eoperator.isEORelyOnEntities" cssClass="radioslide checktoggle form-control" />
                            </div>
                       </div>
                       <div class="col-md-12 alert alert-espd-info" style="border: 1px dotted blue; background-color: #D8D8D8;margin-top: 15px;">
                            ${span18n['createeo_separate_espd_sections_a_b']}
                       </div>
	                </div>
	            </div>
	        </div>
        </c:if>
 

    </div>

    <tiles:insertDefinition name="footerButtons">
    	<tiles:putAttribute name="prevLink" value="true"/>
    	<tiles:putAttribute name="prevUrl" value="filter"/>
    </tiles:insertDefinition>

</form:form>
