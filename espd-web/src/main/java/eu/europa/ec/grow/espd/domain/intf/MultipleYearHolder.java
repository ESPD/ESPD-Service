/*
 *
 * Copyright 2016 EUROPEAN COMMISSION
 *
 * Licensed under the EUPL, Version 1.1 or – as soon they
 * will be approved by the European Commission - subsequent
 * versions of the EUPL (the "Licence");
 *
 * You may not use this work except in compliance with the Licence.
 *
 * You may obtain a copy of the Licence at:
 *
 * https://joinup.ec.europa.eu/community/eupl/og_page/eupl
 *
 * Unless required by applicable law or agreed to in
 * writing, software distributed under the Licence is
 * distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied.
 * See the Licence for the specific language governing
 * permissions and limitations under the Licence.
 *
 */

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
