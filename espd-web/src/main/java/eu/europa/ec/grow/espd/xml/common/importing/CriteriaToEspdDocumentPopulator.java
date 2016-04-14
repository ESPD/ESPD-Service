package eu.europa.ec.grow.espd.xml.common.importing;

import com.google.common.base.Optional;
import eu.europa.ec.grow.espd.domain.EspdDocument;
import eu.europa.ec.grow.espd.domain.enums.criteria.CriteriaList;
import eu.europa.ec.grow.espd.domain.ubl.CcvCriterion;
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

        for (CriterionType ublCriterion : ublCriteria) {
            setCriterionValueOnEspdModel(espdDocument, ublCriterion);
        }

        espdDocument.setUblCriteria(ublCriteria);
    }

    private void setCriterionValueOnEspdModel(EspdDocument espdDocument, CriterionType ublCriterion) {
        Optional<CcvCriterion> ccvCriterion = CriteriaList.findById(ublCriterion.getID().getValue());
        if (!ccvCriterion.isPresent()) {
            return;
        }
        try {
            PropertyUtils.setSimpleProperty(espdDocument, ccvCriterion.get().getEspdDocumentField(),
                    criterionFactory.buildEspdCriterion(ccvCriterion.get(), ublCriterion));
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            log.error(e.getMessage(), e);
        }
    }

}
