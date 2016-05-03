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

package eu.europa.ec.grow.espd.xml.common.exporting;

import eu.europa.ec.grow.espd.domain.EspdCriterion;
import eu.europa.ec.grow.espd.domain.enums.other.Agency;
import eu.europa.ec.grow.espd.domain.enums.criteria.CriterionJurisdictionLevel;
import eu.europa.ec.grow.espd.domain.ubl.CcvCriterion;
import eu.europa.ec.grow.espd.domain.ubl.CcvRequirementGroup;
import eu.europa.ec.grow.espd.domain.ubl.CcvCriterionRequirement;
import isa.names.specification.ubl.schema.xsd.ccv_commonaggregatecomponents_1.CriterionType;
import isa.names.specification.ubl.schema.xsd.ccv_commonaggregatecomponents_1.LegislationType;
import isa.names.specification.ubl.schema.xsd.ccv_commonaggregatecomponents_1.RequirementGroupType;
import isa.names.specification.ubl.schema.xsd.ccv_commonaggregatecomponents_1.RequirementType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.*;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.collections.CollectionUtils.isEmpty;

/**
 * Creates a UBL {@link CriterionType} from the information coming from ESPD.
 * <p/>
 * Created by ratoico on 12/22/15 at 10:27 AM.
 */
public abstract class UblCriterionTypeTemplate {

    private final UblRequirementTypeTemplate ublRequirementTransformerTemplate;

    protected UblCriterionTypeTemplate() {
        this.ublRequirementTransformerTemplate = buildRequirementTransformer();
    }

    /**
     * Creates a UBL {@link CriterionType} from the ESPD criteria.
     *
     * @param ccvCriterion  The meta information concerning a criterion
     * @param espdCriterion The criterion holding the user values
     *
     * @return
     */
    public CriterionType buildCriterionType(CcvCriterion ccvCriterion, EspdCriterion espdCriterion) {
        CriterionType criterionType = new CriterionType();

        addCriterionID(ccvCriterion, criterionType);
        addTypeCode(ccvCriterion, criterionType);
        addName(ccvCriterion, criterionType);
        addDescription(ccvCriterion, criterionType);
        addLegislationReference(ccvCriterion, criterionType);
        addGroups(ccvCriterion, espdCriterion, criterionType);

        return criterionType;
    }

    private void addCriterionID(CcvCriterion input, CriterionType criterionType) {
        IDType idType = new IDType();
        idType.setValue(input.getUuid());
        idType.setSchemeAgencyID(Agency.EU_COM_GROW.getIdentifier());
        idType.setSchemeVersionID(eu.europa.ec.grow.espd.domain.enums.criteria.CriterionType.SCHEME_VERSION_ID);
        idType.setSchemeID(eu.europa.ec.grow.espd.domain.enums.criteria.CriterionType.SCHEME_ID);
        criterionType.setID(idType);
    }

    private void addTypeCode(CcvCriterion input, CriterionType criterionType) {
        TypeCodeType typeCodeType = new TypeCodeType();
        typeCodeType.setValue(input.getTypeCode());
        typeCodeType.setListAgencyID(Agency.EU_COM_GROW.getIdentifier());
        typeCodeType.setListID(eu.europa.ec.grow.espd.domain.enums.criteria.CriterionType.LIST_ID);
        typeCodeType.setListVersionID(eu.europa.ec.grow.espd.domain.enums.criteria.CriterionType.LIST_VERSION_ID);
        criterionType.setTypeCode(typeCodeType);
    }

    private void addName(CcvCriterion input, CriterionType criterionType) {
        NameType nameType = new NameType();
        nameType.setValue(input.getName());
        criterionType.setName(nameType);
    }

    private void addDescription(CcvCriterion input, CriterionType criterionType) {
        DescriptionType descriptionType = new DescriptionType();
        descriptionType.setValue(input.getDescription());
        criterionType.setDescription(descriptionType);
    }

    private void addLegislationReference(CcvCriterion input, CriterionType criterionType) {
        if (input.getLegislation() == null) {
            return;
        }

        LegislationType legislationType = new LegislationType();

        TextType title = new TextType();
        title.setValue(input.getLegislation().getTitle());
        legislationType.setTitle(title);

        DescriptionType description = new DescriptionType();
        description.setValue(input.getLegislation().getDescription());
        legislationType.setDescription(description);

        TypeCodeType jurisdictionLevelCode = new TypeCodeType();
        jurisdictionLevelCode.setValue(CriterionJurisdictionLevel.EU_DIRECTIVE.getCode());
        jurisdictionLevelCode.setListAgencyID(Agency.EU_COM_GROW.getIdentifier());
        jurisdictionLevelCode.setListID(CriterionJurisdictionLevel.LIST_ID);
        jurisdictionLevelCode.setListVersionID(CriterionJurisdictionLevel.LIST_VERSION_ID);
        legislationType.setJurisdictionLevelCode(jurisdictionLevelCode);

        TextType article = new TextType();
        article.setValue(input.getLegislation().getArticle());
        legislationType.setArticle(article);

        URIType uriid = new URIType();
        uriid.setValue(input.getLegislation().getUrl());
        legislationType.setURI(uriid);

        criterionType.getLegislationReference().add(legislationType);
    }

    private void addGroups(CcvCriterion ccvCriterion, EspdCriterion espdCriterion, CriterionType criterionType) {
        if (isEmpty(ccvCriterion.getGroups())) {
            return;
        }

        List<RequirementGroupType> groupTypes = new ArrayList<>(ccvCriterion.getGroups().size() + 1);
        for (CcvRequirementGroup group : ccvCriterion.getGroups()) {
            groupTypes.add(buildGroupType(group, espdCriterion));
        }
        criterionType.getRequirementGroup().addAll(groupTypes);
    }

    private RequirementGroupType buildGroupType(CcvRequirementGroup ccvGroup, EspdCriterion espdCriterion) {
        RequirementGroupType groupType = new RequirementGroupType();

        addGroupId(ccvGroup, groupType);
        addRequirements(ccvGroup, espdCriterion, groupType);
        addSubGroups(ccvGroup, espdCriterion, groupType);

        return groupType;
    }

    private void addGroupId(CcvRequirementGroup input, RequirementGroupType groupType) {
        IDType idType = new IDType();
        idType.setSchemeVersionID("1.0");
        idType.setSchemeAgencyID(Agency.EU_COM_GROW.getIdentifier());
        idType.setValue(input.getId());
        groupType.setID(idType);
    }

    private void addRequirements(CcvRequirementGroup input, EspdCriterion espdCriterion, RequirementGroupType groupType) {
        if (isEmpty(input.getRequirements())) {
            return;
        }

        List<RequirementType> requirementTypes = new ArrayList<>(input.getRequirements().size() + 1);
        for (CcvCriterionRequirement req : input.getRequirements()) {
            requirementTypes.add(ublRequirementTransformerTemplate.buildRequirementType(req, espdCriterion));
        }
        groupType.getRequirement().addAll(requirementTypes);
    }

    private void addSubGroups(CcvRequirementGroup ccvGroup, EspdCriterion espdCriterion, RequirementGroupType parentGroup) {
        if (isEmpty(ccvGroup.getSubgroups())) {
            return;
        }

        List<RequirementGroupType> subGroups = new ArrayList<>(parentGroup.getRequirementGroup().size() + 1);
        for (CcvRequirementGroup ccvSubGroup : ccvGroup.getSubgroups()) {
            subGroups.add(buildGroupType(ccvSubGroup, espdCriterion));
        }
        parentGroup.getRequirementGroup().addAll(subGroups);
    }

    /**
     * @return
     */
    protected abstract UblRequirementTypeTemplate buildRequirementTransformer();

}
