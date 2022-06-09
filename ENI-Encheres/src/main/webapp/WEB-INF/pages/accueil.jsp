<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>A web page</title>
<style type="text/css">
body{
	border: dashed red;
}
h2{
	text-align: center;
}
#filtre{
	float: left;
}
#rechercheArticle{
	text-align: center;
}
#rechercher{
	margin: 3em;
	padding-left: 5em;
	padding-right: 5em;
	padding-top: 2em;
	padding-bottom: 2em;
}
table {
    margin-left:auto; 
    margin-right:auto;
  }
</style>
</head>
<body>
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
            <td colspan="2"><input type="text" id="rechercheArticle" name="rechercheArticle" placeholder="Le nom de l'article contient"/></td>
            <td rowspan="2"><input type="submit" value="Rechercher" id="rechercher"></td>
        </tr>
        <tr>
        	<td><label>Catégorie :	</label></td>
        	<td><input type="text" list="listCate" name="categorie" placeholder="Toutes"/>
					<datalist id="listCate">
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
</body>
</html>