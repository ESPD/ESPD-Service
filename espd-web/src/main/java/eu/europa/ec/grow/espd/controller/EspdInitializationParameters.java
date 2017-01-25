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

package eu.europa.ec.grow.espd.controller;

import eu.europa.ec.grow.espd.domain.PartyImpl;
import eu.europa.ec.grow.espd.domain.enums.other.Country;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Class used to initialize an ESPD document with certain parameters. It also allows one to specify the selected
 * values for the '/filter' page.
 *
 * Created by ratoico on 1/24/17.
 */
@Data
class EspdInitializationParameters extends PartyImpl {

	private String agent;

	private String action;

	private String tedReceptionId;

	private String officialName;

	private Country procurerCountry;

	private String title;

	private String description;

	private String fileRefByCA;

	//trick to use List of MultipartFile as @RequestParam
	public void setAttachments(List<MultipartFile> attachments) throws IOException {
	}

	public List<MultipartFile> getAttachments() {
		return null;
	}
}
