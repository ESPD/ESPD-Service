<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<tiles:importAttribute name="field"/>
<c:forEach begin="1" end="5" varStatus="loop">
    <div class="form-group">
        <div class="col-xs-3">
            <label class="control-label col-md-3 small">${span18n['crit_ratio']}</label>
        </div>
        <div class="col-xs-9">
            <form:input type="text" path="${field}.unboundedGroups[${loop.index - 1}]['ratio']" number="true"
                        cssClass="form-control"/>
        </div>
    </div>
    <div class="form-group">
        <div class="col-xs-3">
            <label class="control-label col-md-3 small">${span18n['crit_description']}</label>
        </div>
        <div class="col-xs-9">
            <form:textarea rows="1" path="${field}.unboundedGroups[${loop.index - 1}]['description']"
                           cssClass="form-control"/>
        </div>
    </div>
    <hr/>
</c:forEach>
