<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

     <footer>
        <div class="row">
            <div class="col-lg-12">
              Last Update:  <span class="datefmt"> <fmt:formatDate value="<%= new java.util.Date() %>"/> </span> | Report an Information Security Incident
             </div>
        </div>
     </footer>

</body>
</html> 