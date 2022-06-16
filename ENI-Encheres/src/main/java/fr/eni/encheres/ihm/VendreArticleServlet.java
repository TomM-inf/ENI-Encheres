package fr.eni.encheres.ihm;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.ArticlesVendusManager;
import fr.eni.encheres.bll.CategorieManager;

@WebServlet("/vendreArticle")
public class VendreArticleServlet extends HttpServlet {
	private CategorieManager categorieManager;
	private ArticlesVendusManager articleVendusManager;
	
	public VendreArticleServlet() {
		categorieManager = new CategorieManager();
		articleVendusManager = new ArticlesVendusManager();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			req.setAttribute("listCategorie", categorieManager.getAllCategorie());
		} catch (Exception e) {
			e.printStackTrace();
		}
		req.getRequestDispatcher("/WEB-INF/pages/vendreArticle.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String nomArticle = req.getParameter("nomArticle");
			String description = req.getParameter("description");
			String dateDebut = req.getParameter("dateDebut");
			String dateFin = req.getParameter("dateFin");
			String sqlDateDebut = dateDebut.substring(8, 10).concat("-").concat(dateDebut.substring(5, 7)).concat("-").concat(dateDebut.substring(0, 4));
			String sqlDateFin = dateFin.substring(8, 10).concat("-").concat(dateFin.substring(5, 7)).concat("-").concat(dateFin.substring(0, 4));
			Integer prixInital = Integer.parseInt(req.getParameter("prixInitial"));
			Integer noUtilisateur = Integer.parseInt(req.getParameter("utilisateur"));
			String nomCategorie = req.getParameter("categorie");
			Integer noCategorie = categorieManager.getCategorieParNom(nomCategorie).getNumCategorie();
			articleVendusManager.addArticleVendu(nomArticle, description, sqlDateDebut, sqlDateFin, prixInital, "Créée", noUtilisateur, noCategorie);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//redirection vers l'accueil
		resp.sendRedirect("./accueil");
	}
}
