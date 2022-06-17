package fr.eni.encheres.dal.sqlServer;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.Articles_vendus;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.ConnectionProvider;
import fr.eni.encheres.dal.UtilisateurDAO;

public class UtilisateurDAOSqlServerImpl implements UtilisateurDAO {

	private static final String CONNEXION_PSEUDO = "SELECT * FROM UTILISATEURS WHERE pseudo=? AND mot_de_passe=?";
	private static final String CONNEXION_EMAIL = "SELECT * FROM UTILISATEURS WHERE email=? AND mot_de_passe=?";
	private static final String RECUPERER_PAR_PSEUDO = "SELECT * FROM UTILISATEURS WHERE pseudo=?";
	private static final String RECUPERER_PAR_ID = "SELECT * FROM UTILISATEURS WHERE no_utilisateur=?";
	private static final String RECUPERER_PAR_EMAIL = "SELECT * FROM UTILISATEURS WHERE email=?";
	private static final String INSCRIPTION = "INSERT INTO UTILISATEURS ("
			+ "pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur)"
			+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String MAJ_UTILISATEUR = "UPDATE UTILISATEURS "
			+ "SET pseudo = ?, nom = ?,prenom= ?,email = ?, telephone = ?, rue = ?, code_postal = ?, ville = ?, mot_de_passe = ?"
			+ " WHERE no_utilisateur = ?;";
	// requetes liées à la suppression d'un utilisateur
	private static final String SUPPRESSION_ENCHERES_NON_COMMENCEES = "DELETE * FROM ARTICLES_VENDUS WHERE no_utilisateur = ? AND etat_vente = ?";
	private static final String RECUPERER_ENCHERES_ENCOURS = "select * from articles_vendus where no_utilisateur = ? and etat_vente= ?";
	private static final String RECUPERER_DERNIER_ENCHERE = "select top 1 * from encheres where no_article = ?  order by montant_enchere desc;";
	private static final String REMBOURSER_CREDIT = "update utilisateurs set credit = credit + ? where no_utilisateur =?";
	private static final String HISTORISATION = "update articles_vendus set etat = ? where no_article= ?";
	
	@Override
	public Utilisateur verifierConnexion(String login, String pw) throws SQLException {
		Connection conn = null;
		Utilisateur utilisateur = null;

		// START--ENCRYPT PW
		String passwordToHash = pw;
		String securePassword = null;

		securePassword = getMD5EncryptedValue(passwordToHash);
		// END--ENCRYPT PW

		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement stmt = conn.prepareStatement(CONNEXION_PSEUDO);
			stmt.setString(1, login);
			stmt.setString(2, securePassword);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				utilisateur = new Utilisateur();
				utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
				utilisateur.setPseudo(rs.getString("pseudo"));
				utilisateur.setNom(rs.getString("nom"));
				utilisateur.setPrenom(rs.getString("prenom"));
				utilisateur.setEmail(rs.getString("email"));
				utilisateur.setTelephone(rs.getString("telephone"));
				utilisateur.setRue(rs.getString("rue"));
				utilisateur.setCodePostal(rs.getString("code_postal"));
				utilisateur.setVille(rs.getString("ville"));
				utilisateur.setMotDePasse(securePassword);
				utilisateur.setCredit(rs.getInt("credit"));
				utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
				utilisateur.setAdministrateur(rs.getBoolean("administrateur"));
				utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
			} else {
				utilisateur = null;
			}

			if (utilisateur != null) {
				return utilisateur;
			} else {
				PreparedStatement statement = conn.prepareStatement(CONNEXION_EMAIL);
				statement.setString(1, login);
				statement.setString(2, securePassword);
				ResultSet res = statement.executeQuery();
				if (res.next()) {
					utilisateur = new Utilisateur();
					utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
					utilisateur.setPseudo(res.getString("pseudo"));
					utilisateur.setNom(res.getString("nom"));
					utilisateur.setPrenom(res.getString("prenom"));
					utilisateur.setEmail(res.getString("email"));
					utilisateur.setTelephone(res.getString("telephone"));
					utilisateur.setRue(res.getString("rue"));
					utilisateur.setCodePostal(res.getString("code_postal"));
					utilisateur.setVille(res.getString("ville"));
					utilisateur.setMotDePasse(securePassword);
					utilisateur.setCredit(res.getInt("credit"));
					utilisateur.setAdministrateur(res.getBoolean("administrateur"));
				} else {
					utilisateur = null;
				}
				return utilisateur;
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
	}

	@Override
	public Utilisateur getUtilisateurByPseudo(String pseudo) throws SQLException {
		Connection conn = null;
		Utilisateur utilisateur = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement stmt = conn.prepareStatement(RECUPERER_PAR_PSEUDO);
			stmt.setString(1, pseudo);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				utilisateur = new Utilisateur();
				utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
				utilisateur.setPseudo(rs.getString("pseudo"));
				utilisateur.setNom(rs.getString("nom"));
				utilisateur.setPrenom(rs.getString("prenom"));
				utilisateur.setEmail(rs.getString("email"));
				utilisateur.setTelephone(rs.getString("telephone"));
				utilisateur.setRue(rs.getString("rue"));
				utilisateur.setCodePostal(rs.getString("code_postal"));
				utilisateur.setVille(rs.getString("ville"));
				utilisateur.setMotDePasse(rs.getString("mot_de_passe"));
				utilisateur.setCredit(rs.getInt("credit"));
				utilisateur.setAdministrateur(rs.getBoolean("administrateur"));
			} else {
				utilisateur = null;
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
		return utilisateur;
	}

	@Override
	public Utilisateur getUtilisateurByEmail(String email) throws SQLException {
		Connection conn = null;
		Utilisateur utilisateur = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement stmt = conn.prepareStatement(RECUPERER_PAR_EMAIL);
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				utilisateur = new Utilisateur();
				utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
				utilisateur.setPseudo(rs.getString("pseudo"));
				utilisateur.setNom(rs.getString("nom"));
				utilisateur.setPrenom(rs.getString("prenom"));
				utilisateur.setEmail(rs.getString("email"));
				utilisateur.setTelephone(rs.getString("telephone"));
				utilisateur.setRue(rs.getString("rue"));
				utilisateur.setCodePostal(rs.getString("code_postal"));
				utilisateur.setVille(rs.getString("ville"));
				utilisateur.setMotDePasse(rs.getString("mot_de_passe"));
				utilisateur.setCredit(rs.getInt("credit"));
				utilisateur.setAdministrateur(rs.getBoolean("administrateur"));
			} else {
				utilisateur = null;
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
		return utilisateur;
	}

	@Override
	public Utilisateur getUtilisateurParId(int id) throws SQLException {
		Connection conn = null;
		Utilisateur utilisateur = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement stmt = conn.prepareStatement(RECUPERER_PAR_ID);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				utilisateur = new Utilisateur();
				utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
				utilisateur.setPseudo(rs.getString("pseudo"));
				utilisateur.setNom(rs.getString("nom"));
				utilisateur.setPrenom(rs.getString("prenom"));
				utilisateur.setEmail(rs.getString("email"));
				utilisateur.setTelephone(rs.getString("telephone"));
				utilisateur.setRue(rs.getString("rue"));
				utilisateur.setCodePostal(rs.getString("code_postal"));
				utilisateur.setVille(rs.getString("ville"));
				utilisateur.setMotDePasse(rs.getString("mot_de_passe"));
				utilisateur.setCredit(rs.getInt("credit"));
				utilisateur.setAdministrateur(rs.getBoolean("administrateur"));
			} else {
				utilisateur = null;
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
		return utilisateur;
	}

	@Override
	public boolean inscription(Utilisateur user) throws SQLException {

		boolean vretour = false;

		String passwordToHash = user.getMotDePasse();
		String securePassword = null;

		// START--ENCRYPT PW
		securePassword = getMD5EncryptedValue(passwordToHash);
		// END--ENCRYPT PW

		Connection conn = null;
		Utilisateur utilisateur = null;
		try {
			conn = ConnectionProvider.getConnection();
			PreparedStatement stmt = conn.prepareStatement(INSCRIPTION);
			stmt.setInt(1, user.getNoUtilisateur());
			stmt.setString(1, user.getPseudo());
			stmt.setString(2, user.getNom());
			stmt.setString(3, user.getPrenom());
			stmt.setString(4, user.getEmail());
			stmt.setString(5, user.getTelephone());
			stmt.setString(6, user.getRue());
			stmt.setString(7, user.getCodePostal());
			stmt.setString(8, user.getVille());
			stmt.setString(9, securePassword);
			stmt.setInt(10, user.getCredit());
			stmt.setBoolean(11, user.isAdministrateur());

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

	@Override
	public boolean modificationUtilisateur(Utilisateur utilisateur) throws SQLException {
		boolean res = false;

		String passwordToHash = utilisateur.getMotDePasse();
		String securePassword = null;

		securePassword = getMD5EncryptedValue(passwordToHash);
		Connection conn = null;
		try {

			conn = ConnectionProvider.getConnection();
			PreparedStatement stmt = conn.prepareStatement(MAJ_UTILISATEUR);
			stmt.setString(1, utilisateur.getPseudo());
			stmt.setString(2, utilisateur.getNom());
			stmt.setString(3, utilisateur.getPrenom());
			stmt.setString(4, utilisateur.getEmail());
			stmt.setString(5, utilisateur.getTelephone());
			stmt.setString(6, utilisateur.getRue());
			stmt.setString(7, utilisateur.getCodePostal());
			stmt.setString(8, utilisateur.getVille());
			stmt.setString(9, securePassword);
			stmt.setInt(10, utilisateur.getNoUtilisateur());
			int row = stmt.executeUpdate();
			res = true;
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

	public String getMD5EncryptedValue(String password) {
		final byte[] defaultBytes = password.getBytes();
		try {
			final MessageDigest md5MsgDigest = MessageDigest.getInstance("MD5");
			md5MsgDigest.reset();
			md5MsgDigest.update(defaultBytes);
			final byte messageDigest[] = md5MsgDigest.digest();

			final StringBuffer hexString = new StringBuffer();
			for (final byte element : messageDigest) {
				final String hex = Integer.toHexString(0xFF & element);
				if (hex.length() == 1) {
					hexString.append('0');
				}
				hexString.append(hex);
			}
			password = hexString + "";
		} catch (final NoSuchAlgorithmException nsae) {
			nsae.printStackTrace();
		}
		return password;
	}

	@Override
	public boolean isAlphaNumeric(String s) {
		return s != null && s.matches("^[a-zA-Z0-9]*$");
	}

	@Override
	public boolean supprimerUtilisateur(int noUtilisateur) throws SQLException {
		boolean res = false;
		// TODO
		Connection conn = null;
		conn = ConnectionProvider.getConnection();
		supprimerEncheresNonCommencees(noUtilisateur, conn);
		List<Articles_vendus> lstArticles = getListeArticlesVendusEnCours(noUtilisateur, conn);
		for (Articles_vendus article : lstArticles) {
			Enchere enchere = getEnchereByIdArticle(article.getNoArticle(), conn);
			rembourserCredit(enchere,conn);
			historisation(article.getNoArticle(),conn);
		}

		return res;
	}

	private void supprimerEncheresNonCommencees(int idUtilisateur, Connection conn) throws SQLException {
		System.out.println("debut supprimerEncheresNonCommencees()");
		PreparedStatement stmt = conn.prepareStatement(SUPPRESSION_ENCHERES_NON_COMMENCEES);
		stmt.setInt(1, idUtilisateur);
		stmt.setString(2, "Créée");
		int row = stmt.executeUpdate();
	}

	private List<Articles_vendus> getListeArticlesVendusEnCours(int idUtilisateur, Connection conn)
			throws SQLException {
		List<Articles_vendus> lstRes = new ArrayList<Articles_vendus>();
		PreparedStatement stmt = conn.prepareStatement(RECUPERER_ENCHERES_ENCOURS);
		stmt.setInt(1, idUtilisateur);
		stmt.setString(2, "En cours");
		ResultSet rs = stmt.executeQuery();
		List<Articles_vendus> listArticles = new ArrayList<Articles_vendus>();
		while (rs.next()) {
	        //  LocalDateTime to Timestamp
			LocalDate ld = rs.getDate("date_fin_encheres").toLocalDate();
			LocalTime lt = rs.getTime("date_fin_encheres").toLocalTime();
			LocalDateTime ldt = LocalDateTime.of(ld, lt);
			
			Articles_vendus article = new Articles_vendus();
			article.setNoArticle(rs.getInt("no_article"));
			article.setNomArticle(rs.getString("nom_article"));
			article.setDescription(rs.getString("description"));
			article.setDateDebut(rs.getDate("date_debut_encheres"));
			article.setDateFin(ldt);
			article.setPrixInitial(rs.getInt("prix_initial"));
			article.setPrixVente(rs.getInt("prix_vente"));
			article.setEtatVente(rs.getString("etat_vente"));
			article.setNoUtilisateur(rs.getInt("no_utilisateur"));
			article.setNoCategorie(rs.getInt("no_categorie"));
			listArticles.add(article);
		}
		return lstRes;
	}

	private Enchere getEnchereByIdArticle(int idArticle, Connection conn) throws SQLException {
		Enchere enchere = new Enchere();
		PreparedStatement stmt = conn.prepareStatement(RECUPERER_DERNIER_ENCHERE);
		stmt.setInt(1, idArticle);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			enchere.setNoUtilisateur(rs.getInt("no_utilisateur"));
			enchere.setMontant(rs.getInt("montant_enchere"));
		}
		return enchere;
	}

	private void rembourserCredit(Enchere enchere, Connection conn) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement(REMBOURSER_CREDIT);
		stmt.setInt(1, enchere.getMontant());
		stmt.setInt(2, enchere.getNoUtilisateur());
		int row = stmt.executeUpdate();
	}
	
	private void historisation(int idArticle,Connection conn) throws SQLException{
		PreparedStatement stmt = conn.prepareStatement(HISTORISATION);
		stmt.setString(1, "Enchères terminées");
		stmt.setInt(2, idArticle);
		int row = stmt.executeUpdate();
	}
}