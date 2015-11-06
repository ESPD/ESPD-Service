<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tiles:importAttribute name="currentPage"/>

<div class="row">
    <strong class="col-md-4 col-md-offset-8">
        ${(param['agent'] == "eo") ? "Role: Economic Operator (EO)": "Role: Contracting Authority (CA)"}
        <c:if test="${param['agent'] == 'eo'}">
            <c:set var="otherAgent" value="ca"></c:set>
        </c:if>
        <c:if test="${param['agent'] == 'ca'}">
            <c:set var="otherAgent" value="eo"></c:set>
        </c:if>
        <a href="${pageContext.request.contextPath}/${currentPage}?agent=${otherAgent}">
            ${(param['agent'] == "eo") ? "View: (CA)": "View: (EO)"}
        </a>
    </strong>
</div>
