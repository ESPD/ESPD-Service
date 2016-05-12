<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

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

<tiles:importAttribute name="page"/>
<tiles:importAttribute name="agent"/>
<tiles:importAttribute name="showLink"/>

<div class="row">
    <strong class="col-md-4 col-md-offset-8">
        <c:if test="${agent == 'eo'}">
        	<span data-i18n="role_eo"><s:message code="role_eo"/></span>
        </c:if>
        <c:if test="${agent == 'ca'}">
        	<span data-i18n="role_ca"><s:message code="role_ca"/></span>
        </c:if>
        
        <c:if test="${showLink != 'false'}">
	        <a href="${pageContext.request.contextPath}/${page}">
		        <c:if test="${agent == 'eo'}">
		        	<span data-i18n="view_ca"><s:message code="view_ca"/></span>
		        </c:if>
		        <c:if test="${agent == 'ca'}">
		        	<span data-i18n="view_eo"><s:message code="view_eo"/></span>
		        </c:if>
	        </a>
        </c:if>
	</strong>
</div>


