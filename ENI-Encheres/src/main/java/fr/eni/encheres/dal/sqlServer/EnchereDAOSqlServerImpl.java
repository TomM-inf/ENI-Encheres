package fr.eni.encheres.dal.sqlServer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.encheres.dal.ConnectionProvider;
import fr.eni.encheres.dal.EnchereDAO;

public class EnchereDAOSqlServerImpl implements EnchereDAO {

	private static final String RECUPERER_DERNIER_ENCHERISSEUR = "SELECT top 1 no_utilisateur from ENCHERES WHERE no_article = ? group by date_enchere,no_utilisateur order BY CAST(date_enchere AS DATE) desc;";

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
