package main.java.com.domaine;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "banner")
public class Banniere {

	private int ID_BANN;
	private String DESIGN_BANN;
	private String PRIX_BANN;
	private String URL_BANN;
	private String LIEN_DE_BANN;
	private String TAILLE_BANN;
	private String PRIX_PROMO;
	

	// private String categorie;

	public Banniere(int iD_BANN, String dESIGN_BANN, String pRIX_BANN,
			String uRL_BANN, String lIEN_DE_BANN, String tAILLE_BANN,
			String pRIX_PROMO) {
		super();
		ID_BANN = iD_BANN;
		DESIGN_BANN = dESIGN_BANN;
		PRIX_BANN = pRIX_BANN;
		URL_BANN = uRL_BANN;
		LIEN_DE_BANN = lIEN_DE_BANN;
		TAILLE_BANN = tAILLE_BANN;
		PRIX_PROMO = pRIX_PROMO;
	}

	@Override
	public String toString() {
		return "Banniere [ID_BANN=" + ID_BANN + ", DESIGN_BANN=" + DESIGN_BANN
				+ ", PRIX_BANN=" + PRIX_BANN + ", URL_BANN=" + URL_BANN
				+ ", LIEN_DE_BANN=" + LIEN_DE_BANN + ", TAILLE_BANN="
				+ TAILLE_BANN + ", PRIX_PROMO=" + PRIX_PROMO + "]";
	}

	public Banniere() {
		super();
	}

	public Banniere(String dESIGN_BANN, String pRIX_BANN, String pRIX_PROMO) {
		super();
		DESIGN_BANN = dESIGN_BANN;
		PRIX_BANN = pRIX_BANN;
		PRIX_PROMO = pRIX_PROMO;
	}

	@XmlElement
	public String getDESIGN_BANN() {
		return DESIGN_BANN;
	}

	public void setDESIGN_BANN(String dESIGN_BANN) {
		DESIGN_BANN = dESIGN_BANN;
	}

	@XmlElement
	public String getPRIX_BANN() {
		return PRIX_BANN;
	}

	public void setPRIX_BANN(String pRIX_BANN) {
		PRIX_BANN = pRIX_BANN;
	}

	public String getURL_BANN() {
		return URL_BANN;
	}

	public void setURL_BANN(String uRL_BANN) {
		URL_BANN = uRL_BANN;
	}

	public String getLIEN_DE_BANN() {
		return LIEN_DE_BANN;
	}

	public void setLIEN_DE_BANN(String lIEN_DE_BANN) {
		LIEN_DE_BANN = lIEN_DE_BANN;
	}

	public String getTAILLE_BANN() {
		return TAILLE_BANN;
	}

	public void setTAILLE_BANN(String tAILLE_BANN) {
		TAILLE_BANN = tAILLE_BANN;
	}

	public int getID_BANN() {
		return ID_BANN;
	}

	public void setID_BANN(int iD_BANN) {
		ID_BANN = iD_BANN;
	}

	
	public String getPRIX_PROMO() {
		return PRIX_PROMO;
	}

	public void setPRIX_PROMO(String pRIX_PROMO) {
		PRIX_PROMO = pRIX_PROMO;
	}
	

}
