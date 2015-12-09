package eu.europa.ec.grow.espd.business.request.exclusion

import eu.europa.ec.grow.espd.business.AbstractEspdXmlMarshalling
import eu.europa.ec.grow.espd.domain.CriminalConvictions
import eu.europa.ec.grow.espd.domain.EspdDocument

/**
 * Created by ratoico on 12/9/15 at 11:35 AM.
 */
class CorruptionRequestTest extends AbstractEspdXmlMarshalling {

    def "02. should contain the 'Corruption' criterion"() {
        given:
        def espd = new EspdDocument(corruption: new CriminalConvictions(exists: true))

        when:
        def request = parseRequestXml(espd)

        then: "CriterionID element"
        request.Criterion.size() == 1
        checkCriterionId(request, 0, "c27b7c4e-c837-4529-b867-ed55ce639db5")


        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, 0, "EXCLUSION.CRIMINAL_CONVICTIONS")

        then: "CriterionName element"
        request.Criterion[0].CriterionName.text() == "Corruption"

        then: "CriterionDescription element"
        request.Criterion[0].CriterionDescription.text() == "Has the economic operator itself or any person who is a member of its administrative, management or supervisory body or has powers of representation, decision or control therein been the subject of a conviction by final judgment for corruption, by a conviction rendered at the most five years ago or in which an exclusion period set out directly in the conviction continues to be applicable? As defined in Article 3 of the Convention on the fight against corruption involving officials of the European Communities or officials of Member States of the European Union, OJ C 195, 25.6.1997, p. 1, and in Article 2(1) of Council Framework Decision 2003/568/JHA of 22 July 2003 on combating corruption in the private sector (OJ L 192, 31.7.2003, p. 54). This exclusion ground also includes corruption as defined in the national law of the contracting authority (contracting entity) or the economic operator."

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, 0, "57(1)")
    }

}