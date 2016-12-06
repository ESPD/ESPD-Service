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

package eu.europa.ec.grow.espd.domain.infrastructure;

import com.google.common.base.Optional;
import eu.europa.ec.grow.espd.domain.ubl.CcvCriterion;
import eu.europa.ec.grow.espd.domain.ubl.CcvCriterionRequirement;
import eu.europa.ec.grow.espd.domain.ubl.CcvRequirementGroup;
import org.apache.commons.collections.CollectionUtils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Central Utility class for holding ESPD criteria, requirement groups and requirements meta information by using
 * the criterion UUID.
 * <p>
 * Created by ratoico on 5/23/16.
 */
public final class CriterionDefinitions {

	private static final CriterionDefinitions INSTANCE = new CriterionDefinitions();

	static {
		// read the criteria only once to populate the enums
		INSTANCE.mergeCriteriaDefinitions(CriteriaDeserializer.parseJsonFile("exclusionCriteria.json"));
		INSTANCE.mergeCriteriaDefinitions(CriteriaDeserializer.parseJsonFile("selectionCriteria.json"));
		INSTANCE.mergeCriteriaDefinitions(CriteriaDeserializer.parseJsonFile("otherCriteria.json"));
	}

	private final Map<String, CcvCriterion> criteria;

	private final Map<String, CcvRequirementGroup> requirementGroups;

	private final Map<String, CcvCriterionRequirement> requirements;

	CriterionDefinitions() {
		criteria = new HashMap<>();
		requirementGroups = new HashMap<>();
		requirements = new HashMap<>();
	}

	private void mergeCriteriaDefinitions(CriterionDefinitions toMergeWith) {
		criteria.putAll(toMergeWith.criteria);
		requirementGroups.putAll(toMergeWith.requirementGroups);
		requirements.putAll(toMergeWith.requirements);
	}

	void addCriterion(String uuid, CcvCriterion criterion) {
		criteria.put(uuid, criterion);
		indexRequirementGroups(criterion);
	}

	private void indexRequirementGroups(CcvCriterion crit) {
		if (CollectionUtils.isEmpty(crit.getGroups())) {
			return;
		}
		for (CcvRequirementGroup group : crit.getGroups()) {
			indexRequirementGroup(group);
		}
	}

	private void indexRequirementGroup(CcvRequirementGroup group) {
		addRequirementGroup(group.getId(), group);
		if (CollectionUtils.isNotEmpty(group.getRequirements())) {
			for (CcvCriterionRequirement requirement : group.getRequirements()) {
				addRequirement(requirement.getId(), requirement);
			}
		}
		if (CollectionUtils.isEmpty(group.getSubgroups())) {
			return;
		}
		for (CcvRequirementGroup subGroup : group.getSubgroups()) {
			indexRequirementGroup(subGroup);
		}
	}

	private void addRequirementGroup(String uuid, CcvRequirementGroup group) {
		requirementGroups.put(uuid, group);
	}

	/**
	 * Add mappings so that when we lookup requirement groups with old ids we get the new requirement group definitions.
	 *
	 * @param newGroupId The id of the new requirement group which will replace all the old ones
	 * @param oldIds The ids of the old requirement groups to be replaced(updated)
	 */
	void addRequirementGroupMappings(String newGroupId, Collection<String> oldIds) {
		CcvRequirementGroup newGroup = requirementGroups.get(newGroupId);
		if (newGroup != null && CollectionUtils.isNotEmpty(oldIds)) {
			for (String oldId : oldIds) {
				addRequirementGroup(oldId, newGroup);
			}
		}
	}

	private void addRequirement(String uuid, CcvCriterionRequirement requirement) {
		requirements.put(uuid, requirement);
	}

	/**
	 * Add mappings so that when we lookup requirements with old ids we get the new requirement definitions.
	 *
	 * @param newRequirementId The id of the new requirement which will replace all the old ones
	 * @param oldIds The ids of the old requirement to be replaced(updated)
	 */
	void addRequirementMappings(String newRequirementId, Collection<String> oldIds) {
		CcvCriterionRequirement newGroup = requirements.get(newRequirementId);
		if (newGroup != null && CollectionUtils.isNotEmpty(oldIds)) {
			for (String oldId : oldIds) {
				addRequirement(oldId, newGroup);
			}
		}
	}

	public static Optional<CcvCriterion> findCriterionById(String uuid) {
		return Optional.fromNullable(INSTANCE.criteria.get(uuid));
	}

	public static Optional<CcvRequirementGroup> findRequirementGroupById(String uuid) {
		return Optional.fromNullable(INSTANCE.requirementGroups.get(uuid));
	}

	public static Optional<CcvCriterionRequirement> findRequirementById(String uuid) {
		return Optional.fromNullable(INSTANCE.requirements.get(uuid));
	}
}
