<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="./css/main.css">
<meta charset="ISO-8859-1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="./js/functions.js" type="text/javascript"></script>
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="./headerConnecte.jsp"></jsp:include>
	<div class="afficher-profil">
	<input type="hidden" id="infoMsg" value="${infoMsg}">
	<%
	if (request.getSession().getAttribute("infoMsg") != null) {
		request.getSession().removeAttribute("infoMsg");
	}
	%>
	<h1>Mon profil</h1>
	<form method="post" action="${pageContext.request.contextPath }/modifierProfil">
		<div class="left-and-right">
			<div class="left all-groups">
				<div class="label-input-group">
					<!--  Pseudo -->
					<label for="pseudo">Pseudo :</label> <input type="text"
						name="pseudo" value="${utilisateur.pseudo}">
				</div>
				<div class="label-input-group">
					<!--  Prenom -->
					<label for="prenom">Prenom :</label> <input type="text"
						name="prenom" value="${utilisateur.prenom}">
				</div>
				<div class="label-input-group">
					<!--  Tel -->
					<label for="tel">Telephone :</label> <input type="text" name="tel"
						value="${utilisateur.telephone}">
				</div>
				<div class="label-input-group">
					<!--  CP -->
					<label for="cp">Code postal :</label> <input type="text" name="cp"

						value="${utilisateur.codePostal}">
				</div>
				<div class="label-input-group">
					<!--  PW -->
					<label for="pw">Mot de passe actuel:</label> <input type="password"
						name="pw" value="">
				</div>
				<div class="label-input-group">
					<!--  PW -->
					<label for="new-pw">Nouveau mot de passe:</label> <input type="password"
						name="new-pw" value="">
				</div>
				<div class="label-input-group">
					<!--  Crédits -->
					<label for="credits">Crédits:</label> <input type="text"
						name="credits" value="${utilisateur.credit}" disabled="disabled">
				</div>
			</div>
			<div class="left all-groups">
				<div class="label-input-group">
					<!--  Nom -->
					<label for="nom">Nom :</label> <input type="text" name="nom"
						value="${utilisateur.nom}">
				</div>
				<div class="label-input-group">
					<!--  Email -->
					<label for="email">Email :</label> <input type="email" name="email"
						value="${utilisateur.email}">
				</div>
				<div class="label-input-group">
					<!--  Rue -->
					<label for="rue">Rue :</label> <input type="text" name="rue"
						value="${utilisateur.rue}">
				</div>
				<div class="label-input-group">
					<!--  Ville -->
					<label for="ville">Ville :</label> <input type="text" name="ville"
						value="${utilisateur.ville}">
				</div>
				<div class="label-input-group">
					<!--  PW Confirmation -->
					<label for="confirm-pw">Confirmation: </label> <input type="password"
						name="confirm-pw" value="">
				</div>
			</div>
		</div>
		<input type="submit" value="Enregistrer"  >
	</form>
	<a href="${pageContext.request.contextPath }/">
		<button>Annuler</button>
	</a>
	</div>
</body>
</html>
