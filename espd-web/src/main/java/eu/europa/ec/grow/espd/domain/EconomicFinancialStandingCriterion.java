package eu.europa.ec.grow.espd.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by ratoico on 1/4/16 at 5:03 PM.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class EconomicFinancialStandingCriterion extends SelectionCriterion
        implements MultipleAmountHolder, MultipleDescriptionHolder, DescriptionHolder, MultipleYearHolder {

    private Integer year1;
    private Integer year2;
    private Integer year3;
    private Integer year4;
    private Integer year5;

    private Double amount1;
    private Double amount2;
    private Double amount3;
    private Double amount4;
    private Double amount5;

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

    private Double ratio1;
    private Double ratio2;
    private Double ratio3;
    private Double ratio4;
    private Double ratio5;

    public static EconomicFinancialStandingCriterion buildWithExists(boolean exists) {
        EconomicFinancialStandingCriterion criterion = new EconomicFinancialStandingCriterion();
        criterion.setExists(exists);
        return criterion;
    }

}
