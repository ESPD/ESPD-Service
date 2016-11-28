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

package eu.europa.ec.grow.espd.xml.response.exporting;

import eu.europa.ec.grow.espd.domain.DynamicRequirementGroup;
import eu.europa.ec.grow.espd.domain.EspdCriterion;
import eu.europa.ec.grow.espd.domain.enums.criteria.ExpectedResponseType;
import eu.europa.ec.grow.espd.domain.enums.other.Country;
import eu.europa.ec.grow.espd.domain.intf.UnboundedRequirementGroup;
import eu.europa.ec.grow.espd.domain.ubl.CcvCriterionRequirement;
import eu.europa.ec.grow.espd.domain.ubl.CcvRequirementGroup;
import eu.europa.ec.grow.espd.xml.common.exporting.CommonUblFactory;
import eu.europa.ec.grow.espd.xml.common.exporting.UblRequirementFactory;
import eu.europa.ec.grow.espd.xml.common.exporting.UblRequirementTypeTemplate;
import isa.names.specification.ubl.schema.xsd.ccv_commonaggregatecomponents_1.RequirementType;
import isa.names.specification.ubl.schema.xsd.ccv_commonaggregatecomponents_1.ResponseType;
import lombok.extern.slf4j.Slf4j;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.IDType;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by ratoico on 12/22/15 at 4:07 PM.
 */
@Slf4j
class UblResponseRequirementTransformer extends UblRequirementTypeTemplate {

	@Override
	protected RequirementType buildRequirementType(CcvCriterionRequirement ccvRequirement, EspdCriterion espdCriterion,
			CcvRequirementGroup group, int unboundedGroupIndex) {
		RequirementType requirementType = new RequirementType();

		IDType idType = CommonUblFactory.buildIdType();
		idType.setValue(ccvRequirement.getId());
		idType.setSchemeID("CriterionRelatedIDs");
		idType.setSchemeVersionID("1.0");
		requirementType.setID(idType);

		requirementType.setDescription(UblRequirementFactory.buildDescriptionType(ccvRequirement.getDescription()));

		requirementType.setResponseDataType(ccvRequirement.getResponseType().getCode());

		requirementType.getResponse().add(buildResponse(ccvRequirement, espdCriterion, group, unboundedGroupIndex));

		return requirementType;
	}

	private ResponseType buildResponse(CcvCriterionRequirement ccvRequirement, EspdCriterion espdCriterion,
			CcvRequirementGroup group, int groupIndex) {
		ResponseType responseType = new ResponseType();

		addRequirementValueOnResponse(ccvRequirement, espdCriterion, responseType, group, groupIndex);

		return responseType;
	}

	private void addRequirementValueOnResponse(CcvCriterionRequirement ccvRequirement, EspdCriterion espdCriterion,
			ResponseType responseType, CcvRequirementGroup group, int groupIndex) {
		if (espdCriterion == null) {
			return;
		}

		ExpectedResponseType type = (ExpectedResponseType) ccvRequirement.getResponseType();
		switch (type) {
		case INDICATOR:
			Boolean answer = readRequirementFirstValue(ccvRequirement, espdCriterion, group, groupIndex);
			if (answer != null) {
				responseType.setIndicator(UblRequirementFactory.buildIndicatorType(answer));
			}
			break;
		case DATE:
			Date date = readRequirementFirstValue(ccvRequirement, espdCriterion, group, groupIndex);
			responseType.setDate(UblRequirementFactory.buildDateType(date));
			break;
		case DESCRIPTION:
			String description = readRequirementFirstValue(ccvRequirement, espdCriterion, group, groupIndex);
			responseType.setDescription(UblRequirementFactory.buildDescriptionType(description));
			break;
		case EVIDENCE_URL:
			String url = readRequirementFirstValue(ccvRequirement, espdCriterion, group, groupIndex);
			responseType.getEvidence().add(UblRequirementFactory.buildEvidenceType(url));
			break;
		case QUANTITY:
			BigDecimal quantity = readRequirementFirstValue(ccvRequirement, espdCriterion, group, groupIndex);
			responseType.setQuantity(UblRequirementFactory.buildQuantityType(quantity));
			break;
		case QUANTITY_YEAR:
			Integer year = readRequirementFirstValue(ccvRequirement, espdCriterion, group, groupIndex);
			responseType.setQuantity(UblRequirementFactory.buildYearType(year));
			break;
		case QUANTITY_INTEGER:
			Integer value = readRequirementFirstValue(ccvRequirement, espdCriterion, group, groupIndex);
			responseType.setQuantity(UblRequirementFactory.buildQuantityIntegerType(value));
			break;
		case AMOUNT:
			BigDecimal amount = readRequirementFirstValue(ccvRequirement, espdCriterion, group, groupIndex);
			String currency = readRequirementSecondValue(ccvRequirement, espdCriterion, group, groupIndex);
			responseType.setAmount(UblRequirementFactory.buildAmountType(amount, currency));
			break;
		case CODE_COUNTRY:
			Country country = readRequirementFirstValue(ccvRequirement, espdCriterion, group, groupIndex);
			responseType.setCode(UblRequirementFactory.buildCountryType(country));
			break;
		case PERCENTAGE:
			BigDecimal percentage = readRequirementFirstValue(ccvRequirement, espdCriterion, group, groupIndex);
			responseType.setPercent(UblRequirementFactory.buildPercentType(percentage));
			break;
		case PERIOD:
			String period = readRequirementFirstValue(ccvRequirement, espdCriterion, group, groupIndex);
			responseType.setPeriod(UblRequirementFactory.buildPeriodType(period));
			break;
		case CODE:
			String code = readRequirementFirstValue(ccvRequirement, espdCriterion, group, groupIndex);
			responseType.setCode(UblRequirementFactory.buildCodeType(code));
			break;
		default:
			throw new IllegalArgumentException(String.format(
					"Could not save the requirement '%s' with id '%s' and expected response type '%s' on the ESPD Response.",
					ccvRequirement, ccvRequirement.getId(), type));
		}

	}

	private <T> T readRequirementFirstValue(CcvCriterionRequirement requirement, EspdCriterion espdCriterion,
			CcvRequirementGroup group, int groupIndex) {
		// most requirements are mapped to only one ESPD field
		return readRequirementValueAtPosition(requirement, espdCriterion, 0, group, groupIndex);
	}

	private <T> T readRequirementSecondValue(CcvCriterionRequirement requirement, EspdCriterion espdCriterion,
			CcvRequirementGroup group, int groupIndex) {
		// this method is needed by requirements of type AMOUNT which are mapped to two fields (amount and currency)
		return readRequirementValueAtPosition(requirement, espdCriterion, 1, group, groupIndex);
	}

	@SuppressWarnings("unchecked")
	private <T> T readRequirementValueAtPosition(CcvCriterionRequirement requirement, EspdCriterion espdCriterion,
			int position, CcvRequirementGroup group, int groupIndex) {
		if (requirement.getEspdCriterionFields().get(position) == null) {
			// there is one criterion which is not mapped to any field (3a6fefd4-f458-4d43-97fb-0725fce5dce2) financial ratio
			return null;
		}
		try {
			if (group.isUnbounded()) {
				List<DynamicRequirementGroup> unboundedGroups = ((UnboundedRequirementGroup) espdCriterion)
						.getUnboundedGroups();
				if (CollectionUtils.isEmpty(unboundedGroups)) {
					return null;
				}
				return (T) PropertyUtils
						.getProperty(unboundedGroups.get(groupIndex),
								requirement.getEspdCriterionFields().get(position));
			} else {
				// all requirements except the ones representing an AMOUNT are mapped to a single ESPD field
				return (T) PropertyUtils.getProperty(espdCriterion, requirement.getEspdCriterionFields().get(position));
			}
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			log.error(e.getMessage(), e);
			return null;
		}
	}
}
