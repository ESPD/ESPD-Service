package eu.europa.ec.grow.espd.business.request.exclusion

import eu.europa.ec.grow.espd.domain.BreachOfObligations
import eu.europa.ec.grow.espd.domain.EspdDocument
/**
 * Created by ratoico on 12/9/15 at 1:15 PM.
 */
class BankruptcyRequestSpecification extends AbstractRequestExclusionFixture {

    def "10. should contain the 'Bankruptcy' criterion"() {
        given:
        def espd = new EspdDocument(bankruptcy: new BreachOfObligations(exists: true))

        when:
        def request = parseRequestXml(espd)
        def idx = 0

        then: "CriterionID element"
        request.Criterion.size() == 1
        checkCriterionId(request, idx, "d3732c09-7d62-4edc-a172-241da6636e7c")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "EXCLUSION.BANKRUPTCY_INSOLVENCY")

        then: "CriterionName element"
        request.Criterion[idx].Name.text() == "Bankruptcy"

        then: "CriterionDescription element"
        request.Criterion[idx].Description.text() == "Is the economic operator bankrupt? This information needs not be given if exclusion of economic operators in this case has been made mandatory under the applicable national law without any possibility of derogation where the economic operator is nevertheless able to perform the contract."

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "57(4)")
    }

}