package eu.europa.ec.grow.espd.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Slf4j
@Component("messageSource")
public class EspdMessageSource extends AbstractMessageSource {

    private Messages messages;

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EspdMessageSource(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    protected MessageFormat resolveCode(String code, Locale locale) {
        String msg = messages.getMessage(code, locale);
        return createMessageFormat(msg, locale);
    }

    @PostConstruct
    public void init() {

        String i18nQuery = getI18NSqlQuery();

        log.info("Initializing message source using query '{}'.", i18nQuery);

        this.messages = jdbcTemplate.query(i18nQuery,
                new ResultSetExtractor<Messages>() {
                    @Override
                    public Messages extractData(ResultSet rs) throws SQLException, DataAccessException {

                        return extractI18NData(rs);
                    }
                });
    }

    /**
     * Returns sql query used to fetch the messages from the database.
     *
     * @return sql query string
     */
    private String getI18NSqlQuery() {
        return "select l.label_code as code, lang.language_code locale, t.label_translation_text as msg "
                + "from LABEL_TRANSLATION t, LABEL l, LANGUAGE lang "
                + "where lang.LANGUAGE_ID = t.LANGUAGE_ID and l.LABEL_ID = t.LABEL_ID";
    }

    /**
     * Extracts messages from the given result set.
     *
     * @param rs is a result set
     *
     * @return initialized {@link Messages} instance
     *
     * @throws SQLException        if a SQLException is encountered getting column values or
     *                             navigating (that is, there's no need to catch SQLException)
     * @throws DataAccessException in case of custom exceptions
     */
    private Messages extractI18NData(ResultSet rs) throws SQLException, DataAccessException {
        Messages messages = new Messages();
        while (rs.next()) {
            Locale locale = new Locale(rs.getString("locale"));
            messages.addMessage(rs.getString("code"), locale, rs.getString("msg"));
        }
        return messages;
    }

    /**
     * Messages bundle
     */
    private static final class Messages {

        /* <code, <locale, message>> */
        private Map<String, Map<Locale, String>> messages;

        public void addMessage(String code, Locale locale, String msg) {
            if (messages == null)
                messages = new HashMap<>();

            Map<Locale, String> data = messages.get(code);
            if (data == null) {
                data = new HashMap<>();
                messages.put(code, data);
            }

            data.put(locale, msg);
        }

        public String getMessage(String code, Locale locale) {
            Map<Locale, String> data = messages.get(code);
            return data != null ? data.get(locale) : null;
        }
    }

}