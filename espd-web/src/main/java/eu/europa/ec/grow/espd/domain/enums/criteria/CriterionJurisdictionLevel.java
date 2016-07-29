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

import lombok.Getter;

/**
 * Created by vigi on 11/18/15:11:23 AM.
 */
@Getter
public enum CriterionJurisdictionLevel {


    /**
     *
     */
    EU_REGULATION("EU_REGULATION", "European Regulation"),
    /**
     *
     */
    EU_DIRECTIVE("EU_DIRECTIVE", "European Directive"),
    /**
     *
     */
    EU_DECISION("EU_DECISION", "European Decision"),
    /**
     *
     */
    NATIONAL_LEGISLATION("NATIONAL_LEGISLATION", "National Legislation");

    public static final String LIST_ID = "CriterionJurisdictionLevel";

    private final String code;

    private final String value;

    CriterionJurisdictionLevel(String code, String value) {
        this.code = code;
        this.value = value;
    }
}
