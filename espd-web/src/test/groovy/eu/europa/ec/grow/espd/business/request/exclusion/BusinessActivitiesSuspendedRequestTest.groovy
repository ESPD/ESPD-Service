package eu.europa.ec.grow.espd.business.request.exclusion
import eu.europa.ec.grow.espd.business.AbstractEspdXmlMarshalling
import eu.europa.ec.grow.espd.domain.BreachOfObligations
import eu.europa.ec.grow.espd.domain.EspdDocument

/**
 * Created by ratoico on 12/9/15 at 1:20 PM.
 */
class BusinessActivitiesSuspendedRequestTest extends AbstractEspdXmlMarshalling {

    def "15. should contain the 'Business activities are suspended' criterion"() {
        given:
        def espd = new EspdDocument(businessActivitiesSuspended: new BreachOfObligations(exists: true))

        when:
        def request = parseRequestXml(espd)
        def idx = 0

        then: "CriterionID element"
        request.Criterion.size() == 1
        checkCriterionId(request, idx, "166536e2-77f7-455c-b018-70582474e4f6")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "EXCLUSION.BANKRUPTCY_INSOLVENCY")

        then: "CriterionName element"
        request.Criterion[idx].CriterionName.text() == "Business activities are suspended"

        then: "CriterionDescription element"
        request.Criterion[idx].CriterionDescription.text() == "Are the business activities of the economic operator suspended?  This information needs not be given if exclusion of economic operators in this case has been made mandatory under the applicable national law without any possibility of derogation where the economic operator is nevertheless able to perform the contract."

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "57(4)")
    }

}