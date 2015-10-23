<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="now" value="<%=new java.util.Date()%>" scope="request"/>
<footer>
	<div class="row">
		<div class="col-lg-12">
			<span data-i18n="FOOTER_LAST_UPDATE"><s:message code='FOOTER_LAST_UPDATE'/></span>
			<span class="datefmt"> <fmt:formatDate value="${now}"/> </span> |
			<span data-i18n="FOOTER_REPORT_SECURITY_INCIDENT"><s:message code='FOOTER_REPORT_SECURITY_INCIDENT'/></span>
		</div>
	</div>
</footer>

</body>
</html> 
