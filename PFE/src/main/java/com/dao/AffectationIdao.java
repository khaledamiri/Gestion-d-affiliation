package main.java.com.dao;

import java.util.List;

import main.java.com.domaine.Affectation;
import main.java.com.domaine.Banniere;
import main.java.com.domaine.Utilisateur;

public interface AffectationIdao {

	public void save(Affectation affectation);
	
	public void delete(Affectation aff) ;

	public List<Banniere> findAll();

	public List<Affectation> findAllAffect();

	public List<Utilisateur> findAllannounceur();

	public List<Utilisateur> findAlluserr();

	public List<Banniere> findAllien();

	public void savebann(Banniere bann);
}
