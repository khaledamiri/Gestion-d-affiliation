package main.java.com.domaine;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "user")
public class Utilisateur implements Serializable {


	private static final long serialVersionUID = 1L;

	 @XmlElement(name = "url")
	private String url;
	private String adresse;
	private String email;
	private String tel;
	private String fax;
	private String login;
	private String password;
	 @XmlElement(name = "cpv")
	private String cpv;
	private String key;
	private String nom;
	private String prenom;
	private String enabled;
	private String categorie_url ;
	private String description_url ;
	private String civilite;
	private String pays;
	private String pwd_lost;
	private String affilie;

	public Utilisateur() {

		super();
	}

	

	

	public Utilisateur(String cpv) {
		super();
		this.cpv = cpv;
	}





	public Utilisateur(String url, String adresse, String email, String tel,
			String fax, String login, String password, String cpv, String key,
			String nom, String prenom, String enabled, String categorie_url,
			String description_url, String civilite, String pays,String affilie) {
		super();
		this.url = url;
		this.adresse = adresse;
		this.email = email;
		this.tel = tel;
		this.fax = fax;
		this.login = login;
		this.password = password;
		this.cpv = cpv;
		this.key = key;
		this.nom = nom;
		this.prenom = prenom;
		this.enabled = enabled;
		this.categorie_url = categorie_url;
		this.description_url = description_url;
		this.civilite = civilite;
		this.pays = pays;
		this.affilie=affilie;
	}




	@Override
	public String toString() {
		return login;

	}

	public String getString() {
		return login;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getCpv() {
		return cpv;
	}

	public void setCpv(String cpv) {
		this.cpv = cpv;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}



	public String getCategorie_url() {
		return categorie_url;
	}



	public void setCategorie_url(String categorie_url) {
		this.categorie_url = categorie_url;
	}



	public String getDescription_url() {
		return description_url;
	}



	public void setDescription_url(String description_url) {
		this.description_url = description_url;
	}



	public String getCivilite() {
		return civilite;
	}


	public void setCivilite(String civilite) {
		this.civilite = civilite;
	}


	public String getPays() {
		return pays;
	}



	public void setPays(String pays) {
		this.pays = pays;
	}





	public String getPwd_lost() {
		return pwd_lost;
	}


	public void setPwd_lost(String pwd_lost) {
		this.pwd_lost = pwd_lost;
	}





	public String getAffilie() {
		return affilie;
	}





	public void setAffilie(String affilie) {
		this.affilie = affilie;
	}

	
	
	
}
