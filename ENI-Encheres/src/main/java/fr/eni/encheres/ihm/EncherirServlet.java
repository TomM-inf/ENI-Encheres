package fr.eni.encheres.ihm;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.EnchereManager;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class encherirServlet
 */
@WebServlet("/encherir")
public class EncherirServlet extends HttpServlet {

	EnchereManager enchereMger;
	
	public EncherirServlet() {
		enchereMger = new EnchereManager();
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		// get article id
		int idArticle = Integer.valueOf(req.getParameter("noArticle"));
		
		// if user not connected redirect
		Utilisateur user = (Utilisateur) req.getSession().getAttribute("utilisateur");
		
		if(user == null) {
			req.getSession().setAttribute("infoMsg", "Veuillez vous connecter pour enchérir.");
			res.sendRedirect(req.getContextPath() + "/details?id="+idArticle);
			return;
		}
		
		// get best offer and user offer
		int offer = Integer.valueOf(req.getParameter("proposition"));
		int bestOffer = Integer.valueOf(req.getParameter("bestOffer"));
//		System.out.println("offer = " + offer);
//		System.out.println("best offer = " + bestOffer);
		
		// if user offer lower than best offer redirect
		if(offer <= bestOffer) {
			req.getSession().setAttribute("infoMsg", "Proposez une meilleure offre que celle en cours.");
			res.sendRedirect(req.getContextPath() + "/details?id="+idArticle);
			return;
		}
		
		// check if enough credit
		if(user.getCredit() < offer) {
			req.getSession().setAttribute("infoMsg", "Vous n'avez pas assez de crédit pour enchérir cette somme.");
			res.sendRedirect(req.getContextPath() + "/details?id="+idArticle);
			return;
		}
		
		// retirer le crédit de l'utilisateur qui achète
		boolean status = enchereMger.removeAuctioneerCredit(user, offer, idArticle);
		
		if(status) {
			req.getSession().setAttribute("infoMsg", "Enchère enregistrée, votre compte a été débité.");
			res.sendRedirect(req.getContextPath() + "/details?id="+idArticle);
			return;
		}
		
		
		// recréditer l'ancien meilleur enchérisseur
		
	}

}
