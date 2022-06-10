package fr.eni.encheres.ihm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.ArticlesVendusManager;
import fr.eni.encheres.bll.CategorieManager;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Articles_vendus;

@WebServlet({"","/accueil"})
public class accueilServlet extends HttpServlet{
	private ArticlesVendusManager articlesVendusMng;
	private UtilisateurManager utilisateurManager;
	private CategorieManager categorieManager;
	
	public accueilServlet() {
		articlesVendusMng = new ArticlesVendusManager();
		utilisateurManager = new UtilisateurManager();
		categorieManager = new CategorieManager();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			req.setAttribute("listArticles", articlesVendusMng.getAllArticle());
			List<String> listPseudo = new ArrayList<String>();
			for (Articles_vendus art : articlesVendusMng.getAllArticle()) {
				listPseudo.add(utilisateurManager.getUtilisateurParId(art.getNoUtilisateur()).getPseudo());
			}
			req.setAttribute("listPseudo", listPseudo);
			req.setAttribute("listCategorie", categorieManager.getAllCategorie());
		} catch (Exception e) {
			// TODO: handle exception
		}
		//TODO: mettre en place sendRedirect
		req.getRequestDispatcher("/WEB-INF/pages/accueil.jsp").forward(req, resp);
	}
}
