<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>A web page</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/main.css" />
<script src="${pageContext.request.contextPath}/js/accueil.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor"
	crossorigin="anonymous">
<style type="text/css">
body {
	border: dashed red;
}

h2 {
	text-align: center;
}

#filtre {
	float: left;
}

#rechercher {
	margin: 3em;
	padding-left: 5em;
	padding-right: 5em;
	padding-top: 2em;
	padding-bottom: 2em;
}

table {
	margin-left: auto;
	margin-right: auto;
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="./js/functions.js" type="text/javascript"></script>
</head>
<body>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2"
		crossorigin="anonymous"></script>
	<c:if test="${empty sessionScope.utilisateur}">
		<jsp:include page="./headerDeconnecte.jsp"></jsp:include>
	</c:if>
	<c:if test="${not empty sessionScope.utilisateur}">
		<jsp:include page="./headerConnecte.jsp"></jsp:include>
	</c:if>

	<input type="hidden" id="infoMsg" value="${infoMsg}">
	<%
	if (request.getSession().getAttribute("infoMsg") != null) {
		request.getSession().removeAttribute("infoMsg");
	}
	%>

	<h2>Listes des encheres</h2>
	<form action="" method="post">
		<table>
			<thead>
				<tr>
					<th colspan="3"><b id="filtre">Filtres :</b></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td colspan="2">
						<div class="input-group has-validation">
							<span class="input-group-text" id="inputGroupPrepend">?</span> <input
								type="text" class="form-control" id="validationCustomUsername"
								aria-describedby="inputGroupPrepend"
								placeholder="Le nom de l'article contient"
								name="rechercheArticle">
						</div>
					</td>
					<td rowspan="3"><input type="submit" value="Rechercher"
						id="rechercher" class="btn btn-primary"></td>
				</tr>
				<tr>
					<td><label>Catégorie : </label></td>
					<td><select name="categorie"
						class="form-select form-select-sm"
						aria-label=".form-select-sm example">
							<option value="Toutes" selected>Toutes</option>
							<c:forEach items="${listCategorie}" var="value">
								<option value="${value.libelle}"><c:out
										value="${value.libelle}" /></option>
							</c:forEach>
					</select></td>
				</tr>
				<c:if test="${not empty sessionScope.utilisateur}">
					<tr>
						<td><div class="form-check">
								<input class="form-check-input achats" type="radio"
									name="flexRadioDefault" id="radioAchat" checked> <label
									class="form-check-label" for="flexRadioDefault1">
									Achats</label>
							</div>
							<div class="form-check">
								<input class="form-check-input" type="checkbox" value="enchereOuvertes" name="enchereOuvertes"
									id="flexCheckDefault"> <label class="form-check-label"
									for="flexCheckDefault"> Enchères ouvertes </label>
							</div>
							<div class="form-check">
								<input class="form-check-input" type="checkbox" value="mesEncheres" name="mesEncheres"
									id="flexCheckChecked"> <label
									class="form-check-label" for="flexCheckChecked">
									Mes enchères </label>
							</div>
							<div class="form-check">
								<input class="form-check-input" type="checkbox" value="encheresRemportes" name="encheresRemportes"
									id="flexCheckChecked"> <label
									class="form-check-label" for="flexCheckChecked">
									Mes enchères remportées </label>
							</div></td>
						<td><div class="form-check">
								<input class="form-check-input ventes" type="radio"
									name="flexRadioDefault" id="flexRadioDefault2">
								<label class="form-check-label" for="flexRadioDefault2">
									Mes ventes</label>
							</div>
							<div class="form-check">
								<input class="form-check-input" type="checkbox" value="encheresOuvertes" name="encheresOuvertes"
									id="flexCheckDefault"> <label class="form-check-label"
									for="flexCheckDefault"> Enchères ouvertes </label>
							</div>
							<div class="form-check">
								<input class="form-check-input" type="checkbox" value="mesEncheresVentes" name="mesEncheresVentes"
									id="flexCheckChecked"> <label
									class="form-check-label" for="flexCheckChecked">
									Mes enchères </label>
							</div>
							<div class="form-check">
								<input class="form-check-input" type="checkbox" value="venteTerminees" name="venteTerminees"
									id="flexCheckChecked"> <label
									class="form-check-label" for="flexCheckChecked">
									Ventes terminées </label>
							</div></td>
					</tr>
				</c:if>
			</tbody>
		</table>
	</form>

	<c:if test="${empty sessionScope.utilisateur}">
		<jsp:include page="./listeEncheresDeconnecte.jsp">
			<jsp:param name="listArticles" value="${listArticles}" />
			<jsp:param name="listPseudo" value="${listPseudo}" />
		</jsp:include>
	</c:if>
	<c:if test="${not empty sessionScope.utilisateur}">
		<jsp:include page="./listeEncheresConnecte.jsp">
			<jsp:param name="listArticles" value="${listArticles}" />
			<jsp:param name="listPseudo" value="${listPseudo}" />
		</jsp:include>
	</c:if>

</body>
</html>