package fr.eni.encheres.bo;

public class Categorie {

	private int numCategorie;
	private String libelle;

	public Categorie(int numCategorie, String libelle) {
		super();
		this.numCategorie = numCategorie;
		this.libelle = libelle;
	}

	public Categorie() {
		super();
	}

	public int getNumCategorie() {
		return numCategorie;
	}

	public void setNumCategorie(int numCategorie) {
		this.numCategorie = numCategorie;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

}
