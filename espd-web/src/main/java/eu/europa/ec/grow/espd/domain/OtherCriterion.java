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

package eu.europa.ec.grow.espd.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * Created by ratoico on 1/20/16 at 10:39 AM.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class OtherCriterion extends EspdCriterion {

	private String description1;
	private String description2;
	private String description3;
	private String description4;
	private String description5;
	private BigDecimal doubleValue1;
	private Boolean booleanValue1;
	private Boolean booleanValue2; // is not applicable (not used anymore)
	private Boolean booleanValue3; // e) Will the economic operator... indicator

	public OtherCriterion() {
		// !! award criteria should always exist (be present in a ESPD Response)
		setExists(true);
	}

	public static OtherCriterion build() {
		return new OtherCriterion();
	}

	public Boolean getBooleanValue1() {
		return Boolean.TRUE.equals(this.booleanValue1);
	}

	@Override
	public Boolean getAnswer() {
		return this.answer;
	}

}
