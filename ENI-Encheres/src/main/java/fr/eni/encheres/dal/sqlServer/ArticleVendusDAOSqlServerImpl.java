package fr.eni.encheres.dal.sqlServer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.Articles_vendus;
import fr.eni.encheres.dal.Articles_vendusDAO;
import fr.eni.encheres.dal.ConnectionProvider;

public class ArticleVendusDAOSqlServerImpl implements Articles_vendusDAO {

	private static final String GETALL = "SELECT * FROM ARTICLES_VENDUS ORDER BY date_debut_encheres";
	private static final String GETMOTCLE = "SELECT * FROM ARTICLES_VENDUS WHERE nom_article LIKE %?%";
	@Override
	public List<Articles_vendus> getArticlesVendus() throws SQLException {
		Connection conn = null;
		List<Articles_vendus> listArticles = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			listArticles = new ArrayList<Articles_vendus>();

			PreparedStatement stmt = conn.prepareStatement(GETALL);
			ResultSet rs = stmt.executeQuery();
			listArticles = this.createListArticle(rs);
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
		
		return listArticles;
	}
	@Override
	public List<Articles_vendus> getArticlesVendusParMotCle(String motCle) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Articles_vendus> getArticlesVendusParCategorie() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Articles_vendus> getArticlesVendusParMotCleEtCategorie() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	private List<Articles_vendus> createListArticle(ResultSet rs) throws SQLException{
		List<Articles_vendus> listArticles = new ArrayList<Articles_vendus>();
		while(rs.next()) {
			Articles_vendus article = new Articles_vendus();
			article.setNoArticle(rs.getInt("no_article"));
			article.setNomArticle(rs.getString("nom_article"));
			article.setDescription(rs.getString("description"));
			article.setDateDebut(rs.getDate("date_debut_encheres"));
			article.setDateFin(rs.getDate("date_fin_encheres"));
			article.setPrixInitial(rs.getInt("prix_initial"));
			article.setPrixVente(rs.getInt("prix_vente"));
			article.setEtatVente(rs.getString("etat_vente"));
			article.setNoUtilisateur(rs.getInt("no_utilisateur"));
			article.setNoCategorie(rs.getInt("no_categorie"));
			listArticles.add(article);
		}
		return listArticles;
		
	}

}
