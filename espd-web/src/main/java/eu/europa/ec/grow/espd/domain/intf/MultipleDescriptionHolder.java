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
 * Created by ratoico on 1/12/16 at 3:01 PM.
 */
public interface MultipleDescriptionHolder {

    String getDescription1();
    String getDescription2();
    String getDescription3();
    String getDescription4();
    String getDescription5();

    void setDescription1(String description1);
    void setDescription2(String description2);
    void setDescription3(String description3);
    void setDescription4(String description4);
    void setDescription5(String description5);

	void setDescriptionAtIndex(String description, int index);
}
