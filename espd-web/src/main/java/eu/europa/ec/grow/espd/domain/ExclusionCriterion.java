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

@Data
@EqualsAndHashCode(callSuper = false)
public abstract class ExclusionCriterion extends EspdCriterion {

    private AvailableElectronically availableElectronically;
    private SelfCleaning selfCleaning;
    private String description;

    public final String getSelfCleaningDescription() {
        if (selfCleaning != null) {
            return selfCleaning.getDescription();
        }
        return null;
    }

    public final boolean getInfoElectronicallyAnswer() {
        return availableElectronically != null && Boolean.TRUE.equals(availableElectronically.getAnswer());
    }

    public final String getInfoElectronicallyUrl() {
        if (availableElectronically != null) {
            return availableElectronically.getUrl();
        }
        return null;
    }

    public final String getInfoElectronicallyCode() {
        if (availableElectronically != null) {
            return availableElectronically.getCode();
        }
        return null;
    }

    public final boolean getSelfCleaningAnswer() {
        return selfCleaning != null && Boolean.TRUE.equals(selfCleaning.getAnswer());
    }

    @Override
    public Boolean getAnswer() {
        if (this.answer == null) {
            // exclusion criteria with no answer have a default value of FALSE
            return Boolean.FALSE;
        }
        return this.answer;
    }

}
