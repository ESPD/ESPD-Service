package eu.europa.ec.grow.espd.domain;

/**
 * Created by ratoico on 2/29/16 at 9:27 AM.
 */
public class QualityAssuranceCriterion extends SelectionCriterion {

    public static QualityAssuranceCriterion buildWithExists(boolean exists) {
        QualityAssuranceCriterion criterion = new QualityAssuranceCriterion();
        criterion.setExists(exists);
        return criterion;
    }
}
