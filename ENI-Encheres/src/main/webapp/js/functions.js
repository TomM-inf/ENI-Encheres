// JQUERY
$('document').ready(function() {
	if($('#infoMsg').val() != null && $('#infoMsg').val() != '') {
		alert($('#infoMsg').val())
		console.log($('#infoMsg').val());
		sessionStorage.removeItem('infoMsg');
	}
});