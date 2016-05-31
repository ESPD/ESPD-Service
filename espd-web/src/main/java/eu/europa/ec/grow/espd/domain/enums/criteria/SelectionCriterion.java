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
import com.fasterxml.jackson.annotation.JsonIgnore;
import eu.europa.ec.grow.espd.domain.ubl.CcvCriterion;
import eu.europa.ec.grow.espd.domain.ubl.CcvCriterionType;
import eu.europa.ec.grow.espd.domain.ubl.CcvLegislation;
import eu.europa.ec.grow.espd.domain.ubl.CcvRequirementGroup;
import lombok.Getter;

import java.util.List;

/**
 * Created by vigi on 11/17/15:3:11 PM.
 */
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum SelectionCriterion implements CcvCriterion {

    ALL_SELECTION_CRITERIA_SATISFIED("7e7db838-eeac-46d9-ab39-42927486f22d"),
    ENROLMENT_PROFESSIONAL_REGISTER("6ee55a59-6adb-4c3a-b89f-e62a7ad7be7f"),
    ENROLMENT_TRADE_REGISTER("87b3fa26-3549-4f92-b8e0-3fd8f04bf5c7"),
    SERVICE_CONTRACTS_AUTHORISATION("9eeb6d5c-0eb8-48e8-a4c5-5087a7c095a4"),
    SERVICE_CONTRACTS_MEMBERSHIP("73f10e36-ed7a-412e-995c-aa76463e3776"),
    GENERAL_YEARLY_TURNOVER("499efc97-2ac1-4af2-9e84-323c2ca67747"),
    AVERAGE_YEARLY_TURNOVER("b16cb9fc-6cb7-4585-9302-9533b415cf48"),
    SPECIFIC_YEARLY_TURNOVER("074f6031-55f9-4e99-b9a4-c4363e8bc315"),
    SPECIFIC_AVERAGE_TURNOVER("d3dfb714-f558-4512-bbc5-e456fa2339de"),
    SETUP_ECONOMIC_OPERATOR("77f481ce-ffb6-483f-8e2b-c78db5e68292"),
    FINANCIAL_RATIO("e4d37adc-08cd-4f4d-a8d8-32b62b0a1f46"),
    PROFESSIONAL_RISK_INSURANCE("7604bd40-4462-4086-8763-a50da51a869c"),
    OTHER_ECONOMIC_OR_FINANCIAL_REQUIREMENTS("ab0e7f2e-6418-40e2-8870-6713123e41ad"),
    WORK_CONTRACTS_PERFORMANCE_OF_WORKS("cdd3bb3e-34a5-43d5-b668-2aab86a73822"),
    SUPPLY_CONTRACTS_PERFORMANCE_OF_DELIVERIES("3a18a175-1863-4b1d-baef-588ce61960ca"),
    SERVICE_CONTRACTS_PERFORMANCE_OF_SERVICES("5e506c16-26ab-4e32-bb78-b27f87dc0565"),
    TECHNICIANS_OR_TECHNICAL_BODIES("3aaca389-4a7b-406b-a4b9-080845d127e7"),
    WORK_CONTRACTS_TECHNICIANS_OR_TECHNICAL_BODIES("c599c130-b29f-461e-a187-4e16c7d40db7"),
    TECHNICAL_FACILITIES_AND_MEASURES("4bf996d9-439c-40c6-9ab9-980a48cb55a1"),
    STUDY_AND_RESEARCH_FACILITIES("90a2e100-44cc-45d3-9970-69d6714f1596"),
    SUPPLY_CHAIN_MANAGEMENT("dc12a151-7fdf-4733-a8f0-30f667292e66"),
    ALLOWANCE_OF_CHECKS("c8809aa1-29b6-4f27-ae2f-27e612e394db"),
    EDUCATIONAL_AND_PROFESSIONAL_QUALIFICATIONS("07301031-2270-41af-8e7e-66fe0c777107"),
    ENVIRONMENTAL_MANAGEMENT_FEATURES("9460457e-b43d-48a9-acd1-615de6ddd33e"),
    NUMBER_OF_MANAGERIAL_STAFF("6346959b-e097-4ea1-89cd-d1b4c131ea4d"),
    AVERAGE_ANNUAL_MANPOWER("1f49b3f0-d50f-43f6-8b30-4bafab108b9b"),
    TOOLS_PLANT_TECHNICAL_EQUIPMENT("cc18c023-211d-484d-a32e-52f3f970285f"),
    SUBCONTRACTING_PROPORTION("612a1625-118d-4ea4-a6db-413184e7c0a8"),
    SUPPLY_CONTRACTS_SAMPLES_DESCRIPTIONS_WITHOUT_CA("bdf0601d-2480-4250-b870-658d0ee95be6"),
    SUPPLY_CONTRACTS_SAMPLES_DESCRIPTIONS_WITH_CA("7662b7a9-bcb8-4763-a0a7-7505d8e8470d"),
    SUPPLY_CONTRACTS_CERTIFICATES_QC("a7669d7d-9297-43e1-9d10-691a1660187c"),
    CERTIFICATE_INDEPENDENT_BODIES_ABOUT_QA("d726bac9-e153-4e75-bfca-c5385587766d"),
    CERTIFICATE_INDEPENDENT_BODIES_ABOUT_ENVIRONMENTAL("8ed65e48-fd0d-444f-97bd-4f58da632999");

    private final String uuid;

    private final String shortName;

    private final String description;

    private final CcvCriterionType criterionType;

    private final CcvLegislation legislationReference;

    private final List<? extends CcvRequirementGroup> groups;

    private final String espdDocumentField;

    SelectionCriterion(String uuid) {
        CcvCriterion criterion = CriteriaDeserializer.getSelectionCriterion(uuid);
        this.uuid = criterion.getUuid();
        this.shortName = criterion.getName();
        this.description = criterion.getDescription();
        this.criterionType = criterion.getCriterionType();
        this.legislationReference = criterion.getLegislation();
        this.groups = criterion.getGroups();
        this.espdDocumentField = criterion.getEspdDocumentField();
    }

    @Override
    @JsonIgnore
    public String getTypeCode() {
        return CriterionType.SELECTION.name() + "." + this.getCriterionType().getEspdType();
    }

    @Override
    public String getName() {
        return getShortName();
    }

    @Override
    @JsonIgnore
    public CcvLegislation getLegislation() {
        return legislationReference;
    }

    @Override
    public CcvCriterionType getCriterionType() {
        return this.criterionType;
    }

}
