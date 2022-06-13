package fr.eni.encheres.ihm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Utilisateur;

@WebServlet("/afficherProfil")
public class AfficherProfilServlet extends HttpServlet{

	private UtilisateurManager UtilisateurMger;
	
	public AfficherProfilServlet() {
		UtilisateurMger = new UtilisateurManager();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//TODO recuperer les infos user
		String pseudo = req.getParameter("pseudo");
		Utilisateur utilisateur = null;
		try {
			//TODO Gérer quel affichage en fonction des boolean (simple, enchereRemporte,monProfil)
			utilisateur = UtilisateurMger.getUtilisateurByPseudo(pseudo);
			req.getSession(true).setAttribute("utilisateur", utilisateur);
			req.getRequestDispatcher("/WEB-INF/pages/profil.jsp").forward(req, resp);
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
}
