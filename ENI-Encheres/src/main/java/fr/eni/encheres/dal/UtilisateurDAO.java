package fr.eni.encheres.dal;

import java.sql.SQLException;

import fr.eni.encheres.bo.Utilisateur;

public interface UtilisateurDAO {

	public Utilisateur verifierConnexion(String login, String pw) throws SQLException;
	public Utilisateur getUtilisateurByPseudo(String pseudo) throws SQLException;
	public Utilisateur getUtilisateurParId(int id) throws SQLException;
	public Utilisateur getUtilisateurByEmail(String email) throws SQLException;
	public boolean inscription(Utilisateur user) throws SQLException;
	public String getMD5EncryptedValue(String password);
	public boolean modificationUtilisateur(Utilisateur utilisateur) throws SQLException;
	public boolean isAlphaNumeric(String s);

}
