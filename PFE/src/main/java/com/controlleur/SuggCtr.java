package main.java.com.controlleur;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import main.java.com.dao.SuggIdao;
import main.java.com.dao.UtilisateurIdao;
import main.java.com.domaine.Suggestion;
import main.java.com.domaine.Utilisateur;

public class SuggCtr {

	private SuggIdao suggdao;
	private Suggestion suggestion = new Suggestion();
	
	private String mail;
	private String objet;
	private String msg;
	
	private int number;

	public int getNumber() {
		return number;
	}
	
	public void sizemail() {
		try {

			number = suggdao.tailleemail();
			

			return ;

		} catch (Exception e) {
			e.printStackTrace();

			return ;

		}

	}

	public void enregistresugg() {
		Calendar cal = Calendar.getInstance();
		cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("h:mm a");
		String a=sdf.format(cal.getTime());
		suggestion.setDate(a);
		
		suggdao.savesugg(suggestion);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("E-mail",
				"Votre E-mail est envoyé! "));
		suggestion=new Suggestion();

	}
	
	
	
	public void sendMail() {

	
		

			final String username = "pfegl5@gmail.com";
			final String password = "GPMFAEIL";

			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");

			Session session = Session.getInstance(props,
					new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(username,
									password);
						}
					});

			try {

				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress("pfegl5@gmail.com"));
				message.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse(mail));
				message.setSubject(objet);
				message.setText(msg);

				Transport.send(message);

				System.out.println("Done");
				FacesContext context3 = FacesContext.getCurrentInstance();
				context3.addMessage(null, new FacesMessage("Successful",
						"Votre E-mail est envoyé !!: "));

			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
		}
	
	
	

	// DataTable Utilisateur

	private Suggestion suggdatatable;
	private DataModel suggTitles;
	private List service_list = new ArrayList();


	public void deleteAction(Suggestion order) {

		try {
			suggdao.delete(suggdatatable);
			System.out.println("ok");
			return;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("KOOOO");
			return;
		}
	}

	// GETTER & SETTER

	public SuggIdao getSuggdao() {
		return suggdao;
	}

	public void setSuggdao(SuggIdao suggdao) {
		this.suggdao = suggdao;
	}

	public Suggestion getSuggestion() {
		return suggestion;
	}

	public void setSuggestion(Suggestion suggestion) {
		this.suggestion = suggestion;
	}

	public Suggestion getSuggdatatable() {
		return suggdatatable;
	}

	public void setSuggdatatable(Suggestion suggdatatable) {
		this.suggdatatable = suggdatatable;
	}

	public DataModel getSuggTitles() {
		suggTitles = new ListDataModel(getService_list());
		return suggTitles;
	}

	public void setSuggTitles(DataModel suggTitles) {
		this.suggTitles = suggTitles;
	}

	public List getService_list() {
		service_list = this.suggdao.findAllsugg();
		return service_list;
	}

	public void setService_list(List service_list) {
		this.service_list = service_list;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getObjet() {
		return objet;
	}

	public void setObjet(String objet) {
		this.objet = objet;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}



}
