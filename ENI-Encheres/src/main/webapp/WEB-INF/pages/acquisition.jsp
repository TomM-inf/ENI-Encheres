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
	<h1>Vous avez remporté la vente</h1>
	<div class="lignes-acquisition">
		<label>${article.nomArticle}</label>
	</div>
	<div class="lignes-acquisition">
		<label>Description :</label><label>${article.description}</label>
	</div>
	<div class="lignes-acquisition">
		<label>Meilleure offre :</label><label>${article.prixVente}</label>
	</div>
	<div class="lignes-acquisition">
		<label>Mise à prix :</label><label>${article.prixInitial}</label>
	</div>
	<div class="lignes-acquisition">
		<label>Retrait :</label><label>${retrait.rue}</label>
	</div>
	<div class="lignes-acquisition">
		<label></label><label>${retrait.codePostal}</label><label>${retrait.ville}</label>
	</div>
	<div class="lignes-acquisition">
		<label>Vendeur :</label><label>${vendeur.pseudo}</label>
	</div>
	<div class="lignes-acquisition">
		<label>Tel :</label><label>${vendeur.telephone}</label>
	</div>
	<a href="${pageContext.request.contextPath }/">
		<button>Retour</button>
	</a>
</body>
</html>