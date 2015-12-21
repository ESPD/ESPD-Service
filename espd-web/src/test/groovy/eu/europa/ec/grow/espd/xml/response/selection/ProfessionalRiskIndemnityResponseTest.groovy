package eu.europa.ec.grow.espd.xml.request.selection

import eu.europa.ec.grow.espd.xml.base.AbstractSelectionCriteriaFixture
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.SelectionCriterion
/**
 * Created by ratoico on 12/9/15 at 1:48 PM.
 */
class ProfessionalRiskIndemnityResponseTest extends AbstractSelectionCriteriaFixture {

    def "11. should contain the 'Professional risk indemnity insurance' criterion"() {
        given:
        def espd = new EspdDocument(professionalRiskInsurance: new SelectionCriterion(exists: true))

        when:
        def request = parseResponseXml(espd)
        def idx = 0

        then: "CriterionID element"
        request.Criterion.size() == 1
        checkCriterionId(request, idx, "7604bd40-4462-4086-8763-a50da51a869c")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "SELECTION.ECONOMIC_FINANCIAL_STANDING")

        then: "CriterionName element"
        request.Criterion[idx].Name.text() == "Professional risk indemnity insurance"

        then: "CriterionDescription element"
        request.Criterion[idx].Description.text() == "The insured amount in its professional risk indemnity insurance is the following."

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "58(3)")

        then: "check all the sub groups"
        request.Criterion[idx].RequirementGroup.size() == 2

        then: "main sub group"
        request.Criterion[idx].RequirementGroup[0].ID.text() == "42dc8062-974d-4201-91ba-7f2ea90338fd"
        request.Criterion[idx].RequirementGroup[0].RequirementGroup.size() == 0
        request.Criterion[idx].RequirementGroup[0].Requirement.size() == 2

        then: "main sub group requirements"
        def r1_0 = request.Criterion[idx].RequirementGroup[0].Requirement[0]
        checkRequirement(r1_0, "42db0eaa-d2dd-48cb-83ac-38d73cab9b50", "Amount", "AMOUNT")

        def r1_1 = request.Criterion[idx].RequirementGroup[0].Requirement[1]
        checkRequirement(r1_1, "095c4a57-7f84-4863-a55e-363068d1aaf4", "Currency", "CURRENCY")

        then: "info available electronically sub group"
        checkInfoAvailableElectronicallyRequirementGroup(request.Criterion[idx].RequirementGroup[1])
    }

}