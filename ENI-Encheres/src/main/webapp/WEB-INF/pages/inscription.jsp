<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Inscription</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="./js/functions.js" type="text/javascript"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/FileSaver.js/1.3.8/FileSaver.js"></script>
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

	<form id="register" action="${pageContext.request.contextPath }/inscription"
		method="post">
		<div class="left-and-right">

			<div class="left all-groups">
				<div class="label-input-group">
					<!--  Pseudo -->
					<label for="pseudo">Pseudo :</label> 
					<input type="text" name="pseudo" value="${pseudo}" pattern="^[a-zA-Z0-9]*$" maxlength="30" required>
				</div>

				<div class="label-input-group">
					<!--  Prenom -->
					<label for="prenom">Prenom :</label> <input type="text"
						name="prenom" value="${prenom}" maxlength="30" required>
				</div>

				<div class="label-input-group">
					<!--  Tel -->
					<label for="tel">Telephone :</label> <input type="text" name="tel" value="${tel}" maxlength="15">
				</div>

				<div class="label-input-group">
					<!--  CP -->
					<label for="cp">Code postal :</label> <input type="text" name="cp" value="${cp}" maxlength="10" required>
				</div>

				<div class="label-input-group">
					<!--  PW -->
					<label for="pw">Mot de passe :</label> <input class="register-input-pw" type="password" name="pw" value="Te1*stpassword" maxlength="256" required>
				</div>
			</div>

			<div class="left all-groups">
				<div class="label-input-group">
					<!--  Nom -->
					<label for="nom">Nom :</label> <input type="text" name="nom" value="${nom}" maxlength="30" required>
				</div>

				<div class="label-input-group">
					<!--  Email -->
					<label for="email">Email :</label> <input type="email" name="email" value="${email}" maxlength="50" required>
				</div>

				<div class="label-input-group">
					<!--  Rue -->
					<label for="rue">Rue :</label> <input type="text" name="rue" value="${rue}" maxlength="30" required>
				</div>

				<div class="label-input-group">
					<!--  Ville -->
					<label for="ville">Ville :</label> <input type="text" name="ville" value="${ville}" maxlength="50" required>
				</div>

				<div class="label-input-group">
					<!--  PW Confirmation -->
					<label for="confirm-pw">Confirmation Mot de passe :</label> <input
						type="password" name="confirm-pw" value="Te1*stpassword" maxlength="256" required>
				</div>
			</div>
		</div>
		<input type="submit" value="Créer">
		
		<input id="js-register" type="submit" value="Créer">
	</form>

	<a href="${pageContext.request.contextPath }/">
		<button>Annuler</button>
	</a>


</body>
</html>