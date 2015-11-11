<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row-fluid">
    <div class="span9">
        <div>
            <c:set var="statusCode" scope="request" value="${requestScope['javax.servlet.error.status_code']}"/>
            <c:set var="errorCode" scope="request" value=""/>
            <c:set var="errorMessage" scope="request" value=""/>
            <c:choose>
                <c:when test="${statusCode == 400}">
                    <c:set var="errorCode" scope="request" value="Bad Request"/>
                    <c:set var="errorMessage" scope="request" value="The request cannot be fulfilled due to bad syntax."/>
                </c:when>
                <c:when test="${statusCode == 401}">
                    <c:set var="errorCode" scope="request" value="Unauthorized"/>
                    <c:set var="errorMessage" scope="request" value="The request was a legal request, but the server is refusing to respond to it."/>
                </c:when>
                <c:when test="${statusCode == 403}">
                    <c:set var="errorCode" scope="request" value="Forbidden"/>
                    <c:set var="errorMessage" scope="request" value="You are not allowed to access this page."/>
                </c:when>
                <c:when test="${statusCode == 404}">
                    <c:set var="errorCode" scope="request" value="Not Found"/>
                    <c:set var="errorMessage" scope="request" value="We could not find the page you were looking for."/>
                </c:when>
                <c:when test="${statusCode == 415}">
                    <c:set var="errorCode" scope="request" value="Unsupported Media Type"/>
                    <c:set var="errorMessage" scope="request" value="The server will not accept the request, because the media type is not supported."/>
                </c:when>
                <c:when test="${statusCode == 500}">
                    <c:set var="errorCode" scope="request" value="Internal Server Error"/>
                    <c:set var="errorMessage" scope="request" value="The server encountered an unexpected error which prevented it from fulfilling the request."/>
                </c:when>
                <c:when test="${statusCode == 503}">
                    <c:set var="errorCode" scope="request" value="Service Unavailable"/>
                    <c:set var="errorMessage" scope="request" value="The server is currently unavailable (overloaded or down)."/>
                </c:when>
                <c:otherwise>
                    <c:set var="errorMessage" scope="request" value="An unexpected error occurred on the server."/>
                </c:otherwise>
            </c:choose>
            <h2 class="text-center alert alert-danger"><c:out value="${errorCode}  (${statusCode})"/></h2>
            <h5 class="alert alert-info">
                <c:out value="${errorMessage}"/>
            </h5>
        </div>
    </div>
</div>
