package eu.europa.ec.grow.espd.business.common;

import eu.europa.ec.grow.espd.criteria.enums.AwardCriterion;
import eu.europa.ec.grow.espd.criteria.enums.ExclusionCriterion;
import eu.europa.ec.grow.espd.criteria.enums.SelectionCriterion;
import eu.europa.ec.grow.espd.domain.EspdDocument;
import eu.europa.ec.grow.espd.entities.CcvCriterion;
import isa.names.specification.ubl.schema.xsd.ccv_commonaggregatecomponents_1.CriterionType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Given a {@link EspdDocument} and a list of UBL {@link CriterionType}, read the UBL data regarding the
 * criteria and update it on the ESPD document.
 * <p/>
 * Created by ratoico on 1/6/16 at 4:52 PM.
 */
@Component
@Slf4j
public class CriteriaToEspdDocumentPopulator {

    private final EspdResponseCriterionFactory criterionFactory = new EspdResponseCriterionFactory();

    /**
     * Update criteria information on the given ESPD document.
     * <p></p>
     * <b>
     * Please be aware that this method mutates the ESPD document!
     * </b>
     *
     * @param espdDocument The given ESPD document to be updated with criteria information
     * @param ublCriteria  UBL criteria from which we read the information
     */
    public void addCriteriaToEspdDocument(EspdDocument espdDocument, List<CriterionType> ublCriteria) {
        if (CollectionUtils.isEmpty(ublCriteria)) {
            return;
        }

        markSelectedExclusionCriteria(espdDocument, ublCriteria);
        markSelectedSelectionCriteria(espdDocument, ublCriteria);
        markSelectedAwardCriteria(espdDocument, ublCriteria);
    }

    private void markSelectedExclusionCriteria(EspdDocument espdDocument, List<CriterionType> ublCriteria) {
        for (ExclusionCriterion criterion : ExclusionCriterion.values()) {
            setCriterionValueOnEspdModel(espdDocument, ublCriteria, criterion);
        }
    }

    private void markSelectedSelectionCriteria(EspdDocument espdDocument, List<CriterionType> ublCriteria) {
        for (SelectionCriterion criterion : SelectionCriterion.values()) {
            setCriterionValueOnEspdModel(espdDocument, ublCriteria, criterion);
        }
    }

    private void markSelectedAwardCriteria(EspdDocument espdDocument, List<CriterionType> ublCriteria) {
        for (AwardCriterion criterion : AwardCriterion.values()) {
            setCriterionValueOnEspdModel(espdDocument, ublCriteria, criterion);
        }
    }

    private void setCriterionValueOnEspdModel(EspdDocument espdDocument, List<CriterionType> ublCriteria,
            CcvCriterion criterion) {
        try {
            PropertyUtils.setSimpleProperty(espdDocument, criterion.getEspdDocumentField(),
                    criterionFactory.buildEspdCriterion(criterion, ublCriteria));
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            log.error(e.getMessage(), e);
        }
    }

}
