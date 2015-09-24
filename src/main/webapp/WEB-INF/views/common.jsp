<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="org.springframework.context.i18n.LocaleContextHolder"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>

  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=9;">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="description" content="">
  <meta name="author" content="">
  
  <title>Espd</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery/jquery.min.js"></script>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/font-awesome-4.2.0/css/font-awesome.min.css"/>

	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap-3.2.0/css/bootstrap-theme.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap-3.2.0/css/bootstrap.min.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}//static/bootstrap-3.2.0/js/bootstrap.min.js"></script>

	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap-plugins/select2/css/select2.ajax.min.css"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}//static/bootstrap-plugins/select2/js/select2.full.ajax.min.js"></script>
	
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap-plugins/x-editable/bootstrap3-editable/css/bootstrap-editable.css"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/bootstrap-plugins/x-editable/bootstrap3-editable/js/bootstrap-editable.js"></script>

	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap-plugins/select2/css/select2.ajax.min.css"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/bootstrap-plugins/select2/js/select2.full.ajax.min.js"></script>

	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap-plugins/fileupload/css/fileinput.min.css"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/bootstrap-plugins/fileupload/js/fileinput.url.min.js"></script>

	<script type="text/javascript" src="${pageContext.request.contextPath}/static/bootstrap-plugins/confirmation/bootstrap-confirmation.min.js"></script>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-plugins/validate/jquery.validate.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-plugins/validate/additional-methods.min.js"></script>
	
	<link href="${pageContext.request.contextPath}/static/css/nav-wizard.bootstrap.css" rel="stylesheet">

	<link href="${pageContext.request.contextPath}/static/css/ec.css" rel="stylesheet">  
	<link href="${pageContext.request.contextPath}/static/css/espd.css" rel="stylesheet">  
	<link href="${pageContext.request.contextPath}/static/css/simple-sidebar.css" rel="stylesheet">  
	<link href="${pageContext.request.contextPath}/static/css/scrolling-nav.css" rel="stylesheet">

	<script>
		<%-- Pseudo console for f**g IE9, otherwise it makes undefined error --%>
		window.console = window.console || (function(){
		    var c = {}; c.log = c.warn = c.debug = c.info = c.error = c.time = c.dir = c.profile = c.clear = c.exception = c.trace = c.assert = function(){};
		    return c;
		})();

		$(function() {
			$(".datepicker").datepicker({ format: "dd-mm-yyyy", clearBtn: true, todayHighlight: true });
		    $(".selectfilter").select2();
		    validatorSetup("[" + "${pageContext.response.locale}" + "]");
		});

		var pageLanguageCode = '${pageContext.response.locale}';
		//var pageDateFormat = '<%=((SimpleDateFormat)DateFormat.getDateInstance(DateFormat.MEDIUM, LocaleContextHolder.getLocale() )).toLocalizedPattern()%>';
		
		function validatorSetup(code) {
			jQuery.extend(jQuery.validator.messages, {
			    required: "<span class=\"label_validator_required\"><s:message code='validator_required'/></span>",
			    remote: "<span class=\"label_validator_remote\"><s:message code='validator_remote'/>.</span>",
			    email: "<span class=\"label_validator_email\"><s:message code='validator_email'/></span>",
			    url: "<span class=\"label_validator_url\"><s:message code='validator_url'/></span>",
			    date: "<span class=\"label_validator_date\"><s:message code='validator_date'/></span>",
			    dateISO: "<span class=\"label_validator_dateISO\"><s:message code='validator_dateISO'/></span>",
			    number: "<span class=\"label_validator_number\"><s:message code='validator_number'/></span>",
			    digits: "<span class=\"label_validator_digits\"><s:message code='validator_digits'/></span>",
			    creditcard: "<span class=\"label_validator_creditcard\"><s:message code='validator_creditcard'/></span>",
			    equalTo: "<span class=\"label_validator_equalTo\"><s:message code='validator_equalTo'/></span>",
			    accept: "<span class=\"label_validator_accept\"><s:message code='validator_accept'/></span>",
			    maxlength: jQuery.validator.format("<span class=\"label_validator_maxlength\"><s:message code='validator_maxlength'/></span>"),
			    minlength: jQuery.validator.format("<span class=\"label_validator_minlength\"><s:message code='validator_minlength'/></span>"),
			    rangelength: jQuery.validator.format("<span class=\"label_validator_rangelength\"><s:message code='validator_rangelength'/></span>"),
			    range: jQuery.validator.format("<span class=\"label_validator_range\"><s:message code='validator_range'/></span>"),
			    max: jQuery.validator.format("<span class=\"label_validator_max\"><s:message code='validator_max'/></span>"),
			    min: jQuery.validator.format("<span class=\"label_validator_min\"><s:message code='validator_min'/></span>")
			});
		}

		function language(code) {
			var flags = [];
			var codes = [];
			$("[class*='label_']").each(function( index ) {
				var className = $(this).attr("class").match(/label[\w_]*\b/)[0].slice(6);
				if(flags[className] != true) {
					flags[className] = true;
					codes.push(className)
				}
			});

			$.ajax({
					type : "POST",
					url : "translate?lang=" + code,
					data : {
						labels : codes
					},
					success : function(data) {
						var array = JSON.parse(data)
						for (var i = 0 ; i < codes.length ;i++) {
							$(".label_" + codes[i]).html(array[i]);
						}
						//$(".datefmt").each(function() {
						//	$(this).html(Date.parse($(this).html()).toLocaleDateString(code))
						//});
						pageLanguageCode = code;
						//pageDateFormat = array.datefmt;
						
						//validatorSetup("[" + code + "]");
					}
				});
		}
	</script>
  