package fr.eni.encheres.dal;

import java.sql.SQLException;

import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Utilisateur;

public interface EnchereDAO {

	public int getDernierEncherisseurByIDArticleVendu(int idArticleVendu) throws SQLException;
	public Enchere getBestOfferByIDArticleVendu(int idArticleVendu) throws SQLException;
	public boolean removeAuctioneerCredit(Utilisateur user, int offer, int noArticle) throws SQLException;
	
	public boolean getEnchereUtilisateurConnecte(int noArticle, int noUtilisateur) throws SQLException;

	public Enchere getBestOfferByIDArticleVendu(int idArticleVendu) throws SQLException;
}
