package fr.eni.encheres.dal.sqlServer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.ConnectionProvider;
import fr.eni.encheres.dal.EnchereDAO;

public class EnchereDAOSqlServerImpl implements EnchereDAO {

	private static final String RECUPERER_DERNIER_ENCHERISSEUR = "SELECT top 1 no_utilisateur from ENCHERES WHERE no_article = ? group by date_enchere,no_utilisateur order BY CAST(date_enchere AS DATE) desc";
	private static final String GET_ENCHERE_UTILISATEUR = "SELECT * FROM ENCHERES e WHERE no_article = ? AND no_utilisateur = ?";
	private static final String GET_BEST_OFFER = "SELECT top 1 * from ENCHERES WHERE no_article = ? order BY CAST(date_enchere AS DATE) desc;";
	private static final String RECUPERER_DERNIER_ENCHERISSEUR = "SELECT top 1 no_utilisateur from ENCHERES WHERE no_article = ? group by date_enchere,no_utilisateur order BY CAST(date_enchere AS DATE) desc;";
	private static final String GET_BEST_OFFER = "SELECT top 1 * from ENCHERES WHERE no_article = ? order BY date_enchere desc;";
	private static final String REMOVE_AUCTIONEER_CREDIT = "UPDATE UTILISATEURS SET CREDIT = CREDIT - ? WHERE no_utilisateur=?;";
	private static final String SAVE_ENCHERE = "INSERT INTO ENCHERES (date_enchere, montant_enchere, no_article, no_utilisateur)"
			+ "VALUES (?, ?, ?, ?)";


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

	@Override
	public Enchere getBestOfferByIDArticleVendu(int idArticleVendu) throws SQLException {
		
		Connection conn = null;
		Enchere enchere = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement stmt = conn.prepareStatement(GET_BEST_OFFER);
			stmt.setInt(1, idArticleVendu);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				enchere = new Enchere();
				enchere.setNoArticle(rs.getInt("no_enchere"));
				enchere.setMontant(rs.getInt("montant_enchere"));
				enchere.setNoArticle(idArticleVendu);
				enchere.setNoUtilisateur(rs.getInt("no_utilisateur"));
				enchere.setDate(rs.getDate("date_enchere").toLocalDate());
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
		
		return enchere;
	}
  
  @Override
	public boolean getEnchereUtilisateurConnecte(int noArticle, int noUtilisateur) throws SQLException {
		Connection conn = null;
		boolean retour = false;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement stmt = conn.prepareStatement(GET_ENCHERE_UTILISATEUR);
			stmt.setInt(1, noArticle);
			stmt.setInt(2, noUtilisateur);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				retour = true;
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
		return retour;
	}


	@Override
	public boolean removeAuctioneerCredit(Utilisateur user, int offer, int noArticle) throws SQLException {
		boolean vretour = false;
		boolean saveEnchere = false;

		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			
			saveEnchere = saveEnchere(offer, noArticle, user.getNoUtilisateur(), conn);
			
			// STOP si l'enchère n'a pas pu être enregistrée
			if(!saveEnchere) {
				return false;
			}
			
			PreparedStatement stmt = conn.prepareStatement(REMOVE_AUCTIONEER_CREDIT);
			
			stmt.setInt(1, offer);
			stmt.setInt(2, user.getNoUtilisateur());

			int row = stmt.executeUpdate();

			if (row > 0) {
				vretour = true;
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
		
		return vretour;
	}
	
	public boolean saveEnchere(int montantEnchere, int noArticle, int noUtilisateur, Connection conn) throws SQLException {
		boolean status = false;
		
        //  LocalDateTime to Timestamp
        LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now);
		
		PreparedStatement stmt = conn.prepareStatement(SAVE_ENCHERE);

		stmt.setTimestamp(1, java.sql.Timestamp.valueOf(String.valueOf(timestamp)));
		stmt.setInt(2, montantEnchere);
		stmt.setInt(3, noArticle);
		stmt.setInt(4, noUtilisateur);

		int row = stmt.executeUpdate();

		if (row > 0) {
			status = true;
		}
		
		return status;
	}

}
