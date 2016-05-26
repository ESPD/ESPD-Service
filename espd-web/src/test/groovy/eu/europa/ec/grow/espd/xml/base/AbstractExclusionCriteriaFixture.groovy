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

package eu.europa.ec.grow.espd.xml.base
/**
 * Created by ratoico on 12/15/15 at 3:53 PM.
 */
class AbstractExclusionCriteriaFixture extends AbstractCriteriaFixture {

    protected static void checkSelfCleaningRequirementGroup(def selfCleaningGroup) {
        assert selfCleaningGroup.ID.text() == "5f9f09f7-f701-432c-9fdc-c22c124a74c9"
        assert selfCleaningGroup.@pi.text() == ""
        assert selfCleaningGroup.Requirement.size() == 1
        assert selfCleaningGroup.RequirementGroup.size() == 1

        def r0 = selfCleaningGroup.Requirement[0]
        checkRequirement(r0, "20c5361b-7599-4ee6-b030-7f8323174d1e", "Have you taken measures to demonstrate your reliability (\"Self-Cleaning\")?", "INDICATOR")
        assert r0.ID.@schemeID.text() == "CriterionRelatedIDs"
        assert r0.ID.@schemeVersionID.text() == "1.0"


        def g1 = selfCleaningGroup.RequirementGroup[0]
        assert g1.Requirement.size() == 1
        assert g1.RequirementGroup.size() == 0
        assert g1.@pi.text() == "GROUP_FULFILLED.ON_TRUE"

        def r1 = g1.Requirement[0]
        checkRequirement(r1, "7b07904f-e080-401a-a3a1-9a3efeeda54b", "Please describe them", "DESCRIPTION")
        assert r1.ID.@schemeID.text() == "CriterionRelatedIDs"
        assert r1.ID.@schemeVersionID.text() == "1.0"
    }

    protected static void checkInfoAvailableElectronicallyRequirementGroup(def infoElectronicallyRequirementGroup) {
        assert infoElectronicallyRequirementGroup.ID.text() == "7458d42a-e581-4640-9283-34ceb3ad4345"
        assert infoElectronicallyRequirementGroup.RequirementGroup.size() == 1
        assert infoElectronicallyRequirementGroup.Requirement.size() == 1
        assert infoElectronicallyRequirementGroup.@pi.text() == ""

        def r0 = infoElectronicallyRequirementGroup.Requirement[0]
        checkRequirement(r0, "c1347b74-1872-4060-a6db-f4044edcd7c4", "Is this information available electronically?", "INDICATOR")
        assert r0.ID.@schemeID.text() == "CriterionRelatedIDs"
        assert r0.ID.@schemeVersionID.text() == "1.0"

        def g1 = infoElectronicallyRequirementGroup.RequirementGroup[0]
        assert g1.ID.text() == "41dd2e9b-1bfd-44c7-93ee-56bd74a4334b"
        assert g1.Requirement.size() == 2
        assert g1.RequirementGroup.size() == 0
        assert g1.@pi.text() == "GROUP_FULFILLED.ON_TRUE"

        def r1 = g1.Requirement[0]
        checkRequirement(r1, "f4313bb6-21b6-499e-bdff-debe10e11d2c", "URL", "EVIDENCE_URL")
        assert r1.ID.@schemeID.text() == "CriterionRelatedIDs"
        assert r1.ID.@schemeVersionID.text() == "1.0"

        def r2 = g1.Requirement[1]
        checkRequirement(r2, "1f1cd18e-3e01-4ca2-af4c-e2981924ba8d", "Code", "CODE")
        assert r2.ID.@schemeID.text() == "CriterionRelatedIDs"
        assert r2.ID.@schemeVersionID.text() == "1.0"
    }

}
