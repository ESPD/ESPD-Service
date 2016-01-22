package eu.europa.ec.grow.espd.xml.base
import eu.europa.ec.grow.espd.criteria.enums.AwardCriterion
import eu.europa.ec.grow.espd.criteria.enums.ExclusionCriterion
import eu.europa.ec.grow.espd.criteria.enums.SelectionCriterion

/**
 * Created by ratoico on 1/20/16 at 10:49 AM.
 */
class AbstractAwardCriteriaFixture extends AbstractCriteriaFixture {

    protected static int getTotalNumberOfCriteria() {
        return ExclusionCriterion.values().size() + SelectionCriterion.values().size() + AwardCriterion.values().size()
    }

}