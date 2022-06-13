package fr.eni.encheres.dal.sqlServer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.encheres.dal.ConnectionProvider;
import fr.eni.encheres.dal.EnchereDAO;

public class EnchereDAOSqlServerImpl implements EnchereDAO {

	private static final String RECUPERER_DERNIER_ENCHERISSEUR = "SELECT TOP 1 no_utilisateur ,MAX(date_enchere) as max_date from ENCHERES WHERE no_article = ? group by no_utilisateur;";

	@Override
	public int getDernierEncherisseurByIDArticleVendu(int idArticleVendu) throws SQLException {
		Connection conn = null;
		int res = 0;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement stmt = conn.prepareStatement(RECUPERER_DERNIER_ENCHERISSEUR);
			stmt.setInt(1, idArticleVendu);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				res = rs.getInt(1);
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
		return res;
	}

}
