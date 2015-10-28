<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="languages" class="java.util.LinkedHashMap" scope="request"/>
<c:set target="${languages}" property="bg" value="български"/>
<c:set target="${languages}" property="cs" value="čeština"/>
<c:set target="${languages}" property="da" value="Dansk"/>
<c:set target="${languages}" property="de" value="Deutsch"/>
<c:set target="${languages}" property="et" value="Eesti keel"/>
<c:set target="${languages}" property="el" value="ελληνικά"/>
<c:set target="${languages}" property="en" value="English"/>
<c:set target="${languages}" property="es" value="Español"/>
<c:set target="${languages}" property="fr" value="Français"/>
<c:set target="${languages}" property="ga" value="Gaeilge"/>
<c:set target="${languages}" property="hr" value="Hrvatski"/>
<c:set target="${languages}" property="it" value="Italiano"/>
<c:set target="${languages}" property="lv" value="Latviešu valoda"/>
<c:set target="${languages}" property="lt" value="Lietuvių kalba"/>
<c:set target="${languages}" property="hu" value="magyar"/>
<c:set target="${languages}" property="mt" value="Malti"/>
<c:set target="${languages}" property="nl" value="Nederlands"/>
<c:set target="${languages}" property="pl" value="Polski"/>
<c:set target="${languages}" property="pt" value="Português"/>
<c:set target="${languages}" property="ro" value="Română"/>
<c:set target="${languages}" property="sk" value="Slovenčina"/>
<c:set target="${languages}" property="sl" value="Slovenščina"/>
<c:set target="${languages}" property="fi" value="Suomi"/>
<c:set target="${languages}" property="sv" value="Svenska"/>

	<div class="row" style="padding:50px">
	
		<fmt:formatNumber var="colLen" value="6"  maxFractionDigits="0" />

		<div class="col-lg-3 col-sm-6">
		<c:forEach var="lang" items="${languages}" varStatus="i">
			${((i.index % colLen) == 0 && !i.first && !i.last) ? "</div><div class='col-lg-3 col-sm-6 langBoxBorder'>" : ""}
			<div class="splashLangDiv">
				<a href="${pageContext.request.contextPath}/filter?lang=${lang.key}" class="splashLangBox" >${lang.key}</a>
				<a href="${pageContext.request.contextPath}/filter?lang=${lang.key}">${lang.value}</a>
			</div>
		</c:forEach>
		</div>
	</div>
