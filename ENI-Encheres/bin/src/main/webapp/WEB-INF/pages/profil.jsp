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
<<<<<<< HEAD
<div class="${simple ? 'afficher-profil' : 'cacher-profil'}">
<div class="lignes-profil"><label>Pseudo :</label><input type="text" value="${utilisateur.pseudo}" disabled="disabled"></div>
<div class="lignes-profil"><label>Ville :</label><input type="text" value="${utilisateur.ville} "disabled="disabled"></div>
<div class="lignes-profil"><label>Code Postal :</label><input type="text" value="${utilisateur.codePostal}" disabled="disabled"></div>
</div>
<!-- tout -->
<div class="${enchereRemporte ? 'afficher-profil' : 'cacher-profil'}">
<div class="lignes-profil"><label>Pseudo :</label><input type="text" value="${utilisateur.pseudo}" disabled="disabled"></div>
<div class="lignes-profil"><label>Nom :</label><input type="text" value="${utilisateur.nom}" disabled="disabled"></div>
<div class="lignes-profil"><label>Prénom :</label><input type="text" value="${utilisateur.prenom}" disabled="disabled"></div>
<div class="lignes-profil"><label>Email :</label><input type="text" value="${utilisateur.email}" disabled="disabled"></div>
<div class="lignes-profil"><label>Téléphone :</label><input type="text" value="${utilisateur.telephone}" disabled="disabled"></div>
<div class="lignes-profil"><label>Rue :</label><input type="text" value="${utilisateur.rue}" disabled="disabled"></div>
<div class="lignes-profil"><label>Ville :</label><input type="text" value="${utilisateur.ville}" disabled="disabled"></div>
<div class="lignes-profil"><label>Code Postal :</label><input type="text" value="${utilisateur.codePostal}" disabled="disabled"></div>
</div>
<!-- tout avec bouton modifier -->
<div class="${monProfil ? 'afficher-profil' : 'cacher-profil'}">
<form action="${pageContext.request.contextPath}/afficherProfil" method="post" class="form-example">
<div class="lignes-profil"><label>Pseudo :</label><input type="text" value="${utilisateur.pseudo}" disabled="disabled"></div>
<div class="lignes-profil"><label>Nom :</label><input type="text" value="${utilisateur.nom}" disabled="disabled"></div>
<div class="lignes-profil"><label>Prénom :</label><input type="text" value="${utilisateur.prenom}" disabled="disabled"></div>
<div class="lignes-profil"><label>Email :</label><input type="text" value="${utilisateur.email}" disabled="disabled"></div>
<div class="lignes-profil"><label>Téléphone :</label><input type="text" value="${utilisateur.telephone}" disabled="disabled"></div>
<div class="lignes-profil"><label>Rue :</label><input type="text" value="${utilisateur.rue}" disabled="disabled"></div>
<div class="lignes-profil"><label>Ville :</label><input type="text" value="${utilisateur.ville}" disabled="disabled"></div>
<div class="lignes-profil"><label>Code Postal :</label><input type="text" value="${utilisateur.codePostal}" disabled="disabled"></div>
=======
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
>>>>>>> master
<input type="submit" value="Modifier">
</form>
</div>
</body>
</html>