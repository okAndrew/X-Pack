<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>

<script
	src="//ajax.googleapis.com/ajax/libs/jqueryui/1.8.9/jquery-ui.min.js"
	type="text/javascript"></script>

<script type="text/javascript" src="res/js/modal.js"></script>

<script src="res/js/bootstrap.js"></script>

<script>
	function toggle(source) {
		checkboxes = document.getElementsByName('checkNoDeletedUser');
		for ( var i = 0, n = checkboxes.length; i < n; i++) {
			checkboxes[i].checked = source.checked;
		}
	}
</script>

<link href="res/css/style.css" rel="stylesheet" />

<div class="modal fade" id="noDeleteUsersModal" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h3>Warning!!!</h3>
				<p>These users you want to remove have some files. Are you sure you
					want to delete?</p>
			</div>
			<div class="modal-body">
				<!-- table -->
				<table class="table table-striped">
					<thead>
						<tr>
							<th><input type="checkbox" onclick="toggle(this)" />All</th>
							<th>id</th>
							<th>login</th>
							<th>email</th>
							<th>type</th>
							<th>tariff</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="user" items="${noDeletedUsers}">
							<tr>
								<td><input type="checkbox" name="checkNoDeletedUser"
									value="${user.id}"></td>
								<td>${user.id}</td>
								<td>${user.login}</td>
								<td>${user.email}</td>
								<td>${user.role}</td>
								<td>${user.idTariff}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<!-- /table -->
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">
					<fmt:message key="Close" bundle="${lang}" />
				</button>
				<a href="#" type="button" class="btn btn-primary">Delete</a>
			</div>
		</div>
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->