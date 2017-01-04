package main.java.com.dao;

import java.util.List;

import main.java.com.domaine.Banniere;
import main.java.com.domaine.Bannierecss;
import main.java.com.domaine.Categorie;
import main.java.com.domaine.Utilisateur;

public interface BannierIdao {

	public String selectBannbynom(int bannId);

	public int selectidbannbylogin();

	public void savebann(Banniere bann);

	public List<Banniere> findAll();

	public void Update(Banniere banniere);

	public void delete(Banniere banniere);

	// Pour Bannierecss

	public void saveCssbann(Bannierecss bann);

	public void Update2(Bannierecss banniere);

	public void delete2(Bannierecss banniere);

	public List<Bannierecss> findAllbannCss();

	// End

	public String getMaxPromo();

	public Integer sizeBanniere();

	public List<String> findCategories();

	public List<Utilisateur> getKeys();

	public String getMaxPromoselonCategorie(Categorie c);

	public String getDESIGN_BANN(Integer a);

	public String getPRIX_BANN(Integer a, String design);

	public String getPRIX_PROMO(Integer a, String design);

	public String getLIEN_DE_BANN(Integer a, String design);

	public String getUrl_Bann(Integer a, String design);

	public String background_color(Integer a, String design);

	public String width(Integer a, String design);

	public String height(Integer a, String design);

	public String color(Integer a, String design);

	public String text_align(Integer a, String design);

	public String line_height(Integer a, String design);

	public String margin_left(Integer a, String design);

	public String text_decoration(Integer a, String design);

	public String text_transform(Integer a, String design);

	public String font_style(Integer a, String design);

	public String font_family(Integer a, String design);

	public String getLastPeriod(String design);

	public String getNomAffSelonKey(String key);

	public int saveIp(String nomaff, String userip, String designID, String date);
	
	public String getBannier();
}
