<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:importAttribute name="field"/>


<div class="form-group">
    <label data-i18n="crit_information_available_electronically" class="control-label small"
           for="${field}-field-available-electronically-exist">
        <s:message code='crit_information_available_electronically'/>
    </label>
    <form:checkbox path="${field}.availableElectronically.exists" id="${field}-field-available-electronically-exists"
                   data-toggle="collapse" data-target="${'#'}${field}-electronically"
                   class="radioslide checktoggle form-control"/>
</div>
<div id="${field}-electronically" class="form-group" style="display:none">
    <label class="control-label col-md-2 small" for="${field}-field6" data-i18n="crit_url"><s:message
            code="crit_url"/></label>
    <div class="col-md-5">
        <s:message code="crit_url_placeholder" var="urlPlaceholder"/>
        <input type="text" class="form-control input-sm" id="${field}-field6" placeholder="${urlPlaceholder}"/>
    </div>
    <label class="control-label col-md-1 small" for="${field}-field7" data-i18n="crit_code"><s:message
            code="crit_code"/></label>
    <div class="col-md-4">
        <s:message code="crit_code_placeholder" var="codePlaceholder"/>
        <input type="text" class="form-control input-sm" id="${field}-field7" placeholder="${codePlaceholder}"/>
    </div>
</div>
