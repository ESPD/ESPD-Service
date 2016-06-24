/*
 *
 * Copyright 2016 EUROPEAN COMMISSION
 *
 * Licensed under the EUPL, Version 1.1 or – as soon they
 * will be approved by the European Commission - subsequent
 * versions of the EUPL (the "Licence");
 *
 * You may not use this work except in compliance with the Licence.
 *
 * You may obtain a copy of the Licence at:
 *
 * https://joinup.ec.europa.eu/community/eupl/og_page/eupl
 *
 * Unless required by applicable law or agreed to in
 * writing, software distributed under the Licence is
 * distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied.
 * See the Licence for the specific language governing
 * permissions and limitations under the Licence.
 *
 */

/* Pseudo console for IE9, otherwise it makes undefined error */
window.console = window.console || (function () {
        var c = {};
        c.log = c.warn = c.debug = c.info = c.error = c.time = c.dir = c.profile = c.clear = c.exception = c.trace = c.assert = function () {
        };
        return c;
    })();

function validator(validators, name, text) {
    validators[name] = jQuery.validator.format("<span data-i18n=\"validator_" + name + "\">" + text + "</span>");
}

function dataShow() {
    $($(this).attr("data-target-show")).show();
}

function dataHide() {
    $($(this).attr("data-target-hide")).hide();
}

var defaultValidators = {};

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
            codes.push(className);
        }
    });

    $.ajax({
        type: "POST",
        url: "translate?lang=" + code,
        data: {
            labels: codes
        },
        success: function (data) {
        	pageLanguage = code.toLowerCase();
            var array = JSON.parse(data);
            var validators = {};
            for (var i = 0; i < codes.length; i++) {
                if (codes[i].indexOf("validator_") == 0) {
                    validator(validators, codes[i].substring("validator_".length), array[i]);
                    if ($("*[data-i18n='" + codes[i] + "']").length == 0) continue;
                }
                var elem = $("*[data-i18n='" + codes[i] + "']");
                var tagName = elem.prop("tagName").toLowerCase();

                if (elem.attr("data-toggle") == "tooltip") {
                    elem.attr("title", array[i]);
                    elem.attr("data-original-title", array[i]);
                } else {
                    elem.html(array[i]);
                }
            }
            jQuery.extend(jQuery.validator.messages, validators);
            sortDropdowns();
            $('.ecertis-link-header:not(.collapsed)').click();//collapse ecertis links
        }
    });
}

function sortDropdowns() {
    $("select.optsorted").each(function () {
        var select = $(this);
        var selected = select.val();
        var options = select.find("option");
        options.remove();
        options = options.sort(function (a, b) {
            return a.innerHTML.localeCompare(b.innerHTML);
        });
        select.append(options);
        select.val(selected);
    });
}