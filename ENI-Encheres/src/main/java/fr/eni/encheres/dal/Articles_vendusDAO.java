package fr.eni.encheres.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.encheres.bo.Articles_vendus;

public interface Articles_vendusDAO {

	public List<Articles_vendus> getArticlesVendus() throws SQLException;
}
