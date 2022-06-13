package fr.eni.encheres.dal;

import java.sql.SQLException;

import fr.eni.encheres.bo.Utilisateur;

public interface UtilisateurDAO {

	public Utilisateur verifierConnexion(String login, String pw) throws SQLException;
	
	public Utilisateur getUtilisateurByPseudo(String pseudo) throws SQLException;
	
	public Utilisateur getUtilisateurParId(int id) throws SQLException;
}
