package eu.europa.ec.grow.espd.business.request.exclusion
import eu.europa.ec.grow.espd.business.AbstractEspdXmlMarshalling
import eu.europa.ec.grow.espd.domain.BreachOfObligations
import eu.europa.ec.grow.espd.domain.EspdDocument

/**
 * Created by ratoico on 12/9/15 at 1:18 PM.
 */
class AnalogousSituationRequestTest extends AbstractEspdXmlMarshalling {

    def "13. should contain the 'Analogous situation like bankruptcy under national law' criterion"() {
        given:
        def espd = new EspdDocument(analogousSituation: new BreachOfObligations(exists: true))

        when:
        def request = parseRequestXml(espd)
        def idx = 0

        then: "CriterionID element"
        request.Criterion.size() == 1
        checkCriterionId(request, idx, "daffa2a9-9f8f-4568-8be8-7b8bf306d096")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "EXCLUSION.BANKRUPTCY_INSOLVENCY")

        then: "CriterionName element"
        request.Criterion[idx].CriterionName.text() == "Analogous situation like bankruptcy under national law"

        then: "CriterionDescription element"
        request.Criterion[idx].CriterionDescription.text() == "Is the economic operator in in any analogous situation like bankruptcy arising from a similar procedure under national laws and regulations? This information needs not be given if exclusion of economic operators in this case has been made mandatory under the applicable national law without any possibility of derogation where the economic operator is nevertheless able to perform the contract."

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "57(4)")
    }

}