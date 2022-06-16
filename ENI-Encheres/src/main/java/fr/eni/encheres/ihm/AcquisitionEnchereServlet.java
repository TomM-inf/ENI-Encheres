package fr.eni.encheres.ihm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.ArticlesVendusManager;
import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.EnchereManager;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Articles_vendus;
import fr.eni.encheres.bo.Utilisateur;

@WebServlet("/acquisitionEnchere")
public class AcquisitionEnchereServlet extends HttpServlet {

	private UtilisateurManager utilisateurMger;
	private EnchereManager enchereMger;
	private ArticlesVendusManager articleMger;

	public AcquisitionEnchereServlet() {
		utilisateurMger = new UtilisateurManager();
		enchereMger = new EnchereManager();
		articleMger = new ArticlesVendusManager();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		int idArticle = Integer.parseInt(req.getParameter("id"));
		Utilisateur utilisateurSession = (Utilisateur) req.getSession().getAttribute("utilisateur");
		try {
			int idVendeur = articleMger.getIdVendeur(idArticle);
			int idGagnantEnchere = enchereMger.getIdDernierEncherisseur(idArticle);
			Articles_vendus articleVendu = articleMger.getArticleVenduByID(idArticle);
			Utilisateur vendeur = utilisateurMger.getUtilisateurParId(idVendeur);
			if (idGagnantEnchere == utilisateurSession.getNoUtilisateur()) {
				req.setAttribute("vendeur", vendeur);
				req.setAttribute("article", articleVendu);
				req.getRequestDispatcher("/WEB-INF/pages/acquisition.jsp").forward(req, res);
			}else if(idVendeur == utilisateurSession.getNoUtilisateur()) {
				Utilisateur gagnant = utilisateurMger.getUtilisateurParId(idGagnantEnchere);
				req.setAttribute("pseudoGagnant", gagnant.getPseudo());
				req.setAttribute("article", articleVendu);
				req.setAttribute("vendeur", vendeur);
				req.getRequestDispatcher("/WEB-INF/pages/detailMaVente.jsp").forward(req, res);
			}
		} catch (BLLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int idArticle = Integer.parseInt(req.getParameter("id"));
		boolean requeteEffectue = false;
		try {
			requeteEffectue = articleMger.setRetraitEffectue(idArticle);
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// gestion d'erreur
//		if(requeteEffectue)
		
	}

}
