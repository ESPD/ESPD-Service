package eu.grow.espd.config;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import eu.grow.espd.util.DatabaseMessageSourceBase;

@Component("messageSource")
public class EspdMessageSource extends DatabaseMessageSourceBase {
	
	private static final String I18N_QUERY = "select l.label_code as code, lang.language_code locale, t.label_translation_text as msg from LABEL_TRANSLATION t, LABEL l, LANGUAGE lang where lang.LANGUAGE_ID = t.LANGUAGE_ID and l.LABEL_ID = t.LABEL_ID";

	@Override protected String getI18NSqlQuery() {
		return I18N_QUERY;
	}

	@Override
	protected Messages extractI18NData(ResultSet rs) throws SQLException, DataAccessException {
		Messages messages = new Messages();
		while (rs.next()) {
			Locale locale = new Locale(rs.getString("locale"));
			messages.addMessage(rs.getString("code"), locale, rs.getString("msg"));
		}
		return messages;
	}
}
