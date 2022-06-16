package fr.eni.encheres.dal.sqlServer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.ConnectionProvider;
import fr.eni.encheres.dal.RetraitDAO;

public class RetraitDAOSqlServerImpl implements RetraitDAO {
	private static final String ADD_RETRAIT = "INSERT INTO RETRAITS VALUES (?, ?, ?, ?)";
	private static final String GET_RETRAIT_BY_ID = "SELECT * FROM RETRAITS WHERE no_article = ?";

	@Override
	public boolean addRetrait(int noArticle, String rue, String codePostal, String ville) throws SQLException {
		boolean retour = false;
		
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();

			PreparedStatement stmt = conn.prepareStatement(ADD_RETRAIT);
			stmt.setInt(1, noArticle);
			stmt.setString(2, rue);
			stmt.setString(3, codePostal);
			stmt.setString(4, ville);
			ResultSet rs = stmt.executeQuery();
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
		
		return retour;
	}

	@Override
	public Retrait getRetraitByIdArticle(int idArticle) throws SQLException {
		Retrait retrait = null;
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement stmt = conn.prepareStatement(GET_RETRAIT_BY_ID);
			stmt.setInt(1, idArticle);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				retrait = new Retrait();
				retrait.setCodePostal(rs.getString("code_postal"));
				retrait.setRue(rs.getString("rue"));
				retrait.setVille(rs.getString("ville"));
				retrait.setNoArticle(rs.getInt("no_article"));
			} else {
				retrait = null;
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
					e.printStackTrace();
				}
			}
		}
		return retrait;
	}

}
