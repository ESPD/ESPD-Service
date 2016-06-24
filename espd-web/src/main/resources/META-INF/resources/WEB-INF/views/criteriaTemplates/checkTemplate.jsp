<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

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

<tiles:importAttribute name="field"/>
<tiles:importAttribute name="title_code"/>
<tiles:importAttribute name="description_code"/>
<tiles:importAttribute name="is_always_checked"/>
<tiles:importAttribute name="default_value"/>
<tiles:importAttribute name="hasCriterion"/>

<c:if test="${hasCriterion}">
	<tiles:importAttribute name="criterion"/>
</c:if>

<div class="checkbox" style="border: 1px solid lightgray;margin-bottom: 5px;padding-left: 5px;padding-bottom: 5px;">
    <label for="empty-${field}"><%-- empty-... "for" prevents checkbox value switch after click on label showing ecertis values --%>
        <!-- Exclusion criteria except 'Purely national grounds' must always be checked -->
        <!-- We cannot make checkboxes disabled otherwise their value will not be submitted so we prevent the changing of their value by always returning false on the click event -->
        <c:if test="${is_always_checked}">
            <form:checkbox path="${field}.exists" checked="checked" onclick="return false"/>
        </c:if>
        <c:if test="${!is_always_checked}">
            <c:choose>
                <c:when test="${default_value}">
                    <form:checkbox path="${field}.exists"/>
                </c:when>
                <c:otherwise>
                    <form:checkbox path="${field}.exists"/>
                </c:otherwise>
            </c:choose>
        </c:if>
		<c:if test="${hasCriterion == true}">
			<span style="font-weight: bold;" class="ecertis-link-header collapsed" data-uuid="${criterion.uuid}" data-i18n="${title_code}" data-toggle="collapse" data-target="${'#'}${field}-ecertis">
				<s:message code='${title_code}'/> 
			</span>

			<div id="${field}-ecertis" class="collapse">
				<h4 id="loading">${span18n["ecertis_loading"]}</h4>
				<h4 id="ecertis404">${span18n["ecertis_404"]}</h4>
				<div id="content">
					<h4>${span18n["ecertis_language"]}: <span id="language"><!-- dynamic Language --></span></h4>
					<h4>${span18n["ecertis_legislation"]}</h4>
					<ul>
						<li>${span18n["crit_description"]}: <span id="description"><!-- dynamic Description --></span></li>
						<li>${span18n["ecertis_article"]}: <span id="article"><!-- dynamic Article --></span></li>
						<li>${span18n["crit_url"]}: <a id="url" target="_blank"><!-- dynamic URL --></a></li>
					</ul>
					<h4 id="evidencesNotFound">
						${span18n["ecertis_no_evidences"]}
					</h4>
					<div id="evidencesFound">
						<h4>${span18n["ecertis_evidences"]}</h4>
						<ul id="list">
							<li id="template">
								<span id="subname"><!-- dynamic subcriteria name --></span>
								<ol id="sublist">
									<li id="evidence">
										<dl>
											<dt>
												<a id="name" target="_blank"><!-- dynamic evidence link --></a>
											</dt>
											<dd id="issued">
												${span18n["ecertis_issued"]}: <span id="issuerNames"><!-- dynamic issuer names --></span>
											</dd>
										</dl>
									</li>
								</ol>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</c:if>
		<c:if test="${hasCriterion != true}">
			<span style="font-weight: bold;" data-i18n="${title_code}">
				<s:message code='${title_code}'/>
			</span>
		</c:if>
	</label>
    <c:if test="${not empty description_code}">
        <br>
        <span class="small" data-i18n="${description_code}"><s:message code='${description_code}'/></span>
    </c:if>
</div>



