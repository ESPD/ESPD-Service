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

import eu.europa.ec.grow.espd.domain.EspdDocument;
import eu.europa.ec.grow.espd.domain.SatisfiesAllCriterion;
import eu.europa.ec.grow.espd.xml.common.exporting.UblCriteriaTemplate;
import eu.europa.ec.grow.espd.xml.common.exporting.UblCriterionTypeTemplate;
import grow.names.specification.ubl.schema.xsd.espdrequest_1.ESPDRequestType;
import isa.names.specification.ubl.schema.xsd.ccv_commonaggregatecomponents_1.CriterionType;
import org.springframework.stereotype.Component;

/**
 * <p>
 * Create the UBL {@link CriterionType} list of criteria for a ESPD Request, including exclusion, selection and award
 * criteria.
 * </p>
 * <p><b>The presence of a criterion in a {@link ESPDRequestType} is handled by the 'exists' flag on the {@link EspdDocument} model {@link eu.europa.ec.grow.espd.domain.EspdCriterion}.</b></p>
 * <p/>
 * The criteria need to be present in the {@link ESPDRequestType} in the following way:
 * <ol>
 * <li>All exclusion criteria except 'Purely national grounds' must be present, unless it was selected as well.</li>
 * <li>CA selects "All section criteria" -> The request contains only "All selection criteria" and not the individual ones.</li>
 * <li>CA select individual selection criteria -> The request contains only the selected ones (and even not the "All selection criteria").</li>
 * <li>CA selects no selection criteria at all -> The request contains all the selection criteria (including "All selection criteria").</li>
 * <li>The request contains only one award criterion: "Meets the objective".</li>
 * </o>
 * <p></p>
 * Created by ratoico on 11/26/15 at 5:19 PM.
 */
@Component
class UblRequestCriteriaTransformer extends UblCriteriaTemplate {

    private final UblRequestCriterionTransformer criterionTransformer;

    UblRequestCriteriaTransformer() {
        this.criterionTransformer = new UblRequestCriterionTransformer();
    }

    @Override
    protected UblCriterionTypeTemplate getCriterionTransformer() {
        return this.criterionTransformer;
    }

	@Override
	protected Boolean satisfiesAllCriterionPresent(SatisfiesAllCriterion satisfiesAllCriterion) {
		// if the CA has selected the 'Satisfies all' (which means that the 'exists' property is true)
		// then this implies that the criterion is present on the ESPD Request.
		return isCriterionSelectedByTheCA(satisfiesAllCriterion);
	}

}
