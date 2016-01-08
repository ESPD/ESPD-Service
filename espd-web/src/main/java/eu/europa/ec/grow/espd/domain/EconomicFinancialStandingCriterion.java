package eu.europa.ec.grow.espd.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by ratoico on 1/4/16 at 5:03 PM.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class EconomicFinancialStandingCriterion extends SelectionCriterion implements MultipleAmountsHolder, DescriptionHolder {

    private Integer year1;
    private Integer year2;
    private Integer year3;

    private Double amount1;
    private Double amount2;
    private Double amount3;

    private String currency1;
    private String currency2;
    private String currency3;

    // TODO ratio

    public static EconomicFinancialStandingCriterion buildWithExists(boolean exists) {
        EconomicFinancialStandingCriterion criterion = new EconomicFinancialStandingCriterion();
        criterion.setExists(exists);
        return criterion;
    }

}
