package eu.europa.ec.grow.espd.business.request.exclusion
import eu.europa.ec.grow.espd.business.AbstractEspdXmlMarshalling
import eu.europa.ec.grow.espd.domain.BreachOfObligations
import eu.europa.ec.grow.espd.domain.EspdDocument

/**
 * Created by ratoico on 12/9/15 at 1:16 PM.
 */
class InsolvencyRequestTest extends AbstractEspdXmlMarshalling {

    def "11. should contain the 'Insolvency' criterion"() {
        given:
        def espd = new EspdDocument(insolvency: new BreachOfObligations(exists: true))

        when:
        def request = parseRequestXml(espd)
        def idx = 0

        then: "CriterionID element"
        request.Criterion.size() == 1
        checkCriterionId(request, idx, "396f288a-e267-4c20-851a-ed4f7498f137")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "EXCLUSION.BANKRUPTCY_INSOLVENCY")

        then: "CriterionName element"
        request.Criterion[idx].CriterionName.text() == "Insolvency"

        then: "CriterionDescription element"
        request.Criterion[idx].CriterionDescription.text() == "Is the economic operator the subject of insolvency or winding-up? This information needs not be given if exclusion of economic operators in this case has been made mandatory under the applicable national law without any possibility of derogation where the economic operator is nevertheless able to perform the contract."

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "57(4)")
    }

}