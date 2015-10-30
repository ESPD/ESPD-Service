<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row" style="border: 1px solid lightgray;margin-right: 20px; margin-left: 20px; margin-bottom: 5px;">
		<div class="col-md-5" style="border-right: 1px solid lightgray; padding-top: 20px;">
				<div class="form-group">
					<div class="col-md-12"> 
						<strong>${number}. </strong>
						<strong data-i18n="${title_code}">  
							<s:message code='${title_code}'/> 
						</strong>
						<c:if test="${tooltip_code != ''}">
							<s:message var="tooltip_text" code='${tooltip_code}'/>
							<span data-i18n="${tooltip_code}" data-toggle="tooltip" title="${tooltip_text}"></span>
						</c:if>
					</div> 
				</div>
		</div> 
		<div class="col-md-7" style="border-left: 1px solid lightgray; padding:20px; left: -1px;  padding-bottom: 0px;">
		
			<div class="col-md-12"> 
				<div class="form-group">
					 
					<label class="control-label small" style="padding-top: 0px;" for="${field}-field6">
						Your answer?
					</label>
					
					<form:checkbox path="${field}.exists" id="${field}-field6" data-toggle="collapse" data-target="${'#'}${field}-form" class="radioslide checktoggle form-control" />
				</div>
			</div>
			
			<div class="col-md-12" id="${field}-form" style="display:none"> 
				<div class="form-group">
							<div class="form-group">
							    <label class="control-label col-md-4 small" for="${field}-field1">Date of conviction</label>
							    <div class="col-md-8"> 
							      <form:input path="${field}.dateOfConviction" cssClass="form-control datepicker" id="${field}-field1" placeholder="Enter date"/>
							    </div>
							</div> 
							<div class="form-group"> 
							    <label class="control-label col-md-4 small" for="${field}-field2">Reason</label>
							    <div class="col-md-8"> 
							      <form:textarea path="${field}.reason" cssClass="form-control" id="${field}-field2" placeholder="Enter reason"/>
							    </div>
							</div>
							<div class="form-group">
							    <label class="control-label col-md-4 small" for="${field}-field3">Who has been convicted</label>
							    <div class="col-md-8"> 
							       <form:textarea path="${field}.convicted" cssClass="form-control" id="${field}-field3" placeholder="Enter Who has been convicted"/>
							      
							    </div>
							</div>
							<div class="form-group">
							    <label class="control-label col-md-4 small" for="${field}-field4">Length of the period of exclusion</label>
							    <div class="col-md-8"> 
							      <form:input path="${field}.periodLength" class="form-control" id="${field}-field4" placeholder="Enter Length of the period of exclusion"/>
							    </div>
							</div>
							
							<div class="form-group">
							    <div class="col-md-12">
							     	<label class="control-label small" for="${field}-field5">Have you taken measures to demonstrate your reliability ("Self-Cleaning")</label> 
							     	<input type="checkbox" id="${field}-field5" data-toggle="collapse" data-target="${'#'}${field}-reliability" class="radioslide checktoggle" />
							    </div>
							</div>
							<div class="tab-pane" id="${field}-reliability" style="display:none">
								<div class="form-group">
								    <label class="control-label col-md-4 small" for="${field}-field6">Please describe them</label>
									<div class="col-md-8"> 
								    	<textarea class="form-control" id="${field}-field6" placeholder="describe">  </textarea>
								    </div>
								</div>
							</div> 
				</div>
			</div>
			
			<div class="col-md-12"> 
				<div class="form-group">
					<label class="control-label small" for="${field}-field6">Is this information available electronically?</label> 
					<input type="checkbox" id="${field}-field6" data-toggle="collapse" data-target="#${field}-electronically" class="radioslide checktoggle form-control" />
				</div>
			</div>
			<div class="col-md-12" id="${field}-electronically" style="display:none">
				<div class="form-group" style="">
					<label class="control-label col-md-2 small" for="${field}-field6">Url</label>
					<div class="col-md-5"> 
						<input type="text"  class="form-control input-sm" id="${field}-field6"/> 
					</div> 
											 	
					 <label class="control-label col-md-1 small" for="${field}-field7">Code</label>
													
					<div class="col-md-4"> 
							<input type="text" class="form-control input-sm" id="${field}-field7" placeholder="Provide code if applicable"/>
					 </div>
				</div>
			 </div>
		</div>

</div>

