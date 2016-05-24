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
import eu.europa.ec.grow.espd.domain.ubl.CcvLegislation;
import lombok.Getter;

/**
 * Created by vigi on 11/18/15:11:26 AM.
 */
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum LegislationReference implements CcvLegislation {

    /**
     *
     */
    DIRECTIVE_2014_24_EU_57_1(
            "DIRECTIVE 2014/24/EU OF THE EUROPEAN PARLIAMENT AND OF THE COUNCIL of 26 February 2014 on public procurement and repealing Directive 2004/18/EC",
            "Directive 2014/24/EU",
            "http://eur-lex.europa.eu/legal-content/EN/TXT/?uri=celex:32014L0024", "57(1)"),
    /**
     *
     */
    DIRECTIVE_2014_24_EU_57_2(
            "DIRECTIVE 2014/24/EU OF THE EUROPEAN PARLIAMENT AND OF THE COUNCIL of 26 February 2014 on public procurement and repealing Directive 2004/18/EC",
            "Directive 2014/24/EU",
            "http://eur-lex.europa.eu/legal-content/EN/TXT/?uri=celex:32014L0024", "57(2)"),
    /**
     *
     */
    DIRECTIVE_2014_24_EU_57_4(
            "DIRECTIVE 2014/24/EU OF THE EUROPEAN PARLIAMENT AND OF THE COUNCIL of 26 February 2014 on public procurement and repealing Directive 2004/18/EC",
            "Directive 2014/24/EU",
            "http://eur-lex.europa.eu/legal-content/EN/TXT/?uri=celex:32014L0024", "57(4)"),
    /**
     *
     */
    DIRECTIVE_2014_24_EU_58_2(
            "DIRECTIVE 2014/24/EU OF THE EUROPEAN PARLIAMENT AND OF THE COUNCIL of 26 February 2014 on public procurement and repealing Directive 2004/18/EC",
            "Directive 2014/24/EU",
            "http://eur-lex.europa.eu/legal-content/EN/TXT/?uri=celex:32014L0024", "58(2)"),
    /**
     *
     */
    DIRECTIVE_2014_24_EU_58_3(
            "DIRECTIVE 2014/24/EU OF THE EUROPEAN PARLIAMENT AND OF THE COUNCIL of 26 February 2014 on public procurement and repealing Directive 2004/18/EC",
            "On public procurement and repealing Directive 2004/18/EC",
            "http://eur-lex.europa.eu/legal-content/EN/TXT/?uri=celex:32014L0024", "58(3)"),
    /**
     *
     */
    DIRECTIVE_2014_24_EU_58_4(
            "DIRECTIVE 2014/24/EU OF THE EUROPEAN PARLIAMENT AND OF THE COUNCIL of 26 February 2014 on public procurement and repealing Directive 2004/18/EC",
            "On public procurement and repealing Directive 2004/18/EC",
            "http://eur-lex.europa.eu/legal-content/EN/TXT/?uri=celex:32014L0024", "58(4)"),
    /**
     *
     */
    DIRECTIVE_2014_24_EU_62_2(
            "DIRECTIVE 2014/24/EU OF THE EUROPEAN PARLIAMENT AND OF THE COUNCIL of 26 February 2014 on public procurement and repealing Directive 2004/18/EC",
                    "On public procurement and repealing Directive 2004/18/EC",
                    "http://eur-lex.europa.eu/legal-content/EN/TXT/?uri=celex:32014L0024", "62(2)");

    private final String title;

    private final String description;

    private final String url;

    private final String article;

    LegislationReference(String title, String description, String url, String article) {
        this.title = title;
        this.description = description;
        this.url = url;
        this.article = article;
    }

}
