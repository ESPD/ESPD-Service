<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

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

<tiles:importAttribute name="field"/>

<div class="form-group">
    <table style="width:100%">
    

        <c:forEach var="group" items="${espd[field].unboundedGroups}" varStatus="loop">

            <tr style="border: 1px solid slategrey;">
                <td>
                    <label class="control-label col-md-3 small">${span18n['crit_description']}</label>
                </td>
                <td>
                    <form:textarea path="${field}.unboundedGroups[${loop.index}]['description']" cssClass="form-control small"
                                   cssStyle="border-radius: 0;"/>
                </td>
            </tr>
            <tr style="border: 1px solid slategrey;">
                <td>
                    <label class="control-label col-md-3 small">${span18n['crit_amount']}</label>
                </td>
                <td>
                    <form:textarea rows="1" path="${field}.unboundedGroups[${loop.index}]['amount']" number="true"
                                   cssClass="form-control small"
                                   cssStyle="border-radius: 0;padding-left: 2px; padding-right: 0;"/>

                    <tiles:insertDefinition name="currencies">
                        <tiles:putAttribute name="currencyField" value="${field}.unboundedGroups[${loop.index}]['currency']"/>
                        <tiles:putAttribute name="style" value="border-radius: 0;"/>
                    </tiles:insertDefinition>
                </td>
            </tr>
            <tr style="border: 1px solid slategrey;">
                <td>
                    <label class="control-label col-md-3 small">${span18n['crit_start_date']}</label>
                </td>
                <td>
                    <form:input type="text" path="${field}.unboundedGroups[${loop.index}]['startDate']" cssClass="form-control datepicker"
                                cssStyle="border-radius: 0;"/>
                </td>
            </tr>
            <tr style="border: 1px solid slategrey;">
                <td>
                    <label class="control-label col-md-3 small">${span18n['crit_end_date']}</label>
                </td>
                <td>
                    <form:input type="text" path="${field}.unboundedGroups[${loop.index}]['endDate']" cssClass="form-control datepicker"
                                cssStyle="border-radius: 0;"/>
                </td>
            </tr>
            <tr style="border: 1px solid slategrey;">
                <td>
                    <label class="control-label col-md-3 small">${span18n['crit_recipients']}</label>
                </td>
                <td>
                    <form:textarea path="${field}.unboundedGroups[${loop.index}]['recipients']" cssClass="form-control small"
                                   cssStyle="border-radius: 0;"/>
                </td>
            </tr>
            <tr>
                <td>&nbsp;</td>
            </tr>
        </c:forEach>
    </table>
</div>

