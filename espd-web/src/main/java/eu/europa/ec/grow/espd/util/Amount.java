package eu.europa.ec.grow.espd.util;

import lombok.Getter;

import java.math.BigDecimal;

/**
 * Created by ratoico on 1/7/16 at 5:09 PM.
 */
@Getter
public final class Amount {

    private final BigDecimal amount;

    private final String currency;

    public Amount(BigDecimal amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }
}
