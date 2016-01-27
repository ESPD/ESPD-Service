package eu.europa.ec.grow.espd.domain;

import lombok.Data;

@Data
public abstract class Criterion {

    private Boolean exists;

    private Boolean answer;

    public boolean getExists() {
        return Boolean.TRUE.equals(exists);
    }

    public boolean getAnswer() {
        return Boolean.TRUE.equals(answer);
    }

}
