package fr.eni.encheres.bo;

import java.time.LocalDateTime;

public class Enchere {
	private int noEnchere;
	private int montant;
	private int noArticle;
	private int noUtilisateur;
	private LocalDateTime date;

	public Enchere() {
		super();
	}

	public Enchere(int noEnchere, int montant, int noArticle, int noUtilisateur, LocalDateTime date) {
		super();
		this.noEnchere = noEnchere;
		this.montant = montant;
		this.noArticle = noArticle;
		this.noUtilisateur = noUtilisateur;
		this.date = date;
	}

	public int getNoEnchere() {
		return noEnchere;
	}

	public void setNoEnchere(int noEnchere) {
		this.noEnchere = noEnchere;
	}

	public int getMontant() {
		return montant;
	}

	public void setMontant(int montant) {
		this.montant = montant;
	}

	public int getNoArticle() {
		return noArticle;
	}

	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}

	public int getNoUtilisateur() {
		return noUtilisateur;
	}

	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Enchere [noEnchere=" + noEnchere + ", montant=" + montant + ", noArticle=" + noArticle
				+ ", noUtilisateur=" + noUtilisateur + ", date=" + date + "]";
	}
	
	

}
