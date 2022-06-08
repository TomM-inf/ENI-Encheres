package fr.eni.encheres.dal;

import fr.eni.encheres.dal.sqlServer.UtilisateurDAOSqlServerImpl;

public class DAOFactory {

	public static UtilisateurDAO getDAOUtilisateur() {
		return new UtilisateurDAOSqlServerImpl();
	}
}
