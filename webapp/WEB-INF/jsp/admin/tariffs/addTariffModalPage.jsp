<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="modal fade" id="addTariffModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<form action="employeeControllerTariffs" method="post">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">
						<fmt:message key="Add_new_tariff" bundle="${lang}" />
					</h4>
				</div>
				<div class="modal-body">
					<div class="container">

						<div class="navbar navbar-default">
							<div class="navbar-collapse collapse">

								<c:if test="${addTarMessage != null}">
									<div id="errorinfo" class="alert alert-danger"
										style="display: none;">
										<fmt:message key="${addTarMessage}" bundle="${lang}" />
									</div>
								</c:if>
								<table class="table">
									<tbody>
										<tr>
											<td><fmt:message key="Name" bundle="${lang}" /></td>
											<td><input type="text" name="name"
												class="form-control first"
												placeholder=<fmt:message key="Name" bundle="${lang}" />
												autofocus="autofocus" /></td>
										</tr>

										<tr>
											<td><fmt:message key="Max_Capacity" bundle="${lang}" />
												(MB)</td>
											<td><input type="text" name="maxCapacity"
												class="form-control midle"
												placeholder=<fmt:message key="Max_Capacity" bundle="${lang}" /> /></td>
										</tr>

										<tr>
											<td><fmt:message key="Price" bundle="${lang}" /></td>
											<td><input type="text" name="price"
												class="form-control midle"
												placeholder=<fmt:message key="Price" bundle="${lang}" /> /></td>
										</tr>

										<tr>
											<td><fmt:message key="Position" bundle="${lang}" /></td>
											<td><input type="text" name="position"
												class="form-control midle"
												placeholder=<fmt:message key="Position" bundle="${lang}" /> /></td>
										</tr>

										<tr>
											<td><fmt:message key="Description" bundle="${lang}" />
												US</td>
											<td><textarea rows="2" name="descriptionUS"
													class="form-control midle"
													placeholder=<fmt:message key="Description" bundle="${lang}" />></textarea></td>
										</tr>
										<tr>
											<td><fmt:message key="Description" bundle="${lang}" />
												UA</td>
											<td><textarea rows="2" name="descriptionUA"
													class="form-control midle"
													placeholder=<fmt:message key="Description" bundle="${lang}" />></textarea></td>
										</tr>
										<tr>
											<td><fmt:message key="Description" bundle="${lang}" />
												RU</td>
											<td><textarea rows="2" name="descriptionRU"
													class="form-control last"
													placeholder=<fmt:message key="Description" bundle="${lang}" />
													autofocus="autofocus"></textarea></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-primary" name="action"
						value="addTariff">
						<fmt:message key="Add_new_tariff" bundle="${lang}" />
					</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">
						<fmt:message key="Close" bundle="${lang}" />
					</button>
				</div>
			</div>
		</form>
	</div>

</div>
