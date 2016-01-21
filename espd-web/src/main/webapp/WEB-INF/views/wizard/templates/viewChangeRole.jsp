<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<tiles:importAttribute name="page"/>
<tiles:importAttribute name="agent"/>

<div class="row">
    <strong class="col-md-4 col-md-offset-8">
        <c:if test="${agent == 'EO'}">
        	<span data-i18n="role_eo"><s:message code="role_eo"/></span>
        </c:if>
        <c:if test="${agent == 'CA'}">
        	<span data-i18n="role_ca"><s:message code="role_ca"/></span>
        </c:if>
        
        <a href="${pageContext.request.contextPath}/${page}">
	        <c:if test="${agent == 'EO'}">
	        	<span data-i18n="view_ca"><s:message code="view_ca"/></span>
	        </c:if>
	        <c:if test="${agent == 'CA'}">
	        	<span data-i18n="view_eo"><s:message code="view_eo"/></span>
	        </c:if>
        </a>
	</strong>
</div>


