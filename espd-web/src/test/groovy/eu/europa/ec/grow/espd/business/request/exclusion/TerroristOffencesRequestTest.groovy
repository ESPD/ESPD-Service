package eu.europa.ec.grow.espd.business.request.exclusion
import eu.europa.ec.grow.espd.business.AbstractEspdXmlMarshalling
import eu.europa.ec.grow.espd.domain.CriminalConvictions
import eu.europa.ec.grow.espd.domain.EspdDocument

/**
 * Created by ratoico on 12/9/15 at 11:55 AM.
 */
class TerroristOffencesRequestTest extends AbstractEspdXmlMarshalling {

    def "04. should contain the 'Terrorist offences or offences linked to terrorist activities' criterion"() {
        given:
        def espd = new EspdDocument(terroristOffences: new CriminalConvictions(exists: true))

        when:
        def request = parseRequestXml(espd)
        def idx = 0

        then: "CriterionID element"
        request.Criterion.size() == 1
        checkCriterionId(request, idx, "d486fb70-86b3-4e75-97f2-0d71b5697c7d")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "EXCLUSION.CRIMINAL_CONVICTIONS")

        then: "CriterionName element"
        request.Criterion[idx].CriterionName.text() == "Terrorist offences or offences linked to terrorist activities"

        then: "CriterionDescription element"
        request.Criterion[idx].CriterionDescription.text() == "Has the economic operator itself or any person who is a member of its administrative, management or supervisory body or has powers of representation, decision or control therein been the subject of a conviction by final judgment for terrorist offences or offences linked to terrorist activities, by a conviction rendered at the most five years ago or in which an exclusion period set out directly in the conviction continues to be applicable? As defined in Articles 1 and 3 of Council Framework Decision of 13 June 2002 on combating terrorism (OJ L 164, 22.6.2002, p. 3). This exclusion ground also includes inciting or aiding or abetting or attempting to commit an offence, as referred to in Article 4 of that Framework Decision."

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "57(1)")

    }

}