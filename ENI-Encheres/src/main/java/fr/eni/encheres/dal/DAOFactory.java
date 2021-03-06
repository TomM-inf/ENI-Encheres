package fr.eni.encheres.dal;

import fr.eni.encheres.dal.sqlServer.ArticleVendusDAOSqlServerImpl;
import fr.eni.encheres.dal.sqlServer.CategorieDAOSqlServerImpl;
import fr.eni.encheres.dal.sqlServer.EnchereDAOSqlServerImpl;
import fr.eni.encheres.dal.sqlServer.RetraitDAOSqlServerImpl;
import fr.eni.encheres.dal.sqlServer.UtilisateurDAOSqlServerImpl;

public class DAOFactory {

	public static UtilisateurDAO getDAOUtilisateur() {
		return new UtilisateurDAOSqlServerImpl();
	}
	
	public static Articles_vendusDAO getDAOArticlesVendus() {
		return new ArticleVendusDAOSqlServerImpl();
	}
	
	public static CategorieDAO getDAOCategorie() {
		return new CategorieDAOSqlServerImpl();
	}
	
	public static EnchereDAO getDAOEnchere() {
		return new EnchereDAOSqlServerImpl();
	}
	
	public static RetraitDAO getDAORetrait() {
		return new RetraitDAOSqlServerImpl();
	}
}
