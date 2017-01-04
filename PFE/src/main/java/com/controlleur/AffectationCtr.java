package main.java.com.controlleur;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import main.java.com.dao.AffectationIdao;
import main.java.com.dao.BannierIdao;
import main.java.com.dao.UtilisateurIdao;
import main.java.com.domaine.Affectation;
import main.java.com.domaine.Banniere;
import main.java.com.domaine.Utilisateur;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.dao.DataAccessException;

public class AffectationCtr {

	private UtilisateurIdao utilisateurdao;
	private Utilisateur utilisateur;
	private BannierIdao banndao;
	private Banniere banniere = new Banniere();
	private AffectationIdao affectationdao;
	private Affectation affect = new Affectation();
	private String message = "";;
	private Map<String, String> itemsPartner = new LinkedHashMap<String, String>();
	private Map<String, String> ItemsBanner = new LinkedHashMap<String, String>();
	private Map<String, String> itemsLien = new LinkedHashMap<String, String>();
	private String selectpartner;
	private String selectbanner;
	private String selectlien;
	private String etat;
	private Date au;

	DateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
	Date currentDate = new Date();
	final String datesaissie = sm.format(currentDate);

	public String enregistreaffectation() {

		DateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
		String auDate = sm.format(au);

		Date d = new Date();

		String datesaissie = sm.format(d);

		Affectation affect = new Affectation();
		affect.setPartner(getSelectpartner());
		affect.setAuth(getSelectbanner());
		affect.setPeriode(auDate);
		affect.setEtat_affect(etat);
		affect.setDate_saissie(datesaissie);

		try {
			affectationdao.save(affect);
			System.out.println(selectpartner);
			System.out.println(selectbanner);
			utilisateur = new Utilisateur();
			setMessage("");
			FacesContext context = FacesContext.getCurrentInstance();

			context.addMessage(null, new FacesMessage("Banniére affectée",
					"Votre affectation est faite! "));
			return "succes";

		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	// Charger banniere
	public Map<String, String> getItemsBanner() {

		try {
			List<Banniere> list = affectationdao.findAll();
			Iterator<Banniere> iter1 = list.iterator();

			while (iter1.hasNext()) {
				Banniere bann = (Banniere) iter1.next();
				String valeur = bann.getDESIGN_BANN();
				String cle = bann.getDESIGN_BANN();

				ItemsBanner.put(valeur, cle);

			}

		} catch (DataAccessException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ItemsBanner;
	}

	// charger partner
	public Map<String, String> getItemsPartner() {

		try {
			List<Utilisateur> list = affectationdao.findAllannounceur();
			Iterator<Utilisateur> iter1 = list.iterator();

			while (iter1.hasNext()) {
				Utilisateur user = (Utilisateur) iter1.next();
				String valeur = user.getLogin();
				String cle = user.getLogin();
				itemsPartner.put(valeur, cle);
			}

		} catch (DataAccessException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return itemsPartner;
	}

	// charger User
	public Map<String, String> getItemsLien() {

		try {
			List<Banniere> list = affectationdao.findAllien();
			Iterator<Banniere> iter1 = list.iterator();

			while (iter1.hasNext()) {
				Banniere user = (Banniere) iter1.next();
				String valeur = user.getLIEN_DE_BANN();
				String cle = user.getLIEN_DE_BANN();
				itemsLien.put(valeur, cle);

			}

		} catch (DataAccessException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return itemsLien;
	}

	public AffectationCtr() {
		super();
	}

	// Datatable

	private Affectation affectdatatable;
	private DataModel affectTitles;
	private List service_list_affect = new ArrayList();

	public void deleteAction(Affectation order) {

		try {
			affectationdao.delete(affectdatatable);

			return;

		} catch (Exception e) {
			e.printStackTrace();

			return;
		}
	}

	// Getters & Setters

	public Affectation getAffectdatatable() {
		return affectdatatable;
	}

	public void setAffectdatatable(Affectation affectdatatable) {
		this.affectdatatable = affectdatatable;
	}

	public DataModel getAffectTitles() {
		affectTitles = new ListDataModel(getService_list_affect());
		return affectTitles;
	}

	public void setAffectTitles(DataModel affectTitles) {
		this.affectTitles = affectTitles;
	}

	public List getService_list_affect() {
		service_list_affect = this.affectationdao.findAllAffect();
		return service_list_affect;
	}

	public void setService_list_affect(List service_list_affect) {
		this.service_list_affect = service_list_affect;
	}

	public UtilisateurIdao getUtilisateurdao() {
		return utilisateurdao;
	}

	public Banniere getBanniere() {
		return banniere;
	}

	public void setBanniere(Banniere banniere) {
		this.banniere = banniere;
	}

	public void setUtilisateurdao(UtilisateurIdao utilisateurdao) {
		this.utilisateurdao = utilisateurdao;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public void setItemsUser(Map<String, String> itemsLien) {
		this.itemsLien = itemsLien;
	}

	public String getSelectpartner() {
		return selectpartner;
	}

	public void setSelectpartner(String selectpartner) {
		this.selectpartner = selectpartner;
	}

	public String getSelectbanner() {
		return selectbanner;
	}

	public void setSelectbanner(String selectbanner) {
		this.selectbanner = selectbanner;
	}

	public BannierIdao getBanndao() {
		return banndao;
	}

	public void setBanndao(BannierIdao banndao) {
		this.banndao = banndao;
	}

	public void setItemsBanner(Map<String, String> itemsBanner) {
		ItemsBanner = itemsBanner;
	}

	public void setItemsPartner(Map<String, String> itemsPartner) {
		this.itemsPartner = itemsPartner;
	}

	public AffectationIdao getAffectationdao() {
		return affectationdao;
	}

	public void setAffectationdao(AffectationIdao affectationdao) {
		this.affectationdao = affectationdao;
	}

	public Affectation getAffect() {
		return affect;
	}

	public void setAffect(Affectation affect) {
		this.affect = affect;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSelectlien() {
		return selectlien;
	}

	public void setSelectlien(String selectlien) {
		this.selectlien = selectlien;
	}

	public void setItemsLien(Map<String, String> itemsLien) {
		this.itemsLien = itemsLien;
	}

	public Date getAu() {
		return au;
	}

	public void setAu(Date au) {
		this.au = au;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public String getDatesaissie() {
		return datesaissie;
	}

	

	public String enregistrebanniere() {

		try {

			// banndao.savebann(banniere);
			affectationdao.savebann(banniere);
			System.out.println(banniere);
			setMessage("");
			return "succes";

		} catch (Exception e) {
			e.printStackTrace();
			setMessage("Banniere existe déjà");
			return "error";

		}
	}

	private String destination = "D:\\UploadFiles\\";

	public void upload1(FileUploadEvent event) {
		FacesMessage msg = new FacesMessage("Success! ", event.getFile()
				.getFileName() + " is uploaded.");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		// Do what you want with the file
		try {
			copyFile(event.getFile().getFileName(), event.getFile()
					.getInputstream());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void copyFile(String fileName, InputStream in) {
		try {

			// write the inputStream to a FileOutputStream
			OutputStream out = new FileOutputStream(new File(destination
					+ fileName));

			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = in.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}

			in.close();
			out.flush();
			out.close();

			System.out.println("New file created!");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public void handleFileUpload(FileUploadEvent event) {
		try {
			File targetFolder = new File("d:/UploadFiles");
			InputStream inputStream = event.getFile().getInputstream();
			OutputStream out = new FileOutputStream(new File(targetFolder,
					event.getFile().getFileName()));
			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = inputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			inputStream.close();
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void copyFile2(String fileName, InputStream in)
			throws FileNotFoundException {
		try {
			// write the inputStream to a FileOutputStream
			OutputStream out = new FileOutputStream(new File(
					"D:\\UploadFiles\\" + fileName));
			// new FileOutputStream(new File(destinationImage + fileName));
			int read = 0;
			byte[] bytes = new byte[1024];
			while ((read = in.read(bytes)) != -1) {
				out.write(bytes, 0, read);
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("File Uploaded Successfully"));
			}
			in.close();
			out.flush();
			out.close();
			// bann.setLIEN_DE_BANN(fileName);
			System.out.println("Image Uploaded! ");
		} catch (IOException e) {
			System.out.println(e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("File Not Uploaded Successfully"));
		}
	}

	private UploadedFile file;

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public void handleFileUpload33(FileUploadEvent event) {
		try {
			File targetFolder = new File("D:\\UploadFiles\\");
			InputStream inputStream = event.getFile().getInputstream();
			OutputStream out = new FileOutputStream(new File(targetFolder,
					event.getFile().getFileName()));
			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = inputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			inputStream.close();
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
