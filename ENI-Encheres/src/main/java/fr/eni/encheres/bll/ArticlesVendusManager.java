package fr.eni.encheres.bll;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.Articles_vendus;
import fr.eni.encheres.bo.Categorie;
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

	public Integer addArticleVendu(String nomArticle, String description, String dateDebut, String dateFin,
			int prixInitial, String etatVente, int noUtilisateur, int noCategorie) throws BLLException {
		Integer retour = null;
		try {
			retour = articleVendusDAO.addArticleVendu(nomArticle, description, dateDebut, dateFin, prixInitial,
					etatVente, noUtilisateur, noCategorie);
		} catch (SQLException e) {
			throw new BLLException();
		}
		return retour;
	}

	public Integer getIdVendeur(int idArticle) throws BLLException {
		int idVendeur = 0;
		try {
			idVendeur = articleVendusDAO.getNoVendeur(idArticle);
		} catch (SQLException e) {
			throw new BLLException();
		}
		return idVendeur;
	}

	public Articles_vendus getArticleVenduByID(int ID) throws BLLException {
		Articles_vendus article = null;

		try {
			article = articleVendusDAO.getArticleVenduByID(ID);
		} catch (SQLException e) {
			throw new BLLException();
		}

		return article;

	}
	
	public Categorie getCategorieByID(int ID) throws BLLException {
		Categorie categorie = null;
		
		try {
			categorie = articleVendusDAO.getCategorieByID(ID);
		} catch (SQLException e) {
			throw new BLLException();
		}
		
		return categorie;
	}
	
}
