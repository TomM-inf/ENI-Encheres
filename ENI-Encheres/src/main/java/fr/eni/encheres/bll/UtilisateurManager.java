package fr.eni.encheres.bll;

import java.sql.SQLException;

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.UtilisateurDAO;

public class UtilisateurManager {

	private UtilisateurDAO utilisateurDAO;
	
	public UtilisateurManager() {
		utilisateurDAO = DAOFactory.getDAOUtilisateur();
	}
	public Utilisateur verifierConnexion(String login, String pw) throws BLLException {
		Utilisateur utilisateur = null;
		try {
			utilisateur = utilisateurDAO.verifierConnexion(login, pw);
		} catch (SQLException e) {
			throw new BLLException(e);
		}
		return utilisateur;
	}
	
	public Utilisateur getUtilisateurParId(int idUtil) throws BLLException {
		Utilisateur utilisateur = null;
		try {
			utilisateur = utilisateurDAO.getUtilisateurParId(idUtil);
		} catch (SQLException e) {
			throw new BLLException(e);
		}
		return utilisateur;
	}
}
