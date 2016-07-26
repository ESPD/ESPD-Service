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

package eu.europa.ec.grow.espd.xml.request.exporting;

import eu.europa.ec.grow.espd.domain.EspdCriterion;
import eu.europa.ec.grow.espd.domain.ubl.CcvCriterionRequirement;
import eu.europa.ec.grow.espd.xml.common.exporting.CommonUblFactory;
import eu.europa.ec.grow.espd.xml.common.exporting.UblRequirementTypeTemplate;
import isa.names.specification.ubl.schema.xsd.ccv_commonaggregatecomponents_1.RequirementType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.DescriptionType;
import oasis.names.specification.ubl.schema.xsd.commonbasiccomponents_2.IDType;
import org.springframework.stereotype.Component;

/**
 * Created by ratoico on 12/7/15 at 2:25 PM.
 */
@Component
class UblRequestRequirementTransformer extends UblRequirementTypeTemplate {

    @Override
    protected RequirementType buildRequirementType(CcvCriterionRequirement ccvRequirement, EspdCriterion espdCriterion) {
        RequirementType requirementType = new RequirementType();

        IDType idType = CommonUblFactory.buildIdType();
        idType.setValue(ccvRequirement.getId());
        idType.setSchemeID("CriterionRelatedIDs");
        idType.setSchemeVersionID("1.0");
        requirementType.setID(idType);

        DescriptionType descriptionType = new DescriptionType();
        descriptionType.setValue(ccvRequirement.getDescription());
        requirementType.setDescription(descriptionType);

        requirementType.setResponseDataType(ccvRequirement.getResponseType().getCode());

        return requirementType;
    }
}
