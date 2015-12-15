package eu.europa.ec.grow.espd.business.request.exclusion

import eu.europa.ec.grow.espd.domain.CriminalConvictions
import eu.europa.ec.grow.espd.domain.EspdDocument
/**
 * Created by ratoico on 12/9/15 at 11:58 AM.
 */
class ChildLabourRequestTest extends AbstractRequestExclusionFixture {

    def "06. should contain the 'Child labour and other forms of trafficking in human beings' criterion"() {
        given:
        def espd = new EspdDocument(childLabour: new CriminalConvictions(exists: true))

        when:
        def request = parseRequestXml(espd)
        def idx = 0

        then: "CriterionID element"
        request.Criterion.size() == 1
        checkCriterionId(request, idx, "d789d01a-fe03-4ccd-9898-73f9cfa080d1")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "EXCLUSION.CRIMINAL_CONVICTIONS")

        then: "CriterionName element"
        request.Criterion[idx].Name.text() == "Child labour and other forms of trafficking in human beings"

        then: "CriterionDescription element"
        request.Criterion[idx].Description.text() == "Has the economic operator itself or any person who is a member of its administrative, management or supervisory body or has powers of representation, decision or control therein been the subject of a conviction by final judgment for child labour and other forms of trafficking in human beings, by a conviction rendered at the most five years ago or in which an exclusion period set out directly in the conviction continues to be applicable? As defined in Article 2 of Directive 2011/36/EU of the European Parliament and of the Council of 5 April 2011 on preventing and combating trafficking in human beings and protecting its victims, and replacing Council Framework Decision 2002/629/JHA (OJ L 101, 15.4.2011, p. 1)."

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "57(1)")

        then: "Your answer"
        request.Criterion[idx].Requirement.size() == 1
        checkRequirement(request.Criterion[idx].Requirement[0], "974c8196-9d1c-419c-9ca9-45bb9f5fd59a", "Your answer?", "CRITERION_INDICATOR")

        then: "check all the sub criteria"
        request.Criterion[idx].SubCriterion.size() == 2

        then: "main sub criterion"
        request.Criterion[idx].SubCriterion[0].ID.text() == "602c69d2-b9db-4edf-bd64-412f476d7575"
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