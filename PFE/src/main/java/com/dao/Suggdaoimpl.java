package main.java.com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import main.java.com.domaine.Suggestion;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class Suggdaoimpl extends JdbcDaoSupport implements SuggIdao {

	@Override
	public void savesugg(Suggestion sugg) {
		
		String sql = "insert into suggestions (name,email,message,time,situation) values(?,?,?,?,?)";

		getJdbcTemplate().update(
				sql,
				new Object[] { sugg.getName(), sugg.getEmail(), sugg.getMsg(),
						sugg.getDate(),sugg.getSituation() });

	}

	@Override
	public void delete(Suggestion sugg) {
		String sql = "delete from suggestions where idSuggestions= '" + sugg.getIdsug()
				+ "'";

		getJdbcTemplate().execute(sql);
		return;

	}

	@Override
	public List<Suggestion> findAllsugg() {

		String sql = "SELECT s.idSuggestions,  s.name, s.email, s.message,s.time,s.situation FROM pfe.suggestions s;";

		RowMapper mapper = new RowMapper() {
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				Suggestion sugg = new Suggestion();
				
				sugg.setIdsug((int) rs.getInt("idSuggestions"));
				sugg.setName((String) rs.getString("name"));
				sugg.setEmail((String) rs.getString("email"));
				sugg.setMsg((String) rs.getString("message"));
				sugg.setDate((String) rs.getString("time"));
				sugg.setSituation((String) rs.getString("situation"));
				

				return sugg;
			}

		};

		return (List<Suggestion>) getJdbcTemplate().query(sql, mapper);
	}

	@Override
	public Integer tailleemail() {

		String sql = "SELECT count(s.name) FROM suggestions s;";
		Integer l = (Integer) getJdbcTemplate().queryForObject(sql,
				Integer.class);
		return l;

	}

}
