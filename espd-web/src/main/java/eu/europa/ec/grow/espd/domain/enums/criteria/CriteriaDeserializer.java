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

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.ArrayNode;
import eu.europa.ec.grow.espd.domain.ubl.*;
import lombok.Setter;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Utility class which reads the meta information about all the possible ESPD criteria definitions stored inside
 * JSON files and loaded via classpath.
 * <p>
 * Created by ratoico on 5/23/16.
 */
final class CriteriaDeserializer extends JsonDeserializer<CriteriaDefinitions> {

	private static final CriteriaDeserializer INSTANCE = new CriteriaDeserializer();
	private static final ObjectMapper MAPPER = new ObjectMapper();

	private CriteriaDeserializer() {
	}

	static {
		SimpleModule module = new SimpleModule();
		module.addDeserializer(CriteriaDefinitions.class, INSTANCE);
		MAPPER.registerModule(module);
	}

	static CriteriaDefinitions parseJsonFile(String fileName) {
		try (InputStream is = new ClassPathResource("criteria/" + fileName).getInputStream()) {
			return MAPPER.readValue(is, CriteriaDefinitions.class);
		} catch (IOException e) {
			throw new IllegalArgumentException(String.format("Could not read JSON file: '%s'.", fileName), e);
		}
	}

	@Override
	public CriteriaDefinitions deserialize(JsonParser jp, DeserializationContext dc)
			throws IOException, JsonProcessingException {
		JsonNode node = jp.getCodec().readTree(jp);
		ArrayNode criteriaNode = (ArrayNode) node.get("criteria");

		CriteriaDefinitions criteriaDefinitions = new CriteriaDefinitions();
		for (JsonNode nd : criteriaNode) {
			CcvCriterion crit = parseCcvCriterion(nd);
			criteriaDefinitions.addCriterion(crit.getUuid(), crit);
		}
		addCcvEntityMappings(node, criteriaDefinitions);

		return criteriaDefinitions;
	}

	private CcvCriterion parseCcvCriterion(final JsonNode node) {
		if (nodeHasNoValues(node)) {
			return null;
		}

		final String name = parseStringNode("name", node);
		final String uuid = parseStringNode("uuid", node);
		final String description = parseStringNode("description", node);
		final String espdDocumentField = parseStringNode("espdDocumentField", node);
		final CcvCriterionType ccvCriterionType = parseCriterionTypeCode(node.get("criterionType"));

		return new CcvCriterion() {
			@Override
			public String getUuid() {
				return uuid;
			}

			@Override
			public String getTypeCode() {
				return null;
			}

			@Override
			public String getName() {
				return name;
			}

			@Override
			public String getDescription() {
				return description;
			}

			@Override
			public CcvLegislation getLegislation() {
				return parseLegislation(node.get("legislationReference"));
			}

			@Override
			public List<? extends CcvRequirementGroup> getGroups() {
				ArrayNode subGroupNodes = (ArrayNode) node.get("groups");
				List<CcvRequirementGroup> subgroups = new ArrayList<>(subGroupNodes.size());
				for (JsonNode nd : subGroupNodes) {
					subgroups.add(parseGroup(nd));
				}
				return subgroups;
			}

			@Override
			public CcvCriterionType getCriterionType() {
				return ccvCriterionType;
			}

			@Override
			public String getEspdDocumentField() {
				return espdDocumentField;
			}
		};
	}

	private void addCcvEntityMappings(JsonNode node, CriteriaDefinitions criteriaDefinitions) {
		// Related to https://github.com/ESPD/ESPD-Service/issues/15. We need to still support the old group ids
		// in order to support requirement group and requirement definitions for versions prior to 2016.12 we need
		// to create a mapping between the old ids and the new definitions for the groups and the requirements
		// so when we do a lookup with the old id we get the new definition

		ArrayNode oldRequirementGroupMappingsNode = (ArrayNode) node.get("requirementGroupMappings");
		if (oldRequirementGroupMappingsNode != null) {
			for (JsonNode nd : oldRequirementGroupMappingsNode) {
				OldCcvEntityMapping mapping = parseOldMappings(nd);
				criteriaDefinitions.addRequirementGroupMappings(mapping.replacementId, mapping.idsToBeReplaced);
			}
		}

		ArrayNode oldRequirementMappingsNode = (ArrayNode) node.get("requirementMappings");
		if (oldRequirementMappingsNode != null) {
			for (JsonNode nd : oldRequirementMappingsNode) {
				OldCcvEntityMapping mapping = parseOldMappings(nd);
				criteriaDefinitions.addRequirementMappings(mapping.replacementId, mapping.idsToBeReplaced);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private static OldCcvEntityMapping parseOldMappings(JsonNode node) {
		String replacementId = parseStringNode("replacementId", node);
		ArrayNode idNodes = (ArrayNode) node.get("idsToBeReplaced");
		List<String> idsToBeReplaced = new ArrayList<>(idNodes.size());
		for (JsonNode nd : idNodes) {
			idsToBeReplaced.add(nd.textValue());
		}

		return new OldCcvEntityMapping(replacementId, idsToBeReplaced);
	}

	private static String parseStringNode(String nodeName, JsonNode parentNode) {
		return parentNode.get(nodeName).textValue();
	}

	private static CcvCriterionType parseCriterionTypeCode(final JsonNode parentNode) {
		if (nodeHasNoValues(parentNode)) {
			return null;
		}
		return new CcvCriterionType() {
			@Override
			public String getEspdType() {
				return parseStringNode("espdType", parentNode);
			}

			@Override
			public String getCode() {
				return parseStringNode("code", parentNode);
			}
		};
	}

	private static CcvLegislation parseLegislation(final JsonNode parentNode) {
		if (nodeHasNoValues(parentNode)) {
			return null;
		}
		return new CcvLegislation() {
			@Override
			public String getTitle() {
				return parseStringNode("title", parentNode);
			}

			@Override
			public String getDescription() {
				return parseStringNode("description", parentNode);
			}

			@Override
			public String getUrl() {
				return parseStringNode("url", parentNode);
			}

			@Override
			public String getArticle() {
				return parseStringNode("article", parentNode);
			}
		};
	}

	private static CcvRequirementGroup parseGroup(final JsonNode parentNode) {
		if (nodeHasNoValues(parentNode)) {
			return null;
		}

		return new CcvRequirementGroup() {
			@Override
			public String getId() {
				return parseStringNode("id", parentNode);
			}

			@Override
			public Boolean fulfillmentIndicator() {
				if (parentNode.get("fulfillmentIndicator") == null) {
					return null;
				}
				return parentNode.get("fulfillmentIndicator").asBoolean();
			}

			@Override
			public List<? extends CcvRequirementGroup> getSubgroups() {
				ArrayNode subGroupNodes = (ArrayNode) parentNode.get("subgroups");
				if (subGroupNodes == null) {
					return Collections.emptyList();
				}
				List<CcvRequirementGroup> subgroups = new ArrayList<>(subGroupNodes.size());
				for (JsonNode nd : subGroupNodes) {
					subgroups.add(parseGroup(nd));
				}
				return subgroups;
			}

			@Override
			public boolean isUnbounded() {
				return parentNode.get("unbounded") != null && parentNode.get("unbounded").asBoolean();
			}

			@Override
			public List<? extends CcvCriterionRequirement> getRequirements() {
				ArrayNode requirementNodes = (ArrayNode) parentNode.get("requirements");
				if (nodeHasNoValues(requirementNodes)) {
					return Collections.emptyList();
				}
				List<CcvCriterionRequirement> requirements = new ArrayList<>(requirementNodes.size());
				for (JsonNode nd : requirementNodes) {
					requirements.add(parseRequirement(nd));
				}
				return requirements;
			}
		};
	}

	private static CcvCriterionRequirement parseRequirement(final JsonNode parentNode) {
		if (nodeHasNoValues(parentNode)) {
			return null;
		}
		return new CcvCriterionRequirement() {
			@Override
			public String getId() {
				return parseStringNode("id", parentNode);
			}

			@Override
			public String getDescription() {
				return parseStringNode("description", parentNode);
			}

			@Override
			public CcvResponseType getResponseType() {
				return ExpectedResponseType.valueOf(parseStringNode("responseType", parentNode));
			}

			@Override
			public List<String> getEspdCriterionFields() {
				ArrayNode fieldNodes = (ArrayNode) parentNode.get("espdCriterionFields");
				List<String> fields = new ArrayList<>(fieldNodes.size());
				for (JsonNode nd : fieldNodes) {
					fields.add(nd.textValue());
				}
				return fields;
			}
		};
	}

	private static boolean nodeHasNoValues(JsonNode parentNode) {
		return parentNode == null || parentNode.size() <= 0;
	}

	@Setter
	private static class OldCcvEntityMapping {

		private final String replacementId;

		private final List<String> idsToBeReplaced;

		private OldCcvEntityMapping(String replacementId, List<String> idsToBeReplaced) {
			this.replacementId = replacementId;
			this.idsToBeReplaced = idsToBeReplaced;
		}
	}

}
