package eu.europa.ec.grow.espd.business.request.exclusion

import eu.europa.ec.grow.espd.business.AbstractEspdXmlMarshalling
import eu.europa.ec.grow.espd.domain.Criterion
import eu.europa.ec.grow.espd.domain.EspdDocument

/**
 * Created by ratoico on 12/9/15 at 1:28 PM.
 */
class NationalExclusionGroundsRequestTest extends AbstractEspdXmlMarshalling {

    def "21. should contain the 'Purely national grounds of exclusion' criterion"() {
        given:
        def espd = new EspdDocument(purelyNationalGrounds: new Criterion(exists: true))

        when:
        def request = parseRequestXml(espd)
        def idx = 0

        then: "CriterionID element"
        request.Criterion.size() == 1
        checkCriterionId(request, idx, "63adb07d-db1b-4ef0-a14e-a99785cf8cf6")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "EXCLUSION.OTHER")

        then: "CriterionName element"
        request.Criterion[idx].CriterionName.text() == "Purely national exclusion grounds"

        then: "CriterionDescription element"
        request.Criterion[idx].CriterionDescription.text() == "Other exclusion grounds that may be foreseen in the national legislation of the contracting authority’s or contracting entity’s Member State. Do the purely national grounds of exclusion, which are specified in the relevant notice or in the procurement documents, apply?"

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "57(4)")
    }

}