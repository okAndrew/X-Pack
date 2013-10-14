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
			setMessage("Not a valid e-mail address", errorinfo);
			return false;
		}
		
		if (p1 == "" || p2 == "" || login == "") {
			setMessage("Fields cannot be empty", errorinfo);
	  		return false;
		}
		
		if(p1 != p2) {
			setMessage("Passwords are different", errorinfo);
			return false;
		}
		
		return true;
	}
	
	function setMessage(message, block) {
		block.style.display = "block";
		block.innerHTML = message;
	}