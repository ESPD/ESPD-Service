package eu.europa.ec.grow.espd.domain;

import eu.europa.ec.grow.espd.domain.intf.MultipleAmountHolder;
import eu.europa.ec.grow.espd.domain.intf.MultipleDescriptionHolder;
import eu.europa.ec.grow.espd.domain.intf.MultipleYearHolder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * Created by ratoico on 1/4/16 at 5:03 PM.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class EconomicFinancialStandingCriterion extends SelectionCriterion
        implements MultipleAmountHolder, MultipleDescriptionHolder, MultipleYearHolder {

    private Integer year1;
    private Integer year2;
    private Integer year3;
    private Integer year4;
    private Integer year5;

    private BigDecimal amount1;
    private BigDecimal amount2;
    private BigDecimal amount3;
    private BigDecimal amount4;
    private BigDecimal amount5;

    private String currency1;
    private String currency2;
    private String currency3;
    private String currency4;
    private String currency5;

    private String description1;
    private String description2;
    private String description3;
    private String description4;
    private String description5;

    private BigDecimal ratio1;
    private BigDecimal ratio2;
    private BigDecimal ratio3;
    private BigDecimal ratio4;
    private BigDecimal ratio5;

    public static EconomicFinancialStandingCriterion buildWithExists(boolean exists) {
        EconomicFinancialStandingCriterion criterion = new EconomicFinancialStandingCriterion();
        criterion.setExists(exists);
        return criterion;
    }

}
