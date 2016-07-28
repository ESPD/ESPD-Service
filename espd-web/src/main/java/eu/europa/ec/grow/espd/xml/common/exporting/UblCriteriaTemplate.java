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
import eu.europa.ec.grow.espd.domain.EspdDocument;
import eu.europa.ec.grow.espd.domain.SatisfiesAllCriterion;
import eu.europa.ec.grow.espd.domain.enums.criteria.ExclusionCriterion;
import eu.europa.ec.grow.espd.domain.enums.criteria.OtherCriterion;
import eu.europa.ec.grow.espd.domain.enums.criteria.SelectionCriterion;
import eu.europa.ec.grow.espd.domain.ubl.CcvCriterion;
import isa.names.specification.ubl.schema.xsd.ccv_commonaggregatecomponents_1.CriterionType;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Creates a list of UBL {@link CriterionType} elements to be populated in a ESPD Request or Response.
 * <p/>
 * Created by ratoico on 12/22/15 at 10:33 AM.
 */
@Slf4j
public abstract class UblCriteriaTemplate {

	/**
	 * Builds a list of UBL {@link CriterionType} elements.
	 *
	 * @param espdDocument The input coming from the ESPD application
	 *
	 * @return The list of UBL criteria
	 */
	public List<CriterionType> apply(EspdDocument espdDocument) {
		List<CriterionType> criterionTypes = new ArrayList<>(
				ExclusionCriterion.values().length + SelectionCriterion.values().length + 1);
		// THE ORDER OF CRITERIA IS VERY IMPORTANT AND IT SHOULD BE COVERED BY THE TESTS!!!
		criterionTypes.addAll(addExclusionCriteria(espdDocument));
		criterionTypes.addAll(addSelectionCriteria(espdDocument));
		criterionTypes.addAll(addAwardCriteria(espdDocument));
		return Collections.unmodifiableList(criterionTypes);
	}

	private List<CriterionType> addExclusionCriteria(EspdDocument espdDocument) {
		List<CriterionType> criterionTypes = new ArrayList<>(ExclusionCriterion.values().length + 1);
		// All exclusion criteria except 'Purely national grounds' must be present no matter the existence
		for (ExclusionCriterion criterion : ExclusionCriterion.values()) {
			addSelectedUblCriterion(criterion, espdDocument, criterionTypes);
		}
		return criterionTypes;
	}

	private List<CriterionType> addSelectionCriteria(EspdDocument espdDocument) {

		List<CriterionType> criterionTypes = new ArrayList<>(SelectionCriterion.values().length + 1);
		if (!espdDocument.atLeastOneSelectionCriterionWasSelected()) {
			// Option 3:
			// CA selects no selection criteria -> EO sees all selection criteria (including "All selection criteria")
			for (SelectionCriterion criterion : SelectionCriterion.values()) {
				addAlwaysUblCriterion(criterion, espdDocument, criterionTypes);
			}
		} else if (satisfiesAllCriterionPresent(espdDocument.getSelectionSatisfiesAll())) {
			// Option 1:
			// CA selects "All section criteria" -> EO sees only "All selection criteria" and not the individual ones.
			addSelectedUblCriterion(SelectionCriterion.ALL_SELECTION_CRITERIA_SATISFIED, espdDocument, criterionTypes);
		} else {
			// Option 2:
			// CA select individual selection criteria -> EO sees only the selected ones (and even not the "All selection criteria")
			for (SelectionCriterion criterion : SelectionCriterion.values()) {
				// this will also cover the case when 'Satisfies all' exists with the answer 'No'
				addSelectedUblCriterion(criterion, espdDocument, criterionTypes);
			}
		}
		return criterionTypes;
	}

	private List<CriterionType> addAwardCriteria(EspdDocument espdDocument) {
		List<CriterionType> criterionTypes = new ArrayList<>(OtherCriterion.values().length + 1);
		// All exclusion criteria except 'Purely national grounds' must be present no matter the existence
		for (OtherCriterion criterion : OtherCriterion.values()) {
			addAlwaysUblCriterion(criterion, espdDocument, criterionTypes);
		}
		return criterionTypes;
	}

	/**
	 * Add a UBL criterion only if it was selected (exists) by the CA.
	 *
	 * @param ccvCriterion The criterion metadata
	 * @param espdDocument The model coming from ESPD
	 * @param ublCriteria  The list of UBL criteria on which we add the ESPD criteria
	 */
	private void addSelectedUblCriterion(CcvCriterion ccvCriterion, EspdDocument espdDocument,
			List<CriterionType> ublCriteria) {
		EspdCriterion espdCriterion = espdDocument.readCriterionFromEspd(ccvCriterion);
		if (isCriterionSelectedByTheCA(espdCriterion)) {
			ublCriteria.add(getCriterionTransformer().buildCriterionType(ccvCriterion, espdCriterion));
		}
	}

	protected final boolean isCriterionSelectedByTheCA(EspdCriterion espdCriterion) {
		return espdCriterion != null && espdCriterion.getExists();
	}

	/**
	 * Add a UBL criterion no matter the exists flag (needed by award criteria which always need to be present).
	 *
	 * @param ccvCriterion The criterion metadata
	 * @param ublCriteria  The list of UBL criteria on which we add the ESPD criteria
	 */
	private void addAlwaysUblCriterion(CcvCriterion ccvCriterion, EspdDocument espdDocument,
			List<CriterionType> ublCriteria) {
		EspdCriterion espdCriterion = espdDocument.readCriterionFromEspd(ccvCriterion);
		ublCriteria.add(getCriterionTransformer().buildCriterionType(ccvCriterion, espdCriterion));
	}

	/**
	 * Construct a class that can build an UBL {@link CriterionType}.
	 *
	 * @return An instance of a class that can build {@link CriterionType}
	 */
	protected abstract UblCriterionTypeTemplate getCriterionTransformer();

	/**
	 * The logic to decide if the 'Satisfies all' selection criterion is present. The presence or absence of this
	 * criterion has an impact on the presence or absence of the other selection criteria as follows:
	 * <ol>
	 * <li>CA selects "All section criteria" -> EO sees only "All selection criteria" and not the individual ones;</li>
	 * <li>CA select individual selection criteria -> EO sees only the selected ones (and even not the "All selection criteria");</li>
	 * <li>CA selects no selection criteria -> EO sees all selection criteria (including "All selection criteria").</li>
	 * </ol>
	 *
	 * @param satisfiesAllCriterion The 'Satisfies all' criterion coming from the ESPD web application model
	 *
	 * @return True if the 'Satisfies all' is present according to the logic, false otherwise.
	 */
	protected abstract Boolean satisfiesAllCriterionPresent(SatisfiesAllCriterion satisfiesAllCriterion);

}
