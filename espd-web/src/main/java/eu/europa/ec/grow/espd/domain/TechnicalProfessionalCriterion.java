package eu.europa.ec.grow.espd.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * Created by ratoico on 1/5/16 at 1:57 PM.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TechnicalProfessionalCriterion extends SelectionCriterion
        implements MultipleAmountHolder, MultipleDescriptionHolder, DescriptionHolder, MultipleYearHolder {

    private String description1;
    private String description2;
    private String description3;
    private String description4;
    private String description5;

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

    private Date date1;
    private Date date2;
    private Date date3;
    private Date date4;
    private Date date5;

    private String recipients1;
    private String recipients2;
    private String recipients3;
    private String recipients4;
    private String recipients5;

    private Integer year1;
    private Integer year2;
    private Integer year3;

    private Integer number1;
    private Integer number2;
    private Integer number3;

    private Double percentage;
    private String specify;

    public static TechnicalProfessionalCriterion buildWithExists(boolean exists) {
        TechnicalProfessionalCriterion criterion = new TechnicalProfessionalCriterion();
        criterion.setExists(exists);
        return criterion;
    }

    @Override
    public Integer getYear4() {
        return null;
    }

    @Override
    public Integer getYear5() {
        return null;
    }

    @Override
    public void setYear4(Integer year4) {

    }

    @Override
    public void setYear5(Integer year5) {

    }
}
