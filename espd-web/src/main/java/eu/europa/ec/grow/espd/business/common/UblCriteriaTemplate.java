package eu.europa.ec.grow.espd.business.common;

import eu.europa.ec.grow.espd.criteria.enums.ExclusionCriterion;
import eu.europa.ec.grow.espd.criteria.enums.SelectionCriterion;
import eu.europa.ec.grow.espd.domain.Criterion;
import eu.europa.ec.grow.espd.domain.EspdDocument;
import eu.europa.ec.grow.espd.entities.CcvCriterion;
import isa.names.specification.ubl.schema.xsd.ccv_commonaggregatecomponents_1.CriterionType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.InvocationTargetException;
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
        criterionTypes.addAll(addExclusionCriteria(espdDocument));
        criterionTypes.addAll(addSelectionCriteria(espdDocument));
        criterionTypes.addAll(buildAwardCriteria(espdDocument));
        return Collections.unmodifiableList(criterionTypes);
    }

    private List<CriterionType> addExclusionCriteria(EspdDocument espdDocument) {
        // THE ORDER OF CRITERIA IS VERY IMPORTANT AND IT SHOULD BE COVERED BY THE TESTS
        List<CriterionType> criterionTypes = new ArrayList<>(ExclusionCriterion.values().length + 1);
        for (ExclusionCriterion criterion : ExclusionCriterion.values()) {
            addSelectedUblCriterion(criterion, espdDocument, criterionTypes);
        }
        return criterionTypes;
    }

    private List<CriterionType> addSelectionCriteria(EspdDocument espdDocument) {
        List<CriterionType> criterionTypes = new ArrayList<>(SelectionCriterion.values().length + 1);
        for (SelectionCriterion criterion : SelectionCriterion.values()) {
            addSelectedUblCriterion(criterion, espdDocument, criterionTypes);
        }
        return criterionTypes;
    }

    /**
     * Add a UBL criterion only if it was selected (exists) by the CA.
     *
     * @param ccvCriterion
     * @param ublCriteria
     */
    protected final void addSelectedUblCriterion(CcvCriterion ccvCriterion, EspdDocument espdDocument,
            List<CriterionType> ublCriteria) {
        Criterion espdCriterion = getCriterionFromEspd(ccvCriterion, espdDocument);
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
     * @param ccvCriterion
     * @param ublCriteria
     */
    protected final void addAlwaysUblCriterion(CcvCriterion ccvCriterion, EspdDocument espdDocument,
            List<CriterionType> ublCriteria) {
        Criterion espdCriterion = getCriterionFromEspd(ccvCriterion, espdDocument);
        ublCriteria.add(ublCriterionTypeTransformer.buildCriterionType(ccvCriterion, espdCriterion));
    }

    private Criterion getCriterionFromEspd(CcvCriterion ccvCriterion, EspdDocument espdDocument) {
        try {
            return (Criterion) PropertyUtils.getSimpleProperty(espdDocument, ccvCriterion.getEspdDocumentField());
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            log.error(e.getMessage(), e);
            return null;
        }
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
