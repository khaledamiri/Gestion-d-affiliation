package main.java.com.controlleur;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

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
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import main.java.com.dao.UtilisateurIdao;
import main.java.com.domaine.AdresseIp;
import main.java.com.domaine.Affectation;
import main.java.com.domaine.Test;
import main.java.com.domaine.Utilisateur;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Path("/user-management")
public class UtilisateurCtr implements AuthenticationSuccessHandler {

	private UtilisateurIdao utilisateurdao;
	private Utilisateur utilisateur = new Utilisateur();
	private Map<String, String> itemsUser = new LinkedHashMap<String, String>();
	private Map<String, String> itemsUser2 = new LinkedHashMap<String, String>();
	private boolean loogedIn = false;
	private String selectpartner;
	private String selectauth;
	private String name;
	private String selectcateg;
	private String selectpays;
	private String selectcivil;
	private Boolean terme;
	private String selectdu;
	private Map<String, String> itemsDu = new LinkedHashMap<String, String>();
	private String mail;
	private String pwd;
	private int number;

	public int getNumber() {
		return number;
	}

	// Les web services android

	@GET
	@Path("/dologin")
	@Produces("application/json")
	public String doLogin(@QueryParam("username") String uname,
			@QueryParam("password") String pwd) {
		String response = "";
		if (checkCredentials(uname, pwd)) {
			response = Utitlity.constructJSON("login", true);
		} else {
			response = Utitlity.constructJSON("login", false,
					"Incorrect Email or Password");
		}
		return response;
	}

	/**
	 * Method to check whether the entered credential is valid
	 * 
	 * @param uname
	 * @param pwd
	 * @return
	 */
	private boolean checkCredentials(String uname, String pwd) {
		System.out.println("Inside checkCredentials");
		boolean result = false;
		if (Utitlity.isNotNull(uname) && Utitlity.isNotNull(pwd)) {
			try {
				result = DBConnection.checkLogin(uname, pwd);
				// System.out.println("Inside checkCredentials try "+result);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				// System.out.println("Inside checkCredentials catch");
				result = false;
			}
		} else {
			// System.out.println("Inside checkCredentials else");
			result = false;
		}

		return result;
	}

	@GET
	@Path("/doregister")
	@Produces("application/json")
	public String doLogin(@QueryParam("login") String login,
			@QueryParam("password") String password,
			@QueryParam("nom") String nom, @QueryParam("prenom") String prenom,
			@QueryParam("tel") String tel, @QueryParam("fax") String fax,
			@QueryParam("email") String email,
			@QueryParam("adresse") String adresse,
			@QueryParam("url") String url,
			@QueryParam("categorie_url") String categorie_url,
			@QueryParam("description_url") String description_url,
			@QueryParam("civilite") String civilite,
			@QueryParam("pays") String pays

	) {
		String response = "";
		// System.out.println("Inside doLogin "+uname+"  "+pwd);
		int retCode = registerUser(login, password, nom, prenom, tel, fax,
				email, adresse, url, categorie_url, description_url, civilite,
				pays);
		if (retCode == 0) {
			response = Utitlity.constructJSON("doregister", true);
		} else if (retCode == 1) {
			response = Utitlity.constructJSON("doregister", false,
					"You are already registered");
		} else if (retCode == 2) {
			response = Utitlity
					.constructJSON("doregister", false,
							"Special Characters are not allowed in Username and Password");
		} else if (retCode == 3) {
			response = Utitlity.constructJSON("doregister", false,
					"Error occured");
		}
		return response;

	}

	private int registerUser(String login, String password, String nom,
			String prenom, String tel, String fax, String email,
			String adresse, String url, String categorie_url,
			String description_url, String civilite, String pays) {
		System.out.println("Inside checkCredentials");
		int result = 3;
		if (Utitlity.isNotNull(login) && Utitlity.isNotNull(password)) {
			try {
				if (DBConnection.insertUser(login, password, nom, prenom, tel,
						fax, email, adresse, url, categorie_url,
						description_url, civilite, pays)) {
					System.out.println("RegisterUSer if");
					result = 0;
				}
			} catch (SQLException sqle) {
				System.out.println("RegisterUSer catch sqle");
				// When Primary key violation occurs that means user is already
				// registered
				if (sqle.getErrorCode() == 1062) {
					result = 1;
				}
				// When special characters are used in name,username or password
				else if (sqle.getErrorCode() == 1064) {
					System.out.println(sqle.getErrorCode());
					result = 2;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Inside checkCredentials catch e ");
				result = 3;
			}
		} else {
			System.out.println("Inside checkCredentials else");
			result = 3;
		}

		return result;
	}

	@GET
	@Path("/docomm")
	@Produces("application/json")
	public String doLogin(@QueryParam("affilieIp") String affilieIp) {
		String response = "";
		if (getCommi(affilieIp) != null) {
			response = Utitlity.constructJSON(getCommi(affilieIp), true);
		} else {
			response = Utitlity.constructJSON("comm", false,
					"Incorrect affilieIp");
		}
		return response;
	}

	/**
	 * Method to check whether the entered credential is valid
	 * 
	 * @param uname
	 * @param pwd
	 * @return
	 */
	private String getCommi(String affilieIp) {
		System.out.println("Inside getComm");
		String result = null;
		if (Utitlity.isNotNull(affilieIp)) {
			try {
				result = DBConnection.checkCommission(affilieIp);
				// System.out.println("Inside checkCredentials try "+result);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				// System.out.println("Inside checkCredentials catch");
				result = null;
			}
		} else {
			// System.out.println("Inside checkCredentials else");
			result = null;
		}

		return result;
	}

	@GET
	@Path("/users/{id}")
	@Produces("application/json")
	public String getComm(@PathParam("id") String id) {
		String response = "";

		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"ApplicationContext.xml");

		Test springDao2 = (Test) ctx.getBean("test", Test.class);

		Integer b = springDao2.getUtilisateurdao().getCommission(id);

		response = Utitlity.constructJSON(b.toString()+" centimes", true);

		return response;

	}

	// Return if periode banniere still valid or no

	@GET
	@Path("/msg/{id}")
	@Produces("application/json")
	public String getPeriodeLimite(@PathParam("id") String id) {
		String response = "";

		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"ApplicationContext.xml");

		Test springDao2 = (Test) ctx.getBean("test", Test.class);

		String b = springDao2.getUtilisateurdao().getPeriode(id);
		if (b != null) {
			response = Utitlity.constructJSON( b, true);
			
		} else {
			response = Utitlity.constructJSON("null", false);
			
		}

		return response;

	}
	
	@GET
	@Path("/doupdate")
	@Produces("application/json")
	public String doUpdate(@QueryParam("jour") String jour,
			@QueryParam("moi") String moi,
			@QueryParam("anne") String anne,
			@QueryParam("announceur") String announceur

	) {
		String response = "";
		// System.out.println("Inside doLogin "+uname+"  "+pwd);
		int retCode = updateAbonn(jour,moi,anne,announceur);
		if (retCode == 0) {
			response = Utitlity.constructJSON("doupdate", true);
		} else if (retCode == 1) {
			response = Utitlity.constructJSON("doupdate", false,
					"You are already registered");
		} else if (retCode == 2) {
			response = Utitlity
					.constructJSON("doupdate", false,
							"Special Characters are not allowed in Username and Password");
		} else if (retCode == 3) {
			response = Utitlity.constructJSON("doupdate", false,
					"Error occured");
		}
		return response;

	}

	private int updateAbonn(String jour, String moi, String anne,String announceur) {
		System.out.println("Inside checkCredentials");
		int result = 3;
		
			try {
				if (DBConnection.updateAb(jour, moi, anne,announceur)) {
					System.out.println("RegisterUSer if");
					result = 0;
				}
			} catch (SQLException sqle) {
				System.out.println("RegisterUSer catch sqle");
				// When Primary key violation occurs that means user is already
				// registered
				if (sqle.getErrorCode() == 1062) {
					result = 1;
				}
				// When special characters are used in name,username or password
				else if (sqle.getErrorCode() == 1064) {
					System.out.println(sqle.getErrorCode());
					result = 2;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Inside checkCredentials catch e ");
				result = 3;
			}
		

		return result;
	}
	// SPRING SECURITY

	public String getSelectdu() {
		return selectdu;
	}

	public void setSelectdu(String selectdu) {
		this.selectdu = selectdu;
	}

	public Map<String, String> getItemsDu(String log) {

		try {
			List<AdresseIp> list = utilisateurdao.findAllIpdate(log);
			Iterator<AdresseIp> iter1 = list.iterator();

			while (iter1.hasNext()) {
				AdresseIp adr = (AdresseIp) iter1.next();
				String valeur = adr.getDate();
				String cle = adr.getDate();

				itemsDu.put(valeur, cle);

			}

		} catch (DataAccessException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return itemsDu;

	}

	public void setItemsDu(Map<String, String> itemsDu) {
		this.itemsDu = itemsDu;
	}

	public Utilisateur getLoggedUser() {

		if (utilisateur != null) {
			utilisateur = new Utilisateur();
			utilisateur.setLogin(SecurityContextHolder.getContext()
					.getAuthentication().getName());

		}
		return utilisateur;

	}

	public boolean isAuthorized() {
		String log = getLoggedUser().getLogin();
		String k = utilisateurdao.getlogin(log);
		if (k == null) {
			return false;
		}
		if (log == k) {
			return false;
		} else
			return true;

	}

	public boolean isConnect() {
		String log = getLoggedUser().getLogin();
		String ano = "anonymousUser";
		if (log.equals(ano)) {
			return true;

		} else
			return false;

	}

	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		Set<String> roles = AuthorityUtils.authorityListToSet(authentication
				.getAuthorities());
		if (roles.contains("ROLE_ADMIN")) {
			response.sendRedirect("http://localhost:8080/PFE/BackOffice/index.xhtml");
			return;
		}
		if (roles.contains("ROLE_AFFILIE")) {
			response.sendRedirect("http://localhost:8080/PFE/FrontOffice/index.xhtml");
			return;

		} else {

			response.sendRedirect("http://localhost:8080/PFE/FrontOffice/index.xhtml");
		}
	}

	// **************

	public boolean hasRole(String role) {
		return this.hasAnyRoles(new String[] { role });
	}

	public boolean isAuthenticated() {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		return auth.isAuthenticated()
				&& auth.getPrincipal() instanceof UserDetails;
	}

	public boolean hasAnyRoles(String[] roles) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		for (GrantedAuthority authority : auth.getAuthorities()) {
			String userRole = authority.getAuthority();
			for (String role : roles)
				if (role.equals(userRole))
					return true;
		}
		return false;
	}

	public Integer commissionAffilie(String affilieIp) {
		try {

			int number = utilisateurdao.getCommission(affilieIp);
			

			return number;

		} catch (Exception e) {
			e.printStackTrace();

			return 0;

		}

	}

	public String commissionAffilie2(String affilieIp) {
		try {

			String number = utilisateurdao.getCommission2(affilieIp);
			

			return number;

		} catch (Exception e) {
			e.printStackTrace();

			return "";

		}

	}

	public String commissionAffilieDate(String affilieIp) {
		DateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
		String duDate = sm.format(du);
		String auDate = sm.format(au);
		try {

			String number = utilisateurdao.getCommission2(affilieIp, duDate,
					auDate);
			

			return number;

		} catch (Exception e) {
			e.printStackTrace();

			return "";

		}

	}

	public void sizeUtil() {
		try {

			number = utilisateurdao.sizeUtilisateur();

			return;

		} catch (Exception e) {
			e.printStackTrace();

			return;

		}

	}

	public void saveMessage() {
		FacesContext context = FacesContext.getCurrentInstance();

		context.addMessage(null, new FacesMessage("Successful",
				"Votre mot de passe à été envoyé à votre E-mail!!: "));

	}

	public void sendMail() throws IOException {

		String s = utilisateurdao.searchMail(mail);
		String mdp = utilisateurdao.getPwd_lost(mail);
		if (s != null) {

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
				message.setFrom(new InternetAddress("khaled-amiri@hotmail.fr"));// "pfegl5@gmail.com"
				message.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse(mail));
				message.setSubject("Votre nouveau mot de passe");
				message.setText("Dear Mail Crawler,"
						+ "\n\n Voici votre mot de passe : " + mdp);

				Transport.send(message);

				FacesContext context3 = FacesContext.getCurrentInstance();
				context3.addMessage(null, new FacesMessage("Successful",
						"Votre mot de passe à été envoyé à votre E-mail!!: "));
				
				FacesContext.getCurrentInstance().getExternalContext().redirect("login.html");
			} catch (MessagingException e) {
				throw new RuntimeException(e);

			}
		}
	}

	// DataTable Utilisateur

	private Utilisateur utilidatatable;
	private DataModel utiliTitles;
	private List service_list_util = new ArrayList();

	private DataModel utiliTitlesAff;
	private List service_list_utilAff = new ArrayList();

	public DataModel getUtiliTitlesAff() {
		utiliTitlesAff = new ListDataModel(getService_list_utilAff());
		return utiliTitlesAff;
	}

	public void setUtiliTitlesAff(DataModel utiliTitlesAff) {
		this.utiliTitlesAff = utiliTitlesAff;
	}

	public List getService_list_utilAff() {
		service_list_utilAff = this.utilisateurdao.findAllaffilie();
		return service_list_utilAff;
	}

	public void setService_list_utilAff(List service_list_utilAff) {
		this.service_list_utilAff = service_list_utilAff;
	}

	public void updateActionUtil(Utilisateur b) {
		try {
			utilisateurdao.Update(utilidatatable);
			System.out.println("OK");
			return;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("KOOO");
			return;
		}

	}

	public void deleteActionUtil(Utilisateur order) {

		try {
			utilisateurdao.delete(utilidatatable);
			System.out.println("ok");
			return;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("KOOOO");
			return;
		}
	}

	public DataModel getUtiliTitles() {
		utiliTitles = new ListDataModel(getService_list_util());
		return utiliTitles;
	}

	public void setUtiliTitles(DataModel utiliTitles) {
		this.utiliTitles = utiliTitles;
	}

	public List getService_list_util() {
		service_list_util = this.utilisateurdao.findAlluser();
		return service_list_util;
	}

	public void setService_list_util(List service_list_util) {
		this.service_list_util = service_list_util;
	}

	// GETTER & SETTER

	public Utilisateur getUtilidatatable() {
		return utilidatatable;
	}

	public void setUtilidatatable(Utilisateur utilidatatable) {
		this.utilidatatable = utilidatatable;
	}

	public UtilisateurIdao getUtilisateurdao() {
		return utilisateurdao;
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

	public Map<String, String> getItemsUser() {
		try {
			List<Utilisateur> list = utilisateurdao.finduserauthority();
			Iterator<Utilisateur> iter1 = list.iterator();

			while (iter1.hasNext()) {
				Utilisateur user = (Utilisateur) iter1.next();
				String valeur = user.getLogin();
				String id = user.getLogin();

				itemsUser.put(valeur, id);

			}

		} catch (DataAccessException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return itemsUser;
	}

	public Map<String, String> getItemsUser2() {
		try {
			List<Utilisateur> list = utilisateurdao.findAlluser();
			Iterator<Utilisateur> iter1 = list.iterator();

			while (iter1.hasNext()) {
				Utilisateur user = (Utilisateur) iter1.next();
				String valeur = user.getLogin();
				String id = user.getLogin();

				itemsUser.put(valeur, id);

			}

		} catch (DataAccessException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return itemsUser;
	}

	public void enregistreutilisateur() {

		if (terme) {
			utilisateur.setCategorie_url(selectcateg);
			utilisateur.setPays(selectpays);
			utilisateur.setCivilite(selectcivil);
			utilisateurdao.saveutilisateur(utilisateur);
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Vous étès enregistré",
					"Votre enregistrement est fait! "));
			utilisateur = new Utilisateur();
		} else {
			System.out.println("erreur");
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					"Vous n'étès pas enregistré",
					"Votre enregistrement est échoué! "));
		}

	}
	
	public void enregistreutilisateurAff() {

		if (terme) {
			utilisateur.setCategorie_url(selectcateg);
			utilisateur.setPays(selectpays);
			utilisateur.setCivilite(selectcivil);
			utilisateurdao.saveutilisateurAff(utilisateur);
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Vous étès enregistré",
					"Votre enregistrement est fait! "));
			utilisateur = new Utilisateur();
		} else {
			System.out.println("erreur");
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					"Vous n'étès pas enregistré",
					"Votre enregistrement est échoué! "));
		}

	}


	public void enregistreutilisateuradmin() {

		try {
			utilisateur.setCategorie_url(selectcateg);
			utilisateur.setPays(selectpays);
			utilisateur.setCivilite(selectcivil);
			utilisateurdao.saveutilisateur(utilisateur);
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Vous étès enregistré",
					"Votre enregistrement est fait! "));
			utilisateur = new Utilisateur();
			utilisateur.setCategorie_url(null);
			utilisateur.setPays(null);
			utilisateur.setCivilite(null);
		} catch (Exception e) {
			e.printStackTrace();

			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(
					"Vous n'étès pas enregistré",
					"Votre enregistrement est échoué! "));
		}

	}

	public void enregistreaffectation() {

		Affectation affect = new Affectation();
		affect.setPartner(getSelectpartner());
		affect.setAuth(getSelectauth());

		try {
			utilisateurdao.save(affect);
			System.out.println(selectpartner);
			System.out.println(selectauth);
			System.out.println("succes");
			affect = new Affectation();
			FacesContext context = FacesContext.getCurrentInstance();

			context.addMessage(null, new FacesMessage("Autorité affectée",
					"Votre affectation est faite! "));

			return;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("erreur");
			return;
		}

	}

	private Date du;
	private Date au;

	public Date getDu() {
		return du;
	}

	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	// Juste yaffichilik PDF
	public void versPDF() throws JRException, IOException {
		DateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
		String duDate = sm.format(du);
		String auDate = sm.format(au);
		String com = commissionAffilieDate(banndatatable.getAdresseIpaffilie());// ya3tik
																				// il
		// commisiion mil
		// date du --> au

		File jasper = new File(FacesContext.getCurrentInstance()
				.getExternalContext().getRealPath("/Rapports/rptPFE.jasper"));
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("Affilie", banndatatable.getAdresseIpaffilie());
		parameters.put("com", com);
		parameters.put("du", duDate);
		parameters.put("au", auDate);
		System.out.println("duDate" + duDate + "auDate" + auDate + "com");
		byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(),
				parameters,
				new JRBeanCollectionDataSource(this.avoiripByName()));
		HttpServletResponse reponse = (HttpServletResponse) FacesContext
				.getCurrentInstance().getExternalContext().getResponse();
		reponse.setContentType("application/pdf");
		reponse.setContentLength(bytes.length);
		ServletOutputStream outstream = reponse.getOutputStream();
		outstream.write(bytes, 0, bytes.length);
		outstream.flush();
		outstream.close();
		FacesContext.getCurrentInstance().responseComplete();

	}

	public List<AdresseIp> avoiripByName() {
		DateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
		String duDate = sm.format(du);
		String auDate = sm.format(au);
		System.out.println(duDate);

		List<AdresseIp> result = utilisateurdao.findIpByNamee(
				banndatatable.getAdresseIpaffilie(), duDate, auDate);
		System.out.println(selectpartner + "partner" + duDate + "DU" + auDate
				+ "AU");
		// Return the datasource
		return result;
	}

	// Adresse IP datatable***********************************************
	private AdresseIp banndatatable;
	private DataModel auteurTitles;
	private List service_list = new ArrayList();

	public DataModel getAuteurTitles() {
		auteurTitles = new ListDataModel(getService_list());
		return auteurTitles;
	}

	public void setAuteurTitles(DataModel auteurTitles) {
		this.auteurTitles = auteurTitles;
	}

	public List getService_list() {
		service_list = this.utilisateurdao.findAllIp();
		return service_list;
	}

	public void setService_list(List service_list) {
		this.service_list = service_list;
	}

	public AdresseIp getBanndatatable() {
		return banndatatable;
	}

	public void setBanndatatable(AdresseIp banndatatable) {
		this.banndatatable = banndatatable;
	}

	// Commission
	public int getRandomPrice() {
		return (int) (Math.random() * 100000);
	}

	// Getters&Setters***********************************************

	public void setItemsUser(Map<String, String> itemsUser) {
		this.itemsUser = itemsUser;
	}

	public void setItemsUser2(Map<String, String> itemsUser2) {
		this.itemsUser2 = itemsUser2;
	}

	public boolean isLoogedIn() {
		return loogedIn;
	}

	public void setLoogedIn(boolean loogedIn) {
		this.loogedIn = loogedIn;
	}

	public String getSelectpartner() {
		return selectpartner;
	}

	public void setSelectpartner(String selectpartner) {
		this.selectpartner = selectpartner;
	}

	public String getSelectauth() {
		return selectauth;
	}

	public void setSelectauth(String selectauth) {
		this.selectauth = selectauth;
	}

	public void setDu(Date du) {
		this.du = du;
	}

	public Date getAu() {
		return au;
	}

	public void setAu(Date au) {
		this.au = au;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSelectcateg() {
		return selectcateg;
	}

	public void setSelectcateg(String selectcateg) {
		this.selectcateg = selectcateg;
	}

	public String getSelectpays() {
		return selectpays;
	}

	public void setSelectpays(String selectpays) {
		this.selectpays = selectpays;
	}

	public String getSelectcivil() {
		return selectcivil;
	}

	public void setSelectcivil(String selectcivil) {
		this.selectcivil = selectcivil;
	}

	public Boolean getTerme() {
		return terme;
	}

	public void setTerme(Boolean terme) {
		this.terme = terme;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

}
