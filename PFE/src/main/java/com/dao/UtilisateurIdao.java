package main.java.com.dao;

import java.util.List;

import main.java.com.domaine.AdresseIp;
import main.java.com.domaine.Affectation;
import main.java.com.domaine.Utilisateur;

public interface UtilisateurIdao {

	public List<Utilisateur> findAlluser();

	public List<Utilisateur> findAllaffilie();
	
	public List<Utilisateur> findAllusernoAffile() ;

	public List<Utilisateur> finduserauthority();

	public void save(Affectation affect);

	public void saveutilisateur(Utilisateur utilisateur);

	public void Update(Utilisateur u);

	public void delete(Utilisateur utilisateur);

	public Integer sizeUtilisateur();
	
	public String getPeriode(String ann);

	// Adresse IP
	public List<AdresseIp> findAllIp();
	public List<AdresseIp> findAllIpdate(String log);

	public List<AdresseIp> findIpByName(String name);

	public List<AdresseIp> findIpByNamee(String name, String du, String au);

	public String getCommission2(String affilieIp);

	public String getCommission2(String affilieIp, String duDate, String auDate);

	public Integer getCommission(String affilieIp);

	public String getlogin(String login);
	
	//Mail
	public String searchMail(String m);
	public String getPwd_lost(String mail);
	
//	public String searchMailforPwd(String pwd);
	public List<Utilisateur> finduserbyname(String login);

	void saveutilisateurAff(Utilisateur utilisateur);
}
