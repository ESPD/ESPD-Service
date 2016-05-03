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

import eu.europa.ec.grow.espd.xml.common.exporting.UblCriterionTypeTemplate;
import eu.europa.ec.grow.espd.xml.common.exporting.UblRequirementTypeTemplate;
import isa.names.specification.ubl.schema.xsd.ccv_commonaggregatecomponents_1.CriterionType;

/**
 * Transforms the criterion information coming from ESPD into a {@link CriterionType} object.
 * <p/>
 * Created by vigi on 11/16/15:3:38 PM.
 */
class UblRequestCriterionTransformer extends UblCriterionTypeTemplate {

    @Override
    protected UblRequirementTypeTemplate buildRequirementTransformer() {
        return new UblRequestRequirementTransformer();
    }
}
