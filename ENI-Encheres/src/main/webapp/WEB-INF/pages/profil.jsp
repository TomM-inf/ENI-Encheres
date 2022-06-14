<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="./css/main.css">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="./js/functions.js" type="text/javascript"></script>
</head>
<body>
<jsp:include page="./headerConnecte.jsp"></jsp:include>
<!-- pseudo ville cp -->
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
<input type="submit" value="Modifier">
</form>
</div>
</body>
</html>
