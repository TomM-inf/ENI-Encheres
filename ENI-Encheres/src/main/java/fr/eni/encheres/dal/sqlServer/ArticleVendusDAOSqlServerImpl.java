package fr.eni.encheres.dal.sqlServer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.Articles_vendus;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.Articles_vendusDAO;
import fr.eni.encheres.dal.ConnectionProvider;

public class ArticleVendusDAOSqlServerImpl implements Articles_vendusDAO {

	private static final String GET_ARTICLE_BY_ID = "SELECT * FROM ARTICLES_VENDUS WHERE no_article=?";
	private static final String GET_CATEGORIE_BY_ID = "SELECT * FROM CATEGORIES WHERE no_categorie=?";
	private static final String GETALL = "SELECT * FROM ARTICLES_VENDUS ORDER BY date_debut_encheres DESC";
	private static final String GETMOTCLE = "SELECT * FROM ARTICLES_VENDUS WHERE nom_article LIKE ? ORDER BY date_debut_encheres DESC";
	private static final String GETCATE = "SELECT * FROM ARTICLES_VENDUS a, CATEGORIES c WHERE a.no_categorie = c.no_categorie AND c.libelle = ? ORDER BY date_debut_encheres DESC"; 
	private static final String GETMOTCLECATE = "SELECT * FROM ARTICLES_VENDUS a, CATEGORIES c WHERE a.no_categorie = c.no_categorie AND c.libelle = ? AND nom_article LIKE ? ORDER BY date_debut_encheres DESC";
	private static final String GET_ARTICLE_RETRAIT = "SELECT no_article FROM ARTICLES_VENDUS WHERE nom_article = ? AND description = ? AND date_debut_encheres = convert(datetime, ?,103) AND date_fin_encheres = convert(datetime, ?,103) AND prix_initial = ? AND no_utilisateur = ? AND no_categorie = ?";
	private static final String ADD_ARTICLE = "INSERT INTO ARTICLES_VENDUS VALUES (?, ?, convert(datetime, ?,103), convert(datetime, ?,103), ?, null, ?, ?, ?)";
	private static final String GET_ID_VENDEUR = "SELECT no_utilisateur FROM ARTICLES_VENDUS WHERE no_article = ?";
	private static final String SET_RETRAIT_EFFECTUE = "UPDATE ARTICLES_VENDUS SET etat_vente = ? WHERE no_article = ?";
	
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
	@Override
	public Integer getNoVendeur(int idArticle) throws SQLException {
		Connection conn = null;
		int noUtilisateur = 0;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement stmt = conn.prepareStatement(GET_ID_VENDEUR);
			stmt.setInt(1, idArticle);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				noUtilisateur = rs.getInt("no_utilisateur");
			} else {
				noUtilisateur = 0;
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
		
		return noUtilisateur;
	} 

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

	@Override
	public Categorie getCategorieByID(int ID) throws SQLException {
		
		Connection conn = null;
		Categorie categorie = null;
		try {
			conn = ConnectionProvider.getConnection();

			PreparedStatement stmt = conn.prepareStatement(GET_CATEGORIE_BY_ID);
			stmt.setInt(1, ID);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				categorie = new Categorie();
				categorie.setNumCategorie(ID);
				categorie.setLibelle(rs.getString("libelle"));
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

		return categorie;
	}

	public boolean setRetraitEffectue(int idArticle) throws SQLException {
		boolean res = false;
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			PreparedStatement stmt = conn.prepareStatement(SET_RETRAIT_EFFECTUE);
			stmt.setString(1, "Retrait effectué");
			stmt.setInt(2, idArticle);
			int row = stmt.executeUpdate();
			if(row > 0) {
				res = true;
			}
		} catch (SQLException e) {
//			conn.rollback();
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

		return res;
	}

}
