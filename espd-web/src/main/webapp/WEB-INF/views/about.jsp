<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="breadCrumb" scope="request" value="<li>Version</li>"/>

<div class="row-fluid">
    <div class="span9">
        <div class="panel alert-info">
            <h3 class="panel-title text-center">Version information</h3>
            <div class="panel-content">
                <ul class="unstyled">
                    <li>Version: <c:out value="${lastBuildDate}"/></li>
                    <li>Last built on: <c:out value="${buildVersion}"/></li>
                    <li>Environment: <c:out value="${environment}"/></li>
                </ul>
            </div>
        </div>
    </div>
</div>
 