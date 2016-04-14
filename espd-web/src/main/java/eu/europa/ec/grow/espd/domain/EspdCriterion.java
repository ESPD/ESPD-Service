package eu.europa.ec.grow.espd.domain;

import lombok.Data;

@Data
public abstract class EspdCriterion {

    protected Boolean exists;

    protected Boolean answer;

    public boolean getExists() {
        return Boolean.TRUE.equals(exists);
    }

    public abstract Boolean getAnswer();

}
