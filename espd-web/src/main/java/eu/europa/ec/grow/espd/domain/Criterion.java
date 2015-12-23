package eu.europa.ec.grow.espd.domain;

import lombok.Data;

@Data
public class Criterion {

    private Boolean exists;

    public boolean getExists() {
        return Boolean.TRUE.equals(exists);
    }

}
