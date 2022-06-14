// JQUERY
$('document').ready(function() {
	
	// remove old session attributes
	sessionStorage.removeItem('pwError');
	

	if($('#infoMsg').val() != null && $('#infoMsg').val() != '') {
		alert($('#infoMsg').val())
		//console.log($('#infoMsg').val());
		sessionStorage.removeItem('infoMsg');
	}
		
	$('#js-register').on('click', function(event) {
		// check if password is strong enough
		if($('.register-input-pw') != null) {
			
			// length	
			var pw = $('.register-input-pw').val();
			if(pw.length < 12) {
				sessionStorage.setItem('pwError', 'too short..');
			}
			
			// uppercase, lowercase, digit, special Char
			var i=0;
			var character='';
			var uppercase='';
			var lowercase='';
			var digit='';
			var specialChar='';
	
			while (i <= pw.length) {
			    character = pw.charAt(i);
				if (character == character.toUpperCase()) {
				    uppercase = 'true';
				}
				if (character == character.toLowerCase()){
				    lowercase = 'true';
				}
				if($.isNumeric(character)) {
					digit = 'true';
				}
				if(containsSpecialChars(character)) {
					specialChar = 'true';
				}
				
				if(uppercase == 'true'
					&& lowercase == 'true'
					&& digit == 'true'
					&& specialChar == 'true') {
						break;
				}
				
			    i++;
			}
			
			if(uppercase != 'true'
				|| lowercase != 'true'
				|| digit != 'true'
				|| specialChar != 'true') {
					sessionStorage.setItem('pwError', 'not strong enough');
			}
		}
	});
	
	
	function containsSpecialChars(str) {
	  const specialChars = /[`!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?~]/;
	  return specialChars.test(str);
	}
});