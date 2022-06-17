package fr.eni.encheres.ihm;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.ArticlesVendusManager;
import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.CategorieManager;
import fr.eni.encheres.bll.EnchereManager;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Articles_vendus;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class DetailVenteServlet
 */
@WebServlet("/details")
public class DetailVenteServlet extends HttpServlet {
       
	private ArticlesVendusManager articlesVendusMng;
	private UtilisateurManager utilisateurManager;
	private CategorieManager categorieManager;
	private EnchereManager enchereMger;
	
	public DetailVenteServlet() {
		articlesVendusMng = new ArticlesVendusManager();
		utilisateurManager = new UtilisateurManager();
		categorieManager = new CategorieManager();
		enchereMger = new EnchereManager();
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		Utilisateur user = (Utilisateur) req.getSession().getAttribute("utilisateur");
		if(user == null) {
			res.sendRedirect(req.getContextPath() + "/");
			return;
		}
		
		Articles_vendus article = null;
		
		// get article id in request params
		int articleID = Integer.valueOf(req.getParameter("id"));
		
		Categorie categorie = null;
		int idCategorie = 0;
		
		String nomVendeur = "";
		String nomEncherisseur = "";
		Enchere enchere = null;
		LocalDate articleDateFin = null;
		
		// get article by id
		try {
			 article = articlesVendusMng.getArticleVenduByID(articleID);
			 
			 articleDateFin = article.getDateFin().toLocalDate();
			 
			 LocalDateTime now = LocalDateTime.now();
			 
			 if(article.getDateFin().isBefore(now)) {
					res.sendRedirect(req.getContextPath() + "/acquisitionEnchere?id=" + articleID);
					return;
			 }
			 
			 idCategorie = article.getNoCategorie();
			 categorie = articlesVendusMng.getCategorieByID(idCategorie);
			 nomVendeur = utilisateurManager.getUtilisateurParId(article.getNoUtilisateur()).getNom();
			 
			 // get nom dernier enchérisseur et montant
			 enchere = enchereMger.getBestOfferByIDArticleVendu(article.getNoArticle());
			 if(enchere != null) {
				 nomEncherisseur = utilisateurManager.getUtilisateurParId(enchere.getNoUtilisateur()).getNom();
			 }
			 
		} catch (BLLException e) {
			e.printStackTrace();
		}
		
//		System.out.println(article.getNoArticle());
		
		req.setAttribute("enchere", enchere);
		req.setAttribute("nomEncherisseur", nomEncherisseur);
		req.setAttribute("nomVendeur", nomVendeur);
		req.setAttribute("categorie", categorie);
		req.setAttribute("article", article);
		req.setAttribute("articleDateFin", articleDateFin);
		
		req.getRequestDispatcher("/WEB-INF/pages/DetailVente.jsp").forward(req, res);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
	}

}
