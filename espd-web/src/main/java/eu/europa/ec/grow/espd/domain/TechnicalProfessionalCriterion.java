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

import eu.europa.ec.grow.espd.domain.intf.MultipleAmountHolder;
import eu.europa.ec.grow.espd.domain.intf.MultipleDescriptionHolder;
import eu.europa.ec.grow.espd.domain.intf.MultipleYearHolder;
import eu.europa.ec.grow.espd.domain.intf.UnboundedRequirementGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.collections.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ratoico on 1/5/16 at 1:57 PM.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TechnicalProfessionalCriterion extends SelectionCriterion
        implements MultipleAmountHolder, MultipleDescriptionHolder, MultipleYearHolder, UnboundedRequirementGroup {

    private String description1;
    private String description2;
    private String description3;
    private String description4;
    private String description5;

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

    private Date startDate1;
    private Date startDate2;
    private Date startDate3;
    private Date startDate4;
    private Date startDate5;

	private Date endDate1;
	private Date endDate2;
	private Date endDate3;
	private Date endDate4;
	private Date endDate5;

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

    private BigDecimal percentage;
    private String specify;
	private List<DynamicRequirementGroup> unboundedGroups = new ArrayList<>(5);

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
        throw new UnsupportedOperationException("Technical professional criterion does not have year4.");
    }

    @Override
    public void setYear5(Integer year5) {
        throw new UnsupportedOperationException("Technical professional criterion does not have year5.");
    }

	@Override
	public List<DynamicRequirementGroup> getUnboundedGroups() {
		return this.unboundedGroups;
	}

	@Override
	public void setDescriptionAtIndex(String description, int index) {
		if (CollectionUtils.isEmpty(unboundedGroups) || unboundedGroups.size() <= index) {
			DynamicRequirementGroup dynamicGroup = new DynamicRequirementGroup();
			unboundedGroups.add(index, dynamicGroup);
		}
		unboundedGroups.get(index).put("description", description);
	}
}
