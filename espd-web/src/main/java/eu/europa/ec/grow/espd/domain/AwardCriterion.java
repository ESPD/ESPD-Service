package eu.europa.ec.grow.espd.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by ratoico on 1/20/16 at 10:39 AM.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AwardCriterion extends Criterion implements MultipleDescriptionHolder {

    private AvailableElectronically availableElectronically;

    private String description1;
    private String description2;
    private String description3;
    private String description4;
    private String description5;
    private Double doubleValue1;
    private Boolean booleanValue1;

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

    public static AwardCriterion buildWithExists(boolean exists) {
        AwardCriterion criterion = new AwardCriterion();
        criterion.setExists(exists);
        return criterion;
    }

    public Boolean getBooleanValue1() {
        return Boolean.TRUE.equals(this.booleanValue1);
    }
}
