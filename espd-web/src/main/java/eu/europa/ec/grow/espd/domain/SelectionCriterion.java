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

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by vigi on 11/3/15:2:56 PM.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public abstract class SelectionCriterion extends EspdCriterion {

    private String description;

    @Override
    public Boolean getAnswer() {
        if (this.answer == null) {
            // selection criteria with no answer have a default value of TRUE
            return Boolean.TRUE;
        }
        return this.answer;
    }
}
