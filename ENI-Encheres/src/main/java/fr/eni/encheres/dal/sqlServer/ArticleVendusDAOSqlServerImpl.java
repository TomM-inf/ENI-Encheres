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

	private static final String GETALL = "SELECT * FROM ARTICLES_VENDUS ORDER BY date_debut_encheres DESC";
	private static final String GETMOTCLE = "SELECT * FROM ARTICLES_VENDUS WHERE nom_article LIKE ? ORDER BY date_debut_encheres DESC";
	private static final String GETCATE = "SELECT * FROM ARTICLES_VENDUS a, CATEGORIES c WHERE a.no_categorie = c.no_categorie AND c.libelle = ? ORDER BY date_debut_encheres DESC"; 
	private static final String GETMOTCLECATE = "SELECT * FROM ARTICLES_VENDUS a, CATEGORIES c WHERE a.no_categorie = c.no_categorie AND c.libelle = ? AND nom_article LIKE ? ORDER BY date_debut_encheres DESC";
	private static final String GET_ARTICLE_RETRAIT = "SELECT no_article FROM ARTICLES_VENDUS WHERE nom_article = ? AND description = ? AND date_debut_encheres = convert(datetime, ?,103) AND date_fin_encheres = convert(datetime, ?,103) AND prix_initial = ? AND no_utilisateur = ? AND no_categorie = ?";
	private static final String ADD_ARTICLE = "INSERT INTO ARTICLES_VENDUS VALUES (?, ?, convert(datetime, ?,103), convert(datetime, ?,103), ?, null, ?, ?, ?)";
	
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
	public Integer addArticleVendu(String nomArticle, String description, String dateDebut, String dateFin, int prixInitial, String etatVente, int noUtilisateur, int noCategorie) throws SQLException {
		Connection conn = null;
		Integer retour = null;
		int rs = 0;
		ResultSet rst = null;
		try {
			conn = ConnectionProvider.getConnection();

			PreparedStatement stmt = conn.prepareStatement(ADD_ARTICLE);
			stmt.setString(1, nomArticle);
			stmt.setString(2, description);
			stmt.setString(3, dateDebut);
			stmt.setString(4, dateFin);
			stmt.setInt(5, prixInitial);
			stmt.setString(6, etatVente);
			stmt.setInt(7, noUtilisateur);
			stmt.setInt(8, noCategorie);
			rs = stmt.executeUpdate();
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
		/*
		 * Si on a bien pu insérer un article alors on récupère le n° pour insersion retrait
		 * puis on renvoie le numéro dans le but de faire la requete d'insertion
		 * TODO: Non fonctionnel
		 */
		/*
		if (rs != 0) {
			Connection conn2 = null;
			try {
				conn2 = ConnectionProvider.getConnection();

				PreparedStatement stmt = conn2.prepareStatement(GET_ARTICLE_RETRAIT);
				stmt.setString(1, nomArticle);
				stmt.setString(2, description);
				stmt.setString(3, dateDebut);
				stmt.setString(4, dateFin);
				stmt.setInt(5, prixInitial);
				stmt.setString(6, etatVente);
				stmt.setInt(7, noUtilisateur);
				stmt.setInt(8, noCategorie);
				rst = stmt.executeQuery();
				System.out.println(rst.getInt("no_article"));
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
		*/
		return retour;
	} 

}
