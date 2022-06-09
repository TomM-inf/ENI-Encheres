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

#rechercheArticle {
	text-align: center;
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
</head>
<body>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2"
		crossorigin="anonymous"></script>
	<jsp:include page="./headerDeconnecte.jsp"></jsp:include>
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
					<td colspan="2"><input type="text" id="rechercheArticle"
						name="rechercheArticle" placeholder="Le nom de l'article contient" /></td>
					<td rowspan="2"><input type="submit" value="Rechercher"
						id="rechercher" class="btn btn-primary"></td>
				</tr>
				<tr>
					<td><label>Catégorie : </label></td>
					<td><input type="text" list="listCate" name="categorie"
						placeholder="Toutes" /> <datalist id="listCate">
							<option value="Toutes">Toutes</option>
							<option value="Informatique">Informatique</option>
							<option value="Ameublement">Ameublement</option>
							<option value="Vetement">Vetement</option>
							<option value="Sport&Loisirs">Sport&Loisirs</option>
						</datalist></td>
				</tr>
			</tbody>
		</table>
	</form>

	<!-- Insérer ici 2004 - liste des encheres en mode déconnecté -->
	<jsp:include page="./listeEncheresDeconnecte.jsp">
		<jsp:param name="listArticles" value="${listArticles}" />
		<jsp:param name="listPseudo" value="${listPseudo}" />
	</jsp:include>

</body>
</html>