<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="./css/main.css">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="./headerConnecte.jsp"></jsp:include>
	<form action="${pageContext.request.contextPath}/acquisitionEnchere" method="post">
		<h1>"${pseudo}" a remporté la vente</h1>
		<div class="lignes-acquisition">
			<label>"${article.nomArticle}"</label>
		</div>
		<div class="lignes-acquisition">
			<label>Description :</label><label>"${article.description}"</label>
		</div>
		<div class="lignes-acquisition">
			<label>Meilleure offre :</label><label>"${article.prixVente}"</label>
		</div>
		<div class="lignes-acquisition">
			<label>Mise à prix :</label><label>"${article.prixInitial}"</label>
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
		<input type="submit" value="Retrait effectué"> <a
			href="${pageContext.request.contextPath }/">
			<button>Retour</button>
		</a>
	</form>
</body>
</html>