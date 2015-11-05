<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:importAttribute name="field"/>

<div class="form-group">
	<label class="control-label col-md-4 small" for="${field}-field-country">Country or Member State concerned</label>
	<div class="col-md-8">	

					<form:select path="country" cssClass="form-control">
						<form:option value="empty" label="---"/>
						<optgroup label="EU">
							<form:option data-i18n="country_austria" value='austria'><s:message code="country_austria"/></form:option>
							<form:option data-i18n="country_belgium" value='belgium'><s:message code="country_belgium"/></form:option>
							<form:option data-i18n="country_bulgaria" value='bulgaria'><s:message code="country_bulgaria"/></form:option>
							<form:option data-i18n="country_cyprus" value='cyprus'><s:message code="country_cyprus"/></form:option>
							<form:option data-i18n="country_croatia" value='croatia'><s:message code="country_croatia"/></form:option>
							<form:option data-i18n="country_czech_republic" value='czech_republic'><s:message code="country_czech_republic"/></form:option>
							<form:option data-i18n="country_denmark" value='denmark'><s:message code="country_denmark"/></form:option>
							<form:option data-i18n="country_estonia" value='estonia'><s:message code="country_estonia"/></form:option>
							<form:option data-i18n="country_finland" value='finland'><s:message code="country_finland"/></form:option>
							<form:option data-i18n="country_france" value='france'><s:message code="country_france"/></form:option>
							<form:option data-i18n="country_germany" value='germany'><s:message code="country_germany"/></form:option>
							<form:option data-i18n="country_greece" value='greece'><s:message code="country_greece"/></form:option>
							<form:option data-i18n="country_hungary" value='hungary'><s:message code="country_hungary"/></form:option>
							<form:option data-i18n="country_ireland" value='ireland'><s:message code="country_ireland"/></form:option>
							<form:option data-i18n="country_italy" value='italy'><s:message code="country_italy"/></form:option>
							<form:option data-i18n="country_latvia" value='latvia'><s:message code="country_latvia"/></form:option>
							<form:option data-i18n="country_lithuania" value='lithuania'><s:message code="country_lithuania"/></form:option>
							<form:option data-i18n="country_luxembourg" value='luxembourg'><s:message code="country_luxembourg"/></form:option>
							<form:option data-i18n="country_malta" value='malta'><s:message code="country_malta"/></form:option>
							<form:option data-i18n="country_netherlands" value='netherlands'><s:message code="country_netherlands"/></form:option>
							<form:option data-i18n="country_poland" value='poland'><s:message code="country_poland"/></form:option>
							<form:option data-i18n="country_portugal" value='portugal'><s:message code="country_portugal"/></form:option>
							<form:option data-i18n="country_romania" value='romania'><s:message code="country_romania"/></form:option>
							<form:option data-i18n="country_slovakia" value='slovakia'><s:message code="country_slovakia"/></form:option>
							<form:option data-i18n="country_slovenia" value='slovenia'><s:message code="country_slovenia"/></form:option>
							<form:option data-i18n="country_spain" value='spain'><s:message code="country_spain"/></form:option>
							<form:option data-i18n="country_sweden" value='sweden'><s:message code="country_sweden"/></form:option>
							<form:option data-i18n="country_united_kingdom" value='united_kingdom'><s:message code="country_united_kingdom"/></form:option>
						</optgroup>
						<optgroup label="EU+">
							<form:option data-i18n="country_turkey" value='turkey'><s:message code="country_turkey"/></form:option>
						</optgroup>
						<optgroup label="EFTA">
							<form:option data-i18n="country_iceland" value='iceland'><s:message code="country_iceland"/></form:option>
							<form:option data-i18n="country_lichtenstein" value='lichtenstein'><s:message code="country_lichtenstein"/></form:option>
							<form:option data-i18n="country_switzerland" value='switzerland'><s:message code="country_switzerland"/></form:option>
							<form:option data-i18n="country_norway" value='norway'><s:message code="country_norway"/></form:option>
						</optgroup>
					</form:select>
	</div>
</div> 

<div class="form-group">
	<label class="control-label col-md-4 small" for="${field}-field-amount">Amount concerned</label>
	<div class="col-md-5">
		<form:input path="${field}.amount" cssClass="form-control" id="${field}-field-amount" placeholder="Enter amount"/>
	</div>
	<div class="col-md-3">
		<form:select id="${field}-field-country" path="${field}.currency" cssClass="form-control">
			<form:option value='euro'>EUR</form:option>
			<form:option value='dollar'>USD</form:option>
		</form:select>
	</div>
</div>

