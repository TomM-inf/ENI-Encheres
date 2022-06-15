<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Vous avez remporté la vente</h1>
	<%-- 	<div class="${monProfil ? 'afficher-profil' : 'cacher-profil'}"> --%>
	<div class="lignes-acquisition">
		<label>Description :</label><label>"${articles_vendus.description}"</label>
	</div>
	<div class="lignes-acquisition">
		<label>Meilleure offre :</label><label>"${articles_vendus.prixVente}"</label>
	</div>
	<div class="lignes-acquisition">
		<label>Mise à prix :</label><label>"${articles_vendus.prixInitial}"</label>
	</div>
	<div class="lignes-acquisition">
		<label>Retrait :</label><label>"${vendeur.rue}"</label>
	</div>
	<div class="lignes-acquisition">
		<label></label><label>"${vendeur.cp}"</label><label>"${vendeur.ville}"</label>
	</div>
	<div class="lignes-acquisition">
		<label>Vendeur :</label><label>"${vendeur.pseudo}"</label>
	</div>
	<div class="lignes-acquisition">
		<label>Tel :</label><label>"${vendeur.telephone}"</label>
	</div>
	<a href="${pageContext.request.contextPath }/">
		<button>Retour</button>
	</a>
</body>
</html>