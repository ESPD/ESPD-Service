package eu.europa.ec.grow.espd.constants.enums;

import lombok.Getter;

/**
 * Created by vigi on 11/17/15:5:29 PM.
 */
@Getter
public enum Agency {

    EU_COM_GROW("EU-COM-GROW", "DG GROW (European Commission)"),
    EU_COM_JUST("EU-COM-JUST", "European Commission, Directorate-General of Justice");

    private final String identifier;

    private final String longName;

    Agency(String identifier, String longName) {
        this.identifier = identifier;
        this.longName = longName;
    }
}
