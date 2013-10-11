<div class="modal fade" id="addUserModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title">Add new user</h4>
			</div>
			<div class="modal-body">
				<div class="container">
					<!-- Static navbar -->
					<div class="navbar navbar-default">
						<div class="navbar-collapse collapse">
							<form action="addUser" method="post">
								<input type="text" name="loginUser" class="form-control first"
									placeholder="Login" autofocus="autofocus"> 
								
								<input type="text" name="email" class="form-control first"
									placeholder="Email" autofocus="autofocus">
								
								<input type="text" name="password" class="form-control first"
									placeholder="Password" autofocus="autofocus">	
								<button type="submit" class="btn btn-primary">Add new
									user</button>
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