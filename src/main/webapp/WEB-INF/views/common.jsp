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

	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap-3.2.0/css/bootstrap-theme.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap-3.2.0/css/bootstrap.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/bootstrap-3.2.0/js/bootstrap.min.js"></script>

	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap-plugins/select2/css/select2.ajax.min.css"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/bootstrap-plugins/select2/js/select2.full.ajax.min.js"></script>

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
		window.console = window.console || (function() {
		    var c = {}; c.log = c.warn = c.debug = c.info = c.error = c.time = c.dir = c.profile = c.clear = c.exception = c.trace = c.assert = function(){};
		    return c;
		})();

		function validator(validators,name,text) {validators[name] = jQuery.validator.format("<span data-i18n=\"validator_"+name+"\">"+text+"</span>");}

		var defaultValidators={};
		validator(defaultValidators, "required", "<s:message code='validator_required'/>");
		validator(defaultValidators, "remote", "<s:message code='validator_remote'/>");
		validator(defaultValidators, "email", "<s:message code='validator_email'/>");
		validator(defaultValidators, "url", "<s:message code='validator_url'/>");
		validator(defaultValidators, "date", "<s:message code='validator_date'/>");
		validator(defaultValidators, "dateISO", "<s:message code='validator_dateISO'/>");
		validator(defaultValidators, "number", "<s:message code='validator_number'/>");
		validator(defaultValidators, "digits", "<s:message code='validator_digits'/>");
		validator(defaultValidators, "creditcard", "<s:message code='validator_creditcard'/>");
		validator(defaultValidators, "equalTo", "<s:message code='validator_equalTo'/>");
		validator(defaultValidators, "accept", "<s:message code='validator_accept'/>");
		validator(defaultValidators, "maxlength", "<s:message code='validator_maxlength'/>");
		validator(defaultValidators, "minlength", "<s:message code='validator_minlength'/>");
		validator(defaultValidators, "rangelength", "<s:message code='validator_rangelength'/>");
		validator(defaultValidators, "range", "<s:message code='validator_range'/>");
		validator(defaultValidators, "max", "<s:message code='validator_max'/>");
		validator(defaultValidators, "min", "<s:message code='validator_min'/>");

		$(function() {
			$(".datepicker").datepicker({ format: "dd-mm-yyyy", clearBtn: true, todayHighlight: true });
		    $(".selectfilter").select2();
		    jQuery.extend(jQuery.validator.messages, defaultValidators);
		    $('[data-toggle="tooltip"]').tooltip({placement:"top", html: true, trigger: "hover"}).addClass( "fa" ).addClass( "fa-info-circle" );

		    $('.radiotab').click(function () {
		        $(this).tab('show');
		    });

		});

		var pageLanguageCode = '${pageContext.response.locale}';
		//var pageDateFormat = '<%=((SimpleDateFormat)DateFormat.getDateInstance(DateFormat.MEDIUM, LocaleContextHolder.getLocale() )).toLocalizedPattern()%>';

		function language(code) {
			var flags = [];
			var codes = [];

			$( "*[data-i18n]" ).each(function( index ) {
				var className = $(this).attr("data-i18n");
				if(flags[className] != true) {
					flags[className] = true;
					codes.push(className)
				}
			});
			for(var name in defaultValidators) {
				if(flags["validator_"+name] != true) {
					codes.push("validator_"+name);
				}
			}

			$.ajax({
					type : "POST",
					url : "translate?lang=" + code,
					data : {
						labels : codes
					},
					success : function(data) {
						var array = JSON.parse(data);
						var validators = {};
						for (var i = 0 ; i < codes.length ;i++) {
							if(codes[i].indexOf("validator_") == 0) {
								validator(validators, codes[i].substring("validator_".length), array[i]);
							}
							var elem = $( "*[data-i18n='"+codes[i]+"']" );
							if (elem.attr("data-toggle") == "tooltip") {
								elem.attr("title", array[i]);
								elem.attr("data-original-title", array[i])
							}
							else {
								elem.html(array[i]);
							}

						}
						pageLanguageCode = code;
						jQuery.extend(jQuery.validator.messages, validators);

						//$(".datefmt").each(function() {
						//	$(this).html(Date.parse($(this).html()).toLocaleDateString(code))
						//});
						//pageDateFormat = array.datefmt;
					}
				});
		}
	</script>
  