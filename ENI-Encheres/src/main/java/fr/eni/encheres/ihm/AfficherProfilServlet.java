package fr.eni.encheres.ihm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.EnchereManager;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Articles_vendus;
import fr.eni.encheres.bo.Utilisateur;

@WebServlet("/afficherProfil")
public class AfficherProfilServlet extends HttpServlet {

	private UtilisateurManager utilisateurMger;
	private EnchereManager enchereMger;

	public AfficherProfilServlet() {
		utilisateurMger = new UtilisateurManager();
		enchereMger = new EnchereManager();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getParameter("monProfil") != null) {
			Utilisateur utilisateurSession = (Utilisateur) req.getSession().getAttribute("utilisateur");
			req.setAttribute("utilisateur", utilisateurSession);
			req.setAttribute("simple", false);
			req.setAttribute("enchereRemporte", false);
			req.setAttribute("monProfil", true);
			req.getRequestDispatcher("/WEB-INF/pages/profil.jsp").forward(req, resp);
		}
//		if(req.getParameter("monProfil").equals("84751547")) {}
		else {
			String pseudo = req.getParameter("pseudo");
			String etat = req.getParameter("etat");
			String id = req.getParameter("id");
			Utilisateur utilisateur = null;
			try {
				utilisateur = utilisateurMger.getUtilisateurByPseudo(pseudo);
				Utilisateur utilisateurSession = (Utilisateur) req.getSession().getAttribute("utilisateur");
				req.setAttribute("utilisateur", utilisateur);
				req.setAttribute("simple", false);
				req.setAttribute("enchereRemporte", false);
				req.setAttribute("monProfil", false);
				if (pseudo.equals(utilisateurSession.getPseudo())) {
					req.setAttribute("monProfil", true);
				} else if (etat.equals("Remporté")) {
					int idAcheteur = enchereMger.getIdDernierEncherisseur(Integer.parseInt(id));
					if (idAcheteur == utilisateurSession.getNoUtilisateur()) {
						req.setAttribute("enchereRemporte", true);
					}
				} else if (req.getSession().getAttribute("utilisateur") != null) {
					req.setAttribute("simple", true);
				}
				req.getRequestDispatcher("/WEB-INF/pages/profil.jsp").forward(req, resp);
			} catch (BLLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/pages/modifierProfil.jsp").forward(req, resp);
	}
}
