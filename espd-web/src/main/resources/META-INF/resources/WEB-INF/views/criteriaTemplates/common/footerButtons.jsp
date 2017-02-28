<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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

<tiles:importAttribute name="prev"/>
<tiles:importAttribute name="next"/>
<tiles:importAttribute name="nextCode"/>
<tiles:importAttribute name="prevCode"/>
<tiles:importAttribute name="overview"/>

<c:set var="prevUrl" value="${fn:startsWith(prev,'/')}"/>
<c:set var="nextUrl" value="${fn:startsWith(next,'/')}"/>
<div class="hidden-print">
    <div class="${next == 'download' ? 'col-md-12' : 'col-md-offset-3 col-md-6'} hidden-print">
        <div class="btn-group btn-group-justified" role="group">
            <c:if test="${!prevUrl}">
                <div class="btn-group" role="group">
                    <button id="prevBtn" type="submit" class="btn btn-default btn-lg" name="prev" value="${prev}">
                        <i class="fa fa-arrow-circle-o-left"></i> <span data-i18n="${prevCode}"><s:message
                            code="${prevCode}"/></span>
                    </button>
                </div>
            </c:if>
            <c:if test="${prevUrl}">
                <div class="btn-group" role="group">
                    <a id="prevAnchor" class="btn btn-default btn-lg" href="${pageContext.request.contextPath}${prev}"
                       role="button">
                        <i class="fa fa-arrow-circle-o-left"></i> <span data-i18n="${prevCode}"><s:message
                            code="${prevCode}"/></span>
                    </a>
                </div>
            </c:if>
            <div class="btn-group" role="group">
                <a id="cancelBtn" class="btn btn-default btn-lg" href="${pageContext.request.contextPath}/welcome"
                   role="button">
                    <i class="fa fa-times-circle"></i>
                    <span data-i18n="cancel"><s:message code="cancel"/></span>
                </a>
            </div>
            <c:if test="${overview}">
                <div class="btn-group" role="group">
                    <button id="overviewBtn" type="submit" class="btn btn-default btn-lg" name="overview" value="true">
                        <i class="fa fa-print"></i><span data-i18n="print"><s:message code="print"/></span>
                    </button>
                </div>
            </c:if>
            <c:if test="${next == 'download'}">
                <div class="btn-group dropup" role="group" aria-label="Download">
                    <button type="submit" class="btn btn-default btn-lg dropdown-toggle" data-toggle="dropdown"
                            aria-haspopup="true" aria-expanded="false" name="download" value="xml">
                            <span data-i18n="download_as"><s:message code="download_as" text="Download as"/></span>
                                    
                        <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu">
                        <li>
                            <button id="downloadXmlButton" type="submit" class="btn btn-default" name="download" value="xml">
                                <i class="fa fa-file-pdf-o"></i><span data-i18n="download_pdf"><s:message
                                    code="download_xml" text="XML"/></span>
                            </button>
                        </li>
                        <li>
                            <button id="downloadPdfButton" type="submit" class="btn btn-default" name="download" value="pdf">
                                <i class="fa fa-file-pdf-o"></i><span data-i18n="download_pdf"><s:message
                                    code="download_pdf" text="PDF"/></span>
                            </button>
                        </li>
                        <li>
                            <button id="downloadZipButton" type="submit" class="btn btn-default" name="download" value="zip">
                                <i class="fa fa-file-zip-o"></i><span data-i18n="download_zip"><s:message
                                    code="download_zip" text="ZIP"/></span>
                            </button>
                        </li>
                    </ul>
                </div>
                <%--<div class="btn-group" role="group">--%>
                <%--<button id="xml" type="submit" class="btn btn-default btn-lg" name="download" value="xml">--%>
                <%--<i class="fa fa-file-code-o"></i><span data-i18n="download_xml"><s:message code="download_xml" text="MXL"/></span>--%>
                <%--</button>--%>
                <%--</div>--%>
                <%--<div class="btn-group" role="group">--%>
                <%--<button id="pdf" type="submit" class="btn btn-default btn-lg" name="download" value="pdf">--%>
                <%--<i class="fa fa-file-pdf-o"></i><span data-i18n="download_pdf"><s:message code="download_pdf" text="PDF"/></span>--%>
                <%--</button>--%>
                <%--</div>--%>
                <%--<div class="btn-group" role="group">--%>
                <%--<button id="zip" type="submit" class="btn btn-default btn-lg" name="download" value="zip">--%>
                <%--<i class="fa fa-file-zip-o"></i><span data-i18n="download_zip"><s:message code="download_zip" text="ZIP"/></span>--%>
                <%--</button>--%>
                <%--</div>--%>
            </c:if>
            <c:if test="${!nextUrl && next != 'overview' && next != 'download'}">
                <div class="btn-group" role="group">
                    <button id="nextBtn" type="submit" class="btn btn-default btn-lg" name="next" value="${next}">
                        <i class="fa fa-arrow-circle-o-right"></i> <span data-i18n="${nextCode}"><s:message
                            code="${nextCode}"/></span>
                    </button>
                </div>
            </c:if>
            <c:if test="${nextUrl}">
                <div class="btn-group" role="group">
                    <a id="nextAnchor" class="btn btn-default btn-lg" href="${pageContext.request.contextPath}${next}"
                       role="button">
                        <i class="fa fa-arrow-circle-o-right"></i> <span data-i18n="${nextCode}"><s:message
                            code="${nextCode}"/></span>
                    </a>
                </div>
            </c:if>
        </div>
    </div>
    <div class="col-md-3 hidden-print">&nbsp;</div>
</div>