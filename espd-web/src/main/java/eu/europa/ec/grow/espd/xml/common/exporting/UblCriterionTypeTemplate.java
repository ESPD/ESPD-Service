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

import eu.europa.ec.grow.espd.domain.DynamicRequirementGroup;
import eu.europa.ec.grow.espd.domain.EspdCriterion;
import eu.europa.ec.grow.espd.domain.enums.criteria.CriterionJurisdictionLevel;
import eu.europa.ec.grow.espd.domain.enums.other.Agency;
import eu.europa.ec.grow.espd.domain.intf.UnboundedRequirementGroup;
import eu.europa.ec.grow.espd.domain.ubl.CcvCriterion;
import eu.europa.ec.grow.espd.domain.ubl.CcvCriterionRequirement;
import eu.europa.ec.grow.espd.domain.ubl.CcvRequirementGroup;
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
 * A {@link CriterionType} can have many {@link RequirementGroupType} and {@link RequirementType} elements.
 * A {@link RequirementGroupType} can in turn have other subgroups and requirements of its own.
 * <p/>
 * <p>
 * Certain requirement groups can be virtually unlimited in number but they always start from the definition of a
 * primary group. Their structure is thus cloned but their responses will vary according to the user input.
 * <p>
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
	 * @return A {@link CriterionType} containing all the information coming from an user of the ESPD application.
	 */
	CriterionType buildCriterionType(CcvCriterion ccvCriterion, EspdCriterion espdCriterion) {
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
		IDType idType = CommonUblFactory.buildIdType();
		idType.setValue(input.getUuid());
		idType.setSchemeVersionID(eu.europa.ec.grow.espd.domain.enums.criteria.CriterionType.SCHEME_VERSION_ID);
		idType.setSchemeID(eu.europa.ec.grow.espd.domain.enums.criteria.CriterionType.SCHEME_ID);
		criterionType.setID(idType);
	}

	private void addTypeCode(CcvCriterion input, CriterionType criterionType) {
		TypeCodeType typeCodeType = new TypeCodeType();
		typeCodeType.setValue(input.getCriterionType().getCode());
		typeCodeType.setListAgencyID(Agency.EU_COM_GROW.getIdentifier());
		typeCodeType.setListID(eu.europa.ec.grow.espd.domain.enums.criteria.CriterionType.LIST_ID);
		typeCodeType.setListVersionID("1.0.2");
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
		jurisdictionLevelCode.setListVersionID("1.0.2");
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
			if (group.isUnbounded() && espdCriterion instanceof UnboundedRequirementGroup) {
				List<DynamicRequirementGroup> unboundedGroups = ((UnboundedRequirementGroup) espdCriterion)
						.getUnboundedGroups();
				if (isEmpty(unboundedGroups)) {
					// if the user did not add values we still need to hold at least the structure of the primary group
					groupTypes.add(buildGroupType(group, espdCriterion, 0));
				} else {
					// we just clone the unbounded groups as needed and fill them with the information coming from the users
					for (int groupIndex = 0; groupIndex < unboundedGroups.size(); groupIndex++) {
						groupTypes.add(buildGroupType(group, espdCriterion, groupIndex));
					}
				}
			} else {
				// just fill in the information for a normal requirement group
				groupTypes.add(buildGroupType(group, espdCriterion, -1));
			}
		}
		criterionType.getRequirementGroup().addAll(groupTypes);
	}

	private RequirementGroupType buildGroupType(CcvRequirementGroup ccvGroup, EspdCriterion espdCriterion,
			int groupIndex) {
		RequirementGroupType groupType = new RequirementGroupType();

		addGroupId(ccvGroup, groupType);
		if (ccvGroup.fulfillmentIndicator() != null) {
			groupType.setPi("GROUP_FULFILLED.ON_" + String.valueOf(ccvGroup.fulfillmentIndicator()).toUpperCase());
		}
		addRequirements(ccvGroup, espdCriterion, groupType, groupIndex);
		addSubGroups(ccvGroup, espdCriterion, groupType, groupIndex);

		return groupType;
	}

	private void addGroupId(CcvRequirementGroup input, RequirementGroupType groupType) {
		IDType idType = CommonUblFactory.buildIdType();
		idType.setSchemeVersionID("1.0");
		idType.setValue(input.getId());
		groupType.setID(idType);
	}

	private void addRequirements(CcvRequirementGroup group, EspdCriterion espdCriterion, RequirementGroupType groupType,
			int groupIndex) {
		if (isEmpty(group.getRequirements())) {
			return;
		}

		List<RequirementType> requirementTypes = new ArrayList<>(group.getRequirements().size() + 1);
		for (CcvCriterionRequirement req : group.getRequirements()) {
			requirementTypes
					.add(ublRequirementTransformerTemplate.buildRequirementType(req, espdCriterion, group, groupIndex));
		}
		groupType.getRequirement().addAll(requirementTypes);
	}

	private void addSubGroups(CcvRequirementGroup ccvGroup, EspdCriterion espdCriterion,
			RequirementGroupType parentGroup, int groupIndex) {
		if (isEmpty(ccvGroup.getSubgroups())) {
			return;
		}

		List<RequirementGroupType> subGroups = new ArrayList<>(parentGroup.getRequirementGroup().size() + 1);
		for (CcvRequirementGroup ccvSubGroup : ccvGroup.getSubgroups()) {
			if (ccvSubGroup.isUnbounded() && espdCriterion instanceof UnboundedRequirementGroup) {
				List<DynamicRequirementGroup> unboundedGroups = ((UnboundedRequirementGroup) espdCriterion).getUnboundedGroups();
				if (isEmpty(unboundedGroups)) {
					// if the user did not add values we still need to hold at least the structure of the primary group
					subGroups.add(buildGroupType(ccvSubGroup, espdCriterion, 0));
				} else {
					// we just clone the unbounded groups as needed and fill them with the information coming from the users
					for (int subGroupIndex = 0; subGroupIndex < unboundedGroups.size(); subGroupIndex++) {
						subGroups.add(buildGroupType(ccvSubGroup, espdCriterion, subGroupIndex));
					}
				}
			}
			else {
				subGroups.add(buildGroupType(ccvSubGroup, espdCriterion, groupIndex));
			}
			
		}
		parentGroup.getRequirementGroup().addAll(subGroups);
		
		
		
		
		
	}

	/**
	 * @return An instance of a class capable of creating {@link RequirementType}.
	 */
	protected abstract UblRequirementTypeTemplate buildRequirementTransformer();

}
