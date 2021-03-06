$(document).ready(function() {
	$('#emailloginchk').hide();
	$('#passwordloginchk').hide();
	
	var email_err = true;
	var pass_err = true;
	
	$('#emaillog').keyup(function() {
		email_chk();
		userStatus_chk();
	});
	
	function email_chk() {
		var email_val = $('#emaillog').val();
		var pattern = /^\b[A-Z0-9._%-]+@[A-Z0-9.-]+\.[A-Z]{2,4}\b$/i;
		if((email_val.length == '') || (email_val.length == null)) {
			$('#emailloginchk').show();
			$('#emailloginchk').html("Please Fill the Email");
			$('#emailloginchk').focus();
			$('#emailloginchk').css("color","red");
			email_err = false;
			return false;
		} else {
			$('#emailloginchk').hide();
		}
		
		if(!pattern.test(email_val)) {
			$('#emailloginchk').show();
			$('#emailloginchk').html("Email is not Valid");
			$('#emailloginchk').focus();
			$('#emailloginchk').css("color","red");
			email_err = false;
			return false;
		} else {
			$('#emailloginchk').hide();
		}
	}
	
	$('#passlog').keyup(function() {
	  	password_chk();
	});
	
	function password_chk() {
		var password_val = $('#passlog').val();
		if((password_val.length == '') || (password_val.length == null)) {
			$('#passwordloginchk').show();
			$('#passwordloginchk').html("Please fill the Password");
			$('#passwordloginchk').focus();
			$('#passwordloginchk').css("color","red");
			pass_err = false;
			return false;
		} else {
			$('#passwordloginchk').hide();
		}
	}
	
	$('#btnLogin').click(function() {
		 email_err = true;
		 pass_err = true;
		 
		 email_chk();
		 password_chk();
		 
		 if((email_err == true) && (pass_err == true)) {
			 return true;
		 } else {
			 return false;
		 }
	});
});

function userStatus_chk() {
	var email = $('#emaillog').val();
	if((email.length == '') || (email.length == null)) {
		email_chk();
	}else {
		/*$.ajax({
			type : "POST",
			url : "/user/checkstatus",
			dataType: "json",
			data: {email: email},
			success : function(data) {
					console.log(data);
					if(data == 1){
						console.log("your account is actived")
					} else {
						console.log("your account is not actived");
					}
					    					
				},
				error : function(xmlHttpRequest, textStatus, errorThrown) {
					
				} 
	    }); */
	}
}

function email_chk() {
	var email_val = $('#emaillog').val();
	var pattern = /^\b[A-Z0-9._%-]+@[A-Z0-9.-]+\.[A-Z]{2,4}\b$/i;
	if((email_val.length == '') || (email_val.length == null)) {
		$('#emailloginchk').show();
		$('#emailloginchk').html("Please Fill the Email");
		$('#emailloginchk').focus();
		$('#emailloginchk').css("color","red");
		email_err = false;
		return false;
	} else {
		$('#emailloginchk').hide();
	}
}	