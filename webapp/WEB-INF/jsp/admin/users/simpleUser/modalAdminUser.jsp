<script type="text/javascript">
	function check(value, id) {
		xmlHttp = GetXmlHttpObject();
		var url = "WEB-INF/jsp/admin/users/simpleUser/checkajax.jsp";
		url = url + "?userEmail=" + value + "&id=" + id;
		xmlHttp.onreadystatechange = stateChange;
		xmlHttp.open("GET", url, true);

		xmlHttp.send(null);
	}
	var stateChange = function stateChanged() {
		if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete") {
			var showdata = xmlHttp.responseText;
			document.getElementById("mydiv").innerHTML = showdata;
		}
	}
	function GetXmlHttpObject() {
		var xmlHttp = null;
		try {
			xmlHttp = new XMLHttpRequest();
		} catch (e) {
			try {
				xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
		}
		return xmlHttp;
	}
	if (true) {
		findElementById(id).style.setBorderColor = green;
	} else {
		findElementById(id).style.setBorderColor = red;
	}
</script>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title">Update user</h4>
			</div>

			<div class="modal-body">
				<div class="container">
					<!-- Static navbar -->
					<div class="navbar navbar-default">
						<div class="navbar-collapse collapse">
							<form action="updateUser" method="post">
								<table class="table">
									<tbody>
										<tr>
											<td>User id</td>
											<td><input type="text" name="userIdHolder"
												class="form-control first" value="${user.id}"
												autofocus="autofocus" readonly></td>
										</tr>
										<tr>
											<td>Login</td>
											<td><input type="text" name="userLogin"
												class="form-control first" value="${user.login}"
												autofocus="autofocus"></td>
										</tr>
										<tr>
											<td>Email</td>
											<td><input type="text" name="userEmail"
												class="form-control first" value="${user.email}"
												autofocus="autofocus"
												onkeyup="check(this.value, '${user.id}');">
												<div id="mydiv"></div></td>
										</tr>
										<tr>
											<td>Tariff</td>
											<td><input type="text" name="userIdTariff"
												class="form-control first" value="${user.idTariff}"
												autofocus="autofocus" readonly></td>
										</tr>
										<tr>
											<td>Capacity</td>
											<td><input type="text" class="form-control first"
												value="${user.capacity}" autofocus="autofocus"
												disabled></td>
										</tr>
										
										<tr>
											<td>Activation</td>
											<td><input type="text" name="userActivation"
												class="form-control last" value="${user.isActivated}"
												autofocus="autofocus"></td>
										</tr>
										<tr>
											<td>Role</td>
											<td><input type="text" name="userRole"
												class="form-control last" value="${user.role}"
												autofocus="autofocus"></td>
										</tr>
										
									</tbody>
								</table>

								<button type="submit" class="btn btn-primary">Save
									changes</button>
							</form>
						</div>
						<!--/.nav-collapse -->
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>

			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->

