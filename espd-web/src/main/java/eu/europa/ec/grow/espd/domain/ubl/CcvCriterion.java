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

package eu.europa.ec.grow.espd.domain.ubl;

import java.io.Serializable;
import java.util.List;

/**
 * Specific condition that the Economic Operator has to fulfill in order to be considered
 * as a candidate in a procurement process.
 * <p/>
 * Created by vigi on 11/17/15:4:16 PM.
 */
public interface CcvCriterion extends Serializable {

    String getUuid();

    String getTypeCode();

    String getName();

    String getDescription();

    CcvLegislation getLegislation();

    List<? extends CcvRequirementGroup> getGroups();

    CcvCriterionType getCriterionType();

    /**
     * The name of the field in the ESPD model to which the criterion maps.
     *
     * @return
     */
    String getEspdDocumentField();
}
