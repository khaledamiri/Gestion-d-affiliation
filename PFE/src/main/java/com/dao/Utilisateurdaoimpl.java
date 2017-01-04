package main.java.com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import main.java.com.domaine.AdresseIp;
import main.java.com.domaine.Affectation;
import main.java.com.domaine.Utilisateur;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class Utilisateurdaoimpl extends JdbcDaoSupport implements
		UtilisateurIdao {

	@Override
	public List<Utilisateur> findAlluser() {

		String sql = "SELECT * FROM utilisateur u";

		RowMapper mapper = new RowMapper() {
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				Utilisateur utilisateur = new Utilisateur();

				utilisateur.setLogin((String) rs.getString("login"));
				utilisateur.setUrl((String) rs.getString("url"));
				utilisateur.setAdresse((String) rs.getString("adresse"));
				utilisateur.setEmail((String) rs.getString("email"));
				utilisateur.setTel((String) rs.getString("tel"));
				utilisateur.setFax((String) rs.getString("fax"));
				utilisateur.setCpv((String) rs.getString("cpv"));
				utilisateur.setNom((String) rs.getString("nom"));
				utilisateur.setPrenom((String) rs.getString("prenom"));
				utilisateur.setPassword((String) rs.getString("password"));
				utilisateur.setKey((String) rs.getString("key"));

				utilisateur.setEnabled((String) rs.getString("enabled"));
				utilisateur.setCategorie_url((String) rs
						.getString("categorie_url"));
				utilisateur.setDescription_url((String) rs
						.getString("description_url"));
				utilisateur.setCivilite((String) rs.getString("civilite"));
				utilisateur.setPays((String) rs.getString("pays"));
				utilisateur.setAffilie((String) rs.getString("affilie"));

				return utilisateur;
			}

		};

		return (List<Utilisateur>) getJdbcTemplate().query(sql, mapper);
	}

	@Override
	public List<Utilisateur> findAllusernoAffile() {
		String r = "ROLE_AFFILIE";
		String sql = "SELECT u.login, u.password, u.nom, u.prenom, u.key, u.cpv, u.tel, u.fax, u.email, u.adresse, u.url, u.enabled where u.login not in ( select a.login from authorities a where a.authority like '"
				+ r + "')";

		RowMapper mapper = new RowMapper() {
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				Utilisateur utilisateur = new Utilisateur();

				utilisateur.setLogin((String) rs.getString("login"));
				utilisateur.setUrl((String) rs.getString("url"));
				utilisateur.setAdresse(rs.getString("adresse"));
				utilisateur.setEmail(rs.getString("email"));
				utilisateur.setTel(rs.getString("tel"));
				utilisateur.setFax(rs.getString("fax"));
				utilisateur.setCpv(rs.getString("cpv"));
				utilisateur.setNom(rs.getString("nom"));
				utilisateur.setPrenom(rs.getString("prenom"));
				utilisateur.setPassword(rs.getString("password"));
				utilisateur.setKey(rs.getString("key"));

				utilisateur.setEnabled(rs.getString("enabled"));

				return utilisateur;
			}

		};

		return (List<Utilisateur>) getJdbcTemplate().query(sql, mapper);
	}

	@Override
	public List<Utilisateur> findAllaffilie() {

		String sql = "SELECT u.login, u.password, u.nom, u.prenom, u.key, u.cpv, u.tel, u.fax, u.email, u.adresse, u.url, u.enabled FROM pfe.utilisateur u,pfe.authorities a where u.login=a.login and a.authority like 'ROLE_AFFILIE';";

		RowMapper mapper = new RowMapper() {
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				Utilisateur utilisateur = new Utilisateur();

				utilisateur.setLogin((String) rs.getString("login"));
				utilisateur.setUrl((String) rs.getString("url"));
				utilisateur.setAdresse(rs.getString("adresse"));
				utilisateur.setEmail(rs.getString("email"));
				utilisateur.setTel(rs.getString("tel"));
				utilisateur.setFax(rs.getString("fax"));
				utilisateur.setCpv(rs.getString("cpv"));
				utilisateur.setNom(rs.getString("nom"));
				utilisateur.setPrenom(rs.getString("prenom"));
				utilisateur.setPassword(rs.getString("password"));
				utilisateur.setKey(rs.getString("key"));

				utilisateur.setEnabled(rs.getString("enabled"));

				return utilisateur;
			}

		};

		return (List<Utilisateur>) getJdbcTemplate().query(sql, mapper);
	}

	@Override
	public void Update(Utilisateur u) {
		String sql = "update utilisateur set  nom='" + u.getNom()
				+ "',prenom='" + u.getPrenom() + "',cpv='" + u.getCpv()
				+ "',tel='" + u.getTel() + "',fax='" + u.getFax() + "',email='"
				+ u.getEmail() + "',adresse='" + u.getAdresse() + "',url='"
				+ u.getUrl() + "',enabled='" + u.getEnabled()
				+ "' where login= '" + u.getLogin() + "'";

		getJdbcTemplate().execute(sql);
		return;

	}

	@Override
	public void delete(Utilisateur utilisateur) {
		String sql = "delete from utilisateur where login= '"
				+ utilisateur.getLogin() + "'";

		getJdbcTemplate().execute(sql);
		return;

	}

	public List<Utilisateur> finduserauthority() {

		String sql = "SELECT u.login ,u.url, u.adresse , u.tel, u.fax, u.email, u.cpv from UTILISATEUR u  where u.login NOT IN (select login from authorities )   ";

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
				utilisateur.setCpv(rs.getString("CPV"));

				return utilisateur;
			}

		};

		return (List<Utilisateur>) getJdbcTemplate().query(sql, mapper);
	}

	public void save(Affectation affect) {

		String sql = "insert into AUTHORITIES (LOGIN,AUTHORITY) values(?,?)";

		getJdbcTemplate().update(sql,
				new Object[] { affect.getPartner(), affect.getAuth() });

	}

	@Override
	public List<AdresseIp> findAllIpdate(String log) {
		String sql = "select a.idadresseIp, a.adresseIpaffilie, a.adresseIpClient, a.designBanniere,a.date from adresseip a order BY a.adresseIpaffilie where a.adresseIpaffilie like '"
				+ log + "'";

		// Mapping d'un enregistrement vers un ResultSet
		RowMapper mapper = new RowMapper() {
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				AdresseIp adresseIp = new AdresseIp();

				adresseIp.setIdadresseIp(rs.getInt("idadresseIp"));
				adresseIp.setAdresseIpaffilie((String) rs
						.getString("adresseIpaffilie"));
				adresseIp.setAdresseIpClient((String) rs
						.getString("adresseIpClient"));
				adresseIp.setDesignBanniere((String) rs
						.getString("designBanniere"));
				adresseIp.setDate((String) rs.getString("date"));

				return adresseIp;
			}

		};

		return (List<AdresseIp>) getJdbcTemplate().query(sql, mapper);
	}

	@Override
	public List<AdresseIp> findAllIp() {
		String sql = "select a.idadresseIp, a.adresseIpaffilie, a.adresseIpClient, a.designBanniere,a.date from adresseip a order BY a.adresseIpaffilie ";

		// Mapping d'un enregistrement vers un ResultSet
		RowMapper mapper = new RowMapper() {
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				AdresseIp adresseIp = new AdresseIp();

				adresseIp.setIdadresseIp(rs.getInt("idadresseIp"));
				adresseIp.setAdresseIpaffilie((String) rs
						.getString("adresseIpaffilie"));
				adresseIp.setAdresseIpClient((String) rs
						.getString("adresseIpClient"));
				adresseIp.setDesignBanniere((String) rs
						.getString("designBanniere"));
				adresseIp.setDate((String) rs.getString("date"));

				return adresseIp;
			}

		};

		return (List<AdresseIp>) getJdbcTemplate().query(sql, mapper);
	}

	public Integer getCommission(String affilieIp) {
		String sql = "SELECT count(adresseIpClient) FROM adresseip a where adresseIpaffilie like '"
				+ affilieIp + "'";
		Integer l = (Integer) getJdbcTemplate().queryForObject(sql,
				Integer.class);
		return l;
	}

	public String getPeriode(String ann) {
		String sql = "SELECT periode FROM pfe.affectation_bann a where periode > CURDATE() and announceur like '"
				+ ann + "'";

		try {
			return (String) getJdbcTemplate().queryForObject(sql, String.class);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	
	public String getCommission2(String affilieIp) {
		String sql = "SELECT count(adresseIpClient) FROM adresseip a where adresseIpaffilie like '"
				+ affilieIp + "'";

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
	public void saveutilisateur(Utilisateur utilisateur) {
		String sql = "insert into utilisateur (login, password,nom ,prenom,tel,fax,email,adresse,url,categorie_url,description_url,civilite,pays,pwd_lost) values(?,?,?,?,?,?,?,?,?,?,?,?,?,'"
				+ generateRandomString() + "')";

		getJdbcTemplate().update(
				sql,
				new Object[] { utilisateur.getLogin(),
						utilisateur.getPassword(), utilisateur.getNom(),
						utilisateur.getPrenom(), utilisateur.getTel(),
						utilisateur.getFax(), utilisateur.getEmail(),
						utilisateur.getAdresse(), utilisateur.getUrl(),
						utilisateur.getCategorie_url(),
						utilisateur.getDescription_url(),
						utilisateur.getCivilite(), utilisateur.getPays() });

	}
	@Override
	public void saveutilisateurAff(Utilisateur utilisateur) {
		String aff="aff";
		String sql = "insert into utilisateur (login, password,nom ,prenom,tel,fax,email,adresse,url,categorie_url,description_url,civilite,pays,pwd_lost,affilie) values(?,?,?,?,?,?,?,?,?,?,?,?,?,'"
				+ generateRandomString() + "','"+aff+"')";

		getJdbcTemplate().update(
				sql,
				new Object[] { utilisateur.getLogin(),
						utilisateur.getPassword(), utilisateur.getNom(),
						utilisateur.getPrenom(), utilisateur.getTel(),
						utilisateur.getFax(), utilisateur.getEmail(),
						utilisateur.getAdresse(), utilisateur.getUrl(),
						utilisateur.getCategorie_url(),
						utilisateur.getDescription_url(),
						utilisateur.getCivilite(), utilisateur.getPays() });

	}
	
	@Override
	public List<AdresseIp> findIpByName(String name) {
		String sql = "select a.idadresseIp, a.adresseIpaffilie, a.adresseIpClient, a.designBanniere,a.date FROM pfe.adresseip a where adresseIpaffilie='"
				+ name + "'";

		// Mapping d'un enregistrement vers un ResultSet
		RowMapper mapper = new RowMapper() {
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				AdresseIp adresseIp = new AdresseIp();

				adresseIp.setIdadresseIp(rs.getInt("idadresseIp"));
				adresseIp.setAdresseIpaffilie((String) rs
						.getString("adresseIpaffilie"));
				adresseIp.setAdresseIpClient((String) rs
						.getString("adresseIpClient"));
				adresseIp.setDesignBanniere((String) rs
						.getString("designBanniere"));
				adresseIp.setDate((String) rs.getString("date"));

				return adresseIp;
			}

		};

		return (List<AdresseIp>) getJdbcTemplate().query(sql, mapper);
	}

	@Override
	public List<AdresseIp> findIpByNamee(String name, String du, String au) {
		String sql = "select a.idadresseIp, a.adresseIpaffilie, a.adresseIpClient, a.designBanniere,a.date FROM pfe.adresseip a where a.adresseIpaffilie='"
				+ name
				+ "' and date IN (select date from pfe.adresseip a where date between '"
				+ du + "' and '" + au + "' )";

		// Mapping d'un enregistrement vers un ResultSet
		RowMapper mapper = new RowMapper() {
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				AdresseIp adresseIp = new AdresseIp();

				adresseIp.setIdadresseIp(rs.getInt("idadresseIp"));
				adresseIp.setAdresseIpaffilie((String) rs
						.getString("adresseIpaffilie"));
				adresseIp.setAdresseIpClient((String) rs
						.getString("adresseIpClient"));
				adresseIp.setDesignBanniere((String) rs
						.getString("designBanniere"));
				adresseIp.setDate((String) rs.getString("date"));

				return adresseIp;
			}

		};

		return (List<AdresseIp>) getJdbcTemplate().query(sql, mapper);
	}

	@Override
	public String getCommission2(String affilieIp, String duDate, String auDate) {
		String sql = "SELECT count(adresseIpClient) FROM adresseip a where adresseIpaffilie like '"
				+ affilieIp
				+ "' and date IN (select date from pfe.adresseip a where date between '"
				+ duDate + "' and '" + auDate + "' )";
		
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
	public Integer sizeUtilisateur() {
		String sql = "SELECT count(login) FROM utilisateur u;";
		Integer l = (Integer) getJdbcTemplate().queryForObject(sql,
				Integer.class);
		return l;

	}

	public String getlogin(String log) {

		String sql = "select distinct u.login from utilisateur u,  authorities a where u.login ='"
				+ log
				+ "' and a.authority = 'ROLE_AFFILIE ' and u.login=a.login ";

		System.out.println(log);

		try {
			return (String) getJdbcTemplate().queryForObject(sql, String.class);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}

	// Random

	private static final String CHAR_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	private static final int RANDOM_STRING_LENGTH = 10;

	/**
	 * This method generates random string
	 * 
	 * @return
	 */
	public String generateRandomString() {

		StringBuffer randStr = new StringBuffer();
		for (int i = 0; i < RANDOM_STRING_LENGTH; i++) {
			int number = getRandomNumber();
			char ch = CHAR_LIST.charAt(number);
			randStr.append(ch);
		}
		return randStr.toString();
	}

	/**
	 * This method generates random Number
	 * 
	 * @return
	 */
	private int getRandomNumber() {
		int randomInt = 0;
		Random randomGenerator = new Random();
		randomInt = randomGenerator.nextInt(CHAR_LIST.length());
		if (randomInt - 1 == -1) {
			return randomInt;
		} else {
			return randomInt - 1;
		}
	}

	@Override
	public String searchMail(String m) {
		String sql = "select distinct u.email from utilisateur u where u.email='"
				+ m + "'  ";

		try {
			return (String) getJdbcTemplate().queryForObject(sql, String.class);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}

	@Override
	public String getPwd_lost(String mail) {
		String sql = "select distinct u.password from utilisateur u where u.email='"
				+ mail + "'  ";

		try {
			return (String) getJdbcTemplate().queryForObject(sql, String.class);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Utilisateur> finduserbyname(String login) {

		String sql = "SELECT u.login, u.password, u.nom, u.prenom, u.key, u.cpv, u.tel, u.fax, u.email, u.adresse, u.url, u.enabled FROM pfe.utilisateur u where u.login='"
				+ login + "';";

		RowMapper mapper = new RowMapper() {
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				Utilisateur utilisateur = new Utilisateur();

				utilisateur.setLogin((String) rs.getString("login"));
				utilisateur.setUrl((String) rs.getString("url"));
				utilisateur.setAdresse(rs.getString("adresse"));
				utilisateur.setEmail(rs.getString("email"));
				utilisateur.setTel(rs.getString("tel"));
				utilisateur.setFax(rs.getString("fax"));
				utilisateur.setCpv(rs.getString("cpv"));
				utilisateur.setNom(rs.getString("nom"));
				utilisateur.setPrenom(rs.getString("prenom"));
				utilisateur.setPassword(rs.getString("password"));
				utilisateur.setKey(rs.getString("key"));

				utilisateur.setEnabled(rs.getString("enabled"));

				return utilisateur;
			}

		};

		return (List<Utilisateur>) getJdbcTemplate().query(sql, mapper);
	}

}
