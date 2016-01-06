package eu.europa.ec.grow.espd.domain;

import eu.europa.ec.grow.espd.constants.enums.Country;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public abstract class ExclusionCriterion extends Criterion {

    private AvailableElectronically availableElectronically;
    private SelfCleaning selfCleaning;
    private BreachOfObligations breachOfObligations;
    private String description;

    public final String getSelfCleaningDescription() {
        if (selfCleaning != null) {
            return selfCleaning.getDescription();
        }
        return null;
    }

    public final boolean getInfoElectronicallyAnswer() {
        if (availableElectronically != null) {
            return Boolean.TRUE.equals(availableElectronically.getExists());
        }
        return false;
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
        if (selfCleaning != null) {
            return Boolean.TRUE.equals(selfCleaning.getExists());
        }
        return false;
    }

    public abstract Date getDateOfConviction();

    public abstract String getPeriodLength();

    public abstract Country getCountry();

    public abstract Double getAmount();

    public abstract String getCurrency();

    public abstract String getReason();

}
