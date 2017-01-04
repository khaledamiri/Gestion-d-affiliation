package main.java.com.dao;

import java.util.List;

import main.java.com.domaine.Suggestion;

public interface SuggIdao {
	
	public void savesugg(Suggestion sugg);
	public void delete(Suggestion sugg);
	public List<Suggestion> findAllsugg();
	public Integer tailleemail() ;

}
