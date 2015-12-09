package eu.europa.ec.grow.espd.business.request.selection
import eu.europa.ec.grow.espd.business.AbstractEspdXmlMarshalling
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.SelectionCriterion
/**
 * Created by ratoico on 12/9/15 at 1:48 PM.
 */
class SupplyContractsCertificatesByQCRequestTest extends AbstractEspdXmlMarshalling {

    def "30. should contain the 'For supply contracts: certificates by quality control institutes' criterion"() {
        given:
        def espd = new EspdDocument(supplyContractsCertificatesQc: new SelectionCriterion(exists: true))

        when:
        def request = parseRequestXml(espd)
        def idx = 0

        then: "CriterionID element"
        request.Criterion.size() == 1
        checkCriterionId(request, idx, "a7669d7d-9297-43e1-9d10-691a1660187c")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "SELECTION.TECHNICAL_PROFESSIONAL_ABILITY")

        then: "CriterionName element"
        request.Criterion[idx].CriterionName.text() == "For supply contracts: certificates by quality control institutes"

        then: "CriterionDescription element"
        request.Criterion[idx].CriterionDescription.text() == "Can the economic operator provide the required certificates drawn up by official quality control institutes or agencies of recognised competence attesting the conformity of products clearly identified by references to the technical specifications or standards, which are set out in the relevant notice or the procurement documents?"

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "58(4)")
    }

}