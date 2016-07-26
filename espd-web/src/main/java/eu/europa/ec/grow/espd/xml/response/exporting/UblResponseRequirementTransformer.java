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

import eu.europa.ec.grow.espd.domain.EspdCriterion;
import eu.europa.ec.grow.espd.domain.enums.criteria.ExpectedResponseType;
import eu.europa.ec.grow.espd.domain.enums.other.Country;
import eu.europa.ec.grow.espd.domain.ubl.CcvCriterionRequirement;
import eu.europa.ec.grow.espd.xml.common.exporting.CommonUblFactory;
import eu.europa.ec.grow.espd.xml.common.exporting.UblRequirementFactory;
import eu.europa.ec.grow.espd.xml.common.exporting.UblRequirementTypeTemplate;
import isa.names.specification.ubl.schema.xsd.ccv_commonaggregatecomponents_1.RequirementType;
import isa.names.specification.ubl.schema.xsd.ccv_commonaggregatecomponents_1.ResponseType;
import lombok.extern.slf4j.Slf4j;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.IDType;
import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by ratoico on 12/22/15 at 4:07 PM.
 */
@Slf4j
class UblResponseRequirementTransformer extends UblRequirementTypeTemplate {

    @Override
    protected RequirementType buildRequirementType(CcvCriterionRequirement ccvRequirement, EspdCriterion espdCriterion) {
        RequirementType requirementType = new RequirementType();

        IDType idType = CommonUblFactory.buildIdType();
        idType.setValue(ccvRequirement.getId());
        idType.setSchemeID("CriterionRelatedIDs");
        idType.setSchemeVersionID("1.0");
        requirementType.setID(idType);

        requirementType.setDescription(UblRequirementFactory.buildDescriptionType(ccvRequirement.getDescription()));

        requirementType.setResponseDataType(ccvRequirement.getResponseType().getCode());

        requirementType.getResponse().add(buildResponse(ccvRequirement, espdCriterion));

        return requirementType;
    }

    private ResponseType buildResponse(CcvCriterionRequirement ccvRequirement, EspdCriterion espdCriterion) {
        ResponseType responseType = new ResponseType();

        addRequirementValueOnResponse(ccvRequirement, espdCriterion, responseType);

        return responseType;
    }

    private void addRequirementValueOnResponse(CcvCriterionRequirement ccvRequirement, EspdCriterion espdCriterion,
            ResponseType responseType) {
        if (espdCriterion == null) {
            return;
        }

        ExpectedResponseType type = (ExpectedResponseType) ccvRequirement.getResponseType();
        switch (type) {
        case INDICATOR:
            Boolean answer = readRequirementFirstValue(ccvRequirement, espdCriterion);
            if (answer != null) {
                responseType.setIndicator(UblRequirementFactory.buildIndicatorType(answer));
            }
            break;
        case DATE:
            Date date = readRequirementFirstValue(ccvRequirement, espdCriterion);
            responseType.setDate(UblRequirementFactory.buildDateType(date));
            break;
        case DESCRIPTION:
            String description = readRequirementFirstValue(ccvRequirement, espdCriterion);
            responseType.setDescription(UblRequirementFactory.buildDescriptionType(description));
            break;
        case EVIDENCE_URL:
            String url = readRequirementFirstValue(ccvRequirement, espdCriterion);
            responseType.getEvidence().add(UblRequirementFactory.buildEvidenceType(url));
            break;
        case QUANTITY:
            BigDecimal quantity = readRequirementFirstValue(ccvRequirement, espdCriterion);
            responseType.setQuantity(UblRequirementFactory.buildQuantityType(quantity));
            break;
        case QUANTITY_YEAR:
            Integer year = readRequirementFirstValue(ccvRequirement, espdCriterion);
            responseType.setQuantity(UblRequirementFactory.buildYearType(year));
            break;
        case QUANTITY_INTEGER:
            Integer value = readRequirementFirstValue(ccvRequirement, espdCriterion);
            responseType.setQuantity(UblRequirementFactory.buildQuantityIntegerType(value));
            break;
        case AMOUNT:
            BigDecimal amount = readRequirementFirstValue(ccvRequirement, espdCriterion);
            String currency = readRequirementSecondValue(ccvRequirement, espdCriterion);
            responseType.setAmount(UblRequirementFactory.buildAmountType(amount, currency));
            break;
        case CODE_COUNTRY:
            Country country = readRequirementFirstValue(ccvRequirement, espdCriterion);
            responseType.setCode(UblRequirementFactory.buildCountryType(country));
            break;
        case PERCENTAGE:
            BigDecimal percentage = readRequirementFirstValue(ccvRequirement, espdCriterion);
            responseType.setPercent(UblRequirementFactory.buildPercentType(percentage));
            break;
        case PERIOD:
            String period = readRequirementFirstValue(ccvRequirement, espdCriterion);
            responseType.setPeriod(UblRequirementFactory.buildPeriodType(period));
            break;
        case CODE:
            String code = readRequirementFirstValue(ccvRequirement, espdCriterion);
            responseType.setCode(UblRequirementFactory.buildCodeType(code));
            break;
        default:
            throw new IllegalArgumentException(String.format(
                    "Could not save the requirement '%s' with id '%s' and expected response type '%s' on the ESPD Response.",
                    ccvRequirement, ccvRequirement.getId(), type));
        }

    }

    private <T> T readRequirementFirstValue(CcvCriterionRequirement requirement, EspdCriterion espdCriterion) {
        // most requirements are mapped to only one ESPD field
        return readRequirementValueAtPosition(requirement, espdCriterion, 0);
    }

    private <T> T readRequirementSecondValue(CcvCriterionRequirement requirement, EspdCriterion espdCriterion) {
        // this method is needed by requirements of type AMOUNT which are mapped to two fields (amount and currency)
        return readRequirementValueAtPosition(requirement, espdCriterion, 1);
    }

    @SuppressWarnings("unchecked")
    private <T> T readRequirementValueAtPosition(CcvCriterionRequirement requirement, EspdCriterion espdCriterion,
            int position) {
        if (requirement.getEspdCriterionFields().get(position) == null) {
            // there is one criterion which is not mapped to any field (3a6fefd4-f458-4d43-97fb-0725fce5dce2) financial ratio
            return null;
        }
        try {
            // all requirements except the ones representing an AMOUNT are mapped to a single ESPD field
            return (T) PropertyUtils.getProperty(espdCriterion, requirement.getEspdCriterionFields().get(position));
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }
}
