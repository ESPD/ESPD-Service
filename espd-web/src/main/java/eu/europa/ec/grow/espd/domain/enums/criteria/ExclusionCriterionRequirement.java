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

package eu.europa.ec.grow.espd.domain.enums.criteria;

import com.fasterxml.jackson.annotation.JsonFormat;
import eu.europa.ec.grow.espd.domain.ubl.CcvCriterionRequirement;
import lombok.Getter;

import java.util.List;

/**
 * Created by vigi on 11/23/15:11:50 AM.
 */
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ExclusionCriterionRequirement implements CcvCriterionRequirement {

	/**
	 *
	 */
	YOUR_ANSWER("974c8196-9d1c-419c-9ca9-45bb9f5fd59a", CriterionText.YOUR_ANSWER_TEXT,
			ExpectedResponseType.INDICATOR, "answer"),
	/**
	 *
	 */
	DATE_OF_CONVICTION("ecf40999-7b64-4e10-b960-7f8ff8674cf6", CriterionText.DATE_OF_CONVICTION_TEXT,
			ExpectedResponseType.DATE, "dateOfConviction"),
	/**
	 *
	 */
	REASON("7d35fb7c-da5b-4830-b598-4f347a04dceb", CriterionText.REASON_TEXT, ExpectedResponseType.DESCRIPTION,
			"reason"),
	/**
	 *
	 */
	WHO_CONVICTED("c5012430-14da-454c-9d01-34cedc6a7ded", CriterionText.WHO_CONVICTED_TEXT,
			ExpectedResponseType.DESCRIPTION, "convicted"),
	/**
	 *
	 */
	LENGTH_PERIOD_EXCLUSION("9ca9096f-edd2-4f19-b6b1-b55c83a2d5c8", CriterionText.LENGTH_PERIOD_EXCLUSION_TEXT,
			ExpectedResponseType.PERIOD, "periodLength"),
	/**
	 *
	 */
	MEASURES_SELF_CLEANING("20c5361b-7599-4ee6-b030-7f8323174d1e", CriterionText.MEASURES_SELF_CLEANING_TEXT,
			ExpectedResponseType.INDICATOR, "selfCleaningAnswer"),
	/**
	 *
	 */
	PLEASE_DESCRIBE_SELF_CLEANING("7b07904f-e080-401a-a3a1-9a3efeeda54b", CriterionText.PLEASE_DESCRIBE_TEXT,
			ExpectedResponseType.DESCRIPTION, "selfCleaningDescription"),
	/**
	 *
	 */
	INFO_AVAILABLE_ELECTRONICALLY("c1347b74-1872-4060-a6db-f4044edcd7c4",
			CriterionText.INFO_AVAILABLE_ELECTRONICALLY_TEXT, ExpectedResponseType.INDICATOR,
			"infoElectronicallyAnswer"),
	/**
	 *
	 */
	URL("f4313bb6-21b6-499e-bdff-debe10e11d2c", CriterionText.URL_TEXT, ExpectedResponseType.EVIDENCE_URL,
			"infoElectronicallyUrl"),
	/**
	 *
	 */
	URL_CODE("1f1cd18e-3e01-4ca2-af4c-e2981924ba8d", CriterionText.URL_CODE_TEXT, ExpectedResponseType.CODE,
			"infoElectronicallyCode"),
	/**
	 *
	 */
	ISSUER("c3ccfa31-0c5e-4e3a-a3fd-db9fb83d78d4", "Issuer", ExpectedResponseType.DESCRIPTION,
			"infoElectronicallyIssuer"),
	/**
	 *
	 */
	COUNTRY_MS("6c87d3d4-e8eb-4253-b385-6373020ab886", CriterionText.COUNTRY_MS_TEXT, ExpectedResponseType.CODE_COUNTRY,
			"country"),
	/**
	 *
	 */
	AMOUNT("9052cc59-cfe5-41c6-a314-02a7f378ffe8", CriterionText.AMOUNT_CONCERNED_TEXT, ExpectedResponseType.AMOUNT,
			"amount", "currency"),
	/**
	 *
	 */
	BREACH_OF_OBLIGATIONS_OTHER_THAN("9b4497e6-a166-46f9-8581-7fc39ff975c4",
			CriterionText.BREACH_OF_OBLIGATIONS_OTHER_THAN_TEXT, ExpectedResponseType.INDICATOR,
			"breachEstablishedOtherThanJudicialDecision"),
	/**
	 *
	 */
	DESCRIBE_MEANS("201f11c3-1fa2-4464-acc0-f021266fd881", CriterionText.DESCRIBE_MEANS_TEXT,
			ExpectedResponseType.DESCRIPTION, "meansDescription"),
	/**
	 *
	 */
	DECISION_FINAL_AND_BINDING("08b0c984-c5e6-4143-8493-868c39745637", CriterionText.DECISION_FINAL_AND_BINDING_TEXT,
			ExpectedResponseType.INDICATOR, "decisionFinalAndBinding"),
	/**
	 *
	 */
	EO_FULFILLED_OBLIGATION("70f8697b-8953-411a-a489-4ff62e5250d2", CriterionText.EO_FULFILLED_OBLIGATIONS_TEXT,
			ExpectedResponseType.INDICATOR, "eoFulfilledObligations"),
	/**
	 *
	 */
	DESCRIBE_OBLIGATIONS("55905dd0-38f0-4f93-8c74-5ae05a21afc5", CriterionText.PLEASE_DESCRIBE_TEXT,
			ExpectedResponseType.DESCRIPTION, "obligationsDescription"),
	/**
	 *
	 */
	REASONS_NEVERTHELESS_CONTRACT("4e3f468a-86c4-4c99-bd15-c8b221229348",
			CriterionText.REASONS_NEVERTHELESS_CONTRACT_TEXT, ExpectedResponseType.DESCRIPTION, "reason"),
	/**
	 *
	 */
	PLEASE_DESCRIBE("e098da8e-4717-4500-965f-f882d5b4e1ad", CriterionText.PLEASE_DESCRIBE_TEXT,
			ExpectedResponseType.DESCRIPTION, "description"),
	;

	private final String id;

	private final String description;

	private final ExpectedResponseType responseType;

	private final List<String> espdCriterionFields;

	ExclusionCriterionRequirement(String id, String description, ExpectedResponseType responseType,
			String... espdCriterionFields) {
		this.id = id;
		this.description = description;
		this.responseType = responseType;
		this.espdCriterionFields = ListUtil.list(espdCriterionFields);
	}

}
