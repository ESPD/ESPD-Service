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
