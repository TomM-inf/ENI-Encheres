package fr.eni.encheres.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.encheres.bo.Categorie;

public interface CategorieDAO {
	public List<Categorie> getAllCategorie() throws SQLException;
}
