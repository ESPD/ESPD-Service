<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="panel panel-default">
	<div class="panel-body">
		<div class="col-md-5">
			<div class="col-md-12">
				<span style="text-weight: bold" data-i18n="${title_code}"> 
					<s:message code='${title_code}'/>
				</span>
				<span data-i18n="${tooltip_code}" data-toggle="tooltip" title="<s:message code='${tooltip_code}'/>"></span>
			</div>
			<div class="col-md-12">
				<label class="control-label small" for="${field}-field6">Information is available electronically?</label> 
				<input type="checkbox" id="${field}-field6" data-toggle="collapse" data-target="#${field}-electronically" class="radioslide checktoggle form-control" />
			</div>
			<div class="col-md-12" id="${field}-electronically" style="display:none">
				<div class="form-group" style="margin-bottom: 0px">
					<label class="control-label col-md-2 small" for="${field}-field6">Evidence:</label>
					<div class="col-md-5"> 
					<select  class="form-control input-sm" id="${field}-field6">
						<option>Please select evidence</option>
						<option>evidence 2</option> 
						<option>evidence 3</option>
					</select>  
					</div> 
												 	
					 <label class="control-label col-md-1 small" for="${field}-field7">Code:</label>
													
					<div class="col-md-4"> 
							<input type="text" class="form-control input-sm" id="${field}-field7" placeholder="Provide code if applicable"/>
					 </div>
				</div>
			 </div>
		</div> 
		<div class="col-md-7">
			<div class="col-md-12">
				<form:checkbox path="${field}" data-target="${'#'}${field}-footer" class="radioslide checktoggle"/>
			</div>
			<div class="col-md-12" id="${field}-footer" style="display:none; margin-left: 30px">
							<div class="form-group">
							    <label class="control-label col-md-3 small" for="${field}-field1">Date of conviction:</label>
							    <div class="col-md-9"> 
							      <input type="text" name="" class="form-control" id="${field}-field1" placeholder="Enter date">
							    </div>
							</div>
							<div class="form-group">
							    <label class="control-label col-md-3 small" for="${field}-field2">Reason:</label>
							    <div class="col-md-9"> 
							      <textarea class="form-control" id="${field}-field2" placeholder="Enter reason">
							      </textarea>
							    </div>
							</div>
							<div class="form-group">
							    <label class="control-label col-md-3 small" for="${field}-field3">Who has been convicted:</label>
							    <div class="col-md-9"> 
							      <textarea class="form-control" id="${field}-field3" placeholder="Enter Who has been convicted">
							      </textarea>
							    </div>
							</div>
							<div class="form-group">
							    <label class="control-label col-md-3 small" for="${field}-field4">Length of the period of exclusion:</label>
							    <div class="col-md-9"> 
							      <input type="text" class="form-control" id="${field}-field4" placeholder="Enter Length of the period of exclusion"/>
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
								    <label class="control-label col-md-3 small" for="${field}-field6">Please describe them:</label>
									<div class="col-md-9"> 
								    	<textarea class="form-control" id="${field}-field6" placeholder="describe">  </textarea>
								    </div>
								</div>
							</div>
			</div>
		</div>
	</div>
</div>



<%--


					<div class="panel panel-default" style="border: 0;  margin-bottom: 5px;" >
						<div class="panel-body" style="padding: 5px;">
							<div class="form-group" style="margin-bottom: 0px">
								<div class="col-md-10">
									<span data-i18n="${title_code}"> 
										<s:message code='${title_code}'/>
									</span>
									<span data-i18n="${tooltip_code}" data-toggle="tooltip" title="<s:message code='${tooltip_code}'/>"></span>
								</div>
								<div class="col-md-2">
									<form:checkbox path="${field}" data-target="${'#'}${field}-footer" class="radioslide checktoggle"/>
								</div>
							</div>
						</div> 
						
						<div class="panel-footer" id="${field}-footer" style="display:none; margin-left: 30px">
							<div class="form-group">
							    <label class="control-label col-md-3 small" for="${field}-field1">Date of conviction:</label>
							    <div class="col-md-9"> 
							      <input type="text" name="" class="form-control" id="${field}-field1" placeholder="Enter date">
							    </div>
							</div>
							<div class="form-group">
							    <label class="control-label col-md-3 small" for="${field}-field2">Reason:</label>
							    <div class="col-md-9"> 
							      <textarea class="form-control" id="${field}-field2" placeholder="Enter reason">
							      </textarea>
							    </div>
							</div>
							<div class="form-group">
							    <label class="control-label col-md-3 small" for="${field}-field3">Who has been convicted:</label>
							    <div class="col-md-9"> 
							      <textarea class="form-control" id="${field}-field3" placeholder="Enter Who has been convicted">
							      </textarea>
							    </div>
							</div>
							<div class="form-group">
							    <label class="control-label col-md-3 small" for="${field}-field4">Length of the period of exclusion:</label>
							    <div class="col-md-9"> 
							      <input type="text" class="form-control" id="${field}-field4" placeholder="Enter Length of the period of exclusion"/>
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
								    <label class="control-label col-md-3 small" for="${field}-field6">Please describe them:</label>
									<div class="col-md-9"> 
								    	<textarea class="form-control" id="${field}-field6" placeholder="describe">  </textarea>
								    </div>
								</div>
							</div>
						</div>
						
						<div class="panel-footer" id="footer2" style="margin-left: 30px">

						</div>
					</div>
					
					 --%>