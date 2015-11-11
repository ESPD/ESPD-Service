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
						
						
						<br>
						<br>
						TODO:  subtext
					</div> 
				</div>
		</div> 
		<div class="col-md-7" style="border-left: 1px solid lightgray; padding:20px; left: -1px;  padding-bottom: 0px;">
			TODO: for prof. misconduct
		</div>

</div>

