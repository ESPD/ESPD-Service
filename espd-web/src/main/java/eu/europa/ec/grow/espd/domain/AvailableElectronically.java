package eu.europa.ec.grow.espd.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class AvailableElectronically {

    private Boolean answer;

    private String url;

    private String code;

}
