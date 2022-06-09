<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Inscription</title>
</head>
<body>

<style>
	.all-groups {
		display:block;
	}
	.label-input-group {
		display:flex;
		flex-direction:row;
	}
	.left-and-right {
		display:flex;
		flex-direction:row;
	}
</style>

<h1>Mon profil</h1>

<form action="${pageContext.request.contextPath }/inscription" method="post">
	<div class="left-and-right">
	
		<div class="left all-groups">	
			<div class="label-input-group">
				<!--  Pseudo -->
				<label for="pseudo">Pseudo :</label>
				<input type="text" name="pseudo">
			</div>
			
			<div class="label-input-group">
				<!--  Prenom -->
				<label for="prenom">Prenom :</label>
				<input type="text" name="prenom">
			</div>
			
			<div class="label-input-group">
				<!--  Tel -->
				<label for="tel">Telephone :</label>
				<input type="text" name="tel">
			</div>
			
			<div class="label-input-group">
				<!--  CP -->
				<label for="cp">Code postal :</label>
				<input type="text" name="cp">
			</div>
			
			<div class="label-input-group">
				<!--  PW -->
				<label for="pw">Mot de passe :</label>
				<input type="text" name="pw">
			</div>		
		</div>
		
		<div class="left all-groups">	
			<div class="label-input-group">
				<!--  Nom -->
				<label for="nom">Nom :</label>
				<input type="text" name="nom">
			</div>
			
			<div class="label-input-group">
				<!--  Email -->
				<label for="email">Email :</label>
				<input type="text" name="email">
			</div>
			
			<div class="label-input-group">
				<!--  Rue -->
				<label for="rue">Rue :</label>
				<input type="text" name="rue">
			</div>
			
			<div class="label-input-group">
				<!--  Ville -->
				<label for="ville">Ville :</label>
				<input type="text" name="ville">
			</div>
			
			<div class="label-input-group">
				<!--  PW Confirmation -->
				<label for="confirm-pw">Confirmation Mot de passe :</label>
				<input type="text" name="confirm-pw">
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