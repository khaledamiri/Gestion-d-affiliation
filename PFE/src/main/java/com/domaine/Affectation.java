package main.java.com.domaine;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class Affectation {

	private int id_affect;
	private String auth;// bann
	private String partner;
	private String date_saissie;
	private String periode;
	private String etat_affect;
	
	
	public Affectation() {
		super();
	}

	public int getId_affect() {
		return id_affect;
	}

	public void setId_affect(int id_affect) {
		this.id_affect = id_affect;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public String getPartner() {
		return partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public String getDate_saissie() {
		return date_saissie;
	}

	public void setDate_saissie(String date_saissie) {
		this.date_saissie = date_saissie;
	}

	public String getPeriode() {
		return periode;
	}

	public void setPeriode(String periode) {
		this.periode = periode;
	}

	public String getEtat_affect() {
		return etat_affect;
	}

	public void setEtat_affect(String etat_affect) {
		this.etat_affect = etat_affect;
	}

	
	

}
