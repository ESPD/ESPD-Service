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

import eu.europa.ec.grow.espd.domain.ubl.CcvCriterionRequirement;
import lombok.Getter;

import java.util.List;

/**
 * Created by ratoico on 1/15/16 at 3:12 PM.
 */
@Getter
public enum OtherCriterionRequirement implements CcvCriterionRequirement {

	/**
	 *
	 */
	INDICATOR("7f18c64e-ae09-4646-9400-f3666d50af51", ExpectedResponseType.INDICATOR, "answer"),
	/**
	 *
	 */
	CORRESPONDING_PERCENTAGE("4e552658-d532-4770-943b-b90efcc9788d", ExpectedResponseType.PERCENTAGE, "doubleValue1"),
	/**
	 *
	 */
	DETAILS_EMPLOYEES_CATEGORY("e01d0929-c7a9-455a-aaf9-e1f7cd966336", ExpectedResponseType.DESCRIPTION,
			"description1"),
	/**
	 *
	 */
	PROVIDE_REGISTRATION_NUMBER("30064ad3-fc11-4579-8528-fdd0b9a5ba75", ExpectedResponseType.DESCRIPTION,
			"description1"),
	/**
	 *
	 */
	REG_NO_AVAILABLE_ELECTRONICALLY("b3403349-cbc0-4d84-879e-fc0f2d90ecbd", ExpectedResponseType.DESCRIPTION,
			"description2"),
	/**
	 *
	 */
	REFERENCES_REGISTRATION("792ff522-6f3f-4a62-ab6e-a8b272bc290e", ExpectedResponseType.DESCRIPTION, "description3"),
	/**
	 *
	 */
	REGISTRATION_COVERS_SELECTION_CRITERIA("d9996ef5-49f9-4cf8-a2f5-31c9f4efd894", ExpectedResponseType.INDICATOR,
			"booleanValue1"),
	/**
	 *
	 */
	EO_ABLE_PROVIDE_CERTIFICATE("0e71abd3-198e-49c5-8128-5708617bb191", ExpectedResponseType.INDICATOR,
			"booleanValue3"),
	/**
	 *
	 */
	DOC_AVAILABLE_ELECTRONICALLY("caa72cea-5443-49fb-84ba-ab6c64427f77", ExpectedResponseType.DESCRIPTION,
			"description5"),
	/**
	 *
	 */
	ECONOMIC_OPERATOR_ROLE("907fd62b-02f1-452c-81a8-785bedb0c536", ExpectedResponseType.DESCRIPTION, "description1"),
	/**
	 *
	 */
	OTHER_ECONOMIC_OPERATORS("7c267f95-a3a7-49ef-abd9-e121dcd641a9", ExpectedResponseType.DESCRIPTION, "description2"),
	/**
	 *
	 */
	PARTICIPANT_GROUP_NAME("96f38793-4469-4153-aba6-c613282cdbdc", ExpectedResponseType.DESCRIPTION, "description3"),
	/**
	 *
	 */
	INFO_AVAILABLE_ELECTRONICALLY("0622bbd1-7378-45e1-8fb9-25429740ac22", ExpectedResponseType.INDICATOR,
			"infoElectronicallyAnswer"),
	/**
	 *
	 */
	URL("ee1ee1cd-3791-4855-8b8b-28d4f4c5c007", ExpectedResponseType.EVIDENCE_URL, "infoElectronicallyUrl"),
	/**
	 *
	 */
	URL_CODE("1e55ff14-c643-4abc-91d7-2f4dfcdf2409", ExpectedResponseType.CODE, "infoElectronicallyCode"),
	/**
	 *
	 */
	ISSUER("d8e1e818-d67b-4bb9-9aeb-4c10943a8342", ExpectedResponseType.DESCRIPTION, "infoElectronicallyIssuer"),
	/**
	 *
	 */
	PLEASE_DESCRIBE("323f19b5-3308-4873-b2d1-767963cc81e9", ExpectedResponseType.DESCRIPTION, "description1"),
	/**
	 *
	 */
	LIST_SUBCONTRACTORS("999c7fe2-61cd-4e86-b76f-e280304dc8c9", ExpectedResponseType.DESCRIPTION, "description1"),;

	private final String id;

	private final ExpectedResponseType responseType;

	private final List<String> espdCriterionFields;

	OtherCriterionRequirement(String id, ExpectedResponseType responseType, String espdCriterionField) {
		this.id = id;
		this.responseType = responseType;
		this.espdCriterionFields = ListUtil.list(espdCriterionField);
	}

	@Override
	public String getDescription() {
		return "";
	}
}
