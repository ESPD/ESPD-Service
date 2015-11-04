<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:importAttribute name="field"/>

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