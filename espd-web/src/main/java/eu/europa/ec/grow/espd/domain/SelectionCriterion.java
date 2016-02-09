package eu.europa.ec.grow.espd.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by vigi on 11/3/15:2:56 PM.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public abstract class SelectionCriterion extends Criterion {

    private AvailableElectronically availableElectronically;
    private String description;

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

    @Override
    public Boolean getAnswer() {
        if (this.answer == null) {
            // selection criteria with no answer have a default value of TRUE
            return Boolean.TRUE;
        }
        return this.answer;
    }
}
