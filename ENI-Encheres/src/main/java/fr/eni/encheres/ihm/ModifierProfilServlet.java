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
		if (req.getParameter("submit").equals("Enregistrer")) {
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

			if (!newPW.isEmpty() && !confirmPW.isEmpty()) {
				if (!UtilisateurMger.getMD5EncryptedValue(pw).equals(utilisateurSession.getMotDePasse())) {
					req.getSession().setAttribute("infoMsg", "Le mot de passe actuel n'est pas le bon");
					res.sendRedirect(req.getContextPath() + "/modifierProfil");
					return;
				}
			}
			if (!newPW.equals(confirmPW)) {
				req.getSession().setAttribute("infoMsg",
						"Veuillez saisir un mot de passe identique dans les deux champs.");
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
				e1.printStackTrace();
			}
			Utilisateur utilisateurNouvellesInfos = utilisateurSession;
			utilisateurNouvellesInfos.setCodePostal(cp);
			utilisateurNouvellesInfos.setEmail(email);
			if (!newPW.isEmpty() && !confirmPW.isEmpty()) {
				utilisateurNouvellesInfos.setMotDePasse(confirmPW);
			}
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
				res.sendRedirect(req.getContextPath() + "/afficherProfil?monProfil=true");
			} else {
				req.getSession().setAttribute("infoMsg", "Erreur, les informations n'ont pas pu être sauvegardées.");
				res.sendRedirect(req.getContextPath() + "/modifierProfil");
			}
		}else if(req.getParameter("submit").equals("Supprimer")) {
			// encheres pas commencées qui ont le no_utilisateur du mec - > delete 
			// ======> DELETE * FROM ARTICLES_VENDUS WHERE no_utilisateur = ? AND etat_vente = ?(pas commencée)
			// encheres en cours passées en terminée > on recredite l'encherisseur
			// = on select la liste des encheres du mec, 
			// ======> select * from articles_vendus where no_utilisateur = ? and etat_vente = ?(en cours)
			// on stocke ça dans une liste d'articles vendus
			// ---- debut liste articles-----
			// on parcours cette liste et pour chaque on select le montant de l'enchere actuel et l'utilisateur qui a enchéri puis on lui crédite ça
			// ======> select top 1 montant_enchere from encheres where no_article = ?(celui qu'on recupere avant et stocké dans une liste d'articles vendus) group by montant_enchere order by montant_enchere desc;
			// on recupere le montant et le no_utilisateur on stocke ça dans objet enchere
			// select credits from utilisateurs where no_utilisateur = ?(celui stocké dans objet enchere)
			// les credits récupérés + le montant actuel de l'enchere sont ajoutés à l'utilisateur
			// ======> update utilisateurs set credits = ?(la somme) where no_utilisateur = ?(toujours celui stocké dans l'objet)
			// on historise l'article vendu ( l'enchere )
			// ======> update articles_vendus set etat = historisé/terminé where no_article = ?(celui stocké dans l'objet que l'on parcoure dans la liste)
			// ---- fin liste articles-----
			// if bid en cours de sa part, on remet celle juste avant lui 
			// ======> select * from encheres where no_utilisateur = ?(numéro du compte a suppr)
			// on stocke les resultats dans une liste d'objets encheres
			// ---- debut liste encheres ----
			// 
			// ---- fin liste encheres ----
			// verifier si le mec avant lui a encore les fonds necessaires
			// Suppression du compte
		} catch (BLLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Utilisateur utilisateurNouvellesInfos = utilisateurSession;
		utilisateurNouvellesInfos.setCodePostal(cp);
		utilisateurNouvellesInfos.setEmail(email);
		if(!newPW.isEmpty() && !confirmPW.isEmpty()) {
			utilisateurNouvellesInfos.setMotDePasse(confirmPW);
		}
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
			res.sendRedirect(req.getContextPath() + "/afficherProfil?monProfil=true");
		} else {
			req.getSession().setAttribute("infoMsg", "Erreur, les informations n'ont pas pu être sauvegardées.");
			res.sendRedirect(req.getContextPath() + "/modifierProfil");
		}
	}
}
