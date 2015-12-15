package eu.europa.ec.grow.espd.business.request.exclusion

import eu.europa.ec.grow.espd.business.AbstractEspdXmlMarshalling
import eu.europa.ec.grow.espd.domain.CriminalConvictions
import eu.europa.ec.grow.espd.domain.EspdDocument

/**
 * Created by ratoico on 12/8/15 at 10:20 AM.
 */
class ParticipationInCriminalOrganisationRequestTest extends AbstractEspdXmlMarshalling {

    def "01. should contain the 'Participation in a criminal organisation' criterion"() {
        given:
        def espd = new EspdDocument(criminalConvictions: new CriminalConvictions(exists: true))

        when:
        def request = parseRequestXml(espd)
        def idx = 0

        then: "CriterionID element"
        request.Criterion.size() == 1
        checkCriterionId(request, idx, "005eb9ed-1347-4ca3-bb29-9bc0db64e1ab")


        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "EXCLUSION.CRIMINAL_CONVICTIONS")

        then: "CriterionName element"
        request.Criterion[idx].Name.text() == "Participation in a criminal organisation"

        then: "CriterionDescription element"
        request.Criterion[idx].Description.text() == "Has the economic operator itself or any person who is a member of its administrative, management or supervisory body or has powers of representation, decision or control therein been the subject of a conviction by final judgment for participation in a criminal orgnisation, by a conviction rendered at the most five years ago or in which an exclusion period set out directly in the conviction continues to be applicable? As defined in Article 2 of Council Framework Decision 2008/841/JHA of 24 October 2008 on the fight against organised crime (OJ L 300, 11.11.2008, p. 42)."

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "57(1)")

//        then: "CriterionGroup"
//        request.Criterion[idx].CriterionRequirementGroup.size() == 2
//
//        then: "first group"
//        request.Criterion[idx].CriterionRequirementGroup[0].ID.text() == "7c637c0c-7703-4389-ba52-02997a055bd7"
//        request.Criterion[idx].CriterionRequirementGroup[0].CriterionRequirementGroup.size() == 1
//        request.Criterion[idx].CriterionRequirementGroup[0].CriterionRequirement.size() == 5
//
//        then: "G1 requirements"
//
//        def r1_1 = request.Criterion[idx].CriterionRequirementGroup[0].CriterionRequirement[0]
//        r1_1.CriterionRequirementID.text() == "974c8196-9d1c-419c-9ca9-45bb9f5fd59a"
//        r1_1.CriterionRequirementID.@schemeID.text() == "CriterionRelatedIDs"
//        r1_1.CriterionRequirementID.@schemeVersionID.text() == "1.0"
//        r1_1.CriterionRequirementDescription.text() == "Your answer?"
//        r1_1.ExpectedResponseType.text() == "REQUIREMENT_INDICATOR"
//
//
//        def r1_2 = request.Criterion[idx].CriterionRequirementGroup[0].CriterionRequirement[1]
//        r1_2.CriterionRequirementID.text() == "ecf40999-7b64-4e10-b960-7f8ff8674cf6"
//        r1_2.CriterionRequirementID.@schemeID.text() == "CriterionRelatedIDs"
//        r1_2.CriterionRequirementID.@schemeVersionID.text() == "1.0"
//        r1_2.CriterionRequirementDescription.text() == "Date of conviction"
//        r1_2.ExpectedResponseType.text() == "DATE"
//
//
//        def r1_3 = request.Criterion[idx].CriterionRequirementGroup[0].CriterionRequirement[2]
//        r1_3.CriterionRequirementID.text() == "7d35fb7c-da5b-4830-b598-4f347a04dceb"
//        r1_3.CriterionRequirementID.@schemeID.text() == "CriterionRelatedIDs"
//        r1_3.CriterionRequirementID.@schemeVersionID.text() == "1.0"
//        r1_3.CriterionRequirementDescription.text() == "Reason"
//        r1_3.ExpectedResponseType.text() == "DESCRIPTION"
//
//
//        def r1_4 = request.Criterion[idx].CriterionRequirementGroup[0].CriterionRequirement[3]
//        r1_4.CriterionRequirementID.text() == "c5012430-14da-454c-9d01-34cedc6a7ded"
//        r1_4.CriterionRequirementID.@schemeID.text() == "CriterionRelatedIDs"
//        r1_4.CriterionRequirementID.@schemeVersionID.text() == "1.0"
//        r1_4.CriterionRequirementDescription.text() == "Who has been convicted"
//        r1_4.ExpectedResponseType.text() == "DESCRIPTION"
//
//
//        def r1_5 = request.Criterion[idx].CriterionRequirementGroup[0].CriterionRequirement[4]
//        r1_5.CriterionRequirementID.text() == "9ca9096f-edd2-4f19-b6b1-b55c83a2d5c8"
//        r1_5.CriterionRequirementID.@schemeID.text() == "CriterionRelatedIDs"
//        r1_5.CriterionRequirementID.@schemeVersionID.text() == "1.0"
//        r1_5.CriterionRequirementDescription.text() == "Length of the period of exclusion"
//        r1_5.ExpectedResponseType.text() == "TEXT"
//
//        then: "check the self-cleaning subgroup"
//        request.Criterion[idx].CriterionRequirementGroup[0].CriterionRequirementGroup[0].CriterionRequirement.size() == 2
//
//        def r1_1_1 = request.Criterion[idx].CriterionRequirementGroup[0].CriterionRequirementGroup[0].CriterionRequirement[0]
//        r1_1_1.CriterionRequirementID.text() == "20c5361b-7599-4ee6-b030-7f8323174d1e"
//        r1_1_1.CriterionRequirementID.@schemeID.text() == "CriterionRelatedIDs"
//        r1_1_1.CriterionRequirementID.@schemeVersionID.text() == "1.0"
//        r1_1_1.CriterionRequirementDescription.text() == "Have you taken measures to demonstrate your reliability (\"Self-Cleaning\")?"
//        r1_1_1.ExpectedResponseType.text() == "REQUIREMENT_INDICATOR"
//
//
//        def r1_1_2 = request.Criterion[idx].CriterionRequirementGroup[0].CriterionRequirementGroup[0].CriterionRequirement[1]
//        r1_1_2.CriterionRequirementID.text() == "7b07904f-e080-401a-a3a1-9a3efeeda54b"
//        r1_1_2.CriterionRequirementID.@schemeID.text() == "CriterionRelatedIDs"
//        r1_1_2.CriterionRequirementID.@schemeVersionID.text() == "1.0"
//        r1_1_2.CriterionRequirementDescription.text() == "Please describe them"
//        r1_1_2.ExpectedResponseType.text() == "DESCRIPTION"
//
//        then: "info available electronically group"
//        request.Criterion[idx].CriterionRequirementGroup[1].ID.text() == "7458d42a-e581-4640-9283-34ceb3ad4345"
//        request.Criterion[idx].CriterionRequirementGroup[1].CriterionRequirementGroup.size() == 0
//        request.Criterion[idx].CriterionRequirementGroup[1].CriterionRequirement.size() == 3
//
//        then: "G2 requirements"
//
//        def r_2_1 = request.Criterion[idx].CriterionRequirementGroup[1].CriterionRequirement[0]
//        r_2_1.CriterionRequirementID.text() == "c1347b74-1872-4060-a6db-f4044edcd7c4"
//        r_2_1.CriterionRequirementID.@schemeID.text() == "CriterionRelatedIDs"
//        r_2_1.CriterionRequirementID.@schemeVersionID.text() == "1.0"
//        r_2_1.CriterionRequirementDescription.text() == "Is this information available electronically?"
//        r_2_1.ExpectedResponseType.text() == "REQUIREMENT_INDICATOR"
//
//
//        def r2_2 = request.Criterion[idx].CriterionRequirementGroup[1].CriterionRequirement[1]
//        r2_2.CriterionRequirementID.text() == "f4313bb6-21b6-499e-bdff-debe10e11d2c"
//        r2_2.CriterionRequirementID.@schemeID.text() == "CriterionRelatedIDs"
//        r2_2.CriterionRequirementID.@schemeVersionID.text() == "1.0"
//        r2_2.CriterionRequirementDescription.text() == "URL"
//        r2_2.ExpectedResponseType.text() == "URL"
//
//
//        def r2_3 = request.Criterion[idx].CriterionRequirementGroup[1].CriterionRequirement[2]
//        r2_3.CriterionRequirementID.text() == "1f1cd18e-3e01-4ca2-af4c-e2981924ba8d"
//        r2_3.CriterionRequirementID.@schemeID.text() == "CriterionRelatedIDs"
//        r2_3.CriterionRequirementID.@schemeVersionID.text() == "1.0"
//        r2_3.CriterionRequirementDescription.text() == "Code"
//        r2_3.ExpectedResponseType.text() == "TEXT"
    }

}