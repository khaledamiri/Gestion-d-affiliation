package com.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import main.java.com.dao.BannierIdao;
import main.java.com.domaine.Banniere;

import org.primefaces.event.FileUploadEvent;

@ManagedBean
@SessionScoped
public class FileUploadView {

	BannierIdao banndao;
	public Banniere banniere = new Banniere();
	private static String imgName;


	public void resetFail() {
		banniere=new Banniere();
         
//        FacesMessage msg = new FacesMessage("Model reset, but it won't work properly.");
//        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	
	
	public void enregistrebanniere() {
//
//		List<Banniere> l = banndao.findAll();
//		
//		for(Banniere b :l){
//			if (b.getDESIGN_BANN().equals(banniere.getDESIGN_BANN())) {
//			
//				FacesContext context = FacesContext.getCurrentInstance();
//				context.addMessage(null, new FacesMessage("Banniére déja ajoutée",
//						"Votre Banniére est déja ajoutée "));
//				 }
//		
//		else
//		{
			
			
			banniere.setLIEN_DE_BANN(imgName);
			banndao.savebann(banniere);
			
			

			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Banniére ajoutée",
					"Votre Banniére est ajoutée avec succé"));

			banniere=new Banniere();
			
			
//		}
			
//		}
		

	}

	private String destination = "C:\\Users\\Amiri\\workspace\\PFE\\WebContent\\uploaded\\";

	public void handleFileUpload(FileUploadEvent event) {
		FacesMessage msg = new FacesMessage("Success! ", event.getFile()
				.getFileName() + " is uploaded.");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		// Do what you want with the file
		System.out.println(event.getFile().getFileName());
		imgName = event.getFile().getFileName();

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

	public BannierIdao getBanndao() {
		return banndao;
	}

	public void setBanndao(BannierIdao banndao) {
		this.banndao = banndao;
	}

	public Banniere getBanniere() {
		return banniere;
	}

	public void setBanniere(Banniere banniere) {
		this.banniere = banniere;
	}

}