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
import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.CategorieManager;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Articles_vendus;

@WebServlet("/vendreArticle")
public class VendreArticleServlet extends HttpServlet {
	private ArticlesVendusManager articlesVendusMng;
	private UtilisateurManager utilisateurManager;
	private CategorieManager categorieManager;
	
	public VendreArticleServlet() {
		articlesVendusMng = new ArticlesVendusManager();
		utilisateurManager = new UtilisateurManager();
		categorieManager = new CategorieManager();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			req.setAttribute("listCategorie", categorieManager.getAllCategorie());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		req.getRequestDispatcher("/WEB-INF/pages/vendreArticle.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
}
