package fr.eni.encheres.dal;

import java.sql.SQLException;

public interface EnchereDAO {

	public int getDernierEncherisseurByIDArticleVendu(int idArticleVendu) throws SQLException;
}