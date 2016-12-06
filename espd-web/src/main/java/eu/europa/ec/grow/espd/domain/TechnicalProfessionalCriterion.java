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

package eu.europa.ec.grow.espd.domain;

import eu.europa.ec.grow.espd.domain.intf.UnboundedRequirementGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ratoico on 1/5/16 at 1:57 PM.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TechnicalProfessionalCriterion extends SelectionCriterion implements UnboundedRequirementGroup {

	private BigDecimal percentage;
	private String specify;
	private List<DynamicRequirementGroup> unboundedGroups = new ArrayList<>(5);

	public static TechnicalProfessionalCriterion buildWithExists(boolean exists) {
		TechnicalProfessionalCriterion criterion = new TechnicalProfessionalCriterion();
		criterion.setExists(exists);
		return criterion;
	}

	@Override
	public List<DynamicRequirementGroup> getUnboundedGroups() {
		return this.unboundedGroups;
	}

}
