<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row-fluid">
    <div class="span9">
        <div>
            <!-- The model values come from org.springframework.boot.autoconfigure.web.BasicErrorController -->
            <h2 class="text-center alert alert-danger"><c:out value="${error}  (${status})"/></h2>
            <h5 class="alert alert-info">
                <c:out value="${message}"/>
            </h5>
            <c:if test="${IncludeStacktrace.ALWAYS eq errorProperties.includeStacktrace}">
                <c:out value="${trace}"/>
            </c:if>
        </div>
    </div>
</div>
