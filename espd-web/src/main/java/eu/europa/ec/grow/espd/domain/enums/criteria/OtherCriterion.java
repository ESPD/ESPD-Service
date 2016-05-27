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

    PROCUREMENT_RESERVED("2043338f-a38a-490b-b3ec-2607cb25a017"),
    EO_REGISTERED("9b19e869-6c89-4cc4-bd6c-ac9ca8602165"),
    EO_PARTICIPATING_PROCUREMENT_PROCEDURE("ee51100f-8e3e-40c9-8f8b-57d5a15be1f2"),
    EO_RELIES_CAPACITIES("0d62c6ed-f074-4fcf-8e9f-f691351d52ad"),
    SUBCONTRACTING_THIRD_PARTIES("72c0c4b1-ca50-4667-9487-461f3eed4ed7"),
    MEETS_OBJECTIVE("9c70375e-1264-407e-8b50-b9736bc08901");

    private final String uuid;

    private final String shortName;

    private final String description;

    private final CcvCriterionType criterionType;

    private final CcvLegislation legislationReference;

    private final List<? extends CcvRequirementGroup> groups;

    private final String espdDocumentField;

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
