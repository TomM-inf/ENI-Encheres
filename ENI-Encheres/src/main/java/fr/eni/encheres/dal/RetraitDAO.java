package fr.eni.encheres.dal;

import java.sql.SQLException;

import fr.eni.encheres.bo.Retrait;

public interface RetraitDAO {
	
	public boolean addRetrait(int noArticle, String rue, String codePostal, String ville) throws SQLException;
	
	public Retrait getRetraitByIdArticle(int idArticle) throws SQLException;
}
