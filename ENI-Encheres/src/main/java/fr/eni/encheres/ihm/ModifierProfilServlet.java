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

@WebServlet("/modifierProfil")
public class ModifierProfilServlet extends HttpServlet {

	private UtilisateurManager UtilisateurMger;

	public ModifierProfilServlet() {

		UtilisateurMger = new UtilisateurManager();

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/pages/modifierProfil.jsp").forward(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String pseudo = req.getParameter("pseudo");
		String prenom = req.getParameter("prenom");
		String tel = req.getParameter("tel");
		String cp = req.getParameter("cp");
		String newPW = req.getParameter("new-pw");
		String pw = req.getParameter("pw");

		String nom = req.getParameter("nom");
		String email = req.getParameter("email");
		String rue = req.getParameter("rue");
		String ville = req.getParameter("ville");
		String confirmPW = req.getParameter("confirm-pw");

		boolean enregistre = false;

		Utilisateur utilisateurSession = (Utilisateur) req.getSession().getAttribute("utilisateur");

		if(!newPW.isEmpty() && !confirmPW.isEmpty()) {
			if (!UtilisateurMger.getMD5EncryptedValue(pw).equals(utilisateurSession.getMotDePasse())) {
				req.getSession().setAttribute("infoMsg", "Le mot de passe actuel n'est pas le bon");
				res.sendRedirect(req.getContextPath() + "/modifierProfil");
				return;
			}
		}
		if (!newPW.equals(confirmPW)) {
			req.getSession().setAttribute("infoMsg", "Veuillez saisir un mot de passe identique dans les deux champs.");
			res.sendRedirect(req.getContextPath() + "/modifierProfil");
			return;
		}
		if (pseudo.length() == 0 || prenom.length() == 0 || cp.length() == 0 || nom.length() == 0
				|| email.length() == 0 || rue.length() == 0 || ville.length() == 0) {
			req.getSession().setAttribute("infoMsg", "Veuillez renseigner tous les champs obligatoires.");
			res.sendRedirect(req.getContextPath() + "/modifierProfil");
			return;
		}

		try {
			if (!email.equals(utilisateurSession.getEmail())) {
				if (UtilisateurMger.getUtilisateurByEmail(email) instanceof Utilisateur) {
					req.getSession().setAttribute("infoMsg", "Un utilisateur est déjà enregistré avec cet email.");
					res.sendRedirect(req.getContextPath() + "/modifierProfil");
					return;
				}
			}
			if (!pseudo.equals(utilisateurSession.getPseudo())) {
				if (UtilisateurMger.getUtilisateurByPseudo(pseudo) instanceof Utilisateur) {
					req.getSession().setAttribute("infoMsg", "Un utilisateur est déjà enregistré avec ce pseudo.");
					res.sendRedirect(req.getContextPath() + "/modifierProfil");
					return;
				}
			}
		} catch (BLLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Utilisateur utilisateurNouvellesInfos = utilisateurSession;
		utilisateurNouvellesInfos.setCodePostal(cp);
		utilisateurNouvellesInfos.setEmail(email);
		utilisateurNouvellesInfos.setMotDePasse(confirmPW);
		utilisateurNouvellesInfos.setNom(nom);
		utilisateurNouvellesInfos.setPrenom(prenom);
		utilisateurNouvellesInfos.setPseudo(pseudo);
		utilisateurNouvellesInfos.setRue(rue);
		utilisateurNouvellesInfos.setTelephone(tel);
		utilisateurNouvellesInfos.setVille(ville);
		try {
			enregistre = UtilisateurMger.modificationUtilisateur(utilisateurNouvellesInfos);
		} catch (BLLException e) {
			e.printStackTrace();
		}
		if (enregistre) {
			req.getSession().setAttribute("infoMsg", "Informations enregistrées avec succès.");
			res.sendRedirect(req.getContextPath() + "/afficherProfil");
		} else {
			req.getSession().setAttribute("infoMsg", "Erreur, les informations n'ont pas pu être sauvegardées.");
			res.sendRedirect(req.getContextPath() + "/modifierProfil");
		}
	}
}
