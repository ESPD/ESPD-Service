<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

        <div class="errorContainer alert alert-danger" style="display: none">
            <ul class="fa-ul">
                <li>
                    <i class="info-label fa fa-exclamation-triangle fa-lg fa-li"></i>
                        ${div18n['correct_errors']}
                    <div class="errorLabelContainer"></div>
                </li>
            </ul>
        </div>
        <div>
            <h2>${span18n['createca_header']}</h2>
        </div>
        <div class="panel panel-espd">
            <div class="panel-heading" data-toggle="collapse" data-target="#ojsdiv">
            	<h4 class="panel-title">${span18n['createca_info_pub']}</h4>
            </div>
            <div id="ojsdiv" class="panel-body collapse in">
                <div class="alert alert-espd-info-dotted">
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
        <div class="panel panel-espd">
            <div class="panel-heading" data-toggle="collapse" data-target="#cadiv">
            	<h4 class="panel-title">${span18n['createca_contact_details_ca']}</h4>
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
        <div class="panel panel-espd">
            <div class="panel-heading" data-toggle="collapse" data-target="#ppdiv">
				<h4 class="panel-title">${span18n['createca_info_procurement_proc']}</h4>
            </div>
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
            <div>
                <h2>${span18n['createeo_header']}</h2>
            </div>
            <div class="panel panel-espd">
                <div class="panel-heading" data-toggle="collapse"data-target="#createeo_info_eo_div">
					<h4 class="panel-title">${span18n['createeo_info_eo']}</h4>
                </div>
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
                                    <label class="control-label col-md-6">${span18n['createeo_is_eo_sized']}
                                        <c:if test="${step != 'print'}">
                                            <span data-i18n="createeo_is_eo_sized_tooltip"
                                                  title="${i18n['createeo_is_eo_sized_tooltip']}"
                                                  data-toggle="tooltip"></span>
                                        </c:if>
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
                                <div class="col-md-12 alert alert-espd-info-dotted">${span18n['createeo_answer_following_parts']}</div>
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
                                <div class="col-md-12 alert alert-espd-info-dotted">
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
                                <div class="col-md-12 alert alert-espd-info-dotted">${span18n['createeo_ensure_others_espd']}</div>
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
            

            <c:forEach var="representative" items="${espd.economicOperator.representatives}" varStatus="vs">
            
            	<a name="representative${vs.index}"></a>

	            <div class="panel panel-espd">
	            
	                <div class="panel-heading clearfix" data-toggle="collapse" data-target="#createeo_info_respresent_div${vs.index}">
	                	<h4 class="panel-title pull-left" style="padding-top: 7.5px;">
	                		${span18n['createeo_info_respresent']} ${'#'}${vs.index+1}
	                	</h4>
	                	<div class="btn-group pull-right hidden-print">
							<button id="addRepresentative" type="submit" class="btn btn-default btn-sm" name="add" value="${vs.index + 1}">
								<i class="fa fa-plus" aria-hidden="true"></i>
							</button>
							<button id="removeRepresentative" type="submit" class="btn btn-default btn-sm " name="remove" value="${vs.index}">
								<i class="fa fa-trash" aria-hidden="true"></i>
							</button>
	                	</div>
	                </div>
	                
	                <div id="createeo_info_respresent_div${vs.index}" class="collapse in">
	                    <div class="panel-body">
	                    
	                    	<c:if test="${vs.index == 0}"><%-- display this alert only for first representative --%>
		                        <div class="col-md-12 alert alert-espd-info-dotted">
									${span18n['createeo_person_empowered']}
		                        </div>
	                        </c:if>

	                            <div class="col-md-12">
	                            	<div class="col-md-6">
		                                <div class="form-group">
		                                    <label class="control-label col-md-4">

			                                   	 ${span18n['createeo_first_name']}
		                                    </label>
		
		                                    <div class="col-md-8">
		                                        <form:textarea rows="1" cssClass="form-control"
		                                                    path="economicOperator.representatives[${vs.index}].firstName"/>
		                                    </div>
		                                </div>
	                            	</div>
	                            	<div class="col-md-6">
		                                <div class="form-group">
		                                    <label class="control-label col-md-4">${span18n['createeo_last_name']}</label>
		
		                                    <div class="col-md-8">
		                                        <form:textarea rows="1" cssClass="form-control"
		                                                    path="economicOperator.representatives[${vs.index}].lastName"/>
		                                    </div>
		                                </div>
	                            	</div>
	                            </div> 
	                            <div class="col-md-12">
	                            	<div class="col-md-6">
		                                <div class="form-group">
		                                    <label class="control-label col-md-4">${span18n['createeo_birth_date']}</label>
		                                    <div class="col-md-8">
		                                        <form:input type="text" path="economicOperator.representatives[${vs.index}].dateOfBirth" cssClass="form-control datepicker"/>
		                                    </div>
		                                </div>
	                            	</div>
	                            	<div class="col-md-6">
		                                <div class="form-group">
		                                    <label class="control-label col-md-4">${span18n['createeo_birth_place']}</label>
		
		                                    <div class="col-md-8">
		                                        <form:textarea rows="1" cssClass="form-control"
		                                                    path="economicOperator.representatives[${vs.index}].placeOfBirth"/>
		                                    </div>
		                                </div>
	                            	</div>
	                            </div>
	                            <div class="col-md-12">
		                            <div class="col-md-6">
		                                <tiles:insertDefinition name="partyInfo">
		                                    <tiles:putAttribute name="field" value="economicOperator.representatives[${vs.index}]"/>
		                                    <tiles:putAttribute name="address" value="true"/>
		                                </tiles:insertDefinition>
		                            </div>
		                            <div class="col-md-6">
		                                <div class="form-group">
		                                    <label class="control-label col-md-4" data-i18n="createca_email"><s:message
		                                            code="createca_email"/></label>
		
		                                    <div class="col-md-8">
		                                        <form:textarea rows="1" cssClass="form-control" path="economicOperator.representatives[${vs.index}].email"/>
		                                    </div>
		                                </div>
		                                <div class="form-group">
		                                    <label class="control-label col-md-4" data-i18n="createca_telephone"><s:message
		                                            code="createca_telephone"/></label>
		
		                                    <div class="col-md-8">
		                                        <form:textarea rows="1" cssClass="form-control" path="economicOperator.representatives[${vs.index}].phone"/>
		                                    </div>
		                                </div>
		                                <div class="form-group">
		                                    <label class="control-label col-md-4">${span18n['createeo_pos_act_in_capacity']}</label>
		
		                                    <div class="col-md-8">
		                                        <form:textarea rows="1" cssClass="form-control" path="economicOperator.representatives[${vs.index}].position"/>
		                                    </div>
		                                </div>
		                            </div>
	                            </div>
	                            <div class="col-md-12">
	                            	<div class="col-md-12">
		                                <div class="form-group">
		                                    <label class="control-label col-md-2">${span18n['createeo_detinfo_of_represent']}</label>
		
		                                    <div class="col-md-10">
		                                        <form:textarea path="economicOperator.representatives[${vs.index}].additionalInfo"
		                                                       cssStyle="resize: none" rows="4" cols="20"
		                                                       cssClass="form-control"/>
		                                    </div>
		                                </div>
	                                </div>
	                            </div>
						</div>
	                </div>
	            </div>
            </c:forEach>
            
            <div class="panel panel-espd">
                <div class="panel-heading" data-toggle="collapse" data-target="#createeo_info_reliance_div">
					<h4 class="panel-title">${span18n['createeo_info_reliance']}</h4>
                </div>
                <div id="createeo_info_reliance_div" class="collapse in">
                    <div class="panel-body">
                        <div class="col-md-12 form-group">
                            <label class="control-label col-md-6">
                                    ${span18n['createeo_eo_consortium_name']}
                            </label>
                            <div class="col-md-6">
                                <form:textarea path="consortiumName"
                                               cssStyle="resize: none" rows="1" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="col-md-12 form-group">
                            <label class="control-label col-md-6">
                                    ${span18n['createeo_eo_rely_other_entities']}
                            </label>
                            <div class="col-md-6">
                                <form:radiobutton path="eoReliesCapacities.answer" value="true"
                                                  data-target-show="#separate_espd_div"/>${span18n["yes"]}
                                <form:radiobutton path="eoReliesCapacities.answer" value="false"
                                                  data-target-hide="#separate_espd_div"/>${span18n["no"]}
                            </div>
                        </div>
                        <div id="separate_espd_div"
                             class="col-md-12 alert  ${espd['eoReliesCapacities'].answer ? '' : 'collapse'}">
                                ${span18n['createeo_separate_espd_sections_a_b']}
                        </div>
                    </div>
                </div>
            </div>
            
            <div class="panel panel-espd">
                <div class="panel-heading" data-toggle="collapse" data-target="#createeo_subcontractors">
					<h4 class="panel-title">${span18n['createeo_information_subcontractors']}</h4>
                </div>
                <div id="createeo_subcontractors" class="collapse in">
                    <div class="panel-body">
                    
                            <div class="col-md-12 alert alert-espd-info-dotted">
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
                            <div class="col-md-12 alert alert-espd-info-dotted">
                            	${span18n['createeo_information_subcontractors_footer']}
                            </div>
                    </div>
                </div>
            </div>
        </c:if>