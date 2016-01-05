package eu.europa.ec.grow.espd.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * Created by ratoico on 1/5/16 at 1:57 PM.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TechnicalProfessionalCriterion extends SelectionCriterion implements MultipleAmountsHolder, DescriptionHolder {

    private String description1;
    private String description2;
    private String description3;

    private Double amount1;
    private Double amount2;
    private Double amount3;

    private String currency1;
    private String currency2;
    private String currency3;

    private Date date1;
    private Date date2;
    private Date date3;

    private String recipients1;
    private String recipients2;
    private String recipients3;

    private Double percentage;

    @Override
    public String getDescription() {
        return description1;
    }
}
