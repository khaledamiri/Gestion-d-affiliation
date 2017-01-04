package com.file;

import java.io.InputStream;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import main.java.com.dao.FileIdao;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean
public class FileDownloadView {

	private StreamedContent file;
	private String path;
	private FileIdao filedao;

	public FileDownloadView() {
		InputStream stream = ((ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext())
				.getResourceAsStream("/uploaded/wspret.zip");
		file = new DefaultStreamedContent(stream, "application/zip",
				"Banniere.zip");
		
	}
	
	
	private void Init() {
		// TODO Auto-generated method stub

	}
	
	public String avoirpath(int n){
		
		String a=filedao.getPathFile(1);
		return a;
		
	}

	public StreamedContent getFile() {
		return file;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public FileIdao getFiledao() {
		return filedao;
	}

	public void setFiledao(FileIdao filedao) {
		this.filedao = filedao;
	}

	
}