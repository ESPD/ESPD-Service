package eu.europa.ec.grow.espd.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public abstract class ExclusionCriterion extends Criterion {

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
        return availableElectronically != null && Boolean.TRUE.equals(availableElectronically.getExists());
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
        return selfCleaning != null && Boolean.TRUE.equals(selfCleaning.getExists());
    }

}
