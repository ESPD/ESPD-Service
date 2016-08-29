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

function EcertisHandler(url, country) {
	return function() {
	   	var uuid = $(this).attr("data-uuid");
	   	if($(this).hasClass( "collapsed" ) && uuid != "") {
	
	   		var content = $(this).attr("data-target");
	    	$(content).find("#content, #issued, #ecertis404").hide();
	    	$(content).children("#loading").show();
						    	
	    	$.getJSON(url.replace("[uuid]",uuid).replace("[country]",country.toLowerCase()).replace("[lang]",pageLanguage),
	    		function( data ) {
					$(content).children("#loading").hide();
									
					if(data && data.DomainID == "eproc" && data.hasOwnProperty("SubCriterion")) {
						content = $(content).children("#content").show();
						$(content).find("#language").html(data.Name.languageID.toUpperCase());
										
						var T = $(content).find("#template").hide();
						$(T).siblings("#subcriterion").remove();
	
						if(data.hasOwnProperty("SubCriterion")) {
							$.each( data.SubCriterion, function( key, val ) {
								var item = T.clone().attr("id","subcriterion").appendTo(T.parent()).show();
								var list = item.children("#evidencesFound").html("");
	    						item.find("#evidencesFound, #evidencesNotFound").hide();
								item.children("#subname").html(val.Name.value);
	
								//Currently display only first LegislationReference from array, in future could be more
								item.find("#description").html(val.LegislationReference[0].Title.value);
								item.find("#url").text(val.LegislationReference[0].Article.value).attr("href",data.LegislationReference[0].URI);
	
								var hasEvidences = false;
								$.each( $(val.RequirementGroup), function( key, val ) {
									$.each( $(val.TypeOfEvidence), function( key, val ) {
										var names = [];
										$.each( $(val["EvidenceIssuerParty"]), function( key, val ) {
											$.each($(val["PartyName"]), function(i,val) { names.push(val.Name.value) });
										})
	
										// EvidenceDocumentReference with evidence URL is an array with only one element,
										// currently it is implemented as it is, but in future could be more than one
										var evidenceURL = val.EvidenceDocumentReference[0].Attachment.ExternalReference.URI;
										var evidence = T.find("#evidence").clone().appendTo(list);
										evidence.find("#name").text(val.Name.value).attr("href", evidenceURL);
										evidence.find("#issued").toggle(names.length != 0).children("#issuerNames").text(names.join(","));
	
										hasEvidences = true;
									});
								});
								item.children(hasEvidences?"#evidencesFound":"#evidencesNotFound").show();
							});
						}
					}
					else {
		   				$(content).find("#ecertis404").show();
					}
		   		}).fail(function() {
		   			$(content).children("#loading").hide();
		   			$(content).find("#ecertis404").show();
				}
		   	);
	    }
	}
}