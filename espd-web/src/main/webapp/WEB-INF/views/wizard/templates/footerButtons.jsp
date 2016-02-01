<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<tiles:importAttribute name="prev"/>
<tiles:importAttribute name="next"/>
<tiles:importAttribute name="nextCode"/>
<tiles:importAttribute name="prevCode"/>
<tiles:importAttribute name="print"/>

<c:set var="prevUrl" value="${fn:startsWith(prev,'/')}"/>
<c:set var="nextUrl" value="${fn:startsWith(next,'/')}"/>
<div class="row hidden-print">
    <div class="col-md-3 hidden-print">&nbsp;</div>
    <div class="col-md-6 hidden-print">
        <div class="btn-group btn-group-justified" role="group">
            <c:if test="${!prevUrl}">
                <div class="btn-group" role="group">
                    <button id="prevBtn" type="submit" class="btn btn-default btn-lg" name="prev" value="${prev}">
                        <i class="fa fa-arrow-circle-o-left"></i> <span data-i18n="${prevCode}"><s:message code="${prevCode}"/></span>
                    </button>
                </div>
            </c:if>
            <c:if test="${prevUrl}">
                <div class="btn-group" role="group">
                    <a id="prevAnchor" class="btn btn-default btn-lg" href="${pageContext.request.contextPath}${prev}" role="button">
                        <i class="fa fa-arrow-circle-o-left"></i> <span data-i18n="${prevCode}"><s:message code="${prevCode}"/></span>
                    </a>
                </div>
            </c:if>
            <div class="btn-group" role="group">
                <a class="btn btn-default btn-lg" href="${pageContext.request.contextPath}/welcome" role="button">
                    <i class="fa fa-times-circle"></i>
                    <span data-i18n="cancel"><s:message code="cancel"/></span>
                </a>
            </div>
            <c:if test="${print}">
                <div class="btn-group" role="group">
                    <a id="printBtn" target="_blank" class="btn btn-default btn-lg" href="${pageContext.request.contextPath}/print" role="button">
                        <i class="fa fa-print"></i> <span data-i18n="print"><s:message code="print"/></span>
                    </a>
                </div>
            </c:if>
            <c:if test="${!nextUrl}">
                <div class="btn-group" role="group">
                    <button id="nextBtn" type="submit" class="btn btn-default btn-lg" name="next" value="${next}">
                        <i class="fa fa-arrow-circle-o-right"></i> <span data-i18n="${nextCode}"><s:message code="${nextCode}"/></span>
                    </button>
                </div>
            </c:if>
            <c:if test="${nextUrl}">
                <div class="btn-group" role="group">
                    <a id="nextAnchor" class="btn btn-default btn-lg" href="${pageContext.request.contextPath}${next}" role="button">
                        <i class="fa fa-arrow-circle-o-right"></i> <span data-i18n="${nextCode}"><s:message code="${nextCode}"/></span>
                    </a>
                </div>
            </c:if>
        </div>
    </div>
    <div class="col-md-3 hidden-print">&nbsp;</div>
</div>