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

package eu.europa.ec.grow.espd.domain.enums.criteria;

import com.fasterxml.jackson.annotation.JsonFormat;
import eu.europa.ec.grow.espd.domain.ubl.CcvCriterionType;
import lombok.Getter;

/**
 * Created by ratoico on 1/15/16 at 3:11 PM.
 */
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum OtherCriterionTypeCode implements CcvCriterionType {

    DATA_ON_ECONOMIC_OPERATOR("Date on economic operator"),

    REDUCTION_OF_CANDIDATES("Reduction of the number of qualified candidates");

    private final String description;

    OtherCriterionTypeCode(final String description) {
        this.description = description;
    }

    @Override
    public String getEspdType() {
        return name();
    }

    @Override
    public String getCode() {
        return null;
    }

}
