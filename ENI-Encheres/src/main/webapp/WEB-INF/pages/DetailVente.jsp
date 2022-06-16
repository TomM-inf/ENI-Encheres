<%@page import="fr.eni.encheres.bll.EnchereManager"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Détail vente</title>
<link rel="stylesheet" href="./css/main.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="./js/functions.js" type="text/javascript"></script>
</head>
<body>

	<input type="hidden" id="infoMsg" value="${infoMsg}">
	<%
	if (request.getSession().getAttribute("infoMsg") != null) {
		request.getSession().removeAttribute("infoMsg");
	}
	%>
	<c:if test="${empty sessionScope.utilisateur}">
		<jsp:include page="./headerDeconnecte.jsp"></jsp:include>
	</c:if>
	<c:if test="${not empty sessionScope.utilisateur}">
		<jsp:include page="./headerConnecte.jsp"></jsp:include>
	</c:if>
	
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

			<form action="${pageContext.request.contextPath }/encherir" method="post">
				<div class="article-label-value">
					<label class="label">Ma proposition :</label>
					<c:choose>
					    <c:when test="${empty enchere.montant}">
					        <input type="hidden" name="bestOffer" value="0">
					    </c:when>
					    <c:otherwise>
					        <input type="hidden" name="bestOffer" value="${enchere.montant }">
					    </c:otherwise>
					</c:choose>
					<input type="hidden" name="noArticle" value="${article.noArticle }">
					<input type="number" name="proposition" id="proposition">
					<input type="submit" value="Enchérir">
				</div>
			</form>

			
		</div>
		
	</div>

</body>
</html>