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
<!-- pseudo ville cp -->
<div class="${simple ? 'cacher-profil' : 'afficher-profile'}">
<label>Pseudo :</label><input type="text" value={$utilisateur.pseudo}>
<label>Ville :</label><input type="text" value={$utilisateur.ville}>
<label>Code Postal :</label><input type="text" value={$utilisateur.cp}>
</div>
<!-- tout -->
<div class="${enchereRemporte ? 'cacher-profil' : 'afficher-profile'}">
<label>Pseudo :</label><input type="text" value={$utilisateur.pseudo}>
<label>Nom :</label><input type="text" value={$utilisateur.nom}>
<label>Prénom :</label><input type="text" value={$utilisateur.prenom}>
<label>Email :</label><input type="text" value={$utilisateur.email}>
<label>Téléphone :</label><input type="text" value={$utilisateur.telephone}>
<label>Rue :</label><input type="text" value={$utilisateur.rue}>
<label>Ville :</label><input type="text" value={$utilisateur.ville}>
<label>Code Postal :</label><input type="text" value={$utilisateur.cp}>
</div>
<!-- tout avec bouton modifier -->
<div class="${monProfil ? 'cacher-profil' : 'afficher-profile'}">
<form action="${pageContext.request.contextPath}/afficherProfil" method="post" class="form-example">
<label>Pseudo :</label><input type="text" value={$utilisateur.pseudo}>
<label>Nom :</label><input type="text" value={$utilisateur.nom}>
<label>Prénom :</label><input type="text" value={$utilisateur.prenom}>
<label>Email :</label><input type="text" value={$utilisateur.email}>
<label>Téléphone :</label><input type="text" value={$utilisateur.telephone}>
<label>Rue :</label><input type="text" value={$utilisateur.rue}>
<label>Ville :</label><input type="text" value={$utilisateur.ville}>
<label>Code Postal :</label><input type="text" value={$utilisateur.cp}>
<input type="submit" value="Modifier">
</form>
</div>
</body>
</html>