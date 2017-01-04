package main.java.com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import main.java.com.domaine.Affectation;
import main.java.com.domaine.Banniere;
import main.java.com.domaine.Utilisateur;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class Affectationdaoimpl extends JdbcDaoSupport implements
		AffectationIdao {

	
	public void savebann(Banniere bann) throws DataAccessException {

		String sql = "INSERT INTO BANNIERE (DESIGN_BANN,PRIX_BANN,URL_BANN,LIEN_BANN,TAILLE_BANN,prix_promo) VALUES (?,?,?,?,?,?)";// ,idCategorie

		getJdbcTemplate().update(
				sql,
				new Object[] { bann.getDESIGN_BANN(), bann.getPRIX_BANN(),
						bann.getURL_BANN(), bann.getLIEN_DE_BANN(),
						bann.getTAILLE_BANN(), bann.getPRIX_PROMO() });
	}
	
	@Override
	public void save(Affectation affect) {
		String sql = "insert into affectation_bann (announceur,banniere,date_saissie, periode, etat_affect) values(?,?,?,?,?)";

		// On r�cup�re et on utilisera directement le jdbcTemplate
		// Affectation user = new Affectation();
		getJdbcTemplate().update(sql,
				new Object[] {  affect.getPartner(),affect.getAuth(),affect.getDate_saissie(),affect.getPeriode(),affect.getEtat_affect()});
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<Banniere> findAll() {
		// TODO Auto-generated method stub
		String sql = "select B.DESIGN_BANN,B.PRIX_BANN,B.URL_BANN,B.LIEN_BANN,B.TAILLE_BANN,B.prix_promo from BANNIERE B";

		// Mapping d'un enregistrement vers un ResultSet
		RowMapper mapper = new RowMapper() {
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				Banniere banniere = new Banniere();
				
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
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Utilisateur> findAllannounceur() {
		//A.idAffectation_role=U.idUtilisateur and 
		String sql = "select U.LOGIN,U.URL,U.ADRESSE,U.EMAIL,U.TEL,U.FAX,U.PASSWORD,U.CPV from UTILISATEUR U ,authorities A where A.authority='ROLE_AFFILIE'and A.login=U.login";

		// Mapping d'un enregistrement vers un ResultSet
		RowMapper mapper = new RowMapper() {
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				Utilisateur utilisateur = new Utilisateur();

				utilisateur.setLogin((String) rs.getString("LOGIN"));
				utilisateur.setUrl((String) rs.getString("URL"));
				utilisateur.setAdresse(rs.getString("ADRESSE"));
				utilisateur.setEmail(rs.getString("EMAIL"));
				utilisateur.setTel(rs.getString("TEL"));
				utilisateur.setFax(rs.getString("FAX"));
				utilisateur.setPassword(rs.getString("PASSWORD"));
				utilisateur.setCpv(rs.getString("CPV"));

				return utilisateur;
			}

		};

		return (List<Utilisateur>) getJdbcTemplate().query(sql, mapper);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Utilisateur> findAlluserr() {
		
		String sql = "select U.LOGIN,U.URL,U.ADRESSE,U.EMAIL,U.TEL,U.FAX,U.PASSWORD,U.CPC,U.CPV from UTILISATEUR U ";

		// Mapping d'un enregistrement vers un ResultSet
		RowMapper mapper = new RowMapper() {
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				Utilisateur utilisateur = new Utilisateur();

				utilisateur.setLogin((String) rs.getString("LOGIN"));
				utilisateur.setUrl((String) rs.getString("URL"));
				utilisateur.setAdresse(rs.getString("ADRESSE"));
				utilisateur.setEmail(rs.getString("EMAIL"));
				utilisateur.setTel(rs.getString("TEL"));
				utilisateur.setFax(rs.getString("FAX"));
				utilisateur.setPassword(rs.getString("PASSWORD"));
				utilisateur.setCpv(rs.getString("CPV"));

				return utilisateur;
			}

		};

		return (List<Utilisateur>) getJdbcTemplate().query(sql, mapper);
}

	@Override
	public List<Banniere> findAllien() {

		String sql = "select B.ID_BANN,B.DESIGN_BANN,B.PRIX_BANN,B.URL_BANN,B.LIEN_DE_BANN,B.TAILLE_BANN from BANNIERE B";

		// // Mapping d'un enregistrement vers un ResultSet
		RowMapper mapper = new RowMapper() {
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				Banniere banniere = new Banniere();

				banniere.setID_BANN(rs.getInt("ID_BANN"));
				banniere.setDESIGN_BANN((String) rs.getString("DESIGN_BANN"));
				banniere.setPRIX_BANN((String) rs.getString("PRIX_BANN"));
				banniere.setURL_BANN((String) rs.getString("URL_BANN"));
				banniere.setLIEN_DE_BANN((String) rs.getString("LIEN_DE_BANN"));
				banniere.setTAILLE_BANN((String) rs.getString("TAILLE_BANN"));

				return banniere;
			}

		};

		return (List<Banniere>) getJdbcTemplate().query(sql, mapper);

		
		
		
	}

	@Override
	public List<Affectation> findAllAffect() {
		String sql = "SELECT idAffectation_bann, announceur, banniere, date_saissie, periode, etat_affect FROM pfe.affectation_bann a;";

		// Mapping d'un enregistrement vers un ResultSet
		RowMapper mapper = new RowMapper() {
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				Affectation aff = new Affectation();
				
				
				aff.setId_affect(rs.getInt("idAffectation_bann"));
				aff.setPartner((String) rs.getString("announceur"));
				aff.setAuth((String) rs.getString("banniere"));
				aff.setDate_saissie((String) rs.getString("date_saissie"));
				aff.setPeriode((String) rs.getString("periode"));
				aff.setEtat_affect((String) rs.getString("etat_affect"));
				return aff;
			}

		};

		return (List<Affectation>) getJdbcTemplate().query(sql, mapper);
	}

	@Override
	public void delete(Affectation aff) {
		
			// TODO Auto-generated method stub

			String sql = "delete from affectation_bann where idAffectation_bann= '"
					+ aff.getId_affect() + "'";

			getJdbcTemplate().execute(sql);
			return;
		
		
	}

	
}
