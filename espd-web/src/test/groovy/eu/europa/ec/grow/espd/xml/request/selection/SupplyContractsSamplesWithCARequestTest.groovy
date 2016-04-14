package eu.europa.ec.grow.espd.xml.request.selection

import eu.europa.ec.grow.espd.domain.enums.criteria.SelectionCriterion
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.TechnicalProfessionalCriterion
import eu.europa.ec.grow.espd.xml.base.AbstractSelectionCriteriaFixture
/**
 * Created by ratoico on 12/9/15 at 1:48 PM.
 */
class SupplyContractsSamplesWithCARequestTest extends AbstractSelectionCriteriaFixture {

    def "30. should contain the 'For supply contracts: samples, descriptions or photographs with certification of authenticity' criterion"() {
        given:
        def espd = new EspdDocument(supplyContractsSamplesDescriptionsWithCa: new TechnicalProfessionalCriterion(exists: true))

        when:
        def request = parseRequestXml(espd)
        def idx = getRequestCriterionIndex(SelectionCriterion.SUPPLY_CONTRACTS_SAMPLES_DESCRIPTIONS_WITH_CA)

        then: "CriterionID element"
        checkCriterionId(request, idx, "7662b7a9-bcb8-4763-a0a7-7505d8e8470d")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "SELECTION.TECHNICAL_PROFESSIONAL_ABILITY")

        then: "CriterionName element"
        request.Criterion[idx].Name.text() == "For supply contracts: samples, descriptions or photographs with certification of authenticity"

        then: "CriterionDescription element"
        request.Criterion[idx].Description.text() == "For public supply contracts: The economic operator will supply the required samples, descriptions or photographs of the products to be supplied and will provide certifications of authenticity where applicable."

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "58(4)")

        then: "check all the sub groups"
        request.Criterion[idx].RequirementGroup.size() == 2

        then: "main sub group"
        request.Criterion[idx].RequirementGroup[0].ID.text() == "511ddbf6-2c53-4fea-a469-3edc9941e603"
        request.Criterion[idx].RequirementGroup[0].RequirementGroup.size() == 0
        request.Criterion[idx].RequirementGroup[0].Requirement.size() == 1

        then: "main sub group requirements"
        def r1_0 = request.Criterion[idx].RequirementGroup[0].Requirement[0]
        checkRequirement(r1_0, "15335c12-ad77-4728-b5ad-3c06a60d65a4", "Your answer?", "INDICATOR")

        then: "info available electronically sub group"
        checkInfoAvailableElectronicallyRequirementGroup(request.Criterion[idx].RequirementGroup[1])
    }

}