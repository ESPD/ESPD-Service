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

import eu.europa.ec.grow.espd.domain.EspdCriterion;
import eu.europa.ec.grow.espd.domain.ubl.CcvCriterionRequirement;
import eu.europa.ec.grow.espd.domain.ubl.CcvRequirementGroup;
import isa.names.specification.ubl.schema.xsd.ccv_commonaggregatecomponents_1.RequirementType;

/**
 * Creates a UBL {@link RequirementType} from the information coming from ESPD.
 * <p></p>
 * Created by ratoico on 12/22/15 at 3:11 PM.
 */
public abstract class UblRequirementTypeTemplate {

	/**
	 * @param ccvRequirement      The meta definition of the requirement coming from a configuration file or eCertis
	 *                            (in the future)
	 * @param espdCriterion       The criterion containing the user information coming from the ESPD application
	 * @param requirementGroup    The parent requirement group of the requirement
	 * @param unboundedGroupIndex If the group is unbounded, we need to track its current position to be able to fetch
	 *                            its information from the ESPD criterion model
	 *
	 * @return An instance of {@link RequirementType} filled with the user information coming from the ESPD application.
	 */
	protected abstract RequirementType buildRequirementType(CcvCriterionRequirement ccvRequirement,
			EspdCriterion espdCriterion, CcvRequirementGroup requirementGroup, int unboundedGroupIndex);
}
