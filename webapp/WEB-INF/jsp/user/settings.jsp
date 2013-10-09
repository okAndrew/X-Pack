<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DreamHost | Settings</title>
<link href="res/css/bootstrap.css" rel="stylesheet" />
<link href="res/css/style.css" rel="stylesheet" />
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="res/js/bootstrap.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/menu.jsp"></jsp:include>
	<div class="container">
		<div class="panel panel-default">
			<div class="panel-body">
				<div class="row">
					<div class="col-md-3">
						<ul class="nav nav-pills nav-stacked">
							<li class="active"><a href="#tabs1-pane1" data-toggle="tab">Personal
									informations</a></li>
							<li><a href="#tabs1-pane2" data-toggle="tab">Service</a></li>
							<li><a href="#tabs1-pane3" data-toggle="tab">...</a></li>
						</ul>
					</div>
					<div class="col-md-6">
						<div class="tab-content">
							<div class="tab-pane active" id="tabs1-pane1">
								<form role="form">
									<div class="form-group">
										<label for="exampleInputEmail1">Email address</label> <input
											type="email" class="form-control" id="exampleInputEmail1"
											placeholder="Enter email">
									</div>
									<div class="form-group">
										<label for="exampleInputPassword1">Password</label> <input
											type="password" class="form-control"
											id="exampleInputPassword1" placeholder="Password">
									</div>
									<button type="submit" class="btn btn-primary">Save</button>
								</form>
							</div>
							<div class="tab-pane" id="tabs1-pane2">
								<p>Tariffs</p>
							</div>
							<div class="tab-pane" id="tabs1-pane3">
								<p>Other</p>
							</div>
						</div>
					</div>
					<div class="col-md-3"></div>
				</div>
			</div>
			<div class="panel-footer"></div>
		</div>
	</div>
</body>
</html>