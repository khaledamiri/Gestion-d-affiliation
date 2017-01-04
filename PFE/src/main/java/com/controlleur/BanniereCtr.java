package main.java.com.controlleur;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import main.java.com.dao.BannierIdao;
import main.java.com.domaine.Banniere;
import main.java.com.domaine.Bannierecss;
import main.java.com.domaine.Test;
import main.java.com.domaine.Utilisateur;
import main.java.com.domaine.messageBean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;

@Path("/xml/banner")
@ManagedBean
@ViewScoped
public class BanniereCtr extends messageBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// *************************************** ATTRIBUTS
	BannierIdao banndao;
	public Banniere banniere = new Banniere();
	Bannierecss bannierecss = new Bannierecss();
	List<Banniere> listbanniere = new ArrayList<Banniere>();
	List<Bannierecss> listbanniereCss = new ArrayList<Bannierecss>();
	private Map<String, String> ItemsBanner = new LinkedHashMap<String, String>();
	public String selectbanner;
	private String value;
	private int number;

	public int getNumber() {
		return number;
	}

	public void reformulationURL() {
		try {
			FacesContext
					.getCurrentInstance()
					.getExternalContext()
					.redirect(
							"http://localhost:8080/PFE/rest/xml/banner/gete/"
									+ value);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// *************************************** Web services

	@GET
	@Path("/givebann/{key}/{id}")
	@Produces("application/xml")
	public Response giveBanniere(@PathParam("key") Integer key,
			@PathParam("id") Integer id) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"ApplicationContext.xml");

		Test springDao2 = (Test) ctx.getBean("test", Test.class);

		List<Utilisateur> lk = springDao2.getBanndao().getKeys();

		// yaffichilik il design de bann lékin prix ,prixpromo et lien NO si le
		// key existe et selon design spécifie.

		String design = springDao2.getBanndao().getDESIGN_BANN(id);

		String prix = springDao2.getBanndao().getPRIX_BANN(key, design);
		String prixpromo = springDao2.getBanndao().getPRIX_PROMO(key, design);
		String lien = springDao2.getBanndao().getLIEN_DE_BANN(key, design);
		String url = springDao2.getBanndao().getUrl_Bann(key, design);

		// yafichilik il css mta3 il bann ++ idcss ++

		String background_color = springDao2.getBanndao().background_color(key,
				design);
		String width = springDao2.getBanndao().width(key, design);
		String height = springDao2.getBanndao().height(key, design);
		String color = springDao2.getBanndao().color(key, design);
		String text_align = springDao2.getBanndao().text_align(key, design);
		String line_height = springDao2.getBanndao().line_height(key, design);
		String margin_left = springDao2.getBanndao().margin_left(key, design);
		String text_decoration = springDao2.getBanndao().text_decoration(key,
				design);
		String text_transform = springDao2.getBanndao().text_transform(key,
				design);
		String font_style = springDao2.getBanndao().font_style(key, design);
		String font_family = springDao2.getBanndao().font_family(key, design);

		// String periode = springDao2.getBanndao().getLastPeriod(design);

		// Boolean a=periode.equals("2015-04-23");
		// if (a) {

		Banniere bann = new Banniere();
		bann.setDESIGN_BANN(design);
		bann.setPRIX_BANN(prix);
		bann.setLIEN_DE_BANN("http://localhost:8080/PFE/uploaded/" + lien);
		bann.setPRIX_PROMO(prixpromo);
		bann.setURL_BANN(url);
		// bann.setBackground_color(background_color);
		// bann.setWidth(width);
		// bann.setHeight(height);
		// bann.setColor(color);
		// bann.setText_align(text_align);
		// bann.setLine_height(line_height);
		// bann.setMargin_left(margin_left);
		// bann.setText_decoration(text_decoration);
		// bann.setText_transform(text_transform);
		// bann.setFont_style(font_style);
		// bann.setFont_family(font_family);

		return Response.status(200).entity(bann).build();
		// }
		// Bannierecss bann = new Bannierecss();
		// return Response.status(200).entity(bann).build();

	}

	
	
	
	@GET
	@Path("/getAffIp/{userip}/{key}/{id}/{date}")
	@Produces("text/plain")
	public Response getAffIp(@PathParam("userip") String userip,
			@PathParam("key") String key, @PathParam("id") Integer id,
			@PathParam("date") String date) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"ApplicationContext.xml");

		Test springDao2 = (Test) ctx.getBean("test", Test.class);
		// avan t insertion on doit chercher design bann selon id entrer
		String designID = springDao2.getBanndao().getDESIGN_BANN(id);
		// avan t insertion on doit chercher nom affilié selon key entrer
		String nomaff = springDao2.getBanndao().getNomAffSelonKey(key);
		// insertion
		Integer ipAff = springDao2.getBanndao().saveIp(nomaff, userip,
				designID, date);

		return Response.status(200).entity(ipAff).build();
	}

	@GET
	@Path("/getsize")
	@Produces("application/xml")
	public Response countBann() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"ApplicationContext.xml");

		Test springDao2 = (Test) ctx.getBean("test", Test.class);
		int n = springDao2.getBanndao().sizeBanniere();
		Banniere b = new Banniere();
		b.setID_BANN(n);

		return Response.status(200).entity(b).build();
	}

	public String enregistrebanniere() {

		try {

			banndao.savebann(banniere);
			System.out.println(banniere);

			return "succes";

		} catch (Exception e) {
			e.printStackTrace();

			return "error";

		}
	}

	@GET
	@Path("/getip/")
	@Produces("json/plain")
	public Response getIp() throws Exception {
		String hostname = InetAddress.getLocalHost().getHostName();
		String hostadress = InetAddress.getLocalHost().getHostAddress();

		return Response.status(200).entity(hostname + " " + hostadress).build();

	}
	
	
	
	

	// ********************************************* Méthodes
	public String enregistreCssbanniere() {

		try {
			bannierecss.setIdBann(selectbanner);
			banndao.saveCssbann(bannierecss);
			System.out.println(bannierecss);
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(
					null,
					new FacesMessage("CSS du banniére ajoutée",
							"Les caractéristiques des banniéres sont ajoutées avec succé"));
			bannierecss = new Bannierecss();
			return "succes";

		} catch (Exception e) {
			e.printStackTrace();

			return "error";

		}
	}

	public Map<String, String> getItemsBanner() {

		try {
			List<Banniere> list = banndao.findAll();
			Iterator<Banniere> iter1 = list.iterator();

			while (iter1.hasNext()) {
				Banniere bann = (Banniere) iter1.next();
				String valeur = bann.getDESIGN_BANN();
				String cle = bann.getDESIGN_BANN();

				ItemsBanner.put(valeur, cle);
				// System.out.println(cle);

			}

		} catch (DataAccessException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ItemsBanner;
	}

	public void sizeBann() {
		try {

			number = banndao.sizeBanniere();

			return;

		} catch (Exception e) {
			e.printStackTrace();

			return;

		}

	}

	public void sizeBanniere() {

		number = banndao.sizeBanniere();

	}

	// *********************************** datatable

	private Banniere banndatatable;
	private DataModel auteurTitles;
	private List service_list = new ArrayList();

	private Bannierecss banndatatableCss;
	private DataModel auteurTitlesCss;
	private List service_listCss = new ArrayList();

	// ***************************************** Méthodes

	public void updateAction(Banniere b) {
		try {
			banndao.Update(banndatatable);
			System.out.println("OK");
			return;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("KOOO");
			return;
		}

	}

	public void deleteAction(Banniere order) {

		try {
			banndao.delete(banndatatable);

			return;

		} catch (Exception e) {
			e.printStackTrace();

			return;
		}
	}

	public void updateAction2(Bannierecss b) {
		try {
			banndao.Update2(banndatatableCss);

			return;
		} catch (Exception e) {
			e.printStackTrace();

			return;
		}

	}

	public void deleteAction2(Bannierecss order) {

		try {
			banndao.delete2(banndatatableCss);
			System.out.println("ok");
			return;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("KOOOO");
			return;
		}
	}

	// ***************************************** GettersSetters

	// public Bannierecss getBanndatatableCss() {
	// return banndatatableCss;
	// }
	//
	// public void setBanndatatableCss(Bannierecss banndatatableCss) {
	// this.banndatatableCss = banndatatableCss;
	// }

	public DataModel getAuteurTitlesCss() {
		auteurTitlesCss = new ListDataModel(getService_listCss());
		return auteurTitlesCss;
	}

	public void setAuteurTitlesCss(DataModel auteurTitlesCss) {
		this.auteurTitlesCss = auteurTitlesCss;
	}

	public DataModel getAuteurTitles() {
		auteurTitles = new ListDataModel(getService_list());
		return auteurTitles;
	}

	public void setAuteurTitles(DataModel auteurTitles) {
		this.auteurTitles = auteurTitles;
	}

	public List getService_listCss() {
		service_listCss = this.banndao.findAllbannCss();
		return service_listCss;
	}

	public void setService_listCss(List service_listCss) {
		this.service_listCss = service_listCss;
	}

	public List getService_list() {
		service_list = this.banndao.findAll();
		return service_list;
	}

	public void setService_list(List service_list) {
		this.service_list = service_list;
	}

	public Bannierecss getBanndatatableCss() {
		return banndatatableCss;
	}

	public void setBanndatatableCss(Bannierecss banndatatableCss) {
		this.banndatatableCss = banndatatableCss;
	}

	public BannierIdao getBanndao() {
		return banndao;
	}

	public void setBanndao(BannierIdao banndao) {
		this.banndao = banndao;
	}

	public Banniere getBanndatatable() {
		return banndatatable;
	}

	public void setBanndatatable(Banniere banndatatable) {
		this.banndatatable = banndatatable;
	}

	public Banniere getBanniere() {
		return banniere;
	}

	public void setBanniere(Banniere banniere) {
		this.banniere = banniere;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public List<Banniere> getListbanniere() {
		banniere = new Banniere();
		listbanniere = banndao.findAll();
		return listbanniere;
	}

	public void setListbanniere(List<Banniere> listbanniere) {
		this.listbanniere = listbanniere;
	}

	public List<Bannierecss> getListbanniereCss() {
		bannierecss = new Bannierecss();
		listbanniereCss = banndao.findAllbannCss();
		return listbanniereCss;
	}

	public void setListbanniereCss(List<Bannierecss> listbanniereCss) {
		this.listbanniereCss = listbanniereCss;
	}

	public Bannierecss getBannierecss() {
		return bannierecss;
	}

	public void setBannierecss(Bannierecss bannierecss) {
		this.bannierecss = bannierecss;
	}

	public void setItemsBanner(Map<String, String> itemsBanner) {
		ItemsBanner = itemsBanner;
	}

	public String getSelectbanner() {
		return selectbanner;
	}

	public void setSelectbanner(String selectbanner) {
		this.selectbanner = selectbanner;
	}

}
