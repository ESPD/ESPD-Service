<%@ page import="eu.europa.ec.grow.espd.constants.enums.Country" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>


<c:set var="lastYearsAmount" value='<%=new Integer[]{2016,2015,2014,2013,2012} %>'/>
<c:set var="lastYearsNumber" value='<%=new Integer[]{2016,2015,2014} %>'/>

<script>
    $(function () {
        $("#ojsNumber").inputmask("9999/S 999-9999999");
        $('input').attr('readonly', true);
        $('textarea').attr('readonly', true);
        $('checkbox').attr('readonly', true);
        $('select').attr('readonly', true);
         $("checkbox").attr("disabled", true);
         $('.radioslide').bootstrapToggle('disable');
    });
</script>

<form:form id="espdform" role="form" class="form-horizontal" method="post" commandName="espd">

    <div class="panel-default">
        
        <div class="paragraph">
            <h2>${span18n['createca_header']}</h2>
        </div>
 
        <div class="espd-panel panel panel-default">
            <div class="espd-panel-heading" data-toggle="collapse" data-target="#ojsdiv">
                ${span18n['createca_info_pub']}
            </div>
            <div id="ojsdiv" class="collapse in">
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
										<tiles:putAttribute name="field" value="authority.country"/>
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
	                                    <form:input cssClass="form-control" path="economicOperator.name" placeholder="${i18n['createeo_name_placeholder']}" data-i18n="createeo_name_placeholder"/>
	                                </div>
	                            </div>
		                       <tiles:insertDefinition name="partyInfo">
		                             <tiles:putAttribute name="field" value="economicOperator"/>
		                             <tiles:putAttribute name="address" value="true"/>
		                       </tiles:insertDefinition>

		                       <div class="form-group">
		                            <label class="control-label col-md-4">${span18n['createeo_internet_addr_if_exists']}</label>
		                            <div class="col-md-8">
		                                <form:input cssClass="form-control" path="economicOperator.website" placeholder="${i18n['createeo_internet_addr_if_exists_placeholder']}" data-i18n="createeo_internet_addr_if_exists_placeholder"/>
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
		                            	<form:input cssClass="form-control" path="economicOperator.contactName" placeholder="${i18n['createeo_contact_person_placeholder']}" data-i18n="createeo_contact_person_placeholder"/>
		                            </div>
		                        </div>

								<div class="form-group">
									<label class="control-label col-md-4">${span18n['createeo_vat']}</label>
									<div class="col-md-8">
										<form:input cssClass="form-control" path="economicOperator.vatNumber" placeholder="${i18n['createeo_vat_placeholder']}" data-i18n="createeo_vat_placeholder"/>
									</div>
								</div>
								
		                       <div class="form-group">
		                            <label class="control-label col-md-4">${span18n['createeo_another_vat']}</label>
		                            <div class="col-md-8">
		                            	<form:input cssClass="form-control" path="economicOperator.anotherNationalId" placeholder="${i18n['createeo_another_vat_placeholder']}" data-i18n="createeo_another_vat_placeholder"/>
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
	                                <form:checkbox path="economicOperator.isSmallSizedEnterprise" cssClass="radioslide checktoggle form-control" />
	                            </div>
	                        </div>
                       </div>
                       <div class="col-md-12">
                       		<div class="form-group">
	                            <label class="control-label col-md-6">
	                            	${span18n['createeo_if_proc_reserved']}
	                            </label>
	                            <div class="col-md-6">
	                                <form:checkbox path="procurementReserved.exists" data-toggle="collapse" data-target="#disworkers-form" cssClass="radioslide checktoggle form-control" />
	                            </div>
                            </div>
                       </div>
                       <div class="col-md-12" id="disworkers-form" style="display:none"> 
	                       <div class=" form-group">
	                            <label class="control-label col-md-6">
	                            	${span18n['createeo_percentage_disworkers']}
	                            </label>
	                            <div class="col-md-6" >
	                                <form:input cssClass="form-control" path="procurementReserved.doubleValue1" placeholder="${i18n['createeo_percentage_disworkers_placeholder']}" data-i18n="createeo_percentage_disworkers_placeholder"/>
	                            </div>
	                       </div>
	                       <div class="form-group">
	                            <label class="control-label col-md-6">
	                            	${span18n['createeo_disworkers_details']}
	                            </label>
	                            <div class="col-md-6">
	                                <form:input cssClass="form-control" path="procurementReserved.description1" placeholder="${i18n['createeo_disworkers_details_placeholder']}" data-i18n="createeo_disworkers_details_placeholder"/>
	                            </div>
	                       </div>
						</div>
						
                       <div class="col-md-12">
                       		<div class="form-group">
	                            <label class="control-label col-md-6">
	                            	${span18n['createeo_eo_approved_cert']}
	                            </label>
	                            <div class="col-md-6">
	                                <form:checkbox path="eoRegistered.exists" data-toggle="collapse" data-target="#reg-official-yes" data-target-invert="#reg-official-no" cssClass="radioslide checktoggle form-control" />
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
		                                <form:input cssClass="form-control" path="eoRegistered.description1" placeholder="${i18n['createeo_provide_regnumber_placeholder']}" data-i18n="createeo_provide_regnumber_placeholder"/>
		                            </div>
	                            </div>
	                       </div>
	                       <div class="col-md-12">
	                       		<div class="form-group">
		                            <label class="control-label col-md-6">${span18n['createeo_cert_e_avaliable']}</label>
		                            <div class="col-md-6">
		                                <form:input cssClass="form-control" path="eoRegistered.description2" placeholder="${i18n['createeo_cert_e_avaliable_placeholder']}" data-i18n="createeo_cert_e_avaliable_placeholder"/>
		                            </div>
	                            </div>
	                       </div>
	                       <div class="col-md-12">
	                       		<div class="form-group">
		                            <label class="control-label col-md-6">${span18n['createeo_ref_for_cert']}</label>
		                            <div class="col-md-6">
		                                <form:input cssClass="form-control" path="eoRegistered.description3" placeholder="${i18n['createeo_ref_for_cert_placeholder']}" data-i18n="createeo_ref_for_cert_placeholder"/>
		                            </div>
		                        </div>
	                       </div>
	                       <div class="col-md-12">
	                       		<div class="form-group">
		                            <label class="control-label col-md-6">${span18n['createeo_all_selection_covered']}</label>
		                            <div class="col-md-6">
		                                <form:checkbox path="eoRegistered.booleanValue1" cssClass="radioslide checktoggle form-control" />
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
		                                <form:input cssClass="form-control" path="eoRegistered.description4" placeholder="${i18n['createeo_eo_has_cert_soc_placeholder']}" data-i18n="createeo_eo_has_cert_soc_placeholder"/>
		                            </div>
	                            </div>
	                       </div>
	                       <div class="col-md-12">
	                       		<div class="form-group">
		                            <label class="control-label col-md-6"> ${span18n['createeo_doc_e_avaliable']}</label>
		                            <div class="col-md-6">
		                                <form:input cssClass="form-control" path="eoRegistered.description5" placeholder="${i18n['createeo_doc_e_avaliable_placeholder']}" data-i18n="createeo_doc_e_avaliable_placeholder"/>
		                            </div>
	                            </div>
	                       </div>
					</div>
					
                       <div class="col-md-12">
                       		<div class="form-group">
	                            <label class="control-label col-md-6">${span18n['createeo_is_eo_proc_together']}</label>
	                            <div class="col-md-6">
	                                <form:checkbox path="eoParticipatingProcurementProcedure.exists" data-toggle="collapse" data-target="#group-form" cssClass="radioslide checktoggle form-control" />
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
		                                <form:input cssClass="form-control" path="eoParticipatingProcurementProcedure.description1" placeholder="${i18n['createeo_eo_group_role_placeholder']}" data-i18n="createeo_eo_group_role_placeholder"/>
		                            </div>
	                           	</div>
	                       </div>
	                       <div class="col-md-12">
	                       		<div class="form-group">
		                            <label class="control-label col-md-6"> ${span18n['createeo_other_eo_part']}</label>
		                            <div class="col-md-6">
		                                <form:input cssClass="form-control" path="eoParticipatingProcurementProcedure.description2" placeholder="${i18n['createeo_other_eo_part_placeholder']}" data-i18n="createeo_other_eo_part_placeholder"/>
		                            </div>
	                            </div>
	                       </div>
	                       <div class="col-md-12">
	                       		<div class="form-group">
		                            <label class="control-label col-md-6" > ${span18n['createeo_name_part_group']}</label>
		                            <div class="col-md-6">
		                                <form:input cssClass="form-control" path="eoParticipatingProcurementProcedure.description3" placeholder="${i18n['createeo_name_part_group_placeholder']}" data-i18n="createeo_name_part_group_placeholder"/>
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
	                                <form:input cssClass="form-control" path="economicOperator.representative.firstName" placeholder="${i18n['createeo_first_name_placeholder']}" data-i18n="createeo_first_name_placeholder"/>
	                           </div>
                           </div>
                       		<div class="form-group">
	                           <label class="control-label col-md-4">${span18n['createeo_birth_date']}</label>
	                           <div class="col-md-8"> 
									<form:input path="economicOperator.representative.dateOfBirth" cssClass="form-control datepicker" placeholder="${i18n['createeo_birth_date_placeholder']}" data-i18n="createeo_birth_date_placeholder"/>
	                           </div>
                           </div>
                       </div>
                       <div class="col-md-6">
                       		<div class="form-group">
	                           <label class="control-label col-md-4">${span18n['createeo_last_name']}</label>
	                           <div class="col-md-8">
	                                <form:input cssClass="form-control" path="economicOperator.representative.lastName" placeholder="${i18n['createeo_last_name_placeholder']}" data-i18n="createeo_last_name_placeholder"/>
	                           </div>
                           </div>
                       		<div class="form-group">
	                           <label class="control-label col-md-4">${span18n['createeo_birth_place']}</label>
	                           <div class="col-md-8">
	                                <form:input cssClass="form-control" path="economicOperator.representative.placeOfBirth" placeholder="${i18n['createeo_birth_place_placeholder']}" data-i18n="createeo_birth_place_placeholder"/>
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
                               <label class="control-label col-md-4" data-i18n="createca_email"><s:message code="createca_email"/></label>
                               <div class="col-md-8">
                                   <form:input cssClass="form-control" path="economicOperator.representative.email" placeholder="${i18n['createca_email_placeholder']}" data-i18n="createca_email_placeholder"/>
                               </div>
                           </div>
                           <div class="form-group">
                               <label class="control-label col-md-4" data-i18n="createca_telephone"><s:message code="createca_telephone"/></label>
                               <div class="col-md-8">
                                   <form:input cssClass="form-control" path="economicOperator.representative.phone" placeholder="${i18n['createca_telephone_placeholder']}" data-i18n="createca_telephone_placeholder"/>
                               </div>
                           </div>
                       		<div class="form-group">
	                           <label class="control-label col-md-4" >${span18n['createeo_pos_act_in_capacity']}</label>
	                           <div class="col-md-8">
	                                 <form:input cssClass="form-control" path="economicOperator.representative.position" placeholder="${i18n['createeo_pos_act_in_capacity_placeholder']}" data-i18n="createeo_pos_act_in_capacity_placeholder"/>
	                           </div>
                           </div>
                       </div>
                       <div class="col-md-12">
                       		<div class="form-group">
	                            <label class="control-label col-md-2">${span18n['createeo_detinfo_of_represent']}</label>
	                            <div class="col-md-10">
	                               <form:textarea path="economicOperator.representative.additionalInfo" cssStyle="resize: none" rows="4" cols="20" cssClass="form-control" placeholder="${i18n['createeo_detinfo_of_represent_placeholder']}" data-i18n="createeo_detinfo_of_represent_placeholder"/>
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
                                <form:checkbox path="eoReliesCapacities.exists" cssClass="radioslide checktoggle form-control" />
                            </div>
                       </div>
                       <div class="col-md-12 alert alert-espd-info" style="border: 1px dotted blue; background-color: #D8D8D8;margin-top: 15px;">
                            ${span18n['createeo_separate_espd_sections_a_b']}
                       </div>
	                </div>
	            </div>
	        </div>

    </div>




	<div class="panel-default">

		<div class="paragraph">
			<h2>${span18n["createcaexcl_header"]}</h2>
		</div>
		
		<div class="panel panel-default espd-panel">

			<div class="espd-panel-heading" data-toggle="collapse" data-target="#criminal_conv">
				${span18n["crit_top_title_grounds_criminal_conv"]}
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

	</div>
	
	
	
	
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
            </div>
            <div id="eo-satisfies-all-section" class="collapse in">
                <div class="espd-panel-body panel-body">
					<strong>${span18n['crit_selection_eo_declares_that']}</strong>
					<span data-i18n="crit_selection_eo_declares_that_tooltip" data-toggle="tooltip" title="${i18n['crit_selection_eo_satisfies_all_criteria']}"></span>
                </div>
                <div class="row criteria-row">
                    <div class="col-md-5 criteria-cell-left">
                        <div class="form-group">
                            <div class="col-md-12">
                                <strong>1. ${span18n['crit_selection_eo_satisfies_all_criteria']}</strong>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-7 criteria-cell-right">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label class="control-label small" style="padding-top:0;">${span18n['crit_your_answer']}</label>
                                <form:checkbox path="selectionSatisfiesAll.exists" data-toggle="collapse" data-target-invert="${'#'}eo-satisfies-all-form" class="radioslide checktoggle form-control"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="eo-satisfies-all-form">
	        <div class="panel panel-default espd-panel">
	            <div data-i18n="createcasel_suitability" class="espd-panel-heading" data-toggle="collapse" data-target="#eo-suitability-section">
	                <s:message code='createcasel_suitability'/>
	            </div>
	            <div id="eo-suitability-section" class="collapse in">
	                <div class="espd-panel-body panel-body">
	                    <div class="">
	                        <strong data-i18n="crit_selection_eo_suitability_article">
	                            <s:message code='crit_selection_eo_suitability_article'/>
	                        </strong>
	                        <span data-i18n="crit_selection_eo_suitability_article_tooltip" data-toggle="tooltip"
	                              title="<s:message code='crit_selection_eo_suitability_article_tooltip'/>"></span>
	                    </div>
	                </div>
	                <tiles:insertDefinition name="suitabilityCriterionEO">
	                    <tiles:putAttribute name="field" value="enrolmentProfessionalRegister"/>
	                    <tiles:putAttribute name="number" value="2"/>
	                    <tiles:putAttribute name="has_your_answer" value="true"/>
	                    <tiles:putAttribute name="title_code" value="crit_selection_suitability_enrolment_professional_register_main"/>
	                    <tiles:putAttribute name="tooltip_code" value="crit_selection_suitability_enrolment_professional_register_description"/>
	                </tiles:insertDefinition>
	
	                <tiles:insertDefinition name="suitabilityCriterionEO">
	                    <tiles:putAttribute name="field" value="enrolmentTradeRegister"/>
	                    <tiles:putAttribute name="number" value="3"/>
	                    <tiles:putAttribute name="has_your_answer" value="true"/>
	                    <tiles:putAttribute name="title_code" value="crit_selection_suitability_enrolment_trade_register_main"/>
	                    <tiles:putAttribute name="tooltip_code"
	                                        value="crit_selection_suitability_enrolment_trade_register_description"/>
	                </tiles:insertDefinition>
	
	                <tiles:insertDefinition name="suitabilityCriterionEO">
	                    <tiles:putAttribute name="field" value="serviceContractsAuthorisation"/>
	                    <tiles:putAttribute name="number" value="4"/>
	                    <tiles:putAttribute name="has_your_answer" value="true"/>
	                    <tiles:putAttribute name="title_code" value="crit_selection_suitability_service_contracts_auth_main"/>
	                    <tiles:putAttribute name="tooltip_code"
	                                        value="crit_selection_suitability_service_contracts_auth_description"/>
	                </tiles:insertDefinition>
	
	                <tiles:insertDefinition name="suitabilityCriterionEO">
	                    <tiles:putAttribute name="field" value="serviceContractsMembership"/>
	                    <tiles:putAttribute name="number" value="5"/>
	                    <tiles:putAttribute name="has_your_answer" value="true"/>
	                    <tiles:putAttribute name="title_code" value="crit_selection_suitability_service_contracts_membership_main"/>
	                    <tiles:putAttribute name="tooltip_code"
	                                        value="crit_selection_suitability_service_contracts_membership_description"/>
	                </tiles:insertDefinition>
	            </div>
	        </div>
	        <div class="panel panel-default espd-panel">
	            <div data-i18n="createcasel_economic_and_financial_standing" class="espd-panel-heading" data-toggle="collapse" data-target="#eo-economic-financial-section">
	                <s:message code='createcasel_economic_and_financial_standing'/>
	            </div>
	            <div id="eo-economic-financial-section" class="collapse in">
	                <div class="espd-panel-body panel-body">
	                    <div class="">
	                        <strong data-i18n="crit_selection_eo_economic_article">
	                            <s:message code='crit_selection_eo_economic_article'/>
	                        </strong>
	                        <span data-i18n="crit_selection_eo_economic_article_tooltip" data-toggle="tooltip" title="${i18n['crit_selection_eo_economic_article_tooltip']}"></span>
	                    </div>
	                </div>
	                <tiles:insertDefinition name="economicFinancialCriterionEO">
	                    <tiles:putAttribute name="field" value="generalYearlyTurnover"/>
	                    <tiles:putAttribute name="number" value="6"/>
	                    <tiles:putAttribute name="has_your_answer" value="true"/>
	                    <tiles:putAttribute name="lastYearsAmount" value="${lastYearsAmount}"/>
	                    <tiles:putAttribute name="title_code" value="crit_selection_economic_general_yearly_turnover_main"/>
	                    <tiles:putAttribute name="tooltip_code" value="crit_selection_economic_general_yearly_turnover_description"/>
	                </tiles:insertDefinition>
	
	                <tiles:insertDefinition name="economicFinancialCriterionEO">
	                    <tiles:putAttribute name="field" value="averageYearlyTurnover"/>
	                    <tiles:putAttribute name="number" value="7"/>
	                    <tiles:putAttribute name="has_your_answer" value="true"/>
	                    <tiles:putAttribute name="lastYearsAmount" value="${lastYearsAmount}"/>
	                    <tiles:putAttribute name="title_code" value="crit_selection_economic_average_yearly_turnover_main"/>
	                    <tiles:putAttribute name="tooltip_code"
	                                        value="crit_selection_economic_average_yearly_turnover_description"/>
	                </tiles:insertDefinition>
	
	                <tiles:insertDefinition name="economicFinancialCriterionEO">
	                    <tiles:putAttribute name="field" value="specificYearlyTurnover"/>
	                    <tiles:putAttribute name="number" value="8"/>
	                    <tiles:putAttribute name="has_your_answer" value="true"/>
	                    <tiles:putAttribute name="lastYearsAmount" value="${lastYearsAmount}"/>
	                    <tiles:putAttribute name="title_code" value="crit_selection_economic_specific_yearly_turnover_main"/>
	                    <tiles:putAttribute name="tooltip_code"
	                                        value="crit_selection_economic_specific_yearly_turnover_description"/>
	                </tiles:insertDefinition>
	
	                <tiles:insertDefinition name="economicFinancialCriterionEO">
	                    <tiles:putAttribute name="field" value="specificAverageTurnover"/>
	                    <tiles:putAttribute name="number" value="9"/>
	                    <tiles:putAttribute name="has_your_answer" value="true"/>
	                    <tiles:putAttribute name="lastYearsAmount" value="${lastYearsAmount}"/>
	                    <tiles:putAttribute name="title_code" value="crit_selection_economic_specific_average_turnover_main"/>
	                    <tiles:putAttribute name="tooltip_code"
	                                        value="crit_selection_economic_specific_average_turnover_description"/>
	                </tiles:insertDefinition>
	
	                <tiles:insertDefinition name="economicFinancialCriterionEO">
	                    <tiles:putAttribute name="field" value="financialRatio"/>
	                    <tiles:putAttribute name="number" value="10"/>
	                    <tiles:putAttribute name="has_multiple_description_ratio" value="true"/>
	                    <tiles:putAttribute name="has_your_answer" value="false"/>
	                    <tiles:putAttribute name="title_code" value="crit_selection_economic_financial_ratio_main"/>
	                    <tiles:putAttribute name="tooltip_code"
	                                        value="crit_selection_economic_financial_ratio_description"/>
	                </tiles:insertDefinition>
	
	                <tiles:insertDefinition name="economicFinancialCriterionEO">
	                    <tiles:putAttribute name="field" value="professionalRiskInsurance"/>
	                    <tiles:putAttribute name="number" value="11"/>
	                    <tiles:putAttribute name="has_your_answer" value="false"/>
	                    <tiles:putAttribute name="has_single_amount" value="true"/>
	                    <tiles:putAttribute name="title_code" value="crit_selection_economic_professional_risk_insurance_main"/>
	                    <tiles:putAttribute name="tooltip_code"
	                                        value="crit_selection_economic_professional_risk_insurance_description"/>
	                </tiles:insertDefinition>
	
	                <tiles:insertDefinition name="economicFinancialCriterionEO">
	                    <tiles:putAttribute name="field" value="otherEconomicFinancialRequirements"/>
	                    <tiles:putAttribute name="number" value="12"/>
	                    <tiles:putAttribute name="has_your_answer" value="false"/>
	                    <tiles:putAttribute name="has_please_describe_them" value="true"/>
	                    <tiles:putAttribute name="title_code" value="crit_selection_economic_other_financial_requirements_main"/>
	                    <tiles:putAttribute name="tooltip_code"
	                                        value="crit_selection_economic_other_financial_requirements_description"/>
	                </tiles:insertDefinition>
	            </div>
	        </div>
	        <div class="panel panel-default espd-panel">
	            <div data-i18n="createcasel_technical_professional_ability" class="espd-panel-heading" data-toggle="collapse" data-target="#eo-technical-professional-section">
	                <s:message code='createcasel_technical_professional_ability'/>
	            </div>
	            <div id="eo-technical-professional-section" class="collapse in">
	                <div class="espd-panel-body panel-body">
	                    <div class="">
	                        <strong data-i18n="crit_selection_technical_professional_ability_article">
	                            <s:message code='crit_selection_technical_professional_ability_article'/>
	                        </strong>
	                        <span data-i18n="crit_selection_technical_professional_ability_article_tooltip" data-toggle="tooltip"
	                              title="<s:message code='crit_selection_technical_professional_ability_article_tooltip'/>"></span>
	                    </div>
	                </div>
	
	                <tiles:insertDefinition name="technicalProfessionalCriterionEO">
	                    <tiles:putAttribute name="field" value="workContractsPerformanceOfWorks"/>
	                    <tiles:putAttribute name="number" value="13"/>
	                    <tiles:putAttribute name="has_multiple_description_amount_date_recipients" value="true"/>
	                    <tiles:putAttribute name="title_code" value="crit_selection_technical_work_contracts_performance_works_main"/>
	                    <tiles:putAttribute name="tooltip_code"
	                                        value="crit_selection_technical_work_contracts_performance_works_description"/>
	                </tiles:insertDefinition>
	
	                <tiles:insertDefinition name="technicalProfessionalCriterionEO">
	                    <tiles:putAttribute name="field" value="supplyContractsPerformanceDeliveries"/>
	                    <tiles:putAttribute name="number" value="14"/>
	                    <tiles:putAttribute name="has_multiple_description_amount_date_recipients" value="true"/>
	                    <tiles:putAttribute name="title_code" value="crit_selection_technical_supply_contracts_performance_deliveries_main"/>
	                    <tiles:putAttribute name="tooltip_code"
	                                        value="crit_selection_technical_supply_contracts_performance_deliveries_description"/>
	                </tiles:insertDefinition>
	
	                <tiles:insertDefinition name="technicalProfessionalCriterionEO">
	                    <tiles:putAttribute name="field" value="serviceContractsPerformanceServices"/>
	                    <tiles:putAttribute name="number" value="15"/>
	                    <tiles:putAttribute name="has_multiple_description_amount_date_recipients" value="true"/>
	                    <tiles:putAttribute name="title_code" value="crit_selection_technical_service_contracts_performance_services_main"/>
	                    <tiles:putAttribute name="tooltip_code"
	                                        value="crit_selection_technical_service_contracts_performance_services_description"/>
	                </tiles:insertDefinition>
	
	                <tiles:insertDefinition name="technicalProfessionalCriterionEO">
	                    <tiles:putAttribute name="field" value="techniciansTechnicalBodies"/>
	                    <tiles:putAttribute name="number" value="16"/>
	                    <tiles:putAttribute name="has_please_describe_them" value="true"/>
	                    <tiles:putAttribute name="title_code" value="crit_selection_technical_technicians_technical_bodies_main"/>
	                    <tiles:putAttribute name="tooltip_code"
	                                        value="crit_selection_technical_technicians_technical_bodies_description"/>
	                </tiles:insertDefinition>
	
	                <tiles:insertDefinition name="technicalProfessionalCriterionEO">
	                    <tiles:putAttribute name="field" value="workContractsTechnicians"/>
	                    <tiles:putAttribute name="number" value="17"/>
	                    <tiles:putAttribute name="has_please_describe_them" value="true"/>
	                    <tiles:putAttribute name="title_code" value="crit_selection_technical_work_contracts_technicians_main"/>
	                    <tiles:putAttribute name="tooltip_code"
	                                        value="crit_selection_technical_work_contracts_technicians_description"/>
	                </tiles:insertDefinition>
	
	                <tiles:insertDefinition name="technicalProfessionalCriterionEO">
	                    <tiles:putAttribute name="field" value="technicalFacilitiesMeasures"/>
	                    <tiles:putAttribute name="number" value="18"/>
	                    <tiles:putAttribute name="has_please_describe_them" value="true"/>
	                    <tiles:putAttribute name="title_code" value="crit_selection_technical_technical_facilities_measures_main"/>
	                    <tiles:putAttribute name="tooltip_code"
	                                        value="crit_selection_technical_technical_facilities_measures_description"/>
	                </tiles:insertDefinition>
	
	                <tiles:insertDefinition name="technicalProfessionalCriterionEO">
	                    <tiles:putAttribute name="field" value="studyResearchFacilities"/>
	                    <tiles:putAttribute name="number" value="19"/>
	                    <tiles:putAttribute name="has_please_describe_them" value="true"/>
	                    <tiles:putAttribute name="title_code" value="crit_selection_technical_study_research_facilities_main"/>
	                    <tiles:putAttribute name="tooltip_code"
	                                        value="crit_selection_technical_study_research_facilities_description"/>
	                </tiles:insertDefinition>
	
	                <tiles:insertDefinition name="technicalProfessionalCriterionEO">
	                    <tiles:putAttribute name="field" value="supplyChainManagement"/>
	                    <tiles:putAttribute name="number" value="20"/>
	                    <tiles:putAttribute name="has_please_describe_them" value="true"/>
	                    <tiles:putAttribute name="title_code" value="crit_selection_technical_supply_chain_management_main"/>
	                    <tiles:putAttribute name="tooltip_code"
	                                        value="crit_selection_technical_supply_chain_management_description"/>
	                </tiles:insertDefinition>
	
	                <tiles:insertDefinition name="technicalProfessionalCriterionEO">
	                    <tiles:putAttribute name="field" value="allowanceOfChecks"/>
	                    <tiles:putAttribute name="number" value="21"/>
	                    <tiles:putAttribute name="has_your_answer" value="true"/>
	                    <tiles:putAttribute name="allows_checks" value="true"/>
	                    <tiles:putAttribute name="has_info_electronically" value="false"/>
	                    <tiles:putAttribute name="title_code" value="crit_selection_technical_allowance_of_checks_main"/>
	                    <tiles:putAttribute name="tooltip_code"
	                                        value="crit_selection_technical_allowance_of_checks_description"/>
	                </tiles:insertDefinition>
	
	                <tiles:insertDefinition name="technicalProfessionalCriterionEO">
	                    <tiles:putAttribute name="field" value="educationalProfessionalQualifications"/>
	                    <tiles:putAttribute name="number" value="22"/>
	                    <tiles:putAttribute name="has_please_describe_them" value="true"/>
	                    <tiles:putAttribute name="title_code" value="crit_selection_technical_educational_professional_qualifications_main"/>
	                    <tiles:putAttribute name="tooltip_code"
	                                        value="crit_selection_technical_educational_professional_qualifications_description"/>
	                </tiles:insertDefinition>
	
	                <tiles:insertDefinition name="technicalProfessionalCriterionEO">
	                    <tiles:putAttribute name="field" value="environmentalManagementFeatures"/>
	                    <tiles:putAttribute name="number" value="23"/>
	                    <tiles:putAttribute name="has_please_describe_them" value="true"/>
	                    <tiles:putAttribute name="title_code" value="crit_selection_technical_environment_management_features_main"/>
	                    <tiles:putAttribute name="tooltip_code"
	                                        value="crit_selection_technical_environment_management_features_description"/>
	                </tiles:insertDefinition>
	
	                <tiles:insertDefinition name="technicalProfessionalCriterionEO">
	                    <tiles:putAttribute name="field" value="numberManagerialStaff"/>
	                    <tiles:putAttribute name="number" value="24"/>
	                    <tiles:putAttribute name="lastYearsNumber" value="${lastYearsNumber}"/>
	                    <tiles:putAttribute name="title_code" value="crit_selection_technical_number_managerial_staff_main"/>
	                    <tiles:putAttribute name="tooltip_code"
	                                        value="crit_selection_technical_number_managerial_staff_description"/>
	                </tiles:insertDefinition>
	
	                <tiles:insertDefinition name="technicalProfessionalCriterionEO">
	                    <tiles:putAttribute name="field" value="averageAnnualManpower"/>
	                    <tiles:putAttribute name="number" value="25"/>
	                    <tiles:putAttribute name="lastYearsNumber" value="${lastYearsNumber}"/>
	                    <tiles:putAttribute name="title_code" value="crit_selection_technical_average_annual_manpower_main"/>
	                    <tiles:putAttribute name="tooltip_code"
	                                        value="crit_selection_technical_average_annual_manpower_description"/>
	                </tiles:insertDefinition>
	
	                <tiles:insertDefinition name="technicalProfessionalCriterionEO">
	                    <tiles:putAttribute name="field" value="toolsPlantTechnicalEquipment"/>
	                    <tiles:putAttribute name="number" value="26"/>
	                    <tiles:putAttribute name="has_please_describe_them" value="true"/>
	                    <tiles:putAttribute name="title_code" value="crit_selection_technical_tools_plant_main"/>
	                    <tiles:putAttribute name="tooltip_code"
	                                        value="crit_selection_technical_tools_plant_description"/>
	                </tiles:insertDefinition>
	
	                <tiles:insertDefinition name="technicalProfessionalCriterionEO">
	                    <tiles:putAttribute name="field" value="subcontractingProportion"/>
	                    <tiles:putAttribute name="number" value="27"/>
	                    <tiles:putAttribute name="has_please_describe_them" value="true"/>
	                    <tiles:putAttribute name="title_code" value="crit_selection_technical_subcontracting_proportion_main"/>
	                    <tiles:putAttribute name="tooltip_code"
	                                        value="crit_selection_technical_subcontracting_proportion_description"/>
	                </tiles:insertDefinition>
	
	                <tiles:insertDefinition name="technicalProfessionalCriterionEO">
	                    <tiles:putAttribute name="field" value="supplyContractsSamplesDescriptionsWithoutCa"/>
	                    <tiles:putAttribute name="number" value="28"/>
	                    <tiles:putAttribute name="has_your_answer" value="true"/>
	                    <tiles:putAttribute name="title_code" value="crit_selection_technical_supply_contracts_without_ca_main"/>
	                    <tiles:putAttribute name="tooltip_code"
	                                        value="crit_selection_technical_supply_contracts_without_ca_description"/>
	                </tiles:insertDefinition>
	
	                <tiles:insertDefinition name="technicalProfessionalCriterionEO">
	                    <tiles:putAttribute name="field" value="supplyContractsSamplesDescriptionsWithCa"/>
	                    <tiles:putAttribute name="number" value="29"/>
	                    <tiles:putAttribute name="has_your_answer" value="true"/>
	                    <tiles:putAttribute name="title_code" value="crit_selection_technical_supply_contracts_with_ca_main"/>
	                    <tiles:putAttribute name="tooltip_code"
	                                        value="crit_selection_technical_supply_contracts_with_ca_description"/>
	                </tiles:insertDefinition>
	
	                <tiles:insertDefinition name="technicalProfessionalCriterionEO">
	                    <tiles:putAttribute name="field" value="supplyContractsCertificatesQc"/>
	                    <tiles:putAttribute name="number" value="30"/>
	                    <tiles:putAttribute name="has_your_answer" value="true"/>
	                    <tiles:putAttribute name="has_please_describe_them" value="true"/>
	                    <tiles:putAttribute name="has_explain_supply_contracts_quality" value="true"/>
	                    <tiles:putAttribute name="title_code" value="crit_selection_technical_supply_contracts_certificate_quality_main"/>
	                    <tiles:putAttribute name="tooltip_code"
	                                        value="crit_selection_technical_supply_contracts_certificate_quality_description"/>
	                </tiles:insertDefinition>
	
	                <tiles:insertDefinition name="technicalProfessionalCriterionEO">
	                    <tiles:putAttribute name="field" value="certificateIndependentBodiesAboutQa"/>
	                    <tiles:putAttribute name="number" value="31"/>
	                    <tiles:putAttribute name="has_your_answer" value="true"/>
	                    <tiles:putAttribute name="has_please_describe_them" value="true"/>
	                    <tiles:putAttribute name="has_explain_certificates_independent_quality" value="true"/>
	                    <tiles:putAttribute name="title_code" value="crit_selection_technical_certificate_independent_bodies_quality_main"/>
	                    <tiles:putAttribute name="tooltip_code"
	                                        value="crit_selection_technical_certificate_independent_bodies_quality_description"/>
	                </tiles:insertDefinition>
	
	                <tiles:insertDefinition name="technicalProfessionalCriterionEO">
	                    <tiles:putAttribute name="field" value="certificateIndependentBodiesAboutEnvironmental"/>
	                    <tiles:putAttribute name="number" value="32"/>
	                    <tiles:putAttribute name="has_your_answer" value="true"/>
	                    <tiles:putAttribute name="has_please_describe_them" value="true"/>
	                    <tiles:putAttribute name="has_explain_certificates_independent_environmental" value="true"/>
	                    <tiles:putAttribute name="title_code" value="crit_selection_technical_certificate_independent_bodies_environmental_main"/>
	                    <tiles:putAttribute name="tooltip_code"
	                                        value="crit_selection_technical_certificate_independent_bodies_environmental_description"/>
	                </tiles:insertDefinition>
	            </div>
	        </div>
		</div>
    
    </div>
    
    
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
					
					<span data-i18n="createcafinish_reduction_question" class="aligned" style="font-weight: bold;">
                        <s:message code='createcafinish_reduction_question'/>
                    </span>
					
					<tiles:insertDefinition name="simpleFormCriterion">
						<tiles:putAttribute name="field" value="meetsObjective"/>
                        <tiles:putAttribute name="descriptionField" value="description1"/>
						<tiles:putAttribute name="title_code" value="createcafinish_title_eo_declares_that"/>
						<tiles:putAttribute name="description_code" value="createcafinish_text_eo_declares_that"/>
						<tiles:putAttribute name="availableElectronically" value="true"/>
						<tiles:putAttribute name="selfCleaning" value="false"/>
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
                    <span data-i18n="createcafinish_concl_statements_text" class="aligned" style="">
                        <s:message code='createcafinish_concl_statements_text'/>
                    </span>
                    
                    <p>
	                    <span data-i18n="createcafinish_concl_statements_signature" class="aligned" style="">
	                        <s:message code='createcafinish_concl_statements_signature'/>
	                    </span>
                    </p>
                    
                </div>
            </div>
		</div>

	</div>
    
    
</form:form>
