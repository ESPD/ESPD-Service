<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>


<tiles:importAttribute name="start"/>
<tiles:importAttribute name="procedure"/>
<tiles:importAttribute name="exclusion"/>
<tiles:importAttribute name="selection"/>
<tiles:importAttribute name="finish"/>

<div class="panel panel-default no-border hidden-print">
    <ul class="nav nav-pills nav-wizard nav-justified">
        <li class="${(start||procedure||exclusion||selection||finish)?'active':''}">
            <a href="#"><i class="fa fa-random"></i>&nbsp;${span18n['progress_start']}</a>
            <div class="nav-arrow"></div>
        </li>
        <li class="${(procedure||exclusion||selection||finish)?'active':''}">
            <div class="nav-wedge"></div>
            <a href="#"><i class="fa fa-university"></i>&nbsp;${span18n['progress_procedure']}</a>
            <div class="nav-arrow"></div>
        </li>
        <li class="${(exclusion||selection||finish)?'active':''}">
            <div class="nav-wedge"></div>
            <a href="#"><i class="fa fa-exclamation"></i>&nbsp;${span18n['progress_exclusion']}</a>
            <div class="nav-arrow"></div>
        </li>
        <li class="${(finish||selection)?'active':''}">
            <div class="nav-wedge"></div>
            <a href="#"><i class="fa fa-check-circle"></i>&nbsp;${span18n['progress_selection']}</a>
            <div class="nav-arrow"></div>
        </li>
        <li class="${finish?'active':''}">
            <div class="nav-wedge"></div>
            <a href="#"><i class="fa fa-download"></i>&nbsp;${span18n['progress_finish']} </a>
        </li>
    </ul>
</div>