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

<div class="row" style="padding:50px">
	<div class="col-md-12" style="margin: 10px;">
		<h1 style="margin-top: 0px;">${span18n["contact_header"]}</h1>

		<div style="color:black">
			${span18n["contact_info"]}
		</div>
		
		<br>

		<div style="color:black">
			${span18n["report_appliction_issue"]}
			<a href="mailto:GROW-ESPD@ec.europa.eu">
				GROW-ESPD@ec.europa.eu
			</a>
		</div>
		
		<div style="color:black">
			${span18n["report_security_incident_title"]}
			<a target="_blank" href="http://ec.europa.eu/growth/tools-databases/security-incidents/index_en.htm">
				${span18n["report_security_incident"]}
			</a>
		</div>

		<h4 style="padding-top: 30px">
			<a href="welcome">${span18n["back_to_main"]}</a>
		</h4>
	</div>
</div>