package com.rapprot;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import main.java.com.domaine.AdresseIp;
import main.java.com.domaine.Test;
import main.java.com.domaine.Utilisateur;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@ManagedBean
@RequestScoped
public class Rapport {

	ApplicationContext ctx = new ClassPathXmlApplicationContext(
			"ApplicationContext.xml");

	Test springDao2 = (Test) ctx.getBean("test", Test.class);
	Utilisateur u = springDao2.getUtilisateurctr().getLoggedUser();

	String name = u.getLogin();
	String com = springDao2.getUtilisateurctr().commissionAffilie2(name);

	// Affilie

	private List getDatasource() {

		List<AdresseIp> result = springDao2.getUtilisateurdao().findIpByName(
				name);

		// Return the datasource
		return result;
	}

	private List<AdresseIp> getDatasource2() {
		  DateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
		    String duDate = sm.format(du);
		    String auDate = sm.format(au);
		   
		List<AdresseIp> result = springDao2.getUtilisateurdao().findIpByNamee(name, duDate, auDate);

		// Return the datasource
		return result;
	}
	
	public void exportTopdfadmin() throws Exception {
		try{
	
		JasperPrint jasperPrint;
		File jasper = new File(FacesContext.getCurrentInstance()
				.getExternalContext()
				.getRealPath("/Rapports/rptAffilie.jasper"));
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("Affilie", name);
		parameters.put("com", com);
		parameters.put("du", du);
		parameters.put("au", au);

		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(
				getDatasource2());
		jasperPrint = JasperFillManager.fillReport(jasper.getPath(),
				parameters, beanCollectionDataSource);

		HttpServletResponse reponse = (HttpServletResponse) FacesContext
				.getCurrentInstance().getExternalContext().getResponse();

		reponse.addHeader("Content-disposition", "attachment; filename=" + name
				+ ".pdf");
		reponse.setContentLength(10000);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		reponse.setContentType("application/pdf");

		ServletOutputStream stream = null;

		stream = reponse.getOutputStream();
		

		// Export To PDF
		JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

		FacesContext.getCurrentInstance().responseComplete();
		addMessage("Votre télèchargemment commence!!");
		}catch(Exception e){e.printStackTrace();addMessage("Vous devez saissir les dates !");}
	}

	// ihabitlik un PDF
	public void exportToPdf() throws JRException, IOException {

		JasperPrint jasperPrint;
		File jasper = new File(FacesContext.getCurrentInstance()
				.getExternalContext()
				.getRealPath("/Rapports/rptAffilie.jasper"));
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("Affilie", name);
		parameters.put("com", com);

		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(
				getDatasource());
		jasperPrint = JasperFillManager.fillReport(jasper.getPath(),
				parameters, beanCollectionDataSource);

		HttpServletResponse reponse = (HttpServletResponse) FacesContext
				.getCurrentInstance().getExternalContext().getResponse();

		reponse.addHeader("Content-disposition", "attachment; filename=" + name
				+ ".pdf");
		reponse.setContentLength(10000);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		reponse.setContentType("application/pdf");

		ServletOutputStream stream = null;

		stream = reponse.getOutputStream();

		// Export To PDF
		JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

		FacesContext.getCurrentInstance().responseComplete();
		addMessage("Vous avez en train de télècharger votre rapport!");

	}

	
	
	
	


	 private Date du;
	 private Date au;
	
	 public Date getDu() {
	 return du;
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
	 
	 public void addMessage(String summary) {
	        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
	        FacesContext.getCurrentInstance().addMessage(null, message);
	    }

}
