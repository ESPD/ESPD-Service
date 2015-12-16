package eu.europa.ec.grow.espd.business.request.exclusion

import eu.europa.ec.grow.espd.domain.CriminalConvictions
import eu.europa.ec.grow.espd.domain.EspdDocument
/**
 * Created by ratoico on 12/9/15 at 11:44 AM.
 */
class FraudRequestTest extends AbstractRequestExclusionFixture {

    def "03. should contain the 'Fraud' criterion"() {
        given:
        def espd = new EspdDocument(fraud: new CriminalConvictions(exists: true))
        def idx = 0

        when:
        def request = parseRequestXml(espd)

        then: "CriterionID element"
        request.Criterion.size() == 1
        checkCriterionId(request, idx, "297d2323-3ede-424e-94bc-a91561e6f320")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "EXCLUSION.CRIMINAL_CONVICTIONS")

        then: "CriterionName element"
        request.Criterion[idx].Name.text() == "Fraud"

        then: "CriterionDescription element"
        request.Criterion[idx].Description.text() == "Has the economic operator itself or any person who is a member of its administrative, management or supervisory body or has powers of representation, decision or control therein been the subject of a conviction by final judgment for fraud, by a conviction rendered at the most five years ago or in which an exclusion period set out directly in the conviction continues to be applicable? Within the meaning of Article 1 of the Convention on the protection of the European Communities' financial interests (OJ C 316, 27.11.1995, p. 48)."

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "57(1)")

        then: "Your answer"
        request.Criterion[idx].Requirement.size() == 1
        checkRequirement(request.Criterion[idx].Requirement[0], "974c8196-9d1c-419c-9ca9-45bb9f5fd59a", "Your answer?", "CRITERION_INDICATOR")

        then: "check all the sub groups"
        request.Criterion[idx].SubCriterion.size() == 2

        then: "main sub group"
        request.Criterion[idx].SubCriterion[0].ID.text() == "94ff6812-b9a6-40c7-9676-d9fb83b51d51"
        request.Criterion[idx].SubCriterion[0].SubCriterion.size() == 1
        request.Criterion[idx].SubCriterion[0].Requirement.size() == 4

        then: "main sub group requirements"
        def r1_1 = request.Criterion[idx].SubCriterion[0].Requirement[0]
        checkRequirement(r1_1, "ecf40999-7b64-4e10-b960-7f8ff8674cf6", "Date of conviction", "DATE")

        def r1_2 = request.Criterion[idx].SubCriterion[0].Requirement[1]
        checkRequirement(r1_2, "7d35fb7c-da5b-4830-b598-4f347a04dceb", "Reason", "DESCRIPTION")

        def r1_3 = request.Criterion[idx].SubCriterion[0].Requirement[2]
        checkRequirement(r1_3, "c5012430-14da-454c-9d01-34cedc6a7ded", "Who has been convicted", "DESCRIPTION")

        def r1_4 = request.Criterion[idx].SubCriterion[0].Requirement[3]
        checkRequirement(r1_4, "9ca9096f-edd2-4f19-b6b1-b55c83a2d5c8", "Length of the period of exclusion", "TEXT")

        then: "check the self-cleaning sub group"
        checkSelfCleaningSubCriterion(request.Criterion[idx].SubCriterion[0].SubCriterion[0])

        then: "info available electronically sub group"
        checkInfoAvailableElectronicallySubCriterion(request.Criterion[idx].SubCriterion[1])
    }

}