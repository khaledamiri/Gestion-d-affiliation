package main.java.com.domaine;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import main.java.com.controlleur.BanniereCtr;
import main.java.com.controlleur.SuggCtr;
import main.java.com.controlleur.UtilisateurCtr;
import main.java.com.dao.BannierIdao;
import main.java.com.dao.FileIdao;
import main.java.com.dao.SuggIdao;
import main.java.com.dao.UtilisateurIdao;

import org.apache.http.client.ClientProtocolException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.file.FileDownloadView;
import com.rapprot.Rapport;

public class Test {

	private BannierIdao banndao;
	private BanniereCtr bannierectr;
	private UtilisateurIdao utilisateurdao;
	private UtilisateurCtr utilisateurctr;
	private SuggIdao suggdao;
	private SuggCtr suggCtr;

	public static void main(String[] args) throws ClientProtocolException,
			IOException {

		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"ApplicationContext.xml");

		Test springDao2 = (Test) ctx.getBean("test", Test.class);
		String s=springDao2.banndao.getBannier();
		System.out.println(s+" Bannieres");

	}
	
	

	public SuggIdao getSuggdao() {
		return suggdao;
	}



	public void setSuggdao(SuggIdao suggdao) {
		this.suggdao = suggdao;
	}



	public SuggCtr getSuggCtr() {
		return suggCtr;
	}



	public void setSuggCtr(SuggCtr suggCtr) {
		this.suggCtr = suggCtr;
	}



	public BannierIdao getBanndao() {
		return banndao;
	}

	public void setBanndao(BannierIdao banndao) {
		this.banndao = banndao;
	}

	public BanniereCtr getBannierectr() {
		return bannierectr;
	}

	public void setBannierectr(BanniereCtr bannierectr) {
		this.bannierectr = bannierectr;
	}

	public UtilisateurIdao getUtilisateurdao() {
		return utilisateurdao;
	}

	public void setUtilisateurdao(UtilisateurIdao utilisateurdao) {
		this.utilisateurdao = utilisateurdao;
	}

	public UtilisateurCtr getUtilisateurctr() {
		return utilisateurctr;
	}

	public void setUtilisateurctr(UtilisateurCtr utilisateurctr) {
		this.utilisateurctr = utilisateurctr;
	}

	

}
