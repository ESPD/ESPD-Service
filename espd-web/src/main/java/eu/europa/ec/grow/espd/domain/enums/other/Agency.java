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

package eu.europa.ec.grow.espd.domain.enums.other;

import lombok.Getter;

/**
 * Created by vigi on 11/17/15:5:29 PM.
 */
@Getter
public enum Agency {

    EU_COM_GROW("EU-COM-GROW", "DG GROW (European Commission)"),
    EU_COM_JUST("EU-COM-JUST", "European Commission, Directorate-General of Justice");

    private final String identifier;

    private final String longName;

    Agency(String identifier, String longName) {
        this.identifier = identifier;
        this.longName = longName;
    }
}
