<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
 