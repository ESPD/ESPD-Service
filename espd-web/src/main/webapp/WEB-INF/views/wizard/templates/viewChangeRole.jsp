<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tiles:importAttribute name="page"/>
<tiles:importAttribute name="agent"/>

<div class="row">
    <strong class="col-md-4 col-md-offset-8">
        <c:if test="${agent == 'EO'}">
        	Role: Economic Operator (EO)
        </c:if>
        <c:if test="${agent == 'CA'}">
        	Role: Contracting Authority (CA)
        </c:if>
        
        <a href="${pageContext.request.contextPath}/${page}">
	        <c:if test="${agent == 'EO'}">
	        	View: (CA)
	        </c:if>
	        <c:if test="${agent == 'CA'}">
	        	View: (EO)
	        </c:if>
        </a>
	</strong>
</div>


