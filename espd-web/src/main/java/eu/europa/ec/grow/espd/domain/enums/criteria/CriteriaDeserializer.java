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
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ratoico on 5/23/16.
 */
final class CriteriaDeserializer extends JsonDeserializer<Criteria> {

    private static final CriteriaDeserializer INSTANCE = new CriteriaDeserializer();
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private static Criteria exclusionCriteria;
    private static Criteria selectionCriteria;
    private static Criteria otherCriteria;

    private CriteriaDeserializer() {
    }

    static {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Criteria.class, INSTANCE);
        MAPPER.registerModule(module);
        // read the criteria only once to populate the enums
        exclusionCriteria = parseJsonFile("exclusionCriteria.json");
        selectionCriteria = parseJsonFile("selectionCriteria.json");
        otherCriteria = parseJsonFile("otherCriteria.json");
    }

    @Override
    public Criteria deserialize(JsonParser jp, DeserializationContext dc)
            throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        ArrayNode critNode = (ArrayNode) node.get("criteria");

        Criteria criteria = new Criteria(critNode.size());
        for (JsonNode nd : critNode) {
            CcvCriterion crit = parseCcvCriterion(nd);
            criteria.put(crit.getUuid(), crit);
        }

        return criteria;
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

    private static Criteria parseJsonFile(String fileName) {
        try (InputStream is = new ClassPathResource("criteria/" + fileName).getInputStream()){
            return MAPPER.readValue(is, Criteria.class);
        } catch (IOException e) {
            throw new IllegalArgumentException(String.format("Could not read JSON file: '%s'.", fileName), e);
        }
    }

    static CcvCriterion getExclusionCriterion(String uuid) {
        return exclusionCriteria.get(uuid);
    }

    static CcvCriterion getSelectionCriterion(String uuid) {
        CcvCriterion criterion = selectionCriteria.get(uuid);
        if (criterion == null) {
            throw new IllegalArgumentException(String.format("The criterion with id: '%s' could not be found.", uuid));
        }
        return criterion;
    }

    static CcvCriterion getOtherCriterion(String uuid) {
        return otherCriteria.get(uuid);
    }
}
