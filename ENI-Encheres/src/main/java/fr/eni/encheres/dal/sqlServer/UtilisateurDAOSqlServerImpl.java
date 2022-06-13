package fr.eni.encheres.dal.sqlServer;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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


	@Override
	public Utilisateur verifierConnexion(String login, String pw) throws SQLException {
		Connection conn = null;
		Utilisateur utilisateur = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement stmt = conn.prepareStatement(CONNEXION_PSEUDO);
			stmt.setString(1, login);
			stmt.setString(2, pw);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				utilisateur = new Utilisateur();
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
			if (utilisateur != null) {
				return utilisateur;
			} else {
				PreparedStatement statement = conn.prepareStatement(CONNEXION_EMAIL);
				statement.setString(1, login);
				statement.setString(2, pw);
				ResultSet res = statement.executeQuery();
				if (res.next()) {
					utilisateur = new Utilisateur();
					utilisateur.setPseudo(res.getString("pseudo"));
					utilisateur.setNom(res.getString("nom"));
					utilisateur.setPrenom(res.getString("prenom"));
					utilisateur.setEmail(res.getString("email"));
					utilisateur.setTelephone(res.getString("telephone"));
					utilisateur.setRue(res.getString("rue"));
					utilisateur.setCodePostal(res.getString("code_postal"));
					utilisateur.setVille(res.getString("ville"));
					utilisateur.setMotDePasse(res.getString("mot_de_passe"));
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
        securePassword = get_SHA_512_SecurePassword(passwordToHash);
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
			System.out.println(user.toString());
			
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

	
    private static String get_SHA_512_SecurePassword(String passwordToHash) {
        String generatedPassword = null;
        String salt = null;
        
		try {
			salt = getSalt();
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes());
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16)
                        .substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }
	
    // Add salt
    private static String getSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt.toString();
    }
}
