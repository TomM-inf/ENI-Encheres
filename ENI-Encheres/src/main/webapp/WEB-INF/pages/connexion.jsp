<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>titre</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/main.css" />
</head>
<body>
	<c:if test="${not empty erreur }">
		<h3 style="color: red;">${erreur}</h3>
	</c:if>
	<form action="${pageContext.request.contextPath}/connexion" method="post">
		Identifiant : <input type="text" name="identifiant" value="${not empty identifiant ? identifiant : " " }" /><br /> 
		Mot de passe : <input type="password" name="motDePasse" id="" /><br />
		<button>Se connecter</button>
		<input type="checkbox" name="chkBox"> <label>Se souvenir de moi</label><br />
		<!-- url mot de passe oublié -->
		<a href="${url}">Mot de passe oublié</a>
	</form>
	<br />
	<input type="button" name="btnCreerCompte" value="Créer un compte">
</body>
</html>