package eu.europa.ec.grow.espd.business.request.exclusion
import eu.europa.ec.grow.espd.business.AbstractEspdXmlMarshalling
import eu.europa.ec.grow.espd.domain.CriminalConvictions
import eu.europa.ec.grow.espd.domain.EspdDocument

/**
 * Created by ratoico on 12/9/15 at 11:44 AM.
 */
class FraudRequestTest extends AbstractEspdXmlMarshalling {

    def "03. should contain the 'Fraud' criterion"() {
        given:
        def espd = new EspdDocument(fraud: new CriminalConvictions(exists: true))

        when:
        def request = parseRequestXml(espd)
        def idx = 0

        then: "CriterionID element"
        request.Criterion.size() == 1
        checkCriterionId(request, idx, "297d2323-3ede-424e-94bc-a91561e6f320")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "EXCLUSION.CRIMINAL_CONVICTIONS")

        then: "CriterionName element"
        request.Criterion[idx].CriterionName.text() == "Fraud"

        then: "CriterionDescription element"
        request.Criterion[idx].CriterionDescription.text() == "Has the economic operator itself or any person who is a member of its administrative, management or supervisory body or has powers of representation, decision or control therein been the subject of a conviction by final judgment for fraud, by a conviction rendered at the most five years ago or in which an exclusion period set out directly in the conviction continues to be applicable? Within the meaning of Article 1 of the Convention on the protection of the European Communities' financial interests (OJ C 316, 27.11.1995, p. 48)."

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "57(1)")
    }

}