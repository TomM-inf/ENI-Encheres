package fr.eni.encheres.ihm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Utilisateur;

@WebServlet("/inscription")
public class InscriptionServlet extends HttpServlet {

	private UtilisateurManager UtilisateurMger;

	public InscriptionServlet() {

		UtilisateurMger = new UtilisateurManager();

	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.getRequestDispatcher("/WEB-INF/pages/inscription.jsp").forward(req, res);

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		boolean registerStatus = false;
		
		req.getSession().setAttribute("infoMsg", null);
		
		String pseudo = req.getParameter("pseudo");
		String prenom = req.getParameter("prenom");
		String tel = req.getParameter("tel");
		String cp = req.getParameter("cp");
		String pw = req.getParameter("pw");

		String nom = req.getParameter("nom");
		String email = req.getParameter("email");
		String rue = req.getParameter("rue");
		String ville = req.getParameter("ville");
		String confirmPW = req.getParameter("confirm-pw");
		
		req.getSession().setAttribute("pseudo", pseudo);
		req.getSession().setAttribute("prenom", prenom);
		req.getSession().setAttribute("tel", tel);
		req.getSession().setAttribute("cp", cp);
		req.getSession().setAttribute("pw", pw);
		
		req.getSession().setAttribute("nom", nom);
		req.getSession().setAttribute("email", email);
		req.getSession().setAttribute("rue", rue);
		req.getSession().setAttribute("ville", ville);
		req.getSession().setAttribute("confirmPW", confirmPW);

		if (!pw.equals(confirmPW)) {
			req.getSession().setAttribute("infoMsg", "Veuillez saisir un mot de passe identique dans les deux champs.");
			res.sendRedirect(req.getContextPath() + "/inscription");
			return;
		}
		
		if(pseudo.length() == 0 || prenom.length() == 0 || tel.length() == 0 || cp.length() == 0 || pw.length() == 0
			|| nom.length() == 0 || email.length() == 0 || rue.length() == 0 || ville.length() == 0 || confirmPW.length() == 0) {
			req.getSession().setAttribute("infoMsg", "Veuillez renseigner tous les champs.");
			res.sendRedirect(req.getContextPath() + "/inscription");
			return;
		}
		
		try {
			if(UtilisateurMger.getUtilisateurByEmail(email) instanceof Utilisateur) {
				req.getSession().setAttribute("infoMsg", "Un utilisateur est déjà enregistré avec cet email.");
				res.sendRedirect(req.getContextPath() + "/inscription");
				return;
			}
			
			if(UtilisateurMger.getUtilisateurByPseudo(pseudo) instanceof Utilisateur) {
				req.getSession().setAttribute("infoMsg", "Un utilisateur est déjà enregistré avec ce pseudo.");
				res.sendRedirect(req.getContextPath() + "/inscription");
				return;
			}
		} catch (BLLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Utilisateur user = new Utilisateur(pseudo, nom, prenom, email, tel, rue, cp, ville, pw, 100, false);
		try {
			registerStatus = UtilisateurMger.inscription(user);
		} catch (BLLException e) {
			e.printStackTrace();
		}
		
		req.getSession().setAttribute("infoMsg", "Enregistré avec succès.");
		res.sendRedirect(req.getContextPath() + "/");
		return;
		
	}

}
