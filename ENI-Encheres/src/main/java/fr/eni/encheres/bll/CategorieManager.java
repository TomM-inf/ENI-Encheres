package fr.eni.encheres.bll;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.dal.CategorieDAO;
import fr.eni.encheres.dal.DAOFactory;

public class CategorieManager {
	private CategorieDAO categorieDAO;
	
	public CategorieManager() {
		categorieDAO = DAOFactory.getDAOCategorie();
	}
	
	public List<Categorie> getAllCategorie() throws BLLException {
		List<Categorie> maListe = new ArrayList<Categorie>();
		try {
			maListe = categorieDAO.getAllCategorie();
		} catch (SQLException e) {
			throw new BLLException();
		}
		return maListe;
	}
}
