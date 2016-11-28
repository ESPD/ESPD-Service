/*
 *
 * Copyright 2016 EUROPEAN COMMISSION
 *
 * Licensed under the EUPL, Version 1.1 or – as soon they
 * will be approved by the European Commission - subsequent
 * versions of the EUPL (the "Licence");
 *
 * You may not use this work except in compliance with the Licence.
 *
 * You may obtain a copy of the Licence at:
 *
 * https://joinup.ec.europa.eu/community/eupl/og_page/eupl
 *
 * Unless required by applicable law or agreed to in
 * writing, software distributed under the Licence is
 * distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied.
 * See the Licence for the specific language governing
 * permissions and limitations under the Licence.
 *
 */

package eu.europa.ec.grow.espd.xml.response.exporting

import eu.europa.ec.grow.espd.domain.DynamicRequirementGroup
import eu.europa.ec.grow.espd.domain.TechnicalProfessionalCriterion
import eu.europa.ec.grow.espd.domain.enums.criteria.ExpectedResponseType
import eu.europa.ec.grow.espd.domain.ubl.CcvCriterionRequirement
import eu.europa.ec.grow.espd.domain.ubl.CcvRequirementGroup
import spock.lang.Specification

/**
 * Created by ratoico on 11/25/16.
 */
class UblResponseRequirementTransformerTest extends Specification {

    UblResponseRequirementTransformer transformer

    void setup() {
        transformer = new UblResponseRequirementTransformer()
    }

    void cleanup() {
        transformer = null
    }


    def "should create a RequirementType element (from a normal bounded group) with data coming from the ESPD model object"() {
        given:
        def ccvRequirement = Mock(CcvCriterionRequirement)
        def group = Mock(CcvRequirementGroup)
        def espdCriterion = new TechnicalProfessionalCriterion(description: "H0d0r")

        when:
        ccvRequirement.getId() >> "51391308-0bf6-423c-95e2-d5a54aa31fb8"
        ccvRequirement.getResponseType() >> ExpectedResponseType.DESCRIPTION
        ccvRequirement.getEspdCriterionFields() >> ["description"]
        group.isUnbounded() >> false
        def result = transformer.buildRequirementType(ccvRequirement, espdCriterion, group, 0)

        then:
        result.ID.value == "51391308-0bf6-423c-95e2-d5a54aa31fb8"
        result.response[0].description.value == "H0d0r"
    }

    def "should create RequirementType elements (from an unbounded group) with data coming from the ESPD model object"() {
        given:
        def ccvRequirement = Mock(CcvCriterionRequirement)
        def group = Mock(CcvRequirementGroup)
        def espdCriterion = new TechnicalProfessionalCriterion(
                unboundedGroups: [new DynamicRequirementGroup("description": "H0d0r0"),
                                     new DynamicRequirementGroup("description": "H0d0r1")])

        when:
        ccvRequirement.getId() >> "ab05ff3b-f3e1-4441-9b43-ee9912e29e92"
        ccvRequirement.getResponseType() >> ExpectedResponseType.DESCRIPTION
        ccvRequirement.getEspdCriterionFields() >> ["description"]
        group.isUnbounded() >> true
        def result1 = transformer.buildRequirementType(ccvRequirement, espdCriterion, group, 0)
        def result2 = transformer.buildRequirementType(ccvRequirement, espdCriterion, group, 1)

        then:
        result1.ID.value == "ab05ff3b-f3e1-4441-9b43-ee9912e29e92"
        result1.response[0].description.value == "H0d0r0"

        then:
        result2.ID.value == "ab05ff3b-f3e1-4441-9b43-ee9912e29e92"
        result2.response[0].description.value == "H0d0r1"
    }
}
