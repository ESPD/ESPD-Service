package eu.europa.ec.grow.espd.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AvailableElectronically extends Criterion {

    @Deprecated
    private String description;

    private String url;

    private String code;

}
