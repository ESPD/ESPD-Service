package eu.europa.ec.grow.espd.util;

import lombok.Getter;

/**
 * Created by ratoico on 1/7/16 at 5:09 PM.
 */
@Getter
public final class Amount {

    private final Double amount;

    private final String currency;

    public Amount(Double amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }
}
