package eu.europa.ec.grow.espd.business.request.exclusion

import eu.europa.ec.grow.espd.domain.CriminalConvictions
import eu.europa.ec.grow.espd.domain.EspdDocument
/**
 * Created by ratoico on 12/9/15 at 11:57 AM.
 */
class MoneyLaunderingRequestTest extends AbstractRequestExclusionFixture {


    def "05. should contain the 'Money laundering or terrorist financing' criterion"() {
        given:
        def espd = new EspdDocument(moneyLaundering: new CriminalConvictions(exists: true))

        when:
        def request = parseRequestXml(espd)
        def idx = 0

        then: "CriterionID element"
        request.Criterion.size() == 1
        checkCriterionId(request, idx, "47112079-6fec-47a3-988f-e561668c3aef")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "EXCLUSION.CRIMINAL_CONVICTIONS")

        then: "CriterionName element"
        request.Criterion[idx].Name.text() == "Money laundering or terrorist financing"

        then: "CriterionDescription element"
        request.Criterion[idx].Description.text() == "Has the economic operator itself or any person who is a member of its administrative, management or supervisory body or has powers of representation, decision or control therein been the subject of a conviction by final judgment for money laundering or terrorist financing, by a conviction rendered at the most five years ago or in which an exclusion period set out directly in the conviction continues to be applicable? As defined in Article 1 of Directive 2005/60/EC of the European Parliament and of the Council of 26 October 2005 on the prevention of the use of the financial system for the purpose of money laundering and terrorist financing (OJ L 309, 25.11.2005, p. 15)."

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "57(1)")

        then: "Your answer"
        request.Criterion[idx].Requirement.size() == 1
        checkRequirement(request.Criterion[idx].Requirement[0], "974c8196-9d1c-419c-9ca9-45bb9f5fd59a", "Your answer?", "CRITERION_INDICATOR")

        then: "check all the sub criteria"
        request.Criterion[idx].SubCriterion.size() == 2

        then: "main sub criterion"
        request.Criterion[idx].SubCriterion[0].ID.text() == "7a866000-53f4-47a9-a4b7-f9f4a81392bf"
        request.Criterion[idx].SubCriterion[0].SubCriterion.size() == 1
        request.Criterion[idx].SubCriterion[0].Requirement.size() == 4

        then: "main sub criterion requirements"
        def r1_1 = request.Criterion[idx].SubCriterion[0].Requirement[0]
        checkRequirement(r1_1, "ecf40999-7b64-4e10-b960-7f8ff8674cf6", "Date of conviction", "DATE")

        def r1_2 = request.Criterion[idx].SubCriterion[0].Requirement[1]
        checkRequirement(r1_2, "7d35fb7c-da5b-4830-b598-4f347a04dceb", "Reason", "DESCRIPTION")

        def r1_3 = request.Criterion[idx].SubCriterion[0].Requirement[2]
        checkRequirement(r1_3, "c5012430-14da-454c-9d01-34cedc6a7ded", "Who has been convicted", "DESCRIPTION")

        def r1_4 = request.Criterion[idx].SubCriterion[0].Requirement[3]
        checkRequirement(r1_4, "9ca9096f-edd2-4f19-b6b1-b55c83a2d5c8", "Length of the period of exclusion", "TEXT")

        then: "check the self-cleaning sub criterion"
        checkSelfCleaningSubCriterion(request.Criterion[idx].SubCriterion[0].SubCriterion[0])

        then: "info available electronically sub criterion"
        checkInfoAvailableElectronicallySubCriterion(request.Criterion[idx].SubCriterion[1])
    }

}