package fr.eni.encheres.bll;

import java.sql.SQLException;

import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.EnchereDAO;

public class EnchereManager {

	private EnchereDAO enchereDAO;
	
	public EnchereManager() {
		enchereDAO = DAOFactory.getDAOEnchere();
	}
	/**
	 * Retourne l'id du dernier encherisseur d'une enchere
	 * @param idArticleVendu l'id de l'article aux encheres
	 * @return
	 */
	public int getIdDernierEncherisseur(int idArticleVendu) {
		int id = 0;
		try {
			id = enchereDAO.getDernierEncherisseurByIDArticleVendu(idArticleVendu);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

  test
	public boolean getEnchereUtilisateurConnecte(int noArticle, int noUtilisateur) {
		boolean retour = false;
		try {
			retour = enchereDAO.getEnchereUtilisateurConnecte(noArticle, noUtilisateur);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retour;
	}

	public Enchere getBestOfferByIDArticleVendu(int ID) {
		Enchere enchere = null;
		
		try {
			enchere = enchereDAO.getBestOfferByIDArticleVendu(ID);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return enchere;
	}
	
}
