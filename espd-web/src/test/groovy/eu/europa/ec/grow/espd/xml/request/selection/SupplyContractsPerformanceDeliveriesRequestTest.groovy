package eu.europa.ec.grow.espd.xml.request.selection

import eu.europa.ec.grow.espd.criteria.enums.SelectionCriterion
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.TechnicalProfessionalCriterion
import eu.europa.ec.grow.espd.xml.base.AbstractSelectionCriteriaFixture
/**
 * Created by ratoico on 12/9/15 at 1:48 PM.
 */
class SupplyContractsPerformanceDeliveriesRequestTest extends AbstractSelectionCriteriaFixture {

    def "15. should contain the 'For supply contracts: performance of deliveries of the specified type' criterion"() {
        given:
        def espd = new EspdDocument(supplyContractsPerformanceDeliveries: new TechnicalProfessionalCriterion(exists: true))

        when:
        def request = parseRequestXml(espd)
        def idx = getRequestCriterionIndex(SelectionCriterion.SUPPLY_CONTRACTS_PERFORMANCE_OF_DELIVERIES)

        then: "CriterionID element"
        request.Criterion.size() == getRequestNumberOfCriteria()
        checkCriterionId(request, idx, "3a18a175-1863-4b1d-baef-588ce61960ca")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "SELECTION.TECHNICAL_PROFESSIONAL_ABILITY")

        then: "CriterionName element"
        request.Criterion[idx].Name.text() == "For supply contracts: performance of deliveries of the specified type"

        then: "CriterionDescription element"
        request.Criterion[idx].Description.text() == "For public supply contracts only: During the reference period, the economic operator has delivered the following principal deliveries of the type specified. Contracting authorities may require up to three years and allow experience dating from more than three years."

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "58(4)")

        then: "check all the sub groups"
        request.Criterion[idx].RequirementGroup.size() == 6

        then: "check description amount date recipients"
        checkDescriptionAmountDateRecipientsGroup1(request.Criterion[idx].RequirementGroup[0])
        checkDescriptionAmountDateRecipientsGroup2(request.Criterion[idx].RequirementGroup[1])
        checkDescriptionAmountDateRecipientsGroup3(request.Criterion[idx].RequirementGroup[2])
        checkDescriptionAmountDateRecipientsGroup4(request.Criterion[idx].RequirementGroup[3])
        checkDescriptionAmountDateRecipientsGroup5(request.Criterion[idx].RequirementGroup[4])

        then: "info available electronically sub group"
        checkInfoAvailableElectronicallyRequirementGroup(request.Criterion[idx].RequirementGroup[5])
    }

}