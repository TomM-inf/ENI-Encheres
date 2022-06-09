package fr.eni.encheres.dal;

import fr.eni.encheres.dal.sqlServer.ArticleVendusDAOSqlServerImpl;
import fr.eni.encheres.dal.sqlServer.UtilisateurDAOSqlServerImpl;

public class DAOFactory {

	public static UtilisateurDAO getDAOUtilisateur() {
		return new UtilisateurDAOSqlServerImpl();
	}
	
	public static Articles_vendusDAO getDAOArticlesVendus() {
		return new ArticleVendusDAOSqlServerImpl();
	}
}
