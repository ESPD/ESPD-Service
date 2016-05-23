<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<%--
  ~
  ~ Copyright 2016 EUROPEAN COMMISSION
  ~
  ~ Licensed under the EUPL, Version 1.1 or – as soon they
  ~ will be approved by the European Commission - subsequent
  ~ versions of the EUPL (the "Licence");
  ~
  ~ You may not use this work except in compliance with the Licence.
  ~
  ~ You may obtain a copy of the Licence at:
  ~
  ~ https://joinup.ec.europa.eu/community/eupl/og_page/eupl
  ~
  ~ Unless required by applicable law or agreed to in
  ~ writing, software distributed under the Licence is
  ~ distributed on an "AS IS" basis,
  ~ WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
  ~ express or implied.
  ~ See the Licence for the specific language governing
  ~ permissions and limitations under the Licence.
  ~
  --%>

<tiles:importAttribute name="field"/>
<tiles:importAttribute name="title_code"/>
<tiles:importAttribute name="description_code"/>
<tiles:importAttribute name="is_always_checked"/>
<tiles:importAttribute name="default_value"/>

<div class="checkbox" style="border: 1px solid lightgray;margin-bottom: 5px;padding-left: 5px;padding-bottom: 5px;">
    <label>
        <!-- Exclusion criteria except 'Purely national grounds' must always be checked -->
        <!-- We cannot make checkboxes disabled otherwise their value will not be submitted so we prevent the changing of their value by always returning false on the click event -->
        <c:if test="${is_always_checked}">
            <form:checkbox path="${field}.exists" checked="checked" onclick="return false"/>
        </c:if>
        <c:if test="${!is_always_checked}">
            <c:choose>
                <c:when test="${default_value}">
                    <form:checkbox path="${field}.exists"/>
                </c:when>
                <c:otherwise>
                    <form:checkbox path="${field}.exists"/>
                </c:otherwise>
            </c:choose>
        </c:if>
		<span style="font-weight: bold;" data-i18n="${title_code}">
			<s:message code='${title_code}'/> 
		</span>
    </label>
    <c:if test="${not empty description_code}">
        <br>
        <span class="small" data-i18n="${description_code}"><s:message code='${description_code}'/></span>
    </c:if>
</div>



