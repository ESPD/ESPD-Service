<%@ page import="eu.europa.ec.grow.espd.domain.enums.other.Language" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="row" style="padding:50px">
    <fmt:formatNumber var="colLen" value="6" maxFractionDigits="0"/>
    <div class="col-lg-3 col-sm-6">
        <c:forEach var="lang" items="<%=Language.values()%>" varStatus="i">
            ${((i.index % colLen) == 0 && !i.first && !i.last) ? "</div><div class='col-lg-3 col-sm-6 langBoxBorder'>" : ""}
            <div class="splashLangDiv">
                <a href="${pageContext.request.contextPath}/filter?lang=${lang.code}" class="splashLangBox">${lang.code}</a>
                <a href="${pageContext.request.contextPath}/filter?lang=${lang.code}">${lang.sourceLanguage}</a>
            </div>
        </c:forEach>
    </div>
</div>
