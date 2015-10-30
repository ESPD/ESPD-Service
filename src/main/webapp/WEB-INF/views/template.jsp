<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<html>
    <head>
        <tiles:insertAttribute name="common" />
    </head>
    <body>
    	<div class="container panel" style="padding:0; border-color: #396ea2 !important;">
	        <div id="header">
	            <tiles:insertAttribute name="header"/>
	            <tiles:insertAttribute name="breadcrumb"/>
	        </div>
	        <div id="body" class="container espd-container">
	            <tiles:insertAttribute name="body"/>
	        </div>
		</div>
        <div id="footer">
            <tiles:insertAttribute name="footer"/>
        </div>
    </body>
</html>