<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<div class="small">
	<span id="${field}-ecertis-show" class="ecertis-link" data-uuid="${criterion.uuid}" data-i18n="${more_information}" data-target="${'#'}${field}-ecertis" data-target-hide="${'#'}${field}-ecertis-show" data-target-show="${'#'}${field}-ecertis-hide;${'#'}${field}-ecertis">
		${i18n['more_information']} <i class="fa fa-angle-double-right" aria-hidden="true"></i>
	</span>
	
	<div id="${field}-ecertis" class="alert collapse" style="padding: 0px; margin: 0px;">
		<br>
		<h4 id="loading">${span18n["ecertis_loading"]}</h4>
		<span id="ecertis404">
			<span style="font-weight: bold;">
				<s:message
					code="information_for_country_in_ecertis"
					arguments="${i18n['country_'.concat(agent == 'eo' ? espd.economicOperator.country : espd.authority.country)]}"
					htmlEscape="false"/>
			</span>
			<br>
			${span18n["ecertis_404"]}
		</span>
		<div id="content">
			<span style="font-weight: bold;">
				<s:message
					code="information_for_country_in_ecertis"
					arguments="${i18n['country_'.concat(agent == 'eo' ? espd.economicOperator.country : espd.authority.country)]}"
					htmlEscape="false"/>
				<%--${span18n["ecertis_language"]}: <span id="language"><!-- dynamic Language --></span> --%>
			</span>
			<ol type="I" id="list" style="padding-left: 13px;">
				<li id="template">
					<span id="subname"><!-- dynamic subcriteria name --></span>
					(
						<span id="title"></span> <a id="url" target="_blank"><!-- dynamic URL --></a>
					)
					<p id="description" style="padding: 0px; margin: 0px;"></p>
					<ol id="evidencesFound">
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
					<span style="padding-left:40px" id="evidencesNotFound">${span18n["ecertis_no_evidences"]}</span>
				</li>
			</ol>
		</div>
	</div>
	
	<span id="${field}-ecertis-hide" style="display:none" class="ecertis-link" data-i18n="${less_information}" data-target-show="${'#'}${field}-ecertis-show" data-target-hide="${'#'}${field}-ecertis;${'#'}${field}-ecertis-hide">
		<i class="fa fa-angle-double-left" aria-hidden="true"></i> ${i18n['less_information']}
	</span>
</div>