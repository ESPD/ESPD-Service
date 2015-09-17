<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container espd-container">
	<div class="row" style="padding:50px">
	
		<fmt:formatNumber var="colLen" value="${fn:length(locales.codes) / 4}"  maxFractionDigits="0" />

		<div class="col-lg-3 col-sm-6">
		<c:forEach var="code" items="${locales.codes}" varStatus="i">
			${((i.index % colLen) == 0 && !i.first && !i.last) ? "</div><div class='col-lg-3 col-sm-6 langBoxBorder'>" : ""}
			<div class="splashLangDiv">
				<a class="splashLangBox" href="#">${code}</a>
				<a href="/espd/filter?lang=${code}&languageCode=${code}">${locales.nameByCode[code]}</a>
			</div>
		</c:forEach>
		</div>
	</div>
</div>