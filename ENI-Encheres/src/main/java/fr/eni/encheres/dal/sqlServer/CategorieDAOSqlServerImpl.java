package fr.eni.encheres.dal.sqlServer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.Articles_vendus;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.dal.CategorieDAO;
import fr.eni.encheres.dal.ConnectionProvider;

public class CategorieDAOSqlServerImpl implements CategorieDAO {
	private static final String GETALL = "SELECT * FROM CATEGORIES";
	
	@Override
	public List<Categorie> getAllCategorie() throws SQLException {
		Connection conn = null;
		List<Categorie> listCategorie = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			listCategorie = new ArrayList<Categorie>();

			PreparedStatement stmt = conn.prepareStatement(GETALL);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Categorie categorie = new Categorie();
				categorie.setNumCategorie(rs.getInt("no_categorie"));
				categorie.setLibelle(rs.getString("libelle"));
				listCategorie.add(categorie);
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
		
		
		return listCategorie;
	}

}
