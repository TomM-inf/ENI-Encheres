<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>A web page</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/main.css" />
</head>
<body>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2"
		crossorigin="anonymous"></script>
	<jsp:include page="./headerConnecte.jsp"></jsp:include>
	<h2>Nouvelle vente</h2>

	<form class="row g-3 needs-validation" method="post">
		<div class="formNouvelArticle">
			<div class="col-md-4">
				<label for="validationCustom01" class="form-label">Article :</label>
				<input type="text" class="form-control" id="validationCustom01"
					name="nomArticle" required>
			</div>
			<div class="col-md-4">
				<label for="validationCustom02" class="form-label">Description
					:</label>
				<textarea class="form-control" id="validationCustom02" name="description" required></textarea>
			</div>
			<div class="col-md-3">
				<label for="validationCustom04" class="form-label">Categorie
					:</label> <select class="form-select" id="validationCustom04" name="categorie" required>
					<option value="Toutes" selected>Toutes</option>
					<c:forEach items="${listCategorie}" var="value">
						<option value="${value.libelle}"><c:out
								value="${value.libelle}" /></option>
					</c:forEach>
				</select>
			</div>
			<div class="col-md-3">
				<label for="formFile" class="form-label">Photo de l'article:
				</label> <input class="form-control" type="file" id="formFile">
			</div>
			<div class="col-md-4">
				<label for="validationCustom01" class="form-label">Mise à
					prix :</label> <input type="number" class="form-control"
					id="validationCustom01" name="prixInitial" required>
			</div>
			<div class="col-md-4">
				<label for="startDate">Début de l'enchère : </label> <input
					id="startDate" class="form-control" type="date" name="dateDebut" />
			</div>
			<div class="col-md-4">
				<label for="endDate">Fin de l'enchère : </label> <input id="endDate"
					class="form-control" type="date" name="dateFin"/>
			</div>
			<div class="venteArticleRetrait">
				Retrait
				<div class="col-md-4">
					<label for="validationCustom01" class="form-label">Rue :</label> <input
						type="text" class="form-control" id="validationCustom01" value="${sessionScope.utilisateur.rue}" required>
				</div>
				<div class="col-md-4">
					<label for="validationCustom01" class="form-label">Code
						postal :</label> <input type="text" class="form-control"
						id="validationCustom01" value="${sessionScope.utilisateur.codePostal}" required>
				</div>
				<div class="col-md-4">
					<label for="validationCustom01" class="form-label">Ville :</label>
					<input type="text" class="form-control" id="validationCustom01"
						value="${sessionScope.utilisateur.ville}" required>
				</div>
				<div class="col-12">
					<button class="btn btn-primary" type="submit">Enregistrer</button>
				</div>
			</div>
		</div>
		<input type="hidden" name="utilisateur" value="${sessionScope.utilisateur.noUtilisateur}"/>
	</form>
</body>
</html>