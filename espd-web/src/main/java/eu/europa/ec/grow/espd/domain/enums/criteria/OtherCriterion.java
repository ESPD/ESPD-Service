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

package eu.europa.ec.grow.espd.domain.enums.criteria;

import com.fasterxml.jackson.annotation.JsonFormat;
import eu.europa.ec.grow.espd.domain.ubl.CcvCriterion;
import eu.europa.ec.grow.espd.domain.ubl.CcvRequirementGroup;
import eu.europa.ec.grow.espd.domain.ubl.CcvCriterionType;
import eu.europa.ec.grow.espd.domain.ubl.CcvLegislation;
import lombok.Getter;

import java.util.List;

/**
 * Created by ratoico on 1/15/16 at 4:11 PM.
 */
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum OtherCriterion implements CcvCriterion {

    /**
     *
     */
    PROCUREMENT_RESERVED("2043338f-a38a-490b-b3ec-2607cb25a017",
            "Procurement reserved",
            "Only in case the procurement is reserved: is the economic operator a sheltered workshop, a 'social business' or will it provide for the performance of the contract in the context of sheltered employment programmes?", OtherCriterionTypeCode.DATA_ON_ECONOMIC_OPERATOR, null, ListUtil
            .list(OtherRequirementGroup.PROCUREMENT_RESERVED_GROUP), "procurementReserved"),
    EO_REGISTERED("9b19e869-6c89-4cc4-bd6c-ac9ca8602165"),
    /**
     *
     */
    EO_PARTICIPATING_PROCUREMENT_PROCEDURE("ee51100f-8e3e-40c9-8f8b-57d5a15be1f2",
            "EO participating in procurement procedure",
            "Is the economic operator participating in the procurement procedure together with others?", OtherCriterionTypeCode.DATA_ON_ECONOMIC_OPERATOR, null,
            ListUtil.list(OtherRequirementGroup.EO_PARTICIPATING_PROCUREMENT_PROCEDURE_GROUP), "eoParticipatingProcurementProcedure"),
    /**
     *
     */
    EO_RELIES_CAPACITIES("0d62c6ed-f074-4fcf-8e9f-f691351d52ad",
            "EO relies capacities",
            "Does the economic operator rely on the capacities of other entities in order to meet the selection criteria set out under Part IV and the criteria and rules (if any) set out under Part V below?", OtherCriterionTypeCode.DATA_ON_ECONOMIC_OPERATOR, null, ListUtil
            .list(OtherRequirementGroup.EO_RELIES_CAPACITIES_GROUP), "eoReliesCapacities"),
    /**
     *
     */
    SUBCONTRACTING_THIRD_PARTIES("72c0c4b1-ca50-4667-9487-461f3eed4ed7",
            "Subcontracting third parties",
            "Does the economic operator intend to subcontract any share of the contract to third parties?",
            OtherCriterionTypeCode.DATA_ON_ECONOMIC_OPERATOR, null,
            ListUtil.list(OtherRequirementGroup.SUBCONTRACTING_THIRD_PARTIES_GROUP), "subcontractingThirdParties"),
    /**
     *
     */
    MEETS_OBJECTIVE("9c70375e-1264-407e-8b50-b9736bc08901",
            "It meets the objective and non discriminatory criteria or rules to be applied in order to limit the number of candidates in the following way: In case certain certificates or other forms of documentary evidence are required, please indicate for each whether the economic operator has the required documents:",
            "If some of these certificates or forms of documentary evidence are available electronically, please indicate for each:",
            OtherCriterionTypeCode.REDUCTION_OF_CANDIDATES, null,
            ListUtil.list(OtherRequirementGroup.MEETS_OBJECTIVE_GROUP, OtherRequirementGroup.INFO_ELECTRONICALLY_GROUP), "meetsObjective"),
    ;

    private final String uuid;

    private final String shortName;

    private final String description;

    private final CcvCriterionType criterionType;

    private final CcvLegislation legislationReference;

    private final List<? extends CcvRequirementGroup> groups;

    private final String espdDocumentField;

    OtherCriterion(String uuid, String shortName, String description,
            OtherCriterionTypeCode criterionTypeCode, LegislationReference legislationReference,
            List<? extends CcvRequirementGroup> groups, String espdDocumentField) {
        this.uuid = uuid;
        this.shortName = shortName;
        this.description = description;
        this.criterionType = criterionTypeCode;
        this.legislationReference = legislationReference;
        this.groups = groups;
        this.espdDocumentField = espdDocumentField;
    }

    OtherCriterion(String uuid) {
        CcvCriterion criterion = CriteriaDeserializer.getOtherCriterion(uuid);
        this.uuid = criterion.getUuid();
        this.shortName = criterion.getName();
        this.description = criterion.getDescription();
        this.criterionType = criterion.getCriterionType();
        this.legislationReference = criterion.getLegislation();
        this.groups = criterion.getGroups();
        this.espdDocumentField = criterion.getEspdDocumentField();
    }

    @Override
    public String getTypeCode() {
        return this.getCriterionType().getTypeName();
    }

    @Override
    public String getName() {
        return getShortName();
    }

    @Override
    public CcvLegislation getLegislation() {
        return getLegislationReference();
    }

    @Override
    public CcvCriterionType getCriterionType() {
        return this.criterionType;
    }

}
