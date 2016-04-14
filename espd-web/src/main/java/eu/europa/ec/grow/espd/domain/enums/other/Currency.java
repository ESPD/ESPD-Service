package eu.europa.ec.grow.espd.domain.enums.other;

import lombok.Getter;

/**
 * Created by ratoico on 1/14/16 at 4:31 PM.
 */
@Getter
public enum Currency {

    EUR("Euro"),
    ALL("Albanian lek"),
    AMD("Armenian dram"),
    AZN("Azerbaijani manat"),
    BAM("Bosnian convertible mark"),
    BGN("Bulgarian lev"),
    BYR("Belarusian ruble"),
    CHF("Swiss franc"),
    CZK("Czech koruna"),
    DKK("Danish krone"),
    GBP("pound sterling"),
    GEL("Georgian lari"),
    HRK("Croatian kuna"),
    HUF("Hungarian forint"),
    ISK("Icelandic króna"),
    MDL("Moldovan krone"),
    PLN("Polish zloty"),
    RON("New Romanian leu"),
    RSD("Serbian dinar"),
    RUB("Russian ruble"),
    SEK("Swedish krona"),
    TRY("Turkish lira"),
    UAH("Ukrainian hryvnia"),
    USD("US dollar");

    private final String description;

    Currency(String description) {
        this.description = description;
    }
}
