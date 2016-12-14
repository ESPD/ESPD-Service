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

package eu.europa.ec.grow.espd.xml.common.importing;

import com.google.common.base.Optional;
import eu.europa.ec.grow.espd.domain.*;
import eu.europa.ec.grow.espd.domain.infrastructure.CriterionDefinitions;
import eu.europa.ec.grow.espd.domain.intf.UnboundedRequirementGroup;
import eu.europa.ec.grow.espd.domain.ubl.CcvCriterion;
import eu.europa.ec.grow.espd.domain.ubl.CcvCriterionRequirement;
import eu.europa.ec.grow.espd.domain.ubl.CcvRequirementGroup;
import eu.europa.ec.grow.espd.util.Amount;
import isa.names.specification.ubl.schema.xsd.ccv_commonaggregatecomponents_1.CriterionType;
import isa.names.specification.ubl.schema.xsd.ccv_commonaggregatecomponents_1.RequirementGroupType;
import isa.names.specification.ubl.schema.xsd.ccv_commonaggregatecomponents_1.RequirementType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;
import static eu.europa.ec.grow.espd.domain.enums.criteria.ExclusionCriterionTypeCode.*;
import static eu.europa.ec.grow.espd.domain.enums.criteria.OtherCriterionTypeCode.DATA_ON_ECONOMIC_OPERATOR;
import static eu.europa.ec.grow.espd.domain.enums.criteria.OtherCriterionTypeCode.REDUCTION_OF_CANDIDATES;
import static eu.europa.ec.grow.espd.domain.enums.criteria.SelectionCriterionTypeCode.*;
import static org.apache.commons.collections.CollectionUtils.isEmpty;
import static org.apache.commons.collections.CollectionUtils.isNotEmpty;
import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * Class that reads data coming from UBL {@link CriterionType} and creates the appropriate ESPD criterion objects
 * containing all the data read from the XML.
 * <p>
 * The algorithm traverses all the {@link RequirementGroupType} recursively (including subgroups) found in the
 * {@link CriterionType} and looks up each requirement by id in the {@link CriterionDefinitions} lookup tables.
 * The criterion requirements are then set dynamically via reflection by using its 'espdCriterionFields' loaded from
 * the criteria JSON definition files.
 * </p>
 * <p>
 * Requirement groups are treated differently when they are unbounded (which means they can ashave  many references as
 * needed) because the requirement values are dynamically stored in a {@link java.util.Map} structure called
 * {@link DynamicRequirementGroup}.
 * </p>
 * <p>
 * For the unbounded requirements there are mappings defined in the configuration JSON files ('requirementGroupMappings'
 * and 'requirementMappings') for supporting versions prior to 2016.12 when there were 3 or 5 requirement groups with
 * a similar structure but sequential ESPD criterion field mappings. Since now there is only one unbounded requirement
 * group for a given criterion, we need to point the old requirement group ids and requirement ids to the primary ones
 * used from version 2016.12.
 * </p>
 * <p>
 * Created by ratoico on 1/7/16 at 11:16 AM.
 */
@Slf4j
class EspdResponseCriterionFactory {

	private final Map<String, CriterionBuilder> criterionBuilders = new HashMap<>();

	EspdResponseCriterionFactory() {
		registerCriterionBuilders();
	}

	private void registerCriterionBuilders() {
		criterionBuilders.put(CRIMINAL_CONVICTIONS.getEspdType(), new CriminalConvictionsCriterionBuilder());
		criterionBuilders.put(PAYMENT_OF_TAXES.getEspdType(), new TaxesCriterionBuilder());
		criterionBuilders.put(PAYMENT_OF_SOCIAL_SECURITY.getEspdType(), new TaxesCriterionBuilder());
		criterionBuilders.put(ENVIRONMENTAL_LAW.getEspdType(), new LawCriterionBuilder());
		criterionBuilders.put(SOCIAL_LAW.getEspdType(), new LawCriterionBuilder());
		criterionBuilders.put(LABOUR_LAW.getEspdType(), new LawCriterionBuilder());
		criterionBuilders.put(BANKRUPTCY_INSOLVENCY.getEspdType(), new BankruptcyCriterionBuilder());
		criterionBuilders.put(MISCONDUCT.getEspdType(), new MisconductDistortionCriterionBuilder());
		criterionBuilders.put(DISTORTING_MARKET.getEspdType(), new MisconductDistortionCriterionBuilder());
		criterionBuilders.put(CONFLICT_OF_INTEREST.getEspdType(), new ConflictInterestCriterionBuilder());
		criterionBuilders.put(OTHER.getEspdType(), new PurelyNationalGroundsBuilder());
		criterionBuilders.put(ALL_CRITERIA_SATISFIED.getEspdType(), new SatisfiesAllCriterionBuilder());
		criterionBuilders.put(SUITABILITY.getEspdType(), new SuitabilityCriterionBuilder());
		criterionBuilders
				.put(ECONOMIC_FINANCIAL_STANDING.getEspdType(), new EconomicFinancialStandingCriterionBuilder());
		criterionBuilders
				.put(TECHNICAL_PROFESSIONAL_ABILITY.getEspdType(), new TechnicalProfessionalCriterionBuilder());
		criterionBuilders.put(QUALITY_ASSURANCE.getEspdType(), new QualityAssuranceCriterionBuilder());
		criterionBuilders.put(DATA_ON_ECONOMIC_OPERATOR.getEspdType(), new OtherCriterionBuilder());
		criterionBuilders.put(REDUCTION_OF_CANDIDATES.getEspdType(), new OtherCriterionBuilder());
	}

	/**
	 * Create a ESPD {@link EspdCriterion} instance containing the appropriate information provided as UBL criteria.
	 *
	 * @return A freshly built {@link EspdCriterion} containing the data coming from the XML
	 *
	 * @throws IllegalArgumentException If the criterion type is not recognized
	 */
	EspdCriterion buildEspdCriterion(CcvCriterion ccvCriterion, CriterionType ublCriterion) {
		CriterionBuilder criterionBuilder = criterionBuilders.get(ccvCriterion.getCriterionType().getEspdType());
		checkArgument(criterionBuilder != null,
				"Could not build criterion '%s' with id '%s' having type code '%s'.",
				ccvCriterion.getName(), ccvCriterion.getUuid(), ccvCriterion.getCriterionType());

		if (ublCriterion == null) {
			return criterionBuilder.buildWithExists(false);
		}

		EspdCriterion criterion = criterionBuilder.buildWithExists(true);

		setCriterionValues(ublCriterion, criterion);

		return criterion;
	}

	private void setCriterionValues(CriterionType criterionType, EspdCriterion espdCriterion) {
		setCriterionValuesForRequirementGroups(espdCriterion, criterionType.getRequirementGroup());
	}

	private void setCriterionValuesForRequirementGroups(EspdCriterion espdCriterion,
			List<RequirementGroupType> groups) {
		if (isEmpty(groups)) {
			return;
		}
		for (RequirementGroupType groupType : groups) {
			setCriterionValuesForRequirementGroup(espdCriterion, groupType);
		}
	}

	private void setCriterionValuesForRequirementGroup(EspdCriterion espdCriterion, RequirementGroupType groupType) {
		Optional<CcvRequirementGroup> ccvGroup = CriterionDefinitions
				.findRequirementGroupById(groupType.getID().getValue());
		DynamicRequirementGroup dynamicGroup = null;
		if (ccvGroup.isPresent() && ccvGroup.get().isUnbounded()) {
			dynamicGroup = new DynamicRequirementGroup();
			((UnboundedRequirementGroup) espdCriterion).getUnboundedGroups().add(dynamicGroup);
		}

		setCriterionValuesForRequirementGroups(espdCriterion, groupType.getRequirementGroup());
		setCriterionValueForRequirements(espdCriterion, groupType.getRequirement(), ccvGroup, dynamicGroup);
	}

	private void setCriterionValueForRequirements(EspdCriterion espdCriterion, List<RequirementType> requirementTypes,
			Optional<CcvRequirementGroup> ccvGroup, DynamicRequirementGroup dynamicGroup) {
		if (isEmpty(requirementTypes)) {
			return;
		}
		for (RequirementType requirementType : requirementTypes) {
			setCriterionValueForRequirement(espdCriterion, requirementType, ccvGroup, dynamicGroup);
		}
	}

	private void setCriterionValueForRequirement(EspdCriterion espdCriterion, RequirementType requirementType,
			Optional<CcvRequirementGroup> ccvGroup, DynamicRequirementGroup dynamicGroup) {
		Optional<CcvCriterionRequirement> requirementById = CriterionDefinitions
				.findRequirementById(requirementType.getID().getValue());

		if (requirementById.isPresent() && isNotEmpty(requirementType.getResponse())) {
			Object requirementValue = requirementById.get().getResponseType()
			                                         .parseValue(requirementType.getResponse().get(0));
			if (ccvGroup.isPresent() && ccvGroup.get().isUnbounded()) {
				addRequirementValueToUnboundedGroup(requirementById.get(), dynamicGroup, requirementValue);
			} else {
				addNormalRequirementValueToEspdCriterion(requirementById.get(), espdCriterion, requirementValue);
			}
		} else {
			log.warn("Requirement with id '{}' could not be found or does not have a response.",
					requirementType.getID().getValue());
		}

	}

	private void addRequirementValueToUnboundedGroup(CcvCriterionRequirement ccvRequirement,
			DynamicRequirementGroup dynamicGroup, Object value) {
		if (value instanceof Amount) {
			// values which represent amounts are special and need to be stored in two fields
			dynamicGroup.put(ccvRequirement.getEspdCriterionFields().get(0), ((Amount) value).getAmount());
			dynamicGroup.put(ccvRequirement.getEspdCriterionFields().get(1), ((Amount) value).getCurrency());
		} else {
			dynamicGroup.put(ccvRequirement.getEspdCriterionFields().get(0), value);
		}
	}

	private void addNormalRequirementValueToEspdCriterion(CcvCriterionRequirement ccvCriterionRequirement,
			EspdCriterion espdCriterion, Object value) {
		String espdFieldName = ccvCriterionRequirement.getEspdCriterionFields().get(0);
		if (isBlank(espdFieldName)) {
			return;
		}

		// values which represent amounts are special and need to be stored in two fields
		try {
			if (value instanceof Amount) {
				PropertyUtils.setProperty(espdCriterion, espdFieldName, ((Amount) value).getAmount());
				PropertyUtils.setProperty(espdCriterion, ccvCriterionRequirement.getEspdCriterionFields().get(1),
						((Amount) value).getCurrency());
			} else {
				PropertyUtils.setProperty(espdCriterion, espdFieldName, value);
			}
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			log.error("Could not set value '{}' on field '{}' of requirement '{}' with id '{}'.", value, espdFieldName,
					ccvCriterionRequirement.getDescription(), ccvCriterionRequirement.getId());
		}
	}

	private interface CriterionBuilder<T extends EspdCriterion> {

		T buildWithExists(boolean exists);

	}

	private static class CriminalConvictionsCriterionBuilder implements CriterionBuilder<CriminalConvictionsCriterion> {

		@Override
		public CriminalConvictionsCriterion buildWithExists(boolean exists) {
			return CriminalConvictionsCriterion.buildWithExists(exists);
		}
	}

	private static class TaxesCriterionBuilder implements CriterionBuilder<TaxesCriterion> {

		@Override
		public TaxesCriterion buildWithExists(boolean exists) {
			return TaxesCriterion.buildWithExists(exists);
		}
	}

	private static class LawCriterionBuilder implements CriterionBuilder<LawCriterion> {

		@Override
		public LawCriterion buildWithExists(boolean exists) {
			return LawCriterion.buildWithExists(exists);
		}
	}

	private static class BankruptcyCriterionBuilder implements CriterionBuilder<BankruptcyCriterion> {

		@Override
		public BankruptcyCriterion buildWithExists(boolean exists) {
			return BankruptcyCriterion.buildWithExists(exists);
		}
	}

	private static class MisconductDistortionCriterionBuilder
			implements CriterionBuilder<MisconductDistortionCriterion> {

		@Override
		public MisconductDistortionCriterion buildWithExists(boolean exists) {
			return MisconductDistortionCriterion.buildWithExists(exists);
		}
	}

	private static class ConflictInterestCriterionBuilder implements CriterionBuilder<ConflictInterestCriterion> {

		@Override
		public ConflictInterestCriterion buildWithExists(boolean exists) {
			return ConflictInterestCriterion.buildWithExists(exists);
		}
	}

	private static class PurelyNationalGroundsBuilder implements CriterionBuilder<PurelyNationalGrounds> {

		@Override
		public PurelyNationalGrounds buildWithExists(boolean exists) {
			return PurelyNationalGrounds.buildWithExists(exists);
		}
	}

	private static class SatisfiesAllCriterionBuilder implements CriterionBuilder<SatisfiesAllCriterion> {

		@Override
		public SatisfiesAllCriterion buildWithExists(boolean exists) {
			return SatisfiesAllCriterion.buildWithExists(exists);
		}
	}

	private static class SuitabilityCriterionBuilder implements CriterionBuilder<SuitabilityCriterion> {

		@Override
		public SuitabilityCriterion buildWithExists(boolean exists) {
			return SuitabilityCriterion.buildWithExists(exists);
		}
	}

	private static class EconomicFinancialStandingCriterionBuilder
			implements CriterionBuilder<EconomicFinancialStandingCriterion> {

		@Override
		public EconomicFinancialStandingCriterion buildWithExists(boolean exists) {
			return EconomicFinancialStandingCriterion.buildWithExists(exists);
		}
	}

	private static class TechnicalProfessionalCriterionBuilder
			implements CriterionBuilder<TechnicalProfessionalCriterion> {

		@Override
		public TechnicalProfessionalCriterion buildWithExists(boolean exists) {
			return TechnicalProfessionalCriterion.buildWithExists(exists);
		}
	}

	private static class QualityAssuranceCriterionBuilder implements CriterionBuilder<QualityAssuranceCriterion> {

		@Override
		public QualityAssuranceCriterion buildWithExists(boolean exists) {
			return QualityAssuranceCriterion.buildWithExists(exists);
		}
	}

	private static class OtherCriterionBuilder implements CriterionBuilder<OtherCriterion> {

		@Override
		public OtherCriterion buildWithExists(boolean exists) {
			return OtherCriterion.build();
		}
	}

}
