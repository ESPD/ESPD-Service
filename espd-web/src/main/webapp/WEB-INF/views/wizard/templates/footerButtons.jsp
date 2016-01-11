<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<tiles:importAttribute name="lastStep"/>
<tiles:importAttribute name="prevLink"/>
<tiles:importAttribute name="prevUrl"/>

<div class="col-md-7">&nbsp;</div>
<div class="col-md-5">
    <div class="btn-group">
    	<c:if test="${!prevLink}">
	        <button type="submit" class="btn btn-default btn-lg" name="prev">
	            <i class="fa fa-arrow-circle-o-left"></i>
	            <span data-i18n="previous"><s:message code="previous"/></span>
	        </button>
        </c:if>
    	<c:if test="${prevLink}">
	        <a class="btn btn-default btn-lg" href="${pageContext.request.contextPath}/${prevUrl}">
	            <i class="fa fa-arrow-circle-o-left"></i>
	            <span data-i18n="previous"><s:message code="previous"/></span>
	        </a>
    	</c:if>

        <a class="btn btn-default btn-lg" href="${pageContext.request.contextPath}/welcome">
            <i class="fa fa-times-circle"></i>
            <span data-i18n="cancel"><s:message code="cancel"/></span>
        </a>
        <button id="nextBtn" type="submit" class="btn btn-default btn-lg" name="next">
            <i class="fa fa-arrow-circle-o-right"></i>
            <c:choose>
                <c:when test="${lastStep == true}">
                    <span data-i18n="export"><s:message code="export"/></span>
                </c:when>
                <c:otherwise>
                    <span data-i18n="next"><s:message code="next"/></span>
                </c:otherwise>
            </c:choose>
        </button>
    </div>
</div>