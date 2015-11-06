<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<div class="col-md-7">&nbsp;</div>
<div class="col-md-5">
    <div class="btn-group">
        <button type="submit" class="btn btn-default btn-lg" name="prev">
            <i class="fa fa-arrow-circle-o-left"></i>
            <span data-i18n="previous"><s:message code="previous"/></span>
        </button>
        <a class="btn btn-default btn-lg" href="${pageContext.request.contextPath}/welcome">
            <i class="fa fa-times-circle"></i>
            <span data-i18n="cancel"><s:message code="cancel"/></span>
        </a>
        <button type="submit" class="btn btn-default btn-lg" name="next">
            <i class="fa fa-arrow-circle-o-right"></i>
            <span data-i18n="next"><s:message code="next"/></span>
        </button>
    </div>
</div>