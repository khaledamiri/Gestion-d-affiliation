package main.java.com.controlleur;

import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

public class BannierefileCtr {
	private StreamedContent file;
	
	
	public BannierefileCtr() throws FileNotFoundException {
		InputStream stream = ((ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext())
				.getResourceAsStream("/WEB-INF/iframesrc.pdf");
		file = new DefaultStreamedContent(stream, "application/pdf",
				"iframeBanniere.pdf");
		
	}

	public void resetFail() {
       
         
        FacesMessage msg = new FacesMessage("Model reset, but it won't work properly.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	public StreamedContent getFile() {
		return file;
	}


	public void setFile(StreamedContent file) {
		this.file = file;
	}
	
	
	
}
