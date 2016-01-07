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
            errorLabelContainer: $(".errorLabelContainer ul"),
            wrapper: 'li'
        });
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
                <li class="active"><a href="#"><i class="fa fa-random"></i>&nbsp;<span
                        data-i18n="progress_start"><s:message code='progress_start'/></span></a>

                    <div class="nav-arrow"></div>
                </li>
                <li class="active">
                    <div class="nav-wedge"></div>
                    <a href="#"><i class="fa fa-university"></i>&nbsp;<span data-i18n="progress_procedure"><s:message
                            code='progress_procedure'/></span></a>

                    <div class="nav-arrow"></div>
                </li>
                <li>
                    <div class="nav-wedge"></div>
                    <a href="#"><i class="fa fa-exclamation"></i>&nbsp;<span data-i18n="progress_exclusion"><s:message
                            code='progress_exclusion'/></span></a>

                    <div class="nav-arrow"></div>
                </li>
                <li>
                    <div class="nav-wedge"></div>
                    <a href="#"><i class="fa fa-check-circle"></i>&nbsp;<span data-i18n="progress_selection"><s:message
                            code='progress_selection'/></span></a>

                    <div class="nav-arrow"></div>
                </li>
                <li>
                    <div class="nav-wedge"></div>
                    <a href="#"><i class="fa fa-download"></i>&nbsp;<span data-i18n="progress_finish"><s:message
                            code='progress_finish'/></span></a></li>
            </ul>
        </div>

        <div class="errorContainer alert alert-danger" style="display: none">
            <ul class="fa-ul">
                <li>
                    <i class="info-label fa fa-exclamation-triangle fa-lg fa-li"></i>

                    <div data-i18n="createca_correct_errors"><s:message code="createca_correct_errors"/></div>
                    <div class="errorLabelContainer">
                        <ul></ul>
                    </div>
                </li>
            </ul>
        </div>

        <c:if test="${isAgentEO}">
	        <div class="paragraph">
	            <h2>
	                <span data-i18n="createeo_header"><s:message code="createeo_header"/></span>
	            </h2>
	        </div>
        
	        <div class="espd-panel panel panel-default">
	            <div class="espd-panel-heading" data-toggle="collapse" data-target="#createeo_info_eo_div">
	                <span data-i18n="createeo_info_eo"><s:message code="createeo_info_eo"/></span>
	            </div>
	            <div id="createeo_info_eo_div" class="collapse in">
	                <div class="panel-body">
	                	
	                        <div class="col-md-6">
	                            <div class="form-group">
	                                <label class="control-label col-md-4" data-i18n="createeo_name"><s:message code="createeo_name"/></label>
	                                <div class="col-md-8">
	                                    <form:input cssClass="form-control" path="eoperator.name" placeholder="Name"/>
	                                </div>
	                            </div>
		                       <tiles:insertDefinition name="partyInfo">
		                             <tiles:putAttribute name="field" value="eoperator"/>
		                             <tiles:putAttribute name="address" value="true"/>
		                       </tiles:insertDefinition>
	                        </div>
	                        <div class="col-md-6">

	                            <tiles:insertDefinition name="partyInfo">
	                                 <tiles:putAttribute name="field" value="eoperator"/>
	                                 <tiles:putAttribute name="vat" value="true"/>
	                                 <tiles:putAttribute name="contacts" value="true"/>
	                            </tiles:insertDefinition>
                            
		                       <div class="form-group">
		                            <label class="control-label col-md-4" data-i18n="createeo_contact_person"><s:message code="createeo_contact_person"/></label>
		                            <div class="col-md-8">
		                                [TEXTAREA]
		                            </div>
		                        </div>
		
		                       <div class="form-group">
		                            <label class="control-label col-md-4" data-i18n="createeo_internet_addr_if_exists"><s:message code="createeo_internet_addr_if_exists"/></label>
		                            <div class="col-md-8">
		                                [INPUT]
		                            </div>
		                       </div>
	                        </div>
                       

                       <div class="col-md-12 form-group">
                            <label class="control-label col-md-6" data-i18n="createeo_is_eo_sized"><s:message code="createeo_is_eo_sized"/></label>
                            <div class="col-md-6">
                                [YES/NO]
                            </div>
                       </div>
                       <div class="col-md-12 form-group">
                            <label class="control-label col-md-6" data-i18n="createeo_if_proc_reserved">
                            	<s:message code="createeo_if_proc_reserved"/>
                            </label>
                            <div class="col-md-6">
                                [YES/NO]
                            </div>
                       </div>
	                       <div class="col-md-12 form-group">
	                            <label class="control-label col-md-8" data-i18n="createeo_percentage_disworkers">
	                            	<s:message code="createeo_percentage_disworkers"/>
	                            </label>
	                            <div class="col-md-4">
	                                [INPUT]
	                            </div>
	                       </div>
	                       <div class="col-md-12 form-group">
	                            <label class="control-label col-md-8" data-i18n="createeo_disworkers_details">
	                            	<s:message code="createeo_disworkers_details"/>
	                            </label>
	                            <div class="col-md-4">
	                                [INPUT]
	                            </div>
	                       </div>

                       <div class="col-md-12 form-group">
                            <label class="control-label col-md-6" data-i18n="createeo_eo_approved_cert">
                            	<s:message code="createeo_eo_approved_cert"/>
                            </label>
                            <div class="col-md-6">
                                [YES/NO]
                            </div>
                       </div>
                           <div class="col-md-12 alert alert-espd-info" style="border: 1px dotted blue; background-color: #D8D8D8;">
                                [IF YES]<span data-i18n="createeo_answer_following_parts"><s:message code="createeo_answer_following_parts"/></span>
                           </div>
	                       <div class="col-md-12 form-group">
	                            <label class="control-label col-md-8" data-i18n="createeo_provide_regnumber"><s:message code="createeo_provide_regnumber"/></label>
	                            <div class="col-md-4">
	                                [INPUT]
	                            </div>
	                       </div>
	                       <div class="col-md-12 form-group">
	                            <label class="control-label col-md-8" data-i18n="createeo_cert_e_avaliable"> <s:message code="createeo_cert_e_avaliable"/></label>
	                            <div class="col-md-4">
	                                [INPUT]
	                            </div>
	                       </div>
	                       <div class="col-md-12 form-group">
	                            <label class="control-label col-md-8" data-i18n="createeo_ref_for_cert"> <s:message code="createeo_ref_for_cert"/></label>
	                            <div class="col-md-4">
	                                [INPUT]
	                            </div>
	                       </div>
	                       <div class="col-md-12 form-group">
	                            <label class="control-label col-md-8" data-i18n="createeo_all_selection_covered"> <s:message code="createeo_all_selection_covered"/></label>
	                            <div class="col-md-4">
	                                [INPUT]
	                            </div>
	                       </div>
 
                           <div class="col-md-12 alert alert-espd-info" style="border: 1px dotted blue; background-color: #D8D8D8;">
                                [IF NO]<span data-i18n="createeo_add_complete_missing"><s:message code="createeo_add_complete_missing"/></span>
                           </div>
	                       <div class="col-md-12 form-group">
	                            <label class="control-label col-md-8" data-i18n="createeo_eo_has_cert_soc"> <s:message code="createeo_eo_has_cert_soc"/></label>
	                            <div class="col-md-4">
	                                [INPUT]
	                            </div>
	                       </div>
	                       <div class="col-md-12 form-group">
	                            <label class="control-label col-md-8" data-i18n="createeo_doc_e_avaliable"> <s:message code="createeo_doc_e_avaliable"/></label>
	                            <div class="col-md-4">
	                                [INPUT]
	                            </div>
	                       </div>

                       <div class="col-md-12 form-group">
                            <label class="control-label col-md-6" data-i18n="createeo_is_eo_proc_together"><s:message code="createeo_is_eo_proc_together"/></label>
                            <div class="col-md-6">
                                [YES/NO]
                            </div>
                       </div>
                            <div class="col-md-12 alert alert-espd-info" style="border: 1px dotted blue; background-color: #D8D8D8;">
                                [IF YES]<span data-i18n="createeo_ensure_others_espd"><s:message code="createeo_ensure_others_espd"/></span>
                            </div>
	                       <div class="col-md-12 form-group">
	                            <label class="control-label col-md-8" data-i18n="createeo_eo_group_role"> <s:message code="createeo_eo_group_role"/></label>
	                            <div class="col-md-4">
	                                [INPUT]
	                            </div>
	                       </div>
	                       <div class="col-md-12 form-group">
	                            <label class="control-label col-md-8" data-i18n="createeo_other_eo_part"> <s:message code="createeo_other_eo_part"/></label>
	                            <div class="col-md-4">
	                                [INPUT]
	                            </div>
	                       </div>
	                       <div class="col-md-12 form-group">
	                            <label class="control-label col-md-8" data-i18n="createeo_name_part_group"> <s:message code="createeo_name_part_group"/></label>
	                            <div class="col-md-4">
	                                [INPUT]
	                            </div>
	                       </div>

                       <div class="col-md-12 form-group">
                            <label class="control-label col-md-6" data-i18n="createeo_lots_concerned"><s:message code="createeo_lots_concerned"/></label>
                            <div class="col-md-6">
                                <form:input cssClass="form-control" path="lotConcerned" id="lotConcerned" placeholder=""/>
                            </div>
                       </div>
                       
	                </div>
	            </div>
	        </div>
	        
	        <div class="espd-panel panel panel-default">
	            <div class="espd-panel-heading" data-toggle="collapse" data-target="#createeo_info_respresent_div">
	                <span data-i18n="createeo_info_respresent"><s:message code="createeo_info_respresent"/></span>
	            </div>
	            <div id="createeo_info_respresent_div" class="collapse in">
	                <div class="panel-body">
                       <div class="col-md-12 alert alert-espd-info" style="border: 1px dotted blue; background-color: #D8D8D8;">
                            <span data-i18n="createeo_person_empowered"><s:message code="createeo_person_empowered"/></span>
                       </div>
                       <div class="col-md-12 form-group">
                           <label class="control-label col-md-4" data-i18n="createeo_full_name_and_birth"><s:message code="createeo_full_name_and_birth"/></label>
                           <div class="col-md-8">
                                [INPUT]
                           </div>
                       </div>
                       <div class="col-md-12 form-group">
                           <label class="control-label col-md-4" data-i18n="createeo_pos_act_in_capacity"><s:message code="createeo_pos_act_in_capacity"/></label>
                           <div class="col-md-8">
                                 [INPUT]
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
                       </div>
                        

                       <div class="col-md-12 form-group">
                            <label class="control-label col-md-3" data-i18n="createeo_detinfo_of_represent"><s:message code="createeo_detinfo_of_represent"/></label>
                            <div class="col-md-9">
                               [TEXTAREA]
                            </div>
                       </div>
	                </div>
	            </div>
	        </div>
	        
	        <div class="espd-panel panel panel-default">
	            <div class="espd-panel-heading" data-toggle="collapse" data-target="#createeo_info_reliance_div">
	                <span data-i18n="createeo_info_reliance"><s:message code="createeo_info_reliance"/></span>
	            </div>
	            <div id="createeo_info_reliance_div" class="collapse in">
	                <div class="panel-body">
                       <div class="col-md-12 form-group">
                            <label class="control-label col-md-6" data-i18n="createeo_eo_rely_other_entities">
                            	<s:message code="createeo_eo_rely_other_entities"/>
                            </label>
                            <div class="col-md-6">
                                [YES/NO]
                            </div>
                       </div>
                       <div class="col-md-12 alert alert-espd-info" style="border: 1px dotted blue; background-color: #D8D8D8;margin-top: 15px;">
                            <span data-i18n="createeo_separate_espd_sections_a_b"><s:message code="createeo_separate_espd_sections_a_b"/></span>
                       </div>
	                </div>
	            </div>
	        </div>
        </c:if>
       
        <div class="paragraph">
            <h2>
                <span data-i18n="createca_header"><s:message code="createca_header"/></span>
            </h2>
        </div>
        
        <div class="espd-panel panel panel-default">
            <div class="espd-panel-heading ${isAgentEO?'collapsed':''}" data-toggle="collapse" data-target="#cadiv">
                <span data-i18n="createca_contact_details_ca"><s:message code="createca_contact_details_ca"/></span>
            </div>
            <div id="cadiv" class="collapse in">
                <div class="panel-body">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="control-label col-md-4 " for="authorityName"><span data-i18n="createca_name"><s:message code="createca_name"/></span></label>
                                <div class="col-md-8">
                                    <form:input cssClass="form-control" id="authorityName" path="authority.name" placeholder="Enter name" required="true"/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-8 col-md-offset-4">
                                    <form:errors path="authority.name" cssClass="alert-danger"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-4 " for="natRegNumber"><span data-i18n="createca_nat_regnum"><s:message
                                        code="createca_nat_regnum"/></span></label>

                                <div class="col-md-8">
                                    <form:input cssClass="form-control" path="authority.nationalRegistrationNumber" id="natRegNumber"
                                                placeholder="Enter registration number"/>
                                </div>
                            </div>
                            
                            <tiles:insertDefinition name="partyInfo">
                                 <tiles:putAttribute name="field" value="authority"/>
                                 <tiles:putAttribute name="address" value="true"/>
                            </tiles:insertDefinition>
                            
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="control-label col-md-4" data-i18n="createca_contact_person"><s:message code="createca_contact_person"/></label>
                                <div class="col-md-8">
                                    <form:input cssClass="form-control" path="authority.contactName" id="contactName" placeholder="Enter contact person"/>
                                </div>
                            </div>
                            
                            <tiles:insertDefinition name="partyInfo">
                                 <tiles:putAttribute name="field" value="authority"/>
                                 <tiles:putAttribute name="contacts" value="true"/>
                            </tiles:insertDefinition>
                            
                            <div class="form-group">
                                <label class="control-label col-md-4" data-i18n="createca_website"><s:message code="createca_website"/></label>
                                <div class="col-md-8">
                                    <form:input cssClass="form-control" path="authority.website" id="website" placeholder="Enter Website"/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="espd-panel panel panel-default">
            <div class="espd-panel-heading" data-toggle="collapse" data-target="#ppdiv">
                <span data-i18n="createca_info_procurement_proc"><s:message code="createca_info_procurement_proc"/></span>
            </div>
            <div id="ppdiv" class="collapse in">
                <div class="panel-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="alert alert-espd-info"
                                 style="border: 1px dotted blue; background-color: #D8D8D8;">
                                <span data-i18n="createca_to_be_filled_alert"><s:message code="createca_to_be_filled_alert"/></span>
                            </div>

                            <div class="alert alert-espd-info"
                                 style="border: 1px dotted blue; background-color: #D8D8D8;">
                                <span data-i18n="createca_to_be_filled_subalert"><s:message code="createca_to_be_filled_subalert"/></span>
                            </div>

                            <div class="col-md-12 form-group">
                                <label class="control-label col-md-4" for="procurerName" data-i18n="createca_procurer_name"><s:message code="createca_procurer_name"/></label>
                                <div class="col-md-8">
                                    <form:input cssClass="form-control" path="procurerName" placeholder="Name"/>
                                </div>
                            </div>

                            <div class="col-md-12 form-group">
                                <label class="control-label col-md-4" for="procedureDesc"><span data-i18n="createca_title_or_short_desc_"><s:message code="createca_title_or_short_desc_"/></span></label>
                                 <div class="col-md-8">
                                	<form:textarea path="procedureDesc" id="procedureDesc" cssStyle="resize: none" rows="4" cols="20"  cssClass="form-control"/>
                               	</div>
                            </div>
                            
                            <div class="col-md-12 form-group">
                                <label class="control-label col-md-4 " for="fileRefByCA"><span
                                        data-i18n="createca_file_ref_ca"><s:message code="createca_file_ref_ca"/></span></label>

                                <div class="col-md-8">
                                    <form:input cssClass="form-control" path="fileRefByCA" id="fileRefByCA" placeholder=""/>
                                </div>
                            </div>
 
                            <%--
                            <div class="col-md-12 form-group">
                                <label class="control-label col-md-8 " for="websiteProcDocs"><span
                                        data-i18n="createca_website_proc_doc"><s:message
                                        code="createca_website_proc_doc"/></span></label>

                                <div class="col-md-4">
                                    <form:input cssClass="form-control" path="websiteProcDocs" id="websiteProcDocs" placeholder=""/>
                                </div>
                            </div>
                            --%>
                        </div>
                    </div>
                </div>
            </div>
        </div>


    </div>

    <tiles:insertDefinition name="footerButtons">
    </tiles:insertDefinition>

</form:form>
