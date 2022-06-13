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
			List<Articles_vendus> listArticles = articlesVendusMng.getAllArticle();
			req.setAttribute("listArticles", listArticles);
			List<String> listPseudo = new ArrayList<String>();
			for (Articles_vendus art : listArticles) {
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
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			List<Articles_vendus> listArticles = new ArrayList<Articles_vendus>();
			if (req.getParameter("rechercheArticle").length() > 0 && req.getParameter("categorie").equals("Toutes")) {
				listArticles = articlesVendusMng.getArticleMotCle(req.getParameter("rechercheArticle"));
			}
			if (req.getParameter("rechercheArticle").length() == 0 && !req.getParameter("categorie").equals("Toutes")) {
				listArticles = articlesVendusMng.getArticleCate(req.getParameter("categorie"));
			}
			if (req.getParameter("rechercheArticle").length() > 0 && !req.getParameter("categorie").equals("Toutes")) {
				listArticles = articlesVendusMng.getArticleMotCleCate(req.getParameter("rechercheArticle"), req.getParameter("categorie"));
			}
			req.setAttribute("listArticles", listArticles);
			List<String> listPseudo = new ArrayList<String>();
			for (Articles_vendus art : listArticles) {
				listPseudo.add(utilisateurManager.getUtilisateurParId(art.getNoUtilisateur()).getPseudo());
			}
			req.setAttribute("listPseudo", listPseudo);
			req.setAttribute("listCategorie", categorieManager.getAllCategorie());
		} catch (Exception e) {
			// TODO: handle exception
		}
		req.getRequestDispatcher("/WEB-INF/pages/accueil.jsp").forward(req, resp);
	}
}