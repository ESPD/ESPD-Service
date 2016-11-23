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
 * Created by ratoico on 12/8/15 at 1:45 PM.
 */
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum SelectionCriterionRequirement implements CcvCriterionRequirement {

	/**
	 *
	 */
	YOUR_ANSWER("15335c12-ad77-4728-b5ad-3c06a60d65a4", ExpectedResponseType.INDICATOR, "answer"),
	/**
	 *
	 */
	YEAR_1("5aacceb3-280e-42f1-b2da-3d8ac7877fe9", ExpectedResponseType.QUANTITY_YEAR, "year1"),
	/**
	 *
	 */
	YEAR_2("49a57870-7fb8-451f-a7af-fa0e7f8b97e7", ExpectedResponseType.QUANTITY_YEAR, "year2"),
	/**
	 *
	 */
	YEAR_3("9d0cf1cb-27bc-4747-8579-47dce4d8d490", ExpectedResponseType.QUANTITY_YEAR, "year3"),
	/**
	 *
	 */
	YEAR_4("17a7353d-a7a4-43ee-9cc8-b9db83eeafb3", ExpectedResponseType.QUANTITY_YEAR, "year4"),
	/**
	 *
	 */
	YEAR_5("34825634-5151-4e31-af1b-7eafadcf15be", ExpectedResponseType.QUANTITY_YEAR, "year5"),
	/**
	 *
	 */
	AMOUNT_1("42db0eaa-d2dd-48cb-83ac-38d73cab9b50", ExpectedResponseType.AMOUNT, "amount1", "currency1"),
	/**
	 *
	 */
	AMOUNT_2("4acd0a02-c267-4d05-b456-c0565c2ffd46", ExpectedResponseType.AMOUNT, "amount2", "currency2"),
	/**
	 *
	 */
	AMOUNT_3("28fb4b41-5178-4b79-ba24-d9a62fa4a658", ExpectedResponseType.AMOUNT, "amount3", "currency3"),
	/**
	 *
	 */
	AMOUNT_4("9f278e42-aa1d-4b2e-97cd-832248aa5393", ExpectedResponseType.AMOUNT, "amount4", "currency4"),
	/**
	 *
	 */
	AMOUNT_5("cc1a0b1e-dbfd-4313-a4fb-2e543b05549b", ExpectedResponseType.AMOUNT, "amount5", "currency5"),
	/**
	 *
	 */
	PLEASE_SPECIFY("15778db8-0d84-42ba-931b-774c1b3d3f9f", ExpectedResponseType.DESCRIPTION, "specify"),
	/**
	 *
	 */
	PERCENTAGE("612a1625-118d-4ea4-a6db-413184e7c0a8", ExpectedResponseType.PERCENTAGE, "percentage"),
	/**
	 *
	 */
	PLEASE_DESCRIBE("51391308-0bf6-423c-95e2-d5a54aa31fb8", ExpectedResponseType.DESCRIPTION, "description"),
	/**
	 *
	 */
	DESCRIPTION_1("ab05ff3b-f3e1-4441-9b43-ee9912e29e92", ExpectedResponseType.DESCRIPTION, "description1"),
	/**
	 *
	 */
	DESCRIPTION_2("927def36-1fa3-4018-8b45-7ee2c5b1e0af", ExpectedResponseType.DESCRIPTION, "description2"),
	/**
	 *
	 */
	DESCRIPTION_3("e6ca4034-cfee-499a-9a47-c4f2862ef4d0", ExpectedResponseType.DESCRIPTION, "description3"),
	/**
	 *
	 */
	DESCRIPTION_4("b1640c24-b405-443e-bf5e-d7771f66aab6", ExpectedResponseType.DESCRIPTION, "description4"),
	/**
	 *
	 */
	DESCRIPTION_5("587129bc-a5e1-43be-94ac-6e5366d30c67", ExpectedResponseType.DESCRIPTION, "description5"),
	/**
	 *
	 */
	RATIO_1("5461b973-7067-457e-93cc-8338da2c3eef", ExpectedResponseType.QUANTITY, "ratio1"),
	/**
	 *
	 */
	RATIO_2("295d82b7-5ee6-4977-8aea-bac4acf6ecdf", ExpectedResponseType.QUANTITY, "ratio2"),
	/**
	 *
	 */
	RATIO_3("2b792afb-87ba-47b5-a80c-aee76a6f2cc8", ExpectedResponseType.QUANTITY, "ratio3"),
	/**
	 *
	 */
	RATIO_4("22dc4bef-182d-4b81-bddc-cc30b218f9bb", ExpectedResponseType.QUANTITY, "ratio4"),
	/**
	 *
	 */
	RATIO_5("990eef0a-14c6-41af-8bf2-b8311332d152", ExpectedResponseType.QUANTITY, "ratio5"),
	/**
	 *
	 */
	START_DATE_1("42ec8116-31a7-4118-8612-5b04f5c8bde7", ExpectedResponseType.DATE, "startDate1"),
	/**
	 *
	 */
	START_DATE_2("8d0e5e16-85ed-4730-a784-d4db8f439c0c", ExpectedResponseType.DATE, "startDate2"),
	/**
	 *
	 */
	START_DATE_3("c953e635-580b-4d7c-a30c-2edbde3b8fdf", ExpectedResponseType.DATE, "startDate3"),
	/**
	 *
	 */
	START_DATE_4("9b263b45-fc63-4b01-a3dc-cb9c95dda449", ExpectedResponseType.DATE, "startDate4"),
	/**
	 *
	 */
	START_DATE_5("056cba1d-986b-4164-92b6-26a1cbdf0690", ExpectedResponseType.DATE, "startDate5"),
	/**
	 *
	 */
	END_DATE_1("3641b897-f9f0-4d90-909a-b6d4c4b1d645", ExpectedResponseType.DATE, "endDate1"),
	/**
	 *
	 */
	END_DATE_2("4c842551-fb07-4a13-91e6-5653820f7e80", ExpectedResponseType.DATE, "endDate2"),
	/**
	 *
	 */
	END_DATE_3("822934ff-da94-40d2-a799-f29ba7bba2b0", ExpectedResponseType.DATE, "endDate3"),
	/**
	 *
	 */
	END_DATE_4("7a95ddbd-05e8-4af4-973f-1b8d05f71e0f", ExpectedResponseType.DATE, "endDate4"),
	/**
	 *
	 */
	END_DATE_5("dd71df86-3ad5-42dd-add5-9bd51dc88f05", ExpectedResponseType.DATE, "endDate5"),
	/**
	 *
	 */
	RECIPIENTS_1("a92536ab-6783-40bb-a037-5d31f421fd85", ExpectedResponseType.DESCRIPTION, "recipients1"),
	/**
	 *
	 */
	RECIPIENTS_2("c8babafa-b6fa-4e14-8749-d913d8f1d33b", ExpectedResponseType.DESCRIPTION, "recipients2"),
	/**
	 *
	 */
	RECIPIENTS_3("5157e1ff-d272-4382-98a9-6953f5a15300", ExpectedResponseType.DESCRIPTION, "recipients3"),
	/**
	 *
	 */
	RECIPIENTS_4("a84ea948-cf03-47b5-b4cf-a35f49910d10", ExpectedResponseType.DESCRIPTION, "recipients4"),
	/**
	 *
	 */
	RECIPIENTS_5("38a4802f-0b93-4e78-ad4e-2a057e1aa578", ExpectedResponseType.DESCRIPTION, "recipients5"),
	/**
	 *
	 */
	INFO_AVAILABLE_ELECTRONICALLY("9dae5670-cb75-4c97-901b-96ddac5a633a", ExpectedResponseType.INDICATOR,
			"infoElectronicallyAnswer"),
	/**
	 *
	 */
	URL("03bb1954-13ae-47d8-8ef8-b7fe0f22d700", ExpectedResponseType.EVIDENCE_URL, "infoElectronicallyUrl"),
	/**
	 *
	 */
	URL_CODE("e2d863a0-60cb-4e58-8c14-4c1595af48b7", ExpectedResponseType.CODE, "infoElectronicallyCode"),
	/**
	 *
	 */
	ISSUER("5cbf74d9-a1e2-4233-921d-8b298842ee7d", ExpectedResponseType.DESCRIPTION, "infoElectronicallyIssuer"),
	/**
	 *
	 */
	ALLOW_CHECKS("23a27c0e-c4f7-42cd-b0fd-a7cedfbf77a7", ExpectedResponseType.INDICATOR, "answer"),
	/**
	 *
	 */
	NUMBER_1("42037f41-53af-44df-b6b8-2395cee98087", ExpectedResponseType.QUANTITY_INTEGER, "number1"),
	/**
	 *
	 */
	NUMBER_2("0bb2d3bf-160f-4904-a4e8-ee672bd5cb30", ExpectedResponseType.QUANTITY_INTEGER, "number2"),
	/**
	 *
	 */
	NUMBER_3("668dbc0d-2a3a-49b9-b8e1-8ebbeccd712a", ExpectedResponseType.QUANTITY_INTEGER, "number3"),
	/**
	 *
	 */
	EXPLAIN_SUPPLY_CONTRACTS_QC("b9dec4cb-2f6f-47d7-a023-e9099b19b338", ExpectedResponseType.DESCRIPTION,
			"description"),
	/**
	 *
	 */
	EXPLAIN_CERTIFICATES_QA("8c5d1e13-54f7-4895-a65c-b8e09253130c", ExpectedResponseType.DESCRIPTION, "description"),
	/**
	 *
	 */
	EXPLAIN_CERTIFICATES_ENVIRONMENTAL("b0aace10-fd73-46d1-ae78-289ee5cd42ca", ExpectedResponseType.DESCRIPTION,
			"description"),
	/**
	 * In the financial ratios a requirement is required previous to a subgroup of requirements so we add a dummy sentence like 'please provide the requested data below'
	 */
	PLEASE_PROVIDE_DATA_BELOW("3a6fefd4-f458-4d43-97fb-0725fce5dce2", ExpectedResponseType.DESCRIPTION, (String) null),
	/**
	 *
	 */
	SPECIFY_YEAR("a18b2c98-8552-45ca-9751-d4c94c05847a", ExpectedResponseType.QUANTITY_YEAR, "year1"),
	/**
	 *
	 */
	NUMBER_OF_YEARS("b98ffd05-6572-4b07-a521-693a1754ed46", ExpectedResponseType.QUANTITY_INTEGER, "numberOfYears"),
	/**
	 *
	 */
	AVERAGE_TURNOVER("217637ba-6bdb-4c73-a38f-27fe0e71d9be", ExpectedResponseType.AMOUNT, "amount1", "currency1"),;

	private final String id;

	private final ExpectedResponseType responseType;

	private final List<String> espdCriterionFields;

	SelectionCriterionRequirement(String id, ExpectedResponseType responseType,
			String... espdCriterionFields) {
		this.id = id;
		this.responseType = responseType;
		this.espdCriterionFields = ListUtil.list(espdCriterionFields);
	}

	@Override
	public String getDescription() {
		return "";
	}
}
