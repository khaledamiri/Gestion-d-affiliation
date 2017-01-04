package main.java.com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import main.java.com.domaine.Banniere;
import main.java.com.domaine.Bannierecss;
import main.java.com.domaine.Categorie;
import main.java.com.domaine.Utilisateur;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class Bannieredaoimpl extends JdbcDaoSupport implements BannierIdao {

	public void savebann(Banniere bann) throws DataAccessException {

		String sql = "INSERT INTO BANNIERE (DESIGN_BANN,PRIX_BANN,URL_BANN,LIEN_BANN,TAILLE_BANN,prix_promo) VALUES (?,?,?,?,?,?)";// ,idCategorie

		getJdbcTemplate().update(
				sql,
				new Object[] { bann.getDESIGN_BANN(), bann.getPRIX_BANN(),
						bann.getURL_BANN(), bann.getLIEN_DE_BANN(),
						bann.getTAILLE_BANN(), bann.getPRIX_PROMO() });
	}

	@Override
	public void saveCssbann(Bannierecss bann) {
		String sql = "INSERT INTO bannierecss(background_color, width, height, color, text_align, line_height, margin_left, text_decoration, text_transform, font_style, font_family,idbann)"
				+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
		getJdbcTemplate().update(
				sql,
				new Object[] { bann.getBackground_color(), bann.getWidth(),
						bann.getHeight(), bann.getColor(),
						bann.getText_align(), bann.getLine_height(),
						bann.getMargin_left(), bann.getText_decoration(),
						bann.getText_transform(), bann.getFont_style(),
						bann.getFont_family(), bann.getIdBann() });

	}

	public String selectBannbynom(int bannId) throws DataAccessException {

		String sql = "SELECT design_bann FROM BANNIERE  WHERE idBanniere = "
				+ bannId;

		return (String) getJdbcTemplate().queryForObject(sql, String.class);
	}

	@Override
	public void Update(Banniere b) {
		String sql = "update BANNIERE set  design_bann='" + b.getDESIGN_BANN()
				+ "',prix_bann='" + b.getPRIX_BANN() + "',url_bann='"
				+ b.getURL_BANN() + "',lien_bann='" + b.getLIEN_DE_BANN()
				+ "',taille_bann='" + b.getTAILLE_BANN() + "',prix_promo='"
				+ b.getPRIX_PROMO() + "' where design_bann='"
				+ b.getDESIGN_BANN() + "'";

		getJdbcTemplate().execute(sql);
		return;

	}

	@Override
	public void delete(Banniere banniere) {
		// TODO Auto-generated method stub

		String sql = "delete from banniere where idBanniere= '"
				+ banniere.getID_BANN() + "'";

		getJdbcTemplate().execute(sql);
		return;
	}

	@SuppressWarnings("unchecked")
	public List<Banniere> findAll() {

		String sql = "select B.idBanniere, B.DESIGN_BANN,B.PRIX_BANN,B.URL_BANN,B.LIEN_BANN,B.TAILLE_BANN,B.prix_promo from BANNIERE B";

		// Mapping d'un enregistrement vers un ResultSet
		RowMapper mapper = new RowMapper() {
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				Banniere banniere = new Banniere();

				banniere.setID_BANN(rs.getInt("idBanniere"));
				banniere.setDESIGN_BANN((String) rs.getString("DESIGN_BANN"));
				banniere.setPRIX_BANN((String) rs.getString("PRIX_BANN"));
				banniere.setURL_BANN((String) rs.getString("URL_BANN"));
				banniere.setLIEN_DE_BANN((String) rs.getString("LIEN_BANN"));
				banniere.setTAILLE_BANN((String) rs.getString("TAILLE_BANN"));
				banniere.setPRIX_PROMO((String) rs.getString("prix_promo"));

				return banniere;
			}

		};

		return (List<Banniere>) getJdbcTemplate().query(sql, mapper);

	}

	public int selectidbannbylogin() {// A Refaire si on fait l'authentification

		String sql = "select B.idBanniere from BANNIERE B;";

		return getJdbcTemplate().queryForInt(sql);
	}

	@Override
	public String getMaxPromo() {

		String sql = "select MIN(prix_promo) from BANNIERE";// where
															// b.prix_promo
															// =(select
															// min(b.prix_promo)
															// from banniere
															// b);";

		String l = (String) getJdbcTemplate().queryForObject(sql, String.class);
		
		return l;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> findCategories() {
		String sql = "SELECT typeCategorie from categorie  ";

		// Mapping d'un enregistrement vers un ResultSet
		RowMapper mapper = new RowMapper() {
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				Categorie cat = new Categorie();

				cat.setTypeCategorie((String) rs.getString("typeCategorie"));

				return cat;
			}

		};
		return (List<String>) getJdbcTemplate().query(sql, mapper);
	}

	@Override
	public String getDESIGN_BANN(Integer a) {

		String sql = "select distinct  b.design_bann from BANNIERE b  where b.idBanniere ='"
				+ a + "'";

		RowMapper mapper = new RowMapper() {
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString(1);
			}

		};

		List<String> strLst = getJdbcTemplate().query(sql, mapper);

		String l = (String) getJdbcTemplate().queryForObject(sql, String.class);
		return l;

	}

	public String getPRIX_BANN(Integer a, String design) {
		String sql = "select distinct  b.prix_bann from BANNIERE b,utilisateur u where u.key ='"
				+ a + "' and design_bann like '" + design + "' ";
		RowMapper mapper = new RowMapper() {
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString(1);
			}

		};

		List<String> strLst = getJdbcTemplate().query(sql, mapper);

		String l = (String) getJdbcTemplate().queryForObject(sql, String.class);
		return l;
	}

	public String getPRIX_PROMO(Integer a, String design) {
		String sql = " select distinct b.prix_promo from BANNIERE b,utilisateur u where u.key = '"
				+ a + "' and design_bann like '" + design + "' ";
		RowMapper mapper = new RowMapper() {
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString(1);
			}

		};

		List<String> strLst = getJdbcTemplate().query(sql, mapper);

		String l = (String) getJdbcTemplate().queryForObject(sql, String.class);
		return l;
	}

	public String getLIEN_DE_BANN(Integer a, String design) {
		String sql = " select distinct b.lien_bann from BANNIERE b,utilisateur u where u.key = '"
				+ a + "' and design_bann like '" + design + "' ";

		RowMapper mapper = new RowMapper() {
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString(1);
			}

		};

		List<String> strLst = getJdbcTemplate().query(sql, mapper);

		String l = (String) getJdbcTemplate().queryForObject(sql, String.class);
		return l;
	}

	@Override
	public String getMaxPromoselonCategorie(Categorie c) {
		String sql = "SELECT Min(prix_promo) FROM banniere ,categorie    WHERE  categorie.typeCategorie like '"
				+ c.getTypeCategorie() + "'";

		String l = (String) getJdbcTemplate().queryForObject(sql, String.class);

		return l;
	}

	@Override
	public Integer sizeBanniere() {
		String sql = "SELECT count(b.design_bann) FROM banniere b";
		Integer l = (Integer) getJdbcTemplate().queryForObject(sql,
				Integer.class);
		return l;
	}

	public int saveIp(String nomaff, String userip, String designID, String date) {

		String sql = "INSERT INTO adresseip ( adresseIpaffilie, adresseIpClient, designBanniere,date) VALUES ('"
				+ nomaff
				+ "','"
				+ userip
				+ "','"
				+ designID
				+ "','"
				+ date
				+ "')";

		int l = getJdbcTemplate().update(sql);
		return l;

	}

	@Override
	public List<Utilisateur> getKeys() {
		String sql = "SELECT  u.key FROM utilisateur u";

		RowMapper mapper = new RowMapper() {
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				Utilisateur utilisateur = new Utilisateur();

				utilisateur.setKey((String) rs.getString("key"));

				return utilisateur;
			}

		};

		return (List<Utilisateur>) getJdbcTemplate().query(sql, mapper);
	}

	public String getTaille_Bann(Integer a) {
		String sql = "select taille_bann from BANNIERE where idBanniere = '"
				+ a + "'";
		RowMapper mapper = new RowMapper() {
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString(1);
			}

		};

		List<String> strLst = getJdbcTemplate().query(sql, mapper);

		String l = (String) getJdbcTemplate().queryForObject(sql, String.class);
		return l;
	}

	public String getNomAffSelonKey(String key) {
		String sql = "select login from utilisateur u where u.key = '" + key
				+ "'";
		RowMapper mapper = new RowMapper() {
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString(1);
			}

		};

		List<String> strLst = getJdbcTemplate().query(sql, mapper);

		String l = (String) getJdbcTemplate().queryForObject(sql, String.class);
		return l;
	}

	@Override
	public String getUrl_Bann(Integer a, String design) {
		String sql = " select distinct b.url_bann from BANNIERE b,utilisateur u where u.key = '"
				+ a + "' and design_bann like '" + design + "' ";

		RowMapper mapper = new RowMapper() {
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString(1);
			}

		};

		List<String> strLst = getJdbcTemplate().query(sql, mapper);

		String l = (String) getJdbcTemplate().queryForObject(sql, String.class);
		return l;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Bannierecss> findAllbannCss() {

		String sql = "select  b.idbannierecss, b.background_color, b.width, b.height, b.color, b.text_align, b.line_height, b.margin_left, b.text_decoration, b.text_transform, b.font_style, b.font_family, b.idbann from bannierecss b ";

		// Mapping d'un enregistrement vers un ResultSet
		RowMapper mapper = new RowMapper() {
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				Bannierecss banniere = new Bannierecss();

				banniere.setIdbanncss(rs.getInt("idbannierecss"));
				banniere.setBackground_color((String) rs
						.getString("background_color"));
				banniere.setWidth((String) rs.getString("width"));
				banniere.setHeight((String) rs.getString("height"));
				banniere.setColor((String) rs.getString("color"));
				banniere.setText_align((String) rs.getString("text_align"));
				banniere.setLine_height((String) rs.getString("line_height"));
				banniere.setMargin_left((String) rs.getString("margin_left"));
				banniere.setText_decoration((String) rs
						.getString("text_decoration"));
				banniere.setText_transform((String) rs
						.getString("text_transform"));
				banniere.setFont_style((String) rs.getString("font_style"));
				banniere.setFont_family((String) rs.getString("font_family"));
				banniere.setIdBann((String) rs.getString("idbann"));
				// banniere.setDESIGN_BANN((String)
				// rs.getString("design_bann"));

				return banniere;
			}

		};

		return (List<Bannierecss>) getJdbcTemplate().query(sql, mapper);

	}

	@Override
	public void Update2(Bannierecss b) {
		String sql = "update bannierecss set  background_color='"
				+ b.getBackground_color() + "',width='" + b.getWidth()
				+ "',height='" + b.getHeight() + "',color='" + b.getColor()
				+ "',text_align='" + b.getText_align() + "',line_height='"
				+ b.getLine_height() + "',margin_left='" + b.getMargin_left()
				+ "',text_decoration='" + b.getText_decoration()
				+ "',text_transform='" + b.getText_transform()
				+ "',font_style='" + b.getFont_style() + "',font_family='"
				+ b.getFont_family() + "',idbann='" + b.getSelectbanner()
				+ "' " + " where idbannierecss= '" + b.getIdbanncss()
				+ "'";

		getJdbcTemplate().execute(sql);
		return;

	}

	@Override
	public void delete2(Bannierecss banniere) {
		String sql = "delete from bannierecss where idbannierecss= '"
				+ banniere.getIdbanncss() + "'";

		getJdbcTemplate().execute(sql);
		return;

	}

	@Override
	public String background_color(Integer a, String design) {
		String sql = " select distinct b.background_color from bannierecss b,utilisateur u where u.key = '"
				+ a + "' and idbann like '" + design + "' ";

		RowMapper mapper = new RowMapper() {
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString(1);
			}

		};

		List<String> strLst = getJdbcTemplate().query(sql, mapper);

		String l = (String) getJdbcTemplate().queryForObject(sql, String.class);
		return l;
	}

	@Override
	public String width(Integer a, String design) {
		String sql = " select distinct b.width from bannierecss b,utilisateur u where u.key = '"
				+ a + "' and idbann like '" + design + "' ";

		RowMapper mapper = new RowMapper() {
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString(1);
			}

		};

		List<String> strLst = getJdbcTemplate().query(sql, mapper);

		String l = (String) getJdbcTemplate().queryForObject(sql, String.class);
		return l;
	}

	@Override
	public String height(Integer a, String design) {
		String sql = " select distinct b.height from bannierecss b,utilisateur u where u.key = '"
				+ a + "' and idbann like '" + design + "' ";

		RowMapper mapper = new RowMapper() {
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString(1);
			}

		};

		List<String> strLst = getJdbcTemplate().query(sql, mapper);

		String l = (String) getJdbcTemplate().queryForObject(sql, String.class);
		return l;
	}

	@Override
	public String color(Integer a, String design) {
		String sql = " select distinct b.color from bannierecss b,utilisateur u where u.key = '"
				+ a + "' and idbann like '" + design + "' ";

		RowMapper mapper = new RowMapper() {
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString(1);
			}

		};

		List<String> strLst = getJdbcTemplate().query(sql, mapper);

		String l = (String) getJdbcTemplate().queryForObject(sql, String.class);
		return l;
	}

	@Override
	public String text_align(Integer a, String design) {
		String sql = " select distinct b.text_align from bannierecss b,utilisateur u where u.key = '"
				+ a + "' and idbann like '" + design + "' ";

		RowMapper mapper = new RowMapper() {
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString(1);
			}

		};

		List<String> strLst = getJdbcTemplate().query(sql, mapper);

		String l = (String) getJdbcTemplate().queryForObject(sql, String.class);
		return l;
	}

	@Override
	public String line_height(Integer a, String design) {
		String sql = " select distinct b.line_height from bannierecss b,utilisateur u where u.key = '"
				+ a + "' and idbann like '" + design + "' ";

		RowMapper mapper = new RowMapper() {
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString(1);
			}

		};

		List<String> strLst = getJdbcTemplate().query(sql, mapper);

		String l = (String) getJdbcTemplate().queryForObject(sql, String.class);
		return l;
	}

	@Override
	public String margin_left(Integer a, String design) {
		String sql = " select distinct b.margin_left from bannierecss b,utilisateur u where u.key = '"
				+ a + "' and idbann like '" + design + "' ";

		RowMapper mapper = new RowMapper() {
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString(1);
			}

		};

		List<String> strLst = getJdbcTemplate().query(sql, mapper);

		String l = (String) getJdbcTemplate().queryForObject(sql, String.class);
		return l;
	}

	@Override
	public String text_decoration(Integer a, String design) {
		String sql = " select distinct b.text_decoration from bannierecss b,utilisateur u where u.key = '"
				+ a + "' and idbann like '" + design + "' ";

		RowMapper mapper = new RowMapper() {
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString(1);
			}

		};

		List<String> strLst = getJdbcTemplate().query(sql, mapper);

		String l = (String) getJdbcTemplate().queryForObject(sql, String.class);
		return l;
	}

	@Override
	public String text_transform(Integer a, String design) {
		String sql = " select distinct b.text_transform from bannierecss b,utilisateur u where u.key = '"
				+ a + "' and idbann like '" + design + "' ";

		RowMapper mapper = new RowMapper() {
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString(1);
			}

		};

		List<String> strLst = getJdbcTemplate().query(sql, mapper);

		String l = (String) getJdbcTemplate().queryForObject(sql, String.class);
		return l;
	}

	@Override
	public String font_style(Integer a, String design) {
		String sql = " select distinct b.font_style from bannierecss b,utilisateur u where u.key = '"
				+ a + "' and idbann like '" + design + "' ";

		RowMapper mapper = new RowMapper() {
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString(1);
			}

		};

		List<String> strLst = getJdbcTemplate().query(sql, mapper);

		String l = (String) getJdbcTemplate().queryForObject(sql, String.class);
		return l;
	}

	@Override
	public String font_family(Integer a, String design) {
		String sql = " select distinct b.font_family from bannierecss b,utilisateur u where u.key = '"
				+ a + "' and idbann like '" + design + "' ";

		RowMapper mapper = new RowMapper() {
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString(1);
			}

		};

		List<String> strLst = getJdbcTemplate().query(sql, mapper);

		String l = (String) getJdbcTemplate().queryForObject(sql, String.class);
		return l;
	}

	@Override
	public String getLastPeriod(String design) {
		String sql = "select a.periode from affectation_bann a where a.banniere = '" + design
				+ "'";
		RowMapper mapper = new RowMapper() {
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString(1);
			}

		};

		List<String> strLst = getJdbcTemplate().query(sql, mapper);

		String l = (String) getJdbcTemplate().queryForObject(sql, String.class);
		return l;
	}
	
	
	public String getBannier() {
		String sql = "SELECT design_bann,lien_bann FROM pfe.banniere a";

		try {
			return (String) getJdbcTemplate().queryForObject(sql, String.class);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

}
