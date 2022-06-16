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

	private static final String GET_ARTICLE_BY_ID = "SELECT * FROM ARTICLES_VENDUS WHERE no_article=?";
	private static final String GETALL = "SELECT * FROM ARTICLES_VENDUS ORDER BY date_debut_encheres DESC";
	private static final String GETMOTCLE = "SELECT * FROM ARTICLES_VENDUS WHERE nom_article LIKE ? ORDER BY date_debut_encheres DESC";
	private static final String GETCATE = "SELECT * FROM ARTICLES_VENDUS a, CATEGORIES c WHERE a.no_categorie = c.no_categorie AND c.libelle = ? ORDER BY date_debut_encheres DESC"; 
	private static final String GETMOTCLECATE = "SELECT * FROM ARTICLES_VENDUS a, CATEGORIES c WHERE a.no_categorie = c.no_categorie AND c.libelle = ? AND nom_article LIKE ? ORDER BY date_debut_encheres DESC";
	private static final String ADD_ARTICLE = "INSERT INTO ARTICLES_VENDUS VALUES (?, ?, ?, ?, ?, null, ?, ?, ?);";
	
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
		Connection conn = null;
		List<Articles_vendus> listArticles = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			listArticles = new ArrayList<Articles_vendus>();

			PreparedStatement stmt = conn.prepareStatement(GETMOTCLE);
			stmt.setString(1, "%"+motCle+"%");
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
	public List<Articles_vendus> getArticlesVendusParCategorie(String categorie) throws SQLException {
		Connection conn = null;
		List<Articles_vendus> listArticles = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			listArticles = new ArrayList<Articles_vendus>();

			PreparedStatement stmt = conn.prepareStatement(GETCATE);
			stmt.setString(1, categorie);
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
	public List<Articles_vendus> getArticlesVendusParMotCleEtCategorie(String motCle, String categorie) throws SQLException {
		Connection conn = null;
		List<Articles_vendus> listArticles = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			listArticles = new ArrayList<Articles_vendus>();

			PreparedStatement stmt = conn.prepareStatement(GETMOTCLECATE);
			stmt.setString(1, categorie);
			stmt.setString(2, "%"+motCle+"%");
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
	
	
	/*
	 * Method création d'une liste d'article
	 * @Params: ResultSet retour
	 */
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
	@Override
	public Articles_vendus getArticleVenduByID(int ID) throws SQLException {
		
		Connection conn = null;
		Articles_vendus article = null;
		try {
			conn = ConnectionProvider.getConnection();

			PreparedStatement stmt = conn.prepareStatement(GET_ARTICLE_BY_ID);
			stmt.setInt(1, ID);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				article = new Articles_vendus();
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

		return article;
	}

}
