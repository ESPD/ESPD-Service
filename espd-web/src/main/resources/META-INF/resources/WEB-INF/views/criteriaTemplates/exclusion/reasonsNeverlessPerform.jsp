<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:importAttribute name="field"/>

<div class="form-group">
	<label class="control-label col-md-3 small">
		${span18n["crit_please_describe_them"]}
	</label>
	<div class="col-md-9">
		<form:textarea path="${field}.description" class="form-control"></form:textarea>
	</div>
</div> 

<div class="form-group">
	<label class="control-label col-md-3 small">
			${span18n["crit_reasons_nevertheless_contract"]}
	</label>
    <div class="col-md-9">
        <form:textarea path="${field}.reason" cssClass="form-control"></form:textarea>
    </div>
</div>

