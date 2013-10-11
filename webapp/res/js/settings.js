function checkChangeLoginForm() {
	alert("1");
	var login = document.forms["editLogin"]["inputLogin"].value;
	alert("2");
	var errorinfo = document.getElementById("settingsErrorDiv");
	alert("3");
	if (login == "") {
		setMessage("Field cannot be empty", errorinfo);
		return false;
	}
	alert("4");
	return true;
} 
function validatePasswords() {
	var p1 = document.forms["form-change-password"]["old_pass"].value;
	var p2 = document.forms["form-change-password"]["new_pass"].value;
	var p3 = document.forms["form-change-password"]["new_pass_r"].value;
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

function setMessage(message, block) {
	block.style.display = "block";
	block.innerHTML = message;
}

function formPassReset() {
	document.getElementById("errorinfo").innerHTML = "";
	document.getElementById("errorinfo").style.display = "none";
	document.getElementById("form-change-password").reset();
}

var $email = "${sessionScope.user.email}";
$("#sendCode").click(function() {
	$.ajax({
		type : "POST",
		data : {
			email : "savruksergiy@gmail.com"
		},
		url : "http://localhost:8080/dreamhost/editEmailForm"
	});
});