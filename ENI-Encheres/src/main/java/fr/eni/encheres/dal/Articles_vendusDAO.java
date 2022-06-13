package fr.eni.encheres.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.encheres.bo.Articles_vendus;

public interface Articles_vendusDAO {

	public List<Articles_vendus> getArticlesVendus() throws SQLException;
	
	public List<Articles_vendus> getArticlesVendusParMotCle(String motCle) throws SQLException;
	
	public List<Articles_vendus> getArticlesVendusParCategorie(String categorie) throws SQLException;
	
	public List<Articles_vendus> getArticlesVendusParMotCleEtCategorie(String motCle, String categorie) throws SQLException;
	
	public boolean addArticleVendu() throws SQLException;
}
