function mainFunc() {
		alert("on load();");
	}
	function validateForm() {
		var p1 = document.forms["form-signin"]["password"].value;
		var p2 = document.forms["form-signin"]["passwordVerify"].value;
		var login = document.forms["form-signin"]["login"].value;
		var email = document.forms["form-signin"]["email"].value;
		var errorinfo = document.getElementById("errorinfo");
		var atpos = email.indexOf("@");
		var dotpos = email.lastIndexOf(".");
		
		errorinfo.style.display = "none";
		
		if (atpos < 1 || dotpos < atpos+2 || dotpos + 2 > email.length) {
			setMessage(strings_loc['Not_a_valid_e-mail_address'], errorinfo);
			return false;
		}
		
		if (p1 == "" || p2 == "" || login == "") {
			setMessage(strings_loc['Fields_cannot_be_empty'], errorinfo);
	  		return false;
		}
		
		if(p1 != p2) {
			setMessage(strings_loc['Passwords_are_different'], errorinfo);
			return false;
		}
		
		return true;
	}
	
	function setMessage(message, block) {
		block.style.display = "block";
		block.innerHTML = message;
	}