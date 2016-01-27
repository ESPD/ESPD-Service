package eu.europa.ec.grow.espd.xml.response

import eu.europa.ec.grow.espd.domain.EconomicFinancialStandingCriterion
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.SuitabilityCriterion
import eu.europa.ec.grow.espd.xml.base.AbstractCriteriaFixture

/**
 * Created by ratoico on 1/27/16 at 11:54 AM.
 */
class EspdResponseCriteriaTest extends AbstractCriteriaFixture {

    def "criteria which were not selected by the CA (exists is false) should not appear in a ESPD Response"() {
        given:
        def espd = new EspdDocument(enrolmentProfessionalRegister: new SuitabilityCriterion(exists: true),
                enrolmentTradeRegister: new SuitabilityCriterion(exists: false), generalYearlyTurnover: null,
                specificYearlyTurnover: new EconomicFinancialStandingCriterion(exists: true))

        when:
        def result = parseResponseXml(espd)

        then:
        result.Criterion.size() == 2
        checkCriterionId(result, 0, "6ee55a59-6adb-4c3a-b89f-e62a7ad7be7f")
        checkCriterionId(result, 1, "074f6031-55f9-4e99-b9a4-c4363e8bc315")
    }

}