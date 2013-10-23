/*
 * 
 */
function checkChangeLoginForm() {
	var login = document.forms["editLogin"]["inputLogin"].value;
	var errorinfo = document.getElementById("settingsErrorDiv");

	if (login == "") {
		setMessage("Field cannot be empty", errorinfo);
		return false;
	}

	return true;
}

/*
 * 
 */
function validatePasswords() {
	var p1 = document.forms["form-change-password"]["old_pass"].value;
	var p2 = document.forms["form-change-password"]["password"].value;
	var p3 = document.forms["form-change-password"]["password_retype"].value;
	var errorinfo = document.getElementById("errorinfo");

	if (p1 == "" || p2 == "" || p3 == "") {
		setMessage("Fields cannot be empty", errorinfo);
		return false;
	}

	if (p2 != p3) {
		setMessage("New passwords are different", errorinfo);
		return false;
	}

	return true;
}

/*
 * 
 */
function setMessage(message, block) {
	block.style.display = "block";
	block.innerHTML = message;
}

/*
 * 
 */
function validatePasswords() {
	var p1 = document.forms["form-change-password"]["old_pass"].value;
	var errorinfo = document.getElementById("errorinfo");

	if (p1 == "" || p2 == "" || p3 == "") {
		setMessage("Fields cannot be empty", errorinfo);
		return false;
	}

	if (p2 != p3) {
		setMessage("New passwords are different", errorinfo);
		return false;
	}

	return true;
}