package eu.europa.ec.grow.espd.business;

import com.google.common.base.Function;
import eu.europa.ec.grow.espd.domain.Criterion;
import eu.europa.ec.grow.espd.domain.EspdDocument;
import eu.europa.ec.grow.espd.entities.CcvCriterion;
import grow.names.specification.ubl.schema.xsd.espdrequest_1.ESPDRequestType;
import grow.names.specification.ubl.schema.xsd.espdresponse_1.ESPDResponseType;
import isa.names.specification.ubl.schema.xsd.ccv_commonaggregatecomponents_1.CriterionResponseType;
import isa.names.specification.ubl.schema.xsd.ccv_commonaggregatecomponents_1.CriterionType;
import isa.names.specification.ubl.schema.xsd.ccv_commonbasiccomponents_1.IndicatorType;

import java.util.List;

/**
 * Abstract class that provides common ways of transforming ESPD Criteria into UBL {@link CriterionType} needed
 * for constructing {@link ESPDRequestType} and {@link ESPDResponseType}.
 * <p/>
 * Created by ratoico on 11/27/15 at 11:04 AM.
 */
abstract class AbstractCriteriaTransformer implements Function<EspdDocument, List<CriterionType>> {

    protected final CcvCriterionToCriterionTypeTransformer ccvCriterionTransformer;

    protected AbstractCriteriaTransformer(CcvCriterionToCriterionTypeTransformer ccvCriterionTransformer) {
        this.ccvCriterionTransformer = ccvCriterionTransformer;
    }

    /**
     * Based on information coming from the ESPD UI, build a UBL {@link CriterionType} which includes
     * the fulfillment indicator.
     *
     * @param criterion     The static criterion definition
     * @param espdCriterion The criterion representation coming from the ESPD UI
     *
     * @return A newly built UBL {@link CriterionType}
     */
    protected final CriterionType buildUblCriterion(CcvCriterion criterion, Criterion espdCriterion) {
        CriterionType ublCriterion = ccvCriterionTransformer.apply(criterion);
        if (isCriterionSelectedInEspd(espdCriterion)) {
            markUblCriterionAsSelected(ublCriterion, true);
        }
        return ublCriterion;
    }

    /**
     * See if an ESPD criterion was selected in the UI.
     *
     * @param espdCriterion The ESPD criterion representation
     *
     * @return The selection status in the UI
     */
    protected final boolean isCriterionSelectedInEspd(Criterion espdCriterion) {
        return espdCriterion != null && espdCriterion.getExists();
    }

    /**
     * Set the fulfillment indicator of the UBL {@link CriterionType}.
     *
     * @param ublCriterion The UBL criterion
     * @param selected     If it is fulfilled or not
     */
    protected final void markUblCriterionAsSelected(CriterionType ublCriterion, boolean selected) {
        CriterionResponseType responseType = new CriterionResponseType();
        IndicatorType selectionIndicator = new IndicatorType();
        selectionIndicator.setValue(selected);
        responseType.setCriterionFulfillmentIndicator(selectionIndicator);
//        ublCriterion.getCriterionResponse().add(responseType);
    }
}
