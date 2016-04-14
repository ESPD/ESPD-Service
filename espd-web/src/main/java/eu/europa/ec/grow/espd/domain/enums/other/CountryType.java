package eu.europa.ec.grow.espd.domain.enums.other;

import lombok.Getter;

/**
 * Created by vigi on 11/16/15:1:26 PM.
 */
@Getter
public enum CountryType {

    ISO_3166_1("ISO 3166-1"),
    ISO_3166_2("ISO 3166-2");
    
    private String isoType;
    
    CountryType(String isoType) {
    	this.isoType = isoType;
    }
    
}
