<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Détail vente</title>
<link rel="stylesheet" href="./css/main.css">
</head>
<body>
	<h3 class="detail-title">Detail vente</h3>	
	<div class="detail-body">
		<div class="article-image"></div>

		<div class="article-information">
			<b><label>${article.nomArticle }</label></b>
			<div class="article-label-value">
				<label class="label">Description :</label>
				<label>${article.description }</label>
			</div>
			<div class="article-label-value">
				<label class="label">Catégorie :</label>
				<label>${categorie.libelle }</label>
			</div>
			<div class="article-label-value">
				<label class="label">Meilleure offre :</label>
				<label>${enchere.montant } pts par ${nomEncherisseur }</label>
			</div>
			<div class="article-label-value">
				<label class="label">Mise à prix :</label>
				<label>${article.prixInitial } points</label>
			</div>
			<div class="article-label-value">
				<label class="label">Fin de l'enchère :</label>
				<label>${article.dateFin }</label>
			</div>
			<div class="article-label-value">
				<label class="label">Retrait :</label>
				<label><b>[todo]</b><!--  ajouter retrait --></label>
			</div>
			<div class="article-label-value">
				<label class="label">Vendeur :</label>
				<label>${nomVendeur }</label>
			</div>

		</div>
	</div>

</body>
</html>