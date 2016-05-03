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

package eu.europa.ec.grow.espd.xml

import com.opencsv.CSVWriter
import eu.europa.ec.grow.espd.domain.enums.criteria.AwardCriterion
import eu.europa.ec.grow.espd.domain.enums.criteria.ExclusionCriterion
import eu.europa.ec.grow.espd.domain.enums.criteria.SelectionCriterion
import eu.europa.ec.grow.espd.domain.ubl.CcvCriterion
import eu.europa.ec.grow.espd.domain.ubl.CcvCriterionRequirement
import eu.europa.ec.grow.espd.domain.ubl.CcvRequirementGroup
import spock.lang.Specification

/**
 * Created by ratoico on 2/17/16 at 10:15 AM.
 */
class CriteriaListTest extends Specification {

    def "generate csv file" ( ) {
        given:
//        writeCsvFile()

        expect:
        1 == 1
    }

    private static void writeCsvFile() {
        CSVWriter writer = new CSVWriter(new FileWriter("espd.csv"), (char) ',');
        writer.writeNext( ["Criterion name", "Criterion id", "Criterion description", "Criterion code", "Legislation", "Requirement group id", "Requirement", "Requirement id", "Expected data type"].toArray(new String[0]) );
        // feed in your array (or convert your data to an array)
        for (ExclusionCriterion crit : ExclusionCriterion.values()) {
            writeCrit(crit, writer);
        }
        for (SelectionCriterion crit : SelectionCriterion.values()) {
            writeCrit(crit, writer);
        }
        for (AwardCriterion crit : AwardCriterion.values()) {
            writeCrit(crit, writer);
        }

        writer.close();
    }

    private static void writeCrit(CcvCriterion crit, CSVWriter writer) {
        writer.writeNext(criterionEntries(crit));
        for (CcvRequirementGroup group : crit.getGroups()) {
            writer.writeNext(reqGroupEntries(group));
            for (CcvCriterionRequirement req : group.getRequirements()) {
                writer.writeNext(reqEntries(req));
            }
            for (CcvRequirementGroup subgroup : group.getSubgroups()) {
                writer.writeNext(reqGroupEntries(subgroup));
                for (CcvCriterionRequirement req : subgroup.getRequirements()) {
                    writer.writeNext(reqEntries(req));
                }
            }
        }
    }

    private static String[] criterionEntries(CcvCriterion crit) {
        String[] s = new String[5];
        s[0] = crit.getName();
        s[1] = crit.getUuid();
        s[2] = crit.getDescription();
        s[3] = crit.getTypeCode();
        s[4] = crit.getLegislation() != null ? crit.getLegislation().getArticle() : "";
        return s;
    }

    private static String[] reqGroupEntries(CcvRequirementGroup group) {
        String[] s = new String[6];
        s[0] = "";
        s[1] = "";
        s[2] = "";
        s[3] = "";
        s[4] = "";
        s[5] = group.getId();

        return s;
    }

    private static String[] reqEntries(CcvCriterionRequirement req) {
        String[] s = new String[9];
        s[0] = "";
        s[1] = "";
        s[2] = "";
        s[3] = "";
        s[4] = "";
        s[5] = "";
        s[6] = req.getDescription();
        s[7] = req.getId();
        s[8] = req.getResponseType().getCode();

        return s;
    }
}
