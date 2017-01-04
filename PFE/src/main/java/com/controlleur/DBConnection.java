package main.java.com.controlleur;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DBConnection {
	/**
	 * Method to create DB Connection
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("finally")
	public static Connection createConnection() throws Exception {
		Connection con = null;
		try {
			Class.forName(Constants.dbClass);
			con = DriverManager.getConnection(Constants.dbUrl,
					Constants.dbUser, Constants.dbPwd);
		} catch (Exception e) {
			throw e;
		} finally {
			return con;
		}
	}

	/**
	 * Method to check whether uname and pwd combination are correct
	 * 
	 * @param uname
	 * @param pwd
	 * @return
	 * @throws Exception
	 */
	public static boolean checkLogin(String login, String password)
			throws Exception {
		boolean isUserAvailable = false;
		Connection dbConn = null;
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Statement stmt = dbConn.createStatement();
			String query = "SELECT * FROM utilisateur WHERE login = '" + login
					+ "' AND password=" + "'" + password + "'";
			// System.out.println(query);
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				// System.out.println(rs.getString(1) + rs.getString(2) +
				// rs.getString(3));
				isUserAvailable = true;
			}
		} catch (SQLException sqle) {
			throw sqle;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		return isUserAvailable;
	}

	/**
	 * Method to insert uname and pwd in DB
	 * 
	 * @param name
	 * @param uname
	 * @param pwd
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public static boolean insertUser(String login, String password, String nom,
			String prenom, String tel, String fax, String email,
			String adresse, String url, String categorie_url,
			String description_url, String civilite, String pays)
			throws SQLException, Exception {
		boolean insertStatus = false;
		Connection dbConn = null;
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Statement stmt = dbConn.createStatement();
			String query = "INSERT into utilisateur (login, password, nom, prenom,  tel, fax, email, adresse, url,  categorie_url, description_url, civilite, pays) values('"
					+ login
					+ "',"
					+ "'"
					+ password
					+ "','"
					+ nom
					+ "','"
					+ prenom
					+ "',"
					+ "'"
					+ tel
					+ "','"
					+ fax
					+ "','"
					+ email
					+ "','"
					+ adresse
					+ "','"
					+ url
					+ "','"
					+ categorie_url
					+ "','"
					+ description_url
					+ "','"
					+ civilite
					+ "','"
					+ pays
					+ "')";
			// System.out.println(query);
			int records = stmt.executeUpdate(query);
			// System.out.println(records);
			// When record is successfully inserted
			if (records > 0) {
				insertStatus = true;
			}
		} catch (SQLException sqle) {
			// sqle.printStackTrace();
			throw sqle;
		} catch (Exception e) {
			// e.printStackTrace();
			// TODO Auto-generated catch block
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		return insertStatus;
	}
	
	public static boolean updateAb(String jour, String moi, String anne,String announceur)
			throws SQLException, Exception {
		boolean insertStatus = false;
		Connection dbConn = null;
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Statement stmt = dbConn.createStatement();
			String p=anne+"-"+moi+"-"+jour;
			String query = "update affectation_bann set  periode='" + p+"' where announceur= '" + announceur + "' ";
			// System.out.println(query);
			int records = stmt.executeUpdate(query);
			// System.out.println(records);
			// When record is successfully inserted
			if (records > 0) {
				insertStatus = true;
			}
		} catch (SQLException sqle) {
			// sqle.printStackTrace();
			throw sqle;
		} catch (Exception e) {
			// e.printStackTrace();
			// TODO Auto-generated catch block
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		return insertStatus;
	}
	

	/**
	 * Method to get coomission of affilie
	 * 
	 * @param uname
	 * @param pwd
	 * @return
	 * @throws Exception
	 */
	public static String checkCommission(String affilieIp) throws Exception {
		String isUserAvailable = null;
		Connection dbConn = null;
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Statement stmt = dbConn.createStatement();
			String query = "SELECT count(adresseIpClient) FROM adresseip a where adresseIpaffilie like '"
					+ affilieIp + "'";
			// System.out.println(query);
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				// System.out.println(rs.getString(1) + rs.getString(2) +
				// rs.getString(3));
				isUserAvailable = rs.getString(1);
			}
		} catch (SQLException sqle) {
			throw sqle;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		return isUserAvailable;
	}

	/**
	 * Method to get banniere of affilie
	 * 
	 * @param uname
	 * @param pwd
	 * @return
	 * @throws Exception
	 */
	public static String checkBann(String affilie) throws Exception {
		String result = null;
		Connection dbConn = null;
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Statement stmt = dbConn.createStatement();
			String query = "SELECT banniere FROM pfe.affectation_bann a where announceur like '"
					+ affilie + "' ";
			// System.out.println(query);
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				// System.out.println(rs.getString(1) + rs.getString(2) +
				// rs.getString(3));
				
				result = rs.getString(1);
			}
		} catch (SQLException sqle) {
			throw sqle;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		

		return result;
	}
}