package main.java.com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class Filedaoimpl extends JdbcDaoSupport implements FileIdao  {

	@Override
	public String getPathFile(int n) {
		String sql = " select distinct f.pathFile from filepath f where f.idfilePath = '"
				+ n + "'";

		RowMapper mapper = new RowMapper() {
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString(1);
			}

		};

		List<String> strLst = getJdbcTemplate().query(sql, mapper);

		System.out.println(n + "dao");
		String l = (String) getJdbcTemplate().queryForObject(sql, String.class);
		return l;
	}

}
