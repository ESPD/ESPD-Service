<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<%--
  ~
  ~ Copyright 2016 EUROPEAN COMMISSION
  ~
  ~ Licensed under the EUPL, Version 1.1 or – as soon they
  ~ will be approved by the European Commission - subsequent
  ~ versions of the EUPL (the "Licence");
  ~
  ~ You may not use this work except in compliance with the Licence.
  ~
  ~ You may obtain a copy of the Licence at:
  ~
  ~ https://joinup.ec.europa.eu/community/eupl/og_page/eupl
  ~
  ~ Unless required by applicable law or agreed to in
  ~ writing, software distributed under the Licence is
  ~ distributed on an "AS IS" basis,
  ~ WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
  ~ express or implied.
  ~ See the Licence for the specific language governing
  ~ permissions and limitations under the Licence.
  ~
  --%>

<tiles:importAttribute name="page"/>
<tiles:importAttribute name="agent"/>
<tiles:importAttribute name="showLink"/>

<script type="text/javascript">
	function stopTimer(x) {
		clearInterval(x);
		document.getElementById("countdowntimer").innerHTML = "00:00";
	}

	function startTimer(duration, display) {
		
		var start = Date.now(), diff, minutes, seconds;

		function timer() {
			// get the number of seconds that have elapsed since 
			// startTimer() was called
			diff = duration - (((Date.now() - start) / 1000) | 0);

			// truncates the float
			minutes = (diff / 60) | 0;
			seconds = (diff % 60) | 0;

			minutes = minutes < 10 ? "0" + minutes : minutes;
			seconds = seconds < 10 ? "0" + seconds : seconds;

			display.textContent = minutes + ":" + seconds;

			if (diff <= 0) {
				stopTimer(x);
			}
		};
		// we do not want to wait a full second before the timer starts
		timer();
		var x = setInterval(timer, 1000);
	}

	window.onload = function() {
		var sixtyMinutes = 60 * 60, 
		display = document.querySelector('#countdowntimer');
		startTimer(sixtyMinutes, display);
	};
</script>

<div class="row">
    <strong class="col-md-8 col-md-offset-6">
        <span class="countdown">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;If you do not take any action, your session will expire in </span>
        <span class="countdown" id="countdowntimer">60:00 </span>
	</strong>
</div>
