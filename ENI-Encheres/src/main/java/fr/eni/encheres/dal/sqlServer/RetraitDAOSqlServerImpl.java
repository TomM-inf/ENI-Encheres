package fr.eni.encheres.dal.sqlServer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.encheres.dal.ConnectionProvider;
import fr.eni.encheres.dal.RetraitDAO;

public class RetraitDAOSqlServerImpl implements RetraitDAO {
	private static final String ADD_RETRAIT = "INSERT INTO RETRAITS VALUES (?, ?, ?, ?)";

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

}
