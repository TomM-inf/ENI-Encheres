package fr.eni.encheres.bll;

import java.sql.SQLException;

import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.RetraitDAO;

public class RetraitManager {
	private RetraitDAO retraitDAO;
	
	public RetraitManager() {
		retraitDAO = DAOFactory.getDAORetrait();
	}
	
	public boolean addRetrait(Integer noArticle, String rue, String codePostal, String ville) throws BLLException {
		boolean retour = false;
		try {
			retour = retraitDAO.addRetrait(noArticle, rue, codePostal, ville);
		} catch (SQLException e) {
			throw new BLLException();
		}
		return retour;
	}
}
