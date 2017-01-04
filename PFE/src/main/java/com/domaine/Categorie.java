package main.java.com.domaine;

public class Categorie {

	private int idCategorie;
	private String typeCategorie;

	public Categorie() {
	}

	public Categorie(int idCategorie, String typeCategorie) {
		super();
		this.idCategorie = idCategorie;
		this.typeCategorie = typeCategorie;
	}

	public int getIdCategorie() {
		return idCategorie;
	}

	public void setIdCategorie(int idCategorie) {
		this.idCategorie = idCategorie;
	}

	public String getTypeCategorie() {
		return typeCategorie;
	}

	public void setTypeCategorie(String typeCategorie) {
		this.typeCategorie = typeCategorie;
	}

}
