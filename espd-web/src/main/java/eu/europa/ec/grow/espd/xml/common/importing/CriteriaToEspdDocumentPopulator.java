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

package eu.europa.ec.grow.espd.xml.common.importing;

import com.google.common.base.Optional;
import eu.europa.ec.grow.espd.domain.EspdDocument;
import eu.europa.ec.grow.espd.domain.enums.criteria.CriteriaDefinitions;
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
        Optional<CcvCriterion> ccvCriterion = CriteriaDefinitions.findCriterionById(ublCriterion.getID().getValue());
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
