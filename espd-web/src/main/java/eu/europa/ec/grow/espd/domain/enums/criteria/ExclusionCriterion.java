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
import eu.europa.ec.grow.espd.domain.infrastructure.CriterionDefinitions;
import eu.europa.ec.grow.espd.domain.ubl.CcvCriterion;
import eu.europa.ec.grow.espd.domain.ubl.CcvCriterionType;
import eu.europa.ec.grow.espd.domain.ubl.CcvLegislation;
import eu.europa.ec.grow.espd.domain.ubl.CcvRequirementGroup;
import lombok.Getter;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import static java.util.Collections.unmodifiableSet;

/**
 * Created by vigi on 11/17/15:2:34 PM.
 */
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ExclusionCriterion implements CcvCriterion {

    PARTICIPATION_CRIMINAL_ORGANISATION("005eb9ed-1347-4ca3-bb29-9bc0db64e1ab"),
    CORRUPTION("c27b7c4e-c837-4529-b867-ed55ce639db5"),
    FRAUD("297d2323-3ede-424e-94bc-a91561e6f320"),
    TERRORIST_OFFENCES("d486fb70-86b3-4e75-97f2-0d71b5697c7d"),
    MONEY_LAUNDERING("47112079-6fec-47a3-988f-e561668c3aef"),
    CHILD_LABOUR("d789d01a-fe03-4ccd-9898-73f9cfa080d1"),
    PAYMENT_OF_TAXES("b61bbeb7-690e-4a40-bc68-d6d4ecfaa3d4"),
    PAYMENT_OF_SOCIAL_SECURITY("7d85e333-bbab-49c0-be8d-c36d71a72f5e"),
    BREACHING_OF_OBLIGATIONS_ENVIRONMENTAL("a80ddb62-d25b-4e4e-ae22-3968460dc0a9"),
    BREACHING_OF_OBLIGATIONS_SOCIAL("a261a395-ed17-4939-9c75-b9ff1109ca6e"),
    BREACHING_OF_OBLIGATIONS_LABOUR("a34b70d6-c43d-4726-9a88-8e2b438424bf"),
    BANKRUPTCY("d3732c09-7d62-4edc-a172-241da6636e7c"),
    INSOLVENCY("396f288a-e267-4c20-851a-ed4f7498f137"),
    ARRANGEMENT_WITH_CREDITORS("68918c7a-f5bc-4a1a-a62f-ad8983600d48"),
    ANALOGOUS_SITUATION("daffa2a9-9f8f-4568-8be8-7b8bf306d096"),
    ASSETS_ADMINISTERED_BY_LIQUIDATOR("8fda202a-0c37-41bb-9d7d-de3f49edbfcb"),
    BUSINESS_ACTIVITIES_SUSPENDED("166536e2-77f7-455c-b018-70582474e4f6"),
    GUILTY_OF_PROFESSIONAL_MISCONDUCT("514d3fde-1e3e-4dcd-b02a-9f984d5bbda3"),
    AGREEMENTS_WITH_OTHER_EO("56d13e3d-76e8-4f23-8af6-13e60a2ee356"),
    CONFLICT_OF_INTEREST_EO_PROCUREMENT_PROCEDURE("b1b5ac18-f393-4280-9659-1367943c1a2e"),
    DIRECT_INVOLVEMENT_PROCUREMENT_PROCEDURE("61874050-5130-4f1c-a174-720939c7b483"),
    EARLY_TERMINATION("3293e92b-7f3e-42f1-bee6-a7641bb04251"),
    GUILTY_OF_MISINTERPRETATION("696a75b2-6107-428f-8b74-82affb67e184"),
    NATIONAL_EXCLUSION_GROUNDS("63adb07d-db1b-4ef0-a14e-a99785cf8cf6");

    private final String uuid;

    private final String shortName;

    private final String description;

    private final CcvCriterionType criterionType;

    private final CcvLegislation legislationReference;

    private final List<? extends CcvRequirementGroup> groups;

    private final String espdDocumentField;

    public static final Set<ExclusionCriterion> ALL_VALUES = unmodifiableSet(EnumSet.allOf(ExclusionCriterion.class));

    ExclusionCriterion(String uuid) {
        CcvCriterion criterion = CriterionDefinitions.findCriterionById(uuid).get();
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
        return CriterionType.EXCLUSION.name() + "." + this.getCriterionType().getEspdType();
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
