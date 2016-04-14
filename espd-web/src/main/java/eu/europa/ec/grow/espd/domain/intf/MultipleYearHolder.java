package eu.europa.ec.grow.espd.domain.intf;

/**
 * Created by ratoico on 1/13/16 at 10:04 AM.
 */
public interface MultipleYearHolder {

    Integer getYear1();
    Integer getYear2();
    Integer getYear3();
    Integer getYear4();
    Integer getYear5();

    void setYear1(Integer year1);
    void setYear2(Integer year2);
    void setYear3(Integer year3);
    void setYear4(Integer year4);
    void setYear5(Integer year5);
}
