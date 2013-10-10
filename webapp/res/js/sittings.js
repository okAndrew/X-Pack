function validatePasswords() {
			var p1 = $
			{
				sessionScope.user.password
			}
			;
			var p2 = document.forms["form-change-password"]["old_pass"].value;
			var p3 = document.forms["form-change-password"]["new_pass"].value;
			var p4 = document.forms["form-change-password"]["new_pass_r"].value;
			var errorinfo = document.getElementById("errorinfo");

			if (p1 != p2) {
				setMessage("Old password incorrect", errorinfo);
				return false;
			}

			if (p1 == "" || p2 == "" || p3 == "" || p3 == "") {
				setMessage("Fields cannot be empty", errorinfo);
				return false;
			}

			if (p3 != p4) {
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
		var newEmailAddress = document.getElementById("newEmailAddress");
		var secretCodeInput = document.getElementById("secretCodeInput");

		$('#submitEmailForm').click(function() {
			$.ajax({
				type : "POST",
				data : {
					email : newEmailAddress,
					code : secretCodeInput
				},
				url : "http://localhost:8080/dreamhost/EditEmailServlet"
			});
		});
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