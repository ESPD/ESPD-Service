package eu.grow.espd.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component("locales")
public class Locales {
	
	private Languages languages;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@PostConstruct
	public void init() {

		String langsQuery = "select language_id id, language_code code, language_name name from LANGUAGE";

		log.info("Initializing locales with query '{}'.", langsQuery);

		this.languages = jdbcTemplate.query(langsQuery,
				new ResultSetExtractor<Languages>() {
					@Override public Languages extractData(ResultSet rs) throws SQLException, DataAccessException {
						return extractLangsData(rs);
					}
				});
	}

	protected Languages extractLangsData(ResultSet rs) throws SQLException, DataAccessException {
		Languages languages = new Languages();
		while (rs.next()) {
			languages.addLocale(rs.getInt("id"), rs.getString("code"), rs.getString("name"));
		}
		return languages;
	}
	
	/**
	 * 
	 * Languages bundle
	 */
	protected static final class Languages {
		
		public Languages() {}

		private Map<String, String> nameByCode = new HashMap<>();

		private List<String> langNames = new ArrayList<>();
		private List<String> langCodes = new ArrayList<>();

		public void addLocale(Integer id, String code, String name) {
			if(!nameByCode.containsKey(code)) {
				nameByCode.put(code, name);
				langNames.add(name);
				langCodes.add(code);
			}
		}
	}

	public List<String> getCodes() {
		return languages.langCodes;
	}

	public List<String> getNames() {
		return languages.langCodes;
	}

	public Map<String, String> getNameByCode() {
		return languages.nameByCode;
	}
	
}
