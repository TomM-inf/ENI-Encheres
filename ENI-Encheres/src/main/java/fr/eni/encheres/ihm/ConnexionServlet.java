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

/**
 * Servlet implementation class ConnexionServlet
 */
@WebServlet("/connexion")
public class ConnexionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtilisateurManager UtilisateurMger;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConnexionServlet() {
    	UtilisateurMger = new UtilisateurManager();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("connectAfterRegister") == "true") {
			request.getSession().removeAttribute("connectAfterRegister");
			doPost(request, response);
		} else {
			request.getRequestDispatcher("/WEB-INF/pages/connexion.jsp").forward(request, response);
		}
		
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String login = req.getParameter("identifiant");
		String pwd = req.getParameter("motDePasse");
		
		if(login == null) {
			login = String.valueOf(req.getSession().getAttribute("pseudo"));
			pwd = String.valueOf(req.getSession().getAttribute("pw"));
		}
		
			Utilisateur utilisateur = null;
			try {
				utilisateur = UtilisateurMger.verifierConnexion(login, pwd);
				if(utilisateur != null) {
					req.getSession(true).setAttribute("utilisateur", utilisateur);
					resp.sendRedirect(req.getContextPath() + "/accueil");
				}else {
					req.setAttribute("erreur", "Connexion refusée. L'identifiant ou le mot de passe est invalide.");
					req.setAttribute("identifiant", login);
					req.getRequestDispatcher("/WEB-INF/pages/connexion.jsp").forward(req, resp);
				}
			} catch (BLLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(utilisateur == null) {
				//TODO gerer mdp invalide
			}
			
//			req.setAttribute("identifiant", login);
//			req.setAttribute("motDePasse", pwd);
//			req.setAttribute("erreur", "Connexion refusée. L'identifiant ou le mot de passe est invalide.");
//			req.getRequestDispatcher("/WEB-INF/pages/connexion.jsp").forward(req, resp);
	}

}
