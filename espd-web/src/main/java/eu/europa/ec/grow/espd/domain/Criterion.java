package eu.europa.ec.grow.espd.domain;

import lombok.Data;

@Data
public abstract class Criterion {

    protected Boolean exists;

    protected Boolean answer;

    public boolean getExists() {
        return Boolean.TRUE.equals(exists);
    }

    public abstract Boolean getAnswer();

}
