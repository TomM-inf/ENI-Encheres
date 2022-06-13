package fr.eni.encheres.bll;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.Articles_vendus;
import fr.eni.encheres.dal.Articles_vendusDAO;
import fr.eni.encheres.dal.DAOFactory;

public class ArticlesVendusManager {
	
	private Articles_vendusDAO articleVendusDAO;
	
	public ArticlesVendusManager() {
		articleVendusDAO = DAOFactory.getDAOArticlesVendus();
	}
	
	public List<Articles_vendus> getAllArticle() throws BLLException {
		List<Articles_vendus> maListe = new ArrayList<Articles_vendus>();
		try {
			maListe = articleVendusDAO.getArticlesVendus();
		} catch (SQLException e) {
			throw new BLLException();
		}
		return maListe;
	}
	
	public List<Articles_vendus> getArticleMotCle(String motcle) throws BLLException {
		List<Articles_vendus> maListe = new ArrayList<Articles_vendus>();
		try {
			maListe = articleVendusDAO.getArticlesVendusParMotCle(motcle);
		} catch (SQLException e) {
			throw new BLLException();
		}
		return maListe;
	}
	
	public List<Articles_vendus> getArticleCate(String categorie) throws BLLException {
		List<Articles_vendus> maListe = new ArrayList<Articles_vendus>();
		try {
			maListe = articleVendusDAO.getArticlesVendusParCategorie(categorie);
		} catch (SQLException e) {
			throw new BLLException();
		}
		return maListe;
	}
	
	public List<Articles_vendus> getArticleMotCleCate(String motcle, String categorie) throws BLLException {
		List<Articles_vendus> maListe = new ArrayList<Articles_vendus>();
		try {
			maListe = articleVendusDAO.getArticlesVendusParMotCleEtCategorie(motcle, categorie);
		} catch (SQLException e) {
			throw new BLLException();
		}
		return maListe;
	}
}