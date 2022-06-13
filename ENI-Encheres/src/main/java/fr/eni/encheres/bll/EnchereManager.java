package fr.eni.encheres.bll;

import java.sql.SQLException;

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
}
