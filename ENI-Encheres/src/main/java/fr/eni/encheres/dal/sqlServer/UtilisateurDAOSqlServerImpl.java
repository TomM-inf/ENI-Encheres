package fr.eni.encheres.dal.sqlServer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.ConnectionProvider;
import fr.eni.encheres.dal.UtilisateurDAO;

public class UtilisateurDAOSqlServerImpl implements UtilisateurDAO {

	private static final String CONNEXION = "SELECT * FROM UTILISATEURS WHERE ?=? AND mot_de_passe=?";
	private static final String GETBYID = "SELECT * FROM UTILISATEURS WHERE no_utilisateur = ?";

	@Override
	public Utilisateur verifierConnexion(String login, String pw) throws SQLException {
		Connection conn = null;
		Utilisateur utilisateur = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement stmt = conn.prepareStatement(CONNEXION);
			stmt.setString(1, "pseudo");
			stmt.setString(2, login);
			stmt.setString(3, pw);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				if (rs.getString(1) == null) {
					return null;
				} else {
					utilisateur = new Utilisateur();
					utilisateur.setPseudo(rs.getString("pseudo"));
					utilisateur.setNom(rs.getString("nom"));
					utilisateur.setPrenom(rs.getString("prenom"));
					utilisateur.setEmail(rs.getString("email"));
					utilisateur.setTelephone(rs.getString("telephone"));
					utilisateur.setRue(rs.getString("rue"));
					utilisateur.setCodePostal(rs.getString("code_postal"));
					utilisateur.setVille(rs.getString("ville"));
					utilisateur.setMotDePasse(rs.getString("mot_de_passe"));
					utilisateur.setCredit(rs.getInt("credit"));
					utilisateur.setAdministrateur(rs.getBoolean("administrateur"));
				}
			}
			if (utilisateur != null) {
			
				return utilisateur;
			} else {
				PreparedStatement statement = conn.prepareStatement(CONNEXION);
				statement.setString(1, "email");
				statement.setString(2, login);
				statement.setString(3, pw);
				ResultSet res = statement.executeQuery();
				while (res.next()) {
					if (rs.getString(1) == null) {
						return null;
					} else {
						utilisateur = new Utilisateur();
						utilisateur.setPseudo(res.getString("pseudo"));
						utilisateur.setNom(res.getString("nom"));
						utilisateur.setPrenom(res.getString("prenom"));
						utilisateur.setEmail(res.getString("email"));
						utilisateur.setTelephone(res.getString("telephone"));
						utilisateur.setRue(res.getString("rue"));
						utilisateur.setCodePostal(res.getString("code_postal"));
						utilisateur.setVille(res.getString("ville"));
						utilisateur.setMotDePasse(res.getString("mot_de_passe"));
						utilisateur.setCredit(res.getInt("credit"));
						utilisateur.setAdministrateur(res.getBoolean("administrateur"));
					}
				}
				if(utilisateur != null) {
					return utilisateur;
				}else {
					return null;
				}
			}
		} catch (SQLException e) {

			conn.rollback();
			e.printStackTrace();
			throw e;

		} finally {
			// Fermer la connexion
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public Utilisateur getUtilisateurParId(int idUtil) throws SQLException {
		Connection conn = null;
		Utilisateur utilisateur = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement stmt = conn.prepareStatement(GETBYID);
			stmt.setInt(1, idUtil);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				utilisateur = new Utilisateur();
				utilisateur.setPseudo(rs.getString("pseudo"));
				utilisateur.setNom(rs.getString("nom"));
				utilisateur.setPrenom(rs.getString("prenom"));
				utilisateur.setEmail(rs.getString("email"));
				utilisateur.setTelephone(rs.getString("telephone"));
				utilisateur.setRue(rs.getString("rue"));
				utilisateur.setCodePostal(rs.getString("code_postal"));
				utilisateur.setVille(rs.getString("ville"));
				utilisateur.setMotDePasse(rs.getString("mot_de_passe"));
				utilisateur.setCredit(rs.getInt("credit"));
				utilisateur.setAdministrateur(rs.getBoolean("administrateur"));
			}
		} catch (SQLException e) {

			conn.rollback();
			e.printStackTrace();
			throw e;

		} finally {
			// Fermer la connexion
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return utilisateur;
	}

}
