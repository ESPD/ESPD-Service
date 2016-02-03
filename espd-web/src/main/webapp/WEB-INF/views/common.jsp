<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="org.springframework.context.i18n.LocaleContextHolder" %>
<%@page import="java.text.DateFormat" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
eu.europa.ec.grow.espd.util.I18NFunc inst = new eu.europa.ec.grow.espd.util.I18NFunc(pageContext);
request.setAttribute("i18n", inst.message());
request.setAttribute("div18n", inst.div());
request.setAttribute("span18n", inst.span());
%>

<meta charset="utf-8"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="ESPD is an information system that will aid in the process of creating an electronic file used throughout the tendering process in the EU and will reduce the administrative burden by reusing key information on the Contracting Authority, Economical Operator, criteria and evidence description.">
<meta name="keywords" content="espd, public, procurement, europa, europe, european commission"/>
<meta name="author" content="European Commission">
<title>ESPD</title>

<link rel="icon" type="image/png" href="data:image/png;base64,iVBORw0KGgo=">
<link rel="stylesheet" type="text/css" href="<s:url value="/static/css/all.css"/>">
<script src="<s:url value="/static/js/all.js"/>"></script>

<script>
    <%-- Pseudo console for f**g IE9, otherwise it makes undefined error --%>
    window.console = window.console || (function () {
                var c = {};
                c.log = c.warn = c.debug = c.info = c.error = c.time = c.dir = c.profile = c.clear = c.exception = c.trace = c.assert = function () {};
                return c;
            })();

    function validator(validators, name, text) {
        validators[name] = jQuery.validator.format("<span data-i18n=\"validator_" + name + "\">" + text + "</span>");
    }

    var defaultValidators = {};
    validator(defaultValidators, "required", "${i18n['validator_required']}");
    validator(defaultValidators, "remote", "${i18n['validator_remote']}");
    validator(defaultValidators, "email", "${i18n['validator_email']}");
    validator(defaultValidators, "url", "${i18n['validator_url']}");
    validator(defaultValidators, "date", "${i18n['validator_date']}");
    validator(defaultValidators, "dateISO", "${i18n['validator_dateISO']}");
    validator(defaultValidators, "number", "${i18n['validator_number']}");
    validator(defaultValidators, "digits", "${i18n['validator_digits']}");
    validator(defaultValidators, "creditcard", "${i18n['validator_creditcard']}");
    validator(defaultValidators, "equalTo", "${i18n['validator_equalTo']}");
    validator(defaultValidators, "accept", "${i18n['validator_accept']}");
    validator(defaultValidators, "maxlength", "${i18n['validator_maxlength']}");
    validator(defaultValidators, "minlength", "${i18n['validator_minlength']}");
    validator(defaultValidators, "rangelength", "${i18n['validator_rangelength']}");
    validator(defaultValidators, "range", "${i18n['validator_range']}");
    validator(defaultValidators, "max", "${i18n['validator_max']}");
    validator(defaultValidators, "min", "${i18n['validator_min']}");

    $(function () {
        //$(".filecontrol").fileinput();
        $("input.datepicker").datepicker({format: "dd-mm-yyyy", clearBtn: true, todayHighlight: true});
        //$(".selectfilter").select2();
        //$(":input").inputmask(); // seems it is too heavy to apply on criterias forms

        jQuery.extend(jQuery.validator.messages, defaultValidators);
        $('span[data-toggle="tooltip"]').tooltip({
            placement: "top",
            html: true,
            trigger: "hover"
        }).addClass("fa").addClass("fa-info-circle");

        $('input.radiotab').click(function () {
            $(this).tab('show');
        });
        $('input.checktoggle:checkbox').change(function () {
            if ($(this).prop('checked')) {
                $($(this).attr("data-target")).show();
                $($(this).attr("data-target-invert")).hide();
            }
            else {
                $($(this).attr("data-target")).hide();
                $($(this).attr("data-target-invert")).show();
            }
        });
        $('input.checktoggle:checkbox:checked').each(function (index) {
            $($(this).attr("data-target")).show();
            $($(this).attr("data-target-invert")).hide();
        });
        $('input.checktoggle:checkbox:not(:checked)').each(function (index) {
            $($(this).attr("data-target")).hide();
            $($(this).attr("data-target-invert")).show();
        });
       
        $('input.radioslide:checkbox').bootstrapToggle({
            style: "ios",
            width: "57",
            size: "mini",
            on: "Yes",
            off: "No",
            onstyle: "primary ios-toggle-on",
            offstyle: "default ios-toggle-off"
        });
    });

    var pageLanguageCode = '${pageContext.response.locale}';
    //var pageDateFormat = '<%=((SimpleDateFormat)DateFormat.getDateInstance(DateFormat.MEDIUM, LocaleContextHolder.getLocale() )).toLocalizedPattern()%>';

    function language(code) {
        var flags = [];
        var codes = [];

        for (var name in defaultValidators) {
            if (flags["validator_" + name] != true) {
                codes.push("validator_" + name);
            }
        }
        $("*[data-i18n]").each(function (index) {
            var className = $(this).attr("data-i18n");
            if (flags[className] != true) {
                flags[className] = true;
                codes.push(className)
            }
        });

        $.ajax({
            type: "POST",
            url: '<s:url value="/translate"/>?lang=' + code,
            data: {
                labels: codes
            },
            success: function (data) {
                var array = JSON.parse(data);
                var validators = {};
                for (var i = 0; i < codes.length; i++) {
                    if (codes[i].indexOf("validator_") == 0) {
                        validator(validators, codes[i].substring("validator_".length), array[i]);
                    }
                    else {
	                    var elem = $("*[data-i18n='" + codes[i] + "']");
	                    var tagName = elem.prop("tagName").toLowerCase();
	                    if(tagName == "input" || tagName == "textarea") {
	                    	$("*[data-i18n='" + codes[i] + "']").attr('placeholder', array[i]);
	                    }
	                    else if (elem.attr("data-toggle") == "tooltip") {
	                        elem.attr("title", array[i]);
	                        elem.attr("data-original-title", array[i])
	                    }
	                    else {
	                        elem.html(array[i]);
	                    }
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
  