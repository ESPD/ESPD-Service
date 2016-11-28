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
 *
 *
 * Created by vigi on 11/23/15:11:49 AM.
 */
public interface CcvCriterionRequirement extends Serializable {

    String getId();

    String getDescription();

    CcvResponseType getResponseType();

	/**
	 * The fields on the parent {@link eu.europa.ec.grow.espd.domain.EspdCriterion} which are mapped to the
	 * requirement. Usually, a requirement is mapped to one field only, but requirements of type AMOUNT are mapped to
	 * an 'amount' and 'currency' fields.
	 * @return
	 */
    List<String> getEspdCriterionFields();
}
