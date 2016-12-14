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

@Data
public abstract class EspdCriterion {

	private AvailableElectronically availableElectronically = new AvailableElectronically();

    protected Boolean exists;

    protected Boolean answer;

    public boolean getExists() {
        return Boolean.TRUE.equals(exists);
    }

    public abstract Boolean getAnswer();

	public final boolean getInfoElectronicallyAnswer() {
		return availableElectronically != null && Boolean.TRUE.equals(availableElectronically.getAnswer());
	}

	public final void setInfoElectronicallyAnswer(boolean answer) {
		if (availableElectronically != null) {
			availableElectronically.setAnswer(answer);
		}
	}

	public final String getInfoElectronicallyUrl() {
		if (availableElectronically != null) {
			return availableElectronically.getUrl();
		}
		return null;
	}

	public final void setInfoElectronicallyUrl(String url) {
		if (availableElectronically != null) {
			availableElectronically.setUrl(url);
		}
	}

	public final String getInfoElectronicallyCode() {
		if (availableElectronically != null) {
			return availableElectronically.getCode();
		}
		return null;
	}

	public final void setInfoElectronicallyCode(String code) {
		if (availableElectronically != null) {
			availableElectronically.setCode(code);
		}
	}

	public final String getInfoElectronicallyIssuer() {
		if (availableElectronically != null) {
			return availableElectronically.getIssuer();
		}
		return null;
	}

	public final void setInfoElectronicallyIssuer(String issuer) {
		if (availableElectronically != null) {
			availableElectronically.setIssuer(issuer);
		}
	}

}
