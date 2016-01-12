package eu.europa.ec.grow.espd.domain;

/**
 * Created by ratoico on 1/5/16 at 2:47 PM.
 */
public interface MultipleAmountsHolder {

    Double getAmount1();
    Double getAmount2();
    Double getAmount3();
    Double getAmount4();
    Double getAmount5();

    void setAmount1(Double amount1);
    void setAmount2(Double amount2);
    void setAmount3(Double amount3);
    void setAmount4(Double amount4);
    void setAmount5(Double amount5);

    String getCurrency1();
    String getCurrency2();
    String getCurrency3();
    String getCurrency4();
    String getCurrency5();

    void setCurrency1(String currency1);
    void setCurrency2(String currency2);
    void setCurrency3(String currency3);
    void setCurrency4(String currency4);
    void setCurrency5(String currency5);

}
