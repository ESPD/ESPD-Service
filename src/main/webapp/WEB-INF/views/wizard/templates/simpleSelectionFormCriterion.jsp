<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:importAttribute name="field"/>


<div class="form-group">
  <label class="control-label col-md-4 small" for="${field}-textarea"><s:message code='crit_please_describe_them'/></label>
  <div class="col-md-8">
    <textarea class="form-control" id="${field}-textarea" placeholder=""></textarea>
  </div>
</div>
