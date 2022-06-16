package fr.eni.encheres.dal;

import java.sql.SQLException;

import fr.eni.encheres.bo.Enchere;

public interface EnchereDAO {

	public int getDernierEncherisseurByIDArticleVendu(int idArticleVendu) throws SQLException;
	
	public boolean getEnchereUtilisateurConnecte(int noArticle, int noUtilisateur) throws SQLException;

	public Enchere getBestOfferByIDArticleVendu(int idArticleVendu) throws SQLException;
}
