package eu.europa.ec.grow.espd.xml.common;

import eu.europa.ec.grow.espd.criteria.enums.ExclusionCriterion;
import eu.europa.ec.grow.espd.criteria.enums.SelectionCriterion;
import eu.europa.ec.grow.espd.domain.Criterion;
import eu.europa.ec.grow.espd.domain.EspdDocument;
import eu.europa.ec.grow.espd.entities.CcvCriterion;
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

    private final UblCriterionTypeTemplate ublCriterionTypeTransformer;

    protected UblCriteriaTemplate() {
        this.ublCriterionTypeTransformer = buildCriterionTypeTransformerTemplate();
    }

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
        criterionTypes.addAll(buildAwardCriteria(espdDocument));
        return Collections.unmodifiableList(criterionTypes);
    }

    private List<CriterionType> addExclusionCriteria(EspdDocument espdDocument) {
        List<CriterionType> criterionTypes = new ArrayList<>(ExclusionCriterion.values().length + 1);
        // All exclusion criteria except 'Purely national grounds' must be present no matter the existence
        for (ExclusionCriterion criterion : ExclusionCriterion.values()) {
            if (ExclusionCriterion.NATIONAL_EXCLUSION_GROUNDS.equals(criterion)) {
                addSelectedUblCriterion(criterion, espdDocument, criterionTypes);
            } else {
                addAlwaysUblCriterion(criterion, espdDocument, criterionTypes);
            }
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
        } else if (isCriterionSelectedByTheCA(espdDocument.getSelectionSatisfiesAll())) {
            // Option 1:
            // CA selects "All section criteria" -> EO sees only "All selection criteria" and not the individual ones.
            addSelectedUblCriterion(SelectionCriterion.ALL_SELECTION_CRITERIA_SATISFIED, espdDocument, criterionTypes);
        } else {
            // Option 2:
            // CA select individual selection criteria -> EO sees only the selected ones (and even not the "All selection criteria")
            for (SelectionCriterion criterion : SelectionCriterion.values()) {
                if (SelectionCriterion.ALL_SELECTION_CRITERIA_SATISFIED.equals(criterion)) {
                    continue;
                }
                addSelectedUblCriterion(criterion, espdDocument, criterionTypes);
            }
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
    protected final void addSelectedUblCriterion(CcvCriterion ccvCriterion, EspdDocument espdDocument,
            List<CriterionType> ublCriteria) {
        Criterion espdCriterion = espdDocument.readCriterionFromEspd(ccvCriterion);
        if (isCriterionSelectedByTheCA(espdCriterion)) {
            ublCriteria.add(ublCriterionTypeTransformer.buildCriterionType(ccvCriterion, espdCriterion));
        }
    }

    private boolean isCriterionSelectedByTheCA(Criterion espdCriterion) {
        return espdCriterion != null && espdCriterion.getExists();
    }

    /**
     * Add a UBL criterion no matter the exists flag (needed by award criteria which always need to be present).
     *
     * @param ccvCriterion The criterion metadata
     * @param ublCriteria  The list of UBL criteria on which we add the ESPD criteria
     */
    protected final void addAlwaysUblCriterion(CcvCriterion ccvCriterion, EspdDocument espdDocument,
            List<CriterionType> ublCriteria) {
        Criterion espdCriterion = espdDocument.readCriterionFromEspd(ccvCriterion);
        ublCriteria.add(ublCriterionTypeTransformer.buildCriterionType(ccvCriterion, espdCriterion));
    }

    /**
     * Construct a class that can build UBL {@link CriterionType}.
     *
     * @return An instance of a class that can build {@link CriterionType}
     */
    protected abstract UblCriterionTypeTemplate buildCriterionTypeTransformerTemplate();

    /**
     * Build the list of economic operator award criteria.
     *
     * @param espdDocument The model containing information coming from the ESPD UI
     *
     * @return The list of economic operator criteria
     */
    protected abstract List<CriterionType> buildAwardCriteria(EspdDocument espdDocument);

}
