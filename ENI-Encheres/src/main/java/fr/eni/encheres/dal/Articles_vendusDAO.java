package fr.eni.encheres.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.encheres.bo.Articles_vendus;

public interface Articles_vendusDAO {

	public List<Articles_vendus> getArticlesVendus() throws SQLException;
	
	public List<Articles_vendus> getArticlesVendusParMotCle(String motCle) throws SQLException;
	
	public List<Articles_vendus> getArticlesVendusParCategorie(String categorie) throws SQLException;
	
	public List<Articles_vendus> getArticlesVendusParMotCleEtCategorie(String motCle, String categorie) throws SQLException;
	
	public Integer addArticleVendu(String nomArticle, String description, String dateDebut, String dateFin, int prixInitial, String etatVente, int noUtilisateur, int noCategorie) throws SQLException;

	public Integer getNoVendeur(int idArticle) throws SQLException;

	public Articles_vendus getArticleVenduByID(int ID) throws SQLException;
	
}
