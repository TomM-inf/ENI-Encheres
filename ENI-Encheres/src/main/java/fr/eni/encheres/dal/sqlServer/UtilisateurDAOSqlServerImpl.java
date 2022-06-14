package fr.eni.encheres.dal.sqlServer;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
	private static final String MAJ_UTILISATEUR = "UPDATE UTILISATEURS "
			+ "SET pseudo = ?, nom = ?,prenom= ?,email = ?, telephone = ?, rue = ?, code_postal = ?, ville = ?, mot_de_passe = ?"
			+ " WHERE no_utilisateur = ?;";


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

			if(row > 0) {
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

	@Override
	public boolean isAlphaNumeric(String s) {
        return s != null && s.matches("^[a-zA-Z0-9]*$");
	}
}
