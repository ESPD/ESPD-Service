package eu.europa.ec.grow.espd.xml.response

import eu.europa.ec.grow.espd.criteria.enums.AwardCriterion
import eu.europa.ec.grow.espd.criteria.enums.ExclusionCriterion
import eu.europa.ec.grow.espd.criteria.enums.SelectionCriterion
import eu.europa.ec.grow.espd.domain.*
import eu.europa.ec.grow.espd.xml.base.AbstractCriteriaFixture

/**
 * Created by ratoico on 1/27/16 at 11:54 AM.
 */
class EspdResponseCriteriaTest extends AbstractCriteriaFixture {

    def "should contain mandatory exclusion criteria plus all selection criteria plus all award criteria"() {
        given:
        def espd = new EspdDocument()
        def idx

        when:
        def result = parseResponseXml(espd)

        then:
        result.Criterion.size() == getMandatoryExclusionCriteriaSize() + SelectionCriterion.values().length + AwardCriterion.values().length

        then: "all exclusion criteria are mandatory (except 'purely national'"
        for (ExclusionCriterion criterion : ExclusionCriterion.values()) {
            idx = getRequestCriterionIndex(criterion)
            if (ExclusionCriterion.NATIONAL_EXCLUSION_GROUNDS.equals(criterion)) {
                continue
            }
            checkCriterionId(result, idx, criterion.getUuid())
        }

        then: "all selection criteria must be present since there were none selected"
        for (SelectionCriterion criterion : SelectionCriterion.values()) {
            checkCriterionId(result, idx++, criterion.getUuid())
        }

        then: "all award criteria must be present"
        for (AwardCriterion criterion : AwardCriterion.values()) {
            checkCriterionId(result, idx++, criterion.getUuid())
        }
    }
}