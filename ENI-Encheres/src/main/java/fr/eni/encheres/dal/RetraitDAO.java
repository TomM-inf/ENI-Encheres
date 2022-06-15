package fr.eni.encheres.dal;

import java.sql.SQLException;

public interface RetraitDAO {
	
	public boolean addRetrait(int noArticle, String rue, String codePostal, String ville) throws SQLException;
}
