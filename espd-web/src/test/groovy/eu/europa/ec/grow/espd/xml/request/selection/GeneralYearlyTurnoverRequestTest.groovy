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

package eu.europa.ec.grow.espd.xml.request.selection

import eu.europa.ec.grow.espd.domain.enums.criteria.SelectionCriterion
import eu.europa.ec.grow.espd.domain.EconomicFinancialStandingCriterion
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.xml.base.AbstractSelectionCriteriaFixture
/**
 * Created by ratoico on 12/9/15 at 1:48 PM.
 */
class GeneralYearlyTurnoverRequestTest extends AbstractSelectionCriteriaFixture {

    def "06. should contain the 'General yearly turnover' criterion"() {
        given:
        def espd = new EspdDocument(generalYearlyTurnover: new EconomicFinancialStandingCriterion(exists: true))

        when:
        def request = parseRequestXml(espd)
        def idx = getRequestCriterionIndex(SelectionCriterion.GENERAL_YEARLY_TURNOVER)

        then: "CriterionID element"
        checkCriterionId(request, idx, "499efc97-2ac1-4af2-9e84-323c2ca67747")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "CRITERION.SELECTION.ECONOMIC_FINANCIAL_STANDING.TURNOVER.GENERAL_YEARLY")

        then: "CriterionName element"
        request.Criterion[idx].Name.text() == "General yearly turnover"

        then: "CriterionDescription element"
        request.Criterion[idx].Description.text() == "Its general yearly turnover for the number of financial years required in the relevant notice, the procurement documents or the ESPD is as follows:"

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "58(3)")

        then: "check all the sub groups"
        request.Criterion[idx].RequirementGroup.size() == 6

        then: "G1"
        def crit = request.Criterion[idx]

        then: "check year amount currency subgroups"
        checkYearAmountCurrencyGroup1(crit.RequirementGroup[0])
        checkYearAmountCurrencyGroup2(crit.RequirementGroup[1])
        checkYearAmountCurrencyGroup3(crit.RequirementGroup[2])
        checkYearAmountCurrencyGroup4(crit.RequirementGroup[3])
        checkYearAmountCurrencyGroup5(crit.RequirementGroup[4])

        then: "info available electronically sub group"
        checkInfoAvailableElectronicallyRequirementGroup(crit.RequirementGroup[5])
    }

}