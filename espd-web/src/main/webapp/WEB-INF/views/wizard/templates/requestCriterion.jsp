<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:importAttribute name="field"/>
<tiles:importAttribute name="title_code"/>
<tiles:importAttribute name="tooltip_code"/>
<tiles:importAttribute name="description_code"/>
<tiles:importAttribute name="is_always_checked"/>

<div class="checkbox" style="border: 1px solid lightgray;margin-bottom: 5px;padding-left: 5px;padding-bottom: 5px;">
    <label>
        <!-- Exclusion criteria except 'Purely national grounds' must always be checked -->
        <!-- We cannot make checkboxes disabled otherwise their value will not be submitted so we prevent the changing of their value by always returning false on the click event -->
        <c:if test="${is_always_checked}">
            <form:checkbox path="${field}.exists" checked="checked" onclick="return false"/>
        </c:if>
        <c:if test="${!is_always_checked}">
            <form:checkbox path="${field}.exists"/>
        </c:if>
		
		<span style="font-weight: bold;" data-i18n="${title_code}">  
			<s:message code='${title_code}'/> 
		</span>

        <c:if test="${not empty tooltip_code}">
            <s:message var="tooltip_text" code='${tooltip_code}'/>
            <span data-i18n="${tooltip_code}" data-toggle="tooltip" title="${tooltip_text}"></span>
        </c:if>
    </label>
    <c:if test="${not empty description_code}">
        <br>
        <span class="small" data-i18n="${description_code}"><s:message code='${description_code}'/></span>
    </c:if>
</div>



