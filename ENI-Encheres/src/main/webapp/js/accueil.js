window.onload = function() {
	var radioAchat = document.getElementsByClassName("form-check-input achats");
	var radioVente = document.getElementsByClassName("form-check-input ventes");

	if (radioAchat) {
		radioAchat[0].addEventListener('click', function onClick() {
			alert("Evènement de click détecté");
		});
	}
	
	if (radioVente) {
		radioVente[0].addEventListener('click', function onClick() {
			alert("Evènement de click détecté");
		});
	}
}

function showAlert() {
	alert("Evènement de click détecté");
}