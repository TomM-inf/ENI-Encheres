<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Inscription</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="./js/functions.js" type="text/javascript"></script>
</head>
<body>

	<style>
.all-groups {
	display: block;
}

.label-input-group {
	display: flex;
	flex-direction: row;
}

.left-and-right {
	display: flex;
	flex-direction: row;
}
</style>

	<input type="hidden" id="infoMsg" value="${infoMsg}">
	<%
	if (request.getSession().getAttribute("infoMsg") != null) {
		request.getSession().removeAttribute("infoMsg");
	}
	%>

	<h1>Mon profil</h1>

	<form action="${pageContext.request.contextPath }/inscription"
		method="post">
		<div class="left-and-right">

			<div class="left all-groups">
				<div class="label-input-group">
					<!--  Pseudo -->
					<label for="pseudo">Pseudo :</label> 
					<input type="text" name="pseudo" value="${pseudo}">
				</div>

				<div class="label-input-group">
					<!--  Prenom -->
					<label for="prenom">Prenom :</label> <input type="text"
						name="prenom" value="${prenom}">
				</div>

				<div class="label-input-group">
					<!--  Tel -->
					<label for="tel">Telephone :</label> <input type="text" name="tel" value="${tel}">
				</div>

				<div class="label-input-group">
					<!--  CP -->
					<label for="cp">Code postal :</label> <input type="text" name="cp" value="${cp}">
				</div>

				<div class="label-input-group">
					<!--  PW -->
					<label for="pw">Mot de passe :</label> <input type="text" name="pw" value="${pw}">
				</div>
			</div>

			<div class="left all-groups">
				<div class="label-input-group">
					<!--  Nom -->
					<label for="nom">Nom :</label> <input type="text" name="nom" value="${nom}">
				</div>

				<div class="label-input-group">
					<!--  Email -->
					<label for="email">Email :</label> <input type="email" name="email" value="${email}">
				</div>

				<div class="label-input-group">
					<!--  Rue -->
					<label for="rue">Rue :</label> <input type="text" name="rue" value="${rue}">
				</div>

				<div class="label-input-group">
					<!--  Ville -->
					<label for="ville">Ville :</label> <input type="text" name="ville" value="${ville}">
				</div>

				<div class="label-input-group">
					<!--  PW Confirmation -->
					<label for="confirm-pw">Confirmation Mot de passe :</label> <input
						type="text" name="confirm-pw" value="${confirmPW}">
				</div>
			</div>
		</div>

		<input type="submit" value="Créer">
	</form>

	<a href="${pageContext.request.contextPath }/">
		<button>Annuler</button>
	</a>


</body>
</html>