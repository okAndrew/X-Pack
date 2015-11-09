<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="modal fade" id="editTariffModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
	<form action="employeeControllerTariffs" method="post">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title">
					<fmt:message key="Edit_tariff" bundle="${lang}" />
				</h4>
			</div>
			<div class="modal-body">
				<div class="container">
					<!-- Static navbar -->
					<div class="navbar navbar-default">
						<div class="navbar-collapse collapse">
							<c:if test="${editTarMessage != null}">
								<div id="errorinfo" class="alert alert-danger"
									style="display: none;">
									<fmt:message key="${editTarMessage}" bundle="${lang}" />
								</div>
							</c:if>

							<table class="table">
								<tbody>
									<tr>
										<td><fmt:message key="Id" bundle="${lang}" /></td>
										<td><input type="text" name="id"
											class="form-control first" value="${tariff.id}"
											autofocus="autofocus" /></td>
									</tr>

									<tr>
										<td><fmt:message key="Name" bundle="${lang}" /></td>
										<td><input type="text" name="name"
											class="form-control midle" value="${tariff.name}"
											autofocus="autofocus" /></td>
									</tr>

									<tr>
										<td><fmt:message key="Max_Capacity" bundle="${lang}" /></td>
										<td><input type="text" name="maxCapacity"
											class="form-control midle" /></td>
									</tr>

									<tr>
										<td><fmt:message key="Price" bundle="${lang}" /></td>
										<td><input type="text" name="price"
											class="form-control midle" /></td>
									</tr>

									<tr>
										<td><fmt:message key="Position" bundle="${lang}" /></td>
										<td><input type="text" name="position"
											class="form-control midle"></td>
									</tr>
									<tr>
										<td><fmt:message key="Description" bundle="${lang}" /></td>
										<td><textarea class="form-control" rows="5"
												name="description" class="form-control midle"></textarea></td>
									</tr>

								</tbody>
							</table>

						</div>
						<!--/.nav-collapse -->
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="submit" class="btn btn-primary" name="action"
					value="editTariff">
					<fmt:message key="Edit_tariff" bundle="${lang}" />
				</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">
					<fmt:message key="Close" bundle="${lang}" />
				</button>
			</div>
		</div>
	</form>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->