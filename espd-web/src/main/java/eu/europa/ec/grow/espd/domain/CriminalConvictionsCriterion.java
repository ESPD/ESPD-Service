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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import eu.europa.ec.grow.espd.domain.intf.UnboundedRequirementGroup;

@Data
@EqualsAndHashCode(callSuper = false)
public class CriminalConvictionsCriterion extends ExclusionCriterion implements UnboundedRequirementGroup {

    //private Date dateOfConviction;
    //private String reason;
    //private String convicted;
    //private String periodLength;
	private List<DynamicRequirementGroup> unboundedGroups = new ArrayList<>(1);

    public static CriminalConvictionsCriterion buildWithExists(Boolean exists) {
        CriminalConvictionsCriterion criminalConvictions = new CriminalConvictionsCriterion();
        criminalConvictions.setExists(exists);
        return criminalConvictions;
    }

	@Override
	public List<DynamicRequirementGroup> getUnboundedGroups() {
		return unboundedGroups;
	}
	
	// Bounded-style settings for were added
	// to support compatibility with previously written tests
	// running bounded groups for criminal convictions

	private Object getFirstUnboundedGroupField(String field) {
		if(CollectionUtils.isEmpty(unboundedGroups)) {
			return null;
		}
		return unboundedGroups.get(0).get(field);
	}

	private void setFirstUnboundedGroupField(String field, Object value) {
		if(unboundedGroups == null) {
			unboundedGroups = new ArrayList<>(1);
		}
		if(CollectionUtils.isEmpty(unboundedGroups)) {
			unboundedGroups.add(new DynamicRequirementGroup());
		}
		unboundedGroups.get(0).put(field, value);
	}
	
	public Date getDateOfConviction() {
		return (Date) getFirstUnboundedGroupField("dateOfConviction");
	}
	
	public String getReason() {
		return (String) getFirstUnboundedGroupField("reason");
	}
	
	public String getConvicted() {
		return (String) getFirstUnboundedGroupField("convicted");
	}
	
	public String getPeriodLength() {
		return (String) getFirstUnboundedGroupField("periodLength");
	}
	
	public void setDateOfConviction(Date value) {
		setFirstUnboundedGroupField("dateOfConviction", value);
	}
	
	public void setReason(String value) {
		setFirstUnboundedGroupField("reason", value);
	}
	
	public void setConvicted(String value) {
		setFirstUnboundedGroupField("convicted", value);
	}
	
	public void setPeriodLength(String value) {
		setFirstUnboundedGroupField("periodLength", value);
	}
	
	//// Unbounded SelfCleaning support for bounded Tests

    public final String getSelfCleaningDescription() {
        return getSelfCleaning().getDescription();
    }

	public final void setSelfCleaningDescription(String description) {
		if(unboundedGroups == null) {
			unboundedGroups = new ArrayList<>(1);
		}
		if(CollectionUtils.isEmpty(unboundedGroups)) {
			unboundedGroups.add(new DynamicRequirementGroup());
		}
		setFirstUnboundedGroupField("selfCleaningDescription", description);
	}

    public final boolean getSelfCleaningAnswer() {
    	SelfCleaning selfCleaning = getSelfCleaning();
        return selfCleaning != null && Boolean.TRUE.equals(selfCleaning.getAnswer());
    }

	public final void setSelfCleaningAnswer(boolean answer) {
		if(unboundedGroups == null) {
			unboundedGroups = new ArrayList<>(1);
		}
		if(CollectionUtils.isEmpty(unboundedGroups)) {
			unboundedGroups.add(new DynamicRequirementGroup());
		}
		unboundedGroups.get(0).setSubIndicatorAnswer(answer);
	}
	@Override
	public void setSelfCleaning(SelfCleaning value) {
		if(value != null) {
			if(unboundedGroups == null) {
				unboundedGroups = new ArrayList<>(1);
			}
			if(CollectionUtils.isEmpty(unboundedGroups)) {
				unboundedGroups.add(new DynamicRequirementGroup());
			}
			unboundedGroups.get(0).setSubIndicatorAnswer(value.getAnswer());
			unboundedGroups.get(0).put("selfCleaningDescription", value.getDescription());
		}
	}
	@Override
	public SelfCleaning getSelfCleaning() {
		if(CollectionUtils.isEmpty(unboundedGroups)) {
			return null;
		}
		SelfCleaning sc = new SelfCleaning();
		sc.setAnswer(unboundedGroups.get(0).getSubIndicatorAnswer());
		if(unboundedGroups.get(0).get("selfCleaningDescription") != null) {
			sc.setDescription((String)unboundedGroups.get(0).get("selfCleaningDescription"));
		}
		return sc;
	}
}
